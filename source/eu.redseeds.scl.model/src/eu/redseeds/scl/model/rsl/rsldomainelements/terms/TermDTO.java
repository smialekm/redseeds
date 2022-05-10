package eu.redseeds.scl.model.rsl.rsldomainelements.terms;

public interface TermDTO {
	
	public static String IS_CLIPBOARD_MEMBER_ATTRIBUTE = "isClipboardMember";

	public void setSynonymUid(long uid);
	
	public long getSynonymUid();
	
	public void delete();
	
	public void setName(String name);
	public String getName();
	
	public boolean autoAssignSense();
	public void autoAddAndAssignSense();
	
	public boolean isConsistent();

	public TermDTO getTerm();
	
//	public boolean isClipboardMember();
//	public void setClipboardMember(boolean member);
	
}
