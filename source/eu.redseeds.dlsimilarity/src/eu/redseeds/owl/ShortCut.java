package eu.redseeds.owl;

import java.util.Vector;

/**
 * This class defines ShortCuts, i.e. new roles between
 * two concepts fromConcept and toConcept.
 * Those new roles are defined in a file and represented
 * with objects from this class.
 * 
 * @author Lothar Hotz
 */

public class ShortCut {
	
	private String fromConceptName;
	private String toConceptName;
	private String newRole;
	private Vector<Step> path = new Vector<Step>();
	private String stepDelimiter = "\\?";		
	private String roleConceptDelimiter = "\\+";			
	
	public ShortCut(String fromConcept, String toConcept, String newRole, String path) {
//		System.out.println("New ShortCut: " + fromConcept + "  " + toConcept);
		this.fromConceptName = fromConcept;
		this.toConceptName = toConcept;
		this.newRole = newRole;
		createSteps(path);
	}
	
	public void createSteps(String p) {
		String [] steps = p.split(stepDelimiter);		
		String role = "";
		String concept = "";
		
		//first step consists of role only
		path.add(new Step(this.fromConceptName, steps[0]));
		
		for (int i=1; i<steps.length; i++) {
			String step = steps[i];
			String[] conceptPlusRole = step.split(roleConceptDelimiter);
			concept = conceptPlusRole[0];
			role = conceptPlusRole[1];
			path.add(new Step(concept, role));
		}


	}
	
	public boolean isFromConcept (String conceptName){
//		System.out.println("FromConceptName of ShortCut:" + this.fromConceptName);
		// TODO: has to return true if concept with name "conceptName" is subclass of concept with name "this.fromConceptName"
		if (conceptName.equals(this.fromConceptName)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean isToConcept (String conceptName){
		// TODO: has to return true if conceptName is subclass of this.fromConceptName		
		if (conceptName.equals(this.toConceptName)) {
			return true;
		}
			else {
			return false;
		}
	}
	

	
	
	public Step getNextStep (String conceptName){
		System.out.println("NextStep: Conceptname: " + conceptName);
		System.out.println("      .... toConceptName: " + this.toConceptName);		
		if (conceptName.equals(this.toConceptName)) {
			return null; 
		} else {
			Step step = findStep(conceptName);
			if (step != null) 
				return step;
			else {
				System.out.println("ERROR: Concept in shortCut " + this.toString() + " cannot be reached. File not consistent with graph");
				return null; /// NOT POSSIBLE null is reserved for nice case above!
			}
		}
	}
	
	public String getNextRole (String conceptName){
//		System.out.println("NextStep: Conceptname: " + conceptName);
//		System.out.println("      .... toConceptName: " + this.toConceptName);		
		if (conceptName.equals(this.toConceptName)) {
			return ""; 
		} else {
			Step step = findStep(conceptName);
			if (step != null) 
				return step.getRole();
			else {
//				System.out.println("Warn: Concept in shortCut " + this.toString() + " cannot be reached. File not consistent with graph");
				return "false";
			}
		}
	}

	public Step findStep(String conceptName) {
		for (Step step : this.path) {
			//System.out.println("Searching concept: " + conceptName + " in step with concept " + step.getConcept());
			
			if (step.getConcept().equals(conceptName))
				return step;
		}
		return null;
	}
	
		
	public String getNewRole () {
		return this.newRole;
	}
	
	public String getFromConceptName(){
		return this.fromConceptName;
	}
	
	public String getToConceptName(){
		return this.toConceptName;
	}
	
	public Vector<Step> getPath(){
		return this.path;
	}
	
	public String toString(){
		return "<SC>From: " + this.fromConceptName + "  via: " + this.newRole
		+ "   to: " + this.toConceptName + " Path: " + this.path.toString();
	}
	
}
