package eu.redseeds.sc.editor.rsl.editors.sentencewidgets.grammars;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public abstract class AbstactGrammar<T extends Enum<T>> {

	protected SortedMap<T, List<T>> transitions = new TreeMap<T, List<T>>();

	protected Map<T, String> stateNames = new HashMap<T, String>();

	// protected T states;

	private Class<T> enumClass;

	public AbstactGrammar() {
		setupStates();
		setupStatesTooltips();
	}

	final public List<T> getPossibleStates(T state) {
		return transitions.get(state);
	}

	// make abstract
	abstract public List<T> getStatesList();/*
											 * { return transitions.keySet(); }
											 */

	public T getStates() {
		try {
			return this.enumClass.newInstance();
		} catch (Exception e) {
			return null;
		}
	}

	public String getStateTooltip(T state) {
		return stateNames.get(state);
	}

	abstract public T getStartState();

	abstract public T getEndState();

	// this method should establish grammar in concrete implementation classes
	// (see DummyGrammar implementation)
	abstract protected void setupStates();

	abstract protected void setupStatesTooltips();

}
