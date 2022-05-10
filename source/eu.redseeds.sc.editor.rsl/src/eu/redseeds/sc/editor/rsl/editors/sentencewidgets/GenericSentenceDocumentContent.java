package eu.redseeds.sc.editor.rsl.editors.sentencewidgets;

import org.eclipse.jface.text.AbstractDocument;
import org.eclipse.jface.text.DefaultLineTracker;

public class GenericSentenceDocumentContent extends AbstractDocument{

	private final GenericSentenceContent genericSentenceContent;

	/**
	 * Creates a new document with the given initial content.
	 *
	 * @param initialContent the document's initial content
	 */
	public GenericSentenceDocumentContent(GenericSentenceContent genericSentenceContent) {
		super();
		this.genericSentenceContent=genericSentenceContent;
		setLineTracker(new DefaultLineTracker());
		setTextStore(new GenericSentenceContentToITextStoreAdapter(genericSentenceContent));
		completeInitialization();
	}

	public GenericSentenceContent getGenericSentenceContent() {
		return genericSentenceContent;
	}
}
