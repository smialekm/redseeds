package eu.redset.navigator.actions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
import eu.redset.emf.model.tsl.ControlSentence;
import eu.redset.emf.model.tsl.TestCase;
import eu.redset.emf.model.tsl.TestScenario;
import eu.redset.emf.model.tsl.UseCaseTest;
import eu.redset.emf.model.tsl.UseCaseTestScenario;
import eu.redset.emf.model.tsl.UseCaseTestScenarioSentence;
import eu.redset.navigator.wizards.SCLElementWizard;
import eu.redset.navigator.wizards.UseCaseTestScenarioSelectionWizard;


public class AddTestCaseAction implements IWorkbenchWindowActionDelegate,
		IViewActionDelegate {

		private IViewPart navigator;
		private TestScenario ts;
		
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
						if (!(parent instanceof TestScenario)) {
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

			SCLElementWizard wizard = new SCLElementWizard(TestCase.class);
			wizard.init(PlatformUI.getWorkbench(), select);
			WizardDialog dialog = new WizardDialog(null, wizard);
			dialog.open();
				
			Map invokes = wizard.getSCLElementCreationOperation().getInvokesMap();
			ts = (TestScenario)wizard.getSCLElementCreationOperation().getParent();
			selectUseCaseTestScenarioRecursively(invokes);			
		}

		private void selectUseCaseTestScenarioRecursively(Map hashMap){
			Iterator entriesRecursive = hashMap.entrySet().iterator();
	        while (entriesRecursive.hasNext()) {
	            Map.Entry entryRecursive = (Map.Entry)entriesRecursive.next();
	            ControlSentence csRecursive = (ControlSentence)entryRecursive.getKey();
	            UseCaseTest uctRecursive = (UseCaseTest)entryRecursive.getValue();
	            UseCaseTestScenarioSelectionWizard uctsWizardRecursive = new UseCaseTestScenarioSelectionWizard(csRecursive, uctRecursive, ts);
	            //uctsWizard.init(PlatformUI.getWorkbench());
	            WizardDialog ucstDialog = new WizardDialog(null, uctsWizardRecursive);
	            ucstDialog.open();
	            if (uctsWizardRecursive.getUseCaseTestScenarioSelectionOperation() != null)
	            	selectUseCaseTestScenarioRecursively(uctsWizardRecursive.getUseCaseTestScenarioSelectionOperation().getInvokesMap());
	        }
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

			warnMB.setMessage("You can create a Test Case only as a Test Scenario child");
			warnMB.setText("Problem");
			warnMB.open();
		}

	}
