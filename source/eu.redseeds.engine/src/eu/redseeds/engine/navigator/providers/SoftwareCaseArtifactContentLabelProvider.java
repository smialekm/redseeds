package eu.redseeds.engine.navigator.providers;

import java.util.List;

import org.eclipse.core.commands.common.EventManager;
import org.eclipse.core.resources.IFile;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

import eu.redseeds.common.Constants;
import eu.redseeds.common.DiagramFileHelper;
import eu.redseeds.common.SCProjectHelper;
import eu.redseeds.engine.navigator.IImageKeys;
import eu.redseeds.sc.current.repository.SCProjectContainer;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementsPackageDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementsSpecificationDTO;
import eu.redseeds.scl.model.sclkernel.ArchitecturalModelDTO;
import eu.redseeds.scl.model.sclkernel.DetailedDesignModelDTO;
import eu.redseeds.scl.sclkernel.SCLElement;
import eu.remics.recovery.model.preferences.MConfiguration;


public class SoftwareCaseArtifactContentLabelProvider extends EventManager					
					implements IProvider {

	@Override
	public Object[] getChildren(Object parentElement) {
		if(parentElement instanceof RequirementsSpecificationDTO){
			RequirementsSpecificationDTO rs = (RequirementsSpecificationDTO)parentElement;
			List<RequirementsPackageDTO> reqs = rs.getRequirementsPackagesDTOList();
			RequirementsPackageDTO recoveredScenariosPack = null;
			for(RequirementsPackageDTO r : reqs){
				if(r.getName().equals("RecoveredScenarios")){
					recoveredScenariosPack = r;
					break;
				}
			}
			if(recoveredScenariosPack != null) reqs.remove(recoveredScenariosPack);
			
			Object[] rsarr = reqs.toArray();
			IFile edm = getExtendedDomainModel(rs);			
			Object[] result = new Object[rsarr.length+(null!=edm && MConfiguration.isEnableRSLDL()?2:1)];
			System.arraycopy(rsarr, 0, result, 0, rsarr.length);
			System.arraycopy(new Object[] {rs.getDomainSpecificationDTO()}, 
					0, result, rsarr.length, 1);
			if(null!=edm && MConfiguration.isEnableRSLDL())
				System.arraycopy(new Object[] {edm}, 
						0, result, rsarr.length+1, 1);
			return result;
		}
		else if(parentElement instanceof ArchitecturalModelDTO) {
			ArchitecturalModelDTO arch = (ArchitecturalModelDTO)parentElement;
			return arch.getPackageDTOList().toArray();
		}
		else if(parentElement instanceof DetailedDesignModelDTO) {
			DetailedDesignModelDTO detd = (DetailedDesignModelDTO)parentElement;
			return detd.getPackageDTOList().toArray();
		}
		return new Object[0];
		
	}
	
	private IFile getExtendedDomainModel(RequirementsSpecificationDTO rs) {
		if(SCProjectHelper.getActiveProject() != null && SCProjectContainer.instance().getSCProject(rs) != null){
			return DiagramFileHelper.getExtendedDomainModel(SCProjectContainer.instance().getSCProject(rs).getEclipseProject());
		}
		return null;
	}

	@Override
	public Object getParent(Object element) {
		return SCProjectContainer.instance().getSCProject(element).getMainCase();
	}

	@Override
	public boolean hasChildren(Object element) {
		if (element instanceof RequirementsSpecificationDTO) {
			return true;
		}
		if (element instanceof ArchitecturalModelDTO) {
			ArchitecturalModelDTO arch = (ArchitecturalModelDTO)element;
			if(arch.getPackageDTOList().size() > 0) {
				return true;
			}
			else {
				return false;
			}
		} 
		if (element instanceof DetailedDesignModelDTO) {
			DetailedDesignModelDTO detd = (DetailedDesignModelDTO)element;
			if(detd.getPackageDTOList().size() > 0) {
				return true;
			}
			else {
				return false;
			}
		}
		else {
			return false;//TODO
		}
	}

	@Override
	public Object[] getElements(Object inputElement) {
		return getChildren(inputElement);
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		// TODO Auto-generated method stub

	}
	
	//---------------
	//label provider
	//---------------
	
	@Override
	public String getText(Object element) {
		if(element instanceof RequirementsSpecificationDTO) {
			return Constants.REQUIREMENTS_NODE_NAME;
		}
		else if(element instanceof ArchitecturalModelDTO) {
			return Constants.ARCHITECTURE_NODE_NAME;
		}
		else if(element instanceof DetailedDesignModelDTO) {
			return Constants.DESIGN_NODE_NAME;
		}
		else if(element instanceof SCLElement) {
			SCLElement child = (SCLElement)element;
			return child.toString();
		}
		else {
			return "";
		}
	}
	
	@Override
	public Image getImage(Object obj) {
		if(obj instanceof RequirementsSpecificationDTO) {
			return ImageCache.getImage(IImageKeys.REQUIREMENTS_SPECIFICATION);
		}
		else if(obj instanceof ArchitecturalModelDTO) {
			return ImageCache.getImage(IImageKeys.PACKAGE);
		}
		else if(obj instanceof DetailedDesignModelDTO) {
			return ImageCache.getImage(IImageKeys.PACKAGE);	
		}
		return PlatformUI.getWorkbench().
				getSharedImages().getImage(ISharedImages.IMG_OBJ_ELEMENT);
	}

	@Override
	public void addListener(ILabelProviderListener listener) {
		addListenerObject(listener);
	}
	
	@Override
	public void removeListener(ILabelProviderListener listener) {
		removeListenerObject(listener);
	}
	
	/* (non-Javadoc)
	 * return true
	 * @see org.eclipse.jface.viewers.IBaseLabelProvider#isLabelProperty(java.lang.Object, java.lang.String)
	 */
	@Override
	public boolean isLabelProperty(Object element, String property) {
		return true;
	}

}
