package eu.redseeds.scl.model.impl.sdsl;

import java.util.ArrayList;
import java.util.List;

import de.uni_koblenz.jgralab.Graph;
import eu.redseeds.scl.impl.uml.usecases.ActorImpl;
import eu.redseeds.scl.model.sdsl.ActorDTO;
import eu.redseeds.scl.model.sdsl.PackageDTO;
import eu.redseeds.scl.model.traceability.TraceabilityLinkDTO;
import eu.redseeds.scl.model.traceability.TraceableObjectDTO;
import eu.redseeds.scl.sclkernel.IsAllocatedTo;
import eu.redseeds.scl.sclkernel.IsDependentOn;

public class ActorDTOImpl extends ActorImpl implements ActorDTO, TraceableObjectDTO {

	public ActorDTOImpl(int arg0, Graph arg1) {
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

}
