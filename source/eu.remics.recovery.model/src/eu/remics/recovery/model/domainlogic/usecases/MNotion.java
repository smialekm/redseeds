package eu.remics.recovery.model.domainlogic.usecases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import eu.redseeds.common.SCProjectHelper;
import eu.redseeds.sc.terminology.model.RemoteJGWNL;
import eu.redseeds.scl.model.rsl.rsldomainelements.domainelements.DomainElementRelationshipDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.ActionTypesEnum;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.AttributeDataTypesEnum;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.DomainStatementDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionSpecialisationDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionTypesEnum;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.ComplexVerbPhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.NounPhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.SimpleVerbPhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.terms.NounDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.terms.TermDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.terms.VerbDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.svosentences.SVOSentenceDTO;
import eu.redseeds.scl.rsl.rsldomainelements.domainelements.DomainElement;
import eu.redseeds.scl.rsl.rsldomainelements.domainelements.DomainElementRelationship;
import eu.redseeds.scl.rsl.rsldomainelements.notions.DomainStatement;
import eu.redseeds.scl.rsl.rsldomainelements.notions.Notion;
import eu.redseeds.scl.rsl.rsldomainelements.notions.NotionSpecialisation;
import eu.redseeds.scl.rsl.rsldomainelements.phrases.NounPhrase;
import eu.redseeds.scl.rsl.rsldomainelements.terms.Noun;
import eu.redseeds.scl.rsl.rsldomainelements.terms.Term;
import eu.redseeds.scl.rsl.rslrequirementrepresentationsentences.svosentences.SVOSentence;
import eu.redseeds.scl.sclkernel.Stereotype;
import eu.remics.recovery.model.RecoveryModelHelper;
import eu.remics.recovery.model.domainlogic.recoveredscenarios.MRecoveredNotion;
import eu.remics.recovery.model.dto.TerminologyOperationFailureException;
import eu.remics.recovery.model.preferences.MConfiguration;

public class MNotion {
	
