package eu.redseeds.scl.model.impl.sdsl;

import java.util.ArrayList;
import java.util.List;

import de.uni_koblenz.jgralab.Graph;
import eu.redseeds.scl.impl.uml.classes.kernel.PackageImpl;
import eu.redseeds.scl.model.sdsl.ActorDTO;
import eu.redseeds.scl.model.sdsl.ClassDTO;
import eu.redseeds.scl.model.sdsl.ComponentDTO;
import eu.redseeds.scl.model.sdsl.InterfaceDTO;
import eu.redseeds.scl.model.sdsl.PackageDTO;
import eu.redseeds.scl.model.traceability.TraceabilityLinkDTO;
import eu.redseeds.scl.model.traceability.TraceableObjectDTO;
import eu.redseeds.scl.sclkernel.IsAllocatedTo;
import eu.redseeds.scl.sclkernel.IsDependentOn;
import eu.redseeds.scl.uml.auxiliaryconstructs.models.Model;
import eu.redseeds.scl.uml.classes.interfaces.Interface;
import eu.redseeds.scl.uml.classes.kernel.PackagableElement;
import eu.redseeds.scl.uml.classes.kernel.Package;
import eu.redseeds.scl.uml.components.basiccomponents.Component;
import eu.redseeds.scl.uml.usecases.Actor;

public class PackageDTOImpl extends PackageImpl implements PackageDTO, TraceableObjectDTO {
	
	public PackageDTOImpl(int arg0, Graph arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void addActor(ActorDTO actor) {
		addPackagedElement((PackagableElement)actor);
	}

	@Override
	public void addClass(ClassDTO cl) {
		addPackagedElement((PackagableElement)cl);
		
	}

	@Override
	public void addComponent(ComponentDTO comp) {
		addPackagedElement((PackagableElement)comp);
		
	}

	@Override
	public void addInterface(InterfaceDTO interf) {
		addPackagedElement((PackagableElement)interf);
		
	}

	@Override
	public void addPackage(PackageDTO pack) {
		addPackagedElement((PackagableElement)pack);
	}

	@Override
	public void deleteRecursively() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public List<ActorDTO> getActorDTOList() {
		List<? extends PackagableElement> l = super.getPackagedElementList();
		List<ActorDTO> result = new ArrayList<ActorDTO>();
		for(PackagableElement elem : l) {
			if(elem instanceof Actor) {
				result.add((ActorDTO)elem);
			}
		}
		return result;
	}

	@Override
	public List<ClassDTO> getClassDTOList() {
		List<? extends PackagableElement> l = super.getPackagedElementList();
		List<ClassDTO> result = new ArrayList<ClassDTO>();
		for(PackagableElement elem : l) {
			if(elem instanceof eu.redseeds.scl.uml.classes.kernel.Class && !(elem instanceof Component) && !(elem instanceof Interface)) {
				result.add((ClassDTO)elem);
			}
		}
		return result;
	}

	@Override
	public List<ComponentDTO> getComponentDTOList() {
		List<? extends PackagableElement> l = super.getPackagedElementList();
		List<ComponentDTO> result = new ArrayList<ComponentDTO>();
		for(PackagableElement elem : l) {
			if(elem instanceof Component) {
				result.add((ComponentDTO)elem);
			}
		}
		return result;
	}

	@Override
	public List<InterfaceDTO> getInterfaceDTOList() {
		List<? extends PackagableElement> l = super.getPackagedElementList();
		List<InterfaceDTO> result = new ArrayList<InterfaceDTO>();
		for(PackagableElement elem : l) {
			if(elem instanceof Interface) {
				result.add((InterfaceDTO)elem);
			}
		}
		return result;
	}
	
	@Override
	public String getName() {
		return super.getName();
	}

	@Override
	public List<PackageDTO> getPackageDTOList() {
		List<? extends PackagableElement> l = super.getPackagedElementList();
		List<PackageDTO> result = new ArrayList<PackageDTO>();
		for(PackagableElement elem : l) {
			if(elem instanceof Package) {
				result.add((PackageDTO)elem);
			}
		}
		return result;
	}

	@Override
	public PackageDTO getParent() {
		if(getOwningPackageList().size()>0) {
			if(getOwningPackageList().get(0) instanceof PackageImpl) {
				return (PackageDTO)getOwningPackageList().get(0); 
			}
		}
		return null;
	}

	@Override
	public void setParent(PackageDTO parent) {
		if(parent == null && this.getParent() == null) {
			if(getOwningPackageList().size()>0) {
				if(getOwningPackageList().get(0) instanceof Model) {
					removeOwningPackage(getOwningPackageList().get(0));
				}
			}
		}
//		addOwningPackage((Package)parent);
		
	}
	
	@Override
	public String toString() {
		return getName();
	}
	
	@Override
	public List<TraceabilityLinkDTO> getTraceabilityLinkDTOList() {
		List<TraceabilityLinkDTO> list = new ArrayList<TraceabilityLinkDTO>();
		for(IsAllocatedTo rel : super.getAllocationToRSLList()) {
			list.add((TraceabilityLinkDTO)rel);
		}
		for(IsAllocatedTo rel : super.getAllocationToUMLList()) {
			list.add((TraceabilityLinkDTO)rel);
		}
		for(IsDependentOn rel : super.getClientDependentOnList()) {
			list.add((TraceabilityLinkDTO)rel);
		}
		for(IsDependentOn rel : super.getSupplierDependentOnList()) {
			list.add((TraceabilityLinkDTO)rel);
		}
		return list;
	}

}
