package eu.redseeds.sc.query.comparefunctions;

import java.util.ArrayList;
import java.util.List;

import de.uni_koblenz.jgralab.Vertex;
import eu.redseeds.sc.query.engine.ukocompare.UkoCompareEngine;


public abstract class AbstractSimilarRemoteNodesLi  extends AbstractCompareFunction {

	
	protected double STRUCTURAL_SIMILARITY_INFLUENCE = 0.2f;

	protected double SEMANTIC_SIMILARITY_INFLUENCE = 0.8f;
	
	protected double SIMILARITY_TREATED_AS_EQUAL = 0.60f;
//	
	protected double calculatedSemanticSim = 0;
//	
	protected double calculatedStrucutralSim = 0;
		
	
	public double performComparison(UkoCompareEngine engine, List<? extends Vertex> elements1, List<? extends Vertex> elements2) { 
		/* create joint sentence set for steps */
		//System.out.println("  LiAlgo - list1.size() " + elements1.size() + " list2.size() " + elements2.size());
		
		if (elements1.size() == 0 && elements2.size() == 0)
			return -1;

		if ((elements1.size() == 1) && (elements2.size() == 1)) {
//			System.out.println("Element1 " + elements1.get(0));
//			System.out.println("Element2 " + elements2.get(0));
//			System.out.println("Sim: " + engine.getSimilarity(elements1.get(0), elements2.get(0)));
			return engine.getSimilarity(elements1.get(0), elements2.get(0));
		}
		
		float semanticSimilarity = 0;
		List<Vertex> jointChildList = new ArrayList<Vertex>();
		jointChildList.addAll(elements1);
	
		List<Integer> orderVector1 = new ArrayList<Integer>();
		List<Integer> orderVector2 = new ArrayList<Integer>();
		for (int i = 0; i < elements1.size(); i++) {
			orderVector1.add(i + 1);
		}
		/* add elements of second scenario to joint sentence */

		int comparedElements = 0;
		for (Vertex currentChild : elements2) {
			double maximalSimilarityForCurrentSentence = 0;
			int indexOfMostSimilarSentence = -1;
			int jointSentenceListIndex = 0;
			for (Vertex jointVertex : elements1) {
				double currentSim = engine.getSimilarity(jointVertex, currentChild);
				if (currentSim > maximalSimilarityForCurrentSentence) {
					maximalSimilarityForCurrentSentence = currentSim;
					indexOfMostSimilarSentence = jointSentenceListIndex + 1;
				}
				jointSentenceListIndex++;
			}
			if (maximalSimilarityForCurrentSentence < SIMILARITY_TREATED_AS_EQUAL) {
				jointChildList.add(currentChild);
				orderVector2.add(jointChildList.size());
			} else {
//				System.out.println("Adding index of most similar sentence " + indexOfMostSimilarSentence);
//				System.out.println("Sentence: " + currentChild);
//				System.out.println("Most similar sentence: " + elements2.get(indexOfMostSimilarSentence-1));
//				System.out.println("Similarity: " + engine.getSimilarity(elements2.get(indexOfMostSimilarSentence-1), currentChild));
				orderVector2.add(indexOfMostSimilarSentence);
			}
//			if (currentChild instanceof SVOSentence)
//				System.out.println("Maximal similarity for current sentence:" + maximalSimilarityForCurrentSentence);
//			if (currentChild instanceof ConstrainedLanguageScenario)
//				System.out.println(("Maximal similarity for current scenario:" + maximalSimilarityForCurrentSentence));
			semanticSimilarity += maximalSimilarityForCurrentSentence;
			comparedElements++;
		}
		/* adding 0 values to the shorter order vector */
		for (int i = orderVector1.size(); i < orderVector2.size(); i++) {
			orderVector1.add(0);
		}
		for (int i = orderVector2.size(); i < orderVector1.size(); i++) {
			orderVector2.add(0);
		}

		semanticSimilarity = semanticSimilarity / elements2.size();
		this.calculatedSemanticSim = semanticSimilarity;

		double structuralSimilarity = calculateStructuralSimilarity(
				orderVector1, orderVector2);	
		this.calculatedStrucutralSim = structuralSimilarity;

		if ((semanticSimilarity < 0.5) && (semanticSimilarity < structuralSimilarity))
			structuralSimilarity = semanticSimilarity;
//		System.out.println("AbstractRemoteNodes similarity: ");
//		System.out.println("  Elements1");
//		for (Vertex v : elements1) {
//			System.out.println("    " + v);
//		}
//		System.out.println("  Elements2");
//		for (Vertex v : elements2) {
//			System.out.println("    " + v);
//		}
//		System.out.print("  OrderVector1: ");
//		for (Integer i : orderVector1) 
//			System.out.print(i + ",");
//		System.out.print("  OrderVector2: ");
//		for (Integer i : orderVector2) 
//			System.out.print(i + ",");
//		System.out.println("  Strucural: " + calculatedStrucutralSim  + " Influence: " + STRUCTURAL_SIMILARITY_INFLUENCE );
//		System.out.println("  Semantic: " + calculatedSemanticSim  + " Influence: " + SEMANTIC_SIMILARITY_INFLUENCE );	
		return STRUCTURAL_SIMILARITY_INFLUENCE * structuralSimilarity + semanticSimilarity
				* SEMANTIC_SIMILARITY_INFLUENCE;
	}
	
	
	
	/**
	 * Calculate the structural similarity between two scenarios based on the
	 * sentence order vectors <code>orderVector1</code> and
	 * <code>orderVector2</code>.
	 * 
	 * @return the structural similarity
	 */
	private double calculateStructuralSimilarity(List<Integer> orderVector1,
			List<Integer> orderVector2) {
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

	
}
