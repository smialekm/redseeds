package eu.redseeds.scl.model.sdsl;

public interface ActorDTO {
	
	public String getName();

	public void setName(String name);

	public String toString();

	public PackageDTO getParent();
		
	public void setParent(PackageDTO parent);

}
