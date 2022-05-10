package eu.redseeds.scl.model.rsl.rsldomainelements.notions;

public interface NotionSpecialisationDTO {
	
	public NotionDTO getGeneralNotion();
	
	public NotionDTO getSpecialisedNotion();
	
	public void setGeneralNotion(NotionDTO notion);
	
	public void setSpecialisedNotion(NotionDTO notion);
	
	public void delete();
	
	/**
	 * Reverses the direction of a specialisation
	 */
	public void reverse();

}
