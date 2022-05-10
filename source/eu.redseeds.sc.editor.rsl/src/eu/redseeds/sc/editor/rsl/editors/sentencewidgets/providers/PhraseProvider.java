package eu.redseeds.sc.editor.rsl.editors.sentencewidgets.providers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.graphics.Color;

import eu.redseeds.sc.editor.rsl.editors.sentencewidgets.grammars.PhraseGrammar;
import eu.redseeds.scl.model.BusinessLayerFacade;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.ComplexVerbPhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.NounPhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.PhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.SimpleVerbPhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.terms.DeterminerDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.terms.ModifierDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.terms.NounDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.terms.PrepositionDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.terms.TermDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.terms.VerbDTO;

public class PhraseProvider implements ISentenceProvider {

	private PhraseGrammar grammar = new PhraseGrammar();

	private NounPhraseDTO nounPhrase;
	private SimpleVerbPhraseDTO simpleVerbPhrase;
	private ComplexVerbPhraseDTO complexVerbPhrase;

	private String unmarkedText = "";

	private Map<Object, StyleRange> styleRangesMap = new HashMap<Object, StyleRange>();

	// Colors
	Color orange = new Color(null, 255, 127, 0);
	Color lime = new Color(null, 127, 255, 127);
	Color red = new Color(null, 255, 0, 0);
	Color green = new Color(null, 0, 255, 0);
	Color blue = new Color(null, 0, 0, 255);
	Color black = new Color(null, 0, 0, 0);

	// styles
	StyleRange noun = new StyleRange();
	StyleRange verb = new StyleRange();
	StyleRange other = new StyleRange();
	StyleRange plain = new StyleRange();

	// temporary
	// to be removed after integrating with eclipse workbench
	private BusinessLayerFacade facade;

	public PhraseProvider() {
		// setup styles
		noun.fontStyle = SWT.BOLD;
		noun.foreground = blue;
		verb.fontStyle = SWT.ITALIC;
		verb.foreground = red;
		other.foreground = green;
		plain.foreground = black;

		styleRangesMap.put(PhraseGrammar.States.Determiner1, other);
		styleRangesMap.put(PhraseGrammar.States.Determiner2, other);
		styleRangesMap.put(PhraseGrammar.States.Determiner3, other);
		styleRangesMap.put(PhraseGrammar.States.Modifier1, other);
		styleRangesMap.put(PhraseGrammar.States.Modifier2, other);
		styleRangesMap.put(PhraseGrammar.States.Modifier3, other);
		styleRangesMap.put(PhraseGrammar.States.Preposition, other);
		styleRangesMap.put(PhraseGrammar.States.Verb, verb);
		styleRangesMap.put(PhraseGrammar.States.Noun1, noun);
		styleRangesMap.put(PhraseGrammar.States.Noun2, noun);
		styleRangesMap.put(PhraseGrammar.States.Noun3, noun);
	}

	public StyleRange getStyleForState(Object state) {
		if (state != null)
			return styleRangesMap.get(state);
		return plain;
	}

	@Override
	public Object getEndState() {
		return grammar.getEndState();
	}

	@Override
	public List<Object> getPossibleStates(Object state) {
		return new ArrayList<Object>(grammar
				.getPossibleStates((PhraseGrammar.States) state));
	}

	@Override
	public Object getStartState() {
		return grammar.getStartState();
	}

