package eu.redseeds.sc.query.engine.impl;


import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import de.uni_koblenz.jgralab.Vertex;
import eu.redseeds.owl.connector.Comparator;
import eu.redseeds.sc.query.engine.ComparisonType;
import eu.redseeds.scl.SCLGraph;
import eu.redseeds.scl.rsl.rsldomainelements.domainelements.DomainElement;
import eu.redseeds.scl.rsl.rslrequirements.requirementsspecifications.Requirement;

public class SimilarityValueUHHAdapted extends AbstractSimilarityValue {
	
	private Comparator simComparator;
	public static boolean HEAP_INFO = false;
	private void printHeapSize(String info){
		if (HEAP_INFO == true) {
		long heapSize = Runtime.getRuntime().totalMemory();
	    
	    // Get maximum size of heap in bytes. The heap cannot grow beyond this size.
	    // Any attempt will result in an OutOfMemoryException.
	    long heapMaxSize = Runtime.getRuntime().maxMemory();
	    
	    // Get amount of free memory within the heap in bytes. This size will increase
	    // after garbage collection and decrease as new objects are created.
	    long heapFreeSize = Runtime.getRuntime().freeMemory();
	    System.out.println("HEAP INFO");
	    System.out.println(info + "   CurrentHeap: " +  heapSize + "Max Size: " + heapMaxSize + "Free Size:" + heapFreeSize);
		}
	}
	
	
	public void clearSimilarityValue () {
		simComparator.clearComparator();
	}
	
	public SimilarityValueUHHAdapted(SCLGraph qGraph, SCLGraph scGraph, Set<Requirement> requirements,
			Set<DomainElement> domainElements, ComparisonType comparisonType) throws Exception {
		super(qGraph, scGraph, requirements, domainElements, comparisonType);
		System.err.println("Creating UH Similarity Value");
		simComparator = new Comparator(qGraph, scGraph);
		printHeapSize("Before Runcompare");
		try {
			overallSimilarity = simComparator.runCompare();
		} catch(Exception e) {
			e.printStackTrace();
		}		
		printHeapSize("After RunCompare");
		//TODO: test, if this works correctly!
		
		System.err.println(" Computing matches of domain elements and requirements...");
		this.computeDomainElementMatches();
		this.computeRequirementMatches();
		System.out.println(" ...complexste!");
	
		
	}
	
	
	@Override
	public double getSimilarity(Requirement req1, Requirement req2){
		return simComparator.runCompare(req1, req2);
	}
	
	@Override
	public double getSimilarity(DomainElement de1, DomainElement de2){
		return simComparator.runCompare(de1, de2);
	}	
		

	@Override
	protected Map<Vertex, Double> getSimilarVertices(Vertex v) {
		if (v instanceof DomainElement) {
			DomainElement d1 = (DomainElement) v;
			Map<Vertex, Double> map = new TreeMap<Vertex, Double>();
			for (DomainElement d2 : getPastCase().getDomainElementVertices()) {
				double sim = simComparator.runCompare(d1, d2);
				if (sim > 0.1)
					map.put(d2, sim);
			}
			
		} else if (v instanceof Requirement) {
			Requirement r1 = (Requirement) v;
			Map<Vertex, Double> map = new TreeMap<Vertex, Double>();
			for (Requirement r2 : getPastCase().getRequirementVertices()) {
				double sim = simComparator.runCompare(r1, r2);
				if (sim > 0.1)
					map.put(r2, sim);
			}
		}
		return null;		
	}

	
}
