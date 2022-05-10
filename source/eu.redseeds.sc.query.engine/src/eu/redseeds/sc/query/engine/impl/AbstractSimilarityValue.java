package eu.redseeds.sc.query.engine.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.Map.Entry;

import de.uni_koblenz.jgralab.Edge;
import de.uni_koblenz.jgralab.Vertex;
import eu.redseeds.sc.query.engine.ComparisonType;
import eu.redseeds.sc.query.engine.DomainElementMatch;
import eu.redseeds.sc.query.engine.RequirementMatch;
import eu.redseeds.sc.query.engine.SimilarityException;
import eu.redseeds.sc.query.engine.SimilarityValue;
import eu.redseeds.scl.SCLGraph;
import eu.redseeds.scl.model.sclkernel.ClipboardDTO;
import eu.redseeds.scl.rsl.rsldomainelements.domainelements.DomainElement;
import eu.redseeds.scl.rsl.rslrequirements.requirementsspecifications.Requirement;
import eu.redseeds.scl.sclkernel.Clipboard;
import eu.redseeds.scl.sclkernel.SCLElement;
import eu.redseeds.scl.sclkernel.SCLRelationship;

public abstract class AbstractSimilarityValue implements SimilarityValue {

	protected static boolean DEBUG = false;

	public static boolean isDEBUG() {
		return DEBUG;
	}

	public static void setDEBUG(boolean debug) {
		DEBUG = debug;
	}

	protected static String DEBUG_PATH = "/tmp";
	protected static final double REQUIREMENT_INFLUENCE_IN_SIMILARITY = 0.67;
	protected static final double DOMAIN_INFLUENCE_IN_SIMILARITY = 0.33;
	protected ComparisonType usedComparisonType = null;
	protected SCLGraph currentCase = null;
	protected SCLGraph pastCase = null;
	protected int requirementsInCurrentCase = 0;
	protected int requirementsInPastCase = 0;
	protected int domainElementsInCurrentCase = 0;
	protected int domainElementsInPastCase = 0;
	protected Set<RequirementMatch> requirementMatches = null;
	protected Set<DomainElementMatch> domainElementMatches = null;
	protected Set<DomainElement> inputDomainElementsSet = null;
	protected Set<Requirement> inputRequirementSet = null;
	//Map from currentRequirement to map from past requirement to similarity 
	protected Map<Requirement, Map<Requirement, Double>> currentRequirementSimilarities = null;
	protected Map<DomainElement, Map<DomainElement, Double>> currentDomainElementSimilarities = null;
	//Map from pastRequirement to map from current requirement to similarity 
	protected Map<Requirement, Map<Requirement, Double>> pastRequirementSimilarities = null;
	protected Map<DomainElement, Map<DomainElement, Double>> pastDomainElementSimilarities = null;
	protected double overallSimilarity = -1;

	
	public ComparisonType getComparisonType() {
		return usedComparisonType;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("OverallSimilarity(");
		sb.append(currentCase.getId());
		sb.append(", ");
		sb.append(pastCase.getId());
		sb.append(") = ");
		sb.append(getSimilarityValue());
		sb.append("\n");
	
		for (RequirementMatch rm : requirementMatches) {
			sb.append("  RequirementSimilarity(");
			sb.append(rm.getCurrentRequirement());
			sb.append(", ");
			sb.append(rm.getPastRequirement());
			sb.append(") = ");
			sb.append(rm.getSimilarityValue());
			sb.append("\n");
		}
	
		for (DomainElementMatch rm : domainElementMatches) {
			sb.append("  DomainElementSimilarity(");
			sb.append(rm.getCurrentDomainElement());
			sb.append(", ");
			sb.append(rm.getPastDomainElement());
			sb.append(") = ");
			sb.append(rm.getSimilarityValue());
			sb.append("\n");
		}
	
		return sb.toString();
	}

	@Override
	public SCLGraph getCurrentCase() {
		return currentCase;
	}

	@Override
	public SCLGraph getPastCase() {
		return pastCase;
	}

	@Override
	public int getRequirementCountInCurrentCase() {
		return requirementsInCurrentCase;
	}

	@Override
	public int getRequirementCountInPastCase() {
		return requirementsInPastCase;
	}

	@Override
	public Set<RequirementMatch> getRequirementsPairs() {
		return requirementMatches;
	}

