package eu.redseeds.sc.current.ui.validation.validators;

import eu.redseeds.sc.current.ui.validation.IValidate;
import eu.redseeds.sc.current.ui.validation.ValidationResult;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentations.descriptiverequirementrepresentations.ConstrainedLanguageScenarioDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.constrainedlanguagesentences.RejoinSentenceDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.UseCaseDTO;

public class RejoinSentenceValidator implements IValidate {

	@Override
	public int getComplexness() {
		return 0;
	}

	@Override
	public String getLabel() {
		return "Validating rejoin sentence";
	}

	@Override
	public ValidationResult[] validate(Object element) {
		if (!(element instanceof RejoinSentenceDTO)) {
			ValidationResult vr = new ValidationResult();
			vr.setProblemID(IValidate.ID_WRONG_TYPE);
			vr.setMessage(MSG_WRONG_TYPE);
			vr.setSclElement(element);
			return new ValidationResult[] { vr };
		}
		
		RejoinSentenceDTO sent = (RejoinSentenceDTO) element;
		if (!validateRejoinSentence(sent)) {
			ValidationResult vr = new ValidationResult();
			vr.setProblemID(IValidate.ID_SENT_REJOIN_EMPTY);
			vr.setMessage(MSG_SENT_REJOIN_EMPTY);
			if (!sent.getOwningScenarios().isEmpty()) {
				ConstrainedLanguageScenarioDTO scen 
					= sent.getOwningScenarios().get(0);
				UseCaseDTO uc = scen.getParent();
				vr.setPath(uc.getSpecificationPath()+"\\"+uc.getName()+"\\"+scen.getName());
			}
			vr.setSclElement(element);
			vr.setSeverity(IValidate.SEVERITY_INFO);
			return new ValidationResult[] { vr };
		}
		
		return new ValidationResult[0];
	}

	@Override
	public ValidationResult[] validateRecursively(Object element) {
		return validate(element);
	}
	
	private boolean validateRejoinSentence(RejoinSentenceDTO rejoin) {
		if (rejoin.getRejoinedSentence()!=null)
			return true;
		else
			return false;
	}

}
