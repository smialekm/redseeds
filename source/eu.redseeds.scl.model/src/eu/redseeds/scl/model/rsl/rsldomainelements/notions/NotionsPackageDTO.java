package eu.redseeds.scl.model.rsl.rsldomainelements.notions;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;

import eu.redseeds.scl.model.rsl.rsldomainelements.DomainElementsPackageDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.domainelements.DomainSpecificationDTO;


public interface NotionsPackageDTO extends DomainElementsPackageDTO{

	public void setName(String name);

	public String getName();

	public void addNotionsPackageDTO(NotionsPackageDTO notionsPackage);

	public List<NotionsPackageDTO> getNotionsPackageDTOList();

	public void addNotionDTO(NotionDTO notion);

	public List<NotionDTO> getNotionDTOList();

	public void delete();

	public void deleteRecursively();

	public String toString();

	public NotionsPackageDTO getParent();

	public void setParent(NotionsPackageDTO pack);

	/**
	 * removes given notion from children list of this package
	 * @param child
	 */
	public void removeChildNotionDTO(NotionDTO child);

	/**
	 * removes given notions package from children list of this package
	 * @param child
	 */
	public void removeChildNotionsPackageDTO(NotionsPackageDTO child);

	public String getSpecificationPath();

	/**
	 * if this a package is a root Domain Specification package this gets the specification (null otherwise)
	 * @return
	 */
	public DomainSpecificationDTO getDomainSpecificationParent();
	
	public ArrayList<IFile> getNotionsDiagrams();

}