package eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.controlsentences;

import java.util.List;

import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.constrainedlanguagesentences.ConstrainedLanguageSentenceDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.UseCaseDTO;
import eu.redseeds.scl.rsl.rslrequirementrepresentationsentences.controlsentences.InclusionType;


public interface InvocationSentenceDTO extends ConstrainedLanguageSentenceDTO {
	
	public InclusionType getInclusionType();	
	public void setInclusionType(InclusionType inclusionType);
	
	public UseCaseDTO getInvokedUseCase();
	public void setInvokedUseCase(UseCaseDTO uc);
	public void setInvokedUseCase(UseCaseDTO uc, UseCaseDTO source);
	
	/**
	 * Adds stereotype with given name
	 * @param stereotypeName
	 */
	public void addStereotype(String stereotypeName);
	
	/**
	 * Removes stereotype with given name
	 * @param stereotypeName
	 */
	public void removeStereotype(String stereotypeName);
	
	/**
	 * Gets stereotypes' names list (as Strings)
	 * @return
	 */
	public List<String> getStereotypes();
	
	public UseCaseDTO getInvocationSource();
	
}