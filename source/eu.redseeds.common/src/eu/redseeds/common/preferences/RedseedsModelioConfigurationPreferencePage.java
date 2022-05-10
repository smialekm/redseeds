package eu.redseeds.common.preferences;

import java.io.File;
import java.io.FileFilter;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.DirectoryFieldEditor;
import org.eclipse.jface.preference.FieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.FileFieldEditor;
import org.eclipse.jface.preference.RadioGroupFieldEditor;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import eu.redseeds.common.Activator;
import eu.redseeds.common.Constants;

public class RedseedsModelioConfigurationPreferencePage extends
		FieldEditorPreferencePage implements IWorkbenchPreferencePage {
	
	private static final String INSTALL_PATH_NOT_VALID = "Installation path is not valid. Please set a valid one!";
	private static final String INSTALL_PATH_NOT_EXISTS = "Installation path must points to a existing file!";
	private static final String INSTALL_PATH_BLANK = "Installation path cannot be blank!";
	private static final String WORKSPACE_PATH_NOT_EXISTS = "Workspace location must be an existing directory!";
	private static final String WORKSPACE_PATH_BLANK = "Workspace location cannot be blank!";
	private static final String PROJECT_NOT_SELECTED = "Project must be selected!";
	private static final String PROJECT_NAME_BLANK = "New project name must be specified!";
	private static final String CODE_PATH_NOT_EXISTS = "Code generation path must be an existing directory!";
	private static final String CODE_PATH_BLANK = "Code generation path cannot be blank!";
	private static final String PROJECT_ALREADY_EXISTS = "Project already exists! Please rename or choose from projects list.";
	
	BooleanFieldEditor setConfiguration;
	FileFieldEditor modelioExec;
	DirectoryFieldEditor modelioWorkspace;
	BooleanFieldEditor createProject;
	StringFieldEditor createProjectName;
	ListFieldEditor existedProjectLocation;
	RadioGroupFieldEditor codeGenerationOutputFlag;
	DirectoryFieldEditor codeGenerationOutput;
	Group groupForCreateProject;
	Group groupForCodeOutput;
	Group gropuForSetCodeGenPath;
	
	Composite fieldEditorParent;
	boolean isSetCodeGenPath;

	@Override
	public void init(IWorkbench workbench) {
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
	}

	@Override
	protected void createFieldEditors() {
		fieldEditorParent = getFieldEditorParent();
		
		Composite g = new Composite(fieldEditorParent, SWT.SHADOW_OUT);
		GridData gridData = new GridData(GridData.FILL, GridData.FILL, true, true);
		gridData.horizontalSpan = 3;
		g.setLayoutData(gridData);
		GridLayout layOut = new GridLayout(2, false);
		g.setLayout(layOut);
		
		setConfiguration = new BooleanFieldEditor(Constants.SET_CONFIGURATION, "Configure Modelio for model generation", g);
		addField(setConfiguration);
		
		modelioExec = new FileFieldEditor(Constants.MODELIO_EXE_PATH, "Installation path:", true, fieldEditorParent);
		modelioExec.setFileExtensions(new String[] { "*.exe" });
		addField(modelioExec);
		modelioExec.getLabelControl(fieldEditorParent).setToolTipText("Please define path for Modelio.exe file.");
		modelioExec.getTextControl(fieldEditorParent).setToolTipText("Please define path for Modelio.exe file.");
		
		modelioWorkspace = new DirectoryFieldEditor(Constants.MODELIO_WORKSPACE, "Workspace location:", fieldEditorParent);
		addField(modelioWorkspace);
		modelioWorkspace.getLabelControl(fieldEditorParent).setToolTipText
		("Please define Modelio workspace path. ReDSeeDS will create new or modify existing project within workspace.");
		modelioWorkspace.getTextControl(fieldEditorParent).setToolTipText
		("Please define Modelio workspace path.");
		
		//Create new project group controls
		groupForCreateProject = new Group(fieldEditorParent, SWT.SHADOW_OUT);
		groupForCreateProject.setText("Project name");
		GridData gd = new GridData(GridData.FILL, GridData.FILL, true, true);
		gd.horizontalSpan = 2;
		groupForCreateProject.setLayoutData(gd);
		GridLayout layout = new GridLayout(2, false);
		groupForCreateProject.setLayout(layout);
		
		createProject = new BooleanFieldEditor(Constants.CREATE_MODELIO_PROJECT, "Create on-the-fly", groupForCreateProject);
		addField(createProject);
		
		createProjectName = new StringFieldEditor(Constants.CREATE_MODELIO_PROJECT_NAME, "New project name", groupForCreateProject);
		addField(createProjectName);
		createProject.getDescriptionControl(groupForCreateProject).setToolTipText("Choose whether ReDSeeDS should create new or modify existing project within workspace.");
		/*existedProjectLocation = new DirectoryFieldEditor(Constants.EXISTING_MODELIO_PROJECT, "Project directory path", groupForCreateProject);
		String filterPath = Activator.getDefault().getPreferenceStore().getString(Constants.MODELIO_WORKSPACE);
		existedProjectLocation.setFilterPath(new File(filterPath));*/
		existedProjectLocation = new ListFieldEditor(Constants.EXISTING_MODELIO_PROJECT, "Select workspace project", groupForCreateProject);
		existedProjectLocation.setEnabled(false, groupForCreateProject);
		addField(existedProjectLocation);
		
		layout = (GridLayout) groupForCreateProject.getLayout();
		layout.numColumns = 3;
		
		//Code output group controls
		groupForCodeOutput = new Group(fieldEditorParent, SWT.SHADOW_OUT);
		groupForCodeOutput.setText("Code generation output path");
		GridData gd2 = new GridData(GridData.FILL, GridData.FILL, true, true);
		gd2.horizontalSpan = 2;
		groupForCodeOutput.setLayoutData(gd2);
		GridLayout layout2 = new GridLayout(2, false);
		groupForCodeOutput.setLayout(layout2);
		
		String[][] labelsAndValues = new String[][]{ 
				{"In ReDSeeDS Software Case", Constants.CODE_GEN_SC_PROJECT_OUTPUT},
				{"Manually set path for code generation", Constants.CODE_GEN_OTHER_OUTPUT} };
		
		codeGenerationOutputFlag = new RadioGroupFieldEditor(Constants.SELECT_GEN_OUTPUT_METHOD, "", 1, labelsAndValues, groupForCodeOutput);
		addField(codeGenerationOutputFlag);
		
		gropuForSetCodeGenPath = new Group(groupForCodeOutput, SWT.NONE);
		GridData d = new GridData(GridData.FILL, GridData.FILL, true, true);
		d.horizontalSpan = 2;
		gropuForSetCodeGenPath.setLayoutData(d);
		GridLayout l = new GridLayout(2, false);
		gropuForSetCodeGenPath.setLayout(l);
		
		codeGenerationOutput = new DirectoryFieldEditor(Constants.CODE_GEN_OUTPUT_PATH, "", gropuForSetCodeGenPath);
		String radioBtnOption = Activator.getDefault().getPreferenceStore().getString(Constants.SELECT_GEN_OUTPUT_METHOD);
		isSetCodeGenPath = radioBtnOption.equals(Constants.CODE_GEN_OTHER_OUTPUT) ? true : false;
		codeGenerationOutput.setEnabled(isSetCodeGenPath, gropuForSetCodeGenPath);
		addField(codeGenerationOutput);
		
		GridLayout lay = (GridLayout) gropuForSetCodeGenPath.getLayout();
		lay.numColumns = 3;
		
		layout2 = (GridLayout) groupForCodeOutput.getLayout();
		layout2.numColumns = 1;
		
		setConfigurationEnabled();
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent event) {
		super.propertyChange(event);
		if(event.getSource() instanceof RadioGroupFieldEditor){
			if(((RadioGroupFieldEditor)event.getSource()).getPreferenceName().equals(Constants.SELECT_GEN_OUTPUT_METHOD)){
				String newVal = (String) event.getNewValue();
				boolean otherOutChosen = newVal.equals(Constants.CODE_GEN_OTHER_OUTPUT) ? true : false;
				codeGenerationOutput.setEnabled(otherOutChosen, gropuForSetCodeGenPath);
				isSetCodeGenPath = otherOutChosen;
			}
		}
		if(event.getProperty().equals(FieldEditor.VALUE)){
			checkState();
		}
	}
	
	@Override
	protected void checkState() {
		super.checkState();
		setConfigurationEnabled();
		validate(true);
	}
	
	@Override
	protected void performDefaults() {
		super.performDefaults();
		codeGenerationOutput.setEnabled(false, gropuForSetCodeGenPath);
	}
	
	@Override
	protected void performApply() {
		if(validate(false))
			super.performApply();
	}
	
	private void setConfigurationEnabled() {
		boolean enable = setConfiguration.getBooleanValue();
		modelioExec.setEnabled(enable, fieldEditorParent);
		modelioWorkspace.setEnabled(enable, fieldEditorParent);
		groupForCreateProject.setEnabled(enable);
		createProject.setEnabled(enable, groupForCreateProject);
		createProjectName.setEnabled(enable, groupForCreateProject);
		existedProjectLocation.setEnabled(enable, groupForCreateProject);
		groupForCodeOutput.setEnabled(enable);
		codeGenerationOutputFlag.setEnabled(enable, groupForCodeOutput);
	}
	
	private boolean validate(boolean resetExistingProjectSelection) {
		boolean isExeSet = false;
		boolean isWorkspaceSet = false;
		boolean isCreateProjectSet = false;
		boolean isProjectSelected = false;
		boolean isCodeOutSet = true;
		
		if(setConfiguration.getBooleanValue() == false){
			return false;
		}
		
		if(modelioExec.getStringValue() != null && !modelioExec.getStringValue().equals("")){
			if(!isExePathValid(modelioExec.getStringValue())){
				/*modelioExec.*/setErrorMessage(INSTALL_PATH_NOT_VALID);
			}
			else if(!isFileExists(modelioExec.getStringValue())){
				/*modelioExec.*/setErrorMessage(INSTALL_PATH_NOT_EXISTS);
			}
			else{
				/*modelioExec.*/setErrorMessage(null);
				isExeSet = true;
			}
		} else{
			/*modelioExec.*/setErrorMessage(INSTALL_PATH_BLANK);
		}
		
		if(modelioWorkspace.getStringValue() != null && !modelioWorkspace.getStringValue().equals("")){
			
			if(resetExistingProjectSelection)
				existedProjectLocation.resetSelection();
			
			if(!isFileExists(modelioWorkspace.getStringValue())){
				/*modelioWorkspace.*/setErrorMessage(WORKSPACE_PATH_NOT_EXISTS);
			}
			else{
				/*modelioWorkspace.*/setErrorMessage(null);
				isWorkspaceSet = true;
			}
		} else{
			/*modelioWorkspace.*/setErrorMessage(WORKSPACE_PATH_BLANK);
		}
		
		if(createProject.getBooleanValue() == true){
			existedProjectLocation.setEnabled(false, groupForCreateProject);
			createProjectName.setEnabled(true, groupForCreateProject);
			existedProjectLocation.setStringToParse(null);
			existedProjectLocation.doLoad();
			if(createProjectName.getStringValue() != null && !createProjectName.getStringValue().equals("")){
				String projectLocation = modelioWorkspace.getStringValue() + "\\" + createProjectName.getStringValue();
				if(isFileExists(projectLocation)){
					/*createProjectName.*/setErrorMessage(PROJECT_ALREADY_EXISTS);
				}
				else{
					/*createProjectName.*/setErrorMessage(null);
					isCreateProjectSet = true;
				}
			} else{
				/*createProjectName.*/setErrorMessage(PROJECT_NAME_BLANK);
			}
			
		} else{
			existedProjectLocation.setEnabled(true, groupForCreateProject);
			createProjectName.setEnabled(false, groupForCreateProject);
			existedProjectLocation.setStringToParse(modelioWorkspace.getStringValue());
			existedProjectLocation.doLoad();
			
			if(existedProjectLocation.getSelection() != null && existedProjectLocation.getSelection().length != 0){
				existedProjectLocation.getList().setSelection(existedProjectLocation.getSelection());
				setErrorMessage(null);
				isProjectSelected = true;
			} else{
				setErrorMessage(PROJECT_NOT_SELECTED);
			}
		}
		
		if(isSetCodeGenPath){
			if(codeGenerationOutput.getStringValue() != null && !codeGenerationOutput.getStringValue().equals("")){
				if(!isFileExists(codeGenerationOutput.getStringValue())){
					/*codeGenerationOutput.*/setErrorMessage(CODE_PATH_NOT_EXISTS);
					isCodeOutSet = false;
				}
				else{
					/*codeGenerationOutput.*/setErrorMessage(null);
				}
			} else{
				/*codeGenerationOutput.*/setErrorMessage(CODE_PATH_BLANK);
				isCodeOutSet = false;
			}
		}
		isCreateProjectSet = isCreateProjectSet ? isCreateProjectSet : isProjectSelected;
		boolean res = isExeSet && isWorkspaceSet && isCreateProjectSet && isCodeOutSet;
		setValid(res);
		return res;
	}
	
	private boolean isExePathValid(String path) {
		boolean val = path.lastIndexOf('\\') != -1 ? true : false;
		if(!val) return false;
		int endIndex = path.lastIndexOf('\\');
		path = path.substring(0, endIndex);
		File folder = new File(path);
		File[] folders = folder.listFiles(new FileFilter() {
			
			@Override
			public boolean accept(File f) {
				if(f.isDirectory() && (f.getName().equals("mdastore") || f.getName().equals("mdk") || f.getName().equals("templates"))){
					return true;
				}
				return false;
			}
		});
		if(folders != null && folders.length != 0){
			return true;
		}
		return false;
	}
	
	
	private boolean isFileExists(String path) {
		return new File(path).exists();
	}

}
