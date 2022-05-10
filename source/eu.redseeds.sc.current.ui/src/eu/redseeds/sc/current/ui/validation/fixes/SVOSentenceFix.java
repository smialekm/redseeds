package eu.redseeds.sc.current.ui.validation.fixes;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Status;

import eu.redseeds.sc.current.repository.SCProject;
import eu.redseeds.sc.current.repository.SCProjectContainer;
import eu.redseeds.sc.current.ui.Activator;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentations.descriptiverequirementrepresentations.ConstrainedLanguageScenarioDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.svosentences.SVOSentenceDTO;

public class SVOSentenceFix extends ScenarioSentenceFix {

	@Override
	public void run(IMarker marker) {
		try {
			Integer elementID 
				= (Integer)marker.getAttribute("sclElementID");
			if(marker.getResource() instanceof IProject) {
				SCProject scProject 
					= SCProjectContainer.instance().getSCProject((IProject)marker.getResource());
				ConstrainedLanguageScenarioDTO scenario = null;
				//get the sentence
				SVOSentenceDTO svoSent = scProject
					.getBusinessLayerFacade().getSVOSentenceByVertexID(elementID.intValue());
				if(svoSent != null) {
					if(svoSent.getOwningScenarios() != null) {
						if(svoSent.getOwningScenarios().size() > 0) {
							scenario = svoSent.getOwningScenarios().get(0);
						}
					}
				}
				if(scenario != null) {
					boolean result = openScenarioEditor(scenario, scProject);
					if(result) {
						marker.delete();
					}
				}
			}
		} catch (CoreException e) {
			Activator.log("Error occured during resolving validation problem: " + e.getMessage(), Status.WARNING);
		}
		
	}

}
