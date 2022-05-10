package eu.remics.recovery.model.domainlogic.recoveredscenarios;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import eu.redseeds.common.SCProjectHelper;
import eu.redseeds.sc.terminology.model.RemoteJGWNL;
import eu.redseeds.scl.model.impl.rsl.rslrequirementrepresentations.descriptiverequirementrepresentations.ConstrainedLanguageScenarioDTOImpl;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.AttributeDataTypesEnum;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.DomainStatementDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionTypesEnum;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.ComplexVerbPhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.NounPhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.SimpleVerbPhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.VerbPhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.terms.PrepositionDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.terms.VerbDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentations.descriptiverequirementrepresentations.ConstrainedLanguageScenarioDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.constrainedlanguagesentences.ConstrainedLanguageSentenceDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.controlsentences.ConditionSentenceDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.controlsentences.InvocationSentenceDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.svosentences.SVOSentenceDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.UseCaseDTO;
import eu.redseeds.scl.rsl.rsldomainelements.notions.Notion;
import eu.redseeds.scl.rsl.rslrequirementrepresentations.descriptiverequirementrepresentations.ConstrainedLanguageScenario;
import eu.redseeds.scl.rsl.rslrequirementrepresentationsentences.constrainedlanguagesentences.ConstrainedLanguageSentence;
import eu.redseeds.scl.rsl.rslrequirementrepresentationsentences.controlsentences.InclusionType;
import eu.redseeds.scl.rsl.rslrequirementrepresentationsentences.svosentences.SVOSentence;
import eu.redseeds.scl.rsl.rslrequirements.requirementsspecifications.RSLUseCase;
import eu.remics.recovery.model.RecoveryModelHelper;
import eu.remics.recovery.model.domainlogic.usecases.MScenario;
import eu.remics.recovery.model.dto.TerminologyOperationFailureException;
import eu.remics.recovery.model.preferences.MConfiguration;

public class MRecoveredScenario {
	
	/**
	 * Set information about script which invokes this recovered scenario (represented by use case) in its description
	 * 
	 * @param call name of script
	 * @param uc this recovered scenario
	 */
	public static void setInvokedBy(String call,UseCaseDTO uc){
		Matcher match = Pattern.compile("InvokedBy: [^\n]*\n").matcher(uc.getDescription()+"\n");
		if(match.find()) uc.setDescription(uc.getDescription().substring(0,match.start())+(match.end()<uc.getDescription().length()?uc.getDescription().substring(match.end()):""));
		if (null!=call) uc.setDescription("InvokedBy: "+call+"\n"+uc.getDescription());
	}

	/**
	 * Get information about script which invokes this recovered scenario (represented by use case) contained in its description
	 * 
	 * @param uc this recovered scenario
	 * @return name of script
	 */
	public static String getInvokedBy(UseCaseDTO uc){
		Matcher match = Pattern.compile("InvokedBy: [^\n]*\n").matcher(uc.getDescription()+"\n");
		if(match.find()) return uc.getDescription().substring(match.start()+11,match.end()-1);
		return null;
	}

	/**
	 * Set information about script file on which is based this recovered scenario (represented by use case) in its description
	 * 
	 * @param uc this use case recovered scenario
	 * @param name name of script file
	 */
	public static void setTestScriptFile(UseCaseDTO uc, String name) {
		Matcher match = Pattern.compile("TestScriptFile: [^\n]*\n").matcher(uc.getDescription()+"\n");
		if(match.find()) uc.setDescription(uc.getDescription().substring(0,match.start())+(match.end()<uc.getDescription().length()?uc.getDescription().substring(match.end()):""));
		if (null!=name) uc.setDescription("TestScriptFile: "+name+"\n"+uc.getDescription());
	}
	
	/**
	 * Get information about script file on which is based this recovered scenario (represented by use case) contained in its description
	 * 
	 * @param uc this recovered scenario
	 * @return name of test script file
	 */
	public static String getTestScriptFile(UseCaseDTO uc){
		Matcher match = Pattern.compile("TestScriptFile: [^\n]*\n").matcher(uc.getDescription()+"\n");
		if(match.find()) return uc.getDescription().substring(match.start()+16,match.end()-1);
		return null;
	}

	/**
	 * Set information about name of the script on which is based this recovered scenario (represented by use case) in its description
	 * 
	 * @param uc this recovered scenario
	 * @param name name of script
	 */
	public static void setTestScriptName(UseCaseDTO uc, String name) {
		Matcher match = Pattern.compile("TestScriptName: [^\n]*\n").matcher(uc.getDescription()+"\n");
		if(match.find()) uc.setDescription(uc.getDescription().substring(0,match.start())+(match.end()<uc.getDescription().length()?uc.getDescription().substring(match.end()):""));
		if (null!=name) uc.setDescription("TestScriptName: "+name+"\n"+uc.getDescription());
	}
	
	/**
	 * Get information about name of the script on which is based this reovered scenario (represented by use case) contained in its description
	 * 
	 * @param uc this recovered scenario
	 * @return name of script
	 */
	public static String getTestScriptName(UseCaseDTO uc){
		Matcher match = Pattern.compile("TestScriptName: [^\n]*\n").matcher(uc.getDescription()+"\n");
		if(match.find()) return uc.getDescription().substring(match.start()+16,match.end()-1);
		return null;
	}
	
	/**
	 * Set information about modification (split or merge) of recovered scenario (represented by use case) in its description.
	 * 
	 * @param uc this recovered scenario
	 * @param type type of modification
	 */
	public static void setModified(UseCaseDTO uc, String type) {
		Matcher match = Pattern.compile("Modified: [^\n]*\n").matcher(uc.getDescription()+"\n");
		if(match.find()) uc.setDescription(uc.getDescription().substring(0,match.start())+(match.end()<uc.getDescription().length()?uc.getDescription().substring(match.end()):""));
		if (null!=type) uc.setDescription("Modified: "+type+"\n"+uc.getDescription());
	}
	
	/**
	 * Get information about modification (split or merge) of recovered scenario (represented by use case) contained in its description.
	 * 
	 * @param uc this recovered scenario
	 * @return type of modification
	 */
	public static String getModified(UseCaseDTO uc){
		Matcher match = Pattern.compile("Modified: [^\n]*\n").matcher(uc.getDescription()+"\n");
		if(match.find()) return uc.getDescription().substring(match.start()+10,match.end()-1);
		return null;
	}
	
	/**
	 * Set information about derived scenario in recovered scenario (represented by use case) description
	 * 
	 * @param uc this recovered scenario
	 * @param scenario derived scenario
	 */
	public static void addDervivedScenario(UseCaseDTO uc, ConstrainedLanguageScenarioDTO scenario){
		Matcher match = Pattern.compile("DerivedScenario: [^\n]*\n").matcher(uc.getDescription()+"\n");
		if(match.find()) uc.setDescription(uc.getDescription().substring(0,match.start())+(match.end()<uc.getDescription().length()?uc.getDescription().substring(match.end()):""));
		uc.setDescription("DerivedScenario: "+((ConstrainedLanguageScenarioDTOImpl)scenario).getId()+"\n"+uc.getDescription());
	}
	
