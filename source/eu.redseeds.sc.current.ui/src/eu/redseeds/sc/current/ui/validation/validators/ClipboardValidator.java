package eu.redseeds.sc.current.ui.validation.validators;

import eu.redseeds.sc.current.ui.validation.IValidate;
import eu.redseeds.sc.current.ui.validation.ValidationResult;

public class ClipboardValidator implements IValidate {

	@Override
	public int getComplexness() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getLabel() {
		return "Validating clipboard";
	}

	@Override
	public ValidationResult[] validate(Object element) {
		return new ValidationResult[0];
	}

	@Override
	public ValidationResult[] validateRecursively(Object element) {
		return new ValidationResult[0];
	}

}
