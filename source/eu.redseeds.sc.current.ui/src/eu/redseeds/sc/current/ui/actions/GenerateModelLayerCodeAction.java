package eu.redseeds.sc.current.ui.actions;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

import eu.redseeds.common.SCProjectHelper;
import eu.redseeds.java.transformation.engine.impl.TransformationExecutionImpl;
import eu.redseeds.java.transformation.engine.interfaces.ITransformationExecution;
import eu.redseeds.modelio.converter.ModelioRunner;
import eu.redseeds.sc.current.repository.SCProject;
import eu.redseeds.sc.current.repository.SCProjectContainer;

public class GenerateModelLayerCodeAction implements IWorkbenchWindowActionDelegate,
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
		
		ITransformationExecution transfExec = new TransformationExecutionImpl();
		transfExec.execute();
		
		String scProjLocation = currSCProject.getEclipseProject().getLocation().toString();
		ModelioRunner.openModelio(scProjLocation);
		
		currSCProject.save();
		SCProjectHelper.refreshSCNavigator();
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
