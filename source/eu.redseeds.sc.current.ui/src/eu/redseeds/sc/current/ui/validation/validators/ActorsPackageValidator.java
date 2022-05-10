package eu.redseeds.sc.current.ui.validation.validators;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import eu.redseeds.sc.current.ui.validation.IValidate;
import eu.redseeds.sc.current.ui.validation.ValidationAdapter;
import eu.redseeds.sc.current.ui.validation.ValidationResult;
import eu.redseeds.scl.model.rsl.rsldomainelements.actors.ActorDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.actors.ActorsPackageDTO;

public class ActorsPackageValidator implements IValidate {

	@Override
	public int getComplexness() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getLabel() {
		return "Validating actors package";
	}

	@Override
	public ValidationResult[] validate(Object element) {
		if (!(element instanceof ActorsPackageDTO)) {
			ValidationResult vr = new ValidationResult();
			vr.setProblemID(IValidate.ID_WRONG_TYPE);
			vr.setMessage(MSG_WRONG_TYPE);
			vr.setSclElement(element);
			return new ValidationResult[] { vr };
		}
		ActorsPackageDTO ap = (ActorsPackageDTO)element;
		if(!validateName(ap)) {
			ValidationResult vr = new ValidationResult();
			vr.setProblemID(IValidate.ID_ACTPACK_DUPLICATE_NAME);
			vr.setMessage(MSG_ACTPACK_DUPLICATE_NAME);
			vr.setSeverity(IValidate.SEVERITY_ERROR);
			vr.setPath(ap.getSpecificationPath());
			vr.setSclElement(ap);
			return new ValidationResult[] { vr };
		}
		return new ValidationResult[0];
	}

	@Override
	public ValidationResult[] validateRecursively(Object element) {
		List<ValidationResult> results = new ArrayList<ValidationResult>();
		results.addAll(Arrays.asList(validate(element)));
		if (element instanceof ActorsPackageDTO) {
			ActorsPackageDTO ap = (ActorsPackageDTO)element;
			//validate actors packages
			if(ap.getActorsPackageDTOList().size() > 0) {
				IValidate validatorAP = ValidationAdapter.getValidator(ap.getActorsPackageDTOList().get(0));
				for(ActorsPackageDTO actpack : ap.getActorsPackageDTOList()) {
					results.addAll(Arrays.asList(validatorAP.validateRecursively(actpack)));
				}
			}
			//validate actors
			if(ap.getActorDTOList().size() > 0) {
				IValidate validatorA = ValidationAdapter.getValidator(ap.getActorDTOList().get(0));
				for(ActorDTO act : ap.getActorDTOList()) {
					results.addAll(Arrays.asList(validatorA.validateRecursively(act)));
				}
			}
		}
		return (ValidationResult[])results.toArray(new ValidationResult[results.size()]);
	}
	
	/**
	 * validates if other package with the same name does not exist at the same level of the domain tree
	 * severity: high
	 * @param ap
	 * @return
	 */
	private boolean validateName(ActorsPackageDTO ap) {
		ActorsPackageDTO parent = ap.getParent();
		List<ActorsPackageDTO> packages = null;
		if(parent != null) {
			packages = parent.getActorsPackageDTOList();
		}
		else if(ap.getDomainSpecificationParent() != null) {
			packages = ap.getDomainSpecificationParent().getActorsPackageDTOList();
		}
		if(packages != null) {
			for(ActorsPackageDTO otherPack : packages) {
				if(otherPack.getName().equals(ap.getName()) && !otherPack.equals(ap)) {
					return false;
				}
			}
		}
		return true;
	}
}
