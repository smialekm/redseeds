package eu.redseeds.scl.model.sclkernel;

import java.util.List;

import eu.redseeds.scl.model.sdsl.PackageDTO;

public interface ArchitecturalModelDTO extends SoftwareCaseArtifact {

	public String toString();
	
	public List<PackageDTO> getPackageDTOList();
	
	public void addPackage(PackageDTO pack); 
}
