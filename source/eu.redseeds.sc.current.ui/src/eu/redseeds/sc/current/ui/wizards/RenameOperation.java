package eu.redseeds.sc.current.ui.wizards;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.ui.actions.WorkspaceModifyOperation;

import eu.redseeds.sc.current.repository.SCProject;
import eu.redseeds.sc.current.repository.SCProjectContainer;
import eu.redseeds.scl.model.rsl.rsldomainelements.actors.ActorDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.actors.ActorsPackageDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionsPackageDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.NounLinkDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.NounPhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.systemelements.SystemElementDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.systemelements.SystemElementsPackageDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementsPackageDTO;

public class RenameOperation extends WorkspaceModifyOperation {
	
	private String newName;
	private NounPhraseDTO newNamePhrase;
	private Object selectedObj;
	private IProject eclipseProject;
	private List<NounLinkDTO> values;
	
	
	public RenameOperation(String newName, Object selectedObj,
			IProject eclipseProject) {
		this.newName = newName;
		this.selectedObj = selectedObj;
		this.eclipseProject = eclipseProject;
	}
	
	public RenameOperation(NounPhraseDTO newName, Object selectedObj,
			IProject eclipseProject) {
		this.newNamePhrase = newName;
		this.selectedObj = selectedObj;
		this.eclipseProject = eclipseProject;
	}
	
	public RenameOperation(NounPhraseDTO newName, List<NounLinkDTO> values, Object selectedObj,
			IProject eclipseProject) {
		this.newNamePhrase = newName;
		this.selectedObj = selectedObj;
		this.values = values;
		this.eclipseProject = eclipseProject;
	}


	@Override
	protected void execute(IProgressMonitor monitor) throws CoreException,
			InvocationTargetException, InterruptedException {
		// start task
		monitor.beginTask("Renaming...", 1);
		renameElement();
		monitor.worked(1);	
	}
	
	private void renameElement() {
		SCProject scProject = SCProjectContainer.instance().getSCProject(eclipseProject);
		
		if (selectedObj instanceof RequirementsPackageDTO){
			RequirementsPackageDTO rp = (RequirementsPackageDTO)selectedObj;				
			rp.setName(newName);								
		}
		else if (selectedObj instanceof ActorsPackageDTO){
			ActorsPackageDTO ap = (ActorsPackageDTO)selectedObj;				
			ap.setName(newName);								
		}
		else if (selectedObj instanceof NotionsPackageDTO){
			NotionsPackageDTO np = (NotionsPackageDTO)selectedObj;				
			np.setName(newName);								
		}
		else if (selectedObj instanceof SystemElementsPackageDTO){
			SystemElementsPackageDTO sep = (SystemElementsPackageDTO)selectedObj;				
			sep.setName(newName);								
		}
		else if (selectedObj instanceof NotionDTO){
			NotionDTO notion = (NotionDTO)selectedObj;	
			notion.rename(newNamePhrase, values);								
		}
		else if (selectedObj instanceof ActorDTO){
			ActorDTO actor = (ActorDTO)selectedObj;	
			actor.rename(newNamePhrase);								
		}
		else if (selectedObj instanceof SystemElementDTO){
			SystemElementDTO sysel = (SystemElementDTO)selectedObj;	
			sysel.rename(newNamePhrase);								
		}
		
		scProject.save();
	}

}