	/**
	 * Remove information about derived scenario in recovered scenario (represented by use case) description
	 * 
	 * @param uc this recovered scenario
	 */
	public static void removeDerivedScenario(UseCaseDTO uc){
		Matcher match = Pattern.compile("DerivedScenario: [^\n]*\n").matcher(uc.getDescription()+"\n");
		if(match.find()) uc.setDescription(uc.getDescription().substring(0,match.start())+(match.end()<uc.getDescription().length()?uc.getDescription().substring(match.end()):""));
	}
	
	/**
	 * Get information about derived scenario from recovered scenario (represented by use case) description
	 * 
	 * @param uc this recovered scenario
	 * @return derived scenario
	 */
	public static ConstrainedLanguageScenarioDTO getDerivedScenario(UseCaseDTO uc){
		RecoveryModelHelper rmh = RecoveryModelHelper.instance((RSLUseCase) uc);
		Matcher match = Pattern.compile("DerivedScenario: [^\n]*\n").matcher(uc.getDescription()+"\n");
		if(match.find()) return rmh.getBussinessLayerFacade().getConstrainedLanguageScenarioByVertexID(Integer.parseInt(uc.getDescription().substring(match.start()+17,match.end()-1)));
		return null;		
	}
	
	/**
	 * Adds new sentence about invocation other use case witch proper scenario
	 * 
	 * @param scenario scenario
	 * @param call name of invoked use case
	 * @return this scenario
	 */
	public static ConstrainedLanguageScenarioDTO addInvokedSentence(ConstrainedLanguageScenarioDTO scenario, String call) {
		RecoveryModelHelper rmh = RecoveryModelHelper.instance((ConstrainedLanguageScenario) scenario);
		InvocationSentenceDTO sent = rmh.getBussinessLayerFacade().createInvocationSentenceDTO();
		sent.setInclusionType(InclusionType.INSERT);
		scenario.addScenarioStep(sent);
		UseCaseDTO uc;
		if (null==(uc=rmh.getBussinessLayerFacade().getUseCaseByName("~"+call))){
			uc = rmh.getBussinessLayerFacade().createUseCaseDTO();
			uc.setName("~"+call);
		}
		sent.setInvokedUseCase(uc);
        return scenario;
    }
	
	/**
	 * Adds new sentence about action of the system (more accuracy about displaying screens)
	 * 
	 * @param scenario scenario
	 * @param windname name of window
	 * @return window assigned notion
	 * @throws TerminologyOperationFailureException
	 */
	public static NotionDTO addSystemSentence(ConstrainedLanguageScenarioDTO scenario, String windname) throws TerminologyOperationFailureException {
		RecoveryModelHelper rmh = RecoveryModelHelper.instance((ConstrainedLanguageScenario) scenario);
		//if the confirmdata flag is turned off, removes last sentence related to the window, proceeded the introduction of data (if such window exist)
		if (!MConfiguration.isConfirmdata() && scenario.getScenarioSentenceList().size()>1 && scenario.getLastSentence() instanceof SVOSentenceDTO){
			SVOSentenceDTO lastSentence = (SVOSentenceDTO) scenario.getLastSentence();
			if (MConfiguration.isProcessdata() && MConfiguration.isValidatedata() && "process".equals(lastSentence.getPredicate() instanceof SimpleVerbPhraseDTO?((SimpleVerbPhraseDTO) lastSentence.getPredicate()).getVerb().getName():((ComplexVerbPhraseDTO) lastSentence.getPredicate()).getSimpleVerbPhrase().getVerb().getName()))
    			lastSentence = (SVOSentenceDTO) scenario.getPreviousSentence(scenario.getPreviousSentence(lastSentence));
    		else if ((MConfiguration.isValidatedata() && "validates".equals(lastSentence.getPredicate() instanceof SimpleVerbPhraseDTO?((SimpleVerbPhraseDTO) lastSentence.getPredicate()).getVerb().getName():((ComplexVerbPhraseDTO) lastSentence.getPredicate()).getSimpleVerbPhrase().getVerb().getName())) || (MConfiguration.isProcessdata() && "process".equals(lastSentence.getPredicate() instanceof SimpleVerbPhraseDTO?((SimpleVerbPhraseDTO) lastSentence.getPredicate()).getVerb().getName():((ComplexVerbPhraseDTO) lastSentence.getPredicate()).getSimpleVerbPhrase().getVerb().getName())))
    			lastSentence=(SVOSentenceDTO) scenario.getPreviousSentence(lastSentence);
			if("selects".equals(lastSentence.getPredicate() instanceof SimpleVerbPhraseDTO?((SimpleVerbPhraseDTO) lastSentence.getPredicate()).getVerb().getName():((ComplexVerbPhraseDTO) lastSentence.getPredicate()).getSimpleVerbPhrase().getVerb().getName()) 
			&& !NotionTypesEnum.Simple_View.tag().equals(rmh.getBussinessLayerFacade().getNotionDTO(((SVOSentenceDTO) lastSentence).getPredicate() instanceof SimpleVerbPhraseDTO?((SimpleVerbPhraseDTO) ((SVOSentenceDTO) lastSentence).getPredicate()).getObject():((ComplexVerbPhraseDTO) ((SVOSentenceDTO) lastSentence).getPredicate()).getSimpleVerbPhrase().getObject()).getType())
			&& scenario.getPreviousSentence(lastSentence) instanceof SVOSentenceDTO 
        	&& ("enters".equals(((SVOSentenceDTO) scenario.getPreviousSentence(lastSentence)).getPredicate() instanceof SimpleVerbPhraseDTO?((SimpleVerbPhraseDTO) ((SVOSentenceDTO) scenario.getPreviousSentence(lastSentence)).getPredicate()).getVerb().getName():((ComplexVerbPhraseDTO) ((SVOSentenceDTO) scenario.getPreviousSentence(lastSentence)).getPredicate()).getSimpleVerbPhrase().getVerb().getName()))){
        		MRecoveredNotion.clean(lastSentence);
        		lastSentence.delete();
			}
		}
        //adds notion and its phases
        String name=rmh.checkNotionName(windname,null,false,true,null);
        //adds new sentence
        SVOSentenceDTO sent = rmh.getBussinessLayerFacade().createSVOSentenceDTO();
        SimpleVerbPhraseDTO vf=rmh.getBussinessLayerFacade().createSimpleVerbPhraseDTO();
        VerbDTO verb = rmh.getBussinessLayerFacade().createVerbDTO();
        verb.setName("shows");
        vf.setVerb(verb);
        NounPhraseDTO noun=rmh.getBussinessLayerFacade().createNounPhraseDTO();
        vf.setObject(noun);
        noun.setNounText(name);
        noun.connect();
        if (noun.getNoun().getSynonymUid()==0){
        	if (SCProjectHelper.getSenseAutoAddAndAssigmentFlag()){
        		try {
        			noun.getNoun().autoAddAndAssignSense();
        		} catch (NullPointerException e){
        			throw new TerminologyOperationFailureException();
        		}
        	}
        	else if (!noun.getNoun().autoAssignSense()) noun.getNoun().setName(noun.getNoun().getName().toLowerCase());
        }
        MRecoveredNotion.cleanNouns(noun.getNoun());
        vf.connect();
        verb.autoAssignSense();
        sent.setPredicate(vf);
        noun=rmh.getBussinessLayerFacade().createNounPhraseDTO();
        sent.setSubject(noun);
        noun.setNounText("System");
        noun.connect();
        if (noun.getNoun().getSynonymUid()==0) noun.getNoun().autoAssignSense();
        sent.setRecipient(rmh.getBussinessLayerFacade().getActorDTO("user"));
        scenario.addScenarioStep(sent);
        return MRecoveredNotion.addNotion(sent, null);
	}
	
