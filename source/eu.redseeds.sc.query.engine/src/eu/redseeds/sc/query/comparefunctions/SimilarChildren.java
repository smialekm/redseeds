package eu.redseeds.sc.query.comparefunctions;

import java.util.ArrayList;
import java.util.List;

import de.uni_koblenz.jgralab.Aggregation;
import de.uni_koblenz.jgralab.Edge;
import de.uni_koblenz.jgralab.Vertex;
import de.uni_koblenz.jgralab.schema.AggregationClass;
import eu.redseeds.sc.query.engine.ukocompare.UkoCompareEngine;

public class SimilarChildren extends AbstractSimilarRemoteNodesLi {
	
	public double ukoCompare(UkoCompareEngine engine, Vertex vertex1, Vertex vertex2) {	
		List<Vertex> elements1 = new ArrayList<Vertex>();
		List<Vertex> elements2 = new ArrayList<Vertex>();
		
		for (Edge e : vertex1.incidences(Aggregation.class)) {
			AggregationClass ac = (AggregationClass) e.getAttributedElementClass();
			if (ac.isAggregateFrom() == e.isNormal()) {
				elements1.add(e.getThat());
			}
		}
		
		for (Edge e : vertex2.incidences(Aggregation.class)) {
			AggregationClass ac = (AggregationClass) e.getAttributedElementClass();
			if (ac.isAggregateFrom() == e.isNormal()) {
				elements2.add(e.getThat());
			}
		}
		
		return performComparison(engine, elements1, elements2);
	}
	
}
