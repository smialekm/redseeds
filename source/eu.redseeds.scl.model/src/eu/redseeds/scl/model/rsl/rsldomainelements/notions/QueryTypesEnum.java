package eu.redseeds.scl.model.rsl.rsldomainelements.notions;

import java.util.Arrays;

public enum QueryTypesEnum {
	
	Get("Get"),
	Compute("Compute"),
	Find("Find"),
	Transform("Transform to"),
	Create("Create"),
	Read("Read"),
	Update("Update"),
	Delete("Delete"),
	Validate("Validate");
	
	public static QueryTypesEnum[] values(ActionTypesEnum actionType){
		if (null==actionType)
			return values();
		if (ActionTypesEnum.Query.equals(actionType))
			return new QueryTypesEnum[]{Get,Compute,Find};
		if (ActionTypesEnum.Transform.equals(actionType))
			return new QueryTypesEnum[]{Transform};
		if (ActionTypesEnum.Create.equals(actionType))
			return new QueryTypesEnum[]{Create};
		if (ActionTypesEnum.Read.equals(actionType))
			return new QueryTypesEnum[]{Read};
		if (ActionTypesEnum.Update.equals(actionType))
			return new QueryTypesEnum[]{Update};
		if (ActionTypesEnum.Delete.equals(actionType))
			return new QueryTypesEnum[]{Delete};
		if (ActionTypesEnum.Validate.equals(actionType))
			return new QueryTypesEnum[]{Validate};
		return new QueryTypesEnum[]{};
	}
	
	public static String[] names(ActionTypesEnum actionType){
		String[] tags = new String[values(actionType).length];
		for (int i=0;i<values(actionType).length;i++) tags[i]=values(actionType)[i].toString();
		return tags;
	}
	
	public static String[] names(){
		String[] tags = new String[values().length];
		for (int i=0;i<values().length;i++) tags[i]=values()[i].toString();
		return tags;
	}
	
	public static QueryTypesEnum getQueryTypeByName(String name){
    	for (QueryTypesEnum at:values())
    		if (at.toString().equals(name))
    			return at;
    	return null;
    }
	
	public ActionTypesEnum getActionType(){
		for (ActionTypesEnum at:ActionTypesEnum.values())
			if (Arrays.asList(values(at)).contains(this))
				return at;
		return null;
	}
	
	private String name;
	
	QueryTypesEnum(String name){
		this.name=name;
	}
	
	@Override
    public java.lang.String toString() {
    	return name;
    }
	
}
