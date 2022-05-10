package eu.remics.common.preferences;

import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import eu.remics.recovery.model.Activator;

public class TALELanguageProfilesPreferencePage extends
		FieldEditorPreferencePage implements IWorkbenchPreferencePage {

	@Override
	public void init(IWorkbench workbench) {
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
		setDescription
		("Language profiles are used to expand TALE recovery functionality by adding definitions for new programming languages or technologies. " +
				"It is highly not recommended to delete existing definitions.");
	}

	@Override
	protected void createFieldEditors() {
		addField(new LanguageProfileListFieldEditor("languageProfiles", "Language profiles", getFieldEditorParent()));
	}

}
