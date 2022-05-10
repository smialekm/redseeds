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
import eu.redseeds.scl.model.rsl.rsldomainelements.actors.ActorDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.actors.ActorsPackageDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.domainelements.DomainSpecificationDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.DomainStatementDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionsPackageDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.systemelements.SystemElementDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.systemelements.SystemElementsPackageDTO;
import eu.redseeds.scl.rsl.rsldomainelements.notions.NotionsPackage;

public class DomainSpecificationContentLabelProvider extends EventManager
implements IProvider {
	
	/**
	 * Handling notions diagrams files
	 */
	public Object[] getNotionDiagramsFromPackage(NotionsPackageDTO notionpack){
		
		ArrayList<IFile> notiondiagramsFiles = new ArrayList<IFile>();

		if(SCProjectHelper.getActiveProject() != null && SCProjectContainer.instance().getSCProject(notionpack) != null
				/*&& SCProjectContainer.instance().getSCProject(SCProjectHelper.getActiveProject())
				.equals(SCProjectContainer.instance().getSCProject(reqpack))*/ ){

			IFolder currSCFolder = SCProjectContainer.instance().getSCProject(notionpack).getEclipseProject().getFolder(Constants.CURRENT_SC_FOLDER_NAME);
			if(!currSCFolder.isAccessible())
				InitialFolderStructureHelper.createInitialFolderStructure(SCProjectContainer.instance().getSCProject(notionpack).getEclipseProject());
			IFolder notiondiagrams = currSCFolder.getFolder(Constants.NOTIONSDIAGRAMS_FOLDER_NAME);
			IResource[] notiondiagramsRes = null;
			try {
				notiondiagramsRes = ((IContainer)notiondiagrams).members();
			} catch (CoreException e) {
				e.printStackTrace();
			}

			if(notiondiagramsRes != null)
				for(int i=0; i< notiondiagramsRes.length; i++)
					if(notiondiagramsRes[i] instanceof IFile && ((IFile)notiondiagramsRes[i]).getFileExtension().equalsIgnoreCase("notiondiagram_diagram") && null!=DiagramFileHelper.getNotionDiagramId(((IFile)notiondiagramsRes[i]).getLocation().toOSString()) && ((NotionsPackage)notionpack).getId() == DiagramFileHelper.getNotionDiagramId(((IFile)notiondiagramsRes[i]).getLocation().toOSString()))
								notiondiagramsFiles.add((IFile)notiondiagramsRes[i]);
			return notiondiagramsFiles.toArray();
		}
		return new Object[0];
	}
	
	public Integer getDiagramPackageId(IFile current){
		if(SCProjectHelper.getActiveProject() != null && current.getFileExtension().equalsIgnoreCase("notiondiagram_diagram"))
			return DiagramFileHelper.getNotionDiagramId(current.getLocation().toOSString());
		return null;
	}
	
	@Override
	public Object[] getChildren(Object parentElement) {
		if(parentElement instanceof DomainSpecificationDTO) {
			DomainSpecificationDTO ds = (DomainSpecificationDTO) parentElement;
			Object[] actorsArray = ds.getActorsPackageDTOList().toArray();
			Object[] notionsArray = ds.getNotionsPackageDTOList().toArray();
			Object[] syselemsArray = ds.getSystemElementsPackageDTOList().toArray();
			Object[] result = new Object[
			                  actorsArray.length 
			                  + notionsArray.length 
			                  + syselemsArray.length];
			System.arraycopy(actorsArray, 0, result, 0, actorsArray.length);
			System.arraycopy(notionsArray, 0, result, actorsArray.length, 
					notionsArray.length);
			System.arraycopy(syselemsArray, 0, result, notionsArray.length + actorsArray.length, 
					syselemsArray.length);
			return result;
		}
		else if(parentElement instanceof ActorsPackageDTO) {
			ActorsPackageDTO apack = (ActorsPackageDTO) parentElement;
			Object[] packages = apack.getActorsPackageDTOList().toArray();
			Object[] actors = apack.getActorDTOList().toArray();
			Object[] result = new Object[packages.length + actors.length];
			System.arraycopy(packages, 0, result, 0, packages.length);
			System.arraycopy(actors, 0, result, packages.length, actors.length);
			return result;
		}
		else if(parentElement instanceof NotionsPackageDTO) {
			NotionsPackageDTO npack = (NotionsPackageDTO) parentElement;
			Object[] packages = npack.getNotionsPackageDTOList().toArray();
			Object[] notions = npack.getNotionDTOList().toArray();
			Object[] notiondiagramsFiles = getNotionDiagramsFromPackage(npack);
			
			Object[] result = new Object[packages.length + notions.length + notiondiagramsFiles.length];
			System.arraycopy(packages, 0, result, 0, packages.length);
			System.arraycopy(notions, 0, result, packages.length, notions.length);
			System.arraycopy(notiondiagramsFiles, 0, result, packages.length+notions.length, notiondiagramsFiles.length);
			return result;
		}
		else if(parentElement instanceof SystemElementsPackageDTO) {
			SystemElementsPackageDTO sepack = (SystemElementsPackageDTO) parentElement;
			Object[] packages = sepack.getSystemElementsPackageDTOList().toArray();
			Object[] syselems = sepack.getSystemElementDTOList().toArray();
			Object[] result = new Object[packages.length + syselems.length];
			System.arraycopy(packages, 0, result, 0, packages.length);
			System.arraycopy(syselems, 0, result, packages.length, syselems.length);
			return result;
		}
		else if (parentElement instanceof ActorDTO) {
			return new Object[0];
		} 
		else if (parentElement instanceof NotionDTO) {
			NotionDTO notion = (NotionDTO) parentElement;
			return notion.getDomainStatementDTOList().toArray();
		}
		else if (parentElement instanceof SystemElementDTO) {
			return new Object[0];
		}
		else if (parentElement instanceof DomainStatementDTO) {
			return new Object[0];
		}
		return new Object[0];
	}

	@Override
	public Object getParent(Object element) {
		if(element instanceof ActorsPackageDTO) {
			ActorsPackageDTO act = (ActorsPackageDTO)element;
			if(act.getParent() == null && act.getDomainSpecificationParent() != null)
				return act.getDomainSpecificationParent();
			if(act.getParent() != null)
				return act.getParent();
		}
		else if(element instanceof NotionsPackageDTO) {
			NotionsPackageDTO notion = (NotionsPackageDTO)element;
			if(notion.getParent() == null && notion.getDomainSpecificationParent() != null)
				return notion.getDomainSpecificationParent();
			if(notion.getParent() != null)
				return notion.getParent();
		}
		else if(element instanceof SystemElementsPackageDTO) {
			SystemElementsPackageDTO sysel = (SystemElementsPackageDTO)element;
			if(sysel.getParent() == null && sysel.getDomainSpecificationParent() != null)
				return sysel.getDomainSpecificationParent();
			if(sysel.getParent() != null)
				return sysel.getParent();
		}
		else if(element instanceof ActorDTO) {
			ActorDTO act = (ActorDTO)element;
			return act.getParent();
		}
		else if(element instanceof NotionDTO) {
			NotionDTO notion = (NotionDTO)element;
			return notion.getParent();
		}
		else if(element instanceof SystemElementDTO) {
			SystemElementDTO sysel = (SystemElementDTO)element;
			return sysel.getParent();
		}
		else if (element instanceof DomainStatementDTO) {
			DomainStatementDTO ds = (DomainStatementDTO) element;
			return ds.getParent();
		}
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		if(element instanceof DomainSpecificationDTO) {
			DomainSpecificationDTO parent = (DomainSpecificationDTO) element;
			int children = parent.getActorsPackageDTOList().size() 
								+ parent.getNotionsPackageDTOList().size() 
								+ parent.getSystemElementsPackageDTOList().size();
			return children > 0 ? true : false;
		}
		else if (element instanceof ActorsPackageDTO) {
			ActorsPackageDTO parent = (ActorsPackageDTO) element;
			return (parent.getActorsPackageDTOList().size() 
					+ parent.getActorDTOList().size()) > 0 ? true : false; 
		} 
		else if (element instanceof NotionsPackageDTO) {
			NotionsPackageDTO parent = (NotionsPackageDTO) element;
			return (parent.getNotionsPackageDTOList().size() 
					+ parent.getNotionDTOList().size() + getNotionDiagramsFromPackage(parent).length ) > 0 ? true : false; 
		}
		else if (element instanceof SystemElementsPackageDTO) {
			SystemElementsPackageDTO parent = (SystemElementsPackageDTO) element;
			return (parent.getSystemElementsPackageDTOList().size() 
					+ parent.getSystemElementDTOList().size()) > 0 ? true : false;
		}
		else if (element instanceof ActorDTO) {
			return false;
		} 
		else if (element instanceof NotionDTO) {
			NotionDTO not = (NotionDTO)element;
			return not.getDomainStatementDTOList().size() > 0 ? true : false;
		}
		else if (element instanceof SystemElementDTO) {
			return false;
		}
		else if (element instanceof DomainStatementDTO) {
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
		if (element instanceof DomainSpecificationDTO) {
			return Constants.DOMAIN_NAME;
		}
		else if (element instanceof ActorsPackageDTO) {
			ActorsPackageDTO parent = (ActorsPackageDTO) element;
			return parent.getName();
		} 
		else if (element instanceof NotionsPackageDTO) {
			NotionsPackageDTO parent = (NotionsPackageDTO) element;
			return parent.getName();
		}
		else if (element instanceof SystemElementsPackageDTO) {
			SystemElementsPackageDTO parent = (SystemElementsPackageDTO) element;
			return parent.getName();
		}
		else if (element instanceof ActorDTO) {
			ActorDTO parent = (ActorDTO) element;
			return parent.getName();
		} 
		else if (element instanceof NotionDTO) {
			NotionDTO parent = (NotionDTO) element;
			return parent.getName();
		}
		else if (element instanceof SystemElementDTO) {
			SystemElementDTO parent = (SystemElementDTO) element;
			return parent.getName();
		}
		else if (element instanceof DomainStatementDTO) {
			DomainStatementDTO parent = (DomainStatementDTO) element;
			return parent.getName();
		}
		else {
			return "";
		}
	}
	
	@Override
	public Image getImage(Object obj) {
		if (obj instanceof DomainSpecificationDTO) {
			return ImageCache.getImage(IImageKeys.DOMAIN_PACKAGE);
		}
		else if (obj instanceof ActorsPackageDTO) {
			return ImageCache.getImage(IImageKeys.DOMAIN_PACKAGE);
		}
		else if (obj instanceof NotionsPackageDTO) {
			return ImageCache.getImage(IImageKeys.DOMAIN_PACKAGE);
		}
		else if (obj instanceof SystemElementsPackageDTO) {
			return ImageCache.getImage(IImageKeys.DOMAIN_PACKAGE);
		}
		else if (obj instanceof ActorDTO) {
			return ImageCache.getImage(IImageKeys.ACTOR);
		}
		else if (obj instanceof NotionDTO) {
			return ImageCache.getImage(IImageKeys.NOTION);
		}
		else if (obj instanceof SystemElementDTO) {
			return ImageCache.getImage(IImageKeys.SYSTEM_ELEMENT);
		}
		else if (obj instanceof DomainStatementDTO) {
			return ImageCache.getImage(IImageKeys.DOMAIN_STATEMENT);
		}
		return PlatformUI.getWorkbench().getSharedImages().getImage(
				ISharedImages.IMG_OBJ_ELEMENT);
	}

	@Override
	public void addListener(ILabelProviderListener listener) {
		addListenerObject(listener);
		
	}

	@Override
	public boolean isLabelProperty(Object element, String property) {
		return true;
	}

	@Override
	public void removeListener(ILabelProviderListener listener) {
		removeListenerObject(listener);
		
	}

}
