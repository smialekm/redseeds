package eu.redseeds.sc.editor.rsl.actions;


import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;

import eu.redseeds.common.SCProjectHelper;
import eu.redseeds.sc.terminology.model.RemoteJGWNL;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.ComplexVerbPhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.NounPhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.PhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.SimpleVerbPhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.terms.DeterminerDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.terms.ModifierDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.terms.NounDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.terms.TermDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.terms.VerbDTO;

public class ProcessSensesManualAssignmentAction extends AbstractProcessSensesAssignmentAction {
	
	public ProcessSensesManualAssignmentAction(PhraseDTO phrase) {
		super(phrase);
	}
	
	public ProcessSensesManualAssignmentAction(TermDTO term) {
		super(term);
	}

	private static String NOUN = "noun";
	private static String VERB = "verb";
	private static String MODIFIER = "modifier";
	private static String DETERMINER = "determiner";
	



	@Override
	public boolean validateTerm(){
		if (term == null){
			return result;
		} else {
			result = true;
			if (term.getSynonymUid() == 0 || null==RemoteJGWNL.getInstance().getTermSenseDTO(term.getSynonymUid())){
				showMessageBox(term);
				result = false;
			}
			
		}
		return result;
	}
	
	@Override
	public boolean validatePhrase(){
		
		if (phrase == null){
			return result;
		} else {
			result = true;
			if (phrase instanceof NounPhraseDTO){
				List<TermDTO> tmpTerms = checkNounPhrase((NounPhraseDTO)phrase);
				if (!tmpTerms.isEmpty()){
					result = false;
					showMessageBox(tmpTerms);
				}
			}
			if (phrase instanceof SimpleVerbPhraseDTO){
				List<TermDTO> tmpTerms = checkSimpleVerbPhrase((SimpleVerbPhraseDTO)phrase);
				if (!tmpTerms.isEmpty()){
					result = false;
					showMessageBox(tmpTerms);
				}
			}
			if (phrase instanceof ComplexVerbPhraseDTO){
				List<TermDTO> tmpTerms = checkComplexVerbPhrase((ComplexVerbPhraseDTO)phrase);
				if (!tmpTerms.isEmpty()){
					result = false;
					showMessageBox(tmpTerms);
				}
			}
		}
		return result;
	}





	protected void showMessageBox(TermDTO term){
		String text = "Please assign sense to ";
		
		if (term instanceof NounDTO){
			text += NOUN + "'"+term.toString()+"'.";
		}
		
		if (term instanceof VerbDTO){
			text += VERB + "'"+term.toString()+"'.";
		}
		
		if (term instanceof ModifierDTO){
			text += MODIFIER + "'"+term.toString()+"'.";
		}
		
		if (term instanceof DeterminerDTO){
			text += DETERMINER + "'"+term.toString()+"'.";
		}
		
		MessageBox errorMB = new MessageBox(SCProjectHelper.getShell(), 
				SWT.ICON_ERROR  | SWT.OK);

		errorMB.setMessage(text);
		errorMB.setText("Error");
		int resultMB = errorMB.open();
		
		if(resultMB == SWT.OK) {
			return; //do nothing
		}
	}

}
