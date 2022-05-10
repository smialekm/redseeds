package eu.remics.recovery.model.domainlogic.usecases;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import eu.redseeds.scl.model.rsl.rslrequirementrepresentations.descriptiverequirementrepresentations.ConstrainedLanguageScenarioDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.constrainedlanguagesentences.ConstrainedLanguageSentenceDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.controlsentences.ConditionSentenceDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.controlsentences.InvocationSentenceDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.controlsentences.PostconditionSentenceDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.controlsentences.PreconditionSentenceDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.UseCaseDTO;
import eu.redseeds.scl.rsl.rslrequirementrepresentations.requirementrepresentations.RequirementRepresentation;
import eu.redseeds.scl.rsl.rslrequirementrepresentationsentences.constrainedlanguagesentences.ConstrainedLanguageSentence;
import eu.redseeds.scl.rsl.rslrequirementrepresentationsentences.controlsentences.InclusionType;
import eu.redseeds.scl.rsl.rslrequirementrepresentationsentences.controlsentences.InvocationSentence;
import eu.redseeds.scl.rsl.rslrequirements.requirementrelationships.NonInvocationRelationship;
import eu.redseeds.scl.rsl.rslrequirements.requirementsspecifications.RSLUseCase;
import eu.redseeds.scl.rsl.rslrequirements.requirementsspecifications.Requirement;
import eu.redseeds.scl.sclkernel.IsAllocatedTo;
import eu.redseeds.scl.sclkernel.Stereotype;
import eu.redseeds.scl.uml.classes.dependencies.Dependency;
import eu.redseeds.scl.uml.usecases.UseCase;
import eu.remics.recovery.model.RecoveryModelHelper;
import eu.remics.recovery.model.domainlogic.recoveredscenarios.MRecoveredScenario;
import eu.remics.recovery.model.preferences.MConfiguration;

public class MUseCase {
	
	/**
	 * Assigns scenario to a given use case used another scenario of this use case as reference
	 * 
	 * @param useCase use case
	 * @param scenario scenario
	 * @param step place of assigns
	 * @param referencescenario reference scenario
	 */
	public static void assigns(UseCaseDTO useCase, UseCaseDTO scenario, int step, ConstrainedLanguageScenarioDTO referencescenario) {
		RecoveryModelHelper rmh = RecoveryModelHelper.instance((RSLUseCase)useCase);
		rmh.saveSCProject();
		try{
		ConstrainedLanguageScenarioDTO scen=rmh.getBussinessLayerFacade().createConstrainedLanguageScenarioDTO();
		if (useCase.getConstrainedLanguageScenarioDTOList().get(0).getScenarioSentenceList().size() <= 1) useCase.getConstrainedLanguageScenarioDTOList().get(0).delete();
		useCase.addConstrainedLanguageScenario(scen);
		if(step>0) scen=MScenario.moveSubscenario(referencescenario, scen, 0, step,null);
		else {
			PreconditionSentenceDTO pre = rmh.getBussinessLayerFacade().createPreconditionSentenceDTO();
			pre.setSentenceText("");
			scen.addScenarioStep(pre);
		}
		scen=MRecoveredScenario.copyScenario(scenario, scen,referencescenario);
		if (1==useCase.getConstrainedLanguageScenarioDTOList().size() && MConfiguration.isAddPostToMain()){
			PostconditionSentenceDTO pc = rmh.getBussinessLayerFacade().createPostconditionSentenceDTO();
			pc.setSuccess(true);
			scen.addScenarioStep(pc);
		}
		MScenario.addInvokedBy(null != scen ? scen : referencescenario, scenario);
		MRecoveredScenario.addDervivedScenario(scenario, null != scen ? scen : referencescenario);
		UseCaseDTO uc;
		if (null!=(uc=rmh.getBussinessLayerFacade().getUseCaseByName(scenario.getName().substring(1))) && ((RSLUseCase)uc).getId()!=((RSLUseCase)useCase).getId()){
			boolean done=false;
			loop:
			while (!done){
				for (InvocationSentence inv : rmh.getBussinessLayerFacade().getInvocationSentenceVertices()) if (null!=((InvocationSentenceDTO) inv).getInvokedUseCase() && ((InvocationSentenceDTO) inv).getInvokedUseCase().getName().equals(scenario.getName().substring(1))){
					((InvocationSentenceDTO) inv).setInvokedUseCase(useCase);
					continue loop;
				}
				done=true;
			}
		}
		MScenario.postAddInvokedBy(scenario, useCase);
		} catch (RuntimeException e){
			rmh.revertSCProject();
			throw e;
		}
		rmh.saveSCProject();
	}
	
