package eu.redseeds.scl.model.rsl.rsldomainelements.terms;


public interface NounDTO extends  TermDTO {
	void setName(String name);
	String getName();
	public boolean equals(NounDTO noun);

}