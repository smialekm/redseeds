package eu.redseeds.scl.model.impl.rsl.rsldomainelements.phrases;

import java.util.ArrayList;
import java.util.List;

import de.uni_koblenz.jgralab.Graph;
import eu.redseeds.common.Constants;
import eu.redseeds.sc.terminology.model.RemoteJGWNL;
import eu.redseeds.sc.terminology.model.TermSenseDTO;
import eu.redseeds.scl.SCLGraph;
import eu.redseeds.scl.impl.rsl.rsldomainelements.phrases.NounPhraseImpl;
import eu.redseeds.scl.model.BusinessLayerFacade;
import eu.redseeds.scl.model.impl.BusinessLayerFacadeImpl;
import eu.redseeds.scl.model.impl.rsl.rsldomainelements.terms.DeterminerDTOImpl;
import eu.redseeds.scl.model.impl.rsl.rsldomainelements.terms.ModifierDTOImpl;
import eu.redseeds.scl.model.impl.rsl.rsldomainelements.terms.NounDTOImpl;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.NounPhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.PhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.SimpleVerbPhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.terms.DeterminerDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.terms.ModifierDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.terms.NounDTO;
import eu.redseeds.scl.rsl.rsldomainelements.phrases.DeterminerLink;
import eu.redseeds.scl.rsl.rsldomainelements.phrases.ModifierLink;
import eu.redseeds.scl.rsl.rsldomainelements.phrases.NounLink;
import eu.redseeds.scl.rsl.rsldomainelements.terms.Determiner;
import eu.redseeds.scl.rsl.rsldomainelements.terms.Modifier;
import eu.redseeds.scl.rsl.rsldomainelements.terms.Noun;

public class NounPhraseDTOImpl extends NounPhraseImpl implements NounPhraseDTO {

	public NounPhraseDTOImpl(int arg0, Graph arg1) {
		super(arg0, arg1);
	}

	@Override
	public DeterminerDTO getDeterminer() {
		DeterminerLink detLink;
		if (getFirstDeterminerIsPartOfSource() != null) {
			detLink = (DeterminerLink) this.getFirstDeterminerIsPartOfSource()
					.getAlpha();
			if (detLink.getFirstDeterminerLinkLinksToTarget() != null) {
				return (DeterminerDTOImpl) (Determiner) detLink
						.getFirstDeterminerLinkLinksToTarget().getOmega();
			}
		}
		return null;
	}

	@Override
	public void setDeterminer(DeterminerDTO det) {
		if (!this.getDeterminerList().isEmpty()) {
			DeterminerLink dl = this.getDeterminerList().get(0);
			this.removeDeterminer(dl);
			dl.delete();
		}

		if (det != null) {
			DeterminerLink detLink = getSCLGraph().createDeterminerLink();
			Determiner d = (Determiner) det;
			d.addDeterminerLink(detLink);
			this.addDeterminer(detLink);
			detLink.setValue(det.getName());
		}
	}

	@Override
	public ModifierDTO getModifier() {
		ModifierLink modLink;
		if (getFirstModifierIsPartOfSource() != null) {
			modLink = (ModifierLink) this.getFirstModifierIsPartOfSource()
					.getAlpha();
			if (modLink.getFirstModifierLinkLinksToTarget() != null) {
				return (ModifierDTOImpl) (Modifier) modLink
						.getFirstModifierLinkLinksToTarget().getOmega();
			}
		}
		return null;
	}

	@Override
	public NounDTO getNoun() {
		NounLink nounLink;
		if (getFirstNounIsPartOfSource() != null) {
			nounLink = (NounLink) this.getFirstNounIsPartOfSource().getAlpha();
			if (nounLink.getFirstNounLinkLinksToTarget() != null) {
				return (NounDTOImpl) (Noun) nounLink
						.getFirstNounLinkLinksToTarget().getOmega();
			}
		}
		return null;
	}

