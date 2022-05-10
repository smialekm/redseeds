package eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications;

import java.util.List;

import eu.redseeds.scl.model.rsl.IHierarchyAware;

public interface RequirementsPackageDTO extends IHierarchyAware{

	/**
	 *
	 * @param req
	 */
	public void addRequirement(RequirementDTO req);

	/**
	 *
	 * @param reqPackage
	 */
	public void addRequirementsPackage(RequirementsPackageDTO reqPackage);

	//public List<SCLElement> getAllChildren();

	public RequirementsPackageDTO getParent();

	public String getSpecificationPath();

	public List<RequirementDTO> getRequirements();

	public List<RequirementsPackageDTO> getRequirementsPackages();

	public void setName(String name);

	public String getName();

	public void addUseCase(UseCaseDTO uc);

	public void delete();

	public void deleteRecursively();

	/**
	 * removes given requirement from children list of this package
	 * @param req
	 */
	public void removeChildRequirementDTO(RequirementDTO req);

	/**
	 * removes given requirements package from children list of this package
	 * @param reqPack
	 */
	public void removeChildRequirementsPackageDTO(RequirementsPackageDTO reqPack);

	public void setParent(RequirementsPackageDTO parent);

	public String toString();

	/**
	 * if this a package is a root Requirements Specification package this gets the specification (null otherwise)
	 * @return
	 */
	public RequirementsSpecificationDTO getRequirementsSpecificationParent();

	/**
	 * Sets new requirements specification as pacakge parent. If there is "old" reqSpec parent the link to it is broken. If there is req package parent the link to this package is broken
	 * @param reqSpec
	 */
	public void setRequirementsSpecificationParent(RequirementsSpecificationDTO reqSpec);
	
	public List<String> getStereotypes();
	
	public void addStereotype(String name);
	
	public void removeStereotype(String name);

}