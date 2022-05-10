package eu.remics.recovery.model.domainlogic.usecases;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;

import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.NounPhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.VerbPhraseDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentations.descriptiverequirementrepresentations.ConstrainedLanguageScenarioDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.constrainedlanguagesentences.ConstrainedLanguageSentenceDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.controlsentences.ConditionSentenceDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.controlsentences.InvocationSentenceDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.controlsentences.PostconditionSentenceDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.controlsentences.PreconditionSentenceDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.svosentences.SVOSentenceDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementsPackageDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.UseCaseDTO;
import eu.redseeds.scl.rsl.rslrequirementrepresentations.descriptiverequirementrepresentations.ConstrainedLanguageScenario;
import eu.redseeds.scl.rsl.rslrequirementrepresentationsentences.constrainedlanguagesentences.ConstrainedLanguageSentence;
import eu.redseeds.scl.rsl.rslrequirementrepresentationsentences.controlsentences.InclusionType;
import eu.redseeds.scl.rsl.rslrequirementrepresentationsentences.controlsentences.InvocationSentence;
import eu.redseeds.scl.rsl.rslrequirements.requirementsspecifications.RSLUseCase;
import eu.remics.recovery.model.RecoveryModelHelper;
import eu.remics.recovery.model.domainlogic.recoveredscenarios.MNameMaper;
import eu.remics.recovery.model.domainlogic.recoveredscenarios.MRecoveredScenario;
import eu.remics.recovery.model.dto.XScenariosCommonPart;
import eu.remics.recovery.model.preferences.MConfiguration;

public class MScenario {

	/**
	 * Creates use case from scenario
	 * 
	 * @param scenario scenario
	 * @param name name for use case
	 * @param parent package for use case
	 * @return created use case
	 */
	public static UseCaseDTO createsUseCase(UseCaseDTO scenario, String name, RequirementsPackageDTO parent){
		RecoveryModelHelper rmh = RecoveryModelHelper.instance((RSLUseCase) scenario);
		UseCaseDTO uc;
		if (null==(uc=rmh.getBussinessLayerFacade().getUseCaseByName(scenario.getName().substring(1))) || !uc.getConstrainedLanguageScenarioDTOList().isEmpty()) uc=rmh.getBussinessLayerFacade().createUseCaseDTO();
		uc.setName(name);
		ConstrainedLanguageScenarioDTO scen = rmh.getBussinessLayerFacade().createConstrainedLanguageScenarioDTO();
		uc.addConstrainedLanguageScenario(scen);
		PreconditionSentenceDTO pre = rmh.getBussinessLayerFacade().createPreconditionSentenceDTO();
		pre.setSentenceText("");
		scen.addScenarioStep(pre);
		scen = MRecoveredScenario.copyScenario(scenario,scen,null);
		scen.setName(name);
		if (MConfiguration.isAddPostToMain()){
			PostconditionSentenceDTO pc = rmh.getBussinessLayerFacade().createPostconditionSentenceDTO();
			pc.setSuccess(true);
			scen.addScenarioStep(pc);
		}
		addInvokedBy(scen,scenario);
		MRecoveredScenario.addDervivedScenario(scenario, scen);
		if (null==parent && null==(parent=rmh.getRequirementsPackage("Scenarios"))){
			parent=rmh.getBussinessLayerFacade().createRequirementsPackageDTO();
			parent.setName("Scenarios");
			rmh.getRequirementsSpecification().addRequirementsPackageDTO(parent);
		}
		parent.addUseCase(uc);
		postAddInvokedBy(scenario, uc);
		rmh.saveSCProject();
		return uc;
	}