	@Override
	public String getStateText(Object state) {
		if (state.equals(PhraseGrammar.States.Determiner2))
			return this.getQuantifier2Text();
		else if (state.equals(PhraseGrammar.States.Determiner1))
			return this.getQuantifier1Text();
		else if (state.equals(PhraseGrammar.States.Determiner3))
			return this.getQuantifier3Text();
		else if (state.equals(PhraseGrammar.States.Modifier1))
			return this.getModifier1Text();
		else if (state.equals(PhraseGrammar.States.Modifier2))
			return this.getModifier2Text();
		else if (state.equals(PhraseGrammar.States.Modifier3))
			return this.getModifier3Text();
		else if (state.equals(PhraseGrammar.States.Noun1))
			return this.getNoun1Text();
		else if (state.equals(PhraseGrammar.States.Noun2))
			return this.getNoun2Text();
		else if (state.equals(PhraseGrammar.States.Noun3))
			return this.getNoun3Text();
		else if (state.equals(PhraseGrammar.States.Verb))
			return this.getVerbText();
		else if (state.equals(PhraseGrammar.States.Preposition))
			return this.getPrepositionText();
		return null;
	}
	
	
	public TermDTO getStateTerm(Object state) {
		if (state == null )
			return null;
		if (state.equals(PhraseGrammar.States.Determiner2))
			return this.getQuantifier2();
		else if (state.equals(PhraseGrammar.States.Determiner1))
			return this.getQuantifier1();
		else if (state.equals(PhraseGrammar.States.Determiner3))
			return this.getQuantifier3();
		else if (state.equals(PhraseGrammar.States.Modifier1))
			return this.getModifier1();
		else if (state.equals(PhraseGrammar.States.Modifier2))
			return this.getModifier2();
		else if (state.equals(PhraseGrammar.States.Modifier3))
			return this.getModifier3();
		else if (state.equals(PhraseGrammar.States.Noun1))
			return this.getNoun1();
		else if (state.equals(PhraseGrammar.States.Noun2))
			return this.getNoun2();
		else if (state.equals(PhraseGrammar.States.Noun3))
			return this.getNoun3();
		else if (state.equals(PhraseGrammar.States.Verb))
			return this.getVerb();
		else if (state.equals(PhraseGrammar.States.Preposition))
			return this.getPreposition();
		return null;
	}

	@Override
	public String getStateTooltip(Object state) {
		return grammar.getStateTooltip((PhraseGrammar.States) state);
	}

	@Override
	public Object getStates() {
		return grammar.getStates();
	}

	@Override
	public List<Object> getStatesList() {
		return new ArrayList<Object>(grammar.getStatesList());
	}

	@Override
	public String getUnmarkedText() {
		return unmarkedText;
	}

	@Override
	public void resetState(Object state) {
		setStateText(state, null);
	}

	@Override
	public void setSentence(Object sentence) {
		if (sentence instanceof NounPhraseDTO)
			this.nounPhrase = (NounPhraseDTO) sentence;
		else if (sentence instanceof SimpleVerbPhraseDTO)
			this.simpleVerbPhrase = (SimpleVerbPhraseDTO) sentence;
		else if (sentence instanceof ComplexVerbPhraseDTO) {
			this.complexVerbPhrase = (ComplexVerbPhraseDTO) sentence;
			this.simpleVerbPhrase = ((ComplexVerbPhraseDTO) sentence)
					.getSimpleVerbPhrase();
		}
	}

	@Override
	public void setStateText(Object state, String text) {
		if (state.equals(PhraseGrammar.States.Determiner2))
			this.setQuantifier2(text);
		else if (state.equals(PhraseGrammar.States.Determiner1))
			this.setQuantifier1(text);
		else if (state.equals(PhraseGrammar.States.Determiner3))
			this.setQuantifier3(text);
		else if (state.equals(PhraseGrammar.States.Modifier1))
			this.setModifier1(text);
		else if (state.equals(PhraseGrammar.States.Modifier2))
			this.setModifier2(text);
		else if (state.equals(PhraseGrammar.States.Modifier3))
			this.setModifier3(text);
		else if (state.equals(PhraseGrammar.States.Noun1))
			this.setNoun1(text);
		else if (state.equals(PhraseGrammar.States.Noun2))
			this.setNoun2(text);
		else if (state.equals(PhraseGrammar.States.Noun3))
			this.setNoun3(text);
		else if (state.equals(PhraseGrammar.States.Verb))
			this.setVerb(text);
		else if (state.equals(PhraseGrammar.States.Preposition))
			this.setPreposition(text);
		
		if (state.equals(PhraseGrammar.States.Noun1) ||
				state.equals(PhraseGrammar.States.Modifier1)||
						state.equals(PhraseGrammar.States.Determiner1 )) {
			if (validateNounPhrase(nounPhrase)){
				connectPhrase(nounPhrase);
			}
		} else if (complexVerbPhrase != null) {
			if (validateComplexVerbPhrase(complexVerbPhrase)){
				connectPhrase(complexVerbPhrase);
			}
		} else if (simpleVerbPhrase != null) {
			if (validateSimpleVerbPhrase(simpleVerbPhrase)){
				connectPhrase(simpleVerbPhrase);
			}
		}
	}

