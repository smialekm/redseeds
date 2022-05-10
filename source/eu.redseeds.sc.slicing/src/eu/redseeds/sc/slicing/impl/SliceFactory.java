package eu.redseeds.sc.slicing.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import de.uni_koblenz.jgralab.Edge;
import de.uni_koblenz.jgralab.Graph;
import de.uni_koblenz.jgralab.Vertex;
import de.uni_koblenz.jgralab.greql2.evaluator.GreqlEvaluator;
import de.uni_koblenz.jgralab.greql2.jvalue.JValue;
import de.uni_koblenz.jgralab.greql2.jvalue.JValueSet;
import de.uni_koblenz.jgralab.greql2.jvalue.JValueSlice;
import eu.redseeds.sc.slicing.Slice;
import eu.redseeds.sc.slicing.Slicer;
import eu.redseeds.sc.slicing.Slice.SliceElementInfo;

public class SliceFactory {


	/**
	 * Computes a slice with the slicing criterion represented by the set of
	 * vertices <code>inputVertices</code> and the regular path expression
	 * <code>rpe</code>. Essentially, a slice contains all vertices and edges
	 * reachable from any vertex in <code>inputVertices</code> by any path
	 * conforming to <code>rpe</code>.
	 * 
	 * @param inputVertices
	 *            a <code>Set</code> of vertices
	 * @param rpe
	 *            a regular path expression
	 * 
	 * @return an instance of <code>Slice</code> representing the slice
	 *         computed with respect to <code>inputVertices</code> and
	 *         <code>rpe</code>
	 */
	public static Slice slice(Set<Vertex> inputVertices, String rpe) {
		if (inputVertices.isEmpty()) {
			throw new RuntimeException("No vertices selected as slicing criterion!");
		}
		
		Graph graph = inputVertices.iterator().next().getGraph();
		

		SliceImpl slice = new SliceImpl(graph);
		GreqlEvaluator greqlEvaluator;
		
		// build query string
		String query = Slicer.RPE_IMPORT_DECLARATIONS + " using sliCritVertices: slice(sliCritVertices, " + rpe + ")";
				
		// check if all vertices in inputVertices belong to the same graph
		if (!checkForSameGraph(inputVertices)) {
			throw new RuntimeException("Malformed slicing criterion: Not all "
					+ "vertices belong to the same graph.");
		}
		
		greqlEvaluator = new GreqlEvaluator(query, graph,
				createBinding(inputVertices));

		try {
			greqlEvaluator.startEvaluation();
		} catch (Exception e) {
			e.printStackTrace();
		}

		markGraph(slice, (JValueSlice) greqlEvaluator
				.getEvaluationResult());
		return slice;
	}

