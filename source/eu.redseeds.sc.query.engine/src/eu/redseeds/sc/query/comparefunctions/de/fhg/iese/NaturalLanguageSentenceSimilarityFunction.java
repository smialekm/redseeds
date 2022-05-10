package eu.redseeds.sc.query.comparefunctions.de.fhg.iese;


import java.util.HashMap;
import java.util.List;

import de.fhg.iese.em.raisin.lucene.similarity.IWord;
import de.fhg.iese.em.raisin.lucene.similarity.Sentence;
import de.fhg.iese.em.raisin.lucene.similarity.SentenceListType;
import de.fhg.iese.em.raisin.lucene.similarity.TextSimilarityProvider;
import de.fhg.iese.em.raisin.lucene.similarity.Word;
import de.fhg.iese.em.raisin.similarity.model.repository.TextSimilarity;
import de.fhg.iese.em.raisin.similarity.model.repository.TextSimilarity_NaturalLanguageSentence;
import de.uni_koblenz.jgralab.Vertex;
import eu.redseeds.sc.query.comparefunctions.AbstractCompareFunction;
import eu.redseeds.sc.query.engine.ukocompare.UkoCompareEngine;
import eu.redseeds.scl.rsl.rslkernel.elements.HyperlinkedSentence;
//import eu.redseeds.scl.rsl.rslrequirementrepresentationsentences.controlsentences.ConditionSentence;
//import eu.redseeds.scl.rsl.rslrequirementrepresentationsentences.controlsentences.InvocationSentence;
//import eu.redseeds.scl.rsl.rslrequirementrepresentationsentences.controlsentences.PostconditionSentence;
//import eu.redseeds.scl.rsl.rslrequirementrepresentationsentences.controlsentences.PreconditionSentence;
//import eu.redseeds.scl.rsl.rslrequirementrepresentationsentences.representationsentences.NaturalLanguageHypertextSentence;
//import eu.redseeds.scl.rsl.rslrequirementrepresentationsentences.svosentences.ConditionalSentence;
//import eu.redseeds.scl.rsl.rslrequirementrepresentationsentences.svosentences.ModalSVOSentence;
//import eu.redseeds.scl.rsl.rslrequirementrepresentationsentences.svosentences.SVOSentence;

/**
 * This function compares two RSL NaturalLanguageHypertextSentence on the basis
 * of their words
 *
 * @author schneick, weber
 *
 */
public class NaturalLanguageSentenceSimilarityFunction extends AbstractCompareFunction {

	private static String FUNCTION_NAME = "iese.NaturalLanguageSentenceSimilarityFunction";
	private enum Parameters { RAISIN, RAISINL }
	Parameters param;


	public NaturalLanguageSentenceSimilarityFunction(String parameter) {
		if (parameter == null || parameter.trim().equals("")) {
			param = Parameters.RAISIN;
		} else {
			try {
				param = Parameters.valueOf(parameter);
			} catch (java.lang.IllegalArgumentException e) {
				//		LogUtil.print(this, LogEvent.WARNING, "Unknown parameter '"
				//				+ parameter + "'. Paramter is set to its default.");
				param = Parameters.RAISIN;
			}
		}
	}

	public NaturalLanguageSentenceSimilarityFunction() {
		this(null);
	}

	@Override
	public double ukoCompare(UkoCompareEngine engine, Vertex vertex1, Vertex vertex2) {
		long startTime = System.nanoTime();

		IESE_NLS_SimilarityFunction simFunction = IESE_NLS_SimilarityFunction.getInstance();
		double result;
		switch (param) {
		case RAISIN:
//			System.out.println("**"+FUNCTION_NAME+"**RAISIN**");
			result = simFunction.compareSentencesWithRAISIN((HyperlinkedSentence) vertex1, (HyperlinkedSentence) vertex2);
			break;
		case RAISINL:
//			System.out.println("**"+FUNCTION_NAME+"**RAISINL**");
			result = simFunction.compareSentencesWithRAISINL((HyperlinkedSentence) vertex1, (HyperlinkedSentence) vertex2);
			break;
		default:
			// this should not happen
			throw new RuntimeException("Unhandled case");
		}

		if (DEBUG) {
			recordComputationTime(FUNCTION_NAME+"."+param, startTime, System.nanoTime());
		}
		return result;
	}
	

	double directCompare(HyperlinkedSentence hls1, HyperlinkedSentence hls2) {

		IESE_NLS_SimilarityFunction simFunction = IESE_NLS_SimilarityFunction.getInstance();
		double result;
		switch (param) {
		case RAISIN:
			result = simFunction.compareSentencesWithRAISIN(hls1, hls2);
			break;
		case RAISINL:
			result = simFunction.compareSentencesWithRAISINL(hls1, hls2);
			break;
		default:
			// this should not happen
			throw new RuntimeException("Unhandled case");
		}

		return result;
	}


}	




// ===========================  Similarity Calculation ========================== //



class IESE_NLS_SimilarityFunction {

	private static IESE_NLS_SimilarityFunction instance = null;
	private HashMap<String, Word> wordMap;

	
	public static IESE_NLS_SimilarityFunction getInstance() {
		if (instance == null) {
			instance = new IESE_NLS_SimilarityFunction();
		}
		return instance;
	}


	private IESE_NLS_SimilarityFunction() {
		wordMap = new HashMap<String, Word>();
	}