	@Override
	public String getNounText() {
		NounLink nounLink;
		if (getFirstNounIsPartOfSource() != null) {
			nounLink = (NounLink) this.getFirstNounIsPartOfSource().getAlpha();
			return nounLink.getValue();
		}
		return null;
	}

	@Override
	public void setModifier(ModifierDTO mod) {
		if (!this.getModifierList().isEmpty()) {
			ModifierLink ml = this.getModifierList().get(0);
			this.removeModifier(ml);
			ml.delete();
		}
		if (mod != null) {
			ModifierLink modLink = getSCLGraph().createModifierLink();
			Modifier m = (Modifier) mod;
			m.addModifierLink(modLink);
			this.addModifier(modLink);
			modLink.setValue(mod.getName());
		}

	}

	@Override
	public void setNoun(NounDTO noun) {
		Noun tmpNoun = (Noun) this.getNoun(); // getting old noun
		NounLink nl;
		
		if (noun == null) {
			// if nounLink already exists, remove it
			if (!this.getNounList().isEmpty()) {
				nl = this.getNounList().get(0);
				if (tmpNoun != null)
					tmpNoun.removeNounLink(nl);
				this.removeNoun(nl);
				nl.delete();
			}
			
		}
		if (noun != null) {
			Noun n = (Noun) noun;
			// if nounLink already exists, link it to given noun
			if (!this.getNounList().isEmpty()) {
				nl = this.getNounList().get(0);
				if (tmpNoun != null)
					tmpNoun.removeNounLink(nl);
				n.addNounLink(nl);
			}
			// if not, create new nounLink, link it to given noun and set value
			// to noun name
			else {
				nl = getSCLGraph().createNounLink();
				n.addNounLink(nl);
				this.addNoun(nl);
				nl.setValue(noun.getName());

			}
		}

			// deleting old noun if exists and isn't connected to anything
			if (tmpNoun != null) {
				if (((Noun) tmpNoun).getNounLinkList().isEmpty())
					tmpNoun.delete();
			}
		

	}

	@Override
	public void setNounText(String nounText) {

		if (nounText != null) {
			NounLink nl;
			if (!this.getNounList().isEmpty()) {
				nl = this.getNounList().get(0);
			} else {
				nl = getSCLGraph().createNounLink();
				this.addNoun(nl);

			}
			nl.setValue(nounText.trim());
		} else {
			NounLink nl;
			if (!this.getNounList().isEmpty()) {
				nl = this.getNounList().get(0);
				NounDTO n = getNoun();
				this.removeNoun(nl);
				nl.delete();
				if (((Noun) n).getNounLinkList().isEmpty())
					n.delete();
			}
			
		}

	}

	private SCLGraph getSCLGraph() {
		return (SCLGraph) this.getGraph();
	}

	@Override
	public String toString() {
		String phrase = "";
		if (this.getDeterminer() != null)
			if (!this.getDeterminer().equals(""))
				phrase += this.getDeterminer() + " ";
		if (this.getModifier() != null)
			if (!this.getModifier().equals(""))
				phrase += this.getModifier() + " ";
		if (this.getNounText() != null)
			if (!this.getNounText().equals(""))
				phrase += this.getNounText();
		return phrase.trim();
	}

	@Deprecated
	public int getPhraseId() {
		//dbildhauer: The Id should not be modified outside JGraLab
		return super.getId();
	}

	@Override
	public void delete() {
		super.delete();
	}

