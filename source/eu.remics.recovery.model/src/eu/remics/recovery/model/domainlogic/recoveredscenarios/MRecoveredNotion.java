package eu.remics.recovery.model.domainlogic.recoveredscenarios;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import eu.redseeds.common.SCProjectHelper;
import eu.redseeds.sc.terminology.model.RemoteJGWNL;
import eu.redseeds.scl.model.rsl.rsldomainelements.domainelements.DomainElementRelationshipDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.AttributeDataTypesEnum;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.DomainStatementDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionTypesEnum;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionsPackageDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.PrimitiveDataTypeDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.ComplexVerbPhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.NounPhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.PhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.SimpleVerbPhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.terms.NounDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.svosentences.SVOSentenceDTO;
import eu.redseeds.scl.rsl.rsldomainelements.domainelements.DomainElementRelationship;
import eu.redseeds.scl.rsl.rsldomainelements.notions.Notion;
import eu.redseeds.scl.rsl.rsldomainelements.phrases.NounPhrase;
import eu.redseeds.scl.rsl.rsldomainelements.terms.Noun;
import eu.redseeds.scl.rsl.rslrequirementrepresentationsentences.svosentences.SVOSentence;
import eu.redseeds.scl.sclkernel.Stereotype;
import eu.remics.recovery.model.RecoveryModelHelper;
import eu.remics.recovery.model.domainlogic.usecases.MNotion;
import eu.remics.recovery.model.dto.TerminologyOperationFailureException;
import eu.remics.recovery.model.preferences.MConfiguration;

