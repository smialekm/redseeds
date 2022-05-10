package eu.redseeds.scl.model.impl.rsl.rsldomainelements.actors;

import java.util.ArrayList;
import java.util.List;

import de.uni_koblenz.jgralab.Edge;
import de.uni_koblenz.jgralab.EdgeDirection;
import de.uni_koblenz.jgralab.Graph;
import eu.redseeds.scl.impl.rsl.rsldomainelements.actors.ActorsPackageImpl;
import eu.redseeds.scl.model.rsl.rsldomainelements.actors.ActorDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.actors.ActorsPackageDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.domainelements.DomainSpecificationDTO;
import eu.redseeds.scl.rsl.rsldomainelements.actors.Actor;
import eu.redseeds.scl.rsl.rsldomainelements.actors.ActorsPackage;
import eu.redseeds.scl.sclkernel.SCLElementsPackage;

public class ActorsPackageDTOImpl extends ActorsPackageImpl implements
		ActorsPackageDTO {

	public ActorsPackageDTOImpl(int arg0, Graph arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getName() {
		return super.getName();
	}

	@Override
	public List<ActorsPackageDTO> getActorsPackageDTOList() {
		List<? extends SCLElementsPackage> l = super.getNestedPackageList();
		List<ActorsPackageDTO> result = new ArrayList<ActorsPackageDTO>();
		for (SCLElementsPackage pack : l) {
			result.add((ActorsPackageDTO) pack);
		}
		return result;
	}

	@Override
	public List<ActorDTO> getActorDTOList() {
		List<? extends Actor> l = super.getActorList();
		List<ActorDTO> result = new ArrayList<ActorDTO>();
		for (Actor actor : l) {
			result.add((ActorDTO) actor);
		}
		return result;
	}

	@Override
	public void addActorDTO(ActorDTO actor) {
		super.addActor((ActorDTOImpl) actor);
	}

	@Override
	public void addActorsPackageDTO(ActorsPackageDTO actorsPackage) {
		super.addNestedPackage((ActorsPackageDTOImpl) actorsPackage);

	}

	@Override
	public void deleteRecursively() {
		for (ActorsPackageDTO actp : getActorsPackageDTOList()) {
			actp.deleteRecursively();
		}
		for (ActorDTO act : getActorDTOList()) {
			act.delete();
		}
		super.delete();
	}

	@Override
	public String toString() {
		return super.getName();
	}

	@Override
	public void removeChildActorDTO(ActorDTO actor) {
		Actor a = (Actor) actor;
		ActorsPackage ap = this;
		for (Edge e : a.getPackageContainsActorIncidences(EdgeDirection.IN)) {// IN
			// here
			// for
			// newest
			// gralab
			if (e.getAlpha() == ap) {
				e.delete();
				break;
			}
		}

	}

	@Override
	public void removeChildActorsPackageDTO(ActorsPackageDTO actPack) {
		ActorsPackage apChild = (ActorsPackage) actPack;
		ActorsPackage apParent = this;
		for (Edge e : apChild
				.getrsl$rsldomainelements$actors$NestingPackageContainsNestedPackageIncidences(EdgeDirection.IN)) {
			if (e.getAlpha() == apParent) {
				e.delete();
				break;
			}
		}

	}

	@Override
	public void setParent(ActorsPackageDTO parent) {
		ActorsPackageDTO oldParent = null;
		if (getParent() != null) {
			oldParent = getParent();
			getParent().removeChildActorsPackageDTO(this);
		}
		// check if new parent is not among children
		for (ActorsPackageDTO rp : getActorsPackageDTOList()) {
			if (rp.equals(parent)) {
				if (getParent() != null) {
					parent.setParent(getParent());// rewire child to this.parent
					break;
				} else {
					parent.setParent(oldParent);// rewire child to this.parent
					break;
				}
			}
		}
		super.addNestingPackage((ActorsPackage) parent);
	}

	@Override
	public ActorsPackageDTO getParent() {
		List<? extends SCLElementsPackage> npl = super.getNestingPackageList();
		ActorsPackageDTO parent = null;
		for (SCLElementsPackage p : npl) {
			if (p instanceof ActorsPackage) {
				parent = (ActorsPackageDTO) p;
				break;
			}
		}
		return parent;
	}

	@Override
	public String getSpecificationPath() {
		String path = "\\" + getName();
		ActorsPackageDTO parent = getParent();
		while (parent != null) {
			path = "\\" + parent.getName() + path;
			parent = parent.getParent();
		}
		return path;
	}

	@Override
	public DomainSpecificationDTO getDomainSpecificationParent() {
		if (getDomainSpecificationList() != null) {
			if (getDomainSpecificationList().size() > 0) {
				return (DomainSpecificationDTO) getDomainSpecificationList()
						.get(0);
			}
		}
		return null;
	}

}