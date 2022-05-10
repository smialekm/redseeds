package eu.redseeds.scl.model;

import java.util.List;

import eu.redseeds.scl.SCLGraph;
import eu.redseeds.scl.model.rsl.rsldomainelements.actors.ActorDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.actors.ActorsPackageDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.domainelements.ActorOrSystemElementDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.domainelements.DomainElementRelationshipDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.domainelements.DomainElementRepresentationDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.DomainStatementDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionSpecialisationDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionsPackageDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.PrimitiveDataTypeDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.ComplexVerbPhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.NounPhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.PhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.SimpleVerbPhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.VerbPhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.systemelements.SystemElementDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.systemelements.SystemElementsPackageDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.terms.DeterminerDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.terms.ModifierDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.terms.NounDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.terms.PrepositionDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.terms.TermDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.terms.VerbDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentations.descriptiverequirementrepresentations.ConstrainedLanguageScenarioDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentations.descriptiverequirementrepresentations.SentenceListDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.constrainedlanguagesentences.RejoinSentenceDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.controlsentences.ConditionSentenceDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.controlsentences.InvocationSentenceDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.controlsentences.PostconditionSentenceDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.controlsentences.PreconditionSentenceDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.representationsentences.NaturalLanguageHypertextSentenceDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.svosentences.SVOSentenceDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsrelationships.NonInvocationRelationshipDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsrelationships.RequirementVocabularyRelationshipDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementsPackageDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.UseCaseDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.usecaserelationships.InvocationRelationshipDTO;
import eu.redseeds.scl.model.sclkernel.ClipboardDTO;
import eu.redseeds.scl.model.sclkernel.SoftwareCaseDTO;
import eu.redseeds.scl.rsl.rslrequirementrepresentationsentences.controlsentences.InvocationSentence;
import eu.redseeds.scl.rsl.rslrequirements.usecaserelationships.InvocationRelationship;
import eu.redseeds.scl.sclkernel.SCLElement;
import eu.redseeds.scl.sclkernel.SCLRelationship;
import eu.remics.alp.ALPDTO;

public interface BusinessLayerFacade extends SCLGraph {

	public ActorDTO createActorDTO();

	public ActorsPackageDTO createActorsPackageDTO();

	public ClipboardDTO createClipboardDTO(String name, SoftwareCaseDTO mainSC);

	public NotionDTO createNotionDTO();

	public NotionsPackageDTO createNotionsPackageDTO();

	public RequirementDTO createRequirementDTO();

	public RequirementsPackageDTO createRequirementsPackageDTO();

	public SystemElementDTO createSystemElementDTO();

	public SystemElementsPackageDTO createSystemElementsPackageDTO();

	public UseCaseDTO createUseCaseDTO();

	public SVOSentenceDTO createSVOSentenceDTO();

	public SimpleVerbPhraseDTO createSimpleVerbPhraseDTO();

	public ComplexVerbPhraseDTO createComplexVerbPhraseDTO();

	public NounPhraseDTO createNounPhraseDTO();

	public DeterminerDTO createDeterminerDTO();

	public DeterminerDTO createDeterminerDTO(String name);

	public ModifierDTO createModifierDTO();

	public ModifierDTO createModifierDTO(String name);

	public NounDTO createNounDTO();

	public NounDTO createNounDTO(String name);

	public VerbDTO createVerbDTO();

	public VerbDTO createVerbDTO(String name);

	public PrepositionDTO createPrepostitionDTO();

	public PrepositionDTO createPrepostitionDTO(String name);

	public ConstrainedLanguageScenarioDTO createConstrainedLanguageScenarioDTO();

	public NaturalLanguageHypertextSentenceDTO createNaturalLanguageHypertextSentenceDTO();

	public SentenceListDTO createSentenceListDTO();

	public ConditionSentenceDTO createConditionSentenceDTO();

	public InvocationSentenceDTO createInvocationSentenceDTO();

	public PreconditionSentenceDTO createPreconditionSentenceDTO();

	public PostconditionSentenceDTO createPostconditionSentenceDTO();

	public RejoinSentenceDTO createRejoinSentenceDTO();

	public DomainElementRepresentationDTO createDomainElementRepresentationDTO();

	public DomainElementRelationshipDTO createDomainElementRelationshipDTO();

	public NotionSpecialisationDTO createNotionSpecialisationDTO();

	public DomainStatementDTO createDomainStatementDTO(PhraseDTO phrase);

	public RequirementVocabularyRelationshipDTO createRequirementVocabularyRelationshipDTO();
	
	public PrimitiveDataTypeDTO createPrimitiveDataTypeDTO();

	public List<NotionDTO> getAllNotions();

	public NotionDTO getNotionDTO(NounDTO noun);
	
	public NotionDTO getNotionDTO(String[] nounNames);
	
	public NotionDTO getNotionDTO(String nounName);

	public NotionDTO getNotionDTO(long synonymId);

	public NotionDTO getNotionDTO(NounPhraseDTO phrase);

	public List<NounPhraseDTO> findNounPhrases(NounPhraseDTO phrase);

	public NounPhraseDTO findNounPhrase(NounPhraseDTO phrase);

	public List<SimpleVerbPhraseDTO> findSimpleVerbPhrases(
			SimpleVerbPhraseDTO phrase);

	public SimpleVerbPhraseDTO findSimpleVerbPhrase(SimpleVerbPhraseDTO phrase);

	public List<ComplexVerbPhraseDTO> findComplexVerbPhrases(
			ComplexVerbPhraseDTO phrase);

	public ComplexVerbPhraseDTO findComplexVerbPhrase(
			ComplexVerbPhraseDTO phrase);

