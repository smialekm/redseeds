package eu.remics.common.preferences;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import eu.redseeds.common.preferences.AddRemoveListFieldEditor;
import eu.remics.recovery.model.Activator;

public class TALEAdvancedPreferencePage extends FieldEditorPreferencePage
		implements IWorkbenchPreferencePage {

	@Override
	public void init(IWorkbench workbench) {
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
	}

	@Override
	protected void createFieldEditors() {
		Composite parent = getFieldEditorParent();

		BooleanFieldEditor showsonlynewwindows = new BooleanFieldEditor("showsonlynewwindows",
				"Do not repeat sentences about opening window.", parent);
		addField(showsonlynewwindows);
		showsonlynewwindows.getDescriptionControl(parent).setToolTipText(
		"Do not create new 'System opens window' sentences if the window have already been opened in the scenario/script.");

		BooleanFieldEditor clean = new BooleanFieldEditor("clean",
				"Remove notions which do not occur in scenario sentences but exist in script.",	parent);
		addField(clean);
		clean.getDescriptionControl(parent).setToolTipText(
						"Some notions e.g. buttons do not occur directly in scenario but exist in script data and application. When this option is on such notions will not be created.");
		Composite g1 = new Composite(parent, SWT.NONE);
		GridData g1d = new GridData(GridData.FILL, GridData.FILL, true, true);
		g1d.horizontalSpan = 2;
		g1.setLayoutData(g1d);
		GridLayout layout1 = new GridLayout(2, false);
		g1.setLayout(layout1);
		addField(new AddRemoveListFieldEditor("dialogroles", "Dialog roles", g1));
	}

}
