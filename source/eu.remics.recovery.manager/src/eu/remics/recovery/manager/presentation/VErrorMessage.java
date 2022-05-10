package eu.remics.recovery.manager.presentation;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;

/**
 * @author Stacja1
 * @version 1.0
 * @created 04-lis-2011 12:21:13
 */
public class VErrorMessage {
	
	public VErrorMessage(){

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

	public void closes(){
		
	}

	public void displays(String msg){
		Shell shell = new Shell();
		shell.forceActive();
		MessageDialog.openError(shell, "Error", msg);
	}

}