package eu.redseeds.sc.merging.resolvers;

import org.eclipse.core.runtime.IStatus;

import eu.redseeds.sc.merging.Activator;
import eu.redseeds.sc.merging.MergeConstants;
import eu.redseeds.sc.merging.conflicts.MergeConflictObject;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementsPackageDTO;

public class MergeResolverReqPackage implements IMergeResolver {

	@Override
	public void resolve(MergeConflictObject conflict) {
		if(!validateConflict(conflict)) {
			Activator.log("Invalid merge object", IStatus.WARNING);
			return;
		}
		
		if(conflict.getConflictType() == MergeConstants.MERGE_CONFLICT_NAMING_REQPACKAGE_ID){
			if(conflict.getConflictAction() == MergeConstants.MERGE_ACTION_OVERWRITE_ID) {
				((RequirementsPackageDTO)conflict.getCurrentObject()).delete();
			}
			if(conflict.getConflictAction() == MergeConstants.MERGE_ACTION_SKIP_ID) {
				((RequirementsPackageDTO)conflict.getPastObject()).delete();
			}
			if(conflict.getConflictAction() == MergeConstants.MERGE_ACTION_AUTOSOLVE_ID) {
				String oldName = ((RequirementsPackageDTO)conflict.getPastObject()).getName();
				((RequirementsPackageDTO)conflict.getPastObject()).setName(
						oldName + MergeConstants.MERGE_CONFLICT_NAMING_RESOLVER_POSTFIX);
			}
		}

	}

	@Override
	public boolean validateConflict(MergeConflictObject conflict) {
		if(conflict == null) {
			return false;
		}
		if(conflict.getCurrentObject() == null || conflict.getPastObject() == null) {
			return false;
		}
		if(!(conflict.getCurrentObject() instanceof RequirementsPackageDTO)) {
			return false;
		}
		if(!(conflict.getPastObject() instanceof RequirementsPackageDTO)) {
			return false;
		}
		return true;
	}

}
