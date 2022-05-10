package eu.redseeds.sc.current.ui.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.PlatformUI;

import eu.redseeds.common.SCProjectHelper;
import eu.redseeds.sc.current.ui.wizards.SCLElementWizard;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementsPackageDTO;

public class AddRequirementAction implements IWorkbenchWindowActionDelegate,
		IViewActionDelegate {

		private IViewPart navigator;
		
		@Override
		public void dispose() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void init(IWorkbenchWindow window) {
		}

		@Override
		public void run(IAction action) {				
			// Receiving selected elements and their paths from Project Explorer
			IStructuredSelection select = null;
			if(navigator != null) {
				select = (IStructuredSelection)navigator.getViewSite().getSelectionProvider().getSelection();
			}
			else { //invoked as a command
				select = (ITreeSelection)SCProjectHelper.getSCNavigator().getViewSite().getSelectionProvider().getSelection();
				//check selection
				ITreeSelection treeSelection = (ITreeSelection) select;
				TreePath[] tr = treeSelection.getPaths();
				if(tr.length > 0) {
					if(tr[0] != null) {
						Object parent = tr[0].getLastSegment();
						if (!(parent instanceof RequirementsPackageDTO)) {
							showWarning();
							return;
						}
					}
					else {
						showWarning();
						return;
					}
				} else {
					showWarning();
					return;
				}
				
			}

			SCLElementWizard wizard = new SCLElementWizard(RequirementDTO.class);
			wizard.init(PlatformUI.getWorkbench(), select);
			WizardDialog dialog = new WizardDialog(null, wizard);
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
		
		private void showWarning() {
			MessageBox warnMB = new MessageBox(SCProjectHelper.getShell(), 
					SWT.ICON_WARNING | SWT.OK );

			warnMB.setMessage("You can create a Requirement only as a Requirements Package child");
			warnMB.setText("Problem");
			warnMB.open();
		}

	}
