/**
 *
 */
package eu.redseeds.sc.editor.rsl.editors.sentencewidgets.providers;

import eu.redseeds.sc.current.repository.SCProjectContainer;
import eu.redseeds.sc.editor.rsl.editors.sentencewidgets.grammars.SVOSentenceGrammar;
import eu.redseeds.scl.model.BusinessLayerFacade;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.ComplexVerbPhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.NounPhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.PhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.SimpleVerbPhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.VerbPhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.terms.DeterminerDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.terms.ModifierDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.terms.PrepositionDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.terms.VerbDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.svosentences.SVOSentenceDTO;

class SVOSentenceProviderHelper implements ISVOSentenceProviderHelper {
	
	private boolean external = false;
	
	public void setExternal (boolean flag){
		external = flag;
	}

	/* (non-Javadoc)
	 * @see eu.redseeds.sc.editor.rsl.editors.sentencewidgets.providers.ISVOSentenceProviderHelper#getStateText(eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.svosentences.SVOSentenceDTO, java.lang.Object)
	 */
	public  String getStateText(SVOSentenceDTO sentenceDTO, Object state) {
		if (state.equals(SVOSentenceGrammar.States.Determiner2))
			return getQuantifier2Text(sentenceDTO);
		else if (state.equals(SVOSentenceGrammar.States.Determiner3))
			return getQuantifier3Text(sentenceDTO);
		else if (state.equals(SVOSentenceGrammar.States.Modifier2))
			return getModifier2Text(sentenceDTO);
		else if (state.equals(SVOSentenceGrammar.States.Modifier3))
			return getModifier3Text(sentenceDTO);
		else if (state.equals(SVOSentenceGrammar.States.Noun1))
			return getNoun1Text(sentenceDTO);
		else if (state.equals(SVOSentenceGrammar.States.Noun2))
			return getNoun2Text(sentenceDTO);
		else if (state.equals(SVOSentenceGrammar.States.Noun3))
			return getNoun3Text(sentenceDTO);
		else if (state.equals(SVOSentenceGrammar.States.Verb))
			return getVerbText(sentenceDTO);
		else if (state.equals(SVOSentenceGrammar.States.Preposition))
			return getPrepositionText(sentenceDTO);
		return null;
	}

	private  String getQuantifier2Text(SVOSentenceDTO sentenceDTO) {
		DeterminerDTO det = getQuantifier2(sentenceDTO);
		if (det != null) {
			return det.getName();
		}
		return null;
	}

	private  String getQuantifier3Text(SVOSentenceDTO sentenceDTO) {
		DeterminerDTO det = getQuantifier3(sentenceDTO);
		if (det != null) {
			return det.getName();
		}
		return null;
	}

	private  String getModifier2Text(SVOSentenceDTO sentenceDTO) {
		ModifierDTO mod = getModifier2(sentenceDTO);
		if (mod != null) {
			return mod.getName();
		}
		return null;
	}

	private  String getModifier3Text(SVOSentenceDTO sentenceDTO) {
		ModifierDTO mod = getModifier3(sentenceDTO);
		if (mod != null) {
			return mod.getName();
		}
		return null;
	}

	private  String getNoun1Text(SVOSentenceDTO sentenceDTO) {
		NounPhraseDTO np = sentenceDTO.getSubject();
		if (np != null)
			return np.getNounText();
		return null;
	}

	private  String getNoun2Text(SVOSentenceDTO sentenceDTO) {

		SimpleVerbPhraseDTO vp = getSimpleVerbPhrase(sentenceDTO);
		if (vp != null) {
			NounPhraseDTO np = vp.getObject();
			if (np != null)
				return np.getNounText();
		}
		return null;
	}

	private  String getNoun3Text(SVOSentenceDTO sentenceDTO) {
		ComplexVerbPhraseDTO vp = getComplexVerbPhrase(sentenceDTO);
		if (vp != null) {
			NounPhraseDTO np = vp.getObject();
			if (np != null)
				return np.getNounText();
		}
		return null;
	}

	private  String getVerbText(SVOSentenceDTO sentenceDTO) {
		VerbDTO verb = getVerb(sentenceDTO);
		if (verb != null) {
			return verb.getName();
		}
		return null;
	}

	private  String getPrepositionText(SVOSentenceDTO sentenceDTO) {
		PrepositionDTO pre = getPreposition(sentenceDTO);
		if (pre != null) {
			return pre.getName();
		}
		return null;
	}

