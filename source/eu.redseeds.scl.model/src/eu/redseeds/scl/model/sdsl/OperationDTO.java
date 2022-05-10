package eu.redseeds.scl.model.sdsl;

public interface OperationDTO {
	
	String getName();
	
	void setName(String name);
	
	String toString();
	
	/**
	 * Returns "+" for public, "-" for private, "#" for protected, null for others
	 * @return
	 */
	String getVisibilityString();
	
	/**
	 * Return comma-separated list of parameters [name : type]
	 * @return
	 */
	String getParameters();
	
//	Object getParent();

}
