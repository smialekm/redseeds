package eu.redseeds.sc.editor.rsl.editors.domain.controls.views;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

import eu.redseeds.sc.terminology.model.RemoteJGWNL;
import eu.redseeds.common.Constants;
import eu.redseeds.scl.model.rsl.rsldomainelements.terms.DeterminerDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.terms.ModifierDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.terms.NounDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.terms.PrepositionDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.terms.VerbDTO;


public class TermsManualAssignmentContentProvider implements IStructuredContentProvider {

	@Override
	public Object[] getElements(Object inputElement) {
		if(inputElement instanceof NounDTO) {
			NounDTO term = (NounDTO) inputElement;
			return RemoteJGWNL.getInstance().getTermSenses(term.toString().toLowerCase(), Constants.TERM_NOUN);
		}
		if(inputElement instanceof VerbDTO) {
			VerbDTO term = (VerbDTO) inputElement;
			return RemoteJGWNL.getInstance().getTermSenses(term.toString().toLowerCase(), Constants.TERM_VERB);
		}
		if(inputElement instanceof PrepositionDTO) {
			PrepositionDTO term = (PrepositionDTO) inputElement;
			return RemoteJGWNL.getInstance().getTermSenses(term.toString().toLowerCase(), Constants.TERM_PREPOSITION);
		}
		if(inputElement instanceof DeterminerDTO) {
			DeterminerDTO term = (DeterminerDTO) inputElement;
			return RemoteJGWNL.getInstance().getTermSenses(term.toString().toLowerCase(), Constants.TERM_DETERMINER);
		}
		if(inputElement instanceof ModifierDTO) {
			ModifierDTO term = (ModifierDTO) inputElement;
			return RemoteJGWNL.getInstance().getTermSenses(term.toString().toLowerCase(), Constants.TERM_MODIFIER);
		}
		return new Object[0];
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		// TODO Auto-generated method stub
		
	}

}