	/**
	 * Merges two notions in to one and set it new name
	 * 
	 * @param aNotion first notion
	 * @param pNotion second notion
	 * @param name0 new name
	 * @return merged notion
	 * @throws TerminologyOperationFailureException 
	 */
	public static NotionDTO merges(NotionDTO aNotion, NotionDTO pNotion, String name) throws TerminologyOperationFailureException {
		RecoveryModelHelper rmh = RecoveryModelHelper.instance((Notion) aNotion);
		String name0=RemoteJGWNL.getInstance().getNounBaseForm(name.trim());
		for (NotionDTO not:pNotion.getNotionDTOAttributeList()) if (((Notion)not).getId()!=((Notion) aNotion).getId()){
			boolean contains=false;
			for (NotionDTO not2:aNotion.getNotionDTOAttributeList()) if (not.getName().equals(not2.getName())){
				contains=true;
				break;
			}
			if (!contains) aNotion.addNotionDTOAttribute(not);
		}
		int id1=((Noun) aNotion.getNamePhrase().getNoun()).getId(),id2=((Noun) pNotion.getNamePhrase().getNoun()).getId();
		NounDTO n;
		if (null==(n=rmh.getBussinessLayerFacade().getNoun(name0))) n=rmh.getBussinessLayerFacade().createNounDTO();
		n.setName(name0);
		if (n.getSynonymUid()==0){
        	if (SCProjectHelper.getSenseAutoAddAndAssigmentFlag()){
        		try {
        			n.autoAddAndAssignSense();
        		} catch (NullPointerException e){
        			throw new TerminologyOperationFailureException();
        		}
        	}
        	else n.autoAssignSense();
        }
        List<NounPhraseDTO> list = new ArrayList<NounPhraseDTO>();
        for (NounPhrase noun: rmh.getBussinessLayerFacade().getNounPhraseVertices()) if (null!=((NounPhraseDTO) noun).getNoun() && (((Noun) ((NounPhraseDTO) noun).getNoun()).getId()==id1 || ((Noun) ((NounPhraseDTO) noun).getNoun()).getId()==id2)) list.add((NounPhraseDTO) noun);
        for (NounPhraseDTO noun:list){
        	noun.setNounText(name);
        	noun.setNoun(n);
        }
		for (DomainStatementDTO dom:pNotion.getDomainStatementDTOList()) if (!(dom.getPhraseDTO() instanceof NounPhraseDTO)){
			boolean contains=false;
			for (DomainStatementDTO dom2:aNotion.getDomainStatementDTOList()) if (!(dom2.getPhraseDTO() instanceof NounPhraseDTO) && ((dom.getPhraseDTO() instanceof SimpleVerbPhraseDTO)?((dom2.getPhraseDTO() instanceof SimpleVerbPhraseDTO) && ((SimpleVerbPhraseDTO) dom.getPhraseDTO()).getVerb().getName().equals(((SimpleVerbPhraseDTO) dom2.getPhraseDTO()).getVerb().getName())):((dom2.getPhraseDTO() instanceof ComplexVerbPhraseDTO) && ((ComplexVerbPhraseDTO) dom.getPhraseDTO()).getSimpleVerbPhrase().getVerb().getName().equals(((ComplexVerbPhraseDTO) dom2.getPhraseDTO()).getSimpleVerbPhrase().getVerb().getName()) && ((ComplexVerbPhraseDTO) dom.getPhraseDTO()).getSimpleVerbPhrase().getObject().getNounText().equals(((ComplexVerbPhraseDTO) dom2.getPhraseDTO()).getSimpleVerbPhrase().getObject().getNounText()) && ((ComplexVerbPhraseDTO) dom.getPhraseDTO()).getPreposition().getName().equals(((ComplexVerbPhraseDTO) dom2.getPhraseDTO()).getPreposition().getName())))){
				contains=true;
				if (dom2.getActionTypeTag().isEmpty() && !dom.getActionTypeTag().isEmpty() && isActionTypeValid(dom2, dom.getActionTypeTag())) setActionType(dom2, dom.getActionTypeTag());
				break;
			}
			if(!contains){
				DomainStatementDTO ds = rmh.getBussinessLayerFacade().createDomainStatementDTO(dom.getPhraseDTO().copy(false));
				aNotion.addDomainStatementDTO(ds);
				if (!dom.getActionTypeTag().isEmpty()){
					if (isActionTypeValid(ds, dom.getActionTypeTag()))
						setActionType(ds, dom.getActionTypeTag());
					else
						autoAssignActionType(ds,true);
				}
			}
		}
		for (NotionDTO not: rmh.getBussinessLayerFacade().getAllNotions()) if (((Notion)not).getId()!=((Notion) aNotion).getId()) for (NotionDTO no:not.getNotionDTOAttributeList()) if (((Notion)no).getId()==((Notion) pNotion).getId()){
			boolean constains = false;
			for (NotionDTO noti:not.getNotionDTOAttributeList()) if (((Notion)noti).getId()==((Notion) aNotion).getId()){
				constains=true;
				break;
			}
			if (!constains) not.addNotionDTOAttribute(aNotion);
			break;
		}
		for (DomainElementRelationship der:rmh.getBussinessLayerFacade().getDomainElementRelationshipVertices()) if (!der.getSourceList().isEmpty() && !der.getTargetList().isEmpty()){
			if (der.getSourceList().get(0).getId()==((Notion) pNotion).getId() && der.getTargetList().get(0).getId()!=((Notion) aNotion).getId()){
				der.removeSource((DomainElement) pNotion);
				der.addSource((DomainElement) aNotion);
			} else if (der.getTargetList().get(0).getId()==((Notion) pNotion).getId() && der.getSourceList().get(0).getId()!=((Notion) aNotion).getId()){
				der.removeTarget((DomainElement) pNotion);
				der.addTarget((DomainElement) aNotion);
			}
		}
		List<NotionSpecialisation> todel = new ArrayList<NotionSpecialisation>();
		for (NotionSpecialisation ns:rmh.getBussinessLayerFacade().getNotionSpecialisationVertices()) if (!ns.getSourceList().isEmpty() && !ns.getTargetList().isEmpty()){
			if (ns.getSourceList().get(0).getId()==((Notion) pNotion).getId() && ns.getTargetList().get(0).getId()!=((Notion) aNotion).getId()){
				if (!existGeneralization(aNotion, (NotionDTO) ns.getTargetList().get(0))){
					ns.removeSource((Notion) pNotion);
					ns.addSource((Notion) aNotion);
				} else todel.add(ns);
			} else if (ns.getTargetList().get(0).getId()==((Notion) pNotion).getId() && ns.getSourceList().get(0).getId()!=((Notion) aNotion).getId()){
				if (!existGeneralization((NotionDTO) ns.getSourceList().get(0), aNotion)){
					ns.removeTarget((Notion) pNotion);
					ns.addTarget((Notion) aNotion);
				} else todel.add(ns);
			}
		}
		for (NotionSpecialisation ns:todel) ns.delete();
		pNotion.delete();
		MRecoveredNotion.cleanNouns(n);
		List<NotionDTO> nlist = new ArrayList<NotionDTO>();
		for (DomainStatement dom : rmh.getBussinessLayerFacade().getDomainStatementVertices()) if (((DomainStatementDTO) dom).getPhraseDTO() instanceof ComplexVerbPhraseDTO){
			if (list.contains(((ComplexVerbPhraseDTO)((DomainStatementDTO) dom).getPhraseDTO()).getSimpleVerbPhrase().getObject()) && !nlist.contains(((DomainStatementDTO) dom).getParent())) nlist.add(((DomainStatementDTO) dom).getParent());
		}
		for (NotionDTO not:nlist)
			for (int i=0;i<not.getDomainStatementDTOList().size()-1;i++) if (not.getDomainStatementDTOList().get(i).getPhraseDTO() instanceof ComplexVerbPhraseDTO)
				for (int j=i+1;j<not.getDomainStatementDTOList().size();j++) if (not.getDomainStatementDTOList().get(j).getPhraseDTO() instanceof ComplexVerbPhraseDTO)
					if (not.getDomainStatementDTOList().get(i).getPhraseDTO().equals(not.getDomainStatementDTOList().get(j).getPhraseDTO())){
						not.getDomainStatementDTOList().get(j).delete();
						j--;
					}
		rmh.saveSCProject();
		return aNotion;
	}
	
	public static boolean checkDataViewAttributeRelationshipExistence(NotionDTO dataview, NotionDTO attribute){
		RecoveryModelHelper rmh = RecoveryModelHelper.instance((Notion) dataview);
		for(DomainElementRelationship der:rmh.getBussinessLayerFacade().getDomainElementRelationshipVertices()) if (der.getSourceList().contains(dataview) && der.getTargetList().contains(attribute) && der.getSourceMultiplicity().equals("1") && der.getTargetMultiplicity().equals("1")) return true;
		return false;
	}
	
