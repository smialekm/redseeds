package usecasediagram.diagram.navigator;

import org.eclipse.jface.viewers.ViewerSorter;

import usecasediagram.diagram.part.UseCaseDiagramVisualIDRegistry;

/**
 * @generated
 */
public class UseCaseDiagramNavigatorSorter extends ViewerSorter {

	/**
	 * @generated
	 */
	private static final int GROUP_CATEGORY = 4004;

	/**
	 * @generated
	 */
	public int category(Object element) {
		if (element instanceof UseCaseDiagramNavigatorItem) {
			UseCaseDiagramNavigatorItem item = (UseCaseDiagramNavigatorItem) element;
			return UseCaseDiagramVisualIDRegistry.getVisualID(item.getView());
		}
		return GROUP_CATEGORY;
	}

}
