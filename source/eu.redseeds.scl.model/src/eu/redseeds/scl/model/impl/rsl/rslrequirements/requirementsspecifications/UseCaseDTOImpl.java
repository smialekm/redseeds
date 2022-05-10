package eu.redseeds.scl.model.impl.rsl.rslrequirements.requirementsspecifications;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.eclipse.core.resources.IFile;

import de.uni_koblenz.jgralab.Graph;
import eu.redseeds.scl.impl.rsl.rslrequirements.requirementsspecifications.RSLUseCaseImpl;
import eu.redseeds.scl.model.BusinessLayerFacade;
import eu.redseeds.scl.model.impl.rsl.rslrequirementrepresentations.descriptiverequirementrepresentations.ConstrainedLanguageScenarioDTOImpl;
import eu.redseeds.scl.model.rsl.rsldomainelements.actors.ActorDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionsPackageDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.systemelements.SystemElementDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentations.descriptiverequirementrepresentations.ConstrainedLanguageScenarioDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentations.descriptiverequirementrepresentations.SentenceListDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.constrainedlanguagesentences.ConstrainedLanguageSentenceDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.representationsentences.NaturalLanguageHypertextSentenceDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.svosentences.SVOSentenceDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsrelationships.NonInvocationRelationshipDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsrelationships.RequirementVocabularyRelationshipDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementsPackageDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.UseCaseDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.usecaserelationships.InvocationRelationshipDTO;
import eu.redseeds.scl.model.traceability.TraceabilityLinkDTO;
import eu.redseeds.scl.model.traceability.TraceableObjectDTO;
import eu.redseeds.scl.rsl.rsldomainelements.notions.NotionsPackage;
import eu.redseeds.scl.rsl.rslkernel.elements.ElementRepresentation;
import eu.redseeds.scl.rsl.rslkernel.elements.HyperlinkedSentence;
import eu.redseeds.scl.rsl.rslrequirementrepresentations.descriptiverequirementrepresentations.SentenceList;
import eu.redseeds.scl.rsl.rslrequirementrepresentationsentences.representationsentences.NaturalLanguageHypertextSentence;
import eu.redseeds.scl.rsl.rslrequirements.requirementrelationships.NonInvocationRelationship;
import eu.redseeds.scl.rsl.rslrequirements.requirementrelationships.RequirementVocabularyRelationship;
import eu.redseeds.scl.rsl.rslrequirements.requirementsspecifications.RSLUseCase;
import eu.redseeds.scl.rsl.rslrequirements.requirementsspecifications.RequirementsPackage;
import eu.redseeds.scl.rsl.rslrequirements.usecaserelationships.InvocationRelationship;
import eu.redseeds.scl.sclkernel.IsAllocatedTo;
import eu.redseeds.scl.sclkernel.SCLElement;
import eu.redseeds.scl.sclkernel.Stereotype;
import eu.redseeds.scl.uml.classes.dependencies.Dependency;
import eu.redseeds.scl.uml.usecases.UseCase;

public class UseCaseDTOImpl extends RSLUseCaseImpl implements UseCaseDTO, TraceableObjectDTO {

//	private boolean valid = true;
	// private static String DEFAULT_NAME = "UC";

//	@Override
//	public boolean isValid() {
//		return valid;
//	}
//
//	@Override
//	public void setValid(boolean valid) {
//		this.valid = valid;
//	}

