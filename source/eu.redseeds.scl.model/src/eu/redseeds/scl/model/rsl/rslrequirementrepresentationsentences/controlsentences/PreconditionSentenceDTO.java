package eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.controlsentences;

import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.constrainedlanguagesentences.ConstrainedLanguageSentenceDTO;


public interface PreconditionSentenceDTO extends ConstrainedLanguageSentenceDTO {
	String getSentenceText();
	void setSentenceText(String text);

}