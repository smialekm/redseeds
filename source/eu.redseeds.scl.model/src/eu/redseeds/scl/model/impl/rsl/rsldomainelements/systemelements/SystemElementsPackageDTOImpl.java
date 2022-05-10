package eu.redseeds.scl.model.impl.rsl.rsldomainelements.systemelements;

import java.util.ArrayList;
import java.util.List;

import de.uni_koblenz.jgralab.Edge;
import de.uni_koblenz.jgralab.EdgeDirection;
import de.uni_koblenz.jgralab.Graph;
import eu.redseeds.scl.impl.rsl.rsldomainelements.systemelements.SystemElementsPackageImpl;
import eu.redseeds.scl.model.rsl.rsldomainelements.domainelements.DomainSpecificationDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.systemelements.SystemElementDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.systemelements.SystemElementsPackageDTO;
import eu.redseeds.scl.rsl.rsldomainelements.systemelements.SystemElement;
import eu.redseeds.scl.rsl.rsldomainelements.systemelements.SystemElementsPackage;
import eu.redseeds.scl.sclkernel.SCLElementsPackage;

public class SystemElementsPackageDTOImpl extends SystemElementsPackageImpl
		implements SystemElementsPackageDTO {

	public SystemElementsPackageDTOImpl(int arg0, Graph arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getName() {
		return super.getName();
	}

	@Override
	public List<SystemElementsPackageDTO> getSystemElementsPackageDTOList() {
		List<? extends SCLElementsPackage> l = super.getNestedPackageList();
		List<SystemElementsPackageDTO> result = new ArrayList<SystemElementsPackageDTO>();
		for (SCLElementsPackage pack : l) {
			result.add((SystemElementsPackageDTO) pack);
		}
		return result;
	}

	@Override
	public List<SystemElementDTO> getSystemElementDTOList() {
		List<? extends SystemElement> l = super.getSystemElementList();
		List<SystemElementDTO> result = new ArrayList<SystemElementDTO>();
		for (SystemElement sysel : l) {
			result.add((SystemElementDTO) sysel);
		}
		return result;
	}

	@Override
	public void deleteRecursively() {
		for (SystemElementsPackageDTO sep : getSystemElementsPackageDTOList()) {
			sep.deleteRecursively();
		}
		for (SystemElementDTO se : getSystemElementDTOList()) {
			se.delete();
		}
		super.delete();
	}

	@Override
	public void addSystemElementDTO(SystemElementDTO sysElement) {
		super.addSystemElement((SystemElementDTOImpl) sysElement);

	}

	@Override
	public void addSystemElementDTO(SystemElementsPackageDTO sysElementsPackage) {
		super
				.addNestedPackage((SystemElementsPackageDTOImpl) sysElementsPackage);
	}

	@Override
	public String toString() {
		return super.getName();
	}

	@Override
	public SystemElementsPackageDTO getParent() {
		List<? extends SCLElementsPackage> pl = super.getNestingPackageList();
		SystemElementsPackageDTO parent = null;
		for (SCLElementsPackage pack : pl) {
			parent = (SystemElementsPackageDTO) pack;
			break;
		}
		return parent;
	}

	@Override
	public void setParent(SystemElementsPackageDTO parent) {
		SystemElementsPackageDTO oldParent = null;
		if (getParent() != null) {
			oldParent = getParent();
			getParent().removeChildSystemElementsPackageDTO(this);
		}
		// check if new parent is not among children
		for (SystemElementsPackageDTO rp : getSystemElementsPackageDTOList()) {
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
		super.addNestingPackage((SystemElementsPackage) parent);
	}

	@Override
	public void removeChildSystemElementDTO(SystemElementDTO child) {
		SystemElement se = (SystemElement) child;
		SystemElementsPackage sep = this;
		for (Edge e : se
				.getPackageContainsSystemElementIncidences(EdgeDirection.IN)) {
			if (e.getAlpha() == sep) {
				e.delete();
				break;
			}
		}

	}

	@Override
	public void removeChildSystemElementsPackageDTO(
			SystemElementsPackageDTO child) {
		SystemElementsPackage sepChild = (SystemElementsPackage) child;
		SystemElementsPackage sepParent = this;
		for (Edge e : sepChild
				.getrsl$rsldomainelements$domainelements$NestingPackageContainsNestedPackageIncidences(EdgeDirection.IN)) {
			if (e.getAlpha() == sepParent) {
				e.delete();
				break;
			}
		}

	}

	@Override
	public String getSpecificationPath() {
		String path = "\\" + getName();
		SystemElementsPackageDTO parent = getParent();
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