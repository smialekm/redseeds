/**
 *
 */
package eu.redseeds.sc.query.ui.view.composite.scSimilarityResult;

import eu.redseeds.scl.model.rsl.IHierarchyAware;

class LeafHierarchyAware {
	private final IElementMatch elementMatch;
	private final IHierarchyAware hierarchyAware;

	LeafHierarchyAware(IElementMatch elementMatch) {
		super();
		this.elementMatch = elementMatch;
		this.hierarchyAware=elementMatch.getPastElement();
	}

	public IHierarchyAware getHierarchyAware() {
		return hierarchyAware;
	}

	public IElementMatch getElementMatch() {
		return elementMatch;
	}

}