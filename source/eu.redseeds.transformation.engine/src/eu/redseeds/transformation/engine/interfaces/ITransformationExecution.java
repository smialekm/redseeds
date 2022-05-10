/**
 * 
 */
package eu.redseeds.transformation.engine.interfaces;

import org.osgi.framework.Bundle;

import eu.redseeds.sc.current.repository.SCProject;
import eu.redseeds.scl.model.TransformationProfile;
/**
 *  ITransformationExecution interface offers operations for executing transformations.
 *  It is an API of the TransformationEngine component.
 */
public interface ITransformationExecution {

	/**
	 * Performs model transformation.
	 * 
	 * run transformation with some transformation rules and 
	 * customisations  (Transformation Properties ??) on the main SC. Result of a 
	 * transformation is put in new clipboard (source and target models are put there). 
	 * This operation is called for transformations Req-Arch and Arch-Design.
	 *  
	 */
//	public void execute(TransformationProperties transformationProperties);
	public void execute(SCProject currSCProject, TransformationProfile transformationProfile);
	
	/**
	 * Performs model transformation.
	 * !!! Diagrams will be not built by transformations!!!
	 * run transformation for generating diagrams in EA metamodel 
	 * for main SC. Transformation profile should be predefined.
	 */
	//public void generateDiagrams(SoftwareCase softwareCase);


	/**
	 * Returns transformations engine Bundle. 
	 * 
	 */
	public Bundle getTransformationEngineBundle();

}
