package eu.redseeds.sc.merging.conflicts;

import eu.redseeds.sc.merging.MergeConstants;

/**
 * Used for creating {@link MergeConflictObject} classes
 *
 */
public class MergeConflictFactory {
	
	public static MergeConflictObject createMergeConflictObject(
			Object currentObject, Object pastObject, int type) {
		return new MergeConflictObject(currentObject, pastObject, type,
				MergeConstants.MERGE_ACTION_DEFAULT_ID);
	}
	
}
