package eu.redseeds.sc.query.ui.actions;

import org.eclipse.core.runtime.Status;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.jface.dialogs.MessageDialog;
import eu.redseeds.sc.current.repository.Activator;
import eu.redseeds.sc.query.ui.editors.SCSimilarityEditor;
import eu.redseeds.sc.query.ui.editors.SCSimilarityEditorInput;


public class SCSimilarityEditorOpenAction implements IWorkbenchWindowActionDelegate {
	
private IWorkbenchWindow window;
	
	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(IWorkbenchWindow window) {
		this.window = window;
	}

	@Override
	public void run(IAction action) {

		try {
			// Receiving references to opened editors in active page
			IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
			IEditorReference[] editorReferences = activePage.findEditors(null, SCSimilarityEditor.EDITOR_ID, 2);
			
			SCSimilarityEditorInput input = new SCSimilarityEditorInput();
			
			int editorReferneceCount = editorReferences.length;
			if (editorReferneceCount == 0) {
				activePage.openEditor(input, SCSimilarityEditor.EDITOR_ID, true);
			} else {
				activePage.activate(editorReferences[0].getEditor(true));

			}

		} catch (PartInitException e) {
			Activator.log("Problem occured during SC Query Manager opening: " + e.getMessage(), Status.ERROR);
			MessageDialog.openInformation(
					window.getShell(),
					"SC Query Manager",
					e.toString());
		}
		
		
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		// TODO Auto-generated method stub

	}

}
