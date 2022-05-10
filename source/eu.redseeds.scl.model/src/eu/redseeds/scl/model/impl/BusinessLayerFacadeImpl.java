package eu.redseeds.scl.model.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import de.uni_koblenz.jgralab.Attribute;
import de.uni_koblenz.jgralab.AttributedElement;
import de.uni_koblenz.jgralab.Edge;
import de.uni_koblenz.jgralab.Vertex;
import eu.redseeds.common.Constants;
import eu.redseeds.sc.terminology.model.RemoteJGWNL;
import eu.redseeds.sc.terminology.model.TermSenseDTO;
import eu.redseeds.scl.impl.SCLGraphImpl;
import eu.redseeds.scl.model.BusinessLayerFacade;
import eu.redseeds.scl.model.impl.rsl.rsldomainelements.actors.ActorDTOImpl;
import eu.redseeds.scl.model.impl.rsl.rsldomainelements.actors.ActorsPackageDTOImpl;
import eu.redseeds.scl.model.impl.rsl.rsldomainelements.notions.NotionDTOImpl;
import eu.redseeds.scl.model.impl.rsl.rsldomainelements.notions.NotionsPackageDTOImpl;
import eu.redseeds.scl.model.impl.rsl.rsldomainelements.phrases.ComplexVerbPhraseDTOImpl;
import eu.redseeds.scl.model.impl.rsl.rsldomainelements.phrases.NounPhraseDTOImpl;
import eu.redseeds.scl.model.impl.rsl.rsldomainelements.phrases.SimpleVerbPhraseDTOImpl;
import eu.redseeds.scl.model.impl.rsl.rsldomainelements.systemelements.SystemElementDTOImpl;
import eu.redseeds.scl.model.impl.rsl.rsldomainelements.systemelements.SystemElementsPackageDTOImpl;
import eu.redseeds.scl.model.impl.rsl.rsldomainelements.terms.DeterminerDTOImpl;
import eu.redseeds.scl.model.impl.rsl.rsldomainelements.terms.ModifierDTOImpl;
import eu.redseeds.scl.model.impl.rsl.rsldomainelements.terms.NounDTOImpl;
import eu.redseeds.scl.model.impl.rsl.rsldomainelements.terms.PrepositionDTOImpl;
import eu.redseeds.scl.model.impl.rsl.rsldomainelements.terms.VerbDTOImpl;
import eu.redseeds.scl.model.impl.rsl.rslrequirementrepresentations.descriptiverequirementrepresentations.ConstrainedLanguageScenarioDTOImpl;
import eu.redseeds.scl.model.impl.rsl.rslrequirementrepresentationsentences.svosentences.SVOSentenceDTOImpl;
import eu.redseeds.scl.model.impl.rsl.rslrequirements.requirementsrelationships.ConflictsWithDTOImpl;
import eu.redseeds.scl.model.impl.rsl.rslrequirements.requirementsrelationships.ConstrainsDTOImpl;
import eu.redseeds.scl.model.impl.rsl.rslrequirements.requirementsrelationships.DependsOnDTOImpl;
import eu.redseeds.scl.model.impl.rsl.rslrequirements.requirementsrelationships.DerivesFromDTOImpl;
import eu.redseeds.scl.model.impl.rsl.rslrequirements.requirementsrelationships.ElaboratesDTOImpl;
import eu.redseeds.scl.model.impl.rsl.rslrequirements.requirementsrelationships.FulfilsDTOImpl;
import eu.redseeds.scl.model.impl.rsl.rslrequirements.requirementsrelationships.IsSimilarToDTOImpl;
import eu.redseeds.scl.model.impl.rsl.rslrequirements.requirementsrelationships.MakesPossibleDTOImpl;
import eu.redseeds.scl.model.impl.rsl.rslrequirements.requirementsrelationships.OperationalizesDTOImpl;
import eu.redseeds.scl.model.impl.rsl.rslrequirements.requirementsrelationships.RequirementVocabularyRelationshipDTOImpl;
import eu.redseeds.scl.model.impl.rsl.rslrequirements.requirementsspecifications.RequirementDTOImpl;
import eu.redseeds.scl.model.impl.rsl.rslrequirements.requirementsspecifications.RequirementsPackageDTOImpl;
import eu.redseeds.scl.model.impl.rsl.rslrequirements.requirementsspecifications.UseCaseDTOImpl;
import eu.redseeds.scl.model.impl.rsl.rslrequirements.usecaserelationships.InvocationRelationshipDTOImpl;
import eu.redseeds.scl.model.rsl.rsldomainelements.actors.ActorDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.actors.ActorsPackageDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.domainelements.ActorOrSystemElementDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.domainelements.DomainElementRelationshipDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.domainelements.DomainElementRepresentationDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.domainelements.DomainSpecificationDTO;
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
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.constrainedlanguagesentences.ConstrainedLanguageSentenceDTO;
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
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementsSpecificationDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.UseCaseDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.usecaserelationships.InvocationRelationshipDTO;
import eu.redseeds.scl.model.sclkernel.ClipboardDTO;
import eu.redseeds.scl.model.sclkernel.SoftwareCaseDTO;
import eu.redseeds.scl.model.sdsl.ClassDTO;
import eu.redseeds.scl.model.sdsl.ComponentDTO;
import eu.redseeds.scl.model.sdsl.InterfaceDTO;
import eu.redseeds.scl.model.sdsl.PackageDTO;
import eu.redseeds.scl.rsl.rsldomainelements.actors.Actor;
import eu.redseeds.scl.rsl.rsldomainelements.actors.ActorsPackage;
import eu.redseeds.scl.rsl.rsldomainelements.domainelements.ActorOrSystemElement;
import eu.redseeds.scl.rsl.rsldomainelements.domainelements.DomainElementRelationship;
import eu.redseeds.scl.rsl.rsldomainelements.domainelements.DomainSpecification;
import eu.redseeds.scl.rsl.rsldomainelements.notions.DomainStatement;
import eu.redseeds.scl.rsl.rsldomainelements.notions.Notion;
import eu.redseeds.scl.rsl.rsldomainelements.notions.NotionSpecialisation;
import eu.redseeds.scl.rsl.rsldomainelements.notions.NotionsPackage;
import eu.redseeds.scl.rsl.rsldomainelements.phrases.NounPhrase;
import eu.redseeds.scl.rsl.rsldomainelements.phrases.Phrase;
import eu.redseeds.scl.rsl.rsldomainelements.systemelements.SystemElement;
import eu.redseeds.scl.rsl.rsldomainelements.systemelements.SystemElementsPackage;
import eu.redseeds.scl.rsl.rsldomainelements.terms.Noun;
import eu.redseeds.scl.rsl.rsldomainelements.terms.Term;
import eu.redseeds.scl.rsl.rslrequirementrepresentations.descriptiverequirementrepresentations.ConstrainedLanguageScenario;
import eu.redseeds.scl.rsl.rslrequirementrepresentationsentences.constrainedlanguagesentences.ConstrainedLanguageSentence;
import eu.redseeds.scl.rsl.rslrequirementrepresentationsentences.constrainedlanguagesentences.RejoinSentence;
import eu.redseeds.scl.rsl.rslrequirementrepresentationsentences.controlsentences.ConditionSentence;
import eu.redseeds.scl.rsl.rslrequirementrepresentationsentences.controlsentences.InvocationSentence;
import eu.redseeds.scl.rsl.rslrequirementrepresentationsentences.controlsentences.PostconditionSentence;
import eu.redseeds.scl.rsl.rslrequirementrepresentationsentences.controlsentences.PreconditionSentence;
import eu.redseeds.scl.rsl.rslrequirementrepresentationsentences.svosentences.SVOSentence;
import eu.redseeds.scl.rsl.rslrequirements.requirementsspecifications.RSLUseCase;
import eu.redseeds.scl.rsl.rslrequirements.requirementsspecifications.Requirement;
import eu.redseeds.scl.rsl.rslrequirements.requirementsspecifications.RequirementsPackage;
import eu.redseeds.scl.rsl.rslrequirements.requirementsspecifications.RequirementsSpecification;
import eu.redseeds.scl.rsl.rslrequirements.usecaserelationships.InvocationRelationship;
import eu.redseeds.scl.sclkernel.Clipboard;
import eu.redseeds.scl.sclkernel.IsAllocatedTo;
import eu.redseeds.scl.sclkernel.SCLElement;
import eu.redseeds.scl.sclkernel.SCLRelationship;
import eu.redseeds.scl.sclkernel.SoftwareCase;
import eu.redseeds.scl.sclkernel.Stereotype;
import eu.redseeds.scl.uml.classes.interfaces.Interface;
import eu.redseeds.scl.uml.classes.kernel.Package;
import eu.redseeds.scl.uml.components.basiccomponents.Component;
import eu.redseeds.scl.uml.usecases.UseCase;
import eu.remics.alp.ALPDTO;
import eu.remics.alp.impl.ALPDTOImpl;

