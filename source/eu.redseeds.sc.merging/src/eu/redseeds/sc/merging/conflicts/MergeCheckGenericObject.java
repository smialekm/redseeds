package eu.redseeds.sc.merging.conflicts;

import eu.redseeds.scl.model.sclkernel.SoftwareCaseDTO;

public class MergeCheckGenericObject implements IMergeCheck {

	@Override
	public MergeConflictObject[] check(Object element, SoftwareCaseDTO targetSC) {
		return new MergeConflictObject[0];
	}

}
