package eu.redseeds.scl.model.rsl.rsldomainelements.notions;

import java.util.Arrays;
import java.util.List;

public enum SentenceTypesEnum {
	SystemToScreen("System to Screen",NotionTypesEnum.Screen),
	SystemToMessage("System to Message",NotionTypesEnum.Message),
	SystemToConfirmationDialog("System to Confirmation Dialog",NotionTypesEnum.Confirmation_Dialog),
	ActorToTrigger("Actor to Trigger",NotionTypesEnum.Trigger,NotionTypesEnum.Trigger,false),
	SystemToListView("System to List View",NotionTypesEnum.List_View,null,false),
	SystemToSimpleView("System to Simple View",NotionTypesEnum.Simple_View,null,false),
	SystemToTreeView("System to Tree View",NotionTypesEnum.Tree_View,null,false),
	SystemToConcept("System to Concept",null,null,false),
	ActorToListView("Actor to List View",NotionTypesEnum.List_View,null,true),
	ActorToSimpleView("Actor to Simple View",NotionTypesEnum.Simple_View),
	ActorToTreeView("Actor to Tree View",NotionTypesEnum.Tree_View,null,true),
	ActorToConcept("Actor to Concept",null);
	
	String name;
	NotionTypesEnum notionType, secondaryNotionType;
	boolean complex,reverseInComplex;
	
	SentenceTypesEnum(String name, NotionTypesEnum type){
		this.name=name;
		this.notionType=type;
	}
	
	SentenceTypesEnum(String name, NotionTypesEnum type, NotionTypesEnum secondaryNotionType, boolean reverseInComplex){
		this.name=name;
		this.notionType=type;
		this.secondaryNotionType=secondaryNotionType;
		this.complex=true;
		this.reverseInComplex=reverseInComplex;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	public static SentenceTypesEnum[] values(boolean actor){
		if (actor)
			return new SentenceTypesEnum[]{ActorToTrigger,ActorToListView,ActorToSimpleView,ActorToTreeView};
		else
			return new SentenceTypesEnum[]{SystemToScreen,SystemToMessage,SystemToConfirmationDialog,SystemToListView,SystemToSimpleView,SystemToTreeView,SystemToConcept};
	}
	
	public NotionTypesEnum notionType(){
		return notionType;
	}
	
	public NotionTypesEnum secondaryNotionType(){
		return secondaryNotionType;
	}
	
	public boolean complex() {
		return complex;
	}
	
	public boolean reverseInComplex(){
		return reverseInComplex;
	}
	
	public static String[] notionTypesTags(boolean actor){
		String[] types = new String[values(actor).length];
		for (int i=0;i<values(actor).length;i++)
			types[i]=null!=values(actor)[i].notionType()?values(actor)[i].notionType().tag():"";
		return types;
	}
	
	public static int getSentenceTypeNumber(NotionDTO not, boolean actor){
		List<String> list = Arrays.asList(notionTypesTags(actor));
		if (list.contains(not.getType()))
    		return list.indexOf(not.getType());
		return -1;
	}
	
	public static int getSentenceTypeNumber(NotionDTO not, NotionDTO not2, boolean actor){
		if (null!=not2){
			List<SentenceTypesEnum> list = Arrays.asList(values(actor));
			for(SentenceTypesEnum st:list)
				if(st.complex()){
					String type1 = !st.reverseInComplex()?(null!=st.notionType()?st.notionType().tag():""):(null!=st.secondaryNotionType()?st.secondaryNotionType().tag():"");
					String type2 = !st.reverseInComplex()?(null!=st.secondaryNotionType()?st.secondaryNotionType().tag():""):(null!=st.notionType()?st.notionType().tag():"");
					if (type1.equals(not.getType()) && type2.equals(not2.getType()))
						return list.indexOf(st);
				}
			for(SentenceTypesEnum st:list)
				if(st.complex() && st.reverseInComplex() && (null!=st.notionType()?st.notionType().tag():"").equals(not2.getType()))
					return list.indexOf(st);
		}
		return getSentenceTypeNumber(not,actor);
	}
	
}
