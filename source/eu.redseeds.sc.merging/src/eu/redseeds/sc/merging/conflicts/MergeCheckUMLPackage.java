package eu.redseeds.sc.merging.conflicts;

import eu.redseeds.sc.merging.MergeConstants;
import eu.redseeds.scl.model.sclkernel.SoftwareCaseDTO;
import eu.redseeds.scl.model.sdsl.PackageDTO;

public class MergeCheckUMLPackage implements IMergeCheck {

	@Override
	public MergeConflictObject[] check(Object element, SoftwareCaseDTO targetSC) {
		if (element instanceof PackageDTO) {
			PackageDTO clipboardP = (PackageDTO) element;
			//check root sc pack name if clipboardP is root
			if(clipboardP.getParent() == null) {//is root in clip
				for(PackageDTO scRootP : targetSC.getArchitecturalModelDTO().getPackageDTOList()) {//check in arch
					if(scRootP.getName().equalsIgnoreCase(clipboardP.getName())) {
						return new MergeConflictObject[] {
								MergeConflictFactory.createMergeConflictObject(
										scRootP, clipboardP, MergeConstants.MERGE_CONFLICT_NAMING_PACKAGE_ID)};
					}
					
				}
				for(PackageDTO scRootP : targetSC.getDetailedDesignModelDTO().getPackageDTOList()) {//check in dd
					if(scRootP.getName().equalsIgnoreCase(clipboardP.getName())) {
						return new MergeConflictObject[] {
								MergeConflictFactory.createMergeConflictObject(
										scRootP, clipboardP, MergeConstants.MERGE_CONFLICT_NAMING_PACKAGE_ID)};
					}
					
				}
			}
		}
		return new MergeConflictObject[0];
	}

}