	public static boolean canBeListView(NotionDTO notion){
		RecoveryModelHelper rmh = RecoveryModelHelper.instance((Notion) notion);
		for(DomainElementRelationship der:rmh.getBussinessLayerFacade().getDomainElementRelationshipVertices()){
			if (der.getSourceList().contains(notion) && !der.getTargetList().isEmpty() && null!=der.getTargetList().get(0) && der.getTargetList().get(0) instanceof NotionDTO){
				if (((NotionDTO) der.getTargetList().get(0)).getType().isEmpty() && (!der.isDirected() || !der.getSourceMultiplicity().equals("1") || !der.getTargetMultiplicity().equals("1")))
					return false;
				if (NotionTypesEnum.Attribute.tag().equals(((NotionDTO) der.getTargetList().get(0)).getType()) && (!der.getSourceMultiplicity().equals("1") || !der.getTargetMultiplicity().equals("1")))
					return false;
				if (NotionTypesEnum.Simple_View.tag().equals(((NotionDTO) der.getTargetList().get(0)).getType()) && (!der.isDirected() || !der.getSourceMultiplicity().equals("1") || !der.getTargetMultiplicity().equals("1")))
					return false;
				if (NotionTypesEnum.Screen.tag().equals(((NotionDTO) der.getTargetList().get(0)).getType()) && (!der.isDirected() || !der.getSourceMultiplicity().equals("1") || !der.getTargetMultiplicity().equals("1")))
					return false;
				if (NotionTypesEnum.Trigger.tag().equals(((NotionDTO) der.getTargetList().get(0)).getType()))
					return false;
				if (NotionTypesEnum.Tree_View.tag().equals(((NotionDTO) der.getTargetList().get(0)).getType()) && (!der.isDirected() || !der.getSourceMultiplicity().equals("1") || !der.getTargetMultiplicity().equals("1")))
					return false;
			}
			if (der.getTargetList().contains(notion) && !der.getSourceList().isEmpty() && null!=der.getSourceList().get(0) && der.getSourceList().get(0) instanceof NotionDTO){
				if (((NotionDTO) der.getSourceList().get(0)).getType().isEmpty())
					return false;
				if (NotionTypesEnum.Attribute.tag().equals(((NotionDTO) der.getSourceList().get(0)).getType()))
					return false;
				if (NotionTypesEnum.Simple_View.tag().equals(((NotionDTO) der.getSourceList().get(0)).getType()))
					return false;
				if (NotionTypesEnum.Screen.tag().equals(((NotionDTO) der.getSourceList().get(0)).getType()))
					return false;
				if (NotionTypesEnum.Trigger.tag().equals(((NotionDTO) der.getSourceList().get(0)).getType()) && (!der.getSourceMultiplicity().equals("1") || !der.getTargetMultiplicity().equals("1")))
					return false;
				if (NotionTypesEnum.Tree_View.tag().equals(((NotionDTO) der.getSourceList().get(0)).getType()) && (!der.isDirected() || !der.getSourceMultiplicity().equals("1") || !der.getTargetMultiplicity().equals("1")))
					return false;
			}
		}
		return true;
	}
	
	public static boolean canBeSimpleView(NotionDTO notion){
		RecoveryModelHelper rmh = RecoveryModelHelper.instance((Notion) notion);
		for(DomainElementRelationship der:rmh.getBussinessLayerFacade().getDomainElementRelationshipVertices()){
			if (der.getSourceList().contains(notion) && !der.getTargetList().isEmpty() && null!=der.getTargetList().get(0) && der.getTargetList().get(0) instanceof NotionDTO){
				if ((((NotionDTO) der.getTargetList().get(0))).getType().isEmpty() && (!der.isDirected() || !der.getSourceMultiplicity().equals("1") || !der.getTargetMultiplicity().equals("1")))
					return false;
				if (NotionTypesEnum.Attribute.tag().equals((((NotionDTO) der.getTargetList().get(0))).getType()) && (!der.getSourceMultiplicity().equals("1") || !der.getTargetMultiplicity().equals("1")))
					return false;
				if (NotionTypesEnum.List_View.tag().equals((((NotionDTO) der.getTargetList().get(0))).getType()))
					return false;
				if (NotionTypesEnum.Tree_View.tag().equals((((NotionDTO) der.getTargetList().get(0))).getType()))
					return false;
				if (NotionTypesEnum.Screen.tag().equals(((NotionDTO) der.getTargetList().get(0)).getType()) && (!der.isDirected() || !der.getSourceMultiplicity().equals("1") || !der.getTargetMultiplicity().equals("1")))
					return false;
				if (NotionTypesEnum.Trigger.tag().equals(((NotionDTO) der.getTargetList().get(0)).getType()))
					return false;
			}
			if (der.getTargetList().contains(notion) && !der.getSourceList().isEmpty() && null!=der.getSourceList().get(0) && der.getSourceList().get(0) instanceof NotionDTO){
				if ((((NotionDTO) der.getSourceList().get(0))).getType().isEmpty())
					return false;
				if (NotionTypesEnum.Attribute.tag().equals((((NotionDTO) der.getSourceList().get(0))).getType()))
					return false;
				if (NotionTypesEnum.List_View.tag().equals(((NotionDTO) der.getSourceList().get(0)).getType()) && (!der.isDirected() || !der.getSourceMultiplicity().equals("1") || !der.getTargetMultiplicity().equals("1")))
					return false;
				if (NotionTypesEnum.Tree_View.tag().equals(((NotionDTO) der.getSourceList().get(0)).getType()) && (!der.isDirected() || !der.getSourceMultiplicity().equals("1") || !der.getTargetMultiplicity().equals("1")))
					return false;
				if (NotionTypesEnum.Screen.tag().equals(((NotionDTO) der.getSourceList().get(0)).getType()) && (!der.getSourceMultiplicity().equals("1") || !der.getTargetMultiplicity().equals("1")))
					return false;
				if (NotionTypesEnum.Trigger.tag().equals(((NotionDTO) der.getSourceList().get(0)).getType()) && (!der.getSourceMultiplicity().equals("1") || !der.getTargetMultiplicity().equals("1")))
					return false;
			}
		}
		return true;
	}
	
