package eu.redseeds.sc.query.comparefunctions;

import java.util.ArrayList;
import java.util.List;

import de.uni_koblenz.jgralab.Edge;
import de.uni_koblenz.jgralab.EdgeDirection;
import de.uni_koblenz.jgralab.Vertex;
import eu.redseeds.sc.query.engine.ukocompare.UkoCompareEngine;


public class RemoteNodesSimilarityLi extends AbstractSimilarRemoteNodesLi {
	
	Class<? extends Edge> edgeClass;
	
	EdgeDirection dir;
	
	List<Class<? extends Vertex>> allowedVertexClasses = null;
	
	
	public RemoteNodesSimilarityLi(Class<? extends Edge> edgeClass, EdgeDirection dir) {
		this.edgeClass = edgeClass;
		this.dir = dir;
	}
	
	public RemoteNodesSimilarityLi(Class<? extends Edge> edgeClass, EdgeDirection dir, double semanticInfluence) {
		this.edgeClass = edgeClass;
		this.dir = dir;
		STRUCTURAL_SIMILARITY_INFLUENCE = 1-semanticInfluence;
		SEMANTIC_SIMILARITY_INFLUENCE = semanticInfluence;
	}
	
	public RemoteNodesSimilarityLi(Class<? extends Edge> edgeClass, EdgeDirection dir, List<Class<? extends Vertex>> allowedVertexClasses) {
		this.edgeClass = edgeClass;
		this.dir = dir;
		this.allowedVertexClasses = allowedVertexClasses;
	}
	
	public RemoteNodesSimilarityLi(Class<? extends Edge> edgeClass, EdgeDirection dir, List<Class<? extends Vertex>> allowedVertexClasses, double semanticInfluence) {
		this.edgeClass = edgeClass;
		this.dir = dir;
		this.allowedVertexClasses = allowedVertexClasses;
		STRUCTURAL_SIMILARITY_INFLUENCE = 1-semanticInfluence;
		SEMANTIC_SIMILARITY_INFLUENCE = semanticInfluence;
	}
	
	
	
	
	public double ukoCompare(UkoCompareEngine engine, Vertex vertex1, Vertex vertex2) {	
		List<Vertex> elements1 = new ArrayList<Vertex>();
		List<Vertex> elements2 = new ArrayList<Vertex>();
		for (Edge e : vertex1.incidences(edgeClass, dir)) {
			if (allowedVertexClasses == null ||  allowedVertexClasses.contains(e.getThat().getM1Class()))
					elements1.add(e.getThat());
		}
		for (Edge e : vertex2.incidences(edgeClass, dir)) {
			if (allowedVertexClasses == null || allowedVertexClasses.contains(e.getThat().getM1Class()))
					elements2.add(e.getThat());
		}
		if (elements1.isEmpty() && elements2.isEmpty())
			return -1;
		if (elements1.isEmpty() || elements2.isEmpty())
			return 0.3;
//		System.out.println("RemoteNodeSimilarity of elements: " + vertex1 + " and " + vertex2);
		return performComparison(engine, elements1, elements2);
	}


	

}
