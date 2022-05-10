package eu.redseeds.sc.slicing.impl;

import java.util.HashSet;
import java.util.Set;

import de.uni_koblenz.jgralab.AttributedElement;
import de.uni_koblenz.jgralab.Edge;
import de.uni_koblenz.jgralab.Graph;
import de.uni_koblenz.jgralab.GraphMarker;
import de.uni_koblenz.jgralab.Vertex;
import eu.redseeds.sc.slicing.Slice;

public class SliceImpl implements Slice {

	private GraphMarker<SliceElementInfo> marker;
	
	SliceImpl(Graph graph) {
		marker = new GraphMarker<SliceElementInfo>(graph);
	}
	
	@Override
	public Iterable<Edge> getEdges() {
		return getEdgesWithMark(SliceElementInfo.EDGE);
	}

	@Override
	public Iterable<AttributedElement> getElements() {
		return marker.getMarkedElements();
	}

	@Override
	public Iterable<Vertex> getInner() {
		return getVerticesWithMark(SliceElementInfo.INNER);
	}

	@Override
	public Iterable<Vertex> getLeaves() {
		return getVerticesWithMark(SliceElementInfo.LEAF);
	}

	@Override
	public Graph getGraph() {
		return marker.getGraph();
	}

	@Override
	public Iterable<Vertex> getSlicingCriterion() {
		return getVerticesWithMark(SliceElementInfo.SLICING_CRITERION);
	}

	@Override
	public Iterable<Vertex> getVertices() {
		Set<Vertex> vertices = new HashSet<Vertex>();
		
		vertices.addAll(getVerticesWithMark(SliceElementInfo.SLICING_CRITERION));
		vertices.addAll(getVerticesWithMark(SliceElementInfo.INNER));
		vertices.addAll(getVerticesWithMark(SliceElementInfo.LEAF));
		
		return vertices;
	}
	
	@Override
	public SliceElementInfo getSliceElementInfo(AttributedElement elem) {
		return marker.getMark(elem);
	}
	
	void setSliceElementInfo(AttributedElement elem, SliceElementInfo info) {
		marker.mark(elem, info);
	}
	
	private Set<Vertex> getVerticesWithMark(SliceElementInfo mark) {
		Set<Vertex> vertices = new HashSet<Vertex>();
		
		for (AttributedElement elem : marker.getMarkedElements()) {
			if (marker.getMark(elem) == mark) {
				vertices.add((Vertex)elem);
			}
		}
		
		return vertices;
	}
	
	private Set<Edge> getEdgesWithMark(SliceElementInfo mark) {
		Set<Edge> edges = new HashSet<Edge>();
		
		for (AttributedElement elem : marker.getMarkedElements()) {
			if (marker.getMark(elem) == mark) {
				edges.add((Edge)elem);
			}
		}
		
		return edges;
	}
}