	/**
	 * Creates markings for <code>GraphElement</code>s in <code>slice</code>.
	 * 
	 * @param marker the <code>GraphMarker&lt;SliceElementInfo&gt; maintaining the markings
	 * @param slice the <code>JValueSlice</code> whose elements shall be marked
	 */
	private static void markGraph(SliceImpl slice,	JValueSlice jValueSlice) {
		Set<Class<? extends Vertex>> relationlikeVertexTypes = new HashSet<Class<? extends Vertex>>();
		
		relationlikeVertexTypes.add(eu.redseeds.scl.rsl.rslrequirements.usecaserelationships.InvocationRelationship.class);
		
		
		Vertex v;

		// mark edges
		for (JValue edge : jValueSlice.edges()) {
			slice.setSliceElementInfo(edge.toEdge().getNormalEdge(), SliceElementInfo.EDGE);
		}

		// mark all inner vertices as INNER_VERTEX if they have no marking yet
		for (JValue node : jValueSlice.innerNodes()) {
			v = node.toVertex();

			if (slice.getSliceElementInfo(v) == null) {
				slice.setSliceElementInfo(v, SliceElementInfo.INNER);
			}
			for (Edge e : v.incidences()) {
				if (slice.getSliceElementInfo(e.getThat()) != null) {
					slice.setSliceElementInfo(e.getNormalEdge(), SliceElementInfo.EDGE);
				}
			}
		}

		// mark all leaves as LEAF if they are not marked as
		// IN_SLICING_CRITERION
		for (JValue node : jValueSlice.leaves()) {
			v = node.toVertex();

			if (slice.getSliceElementInfo(v)
					!= SliceElementInfo.SLICING_CRITERION) {
				slice.setSliceElementInfo(v, SliceElementInfo.LEAF);
			}
			for (Edge e : v.incidences()) {
				if (slice.getSliceElementInfo(e.getThat()) != null) {
					slice.setSliceElementInfo(e.getNormalEdge(), SliceElementInfo.EDGE);
				}
			}
		}

		// mark the slicing criterion vertices as IN_SLICING_CRITERION
		for (JValue node : jValueSlice.getSlicingCriterionVertices()) {
			v = node.toVertex();
			slice.setSliceElementInfo(v, SliceElementInfo.SLICING_CRITERION);
			for (Edge e : v.incidences()) {
				if (slice.getSliceElementInfo(e.getThat()) != null) {
					slice.setSliceElementInfo(e.getNormalEdge(), SliceElementInfo.EDGE);
				}
			}
		}
		
		// mark all "relationlike" vertices whose "source" and "target" vertex are marked, as inner nodes of the slice
		Graph graph = slice.getGraph();
		for (Class<? extends Vertex> cls : relationlikeVertexTypes) {
			for (Vertex y : graph.vertices(cls)) {
				boolean sourceAndTargetMarked = true;
				for (Edge e : y.incidences()) {
					if ((e.getThatRole().equals("source")) || (e.getThatRole().equals("target")))
						sourceAndTargetMarked &= slice.getSliceElementInfo(e.getThat()) != null;
				}
				if (sourceAndTargetMarked) {
					slice.setSliceElementInfo(y, SliceElementInfo.INNER);
					for (Edge e : y.incidences()) {
						if (slice.getSliceElementInfo(e.getThat()) != null) {
							slice.setSliceElementInfo(e.getNormalEdge(), SliceElementInfo.EDGE);
						}
					}
				}
			}			
		}
		
		
	}

	/**
	 * Returns a <code>Map<String, JValue></code> with one contained mapping
	 * ("sliCritVertices", vertices) for the given
	 * <code>Set&lt;Vertex&gt; vertices</code>.
	 * 
	 * @param v
	 *            the <code>Set&lt;Vertex&gt;</code> for which to create a
	 *            mapping
	 * @return a <code>Map&lt;String, JValue&gt;</code> with one contained
	 *         mapping ("sliCritVertices", vertices) for the given
	 *         <code>Set&lt;Vertex&gt; vertices</code>
	 */
	private static Map<String, JValue> createBinding(Set<Vertex> vertices) {
		Map<String, JValue> sliCritVerticesMap = new HashMap<String, JValue>();
		JValueSet sliCritVertices = new JValueSet();
		
		for (Vertex v : vertices) {
			sliCritVertices.add(new JValue(v));
		}
		sliCritVerticesMap.put("sliCritVertices", sliCritVertices);

		return sliCritVerticesMap;
	}

	/**
	 * Checks whether all vertices in the given <code>Set</code> are contained
	 * in the same graph.
	 * 
	 * @param vertices
	 *            the <code>Set</code> whose vertices shall be checked for
	 *            containment in the same graph
	 * @return true if all vertices in the given <code>Set</code> are
	 *         contained in the same graph, else false
	 */
	private static boolean checkForSameGraph(Set<Vertex> vertices) {
		Graph graph = vertices.iterator().next().getGraph();

		for (Vertex v : vertices) {
			if (v.getGraph() != graph) {
				return false;
			}
		}

		return true;
	}
	
}
