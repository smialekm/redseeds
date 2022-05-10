package eu.redseeds.transformation.ui.actions;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreePath;
//import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeSelection;
//import org.eclipse.jface.viewers.TreePath;
//import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import eu.redseeds.common.SCProjectHelper;

import eu.redseeds.sc.current.repository.SCProject;
import eu.redseeds.sc.current.repository.SCProjectContainer;
import eu.redseeds.transformation.ui.Activator;
import eu.redseeds.transformation.ui.interfaces.ITransformationBrowserLogic;

/**
 * Our sample action implements workbench action delegate.
 * The action proxy will be created by the workbench and
 * shown in the UI. When the user tries to use the action,
 * this delegate will be created and execution will be 
 * delegated to it.
 * @see IWorkbenchWindowActionDelegate
 */
public class TransformationOpenAction implements IWorkbenchWindowActionDelegate {
	//private IWorkbenchWindow window;
	/**
	 * The constructor.
	 */
	public TransformationOpenAction() {
	}

	/**
	 * The action has been activated. The argument of the
	 * method represents the 'real' action sitting
	 * in the workbench UI.
	 * @see IWorkbenchWindowActionDelegate#run
	 */
	public void run(IAction action) {

		Activator.log("Transformation... action invoked", Status.INFO);
		SCProject scProj = getCurrentSCProject();
		if (scProj == null) {
			Activator.log("Can't locate current SCProject...", Status.ERROR);
			Activator.getStandardDisplay().syncExec(new Runnable() {
	             public void run() {
	            	 MessageDialog.openError(null, "Transformations", "Can't find selected project");
	             }});
			
			return;
		}
		ITransformationBrowserLogic transfBrowser  = Activator.getDefault().getITransformationBrowserLogicInstance();
		transfBrowser.openTransformationsBrowser();
	}

	/**
	 * Selection in the workbench has been changed. We 
	 * can change the state of the 'real' action here
	 * if we want, but this can only happen after 
	 * the delegate has been created.
	 * @see IWorkbenchWindowActionDelegate#selectionChanged
	 */
	public void selectionChanged(IAction action, ISelection selection) {
	}

	/**
	 * We can use this method to dispose of any system
	 * resources we previously allocated.
	 * @see IWorkbenchWindowActionDelegate#dispose
	 */
	public void dispose() {
	}

	/**
	 * We will cache window object in order to
	 * be able to provide parent shell for the message dialog.
	 * @see IWorkbenchWindowActionDelegate#init
	 */
	public void init(IWorkbenchWindow window) {
		//this.window = window;
	}
	
	private SCProject getCurrentSCProject() {
		
		//workaround for SCProjectHelper.getIProject(currSelection)
		ITreeSelection currSelection = (ITreeSelection) SCProjectHelper.getSelection();
		if (currSelection == null) return null;
		TreePath[] tr = currSelection.getPaths();
		if (tr == null || tr.length < 1) return null;
		// ------------
		
		IProject project = SCProjectHelper.getIProject(currSelection);
		return SCProjectContainer.instance().getSCProject(project);
		
/*		
		if (window == null) {
			Activator.log("Can't locate IWorkbenchWindow...", Status.ERROR);
			return null;
		}

		IWorkbenchPage activePage = window.getActivePage();
		if (activePage == null) {
			Activator.log("Can't locate IWorkbenchPage...", Status.ERROR);
			return null;
		}
		
		ISelection selection = activePage.getSelection();
		if (selection == null) {
			Activator.log("Can't get ISelection...", Status.ERROR);
			return null;
		}

		Object selectedElement = ((IStructuredSelection)selection).getFirstElement();
		if (selectedElement == null) {
			Activator.log("Can't get selectedElement...", Status.ERROR);
			return null;
		}

		ITreeSelection treeSelection = (ITreeSelection)selection;
		if (selection == null) {
			Activator.log("Can't get ITreeSelection...", Status.ERROR);
			return null;
		}

		TreePath[] paths = treeSelection.getPathsFor(selectedElement);
		IProject project = null;
		if (paths.length > 0){
			if (paths[0].getFirstSegment() instanceof IProject){
				project = (IProject)paths[0].getFirstSegment();
			} else return null;
		} else return null;
		
		return SCProjectContainer.instance().getSCProject(project);
*/
	}
	
}