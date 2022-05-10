package eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.constrainedlanguagesentences;

import java.util.List;

import eu.redseeds.scl.model.rsl.rslrequirementrepresentations.descriptiverequirementrepresentations.ConstrainedLanguageScenarioDTO;


public interface ConstrainedLanguageSentenceDTO {
	public List<ConstrainedLanguageScenarioDTO> getOwningScenarios();
	public int getId();
	public ConstrainedLanguageSentenceDTO copy();
	public void delete();
}