package eu.redseeds.scl.model.rsl.rsldomainelements.notions;

import java.util.List;

import org.eclipse.ui.IActionFilter;

import eu.redseeds.scl.model.rsl.rsldomainelements.DomainElementDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.actors.ActorDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.domainelements.DomainElementRelationshipDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.NounLinkDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.NounPhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.PhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.systemelements.SystemElementDTO;


public interface NotionDTO extends DomainElementDTO, IActionFilter{

	public String getUid();
	
	public void setName(String name);

	public void setNamePhrase(NounPhraseDTO phrase);

	public String getName();

	public NounPhraseDTO getNamePhrase();

	public void delete();

	public String toString();

	public List<DomainStatementDTO> getDomainStatementDTOList();

	public void addDomainStatementDTO(DomainStatementDTO statement);

	public NotionsPackageDTO getParent();

	public void setParent(NotionsPackageDTO pack);

	public String getSpecificationPath();

	public String getDescription();

	public void setDescription(String description);

	public List<DomainElementRelationshipDTO> getDomainElementRelationshipDTOList();

	/**
	 * Adds related actor. Multiplicities are set to default (found in the DomainElementRelationshipDTO static field DEFAULT_MULTIPLICITY) on both sides of a relationship.
	 * @param actor
	 * @return
	 */
	public DomainElementRelationshipDTO addRelatedActor(ActorDTO actor);

	/**
	 * Adds related notion. Multiplicities are set to default (found in the DomainElementRelationshipDTO static field DEFAULT_MULTIPLICITY) on both sides of a relationship.
	 * @param otherNotion
	 * @return
	 */
	public DomainElementRelationshipDTO addRelatedNotion(NotionDTO otherNotion);

	/**
	 * Adds related system element. Multiplicities are set to default (found in the DomainElementRelationshipDTO static field DEFAULT_MULTIPLICITY) on both sides of a relationship.
	 * @param systemElement
	 * @return
	 */
	public DomainElementRelationshipDTO addRelatedSystemElement(SystemElementDTO systemElement);

	public List<NotionSpecialisationDTO> getNotionSpecialisationDTOList();

	public void addSpecialisedNotion(NotionDTO notion);

	public void addGeneralNotion(NotionDTO notion);

	public boolean isNameUnique(String name);

	public DomainStatementDTO getDomainStatementDTO(PhraseDTO phrase);

	/**
	 * Checks if actor is related to a given domain element
	 * @param domainElement
	 * @return
	 */
	public boolean checkIfRelated(Object domainElement);

	/**
	 * Renames a notion (also all necessary domain statements, hyperlinks in descriptions)
	 * @param newName
	 */
	public void rename(NounPhraseDTO phrase, List<NounLinkDTO> links);

	/**
	 * Returns non-basic form values for a notion
	 * @return
	 */
	public List<NounLinkDTO> getNonBasicNounLinksValues();
	
	/**
	 * sets data type for this notion
	 * @param dataType
	 */
	public void setDataType(PrimitiveDataTypeDTO dataType);
	
	/**
	 * gets data type of this notion
	 * @return
	 */
	public PrimitiveDataTypeDTO getDataType();
	
	/**
	 * gets notion attributes
	 * @return
	 */
	public List<NotionDTO> getNotionDTOAttributeList();
	
	/**
	 * adds a notion as a notion attribute. If attribute already in the attribute list does nothing 
	 * @param attribute
	 */
	public void addNotionDTOAttribute(NotionDTO attribute);
	
	/**
	 * removes given notion from notion attribute list (notion is not deleted)
	 * @param attribute
	 */
	public void removeNotionDTOAttribute(NotionDTO attribute);
	
	/**
	 * gets notion that this one is an attribute of (or null if notion is not an attribute)
	 * @return
	 */
	public List<NotionDTO> getNotionAttributeParents();
	
	public String getType();
	
	public List<String> getCRUDs();
	
	public String getExtendedDataType();
	
	public List<NotionDTO> getPointedNotionDTOAttributeList();
	
	public List<NotionDTO> getPointingParentNotionList();
	
	public boolean isDomainNotion();

	public NotionDTO getMainConcept();
	
}