	private  PrepositionDTO getPreposition(SVOSentenceDTO sentenceDTO) {
		ComplexVerbPhraseDTO vp = getComplexVerbPhrase(sentenceDTO);
		if (vp != null) {
			if (vp.getPreposition() != null)
				return vp.getPreposition();
		}
		return null;
	}

	private  ComplexVerbPhraseDTO getComplexVerbPhrase(SVOSentenceDTO sentenceDTO) {
		VerbPhraseDTO vp = sentenceDTO.getPredicate();
		if (vp != null)
			if (vp instanceof ComplexVerbPhraseDTO)
				return (ComplexVerbPhraseDTO) vp;
		return null;
	}

	private  DeterminerDTO getQuantifier2(SVOSentenceDTO sentenceDTO) {
		SimpleVerbPhraseDTO vp = getSimpleVerbPhrase(sentenceDTO);
		if (vp != null) {
			NounPhraseDTO np = vp.getObject();
			if (np != null)
				if (np.getDeterminer() != null)
					return np.getDeterminer();
		}
		return null;
	}

	private  DeterminerDTO getQuantifier3(SVOSentenceDTO sentenceDTO) {
		ComplexVerbPhraseDTO vp = getComplexVerbPhrase(sentenceDTO);
		if (vp != null) {
			NounPhraseDTO np = vp.getObject();
			if (np != null)
				if (np.getDeterminer() != null)
					return np.getDeterminer();
		}
		return null;
	}

	private  ModifierDTO getModifier2(SVOSentenceDTO sentenceDTO) {
		SimpleVerbPhraseDTO vp = getSimpleVerbPhrase(sentenceDTO);
		if (vp != null) {
			NounPhraseDTO np = vp.getObject();
			if (np != null)
				if (np.getModifier() != null)
					return np.getModifier();
		}
		return null;
	}

	private  SimpleVerbPhraseDTO getSimpleVerbPhrase(SVOSentenceDTO sentenceDTO) {
		VerbPhraseDTO vp = sentenceDTO.getPredicate();
		if (vp != null) {
			if (vp instanceof SimpleVerbPhraseDTO)
				return (SimpleVerbPhraseDTO) vp;
			else if (vp instanceof ComplexVerbPhraseDTO) {
				ComplexVerbPhraseDTO cvp = (ComplexVerbPhraseDTO) vp;
				return cvp.getSimpleVerbPhrase();
			}
		}
		return null;
	}

	private  VerbDTO getVerb(SVOSentenceDTO sentenceDTO) {
		SimpleVerbPhraseDTO vp = getSimpleVerbPhrase(sentenceDTO);
		if (vp != null) {
			if (vp.getVerb() != null)
				return vp.getVerb();
		}
		return null;
	}

