package eu.redseeds.sc.current.ui.actions;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

import eu.redseeds.common.SCProjectHelper;
import eu.redseeds.java.parser.initial.InitialParser;
import eu.redseeds.sc.current.repository.SCProject;
import eu.redseeds.sc.current.repository.SCProjectContainer;
import eu.redseeds.sc.current.ui.Activator;
import eu.redseeds.scl.model.sclkernel.ArchitecturalModelDTO;
import eu.redseeds.scl.model.sclkernel.DetailedDesignModelDTO;

public class DeleteGeneratedAction implements IWorkbenchWindowActionDelegate,
IViewActionDelegate {
	
	private SCProject currSCProject = null;

	@Override
	public void run(IAction action) {
		ITreeSelection currSelection = (ITreeSelection) SCProjectHelper.getSelection();
		currSCProject = null;
		if (currSelection != null) {
			IProject project = SCProjectHelper.getIProject(currSelection);
			currSCProject = SCProjectContainer.instance().getSCProject(project);
		}
		if (currSCProject == null) {
			Activator.log("Can't locate current SCProject...", Status.ERROR);
			MessageBox confirmMB = new MessageBox(SCProjectHelper.getShell(), SWT.ICON_ERROR);
			confirmMB.setMessage("Can't find selected project!");
			confirmMB.open();
			return;
		}
		if(currSelection.getFirstElement() instanceof DetailedDesignModelDTO || currSelection.getFirstElement() instanceof ArchitecturalModelDTO){
			new InitialParser().deleteGeneratedModel();
			
			currSCProject.save();
			SCProjectHelper.refreshSCNavigator();
		}
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