	/**
	 * Deassigns scenario from use case
	 * 
	 * @param scenario scenario
	 */
	public static void deassigns(ConstrainedLanguageScenarioDTO scenario){
		RecoveryModelHelper rmh = RecoveryModelHelper.instance((ConstrainedLanguageScenario) scenario);
		List<ConstrainedLanguageScenarioDTO> scenarios = scenario.getParent().getConstrainedLanguageScenarioDTOList();
		for (RequirementDTO uc:rmh.getRecoveredScenariosPackage().getRequirements()) if (uc instanceof UseCaseDTO && null!=((UseCaseDTO) uc).getName() && ((UseCaseDTO) uc).getName().charAt(0)=='~' && scenario.equals(MRecoveredScenario.getDerivedScenario((UseCaseDTO)uc))){
			UseCaseDTO tempuc = null;
			boolean finished = false;
			loop:
			while(!finished){
				for (InvocationSentence inv:rmh.getBussinessLayerFacade().getInvocationSentenceVertices()) if (null!=((InvocationSentenceDTO)inv).getInvokedUseCase() && ((InvocationSentenceDTO)inv).getInvokedUseCase().equals(scenario.getParent()) && !(((InvocationSentenceDTO)inv).getOwningScenarios().size()==1 && ((InvocationSentenceDTO)inv).getOwningScenarios().contains(scenario))){
					for (RequirementDTO uc2:rmh.getRecoveredScenariosPackage().getRequirements()) if (uc2 instanceof UseCaseDTO && null!=((UseCaseDTO) uc2).getName() && ((UseCaseDTO) uc2).getName().charAt(0)=='~'){
						if(((InvocationSentenceDTO) inv).getOwningScenarios().contains(MRecoveredScenario.getDerivedScenario((UseCaseDTO)uc2))) 
							for(ConstrainedLanguageSentenceDTO sent:((UseCaseDTO) uc2).getConstrainedLanguageScenarioDTOList().get(0).getScenarioSentenceList()) if (sent instanceof InvocationSentenceDTO && uc.equals(((InvocationSentenceDTO) sent).getInvokedUseCase())){
								if (null==tempuc){
									tempuc = rmh.getBussinessLayerFacade().createUseCaseDTO();
									tempuc.setName(((InvocationSentenceDTO) sent).getInvokedUseCase().getName().substring(1));
								}
								((InvocationSentenceDTO)inv).setInvokedUseCase(tempuc);
								continue loop;
						}
						if (((InvocationSentenceDTO) inv).getOwningScenarios().get(0).getParent().getConstrainedLanguageScenarioDTOList().contains(MRecoveredScenario.getDerivedScenario((UseCaseDTO)uc2))){
							if (null!= MRecoveredScenario.getInvokedBy((UseCaseDTO) uc) && MRecoveredScenario.getInvokedBy((UseCaseDTO) uc).equals(((UseCaseDTO) uc2).getName().substring(1))){
								if (null==tempuc){
									tempuc = rmh.getBussinessLayerFacade().createUseCaseDTO();
									tempuc.setName(uc.getName().substring(1));
								}
								((InvocationSentenceDTO)inv).setInvokedUseCase(tempuc);
								continue loop;
							}
						}
					}
				}
				finished = true;
			}
			MRecoveredScenario.removeDerivedScenario((UseCaseDTO)uc);
		}
		scenario.delete();
		for (ConstrainedLanguageScenarioDTO scen: scenarios) for (ConstrainedLanguageSentenceDTO sent:scen.getScenarioSentenceList()) if ((sent instanceof ConditionSentenceDTO) && scen.getPreviousSentence(sent).getOwningScenarios().size()==sent.getOwningScenarios().size()) for (ConstrainedLanguageScenarioDTO scen2 : sent.getOwningScenarios()) scen2.removeScenarioStep(sent);
		rmh.saveSCProject();
	}
	
	/**
	 * Split recovered scenario into two parts
	 * 
	 * @param name name of new part
	 * @param scenario scenario
	 * @param num place of division
	 * @return new part
	 */
	public static UseCaseDTO split(String name, UseCaseDTO scenario, int num){
		RecoveryModelHelper rmh = RecoveryModelHelper.instance((RSLUseCase) scenario);
		UseCaseDTO uc = rmh.getBussinessLayerFacade().createUseCaseDTO();
		uc.setName("~"+name);
		rmh.getRecoveredScenariosPackage().addUseCase(uc);
		ConstrainedLanguageScenarioDTO scen2=rmh.getBussinessLayerFacade().createConstrainedLanguageScenarioDTO();
		uc.addConstrainedLanguageScenario(scen2);
		scen2=moveSubscenario(scenario.getConstrainedLanguageScenarioDTOList().get(0),scen2,num,scenario.getConstrainedLanguageScenarioDTOList().get(0).getScenarioSentenceList().size(),null);
		scen2.setName(name);
		ConstrainedLanguageScenarioDTO scen1=rmh.getBussinessLayerFacade().createConstrainedLanguageScenarioDTO();
		scenario.addConstrainedLanguageScenario(scen1);
		scen1=moveSubscenario(scenario.getConstrainedLanguageScenarioDTOList().get(0),scen1,0,num,null);
		scen1.setName(scenario.getConstrainedLanguageScenarioDTOList().get(0).getName());
		scenario.getConstrainedLanguageScenarioDTOList().get(0).delete();
		MRecoveredScenario.setTestScriptFile(uc, MRecoveredScenario.getTestScriptFile(scenario));
		MRecoveredScenario.setTestScriptName(uc, MRecoveredScenario.getTestScriptName(scenario));
		if (null==MRecoveredScenario.getModified(scenario) || MRecoveredScenario.getModified(scenario).equals("split")){
			MRecoveredScenario.setModified(scenario, "split");
			MRecoveredScenario.setModified(uc, "split");
		} else {
			MRecoveredScenario.setModified(scenario, "both");
			MRecoveredScenario.setModified(uc, "both");
		}
		rmh.saveSCProject();
		return uc;
	}
	
