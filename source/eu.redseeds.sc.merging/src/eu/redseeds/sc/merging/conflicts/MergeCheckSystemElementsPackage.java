package eu.redseeds.sc.merging.conflicts;

import eu.redseeds.sc.merging.MergeConstants;
import eu.redseeds.scl.model.rsl.rsldomainelements.systemelements.SystemElementsPackageDTO;
import eu.redseeds.scl.model.sclkernel.SoftwareCaseDTO;

public class MergeCheckSystemElementsPackage implements IMergeCheck {

	@Override
	public MergeConflictObject[] check(Object element, SoftwareCaseDTO targetSC) {
		if (element instanceof SystemElementsPackageDTO) {
			SystemElementsPackageDTO clipboardSEP = (SystemElementsPackageDTO) element;
			//check root sc pack name if clipboardRP is root
			if(clipboardSEP.getDomainSpecificationParent() == null) {//is not a root in clip
				SystemElementsPackageDTO scRootSEP = targetSC.getRequirementsSpecificationDTO()
					.getDomainSpecificationDTO().getSystemElementsPackageDTOList().get(0);
				for(SystemElementsPackageDTO scSubRootSEP : scRootSEP.getSystemElementsPackageDTOList()) {
					if(scSubRootSEP.getName().equalsIgnoreCase(clipboardSEP.getName())) {
						return new MergeConflictObject[] 
						       				        {MergeConflictFactory.createMergeConflictObject(
						       				        		scSubRootSEP, clipboardSEP, MergeConstants.MERGE_CONFLICT_NAMING_SYSTEM_ELEMENTS_PACKAGE_ID)};
					}
				}
			}
		}
		return new MergeConflictObject[0];
	}

}
