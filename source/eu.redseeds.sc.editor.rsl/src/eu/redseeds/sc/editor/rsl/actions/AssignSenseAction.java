package eu.redseeds.sc.editor.rsl.actions;

import java.util.List;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPage;

import eu.redseeds.sc.current.repository.SCProject;
import eu.redseeds.sc.current.repository.SCProjectContainer;
import eu.redseeds.sc.editor.rsl.editors.domain.NounControl;
import eu.redseeds.sc.editor.rsl.editors.domain.NounPhraseControl;
import eu.redseeds.sc.editor.rsl.editors.domain.VerbPhraseControl;
import eu.redseeds.sc.terminology.model.TermSenseDTO;
import eu.redseeds.scl.impl.rsl.rsldomainelements.terms.NounImpl;
import eu.redseeds.scl.model.BusinessLayerFacade;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.ComplexVerbPhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.NounPhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.PhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.SimpleVerbPhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.terms.NounDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.terms.TermDTO;

public class AssignSenseAction extends Action {
	IWorkbenchPage activePage = null;	
	private SCProject scProject;
	private BusinessLayerFacade facade;
	private NounDTO directNoun, indirectNoun, directNounWN, indirectNounWN;
	private NounPhraseDTO directNounPhrase, indirectNounPhrase, directNounPhraseWN, indirectNounPhraseWN;
	private SimpleVerbPhraseDTO simpleVerbPhrase, simpleVerbPhraseWN;
	private ComplexVerbPhraseDTO complexVerbPhrase, complexVerbPhraseWN;
	private PhraseDTO oldPhrase, phrase;
	private TermDTO term, prepositionTerm;
	private TermSenseDTO termSense;
	private Composite parent;
	
	
	
	public AssignSenseAction(TermDTO term, TermSenseDTO termSense, PhraseDTO phrase, Composite parent){
		this.term = term;
		this.termSense = termSense;
		this.phrase = phrase;
		this.parent = parent;
	}
	
	@Override
	public void run() {
		if (scProject == null) {
			scProject = SCProjectContainer.instance().getSCProject(term);
			this.facade = scProject.getBusinessLayerFacade();
		}

//		
//		if (parent instanceof VerbPhraseControl || parent instanceof NounPhraseControl)
//			oldPhrase = phrase;
//		else
		if (phrase != null)
			oldPhrase = phrase.copy(false); 
		term.setSynonymUid(termSense.getUid());
		if (term instanceof NounDTO){
			term.setName(termSense.getLemma());
		}
		
		if (phrase != null) {
			updatePhrases();
		//if (!(parent instanceof VerbPhraseControl) && !(parent instanceof NounPhraseControl))
			oldPhrase.deleteRecursively();
		}
		
		if (term instanceof NounDTO && (parent instanceof NounControl || parent instanceof NounPhraseControl || parent instanceof VerbPhraseControl)) ((BusinessLayerFacade)((NounImpl)term).getGraph()).cleanNouns((NounDTO) term);
		
		scProject.save();
	}
	
	
private void updatePhrases(){
		
		if (phrase instanceof NounPhraseDTO){
			directNounPhrase = (NounPhraseDTO)oldPhrase;
			directNoun = directNounPhrase.getNoun();
			directNounPhraseWN = (NounPhraseDTO)phrase;
			directNounWN = directNounPhraseWN.getNoun();
			updateNounPhrases();
		}
		
		if (phrase instanceof SimpleVerbPhraseDTO){
			simpleVerbPhrase = (SimpleVerbPhraseDTO)oldPhrase;
			directNounPhrase = simpleVerbPhrase.getObject();
			directNoun = directNounPhrase.getNoun();
			simpleVerbPhraseWN = (SimpleVerbPhraseDTO)phrase;
			directNounPhraseWN = simpleVerbPhraseWN.getObject();
			directNounWN = directNounPhraseWN.getNoun();
			 
			updateNounPhrases();
			updateSimpleVerbPhrases();
		}
		
		if (phrase instanceof ComplexVerbPhraseDTO){
			complexVerbPhrase = (ComplexVerbPhraseDTO)oldPhrase;
			simpleVerbPhrase = complexVerbPhrase.getSimpleVerbPhrase();
			directNounPhrase = simpleVerbPhrase.getObject();
			indirectNounPhrase = complexVerbPhrase.getObject();
			directNoun = directNounPhrase.getNoun();
			indirectNoun = indirectNounPhrase.getNoun();
			
			complexVerbPhraseWN = (ComplexVerbPhraseDTO)phrase;
			simpleVerbPhraseWN = complexVerbPhraseWN.getSimpleVerbPhrase();
			directNounPhraseWN = simpleVerbPhraseWN.getObject();
			indirectNounPhraseWN = complexVerbPhraseWN.getObject();
			directNounWN = directNounPhraseWN.getNoun();
			indirectNounWN = indirectNounPhraseWN.getNoun();
			prepositionTerm = complexVerbPhraseWN.getPreposition();
			
			updateNounPhrases();
			updateSimpleVerbPhrases();
			updateComplexVerbPhrases();
		}
	}
	
	
	private void updateNounPhrases(){
		List<NounDTO> nouns;
		List<NounPhraseDTO> nounPhrases;
		nouns = facade.findNouns(directNoun);
		nounPhrases = facade.findNounPhrases(directNounPhrase);
		for (NounDTO n : nouns){
			n.setSynonymUid(directNounWN.getSynonymUid());
			n.setName(directNounWN.getName());
		}
		for (NounPhraseDTO np : nounPhrases){
			if (np.getModifier() != null)
				np.getModifier().setSynonymUid(directNounPhraseWN.getModifier().getSynonymUid());
			if (np.getDeterminer() != null)
				np.getDeterminer().setSynonymUid(directNounPhraseWN.getDeterminer().getSynonymUid());
			if (np.getNoun() != null){
				np.getNoun().setSynonymUid(directNounPhraseWN.getNoun().getSynonymUid());
			}
		}
	}
	