	public static boolean canBeAttribute(NotionDTO notion) {
		RecoveryModelHelper rmh = RecoveryModelHelper.instance((Notion) notion);
		for(DomainElementRelationship der:rmh.getBussinessLayerFacade().getDomainElementRelationshipVertices()){
			if (der.getTargetList().contains(notion) && !der.getSourceList().isEmpty() && null!=der.getSourceList().get(0) && der.getSourceList().get(0) instanceof NotionDTO){
				if (NotionTypesEnum.List_View.tag().equals(((NotionDTO) der.getSourceList().get(0)).getType()) && (!der.getSourceMultiplicity().equals("1") || !der.getTargetMultiplicity().equals("1")))
					return false;
				if (NotionTypesEnum.Simple_View.tag().equals(((NotionDTO) der.getSourceList().get(0)).getType()) && (!der.getSourceMultiplicity().equals("1") || !der.getTargetMultiplicity().equals("1")))
					return false;
				if (NotionTypesEnum.Trigger.tag().equals(((NotionDTO) der.getSourceList().get(0)).getType()) && (!der.getSourceMultiplicity().equals("1") || !der.getTargetMultiplicity().equals("1")))
					return false;
			}
			if (der.getSourceList().contains(notion) && !der.getTargetList().isEmpty() && null!=der.getTargetList().get(0) && der.getTargetList().get(0) instanceof NotionDTO){
				if (NotionTypesEnum.List_View.tag().equals(((NotionDTO) der.getTargetList().get(0)).getType()))
					return false;
				if (NotionTypesEnum.Simple_View.tag().equals(((NotionDTO) der.getTargetList().get(0)).getType()))
					return false;
				if (NotionTypesEnum.Trigger.tag().equals(((NotionDTO) der.getTargetList().get(0)).getType()))
					return false;
			}
		}
		return true;
	}
	
	public static boolean canBeTrigger(NotionDTO notion) {
		RecoveryModelHelper rmh = RecoveryModelHelper.instance((Notion) notion);
		for(DomainElementRelationship der:rmh.getBussinessLayerFacade().getDomainElementRelationshipVertices()){
			if (der.getTargetList().contains(notion) && !der.getSourceList().isEmpty() && null!=der.getSourceList().get(0) && der.getSourceList().get(0) instanceof NotionDTO){
				if (NotionTypesEnum.Screen.tag().equals(((NotionDTO) der.getSourceList().get(0)).getType()) && (!der.getSourceMultiplicity().equals("1") || !der.getTargetMultiplicity().equals("1")))
					return false;
				if (NotionTypesEnum.Simple_View.tag().equals(((NotionDTO) der.getSourceList().get(0)).getType()))
					return false;
				if (NotionTypesEnum.List_View.tag().equals(((NotionDTO) der.getSourceList().get(0)).getType()))
					return false;
				if (NotionTypesEnum.Tree_View.tag().equals(((NotionDTO) der.getSourceList().get(0)).getType()))
					return false;
				if (NotionTypesEnum.Attribute.tag().equals(((NotionDTO) der.getSourceList().get(0)).getType()))
					return false;
			}
			if (der.getSourceList().contains(notion) && !der.getTargetList().isEmpty() && null!=der.getTargetList().get(0) && der.getTargetList().get(0) instanceof NotionDTO){
				if (NotionTypesEnum.Screen.tag().equals(((NotionDTO) der.getTargetList().get(0)).getType()) && (!der.getSourceMultiplicity().equals("1") || !der.getTargetMultiplicity().equals("1")))
					return false;
				if (NotionTypesEnum.Simple_View.tag().equals(((NotionDTO) der.getTargetList().get(0)).getType()) && (!der.getSourceMultiplicity().equals("1") || !der.getTargetMultiplicity().equals("1")))
					return false;
				if (NotionTypesEnum.List_View.tag().equals(((NotionDTO) der.getTargetList().get(0)).getType()) && (!der.getSourceMultiplicity().equals("1") || !der.getTargetMultiplicity().equals("1")))
					return false;
				if (NotionTypesEnum.Tree_View.tag().equals(((NotionDTO) der.getTargetList().get(0)).getType()) && (!der.getSourceMultiplicity().equals("1") || !der.getTargetMultiplicity().equals("1")))
					return false;
				if (NotionTypesEnum.Attribute.tag().equals(((NotionDTO) der.getTargetList().get(0)).getType()) && (!der.getSourceMultiplicity().equals("1") || !der.getTargetMultiplicity().equals("1")))
					return false;
			}
		}
		return true;
	}
	
	public static boolean canBeScreen(NotionDTO notion) {
		RecoveryModelHelper rmh = RecoveryModelHelper.instance((Notion) notion);
		for(DomainElementRelationship der:rmh.getBussinessLayerFacade().getDomainElementRelationshipVertices()){
			if (der.getTargetList().contains(notion) && !der.getSourceList().isEmpty() && null!=der.getSourceList().get(0) && der.getSourceList().get(0) instanceof NotionDTO){
				if (NotionTypesEnum.Trigger.tag().equals(((NotionDTO) der.getSourceList().get(0)).getType()))
					return false;
				if (NotionTypesEnum.List_View.tag().equals(((NotionDTO) der.getSourceList().get(0)).getType()) && (!der.isDirected() || !der.getSourceMultiplicity().equals("1") || !der.getTargetMultiplicity().equals("1")))
					return false;
				if (NotionTypesEnum.Tree_View.tag().equals(((NotionDTO) der.getSourceList().get(0)).getType()) && (!der.isDirected() || !der.getSourceMultiplicity().equals("1") || !der.getTargetMultiplicity().equals("1")))
					return false;
				if (NotionTypesEnum.Simple_View.tag().equals(((NotionDTO) der.getSourceList().get(0)).getType()) && (!der.isDirected() || !der.getSourceMultiplicity().equals("1") || !der.getTargetMultiplicity().equals("1")))
					return false;
			}
			if (der.getSourceList().contains(notion) && !der.getTargetList().isEmpty() && null!=der.getTargetList().get(0) && der.getTargetList().get(0) instanceof NotionDTO){
				if (NotionTypesEnum.Trigger.tag().equals(((NotionDTO) der.getTargetList().get(0)).getType()) && (!der.getSourceMultiplicity().equals("1") || !der.getTargetMultiplicity().equals("1")))
					return false;
				if (NotionTypesEnum.List_View.tag().equals(((NotionDTO) der.getTargetList().get(0)).getType()))
					return false;
				if (NotionTypesEnum.Tree_View.tag().equals(((NotionDTO) der.getTargetList().get(0)).getType()))
					return false;
				if (NotionTypesEnum.Simple_View.tag().equals(((NotionDTO) der.getTargetList().get(0)).getType()) && (!der.getSourceMultiplicity().equals("1") || !der.getTargetMultiplicity().equals("1")))
					return false;
			}
		}
		return true;
	}
	
