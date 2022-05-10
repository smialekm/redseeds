package eu.redseeds.sc.query.ui.view.composite.scenariosDiff;


interface IConstrainedLanguageSentenceMatch {
	public IDiffMarkedConstrainedLanguageSentence getCurrentDiffMarkedConstrainedLanguageSentence();
	public IDiffMarkedConstrainedLanguageSentence getPastDiffMarkedConstrainedLanguageSentence();
	public boolean isMissing();
	public boolean isAdded();
	public boolean isSimilar();
}
