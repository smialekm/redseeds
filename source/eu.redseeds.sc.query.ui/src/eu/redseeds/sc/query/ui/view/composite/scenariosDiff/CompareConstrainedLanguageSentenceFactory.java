package eu.redseeds.sc.query.ui.view.composite.scenariosDiff;

abstract class CompareConstrainedLanguageSentenceFactory {

	public static ICompareConstrainedLanguageSentence getDefault(IConstrainedLanguageToWords sentenceToWords){
		return new CompareSentencesToFirstMatchStrategy(sentenceToWords);
	}
}
