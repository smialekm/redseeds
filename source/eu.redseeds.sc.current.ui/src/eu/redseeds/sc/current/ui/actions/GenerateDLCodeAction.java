package eu.redseeds.sc.current.ui.actions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.emf.core.GMFEditingDomainFactory;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.PlatformUI;

import rsldl.DLRepository;
import rsldl.diagram.part.RsldlDiagramEditorPlugin;
import eu.redseeds.common.Constants;
import eu.redseeds.common.DiagramFileHelper;
import eu.redseeds.common.QueriesHelper;
import eu.redseeds.rsldl.engine.JavaDLTransformationEngine;
import eu.redseeds.rsldl.engine.SymjaAdapter;
import eu.redseeds.rsldl.engine.code.GMethod;
import eu.redseeds.rsldl.engine.structure.DLTransformationEngine;
import eu.redseeds.rsldl.engine.structure.IDLSymbolicEngine;
import eu.redseeds.sc.current.repository.SCProject;
import eu.redseeds.sc.current.repository.SCProjectContainer;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.DomainStatementDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.ComplexVerbPhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.SimpleVerbPhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.VerbPhraseDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentations.descriptiverequirementrepresentations.ConstrainedLanguageScenarioDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.constrainedlanguagesentences.ConstrainedLanguageSentenceDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.controlsentences.PreconditionSentenceDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.svosentences.SVOSentenceDTO;
import eu.redseeds.scl.sclkernel.SCLElement;
import eu.remics.util.SCNavigatorHelper;

public class GenerateDLCodeAction implements IWorkbenchWindowActionDelegate, IViewActionDelegate {

