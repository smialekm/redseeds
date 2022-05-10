package eu.redseeds.sc.current.repository;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IStartup;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

import eu.redseeds.common.jobs.IJob;
import eu.redseeds.sc.terminology.model.RemoteJGWNL;

public class Startup implements IStartup {

	public static String MODEL_NATURE="eu.redseeds.model.nature";
	
	public void earlyStartup() {
		Activator.log("Startup", Status.INFO);
		
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		
		// add SCProjectListener to Workspace
		workspace.addResourceChangeListener(
				new SCProjectListener(),
				IResourceChangeEvent.POST_CHANGE | IResourceChangeEvent.PRE_CLOSE
				| IResourceChangeEvent.PRE_DELETE);
		
		// create SCProjects for open Eclipse projects
		final IWorkbench workbench = PlatformUI.getWorkbench(); 
		workbench.getDisplay().asyncExec(new Runnable() { 
			public void run() { 
				IWorkbenchWindow window 
					= workbench.getActiveWorkbenchWindow(); 
				if (window != null) { 
					Shell shell = window.getShell();
					boolean cancelable = false;
					IJob action = new StartUpJob();
					try {
			            ProgressMonitorDialog dialog = new ProgressMonitorDialog(shell);
			            dialog.run(true, cancelable, action);
			        } catch (InvocationTargetException e) {
			            Activator.log("Error during reading projects in workspace: "+e.getMessage(), Status.ERROR);
			        } catch (InterruptedException e) {
			            Activator.log("Error during reading projects in workspace "+e.getMessage(), Status.ERROR);
			        }
			        //display not connected window
			        if(!RemoteJGWNL.getInstance().isConnected()) {
			        	notConnectedMB(shell);
			        }
			        //show projects with not connected terms
			        List<String> projects = new ArrayList<String>();
			        IWorkspace workspace = ResourcesPlugin.getWorkspace();
					for (IProject project : workspace.getRoot().getProjects()) {
						try {
							if (project.isOpen() && project.isNatureEnabled("eu.redseeds.sc.project.projectnature")) {
								if(!SCProjectContainer.instance()
										.getSCProject(project).isTermsConnected())
									projects.add(project.getName());
							}
						} catch (CoreException e) {
							Activator.log("Error during reading projects in workspace "+e.getMessage(), Status.ERROR);
						}
					}
					if(projects.size() > 0) {
						termsNotConnectedMB(shell, projects);
					}
				}
			}
		});
	}
	
	/**
	 * displays a warning 'connection to terminology failed' 
	 * @param shell
	 */
	private void notConnectedMB(Shell shell) {
		MessageBox warnMB = new MessageBox(shell, 
				SWT.ICON_WARNING | SWT.OK );

		warnMB.setMessage("Connecting to the Terminology server failed. Some of application funtions may be unavailable. \nPlease use the \"ReDSeeDS -> Configure Terminology server connection\" menu option and specify valid terminology server.");
		warnMB.setText("Terminology connection problem");
		warnMB.open();
	}
	
	/**
	 * displays a warning 'terms not connected to terminology' 
	 * @param shell
	 */
	private void termsNotConnectedMB(Shell shell, List<String> projects) {
		MessageBox warnMB = new MessageBox(shell, 
				SWT.ICON_WARNING | SWT.OK );
		
		String projectsString = "\n";
		for(String prjName : projects) {
			projectsString += " - " + prjName +"\n";
		}

		warnMB.setMessage("Some terms in the following projects are not connected to the terminology:" + projectsString +"\n Please refer to the error log for more details.");
		warnMB.setText("Terminology connection problem");
		warnMB.open();
	}
	
}
