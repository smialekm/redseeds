package eu.redseeds.sc.query.ui.actions;

import org.eclipse.core.runtime.Status;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.PlatformUI;

import eu.remics.alp.ui.ImportALPWizard;
import eu.redseeds.common.SCProjectHelper;
import eu.redseeds.sc.query.ui.Activator;

public class ImportALPAction implements IWorkbenchWindowActionDelegate, IViewActionDelegate {
	
	private int dialogWidth = 640;
	private int dialogHeight = 460;

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
		Activator.log("Importing ALP", Status.OK);
		ITreeSelection select = (ITreeSelection) SCProjectHelper.getSelection();
		
		ImportALPWizard wiz = new ImportALPWizard();
		
		WizardDialog dialog = new WizardDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), wiz){
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
		// TODO Auto-generated method stub
	}

}
