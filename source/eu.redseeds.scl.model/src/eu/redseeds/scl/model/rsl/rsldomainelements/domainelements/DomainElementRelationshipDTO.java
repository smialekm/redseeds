package eu.redseeds.scl.model.rsl.rsldomainelements.domainelements;

public interface DomainElementRelationshipDTO {
	
	public static final String DEFAULT_MULTIPLICITY = "1";
	
	/**
	 * @return Object (either instance of ActorDTO, NotionDTO or SystemElementDTO)
	 */
	public Object getSource();
	
	/**
	 * @return Object (either instance of ActorDTO, NotionDTO or SystemElementDTO)
	 */
	public Object getTarget();
	
	/**
	 * @param elem Object (either instance of ActorDTO, NotionDTO or SystemElementDTO)
	 */
	public void setSource(Object elem);
	
	/**
	 * @param elem Object (either instance of ActorDTO, NotionDTO or SystemElementDTO)
	 */
	public void setTarget(Object elem);
	
	public String getSourceMultiplicity();
	
	public String getTargetMultiplicity();
	
	public void setSourceMultiplicity(String multiplicityString);
	
	public void setTargetMultiplicity(String multiplicityString);
	
	public void delete();
	
	public boolean isDirected();
	
	public void setDirected(boolean directed);

}
