package eu.remics.recovery.manager.applogic.scenarios;

import java.util.ArrayList;

import eu.redseeds.scl.model.rsl.rslrequirementrepresentations.descriptiverequirementrepresentations.ConstrainedLanguageScenarioDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.UseCaseDTO;
import eu.remics.common.RecoveryManagerHelper;
import eu.remics.recovery.manager.applogic.AMain;
import eu.remics.recovery.manager.presentation.VWarningMessage;
import eu.remics.recovery.manager.views.AssignScenarioView;
import eu.remics.recovery.manager.views.UnassignedScenariosView;
import eu.remics.recovery.model.domainlogic.usecases.MScenario;
import eu.remics.recovery.model.domainlogic.usecases.MUnassignedScenariosList;
import eu.remics.util.SCNavigatorHelper;

/**
 * @author Stacja1
 * @version 1.0
 * @created 14-lis-2011 10:17:36
 */
public class CDeleteScenario {

	ConstrainedLanguageScenarioDTO aScenario;
	public ArrayList<UseCaseDTO> usecases;
	
	MScenario nScenario;
	VWarningMessage nWarningMessage;

	public VWarningMessage vWarningMessage;

	public MUnassignedScenariosList mUnassignedScenariosList;

	public CDeleteScenario() {

	}

	/**
	 * 
	 * @param pScenario
	 *            redseeds_uid2224331128902545551-8809607193467927524-
	 *            7228762985978878336-7596887545888029715redseeds_uid
	 */
	public void _ClicksDeleteScenarioOption() {
		vWarningMessage = new VWarningMessage();
		vWarningMessage.cDeleteScenario = this;
		SelectsOption(vWarningMessage.displays());
	}

	
	public void SelectsOption(int res) {
		if (res == 0 /* User confirms delete option */) {
			for(UseCaseDTO uc : usecases){
				//first check if scenario to delete is being attached
				UseCaseDTO detachedScenario = AMain.cAssignScenarioToUseCase.aScenario;
				if(detachedScenario != null && uc.equals(detachedScenario)){
					RecoveryManagerHelper.hideAssignScenarioView();
				}
				//delete from model
				MUnassignedScenariosList.deletes(uc);
				
				//disable buttons for list
				UnassignedScenariosView uslv = (UnassignedScenariosView) RecoveryManagerHelper.getUnassignedScenarioListView();
				if(uslv == null) return;
				SCNavigatorHelper.refresh();
				uslv.disableButtons();
			}
		} else if (res == 1 /* User cancels */) {
		}

	}

	public void setScenarioList(ArrayList<UseCaseDTO> usecases) {
		this.usecases = usecases;
	}

}