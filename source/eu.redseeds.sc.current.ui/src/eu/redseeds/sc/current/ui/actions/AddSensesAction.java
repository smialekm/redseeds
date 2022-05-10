package eu.redseeds.sc.current.ui.actions;

import java.lang.reflect.InvocationTargetException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.progress.IProgressService;

import eu.redseeds.sc.terminology.model.RemoteJGWNL;
import eu.remics.recovery.model.domainlogic.usecases.MNotion;

public class AddSensesAction implements IWorkbenchWindowActionDelegate,
IViewActionDelegate{

	public AddSensesAction() {
	}

	@Override
	public void run(IAction action) {
		IWorkbench workbench = PlatformUI.getWorkbench();
    	IProgressService progressSevice = workbench.getProgressService();
    	IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();
    	Shell shell = window.getShell();
    	if(!RemoteJGWNL.getInstance().isConnected()) {
    		notConnectedMB(shell);
    		return;
		}
    	try {
			progressSevice.busyCursorWhile(new AddSensesJob());
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void notConnectedMB(Shell shell) {
		MessageBox warnMB = new MessageBox(shell, SWT.ICON_WARNING | SWT.OK );
		warnMB.setMessage("Connecting to the Terminology server failed. Some of application funtions may be unavailable. \nPlease use the \"ReDSeeDS -> Configure Terminology server connection\" menu option and specify valid terminology server.");
		warnMB.setText("Terminology connection problem");
		warnMB.open();
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		// TODO Auto-generated method stub
	}

	@Override
	public void init(IViewPart view) {
		// TODO Auto-generated method stub
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
	}

	@Override
	public void init(IWorkbenchWindow window) {
		// TODO Auto-generated method stub
	}
}

class AddSensesJob implements IRunnableWithProgress {
	
	private int getTotalTime() {
		return 100;
	}

	@Override
	public void run(IProgressMonitor monitor) throws InvocationTargetException,
			InterruptedException {
		monitor.beginTask("Adding senses for all terms", IProgressMonitor.UNKNOWN);
		for(int i=0; i < getTotalTime(); i++){
			Thread.sleep(i);
			monitor.worked(i);
		}
		MNotion.autoAsignSense();
		monitor.done();
	}
	
}
