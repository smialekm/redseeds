package eu.redseeds.sc.current.repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;

import eu.redseeds.scl.model.impl.BusinessLayerFacadeImpl;
import eu.redseeds.scl.sclkernel.SCLElement;


public class SCProjectContainer {

	/**
	 * the singleton instance of this class
	 */
	private static SCProjectContainer scProjectContainer;
	
	/**
	 * the {@code Map} holding the mapping between Eclipse Projects and SCProjects
	 */
	private Map<IProject, SCProject> projectMapping;
	
	private SCProjectContainer() {
		scProjectContainer = null;
		projectMapping = new HashMap<IProject, SCProject>();
	}
	
	/**
	 * Returns the single instance of this class.
	 * 
	 * @return the single instance of this class
	 */
	public static SCProjectContainer instance() {
		if (scProjectContainer == null) {
			scProjectContainer = new SCProjectContainer();
		}
		
		return scProjectContainer;
	}
	
	/**
	 * Stores {@code scProject} in the container.
	 * 
	 * @param scProject the {@code SCProject} to be stored in the container.
	 */
	public void addSCProject(SCProject scProject) {
		projectMapping.put(scProject.getEclipseProject(), scProject);
	}
	
	/**
	 * Returns the {@code SCProject} associated to {@code eclipseProject}. 
	 */
	public SCProject getSCProject(IProject eclipseProject) {
		return projectMapping.get(eclipseProject);
	}
	
	/**
	 * Returns the {@code SCProject} which holds given {@code SCLElement}. 
	 */
	public SCProject getSCProject(Object sclElement) {
		if(sclElement instanceof SCLElement) {
			SCLElement elem = ((SCLElement)sclElement);
			BusinessLayerFacadeImpl facade = ((BusinessLayerFacadeImpl)elem.getGraph());
			for (SCProject scProject : getSCProjects()){
				if(scProject.getBusinessLayerFacade() == null) {//invalid project - skip it
					continue;
				}
				if (scProject.getBusinessLayerFacade().equals(facade)){
					return scProject;
				}
			}
			return null;
		}
		
		if (sclElement instanceof EObject){
			EObject elem = (EObject)sclElement;
			Resource testResource = elem.eResource();
			for (SCProject scProject : getSCProjects()){
				if(scProject.getTestSpecificationContainer() == null) {//invalid project - skip it
					continue;
				}
				for (Resource r : scProject.getTestSpecificationContainer().getTestSpecificationResources())
					if (r.equals(testResource)){
						return scProject;
					}
			}
			return null;
		}
		if(sclElement instanceof IFile)
			return getSCProject(((IFile) sclElement).getProject());
		return null;
	}

	/**
	 * Removes the mapping with {@code eclipseProject} as key. 
	 */
	public void removeSCProject(IProject eclipseProject) {
		projectMapping.remove(eclipseProject);
	}
	
	public Collection<SCProject> getSCProjects() {
		return projectMapping.values();
	}

	/**
	 * Checks whether the container holds the given {@code scProject}.
	 */
	public boolean containsSCProject(IProject eclipseProject) {
		return projectMapping.containsKey(eclipseProject);
	}
}
