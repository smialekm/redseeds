package eu.remics.script.loader.presentation;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;

import eu.remics.script.loader.applogic.CLoadScripts;
/**
 * @author Stacja1
 * @version 1.0
 * @created 04-lis-2011 12:21:12
 */
public class VContinueOrStopProcessingMessage {

	CLoadScripts nLoadScripts;


	public CLoadScripts cLoadScripts;


	public VContinueOrStopProcessingMessage(){

	}

	@Override
	public void finalize() throws Throwable {
		super.finalize();
	}

	public int displays(){
		Shell shell = new Shell();
		shell.forceActive();
		boolean r = MessageDialog.openQuestion(shell, "Continue confirmation", "Map file for script not found. Would you like to continue processing scripts?");
		int res = r ? 0 : 1;
		
		return res;
	}

}