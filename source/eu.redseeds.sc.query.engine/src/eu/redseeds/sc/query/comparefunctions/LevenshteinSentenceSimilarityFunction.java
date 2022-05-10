package eu.redseeds.sc.query.comparefunctions;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Pattern;

import de.uni_koblenz.jgralab.Vertex;
import de.uni_koblenz.jgwnl.exceptions.JGWNLException;
import de.uni_koblenz.jgwnl.info.SynonymInfo;
import de.uni_koblenz.jgwnl.info.WordInfo;
import eu.redseeds.sc.query.engine.ukocompare.UkoCompareEngine;
import eu.redseeds.sc.terminology.model.RemoteJGWNL;
import eu.redseeds.scl.rsl.rslkernel.elements.HyperlinkedSentence;
import eu.redseeds.scl.rsl.rslrequirementrepresentationsentences.representationsentences.NaturalLanguageHypertextSentence;

/**
 * This function compares two RSL {@link HyperlinkedSentence}s on the basis of
 * their words by using Levensthein's editing difference. This method is fine for
 * {@link NaturalLanguageHypertextSentence}s.
 * 
 * @author dbildh
 * 
 */
public class LevenshteinSentenceSimilarityFunction extends	AbstractLevenshteinSimilarity<String> {

	/** a similarity between two words smaller than this value will be set to 0 */

	private static String FUNCTION_NAME = "LevenshteinSimilarityFunction";
	private final HashSet<String> unknownWords = new HashSet<String>();

	private static final Pattern delimiterPattern = Pattern
			.compile("[^a-zA-Z-]+");

	
	public double ukoCompare(UkoCompareEngine engine, Vertex vertex1, Vertex vertex2) {
		long startTime = System.nanoTime();
		double result = compareSentences(engine, (HyperlinkedSentence) vertex1,
				(HyperlinkedSentence) vertex2);

		if (DEBUG) {
			recordComputationTime(FUNCTION_NAME, startTime, System.nanoTime());
		}

		return result;
	}

	protected double compareSentences(UkoCompareEngine engine, HyperlinkedSentence sentence1,
			HyperlinkedSentence sentence2) {
		if (sentence1 == null || sentence2 == null
				|| sentence1.getSentenceText() == null
				|| sentence2.getSentenceText() == null) {
			return Float.NaN;
		}
		List<String> wordList1 = new ArrayList<String>();
		for (String w : delimiterPattern.split(sentence1.getSentenceText())) {
			wordList1.add(w);
		}
		List<String> wordList2 = new ArrayList<String>();
		for (String w : delimiterPattern.split(sentence2.getSentenceText())) {
			wordList2.add(w);
		}
		return compareElementLists(engine, wordList1, wordList2);
	}	


	/**
	 * Calculates the similarity between two words given as strings without any
	 * further information about their sense. Probably, the implementation of
	 * this function needs to be tuned
	 * 
	 * @param word1
	 * @param word2
	 * @return
	 */
	@Override
	protected double elementSimilarity(UkoCompareEngine engine, String word1, String word2) {
		try {
			double maxSim = 0;
			WordInfo info1 = RemoteJGWNL.getInstance().lookupWord(word1.toLowerCase());
			WordInfo info2 = RemoteJGWNL.getInstance().lookupWord(word2.toLowerCase());

			if (info1 == info2) {
				return 1;
			}

			if (info1.getSynsetInfos().size() == 0) {
				// one of those words is not in the terminology.
				if (!unknownWords.contains(word1)) {
					System.err
							.println("\""
									+ word1
									+ "\" is not contained in the terminology.  Is it misspelled?");
					unknownWords.add(word1);
				}
				return 0;
			}
			if (info2.getSynsetInfos().size() == 0) {
				// one of those words is not in the terminology.
				if (!unknownWords.contains(word2)) {
					System.err
							.println("\""
									+ word2
									+ "\" is not contained in the terminology.  Is it misspelled?");
					unknownWords.add(word2);
				}
				return 0;
			}

			for (SynonymInfo synInfo1 : info1.getSynonymInfos()) {
				for (SynonymInfo synInfo2 : info2.getSynonymInfos()) {
					if (synInfo1.getPos() != synInfo2.getPos()) {
						continue;
					}
					double currentSim = RemoteJGWNL.getInstance().similarity(
							synInfo1.getUid(), synInfo2.getUid());
					if (currentSim > maxSim) {
						maxSim = currentSim;
					}
				}
			}
			return maxSim;
		} catch (JGWNLException ex) {
			ex.printStackTrace();
			throw new RuntimeException("JGWNL server not accessible");
		} catch (RemoteException ex) {
			ex.printStackTrace();
			throw new RuntimeException("JGWNL server not accessible");
		}
	}



}
