package eu.redseeds.sc.editor.rsl.editors.sentencewidgets.grammars;

import java.util.Arrays;
import java.util.List;

public class PhraseGrammar extends AbstactGrammar<PhraseGrammar.States> {
	public enum States {
		Start, Determiner1, Modifier1, Noun1, Verb, Determiner2, Modifier2, Noun2, Preposition, Determiner3, Modifier3, Noun3, End
	}

	public List<PhraseGrammar.States> getStatesList() {
		List<PhraseGrammar.States> states = Arrays.asList(PhraseGrammar.States
				.values());
		return states;// transitions.keySet();
	}

	final protected void setupStates() {
		transitions.put(States.Start, Arrays.asList(States.Determiner1,
				States.Modifier1, States.Noun1, States.Verb));

		transitions.put(States.Determiner1, Arrays.asList(States.Modifier1));

		transitions.put(States.Modifier1, Arrays.asList(States.Noun1));

		transitions.put(States.Noun1, Arrays.asList(States.End));

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

	final protected void setupStatesTooltips() {
		stateNames.put(States.Noun1, "noun");
		stateNames.put(States.Noun2, "noun");
		stateNames.put(States.Noun3, "noun");
		stateNames.put(States.Verb, "verb");
		stateNames.put(States.Determiner1, "determiner");
		stateNames.put(States.Determiner2, "determiner");
		stateNames.put(States.Determiner3, "determiner");
		stateNames.put(States.Modifier1, "modifier");
		stateNames.put(States.Modifier2, "modifier");
		stateNames.put(States.Modifier3, "modifier");
		stateNames.put(States.Preposition, "preposition");
	}

	final public States getStartState() {
		return States.Start;
	}

	final public States getEndState() {
		return States.End;
	}

}