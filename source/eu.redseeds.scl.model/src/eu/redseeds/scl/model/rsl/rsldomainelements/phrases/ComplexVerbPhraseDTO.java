package eu.redseeds.scl.model.rsl.rsldomainelements.phrases;

import eu.redseeds.scl.model.rsl.rsldomainelements.terms.PrepositionDTO;


public interface ComplexVerbPhraseDTO extends VerbPhraseDTO{
	PrepositionDTO getPreposition();
	void setPreposition(PrepositionDTO prep);
	
	SimpleVerbPhraseDTO getSimpleVerbPhrase();
	void setSimpleVerbPhrase(SimpleVerbPhraseDTO simplvf);

}