package eu.redseeds.scl.model.impl.rsl.rslrequirementrepresentationsentences.svosentences;

import java.util.ArrayList;
import java.util.List;

import de.uni_koblenz.jgralab.Graph;
import eu.redseeds.scl.SCLGraph;
import eu.redseeds.scl.impl.rsl.rslrequirementrepresentationsentences.svosentences.SVOSentenceImpl;
import eu.redseeds.scl.model.impl.rsl.rsldomainelements.phrases.ComplexVerbPhraseDTOImpl;
import eu.redseeds.scl.model.impl.rsl.rsldomainelements.phrases.NounPhraseDTOImpl;
import eu.redseeds.scl.model.impl.rsl.rsldomainelements.phrases.SimpleVerbPhraseDTOImpl;
import eu.redseeds.scl.model.rsl.rsldomainelements.domainelements.ActorOrSystemElementDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.ComplexVerbPhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.NounPhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.SimpleVerbPhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.VerbPhraseDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentations.descriptiverequirementrepresentations.ConstrainedLanguageScenarioDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.svosentences.SVOSentenceDTO;
import eu.redseeds.scl.rsl.rsldomainelements.domainelements.ActorOrSystemElement;
import eu.redseeds.scl.rsl.rsldomainelements.phrases.ComplexVerbPhrase;
import eu.redseeds.scl.rsl.rsldomainelements.phrases.NounPhrase;
import eu.redseeds.scl.rsl.rsldomainelements.phrases.SimpleVerbPhrase;
import eu.redseeds.scl.rsl.rsldomainelements.phrases.VerbPhrase;
import eu.redseeds.scl.rsl.rslrequirementrepresentations.descriptiverequirementrepresentations.ConstrainedLanguageScenario;
import eu.redseeds.scl.rsl.rslrequirementrepresentationsentences.svosentences.Predicate;
import eu.redseeds.scl.rsl.rslrequirementrepresentationsentences.svosentences.Subject;

