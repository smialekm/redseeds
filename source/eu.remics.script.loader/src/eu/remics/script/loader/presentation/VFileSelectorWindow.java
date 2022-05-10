package eu.remics.script.loader.presentation;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

import eu.redseeds.sc.terminology.model.RemoteJGWNL;
import eu.remics.common.RecoveryManagerHelper;
import eu.remics.recovery.manager.views.UnassignedScenariosView;
import eu.remics.script.loader.applogic.CLoadScripts;
import eu.remics.script.loader.dialogs.ScriptFileDialog;
import eu.remics.script.loader.dialogs.ScriptMapsFolderDialog;

/**
 * @author Stacja1
 * @version 1.0
 * @created 04-lis-2011 12:21:13
 */
public class VFileSelectorWindow {

	ScriptFileDialog scriptFileDialog = new ScriptFileDialog();
	CLoadScripts nLoadScripts;
	public CLoadScripts cLoadScripts;

	@Override
	public void finalize() throws Throwable {
		super.finalize();
	}

	public String[] displays(){
		if(!RemoteJGWNL.getInstance().isConnected()) {
			MessageBox warnMB = new MessageBox(new Shell(), 
					SWT.ICON_WARNING | SWT.OK );

			warnMB.setMessage("Connecting to the Terminology server failed. Some of application funtions may be unavailable. \nPlease use the \"ReDSeeDS -> Configure Terminology server connection\" menu option and specify valid terminology server.");
			warnMB.setText("Terminology connection problem");
			warnMB.open();
        }
		else{
			scriptFileDialog.choose();
			return scriptFileDialog.getSelectedPaths();
		}
		return null;
	}
	
	public String displaysFolderSelector(){
		ScriptMapsFolderDialog dlg = new ScriptMapsFolderDialog(/*new Shell()*/);
		dlg.choose();
		return dlg.getSelectedDir();
	}

	public void showUnassignedScenariosListView(){
		UnassignedScenariosView uslv = (UnassignedScenariosView) RecoveryManagerHelper.getUnassignedScenarioListView();
		if(uslv != null){
			uslv.refresh();
		}
		else{
			RecoveryManagerHelper.showUnassignedScenarioList();
			uslv = (UnassignedScenariosView) RecoveryManagerHelper.getUnassignedScenarioListView();
			uslv.refresh();
		}
	}

}