	@Override
	public void setUnmarkedText(String text) {
		unmarkedText = text;

	}

	public void setBLFacade(BusinessLayerFacade facade) {
		this.facade = facade;
	}

	private BusinessLayerFacade getFacade() {
		// to be re-implemented after integrating with eclipse workbench
		return facade;
	}

	private String getModifier2Text() {
		ModifierDTO mod = getModifier2();
		if (mod != null) {
			return mod.getName();
		}
		return null;
	}

	private ModifierDTO getModifier2() {
		SimpleVerbPhraseDTO vp = getSimpleVerbPhrase();
		if (vp != null) {
			NounPhraseDTO np = vp.getObject();
			if (np != null)
				if (np.getModifier() != null)
					return np.getModifier();
		}
		return null;
	}

	private String getModifier3Text() {
		ModifierDTO mod = getModifier3();
		if (mod != null) {
			return mod.getName();
		}
		return null;
	}

	private ModifierDTO getModifier3() {
		ComplexVerbPhraseDTO vp = getComplexVerbPhrase();
		if (vp != null) {
			NounPhraseDTO np = vp.getObject();
			if (np != null)
				if (np.getModifier() != null)
					return np.getModifier();
		}
		return null;
	}

	private String getNoun1Text() {
		NounPhraseDTO np = nounPhrase;
		if (np != null)
			return np.getNounText();
		return null;
	}

	private NounDTO getNoun1() {
		NounPhraseDTO np = nounPhrase;
		if (np != null)
			if (np.getNoun() != null)
				return np.getNoun();
		return null;
	}

	private String getModifier1Text() {
		ModifierDTO mod = getModifier1();
		if (mod != null) {
			return mod.getName();
		}
		return null;
	}

	private ModifierDTO getModifier1() {
		NounPhraseDTO np = nounPhrase;
		if (np != null)
			if (np.getModifier() != null)
				return np.getModifier();
		return null;
	}

	private String getQuantifier1Text() {
		DeterminerDTO quan = getQuantifier1();
		if (quan != null) {
			return quan.getName();
		}
		return null;
	}

	private DeterminerDTO getQuantifier1() {
		NounPhraseDTO np = nounPhrase;
		if (np != null)
			if (np.getDeterminer() != null)
				return np.getDeterminer();
		return null;
	}

	private String getNoun2Text() {
		SimpleVerbPhraseDTO vp = getSimpleVerbPhrase();
		if (vp != null) {
			NounPhraseDTO np = vp.getObject();
			if (np != null) 
				return np.getNounText();
		}
		return null;
	}

	private NounDTO getNoun2() {
		SimpleVerbPhraseDTO vp = getSimpleVerbPhrase();
		if (vp != null) {
			NounPhraseDTO np = vp.getObject();
			if (np != null) {
				if (np.getNoun() != null) {
					return np.getNoun();
				}
			}
		}

		return null;
	}

	private String getNoun3Text() {
		ComplexVerbPhraseDTO vp = getComplexVerbPhrase();
		if (vp != null) {
			NounPhraseDTO np = vp.getObject();
			if (np != null)
				return np.getNounText();
		}
		return null;
	}

