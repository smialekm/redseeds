package eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications;

import java.util.List;

import org.eclipse.ui.IActionFilter;

import eu.redseeds.scl.model.rsl.rsldomainelements.domainelements.DomainSpecificationDTO;
import eu.redseeds.scl.model.sclkernel.SoftwareCaseArtifact;


public interface RequirementsSpecificationDTO extends SoftwareCaseArtifact, IActionFilter{
	
	public void addRequirementsPackageDTO(RequirementsPackageDTO reqPackage);
	List<RequirementsPackageDTO> getRequirementsPackagesDTOList(); 
	
	DomainSpecificationDTO getDomainSpecificationDTO(); 
	
	public String toString();

}