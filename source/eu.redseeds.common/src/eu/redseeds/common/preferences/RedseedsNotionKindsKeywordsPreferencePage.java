package eu.redseeds.common.preferences;

import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import eu.redseeds.common.Activator;

public class RedseedsNotionKindsKeywordsPreferencePage extends FieldEditorPreferencePage
		implements IWorkbenchPreferencePage {
	
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
		
		Composite g6 = new Composite(g, SWT.NONE);
		GridData g6d = new GridData(GridData.FILL, GridData.FILL, true, true);
		g6d.horizontalSpan = 2;
		g6.setLayoutData(g6d);
		GridLayout layout6 = new GridLayout(2, false);
		g6.setLayout(layout6);
		
		Composite g7 = new Composite(g, SWT.NONE);
		GridData g7d = new GridData(GridData.FILL, GridData.FILL, true, true);
		g7d.horizontalSpan = 2;
		g7.setLayoutData(g7d);
		GridLayout layout7 = new GridLayout(2, false);
		g7.setLayout(layout7);
		
		addField(new AddRemoveListFieldEditor("screenKeywords", "Screen keywords", g1));
		addField(new AddRemoveListFieldEditor("messageKeywords", "Message keywords:", g2));
		addField(new AddRemoveListFieldEditor("confirmationDialogKeywords", "Confirmation dialog keywords:", g3));
		addField(new AddRemoveListFieldEditor("triggerKeywords", "Trigger keywords:", g4));
		addField(new AddRemoveListFieldEditor("listViewKeywords", "List View keywords:", g5));
		addField(new AddRemoveListFieldEditor("treeViewKeywords", "Tree View keywords:", g6));
		addField(new AddRemoveListFieldEditor("simpleViewKeywords", "Simple View keywords:", g7));
	}
	
}
