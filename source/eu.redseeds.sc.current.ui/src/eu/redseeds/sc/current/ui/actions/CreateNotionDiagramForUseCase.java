package eu.redseeds.sc.current.ui.actions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.PlatformUI;

import eu.redseeds.common.Constants;
import eu.redseeds.sc.current.repository.SCProject;
import eu.redseeds.sc.current.repository.SCProjectContainer;
import eu.redseeds.scl.model.BusinessLayerFacade;
import eu.redseeds.scl.model.rsl.rsldomainelements.domainelements.DomainElementRelationshipDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionTypesEnum;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionsPackageDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.ComplexVerbPhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.SimpleVerbPhraseDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentations.descriptiverequirementrepresentations.ConstrainedLanguageScenarioDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.constrainedlanguagesentences.ConstrainedLanguageSentenceDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.controlsentences.InvocationSentenceDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.svosentences.SVOSentenceDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.UseCaseDTO;
import eu.redseeds.scl.rsl.rsldomainelements.notions.NotionsPackage;
import eu.remics.util.SCNavigatorHelper;
import notiondiagram.Notion;
import notiondiagram.NotionDiagram;
import notiondiagram.NotiondiagramFactory;
import notiondiagram.diagram.part.NotionDiagramDiagramEditorUtil;