	/**
	 * Add one recovered scenario in the end of another
	 * 
	 * @param name name of result
	 * @param scenario scenario
	 * @param scenario2 second scenario
	 * @return merged scenario
	 */
	public static UseCaseDTO unsplit(String name, UseCaseDTO scenario, UseCaseDTO scenario2){
		RecoveryModelHelper rmh = RecoveryModelHelper.instance((RSLUseCase) scenario);
		moveSubscenario(scenario2.getConstrainedLanguageScenarioDTOList().get(0),scenario.getConstrainedLanguageScenarioDTOList().get(0),0,scenario2.getConstrainedLanguageScenarioDTOList().get(0).getScenarioSentenceList().size(),null);
		scenario.setName("~"+name);
		scenario.getConstrainedLanguageScenarioDTOList().get(0).setName(name);
		if (null!=MRecoveredScenario.getTestScriptFile(scenario) && !MRecoveredScenario.getTestScriptFile(scenario).equals(MRecoveredScenario.getTestScriptFile(scenario2))) MRecoveredScenario.setTestScriptFile(scenario,null);
		if (null!=MRecoveredScenario.getTestScriptName(scenario) && !MRecoveredScenario.getTestScriptName(scenario).equals(MRecoveredScenario.getTestScriptName(scenario2))) MRecoveredScenario.setTestScriptName(scenario,null);
		if ((null==MRecoveredScenario.getModified(scenario) || MRecoveredScenario.getModified(scenario).equals("merge")) && (null==MRecoveredScenario.getModified(scenario2) || MRecoveredScenario.getModified(scenario2).equals("merge"))) MRecoveredScenario.setModified(scenario, "merge");
		else MRecoveredScenario.setModified(scenario, "both");
		scenario2.delete();
		rmh.saveSCProject();
		return scenario;
	}
	
	/**
	 * Move given number of sentences of one scenario to another (or to new scenario if target scenario is null) beginning from sentence number given as start, if reference scenario is not null move the same sentences in the same places from it
	 * 
	 * @param scenario scenario
	 * @param targetScenario target scenario
	 * @param start start sentence number
	 * @param num number of moved sentence
	 * @param referenceScenario reference scenario
	 * @return scenario with moved sentences
	 */
	public static ConstrainedLanguageScenarioDTO moveSubscenario(ConstrainedLanguageScenarioDTO scenario, ConstrainedLanguageScenarioDTO targetScenario, int start, int num, ConstrainedLanguageScenarioDTO referenceScenario){
		RecoveryModelHelper rmh = RecoveryModelHelper.instance((ConstrainedLanguageScenario) scenario);
		targetScenario.setName(scenario.getName());
		int i=0;
		boolean common = null!=referenceScenario;
		for (ConstrainedLanguageSentenceDTO sent:scenario.getScenarioSentenceList()){
			if (i>=start && i<start+num){
				if (null!=referenceScenario && sent instanceof ConditionSentenceDTO){
					scenario.removeScenarioStep(sent);
				} else {
					if (!MConfiguration.isCheckPrefixInvcationAndConditionSentences() && common && referenceScenario.getScenarioSentenceList().size()>targetScenario.getScenarioSentenceList().size() && (referenceScenario.getScenarioSentenceList().get(targetScenario.getScenarioSentenceList().size()) instanceof ConditionSentenceDTO || referenceScenario.getScenarioSentenceList().get(targetScenario.getScenarioSentenceList().size()) instanceof InvocationSentenceDTO && !(sent instanceof InvocationSentenceDTO))){
						targetScenario.addScenarioStep(referenceScenario.getScenarioSentenceList().get(targetScenario.getScenarioSentenceList().size()));
					}
					if (common && referenceScenario.getScenarioSentenceList().size()>targetScenario.getScenarioSentenceList().size() && MScenario.equalsSentence(referenceScenario.getScenarioSentenceList().get(targetScenario.getScenarioSentenceList().size()), sent)){
						targetScenario.addScenarioStep(referenceScenario.getScenarioSentenceList().get(targetScenario.getScenarioSentenceList().size()));
					} else {
						if (common){
							ConditionSentenceDTO con = null;
							for (ConstrainedLanguageScenarioDTO scen : referenceScenario.getParent().getConstrainedLanguageScenarioDTOList()) if (((ConstrainedLanguageScenario)targetScenario).getId()!=((ConstrainedLanguageScenario)scen).getId() && targetScenario.getScenarioSentenceList().size()-1<scen.getScenarioSentenceList().size() && ((ConstrainedLanguageSentence)scen.getScenarioSentenceList().get(targetScenario.getScenarioSentenceList().size()-1)).getId()==((ConstrainedLanguageSentence)referenceScenario.getScenarioSentenceList().get(targetScenario.getScenarioSentenceList().size()-1)).getId() && !(scen.getScenarioSentenceList().get(targetScenario.getScenarioSentenceList().size()-1) instanceof ConditionSentenceDTO) && (scen.getScenarioSentenceList().size()==targetScenario.getScenarioSentenceList().size() || !(scen.getScenarioSentenceList().get(targetScenario.getScenarioSentenceList().size()) instanceof ConditionSentenceDTO))){
								if (null==con){
									con = rmh.getBussinessLayerFacade().createConditionSentenceDTO();
									con.setSentenceText("");
								}
								scen.insertScenarioStepAfter(scen.getScenarioSentenceList().get(targetScenario.getScenarioSentenceList().size()-1),con);
							}
							if (targetScenario.getLastSentence() instanceof ConditionSentenceDTO) targetScenario.removeScenarioStep(targetScenario.getLastSentence());
							con = rmh.getBussinessLayerFacade().createConditionSentenceDTO();
							con.setSentenceText("");
							targetScenario.addScenarioStep(con);
							common=false;
						}
						if (((RSLUseCase)sent.getOwningScenarios().get(0).getParent()).getId()!=((RSLUseCase)targetScenario.getParent()).getId() && sent instanceof InvocationSentenceDTO){
							InvocationSentenceDTO inv=rmh.getBussinessLayerFacade().createInvocationSentenceDTO();
							inv.setInclusionType(InclusionType.INSERT);
							targetScenario.addScenarioStep(inv);
							inv.setInvokedUseCase(((InvocationSentenceDTO)sent).getInvokedUseCase());
						} else if (null!=referenceScenario && sent.getOwningScenarios().size()>1){
							if (sent instanceof SVOSentenceDTO){
								SVOSentenceDTO step = rmh.getBussinessLayerFacade().createSVOSentenceDTO();
			    				step.setPredicate((VerbPhraseDTO) ((SVOSentenceDTO) sent).getPredicate().copy(false));
			    				step.setSubject((NounPhraseDTO) ((SVOSentenceDTO) sent).getSubject().copy(false));
			    				step.setRecipient(((SVOSentenceDTO) sent).getRecipient());
			    				targetScenario.addScenarioStep(step);
							}
						} else targetScenario.addScenarioStep(sent);
					}
	    		}
			}
			i++;
    	}
		if (common) {
			targetScenario.delete();
			return null;
		}
		return targetScenario;
	}

