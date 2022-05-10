package eu.redseeds.sc.current.ui.actions;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import eu.redseeds.common.SCProjectHelper;
import eu.redseeds.sc.current.ui.Activator;
import eu.redseeds.sc.current.ui.validation.ValidationJob;
import eu.redseeds.sc.current.ui.validation.ValidationMarkerFactory;

public class ValidateAction implements IViewActionDelegate {
	
	private IViewPart navigator;
	
	@Override
	public void run(IAction action) {
		final IStructuredSelection select = (IStructuredSelection) navigator
			.getViewSite().getSelectionProvider().getSelection();
		
		final IProject proj = SCProjectHelper.getIProject(
				(IStructuredSelection)SCProjectHelper.getSelection());
		
		
		final IWorkbench workbench = PlatformUI.getWorkbench(); 
		Display display = Display.getCurrent();
		if(display == null) {
			 display = Display.getDefault();
		}
//		ValidationJob validationJob = new ValidationJob();
//		display.syncExec(validationJob);
//		if(validationJob.getResult() != null) {
//    	MarkerViewUtil.showMarker(
//				PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage(), 
//				validationJob.getResult(), false);
//		}
		
		//TODO: check why eclipse throws null pointer before IJob.run
		display.syncExec( 
				new Runnable() { 
			public void run() { 
				IWorkbenchWindow window 
					= workbench.getActiveWorkbenchWindow(); 
				if (window != null) { 
					Shell shell = window.getShell();
					boolean cancelable = true;
					ValidationJob validationJob = new ValidationJob(getSelectedObject(select).toArray(), proj);
					try {
			            ProgressMonitorDialog dialog = new ProgressMonitorDialog(shell);
			            // eclipse throws null pointer before IJob.run if is forked
			            dialog.run(true, cancelable, validationJob);
			        } catch (InvocationTargetException e) {
			            Activator.log("Error during validating elements: "+e.getMessage(), Status.ERROR);
			        } catch (InterruptedException e) {
			            Activator.log("Error during validating elements: "+e.getMessage(), Status.ERROR);
			        } 
//			        finally {
//			        	if(validationJob.getResult() != null) {
//			        		for(int i = 0; i < validationJob.getResult().size(); i++) {
//			        			MarkerViewUtil.showMarker(
//			            			PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage(), 
//			        				validationJob.getResult().get(i), false);
//			        		}
//			            }
//			        }
				}
			}
		});
		
		//TODO - opening problem view if needed
		try {
			int markers = ValidationMarkerFactory.getNumberOfMarkers(proj);
			if(markers < -1) {
				return;
			}
			else if(markers > 0) {
				PlatformUI.getWorkbench().getActiveWorkbenchWindow()
					.getActivePage().showView(IPageLayout.ID_PROBLEM_VIEW);
			}
		} catch (PartInitException e) {
			Activator.log("Problem occurred during opening of Problem View", Status.WARNING);
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
	
	protected List<Object> getSelectedObject(IStructuredSelection select) {
		ITreeSelection treeSelection = (ITreeSelection) select;

		List<Object> result = new ArrayList<Object>();
		TreePath[] tr = treeSelection.getPaths();

		for (int i = 0; i < tr.length; i++) {
			result.add(tr[i].getLastSegment());
		}
		return result;
	}

}
