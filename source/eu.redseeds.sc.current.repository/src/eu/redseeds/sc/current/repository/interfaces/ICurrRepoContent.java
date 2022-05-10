package eu.redseeds.sc.current.repository.interfaces;

import java.util.Collection;

import org.eclipse.core.resources.IProject;

import eu.redseeds.sc.current.repository.SCProject;
import eu.redseeds.scl.model.sclkernel.SoftwareCaseDTO;

/**
 * This interface offers operations for managing current SC and retrieving
 * information about its structure (artefacts, clipboards, ...).
 * @version 1.1
 * @created 08-lut-2008 15:30:53
 * @modified 08-April-2008, Daniel Bildhauer
 * 
 * Changes from 08th April 2008:
 * Integrated the interfaces of past and current repository as 
 * we have agreed in the skype conference from .
 * All methods that are not longer necessary are commented out
 * 
 */
public interface ICurrRepoContent {

	/**
	 * Creates a space for a new Software Case (TG model with the
	 * structure for main software case, EAP file, and other resources). It uses
	 * an object eclipseProject which identifies the connected Eclipse project.
	 * 
	 * If creation is unsuccessful scProject object is null.
	 * 
	 * @param eclipseProject
	 */
	public SCProject createSCProject(IProject eclipseProject);
		
	/**
	 * Returns the {@code SCProject} associated to {@code eclipseProject}. 
	 */
	public SCProject getSCProject(IProject eclipseProject);
	
	/**
	 * Returns a list of SoftwareCasesProject stored in SC Repository.
	 */
	public Collection<SCProject> getSCProjectList();
		
	
	/**
	 * Deletes the given <code>softwareCase</code> from SC Repository.
	 * The second parameter <code>removeRemote</code> specifies, whether the project
	 * should also be deleted in the remtoe SVN repository.
	 * 
	 * @param softwareCase the SCProject to delete
	 * @param removeRemote if this parameter is set to true, the software case will
	 * be removed also in the remote SVN repository
	 * 
	 */
	public void deleteSoftwareCase(SCProject softwareCase, boolean removeRemote);

	
	/**
	 * Updates the software case project <code>softwareCase</code>, i.e. loads all 
	 * changes from the remote SVN repository
	 *  
	 * @param softwareCase the SCProject to update
	 */
	public void updateSoftwareCase(SCProject softwareCase);

	
	/**
	 * Stores the local changes of the software case project 
	 * <code>softwareCase</code> on the remote SVN repository.
	 * If the <code>softwareCase</code> is a new case that is 
	 * not part of the repository, a new place in the repository
	 * is created for the case.
	 * 
	 * @param softwareCase the SCProject to store
	 */
	public void commitSoftwareCase(SoftwareCaseDTO softwareCase);
	
	
	
	
}