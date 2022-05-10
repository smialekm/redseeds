/**
 *
 */
package eu.redseeds.sc.editor.rsl.editors.sentencewidgets;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.Vector;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledTextContent;
import org.eclipse.swt.custom.TextChangeListener;
import org.eclipse.swt.custom.TextChangedEvent;
import org.eclipse.swt.custom.TextChangingEvent;

import eu.redseeds.sc.editor.rsl.editors.sentencewidgets.providers.ISentenceProvider;

/**
 * @author bojarsj1
 *
 */
public class GenericSentenceContent implements StyledTextContent {

	private ISentenceProvider contentProvider;

	int lastMarkPosition = 0;

	private final static String LineDelimiter = System
			.getProperty("line.separator");

	Vector<TextChangeListener> textListeners = new Vector<TextChangeListener>();
	// stores text listeners for event sending

	/**
	 * Adds a <code>TextChangeListener</code> listening for
	 * <code>TextChangingEvent</code> and <code>TextChangedEvent</code>. A
	 * <code>TextChangingEvent</code> is sent before changes to the text
	 * occur. A <code>TextChangedEvent</code> is sent after changes to the
	 * text occurred.
	 * <p>
	 *
	 * @param listener
	 *            the listener
	 * @exception IllegalArgumentException
	 *                <ul>
	 *                <li>ERROR_NULL_ARGUMENT when listener is null</li>
	 *                </ul>
	 */

	public GenericSentenceContent(ISentenceProvider contentProvider) {
		this.contentProvider = contentProvider;
	}

	@Override
	public void addTextChangeListener(TextChangeListener listener) {
		if (listener == null)
			error(SWT.ERROR_NULL_ARGUMENT);
		textListeners.addElement(listener);
	}

