package eu.redseeds.sc.editor.rsl.editors.sentencewidgets;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.contentassist.ContentAssistant;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import eu.redseeds.sc.editor.rsl.editors.UseCaseScenarioView;
import eu.redseeds.sc.editor.rsl.editors.sentencewidgets.providers.SVOSentenceProvider;
import eu.redseeds.scl.model.rsl.rsldomainelements.domainelements.DomainSpecificationDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.svosentences.SVOSentenceDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementsPackageDTO;

public class SVOSentenceWidgetWithTextViewer extends SVOSentenceWidget{

	private GenericSentenceWidgetWithTextViewer sentenceWidget;

	public SVOSentenceWidgetWithTextViewer(Composite parent, int style,
			SVOSentenceDTO sentence, UseCaseScenarioView scenView){
		super(parent, style,sentence,scenView);
	}

	private ContentAssistant prepareContentAssistant(UseCaseScenarioView scenView){
		
		
		// to prevent null pointer exception for nested subpackages
		RequirementsPackageDTO ucPac = scenView.getScenario().getParent().getParent();
		while (ucPac.getParent()!=null)
			ucPac=ucPac.getParent();

		DomainSpecificationDTO domainSpecificationDTO = ucPac.getRequirementsSpecificationParent().getDomainSpecificationDTO();

		SVOSentenceTextAssistProcessor sentenceTextAssistProcessor = new SVOSentenceTextAssistProcessor(domainSpecificationDTO,sentenceWidget);

		final ContentAssistant assistant = new ContentAssistant();
		assistant.setContentAssistProcessor(sentenceTextAssistProcessor, IDocument.DEFAULT_CONTENT_TYPE);
		assistant.setStatusMessage("Select a notion");
		assistant.setStatusLineVisible(true);
		return assistant;
	}

	@Override
	public GenericSentenceWidgetAbstract getSentenceWidget() {
		if(sentenceWidget==null){
			throw new IllegalStateException("first call SVOSentenceWidgetWithTextViewer.initSentenceWidget(SVOSentenceProvider). sentenceWidget==null");
		}
		return sentenceWidget;
	}

	@Override
	protected void initSentenceWidget(SVOSentenceProvider prov){
		if(sentenceWidget==null){
			sentenceWidget=new GenericSentenceWidgetWithTextViewer(this, SWT.NONE, prov);
		}
	}
	@Override
	protected void addAdditionalFunctionalityToSentenceWidget(UseCaseScenarioView scenView){
		sentenceWidget.addContentAssist(prepareContentAssistant(scenView));
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

