package eu.redseeds.sc.editor.rsl.editors.sentencewidgets;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import eu.redseeds.sc.editor.rsl.editors.UseCaseScenarioView;
import eu.redseeds.sc.editor.rsl.editors.sentencewidgets.providers.SVOSentenceProvider;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.svosentences.SVOSentenceDTO;

public class SVOSentenceWidgetWithStyledText extends SVOSentenceWidget{

	private GenericSentenceWidget sentenceWidget;

	public SVOSentenceWidgetWithStyledText(Composite parent, int arg1,
			SVOSentenceDTO sentence, UseCaseScenarioView scenView){
		super(parent, arg1,sentence,scenView);
	}

	@Override
	public GenericSentenceWidgetAbstract getSentenceWidget() {
		if(sentenceWidget==null){
			throw new IllegalStateException("first call SVOSentenceWidgetWithStyledText.initSentenceWidget(SVOSentenceProvider). sentenceWidget==null");
		}
		return sentenceWidget;
	}

	@Override
	protected void initSentenceWidget(SVOSentenceProvider prov){
		if(sentenceWidget==null){
			sentenceWidget=new GenericSentenceWidget(this, SWT.NONE, prov);
		}
	}
	@Override
	protected void addAdditionalFunctionalityToSentenceWidget(UseCaseScenarioView scenView){
	}

	@Override
	public boolean disableFields() {
		boolean isClipboardMember = super.disableFields();
		sentenceWidget.disableFields(isClipboardMember);
		return isClipboardMember;
	}

	@Override
	public void removeSelection() {
		if(sentenceWidget!=null && !sentenceWidget.isDisposed()){
			sentenceWidget.removeSelection();
		}
	}

}
