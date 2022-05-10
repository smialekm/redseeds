package eu.redseeds.sc.current.ui.validation.validators;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import eu.redseeds.sc.current.ui.validation.IValidate;
import eu.redseeds.sc.current.ui.validation.ValidationAdapter;
import eu.redseeds.sc.current.ui.validation.ValidationResult;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementsPackageDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementsSpecificationDTO;

public class RequirementsPackageValidator implements IValidate {

	@Override
	public int getComplexness() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public String getLabel() {
		return "Validating requirements package";
	}

	@Override
	public ValidationResult[] validate(Object element) {
		if (!(element instanceof RequirementsPackageDTO)) {
			ValidationResult vr = new ValidationResult();
			vr.setProblemID(IValidate.ID_WRONG_TYPE);
			vr.setMessage(MSG_WRONG_TYPE);
			vr.setSclElement(element);
			return new ValidationResult[] { vr };
		}
		RequirementsPackageDTO rp = (RequirementsPackageDTO)element;
		List<ValidationResult> list = new ArrayList<ValidationResult>();
		ValidationResult vr = null;
		if(!validateName(rp)) {
			vr = new ValidationResult();
			vr.setProblemID(IValidate.ID_REQPACK_DUPLICATE_NAME);
			vr.setMessage(MSG_REQPACK_DUPLICATE_NAME);
			vr.setSeverity(IValidate.SEVERITY_ERROR);
			vr.setPath(rp.getSpecificationPath());
			vr.setSclElement(rp);
			list.add(vr);
		}
		if (!vaidateExistenceOfOtherRequirementsPackages(rp)) {
			vr = new ValidationResult();
			vr.setProblemID(IValidate.ID_OTHER_THAN_DEFAULT_USE_CASES_PACKAGE);
			vr.setMessage(MSG_OTHER_THAN_DEFAULT_USE_CASES_PACKAGE);
			vr.setPath(rp.getSpecificationPath());
			vr.setSclElement(rp);
			vr.setSeverity(IValidate.SEVERITY_INFO);
			list.add(vr);
		}
		return list.toArray(new ValidationResult[0]);
	}

	private boolean vaidateExistenceOfOtherRequirementsPackages(RequirementsPackageDTO rp) {
		if (rp.getParent() instanceof RequirementsSpecificationDTO && !"Use Cases".equals(rp.getName()) && !"RecoveredScenarios".equals(rp.getName()))
			return false;
		return true;
	}

	@Override
	public ValidationResult[] validateRecursively(Object element) {
		List<ValidationResult> results = new ArrayList<ValidationResult>();
		results.addAll(Arrays.asList(validate(element)));
		if (element instanceof RequirementsPackageDTO) {
			RequirementsPackageDTO rp = (RequirementsPackageDTO)element;
			//validate root req packages
			if(rp.getRequirementsPackages().size() > 0) {
				IValidate validatorRP = ValidationAdapter.getValidator(rp.getRequirementsPackages().get(0));
				for(RequirementsPackageDTO reqpack : rp.getRequirementsPackages()) {
					results.addAll(Arrays.asList(validatorRP.validateRecursively(reqpack)));
				}
			}
			//validate requirements and use cases
			for(RequirementDTO req : rp.getRequirements()) {
				IValidate validatorR = ValidationAdapter.getValidator(req);//could be a req or a uc -> get validator every time
				results.addAll(Arrays.asList(validatorR.validateRecursively(req)));
			}
		}
		return (ValidationResult[])results.toArray(new ValidationResult[results.size()]);
	}
	
	/**
	 * validates if other package with the same name does not exist at the same level of the reqs tree
	 * severity: high
	 * @param rp
	 * @return
	 */
	private boolean validateName(RequirementsPackageDTO rp) {
		RequirementsPackageDTO parent = rp.getParent();
		List<RequirementsPackageDTO> packages = null;
		if(parent != null) {
			packages = parent.getRequirementsPackages();
		}
		else if(rp.getRequirementsSpecificationParent() != null) {
			packages = rp.getRequirementsSpecificationParent().getRequirementsPackagesDTOList();
		}
		if(packages != null) {
			for(RequirementsPackageDTO otherPack : packages) {
				if(otherPack.getName().equals(rp.getName()) && !otherPack.equals(rp)) {
					return false;
				}
			}
		}
		return true;
	}

}
