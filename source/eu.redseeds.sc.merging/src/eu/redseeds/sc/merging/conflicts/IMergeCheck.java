package eu.redseeds.sc.merging.conflicts;

import eu.redseeds.scl.model.sclkernel.SoftwareCaseDTO;

/**
 * Interface for all merge checks. Merge check is performed before merge. The procedure investigates target SC and returns possible merge conflicts with specified object
 *
 */
public interface IMergeCheck {

	/**
	 * Investigates target SC and returns possible merge conflicts with specified object
	 * @param element
	 * SCL elements from clipboard 
	 * @param targetSC
	 * target SC 
	 * @return
	 */
	public MergeConflictObject[] check(Object element, SoftwareCaseDTO targetSC);
}
