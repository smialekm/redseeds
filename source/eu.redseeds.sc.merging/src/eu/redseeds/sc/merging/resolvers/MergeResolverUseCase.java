package eu.redseeds.sc.merging.resolvers;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import eu.redseeds.sc.merging.Activator;
import eu.redseeds.sc.merging.MergeConstants;
import eu.redseeds.sc.merging.conflicts.MergeConflictObject;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.UseCaseDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.usecaserelationships.InvocationRelationshipDTO;
import eu.redseeds.scl.rsl.rslrequirements.requirementsspecifications.RSLUseCase;
import eu.redseeds.scl.rsl.rslrequirements.usecaserelationships.InvocationRelationship;

public class MergeResolverUseCase implements IMergeResolver {

	@Override
	public void resolve(MergeConflictObject conflict) {
		if(!validateConflict(conflict)) {
			Activator.log("Invalid merge object", IStatus.WARNING);
			return;
		}
		
		if(conflict.getConflictType() == MergeConstants.MERGE_CONFLICT_NAMING_USECASE_ID){
			if(conflict.getConflictAction() == MergeConstants.MERGE_ACTION_OVERWRITE_ID) {
				((UseCaseDTO)conflict.getCurrentObject()).delete();
			}
			if(conflict.getConflictAction() == MergeConstants.MERGE_ACTION_SKIP_ID) {
				((UseCaseDTO)conflict.getPastObject()).delete();
			}
			if(conflict.getConflictAction() == MergeConstants.MERGE_ACTION_AUTOSOLVE_ID) {
				//alp fix
//				String oldName = ((UseCaseDTO)conflict.getPastObject()).getName();
//				((UseCaseDTO)conflict.getPastObject()).setName(
//						oldName + MergeConstants.MERGE_CONFLICT_NAMING_RESOLVER_POSTFIX);
				UseCaseDTO alpUC = (UseCaseDTO)conflict.getPastObject();
				UseCaseDTO scUC = (UseCaseDTO)conflict.getCurrentObject();
				for(InvocationRelationshipDTO invoke : alpUC.getInvocationRelationshipToList()) {
					Activator.log("auto-solving invoke: "
							+ invoke.getSource().getName() 
//							+ " (source uid=" + invoke.getSource().getUid() + ") " 
							+ " ->" 
							+ invoke.getTarget().getName() 
//							+ " (target uid="+ invoke.getTarget().getUid() + ")"
							, Status.INFO);
					((InvocationRelationship)invoke).removeTarget((RSLUseCase)alpUC);
					((InvocationRelationship)invoke).addTarget((RSLUseCase)scUC);
				}
				alpUC.delete();
			}
		}
		
		if(conflict.getConflictType() == MergeConstants.MERGE_CONFLICT_ID_USECASE_ID){
			if(conflict.getConflictAction() == MergeConstants.MERGE_ACTION_OVERWRITE_ID) {
				((UseCaseDTO)conflict.getCurrentObject()).delete();
			}
			if(conflict.getConflictAction() == MergeConstants.MERGE_ACTION_SKIP_ID) {
				((UseCaseDTO)conflict.getPastObject()).delete();
			}
			if(conflict.getConflictAction() == MergeConstants.MERGE_ACTION_AUTOSOLVE_ID) {
				String oldName = ((UseCaseDTO)conflict.getPastObject()).getName();
				((UseCaseDTO)conflict.getPastObject()).setRequirementId(
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
		if(conflict.getConflictType() == MergeConstants.MERGE_CONFLICT_ID_USECASE_ID) {
			if(!(conflict.getCurrentObject() instanceof UseCaseDTO || conflict.getCurrentObject() instanceof RequirementDTO)) {
				return false;
			}
			if(!(conflict.getPastObject() instanceof UseCaseDTO)) {
				return false;
			}
		}
		else {
			if(!(conflict.getCurrentObject() instanceof UseCaseDTO)) {
				return false;
			}
			if(!(conflict.getPastObject() instanceof UseCaseDTO)) {
				return false;
			}
		}
		return true;
	}

}
