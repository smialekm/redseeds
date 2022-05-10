package eu.redseeds.scl.model.impl.rsl.rslrequirements.requirementsspecifications;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import de.uni_koblenz.jgralab.Graph;
import eu.redseeds.scl.model.BusinessLayerFacade;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentations.descriptiverequirementrepresentations.SentenceListDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.representationsentences.NaturalLanguageHypertextSentenceDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsrelationships.NonInvocationRelationshipDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsrelationships.RequirementVocabularyRelationshipDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementsPackageDTO;
import eu.redseeds.scl.model.traceability.TraceabilityLinkDTO;
import eu.redseeds.scl.model.traceability.TraceableObjectDTO;
import eu.redseeds.scl.rsl.rslkernel.elements.ElementRepresentation;
import eu.redseeds.scl.rsl.rslkernel.elements.HyperlinkedSentence;
import eu.redseeds.scl.rsl.rslrequirementrepresentations.descriptiverequirementrepresentations.SentenceList;
import eu.redseeds.scl.rsl.rslrequirementrepresentationsentences.representationsentences.NaturalLanguageHypertextSentence;
import eu.redseeds.scl.rsl.rslrequirements.requirementrelationships.NonInvocationRelationship;
import eu.redseeds.scl.rsl.rslrequirements.requirementrelationships.RequirementVocabularyRelationship;
import eu.redseeds.scl.rsl.rslrequirements.requirementsspecifications.Requirement;
import eu.redseeds.scl.rsl.rslrequirements.requirementsspecifications.RequirementsPackage;
import eu.redseeds.scl.sclkernel.IsAllocatedTo;
import eu.redseeds.scl.sclkernel.SCLElement;
import eu.redseeds.scl.impl.rsl.rslrequirements.requirementsspecifications.RequirementImpl;


public class RequirementDTOImpl extends RequirementImpl implements RequirementDTO, TraceableObjectDTO {

	
	//private static String DEFAULT_NAME = "Req";
	
