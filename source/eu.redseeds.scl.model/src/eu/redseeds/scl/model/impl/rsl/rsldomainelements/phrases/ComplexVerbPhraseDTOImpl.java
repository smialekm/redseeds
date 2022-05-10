package eu.redseeds.scl.model.impl.rsl.rsldomainelements.phrases;


import de.uni_koblenz.jgralab.Graph;
import eu.redseeds.sc.terminology.model.RemoteJGWNL;
import eu.redseeds.scl.SCLGraph;
import eu.redseeds.scl.impl.rsl.rsldomainelements.phrases.ComplexVerbPhraseImpl;
import eu.redseeds.scl.model.BusinessLayerFacade;
import eu.redseeds.scl.model.impl.BusinessLayerFacadeImpl;
import eu.redseeds.scl.model.impl.rsl.rsldomainelements.terms.PrepositionDTOImpl;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.ComplexVerbPhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.NounPhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.PhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.SimpleVerbPhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.terms.PrepositionDTO;
import eu.redseeds.scl.rsl.rsldomainelements.phrases.NounPhrase;
import eu.redseeds.scl.rsl.rsldomainelements.phrases.PhrasePrepositionLink;
import eu.redseeds.scl.rsl.rsldomainelements.phrases.SimpleVerbPhrase;
import eu.redseeds.scl.rsl.rsldomainelements.terms.Preposition;

