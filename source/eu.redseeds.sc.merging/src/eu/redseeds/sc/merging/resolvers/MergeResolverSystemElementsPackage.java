package eu.redseeds.sc.merging.resolvers;

import org.eclipse.core.runtime.IStatus;

import eu.redseeds.sc.merging.Activator;
import eu.redseeds.sc.merging.MergeConstants;
import eu.redseeds.sc.merging.conflicts.MergeConflictObject;
import eu.redseeds.scl.model.rsl.rsldomainelements.systemelements.SystemElementsPackageDTO;

public class MergeResolverSystemElementsPackage implements IMergeResolver {
	
	@Override
	public void resolve(MergeConflictObject conflict) {
		if(!validateConflict(conflict)) {
			Activator.log("Invalid merge object", IStatus.WARNING);
			return;
		}
		
		if(conflict.getConflictType() == MergeConstants.MERGE_CONFLICT_NAMING_SYSTEM_ELEMENTS_PACKAGE_ID){
			if(conflict.getConflictAction() == MergeConstants.MERGE_ACTION_OVERWRITE_ID) {
				((SystemElementsPackageDTO)conflict.getCurrentObject()).delete();
			}
			if(conflict.getConflictAction() == MergeConstants.MERGE_ACTION_SKIP_ID) {
				((SystemElementsPackageDTO)conflict.getPastObject()).delete();
			}
			if(conflict.getConflictAction() == MergeConstants.MERGE_ACTION_AUTOSOLVE_ID) {
				String oldName = ((SystemElementsPackageDTO)conflict.getPastObject()).getName();
				((SystemElementsPackageDTO)conflict.getPastObject()).setName(
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
		if(!(conflict.getCurrentObject() instanceof SystemElementsPackageDTO)) {
			return false;
		}
		if(!(conflict.getPastObject() instanceof SystemElementsPackageDTO)) {
			return false;
		}
		return true;
	}
}
