/**
 *
 */
package eu.redseeds.sc.query.ui.view.composite.scSimilarityResult;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;

import eu.redseeds.scl.model.rsl.IHierarchyAware;

abstract class OrderSorter extends ViewerSorter {

	private ViewerSorter previousViewerSorter;

	@Override
	public int category(Object element) {
		if(element instanceof IElementMatch){
			return  1;
		}
		if(element instanceof IHierarchyAware){
			return 2;
		}
		return 0;
	}

	@Override
	public int compare(Viewer viewer, Object e1, Object e2) {
		if (category(e1) == 1 && category(e2) == 1) {
			IElementMatch em1 = (IElementMatch) e1;
			IElementMatch em2 = (IElementMatch) e2;
			return compareRequirementMatch(viewer,em1, em2);
		}
		if (category(e1) == 2 && category(e2) == 2) {
			IHierarchyAware em1 = (IHierarchyAware) e1;
			IHierarchyAware em2 = (IHierarchyAware) e2;
			return compareRequirementMatch(viewer,em1, em2);
		}
		return 0;
	}

	private int delegateToPreviousViewerSorter(Viewer viewer,Object ob1,Object ob2){
		if(previousViewerSorter!=null){
			return previousViewerSorter.compare(viewer, ob1, ob2);
		}
		return 0;
	}

	protected int compareRequirementMatch(Viewer viewer,IElementMatch em1, IElementMatch em2){
		return delegateToPreviousViewerSorter(viewer,em1,em2);
	}
	protected int compareRequirementMatch(Viewer viewer,IHierarchyAware em1, IHierarchyAware em2){
		return delegateToPreviousViewerSorter(viewer,em1,em2);
	}

	public ViewerSorter getPreviousViewerSorter() {
		return previousViewerSorter;
	}

	public void setPreviousViewerSorter(ViewerSorter previousViewerSorter) {
		this.previousViewerSorter = previousViewerSorter;
	}
}