public class ComplexVerbPhraseDTOImpl extends ComplexVerbPhraseImpl implements
		ComplexVerbPhraseDTO {

	public ComplexVerbPhraseDTOImpl(int arg0, Graph arg1) {
		super(arg0, arg1);
	}

	@Override
	public NounPhraseDTO getObject() {
		if (getFirstVerbPhraseContainsObject() != null)
			return (NounPhraseDTOImpl) (NounPhrase) getFirstVerbPhraseContainsObject()
					.getOmega();
		return null;
	}

	@Override
	public PrepositionDTO getPreposition() {
		PhrasePrepositionLink prepLink;
		if (getFirstPrepositionIsPartOfSource() != null) {
			prepLink = (PhrasePrepositionLink) this
					.getFirstPrepositionIsPartOfSource().getAlpha();
			if (prepLink.getFirstPrepositionLinkLinksToTarget() != null) {
				return (PrepositionDTOImpl) (Preposition) prepLink
						.getFirstPrepositionLinkLinksToTarget().getOmega();
			}
		}
		return null;
	}

	@Override
	public SimpleVerbPhraseDTO getSimpleVerbPhrase() {
		if (getFirstComplexVerbPhraseContainsSimpleVerbPhrase() != null)
			return (SimpleVerbPhraseDTOImpl) (SimpleVerbPhrase) getFirstComplexVerbPhraseContainsSimpleVerbPhrase()
					.getOmega();
		return null;
	}

	@Override
	public void setObject(NounPhraseDTO nf) {
		NounPhraseDTO oldobj =  getObject();
		if (oldobj != null) // TODO: prevent from deleting nounphrase attached
							// to notion
			oldobj.deleteRecursively();
		addObject((NounPhrase) nf);

	}

	@Override
	public void setPreposition(PrepositionDTO prep) {
		if (!this.getPrepositionList().isEmpty()){
			PhrasePrepositionLink pl = this.getPrepositionList().get(0);
			this.removePreposition(pl);
			pl.delete();			
		}			
		if (prep != null) {
			PhrasePrepositionLink prepLink = getSCLGraph()
					.createPhrasePrepositionLink();
			Preposition p = (Preposition) prep;
			p.addPrepositionLink(prepLink);
			this.addPreposition(prepLink);
			prepLink.setValue(prep.getName());
		}

	}

	@Override
	public void setSimpleVerbPhrase(SimpleVerbPhraseDTO simplvf) {
		SimpleVerbPhraseDTO oldsvf = getSimpleVerbPhrase();

		if (oldsvf == null) {
			addSimpleVerbPhrase((SimpleVerbPhrase) simplvf);
		} else {
			if (!oldsvf.equals((Object)simplvf)) {
				this.removeSimpleVerbPhrase((SimpleVerbPhrase) oldsvf);
				oldsvf.deleteRecursively();
				addSimpleVerbPhrase((SimpleVerbPhrase) simplvf);
			}
		}

	}

	SCLGraph getSCLGraph() {
		return (SCLGraph) getGraph();
	}

	@Override
	public String toString() {
		String phrase = "";
		if (this.getSimpleVerbPhrase() != null)
			phrase += this.getSimpleVerbPhrase() + " ";
		if (this.getPreposition() != null)
			if (!this.getPreposition().equals(""))
				phrase += this.getPreposition() + " ";
		if (this.getObject() != null)
			phrase += this.getObject();
		return phrase.trim();
	}
	
	
	public boolean equals(PhraseDTO phrase){
		boolean nounEquals = false;
		boolean verbEquals = false;
		boolean prepEquals = false;
		
		if (phrase instanceof ComplexVerbPhraseDTO){
			NounPhraseDTO thatNoun = ((ComplexVerbPhraseDTO)phrase).getObject();
			SimpleVerbPhraseDTO thatVerb = ((ComplexVerbPhraseDTO)phrase).getSimpleVerbPhrase();
			PrepositionDTO thatPrep = ((ComplexVerbPhraseDTO)phrase).getPreposition();
			
			NounPhraseDTO thisNoun = this.getObject();
			SimpleVerbPhraseDTO thisVerb = this.getSimpleVerbPhrase();
			PrepositionDTO thisPrep = this.getPreposition();
			
			
			if (thatPrep != null && thisPrep != null){
				prepEquals = false;
				if (thatPrep.getSynonymUid() == thisPrep.getSynonymUid()){
					prepEquals = true;
				}
			}	else return prepEquals;
			
			if (thatNoun != null && thisNoun != null){				
				nounEquals = false;
				if (thatNoun.equals(thisNoun)){
					nounEquals = true;
				}
			} else return nounEquals; 
			
			
			if (thisVerb != null && thatVerb != null) {
				verbEquals = false;
				if (thatVerb.equals(thisVerb)){
					verbEquals = true;
				}
			}			
			
		}
		return verbEquals && nounEquals && prepEquals;
	}
	
	public PhraseDTO copy(boolean basicForm){
		ComplexVerbPhraseDTO phrase = (ComplexVerbPhraseDTOImpl)this.getSCLGraph().createComplexVerbPhrase();
		if (this.getObject() != null){
			phrase.setObject((NounPhraseDTO)this.getObject().copy(basicForm));
		}
		if (this.getSimpleVerbPhrase() != null){
			phrase.setSimpleVerbPhrase((SimpleVerbPhraseDTO)this.getSimpleVerbPhrase().copy(basicForm));
		}
		if (this.getPreposition() != null){
			PrepositionDTO prep = (PrepositionDTOImpl)this.getSCLGraph().createPreposition();
			prep.setName(this.getPreposition().getName());
			prep.setSynonymUid(this.getPreposition().getSynonymUid());
			if (prep.getSynonymUid() != 0 && basicForm){
				prep.setName(RemoteJGWNL.getInstance().getTermSenseDTO(prep.getSynonymUid()).getLemma());
			}
			phrase.setPreposition(prep);
		}
		return phrase;
	}
	
	public void deleteRecursively(){
		if (this.getObject() != null){
			this.getObject().deleteRecursively();
		}
		if (this.getSimpleVerbPhrase() != null){
			this.getSimpleVerbPhrase().deleteRecursively();
		}
		if (this.getPreposition() != null){
			this.getPreposition().delete();
		}
		this.delete();
	}
	
	public void connect(){
		this.getObject().connect();
		this.getSimpleVerbPhrase().connect();
		
		//do not try to connect if its already connected
		if (this.getPreposition() != null)
			if (this.getPreposition().getSynonymUid() != 0 )
				return; 
		
		BusinessLayerFacade facade = (BusinessLayerFacadeImpl)this.getSCLGraph();
		//preventing from connecting clipboard members
		if (facade.isAnyClipboardMember(this))
			return;
		ComplexVerbPhraseDTO result = facade.findComplexVerbPhrase((ComplexVerbPhraseDTO)this);
		
		if (result!=null){
			this.getPreposition().setSynonymUid(result.getPreposition().getSynonymUid());
		}
		
		
	}
	
	public boolean hasSenses(){
		if (this.getSimpleVerbPhrase() != null){
			if (!this.getSimpleVerbPhrase().hasSenses())
				return false;
		}
		if (this.getObject() != null){
			if (!this.getObject().hasSenses())
				return false;
		}
		return true;
	}
}