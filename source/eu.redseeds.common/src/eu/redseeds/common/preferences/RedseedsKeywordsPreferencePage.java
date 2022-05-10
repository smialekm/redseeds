package eu.redseeds.common.preferences;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import eu.redseeds.common.Activator;

public class RedseedsKeywordsPreferencePage extends FieldEditorPreferencePage implements
		IWorkbenchPreferencePage {

	Composite parent;
	
	@Override
	public void init(IWorkbench workbench) {
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
	}

	@Override
	protected void createFieldEditors() {
		parent = getFieldEditorParent();
		
		BooleanFieldEditor cruds = new BooleanFieldEditor("assigncruds",
				"Enables auto assign action types during domain statement creation.",	parent);
		addField(cruds);
		cruds.getDescriptionControl(parent).setToolTipText(
						"Enables auto assign action types during domain statement creation.");
		BooleanFieldEditor akinds = new BooleanFieldEditor("autoAssignNotionKind",
				"Enables auto assign notion type during notion creation.",	parent);
		addField(akinds);
		akinds.getDescriptionControl(parent).setToolTipText(
						"Enables auto assign notion type during notion creation based on keywords.");
	}
	
}
