package eu.redseeds.sc.editor.rsl.actions;

import org.eclipse.core.runtime.Status;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.PartInitException;

import eu.redseeds.sc.current.repository.Activator;
import eu.redseeds.sc.editor.rsl.editors.RequirementEditor;
import eu.redseeds.sc.editor.rsl.editors.RequirementEditorInput;



public class RequirementEditorOpenAction implements IWorkbenchWindowActionDelegate {

	private IWorkbenchWindow window;
	
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(IWorkbenchWindow window) {
		// TODO Auto-generated method stub
		this.window = window;
	}

	@Override
	public void run(IAction action) {
		RequirementEditorInput input = new RequirementEditorInput();
		try {
			window.getActivePage().openEditor(input, RequirementEditor.EDITOR_ID,false);
		} catch (PartInitException e) {
			Activator.log("Error during running editor: "+e.getMessage(), Status.ERROR);
		}
		
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		// TODO Auto-generated method stub
		
	}

}