	/**
	 * Restore recovered scenarios after deletion of use case
	 * 
	 * @param usecase use case
	 */
	public static void restoreRecoveredScenarios(UseCaseDTO usecase){
		if (usecase.getConstrainedLanguageScenarioDTOList().isEmpty()) return;
		RecoveryModelHelper rmh = RecoveryModelHelper.instance((RSLUseCase) usecase);
		List<UseCaseDTO> dsl = new ArrayList<UseCaseDTO>();
		for (RequirementDTO uc:rmh.getRecoveredScenariosPackage().getRequirements()) if (uc instanceof UseCaseDTO && null!=((UseCaseDTO) uc).getName() && ((UseCaseDTO) uc).getName().charAt(0)=='~' && usecase.getConstrainedLanguageScenarioDTOList().contains(MRecoveredScenario.getDerivedScenario((UseCaseDTO)uc))){
			dsl.add((UseCaseDTO) uc);
		}
		if (dsl.isEmpty()) return;
		Map<UseCaseDTO, UseCaseDTO> dslm = new HashMap<UseCaseDTO,UseCaseDTO>();
		boolean finished = false;
		loop:
		while(!finished){
			for (InvocationSentence inv:rmh.getBussinessLayerFacade().getInvocationSentenceVertices()) if (null!=((InvocationSentenceDTO)inv).getInvokedUseCase() && ((InvocationSentenceDTO)inv).getInvokedUseCase().equals(usecase) && !((InvocationSentenceDTO)inv).getOwningScenarios().get(0).getParent().equals(usecase)){
				for (RequirementDTO uc:rmh.getRecoveredScenariosPackage().getRequirements()) if (uc instanceof UseCaseDTO && null!=((UseCaseDTO) uc).getName() && ((UseCaseDTO) uc).getName().charAt(0)=='~'){
					if(((InvocationSentenceDTO) inv).getOwningScenarios().contains(MRecoveredScenario.getDerivedScenario((UseCaseDTO)uc))) 
						for(ConstrainedLanguageSentenceDTO sent:((UseCaseDTO) uc).getConstrainedLanguageScenarioDTOList().get(0).getScenarioSentenceList()) if (sent instanceof InvocationSentenceDTO && dsl.contains(((InvocationSentenceDTO) sent).getInvokedUseCase())){
							if (!dslm.containsKey(((InvocationSentenceDTO) sent).getInvokedUseCase())){
								UseCaseDTO tempuc = rmh.getBussinessLayerFacade().createUseCaseDTO();
								tempuc.setName(((InvocationSentenceDTO) sent).getInvokedUseCase().getName().substring(1));
								dslm.put(((InvocationSentenceDTO) sent).getInvokedUseCase(), tempuc);
							}
							((InvocationSentenceDTO)inv).setInvokedUseCase(dslm.get(((InvocationSentenceDTO) sent).getInvokedUseCase()));
							continue loop;
					}
					if (((InvocationSentenceDTO) inv).getOwningScenarios().get(0).getParent().getConstrainedLanguageScenarioDTOList().contains(MRecoveredScenario.getDerivedScenario((UseCaseDTO)uc))){
						for (UseCaseDTO u:dsl) if (null!= MRecoveredScenario.getInvokedBy(u) && MRecoveredScenario.getInvokedBy(u).equals(((UseCaseDTO) uc).getName().substring(1))){
							if (!dslm.containsKey(u)){
								UseCaseDTO tempuc = rmh.getBussinessLayerFacade().createUseCaseDTO();
								tempuc.setName(u.getName().substring(1));
								dslm.put(u, tempuc);
							}
							((InvocationSentenceDTO)inv).setInvokedUseCase(dslm.get((u)));
							continue loop;
						}
					}
				}
			}
			finished = true;
		}
		for (UseCaseDTO uc:dsl) MRecoveredScenario.removeDerivedScenario((UseCaseDTO)uc);
	}

