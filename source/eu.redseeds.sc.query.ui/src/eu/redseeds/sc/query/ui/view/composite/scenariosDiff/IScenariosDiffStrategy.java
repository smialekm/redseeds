package eu.redseeds.sc.query.ui.view.composite.scenariosDiff;

import java.util.List;

import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.constrainedlanguagesentences.ConstrainedLanguageSentenceDTO;

/**
 * This class will compare two lists of sentence and find their similarity
 *
 * @see ScenariosDiffStrategyFactory
 */
interface IScenariosDiffStrategy {
	// public IDiffScenariosResult calculateDiffs(List<SVOSentenceDTO>
	// currentSvoSentences,List<SVOSentenceDTO> pastSvoSentences);

	public IDiffScenariosResult calculateDiffs(List<ConstrainedLanguageSentenceDTO> currentConstrainedLanguageSentences,
			List<ConstrainedLanguageSentenceDTO> pastConstrainedLanguageSentences);
}
