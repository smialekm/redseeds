package eu.remics.common.preferences;

import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import eu.redseeds.common.preferences.AddRemoveListFieldEditor;
import eu.remics.recovery.model.Activator;

public class TALERoleMapingPreferencePage extends
		FieldEditorPreferencePage implements IWorkbenchPreferencePage {
	
	@Override
	public void init(IWorkbench workbench) {
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
	}

	@Override
	protected void createFieldEditors() {
		Composite g = getFieldEditorParent();
		GridData gd = new GridData(GridData.FILL, GridData.FILL, true, true);
		gd.horizontalSpan = 2;
		g.setLayoutData(gd);
		GridLayout layout = new GridLayout(4, false);
		g.setLayout(layout);
		
		Composite g1 = new Composite(g, SWT.NONE);
		GridData g1d = new GridData(GridData.FILL, GridData.FILL, true, true);
		g1d.horizontalSpan = 2;
		g1.setLayoutData(g1d);
		GridLayout layout1 = new GridLayout(2, false);
		g1.setLayout(layout1);
		
		Composite g2 = new Composite(g, SWT.NONE);
		GridData g2d = new GridData(GridData.FILL, GridData.FILL, true, true);
		g2d.horizontalSpan = 2;
		g2.setLayoutData(g2d);
		GridLayout layout2 = new GridLayout(2, false);
		g2.setLayout(layout2);
		
		Composite g3 = new Composite(g, SWT.NONE);
		GridData g3d = new GridData(GridData.FILL, GridData.FILL, true, true);
		g3d.horizontalSpan = 2;
		g3.setLayoutData(g3d);
		GridLayout layout3 = new GridLayout(2, false);
		g3.setLayout(layout3);
		
		addField(new AddRemoveListFieldEditor("inputclickroles", "Input click roles", g1));
		addField(new AddRemoveListFieldEditor("clickroles", "Click roles", g2));
		addField(new AddRemoveListFieldEditor("listroles", "List roles", g3));
		
		
	}

}
