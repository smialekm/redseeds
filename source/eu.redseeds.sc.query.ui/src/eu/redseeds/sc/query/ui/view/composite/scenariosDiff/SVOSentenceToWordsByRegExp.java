package eu.redseeds.sc.query.ui.view.composite.scenariosDiff;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.svosentences.SVOSentenceDTO;

class SVOSentenceToWordsByRegExp implements ISVOSentenceToWords {

	/**
	 * This reg exp is used to find words ex:
	 * [[n:Customer]] [[v:insert n:bracelet p:into n:the bracelet reader]] => Customer | insert | into | the bracelet reader
	 */
	private static final String WORD_MATCH_PATTERN = "\\b[aA-zZ 0-9,]+(?=(.\\]|\\s))\\b";
	private static final Pattern pattern = Pattern.compile(WORD_MATCH_PATTERN);

	@Override
	public String[] splitSentenceToWords(SVOSentenceDTO sentenceDTO) {
		String sentence=SVOSentenceToWordsHelper.getTextVersionOfSentence(sentenceDTO);
		if(sentence==null){
			throw new NullPointerException("sentence cannot be null");
		}
		List<String> result = new ArrayList<String>();
		Matcher matcher = pattern.matcher(sentence);
		while (matcher.find()) {
			int start = matcher.start();
			int end = matcher.end();
			String match = sentence.substring(start, end);
			result.add(match);
		}
		return result.toArray(new String[0]);
	}

}
