package eu.redseeds.sc.editor.rsl.actions;


import eu.redseeds.common.SCProjectHelper;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.PhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.terms.TermDTO;

/**
 * Creates an instance of Action class depending on manual or auto assignment style 
 * @return An created instance.
 */
public class ProcessSensesAssignmentActionFactory {
	
	
	public static AbstractProcessSensesAssignmentAction getTermsContentProvider(PhraseDTO phrase) {
		if (SCProjectHelper.getSenseAutoAddAndAssigmentFlag()) 
			return new ProcessSensesAutoAddAndAssignmentAction(phrase);
		else if (SCProjectHelper.getSenseAutoAssigmentFlag())
			return new ProcessSensesAutoAssignmentAction(phrase);
		else 
			return new ProcessSensesManualAssignmentAction(phrase);
	}
	
	public static AbstractProcessSensesAssignmentAction getTermsContentProvider(TermDTO term) {
		if (SCProjectHelper.getSenseAutoAddAndAssigmentFlag()) 
			return new ProcessSensesAutoAddAndAssignmentAction(term);
		else if (SCProjectHelper.getSenseAutoAssigmentFlag())
			return new ProcessSensesAutoAssignmentAction(term);
		else 
			return new ProcessSensesManualAssignmentAction(term);
	}
}
