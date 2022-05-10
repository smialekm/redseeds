package eu.redseeds.sc.current.ui.validation.fixes;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.IMarkerResolution;
import org.eclipse.ui.IMarkerResolutionGenerator;

import eu.redseeds.sc.current.ui.Activator;
import eu.redseeds.sc.current.ui.validation.IValidate;

/**
 * Class providing instances of IValidationFix accordingly to the type of problem supplied
 *
 */
public class FixAdapter implements IMarkerResolutionGenerator {
	
	@Override
	public IMarkerResolution[] getResolutions(IMarker marker) {
		try {
			Integer problemID = (Integer)marker.getAttribute("validationProblemID");
			if(problemID.intValue() == IValidate.ID_UC_NO_SCENARIOS) {
				return new IMarkerResolution[] {
						new UseCaseNoScenFix()
				};
			}
			else if(problemID.intValue() == IValidate.ID_SCEN_EMPTY
					|| problemID.intValue() == IValidate.ID_SCEN_END_NO_POSTCOND_OR_REJOIN
					|| problemID.intValue() == IValidate.ID_SCEN_NAME_EMPTY) {
				return new IMarkerResolution[] {
						new UseCaseScenFix(),
						new GenericFix()
				};
			}
			else if(problemID.intValue() == IValidate.ID_SENT_COND_EMPTY) {
				return new IMarkerResolution[] {
						new ConditionalSentenceFix(),
						new GenericFix()
				};
			}
			else if(problemID.intValue() == IValidate.ID_SENT_INV_UC_NOT_SET
					|| problemID.intValue() == IValidate.ID_SENT_INV_UC_SAME) {
				return new IMarkerResolution[] {
						new InvocationSentenceFix(),
						new GenericFix()
				};
			}
			else if(problemID.intValue() == IValidate.ID_SENT_POSTCOND_EMPTY) {
				return new IMarkerResolution[] {
						new PostconditionSentenceFix(),
						new GenericFix()
				};
			}
			else if(problemID.intValue() == IValidate.ID_SENT_PRECOND_EMPTY) {
				return new IMarkerResolution[] {
						new PreconditionSentenceFix(),
						new GenericFix()
				};
			}
			else if(problemID.intValue() == IValidate.ID_SENT_REJOIN_EMPTY) {
				return new IMarkerResolution[] {
						new RejoinSentenceFix(),
						new GenericFix()
				};
			}
			else if(problemID.intValue() == IValidate.ID_SENT_SVO_STRUCT) {
				return new IMarkerResolution[] {
						new SVOSentenceFix(),
						new GenericFix()
				};
			}
			else if(problemID.intValue() == IValidate.ID_TERM_NOT_IN_TERMINOLOGY) {
				return new IMarkerResolution[] {
						new TermNotInTerminologyFix(),
						new GenericFix()
				};
			}
			
			return new IMarkerResolution[] {
					new GenericFix()
			};
		} catch (CoreException e) {
			Activator.log("Problem occured during creating fix: " + e.getMessage(), Status.WARNING);
			return new IMarkerResolution[0];
		}
	}

}
