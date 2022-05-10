package eu.redseeds.sc.query.ui.view.composite.scenariosDiff;

import java.util.List;

import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.constrainedlanguagesentences.ConstrainedLanguageSentenceDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.svosentences.SVOSentenceDTO;

/**
 * This class will look for similar sentence in pastSentenceList in order and
 * stop when find first that have at least one the same word (in the same
 * position). What mean word is define by {@link ISVOSentenceToWords}
 */
class CompareSentencesToFirstMatchStrategy implements ICompareConstrainedLanguageSentence {

	private final IConstrainedLanguageToWords sentenceToWords;

	private final IDiffSentencesPairStrategy diffSentencePairStrategy;

	CompareSentencesToFirstMatchStrategy(IConstrainedLanguageToWords sentenceToWords) {
		super();
		this.sentenceToWords = sentenceToWords;
		diffSentencePairStrategy = DiffSentencesPairStrategyFactory.getDefault(sentenceToWords);
	}

	@Override
	public IConstrainedLanguageSentenceMatch compareSentence(ConstrainedLanguageSentenceDTO currentSentences,
			List<ConstrainedLanguageSentenceDTO> pastSentences) {
		validateInput(currentSentences, pastSentences);

		IConstrainedLanguageSentenceMatch sentencesMatchResult = null;
		for (ConstrainedLanguageSentenceDTO pastSentenceDTO : pastSentences) {
			sentencesMatchResult = diffSentences(currentSentences, pastSentenceDTO);
			if (sentencesMatchResult != null) {
				break;
			}
		}
		// if sentencesMatchResult==null then there is no similar sentences to
		// currentSentences in pastSentences
		if (sentencesMatchResult == null) {
			sentencesMatchResult = genSentencesMatch(genSentenceAsMissing(currentSentences), null);
		}

		return sentencesMatchResult;
	}

	/**
	 * Mark all words from sentence as missing
	 *
	 * @param missingSentenceDTO
	 * @return IDiffMarkedSVOSentence with all sentence as missing
	 */
	private IDiffMarkedConstrainedLanguageSentence genSentenceAsMissing(ConstrainedLanguageSentenceDTO missingSentence) {
		IDiffMarkedConstrainedLanguageSentence diffMarkedSVOSentence = genDiffMarkedSentence(missingSentence);
		String[] words = sentenceToWords.splitSentenceToWords(missingSentence);
		if (words != null) {
			for (String word : words) {
				diffMarkedSVOSentence.addDiffMarkedWord(genDiffMarkedWord(DiffType.MISSING, word));
			}
		}
		return diffMarkedSVOSentence;
	}

	/**
	 * Compare two sentences
	 *
	 * @param currentSentences
	 * @param pastSentences
	 * @return
	 * @see {@link IDiffSentencesPairStrategy#diffSentences(SVOSentenceDTO, SVOSentenceDTO)}
	 */
	private IConstrainedLanguageSentenceMatch diffSentences(ConstrainedLanguageSentenceDTO currentSentences, ConstrainedLanguageSentenceDTO pastSentences) {
		return diffSentencePairStrategy.diffSentences(currentSentences, pastSentences);
	}

	private IDiffMarkedConstrainedLanguageSentence genDiffMarkedSentence(ConstrainedLanguageSentenceDTO svoSentenceDTO) {
		return DiffMarkedConstrainedLanguageSentenceFactory.getDefault(svoSentenceDTO);
	}

	private IDiffMarkedWord genDiffMarkedWord(DiffType diffType, String word) {
		return DiffMarkedWordFactory.getDefault(diffType, word);
	}

	private IConstrainedLanguageSentenceMatch genSentencesMatch(IDiffMarkedConstrainedLanguageSentence currentDiffMarkedSVOSentence,
			IDiffMarkedConstrainedLanguageSentence pastDiffMarkedSVOSentence) {
		return ConstrainedLanguageSentenceMatchFactory.getDefault(currentDiffMarkedSVOSentence, pastDiffMarkedSVOSentence);
	}

	private void validateInput(ConstrainedLanguageSentenceDTO currentSentences, List<ConstrainedLanguageSentenceDTO> pastSentences) {
		if (currentSentences == null) {
			throw new NullPointerException("currentSentences cannot be null");
		}
		if (pastSentences == null) {
			throw new NullPointerException("pastSentences cannot be null");
		}
	}

}
