package eu.redseeds.scl.model.rsl.rsldomainelements.systemelements;

import java.util.List;

import eu.redseeds.scl.model.rsl.rsldomainelements.DomainElementsPackageDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.domainelements.DomainSpecificationDTO;

public interface SystemElementsPackageDTO extends DomainElementsPackageDTO {

	public String getName();

	public List<SystemElementsPackageDTO> getSystemElementsPackageDTOList();

	public List<SystemElementDTO> getSystemElementDTOList();

	public void addSystemElementDTO(SystemElementDTO sysElement);

	public void setName(String name);

	public void addSystemElementDTO(SystemElementsPackageDTO sysElementsPackage);

	public void delete();

	public void deleteRecursively();

	public String toString();

	public SystemElementsPackageDTO getParent();

	public void setParent(SystemElementsPackageDTO pack);

	/**
	 * removes given system element from children list of this package
	 * @param child
	 */
	public void removeChildSystemElementDTO(SystemElementDTO child);

	/**
	 * removes given system elements package from children list of this package
	 * @param child
	 */
	public void removeChildSystemElementsPackageDTO(SystemElementsPackageDTO child);

	public String getSpecificationPath();

	/**
	 * if this a package is a root Domain Specification package this gets the specification (null otherwise)
	 * @return
	 */
	public DomainSpecificationDTO getDomainSpecificationParent();

}