	/**
	 * Merges two use case using scenario from first of them as reference, and set new name to it
	 * 
	 * @param aUseCase first use case
	 * @param pUseCase second use case
	 * @param name name
	 * @param step place of merge in reference scenario
	 * @param referenceScenario reference scenario
	 * @return merged use case
	 */
	public static UseCaseDTO merges(UseCaseDTO aUseCase, UseCaseDTO pUseCase, String name, int step, ConstrainedLanguageScenarioDTO referenceScenario) {
		RecoveryModelHelper rmh = RecoveryModelHelper.instance((RSLUseCase) aUseCase);
		rmh.saveSCProject();
		try {
			if (1 != aUseCase.getConstrainedLanguageScenarioDTOList().get(0).getScenarioSentenceList().size()) {
				int lenght = 0, num = aUseCase.getConstrainedLanguageScenarioDTOList().size();
				boolean[] common = new boolean[pUseCase.getConstrainedLanguageScenarioDTOList().size()];
				for (int i = 0; i < pUseCase.getConstrainedLanguageScenarioDTOList().size(); i++) {
					if (pUseCase.getConstrainedLanguageScenarioDTOList().get(i).getScenarioSentenceList().size() - 1 > lenght) lenght = pUseCase.getConstrainedLanguageScenarioDTOList().get(i).getScenarioSentenceList().size() - 1;
					ConstrainedLanguageScenarioDTO sc = rmh.getBussinessLayerFacade().createConstrainedLanguageScenarioDTO();
					aUseCase.addConstrainedLanguageScenario(sc);
					sc = MScenario.moveSubscenario(referenceScenario, sc, 0,step, null);
					sc.setName(pUseCase.getConstrainedLanguageScenarioDTOList().get(i).getName());
					for (RequirementDTO uc : rmh.getRecoveredScenariosPackage().getRequirements())
						if (uc instanceof UseCaseDTO && null != ((UseCaseDTO) uc).getName() && ((UseCaseDTO) uc).getName().charAt(0) == '~' && pUseCase.getConstrainedLanguageScenarioDTOList().get(i).equals(MRecoveredScenario.getDerivedScenario((UseCaseDTO) uc))) {
							MRecoveredScenario.addDervivedScenario((UseCaseDTO) uc, sc);
						}
					common[i] = true;
				}
				int[] shift = new int[aUseCase.getConstrainedLanguageScenarioDTOList().size()];
				for (int i = 0; i < lenght; i++) {
					int[] tmpshift = new int[shift.length];
					for (int j = 0; j < pUseCase.getConstrainedLanguageScenarioDTOList().size(); j++)
						if (i < pUseCase.getConstrainedLanguageScenarioDTOList().get(j).getScenarioSentenceList().size() - 1) {
							boolean find = false;
							if (common[j])
								for (int k = 0; k < num; k++){
									if (!MConfiguration.isCheckPrefixInvcationAndConditionSentences() && i + shift[k] + tmpshift[k] + step < aUseCase.getConstrainedLanguageScenarioDTOList().get(k).getScenarioSentenceList().size() && ((ConstrainedLanguageSentence) aUseCase.getConstrainedLanguageScenarioDTOList().get(k).getScenarioSentenceList().get(i + shift[k] + tmpshift[k] + step - 1)).getId() == ((ConstrainedLanguageSentence) aUseCase.getConstrainedLanguageScenarioDTOList().get(j + num).getScenarioSentenceList().get(i + shift[j + num] + tmpshift[k] + step - 1)).getId() && (aUseCase.getConstrainedLanguageScenarioDTOList().get(k).getScenarioSentenceList().get(i + shift[k] + tmpshift[k] + step) instanceof ConditionSentenceDTO && !(pUseCase.getConstrainedLanguageScenarioDTOList().get(j).getScenarioSentenceList().get(i + 1) instanceof ConditionSentenceDTO) || aUseCase.getConstrainedLanguageScenarioDTOList().get(k).getScenarioSentenceList().get(i + shift[k] + tmpshift[k] + step) instanceof InvocationSentenceDTO && !(pUseCase.getConstrainedLanguageScenarioDTOList().get(j).getScenarioSentenceList().get(i + 1) instanceof InvocationSentenceDTO))){
										aUseCase.getConstrainedLanguageScenarioDTOList().get(j + num).addScenarioStep(aUseCase.getConstrainedLanguageScenarioDTOList().get(k).getScenarioSentenceList().get(i + shift[k] + tmpshift[k] + step));
										shift[k]++;
										shift[j+num]++;
									}
									if (i + shift[k] + tmpshift[k] + step < aUseCase.getConstrainedLanguageScenarioDTOList().get(k).getScenarioSentenceList().size()
											&& ((ConstrainedLanguageSentence) aUseCase.getConstrainedLanguageScenarioDTOList().get(k).getScenarioSentenceList().get(i + shift[k] + tmpshift[k] + step - 1)).getId() == ((ConstrainedLanguageSentence) aUseCase.getConstrainedLanguageScenarioDTOList().get(j + num).getScenarioSentenceList().get(i + shift[j + num] + tmpshift[k] + step - 1)).getId()
											&& MScenario.equalsSentence(aUseCase.getConstrainedLanguageScenarioDTOList().get(k).getScenarioSentenceList().get(i + shift[k] + tmpshift[k] + step), pUseCase.getConstrainedLanguageScenarioDTOList().get(j).getScenarioSentenceList().get(i + 1))) {
										aUseCase.getConstrainedLanguageScenarioDTOList().get(j + num).addScenarioStep(aUseCase.getConstrainedLanguageScenarioDTOList().get(k).getScenarioSentenceList().get(i + shift[k] + tmpshift[k] + step));
										find = true;
										break;
									}
								}
							if (!find && common[j]) {
								if (0 == tmpshift[j + num]) {
									ConditionSentenceDTO con = null;
									for (int k = 0; k < num; k++)
										if (i + step + shift[k] <= aUseCase.getConstrainedLanguageScenarioDTOList().get(k).getScenarioSentenceList().size() && ((ConstrainedLanguageSentence) aUseCase.getConstrainedLanguageScenarioDTOList().get(k).getScenarioSentenceList().get(i + step + shift[k] - 1)).getId() == ((ConstrainedLanguageSentence) aUseCase.getConstrainedLanguageScenarioDTOList().get(j + num).getScenarioSentenceList().get(i + step + shift[j + num] - 1)).getId() && !(aUseCase.getConstrainedLanguageScenarioDTOList().get(k).getScenarioSentenceList().get(i + step + shift[k] + tmpshift[k] - 1) instanceof ConditionSentenceDTO) && (aUseCase.getConstrainedLanguageScenarioDTOList().get(k).getScenarioSentenceList().size()==i + step + shift[k] + tmpshift[k] || !(aUseCase.getConstrainedLanguageScenarioDTOList().get(k).getScenarioSentenceList().get(i + step + shift[k] + tmpshift[k]) instanceof ConditionSentenceDTO))) {
											if (con == null) {
												con = rmh.getBussinessLayerFacade().createConditionSentenceDTO();
												con.setSentenceText("");
											}
											aUseCase.getConstrainedLanguageScenarioDTOList().get(k).insertScenarioStepAfter(aUseCase.getConstrainedLanguageScenarioDTOList().get(k).getScenarioSentenceList().get(i + step + shift[k] + tmpshift[k] - 1), con);
											tmpshift[k]++;
										}
									con = null;
									for (int k = num; k < aUseCase.getConstrainedLanguageScenarioDTOList().size(); k++)
										if (i + step + shift[k] - 1 < aUseCase.getConstrainedLanguageScenarioDTOList().get(k).getScenarioSentenceList().size() && ((ConstrainedLanguageSentence) aUseCase.getConstrainedLanguageScenarioDTOList().get(k).getScenarioSentenceList().get(i + step + shift[k] - 1)).getId() == ((ConstrainedLanguageSentence) aUseCase.getConstrainedLanguageScenarioDTOList().get(j + num).getScenarioSentenceList().get(i + step + shift[j + num] - 1)).getId()) {
											if (aUseCase.getConstrainedLanguageScenarioDTOList().get(k).getLastSentence() instanceof ConditionSentenceDTO) aUseCase.getConstrainedLanguageScenarioDTOList().get(k).removeScenarioStep(aUseCase.getConstrainedLanguageScenarioDTOList().get(k).getLastSentence());
											if (con == null) {
												con = rmh.getBussinessLayerFacade().createConditionSentenceDTO();
												con.setSentenceText("");
											}
											aUseCase.getConstrainedLanguageScenarioDTOList().get(k).addScenarioStep(con);
											tmpshift[k]++;
										}
								}
								for (int k = i; k < pUseCase.getConstrainedLanguageScenarioDTOList().get(j).getScenarioSentenceList().size() - 1; k++)
									if (!(pUseCase.getConstrainedLanguageScenarioDTOList().get(j).getScenarioSentenceList().get(k + 1) instanceof InvocationSentenceDTO)) aUseCase.getConstrainedLanguageScenarioDTOList().get(j + num).addScenarioStep(pUseCase.getConstrainedLanguageScenarioDTOList().get(j).getScenarioSentenceList().get(k + 1));
									else {
										if (pUseCase.getConstrainedLanguageScenarioDTOList().get(j).getScenarioSentenceList().get(k + 1).getOwningScenarios().size() > 1) {
											boolean find2 = false;
											for (int l = num; l < j; l++)
												if (MScenario.equalsSentence(pUseCase.getConstrainedLanguageScenarioDTOList().get(j).getScenarioSentenceList().get(k + 1), aUseCase.getConstrainedLanguageScenarioDTOList().get(l).getScenarioSentenceList().get(k)) && pUseCase.getConstrainedLanguageScenarioDTOList().get(l - num).getScenarioSentenceList().contains(pUseCase.getConstrainedLanguageScenarioDTOList().get(j).getScenarioSentenceList().get(k + 1))) {
													aUseCase.getConstrainedLanguageScenarioDTOList().get(j + num).addScenarioStep(pUseCase.getConstrainedLanguageScenarioDTOList().get(l).getScenarioSentenceList().get(k + 1));
													find2 = true;
													break;
												}
											if (find2) continue;
										}
										InvocationSentenceDTO inv = rmh.getBussinessLayerFacade().createInvocationSentenceDTO();
										inv.setInclusionType(InclusionType.INSERT);
										aUseCase.getConstrainedLanguageScenarioDTOList().get(j + num).addScenarioStep(inv);
										inv.setInvokedUseCase(((InvocationSentenceDTO) pUseCase.getConstrainedLanguageScenarioDTOList().get(j).getScenarioSentenceList().get(k + 1)).getInvokedUseCase());
									}
								common[j] = false;
							}
						}
					for (int j = 0; j < shift.length; j++) shift[j] += tmpshift[j];
				}
				for (int i = num; i < aUseCase.getConstrainedLanguageScenarioDTOList().size(); i++)
					if (common[i - num]) {
						List<UseCaseDTO> pd = new ArrayList<UseCaseDTO>();
						for (RequirementDTO uc : rmh.getRecoveredScenariosPackage().getRequirements())
							if (uc instanceof UseCaseDTO && null != ((UseCaseDTO) uc).getName() && ((UseCaseDTO) uc).getName().charAt(0) == '~' && aUseCase.getConstrainedLanguageScenarioDTOList().get(i).equals(MRecoveredScenario.getDerivedScenario((UseCaseDTO) uc))) {
								pd.add((UseCaseDTO) uc);
								break;
							}
						aUseCase.getConstrainedLanguageScenarioDTOList().get(i).delete();
						if (!pd.isEmpty()) for (UseCaseDTO cpd:pd) MRecoveredScenario.addDervivedScenario(cpd, referenceScenario);
						i--;
					}
			} else {
				aUseCase.getConstrainedLanguageScenarioDTOList().get(0).delete();
				for (ConstrainedLanguageScenarioDTO scen : pUseCase.getConstrainedLanguageScenarioDTOList()) aUseCase.addConstrainedLanguageScenario(scen);
				for (ConstrainedLanguageScenarioDTO scen : pUseCase.getConstrainedLanguageScenarioDTOList()) {
					((RSLUseCase) pUseCase).removeRepresentation((RequirementRepresentation) scen);
					for (ConstrainedLanguageSentenceDTO sent : scen.getScenarioSentenceList())
						if (sent instanceof InvocationSentenceDTO) ((InvocationSentenceDTO) sent).setInvokedUseCase(((InvocationSentenceDTO) sent).getInvokedUseCase());
				}
			}
			aUseCase.setName(name);
			boolean finished = false;
			loop: while (!finished) {
				for (InvocationSentence inv : rmh.getBussinessLayerFacade().getInvocationSentenceVertices())
					if (null != ((InvocationSentenceDTO) inv).getInvokedUseCase() && ((InvocationSentenceDTO) inv).getInvokedUseCase().equals(pUseCase)) {
						((InvocationSentenceDTO) inv).setInvokedUseCase(aUseCase);
						continue loop;
					}
				finished = true;
			}
			for (Dependency dep : rmh.getBussinessLayerFacade().getDependencyVertices())
				if (dep.getClientList().size() > 0 && dep.getClientList().get(0) instanceof eu.redseeds.scl.uml.usecases.Actor && !((eu.redseeds.scl.uml.usecases.Actor) dep.getClientList().get(0)).getAllocationToRSLList().isEmpty() && dep.getSupplierList().size() > 0 && dep.getSupplierList().get(0) instanceof eu.redseeds.scl.uml.usecases.UseCase && !((eu.redseeds.scl.uml.usecases.UseCase) dep.getSupplierList().get(0)).getAllocationToRSLList().isEmpty()) {
					Integer actorid = null;
					shell: for (IsAllocatedTo alloc : ((eu.redseeds.scl.uml.usecases.Actor) dep.getClientList().get(0)).getAllocationToRSLList())
						for (Stereotype ster : alloc.getStereotypeList())
							if (ster.getName().equals("DuplicatedUseCaseStructureForUseRelationship")) {
								actorid = ((eu.redseeds.scl.rsl.rsldomainelements.actors.Actor) alloc.getAllocationSourceList().get(0)).getId();
								break shell;
							}
					if (null == actorid) continue;
					Integer usecaseid = null;
					shell: for (IsAllocatedTo alloc : ((eu.redseeds.scl.uml.usecases.UseCase) dep.getSupplierList().get(0)).getAllocationToRSLList())
						for (Stereotype ster : alloc.getStereotypeList())
							if (ster.getName().equals("DuplicatedUseCaseStructureForUseRelationship")) {
								usecaseid = ((RSLUseCase) alloc.getAllocationSourceList().get(0)).getId();
								break shell;
							}
					if (null == usecaseid) continue;
					if (usecaseid == ((RSLUseCase) pUseCase).getId()) {
						UseCase uc = null;
						shell: for (IsAllocatedTo alloc : ((RSLUseCase) aUseCase).getAllocationToUMLList())
							for (Stereotype ster : alloc.getStereotypeList())
								if (ster.getName().equals("DuplicatedUseCaseStructureForUseRelationship")) {
									uc = (UseCase) alloc.getAllocationTargetList().get(0);
									break shell;
								}
						boolean contains = false;
						for (Dependency depen : rmh.getBussinessLayerFacade().getDependencyVertices())
							if (!depen.getClientList().isEmpty() && depen.getClientList().get(0) instanceof eu.redseeds.scl.uml.usecases.Actor && depen.getClientList().get(0).getId() == dep.getClientList().get(0).getId() && !depen.getSupplierList().isEmpty() && depen.getSupplierList().get(0) instanceof eu.redseeds.scl.uml.usecases.UseCase && depen.getSupplierList().get(0).getId() == uc.getId()) {
								contains = true;
								break;
							}
						if (!contains) {
							dep.removeSupplier(dep.getSupplierList().get(0));
							dep.addSupplier(uc);
						} else
							dep.delete();
					}
				}
			for (NonInvocationRelationship nir : rmh.getBussinessLayerFacade().getNonInvocationRelationshipVertices()) {
				if (!nir.getSourceList().isEmpty() && nir.getSourceList().get(0).getId() == ((RSLUseCase) pUseCase).getId()) {
					nir.removeSource((Requirement) nir.getSourceList().get(0));
					nir.addSource((Requirement) aUseCase);
				}
				if (!nir.getTargetList().isEmpty() && nir.getTargetList().get(0).getId() == ((RSLUseCase) pUseCase).getId()) {
					nir.removeTarget((Requirement) nir.getTargetList().get(0));
					nir.addTarget((Requirement) aUseCase);
				}
			}
			pUseCase.delete();
		} catch (RuntimeException e) {
			rmh.revertSCProject();
			throw e;
		}
		rmh.saveSCProject();
		return aUseCase;
	}
	
