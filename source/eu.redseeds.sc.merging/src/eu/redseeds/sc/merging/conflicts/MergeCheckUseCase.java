package eu.redseeds.sc.merging.conflicts;

import java.util.ArrayList;
import java.util.List;

import eu.redseeds.sc.current.repository.SCProjectContainer;
import eu.redseeds.sc.merging.MergeConstants;
import eu.redseeds.scl.model.BusinessLayerFacade;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.UseCaseDTO;
import eu.redseeds.scl.model.sclkernel.SoftwareCaseDTO;

public class MergeCheckUseCase implements IMergeCheck {

	@Override
	public MergeConflictObject[] check(Object element, SoftwareCaseDTO targetSC) {
		List<MergeConflictObject> conflicts = new ArrayList<MergeConflictObject>();
		if (element instanceof UseCaseDTO) {
			UseCaseDTO clipboardUC = (UseCaseDTO) element;
			BusinessLayerFacade target 
				= SCProjectContainer.instance()
					.getSCProject(targetSC).getBusinessLayerFacade();
			//check the name
			UseCaseDTO scUC = target.getUseCaseByName(clipboardUC.getName());
			if(scUC != null) {
				conflicts.add( 
				        MergeConflictFactory.createMergeConflictObject(
				        		scUC, clipboardUC, MergeConstants.MERGE_CONFLICT_NAMING_USECASE_ID));
			}
			//check the id -TODO
//			System.out.println(((RequirementDTO)clipboardUC).getRequirementId());
//			System.out.println(((RequirementDTO)(target.getUseCaseByName("u1"))).getRequirementId());
			RequirementDTO scR = target.getRequirementByID(clipboardUC.getRequirementId());
			if(scR != null) {
				conflicts.add( 
				        MergeConflictFactory.createMergeConflictObject(
				        		scR, clipboardUC, MergeConstants.MERGE_CONFLICT_ID_USECASE_ID));
			}
		}
		return (MergeConflictObject[])conflicts.toArray(new MergeConflictObject[conflicts.size()]);
	}
	
}
