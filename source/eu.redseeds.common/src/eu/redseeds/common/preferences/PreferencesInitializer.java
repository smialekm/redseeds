package eu.redseeds.common.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

import eu.redseeds.common.Activator;
import eu.redseeds.common.Constants;

public class PreferencesInitializer extends AbstractPreferenceInitializer {

	@Override
	public void initializeDefaultPreferences() {
		IPreferenceStore store = Activator.getDefault().getPreferenceStore();
		store.setDefault(Constants.SENSE_AUTO_ASSIGN, true);
		store.setDefault(Constants.SENSE_AUTO_ADD_ASSIGN, true);
		store.setDefault("checkrelations", true);
		store.setDefault("allowAttributesForDataViews", true);
		store.setDefault("hidePhrasesOnDiagrams", true);
		store.setDefault("hideSentenceTypeCombo", false);
		store.setDefault("assigncruds", true);
		store.setDefault("autoAssignNotionKind", true);
		store.setDefault("crudcreatesenses","save\ncreate\nadd\nwrite");
		store.setDefault("crudreadsenses","get\nfetch\nread\nbuild\nretrieve");
		store.setDefault("crudupdatesenses","update\nmodify\nedit\nrewrite\noverride");
		store.setDefault("cruddeletesenses","delete\nremove\ndestroy");
		store.setDefault("actionvalidatesenses","validate\nverify\nexamine\ninspect\ncheck");
		store.setDefault("actionshowsenses","show\ndisplay\npresent");
		store.setDefault("actionclosesenses","close\nshut down\nshut\nclose down");
		store.setDefault("actionrefreshsenses","refresh\nrenew\nrepaint\nupdate");
		store.setDefault("actionselectsenses","select\npush\npress\nchoose\nclick\ntrigger");
		store.setDefault("confirmationDialogKeywords", "dialog");
		store.setDefault("triggerKeywords", "button\ntrigger\noption");
		store.setDefault("screenKeywords", "screen\npage\nwindow\nform");
		store.setDefault("messageKeywords", "message\ninfo\ninformation\nerror\nnotice\nwarning\nconfirmation\ndialog\npopup");
		store.setDefault("listViewKeywords", "list\ncollection\nset");
		store.setDefault("treeViewKeywords", "tree");
		store.setDefault("simpleViewKeywords", "data\ndetails");
		store.setDefault(Constants.CODE_GEN, false);
		store.setDefault(Constants.SET_CONFIGURATION, false);
		store.setDefault(Constants.MODELIO_EXE_PATH, "");
		store.setDefault(Constants.MODELIO_WORKSPACE, "");
		store.setDefault(Constants.EXISTING_MODELIO_PROJECT, "");
		store.setDefault(Constants.CREATE_MODELIO_PROJECT, true);
		store.setDefault(Constants.CREATE_MODELIO_PROJECT_NAME, "");
		store.setDefault(Constants.SELECT_GEN_METHOD, Constants.EA_MODEL_GEN);
		store.setDefault(Constants.SELECT_GEN_OUTPUT_METHOD, Constants.CODE_GEN_SC_PROJECT_OUTPUT);
		store.setDefault(Constants.CODE_GEN_OUTPUT_PATH, "");
		store.setDefault("enableRSLDL", false);
	}

}
