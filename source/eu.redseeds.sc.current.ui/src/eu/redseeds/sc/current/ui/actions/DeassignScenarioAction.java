package eu.redseeds.sc.current.ui.actions;

import java.util.Iterator;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

import eu.redseeds.scl.model.rsl.rslrequirementrepresentations.descriptiverequirementrepresentations.ConstrainedLanguageScenarioDTO;
import eu.remics.recovery.manager.applogic.AMain;

public class DeassignScenarioAction  implements IWorkbenchWindowActionDelegate,
IViewActionDelegate{

		private IViewPart navigator;

		@Override
		public void dispose() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void run(IAction action) {
			IStructuredSelection select = (IStructuredSelection)navigator.getViewSite().getSelectionProvider().getSelection();
			for (Iterator<?> iterator = select.iterator(); iterator.hasNext();) {
				Object domain = (Object)iterator.next();
				AMain.cDeassignScenarioFromUseCase._ClicksDeassignScenarioOption((ConstrainedLanguageScenarioDTO)domain);
			}
		}

		@Override
		public void selectionChanged(IAction action, ISelection selection) {
		}

		@Override
		public void init(IViewPart view) {
			navigator = view;
		}

		@Override
		public void init(IWorkbenchWindow window) {
			// TODO Auto-generated method stub
			
		}
}