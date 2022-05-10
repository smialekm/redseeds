package eu.redseeds.sc.query.engine.impl;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

import de.uni_koblenz.jgralab.Vertex;
import eu.redseeds.sc.query.engine.ComparisonType;
import eu.redseeds.sc.query.engine.SimilarityException;
import eu.redseeds.sc.query.engine.SimilarityValue;
import eu.redseeds.sc.query.engine.ukocompare.UkoCompareEngine;
import eu.redseeds.scl.SCLGraph;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.svosentences.SVOSentenceDTO;
import eu.redseeds.scl.rsl.rsldomainelements.domainelements.DomainElement;
import eu.redseeds.scl.rsl.rslkernel.elements.HyperlinkedSentence;
import eu.redseeds.scl.rsl.rslrequirementrepresentations.descriptiverequirementrepresentations.ConstrainedLanguageScenario;
import eu.redseeds.scl.rsl.rslrequirementrepresentationsentences.constrainedlanguagesentences.RejoinSentence;
import eu.redseeds.scl.rsl.rslrequirementrepresentationsentences.controlsentences.ConditionSentence;
import eu.redseeds.scl.rsl.rslrequirementrepresentationsentences.svosentences.SVOSentence;
import eu.redseeds.scl.rsl.rslrequirements.requirementsspecifications.Requirement;

public class SimilarityValueUKo extends AbstractSimilarityValue implements SimilarityValue {

	
	private UkoCompareEngine engine = null;
	
	public static String getSentenceText(HyperlinkedSentence sentence) {
		if ((sentence.getSentenceText() != null)
				&& (!sentence.getSentenceText().trim().equals(""))) {
			return sentence.getSentenceText();
		}
		if (sentence instanceof SVOSentence) {
			SVOSentenceDTO svo = (SVOSentenceDTO) sentence;
			return svo.getFullSentenceText();
		}
		if (sentence instanceof RejoinSentence) {
			return "Rejoin: " + (sentence);
		}
		if (sentence instanceof ConditionSentence) {
			return "Condition: " + (sentence);
		}
		return "Sentence with type " + sentence.getM1Class().getSimpleName()
				+ " and id: " + sentence.getId();
	}

	
	
	@Override
	protected Map<Vertex, Double> getSimilarVertices(Vertex v) {
		return engine.getSimilarVertices(v);
	}
		
	
	
	/**
	 * Creates a similarity value that holds the similarity of the currentCase
	 * to the pastCase. The currentCase is restricted to the requirements
	 * specified in <code>inputRequirementsSet</code> and only they are compared
	 * to the complete pastCase.
	 */
	public SimilarityValueUKo(UkoCompareEngine engine,
			Set<Requirement> inputRequirementsSet,
			Set<DomainElement> inputDomainElementsSet,
			ComparisonType comparisonType) throws SimilarityException {
		super((SCLGraph)engine.getCurrentGraph(), (SCLGraph) engine.getPastGraph(), inputRequirementsSet, inputDomainElementsSet, comparisonType);
		this.engine = engine;
		computeDomainElementMatches();
		computeRequirementMatches();
		if (DEBUG) {
			try {
				writeDebugOutput(engine, currentCase, pastCase);
			} catch (Exception e1) {
				System.out.println("Exception caught " + e1.getMessage());
				e1.printStackTrace();
			}
		}
	}
	
	private void writeDebugOutput(UkoCompareEngine engine, SCLGraph currentCase, SCLGraph pastCase)
			throws IOException {
		String currentCaseName = currentCase.getFirstSoftwareCase().getName();
		if (currentCaseName == null) {
			currentCaseName = currentCase.getUid();
		}
		String pastCaseName = pastCase.getFirstSoftwareCase().getName();
		if (pastCaseName == null) {
			pastCaseName = pastCase.getUid();
		}
		BufferedWriter scenarioSimWriter = new BufferedWriter(new FileWriter(
				DEBUG_PATH + currentCaseName + "-" + pastCaseName + ".txt"));
		for (ConstrainedLanguageScenario currentVertex : currentCase.getConstrainedLanguageScenarioVertices()) {
			for (ConstrainedLanguageScenario pastVertex : pastCase.getConstrainedLanguageScenarioVertices()) {
					double similarity = engine.getSimilarity(currentVertex, pastVertex);
						scenarioSimWriter.write("Scenario \"" + currentVertex.getName()
								+ "\" and \"" + pastVertex.getName()
								+ "\" are similar with a value of "
								+ similarity + "\n");
						scenarioSimWriter.write("Scenario 1 \n");
						for (HyperlinkedSentence sentence : currentVertex.getScenarioStepList()) {
							if (!getSentenceText(sentence).equals("")) {
								scenarioSimWriter.write("    "	+ getSentenceText(sentence) + "\n");
							}
						}
						scenarioSimWriter.write("Scenario 2 \n");
						for (HyperlinkedSentence sentence : pastVertex.getScenarioStepList()) {
							if (!getSentenceText(sentence).equals("")) {
								scenarioSimWriter.write("    "	+ getSentenceText(sentence) + "\n");
							}
						}
						scenarioSimWriter
								.write("----------------------------------\n");
			}
		}
		scenarioSimWriter.flush();
		scenarioSimWriter.close();
	}

	
	
}
