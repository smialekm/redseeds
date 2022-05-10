package eu.redseeds.scl.model.rsl.rsldomainelements.systemelements;

import java.util.List;

import eu.redseeds.scl.model.rsl.rsldomainelements.actors.ActorDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.domainelements.ActorOrSystemElementDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.domainelements.DomainElementRelationshipDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.NounLinkDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.NounPhraseDTO;


public interface SystemElementDTO extends ActorOrSystemElementDTO{
	
	public String getName();
	
	public NounPhraseDTO getNounPhrase();

	public void setName(String name);
	
	public void setNamePhrase(NounPhraseDTO phrase);

	public void delete();
	
	public String toString();
	
	public SystemElementsPackageDTO getParent();
	
	public void setParent(SystemElementsPackageDTO pack);
	
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
	 * @param notion
	 * @return
	 */
	public DomainElementRelationshipDTO addRelatedNotion(NotionDTO notion);
	
	/**
	 * Adds related system element. Multiplicities are set to default (found in the DomainElementRelationshipDTO static field DEFAULT_MULTIPLICITY) on both sides of a relationship.
	 * @param otherSystemElement
	 * @return
	 */
	public DomainElementRelationshipDTO addRelatedSystemElement(SystemElementDTO otherSystemElement);
	
	public boolean isNameUnique(String name);
	
	/**
	 * Checks if actor is related to a given notion
	 * @param notion
	 * @return
	 */
	public boolean checkIfRelated(Object domainElement);
	
	/**
	 * Renames a system element (also all necessary domain statements, hyperlinks in descriptions)
	 * @param newName
	 */
	public void rename(NounPhraseDTO phrase);
	
	/**
	 * Returns non-basic form values for a system element
	 * @return
	 */
	public List<NounLinkDTO> getNonBasicNounLinksValues();
	
}