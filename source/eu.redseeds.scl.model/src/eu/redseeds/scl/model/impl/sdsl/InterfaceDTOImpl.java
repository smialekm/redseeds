package eu.redseeds.scl.model.impl.sdsl;

import java.util.ArrayList;
import java.util.List;

import de.uni_koblenz.jgralab.Graph;
import eu.redseeds.scl.impl.uml.classes.interfaces.InterfaceImpl;
import eu.redseeds.scl.model.sdsl.InterfaceDTO;
import eu.redseeds.scl.model.sdsl.OperationDTO;
import eu.redseeds.scl.model.sdsl.PackageDTO;
import eu.redseeds.scl.model.traceability.TraceabilityLinkDTO;
import eu.redseeds.scl.model.traceability.TraceableObjectDTO;
import eu.redseeds.scl.sclkernel.IsAllocatedTo;
import eu.redseeds.scl.sclkernel.IsDependentOn;
import eu.redseeds.scl.uml.classes.kernel.Operation;

public class InterfaceDTOImpl extends InterfaceImpl implements InterfaceDTO, TraceableObjectDTO {

	public InterfaceDTOImpl(int arg0, Graph arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public PackageDTO getParent() {
		if(getOwningPackageList().size()>0) {
			return (PackageDTO)getOwningPackageList().get(0); 
		}
		return null;
	}

	@Override
	public void setParent(PackageDTO parent) {
		// TODO Auto-generated method stub
		
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
	
	@Override
	public List<OperationDTO> getOperationDTOList() {
		List<OperationDTO> opers = new ArrayList<OperationDTO>();
		for(Operation op : this.getOperationList()) {
			opers.add((OperationDTO)op);
		}
		for(Operation op : this.getOwnedOperationList()) {
			opers.add((OperationDTO)op);
		}
		return opers;
	}

	@Override
	public void addOperation(OperationDTO operation) {
		this.addOperation((Operation)operation);
	}

}
