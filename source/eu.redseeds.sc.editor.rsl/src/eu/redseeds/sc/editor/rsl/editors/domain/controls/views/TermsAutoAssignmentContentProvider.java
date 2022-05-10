package eu.redseeds.sc.editor.rsl.editors.domain.controls.views;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

import eu.redseeds.sc.terminology.model.RemoteJGWNL;
import eu.redseeds.sc.terminology.model.TermSenseDTO;
import eu.redseeds.common.Constants;
import eu.redseeds.scl.impl.rsl.rsldomainelements.terms.NounImpl;
import eu.redseeds.scl.model.BusinessLayerFacade;
import eu.redseeds.scl.model.rsl.rsldomainelements.terms.DeterminerDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.terms.ModifierDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.terms.NounDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.terms.PrepositionDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.terms.VerbDTO;


public class TermsAutoAssignmentContentProvider implements IStructuredContentProvider {

	@Override
	public Object[] getElements(Object inputElement) {
		if(inputElement instanceof NounDTO) {
			NounDTO term = (NounDTO) inputElement;
			TermSenseDTO[] senses = RemoteJGWNL.getInstance().getTermSenses(term.toString().toLowerCase(), Constants.TERM_NOUN);
			if (senses.length > 0 && term.getSynonymUid()==0){
				term.setSynonymUid(senses[0].getUid());
				term.setName(senses[0].getLemma());
				((BusinessLayerFacade)((NounImpl)term).getGraph()).cleanNouns(term);
			}
			return senses;
		}
		if(inputElement instanceof VerbDTO) {
			VerbDTO term = (VerbDTO) inputElement;
			TermSenseDTO[] senses = RemoteJGWNL.getInstance().getTermSenses(term.toString().toLowerCase(), Constants.TERM_VERB);
			if (senses.length > 0 && term.getSynonymUid()==0)
				term.setSynonymUid(senses[0].getUid());
			return senses;
		}
		if(inputElement instanceof PrepositionDTO) {
			PrepositionDTO term = (PrepositionDTO) inputElement;
			TermSenseDTO[] senses = RemoteJGWNL.getInstance().getTermSenses(term.toString().toLowerCase(), Constants.TERM_PREPOSITION);
			if (senses.length > 0 && term.getSynonymUid()==0)
				term.setSynonymUid(senses[0].getUid());
			return senses;
		}
		if(inputElement instanceof DeterminerDTO) {
			DeterminerDTO term = (DeterminerDTO) inputElement;
			TermSenseDTO[] senses = RemoteJGWNL.getInstance().getTermSenses(term.toString().toLowerCase(), Constants.TERM_DETERMINER);
			if (senses.length > 0 && term.getSynonymUid()==0)
				term.setSynonymUid(senses[0].getUid());
			return senses;
		}
		if(inputElement instanceof ModifierDTO) {
			ModifierDTO term = (ModifierDTO) inputElement;
			TermSenseDTO[] senses = RemoteJGWNL.getInstance().getTermSenses(term.toString().toLowerCase(), Constants.TERM_MODIFIER
					);
			if (senses.length > 0 && term.getSynonymUid()==0)
				term.setSynonymUid(senses[0].getUid());
			return senses;
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
