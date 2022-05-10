package eu.redseeds.sc.current.ui.validation.fixes;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.IMarkerResolution;

import eu.redseeds.sc.current.repository.SCProject;
import eu.redseeds.sc.current.repository.SCProjectContainer;
import eu.redseeds.sc.current.ui.Activator;
import eu.redseeds.sc.current.ui.validation.IValidate;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentations.descriptiverequirementrepresentations.ConstrainedLanguageScenarioDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.controlsentences.PreconditionSentenceDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.UseCaseDTO;

public class UseCaseNoScenFix implements IMarkerResolution {
	
	@Override
	public String getLabel() {
		return IValidate.FIX_MSG_UC_NO_SCENARIOS;
	}

	@Override
	public void run(IMarker marker) {
		try {
			Integer elementID 
				= (Integer)marker.getAttribute("sclElementID");
			if(marker.getResource() instanceof IProject) {
				SCProject scProject 
					= SCProjectContainer.instance().getSCProject((IProject)marker.getResource());
				UseCaseDTO uc = scProject
					.getBusinessLayerFacade().getUseCaseByVertexID(elementID.intValue());
				ConstrainedLanguageScenarioDTO scenario = scProject
						.getBusinessLayerFacade()
						.createConstrainedLanguageScenarioDTO();
				scenario.setName(uc.getName());
				PreconditionSentenceDTO preconditionSent = scProject
						.getBusinessLayerFacade().createPreconditionSentenceDTO();
				preconditionSent.setSentenceText("");
				scenario.addScenarioStep(preconditionSent);
				uc.addConstrainedLanguageScenario(scenario);
				
				scProject.save();
				
				marker.delete();
			}
		} catch (CoreException e) {
			Activator.log("Error ccured during resolving validation problem: " + e.getMessage(), Status.WARNING);
		}
	}

}
