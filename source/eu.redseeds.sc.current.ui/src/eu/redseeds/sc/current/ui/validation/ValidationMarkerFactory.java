package eu.redseeds.sc.current.ui.validation;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.views.markers.MarkerViewUtil;

import eu.redseeds.sc.current.ui.Activator;

public class ValidationMarkerFactory {
	
	/**
	 * Creates marker for a given validation data. 
	 * Will return null if new marker would be a duplicate one (problem id and specification path are checked)
	 * @param resource
	 * @param validationProblemID
	 * @param severity
	 * @param message
	 * @param specificationPath
	 * @return
	 */
	public static IMarker createValidationMarker(IResource resource, int validationProblemID, int severity, 
			String message, String specificationPath) {
		try {
			if(checkForDuplicateMarkers(resource, validationProblemID, specificationPath)) {
				return null;
			}
			IMarker marker = resource
					.createMarker("org.eclipse.core.resources.problemmarker");
			marker.setAttribute(IMarker.MESSAGE, message);
			marker.setAttribute(IMarker.SEVERITY, severity);
			marker.setAttribute(IMarker.LOCATION, specificationPath.substring(specificationPath.lastIndexOf("\\") + 1));
			marker.setAttribute(MarkerViewUtil.PATH_ATTRIBUTE, specificationPath);
			return marker;
		} catch (CoreException e) {
			Activator.log("Problem occured during creation of an IMarker: " + e.getMessage(), Status.WARNING);
			return null;
		}
	}
	
	/**
	 * @param resource resource for a marker to be created
	 * @param validationProblemID validation problem ID attribute for a marker to be created
	 * @param specificationPath specification path for a marker to be created
	 * @return false if there are no existing markers for a given type id
	 * @throws CoreException
	 */

	private static boolean checkForDuplicateMarkers(IResource resource, 
			int validationProblemID, String specificationPath) throws CoreException {
		IMarker[] markers = resource.findMarkers(
				IMarker.PROBLEM, true, IResource.DEPTH_INFINITE);
		for (IMarker m : markers) {
			Integer problemId 
				= (Integer)m.getAttribute(IValidate.VALIDATION_RESULT_ATTRIBUTE_PROBLEM_ID);
			String path 
				= (String)m.getAttribute(MarkerViewUtil.PATH_ATTRIBUTE);
			if(path != null) {
				if(problemId.intValue() == validationProblemID 
						&& path.equalsIgnoreCase(specificationPath)) {
					return true;
				}
			}
			else if(problemId.intValue() == validationProblemID) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Clear all markers for selected resource
	 * @param resource
	 */
	public static void clearOldMarkers(IResource resource) {
		try {
			IMarker[] markers = resource.findMarkers(
					IMarker.PROBLEM, true, IResource.DEPTH_INFINITE);
			for (IMarker m : markers) {
				m.delete();
			}
		} catch (CoreException e) {
			Activator.log("Problem occured during clearing old validation results: " + e.getMessage(), Status.WARNING);
		}
	}
	
	/**
	 * Clear all markers for a given element
	 * @param marker description of a problem
	 */
	public static void clearMarkersForElement(IMarker marker) {
		IResource resource = marker.getResource();
		try {
			String sclElemPath = (String)marker.getAttribute(MarkerViewUtil.PATH_ATTRIBUTE);
			IMarker[] markers = resource.findMarkers(
					IMarker.PROBLEM, true, IResource.DEPTH_INFINITE);
			for (IMarker m : markers) {
				String path = (String)m.getAttribute(MarkerViewUtil.PATH_ATTRIBUTE);
				if(sclElemPath.equalsIgnoreCase(path)) {
					m.delete();
				}
			}
		} catch (CoreException e) {
			Activator.log("Problem occured during clearing old validation results: " + e.getMessage(), Status.WARNING);
		}
	}
	
	/**
	 * Gets number of existing error markers for a given resource.
	 * @param resource
	 * @return -1 if error, otherwise a number of markers
	 */
	public static int getNumberOfErrorMarkers(IResource resource) {
		if(resource == null) {
			return -1;
		}
		int result = 0;
		try {
			IMarker[] markers = resource.findMarkers(
					IMarker.PROBLEM, true, IResource.DEPTH_INFINITE);
			for(IMarker m: markers) {
				if(((Integer)m.getAttribute(IMarker.SEVERITY)).intValue() == IMarker.SEVERITY_ERROR) {
					result++;
				}
			}
			return result;
		} catch (CoreException e) {
			Activator.log("Problem occured during reading validation results: " + e.getMessage(), Status.WARNING);
			return -1;
		}
	}
	
	/**
	 * Gets number of existing markers for a given resource.
	 * @param resource
	 * @return -1 if error, otherwise a number of markers
	 */
	public static int getNumberOfMarkers(IResource resource) {
		if(resource == null) {
			return -1;
		}
		try {
			IMarker[] markers = resource.findMarkers(
					IMarker.PROBLEM, true, IResource.DEPTH_INFINITE);
			return markers.length;
		} catch (CoreException e) {
			Activator.log("Problem occured during reading validation results: " + e.getMessage(), Status.WARNING);
			return -1;
		}
	}
	
}