	private NounDTO getNoun3() {
		ComplexVerbPhraseDTO vp = getComplexVerbPhrase();
		if (vp != null) {
			NounPhraseDTO np = vp.getObject();
			if (np != null)
				if (np.getNoun() != null)
					return np.getNoun();
		}
		return null;
	}

	private String getPrepositionText() {
		PrepositionDTO pre = getPreposition();
		if (pre != null) {
			return pre.getName();
		}
		return null;
	}

	private PrepositionDTO getPreposition() {
		ComplexVerbPhraseDTO vp = getComplexVerbPhrase();
		if (vp != null) {
			if (vp.getPreposition() != null)
				return vp.getPreposition();
		}
		return null;
	}

	private String getQuantifier2Text() {
		DeterminerDTO det = getQuantifier2();
		if (det != null) {
			return det.getName();
		}
		return null;
	}

	private DeterminerDTO getQuantifier2() {
		SimpleVerbPhraseDTO vp = getSimpleVerbPhrase();
		if (vp != null) {
			NounPhraseDTO np = vp.getObject();
			if (np != null)
				if (np.getDeterminer() != null)
					return np.getDeterminer();
		}
		return null;
	}

	private String getQuantifier3Text() {
		DeterminerDTO det = getQuantifier3();
		if (det != null) {
			return det.getName();
		}
		return null;
	}

	private DeterminerDTO getQuantifier3() {
		ComplexVerbPhraseDTO vp = getComplexVerbPhrase();
		if (vp != null) {
			NounPhraseDTO np = vp.getObject();
			if (np != null)
				if (np.getDeterminer() != null)
					return np.getDeterminer();
		}
		return null;
	}

	private String getVerbText() {
		VerbDTO verb = getVerb();
		if (verb != null) {
			return verb.getName();
		}
		return null;
	}

	private VerbDTO getVerb() {
		SimpleVerbPhraseDTO vp = getSimpleVerbPhrase();
		if (vp != null) {
			if (vp.getVerb() != null)
				return vp.getVerb();
		}
		return null;
	}

	private void setModifier2(String mod) {
		SimpleVerbPhraseDTO predicate = getSimpleVerbPhrase();
		if (predicate == null) {
			predicate = getFacade().createSimpleVerbPhraseDTO();
			simpleVerbPhrase = predicate;
		}
		NounPhraseDTO object = predicate.getObject();
		if (object == null) {
			object = getFacade().createNounPhraseDTO();
			predicate.setObject(object);
		}

		//object.setModifier(getFacade().createModifierDTO(mod));
		//object.getModifier().setSynonymUid(0);
		
		ModifierDTO m = object.getModifier();
		if (m == null) {
			m = getFacade().createModifierDTO();			
		}
		m.setName(mod);
		object.setModifier(m);
		object.getModifier().setSynonymUid(0);
		
		

	}

	private void setModifier3(String mod) {
		ComplexVerbPhraseDTO predicate = getComplexVerbPhrase();
		SimpleVerbPhraseDTO simplePredicate = getSimpleVerbPhrase();
		if (predicate == null) {
			predicate = getFacade().createComplexVerbPhraseDTO();
			if (simplePredicate == null) {
				simplePredicate = getFacade().createSimpleVerbPhraseDTO();
				simpleVerbPhrase = simplePredicate;
			}
			predicate.setSimpleVerbPhrase(simplePredicate);
			complexVerbPhrase = predicate;
		}
		NounPhraseDTO object = predicate.getObject();
		if (object == null) {
			object = getFacade().createNounPhraseDTO();
			predicate.setObject(object);
		}

		//object.setModifier(getFacade().createModifierDTO(mod));
		//object.getModifier().setSynonymUid(0);
		
		ModifierDTO m = object.getModifier();
		if (m == null) {
			m = getFacade().createModifierDTO();			
		}
		m.setName(mod);
		object.setModifier(m);
		object.getModifier().setSynonymUid(0);
	}

