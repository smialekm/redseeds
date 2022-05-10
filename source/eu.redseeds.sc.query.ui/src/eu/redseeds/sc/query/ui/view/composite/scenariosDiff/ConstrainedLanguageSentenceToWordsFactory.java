package eu.redseeds.sc.query.ui.view.composite.scenariosDiff;

abstract class ConstrainedLanguageSentenceToWordsFactory {

	public static IConstrainedLanguageToWords getDefault(){
		return new ConstrainedLanguageToWordsByType();
	}
}