    /**
     * Compare two constrained language sentences
     * 
     * @param sent1 first sentence
     * @param sent2 second sentence
     * @return true if sentences are equals
     */
    public static boolean equalsSentence(ConstrainedLanguageSentenceDTO sent1, ConstrainedLanguageSentenceDTO sent2){
		if (sent1 instanceof SVOSentenceDTO && sent2 instanceof SVOSentenceDTO) return (null==((SVOSentenceDTO) sent1).getSubject() && null==((SVOSentenceDTO) sent2).getSubject()) || null!=((SVOSentenceDTO) sent1).getSubject() && ((SVOSentenceDTO) sent1).getSubject().equals(((SVOSentenceDTO) sent2).getSubject()) && ((null==((SVOSentenceDTO) sent1).getPredicate() && null==((SVOSentenceDTO) sent2).getPredicate()) || null!=((SVOSentenceDTO) sent1).getPredicate() && ((SVOSentenceDTO) sent1).getPredicate().equals(((SVOSentenceDTO) sent2).getPredicate()));
    	if (sent1 instanceof InvocationSentenceDTO && sent2 instanceof InvocationSentenceDTO) return (null==((InvocationSentenceDTO) sent1).getInvokedUseCase() && null==((InvocationSentenceDTO) sent2).getInvokedUseCase()) || null!=((InvocationSentenceDTO) sent1).getInvokedUseCase() && (((InvocationSentenceDTO) sent1).getInvokedUseCase().equals(((InvocationSentenceDTO) sent2).getInvokedUseCase()) || (null!=MRecoveredScenario.getDerivedScenario(((InvocationSentenceDTO) sent1).getInvokedUseCase()) && MRecoveredScenario.getDerivedScenario(((InvocationSentenceDTO) sent1).getInvokedUseCase()).getParent().equals(((InvocationSentenceDTO) sent2).getInvokedUseCase())) || (null!=MRecoveredScenario.getDerivedScenario(((InvocationSentenceDTO) sent2).getInvokedUseCase()) && MRecoveredScenario.getDerivedScenario(((InvocationSentenceDTO) sent2).getInvokedUseCase()).getParent().equals(((InvocationSentenceDTO) sent1).getInvokedUseCase())));
    	if (sent1 instanceof ConditionSentenceDTO && sent2 instanceof ConditionSentenceDTO) return ((ConditionSentenceDTO) sent1).getSentenceText().equals(((ConditionSentenceDTO) sent2).getSentenceText());
    	if(sent1 instanceof PreconditionSentenceDTO && sent2 instanceof PreconditionSentenceDTO) return ((PreconditionSentenceDTO) sent1).getSentenceText().equals(((PreconditionSentenceDTO) sent2).getSentenceText());
    	if (sent1 instanceof PostconditionSentenceDTO && sent2 instanceof PostconditionSentenceDTO) return ((PostconditionSentenceDTO) sent1).isSuccess()== ((PostconditionSentenceDTO) sent2).isSuccess() && (null==((PostconditionSentenceDTO) sent1).getSentenceText() && null==((PostconditionSentenceDTO) sent2).getSentenceText() || ((PostconditionSentenceDTO) sent1).getSentenceText().equals(((PostconditionSentenceDTO) sent2).getSentenceText()));
    	return false;
    }
    
