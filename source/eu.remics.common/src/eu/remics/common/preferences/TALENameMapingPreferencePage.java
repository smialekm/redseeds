package eu.remics.common.preferences;

import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import eu.remics.recovery.model.Activator;

public class TALENameMapingPreferencePage extends FieldEditorPreferencePage
		implements IWorkbenchPreferencePage {

	@Override
	public void init(IWorkbench workbench) {
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
	}

	@Override
	protected void createFieldEditors() {
		addField(new MapListFieldEditor("rolesmap", "Roles map", getFieldEditorParent()));
		addField(new MapListFieldEditor("maperMap", "Maper map", getFieldEditorParent()));
		
	}

}