	public boolean compareNoun(NounPhraseDTO phrase){			
		NounDTO	thatNoun = phrase.getNoun();
		NounDTO thisNoun = this.getNoun();
		
		if (thatNoun != null && thisNoun != null) {
			List<TermSenseDTO> thisSenses = new ArrayList<TermSenseDTO>();
			List<TermSenseDTO> thatSenses = new ArrayList<TermSenseDTO>();
			TermSenseDTO thisSense =null;
			TermSenseDTO thatSense =null;
			if (thisNoun.getSynonymUid() != 0)
				thisSense = RemoteJGWNL.getInstance().getTermSenseDTO(
						thisNoun.getSynonymUid());
			if (thatNoun.getSynonymUid() != 0)
				thatSense = RemoteJGWNL.getInstance().getTermSenseDTO(
						thatNoun.getSynonymUid());

			if (thisSense == null) {
				TermSenseDTO[] senses = RemoteJGWNL.getInstance()
						.getTermSenses(thisNoun.getName(),
								Constants.TERM_NOUN);
				for (TermSenseDTO s : senses) {
					thisSenses.add(s);
				}
			} else {
				thisSenses.add(thisSense);
			}

			if (thatSense == null) {
				TermSenseDTO[] senses = RemoteJGWNL.getInstance()
						.getTermSenses(thatNoun.getName(),
								Constants.TERM_NOUN);
				for (TermSenseDTO s : senses) {
					thatSenses.add(s);
				}
			} else {
				thatSenses.add(thatSense);
			}
			for (TermSenseDTO thisS : thisSenses) {
				for (TermSenseDTO thatS : thatSenses) {
					if (thisS.getUid() == thatS.getUid()) {
						return true;
					}
				}
			}
		} 
		return false;
	}
	
