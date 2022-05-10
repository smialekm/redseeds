package eu.redseeds.scl.model.rsl.rsldomainelements.actors;

import java.util.List;

import eu.redseeds.scl.model.rsl.rsldomainelements.DomainElementsPackageDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.domainelements.DomainSpecificationDTO;

public interface ActorsPackageDTO extends DomainElementsPackageDTO{

	public String getName();

	public List<ActorsPackageDTO> getActorsPackageDTOList();

	public List<ActorDTO> getActorDTOList();

	public void delete();

	public void deleteRecursively();

	public void addActorDTO(ActorDTO actor);

	public void setName(String name);

	public void addActorsPackageDTO(ActorsPackageDTO actorsPackage);

	/**
	 * removes given requirement from children list of this package
	 * @param actor
	 */
	public void removeChildActorDTO(ActorDTO actor);

	/**
	 * removes given requirements package from children list of this package
	 * @param actPack
	 */
	public void removeChildActorsPackageDTO(ActorsPackageDTO actPack);

	public ActorsPackageDTO getParent();

	public void setParent(ActorsPackageDTO parent);

	public String toString();

	public String getSpecificationPath();

	/**
	 * if this a package is a root Domain Specification package this gets the specification (null otherwise)
	 * @return
	 */
	public DomainSpecificationDTO getDomainSpecificationParent();


}