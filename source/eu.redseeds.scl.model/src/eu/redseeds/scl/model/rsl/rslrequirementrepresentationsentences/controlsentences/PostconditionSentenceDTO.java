package eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.controlsentences;

import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.constrainedlanguagesentences.ConstrainedLanguageSentenceDTO;


public interface PostconditionSentenceDTO extends ConstrainedLanguageSentenceDTO {
	public void setSuccess(boolean isSuccess);
	public boolean isSuccess();
	String getSentenceText();
	void setSentenceText(String text);
}