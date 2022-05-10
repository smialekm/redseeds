package eu.redseeds.scl.model.impl.rsl.rsldomainelements.phrases;

import java.util.ArrayList;
import java.util.List;

import de.uni_koblenz.jgralab.Graph;
import eu.redseeds.common.Constants;
import eu.redseeds.sc.terminology.model.RemoteJGWNL;
import eu.redseeds.sc.terminology.model.TermSenseDTO;
import eu.redseeds.scl.SCLGraph;
import eu.redseeds.scl.impl.rsl.rsldomainelements.phrases.SimpleVerbPhraseImpl;
import eu.redseeds.scl.model.BusinessLayerFacade;
import eu.redseeds.scl.model.impl.BusinessLayerFacadeImpl;
import eu.redseeds.scl.model.impl.rsl.rsldomainelements.terms.VerbDTOImpl;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.DomainStatementDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.NounPhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.PhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.SimpleVerbPhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.terms.VerbDTO;
import eu.redseeds.scl.rsl.rsldomainelements.phrases.NounPhrase;
import eu.redseeds.scl.rsl.rsldomainelements.phrases.PhraseVerbLink;
import eu.redseeds.scl.rsl.rsldomainelements.phrases.VerbPhrase;
import eu.redseeds.scl.rsl.rsldomainelements.terms.Verb;
import eu.redseeds.scl.rsl.rslrequirementrepresentationsentences.svosentences.SVOSentence;


public class SimpleVerbPhraseDTOImpl extends SimpleVerbPhraseImpl implements SimpleVerbPhraseDTO {

	@Override
	public NounPhraseDTO getObject() {
		if(getFirstVerbPhraseContainsObject()!=null)
			return (NounPhraseDTOImpl)(NounPhrase)getFirstVerbPhraseContainsObject().getOmega();
		return null;
	}	

	@Override
	public VerbDTO getVerb() {
		PhraseVerbLink verbLink;	
		if(getFirstVerbIsPartOfSource()!=null){
			verbLink = (PhraseVerbLink) this.getFirstVerbIsPartOfSource().getAlpha();			
			if(verbLink.getFirstVerbLinkLinksToTarget()!=null){
				return  (VerbDTOImpl)(Verb)verbLink.getFirstVerbLinkLinksToTarget().getOmega();			
			} 			
		}
		return null;
	}

	@Override
	public void setObject(NounPhraseDTO nf) {
		NounPhraseDTOImpl oldobj = (NounPhraseDTOImpl)getObject();
		if (oldobj!=null) //TODO: prevent from deleting nounphrase attached to notion 
			oldobj.delete();			
		addObject((NounPhrase)nf);		
	}

	@Override
	public void setVerb(VerbDTO verb) {
		if (!this.getVerbList().isEmpty()){
			PhraseVerbLink vl = this.getVerbList().get(0);
			this.removeVerb(vl);
			vl.delete();			
		}			
		if (verb != null) {
		PhraseVerbLink verbLink = getSCLGraph().createPhraseVerbLink();
		Verb v = (Verb)verb;
		v.addVerbLink(verbLink);			
		this.addVerb(verbLink);	
		verbLink.setValue(verb.getName());
		}
	}

	public SimpleVerbPhraseDTOImpl(int arg0, Graph arg1) {
		super(arg0, arg1);		
	}
	
	SCLGraph getSCLGraph(){
		return (SCLGraph)getGraph();
	}
	
	@Override
	public String toString() {	
		String phrase = "";
		if(this.getVerb()!=null)
			if(!this.getVerb().equals(""))
					phrase+=this.getVerb()+" ";
		if(this.getObject()!=null)
					phrase+=this.getObject();
		return phrase.trim();
	}
	
