package eu.redseeds.scl.model.rsl.rsldomainelements.actors;

import java.util.List;

import eu.redseeds.scl.model.rsl.rsldomainelements.domainelements.ActorOrSystemElementDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.domainelements.DomainElementRelationshipDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.NounLinkDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.NounPhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.systemelements.SystemElementDTO;


public interface ActorDTO extends ActorOrSystemElementDTO {
	
	public String getName();
	
	public NounPhraseDTO getNounPhrase();
	
	public void delete();

	public void setName(String name);
	
	public void setNamePhrase(NounPhraseDTO phrase);

	public String toString();
	
	public ActorsPackageDTO getParent();
	
	public void setParent(ActorsPackageDTO actorsPackage);
	
	public String getSpecificationPath();
	
	public String getDescription();
	
	public void setDescription(String description);
	
	
//	public List<ActorDTO> getRelatedActorsList(); commented out. delete if not necessary
	
	public List<DomainElementRelationshipDTO> getDomainElementRelationshipDTOList();
	
	/**
	 * Adds related actor. Multiplicities are set to default (found in the DomainElementRelationshipDTO static field DEFAULT_MULTIPLICITY) on both sides of a relationship.
	 * @param otherActor
	 * @return
	 */
	public DomainElementRelationshipDTO addRelatedActor(ActorDTO otherActor);
	
	/**
	 * Adds related notion. Multiplicities are set to default (found in the DomainElementRelationshipDTO static field DEFAULT_MULTIPLICITY) on both sides of a relationship.
	 * @param otherNotion
	 * @return
	 */
	public DomainElementRelationshipDTO addRelatedNotion(NotionDTO otherNotion);
	
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
	 * Renames an actor (also all necessary domain statements, hyperlinks in descriptions)
	 * @param newName
	 */
	public void rename(NounPhraseDTO phrase);
	
	/**
	 * Returns non-basic form values for an actor
	 * @return
	 */
	public List<NounLinkDTO> getNonBasicNounLinksValues();
}