public class MRecoveredNotion {
	/**
	 * Adds new notion based on SVO sentence
	 * 
	 * @param sentence SVO sentence
	 * @param fields fields of notion
	 * @throws TerminologyOperationFailureException
	 * @return proper notion
	 */
	public static NotionDTO addNotion(SVOSentenceDTO sentence, Set<NotionDTO> fields) throws TerminologyOperationFailureException{
		RecoveryModelHelper rmh = RecoveryModelHelper.instance((SVOSentence) sentence);
		NotionsPackageDTO np=null;
		NotionDTO notion;
		boolean firstisconcept = false;
		boolean phraseexist=false;
		String tag="";
		String ster=null;
		if ((sentence.getPredicate() instanceof SimpleVerbPhraseDTO?((SimpleVerbPhraseDTO) sentence.getPredicate()).getVerb().getName():((ComplexVerbPhraseDTO) sentence.getPredicate()).getSimpleVerbPhrase().getVerb().getName()).equals("shows")){
			if (!MConfiguration.getUiPack().isEmpty()) np=rmh.getNotionsPackage(MConfiguration.getUiPack());
			tag=MConfiguration.getDescWindow();
			ster=NotionTypesEnum.Screen.tag();
		}
		else if ((sentence.getPredicate() instanceof SimpleVerbPhraseDTO?((SimpleVerbPhraseDTO) sentence.getPredicate()).getVerb().getName():((ComplexVerbPhraseDTO) sentence.getPredicate()).getSimpleVerbPhrase().getVerb().getName()).equals("selects")){
			if (null!=fields){
				if (!MConfiguration.getConceptPack().isEmpty()) np=rmh.getNotionsPackage(MConfiguration.getConceptPack());
				tag=MConfiguration.getDescConcept();
				firstisconcept=true;
			} else {
				if (!MConfiguration.getTriggerPack().isEmpty()) np=rmh.getNotionsPackage(MConfiguration.getTriggerPack());
				tag=MConfiguration.getDescTrigger();
				ster=NotionTypesEnum.Trigger.tag();
			}
		}
		else {
			if (!MConfiguration.getDataPack().isEmpty()) np=rmh.getNotionsPackage(MConfiguration.getDataPack());
			tag=MConfiguration.getDescData();
			ster=NotionTypesEnum.Simple_View.tag();
		}
		if (!MConfiguration.isPackageorg() || null==np) np=rmh.getNotionsPackage("Notions");
		String name=RemoteJGWNL.getInstance().getNounBaseForm(sentence.getPredicate() instanceof SimpleVerbPhraseDTO?((SimpleVerbPhraseDTO) sentence.getPredicate()).getObject().getNounText():((ComplexVerbPhraseDTO) sentence.getPredicate()).getSimpleVerbPhrase().getObject().getNounText());
		if (null==(notion=rmh.getNotion(name))){
			notion = rmh.getBussinessLayerFacade().createNotionDTO();
			NounPhraseDTO noun = rmh.getBussinessLayerFacade().createNounPhraseDTO();
			noun.setNounText(name);
			noun.setNoun(rmh.getBussinessLayerFacade().createNounDTO(name));
			notion.setNamePhrase(noun);
			if (!tag.isEmpty()) notion.setDescription(tag);
			if (null!=ster){
				Stereotype stereotype = rmh.getBussinessLayerFacade().createSclkernel$Stereotype();
				stereotype.setName(ster);
				((Notion) notion).addStereotype(stereotype);
			}
			notion.setParent(np);
			if (null!=fields && !firstisconcept) for (NotionDTO attribute:fields){
				if (MConfiguration.isAllowAttributesForDataViews())
					notion.addNotionDTOAttribute(attribute);
				else notion.addRelatedNotion(attribute).setDirected(true);
			}
		}
		PhraseDTO phr=sentence.getPredicate() instanceof SimpleVerbPhraseDTO?sentence.getPredicate():((ComplexVerbPhraseDTO) sentence.getPredicate()).getSimpleVerbPhrase();
		for (DomainStatementDTO ds:notion.getDomainStatementDTOList()) if (!(ds.getPhraseDTO() instanceof NounPhraseDTO) && equalsByName(ds.getPhraseDTO(),phr)){
				phraseexist=true;
				if (0==((SimpleVerbPhraseDTO) ds.getPhraseDTO()).getObject().getNoun().getSynonymUid()) ((SimpleVerbPhraseDTO) ds.getPhraseDTO()).getObject().getNoun().autoAssignSense();
				break;
		}
		try {
			if (!phraseexist){
				DomainStatementDTO dom = rmh.getBussinessLayerFacade().createDomainStatementDTO(sentence.getPredicate() instanceof SimpleVerbPhraseDTO?((SimpleVerbPhraseDTO) sentence.getPredicate()).copy(true):((ComplexVerbPhraseDTO) sentence.getPredicate()).getSimpleVerbPhrase().copy(true));
				notion.addDomainStatementDTO(dom);
				MNotion.autoAssignActionType(dom);
			}
		} catch (NullPointerException e){
			throw new TerminologyOperationFailureException();
		}
		if (sentence.getPredicate() instanceof ComplexVerbPhraseDTO){
			NotionDTO oldNotion = notion;
			if (null!=fields){
				if (!MConfiguration.getListPack().isEmpty()) np=rmh.getNotionsPackage(MConfiguration.getListPack());
				tag=MConfiguration.getDescList();
				ster=NotionTypesEnum.List_View.tag();
			}
			name=RemoteJGWNL.getInstance().getNounBaseForm(((ComplexVerbPhraseDTO) sentence.getPredicate()).getObject().getNounText());
			if (null==(notion=rmh.getNotion(name))){
				notion = rmh.getBussinessLayerFacade().createNotionDTO();
				NounPhraseDTO noun = rmh.getBussinessLayerFacade().createNounPhraseDTO();
				noun.setNounText(name);
				noun.setNoun(rmh.getBussinessLayerFacade().createNounDTO(name));
				notion.setNamePhrase(noun);
				if (!tag.isEmpty()) notion.setDescription(tag);
				if (null!=ster){
					Stereotype stereotype = rmh.getBussinessLayerFacade().createSclkernel$Stereotype();
					stereotype.setName(ster);
					((Notion) notion).addStereotype(stereotype);
				}
				notion.setParent(np);
				if (null!=fields) for (NotionDTO attribute:fields){
					if (MConfiguration.isAllowAttributesForDataViews())
						notion.addNotionDTOAttribute(attribute);
					else notion.addRelatedNotion(attribute).setDirected(true);
				}
				if (!oldNotion.checkIfRelated(notion) && ((Notion) oldNotion).getId()!=((Notion) notion).getId()){
					DomainElementRelationshipDTO rel = notion.addRelatedNotion(oldNotion);
					if (null!=fields) rel.setDirected(true);
				}
			}
			phraseexist=false;
			for (DomainStatementDTO ds:notion.getDomainStatementDTOList()) if (!(ds.getPhraseDTO() instanceof NounPhraseDTO) && equalsByName(ds.getPhraseDTO(),sentence.getPredicate())){
				phraseexist=true;
				if (0==((ComplexVerbPhraseDTO) ds.getPhraseDTO()).getObject().getNoun().getSynonymUid()) ((ComplexVerbPhraseDTO) ds.getPhraseDTO()).getObject().getNoun().autoAssignSense();
				if (0==((ComplexVerbPhraseDTO) ds.getPhraseDTO()).getSimpleVerbPhrase().getObject().getNoun().getSynonymUid()) ((ComplexVerbPhraseDTO) ds.getPhraseDTO()).getSimpleVerbPhrase().getObject().getNoun().autoAssignSense();
				break;
			}
			try {
				if (!phraseexist){
					DomainStatementDTO dom = rmh.getBussinessLayerFacade().createDomainStatementDTO(sentence.getPredicate().copy(true));
					notion.addDomainStatementDTO(dom);
					MNotion.autoAssignActionType(dom);
				}
			} catch (NullPointerException e){
				throw new TerminologyOperationFailureException();
			}
		}
		return notion;
	}
	