public class SVOSentenceDTOImpl extends SVOSentenceImpl implements
		SVOSentenceDTO {

	@Override
	public String getSentenceText() {		
		return super.getSentenceText()==null?"":super.getSentenceText();
	}

	@Override
	public ActorOrSystemElementDTO getRecipient() {
		if (!this.getRecipientList().isEmpty())
			return (ActorOrSystemElementDTO) this.getRecipientList().get(0);
		return null;
	}

	@Override
	public void setRecipient(ActorOrSystemElementDTO recipient) {
		if (!this.getRecipientList().isEmpty())
			this.removeRecipient(this.getRecipientList().get(0));
		if (recipient != null)
			this.addRecipient((ActorOrSystemElement) recipient);

	}

	@Override
	public List<ConstrainedLanguageScenarioDTO> getOwningScenarios() {
		List<? extends ConstrainedLanguageScenario> l = super.getScenarioList();
		List<ConstrainedLanguageScenarioDTO> result = new ArrayList<ConstrainedLanguageScenarioDTO>();
		for (ConstrainedLanguageScenario s : l) {
			if (s instanceof ConstrainedLanguageScenarioDTO) {
				result.add((ConstrainedLanguageScenarioDTO) s);
			}
		}
		return result;
	}

	public SVOSentenceDTOImpl(int arg0, Graph arg1) {
		super(arg0, arg1);
	}

	@Override
	public VerbPhraseDTO getPredicate() {
/*
		VerbPhraseDTO vf = getRealPredicate();
		if (vf == null) {
			vf = (SimpleVerbPhraseDTOImpl) getSCLGraph()
					.createSimpleVerbPhrase();
			setPredicate(vf);
		}
		return vf;*/
		return getRealPredicate();
	}

	private VerbPhraseDTO getRealPredicate() {
		if (getFirstPredicateIsPartOfSource(/* EdgeDirection.IN */) != null) {
			Predicate pre = (Predicate) this
					.getFirstPredicateIsPartOfSource(/* EdgeDirection.IN */)
					.getAlpha();
			if (pre.getFirstPredicateContainsTarget(/* EdgeDirection.OUT */) != null) {
				VerbPhrase verb = (VerbPhrase) pre
						.getFirstPredicateContainsTarget(/* EdgeDirection.OUT */)
						.getOmega();
				if (verb instanceof SimpleVerbPhrase)
					return (SimpleVerbPhraseDTOImpl) verb;
				else if (verb instanceof ComplexVerbPhrase)
					return (ComplexVerbPhraseDTOImpl) verb;
			}
		}
		return null;
	}

	@Override
	public NounPhraseDTO getSubject() {
/*		NounPhraseDTO nf = getRealSubject();
		if (nf == null) {
			nf = (NounPhraseDTOImpl) getSCLGraph().createNounPhrase();
			setSubject(nf);
		}
		return nf;*/
		return getRealSubject();
	}

	private NounPhraseDTO getRealSubject() {
		if (getFirstSubjectIsPartOfSource() != null) {
			Subject subj = (Subject) this.getFirstSubjectIsPartOfSource()
					.getAlpha();
			if (subj.getFirstSubjectContainsTarget() != null) {
				return (NounPhraseDTOImpl) (NounPhrase) subj
						.getFirstSubjectContainsTarget().getOmega();
			}
		}
		return null;
	}

	@Override
	public void setPredicate(VerbPhraseDTO verb) {
		VerbPhraseDTO oldvf = getRealPredicate();
		
		if(verb==null){
			Predicate oldpre = null;
			if (this.getFirstPredicateIsPartOfSource() != null)
				oldpre = (Predicate) this.getFirstPredicateIsPartOfSource()
						.getAlpha();
			
			if (oldvf != null) {
				// TODO: prevent form destroying verbPhrase attached to Notion
				oldvf.deleteRecursively();
				// in order to delete Preposition
				//((SCLElement) oldvf).delete();
			}
			
			if (oldpre != null)
				oldpre.delete();
			return;
			
		}		
		
		Predicate pre = getSCLGraph().createPredicate();
		SimpleVerbPhrase simpl;
		ComplexVerbPhrase compl;
		

		if (verb instanceof SimpleVerbPhrase) {
			simpl = (SimpleVerbPhrase) verb;
			simpl.addPredicate(pre);
		} else if (verb instanceof ComplexVerbPhrase) {
			compl = (ComplexVerbPhrase) verb;
			if (oldvf != null)
				if (oldvf instanceof SimpleVerbPhrase) {
					//compl.addSimpleVerbPhrase((SimpleVerbPhrase) oldvf);
				((ComplexVerbPhraseDTO)compl).setSimpleVerbPhrase((SimpleVerbPhraseDTO)oldvf);
					
					oldvf = null;
				}
			compl.addPredicate(pre);
		}

		Predicate oldpre = null;
		if (this.getFirstPredicateIsPartOfSource() != null)
			oldpre = (Predicate) this.getFirstPredicateIsPartOfSource()
					.getAlpha();
		
		if (oldvf != null) {
			// TODO: prevent form destroying verbPhrase attached to Notion
			oldvf.deleteRecursively();
		}
		
		if (oldpre != null)
			oldpre.delete();


		this.addPredicate(pre);
	}

	@Override
	public void setSubject(NounPhraseDTO noun) {
		Subject sub = getSCLGraph().createSubject();

		NounPhraseDTO oldnf = getRealSubject();
		if (oldnf != null) {
			// TODO: prevent form destroying nounPhrase attached to Notion
			((NounPhraseDTOImpl) oldnf).delete();
			Subject oldsub = (Subject) this.getFirstSubjectIsPartOfSource()
					.getAlpha();
			oldsub.delete();
			
		}
		NounPhrase n = (NounPhrase) noun;
		n.addSubject(sub);
		this.addSubject(sub);
	}

	private SCLGraph getSCLGraph() {
		return (SCLGraph) this.getGraph();
	}
	
	@Override
	public String getFullSentenceText() {		
		if((getSubject()!=null?getSubject().toString():"").equalsIgnoreCase("") 
				&& (getPredicate()!=null?getPredicate().toString():"").equalsIgnoreCase("")) {
			return (getSentenceText()).trim();
		}
		
		
		if(!(getSubject()!=null?getSubject().toString():"").equalsIgnoreCase("") 
				&& (getPredicate()!=null?getPredicate().toString():"").equalsIgnoreCase("")) {
			return (getSubject() + " " + getSentenceText()).trim();
		}
		
		else if(!(getPredicate()!=null?getPredicate().toString():"").equalsIgnoreCase("") 
				&& (getSubject()!=null?getSubject().toString():"").equalsIgnoreCase("")) {
			return ((getPredicate()!=null?getPredicate().toString():"") + " " + getSentenceText()).trim();
		}
		else {
			return ((getSubject()!=null?getSubject().toString():"") + " " 
					+ (getPredicate()!=null?getPredicate().toString():"")).trim();
		}
	}
	
	@Override
	public String toString() {
		return getFullSentenceText();
	}
	
	public SVOSentenceDTO copy(){
		SVOSentenceDTO sentence = (SVOSentenceDTOImpl) this.getSCLGraph()
		.createSVOSentence();
		if(this.getSubject()!=null)
			sentence.setSubject((NounPhraseDTO)this.getSubject().copy(false));
		if(this.getPredicate()!=null)
			sentence.setPredicate((VerbPhraseDTO)this.getPredicate().copy(false));
		if(this.getSentenceText()!=null)
			sentence.setSentenceText(this.getSentenceText());
		if(null!=getRecipient())
			sentence.setRecipient(getRecipient());
		return sentence;
	}
	
	@Override
	public void delete(){		
		if(this.getSubject()!=null)
			this.getSubject().deleteRecursively();
		if(this.getPredicate()!=null)
			this.getPredicate().deleteRecursively();
		
		super.delete();
	}

}