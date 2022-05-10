package eu.redseeds.scl.model.impl.rsl.rslrequirements.requirementsspecifications;

import java.util.ArrayList;
import java.util.List;

import de.uni_koblenz.jgralab.Edge;
import de.uni_koblenz.jgralab.EdgeDirection;
import de.uni_koblenz.jgralab.Graph;
import eu.redseeds.scl.impl.SCLGraphImpl;
import eu.redseeds.scl.impl.rsl.rslrequirements.requirementsspecifications.RequirementsPackageImpl;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementsPackageDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementsSpecificationDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.UseCaseDTO;
import eu.redseeds.scl.model.sclkernel.StereotypeDTO;
import eu.redseeds.scl.model.traceability.TraceabilityLinkDTO;
import eu.redseeds.scl.model.traceability.TraceableObjectDTO;
import eu.redseeds.scl.rsl.rslkernel.elements.RepresentableElementsPackage;
import eu.redseeds.scl.rsl.rslrequirements.requirementsspecifications.RSLUseCase;
import eu.redseeds.scl.rsl.rslrequirements.requirementsspecifications.Requirement;
import eu.redseeds.scl.rsl.rslrequirements.requirementsspecifications.RequirementsPackage;
import eu.redseeds.scl.sclkernel.IsAllocatedTo;
import eu.redseeds.scl.sclkernel.SCLElementsPackage;
import eu.redseeds.scl.sclkernel.Stereotype;

