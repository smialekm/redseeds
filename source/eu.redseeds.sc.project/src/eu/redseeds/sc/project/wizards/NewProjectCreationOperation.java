package eu.redseeds.sc.project.wizards;

/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/


import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.actions.WorkspaceModifyOperation;

import eu.redseeds.common.InitialFolderStructureHelper;

import eu.redseeds.sc.current.repository.SCProject;
import eu.redseeds.sc.current.repository.interfaces.ICurrRepoContent;
import eu.redseeds.sc.project.Activator;

public class NewProjectCreationOperation extends WorkspaceModifyOperation {
	
	private IProject newProject;
	
	public NewProjectCreationOperation(IProject project) {
		newProject=project;
	}
	
	private IProject createProject() throws CoreException {
		if (!newProject.exists()) {
			IProjectDescription desc = ResourcesPlugin.getWorkspace().newProjectDescription(newProject.getName());
			newProject.create(desc, null);
				
			newProject.open(null);
		}

		// assign software case model nature
		IProjectDescription description = newProject.getDescription();
		String[] natures = description.getNatureIds();
		String[] newNatures = new String[natures.length + 1];
		System.arraycopy(natures, 0, newNatures, 0, natures.length);
		newNatures[natures.length] = "eu.redseeds.sc.project.projectnature";
		description.setNatureIds(newNatures);
		newProject.setDescription(description, null);
		
		return newProject;
	}

//	private void createClipboard(IProject project) throws CoreException {
//		IFolder clipboardFolder=newProject.getFolder("clipboard");
//		clipboardFolder.create(false, true, null);
//	}
//
//	private void createOutput(IProject project) throws CoreException {
//		IFolder outputFolder=newProject.getFolder("out");
//		outputFolder.create(false, true, null);
//		outputFolder.setDerived(true);
//	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.actions.WorkspaceModifyOperation#execute(org.eclipse.core.runtime.IProgressMonitor)
	 */
	protected void execute(IProgressMonitor monitor) throws CoreException,
			InvocationTargetException, InterruptedException {

		// start task
		monitor.beginTask(ResourceMessages.NewProjectCreationOperation_creating,
				2); 
		monitor.subTask(ResourceMessages.NewProjectCreationOperation_project); 

		// create project
		IProject project = createProject();
		monitor.worked(1);
		
		// generate empty software case model
		monitor.subTask(ResourceMessages.NewProjectCreationOperation_modelFile);
		
		SCProject newSCProj;
		
		ICurrRepoContent currRepo 
			= Activator.getDefault().getICurrRepoContentInstance();
		if (currRepo != null) {
			newSCProj = currRepo.createSCProject(project);
			newSCProj.setName(project.getName());
			newSCProj.setWorkspaceLocation(
					project.getWorkspace().getRoot().getLocationURI().toString());
			
			if(newSCProj!=null) {
				Activator.log("Created new SC project "+ newSCProj.getName(), Status.INFO);
			}
		}
		
		InitialFolderStructureHelper.createInitialFolderStructure(project);
		monitor.worked(1);

		// create clipboard
//		monitor.subTask(ResourceMessages.NewProjectCreationOperation_clipboard); 
//		createClipboard(project);
//		monitor.worked(1);
//
//		// create output folder
//		monitor.subTask(ResourceMessages.NewProjectCreationOperation_output); 
//		createOutput(project);
//		monitor.worked(1);
//		
	}
	
}
