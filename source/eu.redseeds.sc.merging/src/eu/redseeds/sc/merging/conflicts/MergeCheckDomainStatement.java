package eu.redseeds.sc.merging.conflicts;

import java.util.ArrayList;
import java.util.List;

import eu.redseeds.sc.current.repository.SCProjectContainer;
import eu.redseeds.sc.merging.MergeConstants;
import eu.redseeds.scl.model.BusinessLayerFacade;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.DomainStatementDTO;
import eu.redseeds.scl.model.sclkernel.SoftwareCaseDTO;

public class MergeCheckDomainStatement implements IMergeCheck {

	@Override
	public MergeConflictObject[] check(Object element, SoftwareCaseDTO targetSC) {
		List<MergeConflictObject> conflicts = new ArrayList<MergeConflictObject>();
		if (element instanceof DomainStatementDTO) {
			DomainStatementDTO clipboardDS = (DomainStatementDTO) element;
			BusinessLayerFacade target 
				= SCProjectContainer.instance()
					.getSCProject(targetSC).getBusinessLayerFacade();
			//check the name
			DomainStatementDTO scDS = target.getDomainStatementByNameFast(clipboardDS.getName());
			if(scDS != null) {
				conflicts.add( 
				        MergeConflictFactory.createMergeConflictObject(
				        		scDS, clipboardDS, MergeConstants.MERGE_CONFLICT_NAMING_DOMAIN_STATEMENT_ID));
			}
		}
		return (MergeConflictObject[])conflicts.toArray(new MergeConflictObject[conflicts.size()]);
	}

}
