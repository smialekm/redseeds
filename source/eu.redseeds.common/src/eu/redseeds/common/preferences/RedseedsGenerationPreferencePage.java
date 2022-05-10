package eu.redseeds.common.preferences;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.RadioGroupFieldEditor;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import eu.redseeds.common.Activator;
import eu.redseeds.common.Constants;

public class RedseedsGenerationPreferencePage extends FieldEditorPreferencePage implements
		IWorkbenchPreferencePage {
	
	BooleanFieldEditor genCode;
	//boolean isModelioSet;

	@Override
	public void init(IWorkbench workbench) {
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
	}

	@Override
	protected void createFieldEditors() {
		Composite parent = getFieldEditorParent();
		
		String[][] labelsAndValues = new String[][]{ 
				{"Enterprise Architect", Constants.EA_MODEL_GEN},
				{"Modelio", Constants.MODELIO_MODEL_GEN} };
		
		genCode = new BooleanFieldEditor(Constants.CODE_GEN,
		        "Generate code (Default: off)", parent);
		genCode.getDescriptionControl(parent).setToolTipText("Automatically generate files with source code after generating implementation.");
		addField(genCode);
		
		Group group = new Group(parent, SWT.SHADOW_OUT);
		group.setText("Select tool for generation:");
		
		addField(new RadioGroupFieldEditor(Constants.SELECT_GEN_METHOD, "", 1, labelsAndValues, group));
		
		/*String radioBtnOption = Activator.getDefault().getPreferenceStore().getString(Constants.SELECT_GEN_METHOD);
		isModelioSet = radioBtnOption.equals(Constants.MODELIO_MODEL_GEN) ? true : false;*/
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent event) {
		super.propertyChange(event);
		/*if(event.getSource() instanceof RadioGroupFieldEditor){
			if(((RadioGroupFieldEditor)event.getSource()).getPreferenceName().equals(Constants.SELECT_GEN_METHOD)){
				String newVal = (String) event.getNewValue();
				boolean modelioModelChosen = newVal.equals(Constants.MODELIO_MODEL_GEN) ? true : false;
				isModelioSet = modelioModelChosen;
				validate();
			}
		}
		if(event.getProperty().equals(FieldEditor.VALUE)){
			checkState();
		}*/
	}
	
	@Override
	protected void checkState() {
		super.checkState();
		//validate();
	}
	
	@Override
	protected void performDefaults() {
		super.performDefaults();
		setErrorMessage(null);
		setValid(true);
	}
	
	/*private boolean validate() {
		if(isModelioSet){
			if(genCode.getBooleanValue() == false){
				setErrorMessage("Model generation without code is not yet implemented. Please use ReDSeeDSImporter within Modelio tool or choose Generate code.");
				setValid(false);
				return false;
			}
		}
		setErrorMessage(null);
		setValid(true);
		return true;
	}*/
}
