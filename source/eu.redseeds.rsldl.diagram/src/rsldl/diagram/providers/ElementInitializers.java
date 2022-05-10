package rsldl.diagram.providers;

import rsldl.diagram.part.RsldlDiagramEditorPlugin;

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
		ElementInitializers cached = RsldlDiagramEditorPlugin.getInstance()
				.getElementInitializers();
		if (cached == null) {
			RsldlDiagramEditorPlugin.getInstance().setElementInitializers(
					cached = new ElementInitializers());
		}
		return cached;
	}
}
