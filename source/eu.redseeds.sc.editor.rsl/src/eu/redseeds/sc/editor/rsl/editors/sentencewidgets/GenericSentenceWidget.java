package eu.redseeds.sc.editor.rsl.editors.sentencewidgets;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Composite;

import eu.redseeds.sc.editor.rsl.editors.sentencewidgets.providers.ISentenceProvider;

public class GenericSentenceWidget extends GenericSentenceWidgetAbstract {
	private StyledText textField;

	public GenericSentenceWidget(Composite arg0, int arg1,
			ISentenceProvider contentProvider) {
		super(arg0, arg1,contentProvider);
		this.resetStyleRanges();
	}


	@Override
	protected StyledText getStyledText(){
		return textField;
	}

	@Override
	protected void initialize() {
		textField = new StyledText(this, SWT.SINGLE);
		textField.setContent(getMarker());
	}

	@Override
	public void removeSelection(){
		if(textField!=null){
			textField.setSelection(0, 0);
		}
	}

}
