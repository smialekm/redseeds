package eu.redseeds.scl.model.rsl.rsldomainelements.notions;

import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.PhraseDTO;
import eu.redseeds.scl.sclkernel.Stereotype;

public interface DomainStatementDTO {
	
	public String getName();
	
	public void setName(String name);
	
	public void setPhraseDTO(PhraseDTO phrase);
	
	public PhraseDTO getPhraseDTO();
	
	public void delete();

	void setDescription(String description);

	String getDescription();
	
	public NotionDTO getParent();
	
	public String getSpecificationPath();
	
	public String getCRUD();
	
	public ActionTypesEnum getActionType();
	
	public String getActionTypeTag();

	public Stereotype getActionTypeStereotype();
	
	public boolean isProcessableDomainDomainStatement();
	
}