	public boolean equals(PhraseDTO phrase) {
		boolean nounEquals = false;
		boolean modEquals = false;
		boolean detEquals = false;

		if (phrase instanceof NounPhraseDTO) {
			NounDTO thatNoun = ((NounPhraseDTO) phrase).getNoun();
			ModifierDTO thatMod = ((NounPhraseDTO) phrase).getModifier();
			DeterminerDTO thatDet = ((NounPhraseDTO) phrase).getDeterminer();
			NounDTO thisNoun = this.getNoun();
			ModifierDTO thisMod = this.getModifier();
			DeterminerDTO thisDet = this.getDeterminer();

			if (thatNoun != null && thisNoun != null) {
				List<TermSenseDTO> thisSenses = new ArrayList<TermSenseDTO>();
				List<TermSenseDTO> thatSenses = new ArrayList<TermSenseDTO>();

				TermSenseDTO thisSense = 0!=thisNoun.getSynonymUid()?RemoteJGWNL.getInstance()
						.getTermSenseDTO(thisNoun.getSynonymUid()):null;
				TermSenseDTO thatSense = 0!=thatNoun.getSynonymUid()?RemoteJGWNL.getInstance()
						.getTermSenseDTO(thatNoun.getSynonymUid()):null;

				if (thisSense == null) {
					TermSenseDTO[] senses = RemoteJGWNL.getInstance()
							.getTermSenses(thisNoun.getName(),
									Constants.TERM_NOUN);
					for (TermSenseDTO s : senses) {
						thisSenses.add(s);
					}
				} else {
					thisSenses.add(thisSense);
				}

				if (thatSense == null) {
					TermSenseDTO[] senses = RemoteJGWNL.getInstance()
							.getTermSenses(thatNoun.getName(),
									Constants.TERM_NOUN);
					for (TermSenseDTO s : senses) {
						thatSenses.add(s);
					}
				} else {
					thatSenses.add(thatSense);
				}

				nounEquals = 0!=thisNoun.getSynonymUid() && 0!=thatNoun.getSynonymUid() && thisNoun.getSynonymUid()==thatNoun.getSynonymUid();
				for (TermSenseDTO thisS : thisSenses) {
					for (TermSenseDTO thatS : thatSenses) {
						if (thisS.getUid() == thatS.getUid()) {
							nounEquals = true;
						}
					}
				}
			} else
				return nounEquals;

			if (thisMod == null && thatMod == null) {
				modEquals = true;
			}

			if (thisMod != null && thatMod != null) {
				List<TermSenseDTO> thisSenses = new ArrayList<TermSenseDTO>();
				List<TermSenseDTO> thatSenses = new ArrayList<TermSenseDTO>();

				TermSenseDTO thisSense = RemoteJGWNL.getInstance()
						.getTermSenseDTO(thisMod.getSynonymUid());
				TermSenseDTO thatSense = RemoteJGWNL.getInstance()
						.getTermSenseDTO(thatMod.getSynonymUid());

				if (thisSense == null) {
					TermSenseDTO[] senses = RemoteJGWNL.getInstance()
							.getTermSenses(thisMod.getName(),
									Constants.TERM_MODIFIER);
					for (TermSenseDTO s : senses) {
						thisSenses.add(s);
					}
				} else {
					thisSenses.add(thisSense);
				}

				if (thatSense == null) {
					TermSenseDTO[] senses = RemoteJGWNL.getInstance()
							.getTermSenses(thatMod.getName(),
									Constants.TERM_MODIFIER);
					for (TermSenseDTO s : senses) {
						thatSenses.add(s);
					}
				} else {
					thatSenses.add(thatSense);
				}

				modEquals = 0!=thisMod.getSynonymUid() && 0!=thatMod.getSynonymUid() && thisMod.getSynonymUid()==thatMod.getSynonymUid();
				for (TermSenseDTO thisS : thisSenses) {
					for (TermSenseDTO thatS : thatSenses) {
						if (thisS.getUid() == thatS.getUid()) {
							modEquals = true;
						}
					}
				}
			}

			if (thisDet == null && thatDet == null) {
				detEquals = true;
			}

			if (thisDet != null && thatDet != null) {
				List<TermSenseDTO> thisSenses = new ArrayList<TermSenseDTO>();
				List<TermSenseDTO> thatSenses = new ArrayList<TermSenseDTO>();

				TermSenseDTO thisSense = RemoteJGWNL.getInstance()
						.getTermSenseDTO(thisDet.getSynonymUid());
				TermSenseDTO thatSense = RemoteJGWNL.getInstance()
						.getTermSenseDTO(thatDet.getSynonymUid());

				if (thisSense == null) {
					TermSenseDTO[] senses = RemoteJGWNL.getInstance()
							.getTermSenses(thisDet.getName(),
									Constants.TERM_DETERMINER);
					for (TermSenseDTO s : senses) {
						thisSenses.add(s);
					}
				} else {
					thisSenses.add(thisSense);
				}

				if (thatSense == null) {
					TermSenseDTO[] senses = RemoteJGWNL.getInstance()
							.getTermSenses(thatDet.getName(),
									Constants.TERM_DETERMINER);
					for (TermSenseDTO s : senses) {
						thatSenses.add(s);
					}
				} else {
					thatSenses.add(thatSense);
				}

				detEquals = 0!=thisDet.getSynonymUid() && 0!=thatDet.getSynonymUid() && thisDet.getSynonymUid()==thatDet.getSynonymUid();
				for (TermSenseDTO thisS : thisSenses) {
					for (TermSenseDTO thatS : thatSenses) {
						if (thisS.getUid() == thatS.getUid()) {
							detEquals = true;
						}
					}
				}
			}

		}
		return detEquals && nounEquals && modEquals;
	}

	public PhraseDTO copy(boolean basicForm) {
		NounPhraseDTO phrase = (NounPhraseDTOImpl) this.getSCLGraph()
				.createNounPhrase();
		if (this.getNoun() != null) {
			phrase.setNoun(this.getNoun());
			if (basicForm){
				if (this.getNoun().getSynonymUid()!=0) phrase.setNounText(RemoteJGWNL.getInstance().getTermSenseDTO(this.getNoun().getSynonymUid()).getLemma());
				else phrase.setNounText(this.getNounText().toLowerCase());
			} else
				phrase.setNounText(this.getNounText());
		}
		if (this.getModifier() != null) {
			ModifierDTO mod = (ModifierDTOImpl) this.getSCLGraph()
					.createModifier();
			mod.setName(this.getModifier().getName());
			mod.setSynonymUid(this.getModifier().getSynonymUid());
			if (mod.getSynonymUid() != 0 && basicForm) {
				mod.setName(RemoteJGWNL.getInstance().getTermSenseDTO(
						mod.getSynonymUid()).getLemma());
			}
			phrase.setModifier(mod);
		}
		if (this.getDeterminer() != null) {
			DeterminerDTO det = (DeterminerDTOImpl) this.getSCLGraph()
					.createDeterminer();
			det.setName(this.getDeterminer().getName());
			det.setSynonymUid(this.getDeterminer().getSynonymUid());
			if (det.getSynonymUid() != 0 && basicForm) {
				det.setName(RemoteJGWNL.getInstance().getTermSenseDTO(
						det.getSynonymUid()).getLemma());
			}
			phrase.setDeterminer(det);
		}
		return phrase;
	}