	public static ConstrainedLanguageScenarioDTO addSelectionSentence(ConstrainedLanguageScenarioDTO scenario, String name, String type, NotionDTO window, List<String> col, List<String> coltype, List<String> colvalue) throws TerminologyOperationFailureException {
		RecoveryModelHelper rmh = RecoveryModelHelper.instance((ConstrainedLanguageScenario) scenario);
		ConstrainedLanguageSentenceDTO lastSentence = scenario.getLastSentence();
    	if (lastSentence instanceof SVOSentenceDTO){
    		if (MConfiguration.isProcessdata() && MConfiguration.isValidatedata() && "process".equals(((SVOSentenceDTO) lastSentence).getPredicate() instanceof SimpleVerbPhraseDTO?((SimpleVerbPhraseDTO) ((SVOSentenceDTO) lastSentence).getPredicate()).getVerb().getName():((ComplexVerbPhraseDTO) ((SVOSentenceDTO) lastSentence).getPredicate()).getSimpleVerbPhrase().getVerb().getName()))
    			lastSentence = scenario.getPreviousSentence(scenario.getPreviousSentence(lastSentence));
    		else if ((MConfiguration.isValidatedata() && "validates".equals(((SVOSentenceDTO) lastSentence).getPredicate() instanceof SimpleVerbPhraseDTO?((SimpleVerbPhraseDTO) ((SVOSentenceDTO) lastSentence).getPredicate()).getVerb().getName():((ComplexVerbPhraseDTO) ((SVOSentenceDTO) lastSentence).getPredicate()).getSimpleVerbPhrase().getVerb().getName())) || (MConfiguration.isProcessdata() && "process".equals(((SVOSentenceDTO) lastSentence).getPredicate() instanceof SimpleVerbPhraseDTO?((SimpleVerbPhraseDTO) ((SVOSentenceDTO) lastSentence).getPredicate()).getVerb().getName():((ComplexVerbPhraseDTO) ((SVOSentenceDTO) lastSentence).getPredicate()).getSimpleVerbPhrase().getVerb().getName())))
    			lastSentence=scenario.getPreviousSentence(lastSentence);
    		if (MConfiguration.isConfirmdata() && "selects".equals(((SVOSentenceDTO) lastSentence).getPredicate() instanceof SimpleVerbPhraseDTO?((SimpleVerbPhraseDTO) ((SVOSentenceDTO) lastSentence).getPredicate()).getVerb().getName():((ComplexVerbPhraseDTO) ((SVOSentenceDTO) lastSentence).getPredicate()).getSimpleVerbPhrase().getVerb().getName()) && !NotionTypesEnum.Simple_View.tag().equals(rmh.getBussinessLayerFacade().getNotionDTO(((SVOSentenceDTO) lastSentence).getPredicate() instanceof SimpleVerbPhraseDTO?((SimpleVerbPhraseDTO) ((SVOSentenceDTO) lastSentence).getPredicate()).getObject():((ComplexVerbPhraseDTO) ((SVOSentenceDTO) lastSentence).getPredicate()).getSimpleVerbPhrase().getObject()).getType()))
    			lastSentence=scenario.getPreviousSentence(lastSentence);
    	}
    	Set<NotionDTO> fields = new HashSet<NotionDTO>();
		for (int i =0; i<col.size();i++){
			AttributeDataTypesEnum dt = coltype.get(i).equals("String")?AttributeDataTypesEnum.String:(coltype.get(i).equals("Integer")?AttributeDataTypesEnum.Integer:null);
			if (!col.get(i).isEmpty()) fields.add(MRecoveredNotion.addAttribute(col.get(i), dt, null, null, null, rmh));
			else fields.add(MRecoveredNotion.addAttribute("unknown value", dt, null, null, null, rmh));
		}
		String tabname=rmh.checkNotionName(name,fields,false,false,type);
		String elname = rmh.checkNotionName(name,null,true,true,null);
		if (!(lastSentence instanceof SVOSentenceDTO) || !"selects".equals(((SVOSentenceDTO) lastSentence).getPredicate() instanceof SimpleVerbPhraseDTO?((SimpleVerbPhraseDTO) ((SVOSentenceDTO) lastSentence).getPredicate()).getVerb().getName():((ComplexVerbPhraseDTO) ((SVOSentenceDTO) lastSentence).getPredicate()).getSimpleVerbPhrase().getVerb().getName()) || !NotionTypesEnum.Simple_View.tag().equals(rmh.getBussinessLayerFacade().getNotionDTO(((SVOSentenceDTO) lastSentence).getPredicate() instanceof SimpleVerbPhraseDTO?((SimpleVerbPhraseDTO) ((SVOSentenceDTO) lastSentence).getPredicate()).getObject():((ComplexVerbPhraseDTO) ((SVOSentenceDTO) lastSentence).getPredicate()).getSimpleVerbPhrase().getObject()).getType()) || !elname.equals(((SVOSentenceDTO) lastSentence).getPredicate() instanceof SimpleVerbPhraseDTO?((SimpleVerbPhraseDTO) ((SVOSentenceDTO) lastSentence).getPredicate()).getObject().getNounText():((ComplexVerbPhraseDTO) ((SVOSentenceDTO) lastSentence).getPredicate()).getSimpleVerbPhrase().getObject().getNounText())){
			SVOSentenceDTO sentence = rmh.getBussinessLayerFacade().createSVOSentenceDTO();
        	ComplexVerbPhraseDTO compl = rmh.getBussinessLayerFacade().createComplexVerbPhraseDTO();
        	sentence.setPredicate(compl);
        	PrepositionDTO prep=rmh.getBussinessLayerFacade().createPrepostitionDTO();
        	prep.setName("from");
        	compl.setPreposition(prep);
        	NounPhraseDTO noun=rmh.getBussinessLayerFacade().createNounPhraseDTO();
        	compl.setObject(noun);
        	noun.setNounText(tabname);
        	noun.connect();
        	if (noun.getNoun().getSynonymUid()==0){
        		if (SCProjectHelper.getSenseAutoAddAndAssigmentFlag()){
        			try {
        				noun.getNoun().autoAddAndAssignSense();
        			} catch (NullPointerException e){
        				throw new TerminologyOperationFailureException();
        			}
        		}
        		else if (!noun.getNoun().autoAssignSense()) noun.getNoun().setName(noun.getNoun().getName().toLowerCase());
        	}
        	MRecoveredNotion.cleanNouns(noun.getNoun());
        	SimpleVerbPhraseDTO vf=rmh.getBussinessLayerFacade().createSimpleVerbPhraseDTO();
        	compl.setSimpleVerbPhrase(vf);
        	VerbDTO verb = rmh.getBussinessLayerFacade().createVerbDTO();
        	verb.setName("selects");
        	vf.setVerb(verb);
        	noun=rmh.getBussinessLayerFacade().createNounPhraseDTO();
        	vf.setObject(noun);
        	noun.setNounText(elname);
        	noun.connect();
        	if (noun.getNoun().getSynonymUid()==0){
        		if (SCProjectHelper.getSenseAutoAddAndAssigmentFlag()){
        			try {
        				noun.getNoun().autoAddAndAssignSense();
        			} catch (NullPointerException e){
        				throw new TerminologyOperationFailureException();
        			}
        		}
        		else if (!noun.getNoun().autoAssignSense()) noun.getNoun().setName(noun.getNoun().getName().toLowerCase());
        	}
        	MRecoveredNotion.cleanNouns(noun.getNoun());
        	vf.connect();
        	verb.autoAssignSense();
        	compl.connect();
        	prep.autoAssignSense();
        	noun=rmh.getBussinessLayerFacade().createNounPhraseDTO();
        	sentence.setSubject(noun);
        	noun.setNounText("User");
        	noun.connect();
        	if (noun.getNoun().getSynonymUid()==0) noun.getNoun().autoAssignSense();
        	sentence.setRecipient(rmh.getBussinessLayerFacade().getSystemElementDTO("system"));
        	scenario.addScenarioStep(sentence);
        	//adds proper notion and phrases both to direct and indirect notion;
        	NotionDTO tabnot = MRecoveredNotion.addNotion(sentence, fields);
        	if (null!=window && !tabnot.checkIfRelated(window) && ((Notion) tabnot).getId()!=((Notion) window).getId())
				tabnot.addRelatedNotion(window).setDirected(true);
        	SVOSentenceDTO fsent = addFetchesSentence(scenario, sentence, tabname, fields);
        	if (!((MConfiguration.isValidatedata() || MConfiguration.isProcessdata()) && scenario.getScenarioSentenceList().size()<(MConfiguration.isValidatedata()&&MConfiguration.isProcessdata()?4:3)) && (lastSentence instanceof SVOSentenceDTO) && (((SVOSentenceDTO) lastSentence).getPredicate() instanceof SimpleVerbPhraseDTO) && "enters".equals(((SimpleVerbPhraseDTO) ((SVOSentenceDTO) lastSentence).getPredicate()).getVerb().getName())){
        		if (null!=fsent) fsent.delete();
        		sentence.delete();
        	}
        	
		}
        return scenario;
	}
	
