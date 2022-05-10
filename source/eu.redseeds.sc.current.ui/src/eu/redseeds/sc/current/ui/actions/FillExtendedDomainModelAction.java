package eu.redseeds.sc.current.ui.actions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.OperationHistoryFactory;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.emf.core.GMFEditingDomainFactory;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.PlatformUI;

import eu.redseeds.common.DiagramFileHelper;
import eu.redseeds.sc.current.repository.SCProject;
import eu.redseeds.sc.current.repository.SCProjectContainer;
import eu.redseeds.scl.model.BusinessLayerFacade;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.AttributeDataTypesEnum;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionTypesEnum;
import eu.redseeds.scl.rsl.rsldomainelements.notions.Notion;
import rsldl.DLDiagram;
import rsldl.DLNotion;
import rsldl.DLProperty;
import rsldl.DLPropertyValueType;
import rsldl.DLRepository;
import rsldl.DLTypeRole;
import rsldl.RsldlFactory;
import rsldl.diagram.part.RsldlDiagramEditorPlugin;
import rsldl.diagram.part.RsldlDiagramEditorUtil;

public class FillExtendedDomainModelAction implements IWorkbenchWindowActionDelegate, IViewActionDelegate {

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
		final DLRepository repository = (DLRepository) diagramRoot;
		final List<NotionDTO> dn = new ArrayList<NotionDTO>();
		BusinessLayerFacade bf = SCProjectContainer.instance().getSCProject(getProject()).getBusinessLayerFacade();
		for (Notion n:bf.getNotionVertices())
			if (((NotionDTO) n).isDomainNotion())
				dn.add((NotionDTO) n);
		AbstractTransactionalCommand command = new AbstractTransactionalCommand(editingDomain,"Filling model",Collections.EMPTY_LIST) {
			protected CommandResult doExecuteWithResult(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
				for (NotionDTO n:dn)
					if (null==repository.getNotion(n.getName())) {
						DLNotion dn = null;
						//boolean v = false;
						if (n.getType().isEmpty()) {
							dn = RsldlFactory.eINSTANCE.createDLEntity();
							dn.setType(DLTypeRole.IDENTITY);
						} else switch(NotionTypesEnum.getNotionTypeByTag(n.getType())){
							case Simple_View:
								/*dn = RsldlFactory.eINSTANCE.createDLEntity();
								dn.setType(DLTypeRole.TEMPORARY_ROLE);
								v = true;*/
								break;
							case Attribute:
								dn = RsldlFactory.eINSTANCE.createDLProperty();
								dn.setType(DLTypeRole.IDENTITY);
								switch(AttributeDataTypesEnum.getAttributeDataTypeByTag(n.getExtendedDataType())) {
									case Boolean:
										((DLProperty) dn).setValueType(DLPropertyValueType.BOOLEAN);
										break;
									case Date:
										((DLProperty) dn).setValueType(DLPropertyValueType.DATE);
										break;
									case Description:
										((DLProperty) dn).setValueType(DLPropertyValueType.STRING);
										break;
									case Float:
										((DLProperty) dn).setValueType(DLPropertyValueType.FLOAT);
										break;
									case Integer:
										((DLProperty) dn).setValueType(DLPropertyValueType.INTEGER);
										break;
									case Password:
										((DLProperty) dn).setValueType(DLPropertyValueType.STRING);
										break;
									case String:
										((DLProperty) dn).setValueType(DLPropertyValueType.STRING);
										break;
									case Time:
										((DLProperty) dn).setValueType(DLPropertyValueType.TIME);
										break;
									default:
										break;
								}
								break;
							case List_View:
								/*dn = RsldlFactory.eINSTANCE.createDLProperty();
								dn.setType(DLTypeRole.TEMPORARY_ROLE);
								((DLProperty) dn).setValueType(DLPropertyValueType.SET);
								v=true;*/
								break;
							default:
								break;
						}
						if (null==dn)
							continue;
						dn.setName(n.getName());
						if(null==n.getParent().getParent() /*|| v*/) {
							repository.getRelationshipParticipants().add(dn);
						} else {
							DLDiagram d = repository.getDiagram(n.getParent().getName());
							if (null==d) {
								d = RsldlFactory.eINSTANCE.createDLDiagram();
								d.setName(n.getParent().getName());
								repository.getDiagrams().add(d);
							}
							d.getRelationshipParticipants().add(dn);
						}
					}
				return CommandResult.newOKCommandResult();
			}
		};
		IProgressMonitor monitor = new NullProgressMonitor();
		try {
			OperationHistoryFactory.getOperationHistory().execute(command, new SubProgressMonitor(monitor, 1), null);
			modelResource.save(RsldlDiagramEditorUtil.getSaveOptions());
		} catch (ExecutionException e) {
			RsldlDiagramEditorPlugin.getInstance().logError(
					"Unable to save model", e);
		} catch (IOException ex) {
			RsldlDiagramEditorPlugin.getInstance().logError(
					"Unable save model resource ", ex);
		}
	}
	
	private IProject getProject() {
		Object elem = ((ITreeSelection)PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService().getSelection()).getFirstElement();
		if (elem instanceof IFile)
			return ((IFile) elem).getProject();
		return SCProjectContainer.instance().getSCProject(elem).getEclipseProject();
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
