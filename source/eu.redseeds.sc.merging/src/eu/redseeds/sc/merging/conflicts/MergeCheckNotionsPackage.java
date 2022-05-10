package eu.redseeds.sc.merging.conflicts;

import eu.redseeds.sc.merging.MergeConstants;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionsPackageDTO;
import eu.redseeds.scl.model.sclkernel.SoftwareCaseDTO;

public class MergeCheckNotionsPackage implements IMergeCheck {

	@Override
	public MergeConflictObject[] check(Object element, SoftwareCaseDTO targetSC) {
		if (element instanceof NotionsPackageDTO) {
			NotionsPackageDTO clipboardNP = (NotionsPackageDTO) element;
			//check root sc pack name if clipboardRP is root
			if(clipboardNP.getDomainSpecificationParent() == null) {//is not a root in clip
				NotionsPackageDTO scRootNP = targetSC.getRequirementsSpecificationDTO()
					.getDomainSpecificationDTO().getNotionsPackageDTOList().get(0);
				for(NotionsPackageDTO scSubRootNP : scRootNP.getNotionsPackageDTOList()) {
					if(scSubRootNP.getName().equalsIgnoreCase(clipboardNP.getName())) {
						return new MergeConflictObject[] 
						       				        {MergeConflictFactory.createMergeConflictObject(
						       				        		scSubRootNP, clipboardNP, MergeConstants.MERGE_CONFLICT_NAMING_NOTIONS_PACKAGE_ID)};
					}
				}
			}
		}
		return new MergeConflictObject[0];
	}

}
