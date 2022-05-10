package eu.redseeds.sc.query.engine.ukocompare;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import de.uni_koblenz.jgralab.Edge;
import de.uni_koblenz.jgralab.Graph;
import de.uni_koblenz.jgralab.Vertex;
import eu.redseeds.sc.query.engine.ComparisonType;
import eu.redseeds.sc.query.engine.SimilarityException;
import eu.redseeds.sc.query.engine.impl.SimilarityValueUKo;
import eu.redseeds.scl.SCLGraph;
import eu.redseeds.scl.model.sclkernel.ClipboardDTO;
import eu.redseeds.scl.rsl.rsldomainelements.domainelements.DomainElement;
import eu.redseeds.scl.rsl.rslrequirements.requirementsspecifications.RSLUseCase;
import eu.redseeds.scl.rsl.rslrequirements.requirementsspecifications.Requirement;
import eu.redseeds.scl.sclkernel.Clipboard;
import eu.redseeds.scl.sclkernel.SCLElement;
import eu.redseeds.scl.sclkernel.SCLRelationship;

/**
 * Fast, simple and clean alternative to the SiDiff framework to compare
 * ReDSeeDS software-case graphs. To configure the tool, e.g. to define
 * which measures to use for which elements, look at the to lists
 * {@code compareSequence} and {@code compareConfig} which specify the 
 * sequence of the comparison and the configuration for the single elements 
 * @author dbildh@uni-koblenz.de
 *
 */
public class UkoCompareEngine {

	/* the current graph which is compared to the past graph */
	private Graph currentGraph = null;
	
	/**
	 * @return the graph of the current case which is compared to the past case
	 */
	public Graph getCurrentGraph() {
		return currentGraph;
	}
	
	/* the past graph to which the current graph is compared */
	private Graph pastGraph = null;
	
	/**
	 * @return the graph of the past case to which the current case is compared
	 */
	public Graph getPastGraph() {
		return pastGraph;
	}
	
	/**
	 * Maps vertices to the elements which are similar to them
	 */
	private Map<Vertex, Map<Vertex, Double>> vertexToSimilarElementsMap;
	
	private Map<Class<? extends Vertex>, List<Vertex>> vertexClassesToPastCaseInstances;

	private Map<Class<? extends Vertex>, List<Vertex>> vertexClassesToCurrentCaseInstances;

	/**
	 * The compareSequence specifies the order in which the elements are compared and that some 
	 * elements, especially InvocationSentences, Scenarios and UseCases must be compared three
	 * times because they influence eachother
	 */
	private CompareSequence compareSequence= new CompareSequence();
	
	private CompareConfiguration compareConfiguration = new CombinedCompareConfiguration();

