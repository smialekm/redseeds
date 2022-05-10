package eu.redseeds.sc.merging.conflicts;

import eu.redseeds.sc.merging.MergeConstants;
import eu.redseeds.scl.model.rsl.rsldomainelements.actors.ActorsPackageDTO;
import eu.redseeds.scl.model.sclkernel.SoftwareCaseDTO;

public class MergeCheckActorsPackage implements IMergeCheck {

	@Override
	public MergeConflictObject[] check(Object element, SoftwareCaseDTO targetSC) {
		if (element instanceof ActorsPackageDTO) {
			ActorsPackageDTO clipboardAP = (ActorsPackageDTO) element;
			//check root sc pack name if clipboardRP is root
			if(clipboardAP.getDomainSpecificationParent() == null) {//is not a root in clip
				ActorsPackageDTO scRootAP = targetSC.getRequirementsSpecificationDTO()
					.getDomainSpecificationDTO().getActorsPackageDTOList().get(0);
				for(ActorsPackageDTO scSubRootAP : scRootAP.getActorsPackageDTOList()) {
					if(scSubRootAP.getName().equalsIgnoreCase(clipboardAP.getName())) {
						return new MergeConflictObject[] 
						       				        {MergeConflictFactory.createMergeConflictObject(
						       				        		scSubRootAP, clipboardAP, MergeConstants.MERGE_CONFLICT_NAMING_ACTORS_PACKAGE_ID)};
					}
				}
			}
		}
		return new MergeConflictObject[0];
	}

}