	/**
	 * Compare phrase and domain statement (which also contains phrase) based on its elements names
	 * 
	 * @param domainStatment domain statement
	 * @param phrase phrase
	 * @return
	 */
	public static boolean equalsByName(PhraseDTO domainStatment, PhraseDTO phrase){
		if ((domainStatment instanceof NounPhraseDTO) && (phrase instanceof NounPhraseDTO) && ((Noun)((NounPhraseDTO) domainStatment).getNoun()).getId()==((Noun)((NounPhraseDTO) phrase).getNoun()).getId()) return true;
		if ((domainStatment instanceof SimpleVerbPhraseDTO) && (phrase instanceof SimpleVerbPhraseDTO) && ((SimpleVerbPhraseDTO) domainStatment).getVerb().getName().equals(RemoteJGWNL.getInstance().getVerbBaseForm(((SimpleVerbPhraseDTO) phrase).getVerb().getName())) && ((Noun)((SimpleVerbPhraseDTO) domainStatment).getObject().getNoun()).getId()==((Noun)((SimpleVerbPhraseDTO) phrase).getObject().getNoun()).getId()) return true;
		if ((domainStatment instanceof ComplexVerbPhraseDTO) && (phrase instanceof ComplexVerbPhraseDTO) && ((ComplexVerbPhraseDTO) domainStatment).getSimpleVerbPhrase().getVerb().getName().equals(RemoteJGWNL.getInstance().getVerbBaseForm(((ComplexVerbPhraseDTO) phrase).getSimpleVerbPhrase().getVerb().getName())) && ((Noun)((ComplexVerbPhraseDTO) domainStatment).getSimpleVerbPhrase().getObject().getNoun()).getId()==((Noun)((ComplexVerbPhraseDTO) phrase).getSimpleVerbPhrase().getObject().getNoun()).getId() && ((ComplexVerbPhraseDTO) domainStatment).getPreposition().getName().equals(RemoteJGWNL.getInstance().getPrepositionBaseForm(((ComplexVerbPhraseDTO) phrase).getPreposition().getName())) && ((Noun)((ComplexVerbPhraseDTO) domainStatment).getObject().getNoun()).getId()==((Noun)((ComplexVerbPhraseDTO) phrase).getObject().getNoun()).getId()) return true;
		return false;
	}

    /**
     * Compares set of notion fields ad fields belong to notion
     * 
     * @param notion notion
     * @param fields set of notion fields
     * @return true if set and notion fields are equals
     */
	public static boolean hasTheSameFields(NotionDTO notion, Set<NotionDTO> fields) {
		if (notion.getType().isEmpty() || MConfiguration.isAllowAttributesForDataViews() && (NotionTypesEnum.Simple_View.tag().equals(notion.getType()) || NotionTypesEnum.List_View.tag().equals(notion.getType()) || NotionTypesEnum.Tree_View.tag().equals(notion.getType()))){
			if (notion.getNotionDTOAttributeList().size()!=fields.size()) return false;
			for(NotionDTO attr:notion.getNotionDTOAttributeList()){
				boolean contains = false;
				for (NotionDTO attr2:fields) if (attr.getName().equals(attr2.getName())){
					contains=true;
					break;
				}
				if (!contains) return false;
			}
			return true;
		}
		if (NotionTypesEnum.Simple_View.tag().equals(notion.getType()) || NotionTypesEnum.List_View.tag().equals(notion.getType()) || NotionTypesEnum.Tree_View.tag().equals(notion.getType())){
			if (notion.getPointedNotionDTOAttributeList().size()!=fields.size()) return false;
			for(NotionDTO attr:notion.getPointedNotionDTOAttributeList()){
				boolean contains = false;
				for (NotionDTO attr2:fields) if (attr.getName().equals(attr2.getName())){
					contains=true;
					break;
				}
				if (!contains) return false;
			}
			return true;
		}
		return true;
	}
	
	/**
	 * Returns mapping for roles of the GUI elements associated with the user's selections
	 * 
	 * @param role name of role
	 * @return role name mapping
	 */
    public static String getRoleMaping(String role){
        //returns suffix placed on the map if its exist, otherwise if clicksuffix is turn on returns role
        if (MConfiguration.getRolesmap().containsKey(role)) return MConfiguration.getRolesmap().get(role);
        else if (MConfiguration.isClicksufix()) return MConfiguration.isLowcasesuffixs()?role.toLowerCase():role;
        else return null;
    }
    
