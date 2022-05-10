package eu.redseeds.sc.editor.rsl.actions;

import java.util.ArrayList;
import java.util.List;

import eu.redseeds.sc.terminology.model.RemoteJGWNL;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.ComplexVerbPhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.NounPhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.PhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.SimpleVerbPhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.terms.TermDTO;

public abstract class AbstractProcessSensesAssignmentAction {

	protected PhraseDTO phrase;
	protected TermDTO term;
	protected boolean result = false;
	
	public AbstractProcessSensesAssignmentAction(PhraseDTO phrase){
		this.phrase = phrase;
	}
	
	
	public AbstractProcessSensesAssignmentAction(TermDTO term){
		this.term = term;
	}
	
	
	public abstract boolean validatePhrase();
	
	public abstract boolean validateTerm();
	
	protected List<TermDTO> checkNounPhrase(NounPhraseDTO np){
		List<TermDTO> terms =  new ArrayList<TermDTO>();
		if (np.getNoun() != null){
			if (np.getNoun().getSynonymUid() == 0 || null==RemoteJGWNL.getInstance().getTermSenseDTO(np.getNoun().getSynonymUid())){
				terms.add(np.getNoun());
			}
		}
		if (np.getModifier() != null){
			if (np.getModifier().getSynonymUid() == 0 || null==RemoteJGWNL.getInstance().getTermSenseDTO(np.getModifier().getSynonymUid())){
				terms.add(np.getModifier());
			}
		}
		if (np.getDeterminer() != null){
			if (np.getDeterminer().getSynonymUid() == 0 || null==RemoteJGWNL.getInstance().getTermSenseDTO(np.getDeterminer().getSynonymUid())){
				terms.add(np.getDeterminer());
			}
		}
		return terms;
	}
	
	protected List<TermDTO> checkSimpleVerbPhrase(SimpleVerbPhraseDTO svp){
		List<TermDTO> terms =  new ArrayList<TermDTO>();
		if (svp.getVerb() != null){
			if (svp.getVerb().getSynonymUid() == 0 || null==RemoteJGWNL.getInstance().getTermSenseDTO(svp.getVerb().getSynonymUid())){
				terms.add(svp.getVerb());
			}
		}
		if (svp.getObject() != null){
			List<TermDTO> tmpTerms=checkNounPhrase(svp.getObject());
			terms.addAll(tmpTerms);
		}
		return terms;
	}
	
	protected List<TermDTO> checkComplexVerbPhrase(ComplexVerbPhraseDTO cvp){
		List<TermDTO> terms =  new ArrayList<TermDTO>();
		if (cvp.getObject() != null){
			List<TermDTO> tmpTerms = checkNounPhrase(cvp.getObject()); 
			terms.addAll(tmpTerms);
		}
		
		if (cvp.getPreposition() != null){
			if (cvp.getPreposition().getSynonymUid() == 0 || null==RemoteJGWNL.getInstance().getTermSenseDTO(cvp.getPreposition().getSynonymUid())){
				terms.add(cvp.getPreposition());
			}
		}
		
		if (cvp.getSimpleVerbPhrase() != null){
			List<TermDTO> tmpTerms = checkSimpleVerbPhrase(cvp.getSimpleVerbPhrase()); 
			terms.addAll(tmpTerms);
		}
		return terms;
	}
	
	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public PhraseDTO getPhrase() {
		return phrase;
	}

	public void setPhrase(PhraseDTO phrase) {
		this.phrase = phrase;
	}

	public TermDTO getTerm() {
		return term;
	}

	public void setTerm(TermDTO term) {
		this.term = term;
	}
	
	protected void showMessageBox(List<TermDTO> terms){
		showMessageBox(!terms.isEmpty()?terms.get(0):null);
	}
	
	protected abstract void showMessageBox(TermDTO term);
	
}
