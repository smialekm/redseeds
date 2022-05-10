package eu.redseeds.sc.query.comparefunctions;

import java.util.ArrayList;
import java.util.List;

import de.uni_koblenz.jgralab.Edge;
import de.uni_koblenz.jgralab.EdgeDirection;
import de.uni_koblenz.jgralab.Vertex;
import eu.redseeds.sc.query.engine.ukocompare.UkoCompareEngine;


public class RemoteNodesSimilarityLevenshtein extends AbstractLevenshteinSimilarity<Vertex> {
	
	Class<? extends Edge> edgeClass;
	
	EdgeDirection dir;
	
	List<Class<? extends Vertex>> allowedVertexClasses = null;
	
	
	public RemoteNodesSimilarityLevenshtein(Class<? extends Edge> edgeClass, EdgeDirection dir) {
		this.edgeClass = edgeClass;
		this.dir = dir;
	}
	
	public RemoteNodesSimilarityLevenshtein(Class<? extends Edge> edgeClass, EdgeDirection dir, List<Class<? extends Vertex>> allowedVertexClasses) {
		this.edgeClass = edgeClass;
		this.dir = dir;
	}
	
	public RemoteNodesSimilarityLevenshtein(Class<? extends Edge> edgeClass, EdgeDirection dir, List<Class<? extends Vertex>> allowedVertexClasses, double semanticInfluence) {
		this.edgeClass = edgeClass;
		this.dir = dir;
		
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
		
		return compareElementLists(engine, elements1, elements2);
	}

	@Override
	protected double elementSimilarity(UkoCompareEngine engine, Vertex element1, Vertex element2) {
		return engine.getSimilarity(element1, element2);
	}


	

}
