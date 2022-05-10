package eu.redseeds.scl.model.rsl.rsldomainelements.notions;

import java.util.Arrays;
import java.util.List;

import eu.redseeds.common.DiagramFileHelper;

public enum ActionTypesEnum {
	Create("Create","crudcreate"),
	Read("Read","crudread"),
	Update("Update","crudupdate"),
	Delete("Delete","cruddelete"),
	Validate("Validate","actionvalidate"),
	Show("Show","actionshow"),
	Close("Close","actionclose"),
	Refresh("Refresh","actionrefresh"),
	Select("Select","actionselect"),
	Query("Query","actionquery"),
	Transform("Transform","actiontransform");
	
	public final static String EMPTY = "n/a";
	
	public static ActionTypesEnum[] values(String type){
		if (type.isEmpty() || NotionTypesEnum.Simple_View.tag().equals(type) || NotionTypesEnum.List_View.tag().equals(type) || NotionTypesEnum.Tree_View.tag().equals(type)){
			if (DiagramFileHelper.isEnableRSLDL())
				return new ActionTypesEnum[]{Create,Read,Update,Delete,Validate,Query,Transform};
			return new ActionTypesEnum[]{Create,Read,Update,Delete,Validate};
		}
		if (NotionTypesEnum.Screen.tag().equals(type)){
			return new ActionTypesEnum[]{Show,Close,Refresh};
		}
		if (NotionTypesEnum.Message.tag().equals(type) || NotionTypesEnum.Confirmation_Dialog.tag().equals(type)){
			return new ActionTypesEnum[]{Show};
		}
		if (NotionTypesEnum.Trigger.tag().equals(type)){
			return new ActionTypesEnum[]{Select};
		}
		return new ActionTypesEnum[]{};
	}
	
	public static ActionTypesEnum[] values(String type, boolean actor){
		if (actor){
			if (NotionTypesEnum.Trigger.tag().equals(type)){
				return new ActionTypesEnum[]{Select};
			}
		} else {
			if (type.isEmpty() || NotionTypesEnum.Simple_View.tag().equals(type) || NotionTypesEnum.List_View.tag().equals(type) || NotionTypesEnum.Tree_View.tag().equals(type)){
				if (DiagramFileHelper.isEnableRSLDL())
					return new ActionTypesEnum[]{Create,Read,Update,Delete,Validate,Query,Transform};
				return new ActionTypesEnum[]{Create,Read,Update,Delete,Validate};
			}
			if (NotionTypesEnum.Screen.tag().equals(type)){
				return new ActionTypesEnum[]{Show,Close,Refresh};
			}
			if (NotionTypesEnum.Message.tag().equals(type) || NotionTypesEnum.Confirmation_Dialog.tag().equals(type)){
				return new ActionTypesEnum[]{Show};
			}
		}
		return new ActionTypesEnum[]{};
	}
	
	public static ActionTypesEnum[] cruds(){
		return new ActionTypesEnum[]{Create,Read,Update,Delete,Validate};
	}
	
	public static String[] tags(String type){
		String[] tags = new String[values(type).length];
		for (int i=0;i<values(type).length;i++) tags[i]=values(type)[i].tag();
		return tags;
	}
	
	public static String[] tags(){
		String[] tags = new String[values().length];
		for (int i=0;i<values().length;i++) tags[i]=values()[i].tag();
		return tags;
	}
	
	public static String[] names(String type){
		String[] names = new String[values(type).length];
		for (int i=0;i<values(type).length;i++) names[i]=values(type)[i].toString();
		return names;
	}
	
	public static String[] names(){
		String[] names = new String[values().length];
		for (int i=0;i<values().length;i++) names[i]=values()[i].toString();
		return names;
	}
	
	private String name;
	private String tag;

	ActionTypesEnum(String name, String tag) {
        this.name=name;
        this.tag=tag;
    }

    public String tag() { return tag; }
    @Override
    public java.lang.String toString() {
    	return name;
    }
    
    public static String getActionTypeToDisplay(DomainStatementDTO ds){
    	for (ActionTypesEnum val:values())
    		if (val.tag().equals(ds.getActionTypeTag()))
    			return val.toString();
    	return EMPTY;
    }
    
    public static int getActionTypeNumber(DomainStatementDTO ds){
    	List<String> list = Arrays.asList(tags(ds.getParent().getType()));
    	if (list.contains(ds.getActionTypeTag()))
    		return list.indexOf(ds.getActionTypeTag())+1;
    	return 0;
    }
    
    public static ActionTypesEnum getActionTypeByTag(String name){
    	for (ActionTypesEnum at:values())
    		if (at.tag.equals(name))
    			return at;
    	return null;
    }
    
}