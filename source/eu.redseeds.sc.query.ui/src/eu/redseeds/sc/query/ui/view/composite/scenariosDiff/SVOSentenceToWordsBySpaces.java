package eu.redseeds.sc.query.ui.view.composite.scenariosDiff;

import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.svosentences.SVOSentenceDTO;

class SVOSentenceToWordsBySpaces implements ISVOSentenceToWords {

	private static final String WORD_SEPARATOR = "\\s+";

	@Override
	public String[] splitSentenceToWords(SVOSentenceDTO sentenceDTO) {
		String sentence=SVOSentenceToWordsHelper.getTextVersionOfSentence(sentenceDTO);
		if(sentence==null){
			throw new NullPointerException("sentence cannot be null");
		}
		return sentence.split(WORD_SEPARATOR);
	}

}