	/**
	 * Move scenario from one use case to another, used scenario of second use case as reference
	 * 
	 * @param useCase target use case
	 * @param scenario scenario
	 * @param step place of merge in reference scenario
	 * @param referenceScenario reference scenario
	 * @return return true if this was last scenario of first use case
	 */
	public static void partialMerges(UseCaseDTO useCase,
			ConstrainedLanguageScenarioDTO scenario, int step,
			ConstrainedLanguageScenarioDTO referenceScenario) {
		RecoveryModelHelper rmh = RecoveryModelHelper.instance((RSLUseCase) useCase);
		rmh.saveSCProject();
		try {
			UseCaseDTO parent = scenario.getParent();
			if (1 != useCase.getConstrainedLanguageScenarioDTOList().get(0)
					.getScenarioSentenceList().size()) {
				ConstrainedLanguageScenarioDTO scen = rmh.getBussinessLayerFacade()
						.createConstrainedLanguageScenarioDTO();
				useCase.addConstrainedLanguageScenario(scen);
				scen = MScenario.moveSubscenario(referenceScenario, scen, 0,
						step, null);
				scen = MScenario.moveSubscenario(scenario, scen, 1, scenario
						.getScenarioSentenceList().size() + 1,
						referenceScenario);
				for (RequirementDTO uc : rmh
						.getRecoveredScenariosPackage().getRequirements())
					if (uc instanceof UseCaseDTO
							&& null != ((UseCaseDTO) uc).getName()
							&& ((UseCaseDTO) uc).getName().charAt(0) == '~'
							&& scenario.equals(MRecoveredScenario
									.getDerivedScenario((UseCaseDTO) uc))) {
						MRecoveredScenario.addDervivedScenario((UseCaseDTO) uc,
								null != scen ? scen : referenceScenario);
					}
				scenario.delete();

			} else {
				useCase.getConstrainedLanguageScenarioDTOList().get(0).delete();
				((RSLUseCase) parent)
						.removeRepresentation((RequirementRepresentation) scenario);
				useCase.addConstrainedLanguageScenario(scenario);
				for (ConstrainedLanguageSentenceDTO sent:scenario.getScenarioSentenceList()) if (sent instanceof InvocationSentenceDTO){
					((InvocationSentenceDTO) sent).setInvokedUseCase(((InvocationSentenceDTO) sent).getInvokedUseCase());
				}
			}
			for (ConstrainedLanguageScenarioDTO sc : parent
					.getConstrainedLanguageScenarioDTOList())
				for (ConstrainedLanguageSentenceDTO sent : sc
						.getScenarioSentenceList())
					if ((sent instanceof ConditionSentenceDTO)
							&& sc.getPreviousSentence(sent)
									.getOwningScenarios().size() == sent
									.getOwningScenarios().size())
						for (ConstrainedLanguageScenarioDTO scen2 : sent
								.getOwningScenarios())
							scen2.removeScenarioStep(sent);
		} catch (RuntimeException e) {
			rmh.revertSCProject();
			throw e;
		}
		rmh.saveSCProject();
	}
	
