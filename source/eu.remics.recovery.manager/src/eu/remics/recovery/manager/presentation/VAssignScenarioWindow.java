package eu.remics.recovery.manager.presentation;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;

import eu.redseeds.scl.model.rsl.rslrequirementrepresentations.descriptiverequirementrepresentations.ConstrainedLanguageScenarioDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.UseCaseDTO;
import eu.remics.common.RecoveryManagerHelper;
import eu.remics.recovery.manager.applogic.scenarios.CAssignScenarioToUseCase;
import eu.remics.recovery.manager.views.AssignScenarioView;
/**
 * @author Norbert J
 * @version 1.0
 * @created 07-gru-2011 12:45:05
 */
public class VAssignScenarioWindow {

	CAssignScenarioToUseCase nAssignScenarioToUseCase;

	public CAssignScenarioToUseCase cAssignScenarioToUseCase;

	private UseCaseDTO aUseCase;

	private ConstrainedLanguageScenarioDTO aScenario;

	public VAssignScenarioWindow(){

	}

	/**
	 * 
	 * @param aUseCase    redseeds_uid7425184094490908194-280258001227442530-
	 * 1523296627732256655-6270118062981256396redseeds_uid
	 */
	public void displays(ViewerFilter filter){
		RecoveryManagerHelper.hideAssignScenarioView();
		RecoveryManagerHelper.showAssignScenarioView();
		((AssignScenarioView)RecoveryManagerHelper.getAssignScenarioView()).getAssignScenarioTree().setFilters(new ViewerFilter[]{filter});
		((AssignScenarioView)RecoveryManagerHelper.getAssignScenarioView()).getAssignScenarioTree().expandAll();
	}
	
	public void saveNewUseCase(){
		Shell shell = new Shell();
		shell.forceActive();
		
		AssignScenarioView view = (AssignScenarioView)RecoveryManagerHelper.getAssignScenarioView();
		if(view.getReferencedStepNumber() == 0){
			view.setReferencedStepNumber(view.getReferencedStepNumber()+1);
		}
		int res = cAssignScenarioToUseCase.SelectsOption(view.getReferencedStepNumber());
		if(res != 1){
			if(MessageDialog.open(2, shell, "Attach scenario information message", "Alternative scenario has been attached.", SWT.NONE)){
				RecoveryManagerHelper.hideAssignScenarioView();
				RecoveryManagerHelper.hideFindSimilarScenariosView();
			}
		}
		else{
			MessageDialog.open(1, shell, "Attach scenario error message", "Alternative scenario is empty. Scenario cannot be attached.", SWT.NONE);
		}
	}

	public void validates(){
	}

	public void setUseCase(UseCaseDTO obj) {
		aUseCase = obj;
	}
	
	public UseCaseDTO getUseCase(){
		return aUseCase;
	}

	public void setScenario(ConstrainedLanguageScenarioDTO scen) {
		aScenario = scen;
	}
	
	public ConstrainedLanguageScenarioDTO getReferencedScenario(){
		return aScenario;
	}
}