	public static boolean canBeConcept(NotionDTO notion){
		RecoveryModelHelper rmh = RecoveryModelHelper.instance((Notion) notion);
		for(DomainElementRelationship der:rmh.getBussinessLayerFacade().getDomainElementRelationshipVertices()){
			if (der.getSourceList().contains(notion) && !der.getTargetList().isEmpty() && null!=der.getTargetList().get(0) && der.getTargetList().get(0) instanceof NotionDTO){
				if (NotionTypesEnum.List_View.tag().equals(((NotionDTO) der.getTargetList().get(0)).getType()))
					return false;
				if (NotionTypesEnum.Simple_View.tag().equals(((NotionDTO) der.getTargetList().get(0)).getType()))
					return false;
			}
			if (der.getTargetList().contains(notion) && !der.getSourceList().isEmpty() && null!=der.getSourceList().get(0) && der.getSourceList().get(0) instanceof NotionDTO){
				if (NotionTypesEnum.List_View.tag().equals(((NotionDTO) der.getSourceList().get(0)).getType()) && (!der.isDirected() || !der.getSourceMultiplicity().equals("1") || !der.getTargetMultiplicity().equals("1")))
					return false;
				if (NotionTypesEnum.Simple_View.tag().equals(((NotionDTO) der.getSourceList().get(0)).getType()) && (!der.isDirected() || !der.getSourceMultiplicity().equals("1") || !der.getTargetMultiplicity().equals("1")))
					return false;
			}
		}
		return true;
	}
	
	public static boolean canBeTreeView(NotionDTO notion) {
		RecoveryModelHelper rmh = RecoveryModelHelper.instance((Notion) notion);
		for(DomainElementRelationship der:rmh.getBussinessLayerFacade().getDomainElementRelationshipVertices()){
			if (der.getSourceList().contains(notion) && !der.getTargetList().isEmpty() && null!=der.getTargetList().get(0) && der.getTargetList().get(0) instanceof NotionDTO){
				if (((NotionDTO) der.getTargetList().get(0)).getType().isEmpty() && (!der.isDirected() || !der.getSourceMultiplicity().equals("1") && !der.getTargetMultiplicity().equals("1")))
					return false;
				if (NotionTypesEnum.Attribute.tag().equals(((NotionDTO) der.getTargetList().get(0)).getType()) && (!der.getSourceMultiplicity().equals("1") || !der.getTargetMultiplicity().equals("1")))
					return false;
				if (NotionTypesEnum.Simple_View.tag().equals(((NotionDTO) der.getTargetList().get(0)).getType()) && (!der.isDirected() || !der.getSourceMultiplicity().equals("1") || !der.getTargetMultiplicity().equals("1")))
					return false;
				if (NotionTypesEnum.Screen.tag().equals(((NotionDTO) der.getTargetList().get(0)).getType()) && (!der.isDirected() || !der.getSourceMultiplicity().equals("1") || !der.getTargetMultiplicity().equals("1")))
					return false;
				if (NotionTypesEnum.Trigger.tag().equals(((NotionDTO) der.getTargetList().get(0)).getType()))
					return false;
				if (NotionTypesEnum.List_View.tag().equals(((NotionDTO) der.getTargetList().get(0)).getType()) && (!der.isDirected() || !der.getSourceMultiplicity().equals("1") || !der.getTargetMultiplicity().equals("1")))
					return false;
			}
			if (der.getTargetList().contains(notion) && !der.getSourceList().isEmpty() && null!=der.getSourceList().get(0) && der.getSourceList().get(0) instanceof NotionDTO){
				if (((NotionDTO) der.getSourceList().get(0)).getType().isEmpty())
					return false;
				if (NotionTypesEnum.Attribute.tag().equals(((NotionDTO) der.getSourceList().get(0)).getType()))
					return false;
				if (NotionTypesEnum.Simple_View.tag().equals(((NotionDTO) der.getSourceList().get(0)).getType()))
					return false;
				if (NotionTypesEnum.Screen.tag().equals(((NotionDTO) der.getSourceList().get(0)).getType()))
					return false;
				if (NotionTypesEnum.Trigger.tag().equals(((NotionDTO) der.getSourceList().get(0)).getType()) && (!der.getSourceMultiplicity().equals("1") || !der.getTargetMultiplicity().equals("1")))
					return false;
				if (NotionTypesEnum.List_View.tag().equals(((NotionDTO) der.getSourceList().get(0)).getType()) && (!der.isDirected() || !der.getSourceMultiplicity().equals("1") || !der.getTargetMultiplicity().equals("1")))
					return false;
			}
		}
		return true;
	}
	
	public static boolean canBeOption(NotionDTO notion) {
		RecoveryModelHelper rmh = RecoveryModelHelper.instance((Notion) notion);
		for(DomainElementRelationship der:rmh.getBussinessLayerFacade().getDomainElementRelationshipVertices()){
			if (der.getSourceList().contains(notion) && !der.getTargetList().isEmpty() && null!=der.getTargetList().get(0) && der.getTargetList().get(0) instanceof NotionDTO){
				if (NotionTypesEnum.Attribute.tag().equals(((NotionDTO) der.getTargetList().get(0)).getType()))
					return false;
			}
			if (der.getTargetList().contains(notion) && !der.getSourceList().isEmpty() && null!=der.getSourceList().get(0) && der.getSourceList().get(0) instanceof NotionDTO){
				if (NotionTypesEnum.Attribute.tag().equals(((NotionDTO) der.getSourceList().get(0)).getType()) && (!der.getSourceMultiplicity().equals("1") || !der.getTargetMultiplicity().equals("1")))
					return false;
			}
		}
		return true;
	}

	public static boolean canBeConfirmationDialog(NotionDTO notion) {
		return true;
	}
	
	public static boolean canBeMessage(NotionDTO notion) {
		return true;
	}
	
