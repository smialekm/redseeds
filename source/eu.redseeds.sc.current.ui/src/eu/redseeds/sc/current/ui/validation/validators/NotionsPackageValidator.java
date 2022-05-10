package eu.redseeds.sc.current.ui.validation.validators;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import eu.redseeds.sc.current.ui.validation.IValidate;
import eu.redseeds.sc.current.ui.validation.ValidationAdapter;
import eu.redseeds.sc.current.ui.validation.ValidationResult;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionsPackageDTO;

public class NotionsPackageValidator implements IValidate {

	@Override
	public int getComplexness() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getLabel() {
		return "Validating notions package";
	}

	@Override
	public ValidationResult[] validate(Object element) {
		if (!(element instanceof NotionsPackageDTO)) {
			ValidationResult vr = new ValidationResult();
			vr.setProblemID(IValidate.ID_WRONG_TYPE);
			vr.setMessage(MSG_WRONG_TYPE);
			vr.setSclElement(element);
			return new ValidationResult[] { vr };
		}
		NotionsPackageDTO np = (NotionsPackageDTO)element;
		if(!validateName(np)) {
			ValidationResult vr = new ValidationResult();
			vr.setProblemID(IValidate.ID_NOTPACK_DUPLICATE_NAME);
			vr.setMessage(MSG_NOTPACK_DUPLICATE_NAME);
			vr.setSeverity(IValidate.SEVERITY_ERROR);
			vr.setPath(np.getSpecificationPath());
			vr.setSclElement(np);
			return new ValidationResult[] { vr };
		}
		return new ValidationResult[0];
	}

	@Override
	public ValidationResult[] validateRecursively(Object element) {
		List<ValidationResult> results = new ArrayList<ValidationResult>();
		results.addAll(Arrays.asList(validate(element)));
		if (element instanceof NotionsPackageDTO) {
			NotionsPackageDTO np = (NotionsPackageDTO)element;
			//validate notions packages
			if(np.getNotionsPackageDTOList().size() > 0) {
				IValidate validatorNP = ValidationAdapter.getValidator(np.getNotionsPackageDTOList().get(0));
				for(NotionsPackageDTO notpack : np.getNotionsPackageDTOList()) {
					results.addAll(Arrays.asList(validatorNP.validateRecursively(notpack)));
				}
			}
			//validate notions
			if(np.getNotionDTOList().size() > 0) {
				IValidate validatorN = ValidationAdapter.getValidator(np.getNotionDTOList().get(0));
				for(NotionDTO not : np.getNotionDTOList()) {
					results.addAll(Arrays.asList(validatorN.validateRecursively(not)));
				}
			}
		}
		return (ValidationResult[])results.toArray(new ValidationResult[results.size()]);
	}
	
	/**
	 * validates if other package with the same name does not exist at the same level of the domain tree
	 * severity: high
	 * @param np
	 * @return
	 */
	private boolean validateName(NotionsPackageDTO np) {
		NotionsPackageDTO parent = np.getParent();
		List<NotionsPackageDTO> packages = null;
		if(parent != null) {
			packages = parent.getNotionsPackageDTOList();
		}
		else if(np.getDomainSpecificationParent() != null) {
			packages = np.getDomainSpecificationParent().getNotionsPackageDTOList();
		}
		if(packages != null) {
			for(NotionsPackageDTO otherPack : packages) {
				if(otherPack.getName().equals(np.getName()) && !otherPack.equals(np)) {
					return false;
				}
			}
		}
		return true;
	}

}
