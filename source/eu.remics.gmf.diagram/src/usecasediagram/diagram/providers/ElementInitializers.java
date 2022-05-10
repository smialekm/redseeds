package usecasediagram.diagram.providers;

import usecasediagram.diagram.part.UseCaseDiagramDiagramEditorPlugin;

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
		ElementInitializers cached = UseCaseDiagramDiagramEditorPlugin
				.getInstance().getElementInitializers();
		if (cached == null) {
			UseCaseDiagramDiagramEditorPlugin.getInstance()
					.setElementInitializers(cached = new ElementInitializers());
		}
		return cached;
	}
}
