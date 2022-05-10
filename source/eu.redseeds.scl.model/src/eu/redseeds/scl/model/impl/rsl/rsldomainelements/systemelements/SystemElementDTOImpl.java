package eu.redseeds.scl.model.impl.rsl.rsldomainelements.systemelements;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import de.uni_koblenz.jgralab.Edge;
import de.uni_koblenz.jgralab.EdgeDirection;
import de.uni_koblenz.jgralab.Graph;
import eu.redseeds.common.SCProjectHelper;
import eu.redseeds.scl.impl.SCLGraphImpl;
import eu.redseeds.scl.impl.rsl.rsldomainelements.systemelements.SystemElementImpl;
import eu.redseeds.scl.model.BusinessLayerFacade;
import eu.redseeds.scl.model.rsl.rsldomainelements.actors.ActorDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.domainelements.DomainElementRelationshipDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.domainelements.DomainElementRepresentationDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.NounLinkDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.NounPhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.systemelements.SystemElementDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.systemelements.SystemElementsPackageDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.terms.NounDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.representationsentences.NaturalLanguageHypertextSentenceDTO;
import eu.redseeds.scl.rsl.rsldomainelements.domainelementrepresentations.DomainElementRepresentation;
import eu.redseeds.scl.rsl.rsldomainelements.domainelements.DomainElementRelationship;
import eu.redseeds.scl.rsl.rsldomainelements.phrases.NounLink;
import eu.redseeds.scl.rsl.rsldomainelements.phrases.NounPhrase;
import eu.redseeds.scl.rsl.rsldomainelements.systemelements.SystemElement;
import eu.redseeds.scl.rsl.rsldomainelements.systemelements.SystemElementsPackage;
import eu.redseeds.scl.rsl.rsldomainelements.terms.Noun;
import eu.redseeds.scl.rsl.rslkernel.elements.ElementRepresentation;
import eu.redseeds.scl.rsl.rslkernel.elements.HyperlinkedSentence;
import eu.redseeds.scl.rsl.rslrequirementrepresentationsentences.representationsentences.NaturalLanguageHypertextSentence;
import eu.redseeds.scl.sclkernel.SCLElement;


public class SystemElementDTOImpl extends SystemElementImpl implements SystemElementDTO {

	public SystemElementDTOImpl(int arg0, Graph arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
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
	
	protected NounPhraseDTO getNamePhrase() {
		List<? extends SCLElement> list = super.getNameList();
		if (list.size() > 0) {
		    return ((NounPhraseDTO) list.get(list.size()-1));
		}
		return null;
	}
	
	public NounPhraseDTO getNounPhrase(){
		List<? extends SCLElement> list = super.getNameList();
		if (list.size() > 0) {
		    return ((NounPhraseDTO) list.get(list.size()-1));
		}
		return null;
	}

	@Override
	public void setName(String name) {
		NounPhraseDTO pp;
		NounPhraseDTO phrase 
			= (NounPhraseDTO)((SCLGraphImpl)super.getGraph()).createNounPhrase();
		NounDTO noun 
		= ((BusinessLayerFacade)getGraph()).createNounDTO(name);
		phrase.setNoun(noun);
		if (null!=(pp=this.getNamePhrase())){
			removeName((NounPhrase) pp);
			if (pp.isUnused()) pp.deleteRecursively();
		}
		addName((NounPhrase)phrase);
	}
	
	@Override
	public void setNamePhrase(NounPhraseDTO phrase) {
		addName((NounPhrase)phrase);
	}

	@Override
	public String toString() {
		return getName();             
	}

	@Override
	public SystemElementsPackageDTO getParent() {
		for (Edge e : ((SystemElement)this).getPackageContainsSystemElementIncidences(EdgeDirection.IN)) {
			return (SystemElementsPackageDTO)e.getAlpha(); //TODO: not sure if it is correct
		}
		return null;
		
//		List<? extends DomainElementsPackage> pl = super.getPackageList();//TODO: returns null
//		SystemElementsPackageDTO parent = null;
//		for(DomainElementsPackage pack : pl) {
//			parent = (SystemElementsPackageDTO)pack;
//			break;
//		}
//		return parent;
	}

	@Override
	public void setParent(SystemElementsPackageDTO pack) {
		if(getParent() != null) {
			getParent().removeChildSystemElementDTO(this);
		}
		super.addPackage((SystemElementsPackage)pack);
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
	public DomainElementRelationshipDTO addRelatedNotion(NotionDTO notion) {
		if(notion == null) {
			return null;
		}
		BusinessLayerFacade facade = (BusinessLayerFacade) getGraph();
		DomainElementRelationshipDTO rel = facade.createDomainElementRelationshipDTO();
		rel.setSource(this);
		rel.setTarget(notion);
		rel.setSourceMultiplicity(DomainElementRelationshipDTO.DEFAULT_MULTIPLICITY);
		rel.setTargetMultiplicity(DomainElementRelationshipDTO.DEFAULT_MULTIPLICITY);
		return rel;
	}
	
	@Override
	public DomainElementRelationshipDTO addRelatedSystemElement(SystemElementDTO otherSystemElement) {
		if(otherSystemElement == null) {
			return null;
		}
		BusinessLayerFacade facade = (BusinessLayerFacade) getGraph();
		DomainElementRelationshipDTO rel = facade.createDomainElementRelationshipDTO();
		rel.setSource(this);
		rel.setTarget(otherSystemElement);
		rel.setSourceMultiplicity(DomainElementRelationshipDTO.DEFAULT_MULTIPLICITY);
		rel.setTargetMultiplicity(DomainElementRelationshipDTO.DEFAULT_MULTIPLICITY);
		return rel;
	}

	@Override
	public boolean isNameUnique(String name) {
		BusinessLayerFacade facade = (BusinessLayerFacade)getGraph();
		return facade.isSystemElementNameValid(name);
	}
	
	@Override
	public void delete() {
		//delete relationships that this notion participates in
		List<DomainElementRelationshipDTO> relationships = getDomainElementRelationshipDTOList();
		for (int i = 0; i < relationships.size(); i++) {
			relationships.get(i).delete();
		}
		if (this.getNounPhrase() != null)
			this.getNounPhrase().deleteRecursively();
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
	public void rename(NounPhraseDTO phrase) {
		
		for (NounLink nounLink : ((Noun)getNamePhrase().getNoun()).getNounLinkList()) {
			if (nounLink.getFirstNounLinkLinksToTarget() != null) {
				nounLink.setValue(phrase.getNoun().getName());
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
		
		return result;
	}

}