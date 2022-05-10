package eu.redseeds.sc.current.ui.validation.validators;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import eu.redseeds.sc.current.ui.validation.IValidate;
import eu.redseeds.sc.current.ui.validation.ValidationAdapter;
import eu.redseeds.sc.current.ui.validation.ValidationResult;
import eu.redseeds.scl.model.rsl.rsldomainelements.actors.ActorsPackageDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.domainelements.DomainSpecificationDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionsPackageDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.systemelements.SystemElementsPackageDTO;

public class DomainSpecificationValidator implements IValidate {

	@Override
	public int getComplexness() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getLabel() {
		return "Validating domain specification";
	}

	@Override
	public ValidationResult[] validate(Object element) {
		if (!(element instanceof DomainSpecificationDTO)) {
			ValidationResult vr = new ValidationResult();
			vr.setProblemID(IValidate.ID_WRONG_TYPE);
			vr.setMessage(MSG_WRONG_TYPE);
			vr.setSclElement(element);
			return new ValidationResult[] { vr };
		}
		return new ValidationResult[0];
	}

	@Override
	public ValidationResult[] validateRecursively(Object element) {
		List<ValidationResult> results = new ArrayList<ValidationResult>();
		results.addAll(Arrays.asList(validate(element)));
		if (element instanceof DomainSpecificationDTO) {
			DomainSpecificationDTO ds = (DomainSpecificationDTO)element;
			//validate root packages
			if(ds.getActorsPackageDTOList().size() > 0) {
				IValidate validatorAP = ValidationAdapter.getValidator(ds.getActorsPackageDTOList().get(0));
				for(ActorsPackageDTO actpack : ds.getActorsPackageDTOList()) {
					results.addAll(Arrays.asList(validatorAP.validateRecursively(actpack)));
				}
			}
			if(ds.getNotionsPackageDTOList().size() > 0) {
				IValidate validatorNP = ValidationAdapter.getValidator(ds.getNotionsPackageDTOList().get(0));
				for(NotionsPackageDTO notpack : ds.getNotionsPackageDTOList()) {
					results.addAll(Arrays.asList(validatorNP.validateRecursively(notpack)));
				}
			}
			if(ds.getSystemElementsPackageDTOList().size() > 0) {
				IValidate validatorSEP = ValidationAdapter.getValidator(ds.getSystemElementsPackageDTOList().get(0));
				for(SystemElementsPackageDTO syselpack : ds.getSystemElementsPackageDTOList()) {
					results.addAll(Arrays.asList(validatorSEP.validateRecursively(syselpack)));
				}
			}
		}
		return (ValidationResult[])results.toArray(new ValidationResult[results.size()]);
	}

}
