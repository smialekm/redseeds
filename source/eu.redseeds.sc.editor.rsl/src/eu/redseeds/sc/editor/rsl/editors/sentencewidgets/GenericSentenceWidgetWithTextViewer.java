package eu.redseeds.sc.editor.rsl.editors.sentencewidgets;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.TextViewer;
import org.eclipse.jface.text.contentassist.ContentAssistant;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.widgets.Composite;

import eu.redseeds.sc.editor.rsl.editors.sentencewidgets.providers.ISentenceProvider;

public class GenericSentenceWidgetWithTextViewer extends GenericSentenceWidgetAbstract{
	private TextViewer textViewer;

	public GenericSentenceWidgetWithTextViewer(Composite composite, int style,
			ISentenceProvider contentProvider) {

		super(composite, style,contentProvider);
	}

	public void addContentAssist(final ContentAssistant assistant){
		assistant.install(textViewer);
		textViewer.getControl().addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.character == ' ' && e.stateMask == SWT.CTRL) { // ctrl-space as a trigger
					if(textViewer.getTextWidget().getCaretOffset()>=getMarker().lastMarkPosition){
						assistant.showPossibleCompletions();
					}
				}
			}
		});
	}

	public void addSelectionListener(final ISelectionChangedListener selectionChangedListener){
		textViewer.addSelectionChangedListener(selectionChangedListener);
	}

	public IDocument getDocument(){
		return textViewer.getDocument();
	}

	@Override
	protected StyledText getStyledText(){
		return textViewer.getTextWidget();
	}

	@Override
	protected void initialize() {
		GenericSentenceDocumentContent genericSentenceDocumentContent = new GenericSentenceDocumentContent(getMarker());
		textViewer=new TextViewerUCEditor(this, SWT.SINGLE);
		textViewer.setDocument(genericSentenceDocumentContent);

	}

	public void markedCompletionProposal(ICompletionProposal selectedProposal,Object state){
			 if (selectedProposal.getDisplayString().length() > 0) {
				 	getMarker().mark(state, selectedProposal.getDisplayString().length());
				 	resetStyleRanges();
				 	textViewer.getTextWidget().setCaretOffset(getMarker().lastMarkPosition);
			}
	}

	@Override
	public void removeSelection(){
		if(textViewer!=null){
			textViewer.setSelectedRange(0, 0);
		}
	}
}