public class BusinessLayerFacadeImpl extends SCLGraphImpl implements
		BusinessLayerFacade {

	public BusinessLayerFacadeImpl(String arg0, int arg1, int arg2) {
		super(arg0, arg1, arg2);
		uidMap = new HashMap<String, AttributedElement>();
	}

	public BusinessLayerFacadeImpl(int arg0, int arg1) {
		super(arg0, arg1);
		uidMap = new HashMap<String, AttributedElement>();
	}

	public ClipboardDTO createClipboardDTO(String name, SoftwareCaseDTO mainSC) {
		Clipboard clipboard = this.createClipboard();
		clipboard.addParentSoftwareCase((SoftwareCase) mainSC);
		((ClipboardDTO) clipboard).setName(name);
		return (ClipboardDTO) clipboard;
	}

	public RequirementsPackageDTO createRequirementsPackageDTO() {
		return (RequirementsPackageDTOImpl) this.createRequirementsPackage();
	}

	public RequirementDTO createRequirementDTO() {
		return (RequirementDTOImpl) this.createRequirement();
	}

	public UseCaseDTO createUseCaseDTO() {
		RSLUseCase uc = this.createRSLUseCase();
		UseCase umluc = this.createUseCase();
		IsAllocatedTo iat = createIsAllocatedTo();
		uc.addAllocationToUML(iat);
		umluc.addAllocationToRSL(iat);
		Stereotype ster = createSclkernel$Stereotype();
		ster.setName("DuplicatedUseCaseStructureForUseRelationship");
		iat.addStereotype(ster);
		return (UseCaseDTOImpl) uc;
	}

	public ConstrainedLanguageScenarioDTO createConstrainedLanguageScenarioDTO() {
		return (ConstrainedLanguageScenarioDTOImpl) this
				.createConstrainedLanguageScenario();
	}

	public NotionsPackageDTO createNotionsPackageDTO() {
		return (NotionsPackageDTOImpl) this.createNotionsPackage();
	}

	public NotionDTO createNotionDTO() {
		return (NotionDTOImpl) this.createNotion();
	}

	public ActorsPackageDTO createActorsPackageDTO() {
		return (ActorsPackageDTOImpl) this.createActorsPackage();
	}

	public SystemElementsPackageDTO createSystemElementsPackageDTO() {
		return (SystemElementsPackageDTOImpl) this
				.createSystemElementsPackage();
	}

	public SystemElementDTO createSystemElementDTO() {
		return (SystemElementDTOImpl) this.createSystemElement();
	}

	public SVOSentenceDTO createSVOSentenceDTO() {
		return (SVOSentenceDTOImpl) this.createSVOSentence();
	}

	public ComplexVerbPhraseDTO createComplexVerbPhraseDTO() {
		return (ComplexVerbPhraseDTOImpl) this.createComplexVerbPhrase();
	}

	public SimpleVerbPhraseDTO createSimpleVerbPhraseDTO() {
		return (SimpleVerbPhraseDTOImpl) this.createSimpleVerbPhrase();
	}

	public NounPhraseDTO createNounPhraseDTO() {
		return (NounPhraseDTOImpl) this.createNounPhrase();
	}

	public DeterminerDTO createDeterminerDTO() {
		return (DeterminerDTOImpl) this.createDeterminer();
	}

	public DomainStatementDTO createDomainStatementDTO(PhraseDTO phrase) {
		DomainStatementDTO statement = (DomainStatementDTO) this
				.createDomainStatement();
		statement.setPhraseDTO(phrase);
		// ((DomainStatement)statement).addName((Phrase)phrase);
		return statement;
	}

	public DeterminerDTO createDeterminerDTO(String name) {
		/*
		 * for (Determiner n : this.getDeterminerVertices()) { if (n.getName()
		 * != null) if (n.getName().equals(name)) return (DeterminerDTOImpl) n;
		 * } DeterminerDTOImpl n = (DeterminerDTOImpl) this.createDeterminer();
		 * n.setName(name); return n;
		 */
		DeterminerDTO det = createDeterminerDTO();
		det.setName(name);
		return det;
	}

	public ModifierDTO createModifierDTO() {
		return (ModifierDTOImpl) this.createModifier();
	}

	public ModifierDTO createModifierDTO(String name) {
		/*
		 * for (Modifier n : this.getModifierVertices()) { if (n.getName() !=
		 * null) if (n.getName().equals(name)) return (ModifierDTOImpl) n; }
		 * ModifierDTOImpl n = (ModifierDTOImpl) this.createModifier();
		 * n.setName(name); return n;
		 */
		ModifierDTO mod = createModifierDTO();
		mod.setName(name);
		return mod;
	}

	public NounDTO createNounDTO() {
		return (NounDTOImpl) this.createNoun();
	}

	public NounDTO createNounDTO(String name) {
		for (Noun n : this.getNounVertices()) {
			if (n.getName() != null) {
				if (n.getName().equals(name)) {
					return (NounDTOImpl) n;
				}
			}
		}
		NounDTOImpl n = (NounDTOImpl) this.createNoun();
		n.setName(name);
		return n;
		// NounDTO n = createNounDTO();
		// n.setName(name);
		// return n;
	}

	public PrepositionDTO createPrepostitionDTO() {
		return (PrepositionDTOImpl) this.createPreposition();
	}

	public PrepositionDTO createPrepostitionDTO(String name) {
		/*
		 * for (Preposition n : this.getPrepositionVertices()) { if (n.getName()
		 * != null) if (n.getName().equals(name)) return (PrepositionDTOImpl) n;
		 * } PrepositionDTOImpl n = (PrepositionDTOImpl)
		 * this.createPreposition(); n.setName(name);
		 */
		PrepositionDTO p = createPrepostitionDTO();
		p.setName(name);
		return p;
	}

	public VerbDTO createVerbDTO() {
		return (VerbDTOImpl) this.createVerb();
	}

	public VerbDTO createVerbDTO(String name) {
		/*
		 * for (Verb n : this.getVerbVertices()) { if (n.getName() != null) if
		 * (n.getName().equals(name)) return (VerbDTOImpl) n; } VerbDTOImpl n =
		 * (VerbDTOImpl) this.createVerb(); n.setName(name);
		 */
		VerbDTO v = createVerbDTO();
		v.setName(name);
		return v;
	}

	@Override
	public ActorDTO createActorDTO() {
		Actor act =  createRsl$rsldomainelements$actors$Actor();
		eu.redseeds.scl.uml.usecases.Actor umlact = createUml$usecases$Actor();
		IsAllocatedTo iat = createIsAllocatedTo();
		act.addAllocationToUML(iat);
		umlact.addAllocationToRSL(iat);
		Stereotype ster = createSclkernel$Stereotype();
		ster.setName("DuplicatedUseCaseStructureForUseRelationship");
		iat.addStereotype(ster);
		return (ActorDTOImpl) act;
	}

	@Override
	public NaturalLanguageHypertextSentenceDTO createNaturalLanguageHypertextSentenceDTO() {
		return (NaturalLanguageHypertextSentenceDTO) createNaturalLanguageHypertextSentence();
	}

	@Override
	public SentenceListDTO createSentenceListDTO() {
		return (SentenceListDTO) createSentenceList();
	}

	@Override
	public ConditionSentenceDTO createConditionSentenceDTO() {
		return (ConditionSentenceDTO) createConditionSentence();
	}

	@Override
	public InvocationSentenceDTO createInvocationSentenceDTO() {
		return (InvocationSentenceDTO) createInvocationSentence();
	}

	@Override
	public PreconditionSentenceDTO createPreconditionSentenceDTO() {
		return (PreconditionSentenceDTO) createPreconditionSentence();
	}

	@Override
	public PostconditionSentenceDTO createPostconditionSentenceDTO() {
		return (PostconditionSentenceDTO) createPostconditionSentence();
	}

	@Override
	public RejoinSentenceDTO createRejoinSentenceDTO() {
		return (RejoinSentenceDTO) createRejoinSentence();
	}
	
	@Override
	public PrimitiveDataTypeDTO createPrimitiveDataTypeDTO() {
		return (PrimitiveDataTypeDTO)createPrimitiveDataType();
	}

	@Override
	public List<String> getAllUseCasesNames() {
		List<String> l = new ArrayList<String>();
		for (RSLUseCase uc : getRSLUseCaseVertices()) {
			if (((UseCaseDTO) uc).getName() != null) {
				l.add(((UseCaseDTO) uc).getName());
			}
		}
		return l;
	}

	@Override
	public UseCaseDTO getUseCaseByName(String name) {
		for (RSLUseCase uc : getRSLUseCaseVertices()) {
			if (((UseCaseDTO) uc).getName() != null) {
				if (((UseCaseDTO) uc).getName().equals(name)) {
					if (!isAnyClipboardMember(uc)) {
						return (UseCaseDTO) uc;
					}
				}
			}
		}
		return null;
	}

	@Override
	public List<NotionDTO> getAllNotions() {
		List<NotionDTO> l = new ArrayList<NotionDTO>();
		for (Notion n : getNotionVertices()) {
			if ((NotionDTO) n != null) {
				l.add((NotionDTO) n);
			}
		}
		return l;
	}

	@Override
	public NotionDTO getNotionDTO(NounDTO noun) {
		for (NotionDTO n : getAllNotions()) {
			NounPhraseDTO ph = n.getNamePhrase();
			if (ph != null) {
				NounDTO thisNoun = ph.getNoun();
				if (n != null) {
					if (thisNoun.equals(noun)) {
						if (isAnyClipboardMember(n)) {
							continue;
						}
						return n;
					}
				}
			}
		}
		return null;
	}

	public NotionDTO getNotionDTO(String[] nounNames) {
		for (NotionDTO n : getAllNotions()) {
			for (String s : nounNames) {
				if (n.getName() != null) {
					if (n.getName().equalsIgnoreCase(s)) {
						if (isAnyClipboardMember(n)) {
							continue;
						}
						return n;
					}
				}
			}
		}
		return null;
	}
	
	public NotionDTO getNotionDTO(String nounName) {
		for (NotionDTO n : getAllNotions()) {
			if (n.getName() != null) {
				if (n.getName().equalsIgnoreCase(nounName)) {
					if (isAnyClipboardMember(n)) {
						continue;
					}
					return n;
				}
			}
		}
		return null;
	}

	public NotionDTO getNotionDTO(long synoynmId) {
		for (NotionDTO n : getAllNotions()) {
			// for (DomainStatementDTO ds : n.getDomainStatementDTOList()){
			// PhraseDTO phrase = ds.getPhraseDTO();
			// if (phrase instanceof NounPhraseDTO){
			// if (((NounPhraseDTO)phrase).getNoun().getSynonymUid() ==
			// synoynmId)
			// return n;
			// }
			// if (phrase instanceof SimpleVerbPhraseDTO){
			// if
			// (((SimpleVerbPhraseDTO)phrase).getObject().getNoun().getSynonymUid()
			// == synoynmId)
			// return n;
			// }
			// if (phrase instanceof ComplexVerbPhraseDTO){
			// if
			// (((ComplexVerbPhraseDTO)phrase).getObject().getNoun().getSynonymUid()
			// == synoynmId)
			// return n;
			// }
			// }
			NounPhraseDTO np = n.getNamePhrase();
			NounDTO noun = np.getNoun();
			long id = noun.getSynonymUid();
			if (id == synoynmId) {
				return n;
				// if (n.getNamePhrase().getNoun().getSynonymUid() == synoynmId)
				// return n;
			}
		}
		return null;
	}

	public NotionDTO getNotionDTO(NounPhraseDTO phrase) {
		for (NotionDTO n : getAllNotions()) {
			if (n.getNamePhrase() != null) {
				if (n.getNamePhrase().compareNoun(phrase)) {
					return n;
				}
				// if (n.getNamePhrase().getNoun() != null && phrase.getNoun()
				// != null){
				// System.out.println(n.getNamePhrase().getNoun().getSynonymUid()+" "+phrase.getNoun().getSynonymUid());
				// if (n.getNamePhrase().getNoun().getSynonymUid() ==
				// phrase.getNoun().getSynonymUid()) {
				// return n;
				// }
				// }
			}
		}
		return null;
	}

	public List<NounPhraseDTO> findNounPhrases(NounPhraseDTO phrase) {
		List<NounPhraseDTO> result = new ArrayList<NounPhraseDTO>();
		for (Phrase p : getPhraseVertices()) {
			if ((PhraseDTO) p != null) {
				if (p instanceof NounPhraseDTO) {
					if (((NounPhraseDTO) p).getNoun() != null) {
						if (((NounPhraseDTO) p).getNoun().getSynonymUid() != 0) {
							if (((NounPhraseDTO) (p)).equals(phrase)) {
								result.add((NounPhraseDTO) p);
							}
						}
					}
				}
			}
		}
		return result;
	}

	public NounPhraseDTO findNounPhrase(NounPhraseDTO phrase) {
		NounPhraseDTO result = null;
		for (Phrase p : getPhraseVertices()) {
			if ((PhraseDTO) p != null) {
				if (p instanceof NounPhraseDTO) {
					if (((NounPhraseDTO) p).getNoun() != null) {
						if (((NounPhraseDTO) p).getNoun().getSynonymUid() != 0) {
							if (((NounPhraseDTO) (p)).equals(phrase)) {
								result = (NounPhraseDTO) p;
								break;
							}
						}
					}
				}
			}
		}
		return result;
	}

	public List<SimpleVerbPhraseDTO> findSimpleVerbPhrases(
			SimpleVerbPhraseDTO phrase) {
		List<SimpleVerbPhraseDTO> result = new ArrayList<SimpleVerbPhraseDTO>();
		for (Phrase p : getPhraseVertices()) {
			if ((PhraseDTO) p != null) {
				if (p instanceof SimpleVerbPhraseDTO) {
					if (((SimpleVerbPhraseDTO) (p)).equals(phrase)) {
						result.add((SimpleVerbPhraseDTO) p);
					}
				}
			}
		}
		return result;
	}

	public SimpleVerbPhraseDTO findSimpleVerbPhrase(SimpleVerbPhraseDTO phrase) {
		SimpleVerbPhraseDTO result = null;
		for (Phrase p : getPhraseVertices()) {
			if ((PhraseDTO) p != null) {
				if (p instanceof SimpleVerbPhraseDTO) {
					if (((SimpleVerbPhraseDTO) (p)).equals(phrase)) {
						result = ((SimpleVerbPhraseDTO) p);
						break;
					}
				}
			}
		}
		return result;
	}

	public List<ComplexVerbPhraseDTO> findComplexVerbPhrases(
			ComplexVerbPhraseDTO phrase) {
		List<ComplexVerbPhraseDTO> result = new ArrayList<ComplexVerbPhraseDTO>();
		for (Phrase p : getPhraseVertices()) {
			if ((PhraseDTO) p != null) {
				if (p instanceof ComplexVerbPhraseDTO) {
					if (((ComplexVerbPhraseDTO) (p)).equals(phrase)) {
						result.add((ComplexVerbPhraseDTO) p);
					}
				}
			}
		}
		return result;
	}

	public ComplexVerbPhraseDTO findComplexVerbPhrase(
			ComplexVerbPhraseDTO phrase) {
		ComplexVerbPhraseDTO result = null;
		for (Phrase p : getPhraseVertices()) {
			if ((PhraseDTO) p != null) {
				if (p instanceof ComplexVerbPhraseDTO) {
					if (((ComplexVerbPhraseDTO) (p)).equals(phrase)) {
						result = ((ComplexVerbPhraseDTO) p);
						break;
					}
				}
			}
		}
		return result;
	}

	public List<NounDTO> findNouns(NounDTO noun) {
		List<NounDTO> result = new ArrayList<NounDTO>();
		for (Noun n : getNounVertices()) {
			if ((NounDTO) n != null) {
				if (((NounDTO) (n)).equals(noun)) {
					result.add((NounDTO) n);
				}
			}
		}
		return result;
	}

	public List<NounDTO> getAllNouns() {
		List<NounDTO> result = new ArrayList<NounDTO>();
		for (Noun n : getNounVertices()) {
			result.add((NounDTO) n);
		}
		return result;
	}

	public List<PhraseDTO> getAllPhrases() {
		List<PhraseDTO> result = new ArrayList<PhraseDTO>();
		for (Phrase p : getPhraseVertices()) {
			result.add((PhraseDTO) p);
		}
		return result;

	}

	public List<TermDTO> getAllTerms() {
		List<TermDTO> result = new ArrayList<TermDTO>();
		for (Term t : getTermVertices()) {
			result.add((TermDTO) t);
		}
		return result;
	}
	
	public List<SCLRelationship> getValidableRelationship(){
		List<SCLRelationship> result = new ArrayList<SCLRelationship>();
		for (DomainElementRelationship rel:getDomainElementRelationshipVertices())
			result.add(rel);
		for (NotionSpecialisation rel:getNotionSpecialisationVertices())
			result.add(rel);
		return result;
	}

	@Override
	public ActorDTO getActorDTO(NounDTO noun) {
		for (ActorDTO a : getAllActors()) {
			if (isAnyClipboardMember(a)) {
				continue;
			}
			NounPhraseDTO ph = a.getNounPhrase();
			if (ph != null) {
				NounDTO n = ph.getNoun();
				if (n != null) {
					if (n.equals(noun)) {
						return a;
					}
				}
			}
		}
		return null;
	}

	@Override
	public ActorDTO getActorDTO(NounPhraseDTO phrase) {
		for (ActorDTO a : getAllActors()) {
			if (a.getNounPhrase().equals(phrase)) {
				return a;
			}
		}
		return null;
	}

	@Override
	public ActorDTO getActorDTO(String name) {
		for (ActorDTO a : getAllActors()) {
			if (a.getName() != null) {
				if (a.getName().equals(name)) {
					if (isAnyClipboardMember(a)) {
						continue;
					}
					return a;
				}
			}
		}
		return null;
	}

	@Override
	public List<ActorDTO> getAllActors() {
		List<ActorDTO> l = new ArrayList<ActorDTO>();
		for (ActorOrSystemElement a : getActorOrSystemElementVertices()) {
			if (a != null && a instanceof Actor) {
				l.add((ActorDTO) a);
			}
		}
		return l;
	}

	@Override
	public List<SystemElementDTO> getAllSystemElements() {
		List<SystemElementDTO> l = new ArrayList<SystemElementDTO>();
		for (ActorOrSystemElement se : getActorOrSystemElementVertices()) {
			if (se != null && se instanceof SystemElement) {
				l.add((SystemElementDTO) se);
			}
		}
		return l;
	}

	@Override
	public SystemElementDTO getSystemElementDTO(NounDTO noun) {
		for (SystemElementDTO se : getAllSystemElements()) {
			if (isAnyClipboardMember(se)) {
				continue;
			}
			NounPhraseDTO ph = se.getNounPhrase();
			if (ph != null) {
				NounDTO n = ph.getNoun();
				if (n != null) {
					if (n.equals(noun)) {
						return se;
					}
				}
			}
		}
		return null;
	}

	@Override
	public SystemElementDTO getSystemElementDTO(String name) {
		for (SystemElementDTO se : getAllSystemElements()) {
			if (se.getName() != null) {
				if (se.getName().equals(name)) {
					if (isAnyClipboardMember(se)) {
						continue;
					}
					return se;
				}
			}
		}
		return null;
	}

	@Override
	public SystemElementDTO getSystemElementDTO(NounPhraseDTO phrase) {
		for (SystemElementDTO se : getAllSystemElements()) {
			if (se.getNounPhrase().equals(phrase)) {
				return se;
			}
		}
		return null;
	}

	@Override
	public DomainElementRepresentationDTO createDomainElementRepresentationDTO() {
		return (DomainElementRepresentationDTO) createDomainElementRepresentation();
	}

	@Override
	public DomainElementRelationshipDTO createDomainElementRelationshipDTO() {
		return (DomainElementRelationshipDTO) createDomainElementRelationship();
	}

	@Override
	public NotionSpecialisationDTO createNotionSpecialisationDTO() {
		return (NotionSpecialisationDTO) createNotionSpecialisation();
	}

	public InvocationRelationshipDTO createInvocationRelationshipDTO() {
		return (InvocationRelationshipDTOImpl) createInvocationRelationship();
	}

	@Override
	public NonInvocationRelationshipDTO createNonInvocationRelationship(int type) {
		switch (type) {
		case NonInvocationRelationshipDTO.CONFLICTS_WITH:
			return (ConflictsWithDTOImpl) createConflictsWith();
		case NonInvocationRelationshipDTO.CONSTRAINS:
			return (ConstrainsDTOImpl) createConstrains();
		case NonInvocationRelationshipDTO.DEPENDS_ON:
			return (DependsOnDTOImpl) createDependsOn();
		case NonInvocationRelationshipDTO.DERIVES_FROM:
			return (DerivesFromDTOImpl) createDerivesFrom();
		case NonInvocationRelationshipDTO.ELABORATES:
			return (ElaboratesDTOImpl) createElaborates();
		case NonInvocationRelationshipDTO.FULFILS:
			return (FulfilsDTOImpl) createFulfils();
		case NonInvocationRelationshipDTO.IS_SIMILAR_TO:
			return (IsSimilarToDTOImpl) createIsSimilarTo();
		case NonInvocationRelationshipDTO.MAKES_POSSIBLE:
			return (MakesPossibleDTOImpl) createMakesPossible();
		case NonInvocationRelationshipDTO.OPERATIONALIZES:
			return (OperationalizesDTOImpl) createOperationalizes();
		default:
			return null;
		}
	}

	@Override
	public RequirementVocabularyRelationshipDTO createRequirementVocabularyRelationshipDTO() {
		return (RequirementVocabularyRelationshipDTOImpl) createRequirementVocabularyRelationship();
	}

	@Override
	public List<String> getAllRequirementsNames() {
		List<String> names = new ArrayList<String>();
		for (Requirement req : getRequirementVertices()) {
			if (!(req instanceof RSLUseCase)
					&& ((RequirementDTO) req).getName() != null) {
				names.add(((RequirementDTO) req).getName());
			}
		}
		return names;
	}

	@Override
	public RequirementDTO getRequirementByName(String name) {
		for (Requirement req : getRequirementVertices()) {
			if (!(req instanceof RSLUseCase)
					&& ((RequirementDTO) req).getName() != null) {
				if (((RequirementDTO) req).getName().equals(name)) {
					if (!isAnyClipboardMember(req)) {
						return (RequirementDTO) req;
					}
				}
			}
		}
		return null;
	}

	@Override
	public boolean isRequirementNameValid(String name) {
		for (Requirement r : this.getRequirementVertices()) {
			if (((RequirementDTO) r).getName().equalsIgnoreCase(name)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean isUseCaseNameValid(String name) {
		if (getUseCaseByName(name) == null) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isActorNameValid(String name) {
		for (ActorOrSystemElement ase : this.getActorOrSystemElementVertices()) {
			if (ase instanceof Actor) {
				if (((ActorDTO) ase).getName() == null) {
					return false;
				}
				if (((ActorDTO) ase).getName().equalsIgnoreCase(name)) {
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public boolean isNotionNameValid(String name) {
		for (Notion notion : this.getNotionVertices()) {
			if (null!=((NotionDTO) notion).getName() && ((NotionDTO) notion).getName().equalsIgnoreCase(name)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean isSystemElementNameValid(String name) {
		for (ActorOrSystemElement ase : this.getActorOrSystemElementVertices()) {
			if (ase instanceof SystemElement) {
				if (((SystemElementDTO) ase).getName() == null) {
					return false;
				}
				if (((SystemElementDTO) ase).getName().equalsIgnoreCase(name)) {
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public List<ActorOrSystemElementDTO> getAllRecipients() {
		List<ActorOrSystemElementDTO> result = new ArrayList<ActorOrSystemElementDTO>();
		for (ActorOrSystemElement ase : this.getActorOrSystemElementVertices()) {
			result.add((ActorOrSystemElementDTO) ase);
		}
		return result;
	}

	@Override
	public List<String> getAllRecipientsNames() {
		List<String> result = new ArrayList<String>();
		for (ActorOrSystemElement ase : this.getActorOrSystemElementVertices()) {
			result.add(ase.toString());
		}
		return result;
	}

	@Override
	public UseCaseDTO getUseCaseByVertexID(int id) {
		for (RSLUseCase uc : this.getRSLUseCaseVertices()) {
			if (uc.getId() == id) {
				return (UseCaseDTO) uc;
			}
		}
		return null;
	}

	@Override
	public int getIDOfSCLElement(Object element) {
		// TODO add more types
		if (element instanceof RequirementsSpecificationDTO) {
			RequirementsSpecification rs = (RequirementsSpecification) element;
			return rs.getId();
		} else if (element instanceof RequirementsPackageDTO) {
			RequirementsPackage rp = (RequirementsPackage) element;
			return rp.getId();
		}
		if (element instanceof UseCaseDTO) {
			RSLUseCase uc = (RSLUseCase) element;
			return uc.getId();
		} else if (element instanceof RequirementDTO) {
			Requirement r = (Requirement) element;
			return r.getId();
		} else if (element instanceof ConstrainedLanguageScenarioDTO) {
			ConstrainedLanguageScenario cls = (ConstrainedLanguageScenario) element;
			return cls.getId();
		} else if (element instanceof ConstrainedLanguageSentenceDTO) {
			ConstrainedLanguageSentenceDTO cls = (ConstrainedLanguageSentenceDTO) element;
			return cls.getId();
		} else if (element instanceof DomainSpecificationDTO) {
			DomainSpecification ds = (DomainSpecification) element;
			return ds.getId();
		} else if (element instanceof ActorsPackageDTO) {
			ActorsPackage ap = (ActorsPackage) element;
			return ap.getId();
		} else if (element instanceof NotionsPackageDTO) {
			NotionsPackage np = (NotionsPackage) element;
			return np.getId();
		} else if (element instanceof SystemElementsPackageDTO) {
			SystemElementsPackage sep = (SystemElementsPackage) element;
			return sep.getId();
		} else if (element instanceof ActorDTO) {
			Actor a = (Actor) element;
			return a.getId();
		} else if (element instanceof NotionDTO) {
			Notion n = (Notion) element;
			return n.getId();
		} else if (element instanceof SystemElementDTO) {
			SystemElementsPackage sep = (SystemElementsPackage) element;
			return sep.getId();
		} else if (element instanceof DomainStatementDTO) {
			DomainStatement ds = (DomainStatement) element;
			return ds.getId();
		} else if (element instanceof PackageDTO) {
			Package p = (Package) element;
			return p.getId();
		} else if (element instanceof ClassDTO) {
			return ((eu.redseeds.scl.uml.classes.kernel.Class) element).getId();
		} else if (element instanceof InterfaceDTO) {
			Interface in = (Interface) element;
			return in.getId();
		} else if (element instanceof ComponentDTO) {
			Component co = (Component) element;
			return co.getId();
		}
		return 0;
	}

	@Override
	public ConstrainedLanguageScenarioDTO getConstrainedLanguageScenarioByVertexID(
			int id) {
		for (ConstrainedLanguageScenario scen : this
				.getConstrainedLanguageScenarioVertices()) {
			if (scen.getId() == id) {
				return (ConstrainedLanguageScenarioDTO) scen;
			}
		}
		return null;
	}

	@Override
	public ConditionSentenceDTO getConditionSentenceByVertexID(int id) {
		for (ConditionSentence sent : this.getConditionSentenceVertices()) {
			if (sent.getId() == id) {
				return (ConditionSentenceDTO) sent;
			}
		}
		return null;
	}

	@Override
	public InvocationSentenceDTO getInvocationSentenceByVertexID(int id) {
		for (InvocationSentence sent : this.getInvocationSentenceVertices()) {
			if (sent.getId() == id) {
				return (InvocationSentenceDTO) sent;
			}
		}
		return null;
	}

	@Override
	public PostconditionSentenceDTO getPostconditionSentenceByVertexID(int id) {
		for (PostconditionSentence sent : this
				.getPostconditionSentenceVertices()) {
			if (sent.getId() == id) {
				return (PostconditionSentenceDTO) sent;
			}
		}
		return null;
	}

	@Override
	public RejoinSentenceDTO getRejoinSentenceByVertexID(int id) {
		for (RejoinSentence sent : this.getRejoinSentenceVertices()) {
			if (sent.getId() == id) {
				return (RejoinSentenceDTO) sent;
			}
		}
		return null;
	}

	@Override
	public PreconditionSentenceDTO getPreconditionSentenceByVertexID(int id) {
		for (PreconditionSentence sent : this.getPreconditionSentenceVertices()) {
			if (sent.getId() == id) {
				return (PreconditionSentenceDTO) sent;
			}
		}
		return null;
	}

	@Override
	public SVOSentenceDTO getSVOSentenceByVertexID(int id) {
		for (SVOSentence sent : this.getSVOSentenceVertices()) {
			if (sent.getId() == id) {
				return (SVOSentenceDTO) sent;
			}
		}
		return null;
	}

	@Override
	public boolean isAnyClipboardMember(Object element) {
		// check input
		SCLElement sclElement = null;
		if (element instanceof SCLElement) {
			sclElement = (SCLElement) element;
		} else {
			return false;
		}
		// get clipboards for this SC
		List<ClipboardDTO> clips = new ArrayList<ClipboardDTO>();
		for (Clipboard clipboard : this.getClipboardVertices()) {
			clips.add((ClipboardDTO) clipboard);
		}
		for (ClipboardDTO clipboard : clips) {
			if (clipboard.isClipboardMember(sclElement)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public ClipboardDTO whichClipboardMember(Object element) {
		// check input
		SCLElement sclElement = null;
		if (element instanceof SCLElement) {
			sclElement = (SCLElement) element;
		} else {
			return null;
		}
		// get clipboards for this SC
		List<ClipboardDTO> clips = new ArrayList<ClipboardDTO>();
		for (Clipboard clipboard : this.getClipboardVertices()) {
			clips.add((ClipboardDTO) clipboard);
		}
		for (ClipboardDTO clipboard : clips) {
			if (clipboard.isClipboardMember(sclElement)) {
				return clipboard;
			}
		}
		return null;
	}

	@Override
	public boolean isTermAnyClipboardMember(TermDTO term) {
		// get clipboards for this SC
		List<ClipboardDTO> clips = new ArrayList<ClipboardDTO>();
		for (Clipboard clipboard : this.getClipboardVertices()) {
			clips.add((ClipboardDTO) clipboard);
		}
		for (ClipboardDTO clipboard : clips) {
			if (clipboard.isClipboardMember((SCLElement) term)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public RequirementDTO getRequirementByID(String reqID) {
		if (reqID == null) {
			return null;
		}
		if (reqID.length() == 0) {
			return null;
		}
		for (Requirement req : getRequirementVertices()) {
			if (req instanceof RSLUseCase) {
				if (((UseCaseDTO) req).getRequirementId().equalsIgnoreCase(
						reqID)) {
					if (!isAnyClipboardMember(req)) {
						return (RequirementDTO) req;
					}
				}
			} else if (((RequirementDTO) req).getRequirementId()
					.equalsIgnoreCase(reqID)) {
				if (!isAnyClipboardMember(req)) {
					return (RequirementDTO) req;
				}
			}
		}
		return null;
	}

	@Override
	public List<DomainStatementDTO> getAllDomainStatements() {
		List<DomainStatementDTO> result = new ArrayList<DomainStatementDTO>();
		for (DomainStatement ds : this.getDomainStatementVertices()) {
			if (!isAnyClipboardMember(ds)) {
				result.add((DomainStatementDTO) ds);
			}
		}
		return result;
	}
	
	public List<DomainStatementDTO> getProcessableDomainDomainStatements(){
		List<DomainStatementDTO> result = new ArrayList<DomainStatementDTO>();
		for (DomainStatement ds : this.getDomainStatementVertices()) {
			if (!isAnyClipboardMember(ds) && ((DomainStatementDTO) ds).isProcessableDomainDomainStatement()) {
				result.add((DomainStatementDTO) ds);
			}
		}
		return result;
	}

	public DomainStatementDTO getDomainStatementByNameNearlyLightspeed(
			String name) {
		for (DomainStatement ds : this.getDomainStatementVertices()) {
			if (((DomainStatementDTO) ds).getName().equalsIgnoreCase(name)) {
				if (!isAnyClipboardMember(ds)) {
					return (DomainStatementDTO) ds;
				}
			}
		}
		return null;
	}

	public DomainStatementDTO getDomainStatementByNameFast(String name) {
		List<DomainStatementDTO> dsList = new ArrayList<DomainStatementDTO>();
		for (DomainStatement ds : this.getDomainStatementVertices()) {
			dsList.add((DomainStatementDTO) ds);
		}
		for (DomainStatementDTO ds : dsList) {
			if (ds.getName().equalsIgnoreCase(name)) {
				if (!isAnyClipboardMember(ds)) {
					return ds;
				}
			}
		}
		return null;
	}

	@Override
	public DomainStatementDTO getDomainStatementByName(String name) {
		for (DomainStatementDTO ds : getAllDomainStatements()) {
			if (ds.getName().equalsIgnoreCase(name)) {
				return ds;
			}
		}
		return null;
	}

	@Override
	public DomainStatementDTO getDomainStatementByPhrase(PhraseDTO phrase) {
		ClipboardDTO clip = whichClipboardMember(phrase);
		for (DomainStatement ds : getDomainStatementVertices()) {
			if (((DomainStatementDTO) ds).getPhraseDTO().equals(phrase)) {
				if (clip == null) {
					if (!isAnyClipboardMember(ds)) {
						return ((DomainStatementDTO) ds);
					}
				} else if (clip.isClipboardMember((SCLElement) ds)) {
					return ((DomainStatementDTO) ds);
				}
			}
		}
		return null;
	}

	private String generateUid() {
		String uid = null;
		do {
			Random uidGenerator = new Random();
			StringBuilder uidBuilder = new StringBuilder();
			uidBuilder.append(uidGenerator.nextLong());
			uidBuilder.append("-");
			uidBuilder.append(uidGenerator.nextLong());
			uidBuilder.append("-");
			uidBuilder.append(uidGenerator.nextLong());
			uidBuilder.append("-");
			uidBuilder.append(uidGenerator.nextLong());
			uid = uidBuilder.toString();
		} while ((this.getUid() == uid) || (uidMap.containsKey(uid)));
		return uid;
	}

	@Override
	public void loadingCompleted() {
		synchronized (this) {
			super.loadingCompleted();
			if (this.getUid() == null) {
				this.uid = generateUid();
				uidMap.put(this.uid, this);
			}
			/* initialize uid maps */
			for (SCLElement elem : getSCLElementVertices()) {
				if (elem.getUid() != null) {
					uidMap.put(elem.getUid(), elem);
				}
			}
			for (SCLRelationship rel : getSCLRelationshipVertices()) {
				if (rel.getUid() != null) {
					uidMap.put(rel.getUid(), rel);
				}
			}
			for (SCLElement elem : getSCLElementVertices()) {
				if (elem.getUid() == null) {
					elem.setUid(generateUid());
				}
				uidMap.put(elem.getUid(), elem);
			}
			for (SCLRelationship rel : getSCLRelationshipVertices()) {
				if (rel.getUid() != null) {
					rel.setUid(generateUid());
					uidMap.put(rel.getUid(), rel);
				}
			}
			Map<Class<? extends Edge>, Class<? extends Edge>> classReplacements = new HashMap<Class<? extends Edge>, Class<? extends Edge>>();
			Map<Class<? extends Edge>, Boolean> reverseEdgeDirection = new HashMap<Class<? extends Edge>, Boolean>();
			reverseEdgeDirection.put(eu.redseeds.scl.sclkernel.ElementLinksToStereotype.class, true);
			reverseEdgeDirection.put(eu.redseeds.scl.uml.classes.kernel.ElementLinksToStereotype.class, true);
			reverseEdgeDirection.put(eu.redseeds.scl.rsl.rslrequirements.requirementrelationships.VocabularyLinksToTarget.class, false);
			classReplacements.put(eu.redseeds.scl.sclkernel.ElementLinksToStereotype.class, eu.redseeds.scl.sclkernel.StereotypeLinksToElement.class);
			classReplacements.put(eu.redseeds.scl.uml.classes.kernel.ElementLinksToStereotype.class, eu.redseeds.scl.uml.classes.kernel.UmlStereotypeLinksToUmlElement.class);
			classReplacements.put(eu.redseeds.scl.rsl.rslrequirements.requirementrelationships.VocabularyLinksToTarget.class, eu.redseeds.scl.rsl.rslrequirements.requirementrelationships.RequirementVocabularyLinksToTarget.class);

			
			//delete ElementLinksToStereotype edges and replace them by StereotypeLinksToElement edges
			for (Class<? extends Edge> elemClass : classReplacements.keySet()) {
				List<Edge> edges = new ArrayList<Edge>();
				for (Edge edge : edges(elemClass)) {
					edges.add(edge);
				}
				for (Edge edge : edges) {
					Vertex alpha = edge.getAlpha();
					Vertex omega = edge.getOmega();
					if (reverseEdgeDirection.get(elemClass) == true) {
						alpha = omega;
						omega = edge.getAlpha();
					}						
					Edge newEdge = createEdge(classReplacements.get(elemClass), alpha, omega);
					for (Attribute attr : newEdge.getAttributedElementClass().getAttributeList()) {
						try {
							newEdge.setAttribute(attr.getName(),edge.getAttribute(attr.getName()));
						} catch (NoSuchFieldException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}	
					}
					edge.delete();
				}	
			}
		}
	}

	@Override
	public void vertexDeleted(Vertex v) {
		if (v instanceof SCLElement) {
			uidMap.remove(((SCLElement) v).getUid());
		}
		if (v instanceof SCLRelationship) {
			uidMap.remove(((SCLRelationship) v).getUid());
		}
	}

	@Override
	public void vertexAdded(Vertex v) {
		if (v instanceof SCLElement) {
			SCLElement elem = (SCLElement) v;
			if (elem.getUid() == null) {
				elem.setUid(generateUid());
			}
			uidMap.put(elem.getUid(), elem);
		}
		if (v instanceof SCLRelationship) {
			SCLRelationship elem = (SCLRelationship) v;
			if (elem.getUid() == null) {
				elem.setUid(generateUid());
			}
			uidMap.put(elem.getUid(), elem);
		}
	}

	//
	// @Override
	// public void edgeDeleted(Edge e) {
	// throw new RuntimeException(e + " is going to be deleted!");
	// }
	//
	// @Override
	// public void edgeAdded(Edge e) {
	// throw new RuntimeException(e + " is going to be added!");
	// }

	private final HashMap<String, AttributedElement> uidMap;

	@Override
	public ALPDTO createALPDTO() {
		return (ALPDTOImpl) this.createRequirementsPackage();
	}

	@Override
	public List<RequirementsPackageDTO> getALPs() {
		List<RequirementsPackageDTO> result = new ArrayList<RequirementsPackageDTO>();
		for (RequirementsPackage reqPack : getRequirementsPackageVertices()) {
			if ((RequirementsPackageDTO) reqPack != null) {
				RequirementsPackageDTO reqPackDTO = (RequirementsPackageDTO)reqPack;
				if(reqPackDTO.getStereotypes().contains(Constants.ALP_STEREOTYPE)) {
					result.add(reqPackDTO);
				}
			}
		}
		return result;
	}

	@Override
	public ActorDTO getActorDTOIncludeClipboards(String name) {
		for (ActorDTO a : getAllActors()) {
			if (a.getName() != null) {
				if (a.getName().equals(name)) {
					return a;
				}
			}
		}
		return null;
	}

	@Override
	public SystemElementDTO getSystemElementDTOIncludeClipboards(String name) {
		for (SystemElementDTO se : getAllSystemElements()) {
			if (se.getName() != null) {
				if (se.getName().equals(name)) {
					return se;
				}
			}
		}
		return null;
	}

	@Override
	public List<String> getAllALPsNames() {
		List<String> l = new ArrayList<String>();
		for (RSLUseCase uc : getRSLUseCaseVertices()) {
			if (((UseCaseDTO) uc).getName() != null) {
				//check if is pattern element:
				RequirementsPackageDTO parent = ((UseCaseDTO) uc).getParent();
				while(parent != null) {
					if(parent.getStereotypes().contains(Constants.ALP_STEREOTYPE)) {
						l.add(((UseCaseDTO) uc).getName());
						break;
					}
					parent = parent.getParent();
				}
			}
		}
		return l;
	}


//	REMICS Fix:

	@Override
	public NounDTO getNoun(String name){
		List<TermSenseDTO> thisSenses = new ArrayList<TermSenseDTO>();
		for (TermSenseDTO sens : RemoteJGWNL.getInstance().getTermSenses(name.toLowerCase().trim().replaceAll("[ ]+"," "),Constants.TERM_NOUN)) thisSenses.add(sens);
		List<TermSenseDTO> thatSenses = new ArrayList<TermSenseDTO>();
		for (Noun n : getNounVertices()) {
			if ((NounDTO) n != null) {
				thatSenses.clear();
				TermSenseDTO thatSense = 
					RemoteJGWNL.getInstance().getTermSenseDTO(n.getSynonymUid());
				if (thatSense == null){
					TermSenseDTO[] senses =  
						RemoteJGWNL.getInstance().getTermSenses(n.getName().toLowerCase().replaceAll("[ ]+"," "), Constants.TERM_NOUN);
					if (senses.length > 0) for (TermSenseDTO s : senses){
						thatSenses.add(s);
					} else {
						if (name.trim().replaceAll("[ ]+"," ").equalsIgnoreCase(n.getName().replaceAll("[ ]+"," "))){
							return (NounDTO) n;
						}
					}
				} else {
					thatSenses.add(thatSense);
				}
				for (TermSenseDTO thisS : thisSenses){
					for (TermSenseDTO thatS : thatSenses){	
						if (thisS.getUid() == thatS.getUid()){
							return (NounDTO) n;
						}
					}
				}
			}
		}
		return null;
	}

	@Override
	public List<NounDTO> findNouns(String name){
		List<NounDTO> result = new ArrayList<NounDTO>();
		List<TermSenseDTO> thisSenses = new ArrayList<TermSenseDTO>();
		for (TermSenseDTO sens : RemoteJGWNL.getInstance().getTermSenses(name.toLowerCase().trim().replaceAll("[ ]+"," "),Constants.TERM_NOUN)) thisSenses.add(sens);
		List<TermSenseDTO> thatSenses = new ArrayList<TermSenseDTO>();
		for (Noun n : getNounVertices()) {
			if ((NounDTO) n != null) {
				thatSenses.clear();
				TermSenseDTO thatSense = 
					RemoteJGWNL.getInstance().getTermSenseDTO(n.getSynonymUid());
				if (thatSense == null){
					TermSenseDTO[] senses =  
						RemoteJGWNL.getInstance().getTermSenses(n.getName().toLowerCase().replaceAll("[ ]+"," "), Constants.TERM_NOUN);
					if (senses.length > 0) for (TermSenseDTO s : senses){
						thatSenses.add(s);
					} else {
						if (name.trim().replaceAll("[ ]+"," ").equalsIgnoreCase(n.getName().replaceAll("[ ]+"," "))){
							result.add((NounDTO) n);
							continue;
						}
					}
				} else {
					thatSenses.add(thatSense);
				}
				shell:
				for (TermSenseDTO thisS : thisSenses){
					for (TermSenseDTO thatS : thatSenses){	
						if (thisS.getUid() == thatS.getUid()){
							result.add((NounDTO) n);
							break shell;
						}
					}
				}
			}
		}
		return result;
	}

	public void cleanNouns(NounDTO noun) {
		if (null != noun && ((Noun) noun).getNounLinkList().size()>0) {
			List<NounDTO> nouns = findNouns(noun.getName());
			if (nouns.size() > 1) {
				List<NounPhraseDTO> list = new ArrayList<NounPhraseDTO>();
				for (NounPhrase np : getNounPhraseVertices())
					for (NounDTO no : nouns)
						if (((Noun) no).getId() != ((Noun) noun).getId()
								&& null != ((NounPhraseDTO) np).getNoun()
								&& ((Noun) no).getId() == ((Noun) ((NounPhraseDTO) np)
										.getNoun()).getId()) {
							list.add((NounPhraseDTO) np);
							break;
						}
				for (NounPhraseDTO np : list)
					np.setNoun(noun);
			}
		}
	}

	public List<NotionDTO> findNotions(NounDTO noun) {
		List<NotionDTO> result = new ArrayList<NotionDTO>();
		for (Notion not: getNotionVertices())
			if (noun.equals(((NotionDTO)not).getNamePhrase().getNoun()))
				result.add((NotionDTO) not);
		return result;
	}

	public InvocationRelationship getInvocationRelationship(UseCaseDTO source, UseCaseDTO target) {
		for(InvocationRelationship inv:getInvocationRelationshipVertices()){
			if (null!=((InvocationRelationshipDTO)inv).getSource() && ((RSLUseCase)((InvocationRelationshipDTO)inv).getSource()).getId()==((RSLUseCase)source).getId() && null!=((InvocationRelationshipDTO)inv).getTarget() && ((RSLUseCase)((InvocationRelationshipDTO)inv).getTarget()).getId()==((RSLUseCase)target).getId()) return inv;
		}
		return null;
	}
	
	public List<InvocationSentence> getInvocationSentences(UseCaseDTO source, UseCaseDTO target) {
		List<InvocationSentence> result = new ArrayList<InvocationSentence>();
		for(InvocationSentence inv:getInvocationSentenceVertices()) if (null!=((InvocationSentenceDTO)inv).getInvokedUseCase() && ((RSLUseCase)((InvocationSentenceDTO)inv).getInvokedUseCase()).getId()==((RSLUseCase)target).getId() && (!((InvocationSentenceDTO)inv).getOwningScenarios().isEmpty() && null!=((InvocationSentenceDTO)inv).getOwningScenarios().get(0).getParent() && ((RSLUseCase)((InvocationSentenceDTO)inv).getOwningScenarios().get(0).getParent()).getId()==((RSLUseCase)source).getId() || null!=((InvocationSentenceDTO)inv).getInvocationSource() && ((RSLUseCase)((InvocationSentenceDTO)inv).getInvocationSource()).getId()==((RSLUseCase)source).getId()))
			result.add(inv);
		return result;
	}

	@Override
	public boolean canBeActorOrSystemElement(NounPhraseDTO phrase) {
		if (null!=getActorDTO(phrase) || null!= getSystemElementDTO(phrase)) return true;
		for (SVOSentence sent:getSVOSentenceVertices()){
			if (null!=((SVOSentenceDTO) sent).getSubject() && (((SVOSentenceDTO) sent).getSubject().equals(phrase) || ((NounPhrase) ((SVOSentenceDTO) sent).getSubject()).getId()==((NounPhrase) phrase).getId())) return true;
		}
		return false;
	}

	@Override
	public SCLElement getSCLElementById(long id) {
		for (SCLElement scle : getSCLElementVertices())
			if (scle.getId() == id)
				return scle;
		return null;
	}

	@Override
	public InvocationRelationshipDTO getInvocationRelationshipByUid(String uid) {
		getInvocationRelationshipVertices();
		for (InvocationRelationship ir : this.getInvocationRelationshipVertices()) {
			if (ir.getUid() != null) {
				if (ir.getUid().equals(uid)) {
					return (InvocationRelationshipDTO) ir;
				}
			}
		}
		return null;
	}

	@Override
	public List<ConstrainedLanguageScenarioDTO> getScenariosByVerbPhrase(VerbPhraseDTO phrase) {
		List<ConstrainedLanguageScenarioDTO> result = new ArrayList<ConstrainedLanguageScenarioDTO>();
		for(ConstrainedLanguageScenario s:getConstrainedLanguageScenarioVertices())
			for(ConstrainedLanguageSentence snt:s.getScenarioStepList())
				if (snt instanceof SVOSentenceDTO && null!=((SVOSentenceDTO) snt).getPredicate() && ((SVOSentenceDTO) snt).getPredicate().equals(phrase)){
					result.add((ConstrainedLanguageScenarioDTO) s);
					break;
				}
		return result;
	}

//	@Override
//	public StereotypeDTO createStereotypeDTO() {
//		return (StereotypeDTOImpl) this.createStereotype();
//	}

}