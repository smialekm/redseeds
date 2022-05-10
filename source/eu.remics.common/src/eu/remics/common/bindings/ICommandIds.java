package eu.remics.common.bindings;

/**
 * Interface defining the application's command IDs.
 * Key bindings can be defined for specific commands.
 * To associate an action with a command, use IAction.setActionDefinitionId(commandId).
 *
 * @see org.eclipse.jface.action.IAction#setActionDefinitionId(String)
 */
public interface ICommandIds {

    public static final String CMD_OPEN = null;//"eu.remics.common.bindings.open";
    
    public static final String CMD_ASSIGN_TO_USE_CASE = null;//"eu.remics.common.bindings.assignToUseCase";
    public static final String CMD_CREATE_NEW_USE_CASE = null;//"eu.remics.common.bindings.createNewUseCase";
    public static final String CMD_DELETE_SCENARIO = null;//"eu.remics.common.bindings.deleteScenario";
    
    public static final String CMD_REMOVE_SCENARIO = null;//"eu.remics.common.bindings.removeScenario";
    public static final String CMD_SAVE_USE_CASE = null;//"eu.remics.common.bindings.saveUseCase";
    public static final String CMD_DELETE_USE_CASE = null;//"eu.remics.common.bindings.deleteUseCase";
    
    public static final String CMD_ADD_ATTRIBUTE = null;//"eu.remics.common.bindings.addAttribute";
    public static final String CMD_DELETE_ATTRIBUTE = null;//"eu.remics.common.bindings.deleteAttribute";
    public static final String CMD_DELETE_NOTION = null;//"eu.remics.common.bindings.deleteNotion";
    public static final String CMD_SAVE_NOTION = null;//"eu.remics.common.bindings.saveNotion";
    public static final String CMD_CANCEL_NOTION = null;//"eu.remics.common.bindings.cancelNotion";
    
    public static final String CMD_CREATE_USE_CASE = null;//"eu.remics.common.bindings.createUseCase";
    
    public static final String CMD_LOAD_SCRIPTS = null;//"eu.remics.common.bindings.loadScripts";
    
    public static final String CMD_LOAD_MAPSFOLDER = null;//"eu.remics.common.bindings.loadMapsFolder";
    
    public static final String CMD_REFRESH_TREE = null;//"eu.remics.common.bindings.refreshTree";

	public static final String CMD_MERGE_NOTIONS = null;

	public static final String CMD_MERGE_USE_CASES = null;
	
	public static final String CMD_PARTIAL_MERGE_USE_CASES = null;
	
	public static final String CMD_REVERT_PARTIAL_MERGE_USE_CASES = null;

	public static final String CMD_EDIT_SCENARIO = null;
	
	public static final String CMD_SPLIT_SCENARIO = null;
	
	public static final String CMD_SIMILAR_SCENARIO_DETAIL = null;
	
	public static final String CMD_PREVIEW_SCENARIO = null;
}
