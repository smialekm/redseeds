package eu.redseeds.sc.current.ui.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

import eu.remics.recovery.manager.applogic.AMain;

public class FindSimilarScenariosAction implements IWorkbenchWindowActionDelegate,
IViewActionDelegate{

	public FindSimilarScenariosAction() {
	}

	@Override
	public void run(IAction action) {
		AMain.cFindsimilarScenarios.ClicksFindSimilarScenarios();
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
	}

	@Override
	public void init(IViewPart view) {
	}

	@Override
	public void dispose() {
	}

	@Override
	public void init(IWorkbenchWindow window) {
	}

}