public class RequirementsPackageDTOImpl extends RequirementsPackageImpl
		implements RequirementsPackageDTO, TraceableObjectDTO {

	public RequirementsPackageDTOImpl(int arg0, Graph arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * @param req
	 */
	public void addRequirement(RequirementDTO req) {
		super.addRequirement((RequirementDTOImpl) req);
	}

	/**
	 * 
	 * @param reqPackage
	 */
	public void addRequirementsPackage(RequirementsPackageDTO reqPackage) {
		// TODO nested packages in graph
		super.addNestedPackage((RequirementsPackageDTOImpl) reqPackage);
	}

	@Override
	public void addUseCase(UseCaseDTO uc) {
		super.addRequirement((UseCaseDTOImpl) uc);

	}

	public List<RequirementDTO> getRequirements() {
		List<? extends Requirement> l = super.getRequirementList();
		List<RequirementDTO> result = new ArrayList<RequirementDTO>();
		for (Requirement r : l) {
			if (r instanceof RSLUseCase) {
				result.add((UseCaseDTO) r);
			} else if (r instanceof Requirement) {
				result.add((RequirementDTO) r);
			}
		}
		return result;
	}

	public List<RequirementsPackageDTO> getRequirementsPackages() {
		List<? extends SCLElementsPackage> l = super.getNestedPackageList();
		List<RequirementsPackageDTO> result = new ArrayList<RequirementsPackageDTO>();
		for (SCLElementsPackage pack : l) {
			result.add((RequirementsPackageDTO) pack);
		}
		return result;
	}

	// public List<SCLElement> getAllChildren(){
	// return null;
	// }

	public RequirementsPackageDTO getParent() {

		List<? extends RepresentableElementsPackage> npl = super
				.getNestingPackageList();
		RequirementsPackageDTO parent = null;
		for (RepresentableElementsPackage p : npl) {
			if (p instanceof RequirementsPackage) {
				parent = (RequirementsPackageDTO) p;
				break;
			}
		}
		return parent;
	}

	@Override
	public String getName() {
		return super.getName();
	}

	@Override
	public void deleteRecursively() {
		for (RequirementsPackageDTO rp : getRequirementsPackages()) {
			rp.deleteRecursively();
		}
		for (RequirementDTO req : getRequirements()) {
			req.delete();
		}
		super.delete();

	}

	@Override
	public String toString() {
		return super.getName();
	}

	@Override
	public String getSpecificationPath() {

		String path = "\\" + getName();
		RequirementsPackageDTO parent = getParent();
		while (parent != null) {
			path = "\\" + parent.getName() + path;
			parent = parent.getParent();
		}

		return path;
	}

	@Override
	public void removeChildRequirementDTO(RequirementDTO req) {
		Requirement r = (Requirement) req;
		RequirementsPackage rp = this;
		for (Edge e : r
				.getPackageContainsRequirementIncidences(EdgeDirection.IN)) {
			if (e.getAlpha() == rp) {
				e.delete();
				break;
			}
		}
	}

	@Override
	public void removeChildRequirementsPackageDTO(RequirementsPackageDTO reqPack) {
		RequirementsPackage rpChild = (RequirementsPackage) reqPack;
		RequirementsPackage rpParent = this;
		for (Edge e : rpChild
				.getrsl$rslrequirements$requirementsspecifications$NestingPackageContainsNestedPackageIncidences(EdgeDirection.IN)) {
			if (e.getAlpha() == rpParent) {
				e.delete();
				break;
			}
		}
	}

	@Override
	public void setParent(RequirementsPackageDTO parent) {
		if (getParent() != null) {// if null - parent may be a req spec
			// check if new parent is not among children
			for (RequirementsPackageDTO rp : getRequirementsPackages()) {
				if (rp.equals(parent)) {
					parent.setParent(getParent());// rewire child to this.parent
					break;
				}
			}
			super.addNestingPackage((RequirementsPackage) parent);
			getParent().removeChildRequirementsPackageDTO(this);
		} else {
			// check if new parent is not among children
			for (RequirementsPackageDTO rp : getRequirementsPackages()) {
				if (rp.equals(parent)) {
					parent.setParent(getParent());// rewire child to this.parent
					break;
				}
			}
			super.addNestingPackage((RequirementsPackage) parent);
			for (Edge e : super
					.getRequirementsSpecificationContainsRequirementsPackageIncidences()) {
				if (e.getOmega() == this) {
					e.delete();
					break;
				}
			}
		}
	}

	@Override
	public RequirementsSpecificationDTO getRequirementsSpecificationParent() {
		if (getRequirementsSpecificationList() != null) {
			if (getRequirementsSpecificationList().size() > 0) {
				return (RequirementsSpecificationDTO) getRequirementsSpecificationList()
						.get(0);
			}
		}
		return null;
	}

	@Override
	public List<TraceabilityLinkDTO> getTraceabilityLinkDTOList() {
		List<TraceabilityLinkDTO> list = new ArrayList<TraceabilityLinkDTO>();
		for (IsAllocatedTo rel : super.getAllocationToUMLList()) {
			list.add((TraceabilityLinkDTO) rel);
		}
		return list;
	}

	@Override
	public void setRequirementsSpecificationParent(
			RequirementsSpecificationDTO reqSpec) {

		if (getParent() != null) {
			getParent().removeChildRequirementsPackageDTO(this);
		}

		if (getRequirementsSpecificationParent() != null) {
			for (Edge e : super
					.getRequirementsSpecificationContainsRequirementsPackageIncidences()) {
				if (e.getOmega() == this) {
					e.delete();
					break;
				}
			}
		}

		reqSpec.addRequirementsPackageDTO(this);

	}

	@Override
	public List<String> getStereotypes() {
		List<String> result = new ArrayList<String>();

		List<? extends Stereotype> stereotypes = super.getStereotypeList();
		if(stereotypes == null) {
			return result;
		}
		if(stereotypes.size() == 0) {
			return result;
		}
		for(Stereotype stereotype : stereotypes) {
			result.add(stereotype.getName());
		}
		return result;
	}

	@Override
	public void addStereotype(String name) {
		if(getStereotypes().contains(name)) {
			return;
		}
		
		StereotypeDTO stereotype = (StereotypeDTO)(((SCLGraphImpl)super.getGraph()).createSclkernel$Stereotype());
		stereotype.setName(name);
		super.addStereotype((Stereotype)stereotype);
	}

	@Override
	public void removeStereotype(String name) {
		List<? extends Stereotype> stereotypes = super.getStereotypeList();
		if(stereotypes == null) {
			return;
		}
		if(stereotypes.size() == 0) {
			return;
		}
		for(Stereotype stereotype : stereotypes) {
			if(stereotype.getName().equalsIgnoreCase(name)) {
				super.removeStereotype(stereotype);
				return;
			}
		}
	}

}