    /**
     * Adds new notion represents attribute of other notion (which represent data package)
     * 
     * @param name name of attribute
     * @param type flag specifies attribute type as boolean
     * @return notion represents attributed
     * @throws TerminologyOperationFailureException
     */
    public static NotionDTO addAttribute(String pname, AttributeDataTypesEnum type, String id, Map<NotionDTO, String> attributeToIdMap, List<String> val, RecoveryModelHelper rmh) throws TerminologyOperationFailureException{
    	pname=RemoteJGWNL.getInstance().getNounBaseForm(pname.trim());
    	String name=pname;
    	int i=1;
    	NotionDTO attribute=null;
    	boolean end = false;
    	loop:
    	while (!end){
    		for (NotionDTO not : rmh.getNotionsOfType(Arrays.asList(NotionTypesEnum.Attribute.tag())))
    			if (not.getName().equals(name)){
    				List<String> notval = new ArrayList<String>();
    				for (DomainElementRelationship der : rmh.getBussinessLayerFacade().getDomainElementRelationshipVertices()) if (((Notion) ((DomainElementRelationshipDTO) der).getSource()).getId()==((Notion) not).getId() && ((DomainElementRelationshipDTO) der).isDirected() && ((DomainElementRelationshipDTO) der).getTargetMultiplicity().equals("1") && ((DomainElementRelationshipDTO) der).getSourceMultiplicity().equals("1")) notval.add(((NotionDTO) ((DomainElementRelationshipDTO) der).getTarget()).getName());
    				if ((null==val || notval.containsAll(val) && val.containsAll(notval)) && (!MConfiguration.isAddSeparateNotionFromEachId() || null==attributeToIdMap || !attributeToIdMap.containsKey(not) || null!=id && attributeToIdMap.get(not).equals(id))){
    					attribute=not;
    					break loop;
    				} else {
    					name=pname+(MConfiguration.isCanUseBrackets()?" ("+i+")":" "+i);
    					i++;
    					continue loop;
    				}
    			}
    		end=true;
    	}
    	if (null==attribute){
    		attribute = rmh.getBussinessLayerFacade().createNotionDTO();
    		NounPhraseDTO noun = rmh.getBussinessLayerFacade().createNounPhraseDTO();
			noun.setNounText(name);
    		NounDTO n;
    		if (null==(n=rmh.getBussinessLayerFacade().getNoun(name))) n=rmh.getBussinessLayerFacade().createNounDTO();
			n.setName(name);
			noun.setNoun(n);
    		attribute.setNamePhrase(noun);
    		if (0==n.getSynonymUid()){
    			if (SCProjectHelper.getSenseAutoAddAndAssigmentFlag()){
    				try {
    					attribute.getNamePhrase().getNoun().autoAddAndAssignSense();
    				} catch (NullPointerException e){
    					throw new TerminologyOperationFailureException();
    				}
    			} else attribute.getNamePhrase().getNoun().autoAssignSense();
    		}
    		cleanNouns(attribute.getNamePhrase().getNoun());
        	if (null!=type){
    			PrimitiveDataTypeDTO dt = rmh.getBussinessLayerFacade().createPrimitiveDataTypeDTO();
    			dt.setTypeName(type.getType());
    			attribute.setDataType(dt);
    			MNotion.setExtendedDataType(attribute, type.tag());
    		}
        	if (!MConfiguration.getDescAttribute().isEmpty()) attribute.setDescription(MConfiguration.getDescAttribute());
        	Stereotype stereotype = rmh.getBussinessLayerFacade().createSclkernel$Stereotype();
        	stereotype.setName(NotionTypesEnum.Attribute.tag());
        	((Notion) attribute).addStereotype(stereotype);
        	if (MConfiguration.isPackageorg() && !MConfiguration.getAttributePack().isEmpty() && null!=rmh.getNotionsPackage(MConfiguration.getAttributePack())) attribute.setParent(rmh.getNotionsPackage(MConfiguration.getAttributePack()));
        	else attribute.setParent(rmh.getNotionsPackage("Notions"));
    	}
    	return attribute;
    }
    
