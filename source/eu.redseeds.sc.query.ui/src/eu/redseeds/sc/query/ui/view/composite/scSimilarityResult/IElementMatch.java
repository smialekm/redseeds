/**
 * 
 */
package eu.redseeds.sc.query.ui.view.composite.scSimilarityResult;

import eu.redseeds.scl.model.rsl.IHierarchyAware;

public interface IElementMatch{
	public IHierarchyAware getCurrentElement();
	public IHierarchyAware getPastElement();
	public double getSimilarityValue();
}