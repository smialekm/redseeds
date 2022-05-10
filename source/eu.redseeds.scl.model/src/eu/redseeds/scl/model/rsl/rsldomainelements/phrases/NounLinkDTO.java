package eu.redseeds.scl.model.rsl.rsldomainelements.phrases;

public interface NounLinkDTO {
	
	/**
	 * Used when renaming domain elements 
	 * @return new value that will replace old value for this noun link
	 */
	String getNewValue();
	
	/**
	 * Used when renaming domain elements 
	 * @param newValue
	 */
	void setNewValue(String newValue);
	
	String getValue();

}