    /**
     * Adds new notions represents option to attribute of enumerate type
     * 
     * @param name name of option
     * @param attribute attribute
     * @throws TerminologyOperationFailureException
     */
    public static NotionDTO addOptionToAttribute(String pname, NotionDTO attribute, String id, Map<NotionDTO, String> attributeToIdMap) throws TerminologyOperationFailureException{
    	RecoveryModelHelper rmh = RecoveryModelHelper.instance((Notion) attribute);
    	pname=RemoteJGWNL.getInstance().getNounBaseForm(pname.trim());
    	NotionDTO option=null;
    	String name=pname;
    	int i=1;
    	boolean end = false;
    	loop:
    	while (!end){
    		for (NotionDTO not : rmh.getNotionsOfType(Arrays.asList(NotionTypesEnum.Option.tag()))) if (not.getName().equals(name)){
    			if (!MConfiguration.isAddSeparateNotionFromEachId() || null==attributeToIdMap || !attributeToIdMap.containsKey(not) || null!=id && attributeToIdMap.get(not).equals(id)){
    				option=not;
    				break loop;
    			} else {
    				name=pname+(MConfiguration.isCanUseBrackets()?" ("+i+")":" "+i);
    				i++;
					continue loop;
    			}	
    		}
    		end = true;
    	}
    	if (null==option){
    		option = rmh.getBussinessLayerFacade().createNotionDTO();
    		NounPhraseDTO noun = rmh.getBussinessLayerFacade().createNounPhraseDTO();
			noun.setNounText(name);
    		NounDTO n;
    		if (null==(n=rmh.getBussinessLayerFacade().getNoun(name))) n=rmh.getBussinessLayerFacade().createNounDTO();
			n.setName(name);
			noun.setNoun(n);
    		option.setNamePhrase(noun);
    		if (0==n.getSynonymUid()){
    			if (SCProjectHelper.getSenseAutoAddAndAssigmentFlag()){
    				try {
    					option.getNamePhrase().getNoun().autoAddAndAssignSense();
    				} catch (NullPointerException e){
    					throw new TerminologyOperationFailureException();
    				}
    			} else option.getNamePhrase().getNoun().autoAssignSense();
    		}
    		cleanNouns(option.getNamePhrase().getNoun());
    		if (!MConfiguration.getDescOption().isEmpty()) option.setDescription(MConfiguration.getDescOption());
    		Stereotype stereotype = rmh.getBussinessLayerFacade().createSclkernel$Stereotype();
    		stereotype.setName(NotionTypesEnum.Option.tag());
    		((Notion) option).addStereotype(stereotype);
    		if (MConfiguration.isPackageorg() && !MConfiguration.getOptionPack().isEmpty() && null!=rmh.getNotionsPackage(MConfiguration.getOptionPack())) option.setParent(rmh.getNotionsPackage(MConfiguration.getOptionPack()));
    		else attribute.setParent(rmh.getNotionsPackage("Notions"));
    	}
    	if (!option.checkIfRelated(attribute))
			attribute.addRelatedNotion(option);
    	return option;
    }
	
	/**
	 * Cleans notions after removal of the last sentence
	 * 
	 * @param sentence removed sentence 
	 */
    public static void clean(SVOSentenceDTO sentence){
    	RecoveryModelHelper rmh = RecoveryModelHelper.instance((SVOSentence) sentence);
    	if (!MConfiguration.getClean()) return;
    	boolean phrase = false,notion = false;
        boolean phrase2=(sentence.getPredicate() instanceof SimpleVerbPhraseDTO?true:false);
        boolean notion2=phrase2;
        String name = sentence.getPredicate() instanceof SimpleVerbPhraseDTO?((SimpleVerbPhraseDTO) sentence.getPredicate()).getObject().getNounText():((ComplexVerbPhraseDTO) sentence.getPredicate()).getSimpleVerbPhrase().getObject().getNounText();
        //checks if exists notions or phrases arising only from the deleted sentence
        for (SVOSentence sent : rmh.getBussinessLayerFacade().getSVOSentenceVertices()) if (sent.getId()!=sentence.getId() && null!=((SVOSentenceDTO) sent).getPredicate() && (((SVOSentenceDTO) sent).getPredicate() instanceof SimpleVerbPhraseDTO?null!=((SimpleVerbPhraseDTO)((SVOSentenceDTO) sent).getPredicate()).getObject():null!=((ComplexVerbPhraseDTO)((SVOSentenceDTO) sent).getPredicate()).getSimpleVerbPhrase().getObject()) && (((SVOSentenceDTO) sent).getPredicate() instanceof SimpleVerbPhraseDTO?null!=((SimpleVerbPhraseDTO)((SVOSentenceDTO) sent).getPredicate()).getObject().getNounText():null!=((ComplexVerbPhraseDTO)((SVOSentenceDTO) sent).getPredicate()).getSimpleVerbPhrase().getObject().getNounText()) && (((SVOSentenceDTO) sent).getPredicate() instanceof SimpleVerbPhraseDTO?((SimpleVerbPhraseDTO)((SVOSentenceDTO) sent).getPredicate()).getObject().getNounText().equals(name):((ComplexVerbPhraseDTO)((SVOSentenceDTO) sent).getPredicate()).getSimpleVerbPhrase().getObject().getNounText().equals(name))){
        	notion=true;
            if (sentence.getPredicate() instanceof ComplexVerbPhraseDTO && ((SVOSentenceDTO) sent).getPredicate() instanceof ComplexVerbPhraseDTO && ((ComplexVerbPhraseDTO) ((SVOSentenceDTO) sent).getPredicate()).getObject().getNounText().equals(((ComplexVerbPhraseDTO) sentence.getPredicate()).getObject().getNounText())) notion2=true;
            if (((SVOSentenceDTO) sent).getPredicate() instanceof SimpleVerbPhraseDTO?((SimpleVerbPhraseDTO)((SVOSentenceDTO) sent).getPredicate()).getVerb().getName().equals(sentence.getPredicate() instanceof SimpleVerbPhraseDTO?((SimpleVerbPhraseDTO) sentence.getPredicate()).getVerb().getName():((ComplexVerbPhraseDTO) sentence.getPredicate()).getSimpleVerbPhrase().getVerb().getName()):((ComplexVerbPhraseDTO)((SVOSentenceDTO) sent).getPredicate()).getSimpleVerbPhrase().getVerb().getName().equals(sentence.getPredicate() instanceof SimpleVerbPhraseDTO?((SimpleVerbPhraseDTO) sentence.getPredicate()).getVerb().getName():((ComplexVerbPhraseDTO) sentence.getPredicate()).getSimpleVerbPhrase().getVerb().getName())){
            	phrase=true;
                if (notion2){
                	phrase2=true;
                	break;
                }
            }
        }
        //deletes proper notions and phrases
        if (!phrase){
            NotionDTO not = rmh.getNotion(RemoteJGWNL.getInstance().getNounBaseForm(sentence.getPredicate() instanceof SimpleVerbPhraseDTO ? ((SimpleVerbPhraseDTO) sentence.getPredicate()).getObject().getNounText():((ComplexVerbPhraseDTO) sentence.getPredicate()).getSimpleVerbPhrase().getObject().getNounText()));
            if (!notion) not.delete();
            else for (DomainStatementDTO ds:not.getDomainStatementDTOList()){
            	PhraseDTO phr=sentence.getPredicate() instanceof SimpleVerbPhraseDTO?sentence.getPredicate():((ComplexVerbPhraseDTO) sentence.getPredicate()).getSimpleVerbPhrase();
            	if (MRecoveredNotion.equalsByName(ds.getPhraseDTO(),phr)){
            		ds.delete();
            		break;
            	}
            }
            if(!phrase2){
                not = rmh.getNotion(RemoteJGWNL.getInstance().getNounBaseForm(((ComplexVerbPhraseDTO) sentence.getPredicate()).getObject().getNounText()));
                if (!notion2) not.delete();
                else for (DomainStatementDTO ds:not.getDomainStatementDTOList()) if (MRecoveredNotion.equalsByName(ds.getPhraseDTO(),sentence.getPredicate())){
                	ds.delete();
                	break;
                }
            }
        }
    }
	
