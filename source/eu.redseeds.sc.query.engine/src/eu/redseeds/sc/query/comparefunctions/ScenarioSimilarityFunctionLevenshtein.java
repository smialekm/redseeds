package eu.redseeds.sc.query.comparefunctions;

import java.util.ArrayList;
import java.util.List;

import de.uni_koblenz.jgralab.Edge;
import de.uni_koblenz.jgralab.EdgeDirection;
import de.uni_koblenz.jgralab.Vertex;
import eu.redseeds.sc.query.engine.ukocompare.UkoCompareEngine;
import eu.redseeds.scl.rsl.rslrequirementrepresentations.descriptiverequirementrepresentations.ConstrainedLanguageScenario;
import eu.redseeds.scl.rsl.rslrequirementrepresentationsentences.constrainedlanguagesentences.ConstrainedLanguageSentence;
import eu.redseeds.scl.rsl.rslrequirementrepresentationsentences.controlsentences.PostconditionSentence;
import eu.redseeds.scl.rsl.rslrequirementrepresentationsentences.controlsentences.PreconditionSentence;

/**
 * Compares two scenarios based on the order of sentences and their similarity
 * 
 * @author dbildh@uni-koblenz.de
 * 
 */
public class ScenarioSimilarityFunctionLevenshtein extends AbstractLevenshteinSimilarity<ConstrainedLanguageSentence> {

	private final static float PRECONDITION_SIMILARITY_INFLUENCE = 0.1f;

	private final static float POSTCONDITION_SIMILARITY_INFLUENCE = 0.1f;

	
	@Override
	public double ukoCompare(UkoCompareEngine engine, Vertex vertex1, Vertex vertex2) {
		return compareScenarios(engine, (ConstrainedLanguageScenario)vertex1, (ConstrainedLanguageScenario)vertex2);
	}

	private double compareScenarios(UkoCompareEngine engine, ConstrainedLanguageScenario scenario1,
							ConstrainedLanguageScenario scenario2) {
		PreconditionSentence precondition1 = null;
		PostconditionSentence postcondition1 = null;
		List<ConstrainedLanguageSentence> scenarioSteps1 = new ArrayList<ConstrainedLanguageSentence>();
		for (Edge edge : scenario1
				.getScenarioContainsScenarioStepIncidences(EdgeDirection.OUT)) {
			ConstrainedLanguageSentence currentSentence = (ConstrainedLanguageSentence) edge
					.getThat();
			if ((precondition1 != null)
					&& (currentSentence instanceof PreconditionSentence)) {
				precondition1 = (PreconditionSentence) currentSentence;
			} else if ((postcondition1 != null)
					&& (currentSentence instanceof PostconditionSentence)) {
				postcondition1 = (PostconditionSentence) currentSentence;
			} else {
				scenarioSteps1.add(currentSentence);
			}
		}

		PreconditionSentence precondition2 = null;
		PostconditionSentence postcondition2 = null;
		List<ConstrainedLanguageSentence> scenarioSteps2 = new ArrayList<ConstrainedLanguageSentence>();
		for (Edge edge : scenario2
				.getScenarioContainsScenarioStepIncidences(EdgeDirection.OUT)) {
			ConstrainedLanguageSentence currentSentence = (ConstrainedLanguageSentence) edge
					.getThat();
			if ((precondition2 != null)
					&& (currentSentence instanceof PreconditionSentence)) {
				precondition2 = (PreconditionSentence) currentSentence;
			} else if ((postcondition2 != null)
					&& (currentSentence instanceof PostconditionSentence)) {
				postcondition2 = (PostconditionSentence) currentSentence;
			} else {
				scenarioSteps2.add(currentSentence);
			}
		}
		
		/* semantic and structural similarity of scenario steps calculated by inherited method*/
		double scenarioStepsSim = compareElementLists(engine, scenarioSteps1, scenarioSteps2);
		
		double realPreInf = PRECONDITION_SIMILARITY_INFLUENCE;
		double realPostInf = POSTCONDITION_SIMILARITY_INFLUENCE;
		/* Preconditionsimilarity */
		double preconditionSimilarity = 0;
		if ((precondition1 != null) && (precondition2 != null)) {
			preconditionSimilarity = engine.getSimilarity(precondition1, precondition2);
		} else {
			if ((precondition1 == null) && (precondition2 == null)) {
				preconditionSimilarity = 1;
				realPreInf = 0;
			} else {
				// only one is null
				preconditionSimilarity = 0.5f;
			}
		}

		/* Postconditionsimilarity */
		double postconditionSimilarity = 0;
		if ((postcondition1 != null) && (postcondition2 != null)) {
			postconditionSimilarity = engine.getSimilarity(postcondition1, postcondition2);
		} else {
			if ((postcondition1 == null) && (postcondition2 == null)) {
				postconditionSimilarity = 1;
				realPostInf = 0;
			} else {
				// only one is null
				postconditionSimilarity = 0.3f;
			}
		}
		

		return (preconditionSimilarity	* realPreInf
				+ postconditionSimilarity * realPostInf
				+ (1-realPreInf-realPostInf) * scenarioStepsSim
               );
	}

	@Override
	protected double elementSimilarity(UkoCompareEngine engine,
			ConstrainedLanguageSentence element1,
			ConstrainedLanguageSentence element2) {
		return engine.getSimilarity(element1, element2);
	}


}
