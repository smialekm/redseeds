package eu.redseeds.sc.query.ui.view.composite.scenariosDiff;

abstract class DiffSentencesPairStrategyFactory {

	public static IDiffSentencesPairStrategy getDefault(IConstrainedLanguageToWords sentenceToWords){
		return getWordEqualStrategy(sentenceToWords);
	}
	public static IDiffSentencesPairStrategy getWordEqualStrategy(IConstrainedLanguageToWords sentenceToWords){
		return new WordEqualInOrderDiffSentencesPairStrategy(sentenceToWords);
	}
}
