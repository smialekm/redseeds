package eu.redseeds.sc.editor.rsl.editors.domain.controls.views;

import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionSpecialisationDTO;

public class NotionSpecialisationsList {
	
	public final static String SPECIALISED = "Specialised";
	public final static String GENERAL = "General";
	
	public final static String[] roleTypes = { SPECIALISED, GENERAL };
	
	/**
	 * gets notion which will be used for showing relationship's properties
	 * @param relation
	 * @param parentNotion used for comparison in determining whether to use source or target from relationship 
	 * @return
	 */
	public static NotionDTO getElementToDisplay(NotionSpecialisationDTO relation, NotionDTO parentNotion) {
		boolean showSource = true;//if true will show properties for relationship source, otherwise for target
		if(relation.getSpecialisedNotion().equals(parentNotion)) {
			showSource = false;
		}
		NotionDTO result = null;
		if(showSource) {
			result = relation.getSpecialisedNotion();
		}
		else {
			result = relation.getGeneralNotion();
		}
		return result;
	}
	
	/**
	 * determines whether notion is a general or a specialised notion in a relationship
	 * @param relation
	 * @param parentNotion used for comparison in determining whether to use specialised or general notion from relationship 
	 * @return
	 */
	public static String getRoleToDisplay(NotionSpecialisationDTO relation, NotionDTO parentNotion) {
		boolean showSource = true;//if true will show properties for relationship source, otherwise for target
		if(relation.getSpecialisedNotion().equals(parentNotion)) {
			showSource = false;
		}
		String result = null;
		if(showSource) {
			result = SPECIALISED;
		}
		else {
			result = GENERAL;
		}
		return result;
	}
	
}
