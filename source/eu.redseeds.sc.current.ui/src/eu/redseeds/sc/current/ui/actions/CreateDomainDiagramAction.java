package eu.redseeds.sc.current.ui.actions;

import java.io.IOException;
import java.util.Collections;

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
import org.eclipse.gmf.runtime.diagram.core.services.ViewService;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.emf.core.GMFEditingDomainFactory;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import rsldl.DLDiagram;
import rsldl.DLRepository;
import rsldl.RsldlFactory;
import rsldl.diagram.edit.parts.DLDiagramEditPart;
import rsldl.diagram.part.Messages;
import rsldl.diagram.part.RsldlDiagramEditorPlugin;
import rsldl.diagram.part.RsldlDiagramEditorUtil;

import eu.redseeds.common.Constants;
import eu.redseeds.common.DiagramFileHelper;
import eu.redseeds.sc.current.repository.SCProjectContainer;
import eu.remics.util.SCNavigatorHelper;

public class CreateDomainDiagramAction implements IWorkbenchWindowActionDelegate, IViewActionDelegate {

	String diagramName;
	
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
		if (diagramRoot == null) {
			MessageDialog.openError(Display.getCurrent().getActiveShell(),Messages.InitDiagramFile_ResourceErrorDialogTitle,Messages.InitDiagramFile_ResourceErrorDialogMessage);
			return;
		}
		InputDialog dlg = new InputDialog(Display.getCurrent().getActiveShell(),
	            "Domain Extended Diagram Name", "Enter Domain Extended Diagram name: ", null, null);
		if(dlg.open() != Window.OK || dlg.getValue().equals(""))
			return;
		else
			diagramName = dlg.getValue();
		final DLRepository repository = (DLRepository) diagramRoot;
		URI diagramModelURI = URI.createPlatformResourceURI(getDiagramModelFilePathName(), true);
		final Resource diagramResource = resourceSet.createResource(diagramModelURI);
		AbstractTransactionalCommand command = new AbstractTransactionalCommand(editingDomain,"Creating model",Collections.EMPTY_LIST) {
			protected CommandResult doExecuteWithResult(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
				DLDiagram diag = RsldlFactory.eINSTANCE.createDLDiagram();
				diag.setName(diagramName);
				repository.getDiagrams().add(diag);
				Diagram diagram = ViewService.createDiagram(
						diag,
						DLDiagramEditPart.MODEL_ID,
						RsldlDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);
				diagramResource.getContents().add(diagram);
				return CommandResult.newOKCommandResult();
			}
		};
		IProgressMonitor monitor = new NullProgressMonitor();
		try {
			OperationHistoryFactory.getOperationHistory().execute(command, new SubProgressMonitor(monitor, 1), null);
			modelResource.save(RsldlDiagramEditorUtil.getSaveOptions());
			diagramResource.save(RsldlDiagramEditorUtil.getSaveOptions());
			RsldlDiagramEditorUtil.openDiagram(diagramResource);
		} catch (ExecutionException e) {
			RsldlDiagramEditorPlugin.getInstance().logError(
					"Unable to save model and create diagram", e);
		} catch (IOException ex) {
			RsldlDiagramEditorPlugin.getInstance().logError(
					"Unable save model resource or store diagram resource", ex);
		} catch (PartInitException ex) {
			RsldlDiagramEditorPlugin.getInstance().logError(
					"Unable to open editor", ex);
		}
		SCNavigatorHelper.refresh();
	}
	
	public String getDiagramModelFilePathName(){
		return "/"+SCProjectContainer.instance().getSCProject(getProject()).getName()+"/"+Constants.CURRENT_SC_FOLDER_NAME+"/"+Constants.DOMAINDIAGRAMS_FOLDER_NAME+"/"+diagramName+".rsldl_diagram";
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
