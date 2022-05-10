package eu.redseeds.sc.current.repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

import eu.redset.emf.model.tsl.TSLBusinessLayerFacade;


public class TestSpecificationContainer {
	
	/**
	 * the {@code Map} holding the mapping between Eclipse Projects and SCProjects
	 */
	private Map<Resource, TSLBusinessLayerFacade> testSpecificationMapping;
	
	public TestSpecificationContainer() {
		testSpecificationMapping = new HashMap<Resource, TSLBusinessLayerFacade>();
	}
	
	/**
	 * Stores {@code scProject} in the container.
	 * 
	 * @param scProject the {@code SCProject} to be stored in the container.
	 */
	public void addTestSpecification(Resource resource, TSLBusinessLayerFacade tslFacade) {
		testSpecificationMapping.put(resource, tslFacade);
	}
	
	/**
	 * Returns the {@code SCProject} associated to {@code eclipseProject}. 
	 */
	public TSLBusinessLayerFacade getTSLBusinessLayerFacade(Resource resource) {
		return testSpecificationMapping.get(resource);
	}
	
	/**
	 * Returns the {@code SCProject} which holds given {@code SCLElement}. 
	 */
	public TSLBusinessLayerFacade getTSLFacade(Object testElement) {
		
		if (testElement instanceof EObject){
			System.out.println("------------------------------------------ test");
			EObject elem = (EObject)testElement;
			Resource elemResource = elem.eResource();
			return testSpecificationMapping.get(elemResource);
		}
		return null;
	}

	/**
	 * Removes the mapping with {@code eclipseProject} as key. 
	 */
	//public void removeTestSpecification(T) {
	//	projectMapping.remove(eclipseProject);
	//}
	
	public Collection<Resource> getTestSpecificationResources() {
		return testSpecificationMapping.keySet();
	}
	
	public Collection<TSLBusinessLayerFacade> getTSLFacades() {
		return testSpecificationMapping.values();
	}

	/**
	 * Checks whether the container holds the given {@code scProject}.
	 */
	//public boolean containsTSLBusinessLayerFacade(IProject eclipseProject) {
	//	return projectMapping.containsKey(eclipseProject);
	//}
}
