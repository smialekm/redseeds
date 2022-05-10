package eu.redseeds.scl.model.sclkernel;


import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementsSpecificationDTO;


public interface SoftwareCaseDTO {

	RequirementsSpecificationDTO getRequirementsSpecificationDTO();
	
	ArchitecturalModelDTO getArchitecturalModelDTO();
	
	DetailedDesignModelDTO getDetailedDesignModelDTO(); 
	
	public void setName(String name);
	
	public String getName();
	
	public String toString();

}