	private void setNoun1(String noun) {
		if (nounPhrase == null) {
			nounPhrase = getFacade().createNounPhraseDTO();
		}
		nounPhrase.setNounText(noun);
		//TODO: delete term if not connected anywhere else
		//nounPhrase.setNoun(getFacade().createNounDTO(noun));
		//nounPhrase.getNoun().setSynonymUid(0);
		
		/*NounDTO n = nounPhrase.getNoun();
		if (n == null) {
			n = getFacade().createNounDTO();			
		}
		n.setName(noun);
		nounPhrase.setNoun(n);
		nounPhrase.getNoun().setSynonymUid(0);*/
	}
	
	

	private void setModifier1(String mod) {
		if (nounPhrase == null) {
			nounPhrase = getFacade().createNounPhraseDTO();
		}
		//nounPhrase.setModifier(getFacade().createModifierDTO(mod));
		//nounPhrase.getModifier().setSynonymUid(0);
		
		ModifierDTO m = nounPhrase.getModifier();
		if (m == null) {
			m = getFacade().createModifierDTO();			
		}
		m.setName(mod);
		nounPhrase.setModifier(m);
		nounPhrase.getModifier().setSynonymUid(0);
	}

	private void setQuantifier1(String quan) {
		if (nounPhrase == null) {
			nounPhrase = getFacade().createNounPhraseDTO();
		}
		//nounPhrase.setDeterminer(getFacade().createDeterminerDTO(quan));
		//nounPhrase.getDeterminer().setSynonymUid(0);	
		
		DeterminerDTO d = nounPhrase.getDeterminer();
		if (d == null) {
			d = getFacade().createDeterminerDTO();			
		}
		d.setName(quan);
		nounPhrase.setDeterminer(d);
		nounPhrase.getDeterminer().setSynonymUid(0);
	}

	private void setNoun2(String noun) {
		SimpleVerbPhraseDTO predicate = getSimpleVerbPhrase();
		if (predicate == null) {
			predicate = getFacade().createSimpleVerbPhraseDTO();
			simpleVerbPhrase = predicate;
		}
		NounPhraseDTO object = predicate.getObject();
		if (object == null) {
			object = getFacade().createNounPhraseDTO();
			predicate.setObject(object);
		}
		object.setNounText(noun);
		//TODO: delete term if not connected anywhere else
		//object.setNoun(getFacade().createNounDTO(noun));
		//object.getNoun().setSynonymUid(0);
		
		/*NounDTO n = object.getNoun();
		if (n == null) {
			n = getFacade().createNounDTO();			
		}
		n.setName(noun);
		object.setNoun(n);
		object.getNoun().setSynonymUid(0);*/

	}

	private void setNoun3(String noun) {
		ComplexVerbPhraseDTO predicate = getComplexVerbPhrase();
		SimpleVerbPhraseDTO simplePredicate = getSimpleVerbPhrase();
		if (predicate == null) {
			predicate = getFacade().createComplexVerbPhraseDTO();
			if (simplePredicate == null) {
				simplePredicate = getFacade().createSimpleVerbPhraseDTO();
				simpleVerbPhrase = simplePredicate;
			}
			predicate.setSimpleVerbPhrase(simplePredicate);
			complexVerbPhrase = predicate;
		}
		NounPhraseDTO object = predicate.getObject();
		if (object == null) {
			object = getFacade().createNounPhraseDTO();
			predicate.setObject(object);
		}
		object.setNounText(noun);
		//TODO: delete term if not connected anywhere else
		//object.setNoun(getFacade().createNounDTO(noun));
		//object.getNoun().setSynonymUid(0);
		
		/*NounDTO n = object.getNoun();
		if (n == null) {
			n = getFacade().createNounDTO();			
		}
		n.setName(noun);
		object.setNoun(n);
		object.getNoun().setSynonymUid(0);*/

	}

