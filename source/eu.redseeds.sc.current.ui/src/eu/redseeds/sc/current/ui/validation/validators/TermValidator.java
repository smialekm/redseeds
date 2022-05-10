package eu.redseeds.sc.current.ui.validation.validators;

import eu.redseeds.common.SCProjectHelper;
import eu.redseeds.sc.current.ui.validation.IValidate;
import eu.redseeds.sc.current.ui.validation.ValidationResult;
import eu.redseeds.sc.terminology.model.RemoteJGWNL;
import eu.redseeds.scl.model.rsl.rsldomainelements.terms.TermDTO;

public class TermValidator implements IValidate {

	@Override
	public int getComplexness() {
		return 1;
	}

	@Override
	public String getLabel() {
		return "Validating terms";
	}

	@Override
	public ValidationResult[] validate(Object element) {
		if (!(element instanceof TermDTO)) {
			ValidationResult vr = new ValidationResult();
			vr.setProblemID(IValidate.ID_WRONG_TYPE);
			vr.setMessage(MSG_WRONG_TYPE);
			vr.setSclElement(element);
			return new ValidationResult[] { vr };
		}
		TermDTO term = (TermDTO)element;
		if (!validateSynonymIdNotZero(term)) {
			//TODO Temporary Fix
			/*if (term.getName().isEmpty()){
				BusinessLayerFacade blf = SCProjectContainer.instance().getSCProject(element).getBusinessLayerFacade();
				for (NounPhrase np:blf.getNounPhraseVertices()) if (((Noun) ((NounPhraseDTO) np).getNoun()).getId()==((Term) term).getId()){
					np.delete();
					term.delete();
					return new ValidationResult[0];
				}
			}*/
			ValidationResult vr = new ValidationResult();
			vr.setProblemID(IValidate.ID_TERM_SYN_ID_EQ_ZERO);
			vr.setMessage(MSG_TERM_SYN_ID_EQ_ZERO);
			vr.setSeverity(IValidate.SEVERITY_ERROR);
			vr.setPath(term.getName());
			vr.setSclElement(element);
			return new ValidationResult[] { vr };
		}
		if (!validateTermInTerminology(term)) {
			ValidationResult vr = new ValidationResult();
			vr.setProblemID(IValidate.ID_TERM_NOT_IN_TERMINOLOGY);
			vr.setMessage(MSG_TERM_NOT_IN_TERMINOLOGY);
			vr.setSeverity(IValidate.SEVERITY_ERROR);
			vr.setPath(term.getName());
			vr.setSclElement(element);
			return new ValidationResult[] { vr };
		}
		if (!validateTermIsNotIsolatedInTerminology(term) && !SCProjectHelper.getSenseAutoAddAndAssigmentFlag()) {
			ValidationResult vr = new ValidationResult();
			vr.setProblemID(IValidate.ID_TERM_ISOLATED_TERMINOLOGY);
			vr.setMessage(MSG_TERM_ISOLATED_IN_TERMINOLOGY);
			vr.setSeverity(IValidate.SEVERITY_WARNING);
			vr.setPath(term.getName());
			vr.setSclElement(element);
			return new ValidationResult[] { vr };
		}
		return new ValidationResult[0];
	}

	@Override
	public ValidationResult[] validateRecursively(Object element) {
		return validate(element);
	}
	
	/**
	 * Validates if term has synonym id not equal zero
	 * </br>severity: high
	 * @param term
	 * @return
	 */
	private boolean validateSynonymIdNotZero(TermDTO term) {
		return (term.getSynonymUid() != 0);
	}
	
	/**
	 * Validates if term exists in the terminology
	 * </br>severity: high
	 * @param term
	 * @return
	 */
	private boolean validateTermInTerminology(TermDTO term) {
		return (RemoteJGWNL.getInstance().getTermSenseDTO(term.getSynonymUid()) != null);
	}
	
	/**
	 * Validates if term exists in the terminology
	 * </br>severity: high
	 * @param term
	 * @return
	 */
	private boolean validateTermIsNotIsolatedInTerminology(TermDTO term) {
		return (RemoteJGWNL.getInstance().isTermIsolated(term.getSynonymUid()) == false);
	}

}
