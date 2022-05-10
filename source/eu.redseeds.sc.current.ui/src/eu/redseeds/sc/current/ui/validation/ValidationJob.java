package eu.redseeds.sc.current.ui.validation;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IProgressMonitor;

import eu.redseeds.common.jobs.IJob;

/**
 * validation job
 *
 */
public class ValidationJob implements IJob {
	
	private Object[] sclElement;
	/**
	 * resource resource to be displayed in the problem view
	 */
	private IResource resource; 
	
	/**
	 * @param elementsToValidate
	 * @param resource resource to be displayed in the problem view
	 */
	public ValidationJob(Object[] elementsToValidate, IResource resource) {
		this.sclElement = elementsToValidate;
		this.resource = resource;
	}
	
	List<IMarker> result = new ArrayList<IMarker>();

	public List<IMarker> getResult() {
		return result;
	}

	public void addResult(IMarker marker) {
		if(marker != null) {
			result.add(marker);
		}
	}

	@Override
	public String getName() {
		return "Validating...";
	}

	@Override
	public int getTotalTime() {
		return sclElement.length + 1;
	}

	@Override
	public void run(IProgressMonitor monitor) throws InvocationTargetException,
			InterruptedException {
		
		monitor.beginTask(getName(), getTotalTime());
		
		monitor.subTask("Clearing old validation results...");
		ValidationMarkerFactory.clearOldMarkers(resource);
		monitor.worked(1);
		
		for (int i = 0; i < sclElement.length; i++) {
			IValidate validator = ValidationAdapter.getValidator((sclElement[i]));
			monitor.subTask(validator.getLabel());
			ValidationResult[] valResults = validator.validateRecursively(sclElement[i]);
			monitor.worked(1);
			monitor.subTask("Preparing results...");
			for (int j = 0; j < valResults.length; j++) {
				valResults[j].setResource(resource);
				addResult(valResults[j].getMarker());
			}
			monitor.worked(1);
		}
		monitor.done();
		
	}
	
}