	/**
	 * Searches occurrence of sentences sequence in other sentences sequence
	 * 
	 * @param x first sentence sequence
	 * @param y second sentence sequence
	 * @return shift to occurrence
	 */
	private static int compareSentences(List<ConstrainedLanguageSentenceDTO> x, List<ConstrainedLanguageSentenceDTO> y){
		int[] t=new int[x.size()];
		int i=2,j=0;
        t[0]=-1;
        if (t.length>1) t[1]=0;
        while(i<x.size()){
            if (equalsSentence(x.get(i - 1),x.get(j))){
                t[i] = j + 1;
                i++;
                j++;
            } else if (j>0)j=t[j];
            else {
                t[i] = 0;
                i++;
            }
        }
        i=0;
        j=0;
        while (j+i < y.size()) {
            if (equalsSentence(x.get(i),y.get(j+i))){
                i++;
                if (i==x.size()) return j;
            } else {
                j = j + i - t[i];
                if (i>0) i=t[i];
            }
        }
        return -1;
	}
	
	/**
	 * Searches occurrence of sentences sequence from scenario in sentences of reference scenario, result is update of condition and invocation sentences if needed
	 * 
	 * @param referenceScenario reference scenario
	 * @param scenario scenario
	 * @return three integers, where first is length of common parts, second is the shift between scenarios, and third is shift to start of common part
	 */
	public static int[] compareScenarios(ConstrainedLanguageScenarioDTO referenceScenario, ConstrainedLanguageScenarioDTO scenario){
		int[] l = compareScenariosToFindCommonParts(referenceScenario,scenario);
		if  (MConfiguration.isCheckPrefixInvcationAndConditionSentences()) return l;
		int i=0,j=0,k=0;
		for (ConstrainedLanguageSentenceDTO sent:referenceScenario.getScenarioSentenceList()){
			if (sent instanceof SVOSentenceDTO ){
				i++;
				if (l[1]<0 && i>l[2]+l[1] || l[1]>=0 && i>l[2]) break;
			} else j++;
		}
		i=0;
		for (ConstrainedLanguageSentenceDTO sent:scenario.getScenarioSentenceList()){
			if (sent instanceof SVOSentenceDTO ){
				i++;
				if (l[1]>0 && i>l[2]-l[1] || l[1]<=0 && i>l[2]) break;
			} else k++;
		}
		l[2]+=l[1]>0?Math.max(j,k-l[1]):Math.max(j+l[1],k);
		l[1]+=-k+j;
		return l;
	}
	
	/**
	 * Returns list of SVO sentences based on list of all sentences
	 * 
	 * @param scenario scenario
	 * @return list of SVO sentences
	 */
	private static List<ConstrainedLanguageSentenceDTO> getSVOSentences(ConstrainedLanguageScenarioDTO scenario){
		List<ConstrainedLanguageSentenceDTO> sents = new ArrayList<ConstrainedLanguageSentenceDTO>();
		for(ConstrainedLanguageSentenceDTO sent:scenario.getScenarioSentenceList()) if (sent instanceof SVOSentenceDTO) sents.add(sent);
		return sents;
	}
	
	/**
	 * Searches for the longest common parts of scenarios
	 * 
	 * @return list of scenarios common parts
	 */
	public static List<XScenariosCommonPart> findScenarioCommonParts(){
		RecoveryModelHelper rmh = RecoveryModelHelper.instance();
		List<XScenariosCommonPart> scps = new ArrayList<XScenariosCommonPart>();
		for (ConstrainedLanguageScenario scen1:rmh.getBussinessLayerFacade().getConstrainedLanguageScenarioVertices()){
			for (ConstrainedLanguageScenario scen2:rmh.getBussinessLayerFacade().getConstrainedLanguageScenarioVertices()) if (!scen1.equals(scen2) && null==MRecoveredScenario.getDerivedScenario(((ConstrainedLanguageScenarioDTO)scen2).getParent()) && null==MRecoveredScenario.getDerivedScenario(((ConstrainedLanguageScenarioDTO)scen1).getParent()) && !((ConstrainedLanguageScenarioDTO)scen1).getParent().equals(((ConstrainedLanguageScenarioDTO)scen2).getParent())) {
				int[] l=compareScenariosToFindCommonParts((ConstrainedLanguageScenarioDTO)scen1,(ConstrainedLanguageScenarioDTO)scen2);
				if (1<l[0] || ( 1 == l[0] && ((ConstrainedLanguageScenarioDTO)scen1).getScenarioSentenceList().get(0).equals(((ConstrainedLanguageScenarioDTO)scen2).getScenarioSentenceList().get(0)))){
					XScenariosCommonPart scp;
					if (l[1]>0 || l[1]==0 && ((ConstrainedLanguageScenarioDTO)scen1).getScenarioSentenceList().size()>((ConstrainedLanguageScenarioDTO)scen2).getScenarioSentenceList().size()) scp=new XScenariosCommonPart(((ConstrainedLanguageScenarioDTO)scen1),((ConstrainedLanguageScenarioDTO)scen2),l[0]);
					else scp=new XScenariosCommonPart(((ConstrainedLanguageScenarioDTO)scen2),((ConstrainedLanguageScenarioDTO)scen1),l[0]);
					if (!scps.contains(scp)) scps.add(scp);
				}
			}
		}
		Collections.sort(scps);
		return scps;
	}
	
