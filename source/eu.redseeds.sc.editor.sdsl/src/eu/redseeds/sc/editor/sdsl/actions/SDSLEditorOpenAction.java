package eu.redseeds.sc.editor.sdsl.actions;

import org.eclipse.core.runtime.Status;
import org.eclipse.debug.ui.sourcelookup.CommonSourceNotFoundEditorInput;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.PartInitException;

import eu.redseeds.sc.editor.sdsl.Activator;
import eu.redseeds.sc.editor.sdsl.editors.SDSLEditor;

public class SDSLEditorOpenAction implements IWorkbenchWindowActionDelegate {
	
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
			window.getActivePage().openEditor(new CommonSourceNotFoundEditorInput(null), SDSLEditor.EDITOR_ID,true);
		} catch (PartInitException e) {
			Activator.log("Problem occured during SDSL Editor opening: "+ e.getMessage(), Status.ERROR);
			MessageDialog.openInformation(
					window.getShell(),
					"SDSL Editor",
					e.toString());
		}
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		// TODO Auto-generated method stub

	}

}
