package eu.remics.recovery.model.preferences;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.eclipse.jface.preference.IPreferenceStore;

import eu.remics.recovery.model.Activator;

public class MConfiguration {
	
	private static IPreferenceStore getPreferenceStore(){
    	return Activator.getDefault().getPreferenceStore();
    }
	
	private static IPreferenceStore getReDSeeDSPreferenceStore(){
    	return eu.redseeds.common.Activator.getDefault().getPreferenceStore();
    }

	public static List<LanguageProfile> getLanguageProfiles() {
		ArrayList<LanguageProfile> languageProfiles = new ArrayList<LanguageProfile>();
		String[] t=getPreferenceStore().getString("languageProfiles").split("\n");
		for(int i=0;i<t.length-2;i+=3) languageProfiles.add(new LanguageProfile(t[i],t[i+1].isEmpty()?null:t[i+1],t[i+2]));
		return languageProfiles;
	}
	
	public static HashMap<String, String> getRolesmap() {
		HashMap<String, String> rolesmap = new HashMap<String, String>();
		String[] t=getPreferenceStore().getString("rolesmap").split("\n");
		for(int i=0;i<t.length-1;i+=2) rolesmap.put(t[i], t[i+1]);
		return rolesmap;
	}
	
	public static HashMap<String, String> getMaperMap() {
		HashMap<String, String> maperMap = new HashMap<String, String>();
		String[] t=getPreferenceStore().getString("maperMap").split("\n");
		for(int i=0;i<t.length-1;i+=2) maperMap.put(t[i], t[i+1]);
		return maperMap;
	}
    
	public static boolean isLowcasesuffixs() {
		return getPreferenceStore().getBoolean("lowcasesuffixs");
	}

	public static List<String> getOptionclasses() {
		return Arrays.asList(getPreferenceStore().getString("optionclasses").split("\n"));
	}
	
	public static List<String> getListroles() {
		return Arrays.asList(getPreferenceStore().getString("listroles").split("\n"));
	}
	
	public static boolean isClicksufix() {
		return getPreferenceStore().getBoolean("clicksufix");
	}
	
	public static String getWindowsufix() {
		return getPreferenceStore().getString("windowsufix");
	}
	
	public static String getInputsufix() {
		return getPreferenceStore().getString("inputsufix");
	}
	
	public static boolean isFetchesdata() {
		return getPreferenceStore().getBoolean("fetchesdata");
	}
	
	public static boolean isValidatedata() {
		return getPreferenceStore().getBoolean("validatedata");
	}
	
	public static boolean isProcessdata() {
		return getPreferenceStore().getBoolean("processdata");
	}
	
	public static boolean isConfirmdata() {
		return getPreferenceStore().getBoolean("confirmdata");
	}
	
	public static List<String> getInputclickroles() {
		return Arrays.asList(getPreferenceStore().getString("inputclickroles").split("\n"));
	}
	
	public static List<String> getClickroles() {
		return Arrays.asList(getPreferenceStore().getString("clickroles").split("\n"));
	}
	
	public static List<String> getInputtextclasses() {
		return Arrays.asList(getPreferenceStore().getString("inputtextclasses").split("\n"));
	}
	
	public static List<String> getInputdescclasses() {
		return Arrays.asList(getPreferenceStore().getString("inputdescclasses").split("\n"));
	}
	
	public static List<String> getInputpasswclasses() {
		return Arrays.asList(getPreferenceStore().getString("inputpasswclasses").split("\n"));
	}
	
	public static List<String> getCheckclasses() {
		return Arrays.asList(getPreferenceStore().getString("checkclasses").split("\n"));
	}

	public static boolean isShowsonlynewwindows() {
		return getPreferenceStore().getBoolean("showsonlynewwindows");
	}
	
	public static boolean isFirstUpperCaseChar() {
		return getPreferenceStore().getBoolean("firstUpperCaseChar");
	}

	public static boolean isCheckPrefixInvcationAndConditionSentences() {
		return getPreferenceStore().getBoolean("checkPrefixInvcationAndConditionSentences");
	}

	public static String getDescData() {
		return getPreferenceStore().getString("descData");
	}

	public static String getDescWindow() {
		return getPreferenceStore().getString("descWindow");
	}

	public static String getDescTrigger() {
		return getPreferenceStore().getString("descTrigger");
	}

	public static String getDescAttribute() {
		return getPreferenceStore().getString("descAttribute");
	}

	public static String getDescOption() {
		return getPreferenceStore().getString("descOption");
	}

	public static List<String> getDialogroles() {
		return Arrays.asList(getPreferenceStore().getString("dialogroles").split("\n"));
	}

	public static boolean isEnableDialogNaming() {
		return getPreferenceStore().getBoolean("enableDialogNaming");
	}

	public static boolean isAddSeparateNotionFromEachId() {
		return getPreferenceStore().getBoolean("addSeparateNotionFromEachId");
	}

	public static boolean isCreateSentenceAboutFirstWindowDisplay() {
		return getPreferenceStore().getBoolean("createSentenceAboutFirstWindowDisplay");
	}

	public static boolean getClean() {
		return getPreferenceStore().getBoolean("clean");
	}
	
