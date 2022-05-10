package eu.redseeds.scl.model.impl.rsl.rslrequirements.requirementsrelationships;

import java.util.List;

import de.uni_koblenz.jgralab.Graph;
import eu.redseeds.scl.impl.rsl.rslrequirements.requirementrelationships.RequirementVocabularyRelationshipImpl;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsrelationships.RequirementVocabularyRelationshipDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementDTO;
import eu.redseeds.scl.rsl.rsldomainelements.notions.Notion;
import eu.redseeds.scl.rsl.rslrequirements.requirementsspecifications.Requirement;
import eu.redseeds.scl.sclkernel.SCLElement;

public class RequirementVocabularyRelationshipDTOImpl extends RequirementVocabularyRelationshipImpl implements RequirementVocabularyRelationshipDTO {

	public RequirementVocabularyRelationshipDTOImpl(int id, Graph g) {
		super(id, g);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void setRequirement(RequirementDTO req) {
		if (req != null) {
			this.addSource((Requirement) req);
		}
	}
	
	@Override
	public void setNotion(NotionDTO notion) {
		if (notion != null) {
			this.addTarget((Notion) notion);
		}
	}
	
	@Override
	public RequirementDTO getRequirement() {
		List<? extends SCLElement> sourceList = getSourceList();
		if(sourceList != null) {
			if(!sourceList.isEmpty()) {
				return (RequirementDTO) sourceList.get(0);
			}
		}
		return null;
	}
	
	@Override
	public NotionDTO getNotion() {
		List<? extends SCLElement> targetList = getTargetList();
		if(targetList != null) {
			if(!targetList.isEmpty()) {
				return (NotionDTO) targetList.get(0);
			}
		}
		return null;
	}
	
	@Override
	public void deleteRelationship() {
		super.delete();
	}

}
