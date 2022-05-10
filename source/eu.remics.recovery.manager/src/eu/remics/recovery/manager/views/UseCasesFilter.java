package eu.remics.recovery.manager.views;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

import eu.redseeds.scl.model.rsl.rslrequirementrepresentations.descriptiverequirementrepresentations.ConstrainedLanguageScenarioDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementsPackageDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.UseCaseDTO;

public class UseCasesFilter extends ViewerFilter {

	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {
		if(element instanceof RequirementsPackageDTO){
			RequirementsPackageDTO recoveredPack = (RequirementsPackageDTO) element;
			if(recoveredPack.getName().equalsIgnoreCase("RecoveredScenarios")){
				return false;
			}
		}
		return (element instanceof UseCaseDTO || element instanceof ConstrainedLanguageScenarioDTO
				|| element instanceof RequirementsPackageDTO);
	}
}