	/**
	 * Adds new sentence about users action related to the selection of various options
	 * 
	 * @param scenario scenario
	 * @param name name of UI element
	 * @param type type of UI element
	 * @return this scenario
	 * @throws TerminologyOperationFailureException
	 */
    public static ConstrainedLanguageScenarioDTO addClickSentence(ConstrainedLanguageScenarioDTO scenario, String name, String type, NotionDTO window) throws TerminologyOperationFailureException {
    	RecoveryModelHelper rmh = RecoveryModelHelper.instance((ConstrainedLanguageScenario) scenario);
    	ConstrainedLanguageSentenceDTO lastSentence = scenario.getLastSentence();
    	NotionDTO trigger = null;
    	if (lastSentence instanceof SVOSentenceDTO){
    		if (MConfiguration.isProcessdata() && MConfiguration.isValidatedata() && "process".equals(((SVOSentenceDTO) lastSentence).getPredicate() instanceof SimpleVerbPhraseDTO?((SimpleVerbPhraseDTO) ((SVOSentenceDTO) lastSentence).getPredicate()).getVerb().getName():((ComplexVerbPhraseDTO) ((SVOSentenceDTO) lastSentence).getPredicate()).getSimpleVerbPhrase().getVerb().getName()))
    			lastSentence = scenario.getPreviousSentence(scenario.getPreviousSentence(lastSentence));
    		else if ((MConfiguration.isValidatedata() && "validates".equals(((SVOSentenceDTO) lastSentence).getPredicate() instanceof SimpleVerbPhraseDTO?((SimpleVerbPhraseDTO) ((SVOSentenceDTO) lastSentence).getPredicate()).getVerb().getName():((ComplexVerbPhraseDTO) ((SVOSentenceDTO) lastSentence).getPredicate()).getSimpleVerbPhrase().getVerb().getName())) || (MConfiguration.isProcessdata() && "process".equals(((SVOSentenceDTO) lastSentence).getPredicate() instanceof SimpleVerbPhraseDTO?((SimpleVerbPhraseDTO) ((SVOSentenceDTO) lastSentence).getPredicate()).getVerb().getName():((ComplexVerbPhraseDTO) ((SVOSentenceDTO) lastSentence).getPredicate()).getSimpleVerbPhrase().getVerb().getName())))
    			lastSentence=scenario.getPreviousSentence(lastSentence);
    	}
    	SVOSentenceDTO sentence=null;
        if (name.contains("->")){
            //changes the previous sentence if it is connected with a menu from currently item is select
            if ((lastSentence instanceof SVOSentenceDTO) && ((SVOSentenceDTO)lastSentence).getPredicate() instanceof SimpleVerbPhraseDTO && "selects".equals(((SimpleVerbPhraseDTO) ((SVOSentenceDTO)lastSentence).getPredicate()).getVerb().getName()) && !NotionTypesEnum.Simple_View.tag().equals(rmh.getBussinessLayerFacade().getNotionDTO(((SimpleVerbPhraseDTO) ((SVOSentenceDTO) lastSentence).getPredicate()).getObject()).getType()) && (name.substring(0,name.indexOf("->"))+(null!=MRecoveredNotion.getRoleMaping(type)?" "+MRecoveredNotion.getRoleMaping(type):"")).equals(((SimpleVerbPhraseDTO)((SVOSentenceDTO) lastSentence).getPredicate()).getObject().getNounText())){
            	sentence = (SVOSentenceDTO) lastSentence;
                String notname=rmh.checkNotionName(name.substring(name.indexOf("->")+2), null, false, false, null);
                ComplexVerbPhraseDTO compl = rmh.getBussinessLayerFacade().createComplexVerbPhraseDTO();
                compl.setSimpleVerbPhrase((SimpleVerbPhraseDTO)sentence.getPredicate());
                compl.setObject((NounPhraseDTO) compl.getSimpleVerbPhrase().getObject().copy(false));
                NounPhraseDTO noun=rmh.getBussinessLayerFacade().createNounPhraseDTO();
                compl.getSimpleVerbPhrase().setObject(noun);
                noun.setNounText(notname);
                noun.connect();
                if (noun.getNoun().getSynonymUid()==0){
                	if (SCProjectHelper.getSenseAutoAddAndAssigmentFlag()){
                		try {
                			noun.getNoun().autoAddAndAssignSense();
                		} catch (NullPointerException e){
                			throw new TerminologyOperationFailureException();
                		}
                	} else if (!noun.getNoun().autoAssignSense()) noun.getNoun().setName(noun.getNoun().getName().toLowerCase());
                }
                MRecoveredNotion.cleanNouns(noun.getNoun());
                PrepositionDTO prep=rmh.getBussinessLayerFacade().createPrepostitionDTO();
                prep.setName("from");
                compl.setPreposition(prep);
                compl.connect();
                prep.autoAssignSense();
                sentence.setPredicate(compl);
                //adds proper notion and phrases both to direct and indirect notion; takes account of changes due to alteration of the preceding sentence
                notname=rmh.checkNotionName(name.substring(0,name.indexOf("->")), null, false, false, type);
                boolean phrase=false;
                for (SVOSentence sent : rmh.getBussinessLayerFacade().getSVOSentenceVertices()) if (sent.getId()!=sentence.getId() && null!=((SVOSentenceDTO) sent).getPredicate() && (((SVOSentenceDTO) sent).getPredicate() instanceof SimpleVerbPhraseDTO?null!=((SimpleVerbPhraseDTO)((SVOSentenceDTO) sent).getPredicate()).getObject():null!=((ComplexVerbPhraseDTO)((SVOSentenceDTO) sent).getPredicate()).getSimpleVerbPhrase().getObject()) && (((SVOSentenceDTO) sent).getPredicate() instanceof SimpleVerbPhraseDTO?null!=((SimpleVerbPhraseDTO)((SVOSentenceDTO) sent).getPredicate()).getObject().getNounText():null!=((ComplexVerbPhraseDTO)((SVOSentenceDTO) sent).getPredicate()).getSimpleVerbPhrase().getObject().getNounText()) && (((SVOSentenceDTO) sent).getPredicate() instanceof SimpleVerbPhraseDTO?((SimpleVerbPhraseDTO)((SVOSentenceDTO) sent).getPredicate()).getObject().getNounText().equals(notname):((ComplexVerbPhraseDTO)((SVOSentenceDTO) sent).getPredicate()).getSimpleVerbPhrase().getObject().getNounText().equals(notname)) && (((SVOSentenceDTO) sent).getPredicate() instanceof SimpleVerbPhraseDTO?((SimpleVerbPhraseDTO)((SVOSentenceDTO) sent).getPredicate()).getVerb().getName().equals("selects"):((ComplexVerbPhraseDTO)((SVOSentenceDTO) sent).getPredicate()).getSimpleVerbPhrase().getVerb().getName().equals("selects"))){
                	phrase=true;
                	break;
                }
                if (!phrase){
                	for (DomainStatementDTO ds:rmh.getNotion(RemoteJGWNL.getInstance().getNounBaseForm(notname)).getDomainStatementDTOList()) if (ds.getPhraseDTO() instanceof SimpleVerbPhraseDTO && ((SimpleVerbPhraseDTO)ds.getPhraseDTO()).getVerb().getName().equals("select")){
                		ds.delete();
                		break;
                	}
                }
                trigger = MRecoveredNotion.addNotion(sentence, null);
            } else {
                //adds new sentence in correct form if it is connected which selections from menu, but the previous sentence does not concern the menu 
                sentence = rmh.getBussinessLayerFacade().createSVOSentenceDTO();
                String notname=rmh.checkNotionName(name.substring(0,name.indexOf("->")), null, false, false, type);
                ComplexVerbPhraseDTO compl = rmh.getBussinessLayerFacade().createComplexVerbPhraseDTO();
                sentence.setPredicate(compl);
                PrepositionDTO prep=rmh.getBussinessLayerFacade().createPrepostitionDTO();
                prep.setName("from");
                compl.setPreposition(prep);
                NounPhraseDTO noun=rmh.getBussinessLayerFacade().createNounPhraseDTO();
                compl.setObject(noun);
                noun.setNounText(notname);
                noun.connect();
                if (noun.getNoun().getSynonymUid()==0){
                	if (SCProjectHelper.getSenseAutoAddAndAssigmentFlag()){
                		try {
                			noun.getNoun().autoAddAndAssignSense();
                		} catch (NullPointerException e){
                			throw new TerminologyOperationFailureException();
                		}
                	}
                	else if (!noun.getNoun().autoAssignSense()) noun.getNoun().setName(noun.getNoun().getName().toLowerCase());
                }
                MRecoveredNotion.cleanNouns(noun.getNoun());
                notname=rmh.checkNotionName(name.substring(name.indexOf("->")+2), null, false, false, null);
                SimpleVerbPhraseDTO vf=rmh.getBussinessLayerFacade().createSimpleVerbPhraseDTO();
                compl.setSimpleVerbPhrase(vf);
                VerbDTO verb = rmh.getBussinessLayerFacade().createVerbDTO();
                verb.setName("selects");
                vf.setVerb(verb);
                noun=rmh.getBussinessLayerFacade().createNounPhraseDTO();
                vf.setObject(noun);
                noun.setNounText(notname);
                noun.connect();
                if (noun.getNoun().getSynonymUid()==0){
                	if (SCProjectHelper.getSenseAutoAddAndAssigmentFlag()){
                		try {
                			noun.getNoun().autoAddAndAssignSense();
                		} catch (NullPointerException e){
                			throw new TerminologyOperationFailureException();
                		}
                	}
                	else if (!noun.getNoun().autoAssignSense()) noun.getNoun().setName(noun.getNoun().getName().toLowerCase());
                }
                MRecoveredNotion.cleanNouns(noun.getNoun());
                vf.connect();
                verb.autoAssignSense();
                compl.connect();
                prep.autoAssignSense();
                noun=rmh.getBussinessLayerFacade().createNounPhraseDTO();
                sentence.setSubject(noun);
                noun.setNounText("User");
                noun.connect();
                if (noun.getNoun().getSynonymUid()==0) noun.getNoun().autoAssignSense();
                sentence.setRecipient(rmh.getBussinessLayerFacade().getSystemElementDTO("system"));
                scenario.insertScenarioStepAfter(lastSentence, sentence);
                //adds proper notion and phrases both to direct and indirect notion;
                trigger = MRecoveredNotion.addNotion(sentence, null);
            }
        } else {
            //adds notions and its phrases
        	String notname=rmh.checkNotionName(name,null,false,false,type);
            //adds new sentence not connected with menu
            sentence = rmh.getBussinessLayerFacade().createSVOSentenceDTO();
            SimpleVerbPhraseDTO vf=rmh.getBussinessLayerFacade().createSimpleVerbPhraseDTO();
            VerbDTO verb = rmh.getBussinessLayerFacade().createVerbDTO();
            verb.setName("selects");
            
            vf.setVerb(verb);
            NounPhraseDTO noun=rmh.getBussinessLayerFacade().createNounPhraseDTO();
            vf.setObject(noun);
            noun.setNounText(notname);
            noun.connect();
            if (noun.getNoun().getSynonymUid()==0){
            	if (SCProjectHelper.getSenseAutoAddAndAssigmentFlag()){
            		try {
            			noun.getNoun().autoAddAndAssignSense();
            		} catch (NullPointerException e){
            			throw new TerminologyOperationFailureException();
            		}
            	}
            	else if (!noun.getNoun().autoAssignSense()) noun.getNoun().setName(noun.getNoun().getName().toLowerCase());
            }
            MRecoveredNotion.cleanNouns(noun.getNoun());
            vf.connect();
            verb.autoAssignSense();
            sentence.setPredicate(vf);
            noun=rmh.getBussinessLayerFacade().createNounPhraseDTO();
            sentence.setSubject(noun);
            noun.setNounText("User");
            noun.connect();
            if (noun.getNoun().getSynonymUid()==0) noun.getNoun().autoAssignSense();
            sentence.setRecipient(rmh.getBussinessLayerFacade().getSystemElementDTO("system"));
            scenario.insertScenarioStepAfter(lastSentence, sentence);
            trigger = MRecoveredNotion.addNotion(sentence, null);
        }
        if (null!=window && !trigger.checkIfRelated(window))
			window.addRelatedNotion(trigger);
        return scenario;
    }