    /**
     * Clean duplicated nouns and notions in case of atomically adds new meaning
     * 
     * @param noun noun with assigned new meaning
     * @throws TerminologyOperationFailureException
     */
    public static void cleanNouns(NounDTO noun) throws TerminologyOperationFailureException{
    	RecoveryModelHelper rmh = RecoveryModelHelper.instance((Noun) noun);
    	List<NotionDTO> notions = rmh.getBussinessLayerFacade().findNotions(noun);
    	if (notions.size()>1){
    		NotionDTO baseNotion = rmh.getNotion(noun);
    		for (NotionDTO not:notions){
    			if (null==baseNotion) baseNotion=not;
    			else if (((Notion) baseNotion).getId()!=((Notion)not).getId()){
    				if (baseNotion.getType().equals(not.getType()) && hasTheSameFields(baseNotion,new HashSet<NotionDTO>(MNotion.getRelatedAttributes(not)))) minorMerges(baseNotion, not);
    				else {
    					if (((Noun)baseNotion.getNamePhrase().getNoun()).getId()==((Noun)not.getNamePhrase().getNoun()).getId()){
    						NounDTO nnoun = rmh.getBussinessLayerFacade().createNounDTO();
    						nnoun.setName(not.getNamePhrase().getNoun().getName());
    						nnoun.setSynonymUid(not.getNamePhrase().getNoun().getSynonymUid());
    						not.getNamePhrase().setNoun(nnoun);
    						for (DomainStatementDTO ds:not.getDomainStatementDTOList()) if (ds.getPhraseDTO() instanceof SimpleVerbPhraseDTO) ((SimpleVerbPhraseDTO)ds.getPhraseDTO()).getObject().setNoun(nnoun); else if (ds.getPhraseDTO() instanceof ComplexVerbPhraseDTO) ((ComplexVerbPhraseDTO)ds.getPhraseDTO()).getObject().setNoun(nnoun);
    					}
    					Matcher match = Pattern.compile("\\s("+MConfiguration.getInputsufix()+"|"+MConfiguration.getWindowsufix()+")?\\s\\([0-9]+\\)").matcher(not.getName());
    					NotionDTO notion;
    					boolean find=match.find();
    					while(find && match.end()!=not.getName().length()) find=match.find();
    					boolean data = NotionTypesEnum.Simple_View.tag().equals(not.getType()) || not.getType().isEmpty();
    					boolean window = NotionTypesEnum.Screen.tag().equals(not.getType()) || NotionTypesEnum.Message.tag().equals(not.getType()) || NotionTypesEnum.Confirmation_Dialog.tag().equals(not.getType()) || not.getType().isEmpty();
    					String name = RemoteJGWNL.getInstance().getNounBaseForm(rmh.checkNotionName(find?not.getName().substring(0,match.start()):not.getName(),new HashSet<NotionDTO>(MNotion.getRelatedAttributes(not)),data,window,null));
    					if (null==(notion=rmh.getNotion(name))){
    						Matcher match2 = Pattern.compile("\\s\\([0-9]+\\)").matcher(name);
    						find=match2.find();
    						while(find && match2.end()!=name.length()) find=match2.find();
    						boolean find2;
    						for (NounPhrase np : rmh.getBussinessLayerFacade().getNounPhraseVertices()) if (((Noun)((NounPhraseDTO) np).getNoun()).getId()==((Noun)not.getNamePhrase().getNoun()).getId()){
    							Matcher match3 = Pattern.compile("\\s\\([0-9]+\\)").matcher(((NounPhraseDTO) np).getNounText());
    							find2=match3.find();
    							while(find2 && match3.end()!=((NounPhraseDTO) np).getNounText().length()) find2=match3.find();
    							((NounPhraseDTO) np).setNounText((find2?((NounPhraseDTO) np).getNounText().substring(0,match3.start()):((NounPhraseDTO) np).getNounText())+(find?name.substring(match2.start(),match2.end()):""));
    						}
    						not.getNamePhrase().getNoun().setName(name);
    					} else minorMerges(notion, not);
    				}
    			}
    		}
    	}
    }

