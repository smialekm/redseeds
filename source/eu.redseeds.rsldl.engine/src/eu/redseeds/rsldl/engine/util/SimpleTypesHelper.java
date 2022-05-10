package eu.redseeds.rsldl.engine.util;

import rsldl.DLEntity;
import rsldl.DLInheritanceCondition;
import rsldl.DLNotion;
import rsldl.DLProperty;
import rsldl.DLPropertyValueType;

public class SimpleTypesHelper {

	public static boolean isSimpleType(DLNotion n) {
		if (n instanceof DLProperty)
			return !DLPropertyValueType.SET.equals(((DLProperty) n).getValueType()) && !DLPropertyValueType.ORDERED_SET.equals(((DLProperty) n).getValueType()) && ((DLProperty) n).getAllAttributes().isEmpty();
		if (!n.getAllAttributes().isEmpty())
			return false;
		for (DLInheritanceCondition ic:n.getInheritanceConditions())
			for (DLNotion p:ic.getParents())
				if (isSimpleType(p))
					return true;
		return false;
	}
	
	public static DLNotion getInheritedValueTypeSource(DLEntity e) {
		for(DLInheritanceCondition ic:e.getInheritanceConditions())
			if(1==ic.getParents().size()) {
				DLNotion n;
				if (ic.getParents().get(0) instanceof DLProperty || isSimpleType(ic.getParents().get(0)))
					return ic.getParents().get(0);
				else if(null!=(n=getInheritedValueTypeSource((DLEntity) ic.getParents().get(0))))
					return n;
			}	
		return null;
	}
	
}
