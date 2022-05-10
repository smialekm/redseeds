package eu.remics.script.loader.presentation;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;

import eu.remics.script.loader.applogic.CLoadScripts;

/**
 * @author Stacja1
 * @version 1.0
 * @created 04-lis-2011 12:21:14
 */
public class VFolderWithMapFilesOrEmptyNotionMessage {

	public CLoadScripts cLoadScripts;



	public VFolderWithMapFilesOrEmptyNotionMessage(){

	}

	@Override
	public void finalize() throws Throwable {
		super.finalize();
	}

	public int displays(){
		Shell shell = new Shell();
		shell.forceActive();
		boolean r = MessageDialog.openQuestion(shell, "Select map file confirmation", "Would you like to select map file for script? If No - empty notion will be created");
		int res = r ? 0 : 1;

		return res;
	}

}