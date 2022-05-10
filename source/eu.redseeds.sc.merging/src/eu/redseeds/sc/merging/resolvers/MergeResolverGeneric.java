package eu.redseeds.sc.merging.resolvers;

import eu.redseeds.sc.merging.conflicts.MergeConflictObject;

public class MergeResolverGeneric implements IMergeResolver {

	@Override
	public void resolve(MergeConflictObject conflict) {
		//do nothing

	}

	@Override
	public boolean validateConflict(MergeConflictObject conflict) {
		return true;
	}

}