	/**
	 * Searches length of the longest occurrence of sentences sequence from scenario in sentences of reference scenario, result may not take into account condition and invocation sentences (depends on configuration)
	 * 
	 * @param referenceScenario reference scenario
	 * @param scenario scenario
	 * @return three integers, where first is length of common parts, second is the shift between scenarios, and third is shift to start of common part
	 */
	private static int[] compareScenariosToFindCommonParts(ConstrainedLanguageScenarioDTO referenceScenario, ConstrainedLanguageScenarioDTO scenario){
		List<ConstrainedLanguageSentenceDTO> referenceSentences = MConfiguration.isCheckPrefixInvcationAndConditionSentences()?referenceScenario.getScenarioSentenceList():getSVOSentences(referenceScenario);
		List<ConstrainedLanguageSentenceDTO> sentences = MConfiguration.isCheckPrefixInvcationAndConditionSentences()?scenario.getScenarioSentenceList():getSVOSentences(scenario);
		int[] w = new int[3];		
		for (int i=sentences.size()<referenceSentences.size()?sentences.size():referenceSentences.size();i>0;i--){
			for(int j=0;j<=sentences.size()-i;j++){
				int num=compareSentences(sentences.subList(j, j+i), referenceSentences);
				if (num!=-1){
					//length of common part
					w[0]=i;
					//shift between scenarios
					w[1]=num-j;
					//start of common part
					w[2]=w[1]>0?num:j;
					return w;
				}
			}
		}
		return w;
	}
	
	/**
	 * Adds alternate scenario with invocation of other use cases to this scenario
	 * 
	 * @param scenario scenario
	 * @param recoveredScenario recovered scenario from which this scenario is derived
	 */
	public static void addInvokedBy(ConstrainedLanguageScenarioDTO scenario, UseCaseDTO recoveredScenario){
		RecoveryModelHelper rmh = RecoveryModelHelper.instance((ConstrainedLanguageScenario) scenario);
		for (RequirementDTO inv:rmh.getRecoveredScenariosPackage().getRequirements()) if ((inv instanceof UseCaseDTO)) if (null!=((UseCaseDTO) inv).getName()) if (((UseCaseDTO) inv).getName().charAt(0)=='~') if (MNameMaper.mapScriptNameToScenarioName(MRecoveredScenario.getTestScriptName(recoveredScenario)).equals(MRecoveredScenario.getInvokedBy((UseCaseDTO) inv))){
    		boolean contains=false;
    		loop:
			for (ConstrainedLanguageScenarioDTO scen : scenario.getParent().getConstrainedLanguageScenarioDTOList()) for (ConstrainedLanguageSentenceDTO sent:scen.getScenarioSentenceList()) if (sent instanceof InvocationSentenceDTO){
				if (equalsScriptOrFileName((UseCaseDTO) inv,((InvocationSentenceDTO) sent).getInvokedUseCase())) {
					contains = !MessageDialog.openQuestion(new Shell(), "Automatic invoke addition", "Use case \"" + scenario.getParent().getName() + "\" contains invocation of \""+((InvocationSentenceDTO) sent).getInvokedUseCase().getName()+"\". Do you want to add invocation of \""+inv.getName().substring(1)+"\"? It has the same script name or script file name.");
					break loop;
				}
			}
			if (!contains){
				InvocationSentenceDTO step = rmh.getBussinessLayerFacade().createInvocationSentenceDTO();
				step.setInclusionType(InclusionType.INSERT);
				UseCaseDTO uc;
				ConstrainedLanguageScenarioDTO dervscen = MRecoveredScenario.getDerivedScenario((UseCaseDTO)inv);
				if (null==dervscen){
					if (null==(uc=rmh.getBussinessLayerFacade().getUseCaseByName(inv.getName().substring(1)))){
						uc=rmh.getBussinessLayerFacade().createUseCaseDTO();
						uc.setName(inv.getName().substring(1));
					}
				} else uc=dervscen.getParent();
				ConstrainedLanguageScenarioDTO scen=rmh.getBussinessLayerFacade().createConstrainedLanguageScenarioDTO();
				scenario.getParent().addConstrainedLanguageScenario(scen);
				scen=moveSubscenario(scenario,scen,0,scenario.getLastSentence() instanceof PostconditionSentenceDTO?scenario.getScenarioSentenceList().size()-1:scenario.getScenarioSentenceList().size(),null);
				scen.setName("invocation of "+uc.getName());
				ConditionSentenceDTO con;
				ConstrainedLanguageSentenceDTO last = scenario.getLastSentence() instanceof PostconditionSentenceDTO?scenario.getPreviousSentence(scenario.getLastSentence()):scenario.getLastSentence();
				if (!(last instanceof ConditionSentenceDTO)){
					con = rmh.getBussinessLayerFacade().createConditionSentenceDTO();
					con.setSentenceText("user not wants to do anything more");
					scenario.insertScenarioStepAfter(last, con);
				} else scen.removeScenarioStep(scen.getLastSentence());
				con = rmh.getBussinessLayerFacade().createConditionSentenceDTO();
				con.setSentenceText("user wants to preform "+uc.getName());
				scen.addScenarioStep(con);
				scen.addScenarioStep(step);
				step.setInvokedUseCase(uc);
				if (scenario.getLastSentence() instanceof PostconditionSentenceDTO){
	    			PostconditionSentenceDTO pc = rmh.getBussinessLayerFacade().createPostconditionSentenceDTO();
	    			if (null!=((PostconditionSentenceDTO)scenario.getLastSentence()).getSentenceText()) pc.setSentenceText(((PostconditionSentenceDTO)scenario.getLastSentence()).getSentenceText());
	    			pc.setSuccess(((PostconditionSentenceDTO)scenario.getLastSentence()).isSuccess());
	    			scen.addScenarioStep(pc);
	    		}
			}
    	}
	}
	