	public static void autoAsignSense(){
		RecoveryModelHelper rmh = RecoveryModelHelper.instance();
		for (Term t:rmh.getBussinessLayerFacade().getTermVertices()) if (0==((TermDTO) t).getSynonymUid() || null==RemoteJGWNL.getInstance().getTermSenseDTO(((TermDTO) t).getSynonymUid())){
			if (null!=((TermDTO) t).getName() && !((TermDTO) t).getName().isEmpty()){
				if (SCProjectHelper.getSenseAutoAddAndAssigmentFlag()) ((TermDTO) t).autoAddAndAssignSense();
				else ((TermDTO) t).autoAssignSense();
			}
		}
		rmh.saveSCProject();
	}
	
	public static void removeArtifacts(){
		RecoveryModelHelper rmh = RecoveryModelHelper.instance();
		for(NounPhrase np:rmh.getBussinessLayerFacade().getNounPhraseVertices())
			if (np.getDeterminerList().isEmpty() && np.getElementList().isEmpty() && np.getHyperlinkList().isEmpty() && np.getModifierList().isEmpty() && np.getNotionList().isEmpty() && np.getRepresentableElementList().isEmpty() && np.getStatementList().isEmpty() && np.getSubjectList().isEmpty() && np.getVerbPhraseList().isEmpty())
				np.delete();
	}
	
	public static boolean isAttribute(NotionDTO notion){
		return ((Notion) notion).getParentNotionList().size()>0;
	}
	
	public static boolean isVisibleAttribute(NotionDTO not) {
		if (MConfiguration.isAllowAttributesForDataViews())
			for(Notion par:((Notion) not).getParentNotionList())
				if (NotionTypesEnum.Simple_View.tag().equals(((NotionDTO)par).getType()) || NotionTypesEnum.List_View.tag().equals(((NotionDTO)par).getType()) || NotionTypesEnum.Tree_View.tag().equals(((NotionDTO)par).getType()))
					return true;
		for(NotionDTO par:not.getPointingParentNotionList())
			if (NotionTypesEnum.Simple_View.tag().equals(par.getType()) || NotionTypesEnum.List_View.tag().equals(par.getType()) || NotionTypesEnum.Tree_View.tag().equals(par.getType()))
				return true;
		return false;
	}
	
	public static boolean isAttachedAttribute(NotionDTO not) {
		for(Notion par:((Notion) not).getParentNotionList())
			if (((NotionDTO)par).getType().isEmpty())
				return true;
		return false;
	}
	
	public static Stereotype setActionType(DomainStatementDTO ds, String crud){
		return setActionType(ds, crud, false);
	}
	
	public static Stereotype setActionType(DomainStatementDTO ds, String crud, boolean force){
		RecoveryModelHelper rmh = RecoveryModelHelper.instance((DomainStatement) ds);
		for (Stereotype st:((DomainStatement) ds).getStereotypeList()) if (Arrays.asList(ActionTypesEnum.tags()).contains(st.getName())){
			if (!crud.isEmpty() && st.getName().equals(crud) && !force)
				return st;
			st.delete();
			break;
		}
		if (!crud.isEmpty()){
			Stereotype stereotype = rmh
			.getBussinessLayerFacade().createSclkernel$Stereotype();
			stereotype.setName(crud);
			((DomainStatement)ds).addStereotype(stereotype);
			if (ds.getPhraseDTO() instanceof SimpleVerbPhraseDTO)
				for (SVOSentence sent:rmh.getBussinessLayerFacade().getSVOSentenceVertices())
					if (null!=((SVOSentenceDTO) sent).getPredicate() && (((SVOSentenceDTO) sent).getPredicate() instanceof SimpleVerbPhraseDTO?ds.getPhraseDTO().equals(((SVOSentenceDTO) sent).getPredicate()):ds.getPhraseDTO().equals(((ComplexVerbPhraseDTO) ((SVOSentenceDTO) sent).getPredicate()).getSimpleVerbPhrase())))
						sent.addStereotype(stereotype);
			return stereotype;
		}
		return null;
	}
	
	public static boolean isActionTypeValid(DomainStatementDTO ds, String crud){
		if (Arrays.asList(ActionTypesEnum.tags(ds.getParent().getExtendedDataType())).contains(crud)) return true;
		return false;
	}
	
	public static void clearActionTypes(NotionDTO notion, String type){
		for (DomainStatementDTO ds:notion.getDomainStatementDTOList()){
			if (!ds.getActionTypeTag().isEmpty())
				for (Stereotype st:((DomainStatement) ds).getStereotypeList())
					if (Arrays.asList(ActionTypesEnum.tags()).contains(st.getName())){
						st.delete();
						break;
					}
			autoAssignActionType(ds,type);
		}
	}

	public static void autoAssignActionType(DomainStatementDTO ds){
		autoAssignActionType(ds,ds.getParent().getType());
	}
	
	public static void autoAssignActionType(DomainStatementDTO ds, boolean force){
		autoAssignActionType(ds,ds.getParent().getType(), force);
	}
	
	public static void autoAssignActionType(DomainStatementDTO ds, String type){
		autoAssignActionType(ds, type, false);
	}
	
