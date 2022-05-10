package eu.redseeds.scl.model.impl.traceability;

import de.uni_koblenz.jgralab.Graph;
import eu.redseeds.scl.impl.sclkernel.IsAllocatedToImpl;
import eu.redseeds.scl.model.traceability.TraceabilityLinkDTO;

public class TraceabilityLinkDTOImpl extends IsAllocatedToImpl implements TraceabilityLinkDTO {

	public TraceabilityLinkDTOImpl(int arg0, Graph arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object getSource() {
		if(super.getAllocationSourceList().size() > 0) {
			return super.getAllocationSourceList().get(0);
		}
		return null;
	}

	@Override
	public Object getTarget() {
		if(super.getAllocationTargetList().size() > 0) {
			return super.getAllocationTargetList().get(0);
		}
		return null;
	}

}
