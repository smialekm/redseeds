package eu.remics.recovery.manager.applogic.scenarios;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentations.descriptiverequirementrepresentations.ConstrainedLanguageScenarioDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.UseCaseDTO;
import eu.redseeds.scl.rsl.rslrequirementrepresentations.descriptiverequirementrepresentations.ConstrainedLanguageScenario;
import eu.remics.common.RecoveryManagerHelper;
import eu.remics.recovery.manager.views.UnassignedScenariosView;
import eu.remics.recovery.model.RecoveryModelHelper;
import eu.remics.recovery.model.domainlogic.recoveredscenarios.MRecoveredScenario;
import eu.remics.recovery.model.domainlogic.usecases.MScenario;
import eu.remics.util.SCNavigatorHelper;
/**
 * @author Stacja1
 * @version 1.0
 * @created 04-lis-2011 12:21:02
 */
public class CDeassignScenarioFromUseCase {

	ConstrainedLanguageScenarioDTO aScenario;
	MScenario nScenario;
	UseCaseDTO aUseCase;

	public MScenario mScenario;

	public CDeassignScenarioFromUseCase(){

	}

	/**
	 * 
	 * @param pScenario    redseeds_uid8117013464833395466--92784082877337823--
	 * 7000344071866227747--6015809789817828168redseeds_uid
	 */
	public void _ClicksDeassignScenarioOption(ConstrainedLanguageScenarioDTO scen){
		RecoveryModelHelper rmh = RecoveryModelHelper.instance((ConstrainedLanguageScenario) scen);
		if(!(scen.getParent().getConstrainedLanguageScenarioDTOList().size()==1)){
			for (RequirementDTO uc:rmh.getRecoveredScenariosPackage().getRequirements()) if (uc instanceof UseCaseDTO && null!=((UseCaseDTO) uc).getName() && ((UseCaseDTO) uc).getName().charAt(0)=='~' && scen.equals(MRecoveredScenario.getDerivedScenario((UseCaseDTO)uc))){
				MScenario.deassigns(scen);
				if(rmh.getSCProject() != null){
					if(RecoveryManagerHelper.getUnassignedScenarioListView() == null)
						return;
					((UnassignedScenariosView)RecoveryManagerHelper.getUnassignedScenarioListView()).refresh();
				}
				SCNavigatorHelper.refresh();
				break;
			}
		}
	}
}