/**
 * 
 */
package eu.redseeds.ea.converter.interfaces;

import eu.redseeds.sc.current.repository.SCProject;

/**
 *  IConversions interface offers operations for 
 *  conversion models between EA and TG.
 */
public interface IConversions {

	/**
	 * Performs model conversion.
	 * 
	 *  This operation is a starting point for conversion model created in TG into 
	 *  EA model (stored in EA file). It is used after merging the result of the 
	 *  transformation with current SC or after manual changing the structure of 
	 *  Architecture/DetailedDesign.
	 *  
	 */
	public void convertTGtoEA(SCProject currProject, boolean openAfter,boolean dgrmBitmapSave, boolean genCode);

	/**
	 * Performs models conversion.
	 * 
	 *  This operation is a starting point for conversion model created in EA (stored in EA file)
	 *  into model in TG. Just for synchronisation after changes in EA.
	 *  
	 */
	public void convertEAtoTG(SCProject currProject, String eaModel);
	
	public void convertRequirementsToEA(SCProject currProject, boolean openAfter);

	/**
	 * Opens Enterprise Architect tool.
	 * 
	 */
	public void openEA(String eapPath_FileName);

}
