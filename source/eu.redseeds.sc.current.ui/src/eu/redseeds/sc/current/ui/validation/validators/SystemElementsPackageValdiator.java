package eu.redseeds.sc.current.ui.validation.validators;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import eu.redseeds.sc.current.ui.validation.IValidate;
import eu.redseeds.sc.current.ui.validation.ValidationAdapter;
import eu.redseeds.sc.current.ui.validation.ValidationResult;
import eu.redseeds.scl.model.rsl.rsldomainelements.systemelements.SystemElementDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.systemelements.SystemElementsPackageDTO;

public class SystemElementsPackageValdiator implements IValidate {

	@Override
	public int getComplexness() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getLabel() {
		return "Validating system elements package";
	}

	@Override
	public ValidationResult[] validate(Object element) {
		if (!(element instanceof SystemElementsPackageDTO)) {
			ValidationResult vr = new ValidationResult();
			vr.setProblemID(IValidate.ID_WRONG_TYPE);
			vr.setMessage(MSG_WRONG_TYPE);
			vr.setSclElement(element);
			return new ValidationResult[] { vr };
		}
		SystemElementsPackageDTO sep = (SystemElementsPackageDTO)element;
		if(!validateName(sep)) {
			ValidationResult vr = new ValidationResult();
			vr.setProblemID(IValidate.ID_SYSELPACK_DUPLICATE_NAME);
			vr.setMessage(MSG_SYSELTPACK_DUPLICATE_NAME);
			vr.setSeverity(IValidate.SEVERITY_ERROR);
			vr.setPath(sep.getSpecificationPath());
			vr.setSclElement(sep);
			return new ValidationResult[] { vr };
		}
		return new ValidationResult[0];
	}

	@Override
	public ValidationResult[] validateRecursively(Object element) {
		List<ValidationResult> results = new ArrayList<ValidationResult>();
		results.addAll(Arrays.asList(validate(element)));
		if (element instanceof SystemElementsPackageDTO) {
			SystemElementsPackageDTO sep = (SystemElementsPackageDTO)element;
			//validate notions packages
			if(sep.getSystemElementsPackageDTOList().size() > 0) {
				IValidate validatorSEP = ValidationAdapter.getValidator(sep.getSystemElementsPackageDTOList().get(0));
				for(SystemElementsPackageDTO syselpack : sep.getSystemElementsPackageDTOList()) {
					results.addAll(Arrays.asList(validatorSEP.validateRecursively(syselpack)));
				}
			}
			//validate notions
			if(sep.getSystemElementDTOList().size() > 0) {
				IValidate validatorSE = ValidationAdapter.getValidator(sep.getSystemElementDTOList().get(0));
				for(SystemElementDTO se : sep.getSystemElementDTOList()) {
					results.addAll(Arrays.asList(validatorSE.validateRecursively(se)));
				}
			}
		}
		return (ValidationResult[])results.toArray(new ValidationResult[results.size()]);
	}
	
	/**
	 * validates if other package with the same name does not exist at the same level of the domain tree
	 * severity: high
	 * @param sep
	 * @return
	 */
	private boolean validateName(SystemElementsPackageDTO sep) {
		SystemElementsPackageDTO parent = sep.getParent();
		List<SystemElementsPackageDTO> packages = null;
		if(parent != null) {
			packages = parent.getSystemElementsPackageDTOList();
		}
		else if(sep.getDomainSpecificationParent() != null) {
			packages = sep.getDomainSpecificationParent().getSystemElementsPackageDTOList();
		}
		if(packages != null) {
			for(SystemElementsPackageDTO otherPack : packages) {
				if(otherPack.getName().equals(sep.getName()) && !otherPack.equals(sep)) {
					return false;
				}
			}
		}
		return true;
	}

}
