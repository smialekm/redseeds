package notiondiagram.diagram.providers;

import notiondiagram.diagram.part.NotionDiagramDiagramEditorPlugin;

/**
 * @generated
 */
public class ElementInitializers {

	protected ElementInitializers() {
		// use #getInstance to access cached instance
	}

	/**
	 * @generated
	 */
	public static ElementInitializers getInstance() {
		ElementInitializers cached = NotionDiagramDiagramEditorPlugin
				.getInstance().getElementInitializers();
		if (cached == null) {
			NotionDiagramDiagramEditorPlugin.getInstance()
					.setElementInitializers(cached = new ElementInitializers());
		}
		return cached;
	}
}