	public boolean equals(PhraseDTO phrase){
		boolean nounEquals = false;
		boolean verbEquals = false;
		
		if (phrase instanceof SimpleVerbPhraseDTO){
			NounPhraseDTO thatNoun = ((SimpleVerbPhraseDTO)phrase).getObject();
			VerbDTO thatVerb = ((SimpleVerbPhraseDTO)phrase).getVerb();
			
			NounPhraseDTO thisNoun = this.getObject();
			VerbDTO thisVerb = this.getVerb();
			
			
			if (thatNoun != null && thisNoun != null){				
				nounEquals = false;
				if (thatNoun.equals(thisNoun)){
					nounEquals = true;
				}
			} else return nounEquals; 
			
			
			if (thisVerb != null && thatVerb != null) {
				List<TermSenseDTO> thisSenses = new ArrayList<TermSenseDTO>();
				List<TermSenseDTO> thatSenses = new ArrayList<TermSenseDTO>();
				
				TermSenseDTO thisSense = 
					RemoteJGWNL.getInstance().getTermSenseDTO(thisVerb.getSynonymUid());
				TermSenseDTO thatSense = 
					RemoteJGWNL.getInstance().getTermSenseDTO(thatVerb.getSynonymUid());
				
				if (thisSense == null){
					TermSenseDTO[] senses =  
						RemoteJGWNL.getInstance().getTermSenses(thisVerb.getName(), Constants.TERM_VERB);
					for (TermSenseDTO s : senses){
						thisSenses.add(s);
					}
				} else {
					thisSenses.add(thisSense);
				}
				
				if (thatSense == null){
					TermSenseDTO[] senses =  
						RemoteJGWNL.getInstance().getTermSenses(thatVerb.getName(), Constants.TERM_VERB);
					for (TermSenseDTO s : senses){
						thatSenses.add(s);
					}
				} else {
					thatSenses.add(thatSense);
				}
				
				verbEquals = 0!=thisVerb.getSynonymUid() && 0!=thatVerb.getSynonymUid() && thisVerb.getSynonymUid()==thatVerb.getSynonymUid();;
				for (TermSenseDTO thisS : thisSenses){
					for (TermSenseDTO thatS : thatSenses){	
						if (thisS.getUid() == thatS.getUid()){
							verbEquals = true;
						}
					}
				}
			}			
			
		}
		return verbEquals && nounEquals;
	}
	
	public PhraseDTO copy(boolean basicForm){
		SimpleVerbPhraseDTO phrase = (SimpleVerbPhraseDTOImpl)this.getSCLGraph().createSimpleVerbPhrase();
		if (this.getObject() != null){
			phrase.setObject((NounPhraseDTO)this.getObject().copy(basicForm));
		}
		if (this.getVerb() != null){
			VerbDTO verb = (VerbDTOImpl)this.getSCLGraph().createVerb();
			verb.setName(this.getVerb().getName());
			verb.setSynonymUid(this.getVerb().getSynonymUid());
			if (verb.getSynonymUid() != 0 && basicForm){
				verb.setName(RemoteJGWNL.getInstance().getTermSenseDTO(verb.getSynonymUid()).getLemma());
			}
			phrase.setVerb(verb);
		}
		return phrase;
	}
	
	public void deleteRecursively(){
		if (this.getObject() != null){
			this.getObject().deleteRecursively();
		}
		if (this.getVerb() != null){
			this.getVerb().delete();
		}
		this.delete();
	}
	
	public void connect(){		
		this.getObject().connect();
		
		//do not try to connect if its already connected
		if (this.getVerb() != null)
			if (this.getVerb().getSynonymUid() != 0)
				return;		
		
		
		BusinessLayerFacade facade = (BusinessLayerFacadeImpl)this.getSCLGraph();	
		//preventing from connecting clipboard members
		if (facade.isAnyClipboardMember(this))
			return;
		SimpleVerbPhraseDTO result = facade.findSimpleVerbPhrase((SimpleVerbPhraseDTO)this);		
		if (result!=null){
			SimpleVerbPhraseDTO thatPhrase = result;
			if (this.getVerb() != null && thatPhrase.getVerb() != null)
				this.getVerb().setSynonymUid(thatPhrase.getVerb().getSynonymUid());			
		}
		
		connectToActionStereotype();
	}
	
	public void connectToActionStereotype(){
		BusinessLayerFacade facade = (BusinessLayerFacadeImpl)this.getSCLGraph();
		DomainStatementDTO ds = facade.getDomainStatementByPhrase(this);
		SVOSentence parSent = null;
		VerbPhrase tmp = null;
		if (!this.getComplexVerbPhraseList().isEmpty() && null!=this.getComplexVerbPhraseList().get(0))
			tmp = this.getComplexVerbPhraseList().get(0);
		else tmp = this;
		if (!tmp.getPredicateList().isEmpty() && null!=tmp.getPredicateList().get(0) && !tmp.getPredicateList().get(0).getSourceList().isEmpty() && null!=tmp.getPredicateList().get(0).getSourceList().get(0) && tmp.getPredicateList().get(0).getSourceList().get(0) instanceof SVOSentence)
			parSent = (SVOSentence) tmp.getPredicateList().get(0).getSourceList().get(0);
		if (null!=parSent && null!=ds && null!=ds.getActionTypeStereotype() && !parSent.getStereotypeList().contains(ds.getActionTypeStereotype()))
			parSent.addStereotype(ds.getActionTypeStereotype());
	}
	
	public boolean hasSenses(){
		if (this.getVerb() != null){
			if (this.getVerb().getSynonymUid() == 0)
				return false;
		}
		if (this.getObject() != null){
			if (!this.getObject().hasSenses())
				return false;
		}
		return true;
	}

}