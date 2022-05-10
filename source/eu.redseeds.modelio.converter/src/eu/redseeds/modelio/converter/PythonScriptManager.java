package eu.redseeds.modelio.converter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class PythonScriptManager {
	
	private static final String MODELIO_PYTHON_SCRIPT = "generateModelAndCode.py";
	private static final String GRAPH_FILE_NAME = "sclgraph.tg";
	
	private String scriptPath;
	private String sclGraphPath;
	private String PY_REDSEEDS;
	private String PY_REDSEEDS_AND_CODE;

	public PythonScriptManager(String scProjLocation) {
		this.scriptPath = scProjLocation + "/" + MODELIO_PYTHON_SCRIPT;
		this.sclGraphPath = " \"" + scProjLocation + "/" + GRAPH_FILE_NAME + "\"";
		this.PY_REDSEEDS = buildReDSeeDSImportPart();
	}
	
	public String getScriptPath() {
		return scriptPath;
	}
	
	public void generatePyScript(boolean codeGenFlag, String codeGenOutPath) {
		File py = new File(scriptPath);
		if(py.exists()){
			py.delete();
		}
		try {
			py.createNewFile();
			FileWriter writer = new FileWriter(py);
			if(codeGenFlag){
				PY_REDSEEDS_AND_CODE = buildWithCodeGenOutputPart(codeGenOutPath);
				writer.write(PY_REDSEEDS_AND_CODE);
			}
			else{
				writer.write(PY_REDSEEDS);
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private String buildReDSeeDSImportPart() {
		String code = new StringBuilder()
		.append("from java.io import File\n")
		.append("from com.modeliosoft.modelio.core.app import O\n")
		.append("modelingSession = Modelio.getInstance().getModelingSession()\n")
		.append("moduleService = Modelio.getInstance().getModuleService()\n")
		.append("redseedsMdac = None\n")
		.append("for module in moduleService.getAllPeerMdacs():\n")
		.append("	if (module.getName() == \'ReDSeeDSImporter\'):\n")
		.append("		redseedsMdac = module\n")
		.append("		break\n")
		.append("if redseedsMdac is None :\n")
		.append("	print \'No ReDSeeDS Importer module found in the project\'\n")
		.append("else:\n")
		.append("	print \'Importing with \' + redseedsMdac.getName() + \' \' + redseedsMdac.getVersion().toString()\n")
		.append("	t = modelingSession.createTransaction(\'Import ReDSeeDS model\')\n")
		.append("	sclGraphFilePath =" + sclGraphPath + "\n")
		.append("	file = File(sclGraphFilePath)\n")
		.append("	redseedsMdac.importReDSeeDSModel(file)\n")
		.append("	modelingSession.commit(t)\n")
		.append("	O.getDefault().getModelingSession().save()\n")
		.toString();
		
		return code;
	}
	//TODO useful method for code generation in default Modelio workspace
	private String buildWithCodeGenPart() {
		String code = new StringBuilder()
		.append("from java.io import File\n")
		.append("from com.modeliosoft.modelio.core.app import O\n")
		.append("from java.lang import Class\n")
		.append("modelingSession = Modelio.getInstance().getModelingSession()\n")
		.append("moduleService = Modelio.getInstance().getModuleService()\n")
		.append("redseedsMdac = None\n")
		.append("for module in moduleService.getAllPeerMdacs():\n")
		.append("	if (module.getName() == \'ReDSeeDSImporter\'):\n")
		.append("		redseedsMdac = module\n")
		.append("		break\n")
		.append("if redseedsMdac is None :\n")
		.append("	print \'No ReDSeeDS Importer module found in the project\'\n")
		.append("else:\n")
		.append("	print \'Importing with \' + redseedsMdac.getName() + \' \' + redseedsMdac.getVersion().toString()\n")
		.append("	t = modelingSession.createTransaction(\'Import ReDSeeDS model\')\n")
		.append("	sclGraphFilePath =" + sclGraphPath + "\n")
		.append("	file = File(sclGraphFilePath)\n")
		.append("	redseedsMdac.importReDSeeDSModel(file)\n")
		.append("	modelingSession.commit(t)\n")
		.append("	O.getDefault().getModelingSession().save()\n\n\n")
		.append("javaMdac = None\n")
		.append("for module in moduleService.getAllPeerMdacs():\n")
		.append("	if (module.getName() == \'JavaDesigner\'):\n")
		.append("		javaMdac = module\n")
		.append("		break\n")
		.append("if javaMdac is None :\n")
		.append("	print \'No Java Designer module found in the project\'\n")
		.append("else:\n")
		.append("	print \'Java source generation with \' + javaMdac.getName() + \' \' +	javaMdac.getVersion().toString()\n")
		.append("	t = modelingSession.createTransaction(\'Generate sources\')\n")
		.append("	savedRetrieveMode = javaMdac.getConfiguration().getParameterValue(\'RetrieveDefaultBehaviour\')\n")
		.append("	javaMdac.getConfiguration().setParameterValue(\'RetrieveDefaultBehaviour\', \'Keep\')\n")
		.append("	root = modelingSession.getModel().getRoot()\n")
		.append("	packagesList = root.getOwnedElement(Class.forName(\'com.modeliosoft.modelio.api.model.uml.statik.IPackage\'))\n")
		.append("	for package in packagesList:\n")
		.append("		if package.getName() == 'App':\n")
		.append("			appPackage = package\n")
		.append("			break\n")
		.append("	javaMdac.generate(appPackage, False)\n")
		.append("	javaMdac.getConfiguration().setParameterValue(\'RetrieveDefaultBehaviour\', savedRetrieveMode)\n")
		.append("	modelingSession.rollback(t)\n")
		.toString();
		
		return code;
	}
	
	private String buildWithCodeGenOutputPart(String codeGenOutPath) {
		String code = new StringBuilder()
		.append("from java.io import File\n")
		.append("from java.lang import Class\n")
		.append("from com.modeliosoft.modelio.core.app import O\n")
		.append("modelingSession = Modelio.getInstance().getModelingSession()\n")
		.append("moduleService = Modelio.getInstance().getModuleService()\n")
		.append("redseedsMdac = None\n")
		.append("for module in moduleService.getAllPeerMdacs():\n")
		.append("	if (module.getName() == \'ReDSeeDSImporter\'):\n")
		.append("		redseedsMdac = module\n")
		.append("		break\n")
		.append("if redseedsMdac is None :\n")
		.append("	print \'No ReDSeeDS Importer module found in the project\'\n")
		.append("else:\n")
		.append("	print \'Importing with \' + redseedsMdac.getName() + \' \' + redseedsMdac.getVersion().toString()\n")
		.append("	t = modelingSession.createTransaction(\'Import ReDSeeDS model\')\n")
		.append("	sclGraphFilePath =" + sclGraphPath + "\n")
		.append("	file = File(sclGraphFilePath)\n")
		.append("	redseedsMdac.importReDSeeDSModel(file)\n")
		.append("	modelingSession.commit(t)\n")
		.append("	O.getDefault().getModelingSession().save()\n\n\n")
		.append("javaMdac = None\n")
		.append("for module in moduleService.getAllPeerMdacs():\n")
		.append("	if (module.getName() == \'JavaDesigner\'):\n")
		.append("		javaMdac = module\n")
		.append("		break\n")
		.append("if javaMdac is None :\n")
		.append("	print \'No Java Designer module found in the project\'\n")
		.append("else:\n")
		.append("	print \'Java source generation with \' + javaMdac.getName() + \' \' +	javaMdac.getVersion().toString()\n")
		.append("	t = modelingSession.createTransaction(\'Generate sources\')\n")
		.append("	savedRetrieveMode = javaMdac.getConfiguration().getParameterValue(\'RetrieveDefaultBehaviour\')\n")
		.append("	javaMdac.getConfiguration().setParameterValue(\'RetrieveDefaultBehaviour\', \'Keep\')\n")
		.append("	codePath = javaMdac.getConfiguration().getParameterValue(\'GenerationPath\')\n")
		.append("	genPath =" + " \"" + codeGenOutPath + "\"" + "\n")
		.append("	javaMdac.getConfiguration().setParameterValue(\'GenerationPath\', genPath)\n")
		.append("	codeMode = javaMdac.getConfiguration().getParameterValue(\'GenerationMode\')\n")
		.append("	javaMdac.getConfiguration().setParameterValue(\'GenerationMode\', \'Release\')\n")
		.append("	root = modelingSession.getModel().getRoot()\n")
		.append("	packagesList = root.getOwnedElement(Class.forName(\'com.modeliosoft.modelio.api.model.uml.statik.IPackage\'))\n")
		.append("	for package in packagesList:\n")
		.append("		if package.getName() == 'App':\n")
		.append("			appPackage = package\n")
		.append("			break\n")
		.append("	javaMdac.generate(appPackage, False)\n")
		.append("	javaMdac.getConfiguration().setParameterValue(\'RetrieveDefaultBehaviour\', savedRetrieveMode)\n")
		.append("	javaMdac.getConfiguration().setParameterValue(\'GenerationPath\', codePath)\n")
		.append("	javaMdac.getConfiguration().setParameterValue(\'GenerationMode\', codeMode)\n")
		.append("	modelingSession.rollback(t)\n")
		.toString();
		
		return code;
	}
}
