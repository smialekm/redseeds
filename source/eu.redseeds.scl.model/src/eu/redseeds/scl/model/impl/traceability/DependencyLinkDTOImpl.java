package eu.redseeds.scl.model.impl.traceability;

import de.uni_koblenz.jgralab.Graph;
import eu.redseeds.scl.impl.sclkernel.IsDependentOnImpl;
import eu.redseeds.scl.model.traceability.TraceabilityLinkDTO;

public class DependencyLinkDTOImpl extends IsDependentOnImpl implements TraceabilityLinkDTO {

	public DependencyLinkDTOImpl(int arg0, Graph arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object getSource() {
		if(super.getDependencySourceList().size() > 0) {
			return super.getDependencySourceList().get(0);
		}
		return null;
	}

	@Override
	public Object getTarget() {
		if(super.getDependencyTargetList().size() > 0) {
			return super.getDependencyTargetList().get(0);
		}
		return null;
	}

}
