package eu.redseeds.sc.editor.rsl.editors.sentencewidgets.providers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.graphics.Color;

import eu.redseeds.sc.editor.rsl.editors.sentencewidgets.ITextMarkedListener;
import eu.redseeds.sc.editor.rsl.editors.sentencewidgets.TextMarkedEvent;
import eu.redseeds.sc.editor.rsl.editors.sentencewidgets.grammars.SVOSentenceGrammar;
import eu.redseeds.scl.model.BusinessLayerFacade;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.svosentences.SVOSentenceDTO;

public class SVOSentenceProvider implements ISentenceProvider {

	private List<ITextMarkedListener> _listeners = new ArrayList<ITextMarkedListener>();

	public synchronized void addEventListener(ITextMarkedListener listener)  {
		_listeners.add(listener);
	}

	public synchronized void removeEventListener(ITextMarkedListener listener)   {
		_listeners.remove(listener);
	}

	private synchronized void fireEvent() {
		TextMarkedEvent event = new TextMarkedEvent(this);
	    Iterator<ITextMarkedListener> i = _listeners.iterator();
	    while(i.hasNext())
	    	((ITextMarkedListener) i.next()).handleTextMarkedEvent(event);
	}
	
	@Override
	public boolean isValid() {
		// TODO Auto-generated method stub
		return false;
	}

	private SVOSentenceGrammar grammar = new SVOSentenceGrammar();

	private SVOSentenceDTO sentence;

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

	public SVOSentenceProvider() {
		// setup styles
		noun.fontStyle = SWT.BOLD;
		noun.foreground = blue;
		verb.fontStyle = SWT.ITALIC;
		verb.foreground = red;
		other.foreground = green;
		plain.foreground = black;

		styleRangesMap.put(SVOSentenceGrammar.States.Determiner2, other);
		styleRangesMap.put(SVOSentenceGrammar.States.Determiner3, other);
		styleRangesMap.put(SVOSentenceGrammar.States.Modifier2, other);
		styleRangesMap.put(SVOSentenceGrammar.States.Modifier3, other);
		styleRangesMap.put(SVOSentenceGrammar.States.Preposition, other);
		styleRangesMap.put(SVOSentenceGrammar.States.Verb, verb);
		styleRangesMap.put(SVOSentenceGrammar.States.Noun1, noun);
		styleRangesMap.put(SVOSentenceGrammar.States.Noun2, noun);
		styleRangesMap.put(SVOSentenceGrammar.States.Noun3, noun);
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
				.getPossibleStates((SVOSentenceGrammar.States) state));
	}

	@Override
	public Object getStartState() {
		return grammar.getStartState();
	}

	@Override
	public String getStateText(Object state) {
		return SVOSentenceProviderHelperFactory.getDefault().getStateText(sentence, state);
	}

	@Override
	public String getStateTooltip(Object state) {
		return grammar.getStateTooltip((SVOSentenceGrammar.States) state);
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
		return sentence.getSentenceText();
	}

	@Override
	public void resetState(Object state) {
		setStateText(state, null);
	}

	@Override
	public void setSentence(Object sentence) {
		this.sentence = (SVOSentenceDTO) sentence;
	}

	@Override
	public void setStateText(Object state, String text) {
		SVOSentenceProviderHelperFactory.getDefault().setStateText(sentence, getFacade(), state, text);
		fireEvent();
	}

	@Override
	public void setUnmarkedText(String text) {
		sentence.setSentenceText(text);

	}

	public void setBLFacade(BusinessLayerFacade facade) {
		this.facade = facade;
	}

	public BusinessLayerFacade getFacade() {
		// to be re-implemented after integrating with eclipse workbench
		return facade;
	}

/*	private String getModifier1Text() {
		ModifierDTO mod = getModifier1();
		if (mod != null) {
			return mod.getName();
		}
		return null;
	}*/

/*	private ModifierDTO getModifier1() {
		NounPhraseDTO np = sentence.getSubject();
		if (np != null)
			if (np.getModifier() != null)
				return np.getModifier();
		return null;
	}*/



/*	private NounDTO getNoun1() {
		NounPhraseDTO np = sentence.getSubject();
		if (np != null)
			if (np.getNoun() != null)
				return np.getNoun();
		return null;
	}*/

/*	private NounDTO getNoun2() {
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
	}*/


	/*private NounDTO getNoun3() {
		ComplexVerbPhraseDTO vp = getComplexVerbPhrase();
		if (vp != null) {
			NounPhraseDTO np = vp.getObject();
			if (np != null)
				if (np.getNoun() != null)
					return np.getNoun();
		}
		return null;
	}*/


/*
	private String getQuantifier1Text() {
		DeterminerDTO det = getQuantifier1();
		if (det != null) {
			return det.getName();
		}
		return null;
	}*/

/*	private DeterminerDTO getQuantifier1() {
		NounPhraseDTO np = sentence.getSubject();
		if (np != null)
			if (np.getDeterminer() != null)
				return np.getDeterminer();
		return null;
	}*/



/*	private void setModifier1(String mod) {
		NounPhraseDTO subject = sentence.getSubject();
		if (subject == null) {
			subject = getFacade().createNounPhraseDTO();
			sentence.setSubject(subject);
		}
		ModifierDTO m = subject.getModifier();
		if (m == null) {
			m = getFacade().createModifierDTO();
			subject.setModifier(m);
		}
		m.setName(mod);
	}*/


/*	private void setQuantifier1(String det) {
		NounPhraseDTO subject = sentence.getSubject();
		if (subject == null) {
			subject = getFacade().createNounPhraseDTO();
			sentence.setSubject(subject);
		}
		DeterminerDTO d = subject.getDeterminer();
		if (d == null) {
			d = getFacade().createDeterminerDTO();
			subject.setDeterminer(d);
		}
		d.setName(det);
	}*/


	public Object getSentence(){
		return this.sentence;
	}

	@Override
	public Object getLastState() {
		Object lastState = getStartState();
		for (Object state : getStatesList())
			if (getStateText(state) != null)
				lastState = state;
		return lastState;

	}

}
