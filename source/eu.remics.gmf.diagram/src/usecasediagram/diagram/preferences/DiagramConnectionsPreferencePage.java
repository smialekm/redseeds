package usecasediagram.diagram.preferences;

import org.eclipse.gmf.runtime.diagram.ui.preferences.ConnectionsPreferencePage;

import usecasediagram.diagram.part.UseCaseDiagramDiagramEditorPlugin;

/**
 * @generated
 */
public class DiagramConnectionsPreferencePage extends ConnectionsPreferencePage {

	/**
	 * @generated
	 */
	public DiagramConnectionsPreferencePage() {
		setPreferenceStore(UseCaseDiagramDiagramEditorPlugin.getInstance()
				.getPreferenceStore());
	}
}
