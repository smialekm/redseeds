package eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications;

import java.util.List;

import eu.redseeds.scl.model.rsl.IHierarchyAware;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsrelationships.NonInvocationRelationshipDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsrelationships.RequirementVocabularyRelationshipDTO;

public interface RequirementDTO extends IHierarchyAware{

	/**
	 *
	 * @param relationType
	 * @param req
	 */
	public String getUid();
	
	public NonInvocationRelationshipDTO addRelatedRequirement(RequirementDTO req, int relType, boolean isTarget);

	public RequirementsPackageDTO getParent();

	public String getSpecificationPath();

	public List<RequirementDTO> getRelatedRequirements();

	public List<NonInvocationRelationshipDTO> getNonInvocationRelationshipDTOFromList();

	public List<NonInvocationRelationshipDTO> getNonInvocationRelationshipDTOToList();

	public String getName();

	public void setName(String name);

	public String getDescription();

	public void setDescription(String description);

	public void delete();

	public String toString();

	public String getRequirementId();

	public void setRequirementId(String id);

	public void setParent(RequirementsPackageDTO parent);

	public boolean isNameUnique(String name);

	public List<NotionDTO> getRelatedNotions();

	/**
	 * Checks if actor is related to a given notion
	 * @param notion
	 * @return
	 */
	public boolean checkIfRelated(NotionDTO notion);

	public RequirementVocabularyRelationshipDTO addRelatedNotion(NotionDTO notion);

}