	/**
     * Adds new sentence about users action related to data entries
     * 
     * @param scenario scenario
     * @param windname name of actual window
     * @param fields field of actual window
     * @param value entered value
     * @return this scenario
     * @throws TerminologyOperationFailureException
     */
    public static ConstrainedLanguageScenarioDTO addInputSentence(ConstrainedLanguageScenarioDTO scenario, String windname, Set<NotionDTO> fields, String value, NotionDTO windowNotion) throws TerminologyOperationFailureException {
    	RecoveryModelHelper rmh = RecoveryModelHelper.instance((ConstrainedLanguageScenario) scenario);
    	//if the previous sentence does not apply to enter the same data, adds new sentence
    	String name=rmh.checkNotionName(windname, fields, true, false, null);
    	ConstrainedLanguageSentenceDTO lastSentence = scenario.getLastSentence();
    	while (lastSentence instanceof SVOSentenceDTO && "selects".equals(((SVOSentenceDTO) lastSentence).getPredicate() instanceof SimpleVerbPhraseDTO?((SimpleVerbPhraseDTO) ((SVOSentenceDTO) lastSentence).getPredicate()).getVerb().getName():((ComplexVerbPhraseDTO) ((SVOSentenceDTO) lastSentence).getPredicate()).getSimpleVerbPhrase().getVerb().getName()) && NotionTypesEnum.Simple_View.tag().equals(rmh.getBussinessLayerFacade().getNotionDTO(((SVOSentenceDTO) lastSentence).getPredicate() instanceof SimpleVerbPhraseDTO?((SimpleVerbPhraseDTO) ((SVOSentenceDTO) lastSentence).getPredicate()).getObject():((ComplexVerbPhraseDTO) ((SVOSentenceDTO) lastSentence).getPredicate()).getSimpleVerbPhrase().getObject()).getType())){
			((SVOSentenceDTO) lastSentence).delete();
			lastSentence = scenario.getLastSentence();
		}
    	if (lastSentence instanceof SVOSentenceDTO){
    		if (MConfiguration.isProcessdata() && MConfiguration.isValidatedata() && "process".equals(((SVOSentenceDTO) lastSentence).getPredicate() instanceof SimpleVerbPhraseDTO?((SimpleVerbPhraseDTO) ((SVOSentenceDTO) lastSentence).getPredicate()).getVerb().getName():((ComplexVerbPhraseDTO) ((SVOSentenceDTO) lastSentence).getPredicate()).getSimpleVerbPhrase().getVerb().getName()))
    			lastSentence = scenario.getPreviousSentence(scenario.getPreviousSentence(lastSentence));
    		else if ((MConfiguration.isValidatedata() && "validates".equals(((SVOSentenceDTO) lastSentence).getPredicate() instanceof SimpleVerbPhraseDTO?((SimpleVerbPhraseDTO) ((SVOSentenceDTO) lastSentence).getPredicate()).getVerb().getName():((ComplexVerbPhraseDTO) ((SVOSentenceDTO) lastSentence).getPredicate()).getSimpleVerbPhrase().getVerb().getName())) || (MConfiguration.isProcessdata() && "process".equals(((SVOSentenceDTO) lastSentence).getPredicate() instanceof SimpleVerbPhraseDTO?((SimpleVerbPhraseDTO) ((SVOSentenceDTO) lastSentence).getPredicate()).getVerb().getName():((ComplexVerbPhraseDTO) ((SVOSentenceDTO) lastSentence).getPredicate()).getSimpleVerbPhrase().getVerb().getName())))
    			lastSentence=scenario.getPreviousSentence(lastSentence);
    		if (MConfiguration.isConfirmdata() && "selects".equals(((SVOSentenceDTO) lastSentence).getPredicate() instanceof SimpleVerbPhraseDTO?((SimpleVerbPhraseDTO) ((SVOSentenceDTO) lastSentence).getPredicate()).getVerb().getName():((ComplexVerbPhraseDTO) ((SVOSentenceDTO) lastSentence).getPredicate()).getSimpleVerbPhrase().getVerb().getName()) && !NotionTypesEnum.Simple_View.tag().equals(rmh.getBussinessLayerFacade().getNotionDTO(((SVOSentenceDTO) lastSentence).getPredicate() instanceof SimpleVerbPhraseDTO?((SimpleVerbPhraseDTO) ((SVOSentenceDTO) lastSentence).getPredicate()).getObject():((ComplexVerbPhraseDTO) ((SVOSentenceDTO) lastSentence).getPredicate()).getSimpleVerbPhrase().getObject()).getType()))
    			lastSentence=scenario.getPreviousSentence(lastSentence);
    	}
    	if (((MConfiguration.isValidatedata() || MConfiguration.isProcessdata()) && scenario.getScenarioSentenceList().size()<(MConfiguration.isValidatedata()&&MConfiguration.isProcessdata()?3:2)) || !(lastSentence instanceof SVOSentenceDTO) || !(((SVOSentenceDTO) lastSentence).getPredicate() instanceof SimpleVerbPhraseDTO) || !"enters".equals(((SimpleVerbPhraseDTO) ((SVOSentenceDTO) lastSentence).getPredicate()).getVerb().getName()) || !((SimpleVerbPhraseDTO) ((SVOSentenceDTO) lastSentence).getPredicate()).getObject().getNounText().equals(name)){
        	SVOSentenceDTO sentence = rmh.getBussinessLayerFacade().createSVOSentenceDTO();
            SimpleVerbPhraseDTO vf=rmh.getBussinessLayerFacade().createSimpleVerbPhraseDTO();
            VerbDTO verb = rmh.getBussinessLayerFacade().createVerbDTO();
            verb.setName("enters");
            vf.setVerb(verb);
            NounPhraseDTO noun=rmh.getBussinessLayerFacade().createNounPhraseDTO();
            vf.setObject(noun);
            noun.setNounText(name);
            noun.connect();
            if (noun.getNoun().getSynonymUid()==0){
            	if (SCProjectHelper.getSenseAutoAddAndAssigmentFlag()){
            		try {
            			noun.getNoun().autoAddAndAssignSense();
            		} catch (NullPointerException e){
            			throw new TerminologyOperationFailureException();
            		}
            	}
            	else if (!noun.getNoun().autoAssignSense()) noun.getNoun().setName(noun.getNoun().getName().toLowerCase());
            }
            MRecoveredNotion.cleanNouns(noun.getNoun());
            vf.connect();
            verb.autoAssignSense();
            sentence.setPredicate(vf);
            noun=rmh.getBussinessLayerFacade().createNounPhraseDTO();
            sentence.setSubject(noun);
            noun.setNounText("User");
            noun.connect();
            if (noun.getNoun().getSynonymUid()==0) noun.getNoun().autoAssignSense();
            sentence.setRecipient(rmh.getBussinessLayerFacade().getSystemElementDTO("system"));
            scenario.addScenarioStep(sentence);
        	//adds phrase connected with the data entry
            NotionDTO not = MRecoveredNotion.addNotion(sentence, fields);
            if (null!=windowNotion && !not.checkIfRelated(windowNotion) && ((Notion) not).getId()!=((Notion) windowNotion).getId())
            	windowNotion.addRelatedNotion(not);
            addFetchesSentence(scenario, sentence, name, fields);
            //adds sentence about validate data and proper phrases if validatedata flag is turned on
            if (MConfiguration.isValidatedata()){
            	sentence = rmh.getBussinessLayerFacade().createSVOSentenceDTO();
                vf=rmh.getBussinessLayerFacade().createSimpleVerbPhraseDTO();
                verb = rmh.getBussinessLayerFacade().createVerbDTO();
                verb.setName("validates");
                vf.setVerb(verb);
                noun=rmh.getBussinessLayerFacade().createNounPhraseDTO();
                vf.setObject(noun);
                noun.setNounText(name);
                noun.connect();
                if (noun.getNoun().getSynonymUid()==0) noun.getNoun().autoAssignSense();
                vf.connect();
                verb.autoAssignSense();
                sentence.setPredicate(vf);
                noun=rmh.getBussinessLayerFacade().createNounPhraseDTO();
                sentence.setSubject(noun);
                noun.setNounText("System");
                noun.connect();
                if (noun.getNoun().getSynonymUid()==0) noun.getNoun().autoAssignSense();
                sentence.setRecipient(rmh.getBussinessLayerFacade().getSystemElementDTO("system"));
                scenario.addScenarioStep(sentence);
                MRecoveredNotion.addNotion(sentence, fields);
            }
            //adds sentence about process data and proper phrases if processdata flag is turned on
            if (MConfiguration.isProcessdata()){
            	sentence = rmh.getBussinessLayerFacade().createSVOSentenceDTO();
                vf=rmh.getBussinessLayerFacade().createSimpleVerbPhraseDTO();
                verb = rmh.getBussinessLayerFacade().createVerbDTO();
                verb.setName("process");
                vf.setVerb(verb);
                noun=rmh.getBussinessLayerFacade().createNounPhraseDTO();
                vf.setObject(noun);
                noun.setNounText(name);
                noun.connect();
                if (noun.getNoun().getSynonymUid()==0) noun.getNoun().autoAssignSense();
                vf.connect();
                verb.autoAssignSense();
                sentence.setPredicate(vf);
                noun=rmh.getBussinessLayerFacade().createNounPhraseDTO();
                sentence.setSubject(noun);
                noun.setNounText("System");
                noun.connect();
                if (noun.getNoun().getSynonymUid()==0) noun.getNoun().autoAssignSense();
                sentence.setRecipient(rmh.getBussinessLayerFacade().getSystemElementDTO("system"));
                scenario.addScenarioStep(sentence);
                MRecoveredNotion.addNotion(sentence, fields);
            }
        }
        return scenario;
    }
    
