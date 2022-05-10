package eu.redseeds.scl.model.impl.rsl.rsldomainelements.notions;

import de.uni_koblenz.jgralab.Graph;
import eu.redseeds.scl.impl.rsl.rsldomainelements.notions.PrimitiveDataTypeImpl;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.PrimitiveDataTypeDTO;
import eu.redseeds.scl.rsl.rsldomainelements.notions.AttributeDataType;
import eu.redseeds.scl.rsl.rsldomainelements.notions.AttributeDataTypes;

public class PrimitiveDataTypeDTOImpl extends PrimitiveDataTypeImpl implements
		PrimitiveDataTypeDTO, AttributeDataType {

	public PrimitiveDataTypeDTOImpl(int arg0, Graph arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setTypeName(AttributeDataTypes newType) {
		super.setTypeName(newType);

	}
	
	@Override
	public AttributeDataTypes getTypeName() {
		return super.getTypeName();
	}

}
