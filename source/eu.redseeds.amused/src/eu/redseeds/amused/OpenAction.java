package eu.redseeds.amused;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;


public class OpenAction implements IWorkbenchWindowActionDelegate{

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(IWorkbenchWindow arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run(IAction arg0) {
		MessageDialog.openInformation(null, "AMUSEd", "AMUSEd UIStoryBoard Editor may be obtained from Fraunhofer.");
	}

	
	
	@Override
	public void selectionChanged(IAction arg0, ISelection arg1) {
		// TODO Auto-generated method stub
		
	}
	
}
