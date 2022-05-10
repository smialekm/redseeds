package eu.redseeds.sc.merging.conflicts;

import java.util.ArrayList;
import java.util.List;

import eu.redseeds.sc.current.repository.SCProjectContainer;
import eu.redseeds.sc.merging.MergeConstants;
import eu.redseeds.scl.model.BusinessLayerFacade;
import eu.redseeds.scl.model.rsl.rsldomainelements.actors.ActorDTO;
import eu.redseeds.scl.model.sclkernel.SoftwareCaseDTO;

public class MergeCheckActor implements IMergeCheck {

	@Override
	public MergeConflictObject[] check(Object element, SoftwareCaseDTO targetSC) {
		List<MergeConflictObject> conflicts = new ArrayList<MergeConflictObject>();
		if (element instanceof ActorDTO) {
			ActorDTO clipboardA = (ActorDTO) element;
			BusinessLayerFacade target 
				= SCProjectContainer.instance()
					.getSCProject(targetSC).getBusinessLayerFacade();
			//check the name
			ActorDTO scA = target.getActorDTO(clipboardA.getName());
			if(scA != null) {
				conflicts.add( 
				        MergeConflictFactory.createMergeConflictObject(
				        		scA, clipboardA, MergeConstants.MERGE_CONFLICT_NAMING_ACTOR_ID));
			}
		}
		return (MergeConflictObject[])conflicts.toArray(new MergeConflictObject[conflicts.size()]);
	}

}
