package eu.redseeds.java.parser.elements;

import eu.redseeds.java.repository.SCFacade;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionTypesEnum;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.ComplexVerbPhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.NounPhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.PhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.SimpleVerbPhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.VerbPhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.terms.NounDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.terms.VerbDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentations.descriptiverequirementrepresentations.ConstrainedLanguageScenarioDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.constrainedlanguagesentences.ConstrainedLanguageSentenceDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.controlsentences.PreconditionSentenceDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.svosentences.SVOSentenceDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementsPackageDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementsSpecificationDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.UseCaseDTO;

public class UseCasesParser {
	
	private SCFacade scFacade;
	private SimpleVerbPhraseDTO simpleVerbPhrase;
	private ComplexVerbPhraseDTO complexVerbPhrase;
	
	private boolean isSystem2SystemSentenceType = false;
	private boolean verbPhraseWasFoundInScenarios = false;
	private boolean paramIsEmpty = false;
	private boolean paramIsEntityType = false;
	private boolean paramIsValueObjectType = false;
	private boolean paramIsFrameType = false;
	private boolean returnIsVoid = false;
	private boolean returnIsValueObject = false;
	private boolean conceptIsInPrecondition = false;
	
	private NotionDTO conceptFromPrecondition;
	private NotionDTO actor2conceptNotion;
	//private NotionDTO system2screenNotion;

	public UseCasesParser() {
		scFacade = SCFacade.instance();
	}
	
	public boolean isSystem2SystemSentenceType() {
		return isSystem2SystemSentenceType;
	}
	
	public boolean verbPhraseWasFoundInScenarios() {
		return verbPhraseWasFoundInScenarios;
	}
	
	public boolean paramIsEmpty() {
		return paramIsEmpty;
	}
	
	public boolean paramIsEntityType() {
		return paramIsEntityType;
	}
	
	public boolean paramIsValueObjectType() {
		return paramIsValueObjectType;
	}
	
	public boolean paramIsFrameType() {
		return paramIsFrameType;
	}
	
	public boolean returnIsVoid() {
		return returnIsVoid;
	}
	
	public boolean returnIsValueObject() {
		return returnIsValueObject;
	}
	
	public boolean conceptInPrecondition() {
		return conceptIsInPrecondition;
	}
	
	public NotionDTO getConceptFromPrecondition() {
		return conceptFromPrecondition;
	}
	
	public NotionDTO getActor2ConceptNotion() {
		return actor2conceptNotion;
	}
	
	/*public NotionDTO getSystem2ScreenNotion() {
		return system2screenNotion;
	}*/
	
	public void setPhraseToFind(PhraseDTO phrase){
		if(phrase instanceof SimpleVerbPhraseDTO){
			this.simpleVerbPhrase = (SimpleVerbPhraseDTO) phrase;
		}
		else if(phrase instanceof ComplexVerbPhraseDTO){
			this.complexVerbPhrase = (ComplexVerbPhraseDTO) phrase;
		}
		isSystem2SystemSentenceType = false;
		verbPhraseWasFoundInScenarios = false;
		paramIsEmpty = false;
		paramIsEntityType = false;
		paramIsFrameType = false;
		paramIsValueObjectType = false;
		returnIsVoid = false;
		returnIsValueObject = false;
		conceptIsInPrecondition = false;
		actor2conceptNotion = null;
		conceptFromPrecondition = null;
		//system2screenNotion = null;
	}
	
	public void parse() {
		RequirementsSpecificationDTO reqSpec = scFacade.getMainCase().getRequirementsSpecificationDTO();
		for(RequirementsPackageDTO reqPack : reqSpec.getRequirementsPackagesDTOList()){
			boolean isFound = parsePackage(reqPack);
			if(isFound){
				return;
			}
		}
	}
	
	private boolean parsePackage(RequirementsPackageDTO reqPack) {
		for(RequirementsPackageDTO reqPackChild : reqPack.getRequirementsPackages()){
			parsePackage(reqPackChild);
		}
		for(RequirementDTO req : reqPack.getRequirements()){
			if(req instanceof UseCaseDTO){
				boolean isFound = parseUseCase((UseCaseDTO)req);
				if(isFound){
					return true;
				}
			}
		}
		return false;
	}
	
	private boolean parseUseCase(UseCaseDTO uc) {
		for(ConstrainedLanguageScenarioDTO scen : uc.getConstrainedLanguageScenarioDTOList()){
			for(ConstrainedLanguageSentenceDTO sentence : scen.getScenarioSentenceList()){
				if(sentence instanceof SVOSentenceDTO){
					int sentenceNoFound = findSystem2SystemSVOSentence((SVOSentenceDTO)sentence);
					if(sentenceNoFound != -1){
						
						parseLocalContext(sentenceNoFound, (SVOSentenceDTO)sentence, scen);
						
						return true;
					}
				}
			}
		}
		return false;
	}

