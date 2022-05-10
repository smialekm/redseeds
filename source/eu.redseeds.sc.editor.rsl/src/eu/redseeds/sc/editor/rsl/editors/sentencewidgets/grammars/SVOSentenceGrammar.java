package eu.redseeds.sc.editor.rsl.editors.sentencewidgets.grammars;

import java.util.Arrays;
import java.util.List;

public class SVOSentenceGrammar extends
		AbstactGrammar<SVOSentenceGrammar.States> {
	public enum States {
		Start, Noun1, Verb, Determiner2, Modifier2, Noun2, Preposition, Determiner3, Modifier3, Noun3, End
	}

	@Override
	public List<SVOSentenceGrammar.States> getStatesList() {
		List<SVOSentenceGrammar.States> states = Arrays
				.asList(SVOSentenceGrammar.States.values());
		return states;// transitions.keySet();
	}

	@Override
	final protected void setupStates() {
		transitions.put(States.Start, Arrays.asList(States.Noun1));
		transitions.put(States.Noun1, Arrays.asList(States.Verb));
		transitions.put(States.Verb, Arrays.asList(States.Determiner2,
				States.Modifier2, States.Noun2));
		transitions.put(States.Determiner2, Arrays.asList(States.Modifier2,
				States.Noun2));
		transitions.put(States.Modifier2, Arrays.asList(States.Noun2));
		transitions.put(States.Noun2, Arrays.asList(States.Preposition,
				States.End));
		transitions.put(States.Preposition, Arrays.asList(States.Determiner3,
				States.Modifier3, States.Noun3));
		transitions.put(States.Determiner3, Arrays.asList(States.Modifier3,
				States.Noun3));
		transitions.put(States.Modifier3, Arrays.asList(States.Noun3));
		transitions.put(States.Noun3, Arrays.asList(States.End));
		transitions.put(States.End, Arrays.asList(States.End));

	}

	@Override
	final protected void setupStatesTooltips() {
		stateNames.put(States.Noun1, "noun");
		stateNames.put(States.Noun2, "noun");
		stateNames.put(States.Noun3, "noun");
		stateNames.put(States.Verb, "verb");
		stateNames.put(States.Determiner2, "determiner");
		stateNames.put(States.Determiner3, "determiner");
		stateNames.put(States.Modifier2, "modifier");
		stateNames.put(States.Modifier3, "modifier");
		stateNames.put(States.Preposition, "preposition");
	}

	@Override
	final public States getStartState() {
		return States.Start;
	}

	@Override
	final public States getEndState() {
		return States.End;
	}

}