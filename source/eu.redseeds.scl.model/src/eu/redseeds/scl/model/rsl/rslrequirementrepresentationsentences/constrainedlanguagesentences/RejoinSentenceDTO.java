package eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.constrainedlanguagesentences;

import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.constrainedlanguagesentences.ConstrainedLanguageSentenceDTO;


public interface RejoinSentenceDTO extends ConstrainedLanguageSentenceDTO {
	
	public ConstrainedLanguageSentenceDTO getRejoinedSentence();
	public void setRejoinedSentence(ConstrainedLanguageSentenceDTO rj);
	
}