	public List<NounDTO> findNouns(NounDTO noun);

	public List<NounDTO> getAllNouns();
	
	public List<PhraseDTO> getAllPhrases();

	public List<TermDTO> getAllTerms();
	
	public List<SCLRelationship> getValidableRelationship();

	public List<ActorDTO> getAllActors();

	public ActorDTO getActorDTO(NounDTO noun);
	
	public ActorDTO getActorDTO(String name);

	public ActorDTO getActorDTO(NounPhraseDTO phrase);
	
	public ActorDTO getActorDTOIncludeClipboards(String name);

	public List<SystemElementDTO> getAllSystemElements();

	public SystemElementDTO getSystemElementDTO(NounDTO noun);
	
	public SystemElementDTO getSystemElementDTO(String name);
	
	public SystemElementDTO getSystemElementDTOIncludeClipboards(String name);

	public SystemElementDTO getSystemElementDTO(NounPhraseDTO phrase);

	public List<String> getAllUseCasesNames();
	
	/**
	 * gets names of all patterns in the SC
	 * @return list of names
	 */
	public List<String> getAllALPsNames();

	public UseCaseDTO getUseCaseByName(String name);

	public List<String> getAllRequirementsNames();

	public RequirementDTO getRequirementByName(String name);
	
	public RequirementDTO getRequirementByID(String reqID);

	public InvocationRelationshipDTO createInvocationRelationshipDTO();

	public NonInvocationRelationshipDTO createNonInvocationRelationship(int type);

	public boolean isRequirementNameValid(String name);

	public boolean isUseCaseNameValid(String name);

	public boolean isActorNameValid(String name);

	public boolean isNotionNameValid(String name);

	public boolean isSystemElementNameValid(String name);

	public List<ActorOrSystemElementDTO> getAllRecipients();

	public List<String> getAllRecipientsNames();

	public UseCaseDTO getUseCaseByVertexID(int id);

	public ConstrainedLanguageScenarioDTO getConstrainedLanguageScenarioByVertexID(
			int id);

	public ConditionSentenceDTO getConditionSentenceByVertexID(int id);

	public InvocationSentenceDTO getInvocationSentenceByVertexID(int id);

	public PreconditionSentenceDTO getPreconditionSentenceByVertexID(int id);

	public PostconditionSentenceDTO getPostconditionSentenceByVertexID(int id);
	
	public RejoinSentenceDTO  getRejoinSentenceByVertexID(int id);

	public SVOSentenceDTO getSVOSentenceByVertexID(int id);

	/**
	 * Gets vertex id of a given SCL element
	 * 
	 * @param element
	 * @return 0 if element does not exist or Object supplied is not an instance
	 *         of any SCL element
	 */
	public int getIDOfSCLElement(Object element);
	
	/**
	 * Checks if given SCL element is a member of any clipboard in an SC associated to this facade
	 * @param element
	 * @return
	 */
	public boolean isAnyClipboardMember(Object element);
	
	/**
	 * Checks if given SCL element is a member of any clipboard in an SC associated to this facade.
	 * Returns the clipboard element belongs to (null if elements in not a clipboard member)
	 * 
	 * @param element
	 * @return
	 */
	public ClipboardDTO whichClipboardMember(Object element);
	
	/**
	 * Checks if given term is a member of any clipboard in an SC associated to this facade
	 * @param element
	 * @return
	 */
	public boolean isTermAnyClipboardMember(TermDTO term);
	
	/**
	 * clipboard members excluded
	 * @return
	 */
	public List<DomainStatementDTO> getAllDomainStatements();
	
	public List<DomainStatementDTO> getProcessableDomainDomainStatements();
	
	public DomainStatementDTO getDomainStatementByName(String name);
	
	/**
	 * Faster version of getDomainStatementByName
	 * @param name
	 * @return
	 */
	public DomainStatementDTO getDomainStatementByNameFast(String name);
	
	/**
	 * Gives the DomainStatemntDTO according to phrase's clipboard.
	 * @param phrase
	 * @return
	 */
	public DomainStatementDTO getDomainStatementByPhrase(PhraseDTO phrase);
	
	public ALPDTO createALPDTO();
	
//	public StereotypeDTO createStereotypeDTO();
	
	public List<RequirementsPackageDTO> getALPs();
	
	
//	REMICS Fix:
	
	/**
	 * Returns noun with given name
	 * 
	 * @param name name
	 * @return noun
	 */
	public NounDTO getNoun(String name);
	
	/**
	 * Returns list of nouns with given name
	 * 
	 * @param name name
	 * @return list of nouns
	 */
	public List<NounDTO> findNouns(String name);
	
	/**
     * Clean duplicated nouns and notions after adds of new meaning
     * 
     * @param noun noun with assigned new meaning
     */
	public void cleanNouns(NounDTO noun);
	
	/**
	 * Returns list of notions with given noun
	 * 
	 * @param noun noun
	 * @return list of notions
	 */
	public List<NotionDTO> findNotions(NounDTO noun);
	
	public InvocationRelationship getInvocationRelationship(UseCaseDTO source, UseCaseDTO target);
	
	public InvocationRelationshipDTO getInvocationRelationshipByUid(String uid);
	
	public List<InvocationSentence> getInvocationSentences(UseCaseDTO source, UseCaseDTO target);

	public boolean canBeActorOrSystemElement(NounPhraseDTO phrase);

	public SCLElement getSCLElementById(long id);
	
	public List<ConstrainedLanguageScenarioDTO> getScenariosByVerbPhrase(VerbPhraseDTO phrase);

}