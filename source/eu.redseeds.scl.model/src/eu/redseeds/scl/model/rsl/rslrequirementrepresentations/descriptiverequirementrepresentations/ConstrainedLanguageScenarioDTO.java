package eu.redseeds.scl.model.rsl.rslrequirementrepresentations.descriptiverequirementrepresentations;

import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.constrainedlanguagesentences.ConstrainedLanguageSentenceDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.UseCaseDTO;


public interface ConstrainedLanguageScenarioDTO {

	public void setName(String name);
	
	public String getName();
	
	public String toString();
	
	public void addScenarioStep(ConstrainedLanguageSentenceDTO step);
	
	public void removeScenarioStep(ConstrainedLanguageSentenceDTO step);
	
	public void insertScenarioStepAfter(ConstrainedLanguageSentenceDTO after,ConstrainedLanguageSentenceDTO step);
	
	public ConstrainedLanguageSentenceDTO getPreviousSentence(ConstrainedLanguageSentenceDTO curent);
	
	public ConstrainedLanguageSentenceDTO getLastSentence();
	
	public java.util.List<ConstrainedLanguageSentenceDTO> getScenarioSentenceList();

	public UseCaseDTO getParent();	
	
	public void delete();
	
	public ConstrainedLanguageSentenceDTO getNextSentence(ConstrainedLanguageSentenceDTO curent);
	
}