	@Override
	public double getSimilarity(Requirement currentRequirement,
			Requirement pastRequirement) throws SimilarityException {
		if (!inputRequirementSet.contains(currentRequirement)) {
			throw new SimilarityException(
					"The current requirement is not a member of the set of requirements used for comparison.");
		}
		if (!pastCase.containsVertex(pastRequirement)) {
			throw new SimilarityException(
					"The past requirement is not part of the past case.");
		}
		Double d = currentRequirementSimilarities.get(currentRequirement).get(pastRequirement);
		if (d != null) 
			return d;
		else
			return 0;
	}
	
	
	public Map<Requirement, Double> getSimilarRequirements(Requirement requirement) throws SimilarityException {
		if (inputRequirementSet.contains(requirement)) {
			return currentRequirementSimilarities.get(requirement);
		} else if (pastCase.containsVertex(requirement)) {
			return pastRequirementSimilarities.get(requirement);
		} else {
			throw new SimilarityException(
			"The given requirement is neither a member of the set of requirements used for comparison nor of the past case.");
		}
	}
	
	

	public Map<DomainElement, Double> getSimilarDomainElements(DomainElement domainElement) throws SimilarityException {
		if (inputDomainElementsSet.contains(domainElement)) {
			return currentDomainElementSimilarities.get(domainElement);
		} else if (pastCase.containsVertex(domainElement)) {
			return pastDomainElementSimilarities.get(domainElement);
		} else {
			throw new SimilarityException(
			"The given domain lement is neither a member of the set of domain elements used for comparison nor of the past case.");
		}
	}
	
	
	

	@Override
	public double getSimilarityValue() {
		if (overallSimilarity == -1) {
			double domainSimilarity = getDomainSimilarityValue();
			double requirementSimilarity = getRequirementSimilarityValue();
			if (getDomainElementCountInCurrentCase() == 0)
				domainSimilarity = requirementSimilarity;
			if (getRequirementCountInCurrentCase() == 0)
				requirementSimilarity = domainSimilarity;
			switch (usedComparisonType) {
			case DOMAIN_ONLY:
				overallSimilarity = domainSimilarity;
				break;
	
			case REQUIREMENTS_ONLY:
				overallSimilarity = requirementSimilarity;
				break;
	
			case REQUIREMENTS_AND_DOMAIN:
				overallSimilarity = (domainSimilarity
						* DOMAIN_INFLUENCE_IN_SIMILARITY + requirementSimilarity
						* REQUIREMENT_INFLUENCE_IN_SIMILARITY)
						/ (DOMAIN_INFLUENCE_IN_SIMILARITY + REQUIREMENT_INFLUENCE_IN_SIMILARITY);
				break;
			default:
				throw new RuntimeException("Unhandled ComparisonType "
						+ usedComparisonType + " in similarity calculation");
			}
	
		}
		return overallSimilarity;
	}

	private double getRequirementSimilarityValue() {
		if ((getRequirementCountInCurrentCase() == 0) || (requirementMatches.size() == 0))
			return 0;
		double simSum = 0;
		for (RequirementMatch match : requirementMatches) {
			if (match.getSimilarityValue() > MATCHING_THRESHOLD)
				simSum += match.getSimilarityValue();
		}
		return simSum / getRequirementCountInCurrentCase();
	}

	private double getDomainSimilarityValue() {
		if ((getDomainElementCountInCurrentCase() == 0) || (domainElementMatches.size() == 0))
			return 0;
		double simSum = 0;
		for (DomainElementMatch match : domainElementMatches) {
			if (match.getSimilarityValue() > MATCHING_THRESHOLD)
				simSum += match.getSimilarityValue();
		}
		return simSum / getDomainElementCountInCurrentCase();
	}

	@Override
	public Set<Requirement> getMatchedRequirementsInPastCase() {
		Set<Requirement> matchedPastRequirements = new TreeSet<Requirement>();
		for (RequirementMatch match : requirementMatches) {
			matchedPastRequirements.add(match.getPastRequirement());
		}
		return matchedPastRequirements;
	}

	@Override
	public int getDomainElementCountInCurrentCase() {
		return domainElementsInCurrentCase;
	}

	@Override
	public int getDomainElementCountInPastCase() {
		return domainElementsInPastCase;
	}

	@Override
	public Set<DomainElementMatch> getDomainElementPairs() {
		return domainElementMatches;
	}

