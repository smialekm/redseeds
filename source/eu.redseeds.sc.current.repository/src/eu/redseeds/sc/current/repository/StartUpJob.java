package eu.redseeds.sc.current.repository;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Status;

import eu.redseeds.common.jobs.IJob;
import eu.redseeds.sc.current.repository.impl.CurrRepoContentImpl;
import eu.redseeds.sc.current.repository.interfaces.ICurrRepoContent;
import eu.redseeds.sc.terminology.model.RemoteJGWNL;
import eu.redseeds.scl.model.BusinessLayerFacade;
import eu.redseeds.scl.model.rsl.rsldomainelements.terms.TermDTO;

public class StartUpJob implements IJob {

	@Override
	public int getTotalTime() {
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		return workspace.getRoot().getProjects().length + 5;
	}
	
	@Override
	public String getName() {
		return "Preparing workspace";
	}

	@Override
	public void run(IProgressMonitor monitor) throws InvocationTargetException,
			InterruptedException {
		monitor.beginTask(getName(), getTotalTime());
		
		readProjects(monitor);
		
		//checkJGWNLServer(monitor);
		
		//checkTerms(monitor);
		
		initGReQL(monitor);
		
		monitor.done();
	}
	
	private void checkJGWNLServer(IProgressMonitor monitor) {
		monitor.subTask("Connecting to the JGWNL server...");
		RemoteJGWNL.getInstance();
		monitor.worked(1);
	}

	private void readProjects(IProgressMonitor monitor) {
		monitor.subTask("Init");
		Activator.log("Reading projects in workspace", Status.INFO);
		monitor.subTask("Reading projects in workspace");
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		ICurrRepoContent currRepo = new CurrRepoContentImpl();
		monitor.worked(1);
		for (IProject project : workspace.getRoot().getProjects()) {
			try {
				if (project.isOpen() && project.isNatureEnabled("eu.redseeds.sc.project.projectnature")) {
					currRepo.createSCProject(project);
//					Activator.log("Project read "+project.getName(), Status.INFO);
				}
			} catch (CoreException ce) {
				Activator.log("Reading project error: " + ce.getMessage(), Status.ERROR);
			}
			finally {
				monitor.worked(1);
			}
		}
		monitor.subTask("Reading projects finished");
		Activator.log("Reading projects finished", Status.INFO);
		monitor.worked(1);
	}
	
	private void checkTerms(IProgressMonitor monitor) {
		monitor.subTask("Checking term links...");
        if(!RemoteJGWNL.getInstance().isConnected()) {//skip the test
        	return;
        }
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		for (IProject project : workspace.getRoot().getProjects()) {
			try {
				if (project.isOpen() && project.isNatureEnabled("eu.redseeds.sc.project.projectnature")) {
					SCProject scproject = SCProjectContainer.instance().getSCProject(project);
					BusinessLayerFacade facade = scproject.getBusinessLayerFacade();
					for(TermDTO term : facade.getAllTerms()) {
						if (term.getSynonymUid() != 0){
							if (RemoteJGWNL.getInstance().getTermSenseDTO(term.getSynonymUid()) == null) {
								Activator.log("Reading project \""+project.getName()+"\" error: Term sense for \"" + term.getName() + "\" is not present in the terminology", Status.ERROR);
								scproject.setTermsConnected(false);
							}
						}
					}
				}
			} catch (CoreException e) {
				Activator.log("Reading project error: " + e.getMessage(), Status.ERROR);
			}
		}
		monitor.worked(1);
	}
	
	/**
	 * Used to avoid delays for initialization later (which causes problems for e.g. drag&drop)
	 * @param monitor
	 */
	private void initGReQL(IProgressMonitor monitor) {
		monitor.subTask("Initializing components...");
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		for (IProject project : workspace.getRoot().getProjects()) {
			try {
				if (project.isOpen() && project.isNatureEnabled("eu.redseeds.sc.project.projectnature")) {
					SCProject scproject = SCProjectContainer.instance().getSCProject(project);
					BusinessLayerFacade facade = scproject.getBusinessLayerFacade();
					if(scproject.getMainCase() != null) {
						if(scproject.getMainCase().getRequirementsSpecificationDTO() != null) {
							facade.isAnyClipboardMember(scproject.getMainCase().getRequirementsSpecificationDTO());
//							break;
						}
						else {
							continue;
						}
					}
					
				}
			} catch (CoreException e) {
				Activator.log("Reading project error: " + e.getMessage(), Status.ERROR);
			}
		}
		monitor.worked(1);
	}
	
//	public static void sleep(int ms) {
//        try {
//            Thread.sleep(ms * 10);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }

}
