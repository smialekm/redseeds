package eu.redseeds.sc.query.ui.view.composite.scenariosDiff;

abstract class ConstrainedLanguageSentenceMatchFactory {

	public static IConstrainedLanguageSentenceMatch getDefault(IDiffMarkedConstrainedLanguageSentence currentDiffMarkedSVOSentence,
			IDiffMarkedConstrainedLanguageSentence pastDiffMarkedSVOSentence) {
		return new ConstrainedLanguageSentenceMatch(currentDiffMarkedSVOSentence, pastDiffMarkedSVOSentence);
	}
}
