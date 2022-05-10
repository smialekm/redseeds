package eu.redseeds.engine.navigator.actions;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;

import eu.redseeds.engine.Activator;

public class ClearValidationProblemsAction implements IViewActionDelegate {

	@Override
	public void init(IViewPart view) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run(IAction action) {
		// delete markers
		IResource input = ResourcesPlugin.getWorkspace().getRoot();
		try {
			IMarker[] newMarkers = input.findMarkers(IMarker.PROBLEM, true,
					IResource.DEPTH_INFINITE);
			for(IMarker marker : newMarkers) {
				marker.delete();
			}
		} catch (CoreException e) {
			Activator.log("Problem during clearing validation problem view", IStatus.ERROR);
		}

	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		// TODO Auto-generated method stub
	}
}
