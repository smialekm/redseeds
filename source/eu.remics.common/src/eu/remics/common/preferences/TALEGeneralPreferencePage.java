package eu.remics.common.preferences;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import eu.remics.recovery.model.Activator;

public class TALEGeneralPreferencePage extends FieldEditorPreferencePage implements
		IWorkbenchPreferencePage {

	@Override
	public void init(IWorkbench workbench) {
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
	}

	@Override
	protected void createFieldEditors() {
		Composite parent = getFieldEditorParent();

		BooleanFieldEditor enableDialogNaming = new BooleanFieldEditor("enableDialogNaming",
		        "Enable dialog naming for window notions.", parent); //Definicja pola
		addField(enableDialogNaming); //Dodanie pola
		enableDialogNaming.getDescriptionControl(parent).setToolTipText("Changes name recovering method for windows. Turn on this option if recovered window notions don't have proper names."); //Dodanie tooltipa (po przetrzymaniu myszk¹ nad polem)
		
		BooleanFieldEditor addSeparateNotionFromEachId = new BooleanFieldEditor("addSeparateNotionFromEachId",
		        "Add separate notion from each attribute.", parent);
		addField (addSeparateNotionFromEachId);
		addSeparateNotionFromEachId.getDescriptionControl(parent).setToolTipText("Turn off this option if recovered data notions have to many attributes of the same type or duplicates. \n Warning: Turning off this option might cause omitting creation of some attribute and data notions. (Recommened: on)");
		
		BooleanFieldEditor createSentenceAboutFirstWindowDisplay = new BooleanFieldEditor("createSentenceAboutFirstWindowDisplay",
		        "Add additional sentence about displayed window if the scenario/script starts with user action.", parent);
		addField (createSentenceAboutFirstWindowDisplay);
		
		BooleanFieldEditor clicksufix = new BooleanFieldEditor("clicksufix",
		        "Add type definition for trigger notion names (buttons, links etc.).", parent);
		addField (clicksufix);
		clicksufix.getDescriptionControl(parent).setToolTipText("Generated trigger notions will have type definiton in their names e.g. notion based on 'ok' button will be named 'ok button'");
				
		//addField(new BooleanFieldEditor("lowcasesuffixs",
		        //"Low case suffixes for trigger notions", getFieldEditorParent()));
		//clicksufix.getDescriptionControl(parent).setToolTipText("");
		addField(new StringFieldEditor("windowsufix", 
				"Define type name for window notion : ",
				parent));
		addField(new StringFieldEditor("inputsufix",
				"Define type name for data notion: ",
				parent));
		addField(new BooleanFieldEditor("fetchesdata",
		        "Add additional sentence about data fetch before window display for each data input sentence in window.", parent));
		addField(new BooleanFieldEditor("validatedata",
		        "Add additional validation sentence accompanying each data input sentence.", parent));
		addField(new BooleanFieldEditor("processdata",
		        "Add additional data process sentence accompanying each data input sentence.", parent));
		addField(new BooleanFieldEditor("confirmdata",
		        "Add additional confirmation sentence accompanying each data input sentence.", parent));
		addField(new BooleanFieldEditor("checkPrefixInvcationAndConditionSentences",
		        "Do not skip invocation and condition sentences while comparing scenarios.", parent));
		addField(new BooleanFieldEditor("firstUpperCaseChar",
		        "Convert first letter in script name to capital letter during script loading.", parent));
		BooleanFieldEditor brackets = new BooleanFieldEditor("canUseBrackets",
				"Enable use of \"()\" brackets for numbering of recovered notions duplicates.",	parent);
		addField(brackets);
		brackets.getDescriptionControl(parent).setToolTipText(
						"When this option is on created duplicated notions will be named \"notion (1)\", \"notion (2)\" etc. instead of \"notion 1\" \"notion 2\". Warning! This might cause errors in generated code for some transformations.");
		BooleanFieldEditor posts = new BooleanFieldEditor("addPostToMain",
				"Automatically add a postcondition to main scenario when creating a use case from a test script.",	parent);
		addField(posts);
		posts.getDescriptionControl(parent).setToolTipText(
						"Automatically add a postcondition to main scenario when creating a use case from a test script.");
	}
	
}