	@Override
	public Set<DomainElement> getMatchedDomainElementsInPastCase() {
		Set<DomainElement> matchedPastDomainElements = new TreeSet<DomainElement>();
		for (DomainElementMatch match : domainElementMatches) {
			matchedPastDomainElements.add(match.getPastDomainElement());
		}
		return matchedPastDomainElements;
	}

	@Override
	public double getSimilarity(DomainElement currentDomainElement, DomainElement pastDomainElement)
			throws SimilarityException {
		if (!inputDomainElementsSet.contains(currentDomainElement)) {
			throw new SimilarityException(
					"The current DomainElement is not a member of the set of DomainElements used for comparison.");
		}
		if (!pastCase.containsVertex(pastDomainElement)) {
			throw new SimilarityException(
					"The past DomainElement is not part of the past case.");
		}
		Double d = currentDomainElementSimilarities.get(currentDomainElement).get(pastDomainElement);
		if (d != null) 
			return d;
		else
			return 0;
	}



	public Set<DomainElement> getUsedDomainElements() {
		return inputDomainElementsSet;
	}

	public Set<Requirement> getUsedRequirements() {
		return inputRequirementSet;
	}
	
	protected abstract Map<Vertex, Double> getSimilarVertices(Vertex v);

	
	protected void computeRequirementMatches() throws SimilarityException  {
	//	System.out.println("Computing requirement match");
		for (Requirement currentRequirement : inputRequirementSet) {
	//		System.out.println("Comparing requirement " + currentRequirement);
			double highestSim = 0;
			Requirement partner = null;
			Map<Requirement, Double> mapOfCurrentRequirement = new HashMap<Requirement, Double>();
			currentRequirementSimilarities.put(currentRequirement, mapOfCurrentRequirement);
			Map<Vertex, Double> similarityMap = getSimilarVertices(currentRequirement);
	//		System.out.println("  Similarity map is: " + similarityMap);
	//		if (similarityMap != null)
	//			System.out.println("  Similarity map contains: " + similarityMap.size() + " elements ");
			if (similarityMap != null) {
				for (Entry<Vertex, Double> simEntry : similarityMap.entrySet()) {
					Requirement pastVertex = (Requirement) simEntry.getKey();
					System.out.print("  with requirement ");
					System.out.print(pastVertex);
					double currentSim = simEntry.getValue();
	//				System.out.println("   similarity: " + currentSim);
					mapOfCurrentRequirement.put(pastVertex, currentSim);
					Map<Requirement, Double> mapOfPastRequirement = pastRequirementSimilarities.get(pastVertex);
					if (mapOfPastRequirement == null) {
						mapOfPastRequirement = new HashMap<Requirement, Double>();
						pastRequirementSimilarities.put(pastVertex, mapOfPastRequirement);
					}
					mapOfPastRequirement.put(currentRequirement, currentSim);
					if (currentSim > highestSim) {
						partner = pastVertex;
						highestSim = currentSim;
					}
				}	
			}
			if ((partner != null) && (highestSim > MATCHING_THRESHOLD)) {
	//			System.out.println("Adding new requirement match witch similarity " + highestSim + " and partner " + partner);
				requirementMatches.add(new RequirementMatchImpl(currentRequirement,
						partner, highestSim));
			}
		}
	}
	
	
	protected void computeDomainElementMatches() throws SimilarityException {
	//	System.out.println("Computing domain element match");
		for (DomainElement currentDomainElement : inputDomainElementsSet) {
	//		System.out.println("Comparing domain element " + currentDomainElement);
			double highestSim = 0;
			DomainElement partner = null;
			HashMap<DomainElement, Double> map = new HashMap<DomainElement, Double>();
			currentDomainElementSimilarities.put(currentDomainElement, map);
			Map<Vertex, Double> similarityMap = getSimilarVertices(currentDomainElement);
			if (similarityMap != null) {
				for (Entry<Vertex, Double> simEntry : similarityMap.entrySet()) {
					DomainElement pastVertex = (DomainElement) simEntry.getKey();
	//				System.out.print("  with element " + pastVertex);
					double currentSim = simEntry.getValue();
	//				System.out.println("   similarity: " + currentSim);
					Map<DomainElement, Double> mapOfPastDomainElement = pastDomainElementSimilarities.get(pastVertex);
					if (mapOfPastDomainElement == null) {
						mapOfPastDomainElement = new HashMap<DomainElement, Double>();
						pastDomainElementSimilarities.put(pastVertex, mapOfPastDomainElement);
					}
					mapOfPastDomainElement.put(currentDomainElement, currentSim);
					map.put(pastVertex, currentSim);
					if (currentSim > highestSim) {
						partner = pastVertex;
						highestSim = currentSim;
					}
				}
			}
			if ((partner != null) && (highestSim > MATCHING_THRESHOLD)) {
	//			System.out.println("Adding new domain element match witch similarity " + highestSim + " and partner " + partner);
				domainElementMatches.add(new DomainElementMatchImpl(currentDomainElement,
						partner, highestSim));
			}
		}
	}


