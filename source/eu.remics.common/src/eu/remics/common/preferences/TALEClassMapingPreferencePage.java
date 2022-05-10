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

public class TALEClassMapingPreferencePage extends
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
		
		Composite g4 = new Composite(g, SWT.NONE);
		GridData g4d = new GridData(GridData.FILL, GridData.FILL, true, true);
		g4d.horizontalSpan = 2;
		g4.setLayoutData(g4d);
		GridLayout layout4 = new GridLayout(2, false);
		g4.setLayout(layout4);
		
		Composite g5 = new Composite(g, SWT.NONE);
		GridData g5d = new GridData(GridData.FILL, GridData.FILL, true, true);
		g5d.horizontalSpan = 2;
		g5.setLayoutData(g5d);
		GridLayout layout5 = new GridLayout(2, false);
		g5.setLayout(layout5);
		
		addField(new AddRemoveListFieldEditor("inputtextclasses", "Text input classes", g1));
		addField(new AddRemoveListFieldEditor("inputdescclasses", "Description input classes", g2));
		addField(new AddRemoveListFieldEditor("inputpasswclasses", "Password input classes", g3));
		addField(new AddRemoveListFieldEditor("checkclasses", "Check classes", g4));
		addField(new AddRemoveListFieldEditor("optionclasses", "Option classes", g5));
		
	}

}
