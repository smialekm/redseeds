package eu.redseeds.sc.editor.rsl.editors.sentencewidgets;

import org.eclipse.jface.text.DocumentEvent;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentAdapter;
import org.eclipse.jface.text.IDocumentAdapterExtension;
import org.eclipse.jface.text.IDocumentListener;
import org.eclipse.jface.text.TextViewer;
import org.eclipse.swt.custom.StyledTextContent;
import org.eclipse.swt.custom.TextChangeListener;
import org.eclipse.swt.widgets.Composite;

public class TextViewerUCEditor extends TextViewer {

	public TextViewerUCEditor(Composite parent, int styles) {
		super(parent, styles);
	}

	@Override
	protected IDocumentAdapter createDocumentAdapter() {
		return new GenericSentenceDocumentAdapter(super.createDocumentAdapter());
	}

	/**
	 * This class is small (method getLine(int)) modification of DefaultDocumentAdapter. This class is
	 * necessary to resolve backspace bug in UC editor with TextViewer with
	 * content assists.
	 */
	private static class GenericSentenceDocumentAdapter implements IDocumentAdapter, IDocumentListener,
			IDocumentAdapterExtension {
		private final IDocumentAdapter documentAdapter;
		private GenericSentenceDocumentContent genericSentenceDocumentContent;

		public GenericSentenceDocumentAdapter(IDocumentAdapter documentAdapter) {
			super();
			this.documentAdapter = documentAdapter;
		}

		private StyledTextContent getStyledTextContent() {
			if (genericSentenceDocumentContent != null) {
				return genericSentenceDocumentContent.getGenericSentenceContent();
			}
			return null;
		}

		public void addTextChangeListener(TextChangeListener listener) {
			documentAdapter.addTextChangeListener(listener);
		}

		public void documentAboutToBeChanged(DocumentEvent event) {
			((IDocumentListener) documentAdapter).documentAboutToBeChanged(event);
		}

		public void documentChanged(DocumentEvent event) {
			((IDocumentListener) documentAdapter).documentChanged(event);
		}

		public int getCharCount() {
			return documentAdapter.getCharCount();
		}

		/**
		 * This method must use getStyledTextContent instead of
		 * documentAdapter.getLine(lineIndex)
		 */
		public String getLine(int lineIndex) {
			StyledTextContent styledTextContent = getStyledTextContent();
			if (styledTextContent != null) {
				return getStyledTextContent().getLine(lineIndex);
			}
			return "";
		}

		public int getLineAtOffset(int offset) {
			return documentAdapter.getLineAtOffset(offset);
		}

		public int getLineCount() {
			return documentAdapter.getLineCount();
		}

		public String getLineDelimiter() {
			return documentAdapter.getLineDelimiter();
		}

		public int getOffsetAtLine(int lineIndex) {
			return documentAdapter.getOffsetAtLine(lineIndex);
		}

		public String getTextRange(int start, int length) {
			return documentAdapter.getTextRange(start, length);
		}

		public void removeTextChangeListener(TextChangeListener listener) {
			documentAdapter.removeTextChangeListener(listener);
		}

		public void replaceTextRange(int start, int replaceLength, String text) {
			documentAdapter.replaceTextRange(start, replaceLength, text);
		}

		public void resumeForwardingDocumentChanges() {
			((IDocumentAdapterExtension) documentAdapter).resumeForwardingDocumentChanges();
		}

		public void setDocument(IDocument document) {
			if (document != null) {
				if (document instanceof GenericSentenceDocumentContent) {
					genericSentenceDocumentContent = (GenericSentenceDocumentContent) document;
					documentAdapter.setDocument(document);
				} else {
					throw new IllegalArgumentException(
							"only document of type GenericSentenceDocumentContent is supported document:" + document);
				}
			} else {
				genericSentenceDocumentContent = null;
			}
		}

		public void setText(String text) {
			documentAdapter.setText(text);
		}

		public void stopForwardingDocumentChanges() {
			((IDocumentAdapterExtension) documentAdapter).stopForwardingDocumentChanges();
		}
	}
}
