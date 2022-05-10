package eu.remics.recovery.model.preferences;

public interface INameConverter {

	/**
	 * Returns name used in sentences
	 * 
	 * @param name base name
	 * @return name used in sentences
	 */
    public String convertName(String name);
    /**
     * Returns SimpleName value used in map
     * 
     * @param name base name
     * @return SimpleName value used in map
     */
    public String getSimpleName(String name);
    /**
     * Returns value of language dependent property used in map
     * 
     * @param name base name
     * @return value of language dependent property used in map
     */
    public String getValue(String name);
	
}
