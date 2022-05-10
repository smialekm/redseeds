package eu.redseeds.scl.model.impl.rsl.rsldomainelements.notions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

import de.uni_koblenz.jgralab.Edge;
import de.uni_koblenz.jgralab.EdgeDirection;
import de.uni_koblenz.jgralab.Graph;
import de.uni_koblenz.jgralab.GraphIO;
import de.uni_koblenz.jgralab.GraphIOException;
import eu.redseeds.common.SCProjectHelper;
import eu.redseeds.scl.impl.SCLGraphImpl;
import eu.redseeds.scl.impl.rsl.rsldomainelements.notions.NotionImpl;
import eu.redseeds.scl.model.BusinessLayerFacade;
import eu.redseeds.scl.model.rsl.rsldomainelements.actors.ActorDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.domainelements.DomainElementRelationshipDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.domainelements.DomainElementRepresentationDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.AttributeDataTypesEnum;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.DomainStatementDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionSpecialisationDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionTypesEnum;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionsPackageDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.PrimitiveDataTypeDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.ComplexVerbPhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.NounLinkDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.NounPhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.PhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.VerbPhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.systemelements.SystemElementDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.terms.NounDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.representationsentences.NaturalLanguageHypertextSentenceDTO;
import eu.redseeds.scl.model.traceability.TraceabilityLinkDTO;
import eu.redseeds.scl.model.traceability.TraceableObjectDTO;
import eu.redseeds.scl.rsl.rsldomainelements.domainelementrepresentations.DomainElementRepresentation;
import eu.redseeds.scl.rsl.rsldomainelements.domainelements.DomainElementRelationship;
import eu.redseeds.scl.rsl.rsldomainelements.notions.AttributeDataType;
import eu.redseeds.scl.rsl.rsldomainelements.notions.DomainStatement;
import eu.redseeds.scl.rsl.rsldomainelements.notions.Notion;
import eu.redseeds.scl.rsl.rsldomainelements.notions.NotionSpecialisation;
import eu.redseeds.scl.rsl.rsldomainelements.notions.NotionsPackage;
import eu.redseeds.scl.rsl.rsldomainelements.phrases.NounLink;
import eu.redseeds.scl.rsl.rsldomainelements.phrases.NounPhrase;
import eu.redseeds.scl.rsl.rsldomainelements.terms.Noun;
import eu.redseeds.scl.rsl.rslkernel.elements.ElementRepresentation;
import eu.redseeds.scl.rsl.rslkernel.elements.HyperlinkedSentence;
import eu.redseeds.scl.rsl.rslrequirementrepresentationsentences.representationsentences.NaturalLanguageHypertextSentence;
import eu.redseeds.scl.sclkernel.IsAllocatedTo;
import eu.redseeds.scl.sclkernel.SCLElement;
import eu.redseeds.scl.sclkernel.Stereotype;


public class NotionDTOImpl extends NotionImpl implements NotionDTO, TraceableObjectDTO {

	public NotionDTOImpl(int arg0, Graph arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}
	
	public void readAttributeValues(GraphIO io) throws GraphIOException {
		try {
			defaultValue = io.matchUtfString();
			setDefaultValue(defaultValue);
			isPersistent = io.matchBoolean();
			setIsPersistent(isPersistent);
			uid = io.matchUtfString();
			setUid(uid);
		} catch (GraphIOException ex) {
			if (getDefaultValue() != null) {
				setUid(getDefaultValue());
				setDefaultValue(null);
			}
		}
	}
	
	public String getName() {
		List<? extends SCLElement> list = super.getNameList();
		if (list.size() > 0) {
			NounDTO  noun = ((NounPhraseDTO) list.get(list.size()-1)).getNoun();
			if(noun != null) {
				return noun.getName();
			}
		}
		return null;
	}
	
	public NounPhraseDTO getNamePhrase() {
		List<? extends SCLElement> list = super.getNameList();
		if (list.size() > 0) {
		    return ((NounPhraseDTO) list.get(list.size()-1));
		}
		return null;
	}
	

