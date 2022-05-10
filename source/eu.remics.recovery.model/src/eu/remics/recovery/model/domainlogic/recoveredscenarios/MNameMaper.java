package eu.remics.recovery.model.domainlogic.recoveredscenarios;

import eu.remics.recovery.model.preferences.MConfiguration;

public class MNameMaper {

	/**
	 * Maps script name to scenario name
	 * 
	 * @param name script name
	 * @return scenario name
	 */
	//Maps name
	public static String mapScriptNameToScenarioName(String name){
		for (String s:MConfiguration.getMaperMap().keySet()){
			name=name.replaceAll(s,MConfiguration.getMaperMap().get(s));
		}
		if (MConfiguration.isFirstUpperCaseChar()) name=name.substring(0,1).toUpperCase()+name.substring(1);
		return name;
	}
	
}
