package eu.redseeds.sc.editor.rsl.editors.domain.controls.views;

import eu.redseeds.scl.model.rsl.rsldomainelements.domainelements.DomainElementRelationshipDTO;

public class RelatedDomainElementsList {
	
	public final static String[] boolValues = { "true", "false" };
	
	/**
	 * gets element which will be used for showing relationsahip's properties
	 * @param relation
	 * @param parentObject used for comparison in determining whether to use source or target from relationship 
	 * @return
	 */
	public static Object getElementToDisplay(DomainElementRelationshipDTO relation, Object parentObject) {
		boolean showSource = true;//if true will show properties for relationship source, otherwise for target
		if(relation.getSource().equals(parentObject)) {
			showSource = false;
		}
		Object result = null;
		if(showSource) {
			result = relation.getSource();
		}
		else {
			result = relation.getTarget();
		}
		return result;
	}
	
	/**
	 * determines whether to display source or target element multiplicity using given element. This returns given element's multiplicity
	 * @param relation
	 * @param parentObject used for comparison in determining whether to use source or target from relationship 
	 * @return
	 */
	public static String getThisMultiplicityToDisplay(DomainElementRelationshipDTO relation, Object parentObject) {
		boolean showSource = true;//if true will show properties for relationship source, otherwise for target
		if(relation.getSource().equals(parentObject)) {
			showSource = true;
		}
		else {
			showSource = false;
		}
		String result = null;
		if(showSource) {
			result = relation.getSourceMultiplicity();
		}
		else {
			result = relation.getTargetMultiplicity();
		}
		return result;
	}
	
	/**
	 * determines whether to display source or target element multiplicity using given element. This returns related element's multiplicity
	 * @param relation
	 * @param parentObject used for comparison in determining whether to use source or target from relationship 
	 * @return
	 */
	public static String getOtherMultiplicityToDisplay(DomainElementRelationshipDTO relation, Object parentObject) {
		boolean showSource = true;//if true will show properties for relationship source, otherwise for target
		if(relation.getSource().equals(parentObject)) {
			showSource = false;
		}
		String result = null;
		if(showSource) {
			result = relation.getSourceMultiplicity();
		}
		else {
			result = relation.getTargetMultiplicity();
		}
		return result;
	}
	
	/**
	 * Determines whether to set source or target element multiplicity and updates multiplicity value accordingly. This one updates given element's multiplicity. 
	 * @param relation
	 * @param parentObject used for comparison in determining whether to use source or target from relationship 
	 * @param newValue new value of multiplicity to set
	 * @return
	 */
	public static void setThisMultiplicity(DomainElementRelationshipDTO relation, Object parentObject, String newValue) {
		boolean showSource = true;//if true will show properties for relationship source, otherwise for target
		if(relation.getSource().equals(parentObject)) {
			showSource = true;
		}
		else {
			showSource = false;
		}
		if(showSource) {
			relation.setSourceMultiplicity(newValue);
		}
		else {
			relation.setTargetMultiplicity(newValue);
		}
	}
	
	/**
	 * Determines whether to set source or target element multiplicity and updates multiplicity value accordingly. This one updates element's multiplicity of an element related to given. 
	 * @param relation
	 * @param parentObject used for comparison in determining whether to use source or target from relationship 
	 * @param newValue new value of multiplicity to set
	 * @return
	 */
	public static void setOtherMultiplicity(DomainElementRelationshipDTO relation, Object parentObject, String newValue) {
		boolean showSource = true;//if true will show properties for relationship source, otherwise for target
		if(relation.getSource().equals(parentObject)) {
			showSource = false;
		}
		if(showSource) {
			relation.setSourceMultiplicity(newValue);
		}
		else {
			relation.setTargetMultiplicity(newValue);
		}
	}

}
