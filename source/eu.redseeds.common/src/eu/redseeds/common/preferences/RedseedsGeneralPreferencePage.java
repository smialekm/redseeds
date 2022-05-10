package eu.redseeds.common.preferences;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import eu.redseeds.common.Activator;
import eu.redseeds.common.Constants;

public class RedseedsGeneralPreferencePage extends FieldEditorPreferencePage
		implements IWorkbenchPreferencePage {
	
	Composite parent;
	BooleanFieldEditor senseAutoAssigmentFlag;
	BooleanFieldEditor senseAutoAddAndAssigmentFlag;

	@Override
	public void init(IWorkbench workbench) {
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
	}

	@Override
	protected void createFieldEditors() {
		parent = getFieldEditorParent();
		
		senseAutoAssigmentFlag = new BooleanFieldEditor(Constants.SENSE_AUTO_ASSIGN,
		        "Automatically assign sense from WordNet database to notions.", parent);
		addField(senseAutoAssigmentFlag);
		
		senseAutoAddAndAssigmentFlag = new BooleanFieldEditor(Constants.SENSE_AUTO_ADD_ASSIGN,
		        "Add stub-sense to WordNet if no sense was found for notion.", parent);
		addField(senseAutoAddAndAssigmentFlag);
		BooleanFieldEditor checs = new BooleanFieldEditor("checkrelations",
				"Enables checking notions relations correctness.",	parent);
		addField(checs);
		checs.getDescriptionControl(parent).setToolTipText(
						"Enables checking notions relations correctness.");
		BooleanFieldEditor aafdv = new BooleanFieldEditor("allowAttributesForDataViews",
				"Enables attribute relation to Data Views.",	parent);
		addField(aafdv);
		aafdv.getDescriptionControl(parent).setToolTipText(
						"Enables attribute relation to Data Views.");
		BooleanFieldEditor hpod = new BooleanFieldEditor("hidePhrasesOnDiagrams",
				"Hide phrases on notion diagram.",	parent);
		addField(hpod);
		hpod.getDescriptionControl(parent).setToolTipText(
						"Hides notion phrases on notion diagram.");
		BooleanFieldEditor hstp = new BooleanFieldEditor("hideSentenceTypeCombo",
				"Hide sentences and actions types combo.",	parent);
		addField(hstp);
		hstp.getDescriptionControl(parent).setToolTipText(
						"Hides sentences and actions types combo.");
		BooleanFieldEditor rsldl = new BooleanFieldEditor("enableRSLDL",
				"Enable RSL-DL extension (Beta)",	parent);
		addField(rsldl);
		rsldl.getDescriptionControl(parent).setToolTipText(
						"Enable extended domain modeling for purposes of code generation.");
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
		if(senseAutoAddAndAssigmentFlag.getBooleanValue() == false){
			senseAutoAssigmentFlag.setEnabled(true, parent);
		}
		else{
			senseAutoAssigmentFlag.setEnabled(false, parent);
			senseAutoAssigmentFlag.loadDefault();
		}
	}

}