	public RequirementDTOImpl(int arg0, Graph arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public NonInvocationRelationshipDTO addRelatedRequirement(RequirementDTO req, int relType, boolean isTarget) {
		
		if(req == null) {
			return null;
		}
		BusinessLayerFacade facade = (BusinessLayerFacade) getGraph();
		NonInvocationRelationshipDTO rel = facade.createNonInvocationRelationship(relType);
		if (isTarget) {
			rel.setSource(this);
			rel.setTarget(req);
		}
		else {
			rel.setSource(req);
			rel.setTarget(this);
		}

		return rel;
		
	}

	@Override
	public RequirementsPackageDTO getParent() {
		
		List<? extends RequirementsPackage> pl = super.getPackageList();
		RequirementsPackageDTO parent = null;
		for(RequirementsPackage pack : pl) {
			parent = (RequirementsPackageDTO)pack;
			break;
		}
		return parent;
	}
	
	

	@Override
	public List<RequirementDTO> getRelatedRequirements() {
		
		List<RequirementDTO> result = new ArrayList<RequirementDTO>();
		
		List<? extends NonInvocationRelationship> toList = getToList();
		for(NonInvocationRelationship r : toList) {
			if (r.getSourceList() != null) {
				if (r.getSourceList().get(0) instanceof Requirement) {
					result.add((RequirementDTO)r.getSourceList().get(0));
				}
			}
		}
		
		List<? extends NonInvocationRelationship> fromList = getFromList();
		for(NonInvocationRelationship r : fromList) {
			if (r.getTargetList() != null) {
				if (r.getTargetList().get(0) instanceof Requirement) {
					result.add((RequirementDTO)r.getTargetList().get(0));
				}
			}
		}
		
		return result;
	}
	
	
	public String getName() {
		
		for (SCLElement s : this.getNameList()) {
			if (s instanceof NaturalLanguageHypertextSentence) {
				return ((NaturalLanguageHypertextSentence) s).getSentenceText();
			}
		}

		return "";
	}

	@Override
	public void setName(String name) {
		//super.setRequirementId(name);
		
		for (SCLElement element : getNameList()) {
			if (element instanceof NaturalLanguageHypertextSentence) {
				((NaturalLanguageHypertextSentence) element).setSentenceText(name);
				return;
			}
		}

		NaturalLanguageHypertextSentence sentence = (NaturalLanguageHypertextSentence) ((BusinessLayerFacade) getGraph()).createNaturalLanguageHypertextSentenceDTO();
		sentence.setSentenceText(name);
		addName(sentence);
	}

	@Override
	public String toString() {
		return getName();
	}

	@Override
	public String getDescription() {
		
		for (ElementRepresentation er : getRepresentationList()) {
			if (er instanceof SentenceList) {
				SentenceList sl = (SentenceList) er;
				StringBuilder sb = new StringBuilder();

				for (HyperlinkedSentence hls : sl.getSentenceList()) {
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
			if (er instanceof SentenceList) {
				er.delete();
				break;
			}
		}
		SentenceListDTO list = facade.createSentenceListDTO();
		addRepresentation((SentenceList) list);

		while (tokenizer.hasMoreTokens()) {
			String currentSentenceText = tokenizer.nextToken();
			NaturalLanguageHypertextSentenceDTO currentSentence = facade
					.createNaturalLanguageHypertextSentenceDTO();
			currentSentence.setSentenceText(currentSentenceText);
			list.addSentence(currentSentence);
		}
		
	}

	@Override
	public String getSpecificationPath() {
		return getParent().getSpecificationPath();
	}

	@Override
	public void setParent(RequirementsPackageDTO parent) {
		super.addPackage((RequirementsPackage)parent);
		getParent().removeChildRequirementDTO(this);
	}

	@Override
	public boolean isNameUnique(String name) {
		BusinessLayerFacade facade = (BusinessLayerFacade)getGraph();
		return facade.isRequirementNameValid(name);
	}

	@Override
	public List<NonInvocationRelationshipDTO> getNonInvocationRelationshipDTOFromList() {
		List<NonInvocationRelationshipDTO> list = new ArrayList<NonInvocationRelationshipDTO>();
		for(NonInvocationRelationship rel : getFromList()) {
			list.add((NonInvocationRelationshipDTO)rel);
		}
		return list;
	}

	@Override
	public List<NonInvocationRelationshipDTO> getNonInvocationRelationshipDTOToList() {
		List<NonInvocationRelationshipDTO> list = new ArrayList<NonInvocationRelationshipDTO>();
		for(NonInvocationRelationship rel : getToList()) {
			list.add((NonInvocationRelationshipDTO)rel);
		}
		return list;
	}
	
	@Override
	public void delete() {
		//delete relationships
		List<NonInvocationRelationshipDTO> relationships = getNonInvocationRelationshipDTOFromList();
		for (int i = 0; i < relationships.size(); i++) {
			relationships.get(i).deleteRelationship();
		}
		relationships = getNonInvocationRelationshipDTOToList();
		for (int i = 0; i < relationships.size(); i++) {
			relationships.get(i).deleteRelationship();
		}
		super.delete();
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
	public List<NotionDTO> getRelatedNotions() {
		List<NotionDTO> results = new ArrayList<NotionDTO>();
		for(RequirementVocabularyRelationship rvr :  getVocabularyList()) {
			results.add(((RequirementVocabularyRelationshipDTO)rvr).getNotion());
		}
		return results;
	}

	@Override
	public boolean checkIfRelated(NotionDTO notion) {
		for(NotionDTO relNotion : getRelatedNotions()) {
			if(notion.equals(relNotion)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public RequirementVocabularyRelationshipDTO addRelatedNotion(NotionDTO notion) {
		if(notion == null) {
			return null;
		}
		BusinessLayerFacade facade = (BusinessLayerFacade) getGraph();
		RequirementVocabularyRelationshipDTO rel 
			= facade.createRequirementVocabularyRelationshipDTO();
		rel.setRequirement(this);
		rel.setNotion(notion);
		return rel;
	}

//	@Override
//	public void removeNonInvocationRelationship(NonInvocationRelationshipDTO rel) {
//		if (rel == null)
//			return;
//		
//		rel.deleteRelationship();
//		
//	}


}