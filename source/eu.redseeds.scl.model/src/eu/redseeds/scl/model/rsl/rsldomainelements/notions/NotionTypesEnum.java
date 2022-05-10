package eu.redseeds.scl.model.rsl.rsldomainelements.notions;

public enum NotionTypesEnum {
	Screen("Screen","tagFrame"),
	Message("Message","tagMessage"),
	Confirmation_Dialog("Confirmation Dialog","tagConfirmation"),
	Trigger("Trigger","tagTrigger"),
	Attribute("Attribute","tagAttribute"),
	Option("Option","tagOption"),
	List_View("List View","tagList"),
	Simple_View("Simple View","tagNonpersistent"),
	Tree_View("Tree View","tagTree");
	
	public final static String EMPTY = "Concept";
	
	String name;
	String tag;
	
	public static String[] tags(){
		String[] tags = new String[values().length];
		for (int i=0;i<values().length;i++) tags[i]=values()[i].tag();
		return tags;
	}
	
	public static NotionTypesEnum[] domainValues(){
		return new NotionTypesEnum[]{Attribute,List_View,Simple_View,Tree_View};
	}
	
	public static NotionTypesEnum[] viewValues(){
		return new NotionTypesEnum[]{List_View,Simple_View,Tree_View};
	}
	
	public static String[] domainTags(){
		String[] tags = new String[domainValues().length];
		for (int i=0;i<domainValues().length;i++) tags[i]=domainValues()[i].tag();
		return tags;
	}
	
	public static String[] viewTags(){
		String[] tags = new String[viewValues().length];
		for (int i=0;i<viewValues().length;i++) tags[i]=viewValues()[i].tag();
		return tags;
	}
	
	NotionTypesEnum(String name, String tag){
		this.name=name;
		this.tag=tag;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	public String tag(){
		return tag;
	}
	
	public static String getTypeToDisplay(NotionDTO not){
    	for (NotionTypesEnum val:values())
    		if (val.tag().equals(not.getType()))
    			return val.toString();
    	return EMPTY;
    }
	
	public static NotionTypesEnum getNotionTypeByTag(String tag) {
		for (NotionTypesEnum v:values())
			if (v.tag().equals(tag))
				return v;
		return null;
	}
	
}
