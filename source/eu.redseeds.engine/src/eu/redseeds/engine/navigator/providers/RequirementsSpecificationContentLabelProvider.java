package eu.redseeds.engine.navigator.providers;

import java.util.ArrayList;

import org.eclipse.core.commands.common.EventManager;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

import eu.redseeds.common.Constants;
import eu.redseeds.common.DiagramFileHelper;
import eu.redseeds.common.InitialFolderStructureHelper;
import eu.redseeds.common.SCProjectHelper;
import eu.redseeds.engine.navigator.IImageKeys;
import eu.redseeds.sc.current.repository.SCProjectContainer;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentations.descriptiverequirementrepresentations.ConstrainedLanguageScenarioDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementsPackageDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementsSpecificationDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.UseCaseDTO;
import eu.redseeds.scl.rsl.rslrequirements.requirementsspecifications.RequirementsPackage;

public class RequirementsSpecificationContentLabelProvider extends EventManager
		implements IProvider {
	
	/**
	 * Handling usecase diagrams files
	 */
	public Object[] getUseCaseDiagramsFromPackage(RequirementsPackageDTO reqpack){
		
		ArrayList<IFile> ucdiagramsFiles = new ArrayList<IFile>();

		if(SCProjectHelper.getActiveProject() != null && SCProjectContainer.instance().getSCProject(reqpack) != null
				/*&& SCProjectContainer.instance().getSCProject(SCProjectHelper.getActiveProject())
				.equals(SCProjectContainer.instance().getSCProject(reqpack))*/ ){

			IFolder currSCFolder = SCProjectContainer.instance().getSCProject(reqpack).getEclipseProject().getFolder(Constants.CURRENT_SC_FOLDER_NAME);
			if(!currSCFolder.isAccessible())
				InitialFolderStructureHelper.createInitialFolderStructure(SCProjectContainer.instance().getSCProject(reqpack).getEclipseProject());
			IFolder ucdiagrams = currSCFolder.getFolder(Constants.UCDIAGRAMS_FOLDER_NAME);
			IResource[] ucdiagramsRes = null;
			try {
				ucdiagramsRes = ((IContainer)ucdiagrams).members();
			} catch (CoreException e) {
				e.printStackTrace();
			}

			if(ucdiagramsRes != null)
				for(int i=0; i< ucdiagramsRes.length; i++)
					if(ucdiagramsRes[i] instanceof IFile && ((IFile)ucdiagramsRes[i]).getFileExtension().equalsIgnoreCase("usecasediagram_diagram") && null!=DiagramFileHelper.getUseCaseDiagramId(((IFile)ucdiagramsRes[i]).getLocation().toOSString()) && ((RequirementsPackage)reqpack).getId() == DiagramFileHelper.getUseCaseDiagramId(((IFile)ucdiagramsRes[i]).getLocation().toOSString()))
								ucdiagramsFiles.add((IFile)ucdiagramsRes[i]);
			return ucdiagramsFiles.toArray();
		}
		return new Object[0];
	}
	
	public Integer getDiagramPackageId(IFile current){
		if(SCProjectHelper.getActiveProject() != null && current.getFileExtension().equalsIgnoreCase("usecasediagram_diagram"))
				return DiagramFileHelper.getUseCaseDiagramId(current.getLocation().toOSString());
		return null;
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		if(parentElement instanceof RequirementsSpecificationDTO) {
			RequirementsSpecificationDTO reqspec 
				= (RequirementsSpecificationDTO) parentElement;
			return new Object[] { reqspec.getRequirementsPackagesDTOList().toArray() };
		}
		else if(parentElement instanceof RequirementsPackageDTO) {
			RequirementsPackageDTO reqpack 
				= (RequirementsPackageDTO) parentElement;
			Object[] reqs  = reqpack.getRequirements().toArray();
			Object[] reqspacks = reqpack.getRequirementsPackages().toArray();
			Object[] ucdiagramsFiles = getUseCaseDiagramsFromPackage(reqpack);
			
			Object[] result = new Object[reqs.length + reqspacks.length + ucdiagramsFiles.length];
			System.arraycopy(reqs, 0, result, 0, reqs.length);
			System.arraycopy(reqspacks, 0, result, reqs.length, reqspacks.length);
			System.arraycopy(ucdiagramsFiles, 0, result, reqs.length+reqspacks.length, ucdiagramsFiles.length);
			return result;
		}
		else if(parentElement instanceof RequirementDTO 
				&& !(parentElement instanceof UseCaseDTO) ) {
			return new Object[0];
		}
		else if(parentElement instanceof UseCaseDTO) {
			UseCaseDTO uc = (UseCaseDTO) parentElement;
//			List<TraceabilityLinkDTO> list 
//			= ((TraceableObjectDTO)uc).getTraceabilityLinkDTOList();
//			for(TraceabilityLinkDTO link : list) {
//				System.out.println("Source: "+link.getSource());
//				System.out.println("Target: "+link.getTarget());
//			}
			return uc.getConstrainedLanguageScenarioDTOList().toArray();
		}
		else if(parentElement instanceof ConstrainedLanguageScenarioDTO) {
			return new Object[0];
		}	
		return new Object[0];
	}

	@Override
	public Object getParent(Object element) {
		if(element instanceof RequirementsPackageDTO) {
			RequirementsPackageDTO reqpack 
				= (RequirementsPackageDTO) element;
			return reqpack.getParent();
		}
		else if(element instanceof RequirementDTO 
				&& !(element instanceof UseCaseDTO) ) {
			RequirementDTO req = (RequirementDTO)element;
			return req.getParent();
		}
		else if(element instanceof UseCaseDTO) {
			UseCaseDTO uc = (UseCaseDTO)element;
			return uc.getParent();
		}
		/*else if(element instanceof IFile){
			IFile ucdiagram = (IFile) element;
			if(ucdiagram.getFileExtension().equals("usecasediagram_diagram")){
				return RecoveryModelHelper.getRequirementsPackageByVertexID(getDiagramPackageId(ucdiagram));
			}
		}*/
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		if(element instanceof RequirementsSpecificationDTO) {
			RequirementsSpecificationDTO reqspec 
				= (RequirementsSpecificationDTO) element;
			return 
				reqspec.getRequirementsPackagesDTOList().size() > 0 ? true : false;
		}
		else if(element instanceof RequirementsPackageDTO) {
			RequirementsPackageDTO parent = (RequirementsPackageDTO) element;
			return (parent.getRequirementsPackages().size() 
					+ parent.getRequirements().size() + getUseCaseDiagramsFromPackage(parent).length) > 0 ? true : false;
		}
		else if(element instanceof RequirementDTO && !(element instanceof UseCaseDTO)) {
			return false;
		}
		else if(element instanceof UseCaseDTO) {
			UseCaseDTO uc = (UseCaseDTO)element;
			return uc.getConstrainedLanguageScenarioDTOList().size() > 0 ? true : false;
		}
		else if(element instanceof ConstrainedLanguageScenarioDTO) {
			return false;
		}
		else {
			return true;//TODO
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
		if (element instanceof RequirementsPackageDTO) {
			RequirementsPackageDTO parent = (RequirementsPackageDTO) element;
			return parent.getName();
		} 
		else if (element instanceof RequirementDTO && !(element instanceof UseCaseDTO)) {
			RequirementDTO parent = (RequirementDTO) element;
			return parent.getName();
		} 
		else if (element instanceof UseCaseDTO) {
			UseCaseDTO parent = (UseCaseDTO) element;
			return parent.getName();
		}
		else if (element instanceof ConstrainedLanguageScenarioDTO) {
			ConstrainedLanguageScenarioDTO parent 
				= (ConstrainedLanguageScenarioDTO) element;
			return parent.getName();
		}
		else {
			return "";
		}
	}

	@Override
	public Image getImage(Object obj) {
		if (obj instanceof RequirementsPackageDTO) {
			if(((RequirementsPackageDTO)obj).getStereotypes().contains(Constants.ALP_STEREOTYPE)) {
				return ImageCache.getImage(IImageKeys.ALP);
			}
			else {
				return ImageCache.getImage(IImageKeys.REQUIREMENTS_PACKAGE);
			}
		}
		else if (obj instanceof RequirementDTO && !(obj instanceof UseCaseDTO)) {
			return ImageCache.getImage(IImageKeys.REQUIREMENT);
		}
		else if (obj instanceof UseCaseDTO) {
			return ImageCache.getImage(IImageKeys.USE_CASE);
//			if(!((UseCaseDTO)obj).isValid()) {
//				ImageDescriptor imagedescs =Activator.getDefault().
//					createImageDescriptorFor(IImageKeys.PROBLEM);
//				DecorationOverlayIcon oi = new DecorationOverlayIcon(image, 
//						new ImageDescriptor[] {imagedescs});
//				return oi.createImage();
//			}
//			return image;
		}
		else if (obj instanceof ConstrainedLanguageScenarioDTO) {
			return ImageCache.getImage(IImageKeys.SCENARIO);
		}
		return PlatformUI.getWorkbench().getSharedImages().getImage(
				ISharedImages.IMG_OBJ_ELEMENT);
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
