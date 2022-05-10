package eu.redseeds.sc.query.engine.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import de.uni_koblenz.jgralab.Vertex;
import eu.redseeds.sc.query.engine.SimilarityException;
import eu.redseeds.scl.rsl.rsldomainelements.domainelements.DomainElement;
import eu.redseeds.scl.rsl.rslrequirements.requirementsspecifications.Requirement;


public class CombinedSimilarityValue extends AbstractSimilarityValue {
	
	/* toggles the weight of the combined approach of UKo and Fraunhofer 
	 * in the similarity calculation. Should be between 0 (not weighted, whole comparison 
	 * is based on OWL by UH) and 1.0 (maximal weight, OWL comparison is not used)
	 */
	private static final double WEIGHT_OF_UKOFRAUNHOFER_DIFF = 0.66;
	
	private static double WEIGHT_OF_OWL_DIFF;
	
	private SimilarityValueUHHAdapted uhSim;
	
	private SimilarityValueUKo ukoSim;
	
	
	
	
	public CombinedSimilarityValue(SimilarityValueUKo ukoSim, SimilarityValueUHHAdapted uhSim) throws SimilarityException {
		super(ukoSim.getCurrentCase(), ukoSim.getPastCase(), ukoSim.inputRequirementSet, ukoSim.inputDomainElementsSet, ukoSim.getComparisonType());
		if ((ukoSim != null) && (uhSim != null))
		if (   ( ukoSim.getCurrentCase() != uhSim.getCurrentCase())
		    || (ukoSim.getPastCase() != uhSim.getPastCase())
		    || (ukoSim.getRequirementCountInCurrentCase() != uhSim.getRequirementCountInCurrentCase())
		    || (ukoSim.getComparisonType() != uhSim.getComparisonType()))
			throw new RuntimeException("Cannot combine two similarity values calculated with different graphs or comparison types, \n  UKOCurrentCase: " + ukoSim.getCurrentCase() + " \n  " +
					"UKOPastCase: " + ukoSim.getPastCase() + "\n UHCurrentCase: " + uhSim.getCurrentCase() + " \n UHPastCase: " + uhSim.getPastCase() + " \n UKOType: " + ukoSim.getComparisonType() + "\n UHType: " + uhSim.getComparisonType() + "\n UKOReqCount: " + ukoSim.getRequirementCountInCurrentCase() + "\n UHReqCount: " + uhSim.getRequirementCountInCurrentCase());
		WEIGHT_OF_OWL_DIFF = 1 - WEIGHT_OF_UKOFRAUNHOFER_DIFF;
	
		this.ukoSim = ukoSim;
		this.uhSim = uhSim;
		
		
		computeDomainElementMatches();
		computeRequirementMatches();
		// Gains some heap space.
		this.uhSim.clearSimilarityValue();
	}
	
	
	protected void computeDomainElementMatches() throws SimilarityException {
		for (DomainElement currentDomainElement : inputDomainElementsSet) {
			double highestSim = 0;
			DomainElement partner = null;
			Map<DomainElement, Double> mapOfCurrentDomainElement = new HashMap<DomainElement, Double>();
			currentDomainElementSimilarities.put(currentDomainElement, mapOfCurrentDomainElement);
			for (Entry<DomainElement, Double> entry : ukoSim.getSimilarDomainElements(currentDomainElement).entrySet()) {
				DomainElement pastVertex = (DomainElement) entry.getKey();
				double uhVal = uhSim.getSimilarity(currentDomainElement, pastVertex);
				double combinedVal = 0;
				if (uhVal != -1)
					combinedVal = (entry.getValue() * WEIGHT_OF_UKOFRAUNHOFER_DIFF) + (uhVal * WEIGHT_OF_OWL_DIFF);
				else
					combinedVal = entry.getValue();
				mapOfCurrentDomainElement.put(pastVertex, combinedVal);
				Map<DomainElement, Double> mapOfPastDomainElement = pastDomainElementSimilarities.get(pastVertex);
				if (mapOfPastDomainElement == null) {
					mapOfPastDomainElement = new HashMap<DomainElement, Double>();
					pastDomainElementSimilarities.put(pastVertex, mapOfPastDomainElement);
				}
				mapOfPastDomainElement.put(currentDomainElement, combinedVal);
				if (combinedVal > highestSim) {
					partner = pastVertex;
					highestSim = combinedVal;
				}
			}	
			if ((partner != null) && (highestSim > MATCHING_THRESHOLD)) {
				domainElementMatches.add(new DomainElementMatchImpl(currentDomainElement,
					partner, highestSim));
			}
		}
	}

	

	protected void computeRequirementMatches() throws SimilarityException {
		for (Requirement currentRequirement : inputRequirementSet) {
			double highestSim = 0;
			Requirement partner = null;
			Map<Requirement, Double> mapOfCurrentRequirement = new HashMap<Requirement, Double>();
			currentRequirementSimilarities.put(currentRequirement, mapOfCurrentRequirement);
			for (Entry<Requirement, Double> entry : ukoSim.getSimilarRequirements(currentRequirement).entrySet()) {
				Requirement pastVertex = (Requirement) entry.getKey();
				double uhVal = uhSim.getSimilarity(currentRequirement, pastVertex);
				double combinedVal = 0;
				if (uhVal != -1)
					combinedVal = (entry.getValue() * WEIGHT_OF_UKOFRAUNHOFER_DIFF) + (uhVal * WEIGHT_OF_OWL_DIFF);
				else
					combinedVal = entry.getValue();
				mapOfCurrentRequirement.put(pastVertex, combinedVal);
				Map<Requirement, Double> mapOfPastRequirement = pastRequirementSimilarities.get(pastVertex);
				if (mapOfPastRequirement == null) {
					mapOfPastRequirement = new HashMap<Requirement, Double>();
					pastRequirementSimilarities.put(pastVertex, mapOfPastRequirement);
				}
				mapOfPastRequirement.put(currentRequirement, combinedVal);
				if (combinedVal > highestSim) {
					partner = pastVertex;
					highestSim = combinedVal;
				}
			}	
			if ((partner != null) && (highestSim > MATCHING_THRESHOLD)) {
				requirementMatches.add(new RequirementMatchImpl(currentRequirement,
					partner, highestSim));
			}
		}
	}
	



	@Override
	protected Map<Vertex, Double> getSimilarVertices(Vertex v) {
		throw new UnsupportedOperationException("getSimilarVertices(Vertex v) is not implemented in CombinedSimilarityValue");
	}
	

}
