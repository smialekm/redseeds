package eu.redseeds.scl.model.rsl.rslrequirements.usecaserelationships;

import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.UseCaseDTO;

public interface InvocationRelationshipDTO {
	
	public void setSource(UseCaseDTO uc);
	public void setTarget(UseCaseDTO uc);
	
	public UseCaseDTO getSource();
	public UseCaseDTO getTarget();
	
	public int getInvocationType();
	
	public void deleteRelationship();
	
	public String getUid();

}
