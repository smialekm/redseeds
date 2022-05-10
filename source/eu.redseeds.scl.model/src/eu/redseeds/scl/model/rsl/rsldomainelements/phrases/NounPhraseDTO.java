package eu.redseeds.scl.model.rsl.rsldomainelements.phrases;

import eu.redseeds.scl.model.rsl.rsldomainelements.terms.DeterminerDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.terms.ModifierDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.terms.NounDTO;


public interface NounPhraseDTO extends PhraseDTO {
	
	/**
	 * What kind of ID should this method return
	 */
	@Deprecated
	int getPhraseId();
	
	DeterminerDTO getDeterminer();
	void setDeterminer(DeterminerDTO det);
	
	ModifierDTO getModifier();
	void setModifier(ModifierDTO mod);
	
	NounDTO getNoun();
	void setNoun(NounDTO noun);
	
	public void setNounText(String nounText);
	public String getNounText();
	
	public boolean compareNoun(NounPhraseDTO phrase);
	
	void delete();
	
	public boolean isConsistent();
	
	public boolean isUnused();

}