	private int findSystem2SystemSVOSentence(SVOSentenceDTO svoSentence) {
		NounPhraseDTO subject = svoSentence.getSubject();
		VerbPhraseDTO predicate = svoSentence.getPredicate();
		
		if(predicate != null){
			//SVO
			if(predicate instanceof SimpleVerbPhraseDTO){
				SimpleVerbPhraseDTO svp = (SimpleVerbPhraseDTO) predicate;
				VerbDTO verb = svp.getVerb();
				
				if(verb != null){
					String verbName = verb.getName();
					String refVerbName = simpleVerbPhrase != null ? simpleVerbPhrase.getVerb().getName() : "";
					
					if(verbName.startsWith(refVerbName)){
						NounPhraseDTO np = svp.getObject();
						
						if(np != null){
							//String nounText = np.getNounText();
							//String refNounText = simpleVerbPhrase != null ? simpleVerbPhrase.getObject().getNounText() : "";
							
							//if(refNounText.equalsIgnoreCase(nounText)){
							if(np.equals(simpleVerbPhrase.getObject())){
								NounDTO noun = subject.getNoun();
								
								//service layer operations derives only from System->System sentences (JGWNLServer must be running)
								if(scFacade.getFacade().getSystemElementDTO(noun) != null){
									isSystem2SystemSentenceType = true;
									ConstrainedLanguageScenarioDTO parent = svoSentence.getOwningScenarios().get(0);
									if(parent != null){
										int svoSentenceNo = parent.getScenarioSentenceList().indexOf(svoSentence);
										return svoSentenceNo;
									}
								}
								//at least verb phrase was found among scenarios
								else{
									verbPhraseWasFoundInScenarios = true;
								}
							}
						}
					}
				}
			}
			//SVOO
			else if(predicate instanceof ComplexVerbPhraseDTO){
				ComplexVerbPhraseDTO cvp = (ComplexVerbPhraseDTO) predicate;
				SimpleVerbPhraseDTO svp = cvp.getSimpleVerbPhrase();
				SimpleVerbPhraseDTO refSvp = complexVerbPhrase != null ? complexVerbPhrase.getSimpleVerbPhrase() : null;
				
				if(svp != null && refSvp != null){
					VerbDTO verb = svp.getVerb();
					
					if(verb != null && refSvp.getVerb() != null){
						String verbName = verb.getName();
						String refVerbName = refSvp.getVerb().getName();
						
						if(verbName.startsWith(refVerbName)){
							NounPhraseDTO snp = svp.getObject();
							
							if(snp != null && refSvp.getObject() != null){
								//String nounText = snp.getNounText();
								//String refNounText = refSvp.getObject().getNounText();
								
								//if(refNounText.equals(nounText)){
								if(snp.equals(refSvp.getObject())){
									NounDTO noun = subject.getNoun();
									
									//service layer operations derives only from System->System sentences (JGWNLServer must be running)
									if(scFacade.getFacade().getSystemElementDTO(noun) != null){
										isSystem2SystemSentenceType = true;
										ConstrainedLanguageScenarioDTO parent = svoSentence.getOwningScenarios().get(0);
										if(parent != null){
											int svoSentenceNo = parent.getScenarioSentenceList().indexOf(svoSentence);
											return svoSentenceNo;
										}
									}
									//at least verb phrase was found among scenarios
									else{
										verbPhraseWasFoundInScenarios = true;
									}
								}
							}
						}
					}
				}
				
				/*NounPhraseDTO cnp = cvp.getObject();
				NounPhraseDTO refCnp = complexVerbPhrase != null ? complexVerbPhrase.getObject() : null;
				if(cnp != null){
					String nounText = cnp.getNounText();
					String refNounText = refCnp.getNounText();
				}*/
			}
		}
		
		return -1;
	}
	
