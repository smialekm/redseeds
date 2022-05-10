package eu.redseeds.sc.editor.rsl.editors.sentencewidgets;

import org.eclipse.swt.graphics.Color;

import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.constrainedlanguagesentences.ConstrainedLanguageSentenceDTO;

public interface ScenarioSentenceWidget {
	public  static final Color grey = new Color(null, 235, 235, 235);
	public  static final Color white = new Color(null, 255, 255, 255);
	public ConstrainedLanguageSentenceDTO getSentence();
	public boolean hasFocusIncChildren();
	public void refreshWidget();
	public boolean disableFields();
	/**
	 * if Widget is capable to remove selection then selection should be removed, if not just do nothing
	 */
	public void removeSelection();
}
