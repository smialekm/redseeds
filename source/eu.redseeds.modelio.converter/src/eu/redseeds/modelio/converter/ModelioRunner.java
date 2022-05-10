package eu.redseeds.modelio.converter;

import java.io.File;

import eu.redseeds.common.Constants;

public class ModelioRunner {
	
	public static void openModelio(String scProjLocation) {
		//cmd parameters for running Modelio
		String modelioPath = eu.redseeds.common.Activator.getDefault().getPreferenceStore().getString(Constants.MODELIO_EXE_PATH);
		String workspacePath = eu.redseeds.common.Activator.getDefault().getPreferenceStore().getString(Constants.MODELIO_WORKSPACE);
		boolean createProjectOnFly = eu.redseeds.common.Activator.getDefault().getPreferenceStore().getBoolean(Constants.CREATE_MODELIO_PROJECT);
		String projectName = "";
		String createCommandLineOption = " -project ";
		String templateCommandLineOption = " -template ReDSeeDS";
		if(createProjectOnFly){
			projectName = eu.redseeds.common.Activator.getDefault().getPreferenceStore().getString(Constants.CREATE_MODELIO_PROJECT_NAME);
			createCommandLineOption = " -create ";
			
			//delete old project for precautions
			String projectPath = workspacePath + "\\" + projectName;
			File file = new File(projectPath);
			deleteProjectResources(file);
			
		} else{
			projectName = eu.redseeds.common.Activator.getDefault().getPreferenceStore().getString(Constants.EXISTING_MODELIO_PROJECT);
		}
		
		PythonScriptManager pyManager = new PythonScriptManager(scProjLocation);
		
		//check if Code Gen flag is on
		boolean codeGenFlag = eu.redseeds.common.Activator.getDefault().getPreferenceStore().getBoolean(Constants.CODE_GEN);
		
		//check which Code Gen output option is set
		String radioBtnOption = eu.redseeds.common.Activator.getDefault().getPreferenceStore().getString(Constants.SELECT_GEN_OUTPUT_METHOD);
		boolean codeGenInSCProject = radioBtnOption.equals(Constants.CODE_GEN_SC_PROJECT_OUTPUT) ? true : false;
		String codeGenOutPath = "";
		if(!codeGenInSCProject){
			//get Code Gen output path
			codeGenOutPath = eu.redseeds.common.Activator.getDefault().getPreferenceStore().getString(Constants.CODE_GEN_OUTPUT_PATH);
			codeGenOutPath = codeGenOutPath.replace("\\", "/");
		}
		else{
			codeGenOutPath = scProjLocation + "/CurrentSC/Code";
		}
		
		//generate py script
		pyManager.generatePyScript(codeGenFlag, codeGenOutPath);
		
		//run Modelio instance with script execution
		Runtime rt = Runtime.getRuntime();
		String command = "";
		if(createProjectOnFly){
			command = "cmd /C start " + modelioPath + " -workspace " + "\"" + workspacePath + "\"" + createCommandLineOption + projectName + templateCommandLineOption + " -script " + "\"" + pyManager.getScriptPath() + "\"";
		}
		else{
			command = "cmd /C start " + modelioPath + " -workspace " + "\"" + workspacePath + "\"" + createCommandLineOption + projectName + " -script " + "\"" + pyManager.getScriptPath() + "\"";
		}
		try {
			rt.exec(command);
		} catch (Exception ex) {
			Activator.logException(ex);
		}
	}
	
	private static void deleteProjectResources(File file) {
		if(file.exists()){
			File[] files = file.listFiles();
			if(files != null && files.length != 0){
				for(int i=0; i < files.length; i++){
					File f = files[i];
					if(f.isDirectory()){
						deleteProjectResources(f);
					}
					f.delete();
				}
			}
			file.delete();
		}
	}
}
