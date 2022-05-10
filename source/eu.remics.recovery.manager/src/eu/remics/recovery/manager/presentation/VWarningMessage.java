package eu.remics.recovery.manager.presentation;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;

import eu.remics.recovery.manager.applogic.scenarios.CDeleteScenario;
/**
 * @author Stacja1
 * @version 1.0
 * @created 04-lis-2011 12:21:16
 */
public class VWarningMessage {

	CDeleteScenario nDeleteScenario;
	public CDeleteScenario cDeleteScenario;

 
	public VWarningMessage(){
		
	}

	public void finalize() throws Throwable {
		super.finalize();
	}

	public int displays(){
		Shell shell = new Shell();
		shell.forceActive();
		boolean r = MessageDialog.openQuestion(shell, "Delete confirmation", "Are you sure you want to delete?");
		int res = r ? 0 : 1;
		return res;
	}

	public void closes(){
		
	}

}
