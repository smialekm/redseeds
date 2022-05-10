package eu.redseeds.sc.current.ui.validation.validators;

import eu.redseeds.sc.current.ui.validation.IValidate;
import eu.redseeds.sc.current.ui.validation.ValidationResult;

public class GenericValidator implements IValidate {

	@Override
	public int getComplexness() {
		return 1;
	}

	@Override
	public ValidationResult[] validate(Object element) {
		ValidationResult vr = new ValidationResult();
		vr.setProblemID(IValidate.ID_NOT_IMPLEMENTED);
		vr.setMessage(MSG_NOT_IMPLEMENTED);
		vr.setPath("");
		vr.setSclElement(element);
		return new ValidationResult[] { vr };
	}

	@Override
	public ValidationResult[] validateRecursively(Object element) {
		return validate(element);
	}

	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return "Running generic validation...";
	}

}