	/**
	 * Adds alternate scenario with invocation of this scenario to other use case
	 * 
	 * @param oldscenario recovered scenario from which this scenario is derived
	 * @param newusecase use case contains this scenario
	 */
	public static void postAddInvokedBy(UseCaseDTO oldscenario, UseCaseDTO newusecase){
		RecoveryModelHelper rmh = RecoveryModelHelper.instance((RSLUseCase) oldscenario);
		ConstrainedLanguageScenarioDTO scenario;
		if (null!=MRecoveredScenario.getInvokedBy(oldscenario)) for (RequirementDTO inv:rmh.getRecoveredScenariosPackage().getRequirements()) if ((inv instanceof UseCaseDTO)) if (null!=((UseCaseDTO) inv).getName()) if (((UseCaseDTO) inv).getName().charAt(0)=='~') if (MNameMaper.mapScriptNameToScenarioName(MRecoveredScenario.getTestScriptName((UseCaseDTO) inv)).equals(MRecoveredScenario.getInvokedBy(oldscenario))) if (null!=(scenario=MRecoveredScenario.getDerivedScenario((UseCaseDTO) inv)) && ((RSLUseCase)scenario.getParent()).getId()!=((RSLUseCase)newusecase).getId()){
			boolean contains=false;
			loop:
			for (ConstrainedLanguageScenarioDTO scen : scenario.getParent().getConstrainedLanguageScenarioDTOList()) for (ConstrainedLanguageSentenceDTO sent:scen.getScenarioSentenceList())
				if (sent instanceof InvocationSentenceDTO && newusecase.equals(((InvocationSentenceDTO) sent).getInvokedUseCase()) && scen.getLastSentence().equals(sent) && scen.getPreviousSentence(sent) instanceof ConditionSentenceDTO){
					contains = true;
					break loop;
			}
			loop2:
			if (!contains) for (ConstrainedLanguageScenarioDTO scen : scenario.getParent().getConstrainedLanguageScenarioDTOList()) for (ConstrainedLanguageSentenceDTO sent:scen.getScenarioSentenceList())
				if (sent instanceof InvocationSentenceDTO && equalsScriptOrFileName(newusecase,((InvocationSentenceDTO) sent).getInvokedUseCase())) {
					contains = !MessageDialog.openQuestion(new Shell(), "Automatic invoke addition", "Use case \"" + scenario.getParent().getName() + "\" contains invocation of \""+((InvocationSentenceDTO) sent).getInvokedUseCase().getName()+"\". Do you want to add invocation of \""+newusecase.getName()+"\"? It has the same script name or script file name.");
					break loop2;
			}
			if (!contains){
				InvocationSentenceDTO step = rmh.getBussinessLayerFacade().createInvocationSentenceDTO();
	    		step.setInclusionType(InclusionType.INSERT);
	    		ConstrainedLanguageScenarioDTO scen=rmh.getBussinessLayerFacade().createConstrainedLanguageScenarioDTO();
	    		scenario.getParent().addConstrainedLanguageScenario(scen);
	    		scen=moveSubscenario(scenario,scen,0,scenario.getLastSentence() instanceof PostconditionSentenceDTO?scenario.getScenarioSentenceList().size()-1:scenario.getScenarioSentenceList().size(),null);
	    		scen.setName("invocation of "+newusecase.getName());
	    		ConditionSentenceDTO con;
	    		ConstrainedLanguageSentenceDTO last = scenario.getLastSentence() instanceof PostconditionSentenceDTO?scenario.getPreviousSentence(scenario.getLastSentence()):scenario.getLastSentence();
	    		if (!(last instanceof ConditionSentenceDTO)){
	    			con = rmh.getBussinessLayerFacade().createConditionSentenceDTO();
	    			con.setSentenceText("user not wants to do anything more");
	    			scenario.insertScenarioStepAfter(last, con);
	    		} else scen.removeScenarioStep(scen.getLastSentence());
	    		con = rmh.getBussinessLayerFacade().createConditionSentenceDTO();
	    		con.setSentenceText("user wants to preform "+newusecase.getName());
	    		scen.addScenarioStep(con);
	    		scen.addScenarioStep(step);
	    		step.setInvokedUseCase(newusecase);
	    		if (scenario.getLastSentence() instanceof PostconditionSentenceDTO){
	    			PostconditionSentenceDTO pc = rmh.getBussinessLayerFacade().createPostconditionSentenceDTO();
	    			if (null!=((PostconditionSentenceDTO)scenario.getLastSentence()).getSentenceText()) pc.setSentenceText(((PostconditionSentenceDTO)scenario.getLastSentence()).getSentenceText());
	    			pc.setSuccess(((PostconditionSentenceDTO)scenario.getLastSentence()).isSuccess());
	    			scen.addScenarioStep(pc);
	    		}
			}
		}
	}
	
