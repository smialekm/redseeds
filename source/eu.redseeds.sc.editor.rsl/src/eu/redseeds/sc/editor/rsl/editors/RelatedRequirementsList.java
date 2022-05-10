package eu.redseeds.sc.editor.rsl.editors;

import java.util.List;

import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementDTO;

public class RelatedRequirementsList {
	
	private List<RequirementDTO> relatedRequirements;
	private String[] relationTypes = new String[] {"constrains", "fulfills", "makes possible", "operationalises"};
	
	public RelatedRequirementsList(RequirementDTO requirement) {
		
		relatedRequirements = requirement.getRelatedRequirements();

//		relatedRequirements = new Vector<RequirementDTO>();
//		relatedRequirements.add(requirement);
//		relatedRequirements.add(requirement);
	}
	
	public String[] getRelationTypes() {
		return relationTypes;
	}
	
	public List<RequirementDTO> getRelatedRequirements() {
		return relatedRequirements;
	}
	
	public void addRelatedRequirement(RequirementDTO req) {

	}

	public void removeRelatedRequirement(RequirementDTO req) {

	}

	public void relatedRequirementChanged(RequirementDTO req) {

	}

	public void removeChangeListener(IRelatedRequirementsViewer viewer) {
		//hangeListeners.remove(viewer);
	}

	public void addChangeListener(IRelatedRequirementsViewer viewer) {
		//changeListeners.add(viewer);
	}

	

}
