package eu.remics.common.preferences;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import eu.remics.recovery.model.Activator;

public class TALETagsPreferencePage extends FieldEditorPreferencePage implements
		IWorkbenchPreferencePage {
	
	Composite fieldEditorParent;
	Composite g;
	List<StringFieldEditor> fieldEditors = new ArrayList<StringFieldEditor>();
	BooleanFieldEditor packageorg;
	StringFieldEditor dataPack;
	StringFieldEditor conceptPack;
	StringFieldEditor uiPack;
	StringFieldEditor triggerPack;
	StringFieldEditor attributePack;
	StringFieldEditor optionPack;
	StringFieldEditor listPack;
	
	StringFieldEditor descData;
	StringFieldEditor descConcept;
	StringFieldEditor descWindow;
	StringFieldEditor descTrigger;
	StringFieldEditor descAttribute;
	StringFieldEditor descOption;
	StringFieldEditor descList;

	@Override
	public void init(IWorkbench workbench) {
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
	}

	@Override
	protected void createFieldEditors() {
		fieldEditorParent = getFieldEditorParent();
		
		packageorg = new BooleanFieldEditor("packageorg",
		        "Organise in packages", fieldEditorParent);
		addField(packageorg);
		
		g = new Composite(fieldEditorParent, SWT.NONE);
		GridData gd = new GridData(GridData.FILL, GridData.FILL, true, true);
		gd.horizontalSpan = 2;
		g.setLayoutData(gd);
		GridLayout layout = new GridLayout(2, false);
		g.setLayout(layout);
		
		descData = new StringFieldEditor("descData", "Data description:",
				g);
		addField(descData);
		
		dataPack = new StringFieldEditor("dataPack", "Data package:",
				g);
		addField(dataPack);
		fieldEditors.add(dataPack);
		
		descConcept = new StringFieldEditor("descConcept", "Concept description:",
				g);
		addField(descConcept);
		
		conceptPack = new StringFieldEditor("conceptPack", "Concept package:",
				g);
		addField(conceptPack);
		fieldEditors.add(conceptPack);
		
		descWindow = new StringFieldEditor("descWindow", "Window description:",
				g);
		addField(descWindow);
		
		uiPack = new StringFieldEditor("uiPack", "Window package:",
				g);
		addField(uiPack);
		fieldEditors.add(uiPack);
		
		descTrigger = new StringFieldEditor("descTrigger", "Trigger description:",
				g);
		addField(descTrigger);
		
		triggerPack = new StringFieldEditor("triggerPack", "Trigger package:",
				g);
		addField(triggerPack);
		fieldEditors.add(triggerPack);
		
		descAttribute = new StringFieldEditor("descAttribute", "Attribute description:",
				g);
		addField(descAttribute);
		
		attributePack = new StringFieldEditor("attributePack", "Attribute package:",
				g);
		addField(attributePack);
		fieldEditors.add(attributePack);
		
		descOption = new StringFieldEditor("descOption", "Option description:",
				g);
		addField(descOption);
		
		optionPack = new StringFieldEditor("optionPack", "Option package:",
				g);
		addField(optionPack);
		fieldEditors.add(optionPack);
		
		descList = new StringFieldEditor("descList", "List description:",
				g);
		addField(descList);
		
		listPack = new StringFieldEditor("listPack", "List package:",
				g);
		addField(listPack);
		fieldEditors.add(listPack);
		
		layout = (GridLayout) g.getLayout();
		layout.numColumns = 2;
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent event) {
		super.propertyChange(event);
		if(event.getProperty().equals(FieldEditor.VALUE)){
			checkState();
		}
	}
	
	@Override
	protected void checkState() {
		super.checkState();
		if(packageorg.getBooleanValue() == true){
			for(StringFieldEditor editor : fieldEditors){
				editor.setEnabled(true, g);
			}
		}
		else{
			for(StringFieldEditor editor : fieldEditors){
				editor.setEnabled(false, g);
			}
		}
	}

}
