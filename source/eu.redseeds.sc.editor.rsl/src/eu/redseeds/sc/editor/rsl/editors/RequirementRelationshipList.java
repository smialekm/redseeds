package eu.redseeds.sc.editor.rsl.editors;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import eu.redseeds.scl.model.rsl.rslrequirements.requirementsrelationships.NonInvocationRelationshipDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementDTO;

public class RequirementRelationshipList {

	private List<NonInvocationRelationshipDTO> relationshipsTo;
	private List<NonInvocationRelationshipDTO> relationshipsFrom;
	private List<RequirementDTO> relatedRequirements;

	private Set<IRelatedRequirementsViewer> changeListeners = new HashSet<IRelatedRequirementsViewer>();
	
	
	public RequirementRelationshipList(RequirementDTO requirement) {		
		relationshipsTo = requirement.getNonInvocationRelationshipDTOFromList();
		relationshipsFrom = requirement.getNonInvocationRelationshipDTOToList();
		relatedRequirements = requirement.getRelatedRequirements();
	}
	
	
	public List<RequirementDTO> getRelatedRequirements() {
		return relatedRequirements;
	}
	
	public List<NonInvocationRelationshipDTO> getRelationships() {
		List<NonInvocationRelationshipDTO> result = new Vector<NonInvocationRelationshipDTO>();
		result.addAll(relationshipsFrom);
		result.addAll(relationshipsTo);
		return result;
	}
	
	public void addRelationship(NonInvocationRelationshipDTO rel) {

	}

	public void removeRelationship(NonInvocationRelationshipDTO rel) {

	}

	public void relationshipChanged(NonInvocationRelationshipDTO rel) {

	}

	public void removeChangeListener(IRelatedRequirementsViewer viewer) {
		changeListeners.remove(viewer);
	}

	public void addChangeListener(IRelatedRequirementsViewer viewer) {
		changeListeners.add(viewer);
	}
	
}
