package eu.redseeds.sc.merging.resolvers;

import org.eclipse.core.runtime.IStatus;

import eu.redseeds.sc.merging.Activator;
import eu.redseeds.sc.merging.MergeConstants;
import eu.redseeds.sc.merging.conflicts.MergeConflictObject;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.DomainStatementDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionDTO;

public class MergeResolverDomainStatement implements IMergeResolver {

	@Override
	public void resolve(MergeConflictObject conflict) {
		if(!validateConflict(conflict)) {
			Activator.log("Invalid merge object", IStatus.WARNING);
			return;
		}
		
		if(conflict.getConflictType() == MergeConstants.MERGE_CONFLICT_NAMING_DOMAIN_STATEMENT_ID){
			if(conflict.getConflictAction() == MergeConstants.MERGE_ACTION_OVERWRITE_ID) {
				((DomainStatementDTO)conflict.getCurrentObject()).delete();
			}
			if(conflict.getConflictAction() == MergeConstants.MERGE_ACTION_SKIP_ID) {
				((DomainStatementDTO)conflict.getPastObject()).delete();
			}
			if(conflict.getConflictAction() == MergeConstants.MERGE_ACTION_AUTOSOLVE_ID) {
				if(conflict.getParent().getConflictAction() != MergeConstants.MERGE_ACTION_AUTOSOLVE_ID) {//if parent is not autosolved
					if(conflict.getParent().getConflictAction() == MergeConstants.MERGE_ACTION_OVERWRITE_ID) {
						((DomainStatementDTO)conflict.getCurrentObject()).delete();
					}
					if(conflict.getParent().getConflictAction() == MergeConstants.MERGE_ACTION_SKIP_ID) {
						((DomainStatementDTO)conflict.getPastObject()).delete();
					}
				}
				else {//parent is autosolved
					String oldName = ((DomainStatementDTO)conflict.getPastObject()).getName();
					if(conflict.getParent().getPastObject() instanceof NotionDTO) {
						NotionDTO parentNotion = (NotionDTO)conflict.getParent().getPastObject();
						oldName.replaceFirst(parentNotion.getName(), parentNotion.getName() + MergeConstants.MERGE_CONFLICT_NAMING_RESOLVER_POSTFIX);
						((DomainStatementDTO)conflict.getPastObject()).setName(oldName);
					}
					else {
						((DomainStatementDTO)conflict.getPastObject()).setName(
								oldName + MergeConstants.MERGE_CONFLICT_NAMING_RESOLVER_POSTFIX);
					}
				}
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
		if(!(conflict.getCurrentObject() instanceof DomainStatementDTO)) {
			return false;
		}
		if(!(conflict.getPastObject() instanceof DomainStatementDTO)) {
			return false;
		}
		return true;
	}

}
