package eu.redseeds.sc.query.ui.actions;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.Status;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.PlatformUI;

import eu.redseeds.common.SCProjectHelper;
import eu.redseeds.sc.current.repository.SCProjectContainer;
import eu.redseeds.sc.merging.Activator;
import eu.redseeds.sc.merging.BuildMergeStackJob;
import eu.redseeds.sc.merging.MergeJob;
import eu.redseeds.sc.merging.PreMergeJob;
import eu.redseeds.sc.merging.MergeStack;
import eu.redseeds.sc.merging.SCMergeManager;
import eu.redseeds.sc.merging.conflicts.MergeConflictObject;
import eu.redseeds.sc.merging.dialogs.MergeConflictsDialog;
import eu.redseeds.sc.merging.dialogs.MergeMessageBoxFactory;
import eu.redseeds.scl.model.sclkernel.ClipboardDTO;
import eu.redseeds.scl.model.sclkernel.SoftwareCaseDTO;

public class MergeSliceAction implements IWorkbenchWindowActionDelegate, IViewActionDelegate {
	
	public MergeSliceAction() {
		// TODO Auto-generated constructor stub
	}

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
		ITreeSelection select = (ITreeSelection) SCProjectHelper.getSelection();
		TreePath[] tr = select.getPaths();
		
		final ClipboardDTO clipboard = (ClipboardDTO)tr[0].getLastSegment();
		
		if(!SCMergeManager.validateClipboardForMerging(clipboard)) {
			MergeMessageBoxFactory.clipboardNotValidMB().open();
			return;
		}
		
		final SoftwareCaseDTO sc 
			= SCProjectContainer.instance().getSCProject(clipboard).getMainCase();
		
		if(!SCMergeManager.validateTargetSCForMerging(sc)) {
			MergeMessageBoxFactory.scNotValidMB().open();
			return;
		}
		
		final IWorkbench workbench = PlatformUI.getWorkbench(); 
		Display display = Display.getCurrent();
		if(display == null) {
			 display = Display.getDefault();
		}
		
		final BuildMergeStackJob bmsj = new BuildMergeStackJob(clipboard);
		
		display.syncExec( 
				new Runnable() { 
			public void run() { 
				IWorkbenchWindow window 
					= workbench.getActiveWorkbenchWindow(); 
				if (window != null) { 
					Shell shell = window.getShell();
					boolean cancelable = true;
					try {
			            ProgressMonitorDialog dialog = new ProgressMonitorDialog(shell);
			            // eclipse throws null pointer before IJob.run if is forked
			            dialog.run(true, cancelable, bmsj);
			        } catch (InvocationTargetException e) {
			            Activator.log("Error during gathering elements for merge: "+e.getMessage(), Status.ERROR);
			        } catch (InterruptedException e) {
			            Activator.log("Error during gathering elements for merge: "+e.getMessage(), Status.ERROR);
			        } 
				}
			}
		});
		
		final MergeStack ms = bmsj.getMergeStack();
		
		final List<MergeConflictObject> conflicts = new ArrayList<MergeConflictObject>();

		display.syncExec( 
				new Runnable() { 
			public void run() { 
				IWorkbenchWindow window 
					= workbench.getActiveWorkbenchWindow(); 
				if (window != null) { 
					Shell shell = window.getShell();
					boolean cancelable = false;
					PreMergeJob preMergeJob = new PreMergeJob(ms, sc);
					try {
			            ProgressMonitorDialog dialog = new ProgressMonitorDialog(shell);
			            // eclipse throws null pointer before IJob.run if is forked
			            dialog.run(false, cancelable, preMergeJob);
			        } catch (InvocationTargetException e) {
			            Activator.log("Error during merge analysis: "+e.getMessage(), Status.ERROR);
			        } catch (InterruptedException e) {
			            Activator.log("Error during merge analysis: "+e.getMessage(), Status.ERROR);
			        } 
			        conflicts.addAll(preMergeJob.getFoundConflicts());
				}
			}
		});
		
		if(conflicts.size() > 0) {//if there are any conflicts
			//refine the list
			SCMergeManager.refineMergeConflictsList(conflicts);
			
			//show dialog
			MergeConflictsDialog confdial = new MergeConflictsDialog(SCProjectHelper.getShell());
			confdial.setInput(conflicts.toArray());
			int dialogResult = confdial.open();
			if(dialogResult != Window.OK) {
				return;
			}
		}
		
		display.syncExec( 
				new Runnable() { 
			public void run() { 
				IWorkbenchWindow window 
					= workbench.getActiveWorkbenchWindow(); 
				if (window != null) { 
					Shell shell = window.getShell();
					boolean cancelable = true;
					MergeJob mergeJob = new MergeJob(conflicts, clipboard, sc);
					try {
			            ProgressMonitorDialog dialog = new ProgressMonitorDialog(shell);
			            // eclipse throws null pointer before IJob.run if is forked
			            dialog.run(true, cancelable, mergeJob);
			        } catch (InvocationTargetException e) {
			            Activator.log("Error during merging elements: "+e.getMessage(), Status.ERROR);
			        } catch (InterruptedException e) {
			            Activator.log("Error during merging elements: "+e.getMessage(), Status.ERROR);
			        } 
				}
			}
		});
		
		SCProjectHelper.refreshSCNavigator();
		SCProjectContainer.instance().getSCProject(clipboard).save();

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
