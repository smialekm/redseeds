package eu.redseeds.sc.query.ui.view.composite.scenariosDiff;

import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.constrainedlanguagesentences.ConstrainedLanguageSentenceDTO;

public interface IConstrainedLanguageToWords {
	public String[] splitSentenceToWords(ConstrainedLanguageSentenceDTO sentence);
}
