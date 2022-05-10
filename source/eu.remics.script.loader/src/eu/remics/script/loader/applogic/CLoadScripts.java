package eu.remics.script.loader.applogic;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentations.descriptiverequirementrepresentations.ConstrainedLanguageScenarioDTO;
import eu.remics.recovery.manager.presentation.VErrorMessage;
import eu.remics.recovery.model.domainlogic.recoveredscenarios.MListOfScripts;
import eu.remics.recovery.model.domainlogic.usecases.MNotion;
import eu.remics.recovery.model.domainlogic.usecases.MUnassignedScenariosList;
import eu.remics.recovery.model.dto.TerminologyOperationFailureException;
import eu.remics.script.loader.presentation.VContinueOrStopProcessingMessage;
import eu.remics.script.loader.presentation.VFileSelectorWindow;
import eu.remics.script.loader.presentation.VFolderWithMapFilesOrEmptyNotionMessage;
import eu.remics.util.SCNavigatorHelper;
/**
 * @author Stacja1
 * @version 1.0
 * @created 14-lis-2011 12:54:15
 */
public class CLoadScripts {

	public List<String> aListOfScripts;
	public String aMapFolder;
	public NotionDTO aNotion;
	public ConstrainedLanguageScenarioDTO aScenario;
	VFileSelectorWindow nFileSelectorWindow;
	MListOfScripts nListOfScripts;
	MNotion nNotion;
	MUnassignedScenariosList nUnassignedScenariosList;

	public VContinueOrStopProcessingMessage vContinueOrStopProcessingMessage;

	public VFolderWithMapFilesOrEmptyNotionMessage vFolderWithMapFilesOrEmptyNotionMessage;

	public MNotion mNotion;

	public VFileSelectorWindow vFileSelectorWindow = new VFileSelectorWindow();
	
	public VErrorMessage vErrorMessage = new VErrorMessage();

	public MListOfScripts mListOfScripts = new MListOfScripts();;

	public MUnassignedScenariosList mUnassignedScenariosList;

	public CLoadScripts(){

	}

	public void finalize() throws Throwable {

	}

	public void _ClicksLoadScriptsButton(){
		String[] paths = vFileSelectorWindow.displays();
		if(paths == null || paths.length == 0 || paths[0].equals("")){
			return;
		}
		ClicksOpenButton(paths);

	}

	public int ChoosesOption(int res, int r) {
		if (res == 0 /* User chooses continue option */) {
			vFolderWithMapFilesOrEmptyNotionMessage = new VFolderWithMapFilesOrEmptyNotionMessage();
			vFolderWithMapFilesOrEmptyNotionMessage.cLoadScripts = this;
			res = vFolderWithMapFilesOrEmptyNotionMessage.displays();

			if (res == 0 /* User chooses choose manually option */) {
				aMapFolder = vFileSelectorWindow.displaysFolderSelector();

				try {
					r = MListOfScripts.processes(aListOfScripts, aMapFolder);
					vFileSelectorWindow.showUnassignedScenariosListView();
				} catch (TerminologyOperationFailureException e) {
					e.printStackTrace();
					vErrorMessage.displays("Terminology server is not connected.");
				} catch (InstantiationException e) {
					e.printStackTrace();
					vErrorMessage.displays("Can not create a new instance of name converter class.");
				} catch (IllegalAccessException e) {
					e.printStackTrace();
					vErrorMessage.displays("Can not find proper method in name converter class.");
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
					vErrorMessage.displays("Can not find proper name converter class.");
				}
			} else if (res == 1 /* User chooses empty notion option */) {
				try {
					r = MListOfScripts.processesWithEmptyNotions(aListOfScripts);
					vFileSelectorWindow.showUnassignedScenariosListView();
				} catch (TerminologyOperationFailureException e) {
					e.printStackTrace();
					vErrorMessage.displays("Terminology server is not connected.");
				} catch (InstantiationException e) {
					e.printStackTrace();
					vErrorMessage.displays("Can not create a new instance of name converter class.");
				} catch (IllegalAccessException e) {
					e.printStackTrace();
					vErrorMessage.displays("Can not find proper method in name converter class.");
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
					vErrorMessage.displays("Can not find proper name converter class.");
				}
			}
		}
		else if (res == 1 /* User chooses stop option */) {

		}
		return r;
	}

	public void ClicksOpenButton(String[] scriptsPaths){
		int res=0;
		int r = 0;
		aListOfScripts = new ArrayList<String>(Arrays.asList(scriptsPaths));
		try {
			r = MListOfScripts.processes(aListOfScripts);
			vFileSelectorWindow.showUnassignedScenariosListView();
		} catch (TerminologyOperationFailureException e) {
			e.printStackTrace();
			vErrorMessage.displays("Terminology server is not connected.");
		} catch (InstantiationException e) {
			e.printStackTrace();
			vErrorMessage.displays("Can not create a new instance of name converter class.");
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			vErrorMessage.displays("Can not find proper method in name converter class.");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			vErrorMessage.displays("Can not find proper name converter class for.");
		}
		while(aListOfScripts.size() > r/*File not found*/ && 0==res) {
			vContinueOrStopProcessingMessage = new VContinueOrStopProcessingMessage();
			vContinueOrStopProcessingMessage.cLoadScripts = this;
			res=vContinueOrStopProcessingMessage.displays();
			r = ChoosesOption(res, r);
		}
		if(r > 0){
			String scriptsWithErrors = "";
			for(int i=0; i < aListOfScripts.size(); i++){
				String name = aListOfScripts.get(i);
				name = name.substring(name.lastIndexOf("\\")+1);
				if(i == aListOfScripts.size()-1)
					scriptsWithErrors += name;
				else
					scriptsWithErrors += name + ", ";
			}
			vErrorMessage.displays("Processed scripts contains errors: " + scriptsWithErrors);
		}
		SCNavigatorHelper.refresh();
	}

	public void init(){
		aListOfScripts = new ArrayList<String>();
	}
}