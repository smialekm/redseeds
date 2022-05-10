package eu.redseeds.sc.editor.rsl.editors.sentencewidgets;

import org.eclipse.swt.widgets.Composite;

import eu.redseeds.sc.editor.rsl.editors.UseCaseScenarioView;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.svosentences.SVOSentenceDTO;

public class SVOSentenceWidgetFactory {
	private SVOSentenceWidgetFactory() {
	}

	public static SVOSentenceWidget getDefaultInstance(Composite parent, int style,
			SVOSentenceDTO sentence, UseCaseScenarioView scenView){

		return getInstanceWithTextViewer(parent,style,sentence,scenView);
	}

	/**
	 * This one have content assist and is based on TextViewer component
	 *
	 * @param parent
	 * @param style
	 * @param sentence
	 * @param scenView
	 * @return
	 */
	public static SVOSentenceWidget getInstanceWithTextViewer(Composite parent, int style,
			SVOSentenceDTO sentence, UseCaseScenarioView scenView) {
		return new SVOSentenceWidgetWithTextViewer(parent, style, sentence, scenView);
	}

	/**
	 *This doesn't have content assist and is based on StyledText component )
	 * @param parent
	 * @param style
	 * @param sentence
	 * @param scenView
	 * @return
	 * @deprecated this is old version you should use {@link #getInstanceWithTextViewer(Composite, int, SVOSentenceDTO, UseCaseScenarioView)
	 */
	public static SVOSentenceWidget getInstanceWithStyledText(Composite parent, int style,
			SVOSentenceDTO sentence, UseCaseScenarioView scenView) {
		return new SVOSentenceWidgetWithStyledText(parent, style, sentence, scenView);
	}

}