    public static SVOSentenceDTO addFetchesSentence(ConstrainedLanguageScenarioDTO scenario, SVOSentenceDTO curent, String name, Set<NotionDTO> fields) throws TerminologyOperationFailureException{
    	if (!MConfiguration.isFetchesdata()) return null;
    	RecoveryModelHelper rmh = RecoveryModelHelper.instance((ConstrainedLanguageScenario) scenario);
    	ConstrainedLanguageSentenceDTO lastSentence = scenario.getPreviousSentence((ConstrainedLanguageSentenceDTO) curent);
    	while (null!=lastSentence && (!(lastSentence instanceof SVOSentenceDTO) || !(((SVOSentenceDTO) lastSentence).getPredicate() instanceof SimpleVerbPhraseDTO) || !"shows".equals(((SimpleVerbPhraseDTO) ((SVOSentenceDTO) lastSentence).getPredicate()).getVerb().getName())))
    		lastSentence=scenario.getPreviousSentence(lastSentence);
    	if (null==lastSentence) return null;
    	ConstrainedLanguageSentenceDTO sent = scenario.getPreviousSentence(lastSentence);
    	while (null!= sent && sent instanceof SVOSentenceDTO && ((SVOSentenceDTO) sent).getPredicate() instanceof SimpleVerbPhraseDTO && "fetches".equals(((SimpleVerbPhraseDTO) ((SVOSentenceDTO) sent).getPredicate()).getVerb().getName())){
    		if (name.equals(((SimpleVerbPhraseDTO) ((SVOSentenceDTO) sent).getPredicate()).getObject().getNounText())) return null;
    		sent=scenario.getPreviousSentence(sent);
    	}
    	SVOSentenceDTO sentence = rmh.getBussinessLayerFacade().createSVOSentenceDTO();
        SimpleVerbPhraseDTO vf = rmh.getBussinessLayerFacade().createSimpleVerbPhraseDTO();
        VerbDTO verb = rmh.getBussinessLayerFacade().createVerbDTO();
        verb.setName("fetches");
        vf.setVerb(verb);
        NounPhraseDTO noun = rmh.getBussinessLayerFacade().createNounPhraseDTO();
        vf.setObject(noun);
        noun.setNounText(name);
        noun.connect();
        if (noun.getNoun().getSynonymUid()==0) noun.getNoun().autoAssignSense();
        vf.connect();
        verb.autoAssignSense();
        sentence.setPredicate(vf);
        noun=rmh.getBussinessLayerFacade().createNounPhraseDTO();
        sentence.setSubject(noun);
        noun.setNounText("System");
        noun.connect();
        if (noun.getNoun().getSynonymUid()==0) noun.getNoun().autoAssignSense();
        sentence.setRecipient(rmh.getBussinessLayerFacade().getSystemElementDTO("system"));
        scenario.insertScenarioStepAfter(scenario.getPreviousSentence(lastSentence), sentence);
        MRecoveredNotion.addNotion(sentence, fields);
        return sentence;
    }
    
