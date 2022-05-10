package eu.redseeds.sc.query.ui.view.composite.scenariosDiff;

import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.svosentences.SVOSentenceDTO;

class SVOSentenceDTOHelper {

	public static String getTextVersionOfSentence(SVOSentenceDTO svoSentences) {
		return svoSentences.getFullSentenceText();
	}
}
