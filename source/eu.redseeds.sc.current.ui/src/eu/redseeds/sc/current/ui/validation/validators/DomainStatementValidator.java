package eu.redseeds.sc.current.ui.validation.validators;

import eu.redseeds.sc.current.ui.validation.IValidate;
import eu.redseeds.sc.current.ui.validation.ValidationResult;
import eu.redseeds.sc.terminology.model.RemoteJGWNL;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.DomainStatementDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.ComplexVerbPhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.NounPhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.SimpleVerbPhraseDTO;

public class DomainStatementValidator implements IValidate {

	@Override
	public int getComplexness() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return "Validating domain statement";
	}

	@Override
	public ValidationResult[] validate(Object element) {
		if (!(element instanceof DomainStatementDTO)) {
			ValidationResult vr = new ValidationResult();
			vr.setProblemID(IValidate.ID_WRONG_TYPE);
			vr.setMessage(MSG_WRONG_TYPE);
			vr.setSclElement(element);
			return new ValidationResult[] { vr };
		}
		DomainStatementDTO statement = (DomainStatementDTO)element;
		if(!validateIfTerminologyLinksExistInDomainStatement(statement)) {
			ValidationResult vr = new ValidationResult();
			vr.setProblemID(IValidate.ID_DOM_STAT_NOT_LINKED);
			vr.setMessage(MSG_DOM_STAT_NOT_LINKED);
			vr.setSeverity(IValidate.SEVERITY_ERROR);
			vr.setPath(statement.getSpecificationPath());
			vr.setSclElement(statement);
			return new ValidationResult[] { vr };
		}
		return new ValidationResult[0];
	}

	@Override
	public ValidationResult[] validateRecursively(Object element) {
		return validate(element);
	}
	
	/**
	 * Validates if all domain statement in a given notion have phrases 
	 * properly linked to terminology elements
	 * </br>severity: high
	 * @param notion
	 * @return
	 */
	private boolean validateIfTerminologyLinksExistInDomainStatement(DomainStatementDTO statement) {
		if (statement.getPhraseDTO() instanceof NounPhraseDTO) {
			return validateNounPhrase((NounPhraseDTO)statement.getPhraseDTO());
		}
		if (statement.getPhraseDTO() instanceof SimpleVerbPhraseDTO) {
			return validateSimpleVerbPhrase((SimpleVerbPhraseDTO)statement.getPhraseDTO());
		}
		if (statement.getPhraseDTO() instanceof ComplexVerbPhraseDTO) {
			return validateComplexVerbPhrase((ComplexVerbPhraseDTO)statement.getPhraseDTO());
		}
		return false;
	}
	
	private boolean validateSimpleVerbPhrase(SimpleVerbPhraseDTO svPhrase) {
		if(validateNounPhrase(svPhrase.getObject())) {
			if(svPhrase.getVerb().getSynonymUid() == 0) {
				return false;
			}
			if (RemoteJGWNL.getInstance().getTermSenseDTO(svPhrase.getVerb().getSynonymUid()) == null){
				return false;
			}
			return true;
		}
		else {
			return false;
		}
	}
	
	private boolean validateComplexVerbPhrase(ComplexVerbPhraseDTO cvPhrase) {
		if(validateNounPhrase(cvPhrase.getObject())) {
			if(validateSimpleVerbPhrase(cvPhrase.getSimpleVerbPhrase())) {
				//TODO consider prepositions
				return true;
			}
		}
		return false;
	}

	private boolean validateNounPhrase(NounPhraseDTO nounPh) {
		if(nounPh == null) {
			return false;
		}
		if (nounPh.getNoun() != null) {
			if (nounPh.getNoun().getSynonymUid() == 0) {
				return false;
			}
			if (RemoteJGWNL.getInstance().getTermSenseDTO(nounPh.getNoun().getSynonymUid()) == null) {
				return false;
			}
		} else {
			return false;
		}
		if(nounPh.getModifier() != null) {
			if(nounPh.getModifier().getSynonymUid() == 0) {
				return false;
			}
			if (RemoteJGWNL.getInstance().getTermSenseDTO(nounPh.getModifier().getSynonymUid()) == null) {
				return false;
			}
		}
		if(nounPh.getDeterminer() != null) {
			if(nounPh.getDeterminer().getSynonymUid() == 0) {
				return false;
			}
			if (RemoteJGWNL.getInstance().getTermSenseDTO(nounPh.getDeterminer().getSynonymUid()) == null) {
				return false;
			}
		}
		return true;
	}

}