	public void deleteRecursively() {
		
		//deleting NounDTO if it's connected only to this Phrase
		if (this.getNoun() != null){ 
			if (((Noun) this.getNoun() ).getNounLinkList().size()==1)
				this.getNoun().delete();			
		}
		
		//removing nounLink
		if (!this.getNounList().isEmpty()) {
			NounLink nl = this.getNounList().get(0);
			this.removeNoun(nl);
			nl.delete();
		}

		if (this.getModifier() != null) {
			this.getModifier().delete();
		}
		if (this.getDeterminer() != null) {
			this.getDeterminer().delete();
		}
		this.delete();
	}

	public void connect(){
    	boolean shouldConnect=true;
		if (getNoun() != null && getNoun().getSynonymUid() != 0) shouldConnect = false;
		if (getDeterminer() != null && getDeterminer().getSynonymUid() == 0) shouldConnect = true;
		if (getModifier() != null && getModifier().getSynonymUid() == 0) shouldConnect = true;
		if(!shouldConnect) return;		
		BusinessLayerFacade facade = (BusinessLayerFacadeImpl) this.getSCLGraph();
		if (facade.isAnyClipboardMember(this)) return;
    	NounDTO tmpNoun;
		if (null==(tmpNoun=facade.getNoun(getNounText()))) {
			tmpNoun=facade.createNounDTO();
			tmpNoun.setName(getNounText());
		}
		setNoun(tmpNoun);
		NounPhraseDTO thatPhrase;
		if (null!=(thatPhrase = facade.findNounPhrase(this))) {
			if (getNoun() != null && thatPhrase.getNoun() != null){
				getNoun().setSynonymUid(thatPhrase.getNoun().getSynonymUid());
				facade.cleanNouns(thatPhrase.getNoun());
			}
			if (getModifier() != null && thatPhrase.getModifier() != null) getModifier().setSynonymUid(thatPhrase.getModifier().getSynonymUid());
			if (getDeterminer() != null && thatPhrase.getDeterminer() != null) getDeterminer().setSynonymUid(thatPhrase.getDeterminer().getSynonymUid());
		}
		
		if (!this.getVerbPhraseList().isEmpty() && null!=this.getVerbPhraseList() && this.getVerbPhraseList().get(0) instanceof SimpleVerbPhraseDTO)
			((SimpleVerbPhraseDTO) this.getVerbPhraseList().get(0)).connectToActionStereotype();
		
    }

	public boolean hasSenses() {
		if (this.getNoun() != null) {
			if (this.getNoun().getSynonymUid() == 0)
				return false;
		}
		if (this.getModifier() != null) {
			if (this.getModifier().getSynonymUid() == 0)
				return false;
		}
		if (this.getDeterminer() != null) {
			if (this.getDeterminer().getSynonymUid() == 0)
				return false;
		}
		return true;
	}

	@Override
	public boolean isConsistent() {
		TermSenseDTO[] senses = RemoteJGWNL.getInstance()
		.getTermSenses(getNounText(),
				Constants.TERM_NOUN);
		for (TermSenseDTO s : senses) if (s.getUid()==getNoun().getSynonymUid()) return getNoun().isConsistent();
		return false;
	}
	
	public boolean isUnused(){
		return getNotionList().isEmpty() && getElementList().isEmpty() && getVerbPhraseList().isEmpty();
	}
	
}