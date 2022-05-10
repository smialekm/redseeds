package eu.redseeds.scl.model.rsl.rsldomainelements.notions;

import eu.redseeds.scl.rsl.rsldomainelements.notions.AttributeDataTypes;

public interface PrimitiveDataTypeDTO extends AttributeDataTypeDTO {
	AttributeDataTypes getTypeName();
	void setTypeName(AttributeDataTypes newType);
}