	/**
     * Introduces the final fixes after sentences addition
     * 
     * @param scenario scenario
     * @return this scenario
     */
    public static ConstrainedLanguageScenarioDTO end(ConstrainedLanguageScenarioDTO scenario){
    	RecoveryModelHelper rmh = RecoveryModelHelper.instance((ConstrainedLanguageScenario) scenario);
    	//if the confirmdata flag is turned off, removes last sentence related to the window, preceded the introduction of data (if such window exist)
        if (!MConfiguration.isConfirmdata() && scenario.getScenarioSentenceList().size()>1 && scenario.getLastSentence() instanceof SVOSentenceDTO){
			SVOSentenceDTO lastSentence = (SVOSentenceDTO) scenario.getLastSentence();
			if (MConfiguration.isProcessdata() && MConfiguration.isValidatedata() && "process".equals(lastSentence.getPredicate() instanceof SimpleVerbPhraseDTO?((SimpleVerbPhraseDTO) lastSentence.getPredicate()).getVerb().getName():((ComplexVerbPhraseDTO) lastSentence.getPredicate()).getSimpleVerbPhrase().getVerb().getName()))
    			lastSentence = (SVOSentenceDTO) scenario.getPreviousSentence(scenario.getPreviousSentence(lastSentence));
    		else if ((MConfiguration.isValidatedata() && "validates".equals(lastSentence.getPredicate() instanceof SimpleVerbPhraseDTO?((SimpleVerbPhraseDTO) lastSentence.getPredicate()).getVerb().getName():((ComplexVerbPhraseDTO) lastSentence.getPredicate()).getSimpleVerbPhrase().getVerb().getName())) || (MConfiguration.isProcessdata() && "process".equals(lastSentence.getPredicate() instanceof SimpleVerbPhraseDTO?((SimpleVerbPhraseDTO) lastSentence.getPredicate()).getVerb().getName():((ComplexVerbPhraseDTO) lastSentence.getPredicate()).getSimpleVerbPhrase().getVerb().getName())))
    			lastSentence=(SVOSentenceDTO) scenario.getPreviousSentence(lastSentence);
			if("selects".equals(lastSentence.getPredicate() instanceof SimpleVerbPhraseDTO?((SimpleVerbPhraseDTO) lastSentence.getPredicate()).getVerb().getName():((ComplexVerbPhraseDTO) lastSentence.getPredicate()).getSimpleVerbPhrase().getVerb().getName()) 
        	&& !NotionTypesEnum.Simple_View.tag().equals(rmh.getBussinessLayerFacade().getNotionDTO(((SVOSentenceDTO) lastSentence).getPredicate() instanceof SimpleVerbPhraseDTO?((SimpleVerbPhraseDTO) ((SVOSentenceDTO) lastSentence).getPredicate()).getObject():((ComplexVerbPhraseDTO) ((SVOSentenceDTO) lastSentence).getPredicate()).getSimpleVerbPhrase().getObject()).getType())
			&& scenario.getPreviousSentence(lastSentence) instanceof SVOSentenceDTO 
        	&& ("enters".equals(((SVOSentenceDTO) scenario.getPreviousSentence(lastSentence)).getPredicate() instanceof SimpleVerbPhraseDTO?((SimpleVerbPhraseDTO) ((SVOSentenceDTO) scenario.getPreviousSentence(lastSentence)).getPredicate()).getVerb().getName():((ComplexVerbPhraseDTO) ((SVOSentenceDTO) scenario.getPreviousSentence(lastSentence)).getPredicate()).getSimpleVerbPhrase().getVerb().getName()))){
        		MRecoveredNotion.clean(lastSentence);
        		lastSentence.delete();
			}
		}
        return scenario;
    }
    
