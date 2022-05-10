package eu.redseeds.scl.model.rsl.rsldomainelements.phrases;


public interface VerbPhraseDTO  extends PhraseDTO{
	
	NounPhraseDTO getObject();
	void setObject(NounPhraseDTO obj);
	String getUid();

}