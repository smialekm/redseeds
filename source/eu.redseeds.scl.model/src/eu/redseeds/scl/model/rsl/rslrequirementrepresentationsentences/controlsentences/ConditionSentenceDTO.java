package eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.controlsentences;

import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.constrainedlanguagesentences.ConstrainedLanguageSentenceDTO;


public interface ConditionSentenceDTO extends ConstrainedLanguageSentenceDTO {
	public void setSentenceText(String text);
	public String getSentenceText();
}