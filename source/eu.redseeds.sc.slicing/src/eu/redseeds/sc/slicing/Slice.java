package eu.redseeds.sc.slicing;

import de.uni_koblenz.jgralab.AttributedElement;
import de.uni_koblenz.jgralab.Edge;
import de.uni_koblenz.jgralab.Graph;
import de.uni_koblenz.jgralab.Vertex;

public interface Slice {
	
	/**
	 * Returns the <code>Graph</code> this slice is a subgraph of.
	 * 
	 * @return the <code>Graph</code> this slice is a subgraph of
	 */
	public Graph getGraph();
	
	/**
	 * Returns all <code>AttributedElement</code>s contained in the slice.
	 * 
	 * @return all <code>AttributedElement</code>s contained in the slice
	 */
	public Iterable<AttributedElement> getElements();

	/**
	 * Returns all vertices contained in the slice.
	 * 
	 * @return all vertices contained in the slice
	 */
	public Iterable<Vertex> getVertices();
	
	/**
	 * Returns all vertices which are part of the slice's slicing criterion.
	 * 
	 * @return all vertices which are part of the slice's slicing criterion
	 */
	public Iterable<Vertex> getSlicingCriterion();
	
	/**
	 * Returns all inner vertices of the slice.
	 * 
	 * @return all inner vertices of the slice
	 */
	public Iterable<Vertex> getInner();
	
	/**
	 * Returns all leaf vertices of the slice.
	 * 
	 * @return all leaf vertices of the slice
	 */
	public Iterable<Vertex> getLeaves();
	
	/**
	 * Returns all edges of the slice.
	 * 
	 * @return all edges of the slice
	 */
	public Iterable<Edge> getEdges();
	
	/**
	 * Returns the <code>SliceElementInfo</code> for the given
	 * <code>AttributedElement</code>. Returns <code>null</code> if there
	 * is no such info for the element.
	 * 
	 * @param elem
	 *            the <code>AttributedElement</code> whose
	 *            <code>SliceElementInfo</code> shall be returned
	 * @return the <code>SliceElementInfo</code> for the given
	 *         <code>AttributedElement</code>. <code>null</code> if there
	 *         is no such info.
	 */
	public SliceElementInfo getSliceElementInfo(AttributedElement elem);
	
	public enum SliceElementInfo {
		SLICING_CRITERION,
		INNER,
		LEAF,
		EDGE
	}
}
