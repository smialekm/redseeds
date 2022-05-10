package eu.redseeds.scl.model.rsl.rsldomainelements.notions;

import eu.redseeds.scl.rsl.rsldomainelements.notions.AttributeDataTypes;

public enum AttributeDataTypesEnum {
	String("Text",AttributeDataTypes.STRING,"dtstring"),
	Integer("Number",AttributeDataTypes.INTEGER,"dtinteger"),
	Boolean("True/False",AttributeDataTypes.BOOLEAN,"dtboolean"),
	Date("Date",AttributeDataTypes.DATE,"dtdate"),
	Time("Time",AttributeDataTypes.TIME,"dttime"),
	Float("Floating point number",AttributeDataTypes.FLOAT,"dtfloat"),
	Description("Description",AttributeDataTypes.STRING,"dtdescription"),
	Password("Password",AttributeDataTypes.STRING,"dtpassword");
	
	public final static String EMPTY = "(none)";
	
	private String name;
	private String tag;
	private AttributeDataTypes type;

	AttributeDataTypesEnum(String name, AttributeDataTypes type, String tag) {
        this.name=name;
        this.type=type;
        this.tag=tag;
    }
	
	public static String[] tags(){
		String[] tags = new String[values().length];
		for (int i=0;i<values().length;i++) tags[i]=values()[i].tag();
		return tags;
	}
	
    public String getName() { return name; }
    public String tag() { return tag; }
    public AttributeDataTypes getType() { return type; }
    @Override
    public java.lang.String toString() {
    	return type.toString();
    }
    
    public static String getExtendedDataTypeToDisplay(NotionDTO not){
    	for (AttributeDataTypesEnum val:values())
    		if (val.tag().equals(not.getExtendedDataType()) || not.getExtendedDataType().isEmpty() && null!=not.getDataType() && val.getType().equals(not.getDataType().getTypeName()))
    			return val.getName();
    	return EMPTY;
    }
    
    public static AttributeDataTypesEnum getAttributeDataTypeByTag(String tag) {
		for (AttributeDataTypesEnum v:values())
			if (v.tag().equals(tag))
				return v;
		return null;
	}
    
}