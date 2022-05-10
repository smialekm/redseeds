package eu.redseeds.sc.editor.rsl.actions;

import java.util.List;

import org.eclipse.jface.action.Action;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;

import eu.redseeds.common.SCProjectHelper;
import eu.redseeds.sc.current.repository.SCProject;
import eu.redseeds.scl.model.rsl.rsldomainelements.actors.ActorDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.actors.ActorsPackageDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionsPackageDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.systemelements.SystemElementDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.systemelements.SystemElementsPackageDTO;

public class AddDomainElementAction extends Action {
	IWorkbenchPage activePage = null;
	private Object domainElement;
	private SCProject scProject;
	
	public AddDomainElementAction(SCProject scProject){
		this.scProject = scProject;
		activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage(); 
	}
	

	
	@Override
	public void run() {
		if (domainElement instanceof NotionDTO) {
			List<NotionsPackageDTO> notionsPackages = scProject.getMainCase()
					.getRequirementsSpecificationDTO()
					.getDomainSpecificationDTO().getNotionsPackageDTOList();
			if (notionsPackages != null) {
				if (notionsPackages.size() == 1) {
					NotionsPackageDTO pack = notionsPackages.get(0);
					pack.addNotionDTO((NotionDTO) domainElement);
				}
			}
		}
		if (domainElement instanceof ActorDTO) {
			List<ActorsPackageDTO> actorsPackages = scProject.getMainCase()
					.getRequirementsSpecificationDTO()
					.getDomainSpecificationDTO().getActorsPackageDTOList();
			if (actorsPackages != null) {
				if (actorsPackages.size() == 1) {
					ActorsPackageDTO pack = actorsPackages.get(0);
					pack.addActorDTO((ActorDTO) domainElement);
				}
			}
		}
		if (domainElement instanceof SystemElementDTO) {
			List<SystemElementsPackageDTO> sysElemsPackages = scProject.getMainCase()
					.getRequirementsSpecificationDTO()
					.getDomainSpecificationDTO().getSystemElementsPackageDTOList();
			if (sysElemsPackages != null) {
				if (sysElemsPackages.size() == 1) {
					SystemElementsPackageDTO pack = sysElemsPackages.get(0);
					pack.addSystemElementDTO((SystemElementDTO) domainElement);
				}
			}
		}
		scProject.save();
		SCProjectHelper.refreshSCNavigator();
	}
	
	public Object getDomainElement() {
		return domainElement;
	}

	public void setDomainElement(Object elem) {
		this.domainElement = elem;
	}

}
