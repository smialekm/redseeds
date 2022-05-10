package eu.redseeds.sc.current.ui.validation.validators;

import eu.redseeds.sc.current.ui.validation.IValidate;
import eu.redseeds.sc.current.ui.validation.ValidationResult;
import eu.redseeds.scl.model.rsl.rsldomainelements.actors.ActorDTO;

public class ActorValidator implements IValidate {

	@Override
	public int getComplexness() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getLabel() {
		return "Validating actor";
	}

	@Override
	public ValidationResult[] validate(Object element) {
		if (!(element instanceof ActorDTO)) {
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
		return validate(element);
	}

}
