package eu.remics.recovery.manager.applogic.scenarios;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;

import eu.remics.common.RecoveryManagerHelper;
import eu.remics.recovery.manager.applogic.ActionsFactory;
import eu.remics.recovery.manager.presentation.VUseCaseNameInputDialog;
import eu.remics.recovery.manager.views.UnassignedScenariosView;
import eu.remics.recovery.model.domainlogic.usecases.MScenario;
import eu.remics.util.SCNavigatorHelper;

public class CUnsplitScenario {
	
	public CUnsplitScenario(){
		RecoveryManagerHelper.setCUnsplitScenarioObject(this);
	}
	
	public void Unsplit(){
		if(MessageDialog.openQuestion(new Shell(), "Join confirmation", "Are you sure you want to join scenarios?")){
			String name = new VUseCaseNameInputDialog().displayForUnsplitted(RecoveryManagerHelper.getFirstScenarioToUnsplit().getName());
			if(name == null){
				return;
			}
			/*if(!name.equals(RecoveryManagerHelper.getFirstScenarioToUnsplit().getName())){
				name = "~"+name;
			}*/
			MScenario.unsplit(name, RecoveryManagerHelper.getFirstScenarioToUnsplit(), RecoveryManagerHelper.getSecondScenarioToUnsplit());
			SCNavigatorHelper.refresh();
			UnassignedScenariosView view  = (UnassignedScenariosView) RecoveryManagerHelper.getUnassignedScenarioListView();
			if(view == null) return;
			view.refresh();
			MessageDialog.open(2, new Shell(), "Join scenario information", "Scenarios were joined.", SWT.NONE);
			ActionsFactory.disableAction(ActionsFactory.ATTACH_UC);
			ActionsFactory.disableAction(ActionsFactory.CREATE);
			ActionsFactory.disableAction(ActionsFactory.DELETE);
			ActionsFactory.disableAction(ActionsFactory.SPLIT);
		}
	}
}