	private void setPreposition(String prep) {
		ComplexVerbPhraseDTO predicate = getComplexVerbPhrase();
		SimpleVerbPhraseDTO simplePredicate = getSimpleVerbPhrase();
		if (predicate == null) {
			predicate = getFacade().createComplexVerbPhraseDTO();
			if (simplePredicate == null) {
				simplePredicate = getFacade().createSimpleVerbPhraseDTO();
				simpleVerbPhrase = simplePredicate;
			}
			predicate.setSimpleVerbPhrase(simplePredicate);
			complexVerbPhrase = predicate;
		}

		//predicate.setPreposition(getFacade().createPrepostitionDTO(prep));
		//predicate.getPreposition().setSynonymUid(0);
		
		PrepositionDTO p = predicate.getPreposition();
		if (p == null) {
			p = getFacade().createPrepostitionDTO();			
		}
		p.setName(prep);
		predicate.setPreposition(p);
		predicate.getPreposition().setSynonymUid(0);

	}

	/*
	 * private void setQuantifier1(String det) { NounPhraseDTO subject =
	 * sentence.getSubject(); if (subject == null) { subject =
	 * getFacade().createNounPhraseDTO(); sentence.setSubject(subject); }
	 * DeterminerDTO d = subject.getDeterminer(); if (d == null) { d =
	 * getFacade().createDeterminerDTO(); subject.setDeterminer(d); }
	 * d.setName(det); }
	 */

	private void setQuantifier2(String quan) {
		SimpleVerbPhraseDTO predicate = getSimpleVerbPhrase();
		if (predicate == null) {
			predicate = getFacade().createSimpleVerbPhraseDTO();
			simpleVerbPhrase = predicate;
		}
		NounPhraseDTO object = predicate.getObject();
		if (object == null) {
			object = getFacade().createNounPhraseDTO();
			predicate.setObject(object);
		}

		//object.setDeterminer(getFacade().createDeterminerDTO(quan));
		//object.getDeterminer().setSynonymUid(0);
		
		DeterminerDTO d = object.getDeterminer();
		if (d == null) {
			d = getFacade().createDeterminerDTO();			
		}
		d.setName(quan);
		object.setDeterminer(d);
		object.getDeterminer().setSynonymUid(0);

	}

	private void setQuantifier3(String quan) {
		ComplexVerbPhraseDTO predicate = getComplexVerbPhrase();
		SimpleVerbPhraseDTO simplePredicate = getSimpleVerbPhrase();
		if (predicate == null) {
			predicate = getFacade().createComplexVerbPhraseDTO();
			if (simplePredicate == null) {
				simplePredicate = getFacade().createSimpleVerbPhraseDTO();
				simpleVerbPhrase = simplePredicate;
			}
			predicate.setSimpleVerbPhrase(simplePredicate);
			complexVerbPhrase = predicate;
		}
		NounPhraseDTO object = predicate.getObject();
		if (object == null) {
			object = getFacade().createNounPhraseDTO();
			predicate.setObject(object);
		}

		//object.setDeterminer(getFacade().createDeterminerDTO(quan));
		//object.getDeterminer().setSynonymUid(0);
		
		DeterminerDTO d = object.getDeterminer();
		if (d == null) {
			d = getFacade().createDeterminerDTO();			
		}
		d.setName(quan);
		object.setDeterminer(d);
		object.getDeterminer().setSynonymUid(0);

	}

	private void setVerb(String verb) {
		SimpleVerbPhraseDTO predicate = getSimpleVerbPhrase();
		if (predicate == null) {
			predicate = getFacade().createSimpleVerbPhraseDTO();
			simpleVerbPhrase = predicate;
		}

		//predicate.setVerb(getFacade().createVerbDTO(verb));
		//predicate.getVerb().setSynonymUid(0);
		
		VerbDTO v = predicate.getVerb();
		if (v == null) {
			v = getFacade().createVerbDTO();			
		}
		v.setName(verb);
		predicate.setVerb(v);
		predicate.getVerb().setSynonymUid(0);

	}