	public static boolean isPackageorg() {
		return getPreferenceStore().getBoolean("packageorg");
	}
	
	public static String getDataPack() {
		return getPreferenceStore().getString("dataPack");
	}
	
	public static String getUiPack() {
		return getPreferenceStore().getString("uiPack");
	}
	
	public static String getTriggerPack() {
		return getPreferenceStore().getString("triggerPack");
	}
	
	public static String getAttributePack() {
		return getPreferenceStore().getString("attributePack");
	}
	
	public static String getOptionPack() {
		return getPreferenceStore().getString("optionPack");
	}
	
	public static String getDescList() {
		return getPreferenceStore().getString("descList");
	}
	
	public static String getListPack() {
		return getPreferenceStore().getString("listPack");
	}
	
	public static boolean isCanUseBrackets() {
		return getPreferenceStore().getBoolean("canUseBrackets");
	}
	
	public static boolean isAddPostToMain() {
		return getPreferenceStore().getBoolean("addPostToMain");
	}
	
	public static boolean isCheckRelations() {
		return getReDSeeDSPreferenceStore().getBoolean("checkrelations");
	}
	
	public static boolean isAssignCruds() {
		return getReDSeeDSPreferenceStore().getBoolean("assigncruds");
	}
	
	public static List<String> getCrudcreatesenses() {
		return Arrays.asList(getReDSeeDSPreferenceStore().getString("crudcreatesenses").split("\n"));
	}
	
	public static List<String> getCrudreadsenses() {
		return Arrays.asList(getReDSeeDSPreferenceStore().getString("crudreadsenses").split("\n"));
	}
	
	public static List<String> getCrudupdatesenses() {
		return Arrays.asList(getReDSeeDSPreferenceStore().getString("crudupdatesenses").split("\n"));
	}
	
	public static List<String> getCruddeletesenses() {
		return Arrays.asList(getReDSeeDSPreferenceStore().getString("cruddeletesenses").split("\n"));
	}
	
	public static List<String> getActionvalidatesenses() {
		return Arrays.asList(getReDSeeDSPreferenceStore().getString("actionvalidatesenses").split("\n"));
	}
	
	public static List<String> getActionshowsenses() {
		return Arrays.asList(getReDSeeDSPreferenceStore().getString("actionshowsenses").split("\n"));
	}
	
	public static List<String> getActionclosesenses() {
		return Arrays.asList(getReDSeeDSPreferenceStore().getString("actionclosesenses").split("\n"));
	}
	
	public static List<String> getActionrefreshsenses() {
		return Arrays.asList(getReDSeeDSPreferenceStore().getString("actionrefreshsenses").split("\n"));
	}
	
	public static List<String> getActionselectsenses() {
		return Arrays.asList(getReDSeeDSPreferenceStore().getString("actionselectsenses").split("\n"));
	}
	
	public static boolean isAllowAttributesForDataViews(){
		return getReDSeeDSPreferenceStore().getBoolean("allowAttributesForDataViews");
	}
	
	public static int getDiagramDispersType(){
		return getPreferenceStore().getInt("diagramDispersType");
	}
	
	public static boolean isHidePhrasesOnDiagrams(){
		return getReDSeeDSPreferenceStore().getBoolean("hidePhrasesOnDiagrams");
	}

	public static String getDescConcept() {
		return getPreferenceStore().getString("descConcept");
	}

	public static String getConceptPack() {
		return getPreferenceStore().getString("conceptPack");
	}
	
	public static boolean isHideSentenceTypeCombo() {
		return getReDSeeDSPreferenceStore().getBoolean("hideSentenceTypeCombo");
	}
	
	public static boolean isAutoAssignNotionKind() {
		return getReDSeeDSPreferenceStore().getBoolean("autoAssignNotionKind");
	}
	
	public static List<String> getConfirmationDialogKeywords() {
		return Arrays.asList(getReDSeeDSPreferenceStore().getString("confirmationDialogKeywords").split("\n"));
	}
	
	public static List<String> getTriggerKeywords() {
		return Arrays.asList(getReDSeeDSPreferenceStore().getString("triggerKeywords").split("\n"));
	}
	
	public static List<String> getScreenKeywords() {
		return Arrays.asList(getReDSeeDSPreferenceStore().getString("screenKeywords").split("\n"));
	}
	
	public static List<String> getMessageKeywords() {
		return Arrays.asList(getReDSeeDSPreferenceStore().getString("messageKeywords").split("\n"));
	}
	
	public static List<String> getListViewKeywords() {
		return Arrays.asList(getReDSeeDSPreferenceStore().getString("listViewKeywords").split("\n"));
	}
	
	public static List<String> getTreeViewKeywords() {
		return Arrays.asList(getReDSeeDSPreferenceStore().getString("treeViewKeywords").split("\n"));
	}
	
	public static List<String> getSimpleViewKeywords() {
		return Arrays.asList(getReDSeeDSPreferenceStore().getString("simpleViewKeywords").split("\n"));
	}
	
	public static boolean isEnableRSLDL() {
		return getReDSeeDSPreferenceStore().getBoolean("enableRSLDL");
	}
	
}
