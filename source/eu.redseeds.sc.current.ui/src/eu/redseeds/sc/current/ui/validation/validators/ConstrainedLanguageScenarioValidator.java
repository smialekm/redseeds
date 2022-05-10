package eu.redseeds.sc.current.ui.validation.validators;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import eu.redseeds.sc.current.ui.validation.IValidate;
import eu.redseeds.sc.current.ui.validation.ValidationAdapter;
import eu.redseeds.sc.current.ui.validation.ValidationResult;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentations.descriptiverequirementrepresentations.ConstrainedLanguageScenarioDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.constrainedlanguagesentences.RejoinSentenceDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.controlsentences.PostconditionSentenceDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.UseCaseDTO;
import eu.redseeds.scl.rsl.rslrequirementrepresentations.descriptiverequirementrepresentations.ConstrainedLanguageScenario;
import eu.remics.recovery.model.RecoveryModelHelper;

public class ConstrainedLanguageScenarioValidator implements IValidate {

	@Override
	public int getComplexness() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public String getLabel() {
		return "Validating Constrained Language Scenario";
	}

	@Override
	public ValidationResult[] validate(Object element) {
		if (!(element instanceof ConstrainedLanguageScenarioDTO)) {
			ValidationResult vr = new ValidationResult();
			vr.setProblemID(IValidate.ID_WRONG_TYPE);
			vr.setMessage(MSG_WRONG_TYPE);
			vr.setSclElement(element);
			return new ValidationResult[] { vr };
		}
		ConstrainedLanguageScenarioDTO scen = (ConstrainedLanguageScenarioDTO) element;
		List<ValidationResult> results = new ArrayList<ValidationResult>();
		if(!validateContainsSentences(scen)) {
			ValidationResult vr = new ValidationResult();
			vr.setProblemID(IValidate.ID_SCEN_EMPTY);
			vr.setMessage(MSG_SCEN_EMPTY);
			if (scen.getParent() != null) {
				UseCaseDTO uc = scen.getParent();
				vr.setPath(uc.getSpecificationPath()+"\\"+uc.getName()+"\\"+scen.getName());
			}
			vr.setSclElement(element);
			results.add(vr);
		}
		if(!validateEndsWithPostconditionOrRejoin(scen)) {
			ValidationResult vr = new ValidationResult();
			vr.setProblemID(IValidate.ID_SCEN_END_NO_POSTCOND_OR_REJOIN);
			vr.setMessage(MSG_SCEN_END_NO_POSTCOND_OR_REJOIN);
			if (scen.getParent() != null) {
				UseCaseDTO uc = scen.getParent();
				vr.setPath(uc.getSpecificationPath()+"\\"+uc.getName()+"\\"+scen.getName());
			}
			vr.setSclElement(element);
			results.add(vr);
		}
		if(!validateNameNotEmpty(scen)) {
			ValidationResult vr = new ValidationResult();
			vr.setProblemID(IValidate.ID_SCEN_NAME_EMPTY);
			vr.setMessage(MSG_SCEN_NAME_EMPTY);
			if (scen.getParent() != null) {
				UseCaseDTO uc = scen.getParent();
				vr.setPath(uc.getSpecificationPath()+"\\"+uc.getName());
			}
			vr.setSclElement(element);
			results.add(vr);
		}
		return (ValidationResult[])results.toArray(new ValidationResult[results.size()]);
	}

	@Override
	public ValidationResult[] validateRecursively(Object element) {
		if (!(element instanceof ConstrainedLanguageScenarioDTO)) {
			ValidationResult vr = new ValidationResult();
			vr.setProblemID(IValidate.ID_WRONG_TYPE);
			vr.setMessage(MSG_WRONG_TYPE);
			vr.setSclElement(element);
			return new ValidationResult[] { vr };
		}
		ConstrainedLanguageScenarioDTO scen = (ConstrainedLanguageScenarioDTO) element;
		RecoveryModelHelper rmh = RecoveryModelHelper.instance((ConstrainedLanguageScenario)scen);
		List<ValidationResult> results = new ArrayList<ValidationResult>();
		if(!rmh.getRecoveredScenariosPackage().getRequirements().contains(scen.getParent())){
			results.addAll(Arrays.asList(validate(element)));
			Object[] children = scen.getScenarioSentenceList().toArray();
			for (int i = 0; i < children.length; i++) {
				IValidate validator = ValidationAdapter.getValidator(children[i]);
				results.addAll(Arrays.asList(validator.validateRecursively(children[i])));
			}
		}
		return (ValidationResult[])results.toArray(new ValidationResult[results.size()]);
	}

	// validates if scenario contains any sentences besides precondition
	// severity: medium
	// action to be performed:
	// UseCase editor with given scenario
	// getting UseCase:
	// scenario.getParent()
	//
	private boolean validateContainsSentences(
			ConstrainedLanguageScenarioDTO scen) {
		if (scen.getScenarioSentenceList() != null)
			if (scen.getScenarioSentenceList().size() > 1)
				return true;
		return false;
	}

	// validates if scenario ends with postconditionSentence or rejoinSentence
	// severity: medium
	// action to be performed:
	// the same as for validateContainsSentences
	//
	private boolean validateEndsWithPostconditionOrRejoin(
			ConstrainedLanguageScenarioDTO scen) {
		if (validateContainsSentences(scen))
			if (scen.getScenarioSentenceList().get(
					scen.getScenarioSentenceList().size() - 1) instanceof PostconditionSentenceDTO ||
					scen.getScenarioSentenceList().get(
							scen.getScenarioSentenceList().size() - 1) instanceof RejoinSentenceDTO)
				return true;
		return false;
	}

	// validates if scenario's name is not empty
	// severity: medium
	// action to be performed:
	// the same as for validateContainsSentences
	//
	private boolean validateNameNotEmpty(ConstrainedLanguageScenarioDTO scen) {
		if (scen.getName() != null)
			if (!scen.getName().equals(""))
				return true;
		return false;
	}

}
