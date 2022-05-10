package eu.redseeds.sc.query.comparefunctions.de.fhg.iese;

import java.util.ArrayList;
import java.util.HashMap;

import de.fhg.iese.em.raisin.lucene.similarity.SentenceListType;
import de.uni_koblenz.jgralab.Vertex;
import eu.redseeds.sc.query.comparefunctions.AbstractCompareFunction;
import eu.redseeds.sc.query.engine.ukocompare.UkoCompareEngine;
import eu.redseeds.scl.rsl.rslkernel.elements.HyperlinkedSentence;
import eu.redseeds.scl.rsl.rslrequirementrepresentations.descriptiverequirementrepresentations.SentenceList;
import eu.redseeds.scl.rsl.rslrequirementrepresentationsentences.svosentences.SVOSentence;

/**
 * This function compares two lists of sentences of the type HyperlinkedSentence
 * 
 * @author schneick
 * 
 */
public class SentenceListSimilarityFunction extends AbstractCompareFunction {

	private static String FUNCTION_NAME = "iese.SentenceListSimilarityFunction";
	private enum Parameters { RAISIN, RAISINL }
	Parameters param;
	NaturalLanguageSentenceSimilarityFunction nls_sim;
	SVOSentenceSimilarityFunction svo_sim;

	
	public SentenceListSimilarityFunction(String parameter) {
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
		
		nls_sim = new NaturalLanguageSentenceSimilarityFunction(param.name());
		svo_sim = new SVOSentenceSimilarityFunction(param.name());
	}

	public SentenceListSimilarityFunction() {
		this(null);
	}
	
	
	@Override
	public double ukoCompare(UkoCompareEngine engine, Vertex vertex1, Vertex vertex2) {
		long startTime = System.nanoTime();

		if (vertex1 == null || vertex2 == null) {
			return Double.NaN;
		}
		double result;
		
		HashMap<SentenceListType, ArrayList<HyperlinkedSentence>> sentenceListMap1 = new HashMap<SentenceListType, ArrayList<HyperlinkedSentence>>();
		HashMap<SentenceListType, ArrayList<HyperlinkedSentence>> sentenceListMap2 = new HashMap<SentenceListType, ArrayList<HyperlinkedSentence>>();
		initializeSentenceListMaps((SentenceList) vertex1, (SentenceList) vertex2, sentenceListMap1, sentenceListMap2);
		
		result = compareSentenceLists(sentenceListMap1, sentenceListMap1);
		
		if (DEBUG) {
			recordComputationTime(FUNCTION_NAME+"."+param, startTime, System.nanoTime());
		}
		return result;
	}

	
	
	private void initializeSentenceListMaps(SentenceList sentenceList1, SentenceList sentenceList2, HashMap<SentenceListType, ArrayList<HyperlinkedSentence>> sentenceListMap1, HashMap<SentenceListType, ArrayList<HyperlinkedSentence>> sentenceListMap2){
		// initialize maps
		for (SentenceListType sentenceListType : SentenceListType.values()) {
			ArrayList<HyperlinkedSentence> arrayList1x, arrayList2x;
			arrayList1x = new ArrayList<HyperlinkedSentence>();
			arrayList2x = new ArrayList<HyperlinkedSentence>();
			sentenceListMap1.put(sentenceListType, arrayList1x);
			sentenceListMap2.put(sentenceListType, arrayList2x);
		}
		
		// mapping typed sentence lists
		for (HyperlinkedSentence sentence1 : sentenceList1.getSentenceList()) {
			if (sentence1 instanceof SVOSentence){
				sentenceListMap1.get(SentenceListType.SVOSentence).add(sentence1);
			} else {
				sentenceListMap1.get(SentenceListType.NaturalLanguageHypertextSentence).add(sentence1);
			}
		}
		for (HyperlinkedSentence sentence2 : sentenceList2.getSentenceList()) {
			if (sentence2 instanceof SVOSentence){
				sentenceListMap2.get(SentenceListType.SVOSentence).add(sentence2);
			} else {
				sentenceListMap2.get(SentenceListType.NaturalLanguageHypertextSentence).add(sentence2);
			}
		}
	}
	
	
//===========================  Similarity Calculation ========================== //