	/**
	 *Helps to determine parameters and return type for System->System operations to generate
	 */
	private void parseLocalContext(int start, SVOSentenceDTO refSentence, ConstrainedLanguageScenarioDTO scenario) {
		
		/*for(int i = 1; i < start; i++){
			ConstrainedLanguageSentenceDTO sentence = scenario.getScenarioSentenceList().get(i);
			
			if(sentence instanceof SVOSentenceDTO){
				SVOSentenceDTO svoSentence = (SVOSentenceDTO) sentence;
				NounPhraseDTO subject = svoSentence.getSubject();
				NounDTO noun = subject.getNoun();
				
				//find sentence of type System->Screen
				if(scFacade.getFacade().getSystemElementDTO(noun) != null){
					VerbPhraseDTO predicate = svoSentence.getPredicate();
					
					if(predicate != null){
						//SVO
						if(predicate instanceof SimpleVerbPhraseDTO){
							SimpleVerbPhraseDTO svp = (SimpleVerbPhraseDTO) predicate;
							
							NounPhraseDTO np = svp.getObject();
							
							if(np != null){

								if(scFacade.getFacade().getNotionDTO(np) != null){
									NotionDTO sys2sysNotion = scFacade.getFacade().getNotionDTO(np);
									
									//System->Screen
									if(sys2sysNotion.getType().equals(NotionTypesEnum.Screen.tag())){
										system2screenNotion = sys2sysNotion;
									}
								}
							}
							
							
						}
					}
				}
				
			}
		}*/
		
		for(int i = start-1; i >= 1; i--){
			paramIsEmpty = false;
			ConstrainedLanguageSentenceDTO sentence = scenario.getScenarioSentenceList().get(i);
			
			if(sentence instanceof SVOSentenceDTO){
				SVOSentenceDTO svoSentence = (SVOSentenceDTO) sentence;
				NounPhraseDTO subject = svoSentence.getSubject();
				NounDTO noun = subject.getNoun();
				
				//sentence of type Actor->...
				if(scFacade.getFacade().getActorDTO(noun) != null){
					VerbPhraseDTO predicate = svoSentence.getPredicate();
					
					if(predicate != null){
						
						//SVO
						if(predicate instanceof SimpleVerbPhraseDTO){
							SimpleVerbPhraseDTO svp = (SimpleVerbPhraseDTO) predicate;
					
							NounPhraseDTO np = svp.getObject();

							if(np != null){
								NounPhraseDTO refNounPhrase = null;
								
								if(refSentence.getPredicate() instanceof SimpleVerbPhraseDTO){
									refNounPhrase = refSentence.getPredicate().getObject();
								}
								
								if(scFacade.getFacade().getNotionDTO(np) != null && scFacade.getFacade().getNotionDTO(refNounPhrase) != null){
									NotionDTO act2notion = scFacade.getFacade().getNotionDTO(np);
									NotionDTO sys2sysNotion = scFacade.getFacade().getNotionDTO(refNounPhrase);
									
									//Actor->Entity
									if(act2notion.getType().equals("")){
										paramIsEntityType = true;
										actor2conceptNotion = act2notion;
									}
									//Actor->Value object (simple view)
									else if(act2notion.getType().equals(NotionTypesEnum.Simple_View.tag())){
										paramIsValueObjectType = true;
										actor2conceptNotion = act2notion;
									}
									//Actor->Frame
									else if(act2notion.getType().equals(NotionTypesEnum.Screen.tag())){
										paramIsFrameType = true;
										actor2conceptNotion = act2notion;
									}
									//no suitable sentence found (Actor->Trigger | Actor->other UI element)
									else{
										paramIsEmpty = true;
									}
									
									
									//System->List (list view)
									if(sys2sysNotion.getType().equals(NotionTypesEnum.List_View.tag())){
										
										if(paramIsEntityType()){
											//Find operation
											returnIsValueObject = true;
											return;
										}
										else if(paramIsValueObjectType()){
											//Find operation
											returnIsValueObject = true;
											checkForConceptInPrecondition(scenario.getScenarioSentenceList().get(0));
											return;
										}
										else if(paramIsFrameType()){
											//Find operation
											returnIsValueObject = true;
											return;
										}
										else if(paramIsEmpty()){
											//Read operation
											returnIsValueObject = true;
											checkForConceptInPrecondition(scenario.getScenarioSentenceList().get(0));
										}
									}
									//System->Entity
									else if(sys2sysNotion.getType().equals("")){
										
										if(paramIsEntityType()){
											//Create, Update or Delete operation
											returnIsVoid = true;
											return;
										}
										else if(paramIsValueObjectType()){
											//Read, Create, Update or Delete operation
											return;
										}
										else if(paramIsFrameType()){
											//Read, Create, Update or Delete operation
											return;
										}
										else if(paramIsEmpty()){
											//error - no parameter
										}
									}
									//System->Value object (simple view)
									else if(sys2sysNotion.getType().equals(NotionTypesEnum.Simple_View.tag())){
										
										if(paramIsEntityType()){
											//Read, Create, Update or Delete operation
											return;
										}
										else if(paramIsValueObjectType()){
											//Create, Update or Delete operation
											returnIsVoid = true;
											checkForConceptInPrecondition(scenario.getScenarioSentenceList().get(0));
											return;
										}
										else if(paramIsFrameType()){
											//Create, Update or Delete operation
											returnIsVoid = true;
											checkForConceptInPrecondition(scenario.getScenarioSentenceList().get(0));
											return;
										}
										else if(paramIsEmpty()){
											checkForConceptInPrecondition(scenario.getScenarioSentenceList().get(0));
										}
									}
									
								}
							}
									
						}
						
					}
					
				}
			}
			
		}
	}
	
	private void checkForConceptInPrecondition(ConstrainedLanguageSentenceDTO sentence) {
		if(sentence instanceof PreconditionSentenceDTO){
			PreconditionSentenceDTO precondSentence = (PreconditionSentenceDTO) sentence;
			String sentenceText = precondSentence.getSentenceText();
			if(sentenceText != null && !sentenceText.isEmpty()){
				int beginIdx = sentenceText.indexOf(">");
				int endIdx = sentenceText.indexOf("</");
				String conceptName = sentenceText.substring(beginIdx+1, endIdx);
				if(conceptName != null && !conceptName.isEmpty()){
					NotionDTO concept = scFacade.getFacade().getNotionDTO(new String[]{conceptName});
					if(concept != null){
						conceptIsInPrecondition = true;
						conceptFromPrecondition = concept;
					}
				}
			}
		}
	}
}
