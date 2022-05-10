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
import org.eclipse.ui.model.WorkbenchLabelProvider;

import eu.redseeds.common.Constants;
import eu.redseeds.common.InitialFolderStructureHelper;
import eu.redseeds.common.SCProjectHelper;
import eu.redseeds.engine.navigator.IImageKeys;
import eu.redseeds.sc.current.repository.SCProject;
import eu.redseeds.sc.current.repository.SCProjectContainer;
import eu.redseeds.scl.rsl.rslrequirements.requirementsspecifications.RequirementsSpecification;

public class ExtendedDomainSpecificationContentLabelProvider extends EventManager implements IProvider {

	@Override
	public Object[] getElements(Object inputElement) {
		return getChildren(inputElement);
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		if(parentElement instanceof IFile && "model.rsldl".equals(((IFile) parentElement).getName())) {
			IFile file = (IFile) parentElement;
			return getDomainDiagrams(file);
		}
		return new Object[0];
	}
	
	public Object[] getDomainDiagrams(IFile file){
		ArrayList<IFile> domaindiagramsFiles = new ArrayList<IFile>();
		if(null!=SCProjectHelper.getActiveProject() && null!=file.getProject()){
			IFolder currSCFolder = file.getProject().getFolder(Constants.CURRENT_SC_FOLDER_NAME);
			if(!currSCFolder.isAccessible())
				InitialFolderStructureHelper.createInitialFolderStructure(file.getProject());
			IFolder domaindiagrams = currSCFolder.getFolder(Constants.DOMAINDIAGRAMS_FOLDER_NAME);
			IResource[] domaindiagramsRes = null;
			try {
				domaindiagramsRes = ((IContainer)domaindiagrams).members();
			} catch (CoreException e) {
				e.printStackTrace();
			}
			if(domaindiagramsRes != null)
				for(int i=0; i< domaindiagramsRes.length; i++)
					if(domaindiagramsRes[i] instanceof IFile && ((IFile)domaindiagramsRes[i]).getFileExtension().equalsIgnoreCase("rsldl_diagram"))
								domaindiagramsFiles.add((IFile)domaindiagramsRes[i]);
			return domaindiagramsFiles.toArray();
		}
		return new Object[0];
	}

	@Override
	public Object getParent(Object element) {
		if(element instanceof IFile && "model.rsldl".equals(((IFile) element).getName())) {
			if(SCProjectHelper.getActiveProject() != null && ((IFile) element).getProject() != null && null!=SCProjectContainer.instance().getSCProject(((IFile) element).getProject())){
				SCProject scp = SCProjectContainer.instance().getSCProject(((IFile) element).getProject());
				for(RequirementsSpecification v:scp.getBusinessLayerFacade().getRequirementsSpecificationVertices())
					return v;
			}
			return null;
		}
		if(element instanceof IFile && "rsldl_diagram".equalsIgnoreCase(((IFile) element).getFileExtension())) {
			if(SCProjectHelper.getActiveProject() != null && ((IFile) element).getProject() != null){
				IFolder currSCFolder = ((IFile) element).getProject().getFolder(Constants.CURRENT_SC_FOLDER_NAME);
				if(!currSCFolder.isAccessible())
					InitialFolderStructureHelper.createInitialFolderStructure(((IFile) element).getProject());
				IFile edm = currSCFolder.getFile(Constants.DOMAINDIAGRAMS_FILE_NAME);
				if(edm.isAccessible())
					return edm;
			}
			return null;
		}
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		if(element instanceof IFile && "model.rsldl".equals(((IFile) element).getName())) {
			IFile file = (IFile) element;
			return 0<getDomainDiagrams(file).length;
		}
		return false;
	}

	@Override
	public void dispose() {
		
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		
	}

	@Override
	public Image getImage(Object element) {
		if(element instanceof IFile && "model.rsldl".equals(((IFile) element).getName())) {
			return ImageCache.getImage(IImageKeys.EXTENDED_DOMAIN_PACKAGE);
		} else if(element instanceof IFile && "rsldl_diagram".equalsIgnoreCase(((IFile) element).getFileExtension())){
			return hasChildren(element)?ImageCache.getImage(IImageKeys.DOMAIN_PACKAGE):new WorkbenchLabelProvider().getImage(element);
		}
		return null;
	}

	@Override
	public String getText(Object element) {
		if(element instanceof IFile && "model.rsldl".equals(((IFile) element).getName())) {
			return Constants.DOMAINDIAGRAMS_NODE_NAME;
		} else if(element instanceof IFile && "rsldl_diagram".equalsIgnoreCase(((IFile) element).getFileExtension())){
			String name = ((IFile) element).getName();
			return name.substring(0,name.lastIndexOf('.'));
		}
		return element.toString();
	}

	@Override
	public void addListener(ILabelProviderListener listener) {
		
	}

	@Override
	public boolean isLabelProperty(Object element, String property) {
		return false;
	}

	@Override
	public void removeListener(ILabelProviderListener listener) {
		
	}

}
