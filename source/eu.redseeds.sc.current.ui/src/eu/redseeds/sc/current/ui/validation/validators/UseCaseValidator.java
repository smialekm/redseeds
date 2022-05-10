package eu.redseeds.sc.current.ui.validation.validators;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import eu.redseeds.sc.current.ui.validation.IValidate;
import eu.redseeds.sc.current.ui.validation.ValidationAdapter;
import eu.redseeds.sc.current.ui.validation.ValidationResult;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentations.descriptiverequirementrepresentations.ConstrainedLanguageScenarioDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.UseCaseDTO;

public class UseCaseValidator implements IValidate {

	@Override
	public ValidationResult[] validate(Object element) {
		if (!(element instanceof UseCaseDTO)) {
			ValidationResult vr = new ValidationResult();
			vr.setProblemID(IValidate.ID_WRONG_TYPE);
			vr.setMessage(MSG_WRONG_TYPE);
			vr.setSclElement(element);
			return new ValidationResult[] { vr };
		}
		UseCaseDTO uc = (UseCaseDTO)element;
		if(!validateScenariosCount(uc)) {
			ValidationResult vr = new ValidationResult();
			vr.setProblemID(IValidate.ID_UC_NO_SCENARIOS);
			vr.setMessage(MSG_UC_NO_SCENARIOS);
			vr.setPath(uc.getSpecificationPath()+"\\"+uc.getName());
			vr.setSclElement(uc);
//			uc.setValid(false);
			return new ValidationResult[] { vr };
		}
		return new ValidationResult[0];
	}

	@Override
	public ValidationResult[] validateRecursively(Object element) {
		if (!(element instanceof UseCaseDTO)) {
			ValidationResult vr = new ValidationResult();
			vr.setProblemID(IValidate.ID_WRONG_TYPE);
			vr.setMessage(MSG_WRONG_TYPE);
			vr.setSclElement(element);
			return new ValidationResult[] { vr };
		}
		List<ValidationResult> results = new ArrayList<ValidationResult>();
		results.addAll(Arrays.asList(validate(element)));
		UseCaseDTO uc = (UseCaseDTO)element;
		List<ConstrainedLanguageScenarioDTO> scens = uc.getConstrainedLanguageScenarioDTOList();
		if(scens.size() > 0) {
			IValidate validator = ValidationAdapter.getValidator(scens.get(0));
			for (Iterator<ConstrainedLanguageScenarioDTO> iterator = scens.iterator(); iterator.hasNext();) {
				ConstrainedLanguageScenarioDTO scenario 
					= (ConstrainedLanguageScenarioDTO) iterator.next();
				results.addAll(Arrays.asList(validator.validateRecursively(scenario)));
			}
		}
//		if(results.size() > 0) {
//			uc.setValid(false);
//		}
		return (ValidationResult[])results.toArray(new ValidationResult[results.size()]);
		
	}

	@Override
	public int getComplexness() {
		return 1;
	}
	
	private boolean validateScenariosCount(UseCaseDTO uc) {
		if(uc.getConstrainedLanguageScenarioDTOList().size() == 0) {
			return false;
		}
		else {
			return true;
		}
	}

	@Override
	public String getLabel() {
		return "Validating use case";
	}

}