	public static void autoAssignActionType(DomainStatementDTO ds, String type, boolean force){
		if (ds.getPhraseDTO() instanceof NounPhraseDTO) return;
		if (force) setActionType(ds, "");
		RecoveryModelHelper rmh = RecoveryModelHelper.instance((DomainStatement) ds);
		VerbDTO verb = ds.getPhraseDTO() instanceof SimpleVerbPhraseDTO?((SimpleVerbPhraseDTO) ds.getPhraseDTO()).getVerb():((ComplexVerbPhraseDTO) ds.getPhraseDTO()).getSimpleVerbPhrase().getVerb();
		if (type.isEmpty() || NotionTypesEnum.Simple_View.tag().equals(type) || NotionTypesEnum.List_View.tag().equals(type) || NotionTypesEnum.Tree_View.tag().equals(type)){
			for (String s:MConfiguration.getCrudcreatesenses()) if (rmh.compareSenses(verb, s)){
				setActionType(ds, ActionTypesEnum.Create.tag());
				return;
			}
			for (String s:MConfiguration.getCrudreadsenses()) if (rmh.compareSenses(verb, s)){
				setActionType(ds, ActionTypesEnum.Read.tag());
				return;
			}
			for (String s:MConfiguration.getCrudupdatesenses()) if (rmh.compareSenses(verb, s)){
				setActionType(ds, ActionTypesEnum.Update.tag());
				return;
			}
			for (String s:MConfiguration.getCruddeletesenses()) if (rmh.compareSenses(verb, s)){
				setActionType(ds, ActionTypesEnum.Delete.tag());
				return;
			}
			for (String s:MConfiguration.getActionvalidatesenses()) if (rmh.compareSenses(verb, s)){
				setActionType(ds, ActionTypesEnum.Validate.tag());
				return;
			}
		}
		if (NotionTypesEnum.Screen.tag().equals(type)){
			for (String s:MConfiguration.getActionshowsenses()) if (rmh.compareSenses(verb, s)){
				setActionType(ds, ActionTypesEnum.Show.tag());
				return;
			}
			for (String s:MConfiguration.getActionclosesenses()) if (rmh.compareSenses(verb, s)){
				setActionType(ds, ActionTypesEnum.Close.tag());
				return;
			}
			for (String s:MConfiguration.getActionrefreshsenses()) if (rmh.compareSenses(verb, s)){
				setActionType(ds, ActionTypesEnum.Refresh.tag());
				return;
			}
		}
		if (NotionTypesEnum.Message.tag().equals(type) || NotionTypesEnum.Confirmation_Dialog.tag().equals(type)){
			for (String s:MConfiguration.getActionshowsenses()) if (rmh.compareSenses(verb, s)){
				setActionType(ds, ActionTypesEnum.Show.tag());
				return;
			}
		}
		if (NotionTypesEnum.Trigger.tag().equals(type)){
			for (String s:MConfiguration.getActionselectsenses()) if (rmh.compareSenses(verb, s)){
				setActionType(ds, ActionTypesEnum.Select.tag());
				return;
			}
		}
	}
	
	public static boolean existGeneralization(NotionDTO source,
			NotionDTO target) {
		RecoveryModelHelper rmh = RecoveryModelHelper.instance((Notion)source);
		for (NotionSpecialisation ns:rmh.getBussinessLayerFacade().getNotionSpecialisationVertices())
			if (((Notion)((NotionSpecialisationDTO)ns).getGeneralNotion()).getId()==((Notion) source).getId() && ((Notion)((NotionSpecialisationDTO)ns).getSpecialisedNotion()).getId()==((Notion) target).getId() || ((Notion)((NotionSpecialisationDTO)ns).getGeneralNotion()).getId()==((Notion) target).getId() && ((Notion)((NotionSpecialisationDTO)ns).getSpecialisedNotion()).getId()==((Notion) source).getId())
				return true;
		return false;
	}
	
	public static boolean canCreateRelation(NotionDTO source, NotionDTO target, boolean canBeDirected, boolean canReverse){
		if (target.checkIfRelated(source)) return false;
		return canExistRelation(source, target, canBeDirected, canReverse);
	}
	
	public static boolean canExistRelation(NotionDTO source, NotionDTO target, boolean canBeDirected, boolean canReverse){
		if (MConfiguration.isCheckRelations()){
			String sourceType = source.getType();
			String targetType = target.getType();
			if (sourceType.isEmpty()){
				if (NotionTypesEnum.Simple_View.tag().equals(targetType)) return canReverse && canBeDirected;
				if (NotionTypesEnum.List_View.tag().equals(targetType)) return canReverse && canBeDirected;
				if (NotionTypesEnum.Tree_View.tag().equals(targetType)) return canReverse && canBeDirected;
			} else if (NotionTypesEnum.Simple_View.tag().equals(sourceType)){
				if (targetType.isEmpty()) return canBeDirected;
				if (NotionTypesEnum.List_View.tag().equals(targetType)) return canBeDirected && canReverse;
				if (NotionTypesEnum.Screen.tag().equals(targetType)) return canBeDirected || canReverse;
				if (NotionTypesEnum.Attribute.tag().equals(targetType)) return true;
				if (NotionTypesEnum.Trigger.tag().equals(targetType)) return canReverse;
			} else if (NotionTypesEnum.List_View.tag().equals(sourceType)){
				if (targetType.isEmpty()) return canBeDirected;
				if (NotionTypesEnum.Simple_View.tag().equals(targetType)) return canBeDirected;
				if (NotionTypesEnum.Screen.tag().equals(targetType)) return canBeDirected;
				if (NotionTypesEnum.Attribute.tag().equals(targetType)) return true;
				if (NotionTypesEnum.Trigger.tag().equals(targetType)) return canReverse;
				if (NotionTypesEnum.Tree_View.tag().equals(targetType)) return canBeDirected;
			} else if (NotionTypesEnum.Screen.tag().equals(sourceType)){
				if (NotionTypesEnum.Simple_View.tag().equals(targetType)) return true;
				if (NotionTypesEnum.List_View.tag().equals(targetType)) return canReverse && canBeDirected;
				if (NotionTypesEnum.Trigger.tag().equals(targetType)) return true;
				if (NotionTypesEnum.Tree_View.tag().equals(targetType)) return canReverse && canBeDirected;
			} else if (NotionTypesEnum.Trigger.tag().equals(sourceType)){
				if (NotionTypesEnum.Screen.tag().equals(targetType)) return canReverse;
				if (NotionTypesEnum.Simple_View.tag().equals(targetType)) return true;
				if (NotionTypesEnum.List_View.tag().equals(targetType)) return true;
				if (NotionTypesEnum.Tree_View.tag().equals(targetType)) return true;
				if (NotionTypesEnum.Attribute.tag().equals(targetType)) return true;
			} else if (NotionTypesEnum.Attribute.tag().equals(sourceType)){
				if (NotionTypesEnum.Simple_View.tag().equals(targetType)) return canReverse;
				if (NotionTypesEnum.List_View.tag().equals(targetType)) return canReverse;
				if (NotionTypesEnum.Tree_View.tag().equals(targetType)) return canReverse;
				if (NotionTypesEnum.Trigger.tag().equals(targetType)) return canReverse;
			} else if (NotionTypesEnum.Tree_View.tag().equals(sourceType)){
				if (targetType.isEmpty()) return canBeDirected;
				if (NotionTypesEnum.Simple_View.tag().equals(targetType)) return canBeDirected;
				if (NotionTypesEnum.Screen.tag().equals(targetType)) return canBeDirected;
				if (NotionTypesEnum.Attribute.tag().equals(targetType)) return true;
				if (NotionTypesEnum.Trigger.tag().equals(targetType)) return canReverse;
				if (NotionTypesEnum.List_View.tag().equals(targetType)) return canBeDirected;
			}
		}
		return true;
	}
	
