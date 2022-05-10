package eu.redseeds.scl.model.impl.rsl.rsldomainelements.notions;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

import de.uni_koblenz.jgralab.Graph;
import eu.redseeds.scl.impl.rsl.rsldomainelements.notions.DomainStatementImpl;
import eu.redseeds.scl.model.BusinessLayerFacade;
import eu.redseeds.scl.model.rsl.rsldomainelements.domainelements.DomainElementRepresentationDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.ActionTypesEnum;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.DomainStatementDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionTypesEnum;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.ComplexVerbPhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.NounPhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.PhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.SimpleVerbPhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.VerbPhraseDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.representationsentences.NaturalLanguageHypertextSentenceDTO;
import eu.redseeds.scl.model.traceability.TraceabilityLinkDTO;
import eu.redseeds.scl.model.traceability.TraceableObjectDTO;
import eu.redseeds.scl.rsl.rsldomainelements.domainelementrepresentations.DomainElementRepresentation;
import eu.redseeds.scl.rsl.rsldomainelements.domainelements.DomainElement;
import eu.redseeds.scl.rsl.rsldomainelements.phrases.ComplexVerbPhrase;
import eu.redseeds.scl.rsl.rsldomainelements.phrases.NounPhrase;
import eu.redseeds.scl.rsl.rsldomainelements.phrases.SimpleVerbPhrase;
import eu.redseeds.scl.rsl.rslkernel.elements.ElementRepresentation;
import eu.redseeds.scl.rsl.rslkernel.elements.HyperlinkedSentence;
import eu.redseeds.scl.rsl.rslrequirementrepresentationsentences.representationsentences.NaturalLanguageHypertextSentence;
import eu.redseeds.scl.sclkernel.IsAllocatedTo;
import eu.redseeds.scl.sclkernel.SCLElement;
import eu.redseeds.scl.sclkernel.Stereotype;


public class DomainStatementDTOImpl extends DomainStatementImpl implements DomainStatementDTO, TraceableObjectDTO {

	
	public DomainStatementDTOImpl(int arg0, Graph arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getName() {
		List<? extends SCLElement> list = super.getNameList();
		if (list.size() > 0) {
			if (list.get(0) instanceof NounPhrase){
				return ((NounPhraseDTO) list.get(0)).toString();
			}
			if (list.get(0) instanceof SimpleVerbPhrase){
				return ((SimpleVerbPhraseDTO) list.get(0)).toString();
			}
			if (list.get(0) instanceof ComplexVerbPhrase){
				return ((ComplexVerbPhraseDTO) list.get(0)).toString();
			}
		}
		return null;
		
	}

	@Override
	public void setName(String name) {
//		NounPhraseDTO phrase 
//			= (NounPhraseDTO)((SCLGraphImpl)super.getGraph()).createNounPhrase();
//		NounDTO noun 
//			= (NounDTO)((SCLGraphImpl)super.getGraph()).createNoun();
//		noun.setName(name);
//		phrase.setNoun(noun);
//		addName((NounPhrase)phrase);
	}

	@Override
	public void setPhraseDTO(PhraseDTO phrase) {
		if (phrase instanceof NounPhraseDTO){
			addName((NounPhrase)phrase);
		}
		if (phrase instanceof SimpleVerbPhraseDTO){
			addName((SimpleVerbPhrase)phrase);
		}
		if (phrase instanceof ComplexVerbPhraseDTO){
			addName((ComplexVerbPhrase)phrase);
		}
	}

	@Override
	public PhraseDTO getPhraseDTO() {
		List<? extends SCLElement> list = super.getNameList();
		if (list.size() > 0) {
			if (list.get(0) instanceof NounPhrase){
				return ((NounPhraseDTO) list.get(0));
			}
			if (list.get(0) instanceof SimpleVerbPhrase){
				return ((SimpleVerbPhraseDTO) list.get(0));
			}
			if (list.get(0) instanceof ComplexVerbPhrase){
				return ((ComplexVerbPhraseDTO) list.get(0));
			}
		}
		return null;
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
				if (sb.length()>0 && sb.lastIndexOf("\n") == sb.length()-1){
					sb.deleteCharAt(sb.length()-1);
				}

				return sb.toString();
			}
		}
		return "";
	}
	
	public NotionDTO getParent(){
		for (DomainElement n : getDomainElementList()) {
			if (n instanceof NotionDTO) {
				return (NotionDTO)n;
			}
		}	
		return null;
	}

	@Override
	public String getSpecificationPath() {
		if(getParent() != null) {
			return getParent().getSpecificationPath() + "\\" + getParent().getName();
		}
		return null;
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
	public void delete(){
		if (this.getPhraseDTO() != null)
			this.getPhraseDTO().deleteRecursively();
		for (Stereotype s:getStereotypeList())
			s.delete();
		super.delete();
	}

	@Override
	public String getCRUD() {
		for (Stereotype st:getStereotypeList())
			if (Arrays.asList(ActionTypesEnum.tags(NotionTypesEnum.Simple_View.tag())).contains(st.getName()))
				return st.getName();
		return "";
	}

	@Override
	public ActionTypesEnum getActionType() {
		for (Stereotype st:getStereotypeList())
			if (Arrays.asList(ActionTypesEnum.tags()).contains(st.getName()))
				return ActionTypesEnum.getActionTypeByTag(st.getName());
		return null;
	}
	
	@Override
	public String getActionTypeTag() {
		for (Stereotype st:getStereotypeList())
			if (Arrays.asList(ActionTypesEnum.tags()).contains(st.getName()))
				return st.getName();
		return "";
	}
	
	@Override
	public Stereotype getActionTypeStereotype() {
		for (Stereotype st:getStereotypeList())
			if (Arrays.asList(ActionTypesEnum.tags()).contains(st.getName()))
				return st;
		return null;
	}
	
	@Override
	public boolean isProcessableDomainDomainStatement() {
		return null!= getParent() && getParent().isDomainNotion() && getPhraseDTO() instanceof VerbPhraseDTO && Arrays.asList(ActionTypesEnum.tags("")).contains(getActionTypeTag());
	}
	
}