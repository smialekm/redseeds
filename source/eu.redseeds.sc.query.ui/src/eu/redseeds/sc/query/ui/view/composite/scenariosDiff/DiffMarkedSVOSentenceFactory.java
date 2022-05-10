package eu.redseeds.sc.query.ui.view.composite.scenariosDiff;

import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.constrainedlanguagesentences.ConstrainedLanguageSentenceDTO;

abstract class DiffMarkedConstrainedLanguageSentenceFactory {

	public static IDiffMarkedConstrainedLanguageSentence getDefault(ConstrainedLanguageSentenceDTO sentence){
		return new SimpleDiffMarkedConstrainedLanguageSentence(sentence);
	}
}