	private  ModifierDTO getModifier3(SVOSentenceDTO sentenceDTO) {
		ComplexVerbPhraseDTO vp = getComplexVerbPhrase(sentenceDTO);
		if (vp != null) {
			NounPhraseDTO np = vp.getObject();
			if (np != null)
				if (np.getModifier() != null)
					return np.getModifier();
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see eu.redseeds.sc.editor.rsl.editors.sentencewidgets.providers.ISVOSentenceProviderHelper#setStateText(eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.svosentences.SVOSentenceDTO, eu.redseeds.scl.model.BusinessLayerFacade, java.lang.Object, java.lang.String)
	 */
	public  void setStateText(SVOSentenceDTO sentenceDTO, BusinessLayerFacade facade, Object state, String text) {
		if (state.equals(SVOSentenceGrammar.States.Determiner2))
			setQuantifier2(sentenceDTO, facade, text);
		else if (state.equals(SVOSentenceGrammar.States.Determiner3))
			setQuantifier3(sentenceDTO, facade, text);
		else if (state.equals(SVOSentenceGrammar.States.Modifier2))
			setModifier2(sentenceDTO, facade, text);
		else if (state.equals(SVOSentenceGrammar.States.Modifier3))
			setModifier3(sentenceDTO, facade, text);
		else if (state.equals(SVOSentenceGrammar.States.Noun1))
			setNoun1(sentenceDTO, facade, text);
		else if (state.equals(SVOSentenceGrammar.States.Noun2))
			setNoun2(sentenceDTO, facade, text);
		else if (state.equals(SVOSentenceGrammar.States.Noun3))
			setNoun3(sentenceDTO, facade, text);
		else if (state.equals(SVOSentenceGrammar.States.Verb))
			setVerb(sentenceDTO, facade, text);
		else if (state.equals(SVOSentenceGrammar.States.Preposition))
			setPreposition(sentenceDTO, facade, text);

		if (state.equals(SVOSentenceGrammar.States.Noun1)) {
			if (validateNounPhrase(sentenceDTO.getSubject()))// validateNounPhrase
				// test if
				// subject isn't
				// null
				connectPhrase(sentenceDTO.getSubject());
		} else if (sentenceDTO.getPredicate() != null) {
			if (sentenceDTO.getPredicate() instanceof SimpleVerbPhraseDTO) {
				if (validateSimpleVerbPhrase(((SimpleVerbPhraseDTO) sentenceDTO.getPredicate())))
					connectPhrase(sentenceDTO.getPredicate());
			} else if (sentenceDTO.getPredicate() instanceof ComplexVerbPhraseDTO) {
				if (validateComplexVerbPhrase(((ComplexVerbPhraseDTO) sentenceDTO.getPredicate())))
					connectPhrase(sentenceDTO.getPredicate());
			}
		}
	}

	private  void setQuantifier2(SVOSentenceDTO sentenceDTO, BusinessLayerFacade facade, String quan) {
		SimpleVerbPhraseDTO predicate = getSimpleVerbPhrase(sentenceDTO);
		if (quan == null) { // prevents from setting quan name to null od
							// quan
			// deletion
			if (predicate != null)
				if (predicate.getObject() != null)
					if (predicate.getObject().getDeterminer() != null)
						predicate.getObject().setDeterminer(null);
			return;
		}

		if (predicate == null) {
			predicate = facade.createSimpleVerbPhraseDTO();
			sentenceDTO.setPredicate(predicate);
		}
		NounPhraseDTO object = predicate.getObject();
		if (object == null) {
			object = facade.createNounPhraseDTO();
			predicate.setObject(object);
		}

		// object.setDeterminer(getFacade().createDeterminerDTO(quan));
		// object.getDeterminer().setSynonymUid(0);

		DeterminerDTO d = object.getDeterminer();
		if (d == null) {
			d = facade.createDeterminerDTO();
		}
		d.setName(quan);
		object.setDeterminer(d);
		object.getDeterminer().setSynonymUid(0);
	}

	private  void setQuantifier3(SVOSentenceDTO sentenceDTO, BusinessLayerFacade facade, String quan) {
		ComplexVerbPhraseDTO predicate = getComplexVerbPhrase(sentenceDTO);
		if (quan == null) { // prevents from setting quan name to null od
							// quan
			// deletion
			if (predicate != null)
				if (predicate.getObject() != null)
					if (predicate.getObject().getDeterminer() != null)
						predicate.getObject().setDeterminer(null);
			return;
		}

		SimpleVerbPhraseDTO simplePredicate = getSimpleVerbPhrase(sentenceDTO);
		if (predicate == null) {
			predicate = facade.createComplexVerbPhraseDTO();
			if (simplePredicate == null) {
				simplePredicate = facade.createSimpleVerbPhraseDTO();
			}
			predicate.setSimpleVerbPhrase(simplePredicate);
			sentenceDTO.setPredicate(predicate);
		}
		NounPhraseDTO object = predicate.getObject();
		if (object == null) {
			object = facade.createNounPhraseDTO();
			predicate.setObject(object);
		}

		// object.setDeterminer(getFacade().createDeterminerDTO(quan));
		// object.getDeterminer().setSynonymUid(0);

		DeterminerDTO d = object.getDeterminer();
		if (d == null) {
			d = facade.createDeterminerDTO();
		}
		d.setName(quan);
		object.setDeterminer(d);
		object.getDeterminer().setSynonymUid(0);

	}

	private  void setVerb(SVOSentenceDTO sentenceDTO, BusinessLayerFacade facade, String verb) {

		if (verb == null) {
			sentenceDTO.setPredicate(null);
			return;
		}
		SimpleVerbPhraseDTO predicate = getSimpleVerbPhrase(sentenceDTO);
		if (predicate == null) {
			predicate = facade.createSimpleVerbPhraseDTO();
			sentenceDTO.setPredicate(predicate);
		}

		// predicate.setVerb(getFacade().createVerbDTO(verb));
		// predicate.getVerb().setSynonymUid(0);

		VerbDTO v = predicate.getVerb();
		if (v == null) {
			v = facade.createVerbDTO();
		}
		v.setName(verb);
		predicate.setVerb(v);
		predicate.getVerb().setSynonymUid(0);

	}

	private  void setModifier2(SVOSentenceDTO sentenceDTO, BusinessLayerFacade facade, String mod) {
		SimpleVerbPhraseDTO predicate = getSimpleVerbPhrase(sentenceDTO);

		if (mod == null) { // prevents from setting mod name to null od mod
			// deletion
			if (predicate != null)
				if (predicate.getObject() != null)
					if (predicate.getObject().getModifier() != null)
						predicate.getObject().setModifier(null);
			return;
		}

		if (predicate == null) {
			predicate = facade.createSimpleVerbPhraseDTO();
			sentenceDTO.setPredicate(predicate);
		}
		NounPhraseDTO object = predicate.getObject();
		if (object == null) {
			object = facade.createNounPhraseDTO();
			predicate.setObject(object);
		}

		// object.setModifier(getFacade().createModifierDTO(mod));
		// object.getModifier().setSynonymUid(0);

		ModifierDTO m = object.getModifier();
		if (m == null) {
			m = facade.createModifierDTO();
		}
		object.setModifier(m);
		m.setName(mod);
		object.getModifier().setSynonymUid(0);
	}

	private  void setModifier3(SVOSentenceDTO sentenceDTO, BusinessLayerFacade facade, String mod) {
		ComplexVerbPhraseDTO predicate = getComplexVerbPhrase(sentenceDTO);
		if (mod == null) { // prevents from setting mod name to null od mod
			// deletion
			if (predicate != null)
				if (predicate.getObject() != null)
					if (predicate.getObject().getModifier() != null)
						predicate.getObject().setModifier(null);
			return;
		}

		SimpleVerbPhraseDTO simplePredicate = getSimpleVerbPhrase(sentenceDTO);
		if (predicate == null) {
			predicate = facade.createComplexVerbPhraseDTO();
			if (simplePredicate == null) {
				simplePredicate = facade.createSimpleVerbPhraseDTO();
			}
			predicate.setSimpleVerbPhrase(simplePredicate);
			sentenceDTO.setPredicate(predicate);
		}
		NounPhraseDTO object = predicate.getObject();
		if (object == null) {
			object = facade.createNounPhraseDTO();
			predicate.setObject(object);
		}

		// object.setModifier(facade.createModifierDTO(mod));
		// object.getModifier().setSynonymUid(0);

		ModifierDTO m = object.getModifier();
		if (m == null) {
			m = facade.createModifierDTO();
		}
		m.setName(mod);
		object.setModifier(m);
		object.getModifier().setSynonymUid(0);
	}

	private  void setNoun1(SVOSentenceDTO sentenceDTO, BusinessLayerFacade facade, String noun) {
		NounPhraseDTO subject = sentenceDTO.getSubject();
		if (subject == null) {
			subject = facade.createNounPhraseDTO();
			sentenceDTO.setSubject(subject);
		}
		subject.setNoun(null);// unlink from previous term
		subject.setNounText(noun);
		// delete term if not connected anywhere else
		// subject.setNoun(facade.createNounDTO(noun));
		// subject.getNoun().setSynonymUid(0);
		/*
		 * NounDTO n = subject.getNoun(); if (n == null) { n =
		 * facade.createNounDTO(); } n.setName(noun); subject.setNoun(n);
		 * subject.getNoun().setSynonymUid(0);
		 */
	}

	private  void setNoun2(SVOSentenceDTO sentenceDTO, BusinessLayerFacade facade, String noun) {
		SimpleVerbPhraseDTO predicate = getSimpleVerbPhrase(sentenceDTO);
		if (predicate == null) {
			predicate = facade.createSimpleVerbPhraseDTO();
			sentenceDTO.setPredicate(predicate);
		}
		NounPhraseDTO object = predicate.getObject();
		if (object == null) {
			object = facade.createNounPhraseDTO();
			predicate.setObject(object);
		}
		object.setNoun(null);// unlink from previous term
		object.setNounText(noun);
		// delete term if not connected anywhere else
		// object.setNoun(facade.createNounDTO(noun));
		// object.getNoun().setSynonymUid(0);

		/*
		 * NounDTO n = object.getNoun(); if (n == null) { n =
		 * facade.createNounDTO(); } n.setName(noun); object.setNoun(n);
		 * object.getNoun().setSynonymUid(0);
		 */
	}

	private  void setNoun3(SVOSentenceDTO sentenceDTO, BusinessLayerFacade facade, String noun) {
		ComplexVerbPhraseDTO predicate = getComplexVerbPhrase(sentenceDTO);
		SimpleVerbPhraseDTO simplePredicate = getSimpleVerbPhrase(sentenceDTO);
		if (predicate == null) {
			if (null==noun) return;
			predicate = facade.createComplexVerbPhraseDTO();
			if (simplePredicate == null) {
				simplePredicate = facade.createSimpleVerbPhraseDTO();
			}
			predicate.setSimpleVerbPhrase(simplePredicate);
			sentenceDTO.setPredicate(predicate);
		}
		NounPhraseDTO object = predicate.getObject();
		if (object == null) {
			object = facade.createNounPhraseDTO();
			predicate.setObject(object);
		}
		object.setNoun(null);// unlink from previous term
		object.setNounText(noun);
		// delete term if not connected anywhere else (!already done in
		// setNoun
		// method)
		// object.setNoun(facade.createNounDTO(noun));
		// object.getNoun().setSynonymUid(0);

		/*
		 * NounDTO n = object.getNoun(); if (n == null) { n =
		 * facade.createNounDTO(); } n.setName(noun); object.setNoun(n);
		 * object.getNoun().setSynonymUid(0);
		 */

	}

	private  void setPreposition(SVOSentenceDTO sentenceDTO, BusinessLayerFacade facade, String prep) {
		ComplexVerbPhraseDTO predicate = getComplexVerbPhrase(sentenceDTO);
		SimpleVerbPhraseDTO simplePredicate = getSimpleVerbPhrase(sentenceDTO);

		// if preposition equals null, replace complexVerbPhrase with
		// SimpleVerbPhrase
		// (remove indirectly complexVerbPhrase from graph)
		if (prep == null) {
			if (predicate != null) {
				sentenceDTO.setPredicate((VerbPhraseDTO) (simplePredicate.copy(false)));
			}
			return;
		}

		if (predicate == null) {
			predicate = facade.createComplexVerbPhraseDTO();
/*			if (simplePredicate == null) {
				simplePredicate = facade.createSimpleVerbPhraseDTO();
			}*/
			// predicate.setSimpleVerbPhrase(simplePredicate);
			sentenceDTO.setPredicate(predicate);
		}

		// predicate.setPreposition(facade.createPrepostitionDTO(prep));
		// predicate.getPreposition().setSynonymUid(0);

		PrepositionDTO p = predicate.getPreposition();
		if (p == null) {
			p = facade.createPrepostitionDTO();
		}
		p.setName(prep);
		predicate.setPreposition(p);
		predicate.getPreposition().setSynonymUid(0);
	}

	private  boolean validateNounPhrase(NounPhraseDTO nf) {
		if (nf == null)
			return false;
		if (nf.getNounText() == null)
			return false;
		// if (nf.getNoun().getName() == null)
		// return false;
		if (nf.getNounText().equals(""))
			return false;
		return true;
	}

	private  boolean validateSimpleVerbPhrase(SimpleVerbPhraseDTO svf) {
		if (svf == null)
			return false;
		if (svf.getVerb() == null)
			return false;
		if (svf.getVerb().getName() == null)
			return false;
		if (svf.getVerb().getName().equals(""))
			return false;
		return validateNounPhrase(svf.getObject());
	}

	private  boolean validateComplexVerbPhrase(ComplexVerbPhraseDTO cvf) {
		if (cvf == null)
			return false;
		if (cvf.getPreposition() == null)
			return false;
		if (cvf.getPreposition().getName() == null)
			return false;
		if (cvf.getPreposition().getName().equals(""))
			return false;
		if (!validateNounPhrase(cvf.getObject()))
			return false;
		return validateSimpleVerbPhrase(cvf.getSimpleVerbPhrase());
	}

	private  void connectPhrase(PhraseDTO phrase) {
		phrase.connect();
		// tstraszak, 22.09.2008 - autosave
		if(!external)
			SCProjectContainer.instance().getSCProject(phrase).save();
	}
}