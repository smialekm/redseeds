package eu.redseeds.sc.project.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.PlatformUI;

import eu.redseeds.sc.project.wizards.NewSCProjectWizard;

public class CreateNewProjectAction implements IWorkbenchWindowActionDelegate {

	@Override
	public void run(IAction action) {
		NewSCProjectWizard wizard = new NewSCProjectWizard();
		wizard.init(PlatformUI.getWorkbench(), null);
		WizardDialog dialog = new WizardDialog(null, wizard);
		dialog.open();

	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(IWorkbenchWindow window) {
		// TODO Auto-generated method stub
		
	}

}
