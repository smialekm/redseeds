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
import org.eclipse.ui.PlatformUI;

import rsldl.DLDomain;
import rsldl.DLRepository;
import rsldl.RsldlFactory;
import rsldl.diagram.part.RsldlDiagramEditorPlugin;
import rsldl.diagram.part.RsldlDiagramEditorUtil;
import eu.redseeds.common.DiagramFileHelper;
import eu.redseeds.sc.current.repository.SCProjectContainer;

public class AddDomainAction implements IWorkbenchWindowActionDelegate, IViewActionDelegate {

	String domainName;
	
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
		List<String> domains = new ArrayList<String>();
		for(DLDomain d:repository.getDomains())
			if(null!=d.getName())
				domains.add(d.getName());
		InputDialog dlg = new InputDialog(Display.getCurrent().getActiveShell(),
	            "Domain Name", "Enter Domain name: ", null, null);
		if(dlg.open() != Window.OK || dlg.getValue().equals(""))
			return;
		else
			domainName = dlg.getValue().trim();
		if (domains.contains(domainName)){
			MessageDialog.openError(Display.getCurrent().getActiveShell(), "Identical names", "Extended domain diagram with identical name already exists! Please rename it.");
			return;
		}
		AbstractTransactionalCommand command = new AbstractTransactionalCommand(editingDomain,"Adding new domain",Collections.EMPTY_LIST) {
			protected CommandResult doExecuteWithResult(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
				DLDomain dmn = RsldlFactory.eINSTANCE.createDLDomain();
				dmn.setName(domainName);
				repository.getDomains().add(dmn);
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