	/**
	 * @return the logical length of the text store
	 * @see org.eclipse.swt.custom.StyledTextContent#getCharCount()
	 */
	@Override
	public int getCharCount() {
		return getText().length();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.eclipse.swt.custom.StyledTextContent#getLine(int)
	 */
	@Override
	public String getLine(int arg0) {
		return getText();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.eclipse.swt.custom.StyledTextContent#getLineAtOffset(int)
	 */
	@Override
	public int getLineAtOffset(int arg0) {
		return 0;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.eclipse.swt.custom.StyledTextContent#getLineCount()
	 */
	@Override
	public int getLineCount() {
		return 1;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.eclipse.swt.custom.StyledTextContent#getLineDelimiter()
	 */
	@Override
	public String getLineDelimiter() {
		return LineDelimiter;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.eclipse.swt.custom.StyledTextContent#getOffsetAtLine(int)
	 */
	@Override
	public int getOffsetAtLine(int arg0) {
		return 0;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.eclipse.swt.custom.StyledTextContent#getTextRange(int, int)
	 */
	@Override
	public String getTextRange(int start, int offset) {
		return getText().substring(start, start + offset);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.eclipse.swt.custom.StyledTextContent#removeTextChangeListener(org.eclipse.swt.custom.TextChangeListener)
	 */
	@Override
	public void removeTextChangeListener(TextChangeListener listener) {
		if (listener == null)
			error(SWT.ERROR_NULL_ARGUMENT);
		textListeners.remove(listener);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.eclipse.swt.custom.StyledTextContent#replaceTextRange(int, int,
	 *      java.lang.String)
	 */
	@Override
	public void replaceTextRange(int start, int replaceLength, String newText) {

		// inform listeners
		TextChangingEvent event = new TextChangingEvent(this);
		event.start = start;
		event.replaceLineCount = 0; // byc moze 1?
		event.newLineCount = 1;
		event.replaceCharCount = replaceLength;
		event.newCharCount = newText.length();
		sendTextChangingEvent(event);

		// case where text replacement is in unmarked text
		if (start > this.lastMarkPosition) {
			String before, after, actual;
			actual = getText();
			before = actual.substring(0, start);
			after = actual.substring(start + replaceLength, actual.length());
			actual = before + newText + after;
			contentProvider.setUnmarkedText(actual.substring(lastMarkPosition,
					actual.length()));

		} else {
			Object startState = contentProvider.getStartState();
			boolean singleStateRange = false;

			for (Map.Entry<Object, Integer> entry : getStatesOffsets()
					.entrySet())
				if (entry.getValue() >= start) {
					startState = entry.getKey();
					if (entry.getValue() >= start + replaceLength)
						singleStateRange = true;
					break;
				}
			if (singleStateRange) {
				int stateStart, stateEnd;
				stateEnd = getStatesOffsets().get(startState);
				stateStart = stateEnd
						- contentProvider.getStateText(startState).length();
				String before, after, actual;
				actual = getText();

				before = actual.substring(0, start);
				after = actual
						.substring(start + replaceLength, actual.length());
				actual = before + newText + after;
				String correctActual = actual.substring(stateStart, stateEnd - replaceLength+ newText.length());

				//TODO TP:If we had rmv whole sentence then rmv from statesOffsets. If this code work ok then rmv org and leave this
				if("".equals(correctActual.trim())){
					contentProvider.resetState(startState);
					getStatesOffsets().remove(startState);
				}else{
					contentProvider.setStateText(startState, correctActual);
				}
				//org
//				contentProvider.setStateText(startState, correctActual);

			} else {
				String before, after, actual;
				actual = getText();
				this.resetStates(start);
				before = actual.substring(0, start);
				after = actual
						.substring(start + replaceLength, actual.length());
				actual = before + newText + after;
				if (lastMarkPosition > actual.length()) {
					this.resetStates(lastMarkPosition);
					contentProvider.setUnmarkedText(" ");
				} else
					contentProvider.setUnmarkedText(actual.substring(
							lastMarkPosition, actual.length()));
			}

		}
		lastMarkPosition = getText().length()
				- contentProvider.getUnmarkedText().length();
		TextChangedEvent event1 = new TextChangedEvent(this);
		sendTextChangedEvent(event1);

	}

	/**
	 * Sets the content to text and removes the gap since there are no sensible
	 * predictions about where the next change will occur.
	 * <p>
	 *
	 * @param text
	 *            the text
	 */
	@Override
	public void setText(String text) {
		contentProvider.setUnmarkedText(text);
		for (Object state : contentProvider.getStatesList())
			contentProvider.resetState(state);
		lastMarkPosition = getText().length()
				- contentProvider.getUnmarkedText().length();
		TextChangedEvent event = new TextChangedEvent(this);
		sendTextChangedEvent(event);
	}

	/**
	 * Sends the text listeners the TextChanged event.
	 */
	void sendTextChangingEvent(TextChangingEvent event) {
		for (int i = 0; i < textListeners.size(); i++) {
			((TextChangeListener) textListeners.elementAt(i))
					.textChanging(event);
			// .handleEvent(event);
		}
	}

	/**
	 * Sends the text listeners the TextChanged event.
	 */
	void sendTextChangedEvent(TextChangedEvent event) {
		for (int i = 0; i < textListeners.size(); i++) {
			((TextChangeListener) textListeners.elementAt(i))
					.textChanged(event);
			// .handleEvent(event);
		}
	}

	/**
	 * Reports an SWT error.
	 * <p>
	 *
	 * @param code
	 *            the error code
	 */
	void error(int code) {
		SWT.error(code);
	}

	public Map<Object, String> getNextSates() {
		Map<Object, String> possibleStates = new HashMap<Object, String>();
		for (Object state : contentProvider.getPossibleStates(getLastState()))
			possibleStates.put(state, contentProvider.getStateTooltip(state));
		return possibleStates;
	}

	public void mark(Object state, int offset) {
		contentProvider.setStateText(state, getText().substring(
				lastMarkPosition, lastMarkPosition + offset).trim());
		contentProvider.setUnmarkedText(contentProvider.getUnmarkedText()
				.substring(offset, contentProvider.getUnmarkedText().length()));
		lastMarkPosition = getText().length()
				- contentProvider.getUnmarkedText().length();

	}

	public int getLastMarkPosition() {
		return lastMarkPosition;
	}

	private Object getLastState() {
		return contentProvider.getLastState();

	}

	public SortedMap<Object, Integer> getStatesOffsets() {
		int lastOffset = 0;
		SortedMap<Object, Integer> statesOffsets = new TreeMap<Object, Integer>();
		for (Object state : contentProvider.getStatesList()) {
			if (contentProvider.getStateText(state) != null) {
				statesOffsets.put(state, contentProvider.getStateText(state)
						.length()
						+ lastOffset);
				lastOffset = contentProvider.getStateText(state).length() + 1
						+ lastOffset;
			}
		}
		if (statesOffsets.size() > 0)
			statesOffsets.put(statesOffsets.lastKey(), statesOffsets
					.get(statesOffsets.lastKey()));
		return statesOffsets;
	}

	public String getText() {
		String text = "";
		for (Object state : contentProvider.getStatesList()) {
			String stateText = contentProvider.getStateText(state);
			if (stateText != null){
				text += stateText+ " ";
			}
		}
		text += contentProvider.getUnmarkedText();
		return text;

	}

	public Object getStateAtOffset(int offset) {
		for (Map.Entry<Object, Integer> entry : getStatesOffsets().entrySet()){
			if (entry.getValue() > offset || offset==0) {
				return entry.getKey();
			}
		}
		return null;
	}

	public void resetStates(int offset) {
		for (Map.Entry<Object, Integer> entry : getStatesOffsets().entrySet()){
			if (entry.getValue() > offset || offset==0) {
				contentProvider.resetState(entry.getKey());
			}
		}
		lastMarkPosition = getText().length()
				- contentProvider.getUnmarkedText().length();
	}

	public ISentenceProvider getContentProvider() {
		return contentProvider;
	}

	public void setSentenceProvider(ISentenceProvider contentProvider) {
		this.contentProvider = contentProvider;
		lastMarkPosition = getText().length()
				- contentProvider.getUnmarkedText().length();
	}

	public Map<Integer, StyleRange> getStyleRangesOffsets() {
		Map<Integer, StyleRange> styleRangesOffsets = new TreeMap<Integer, StyleRange>();
		for (Map.Entry<Object, Integer> style : this.getStatesOffsets()
				.entrySet())
			styleRangesOffsets.put(style.getValue(), this.contentProvider
					.getStyleForState(style.getKey()));
		styleRangesOffsets.put(this.getText().length(), contentProvider
				.getStyleForState(null));
		return styleRangesOffsets;
	}
}
