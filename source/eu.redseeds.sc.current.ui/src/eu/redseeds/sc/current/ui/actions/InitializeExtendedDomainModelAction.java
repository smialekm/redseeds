package eu.redseeds.sc.current.ui.actions;

import java.io.IOException;
import java.util.Collections;

import notiondiagram.diagram.part.NotionDiagramDiagramEditorPlugin;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.OperationHistoryFactory;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.emf.core.GMFEditingDomainFactory;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.PlatformUI;

import rsldl.DLDiagram;
import rsldl.RsldlFactory;
import rsldl.diagram.part.RsldlDiagramEditorPlugin;
import rsldl.diagram.part.RsldlDiagramEditorUtil;

import eu.redseeds.common.Constants;
import eu.redseeds.common.DiagramFileHelper;
import eu.redseeds.sc.current.repository.SCProject;
import eu.redseeds.sc.current.repository.SCProjectContainer;
import eu.remics.util.SCNavigatorHelper;

public class InitializeExtendedDomainModelAction implements IWorkbenchWindowActionDelegate, IViewActionDelegate {

	@Override
	public void run(IAction action) {
		IFile edm = DiagramFileHelper.getExtendedDomainModel(getSCProject().getEclipseProject());
		if(null!=edm){
			MessageDialog.openError(Display.getCurrent().getActiveShell(), "RSL-DL model already initialized", "Extended domain model already exist.");
			return;
		}
		URI modelURI = URI.createPlatformResourceURI(getDomainModelFilePathName(), false);
		TransactionalEditingDomain editingDomain = GMFEditingDomainFactory.INSTANCE.createEditingDomain();
		final Resource modelResource = editingDomain.getResourceSet().createResource(modelURI);
		AbstractTransactionalCommand command = new AbstractTransactionalCommand(editingDomain,"Creating model",Collections.EMPTY_LIST) {
			protected CommandResult doExecuteWithResult(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
				DLDiagram model = RsldlFactory.eINSTANCE.createDLRepository();
				modelResource.getContents().add(model);
				try {
					modelResource.save(RsldlDiagramEditorUtil.getSaveOptions());
				} catch (IOException e) {
					RsldlDiagramEditorPlugin.getInstance().logError("Unable to store model resource", e);
				}
				return CommandResult.newOKCommandResult();
			}
		};
		IProgressMonitor monitor = new NullProgressMonitor();
		try {
			OperationHistoryFactory.getOperationHistory().execute(command, new SubProgressMonitor(monitor, 1), null);
		} catch (ExecutionException e) {
			RsldlDiagramEditorPlugin.getInstance().logError("Unable to create model", e);
		}
		IFile file = WorkspaceSynchronizer.getFile(modelResource);
		if (file != null) {
			try {
				file.setCharset("UTF-8", new NullProgressMonitor());
			} catch (CoreException e) {
				NotionDiagramDiagramEditorPlugin.getInstance().logError("Unable to set charset for file " + file.getFullPath(), e);
			}
		}
		SCNavigatorHelper.refresh();
	}
	
	public String getDomainModelFilePathName(){
		return "/"+getSCProject().getName()+"/"+Constants.CURRENT_SC_FOLDER_NAME+"/"+Constants.DOMAINDIAGRAMS_FILE_NAME;
	}
	
	private SCProject getSCProject() {
		Object elem = ((ITreeSelection)PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService().getSelection()).getFirstElement();
		SCProject scproj = SCProjectContainer.instance().getSCProject(elem);
		return scproj;
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
