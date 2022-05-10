package eu.redseeds.sc.merging.conflicts;

import java.util.ArrayList;
import java.util.List;

import eu.redseeds.sc.current.repository.SCProjectContainer;
import eu.redseeds.sc.merging.MergeConstants;
import eu.redseeds.scl.model.BusinessLayerFacade;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementDTO;
import eu.redseeds.scl.model.sclkernel.SoftwareCaseDTO;

public class MergeCheckRequirement implements IMergeCheck {

	@Override
	public MergeConflictObject[] check(Object element, SoftwareCaseDTO targetSC) {
		List<MergeConflictObject> conflicts = new ArrayList<MergeConflictObject>();
		if (element instanceof RequirementDTO) {
			RequirementDTO clipboardR = (RequirementDTO) element;
			BusinessLayerFacade target 
				= SCProjectContainer.instance()
					.getSCProject(targetSC).getBusinessLayerFacade();
			//check the name
			RequirementDTO scR = target.getRequirementByName(clipboardR.getName());
			if(scR != null) {
				conflicts.add( 
				        MergeConflictFactory.createMergeConflictObject(
				        		scR, clipboardR, MergeConstants.MERGE_CONFLICT_NAMING_REQUIREMENT_ID));
			}
			//check the id -TODO
			scR = target.getRequirementByID(clipboardR.getRequirementId());
			if(scR != null) {
				conflicts.add( 
				        MergeConflictFactory.createMergeConflictObject(
				        		scR, clipboardR, MergeConstants.MERGE_CONFLICT_ID_REQUIREMENT_ID));
			}
		}
		return (MergeConflictObject[])conflicts.toArray(new MergeConflictObject[conflicts.size()]);
	}

}
