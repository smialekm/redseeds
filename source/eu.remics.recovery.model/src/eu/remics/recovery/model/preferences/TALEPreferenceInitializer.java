package eu.remics.recovery.model.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

import eu.remics.recovery.model.Activator;

public class TALEPreferenceInitializer extends AbstractPreferenceInitializer {

	@Override
	public void initializeDefaultPreferences() {
		IPreferenceStore store = Activator.getDefault().getPreferenceStore();
		store.setDefault("languageProfiles","Java\n\neu.remics.recovery.model.preferences.JavaNameConverter\nHtml\n.url\neu.remics.recovery.model.preferences.HtmlNameConverter\nWin\n.processName\neu.remics.recovery.model.preferences.WinNameConverter");
		store.setDefault("inputclickroles","CheckBox\nRadioButton\nComboBox");
		store.setDefault("clickroles","Button\nMenuBar\nLink");
		store.setDefault("inputtextclasses",".*?TextField\n.*?ComboBox");
		store.setDefault("inputdescclasses",".*?FieldTextArea\n.*?TextArea");
		store.setDefault("inputpasswclasses",".*?PasswordField");
		store.setDefault("checkclasses",".*?CheckBox");
		store.setDefault("optionclasses",".*?RadioButton");
		store.setDefault("showsonlynewwindows",false);
		store.setDefault("dialogroles","Dialog");
		store.setDefault("enableDialogNaming",true);
		store.setDefault("addSeparateNotionFromEachId",true);
		store.setDefault("createSentenceAboutFirstWindowDisplay",false);
		store.setDefault("rolesmap", "");
		store.setDefault("clicksufix",true);
		store.setDefault("lowcasesuffixs",true);
		store.setDefault("windowsufix","window");
		store.setDefault("inputsufix","data");
		store.setDefault("validatedata",false);
		store.setDefault("processdata",false);
		store.setDefault("confirmdata",true);
		store.setDefault("checkPrefixInvcationAndConditionSentences",false);
		store.setDefault("maperMap", "\\_\n ");
		store.setDefault("firstUpperCaseChar",false);
		store.setDefault("descData","");
		store.setDefault("descWindow","<fr>");
		store.setDefault("descTrigger","<tr>");
		store.setDefault("descAttribute","<at>");
		store.setDefault("descOption","<opt>");
		store.setDefault("descList","<li>");
		store.setDefault("dataPack","Data");
		store.setDefault("uiPack","UIElements");
		store.setDefault("triggerPack","Triggers");
		store.setDefault("attributePack","Attributes");
		store.setDefault("optionPack","Options");
		store.setDefault("listPack","Lists");
		store.setDefault("packageorg",true);
		store.setDefault("clean",false);
		store.setDefault("listroles","Table");
		store.setDefault("canUseBrackets",false);
		store.setDefault("addPostToMain", true);
		store.setDefault("diagramDispersType",0);
		store.setDefault("descConcept", "");
		store.setDefault("conceptPack", "");
		store.setDefault("fetchesdata", true);
		
	}

}
