package eu.redseeds.scl.model.rsl.rsldomainelements.phrases;

import eu.redseeds.scl.model.rsl.rsldomainelements.terms.VerbDTO;


public interface SimpleVerbPhraseDTO extends VerbPhraseDTO{
	
	VerbDTO getVerb();
	void setVerb(VerbDTO verb);
	void connectToActionStereotype();

}