	private SimpleVerbPhraseDTO getSimpleVerbPhrase() {
		if (complexVerbPhrase == null)
			return simpleVerbPhrase;
		else
			return complexVerbPhrase.getSimpleVerbPhrase();
	}

	private ComplexVerbPhraseDTO getComplexVerbPhrase() {
		return complexVerbPhrase;
	}

	public Object getSentence() {
		// TODO get rid of bug
		if (complexVerbPhrase != null) {
			if (complexVerbPhrase.getPreposition() != null) {
				if (complexVerbPhrase.getPreposition().getName() != null)
					if (!complexVerbPhrase.getPreposition().getName()
							.equals(""))
						if (complexVerbPhrase.getObject() != null) {
							if (complexVerbPhrase.getObject().getNoun() != null)
								if (complexVerbPhrase.getObject().getNoun()
										.getName() != null)
									if (!complexVerbPhrase.getObject()
											.getNoun().getName().equals(""))
										return complexVerbPhrase;
							/*
							 * ((NounPhrase) complexVerbPhrase.getObject())
							 * .delete();
							 */
						}
			}
			/*
			 * ((ComplexVerbPhrase) complexVerbPhrase).delete();
			 * complexVerbPhrase = null;
			 */

		}

		if (simpleVerbPhrase != null) {
			if (simpleVerbPhrase.getVerb() != null) {
				if (simpleVerbPhrase.getVerb().getName() != null)
					if (!simpleVerbPhrase.getVerb().getName().equals(""))
						if (simpleVerbPhrase.getObject() != null) {
							if (simpleVerbPhrase.getObject().getNoun() != null)
								if (simpleVerbPhrase.getObject().getNoun()
										.getName() != null)
									if (!simpleVerbPhrase.getObject().getNoun()
											.getName().equals(""))
										return simpleVerbPhrase;
							/*
							 * ((NounPhrase) simpleVerbPhrase.getObject())
							 * .delete();
							 */
						}
			}
			/*
			 * ((SimpleVerbPhrase) simpleVerbPhrase).delete(); simpleVerbPhrase =
			 * null;
			 */

		}

		if (nounPhrase != null) {
			if (nounPhrase.getNoun() != null)
				if (nounPhrase.getNoun().getName() != null)
					if (!nounPhrase.getNoun().getName().equals(""))
						return nounPhrase;
			/*
			 * if(nounPhrase.getDeterminer()==null &&
			 * nounPhrase.getModifier()==null){ ((NounPhrase)
			 * nounPhrase).delete(); nounPhrase = null; }
			 */
		}

		return null;
	}

	@Override
	public boolean isValid() {
		if (this.getPossibleStates(getLastState()).contains(getEndState())
				&& this.getUnmarkedText().trim().equals(""))
			return true;
		return false;
	}

	@Override
	public Object getLastState() {
		Object lastState = getStartState();
		for (Object state : getStatesList())
			if (getStateText(state) != null)
				lastState = state;
		return lastState;

	}
	
	private void connectPhrase (PhraseDTO phrase){
		phrase.connect();
	}	
	
	//modified by t_straszak 12.09.2008
	boolean validateNounPhrase(NounPhraseDTO nf) {
		if (nf == null)
			return false;
		if (nf.getNounText() == null)
			return false;
//		if (nf.getNoun().getName() == null)
//			return false;
		if (nf.getNounText().equals(""))
			return false;
		return true;
	}

	boolean validateSimpleVerbPhrase(SimpleVerbPhraseDTO svf) {
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

	boolean validateComplexVerbPhrase(ComplexVerbPhraseDTO cvf) {
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

}
