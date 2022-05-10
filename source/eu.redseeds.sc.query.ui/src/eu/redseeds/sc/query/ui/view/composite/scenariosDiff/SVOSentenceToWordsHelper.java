package eu.redseeds.sc.query.ui.view.composite.scenariosDiff;

import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.svosentences.SVOSentenceDTO;

abstract class SVOSentenceToWordsHelper {
	public static String getTextVersionOfSentence(SVOSentenceDTO svoSentences) {
		return SVOSentenceDTOHelper.getTextVersionOfSentence(svoSentences);
	}
}
