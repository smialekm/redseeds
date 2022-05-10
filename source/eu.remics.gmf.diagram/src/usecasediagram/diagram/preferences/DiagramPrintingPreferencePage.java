package usecasediagram.diagram.preferences;

import org.eclipse.gmf.runtime.diagram.ui.preferences.PrintingPreferencePage;

import usecasediagram.diagram.part.UseCaseDiagramDiagramEditorPlugin;

/**
 * @generated
 */
public class DiagramPrintingPreferencePage extends PrintingPreferencePage {

	/**
	 * @generated
	 */
	public DiagramPrintingPreferencePage() {
		setPreferenceStore(UseCaseDiagramDiagramEditorPlugin.getInstance()
				.getPreferenceStore());
	}
}
