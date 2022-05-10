package eu.redseeds.sc.merging.conflicts;

import java.util.ArrayList;
import java.util.List;

import eu.redseeds.sc.current.repository.SCProjectContainer;
import eu.redseeds.sc.merging.MergeConstants;
import eu.redseeds.scl.model.BusinessLayerFacade;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionDTO;
import eu.redseeds.scl.model.sclkernel.SoftwareCaseDTO;

public class MergeCheckNotion implements IMergeCheck {

	@Override
	public MergeConflictObject[] check(Object element, SoftwareCaseDTO targetSC) {
		List<MergeConflictObject> conflicts = new ArrayList<MergeConflictObject>();
		if (element instanceof NotionDTO) {
			NotionDTO clipboardN = (NotionDTO) element;
			BusinessLayerFacade target 
				= SCProjectContainer.instance()
					.getSCProject(targetSC).getBusinessLayerFacade();
			//check the name
			NotionDTO scN = target.getNotionDTO(
					new String[] { clipboardN.getName() }
					);
			if(scN != null) {
				conflicts.add( 
				        MergeConflictFactory.createMergeConflictObject(
				        		scN, clipboardN, MergeConstants.MERGE_CONFLICT_NAMING_NOTION_ID));
			}
		}
		return (MergeConflictObject[])conflicts.toArray(new MergeConflictObject[conflicts.size()]);
	}

}
