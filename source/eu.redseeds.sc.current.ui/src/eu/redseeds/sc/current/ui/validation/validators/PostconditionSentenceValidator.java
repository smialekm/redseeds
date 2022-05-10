package eu.redseeds.sc.current.ui.validation.validators;

import eu.redseeds.sc.current.ui.validation.IValidate;
import eu.redseeds.sc.current.ui.validation.ValidationResult;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentations.descriptiverequirementrepresentations.ConstrainedLanguageScenarioDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.controlsentences.PostconditionSentenceDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.UseCaseDTO;

public class PostconditionSentenceValidator implements IValidate {

	@Override
	public int getComplexness() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public String getLabel() {
		return "Validating Postcondition Sentence";
	}

	@Override
	public ValidationResult[] validate(Object element) {
		if (!(element instanceof PostconditionSentenceDTO)) {
			ValidationResult vr = new ValidationResult();
			vr.setProblemID(IValidate.ID_WRONG_TYPE);
			vr.setMessage(MSG_WRONG_TYPE);
			vr.setSclElement(element);
			return new ValidationResult[] { vr };
		}
		PostconditionSentenceDTO sent = (PostconditionSentenceDTO) element;
		if (!validatePostcondition(sent)) {
			ValidationResult vr = new ValidationResult();
			vr.setProblemID(IValidate.ID_SENT_POSTCOND_EMPTY);
			vr.setMessage(MSG_SENT_POSTCOND_EMPTY);
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
		// TODO validate children
		return validate(element);
	}

	// validates if postcondition in PostconditionSentence is set
	// severity: very low
	// action to be performed:
	// UseCase editor with sentence's useCase on scenario containing sentence
	// getting scenario:
	// if(!sentence.getOwningScenarios().isEmpty())
	// sentence.getOwningScenarios().get(0)
	// getting UseCase:
	// sentence.getOwningScenarios().get(0).getParent()
	//
	private boolean validatePostcondition(PostconditionSentenceDTO post) {
		if (post.getSentenceText() != null)
			if (!post.getSentenceText().equals(""))
				return true;
		return false;
	}

}
