package eu.redseeds.sc.query.ui.view.composite.scenariosDiff;

import java.util.List;

import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.constrainedlanguagesentences.ConstrainedLanguageSentenceDTO;

interface IScenariosDiffContentProvider {

	public abstract List<ConstrainedLanguageSentenceDTO> getCurrentSentences();

	public abstract List<ConstrainedLanguageSentenceDTO> getPastSentences();

	public abstract String getCurrElmPath();

	public abstract String getPastElmPath();

}