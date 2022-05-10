package eu.remics.recovery.manager.applogic.scenarios;

import java.util.List;

import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.UseCaseDTO;
import eu.remics.common.RecoveryManagerHelper;
import eu.remics.recovery.manager.views.RecoveryScenarioPreviewView;

public class CPreviewScenario {
	
	public List<UseCaseDTO> scenarios;
	
	public void Preview(){
		RecoveryManagerHelper.hidePreviewView();
		RecoveryManagerHelper.showPreviewView();
		if (!scenarios.isEmpty())getView().setInput(scenarios.get(0));
	}
	
	public void setScenarioList(List<UseCaseDTO> scenarios) {
		this.scenarios = scenarios;
	}
	
	public RecoveryScenarioPreviewView getView(){
		return (RecoveryScenarioPreviewView)RecoveryManagerHelper.getPreviewView();
	}
}
