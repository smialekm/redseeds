package rsldl.diagram.navigator;

import org.eclipse.jface.viewers.ViewerSorter;

import rsldl.diagram.part.RsldlVisualIDRegistry;

/**
 * @generated
 */
public class RsldlNavigatorSorter extends ViewerSorter {

	/**
	 * @generated
	 */
	private static final int GROUP_CATEGORY = 7004;

	/**
	 * @generated
	 */
	public int category(Object element) {
		if (element instanceof RsldlNavigatorItem) {
			RsldlNavigatorItem item = (RsldlNavigatorItem) element;
			return RsldlVisualIDRegistry.getVisualID(item.getView());
		}
		return GROUP_CATEGORY;
	}

}
