package eu.redseeds.scl.model.impl.sdsl;

import java.util.List;

import de.uni_koblenz.jgralab.Graph;
import eu.redseeds.scl.impl.uml.classes.kernel.OperationImpl;
import eu.redseeds.scl.model.sdsl.OperationDTO;
import eu.redseeds.scl.uml.classes.kernel.Parameter;
import eu.redseeds.scl.uml.classes.kernel.VisibilityKind;

public class OperationDTOImpl extends OperationImpl implements OperationDTO {

	public OperationDTOImpl(int arg0, Graph arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return getName();
	}

	@Override
	public String getVisibilityString() {
		if(getVisibility() == null) {
			return "";
		}
		if(getVisibility().compareTo(VisibilityKind.PRIVATE) == 0) {
			return "-";
		}
		if(getVisibility().compareTo(VisibilityKind.PUBLIC) == 0) {
			return "+";
		}
		if(getVisibility().compareTo(VisibilityKind.PROTECTED) == 0) {
			return "#";
		}
		return "";
	}
	
	@Override
	public String getName() {
		return getVisibilityString() + super.getName() + "(" + getParameters() + ")";
	}

	@Override
	public String getParameters() {
		List<? extends Parameter> params = super.getOwnedParameterList();
		String result = "";
		int i = 0;
		for(Parameter param : params) {
			if(i != 0) { //not for the first one
				result += ", ";
			}
			result += param.getName();
			if(param.getTypeList().size() > 0) {
				result += " : " + param.getTypeList().get(0);
			}
			i++;
		}
		return result;
	}

//	@Override
//	public Object getParent() {
//		if(getClassList() != null) {
//			if(getClassList().size() > 0) {
//				return getClassList().get(0); 
//			}
//		}
//		if(getInterfaceList() != null) {
//			if(getInterfaceList().size() > 0) {
//				return getInterfaceList().get(0);
//			}
//		}
//		return null;
//	}

}