	@Override
	public void setName(String name) {
		NounDTO noun = ((BusinessLayerFacade) getGraph()).createNounDTO(name);
		if (getName() == null) {
			NounPhraseDTO phrase = (NounPhraseDTO) ((SCLGraphImpl) super
					.getGraph()).createNounPhrase();
			phrase.setNoun(noun);
			addName((NounPhrase) phrase);
			DomainStatementDTO statement = (DomainStatementDTO) ((SCLGraphImpl) super
					.getGraph()).createDomainStatement();
			statement.setPhraseDTO(phrase.copy(false));
			addDomainStatementDTO(statement);
		}

		for (DomainStatementDTO ds : getDomainStatementDTOList()) {
			NounPhraseDTO np = null;
			if (ds.getPhraseDTO() instanceof NounPhraseDTO) {
				np = (NounPhraseDTO) ds.getPhraseDTO();
			} else if (ds.getPhraseDTO() instanceof VerbPhraseDTO) {
				np = ((VerbPhraseDTO) ds.getPhraseDTO()).getObject();			}
			if (np != null) {
				np.setNoun(noun);
				np.setNounText(name);
			}
		}
		getNamePhrase().setNounText(name);
		getNamePhrase().setNoun(noun);

	}
	
	@Override
	public void setNamePhrase(NounPhraseDTO phrase) {		
		NounDTO noun = phrase.getNoun();
		if (getName() == null){
			addName((NounPhrase)phrase);
			DomainStatementDTO statement = (DomainStatementDTO)((SCLGraphImpl)super.getGraph()).createDomainStatement();
			statement.setPhraseDTO(phrase.copy(false));
			addDomainStatementDTO(statement);
		}
		
		for (DomainStatementDTO ds : getDomainStatementDTOList()){
			if (ds.getPhraseDTO() instanceof NounPhraseDTO){
				NounPhraseDTO np = (NounPhraseDTO)ds.getPhraseDTO();
				np.setNoun(noun);
			}
		}
		
	}

	@Override
	public String toString() {
		return getName();
	}

	@Override
	public List<DomainStatementDTO> getDomainStatementDTOList() {
		List<? extends DomainStatement> l = super.getStatementList();
		List<DomainStatementDTO> result = new ArrayList<DomainStatementDTO>();
		for(DomainStatement domStat : l) {
			result.add((DomainStatementDTO)domStat);
		}
		return result;
	}

	@Override
	public NotionsPackageDTO getParent() {
		for (Edge e : ((Notion)this).getPackageContainsNotionIncidences(EdgeDirection.IN)) {
			return (NotionsPackageDTO)e.getAlpha(); //TODO: not sure if it is correct
		}
		return null;
		
//		List<? extends DomainElementsPackage> pl = super.getPackageList();//TODO: returns null
//		NotionsPackageDTO parent = null;
//		for(DomainElementsPackage pack : pl) {
//			parent = (NotionsPackageDTO)pack;
//			break;
//		}
//		return parent;
	}

	@Override
	public void setParent(NotionsPackageDTO pack) {
		if(getParent() != null) {
			getParent().removeChildNotionDTO(this);
		}
		super.addPackage((NotionsPackage)pack);
		
	}

	@Override
	public String getSpecificationPath() {
		return getParent().getSpecificationPath();
	}

	@Override
	public String getDescription() {
		for (ElementRepresentation er : getRepresentationList()) {
			if (er instanceof DomainElementRepresentation) {
				
				List<? extends HyperlinkedSentence> sl = er.getSentenceList();
				StringBuilder sb = new StringBuilder();

				for (HyperlinkedSentence hls : sl) {
					if (hls instanceof NaturalLanguageHypertextSentence) {
						sb.append(hls.getSentenceText()).append("\n");
					}
				}

				return sb.toString();
			}
		}
		return "";
	}

	@Override
	public void setDescription(String description) {
		BusinessLayerFacade facade = (BusinessLayerFacade) getGraph();

		StringTokenizer tokenizer = new StringTokenizer(description, "\n");

		// get existing sentence list object
		for (ElementRepresentation er : getRepresentationList()) {
			if (er instanceof DomainElementRepresentation) {
				er.delete();
				break;
			}
		}
		DomainElementRepresentationDTO der 
			= facade.createDomainElementRepresentationDTO();

		addRepresentation((DomainElementRepresentation)der);

		while (tokenizer.hasMoreTokens()) {
			String currentSentenceText = tokenizer.nextToken();
			NaturalLanguageHypertextSentenceDTO currentSentence = facade
					.createNaturalLanguageHypertextSentenceDTO();
			currentSentence.setSentenceText(currentSentenceText);
			der.addSentence(currentSentence);
		}
	}

	@Override
	public List<DomainElementRelationshipDTO> getDomainElementRelationshipDTOList() {
		List<DomainElementRelationshipDTO> result = new ArrayList<DomainElementRelationshipDTO>();
		for(DomainElementRelationship der : getToSourceList()) {
			result.add((DomainElementRelationshipDTO)der);
		}
		for(DomainElementRelationship der : getToTargetList()) if (!result.contains(der)) {
			result.add((DomainElementRelationshipDTO)der);
		}
		return result;
	}

