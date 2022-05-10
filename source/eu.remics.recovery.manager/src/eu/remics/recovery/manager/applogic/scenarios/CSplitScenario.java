package eu.remics.recovery.manager.applogic.scenarios;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;

import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.UseCaseDTO;
import eu.remics.common.RecoveryManagerHelper;
import eu.remics.recovery.manager.presentation.VErrorMessage;
import eu.remics.recovery.manager.presentation.VSplitScenarioWindow;
import eu.remics.recovery.manager.views.SplitScenarioView;
import eu.remics.recovery.manager.views.UnassignedScenariosView;
import eu.remics.recovery.model.domainlogic.usecases.MScenario;
import eu.remics.util.SCNavigatorHelper;

public class CSplitScenario {
	
	private UseCaseDTO aScenario;
	public VSplitScenarioWindow vSplitScenarioWindow;
	private int aNumber;

	public void ClicksSplitScenario(){
		vSplitScenarioWindow = new VSplitScenarioWindow();
		vSplitScenarioWindow.displays();
		SplitScenarioView view = getView();
		view.getViewer().setInput(aScenario.getConstrainedLanguageScenarioDTOList().get(0).getScenarioSentenceList());
	}
	
	public void Split(){
		if(aScenario.getConstrainedLanguageScenarioDTOList().get(0).getScenarioSentenceList().size() == 1){
			VErrorMessage vErrorMessage = new VErrorMessage();
			vErrorMessage.displays("Cannot split scenario with one sentence.");
		}
		else{
			if(!getView().getReferenceScenarioName().isEmpty()){
				MScenario.split(getView().getReferenceScenarioName(), aScenario, aNumber);
				SCNavigatorHelper.refresh();
				UnassignedScenariosView view  = (UnassignedScenariosView) RecoveryManagerHelper.getUnassignedScenarioListView();
				if(view == null) return;
				view.refresh();
				if(MessageDialog.open(2, new Shell(), "Split scenario information", "Scenario was splitted in two.", SWT.NONE)){
					RecoveryManagerHelper.hideSplitScenarioView();
				}
			}
			else{
				VErrorMessage vErrorMessage = new VErrorMessage();
				vErrorMessage.displays("Name for second scenario is unspecified. Please set name.");
			}
		}
	}
	
	public void setScenario(UseCaseDTO pScenario){
		aScenario = pScenario;
	}
	
	public UseCaseDTO getScenario(){
		return aScenario;
	}
	
	public SplitScenarioView getView(){
		return (SplitScenarioView)RecoveryManagerHelper.getSplitScenarioView();
	}
	
	public void setSplitNumber(int num){
		aNumber = num;
	}
}
