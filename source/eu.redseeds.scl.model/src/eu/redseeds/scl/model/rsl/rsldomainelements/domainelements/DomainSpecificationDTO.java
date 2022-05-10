package eu.redseeds.scl.model.rsl.rsldomainelements.domainelements;

import java.util.List;

import eu.redseeds.scl.model.rsl.rsldomainelements.actors.ActorsPackageDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionsPackageDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.systemelements.SystemElementsPackageDTO;


public interface DomainSpecificationDTO {
	
	List<NotionsPackageDTO> getNotionsPackageDTOList();
	List<ActorsPackageDTO> getActorsPackageDTOList();
	List<SystemElementsPackageDTO> getSystemElementsPackageDTOList();
	
	public void addActorsPackageDTO(ActorsPackageDTO actorsPackage);
	public void addNotionsPackageDTO(NotionsPackageDTO notionsPackage);
	public void addSystemElementsPackageDTO(SystemElementsPackageDTO systemElementsPackage);

	public String toString();
}