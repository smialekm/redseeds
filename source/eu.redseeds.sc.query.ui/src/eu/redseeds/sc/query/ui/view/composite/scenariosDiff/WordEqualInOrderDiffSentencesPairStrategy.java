package eu.redseeds.sc.query.ui.view.composite.scenariosDiff;

import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.constrainedlanguagesentences.ConstrainedLanguageSentenceDTO;

class WordEqualInOrderDiffSentencesPairStrategy implements IDiffSentencesPairStrategy {

	private final IConstrainedLanguageToWords sentenceToWords;

	public WordEqualInOrderDiffSentencesPairStrategy(IConstrainedLanguageToWords sentenceToWords) {
		super();
		this.sentenceToWords = sentenceToWords;
	}

	@Override
	public IConstrainedLanguageSentenceMatch diffSentences(ConstrainedLanguageSentenceDTO currentSentences, ConstrainedLanguageSentenceDTO pastSentences) {

		IConstrainedLanguageSentenceMatch result=null;

		//compare only the object of the same type
		if(currentSentences.getClass().isInstance(pastSentences)){
			// if this two sentence have 0 same words then this flag will be true
			boolean allIsOther = true;

			// split it to words
			String[] fullCurrentSentencesWords = splitSentenceToWords(currentSentences);
			String[] fullPastSentencesWords = splitSentenceToWords(pastSentences);

			int currentIdx = fullCurrentSentencesWords.length;
			int pastIdx = fullPastSentencesWords.length;

			IDiffMarkedConstrainedLanguageSentence currentDiffMarked = genDiffMarkedSVOSentence(currentSentences);
			IDiffMarkedConstrainedLanguageSentence pastDiffMarked = genDiffMarkedSVOSentence(pastSentences);

			int index = 0;
			for (; index < currentIdx && index < pastIdx; index++) {
				String currentWord = fullCurrentSentencesWords[index];
				String pastWord = fullPastSentencesWords[index];
				if (currentWord.equals(pastWord)) {
					allIsOther = false;
					currentDiffMarked.addDiffMarkedWord(genDiffMarkedWord(DiffType.SAME, currentWord));
					pastDiffMarked.addDiffMarkedWord(genDiffMarkedWord(DiffType.SAME, pastWord));
				} else {
					currentDiffMarked.addDiffMarkedWord(genDiffMarkedWord(DiffType.OTHER, currentWord));
					pastDiffMarked.addDiffMarkedWord(genDiffMarkedWord(DiffType.OTHER, pastWord));
				}
			}
			if (index < currentIdx) {
				for (int i = index; i < currentIdx; i++) {
					String currentWord = fullCurrentSentencesWords[i];
					currentDiffMarked.addDiffMarkedWord(genDiffMarkedWord(DiffType.OTHER, currentWord));
				}
			}
			if (index < pastIdx) {
				for (int i = index; i < currentIdx; i++) {
					String pastWord = fullPastSentencesWords[i];
					pastDiffMarked.addDiffMarkedWord(genDiffMarkedWord(DiffType.OTHER, pastWord));
				}
			}

			result=allIsOther ? null : genSVOSentencesMatch(currentDiffMarked, pastDiffMarked);
		}

		return result;
	}

	private IDiffMarkedConstrainedLanguageSentence genDiffMarkedSVOSentence(ConstrainedLanguageSentenceDTO sentence){
		return DiffMarkedConstrainedLanguageSentenceFactory.getDefault(sentence);
	}

	private IDiffMarkedWord genDiffMarkedWord(DiffType diffType,String word){
		return DiffMarkedWordFactory.getDefault(diffType, word);
	}

	private IConstrainedLanguageSentenceMatch genSVOSentencesMatch(IDiffMarkedConstrainedLanguageSentence currentDiffMarkedSentence, IDiffMarkedConstrainedLanguageSentence pastDiffMarkedSentence){
		return ConstrainedLanguageSentenceMatchFactory.getDefault(currentDiffMarkedSentence, pastDiffMarkedSentence);
	}

	private String[] splitSentenceToWords(ConstrainedLanguageSentenceDTO sentenceDTO) {
		return sentenceToWords.splitSentenceToWords(sentenceDTO);
	}

}
