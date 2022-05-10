package eu.redseeds.scl.model.rsl.rsldomainelements.phrases;

public interface PhraseDTO {

	public boolean equals(PhraseDTO phrase);
	public PhraseDTO copy(boolean basicForm);
	public void deleteRecursively();
	public void connect();
	public boolean hasSenses();
	public String getUid();
}
