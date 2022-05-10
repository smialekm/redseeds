package eu.redseeds.sc.current.ui.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.PlatformUI;

import eu.redseeds.sc.current.ui.wizards.RenameWizard;

public class RenameAction implements IWorkbenchWindowActionDelegate, IViewActionDelegate {
	
	private IViewPart navigator;
	
	private int dialogWidth = 640;
	private int dialogHeight = 360;

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(IWorkbenchWindow window) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run(IAction action) {
		IStructuredSelection select 
			= (IStructuredSelection)navigator.getViewSite().getSelectionProvider().
				getSelection();
		
		RenameWizard wiz = new RenameWizard();
			
		WizardDialog dialog = new WizardDialog(null, wiz){
			@Override
	        protected void configureShell(Shell newShell) {
				super.configureShell(newShell);
				newShell.setSize(dialogWidth, dialogHeight);
				Shell parentShell = Display.getCurrent().getActiveShell();
				newShell.setLocation( 
						(parentShell.getSize().x - dialogWidth) / 2,
						(parentShell.getSize().y - dialogHeight) / 2 
				);
			}	
		};
		
		wiz.init(PlatformUI.getWorkbench(), select);
		
		dialog.open();
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(IViewPart view) {
		navigator = view;
	}

}