	private void updateSimpleVerbPhrases(){
		List<SimpleVerbPhraseDTO> simpleVerbPhrases;
		simpleVerbPhrases = facade.findSimpleVerbPhrases(simpleVerbPhrase);
		for (SimpleVerbPhraseDTO svp : simpleVerbPhrases){
			if (svp.getVerb() != null)
				svp.getVerb().setSynonymUid(simpleVerbPhraseWN.getVerb().getSynonymUid());
		}
		
	}
	
	private void updateComplexVerbPhrases(){
		List<NounDTO> nouns;
		List<NounPhraseDTO> nounPhrases;
		List<ComplexVerbPhraseDTO> cvPhrases;
		nouns = facade.findNouns(indirectNoun);
		nounPhrases = facade.findNounPhrases(indirectNounPhrase);
		for (NounDTO n : nouns){
			n.setSynonymUid(indirectNounWN.getSynonymUid());
		}
		for (NounPhraseDTO np : nounPhrases){
			if (np.getModifier() != null)
				np.getModifier().setSynonymUid(indirectNounPhraseWN.getModifier().getSynonymUid());
			if (np.getDeterminer() != null)
				np.getDeterminer().setSynonymUid(indirectNounPhraseWN.getDeterminer().getSynonymUid());
			if (np.getNoun() != null){
				np.getNoun().setSynonymUid(indirectNounPhraseWN.getNoun().getSynonymUid());
			}
		}
		cvPhrases = facade.findComplexVerbPhrases(complexVerbPhrase);
		for (ComplexVerbPhraseDTO cvp : cvPhrases){
			if (prepositionTerm != null && cvp.getPreposition() != null){
				cvp.getPreposition().setSynonymUid(prepositionTerm.getSynonymUid());
			}
		}
		
	}



	public TermDTO getTerm() {
		return term;
	}



	public void setTerm(TermDTO term) {
		this.term = term;
	}

	public PhraseDTO getPhrase() {
		return phrase;
	}

	public void setPhrase(PhraseDTO phrase) {
		this.phrase = phrase;
	}

	public TermSenseDTO getTermSense() {
		return termSense;
	}

	public void setTermSense(TermSenseDTO termSense) {
		this.termSense = termSense;
	}

	public Composite getParent() {
		return parent;
	}

	public void setParent(Composite parent) {
		this.parent = parent;
	}

}
