package eu.redseeds.owl;

public class Step {
	private String role;
	private String concept;
	
	public Step (String role){
		this.role = role;
		this.concept = "";
	}
	
	public Step (String concept, String role){
		// Only role names without "-of" because in the graph those are not used
		this.role = role.split("-of")[0];
		this.concept = concept;
//		System.out.println("Create Step: " + this.role + " " + this.concept);		
			}
	
	public String getConcept () {
		return this.concept;
	}
	
	public String getRole () {
		return this.role;
	}
	
	public String toString(){
		return "<ST>Concept: " + this.concept + " Role: " + this.role;
	}
}