    /** 
     * Copy sentences of one scenario to another (or to new scenario if target scenario is null), if reference scenario is not null move the same sentences in the same places from it
	 *  
     * @param scenario recovered scenario
     * @param targetScenario target scenario
     * @param referenceScenario reference scenario
     * @return scenario with copied sentences
     */
    public static ConstrainedLanguageScenarioDTO copyScenario(UseCaseDTO scenario,ConstrainedLanguageScenarioDTO targetScenario, ConstrainedLanguageScenarioDTO referenceScenario){
    	RecoveryModelHelper rmh = RecoveryModelHelper.instance((RSLUseCase) scenario);
    	targetScenario.setName(scenario.getName().substring(1));
    	boolean common = null!=referenceScenario;
    	for (ConstrainedLanguageSentenceDTO sent:scenario.getConstrainedLanguageScenarioDTOList().get(0).getScenarioSentenceList()){
    		if (!MConfiguration.isCheckPrefixInvcationAndConditionSentences() && common && referenceScenario.getScenarioSentenceList().size()>targetScenario.getScenarioSentenceList().size() && (referenceScenario.getScenarioSentenceList().get(targetScenario.getScenarioSentenceList().size()) instanceof ConditionSentenceDTO && !(sent instanceof ConditionSentenceDTO) || referenceScenario.getScenarioSentenceList().get(targetScenario.getScenarioSentenceList().size()) instanceof InvocationSentenceDTO && !(sent instanceof InvocationSentenceDTO))){
    			targetScenario.addScenarioStep(referenceScenario.getScenarioSentenceList().get(targetScenario.getScenarioSentenceList().size()));
    		}
    		if (common && referenceScenario.getScenarioSentenceList().size()>targetScenario.getScenarioSentenceList().size() && MScenario.equalsSentence(referenceScenario.getScenarioSentenceList().get(targetScenario.getScenarioSentenceList().size()), sent)){
    			targetScenario.addScenarioStep(referenceScenario.getScenarioSentenceList().get(targetScenario.getScenarioSentenceList().size()));
    		} else {
    			if (common){
    				ConditionSentenceDTO con=null;
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
    			if (sent instanceof SVOSentenceDTO){
    				SVOSentenceDTO step = rmh.getBussinessLayerFacade().createSVOSentenceDTO();
    				step.setPredicate((VerbPhraseDTO) ((SVOSentenceDTO) sent).getPredicate().copy(false));
    				step.setSubject((NounPhraseDTO) ((SVOSentenceDTO) sent).getSubject().copy(false));
    				step.setRecipient(((SVOSentenceDTO) sent).getRecipient());
    				targetScenario.addScenarioStep(step);
    			} else if (sent instanceof InvocationSentenceDTO){
    				InvocationSentenceDTO step = rmh.getBussinessLayerFacade().createInvocationSentenceDTO();
    				step.setInclusionType(InclusionType.INSERT);
    				UseCaseDTO uc;
    				if(null!=getDerivedScenario(((InvocationSentenceDTO) sent).getInvokedUseCase())) uc=getDerivedScenario(((InvocationSentenceDTO) sent).getInvokedUseCase()).getParent();
    				else if (null==(uc=rmh.getBussinessLayerFacade().getUseCaseByName(((InvocationSentenceDTO) sent).getInvokedUseCase().getName().substring(1)))){
    					uc=rmh.getBussinessLayerFacade().createUseCaseDTO();
    					uc.setName(((InvocationSentenceDTO) sent).getInvokedUseCase().getName().substring(1));
    				}
    				targetScenario.addScenarioStep(step);
    				step.setInvokedUseCase(uc);
    			}
    		}
    	}
    	if (common) {
			targetScenario.delete();
			return null;
		}
    	return targetScenario;
    }
    
    /**
     * Returns names of invoked in recovery scenario (represented by use case) use cases
     * 
     * @param scenario recovered scenario
     * @return names of invokes use cases
     */
    public static String getInvokes(UseCaseDTO scenario){
    	String s="";
    	for (ConstrainedLanguageSentenceDTO sent:scenario.getConstrainedLanguageScenarioDTOList().get(0).getScenarioSentenceList()) if (sent instanceof InvocationSentenceDTO) if (null!=((InvocationSentenceDTO) sent).getInvokedUseCase() && null!=((InvocationSentenceDTO) sent).getInvokedUseCase().getName()){
    		if (s.isEmpty()) s=((InvocationSentenceDTO) sent).getInvokedUseCase().getName().substring(1);
    		else s+=", "+((InvocationSentenceDTO) sent).getInvokedUseCase().getName().substring(1);
    	}
    	return s;
    }
	
}
