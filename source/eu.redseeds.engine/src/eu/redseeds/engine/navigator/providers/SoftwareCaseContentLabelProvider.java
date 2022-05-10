package eu.redseeds.engine.navigator.providers;

import org.eclipse.core.commands.common.EventManager;
import org.eclipse.core.resources.IFolder;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

import eu.redseeds.common.Constants;
import eu.redseeds.sc.current.repository.SCProjectContainer;
import eu.redseeds.scl.model.sclkernel.ClipboardDTO;
import eu.redseeds.scl.model.sclkernel.SoftwareCaseArtifact;
import eu.redseeds.scl.model.sclkernel.SoftwareCaseDTO;

public class SoftwareCaseContentLabelProvider extends EventManager implements
		IProvider {

	@Override
	public Object[] getChildren(Object parentElement) {
		if (parentElement instanceof SoftwareCaseDTO) {
			SoftwareCaseDTO sc = (SoftwareCaseDTO) parentElement;
			IFolder codeFolder 
				= SCProjectContainer.instance().getSCProject(sc).getEclipseProject()
					.getFolder(
							Constants.CURRENT_SC_FOLDER_NAME 
							+ System.getProperty("file.separator")//IPath.SEPARATOR
							+ Constants.CODE_FOLDER_NAME);
			return new Object[] {
					sc.getRequirementsSpecificationDTO(), 
					sc.getArchitecturalModelDTO(), 
					sc.getDetailedDesignModelDTO(),
					codeFolder};
		}
		else if (parentElement instanceof ClipboardDTO) {
			ClipboardDTO clip = (ClipboardDTO) parentElement;
			return new SoftwareCaseArtifact[] {
					clip.getRequirementsSpecificationDTO(), 
					clip.getArchitecturalModelDTO(), 
					clip.getDetailedDesignModelDTO()};
		}
		return new Object[0];
	}

	@Override
	public Object getParent(Object element) {
		if (element instanceof SoftwareCaseDTO) {
			SoftwareCaseDTO sc = (SoftwareCaseDTO) element;
			return SCProjectContainer.instance().getSCProject(sc).getEclipseProject();
		}
		else if (element instanceof ClipboardDTO) {
			ClipboardDTO clip = (ClipboardDTO) element;
			return SCProjectContainer.instance().getSCProject(clip).getMainCase();
		}
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		if (element instanceof SoftwareCaseDTO) {
			return true;
		}
		else if (element instanceof ClipboardDTO) {
			return true;
		}
		return true;
	}

	@Override
	public Object[] getElements(Object inputElement) {
		// TODO Auto-generated method stub
		return null;
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
		if (element instanceof SoftwareCaseDTO) {
			SoftwareCaseDTO parent = (SoftwareCaseDTO) element;
			return parent.toString();
		}  
		else if (element instanceof ClipboardDTO) {
			ClipboardDTO parent = (ClipboardDTO) element;
			return parent.getName();
		} 
		else {
			return "";
		}
	}

	@Override
	public Image getImage(Object obj) {
		if (obj instanceof SoftwareCaseDTO) {
			return PlatformUI.getWorkbench().getSharedImages().getImage(
					ISharedImages.IMG_OBJ_FOLDER);
		}
		else if (obj instanceof ClipboardDTO) {
			return PlatformUI.getWorkbench().getSharedImages().getImage(
					ISharedImages.IMG_OBJ_FOLDER);
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
