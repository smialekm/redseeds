package eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.svosentences;

import eu.redseeds.scl.model.rsl.rsldomainelements.domainelements.ActorOrSystemElementDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.NounPhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.VerbPhraseDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.constrainedlanguagesentences.ConstrainedLanguageSentenceDTO;




public interface SVOSentenceDTO extends ConstrainedLanguageSentenceDTO {
	void setSentenceText(String test);
	String getSentenceText();
	void delete();
	VerbPhraseDTO getPredicate();
	void setPredicate(VerbPhraseDTO verbPhrase);
	NounPhraseDTO getSubject();
	void setSubject(NounPhraseDTO nounPhrase);	
	void setRecipient(ActorOrSystemElementDTO recipient);
	ActorOrSystemElementDTO getRecipient();
	/**
	 * return full sentence text, even for sentences with marked subject and predicate
	 * @return
	 */
	String getFullSentenceText();
	public SVOSentenceDTO copy();
}