	public UseCaseDTOImpl(int arg0, Graph arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub

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
				// remove last newline
				if (sb.length() > 0) // if prevents from IndexOutOfBounds
										// Exception
					sb.deleteCharAt(sb.length() - 1);

				return sb.toString();
			}
		}

		return "";
	}

	@Override
	public Map<UseCaseDTO, NonInvocationRelationshipDTO> getRelatedUseCases() {
		Map<UseCaseDTO, NonInvocationRelationshipDTO> ret = new HashMap<UseCaseDTO, NonInvocationRelationshipDTO>();

		List<? extends NonInvocationRelationship> rels = getToList();

		for (NonInvocationRelationship nir : rels) {
			for (SCLElement scel : nir.getSourceList()) {
				if (scel instanceof UseCaseDTO) {
					ret.put((UseCaseDTO)scel, (NonInvocationRelationshipDTO) nir);
				}
			}
		}

		return ret;
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
	public String getName() {
		for (SCLElement s : this.getNameList()) {
			if (s instanceof NaturalLanguageHypertextSentence) {
				return ((NaturalLanguageHypertextSentence) s).getSentenceText();
			}
		}

		return null;
	}

	@Override
	public RequirementsPackageDTO getParent() {

		List<? extends RequirementsPackage> pl = super.getPackageList();
		RequirementsPackageDTO parent = null;
		for (RequirementsPackage pack : pl) {
			parent = (RequirementsPackageDTO) pack;
			break;
		}
		return parent;
	}

	@Override
	public List<RequirementDTO> getRelatedRequirements() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setName(String name) {
		for (SCLElement element : getNameList()) {
			if (element instanceof NaturalLanguageHypertextSentence) {
				((NaturalLanguageHypertextSentence) element)
						.setSentenceText(name);
				return;
			}
		}

		// if there was no NaturalLan... create one and add
		NaturalLanguageHypertextSentence sentence = (NaturalLanguageHypertextSentence) ((BusinessLayerFacade) getGraph())
				.createNaturalLanguageHypertextSentenceDTO();
		sentence.setSentenceText(name);
		addName(sentence);
	}

	@Override
	public String toString() {
		return this.getName();
	}

	@Override
	public List<ActorDTO> getActors() {
		
		java.util.Set<ActorDTO> res = new HashSet<ActorDTO>();
		BusinessLayerFacade facade = (BusinessLayerFacade)this.getGraph();
		
		for(ConstrainedLanguageScenarioDTO scen : getConstrainedLanguageScenarioDTOList()) {
			for(ConstrainedLanguageSentenceDTO sent : scen.getScenarioSentenceList()) {
				if(sent instanceof SVOSentenceDTO) {
					ActorDTO actor = facade.getActorDTO(((SVOSentenceDTO)sent).getSubject());
					if(actor != null) {
						res.add(actor);
					}
				}
			}
		}
		return new ArrayList<ActorDTO>(res);
	}
	
	@Override
	public List<SystemElementDTO> getSystemElements() {
		
		java.util.Set<SystemElementDTO> res = new HashSet<SystemElementDTO>();
		BusinessLayerFacade facade = (BusinessLayerFacade)this.getGraph();
		
		for(ConstrainedLanguageScenarioDTO scen : getConstrainedLanguageScenarioDTOList()) {
			for(ConstrainedLanguageSentenceDTO sent : scen.getScenarioSentenceList()) {
				if(sent instanceof SVOSentenceDTO) {
					SystemElementDTO sysel = facade.getSystemElementDTO(((SVOSentenceDTO)sent).getSubject());
					if(sysel != null) {
						res.add(sysel);
					}
				}
			}
		}
		return new ArrayList<SystemElementDTO>(res);
	}

	@Override
	public List<RequirementDTO> getRequirments() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addConstrainedLanguageScenario(
			ConstrainedLanguageScenarioDTO scenario) {
		super.addRepresentation((ConstrainedLanguageScenarioDTOImpl) scenario);
	}

	@Override
	public List<ConstrainedLanguageScenarioDTO> getConstrainedLanguageScenarioDTOList() {
		List<? extends ElementRepresentation> l = super.getRepresentationList();
		List<ConstrainedLanguageScenarioDTO> result = new ArrayList<ConstrainedLanguageScenarioDTO>();
		for (ElementRepresentation s : l) {
			if (s instanceof ConstrainedLanguageScenarioDTO) {
				result.add((ConstrainedLanguageScenarioDTO) s);
			}
		}
		return result;
	}

	public void setDescription(String text) {
		BusinessLayerFacade facade = (BusinessLayerFacade) getGraph();

		StringTokenizer tokenizer = new StringTokenizer(text, "\n");

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
		super.addPackage((RequirementsPackage) parent);
		getParent().removeChildRequirementDTO(this);
	}

	@Override
	public boolean isNameUnique(String name) {
		BusinessLayerFacade facade = (BusinessLayerFacade)getGraph();
		return facade.isUseCaseNameValid(name);
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
		for (IsAllocatedTo alloc: getAllocationToUMLList()) if (!alloc.isIsGenerated() && !alloc.getAllocationTargetList().isEmpty() && alloc.getAllocationTargetList().get(0) instanceof UseCase){
			boolean find=false;
			for (Stereotype ster:alloc.getStereotypeList()) if(ster.getName().equals("DuplicatedUseCaseStructureForUseRelationship")){
					find=true;
					break;
			}
			if (!find) continue;
			UseCase uc = (UseCase) alloc.getAllocationTargetList().get(0);
			for(Dependency dep:uc.getSupplierDependencyList()) dep.delete();
			uc.delete();
			for (IsAllocatedTo a:getAllocationToUMLList()){
				for (Stereotype s:a.getStereotypeList())
					s.delete();
				a.delete();
			}	
			break;
		}
		// delete all outgoing NonInvocationRelationships of this UseCase
		List<NonInvocationRelationshipDTO> relationships = getNonInvocationRelationshipDTOFromList();
		for (int i = 0; i < relationships.size(); i++) {
			relationships.get(i).deleteRelationship();
		}
		// delete all incoming NonInvocationRelationships of this UseCase
		relationships = getNonInvocationRelationshipDTOToList();
		for (int i = 0; i < relationships.size(); i++) {
			relationships.get(i).deleteRelationship();
		}
		
		// delete all outgoing InvocationRelationships of this UseCase
		List<InvocationRelationshipDTO> relationships2 = getInvocationRelationshipFromList();
		for (int i = 0; i < relationships2.size(); i++) {
			UseCaseDTO uc = relationships2.get(i).getTarget();
			if (null!=uc && uc.getConstrainedLanguageScenarioDTOList().isEmpty() && null==uc.getParent() && ((RSLUseCase) uc).getInvokedList().size()==1) uc.delete();
			else relationships2.get(i).deleteRelationship();
		}
		// delete all incoming InvocationRelationships of this UseCase
		relationships2 = getInvocationRelationshipToList();
		for (int i = 0; i < relationships2.size(); i++) {
			relationships2.get(i).deleteRelationship();
		}
		
		//delete all scenarios and theirs dependencies (sentences, phrases etc.)
		while(this.getConstrainedLanguageScenarioDTOList().size()>0){
			this.getConstrainedLanguageScenarioDTOList().get(0).delete();
		}
		
		super.delete();
	}

	@Override
	public InvocationRelationshipDTO addInvokedUseCase(UseCaseDTO uc) {
		
		if(uc == null)
			return null;
		
		BusinessLayerFacade facade = (BusinessLayerFacade) getGraph();
		InvocationRelationshipDTO rel = facade.createInvocationRelationshipDTO();
		rel.setSource(this);
		rel.setTarget(uc);

		return rel;
	}

	@Override
	public List<InvocationRelationshipDTO> getInvocationRelationshipFromList() {
		List<InvocationRelationshipDTO> list = new ArrayList<InvocationRelationshipDTO>();
		for(InvocationRelationship rel : super.getInvokeList()) {
			list.add((InvocationRelationshipDTO)rel);
		}
		return list;
	}

	@Override
	public List<InvocationRelationshipDTO> getInvocationRelationshipToList() {
		List<InvocationRelationshipDTO> list = new ArrayList<InvocationRelationshipDTO>();
		for(InvocationRelationship rel : super.getInvokedList()) {
			list.add((InvocationRelationshipDTO)rel);
		}
		return list;
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

	@Override
	public List<ActorDTO> getDependentActors() {
		List<ActorDTO> result = new ArrayList<ActorDTO>();
		UseCase uc = null;
		loop:
		for(IsAllocatedTo alloc:getAllocationToUMLList())
			for (Stereotype ster : alloc.getStereotypeList())
				if (ster.getName().equals("DuplicatedUseCaseStructureForUseRelationship")){
					uc = (UseCase) alloc.getAllocationTargetList().get(0);
					break loop;
				}
		for (Dependency dep:((BusinessLayerFacade) getGraph()).getDependencyVertices())
			if (!dep.getSupplierList().isEmpty() && !dep.getClientList().isEmpty() && dep.getSupplierList().get(0).getId()==uc.getId()){
				loop:
				for (IsAllocatedTo alloc:dep.getClientList().get(0).getAllocationToRSLList()){
					for (Stereotype ster : alloc.getStereotypeList())
						if (ster.getName().equals("DuplicatedUseCaseStructureForUseRelationship")){
							result.add((ActorDTO) alloc.getAllocationSourceList().get(0));
							break loop;
						}
				}
			}
		return result;
	}

	@Override
	public boolean testAttribute(Object target, String name, String value) {
		if (!(target instanceof UseCaseDTO))
			return false;
		if ("NotionDiagramExists".equals(name)){
			for(NotionsPackage elem:((BusinessLayerFacade) getGraph()).getNotionsPackageVertices()){
				ArrayList<IFile> diagrams = ((NotionsPackageDTO) elem).getNotionsDiagrams();
				if(!diagrams.isEmpty()){
					String curr = getName()+".notiondiagram_diagram";
					for(IFile obj : diagrams)
						if(curr.equals(obj.getName()))
							return "true".equals(value)?true:false;
				}
				
			}
			return "true".equals(value)?false:true;
		}
		return false;
	}


}