	@Override
	public DomainElementRelationshipDTO addRelatedActor(ActorDTO actor) {
		if(actor == null) {
			return null;
		}
		BusinessLayerFacade facade = (BusinessLayerFacade) getGraph();
		DomainElementRelationshipDTO rel = facade.createDomainElementRelationshipDTO();
		rel.setSource(this);
		rel.setTarget(actor);
		rel.setSourceMultiplicity(DomainElementRelationshipDTO.DEFAULT_MULTIPLICITY);
		rel.setTargetMultiplicity(DomainElementRelationshipDTO.DEFAULT_MULTIPLICITY);
		return rel;
	}
	
	@Override
	public DomainElementRelationshipDTO addRelatedNotion(NotionDTO otherNotion) {
		if(otherNotion == null) {
			return null;
		}
		BusinessLayerFacade facade = (BusinessLayerFacade) getGraph();
		DomainElementRelationshipDTO rel = facade.createDomainElementRelationshipDTO();
		rel.setSource(this);
		rel.setTarget(otherNotion);
		rel.setSourceMultiplicity(DomainElementRelationshipDTO.DEFAULT_MULTIPLICITY);
		rel.setTargetMultiplicity(DomainElementRelationshipDTO.DEFAULT_MULTIPLICITY);
		return rel;
	}
	
	@Override
	public DomainElementRelationshipDTO addRelatedSystemElement(SystemElementDTO systemElement) {
		if(systemElement == null) {
			return null;
		}
		BusinessLayerFacade facade = (BusinessLayerFacade) getGraph();
		DomainElementRelationshipDTO rel = facade.createDomainElementRelationshipDTO();
		rel.setSource(this);
		rel.setTarget(systemElement);
		rel.setSourceMultiplicity(DomainElementRelationshipDTO.DEFAULT_MULTIPLICITY);
		rel.setTargetMultiplicity(DomainElementRelationshipDTO.DEFAULT_MULTIPLICITY);
		return rel;
	}

	@Override
	public List<NotionSpecialisationDTO> getNotionSpecialisationDTOList() {
		List<NotionSpecialisationDTO> result = new ArrayList<NotionSpecialisationDTO>();
		for(NotionSpecialisation ns : getGeneralList()) {
			result.add((NotionSpecialisationDTO)ns);
		}
		for(NotionSpecialisation ns : getSpecialList()) {
			result.add((NotionSpecialisationDTO)ns);
		}
		return result;
	}

	@Override
	public void addGeneralNotion(NotionDTO notion) {
		if(notion == null) {
			return;
		}
		BusinessLayerFacade facade = (BusinessLayerFacade) getGraph();
		NotionSpecialisationDTO nspecial = facade.createNotionSpecialisationDTO();
		nspecial.setSpecialisedNotion(this);
		nspecial.setGeneralNotion(notion);
		return;
	}

	@Override
	public void addSpecialisedNotion(NotionDTO notion) {
		if(notion == null) {
			return;
		}
		BusinessLayerFacade facade = (BusinessLayerFacade) getGraph();
		NotionSpecialisationDTO nspecial = facade.createNotionSpecialisationDTO();
		nspecial.setSpecialisedNotion(notion);
		nspecial.setGeneralNotion(this);
		return;
	}

	@Override
	public boolean isNameUnique(String name) {
		BusinessLayerFacade facade = (BusinessLayerFacade)getGraph();
		return facade.isNotionNameValid(name);
	}

	@Override
	public void addDomainStatementDTO(DomainStatementDTO statement) {
		addStatement((DomainStatement)statement);
		PhraseDTO phrase = statement.getPhraseDTO();
		
		if (phrase instanceof ComplexVerbPhraseDTO) {
			ComplexVerbPhraseDTO complexPh 
				= (ComplexVerbPhraseDTO) phrase;
			BusinessLayerFacade facade 
				= (BusinessLayerFacade) getGraph();
			NotionDTO notion 
				= facade.getNotionDTO(complexPh.getSimpleVerbPhrase().getObject().getNoun());
			if(notion != null && null == NotionTypesEnum.getNotionTypeByTag(notion.getType())) {
				for (DomainElementRelationshipDTO rel : getDomainElementRelationshipDTOList()){
					if (rel.getTarget().equals(notion)){
						return;
					}
				}
				NotionTypesEnum t = NotionTypesEnum.getNotionTypeByTag(getType());
				if (null==t)
					addRelatedNotion(notion);
				else if (Arrays.asList(NotionTypesEnum.viewValues()).contains(t))
					addRelatedNotion(notion).setDirected(true);
			}
			
		}
				
	}
	
