package eu.redseeds.sc.merging.resolvers;

import org.eclipse.core.runtime.IStatus;

import eu.redseeds.sc.merging.Activator;
import eu.redseeds.sc.merging.MergeConstants;
import eu.redseeds.sc.merging.conflicts.MergeConflictObject;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.UseCaseDTO;

public class MergeResolverRequirement implements IMergeResolver {

	@Override
	public void resolve(MergeConflictObject conflict) {
		if(!validateConflict(conflict)) {
			Activator.log("Invalid merge object", IStatus.WARNING);
			return;
		}
		
		if(conflict.getConflictType() == MergeConstants.MERGE_CONFLICT_NAMING_REQUIREMENT_ID){
			if(conflict.getConflictAction() == MergeConstants.MERGE_ACTION_OVERWRITE_ID) {
				((RequirementDTO)conflict.getCurrentObject()).delete();
			}
			if(conflict.getConflictAction() == MergeConstants.MERGE_ACTION_SKIP_ID) {
				((RequirementDTO)conflict.getPastObject()).delete();
			}
			if(conflict.getConflictAction() == MergeConstants.MERGE_ACTION_AUTOSOLVE_ID) {
				String oldName = ((RequirementDTO)conflict.getPastObject()).getName();
				((RequirementDTO)conflict.getPastObject()).setName(
						oldName + MergeConstants.MERGE_CONFLICT_NAMING_RESOLVER_POSTFIX);
			}
		}
		
		if(conflict.getConflictType() == MergeConstants.MERGE_CONFLICT_ID_REQUIREMENT_ID){
			if(conflict.getConflictAction() == MergeConstants.MERGE_ACTION_OVERWRITE_ID) {
				((RequirementDTO)conflict.getCurrentObject()).delete();
			}
			if(conflict.getConflictAction() == MergeConstants.MERGE_ACTION_SKIP_ID) {
				((RequirementDTO)conflict.getPastObject()).delete();
			}
			if(conflict.getConflictAction() == MergeConstants.MERGE_ACTION_AUTOSOLVE_ID) {
				String oldName = ((RequirementDTO)conflict.getPastObject()).getName();
				((RequirementDTO)conflict.getPastObject()).setRequirementId(
						oldName + MergeConstants.MERGE_CONFLICT_ID_RESOLVER_POSTFIX);
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
		if(conflict.getConflictType() == MergeConstants.MERGE_CONFLICT_ID_REQUIREMENT_ID) {
			if(!(conflict.getCurrentObject() instanceof UseCaseDTO || conflict.getCurrentObject() instanceof RequirementDTO)) {
				return false;
			}
			if(!(conflict.getPastObject() instanceof RequirementDTO)) {
				return false;
			}
		}
		else {
			if(!(conflict.getCurrentObject() instanceof RequirementDTO)) {
				return false;
			}
			if(!(conflict.getPastObject() instanceof RequirementDTO)) {
				return false;
			}
		}
		return true;
	}

}
