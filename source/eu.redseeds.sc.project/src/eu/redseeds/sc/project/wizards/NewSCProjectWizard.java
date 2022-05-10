package eu.redseeds.sc.project.wizards;

//import org.eclipse.jface.viewers.IStructuredSelection;
//import org.eclipse.jface.wizard.Wizard;
//import org.eclipse.ui.INewWizard;
//import org.eclipse.ui.IWorkbench;
//import org.eclipse.core.runtime.*;
//import org.eclipse.jface.operation.*;
//import java.lang.reflect.InvocationTargetException;
//
//import org.eclipse.jface.dialogs.IDialogSettings;
//import org.eclipse.jface.dialogs.MessageDialog;
//import org.eclipse.jface.viewers.ISelection;
//import org.eclipse.core.resources.*;
//import org.eclipse.core.runtime.CoreException;
//import java.io.*;
//import org.eclipse.ui.*;
//import org.eclipse.ui.ide.IDE;
//import org.eclipse.ui.wizards.newresource.BasicNewResourceWizard;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.dialogs.WizardNewProjectCreationPage;
import org.eclipse.ui.wizards.newresource.BasicNewResourceWizard;

import eu.redseeds.sc.project.Activator;

public class NewSCProjectWizard extends BasicNewResourceWizard
		implements IExecutableExtension {

	private WizardNewProjectCreationPage mainPage;
	// cache of newly-created project
	private IProject newProject;

	/**
	 * The config element which declares this wizard.
	 */
	private IConfigurationElement configElement;

	/**
	 * Creates a wizard for creating a new project resource in the workspace.
	 */
	public NewSCProjectWizard() {
		IDialogSettings workbenchSettings = Activator.getDefault()
				.getDialogSettings();
		IDialogSettings section = workbenchSettings
				.getSection("BasicNewProjectResourceWizard");//$NON-NLS-1$
		if (section == null) {
			section = workbenchSettings
					.addNewSection("BasicNewProjectResourceWizard");//$NON-NLS-1$
		}
		setDialogSettings(section);
	}

	public void addPages() {
		super.addPages();

		mainPage = new WizardNewProjectCreationPage("basicNewProjectPage");//$NON-NLS-1$
		mainPage.setTitle(ResourceMessages.NewProject_title);
		mainPage.setDescription(ResourceMessages.NewProject_description);
		this.addPage(mainPage);
	}

	/**
	 * Returns the newly created project.
	 * 
	 * @return the created project, or <code>null</code> if project not
	 *         created
	 */
	public IProject getNewProject() {
		return newProject;
	}

	/*
	 * (non-Javadoc) Method declared on IWorkbenchWizard.
	 */
	public void init(IWorkbench workbench, IStructuredSelection currentSelection) {
		super.init(workbench, currentSelection);
		setNeedsProgressMonitor(true);
		setWindowTitle(ResourceMessages.NewProject_windowTitle);
	}

	/*
	 * (non-Javadoc) Method declared on IWizard.
	 */
	public boolean performFinish() {
		try {
			//BasicNewProjectResourceWizard.updatePerspective(configElement);
			if(mainPage.getProjectName().contains("#")){
				MessageDialog.openError(getShell(), getWindowTitle(), "Project name contains invalid # character. Please rename it.");
				return false;
			}
			getContainer()
					.run(
							false,
							true,
							new NewProjectCreationOperation(mainPage
									.getProjectHandle()));
			return true;
		} catch (InvocationTargetException e) {
			Activator.logException(e);
		} catch (InterruptedException e) {
		}
		return false;
	}

	/**
	 * Stores the configuration element for the wizard. The config element will
	 * be used in <code>performFinish</code> to set the result perspective.
	 */
	public void setInitializationData(IConfigurationElement cfig,
			String propertyName, Object data) {
		configElement = cfig;
	}

}