	public DomainStatementDTO getDomainStatementDTO(PhraseDTO phrase){
		for (DomainStatementDTO ds : getDomainStatementDTOList()){
			if (ds.getPhraseDTO().equals(phrase)){
				return ds;
			}
		}
		return null;
	}
	
	@Override
	public void delete() {
		//delete owned domain statements
		List<DomainStatementDTO> stmts = getDomainStatementDTOList();
		for (int i = 0; i < stmts.size(); i++) {
			stmts.get(i).delete();
		}
		//delete relationships that this notion participates in
		List<DomainElementRelationshipDTO> relationships = getDomainElementRelationshipDTOList();
		for (int i = 0; i < relationships.size(); i++) {
			relationships.get(i).delete();
		}
		List<NotionSpecialisationDTO> specialisations = getNotionSpecialisationDTOList();
		for (int i = 0; i < specialisations.size(); i++) {
			specialisations.get(i).delete();
		}
		if (this.getNamePhrase() != null)
			this.getNamePhrase().deleteRecursively();
		for (Notion not:getNotionAttributeList()) if (not.getParentNotionList().size()>1) removeNotionAttribute(not);
		for (Stereotype s:getStereotypeList())
			s.delete();
		super.delete();
	}
	
	@Override
	public boolean checkIfRelated(Object domainElement) {
		for(DomainElementRelationshipDTO rel : getDomainElementRelationshipDTOList()) {
			if(rel.getSource().equals(domainElement) || rel.getTarget().equals(domainElement)) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public List<TraceabilityLinkDTO> getTraceabilityLinkDTOList() {
		List<TraceabilityLinkDTO> list = new ArrayList<TraceabilityLinkDTO>();
		for(IsAllocatedTo rel : super.getAllocationToUMLList()) {
			list.add((TraceabilityLinkDTO)rel);
		}
		return list;
	}

	@Override
	public void rename(NounPhraseDTO phrase, List<NounLinkDTO> links) {
		
		String basicForm = getNamePhrase().getNounText();
		int i = 0;
		for (NounLink nounLink : ((Noun)getNamePhrase().getNoun()).getNounLinkList()) {
			if (nounLink.getFirstNounLinkLinksToTarget() != null) {
				if(nounLink.getValue().equalsIgnoreCase(basicForm)) { //base form
					nounLink.setValue(phrase.getNoun().getName());
				}
				else { //non-base form
					nounLink.setValue(links.get(i).getNewValue());
					i++;
				}
			}
		}
		
		getNamePhrase().getNoun().setName(phrase.getNoun().getName());
		if (phrase.getNoun().getSynonymUid()!=0) getNamePhrase().getNoun().setSynonymUid(phrase.getNoun().getSynonymUid());
		else if (SCProjectHelper.getSenseAutoAddAndAssigmentFlag()){
			try {
				getNamePhrase().getNoun().autoAddAndAssignSense();
			} catch (NullPointerException e){
				e.printStackTrace();
			}
		}
		((BusinessLayerFacade)getGraph()).cleanNouns(getNamePhrase().getNoun());
		
		//TODO - hyperlinks
		
		phrase.deleteRecursively();
	}

	@Override
	public List<NounLinkDTO> getNonBasicNounLinksValues() {
		String basicForm = getNamePhrase().getNounText();
		List<NounLinkDTO> result = new ArrayList<NounLinkDTO>();
		
		for (NounLink nounLink : ((Noun)getNamePhrase().getNoun()).getNounLinkList()) {
			if (nounLink.getFirstNounLinkLinksToTarget() != null) {
				if (!nounLink.getValue().equalsIgnoreCase(basicForm)) {
					result.add((NounLinkDTO)nounLink);
				}
			}
		}
		
		//TODO: hyperlinks in descriptions
		
		return result;
	}

	@Override
	public PrimitiveDataTypeDTO getDataType() {
		List<? extends AttributeDataType> list = super.getDataTypeList();
		if (list.size() > 0) {
		    return ((PrimitiveDataTypeDTO) list.get(list.size()-1));
		}
		return null;
	}

	@Override
	public void setDataType(PrimitiveDataTypeDTO dataType) {
		List<? extends AttributeDataType> list = super.getDataTypeList();
		AttributeDataType currentDataType = null;
		if (list.size() > 0) {
		    currentDataType = list.get(list.size()-1);
		}
		if(currentDataType != null) {
			removeDataType(currentDataType);
		}
		if (null!=dataType) addDataType((AttributeDataType)dataType);
		
	}

	@Override
	public void addNotionDTOAttribute(NotionDTO attribute) {
		boolean alreadyAnAttribute = false;
		for(Notion notion : getNotionAttributeList()) {
			if(notion.equals(attribute)) {
				alreadyAnAttribute = true;
				break;
			}
		}
		if(!alreadyAnAttribute) {
			super.addNotionAttribute((Notion)attribute);
		}
	}

	@Override
	public List<NotionDTO> getNotionDTOAttributeList() {
		List<NotionDTO> result = new ArrayList<NotionDTO>();
		for(Notion notion : getNotionAttributeList()) {
			result.add((NotionDTO)notion);
		}
		return result;
	}

	@Override
	public void removeNotionDTOAttribute(NotionDTO attribute) {
		super.removeNotionAttribute((Notion)attribute);
		
	}

	@Override
	public List<NotionDTO> getNotionAttributeParents() {
		List<NotionDTO> list = new ArrayList<NotionDTO>();
		for(Notion not:super.getParentNotionList()) list.add((NotionDTO) not);
		return list;
	}

	@Override
	public String getType() {
		for (Stereotype st:getStereotypeList())
			if (Arrays.asList(NotionTypesEnum.tags()).contains(st.getName()))
				return st.getName();
		return "";
	}
	
	@Override
	public String getExtendedDataType() {
		for (Stereotype st:getStereotypeList()) if (Arrays.asList(AttributeDataTypesEnum.tags()).contains(st.getName()))
			return st.getName();
		return "";
	}

	@Override
	public List<String> getCRUDs() {
		List<String> result = new ArrayList<String>();
		for (DomainStatementDTO ds:getDomainStatementDTOList()){
			if (!ds.getCRUD().isEmpty() && !result.contains(ds.getCRUD())) result.add(ds.getCRUD());
		}
		return result;
	}

	@Override
	public boolean testAttribute(Object target, String name, String value) {
		if (!(target instanceof NotionDTO)) return false;
		if ("type".equals(name)){
			if ("dataviews".equals(value) && (NotionTypesEnum.Simple_View.tag().equals(((NotionDTO) target).getType()) || NotionTypesEnum.List_View.tag().equals(((NotionDTO) target).getType()) || NotionTypesEnum.Tree_View.tag().equals(((NotionDTO) target).getType())))
				return true;
			if ("concept".equals(value) && ((NotionDTO) target).getType().isEmpty())
				return true;
		}
		return false;
	}

	@Override
	public List<NotionDTO> getPointedNotionDTOAttributeList() {
		List<NotionDTO> result = new ArrayList<NotionDTO>();
		for (DomainElementRelationship der:getToTargetList())
			if (((DomainElementRelationshipDTO) der).getTarget() instanceof NotionDTO && NotionTypesEnum.Attribute.tag().equals(((NotionDTO) ((DomainElementRelationshipDTO) der).getTarget()).getType()))
				result.add((NotionDTO) ((DomainElementRelationshipDTO) der).getTarget());
		return result;
	}

	@Override
	public List<NotionDTO> getPointingParentNotionList() {
		List<NotionDTO> result = new ArrayList<NotionDTO>();
		if (!NotionTypesEnum.Attribute.tag().equals(getType()))
			return result;
		for (DomainElementRelationship der:getToSourceList())
			if (((DomainElementRelationshipDTO) der).getSource() instanceof NotionDTO)
				result.add((NotionDTO) ((DomainElementRelationshipDTO) der).getSource());
		return result;
	}

	@Override
	public boolean isDomainNotion() {
		if (getType().isEmpty() || Arrays.asList(NotionTypesEnum.domainTags()).contains(getType()))
			return true;
		return false;
	}

	@Override
	public NotionDTO getMainConcept() {
		if (!NotionTypesEnum.Simple_View.tag().equals(getType()) && !NotionTypesEnum.List_View.tag().equals(getType()) && !NotionTypesEnum.Tree_View.tag().equals(getType()))
			return null;
		for (DomainElementRelationship der:getToTargetList())
			if (der.isDirected() && ((DomainElementRelationshipDTO) der).getTarget() instanceof NotionDTO && ((NotionDTO) ((DomainElementRelationshipDTO) der).getTarget()).getType().isEmpty())
				return (NotionDTO) ((DomainElementRelationshipDTO) der).getTarget();
		return null;
	}

}