	/**
	 * Do final changes after merge two use cases by moved its scenario and set it new name
	 * 
	 * @param aUseCase first use case
	 * @param pUseCase second use case
	 * @param name name
	 * @return merged use case
	 */
	public static UseCaseDTO endMerges(UseCaseDTO aUseCase,UseCaseDTO pUseCase,String name){
		RecoveryModelHelper rmh = RecoveryModelHelper.instance((RSLUseCase) aUseCase);
		aUseCase.setName(name);
		boolean finished = false;
		loop:
		while(!finished){
			for (InvocationSentence inv : rmh.getBussinessLayerFacade().getInvocationSentenceVertices())
				if (null!=((InvocationSentenceDTO) inv).getInvokedUseCase() && ((InvocationSentenceDTO) inv).getInvokedUseCase().equals(pUseCase)){
					((InvocationSentenceDTO) inv).setInvokedUseCase(aUseCase);
					continue loop;
				}
			finished = true;
		}
		for (Dependency dep:rmh.getBussinessLayerFacade().getDependencyVertices()) if (dep.getClientList().size()>0 && dep.getClientList().get(0) instanceof eu.redseeds.scl.uml.usecases.Actor && !((eu.redseeds.scl.uml.usecases.Actor)dep.getClientList().get(0)).getAllocationToRSLList().isEmpty() && dep.getSupplierList().size()>0 && dep.getSupplierList().get(0) instanceof eu.redseeds.scl.uml.usecases.UseCase && !((eu.redseeds.scl.uml.usecases.UseCase)dep.getSupplierList().get(0)).getAllocationToRSLList().isEmpty()){
			Integer actorid = null;
			shell:
			for (IsAllocatedTo alloc:((eu.redseeds.scl.uml.usecases.Actor)dep.getClientList().get(0)).getAllocationToRSLList()) for (Stereotype ster:alloc.getStereotypeList()) if (ster.getName().equals("DuplicatedUseCaseStructureForUseRelationship")){
					actorid=((eu.redseeds.scl.rsl.rsldomainelements.actors.Actor) alloc.getAllocationSourceList().get(0)).getId();
					break shell;
			}
			if (null==actorid) continue;
			Integer usecaseid = null;
			shell:
			for (IsAllocatedTo alloc:((eu.redseeds.scl.uml.usecases.UseCase)dep.getSupplierList().get(0)).getAllocationToRSLList()) for (Stereotype ster:alloc.getStereotypeList()) if (ster.getName().equals("DuplicatedUseCaseStructureForUseRelationship")){
					usecaseid=((RSLUseCase) alloc.getAllocationSourceList().get(0)).getId();
					break shell;
			}
			if (null==usecaseid) continue;
			if (usecaseid==((RSLUseCase) pUseCase).getId()){
				UseCase uc = null;
				shell:
				for (IsAllocatedTo alloc:((RSLUseCase)aUseCase).getAllocationToUMLList()) for (Stereotype ster:alloc.getStereotypeList()) if (ster.getName().equals("DuplicatedUseCaseStructureForUseRelationship")){
						uc=(UseCase) alloc.getAllocationTargetList().get(0);
						break shell;
				}
				boolean contains = false;
				for (Dependency depen:rmh.getBussinessLayerFacade().getDependencyVertices())
					if (!depen.getClientList().isEmpty() && depen.getClientList().get(0) instanceof eu.redseeds.scl.uml.usecases.Actor && depen.getClientList().get(0).getId()==dep.getClientList().get(0).getId() && !depen.getSupplierList().isEmpty() && depen.getSupplierList().get(0) instanceof eu.redseeds.scl.uml.usecases.UseCase && depen.getSupplierList().get(0).getId()==uc.getId()){
						contains = true;
						break;
				}
				if (!contains){
					dep.removeSupplier(dep.getSupplierList().get(0));
					dep.addSupplier(uc);
				} else dep.delete();
			}
		}
		for (NonInvocationRelationship nir : rmh.getBussinessLayerFacade().getNonInvocationRelationshipVertices()){
			if (!nir.getSourceList().isEmpty() && nir.getSourceList().get(0).getId()==((RSLUseCase) pUseCase).getId()){
				nir.removeSource((Requirement) nir.getSourceList().get(0));
				nir.addSource((Requirement) aUseCase);
			}
			if (!nir.getTargetList().isEmpty() && nir.getTargetList().get(0).getId()==((RSLUseCase) pUseCase).getId()){
				nir.removeTarget((Requirement) nir.getTargetList().get(0));
				nir.addTarget((Requirement) aUseCase);
			}
		}
		pUseCase.delete();
		rmh.saveSCProject();
		return aUseCase;
	}

}