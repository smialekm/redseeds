package eu.redseeds.sc.merging.conflicts;

import eu.redseeds.sc.merging.MergeConstants;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementsPackageDTO;
import eu.redseeds.scl.model.sclkernel.SoftwareCaseDTO;

public class MergeCheckReqPackage implements IMergeCheck {

	@Override
	public MergeConflictObject[] check(Object element, SoftwareCaseDTO targetSC) {
		if (element instanceof RequirementsPackageDTO) {
			RequirementsPackageDTO clipboardRP = (RequirementsPackageDTO) element;
			//check root sc pack name if clipboardRP is root
			if(clipboardRP.getRequirementsSpecificationParent() != null) {//is root in clip
				for(RequirementsPackageDTO scRootRP : targetSC.getRequirementsSpecificationDTO().getRequirementsPackagesDTOList()) {
					if(scRootRP.getName().equalsIgnoreCase(clipboardRP.getName())) {
						return new MergeConflictObject[] 
						       				        {MergeConflictFactory.createMergeConflictObject(
						       				        		scRootRP, clipboardRP, MergeConstants.MERGE_CONFLICT_NAMING_REQPACKAGE_ID)};
					}
				}
			}
		}
		return new MergeConflictObject[0];
	}

}