    /**
     * Merge of duplicated notions. Used in cleanNouns method
     * 
     * @param aNotion notion
     * @param pNotion duplicated notion
     * @return merged notion
     * @throws TerminologyOperationFailureException
     */
    private static NotionDTO minorMerges(NotionDTO aNotion, NotionDTO pNotion) throws TerminologyOperationFailureException {
		RecoveryModelHelper rmh = RecoveryModelHelper.instance((Notion) aNotion);
    	if (pNotion.getType().isEmpty() || MConfiguration.isAllowAttributesForDataViews() && (NotionTypesEnum.Simple_View.tag().equals(pNotion.getType()) || NotionTypesEnum.List_View.tag().equals(pNotion.getType()) || NotionTypesEnum.Tree_View.tag().equals(pNotion.getType()))){
    		for (NotionDTO not:pNotion.getNotionDTOAttributeList()){
    			boolean contains=false;
    			for (NotionDTO not2:aNotion.getNotionDTOAttributeList()) if (not.getName().equals(not2.getName())){
    				contains=true;
    				break;
    			}
    			if (!contains) aNotion.addNotionDTOAttribute(not);
    		}
    	} else if (NotionTypesEnum.Simple_View.tag().equals(pNotion.getType()) || NotionTypesEnum.List_View.tag().equals(pNotion.getType()) || NotionTypesEnum.Tree_View.tag().equals(pNotion.getType())){
    		for (NotionDTO not:pNotion.getPointedNotionDTOAttributeList()){
    			boolean contains=false;
    			for (NotionDTO not2:aNotion.getPointedNotionDTOAttributeList()) if (not.getName().equals(not2.getName())){
    				contains=true;
    				break;
    			}
    			if (!contains) aNotion.addRelatedNotion(not).setDirected(true);
    		}
    	}
		int id = ((Noun)pNotion.getNamePhrase().getNoun()).getId();
        List<NounPhraseDTO> list = new ArrayList<NounPhraseDTO>();
        for (NounPhrase noun: rmh.getBussinessLayerFacade().getNounPhraseVertices()) if (((Noun)((NounPhraseDTO) noun).getNoun()).getId()==id) list.add((NounPhraseDTO) noun);
        if (!RemoteJGWNL.getInstance().getNounBaseForm(aNotion.getName()).equals(RemoteJGWNL.getInstance().getNounBaseForm(pNotion.getName()))){
        	Matcher match = Pattern.compile("\\s\\([0-9]+\\)").matcher(aNotion.getName());
			boolean find=match.find();
			while(find && match.end()!=aNotion.getName().length()) find=match.find();
			boolean find2;
        	for (NounPhraseDTO noun:list){
        		Matcher match2 = Pattern.compile("\\s\\([0-9]+\\)").matcher(((NounPhraseDTO) noun).getNounText());
				find2=match2.find();
				while(find2 && match2.end()!=((NounPhraseDTO) noun).getNounText().length()) find2=match2.find();
				((NounPhraseDTO) noun).setNounText((find2?((NounPhraseDTO) noun).getNounText().substring(0,match2.start()):((NounPhraseDTO) noun).getNounText())+(find?aNotion.getName().substring(match.start(),match.end()):""));
        	}
    	}
        for (NounPhraseDTO noun:list){
        	noun.setNoun(aNotion.getNamePhrase().getNoun());
        }
		for (DomainStatementDTO dom:pNotion.getDomainStatementDTOList()) if (!(dom.getPhraseDTO() instanceof NounPhraseDTO)){
			boolean contains=false;
			for (DomainStatementDTO dom2:aNotion.getDomainStatementDTOList()) if (!(dom2.getPhraseDTO() instanceof NounPhraseDTO) && ((dom.getPhraseDTO() instanceof SimpleVerbPhraseDTO)?((dom2.getPhraseDTO() instanceof SimpleVerbPhraseDTO) && ((SimpleVerbPhraseDTO) dom.getPhraseDTO()).getVerb().getName().equals(((SimpleVerbPhraseDTO) dom2.getPhraseDTO()).getVerb().getName())):((dom2.getPhraseDTO() instanceof ComplexVerbPhraseDTO) && ((ComplexVerbPhraseDTO) dom.getPhraseDTO()).getSimpleVerbPhrase().getVerb().getName().equals(((ComplexVerbPhraseDTO) dom2.getPhraseDTO()).getSimpleVerbPhrase().getVerb().getName()) && ((ComplexVerbPhraseDTO) dom.getPhraseDTO()).getSimpleVerbPhrase().getObject().getNounText().equals(((ComplexVerbPhraseDTO) dom2.getPhraseDTO()).getSimpleVerbPhrase().getObject().getNounText()) && ((ComplexVerbPhraseDTO) dom.getPhraseDTO()).getPreposition().getName().equals(((ComplexVerbPhraseDTO) dom2.getPhraseDTO()).getPreposition().getName())))){
				contains=true;
				if (dom2.getActionTypeTag().isEmpty() && !dom.getActionTypeTag().isEmpty()  && MNotion.isActionTypeValid(dom2, dom.getActionTypeTag())) MNotion.setActionType(dom2, dom.getActionTypeTag());
				break;
			}
			if(!contains){
				DomainStatementDTO ds = rmh.getBussinessLayerFacade().createDomainStatementDTO(dom.getPhraseDTO().copy(false));
				aNotion.addDomainStatementDTO(ds);
				if (!dom.getActionTypeTag().isEmpty()){
					if (MNotion.isActionTypeValid(ds, dom.getActionTypeTag()))
						MNotion.setActionType(ds, dom.getActionTypeTag());
					else
						MNotion.autoAssignActionType(ds,true);
				}
			}
		}
		if (NotionTypesEnum.Attribute.tag().equals(pNotion.getType()))
			for (NotionDTO not: rmh.getBussinessLayerFacade().getAllNotions()){
				if (not.getType().isEmpty() || MConfiguration.isAllowAttributesForDataViews() && (NotionTypesEnum.Simple_View.tag().equals(not.getType()) || NotionTypesEnum.List_View.tag().equals(not.getType()) || NotionTypesEnum.Tree_View.tag().equals(not.getType()))){
					for (NotionDTO no:not.getNotionDTOAttributeList()) if (((Notion)no).getId()==((Notion) pNotion).getId()){
						boolean constains = false;
						for (NotionDTO noti:not.getNotionDTOAttributeList()) if (((Notion)noti).getId()==((Notion) aNotion).getId()){
							constains=true;
							break;
						}
						if (!constains) not.addNotionDTOAttribute(aNotion);
						break;
					}
				} else if (NotionTypesEnum.Simple_View.tag().equals(not.getType()) || NotionTypesEnum.List_View.tag().equals(not.getType()) || NotionTypesEnum.Tree_View.tag().equals(not.getType())){
					for (NotionDTO no:not.getPointedNotionDTOAttributeList()) if (((Notion)no).getId()==((Notion) pNotion).getId()){
						boolean constains = false;
						for (NotionDTO noti:not.getPointedNotionDTOAttributeList()) if (((Notion)noti).getId()==((Notion) aNotion).getId()){
							constains=true;
							break;
						}
						if (!constains) not.addRelatedNotion(aNotion).setDirected(true);
						break;
					}
				}
			}
		boolean per = false;
		for (Stereotype st:((Notion) pNotion).getStereotypeList()) if (st.getName().equals(NotionTypesEnum.Simple_View.tag())){
			per = true;
			break;
		}
		if (per) for (Stereotype st:((Notion) aNotion).getStereotypeList()) if (st.getName().equals(NotionTypesEnum.Simple_View.tag())){
			per = false;
			break;
		}
		if (per){
			Stereotype stereotype = rmh.getBussinessLayerFacade().createSclkernel$Stereotype();
			stereotype.setName(NotionTypesEnum.Simple_View.tag());
			((Notion) aNotion).addStereotype(stereotype);
		}
		pNotion.delete();
		return aNotion;
	}
    
}
