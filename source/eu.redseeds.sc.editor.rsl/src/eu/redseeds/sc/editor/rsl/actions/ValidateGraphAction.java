package eu.redseeds.sc.editor.rsl.actions;

import java.io.File;
import java.net.URL;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.browser.IWebBrowser;
import org.eclipse.ui.browser.IWorkbenchBrowserSupport;

import de.uni_koblenz.jgralab.graphvalidator.GraphValidator;
import eu.redseeds.common.SCProjectHelper;
import eu.redseeds.sc.current.repository.Activator;
import eu.redseeds.sc.current.repository.SCProject;
import eu.redseeds.sc.current.repository.SCProjectContainer;

public class ValidateGraphAction implements IWorkbenchWindowActionDelegate {

	private IWorkbenchPage activePage;

	@Override
	public void run(IAction action) {
		IProject project = null;
		ISelection selection = activePage.getSelection();
		if(selection == null){
			selection = SCProjectHelper.getSelection();
		}
		if(selection.isEmpty()){
			project = SCProjectHelper.getActiveProject();
			if(project == null){
				MessageDialog.openError(new Shell(), "Project not selected", "Project is not selected. Please choose one.");
				return;
			}
		}
		else{
			Object selectedElement = ((IStructuredSelection) selection)
			.getFirstElement();
			if(selection instanceof ITreeSelection){
				ITreeSelection treeSelection = (ITreeSelection) selection;
				TreePath[] paths = treeSelection.getPathsFor(selectedElement);
				if (paths.length > 0) {
					if (paths[0].getFirstSegment() instanceof IProject) {
						project = (IProject) paths[0].getFirstSegment();
					} else {
						return;
					}
				} else {
					return;
				}
			}
		}

		SCProject currentProject = SCProjectContainer.instance().getSCProject(
				project);
		GraphValidator gv = new GraphValidator(currentProject.getSCLGraph());
		File reportFile = new File("graphvalidation.html");
		try {
			gv.createValidationReport(reportFile.getCanonicalPath());
			IWorkbenchBrowserSupport support = PlatformUI.getWorkbench()
					.getBrowserSupport();
			IWebBrowser browser = support
					.createBrowser("Graph Validation Report");
			browser.openURL(new URL("file://" + reportFile.getAbsolutePath()));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Activator.log(e.getMessage(), IStatus.ERROR);
		}
	}

	@Override
	public void dispose() {

	}

	@Override
	public void init(IWorkbenchWindow window) {
		activePage = window.getActivePage();
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		// TODO Auto-generated method stub

	}
}
