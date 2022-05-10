/**
 *
 */
package eu.redseeds.sc.editor.rsl.editors.sentencewidgets;

import org.eclipse.jface.text.ITextStore;

class GenericSentenceContentToITextStoreAdapter implements ITextStore{

	private final GenericSentenceContent genericSentenceContent;

	public GenericSentenceContentToITextStoreAdapter(GenericSentenceContent genericSentenceContent) {
		super();
		this.genericSentenceContent = genericSentenceContent;
	}

	@Override
	public char get(int offset) {
		return genericSentenceContent.getText().charAt(offset);
	}

	@Override
	public String get(int offset, int length) {
		return genericSentenceContent.getText().substring(offset, length + offset);
	}

	@Override
	public int getLength() {
		return genericSentenceContent.getText().length();
	}

	@Override
	public void replace(int offset, int length, String text) {
		genericSentenceContent.replaceTextRange(offset, length, text);

	}

	@Override
	public void set(String text) {
		genericSentenceContent.setText(text);

	}
}