	@Override
	public void run(IAction action) {
		IFile edm = DiagramFileHelper.getExtendedDomainModel(getProject());
		URI domainModelURI = URI.createPlatformResourceURI(edm.getFullPath().toString(), true);
		TransactionalEditingDomain editingDomain = GMFEditingDomainFactory.INSTANCE.createEditingDomain();
		ResourceSet resourceSet = editingDomain.getResourceSet();
		EObject diagramRoot = null;
		Resource modelResource = null;
		try {
			modelResource = resourceSet.getResource(domainModelURI, true);
			diagramRoot = (EObject) modelResource.getContents().get(0);
		} catch (WrappedException ex) {
			RsldlDiagramEditorPlugin.getInstance().logError("Unable to load resource: " + domainModelURI, ex); //$NON-NLS-1$
		}
		if (diagramRoot == null)
			return;
		DLTransformationEngine engine = new JavaDLTransformationEngine();
		engine.setRepository((DLRepository) diagramRoot);
		IDLSymbolicEngine se = new SymjaAdapter();
		engine.setSymbolicEngine(se);
		for (DomainStatementDTO ds:getSCProject().getBusinessLayerFacade().getProcessableDomainDomainStatements()){
			String[] p = QueriesHelper.getParameters(ds.getDescription());
			if(null!=p[0] && null!=p[1])
				switch(ds.getActionType()){
					case Query:
						Map<Integer, String[]> bsn = getBasedOn(ds,p[1]);
						Map<Integer, String> cl = new HashMap<Integer, String>();
						for(Integer b:bsn.keySet()){
							GMethod m = engine.solveQuery(p[1], bsn.get(b), p[2]);
							cl.put(b, null!=m?m.toSimplifiedString():null);
						}
						ds.setDescription(QueriesHelper.getRSLDLDescription(p[0], p[1], p[2], cl)+QueriesHelper.getDescription(ds.getDescription()));
						break;
					case Transform:
						if(ds.getPhraseDTO() instanceof SimpleVerbPhraseDTO){
							//TODO
							break;
						}
						String n2 = p[1].equals(((ComplexVerbPhraseDTO) ds.getPhraseDTO()).getObject().getNoun().getName())?((ComplexVerbPhraseDTO) ds.getPhraseDTO()).getSimpleVerbPhrase().getObject().getNoun().getName():((ComplexVerbPhraseDTO) ds.getPhraseDTO()).getObject().getNoun().getName();		
						GMethod m = engine.solveTransform(p[1], n2, p[2]);
						ds.setDescription(QueriesHelper.getRSLDLDescription(p[0], p[1], p[2], m.toSimplifiedString())+QueriesHelper.getDescription(ds.getDescription()));
						break;
					case Create:
					case Read:
					case Update:
					case Delete:
					case Validate:
						engine.solveCRUD(p[1], p[2], getSCProject().getBusinessLayerFacade());
						break;
					default:
						break;
				}
		}
		try {
			engine.generateFilesForResult(getCodeFolderPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
		SCNavigatorHelper.refresh();
	}
	
	private String getCodeFolderPath(){
		return ResourcesPlugin.getWorkspace().getRoot().getLocation().toString()+getProject().getFolder(Constants.CURRENT_SC_FOLDER_NAME).getFolder(Constants.CODE_FOLDER_NAME).getFolder(Constants.DL_CODE_FOLDER_NAME).getFullPath().toString();
	}
	
	private IProject getProject() {
		Object elem = ((ITreeSelection)PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService().getSelection()).getFirstElement();
		if (elem instanceof IFile)
			return ((IFile) elem).getProject();
		return SCProjectContainer.instance().getSCProject(elem).getEclipseProject();
	}
	
	private SCProject getSCProject() {
		Object elem = ((ITreeSelection)PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService().getSelection()).getFirstElement();
		if (elem instanceof IFile)
			return SCProjectContainer.instance().getSCProject(((IFile) elem).getProject());
		return SCProjectContainer.instance().getSCProject(elem);
	}
	
	private Map<Integer, String[]> getBasedOn(DomainStatementDTO ds, String what){
		if (!(ds.getPhraseDTO() instanceof VerbPhraseDTO))
			return null;
		Map<Integer, String[]> bsn = new HashMap<Integer, String[]>();
		for(ConstrainedLanguageScenarioDTO cls:getSCProject().getBusinessLayerFacade().getScenariosByVerbPhrase((VerbPhraseDTO) ds.getPhraseDTO())){
			List<String> notions = new ArrayList<String>();
			if (ds.getPhraseDTO() instanceof ComplexVerbPhraseDTO){
				if (what.equals(((ComplexVerbPhraseDTO) ds.getPhraseDTO()).getObject().getNoun().getName()))
					notions.add(((ComplexVerbPhraseDTO) ds.getPhraseDTO()).getSimpleVerbPhrase().getObject().getNoun().getName());
				else notions.add(((ComplexVerbPhraseDTO) ds.getPhraseDTO()).getObject().getNoun().getName());
			}
			for(ConstrainedLanguageSentenceDTO snt:cls.getScenarioSentenceList())
				if (snt instanceof SVOSentenceDTO) {
					if (!((SVOSentenceDTO) snt).getPredicate().equals(ds.getPhraseDTO())){
						if (((SVOSentenceDTO) snt).getPredicate() instanceof ComplexVerbPhraseDTO){
							if(getSCProject().getBusinessLayerFacade().getNotionDTO(((ComplexVerbPhraseDTO) ((SVOSentenceDTO) snt).getPredicate()).getSimpleVerbPhrase().getObject().getNoun()).isDomainNotion() && !notions.contains(((ComplexVerbPhraseDTO) ((SVOSentenceDTO) snt).getPredicate()).getSimpleVerbPhrase().getObject().getNoun().getName()))
								notions.add(((ComplexVerbPhraseDTO) ((SVOSentenceDTO) snt).getPredicate()).getSimpleVerbPhrase().getObject().getNoun().getName());
							if(getSCProject().getBusinessLayerFacade().getNotionDTO(((ComplexVerbPhraseDTO) ((SVOSentenceDTO) snt).getPredicate()).getObject().getNoun()).isDomainNotion() && !notions.contains(((ComplexVerbPhraseDTO) ((SVOSentenceDTO) snt).getPredicate()).getObject().getNoun().getName()))
								notions.add(((ComplexVerbPhraseDTO) ((SVOSentenceDTO) snt).getPredicate()).getObject().getNoun().getName());
						} else
							if(getSCProject().getBusinessLayerFacade().getNotionDTO(((SimpleVerbPhraseDTO) ((SVOSentenceDTO) snt).getPredicate()).getObject().getNoun()).isDomainNotion() && !notions.contains(((SimpleVerbPhraseDTO) ((SVOSentenceDTO) snt).getPredicate()).getObject().getNoun().getName()))
								notions.add(((SimpleVerbPhraseDTO) ((SVOSentenceDTO) snt).getPredicate()).getObject().getNoun().getName());
					} else
						break;
				} else if (snt instanceof PreconditionSentenceDTO){
					String[] s = ((PreconditionSentenceDTO) snt).getSentenceText().split("<param>|</param><param>|</param>");
					for (String n:Arrays.asList(s))
						if (!notions.contains(n))
							notions.add(n);
				}
			bsn.put(((SCLElement) cls).getId(), notions.toArray(new String[notions.size()]));
		}
		return bsn;
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
