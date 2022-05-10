package eu.redseeds.sc.editor.rsl.actions;

import org.eclipse.core.runtime.Status;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.PartInitException;
import org.eclipse.debug.ui.sourcelookup.CommonSourceNotFoundEditorInput;

import eu.redseeds.sc.current.repository.Activator;
import eu.redseeds.sc.editor.rsl.editors.TermEditor;

public class TermEditorOpenAction implements IWorkbenchWindowActionDelegate {

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
//		TermEditorInput input = new TermEditorInput();
//		try {
//			window.getActivePage().openEditor(input, TermEditor.EDITOR_ID, false);
//		} catch (PartInitException e) {
//			Activator.log("Error during running editor: " + e.getMessage(), Status.ERROR);
//		}
		
		try {
			window.getActivePage().openEditor(new CommonSourceNotFoundEditorInput(null), TermEditor.EDITOR_ID,true);
		} catch (PartInitException e) {
			Activator.log("Problem occured during Term Editor opening: " + e.getMessage(), Status.ERROR);
			MessageDialog.openInformation(
					window.getShell(),
					"Term Editor",
					e.toString());
		}
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		// TODO Auto-generated method stub

	}

}
