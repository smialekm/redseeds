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
import eu.redseeds.scl.model.rsl.rsldomainelements.terms.TermDTO;

public class TermNotInTerminologyFix implements IMarkerResolution {

	@Override
	public String getLabel() {
		return IValidate.FIX_TERM_NOT_IN_TERMINOLOGY;
	}

	@Override
	public void run(IMarker marker) {
		String termName;
		try {
			termName = (String) marker.getAttribute(IMarker.LOCATION);
		} catch (CoreException e) {
			Activator.log("Error ccured during resolving validation problem: " + e.getMessage(), Status.WARNING);
			return;
		}
		SCProject scProject 
			= SCProjectContainer.instance().getSCProject((IProject)marker.getResource());
		//TODO - getTermByName method in facade
		for(TermDTO term : scProject.getBusinessLayerFacade().getAllTerms()) {
			if(term.getName().equalsIgnoreCase(termName)) {
				term.setSynonymUid(0);
				break;
			}
		}
		try {
			marker.setAttribute(IMarker.MESSAGE, IValidate.MSG_TERM_SYN_ID_EQ_ZERO);
		} catch (CoreException e) {
			Activator.log("Error ccured during resolving validation problem: " + e.getMessage(), Status.WARNING);
		}
	}

}
