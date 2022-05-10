package eu.redseeds.sc.current.repository;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Status;

import eu.redseeds.sc.current.repository.impl.CurrRepoContentImpl;
import eu.redseeds.sc.current.repository.interfaces.ICurrRepoContent;


public class SCProjectListener implements IResourceChangeListener {
	
	/**
	 * Processes the given {@code event}. If {@code event} represents the opening of a
	 * software case project, {@code createSCProject} is called. If {@code event} represents the
	 * closing or deletion of a software case project, {@code removeSCProject} is called.
	 * 
	 * @param event the {@code IResourceChangeEvent} to be processed
	 * 
	 * @author hschwarz
	 */
	@Override
	public void resourceChanged(IResourceChangeEvent event) {
		IResource resource;
		
		try {
			switch (event.getType()) {
				// in the case an Eclipse project associated with a software case is closed
				case IResourceChangeEvent.PRE_CLOSE:
					resource = event.getResource();
					
					if (((IProject)resource).isNatureEnabled("eu.redseeds.sc.project.projectnature")) {
						removeSCProject((IProject)resource);
					}
					
					break;
				// in the case an Eclipse project associated with a software case is changed
				case IResourceChangeEvent.POST_CHANGE:
					for (IResourceDelta childDelta : event.getDelta().getAffectedChildren(IResourceDelta.CHANGED | IResourceDelta.OPEN | IResourceDelta.ADDED)) {
						resource = childDelta.getResource();
						
						// if the project is open and it is a software case project
						if (resource.getType() == IResource.PROJECT
								&& ((IProject)resource).isOpen()
								&& ((IProject)resource).isNatureEnabled("eu.redseeds.sc.project.projectnature")) {
							// if there is no associated SCProject instance, i.e. the project was closed before and is opened now
							if (!SCProjectContainer.instance().containsSCProject((IProject)resource)) {
								createSCProject((IProject)resource);
							} else {
								// check for deleted folders and remove associated clipboard software cases
								for (IResourceDelta childChildDelta : childDelta.getAffectedChildren(IResourceDelta.REMOVED)) {									
									if (childChildDelta.getResource().getType() == IResource.FOLDER) {
										SCProjectContainer.instance().getSCProject((IProject)resource).deleteClipboard((IFolder)childChildDelta.getResource());										
									}
								}
							}
						}	
					}
					
					break;
				// in the case a software case Eclipse project is deleted, remove the associated SCProject instance
				case IResourceChangeEvent.PRE_DELETE:
					resource = event.getResource();
					
					if (((IProject)resource).isNatureEnabled("eu.redseeds.sc.project.projectnature")) {
						removeSCProject((IProject)resource);
					}
					
					Runtime.getRuntime().gc();
					
					break;
			}
		} catch (CoreException ce) {
			Activator.log("Error during processing project related event: "+ce.getMessage(), Status.ERROR);
		}
	}
	
	private void removeSCProject(IProject eclipseProject) {
		SCProjectContainer.instance().removeSCProject(eclipseProject);
	}
	
	private void createSCProject(IProject eclipseProject) {
		ICurrRepoContent currRepo = new CurrRepoContentImpl(); 
		currRepo.createSCProject(eclipseProject);
	}
}