public class CreateNotionDiagramForUseCase implements IWorkbenchWindowActionDelegate,
IViewActionDelegate{
	
	protected Resource diagram;
	String diagramName;

	@Override
	public void run(IAction action) {
		
		IProgressMonitor monitor = new NullProgressMonitor();
		UseCaseDTO useCase = (UseCaseDTO)((ITreeSelection)PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService().getSelection()).getFirstElement();
		NotionsPackageDTO notionPack = SCProjectContainer.instance().getSCProject(useCase).getMainCase().getRequirementsSpecificationDTO().getDomainSpecificationDTO().getNotionsPackageDTOList().get(0);
		final int notionPackId = ((NotionsPackage) notionPack).getId();
		diagramName = useCase.getName();
		
		if(searchForIdenticalNamesInNotionPackages(notionPack))
			return;
		
		BusinessLayerFacade facade = SCProjectContainer.instance().getSCProject(useCase).getBusinessLayerFacade();
		final List<NotionDTO> notions = new ArrayList<NotionDTO>();
		for(ConstrainedLanguageScenarioDTO cls:useCase.getConstrainedLanguageScenarioDTOList())
			for(ConstrainedLanguageSentenceDTO snt:cls.getScenarioSentenceList()) {
				if (snt instanceof SVOSentenceDTO && null!=((SVOSentenceDTO) snt).getPredicate()) {
					if (((SVOSentenceDTO) snt).getPredicate() instanceof ComplexVerbPhraseDTO){
						addNotionToList(facade.getNotionDTO(((ComplexVerbPhraseDTO) ((SVOSentenceDTO) snt).getPredicate()).getSimpleVerbPhrase().getObject().getNoun()),notions);
						addNotionToList(facade.getNotionDTO(((ComplexVerbPhraseDTO) ((SVOSentenceDTO) snt).getPredicate()).getObject().getNoun()),notions);
					} else
						addNotionToList(facade.getNotionDTO(((SimpleVerbPhraseDTO) ((SVOSentenceDTO) snt).getPredicate()).getObject().getNoun()),notions);
				} else if (snt instanceof InvocationSentenceDTO && ((InvocationSentenceDTO) snt).getInvokedUseCase().getConstrainedLanguageScenarioDTOList().get(0).getScenarioSentenceList().size()>1) {
					ConstrainedLanguageSentenceDTO svos = ((InvocationSentenceDTO) snt).getInvokedUseCase().getConstrainedLanguageScenarioDTOList().get(0).getScenarioSentenceList().get(1);
					if (svos instanceof SVOSentenceDTO && null!=((SVOSentenceDTO) svos).getPredicate())
						addNotionToList(facade.getNotionDTO(((((SVOSentenceDTO) svos).getPredicate() instanceof SimpleVerbPhraseDTO)?((SVOSentenceDTO) svos).getPredicate().getObject():((ComplexVerbPhraseDTO) ((SVOSentenceDTO) svos).getPredicate()).getSimpleVerbPhrase().getObject()).getNoun()),notions);
				}
			}
		
		URI diagramModelFileURI = URI.createPlatformResourceURI(getDiagramModelFilePathName(), false);
		URI domainModelFileURI = URI.createPlatformResourceURI(getDomainModelFilePathName(), false);
		
		diagram = NotionDiagramDiagramEditorUtil.createDiagram(
				diagramModelFileURI,
				domainModelFileURI, monitor);
		
		final NotionDiagram notiondiagram = (NotionDiagram) ((Diagram)diagram.getContents().get(0)).getElement();
		TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(diagram);
		editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {
			
			@Override
			protected void doExecute() {
				notiondiagram.setPackage(notionPackId);
				notiondiagram.setName(diagramName);
				for (NotionDTO n:notions) {
					Notion newElement = NotiondiagramFactory.eINSTANCE.createNotion();
					newElement.setId(((eu.redseeds.scl.rsl.rsldomainelements.notions.Notion) n).getId());
					notiondiagram.getNotions().add(newElement);
				}
				try {
					for(Resource res : diagram.getResourceSet().getResources())
						res.save(NotionDiagramDiagramEditorUtil
							.getSaveOptions());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		
		SCNavigatorHelper.refresh();
	}
	
	private void addNotionToList(NotionDTO n, List<NotionDTO> notions) {
		if (null==n || notions.contains(n))
			return;
		notions.add(n);
		if (!Arrays.asList(NotionTypesEnum.viewTags()).contains(n.getType()))
			return;
		for(DomainElementRelationshipDTO r:n.getDomainElementRelationshipDTOList())
			if (r.isDirected() && n.equals(r.getSource()) && r.getTarget() instanceof NotionDTO && (((NotionDTO) r.getTarget()).getType().isEmpty() || NotionTypesEnum.Attribute.tag().equals(((NotionDTO) r.getTarget()).getType()))) {
				if (!notions.contains(r.getTarget()))
					notions.add((NotionDTO) r.getTarget());
				if(((NotionDTO) r.getTarget()).getType().isEmpty()) {
					for(NotionDTO na:((NotionDTO) r.getTarget()).getNotionDTOAttributeList())
						if (!notions.contains(na))
							notions.add(na);
				} else for (NotionDTO p:((NotionDTO) r.getTarget()).getNotionAttributeParents())
					if (!notions.contains(p))
						notions.add(p);
			}
	}
	
	public String getDiagramModelFilePathName(){
		return "/"+getProjectName()+"/"+Constants.CURRENT_SC_FOLDER_NAME+"/"+Constants.NOTIONSDIAGRAMS_FOLDER_NAME+"/"+diagramName+".notiondiagram_diagram";
	}
	
	public String getDomainModelFilePathName(){
		return "/"+getProjectName()+"/"+Constants.CURRENT_SC_FOLDER_NAME+"/"+Constants.NOTIONSDIAGRAMS_FOLDER_NAME+"/"+diagramName+".notiondiagram";
	}
	
	private String getProjectName() {
		Object elem = ((ITreeSelection)PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService().getSelection()).getFirstElement();
		SCProject scproj = SCProjectContainer.instance().getSCProject(elem);
		return scproj.getName();
	}
	
	public boolean searchForIdenticalNamesInNotionPackages(NotionsPackageDTO notionPack){
		List<NotionsPackageDTO> activeProjectNotionPackages = SCProjectContainer.instance().getSCProject(notionPack).getMainCase().getRequirementsSpecificationDTO().getDomainSpecificationDTO().getNotionsPackageDTOList();
		for(NotionsPackageDTO elem : activeProjectNotionPackages){
			ArrayList<IFile> diagrams = elem.getNotionsDiagrams();
			if(!diagrams.isEmpty()){
				String curr = diagramName+".notiondiagram_diagram";
				for(IFile obj : diagrams){
					if(curr.equals(obj.getName()))
						return true;
				}
			}
		}
		for(NotionsPackageDTO elem : activeProjectNotionPackages){
			if(!elem.getNotionsPackageDTOList().isEmpty()){
				if(!searchByRecursion(elem)) continue;
				else{
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean searchByRecursion(NotionsPackageDTO notionPackage){
		for(NotionsPackageDTO elem : notionPackage.getNotionsPackageDTOList()){
			ArrayList<IFile> diagrams = elem.getNotionsDiagrams();
			if(!diagrams.isEmpty()){
				String curr = diagramName+".notiondiagram_diagram";
				for(IFile obj : diagrams){
					if(curr.equals(obj.getName()))
						return true;
				}
			}
		}
		for(NotionsPackageDTO elem : notionPackage.getNotionsPackageDTOList()){
			if(!elem.getNotionsPackageDTOList().isEmpty())
				return searchByRecursion(elem);
		}
		return false;
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		
	}

	@Override
	public void init(IViewPart view) {
		
	}

	@Override
	public void dispose() {
		
	}

	@Override
	public void init(IWorkbenchWindow window) {
		
	}

}
