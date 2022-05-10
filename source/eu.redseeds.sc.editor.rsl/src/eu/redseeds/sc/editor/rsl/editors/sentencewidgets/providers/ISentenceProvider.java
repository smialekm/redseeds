package eu.redseeds.sc.editor.rsl.editors.sentencewidgets.providers;

import java.util.List;

import org.eclipse.swt.custom.StyleRange;

public interface ISentenceProvider {
	void setStateText(Object state, String text);

	String getStateText(Object state);

	void setUnmarkedText(String text);

	String getUnmarkedText();

	void resetState(Object state);

	List<Object> getStatesList();

	void setSentence(Object sentence);

	Object getSentence();

	// directly from grammar
	Object getStates();

	public String getStateTooltip(Object state);

	public List<Object> getPossibleStates(Object state);

	public Object getEndState();

	public Object getStartState();

	public StyleRange getStyleForState(Object State);

	boolean isValid();

	public Object getLastState();


}
