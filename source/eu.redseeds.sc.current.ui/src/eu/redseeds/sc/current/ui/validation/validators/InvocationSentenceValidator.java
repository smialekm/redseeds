package eu.redseeds.sc.current.ui.validation.validators;

import eu.redseeds.sc.current.ui.validation.IValidate;
import eu.redseeds.sc.current.ui.validation.ValidationResult;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentations.descriptiverequirementrepresentations.ConstrainedLanguageScenarioDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.controlsentences.InvocationSentenceDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.UseCaseDTO;

public class InvocationSentenceValidator implements IValidate {

	@Override
	public int getComplexness() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ValidationResult[] validate(Object element) {
		if (!(element instanceof InvocationSentenceDTO)) {
			ValidationResult vr = new ValidationResult();
			vr.setProblemID(IValidate.ID_WRONG_TYPE);
			vr.setMessage(MSG_WRONG_TYPE);
			vr.setSclElement(element);
			return new ValidationResult[] { vr };
		}
		InvocationSentenceDTO sent = (InvocationSentenceDTO) element;
		if (!validateInvokedUseCase(sent)) {
			ValidationResult vr = new ValidationResult();
			vr.setProblemID(IValidate.ID_SENT_INV_UC_NOT_SET);
			vr.setMessage(MSG_SENT_INV_UC_NOT_SET);
			if (!sent.getOwningScenarios().isEmpty()) {
				ConstrainedLanguageScenarioDTO scen 
					= sent.getOwningScenarios().get(0);
				UseCaseDTO uc = scen.getParent();
				vr.setPath(uc.getSpecificationPath()+"\\"+uc.getName()+"\\"+scen.getName());
			}
			vr.setSclElement(element);
			vr.setSeverity(IValidate.SEVERITY_ERROR);
			return new ValidationResult[] { vr };
		}
		if (!validateInvokedUseCaseDifferentThenOwn(sent)) {
			ValidationResult vr = new ValidationResult();
			vr.setProblemID(IValidate.ID_SENT_INV_UC_SAME);
			vr.setMessage(MSG_SENT_INV_UC_SAME);
			if (!sent.getOwningScenarios().isEmpty()) {
				ConstrainedLanguageScenarioDTO scen 
					= sent.getOwningScenarios().get(0);
				UseCaseDTO uc = scen.getParent();
				vr.setPath(uc.getSpecificationPath()+"\\"+uc.getName()+"\\"+scen.getName());
			}
			vr.setSclElement(element);
			vr.setSeverity(IValidate.SEVERITY_ERROR);
			return new ValidationResult[] { vr };
		}
		return new ValidationResult[0];
	}

	@Override
	public ValidationResult[] validateRecursively(Object element) {
		// TODO validate children
		return validate(element);
	}

	/*
	 * // validates if InclusionType is set // this should not occur due to RSL
	 * Editor restrictions // severity: high private boolean
	 * validateInclusionType(InvocationSentenceDTO inv) {
	 * if(inv.getInclusionType()==null) return false; return true; }
	 */

	// validates if invoked UseCase is set
	// severity: high
	// action to be performed:
	// UseCase editor with sentence's useCase on scenario containing sentence
	// getting scenario:
	// if(!sentence.getOwningScenarios().isEmpty())
	// sentence.getOwningScenarios().get(0)
	// getting UseCase:
	// sentence.getOwningScenarios().get(0).getParent()
	//
	private boolean validateInvokedUseCase(InvocationSentenceDTO inv) {
		if (inv.getInvokedUseCase() != null)
			if (inv.getInvokedUseCase() instanceof UseCaseDTO)
				return true;
		return false;

	}

	// validates if invoked UseCase is different than current use case
	// severity: high
	// action to be performed:
	// the same as for validateInvokedUseCase
	private boolean validateInvokedUseCaseDifferentThenOwn(
			InvocationSentenceDTO inv) {
		if (inv.getInvokedUseCase() != null)
			if (inv.getInvokedUseCase() instanceof UseCaseDTO)
				if (!inv.getOwningScenarios().isEmpty())
					if (inv.getOwningScenarios().get(0).getParent() != null)
						if (!inv.getOwningScenarios().get(0).getParent()
								.equals(inv.getInvokedUseCase()))
							return true;
		return false;

	}

}
