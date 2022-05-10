package eu.redseeds.sc.editor.rsl.editors.domain.controls;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.custom.VerifyKeyListener;
import org.eclipse.swt.events.VerifyEvent;

public class EditHyperlinkedTextListener implements VerifyKeyListener {
	
	private StyledText descriptionText = null;

	public EditHyperlinkedTextListener(StyledText descriptionText) {
		super();
		this.descriptionText = descriptionText;
	}

	@Override
	public void verifyKey(VerifyEvent event) {
		
		if(event.keyCode == SWT.CTRL 
				|| event.keyCode == SWT.ALT) {//ignore these keys
			return;
		}
		if(event.stateMask == SWT.SHIFT) {//ignore marking (arrows + shift)
			if(event.keyCode == SWT.ARROW_DOWN
					|| event.keyCode == SWT.ARROW_LEFT
					|| event.keyCode == SWT.ARROW_RIGHT
					|| event.keyCode == SWT.ARROW_UP) {
				return;
			}
		}
		
		if(event.keyCode == SWT.ARROW_DOWN
				|| event.keyCode == SWT.ARROW_LEFT
				|| event.keyCode == SWT.ARROW_RIGHT
				|| event.keyCode == SWT.ARROW_UP) { //don't do anything on arrow-press either
			return;
		}

//		if(event.character == 127 || event.character == '\b') {//delete or backspace pressed
			if(descriptionText.getText().length() == 0) {//nothing to delete
				return;
			}
			StyleRange[] newStyles = new StyleRange[0]; //empty style array for removing existing styles
			int startStyle = 0;
			int endStyle = 0;
			if(descriptionText.getSelectionCount() > 0) {
				startStyle = Math.min(descriptionText.getSelectionRange().x, descriptionText.getSelectionRange().y);
				endStyle = Math.max(descriptionText.getSelectionRange().x, descriptionText.getSelectionRange().y);
			}
			else {
				startStyle = descriptionText.getCaretOffset();
				endStyle = startStyle;
			}
			int caretOffset = descriptionText.getCaretOffset();
			if(caretOffset > 0) { //is not at position 0 in the text
				caretOffset--;
			}
			StyleRange style = descriptionText.getStyleRangeAtOffset(caretOffset);
			while(style != null && endStyle < descriptionText.getText().length() - 1) { //look for style forward from caret position
				style = descriptionText.getStyleRangeAtOffset(++endStyle);
			}
			style = descriptionText.getStyleRangeAtOffset(caretOffset); //ensure not null
			while(style != null && startStyle > 0) { //look for style backward from caret position
				style = descriptionText.getStyleRangeAtOffset(--startStyle);
			}
			if(event.character == ' ' 
				|| event.character == ',' 
				|| event.character == ';' 
				|| event.character == ':' 
				|| event.character == '!' 
				|| event.character == '.' 
				|| event.character == '?'){
				if(endStyle == descriptionText.getCaretOffset()) {
					return;// no change - end of word
				}
			}
			descriptionText.replaceStyleRanges(startStyle, endStyle - startStyle, newStyles);
//		}
	}

}
