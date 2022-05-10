package eu.redseeds.sc.current.repository.impl;

import java.util.Collection;

import org.eclipse.core.resources.IProject;

import eu.redseeds.sc.current.repository.SCProject;
import eu.redseeds.sc.current.repository.SCProjectContainer;
import eu.redseeds.sc.current.repository.interfaces.ICurrRepoContent;
import eu.redseeds.scl.model.sclkernel.SoftwareCaseDTO;

public class CurrRepoContentImpl implements ICurrRepoContent {

	/**
	 * Creates a new CurrentRepository object
	 */
	public CurrRepoContentImpl() {
	}

	public SCProject getSCProject(IProject eclipseProject) {
		return SCProjectContainer.instance().getSCProject(eclipseProject);
	}

	@Override
	public SCProject createSCProject(IProject eclipseProject) {
		if (eclipseProject == null) {
			//throw new Exception("Creating a new software case project without any given information is not possible");
			return null;
		}
		SCProject scProject = new SCProject(eclipseProject);
		
		SCProjectContainer.instance().addSCProject(scProject);
		return scProject;
	}
	
	
	@Override
	public void deleteSoftwareCase(SCProject softwareCase, boolean removeRemote) {
		// TODO Auto-generated method stub
	}

	@Override
	public Collection<SCProject> getSCProjectList() {
		return SCProjectContainer.instance().getSCProjects();
	}

	@Override
	public void updateSoftwareCase(SCProject softwareCase) {
		// TODO Auto-generated method stub
	}

	@Override
	public void commitSoftwareCase(SoftwareCaseDTO softwareCase) {
		// TODO Auto-generated method stub
	}

}
