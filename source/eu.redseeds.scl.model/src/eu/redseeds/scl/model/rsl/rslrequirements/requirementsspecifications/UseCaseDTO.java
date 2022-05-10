package eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications;

import java.util.List;
import java.util.Map;

import org.eclipse.ui.IActionFilter;

import eu.redseeds.scl.model.rsl.rsldomainelements.actors.ActorDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.systemelements.SystemElementDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentations.descriptiverequirementrepresentations.ConstrainedLanguageScenarioDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsrelationships.NonInvocationRelationshipDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.usecaserelationships.InvocationRelationshipDTO;

public interface UseCaseDTO extends RequirementDTO, IActionFilter{
	public String getUid();

	public String getDescription();

	public void setDescription(String description);
	
	public Map<UseCaseDTO, NonInvocationRelationshipDTO> getRelatedUseCases();
	
	public String toString();
	
	public List<ActorDTO> getActors();

	public List<RequirementDTO> getRequirments();

	public void setUid(String text);
	
	public void addConstrainedLanguageScenario(ConstrainedLanguageScenarioDTO scenario);
	
	public List<ConstrainedLanguageScenarioDTO> getConstrainedLanguageScenarioDTOList();
	
	public void setParent(RequirementsPackageDTO parent);
	
//	public void setValid(boolean valid);
//
//	public boolean isValid();
	
	public InvocationRelationshipDTO addInvokedUseCase(UseCaseDTO uc);
	
	public List<InvocationRelationshipDTO> getInvocationRelationshipToList();
	
	public List<InvocationRelationshipDTO> getInvocationRelationshipFromList();

	List<SystemElementDTO> getSystemElements();
	
	List<ActorDTO> getDependentActors();
	
}