	protected AbstractSimilarityValue(SCLGraph currentCase, SCLGraph pastCase, Set<Requirement> inRequirementsSet,
			Set<DomainElement> inDomainElementsSet,
			ComparisonType comparisonType) {
		this.currentCase = currentCase;
		this.pastCase = pastCase;
		if (inRequirementsSet == null) {
			inRequirementsSet = new HashSet<Requirement>();
			for (Requirement r : currentCase.getRequirementVertices()) {
				inRequirementsSet.add(r);
			}
		}
		if (inDomainElementsSet == null) {
			inDomainElementsSet = new HashSet<DomainElement>();
			for (DomainElement d : currentCase.getDomainElementVertices()) {
				inDomainElementsSet.add(d);
			}
		}
//		System.out.println("Set of input requirement contains " + inRequirementsSet.size() + " element");
//		System.out.println("Set of input domain element contains " + inDomainElementsSet.size() + " element");
		usedComparisonType = comparisonType;
		this.currentCase = currentCase;
		this.pastCase = pastCase;
   		List<Clipboard> pastCaseClipboards = new ArrayList<Clipboard>();
   		List<Clipboard> currentCaseClipboards = new ArrayList<Clipboard>();
   		for (Clipboard c : ((SCLGraph)currentCase).getClipboardVertices()) {
   			currentCaseClipboards.add(c);
   		}
   		for (Clipboard c : ((SCLGraph)pastCase).getClipboardVertices()) {
   			pastCaseClipboards.add(c);
   		}
   		this.inputDomainElementsSet = new HashSet<DomainElement>();
   		this.inputRequirementSet = new HashSet<Requirement>();
   		for (Requirement r : inRequirementsSet) {
   			if (!isMemberOfClipboard(r, currentCaseClipboards)) {
   				this.inputRequirementSet.add(r);
   			}
   		}		
   		for (DomainElement d : inDomainElementsSet) {
   			if (!isMemberOfClipboard(d, currentCaseClipboards))
   				this.inputDomainElementsSet.add(d);
   		}
		requirementsInCurrentCase = this.inputRequirementSet.size();
		requirementMatches = new TreeSet<RequirementMatch>();
		domainElementsInCurrentCase = this.inputDomainElementsSet.size();
		domainElementMatches = new TreeSet<DomainElementMatch>();
		
		currentRequirementSimilarities = new HashMap<Requirement, Map<Requirement, Double>>();
		currentDomainElementSimilarities = new HashMap<DomainElement, Map<DomainElement, Double>>();
		pastRequirementSimilarities = new HashMap<Requirement, Map<Requirement, Double>>();
		pastDomainElementSimilarities = new HashMap<DomainElement, Map<DomainElement, Double>>();
		for (Requirement r : pastCase.getRequirementVertices()) {
			if (!isMemberOfClipboard(r, pastCaseClipboards))
				requirementsInPastCase++;
		}
//		System.out.println("In past case there are " + requirementsInPastCase + " requirement") ;
		for (DomainElement d : pastCase.getDomainElementVertices()) {
			if (!isMemberOfClipboard(d, pastCaseClipboards))
				domainElementsInPastCase++;
		}
//		System.out.println("In past case there are " + domainElementsInPastCase + " domain elements") ;
		
	}
	
    private boolean isMemberOfClipboard(Vertex v, List<Clipboard> clipboards) {
    	SCLElement elem = null;
    	if (v instanceof SCLElement) {
    		elem = (SCLElement) v;
    	} else {
    		SCLRelationship r = (SCLRelationship) v;
    		Edge edge = r.getFirstSCLRelationshipLinksToSource();
    		if (edge != null)
    			elem = (SCLElement) edge.getThat();
    	}		
    	if (elem != null)
    		for (Clipboard c : clipboards) {
    			if (((ClipboardDTO)c).isClipboardMember(elem)) {
    				return true;
    			}
    		}	
    	return false;
    }
	
}