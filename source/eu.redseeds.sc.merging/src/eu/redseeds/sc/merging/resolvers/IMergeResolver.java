package eu.redseeds.sc.merging.resolvers;

import eu.redseeds.sc.merging.conflicts.MergeConflictObject;

/**
 * Interface for all merge resolvers
 *
 */
public interface IMergeResolver {
	
	/**
	 * Resolves specified conflict
	 * @param conflict
	 */
	public void resolve(MergeConflictObject conflict);
	
	/**
	 * Validates if given MergeConflictObject is valid for this resolver
	 * @param conflict
	 * @return
	 */
	public boolean validateConflict(MergeConflictObject conflict);

}