	// TODO function description
	/**
	 * Compares two arbitrary sentence lists
	 * 
	 * @return
	 */
	double compareSentenceLists(HashMap<SentenceListType, ArrayList<HyperlinkedSentence>> sentenceListMap1, HashMap<SentenceListType, ArrayList<HyperlinkedSentence>> sentenceListMap2) {
		Double outSim = 0.0d;
		
		try {
						
//						System.out.println("\n-- Comparing --\n" + sentenceListMap1	+ "\n-- With --");
//						System.out.println(sentenceListMap2 + "\n-- /End --");
//						System.out.print("***");
		
						if (	sentenceListMap1.get(SentenceListType.NaturalLanguageHypertextSentence).size()>0
								||
								sentenceListMap1.get(SentenceListType.SVOSentence).size()>0
							){
							
		
							// NaturalLanguageHypertextSentence
							double overallSimNHLS = getNaturalLanguageHypertextSentenceListSim(
									sentenceListMap1.get(SentenceListType.NaturalLanguageHypertextSentence),
									sentenceListMap2.get(SentenceListType.NaturalLanguageHypertextSentence));
		
							// SVOSentence
							double overallSimSVOS = getSVOSentenceListSim(
									sentenceListMap1.get(SentenceListType.SVOSentence), 
									sentenceListMap2.get(SentenceListType.SVOSentence));
		
							outSim =(
										overallSimNHLS*
										sentenceListMap1.get(SentenceListType.NaturalLanguageHypertextSentence).size() 
										+ 
										overallSimSVOS*
										sentenceListMap1.get(SentenceListType.SVOSentence).size()
									)/(
										sentenceListMap1.get(SentenceListType.NaturalLanguageHypertextSentence).size() 
										+ 
										sentenceListMap1.get(SentenceListType.SVOSentence).size()
									);
		
//							System.out.print("   nlhs:" + overallSimNHLS + "   svos:" + overallSimSVOS); 
						}
						
//						System.out.println("   total:" + outSim + "   ***\n");
						
					} catch (Throwable t) {
						t.printStackTrace();
					}
		
		return outSim;
	}


	
	private Double getNaturalLanguageHypertextSentenceListSim(ArrayList<HyperlinkedSentence> sentenceList1, ArrayList<HyperlinkedSentence> sentenceList2) {
		int sentenceCount1 = sentenceList1.size();
		int sentenceCount2 = sentenceList2.size();
//		int maxDistance = Math.max(sentenceCount1, sentenceCount2) - 1;
		double sizeRatio = ((Integer) Math.min(sentenceCount1, sentenceCount2))
		.doubleValue()
		/ ((Integer) Math.max(sentenceCount1, sentenceCount2))
		.doubleValue();
		double correctionRatio = sizeRatio;
		if (sentenceCount2 < sentenceCount1) {
			correctionRatio = 1.0d / correctionRatio;
		}
		double overallSim = 0.0d;

//		for (int i1 = 0; i1 < sentenceList1.size(); i1++) {
//		float mySim = 0.0f;
//		String sentence1 = sentenceList1.get(i1);
//		for (int i2 = 0; i2 < sentenceList2.size(); i2++) {
//		float distance = Math.abs(i1-i2) * correctionRatio;
//		String sentence2 = sentenceList2.get(i2);
//		float simix = (new Float(tSim.similarity(sentence1,
//		sentence2).getSimilarity())) / (1 + distance);
//		if (mySim < simix){
//		mySim = simix;
//		}
//		}
//		overallSim += mySim;
//		}

		for (HyperlinkedSentence sentence1 : sentenceList1) {
			double mySim = 0.0d;
			for (HyperlinkedSentence sentence2 : sentenceList2) {
				mySim = Math.max(mySim, nls_sim.directCompare(sentence1, sentence2));
			}
			overallSim += mySim;
		}

		if (sentenceCount1 != 0 && sentenceCount2 != 0) {
			// Similarity depends on size ratio of the two cases
			overallSim = overallSim / sentenceCount1 * sizeRatio;
			// Even lower similarity if query case is bigger than the compare
			// case
			if (sentenceCount1 > sentenceCount2) {
				overallSim = overallSim * sizeRatio;
			}
		}
		return overallSim;
	}
	
	
	private Double getSVOSentenceListSim(ArrayList<HyperlinkedSentence> sentenceList1, ArrayList<HyperlinkedSentence> sentenceList2) {
		int sentenceCount1 = sentenceList1.size();
		int sentenceCount2 = sentenceList2.size();

		double sizeRatio = ((Integer) Math.min(sentenceCount1, sentenceCount2))
		.doubleValue()
		/ ((Integer) Math.max(sentenceCount1, sentenceCount2))
		.doubleValue();
		double correctionRatio = sizeRatio;
		if (sentenceCount2 < sentenceCount1) {
			correctionRatio = 1.0d / correctionRatio;
		}
		double overallSim = 0.0d;

		for (HyperlinkedSentence sentence1 : sentenceList1) {
			double mySim = 0.0d;
			for (HyperlinkedSentence sentence2 : sentenceList2) {
				mySim = Math.max(mySim, svo_sim.directCompare((SVOSentence) sentence1, (SVOSentence) sentence2));
			}
			overallSim += mySim;
		}

		if (sentenceCount1 != 0 && sentenceCount2 != 0) {
			// Similarity depends on size ratio of the two cases
			overallSim = overallSim / sentenceCount1 * sizeRatio;
			// Even lower similarity if query case is bigger than the compare
			// case
			if (sentenceCount1 > sentenceCount2) {
				overallSim = overallSim * sizeRatio;
			}
		}
		return overallSim;
	}
}

