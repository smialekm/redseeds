package eu.redseeds.sc.current.ui.actions;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.progress.IProgressService;

import eu.redseeds.common.SCProjectHelper;
import eu.redseeds.ea.converter.interfaces.IConversions;
import eu.redseeds.sc.current.repository.SCProject;
import eu.redseeds.sc.current.repository.SCProjectContainer;
import eu.redseeds.sc.current.ui.Activator;

public class TransferRequirementsAction implements IWorkbenchWindowActionDelegate,
IViewActionDelegate {

	private IConversions eaConversions;

	public TransferRequirementsAction(){
		eaConversions = Activator.getDefault().getIConversionsInstance();
	}
	
	@Override
	public void run(IAction action) {
		final SCProject currSCProject = getCurrentSCProject();
		if(currSCProject == null){
			Activator.log("Can't locate current SCProject...", Status.ERROR);
			MessageBox confirmMB = new MessageBox(SCProjectHelper.getShell(), SWT.ICON_ERROR);
			confirmMB.setMessage("Can't find selected project!");
			confirmMB.open();
			return;
		}
		
		final IWorkbench workbench = PlatformUI.getWorkbench();
    	IProgressService progressSevice = workbench.getProgressService();
    	try {
    		progressSevice.busyCursorWhile(new IRunnableWithProgress() {
				
				@Override
				public void run(IProgressMonitor monitor) throws InvocationTargetException {
					monitor.beginTask("Transfer requirements to CASE tool", 100);
					
					eaConversions.convertRequirementsToEA(currSCProject, false);
					
					monitor.done();
				}
			});
    	} catch (InvocationTargetException e) {
    		e.printStackTrace();
    	} catch (InterruptedException e) {
			e.printStackTrace();
		}
			
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

	private SCProject getCurrentSCProject() {
		ITreeSelection currSelection = (ITreeSelection) SCProjectHelper.getSelection();
		if (currSelection == null){
			return null;
		}
		TreePath[] tr = currSelection.getPaths();
		if (tr == null || tr.length < 1)
			return null;
		IProject project = SCProjectHelper.getIProject(currSelection);
		return SCProjectContainer.instance().getSCProject(project);
	}
	
}
