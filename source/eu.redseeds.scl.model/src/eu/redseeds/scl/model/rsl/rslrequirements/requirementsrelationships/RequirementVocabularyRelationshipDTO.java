package eu.redseeds.scl.model.rsl.rslrequirements.requirementsrelationships;

import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementDTO;

public interface RequirementVocabularyRelationshipDTO {

	/**
	 * Sets the requirement (source of the relationship)
	 * @param req
	 */
	public void setRequirement(RequirementDTO req);
	
	/**
	 * Sets the notion (target of the relationship)
	 * @param notion
	 */
	public void setNotion(NotionDTO notion);
	
	/**
	 * Gets the requirement (source of the relationship)
	 * @return
	 */
	public RequirementDTO getRequirement();
	
	/**
	 * Gets the notion (target of the relationship)
	 * @return
	 */
	public NotionDTO getNotion();
	
	public void deleteRelationship();
	
}
