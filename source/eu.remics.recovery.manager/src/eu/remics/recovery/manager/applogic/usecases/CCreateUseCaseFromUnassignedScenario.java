package eu.remics.recovery.manager.applogic.usecases;
import java.util.ArrayList;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;

import eu.redseeds.scl.model.rsl.rslrequirementrepresentations.descriptiverequirementrepresentations.ConstrainedLanguageScenarioDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementsPackageDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.UseCaseDTO;
import eu.redseeds.scl.rsl.rslrequirements.requirementsspecifications.RSLUseCase;
import eu.redseeds.scl.rsl.rslrequirements.requirementsspecifications.RequirementsPackage;
import eu.remics.common.RecoveryManagerHelper;
import eu.remics.recovery.manager.presentation.VUseCaseNameInputDialog;
import eu.remics.recovery.manager.views.UnassignedScenariosView;
import eu.remics.recovery.model.RecoveryModelHelper;
import eu.remics.recovery.model.domainlogic.usecases.MScenario;
import eu.remics.recovery.model.domainlogic.usecases.MUnassignedScenariosList;
import eu.remics.recovery.model.domainlogic.usecases.MUseCase;
import eu.remics.util.SCNavigatorHelper;
/**
 * @author Stacja1
 * @version 1.0
 * @created 04-lis-2011 12:21:01
 */
/**
 * @author Stacja3
 *
 */
public class CCreateUseCaseFromUnassignedScenario {

	public ConstrainedLanguageScenarioDTO aScenario;
	public UseCaseDTO aUseCase;
	MScenario nScenario;
	MUseCase nUseCase;

	public MScenario mScenario;
	public MUnassignedScenariosList mUnassignedScenariosList = new MUnassignedScenariosList();

	public MUseCase mUseCase;
	private ArrayList<UseCaseDTO> usecases;
	private RequirementsPackageDTO aUseCasePackage;
	public CCreateUseCaseFromUnassignedScenario(){

	}

	/**
	 * 
	 * @param pUnassignedScenariosList    redseeds_uid-6123292435523137799-
	 * 1743871917253302367-4815884966167308466--1300805004653482104redseeds_uid
	 */

	public void ClicksCreateNewUseCaseOption(){
		RecoveryModelHelper rmh = !this.usecases.isEmpty()?RecoveryModelHelper.instance((RSLUseCase) this.usecases.get(0)):(null!=aUseCasePackage?RecoveryModelHelper.instance((RequirementsPackage) aUseCasePackage):RecoveryModelHelper.instance());
		
		if(this.usecases.size() == 1){
			VUseCaseNameInputDialog vUseCaseNameInputDialog = new VUseCaseNameInputDialog();
			String useCaseName = vUseCaseNameInputDialog.display(this.usecases.get(0).getName().substring(1));
			if(useCaseName == null){
				return;
			}
			if(rmh.getUseCase(useCaseName) != null 
				&& (!rmh.getUseCase(useCaseName).getConstrainedLanguageScenarioDTOList().isEmpty() 
						|| rmh.getUseCase(useCaseName).getName().startsWith("~")) ){
				MessageDialog.openError(Display.getCurrent().getActiveShell(), "Use Case Name", "Use Case with this name already exists in project. Please rename it.");
				ClicksCreateNewUseCaseOption();
				return;
			}
			UseCaseDTO uc = this.usecases.get(0);
			
			if(aUseCasePackage != null && !aUseCasePackage.equals(rmh.getRequirementsPackage(aUseCasePackage.getName())))
				setUseCasePackage(null);
			MScenario.createsUseCase(uc, useCaseName, aUseCasePackage);
			
			SCNavigatorHelper.refresh();
			
			if(rmh.getSCProject() != null){
				if(RecoveryManagerHelper.getUnassignedScenarioListView() == null)
					return;
				((UnassignedScenariosView)RecoveryManagerHelper.getUnassignedScenarioListView()).refresh();
			}
		}
		else{
			ArrayList<String> names = new ArrayList<String>();
			ArrayList<UseCaseDTO> usecases = new ArrayList<UseCaseDTO>();
			boolean flag = false;
			for(UseCaseDTO uc : this.usecases){
				VUseCaseNameInputDialog vUseCaseNameInputDialog = new VUseCaseNameInputDialog();
				String useCaseName = vUseCaseNameInputDialog.display(uc.getName().substring(1));
				if(useCaseName == null){
					continue;
				}
				while(rmh.getUseCase(useCaseName) != null 
						&& (!rmh.getUseCase(useCaseName).getConstrainedLanguageScenarioDTOList().isEmpty() 
								|| rmh.getUseCase(useCaseName).getName().startsWith("~"))){
					MessageDialog.openError(Display.getCurrent().getActiveShell(), "Use Case Name", "Use Case with this name already exists in project. Please rename it.");
					vUseCaseNameInputDialog = new VUseCaseNameInputDialog();
					useCaseName = vUseCaseNameInputDialog.display(uc.getName().substring(1));
					if(useCaseName == null){
						flag = true;
						break;
					}
					else{
						flag = false;
					}
				}
				if(!flag){
					usecases.add(uc);
					names.add(useCaseName);
				}
			}
			
			if(aUseCasePackage != null && !aUseCasePackage.equals(rmh.getRequirementsPackage(aUseCasePackage.getName())))
				setUseCasePackage(null);
			mUnassignedScenariosList.automaticallyCreatesUseCases(usecases, names, aUseCasePackage);
			
			SCNavigatorHelper.refresh();
			
			if(rmh.getSCProject() != null){
				if(RecoveryManagerHelper.getUnassignedScenarioListView() == null)
					return;
				((UnassignedScenariosView)RecoveryManagerHelper.getUnassignedScenarioListView()).refresh();
			}
		}
	}
	
	public void setScenarioList(ArrayList<UseCaseDTO> usecases) {
		this.usecases = usecases;

	}

	public void setUseCasePackage(RequirementsPackageDTO pUseCasePackage) {
		aUseCasePackage = pUseCasePackage;
	}

}