package eu.redseeds.sc.query.ui.view.composite.scenariosDiff;

import java.util.List;

import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.constrainedlanguagesentences.ConstrainedLanguageSentenceDTO;

/**
 * This determines how one current sentence will be compare with left past sentence.
 * This class can tell us only if sentence is {@link DiffType#OTHER},{@link DiffType#SAME},{@link DiffType#MISSING}
 * but not if is {@link DiffType#ADDED}.
 * @see IScenariosDiffStrategy
 */
interface ICompareConstrainedLanguageSentence {
	/**
	 *
	 * @param currentSvoSentences
	 * @param pastSvoSentences
	 * @throws NullPointerException if currentSvoSentences is null
	 * @throws NullPointerException if pastSvoSentences is null
	 * @return
	 */
	public IConstrainedLanguageSentenceMatch compareSentence(ConstrainedLanguageSentenceDTO currentSentences,List<ConstrainedLanguageSentenceDTO> pastSentences);
}
