package eu.redset.transformation.ui.actions;


import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.progress.IProgressService;
import org.eclipse.jface.dialogs.MessageDialog;

import eu.redseeds.sc.current.repository.SCProject;
import eu.redseeds.sc.current.repository.SCProjectContainer;
import eu.redset.transformation.engine.interfaces.ITransformationExecution;
import eu.redset.transformation.engine.impl.TransformtaionExecutionImpl;

/**
 * Our sample action implements workbench action delegate.
 * The action proxy will be created by the workbench and
 * shown in the UI. When the user tries to use the action,
 * this delegate will be created and execution will be 
 * delegated to it.
 * @see IWorkbenchWindowActionDelegate
 */
public class RunTransformationAction implements IWorkbenchWindowActionDelegate {
	
	private static String ID_PROJECT_EXPLORER = "eu.redseeds.engine.navigator.view";
	private IWorkbenchWindow window;
	private SCProject proj;
	/**
	 * The constructor.
	 */
	public RunTransformationAction() {
	}

	/**
	 * The action has been activated. The argument of the
	 * method represents the 'real' action sitting
	 * in the workbench UI.
	 * @see IWorkbenchWindowActionDelegate#run
	 */
	public void run(IAction action) {
		IWorkbench workbench = PlatformUI.getWorkbench();
    	IProgressService progressSevice = workbench.getProgressService();
    	//IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();
    	//Shell shell = window.getShell();
    	proj = getSCProject();
    	try {
			progressSevice.busyCursorWhile(new RunTransformationJob());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
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
		this.window = window;
	}
	
	private SCProject getSCProject(){

		SCProject proj;
		
		try {
	
			/********* retrieving the selected item ***************/
			
			// Receiving references to opened editors in active page
			PlatformUI.getWorkbench();
			 PlatformUI.getWorkbench().getActiveWorkbenchWindow();
			 PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
			IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
			
			// Receiving selected elements and their paths from Project Explorer
			IViewPart projectExplorer = ((IViewPart)activePage.findView(ID_PROJECT_EXPLORER));
			ITreeSelection select = (ITreeSelection)projectExplorer.getSite().getWorkbenchWindow().getSelectionService().getSelection();
			TreePath[] selectedPaths = select.getPaths();
			
			// Tests generation can be performed if only one element is selected
			if (selectedPaths.length != 1){
				throw new PartInitException("Tests generation can not be performed for current selection");
			}
			
			// Test generation can be performed if 2-level element is selected (sc or clipboard)
			if (selectedPaths[0].getSegmentCount() != 2){
				throw new PartInitException("Tests generation can not be performed for current selection");
			}
			
			proj = SCProjectContainer.instance().getSCProject(select.getFirstElement());
			
			return proj;
		
		} catch (PartInitException e) {
			MessageDialog.openInformation(window.getShell(),
					"Current Software Case Browser", e.toString());
		}
		
		return null;

	}
	
	
	class RunTransformationJob implements IRunnableWithProgress {
		
		private int getTotalTime() {
			return 100;
		}

		@Override
		public void run(IProgressMonitor monitor) throws InterruptedException {
			monitor.beginTask("Generating tests", IProgressMonitor.UNKNOWN);
			for(int i=0; i < getTotalTime(); i++){
				Thread.sleep(i);
				monitor.worked(i);
			}
			

			ITransformationExecution transfExec = new TransformtaionExecutionImpl();
			transfExec.execute(proj);
			//proj.saveTS();
			monitor.done();
		}
	}
}