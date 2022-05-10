package eu.redseeds.sc.merging.resolvers;

import org.eclipse.core.runtime.IStatus;

import eu.redseeds.sc.merging.Activator;
import eu.redseeds.sc.merging.MergeConstants;
import eu.redseeds.sc.merging.conflicts.MergeConflictObject;
import eu.redseeds.scl.impl.rsl.rsldomainelements.notions.NotionImpl;
import eu.redseeds.scl.model.rsl.rsldomainelements.domainelements.DomainElementRelationshipDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.DomainStatementDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionDTO;
import eu.redseeds.scl.rsl.rsldomainelements.notions.DomainStatement;

public class MergeResolverNotion implements IMergeResolver {

	@Override
	public void resolve(MergeConflictObject conflict) {
		if(!validateConflict(conflict)) {
			Activator.log("Invalid merge object", IStatus.WARNING);
			return;
		}
		
		if(conflict.getConflictType() == MergeConstants.MERGE_CONFLICT_NAMING_NOTION_ID){
			if(conflict.getConflictAction() == MergeConstants.MERGE_ACTION_OVERWRITE_ID) {
				((NotionDTO)conflict.getCurrentObject()).delete();
			}
			if(conflict.getConflictAction() == MergeConstants.MERGE_ACTION_SKIP_ID) {
				((NotionDTO)conflict.getPastObject()).delete();
			}
			if(conflict.getConflictAction() == MergeConstants.MERGE_ACTION_AUTOSOLVE_ID) {
				//ALP fix
//				String oldName = ((NotionDTO)conflict.getPastObject()).getName();
//				((NotionDTO)conflict.getPastObject()).setName(
//						oldName + MergeConstants.MERGE_CONFLICT_NAMING_RESOLVER_POSTFIX);
				NotionDTO alpNotion = (NotionDTO)conflict.getPastObject();
				NotionDTO scNotion = (NotionDTO)conflict.getCurrentObject();
				for(DomainStatementDTO statement : alpNotion.getDomainStatementDTOList()) {
					//skip same name phrase
					if(!statement.getName().equalsIgnoreCase(scNotion.getName())) {
						((NotionImpl)alpNotion).removeStatement((DomainStatement)statement);
						boolean shouldBeAdded = false;
						for(DomainStatementDTO dsDTO : scNotion.getDomainStatementDTOList()) {
							if(!dsDTO.getName().equalsIgnoreCase(statement.getName())) {
								shouldBeAdded = true;
							}
							else {
								statement.delete();
								shouldBeAdded = false;
								break;
							}
						}
						if(shouldBeAdded) {
							scNotion.addDomainStatementDTO(statement);
						}
					}
				}
				//copy relationships
				for(DomainElementRelationshipDTO relation : alpNotion.getDomainElementRelationshipDTOList()) {
					if(relation.getSource().equals(alpNotion)) {
						relation.setSource(scNotion);
					}
					else if(relation.getTarget().equals(alpNotion)){
						relation.setTarget(scNotion);
					}
				}
				((NotionDTO)conflict.getPastObject()).delete();
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
		if(!(conflict.getCurrentObject() instanceof NotionDTO)) {
			return false;
		}
		if(!(conflict.getPastObject() instanceof NotionDTO)) {
			return false;
		}
		return true;
	}


}
