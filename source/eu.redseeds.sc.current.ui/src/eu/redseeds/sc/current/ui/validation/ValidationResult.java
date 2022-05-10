package eu.redseeds.sc.current.ui.validation;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Status;

import eu.redseeds.sc.current.repository.SCProject;
import eu.redseeds.sc.current.repository.SCProjectContainer;
import eu.redseeds.sc.current.ui.Activator;

/**
 * Container class for validation results
 *
 */
public class ValidationResult {
	
	private IResource resource;
	private Object sclElement;
	private int problemID;
	private String message;
	/**
	 * path to the element
	 */
	private String path;
	/**
	 * defaults to IValidate.SEVERITY_WARNING
	 */
	private int severity = IValidate.SEVERITY_WARNING;//default
	
	/**
	 * @return See severity constants in IValidate.
	 */
	public int getSeverity() {
		return severity;
	}

	/**
	 * See severity constants in IValidate.
	 * @param severity
	 */
	public void setSeverity(int severity) {
		this.severity = severity;
	}

	/**
	 * Creates a marker for this validation result. 
	 * Will return null if new marker would be a duplicate one (problem id and specification path are checked)
	 * @return
	 */
	public IMarker getMarker() {
		IMarker marker 
			= ValidationMarkerFactory.createValidationMarker(getResource(), getProblemID(), getSeverity(), this.getMessage(), getPath());
		if(marker == null) {//is duplicate
			return null;
		}
		try {
			marker.setAttribute("validationProblemID", getProblemID());
			if(resource instanceof IProject) {
				SCProject scProject 
					= SCProjectContainer.instance().getSCProject((IProject)marker.getResource());
				marker.setAttribute("sclElementID", 
						scProject.getBusinessLayerFacade().
							getIDOfSCLElement(getSclElement()));
			}
		} catch (CoreException e) {
			Activator.log("Problem occured during setting IMarker attribute: " + e.getMessage(), Status.WARNING);
		}
		return marker;
	}

	/**
	 * Get problem id concerning this validation result. 
	 * See validation ids in eu.redseeds.sc.current.ui.validation.IValidate 
	 */
	public int getProblemID() {
		return problemID;
	}

	/**
	 * Set problem id concerning this validation result. 
	 * See validation ids in eu.redseeds.sc.current.ui.validation.IValidate 
	 * @param problem_id
	 */
	public void setProblemID(int problem_id) {
		problemID = problem_id;
	}

	/**
	 * Get SCL element concerning this validation result 
	 * @return
	 */
	public Object getSclElement() {
		return sclElement;
	}

	/**
	 * Set SCL element concerning this validation result 
	 * @param sclElement
	 */
	public void setSclElement(Object sclElement) {
		this.sclElement = sclElement;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * Gets resource for this validation result
	 * @return
	 */
	public IResource getResource() {
		return resource;
	}

	/**
	 * Sets resource for this validation result
	 * @param resource
	 */
	public void setResource(IResource resource) {
		this.resource = resource;
	}

	/**
	 * Gets path to the element
	 * @return
	 */
	public String getPath() {
		return path;
	}

	/**
	 * Sets path to the element
	 * @param path
	 */
	public void setPath(String path) {
		this.path = path;
	}

}
