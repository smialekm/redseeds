package eu.redseeds.sc.current.ui.validation.fixes;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Status;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.views.markers.WorkbenchMarkerResolution;

import eu.redseeds.sc.current.ui.Activator;
import eu.redseeds.sc.current.ui.validation.IValidate;

public class GenericFix extends WorkbenchMarkerResolution {

	@Override
	public String getLabel() {
		return IValidate.FIX_WRONG_TYPE;
	}

	@Override
	public void run(IMarker marker) {
		try {
			marker.delete();
		} catch (CoreException e) {
			Activator.log("Error ccured during resolving validation problem: " + e.getMessage(), Status.WARNING);
		}
		
	}

	@Override
	public IMarker[] findOtherMarkers(IMarker[] markers) {
		List<IMarker> results = new ArrayList<IMarker>();
		for (int i = 0; i < markers.length; i++) {
			IMarker marker = markers[i];
			Integer problemId = null;
			try {
				problemId = (Integer)marker.getAttribute(IValidate.VALIDATION_RESULT_ATTRIBUTE_PROBLEM_ID);
			} catch (CoreException e) {
				Activator.log("Error ccured during searching for similar validation problems: " + e.getMessage(), Status.WARNING);
			}
			if(problemId.intValue() == IValidate.ID_SENT_SVO_STRUCT
					|| problemId.intValue() == IValidate.ID_SENT_COND_EMPTY
					|| problemId.intValue() == IValidate.ID_SENT_INV_UC_NOT_SET
					|| problemId.intValue() == IValidate.ID_SENT_INV_UC_SAME
					|| problemId.intValue() == IValidate.ID_SENT_POSTCOND_EMPTY
					|| problemId.intValue() == IValidate.ID_SENT_PRECOND_EMPTY) {
				results.add(marker);
			}
		}
		return (IMarker[])results.toArray(new IMarker[results.size()]);
	}

	@Override
	public String getDescription() {
		return getLabel();
	}

	@Override
	public Image getImage() {
		return null;
	}

}