	/**
	 * Creates a Map which holds for each VertexClass a list of vertices in the graph
	 * that belong to the main case and not to a clipboard
	 */
	private Map<Class<? extends Vertex>, List<Vertex>> initVerticesInMainCase(Graph graph) {
		Map<Class<? extends Vertex>, List<Vertex>> returnValue = new HashMap<Class<? extends Vertex>, List<Vertex>>(compareConfiguration.size());
		List<Clipboard> clipboardsInCase = new ArrayList<Clipboard>();
   		for (Clipboard c : ((SCLGraph)graph).getClipboardVertices()) {
   			clipboardsInCase.add(c);
   		}
   		
       	for (Class<? extends Vertex> vc : compareConfiguration.getConfiguredClasses()) {
       		if (vc == RSLUseCase.class)
       			continue;
       		List<Vertex> verticesOfClass = new ArrayList<Vertex>();
       		returnValue.put(vc, verticesOfClass);
       		for (Vertex v : graph.vertices(vc)) {
       			if (!isMemberOfClipboard(v, clipboardsInCase))
       				verticesOfClass.add(v);
       		}
       	}	
   		
		return returnValue;
	}
	
	
	/**
	 * Creates a new instance of this {@link UkoCompareEngine} that compares
	 * the tow given graphs
	 * @param graph1
	 * @param graph2
	 */
    public UkoCompareEngine(Graph graph1, Graph graph2) {
		vertexToSimilarElementsMap = new HashMap<Vertex, Map<Vertex, Double>>(graph1.getVCount());
		this.currentGraph = graph1;
		this.pastGraph = graph2;
		vertexClassesToPastCaseInstances = initVerticesInMainCase(graph2);
		vertexClassesToCurrentCaseInstances = initVerticesInMainCase(graph1);
       	for (Class<? extends Vertex> vc : compareSequence) {
    		compareVertexClasses(vc);
    	}
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

    
    /**
     * Compares all VertexClasses that match the given VertexClass vc
     * @param vc
     */
    private void compareVertexClasses(Class<? extends Vertex> vc) {
    //	System.out.println("Comparing vertices of class" + vc.getName());
    	if (vertexClassesToCurrentCaseInstances.get(vc) == null)
    		return;
		Iterable<CompareConfigurationEntry> entries = compareConfiguration.getConfigurations(vc);
		if (entries == null)
			return;
    	for (Vertex vertexOfCurrentCase : vertexClassesToCurrentCaseInstances.get(vc)) {
    	//	System.out.println("Comparing vertex " + vertexOfCurrentCase);
			Map<Vertex, Double> currentMap = new HashMap<Vertex, Double>(vertexClassesToPastCaseInstances.get(vc).size());
			vertexToSimilarElementsMap.put(vertexOfCurrentCase, currentMap);
    		for (Vertex vertexOfPastCase : vertexClassesToPastCaseInstances.get(vc)) {
    		//	System.out.println("  Comparing with vertex " + vertexOfPastCase);
    			double value = 0;
    			double totalUsedWeight = 0;
    			for (CompareConfigurationEntry entry : entries) {
    			//	System.out.print("   Comparing with configuration " + entry.compareFunctionToUse);
    				double currentVal = entry.compareFunctionToUse.ukoCompare(this, vertexOfCurrentCase, vertexOfPastCase);
    				if (currentVal >= 0) {
    					if (currentVal > 1)
    						currentVal = 1;
    					value += currentVal * entry.weight;
    					totalUsedWeight += entry.weight;
    				} 
    			//	System.out.println("  result: " + currentVal);
    				
    			}
    			if (totalUsedWeight > 0)
    				value /= totalUsedWeight;
    			else
    				value = 0;
    			//System.out.println("  Combined value: " + value);
    			if (value > 0.1) {
    			//	System.out.println("  Putting into map");
    				currentMap.put(vertexOfPastCase, value);
    			}	
    		}
    	}
    }
    
	/**
	 * Get the similarity of th two given vertices wehre 
	 * vertex1 is vertex of the current graph and vertex2
	 * is a vertex of the past graph
	 * @param vertex1
	 * @param vertex2
	 * @return
	 */
	public double getSimilarity(Vertex vertex1, Vertex vertex2) {
		Map<Vertex, Double> map = vertexToSimilarElementsMap.get(vertex1);
		if (map != null) {
			Double doub = map.get(vertex2);
			if (doub != null)
				return doub;
		}	
		return -1;		
	}
	
	/**
	 * @param vertex
	 * @return the map of elements that are similar to the given vertex and their similarities
	 */
	public Map<Vertex, Double> getSimilarVertices(Vertex vertex) {
		return vertexToSimilarElementsMap.get(vertex);
	}
		
	public SimilarityValueUKo getCaseSimilarity(Set<Requirement> inputRequirementsSet,
											 Set<DomainElement> inputDomainElementsSet,
											 ComparisonType comparisonType) throws SimilarityException {
		SimilarityValueUKo retVal = new SimilarityValueUKo(this, inputRequirementsSet, inputDomainElementsSet, comparisonType);
		return retVal;
	}
		
}