	/**
	 * Checks if scenarios are derived from the same script file
	 * 
	 * @param uc1 first use case or recovered scenario
	 * @param uc2 second use case
	 * @return true if scenarios are derived from the same script file
	 */
	private static boolean equalsScriptOrFileName(UseCaseDTO uc1, UseCaseDTO uc2){
		RecoveryModelHelper rmh = RecoveryModelHelper.instance((RSLUseCase) uc1);
		List<String> sf = new ArrayList<String>();
		if (null!=uc1.getName() && uc1.getName().charAt(0)=='~'){
			sf.add(MRecoveredScenario.getTestScriptName(uc1));
			sf.add(MRecoveredScenario.getTestScriptFile(uc1));
		} else for (RequirementDTO uc:rmh.getRecoveredScenariosPackage().getRequirements()) if ((uc instanceof UseCaseDTO) && null!=((UseCaseDTO) uc).getName() && ((UseCaseDTO) uc).getName().charAt(0)=='~' && uc1.getConstrainedLanguageScenarioDTOList().contains(MRecoveredScenario.getDerivedScenario((UseCaseDTO) uc))){
			sf.add(MRecoveredScenario.getTestScriptName((UseCaseDTO) uc));
			sf.add(MRecoveredScenario.getTestScriptFile((UseCaseDTO) uc));
		}
		for (RequirementDTO uc:rmh.getRecoveredScenariosPackage().getRequirements()) if ((uc instanceof UseCaseDTO) && null!=((UseCaseDTO) uc).getName() && ((UseCaseDTO) uc).getName().charAt(0)=='~' && (uc2.getConstrainedLanguageScenarioDTOList().contains(MRecoveredScenario.getDerivedScenario((UseCaseDTO) uc)) || uc2.equals(rmh.getBussinessLayerFacade().getUseCaseByName(uc.getName().substring(1)))) && ((sf.contains(MRecoveredScenario.getTestScriptName((UseCaseDTO) uc)) || sf.contains(MRecoveredScenario.getTestScriptFile((UseCaseDTO) uc)))))
			return true;
		return false;
	}
	
	/**
	 * Checks if scenario is derived scenario
	 * 
	 * @param scenario scenario
	 * @return true if scenario is derived scenario
	 */
	public static boolean isDerivedScenario(ConstrainedLanguageScenarioDTO scenario){
		RecoveryModelHelper rmh = RecoveryModelHelper.instance((ConstrainedLanguageScenario) scenario);
		for (RequirementDTO uc:rmh.getRecoveredScenariosPackage().getRequirements()) if (uc instanceof UseCaseDTO && null!=((UseCaseDTO) uc).getName() && ((UseCaseDTO) uc).getName().charAt(0)=='~' && scenario.equals(MRecoveredScenario.getDerivedScenario((UseCaseDTO)uc))) return true;
		return false;
	}
	
}