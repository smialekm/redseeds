package notiondiagram.diagram.navigator;

import notiondiagram.diagram.part.NotionDiagramVisualIDRegistry;

import org.eclipse.jface.viewers.ViewerSorter;

/**
 * @generated
 */
public class NotionDiagramNavigatorSorter extends ViewerSorter {

	/**
	 * @generated
	 */
	private static final int GROUP_CATEGORY = 7003;

	/**
	 * @generated
	 */
	public int category(Object element) {
		if (element instanceof NotionDiagramNavigatorItem) {
			NotionDiagramNavigatorItem item = (NotionDiagramNavigatorItem) element;
			return NotionDiagramVisualIDRegistry.getVisualID(item.getView());
		}
		return GROUP_CATEGORY;
	}

}
