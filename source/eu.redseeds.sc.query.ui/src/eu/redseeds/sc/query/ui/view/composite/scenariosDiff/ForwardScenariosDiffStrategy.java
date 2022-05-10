package eu.redseeds.sc.query.ui.view.composite.scenariosDiff;

import java.util.ArrayList;
import java.util.List;

import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.constrainedlanguagesentences.ConstrainedLanguageSentenceDTO;

/**
 * This strategy will look for similar sentence only forward so if we have
 * (CS1,CS2,CS3) and (PS1,PS2,PS3) and CS1 is similar to PS2 then PS1 is
 * {@link DiffType#ADDED} and for CS2 we will start looking form PS3 and if CS2
 * will be similar to PS3 then CS3 will be {@link DiffType#MISSING} (because
 * there is no more PSx)
 */
class ForwardScenariosDiffStrategy implements IScenariosDiffStrategy {

//	private ISVOSentenceToWords sentenceToWords = SVOSentenceToWordsFactory.getDefault();
	private IConstrainedLanguageToWords sentenceToWords = ConstrainedLanguageSentenceToWordsFactory.getDefault();

//	private ICompareSVOSentenceStrategy compareSVOSentenceStrategy = CompareSVOSentenceFactory.getDefault(sentenceToWords);
	private ICompareConstrainedLanguageSentence compareSentenceStrategy = CompareConstrainedLanguageSentenceFactory
			.getDefault(sentenceToWords);

	@Override
	public IDiffScenariosResult calculateDiffs(List<ConstrainedLanguageSentenceDTO> currentConstrainedLanguageSentences,
			List<ConstrainedLanguageSentenceDTO> pastConstrainedLanguageSentences) {

		validate(currentConstrainedLanguageSentences, pastConstrainedLanguageSentences);
		IDiffScenariosResult result = DiffScenariosResultFactory.getDefault();

		List<ConstrainedLanguageSentenceDTO> workingCopyOfPastSentences = new ArrayList<ConstrainedLanguageSentenceDTO>(pastConstrainedLanguageSentences);
		for (ConstrainedLanguageSentenceDTO currentConstrainedLanguageSentence : currentConstrainedLanguageSentences) {
			IConstrainedLanguageSentenceMatch sentencesMatch = compareSentence(currentConstrainedLanguageSentence,
					workingCopyOfPastSentences);
			// if is missing then just add to resut
			if (sentencesMatch.isMissing()) {
				result.addConstrainedLanguageSentenceMatchs(sentencesMatch);
			}
			/*
			 * if there is past sentence similar to current sentence then: -
			 * mark all past sentence before the similar past sentence as ADDED,
			 * add them to result ,rmv them from workingCopypastSvoSentences
			 * and, - add similar pair to result
			 */
			if (sentencesMatch.isSimilar()) {
				ConstrainedLanguageSentenceDTO pastSentenceDTO = sentencesMatch.getPastDiffMarkedConstrainedLanguageSentence()
						.getConstrainedLanguageSentenceDTO();
				// first proceed sentence before similar past sentence
				int pastIdx = workingCopyOfPastSentences.indexOf(pastSentenceDTO);
				// get all sentence before similar past sentence
				List<ConstrainedLanguageSentenceDTO> addedSentences = workingCopyOfPastSentences.subList(0, pastIdx);
				// mark all sentence that are before past sentence as added
				List<IConstrainedLanguageSentenceMatch> addedSentencesMatch = generateAddedSentencesMatch(addedSentences);
				result.addAllConstrainedLanguageSentenceMatchs(addedSentencesMatch);

				// rmv added sentence from working list, we dont need them any more
				addedSentences.clear();

				//add similar sentence
				result.addConstrainedLanguageSentenceMatchs(sentencesMatch);

				// and rmv similar sentence from working list
				workingCopyOfPastSentences.remove(pastSentenceDTO);

			}
		}
		// if working list is not empty then all what left should be mark as
		// ADDED
		if (!workingCopyOfPastSentences.isEmpty()) {
			List<IConstrainedLanguageSentenceMatch> addedSentencesMatch = generateAddedSentencesMatch(workingCopyOfPastSentences);
			workingCopyOfPastSentences.clear();
			result.addAllConstrainedLanguageSentenceMatchs(addedSentencesMatch);
		}
		return result;
	}

	private void validate(List<ConstrainedLanguageSentenceDTO> currentConstrainedLanguageSentence,
			List<ConstrainedLanguageSentenceDTO> pastConstrainedLanguageSentence) {
		if (currentConstrainedLanguageSentence == null) {
			throw new NullPointerException("SvoSentences from current use case cannot be null");
		}
		if (pastConstrainedLanguageSentence == null) {
			throw new NullPointerException("SvoSentences from past use case cannot be null");
		}
	}

	private IConstrainedLanguageSentenceMatch compareSentence(ConstrainedLanguageSentenceDTO currentSentences,
			List<ConstrainedLanguageSentenceDTO> pastSentences) {
		return compareSentenceStrategy.compareSentence(currentSentences, pastSentences);
	}

	private List<IConstrainedLanguageSentenceMatch> generateAddedSentencesMatch(List<ConstrainedLanguageSentenceDTO> sentences) {
		List<IConstrainedLanguageSentenceMatch> result = new ArrayList<IConstrainedLanguageSentenceMatch>();
		for (ConstrainedLanguageSentenceDTO sentence : sentences) {
			IDiffMarkedConstrainedLanguageSentence diffMarkedSentence = genSentenceAsAdded(sentence);
			result.add(genSentencesMatch(null, diffMarkedSentence));
		}
		return result;
	}

	/**
	 * Mark all words from sentence as {@link DiffType#ADDED}
	 *
	 * @param missingSentenceDTO
	 * @return IDiffMarkedSVOSentence with all sentence as missing
	 */
	private IDiffMarkedConstrainedLanguageSentence genSentenceAsAdded(ConstrainedLanguageSentenceDTO missingSentence) {
		IDiffMarkedConstrainedLanguageSentence diffMarkedSentence = genDiffMarkedSentence(missingSentence);
		String[] words = sentenceToWords.splitSentenceToWords(missingSentence);
		if (words != null) {
			for (String word : words) {
				diffMarkedSentence.addDiffMarkedWord(genDiffMarkedWord(DiffType.ADDED, word));
			}
		}
		return diffMarkedSentence;
	}

	private IDiffMarkedConstrainedLanguageSentence genDiffMarkedSentence(ConstrainedLanguageSentenceDTO svoSentence) {
		return DiffMarkedConstrainedLanguageSentenceFactory.getDefault(svoSentence);
	}

	private IDiffMarkedWord genDiffMarkedWord(DiffType diffType, String word) {
		return DiffMarkedWordFactory.getDefault(diffType, word);
	}

	private IConstrainedLanguageSentenceMatch genSentencesMatch(IDiffMarkedConstrainedLanguageSentence currentDiffMarkedSentence,
			IDiffMarkedConstrainedLanguageSentence pastDiffMarkedSentence) {
		return ConstrainedLanguageSentenceMatchFactory.getDefault(currentDiffMarkedSentence, pastDiffMarkedSentence);
	}

}