	// TODO function description
	/**
	 * Compares two arbitrary sentences on the basis of their words and the oder
	 * of their words. Can be used to calculate the similarity of two
	 * NaturalLanguageHypertextSentences, but also to compare a
	 * NaturalLanguageHypertextSentence with an SVOSentence or any other
	 * StructuredLanguageSentence. For better results, try to extend it so the
	 * known synonyms in structured sentences are used.
	 * 
	 * @return
	 */
	double compareSentencesWithRAISIN(HyperlinkedSentence sentence1, HyperlinkedSentence sentence2) {
		if (sentence1 == null || sentence2 == null) {
			return Double.NaN;
		}
		Double outSim = 0.0d;

		TextSimilarity_NaturalLanguageSentence nls_sim = null;
		try {
			nls_sim = new TextSimilarity_NaturalLanguageSentence("TGraph_NaturalLanguageHypertextSentence");
		} catch (Throwable t) {
			t.printStackTrace();
		}
		outSim = nls_sim.similarity(sentence1.getSentenceText(), sentence2.getSentenceText()).getSimilarity();
		if (outSim==null || Double.isNaN(outSim)){
			outSim = 0.0d;
		}

//		System.out.println("*\t>>>"+sentence1.getSentenceText()+"<<<["+getSentenceListType(sentence1)+"]" +
//		"\n*\t>>>"+sentence2.getSentenceText()+"<<<["+getSentenceListType(sentence2)+"]" +
//		"\n*\tNLSS:"+outSim+"\n"+"**");

		return outSim;
	}

	/**
	 * Compares two arbitrary sentences on the basis of their words and the order
	 * of their words. Can be used to calculate the similarity of two
	 * NaturalLanguageHypertextSentences, but also to compare a
	 * NaturalLanguageHypertextSentence with an SVOSentence or any other
	 * StructuredLanguageSentence. 
	 * Synonyms, hypernyms and hyponyms as known from standard WordNet-Repository 
	 * are taken into account.
	 * 
	 * @return
	 */
	double compareSentencesWithRAISINL(HyperlinkedSentence sentence1, HyperlinkedSentence sentence2) {
		if (sentence1 == null || sentence2 == null) {
			return Double.NaN;
		}
		Double outSim = 0.0d;

//		TODO Satz- und Wordtypen ber�cksichtigen
		try {
			TextSimilarity ts = new TextSimilarity("helper"); TextSimilarity.TextSimilarityUtilities tsu = ts.new TextSimilarityUtilities();
			TextSimilarityProvider tsp = new TextSimilarityProvider();

			List<String> sentence1Terms = tsu.getParsedTerms(sentence1.getSentenceText());
			List<String> sentence2Terms = tsu.getParsedTerms(sentence2.getSentenceText());
			Word[] sentence1Words = new Word[sentence1Terms.size()];
			Word[] sentence2Words = new Word[sentence2Terms.size()];

			for (int i = 0; i < sentence1Words.length; i++) {
				if (!wordMap.containsKey(sentence1Terms.get(i))){
					sentence1Words[i] = new Word(sentence1Terms.get(i), IWord.WordType.UNKNOWN);
					wordMap.put(sentence1Terms.get(i), sentence1Words[i]);
				} else {
					sentence1Words[i] = wordMap.get(sentence1Terms.get(i));
				}
			}
			Sentence satz1 = new Sentence(sentence1Words, SentenceListType.NaturalLanguageHypertextSentence);

			for (int i = 0; i < sentence2Words.length; i++) {
				if (!wordMap.containsKey(sentence2Terms.get(i))){
					sentence2Words[i] = new Word(sentence2Terms.get(i), IWord.WordType.UNKNOWN);
					wordMap.put(sentence2Terms.get(i), sentence2Words[i]);
				} else {
					sentence2Words[i] = wordMap.get(sentence2Terms.get(i));
				}
			}
			Sentence satz2 = new Sentence(sentence2Words, SentenceListType.NaturalLanguageHypertextSentence);
			
			outSim = tsp.calculate(satz1, satz2);
			if (outSim==null || Double.isNaN(outSim)){
				outSim = 0.0d;
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}


//		System.out.println("*\t>>>"+sentence1.getSentenceText()+"<<<["+getSentenceListType(sentence1)+"]" +
//		"\n*\t>>>"+sentence2.getSentenceText()+"<<<["+getSentenceListType(sentence2)+"]" +
//		"\n*\tNLSS:"+outSim+"\n"+"**");

		return outSim;

	}


//	// ------ Helpers ------ //
//	/**
//	 * Gives back sentence type as defined in de.fhg.iese.em.raisin.lucene.similarity.SentenceListType
//	 * @param sentence
//	 * @return
//	 */
//	private SentenceListType getSentenceListType(HyperlinkedSentence sentence){
//		SentenceListType slt = SentenceListType.NaturalLanguageHypertextSentence;
//		if (sentence instanceof SVOSentence){
//			slt = SentenceListType.SVOSentence;
//			if (sentence instanceof ModalSVOSentence){
//				slt = SentenceListType.ModalSVOSentence;
//			} 
//		} else { 
//			if (sentence instanceof NaturalLanguageHypertextSentence){
//				slt = SentenceListType.NaturalLanguageHypertextSentence;
//			} else {
//				if (sentence instanceof ConditionSentence){
//					slt = SentenceListType.ConditionSentence;
//				} else {
//					if (sentence instanceof PostconditionSentence){
//						slt = SentenceListType.PostconditionSentence;
//					} else {
//						if (sentence instanceof PreconditionSentence){
//							slt = SentenceListType.PreconditionSentence;
//						} else {
//							if (sentence instanceof InvocationSentence){
//								slt = SentenceListType.InvocationSentence;
//							} else	{    
//								if (sentence instanceof ConditionalSentence){
//									slt = SentenceListType.ConditionalSentence;
//								}
//							}
//						}
//					}
//				}
//			}
//		}
//		return slt;
//	}

}
