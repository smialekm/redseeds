package eu.remics.recovery.model.domainlogic.usecases;

import java.util.ArrayList;
import java.util.List;

import eu.redseeds.scl.model.rsl.rslrequirementrepresentations.descriptiverequirementrepresentations.ConstrainedLanguageScenarioDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.constrainedlanguagesentences.ConstrainedLanguageSentenceDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.controlsentences.InvocationSentenceDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementsPackageDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.UseCaseDTO;
import eu.redseeds.scl.rsl.rslrequirementrepresentationsentences.controlsentences.InvocationSentence;
import eu.redseeds.scl.rsl.rslrequirements.requirementsspecifications.RSLUseCase;
import eu.remics.recovery.model.RecoveryModelHelper;
import eu.remics.recovery.model.domainlogic.recoveredscenarios.MRecoveredScenario;

public class MUnassignedScenariosList {

	/**
	 * Returns unassigned recovery scenario list
	 * 
	 * @return recovered scenario list
	 */
	public static List<UseCaseDTO> getUnnasignedScenarios() {
		RecoveryModelHelper rmh = RecoveryModelHelper.instance();
		List<UseCaseDTO> list = new ArrayList<UseCaseDTO>();
		if (null!=rmh.getSCProject() && null!=rmh.getRecoveredScenariosPackage())
		for (RequirementDTO uc:rmh.getRecoveredScenariosPackage().getRequirements()) if (uc instanceof UseCaseDTO && null!=((UseCaseDTO) uc).getName() && ((UseCaseDTO)uc).getName().charAt(0)=='~' && null==MRecoveredScenario.getDerivedScenario((UseCaseDTO)uc)) list.add((UseCaseDTO) uc);
		return list;
	}
	
	/**
	 * Checks if scenario is in unassigned scenarios list
	 * 
	 * @param scen scenario
	 * @return true if scenario is in the list
	 */
	public static boolean isInUnassignedList(ConstrainedLanguageScenarioDTO scen){
		for(UseCaseDTO uc : getUnnasignedScenarios()){
			if(uc.getConstrainedLanguageScenarioDTOList().get(0).equals(scen)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Deletes scenario from unassigned scenarios list
	 * 
	 * @param useCase scenario (represented by use case)
	 */
	public static void deletes(UseCaseDTO useCase){
		RecoveryModelHelper rmh = RecoveryModelHelper.instance((RSLUseCase) useCase);
		if (useCase.getName().charAt(0)=='~'){
			List<UseCaseDTO> list = new ArrayList<UseCaseDTO>();
			for (ConstrainedLanguageSentenceDTO sent:useCase.getConstrainedLanguageScenarioDTOList().get(0).getScenarioSentenceList()) if (sent instanceof InvocationSentenceDTO && !list.contains(((InvocationSentenceDTO) sent).getInvokedUseCase())){
				boolean find=false;
				for (InvocationSentence inv : rmh.getBussinessLayerFacade().getInvocationSentenceVertices()) if (null!=((InvocationSentenceDTO) inv).getInvokedUseCase() && ((InvocationSentenceDTO) inv).getInvokedUseCase().equals(((InvocationSentenceDTO) sent).getInvokedUseCase()) && !useCase.getConstrainedLanguageScenarioDTOList().get(0).getScenarioSentenceList().contains(inv)) find=true;
				if (!find && null!=((InvocationSentenceDTO) sent).getInvokedUseCase()) list.add(((InvocationSentenceDTO) sent).getInvokedUseCase());
			}
			for (UseCaseDTO uc:list) if (uc.getConstrainedLanguageScenarioDTOList().isEmpty()) uc.delete();
			UseCaseDTO tempuc=null;
			boolean finished = false;
			loop:
			while(!finished){
				for (InvocationSentence inv:rmh.getBussinessLayerFacade().getInvocationSentenceVertices()) if (null!=((InvocationSentenceDTO)inv).getInvokedUseCase() && ((InvocationSentenceDTO)inv).getInvokedUseCase().equals(useCase)){
					if (null==tempuc){
						tempuc=rmh.getBussinessLayerFacade().createUseCaseDTO();
						tempuc.setName(useCase.getName());
					}
					((InvocationSentenceDTO)inv).setInvokedUseCase(tempuc);
					continue loop;
				}
				finished = true;
			}
			useCase.delete();	
			rmh.saveSCProject();
		}
	}
	
	/**
	 * Starts automatically creation of use case for given list
	 * 
	 * @param scenariosList list of recovered scenarios
	 * @param namesList list of names for use cases
	 * @param useCasesPackage package for use cases
	 * @return list of created use cases
	 */
	public List<UseCaseDTO> automaticallyCreatesUseCases(List<UseCaseDTO> scenariosList, List<String> namesList, RequirementsPackageDTO useCasesPackage){
		List<UseCaseDTO> createdUseCasesList = new ArrayList<UseCaseDTO>();
		//calls automaticallyAddUseCase method for use case until list is not empty
		while(!scenariosList.isEmpty()) automaticallyAddUseCase(scenariosList.get(0),scenariosList,namesList,createdUseCasesList,useCasesPackage);
		return createdUseCasesList;
	}
	
	/**
	 * Process simple use case in process of automatically creation of use case for given list
	 * 
	 * @param scenario currently processed recovered scenario
	 * @param scenariosList list of recovered scenarios
	 * @param aNamesList list of names for use cases
	 * @param aCreatedUseCasesList list of created use cases
	 * @param aUseCasePackage package for use cases
	 * @return list of created use cases
	 */
	private List<UseCaseDTO> automaticallyAddUseCase(UseCaseDTO scenario, List<UseCaseDTO> scenariosList, List<String> aNamesList, List<UseCaseDTO> aCreatedUseCasesList,RequirementsPackageDTO aUseCasePackage){
		//calls this function for use case with invoked that use case if any
		boolean end=false;
		loop:
		while(!end){
			for (UseCaseDTO uc : scenariosList) if (uc.getName().substring(1).equals(MRecoveredScenario.getInvokedBy(scenario))){
				automaticallyAddUseCase(uc,scenariosList,aNamesList,aCreatedUseCasesList,aUseCasePackage);
				continue loop;
			}
			end=true;
		}//adds current use case
		aCreatedUseCasesList.add(MScenario.createsUseCase(scenario, aNamesList.get(scenariosList.indexOf(scenario)), aUseCasePackage));
		aNamesList.remove(scenariosList.indexOf(scenario));
		scenariosList.remove(scenario);
		return aCreatedUseCasesList;
	}
	
}