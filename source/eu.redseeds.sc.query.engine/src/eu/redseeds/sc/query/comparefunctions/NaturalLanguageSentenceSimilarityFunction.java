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
 * their words by using information retrieval. This method is fine for
 * {@link NaturalLanguageHypertextSentence}s.
 * 
 * @author dbildh
 * 
 */
public class NaturalLanguageSentenceSimilarityFunction extends
		AbstractCompareFunction {

	/** a similarity between two words smaller than this value will be set to 0 */
	private static final double SIMILARITY_THRESHOLD = 0.2;
	private static String FUNCTION_NAME = "NaturalLanguageSentenceSimilarityFunction";
	/**
	 * the influence of the semantic similarity on the total sim. 1 - DELTA is
	 * influence of structural sim
	 */
	private static final double DELTA = 0.70;

	private static final Pattern delimiterPattern = Pattern
			.compile("[^a-zA-Z-]+");

	
	public double ukoCompare(UkoCompareEngine engine, Vertex vertex1, Vertex vertex2) {
		long startTime = System.nanoTime();
		double result = compareSentences((HyperlinkedSentence) vertex1,
				(HyperlinkedSentence) vertex2);

		if (DEBUG) {
			recordComputationTime(FUNCTION_NAME, startTime, System.nanoTime());
		}

		return result;
	}

	/**
	 * Compares two arbitrary sentences on the basis of their words and the
	 * order of their words. Can be used to calculate the similarity of two
	 * NaturalLanguageHypertextSentences, but also to compare a
	 * NaturalLanguageHypertextSentence with an SVOSentence or any other
	 * StructuredLanguageSentence. For better results, try to extend it so the
	 * known synonyms in structured sentences are used.
	 * 
	 * @return
	 */
	private float compareSentences(HyperlinkedSentence sentence1,
			HyperlinkedSentence sentence2) {
		if (sentence1 == null || sentence2 == null
				|| sentence1.getSentenceText() == null
				|| sentence2.getSentenceText() == null) {
			return Float.NaN;
		}
//		System.out.println("Computing NaturalLanguageSimilarity for sentences ");
//		System.out.println("    " + sentence1.getSentenceText());
//		System.out.println("    " + sentence2.getSentenceText());
		List<String> wordList1 = new ArrayList<String>();
		for (String w : delimiterPattern.split(sentence1.getSentenceText())) {
			wordList1.add(w);
		}
		List<String> wordList2 = new ArrayList<String>();
		for (String w : delimiterPattern.split(sentence2.getSentenceText())) {
			wordList2.add(w);
		}

		if (wordList1.isEmpty() || wordList2.isEmpty()) {
			return Float.NaN;
		}

		float result = compareWordLists(wordList1, wordList2);
		System.out.println("      Value:  " + result);
		return result;
	}

	private float compareWordLists(List<String> wordList1,
			List<String> wordList2) {
		List<String> jointWordList = createJointWordList(wordList1, wordList2);

		try {
			SimilarityContainer simContainer1 = calculateSemanticVector(
					wordList1, jointWordList);
			SimilarityContainer simContainer2 = calculateSemanticVector(
					wordList2, jointWordList);

			// We use a small difference to Li's original algorithm. Our word
			// order vectors have the sentence's size and the values are indexes
			// in the joint word set, not the other way round. With original Li
			// "foo bar bar" and "foo bar foo" would be equal.
			calculateWordOrder(simContainer1, wordList1, jointWordList);
			calculateWordOrder(simContainer2, wordList2, jointWordList);
			// Since the order vectors have to be equally sized, enlarge the
			// smaller with 0 values.
			List<Integer> l1 = simContainer1.getOrderVector();
			List<Integer> l2 = simContainer2.getOrderVector();
			while (l1.size() < l2.size()) {
				l1.add(0);
			}
			while (l2.size() < l1.size()) {
				l2.add(0);
			}

			double semanticSim = calculateSemanticSimilarity(simContainer1
					.getSimilarities(), simContainer2.getSimilarities());
			double strucalSim = calculateStructuralSimilarity(simContainer1
					.getOrderVector(), simContainer2.getOrderVector());

			return (float) (DELTA * semanticSim + (1 - DELTA) * strucalSim);
		} catch (JGWNLException ex) {
			ex.printStackTrace();
			throw new RuntimeException("Error accessing the JGWNL", ex);
		}
	}

	private ArrayList<String> createJointWordList(List<String> wordList1,
			List<String> wordList2) {
		HashSet<String> tempSet = new HashSet<String>();
		tempSet.addAll(wordList1);
		tempSet.addAll(wordList2);
		return new ArrayList<String>(tempSet);
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
	private double wordSimilarity(String word1, String word2) {
		try {
			double maxSim = 0;
			WordInfo info1 = RemoteJGWNL.getInstance().lookupWord(word1);
			WordInfo info2 = RemoteJGWNL.getInstance().lookupWord(word2);

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

	private final HashSet<String> unknownWords = new HashSet<String>();

	/**
	 * Calculates the word similarity between a word of the
	 * <code>jointList</code> with every word of the <code>sentence</code>. The
	 * maximal similarity is put in a <code>semanticVector</code>. This is done
	 * for each word of the <code>jointList</code>.
	 * 
	 * @return a list containing the maximal similarities.
	 * @throws JGWNLException
	 */
	private SimilarityContainer calculateSemanticVector(List<String> wordList,
			List<String> jointList) throws JGWNLException {
		SimilarityContainer simContainer = new SimilarityContainer();

		for (String jointWord : jointList) {
			double maxSim = -1.0;
			int mostSimIndex = 0;
			for (int j = 0; j < wordList.size(); j++) {
				double actSim = 0.0;
				if (jointWord.equals(wordList.get(j))) {
					actSim = 1.0;
				} else {
					actSim = wordSimilarity(jointWord, wordList.get(j));
				}
				if (actSim > maxSim) {
					maxSim = actSim;
					mostSimIndex = j;
					if (maxSim == 1.0) {
						break;
					}
				}
			}
			if (maxSim > SIMILARITY_THRESHOLD) {
				simContainer.addSimilarity(maxSim);
			} else {
				simContainer.addSimilarity(0.0);
			}
			simContainer.addMostSimilarWord(wordList.get(mostSimIndex));
		}
		return simContainer;
	}

	/**
	 * Calculates a word order vector for the sentence represented by
	 * <code>simContainer</code>. The order of the words corresponds to the most
	 * similar word of the sentence <code>wordList</code> to a word of the
	 * <code>jointList</code>.
	 * 
	 * @param simContainer
	 *            holds the sentence and the order vector
	 * @param wordList
	 *            a sentence represented as list of strings
	 * @param jointList
	 *            holds the words to which the words of the sentence are
	 *            compared.
	 */
	private void calculateWordOrder(SimilarityContainer simContainer,
			List<String> wordList, List<String> jointList) {
		for (int i = 0; i < wordList.size(); i++) {
			simContainer.addOrderIndex(jointList.indexOf(wordList.get(i)) + 1);
		}
	}

	/**
	 * Calculates the semantic similarity between <code>s1</code> and
	 * <code>s2</code> as the cosinus between the two vectors.
	 * 
	 * @return the semantic similarity between <code>s1</code> and
	 *         <code>s2</code>.
	 */
	private static double calculateSemanticSimilarity(List<Double> s1,
			List<Double> s2) {
		double numerator = 0.0;
		double denominator = 0.0;
		double lengthVectorS1 = 0.0;
		double lengthVectorS2 = 0.0;

		for (int i = 0; i < s1.size(); i++) {
			numerator = numerator + (s1.get(i) * s2.get(i));
			lengthVectorS1 = lengthVectorS1 + Math.pow(s1.get(i), 2);
			lengthVectorS2 = lengthVectorS2 + Math.pow(s2.get(i), 2);
		}
		lengthVectorS1 = Math.sqrt(lengthVectorS1);
		lengthVectorS2 = Math.sqrt(lengthVectorS2);
		denominator = lengthVectorS1 * lengthVectorS2;

		return (denominator != 0 ? numerator / denominator : 0.0);
	}

	/**
	 * Calculate the structural similarity between two sentences based on the
	 * word order vectors <code>orderVector1</code> and
	 * <code>orderVector2</code>.
	 * 
	 * @return the structural similarity
	 */
	private static double calculateStructuralSimilarity(
			List<Integer> orderVector1, List<Integer> orderVector2) {
		double numerator = 0.0;
		double denominator = 0.0;

		for (int i = 0; i < orderVector1.size(); i++) {
			numerator += Math.pow((orderVector1.get(i) - orderVector2.get(i)),
					2);
			denominator += Math.pow(
					(orderVector1.get(i) + orderVector2.get(i)), 2);
		}
		numerator = Math.sqrt(numerator);
		denominator = Math.sqrt(denominator);

		return denominator != 0 ? (1 - (numerator / denominator)) : 0.0;
	}

	/**
	 * This class administrates similarity related information of the sentence
	 * <code>sentence</code> to another (of this class unknown) sentence. The
	 * other sentence is the <code>jointWordList</code> of the class
	 * <code>SentenceSimilarity</code>.
	 * 
	 * @author Thomas Bernd
	 */
	class SimilarityContainer {

		/**
		 * The similarities of i-th words of this sentence to the words of
		 * another sentence.
		 */
		private final List<Double> similarity;
		/**
		 * The words of this sentence which are most similar to the word of the
		 * other sentence at index i. The index of this array corresponds to the
		 * index of the array <code>similarity</code>.
		 */
		private final List<String> mostSimilarWords;
		/**
		 * The order of words of this sentence compared to the order of another
		 * sentence. The indices of the list correspond to the word order in
		 * this sentence. The integers saved in the list correspond to the index
		 * of the most similar word in the other sentence.
		 */
		private final List<Integer> orderVector;

		public SimilarityContainer() {
			this.similarity = new ArrayList<Double>();
			this.mostSimilarWords = new ArrayList<String>();
			this.orderVector = new ArrayList<Integer>();
		}

		public List<String> getMostSimilarWords() {
			return mostSimilarWords;
		}

		public List<Double> getSimilarities() {
			return similarity;
		}

		public List<Integer> getOrderVector() {
			return orderVector;
		}

		public void addSimilarity(double sim) {
			similarity.add(sim);
		}

		public void addMostSimilarWord(String w) {
			mostSimilarWords.add(w);
		}

		public void addOrderIndex(int i) {
			orderVector.add(i);
		}
	}

	public static void main(String[] args) {
		String[][] sentences = { { "dog bites man and dog",
				"dog bites dog and man and fuzzy" } };
		for (String[] pair : sentences) {
			List<String> wordList1 = new ArrayList<String>();
			for (String w : pair[0].split("\\s")) {
				wordList1.add(w);
			}
			List<String> wordList2 = new ArrayList<String>();
			for (String w : pair[1].split("\\s")) {
				wordList2.add(w);
			}

			NaturalLanguageSentenceSimilarityFunction f = new NaturalLanguageSentenceSimilarityFunction();
			float sim = f.compareWordLists(wordList1, wordList2);
			System.out.println("sim(" + pair[0] + ",\n    " + pair[1]
					+ ")\n    = " + sim);
		}
	}
}