	public static boolean canCreateGeneralization(NotionDTO source, NotionDTO target){
		return !existGeneralization((NotionDTO) source,(NotionDTO) target) && canExistGeneralization(source, target);
	}
	
	public static boolean canExistGeneralization(NotionDTO source, NotionDTO target){
		return (!MConfiguration.isCheckRelations() || ((NotionDTO) source).getType().equals(((NotionDTO) target).getType())) && ((Notion) source).getId()!=((Notion) target).getId();
	}
	
	public static boolean canAddAtribute(NotionDTO source, NotionDTO target){
		return !target.getNotionDTOAttributeList().contains(source) && canExistAddedAtribute(source, target);
	}
	
	public static boolean canExistAddedAtribute(NotionDTO source, NotionDTO target){
		return (NotionTypesEnum.Attribute.tag().equals(((NotionDTO) source).getType()) && (((NotionDTO) target).getType().isEmpty() || MConfiguration.isAllowAttributesForDataViews() && (NotionTypesEnum.Simple_View.tag().equals(((NotionDTO) target).getType()) || NotionTypesEnum.List_View.tag().equals(((NotionDTO) target).getType()) || NotionTypesEnum.Tree_View.tag().equals(((NotionDTO) target).getType()))) || !MConfiguration.isCheckRelations());
	}

	public static boolean isClearActionType(NotionDTO notion, int i) {
		List<ActionTypesEnum> l1 = Arrays.asList(ActionTypesEnum.values(notion.getType()));
		List<ActionTypesEnum> l2 = Arrays.asList(ActionTypesEnum.values(i>0?NotionTypesEnum.values()[i-1].tag():""));
		return !l1.equals(l2);
	}
	
	public static void setExtendedDataType(NotionDTO notion, String edt){
		RecoveryModelHelper rmh = RecoveryModelHelper.instance((Notion) notion);
		for (Stereotype st:((Notion) notion).getStereotypeList()) if (Arrays.asList(AttributeDataTypesEnum.tags()).contains(st.getName())){
			st.delete();
			break;
		}
		if (!edt.isEmpty()){
			Stereotype stereotype = rmh
			.getBussinessLayerFacade().createSclkernel$Stereotype();
			stereotype.setName(edt);
			((Notion) notion).addStereotype(stereotype);
		}
	}

	public static String getStereotype(DomainElementRelationshipDTO der) {
		String sourceType = ((NotionDTO) der.getSource()).getType();
		String targetType = ((NotionDTO) der.getTarget()).getType();
		if (NotionTypesEnum.Simple_View.tag().equals(sourceType)){
			if (targetType.isEmpty()) return "main concept";
		} else if (NotionTypesEnum.List_View.tag().equals(sourceType)){
			if (targetType.isEmpty()) return "main concept";
			if (NotionTypesEnum.Simple_View.tag().equals(targetType)) return "master-details";
			if (NotionTypesEnum.Tree_View.tag().equals(targetType)) return "master-details";
		} else if (NotionTypesEnum.Trigger.tag().equals(sourceType)){
			if (NotionTypesEnum.Simple_View.tag().equals(targetType)) return "action param";
			if (NotionTypesEnum.List_View.tag().equals(targetType)) return "action param";
			if (NotionTypesEnum.Tree_View.tag().equals(targetType)) return "action param";
			if (NotionTypesEnum.Attribute.tag().equals(targetType)) return "action param";
		} else if (NotionTypesEnum.Tree_View.tag().equals(sourceType)){
			if (targetType.isEmpty()) return "main concept";
			if (NotionTypesEnum.Simple_View.tag().equals(targetType)) return "master-details";
			if (NotionTypesEnum.List_View.tag().equals(targetType)) return "master-details";
		}
		return "";
	}
	
	public static List<NotionDTO> getAttributesFromNotions(List<NotionDTO> notions){
		List<NotionDTO> result = new ArrayList<NotionDTO>();
		for (NotionDTO not:notions)
			for (NotionDTO attr:not.getNotionDTOAttributeList())
				if (!result.contains(attr))
					result.add(attr);
		return result;
	}
	
	public static List<NotionDTO> getRelatedAttributesFromNotions(List<NotionDTO> notions){
		List<NotionDTO> result = new ArrayList<NotionDTO>();
		for (NotionDTO not:notions){
			if (MConfiguration.isAllowAttributesForDataViews()){
				for (NotionDTO attr:not.getNotionDTOAttributeList())
					if (!result.contains(attr))
						result.add(attr);
			} else {
				for (NotionDTO attr:not.getPointedNotionDTOAttributeList())
					if (!result.contains(attr))
						result.add(attr);
			}
		}
		return result;
	}
	
	public static List<NotionDTO> getRelatedAttributes(NotionDTO notion){
			if (notion.getType().isEmpty() || MConfiguration.isAllowAttributesForDataViews())
				return notion.getNotionDTOAttributeList();
			return notion.getPointedNotionDTOAttributeList();
	}
	
}