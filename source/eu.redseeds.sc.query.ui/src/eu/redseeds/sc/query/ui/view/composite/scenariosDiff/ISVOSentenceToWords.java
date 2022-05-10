package eu.redseeds.sc.query.ui.view.composite.scenariosDiff;

import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.svosentences.SVOSentenceDTO;

interface ISVOSentenceToWords {
	/**
	 *
	 * @param sentence
	 * @throw {@link NullPointerException} if sentence is null
	 * @return
	 */
	public String[] splitSentenceToWords(SVOSentenceDTO sentenceDTO);
}
