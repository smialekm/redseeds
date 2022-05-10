package eu.redseeds.engine.navigator.providers;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.model.BaseWorkbenchContentProvider;
import org.eclipse.ui.model.WorkbenchLabelProvider;

import eu.redseeds.common.DiagramFileHelper;

public class FolderFileContentLabelProvider extends BaseWorkbenchContentProvider implements IProvider {
	
//	@Override
//	public Object[] getChildren(Object parentElement) {
//		if(parentElement instanceof IFolder) {
//			IFolder folder = (IFolder)parentElement;
//			System.out.println(folder);
////			try {
//				return new Object[] { folder };
////			} catch (CoreException e) {
////				return null;
////			}
//		}
//		return null;
//	}
//
//	@Override
//	public Object getParent(Object element) {
//		if(element instanceof IResource){
//			IResource resource = (IResource)element;
//			return resource.getParent();
//		}
//			
//		return null;
//	}
//
//	@Override
//	public boolean hasChildren(Object element) {
//		if(element instanceof IFolder) {
//			IFolder folder = (IFolder)element;
//			try {
//				if(folder.members().length > 0) {
//					return true;
//				}
//			} catch (CoreException e) {
//				return false;
//			}
//		}
//		return false;
//	}
//
//	@Override
//	public Object[] getElements(Object inputElement) {
//		return getChildren(inputElement);
//	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		// TODO Auto-generated method stub

	}

	@Override
	public Image getImage(Object element) {
		if(element instanceof IFile && (((IFile)element).getFileExtension().equalsIgnoreCase("usecasediagram_diagram") || ((IFile)element).getFileExtension().equalsIgnoreCase("notiondiagram_diagram")))
			return new WorkbenchLabelProvider().getImage(element);
		return null;
	}

	@Override
	public String getText(Object element) {
		if (element instanceof IResource[]) {
//			IResource[] new_name = (IResource[]) element;
			return "Code for SC";
		}
		else if(element instanceof IFile){
			if(((IFile)element).getFileExtension().equalsIgnoreCase("usecasediagram_diagram")){
				return DiagramFileHelper.getUseCaseDiagramName(((IFile)element).getLocation().toOSString());
			}
			else if(((IFile)element).getFileExtension().equalsIgnoreCase("notiondiagram_diagram")){
				return DiagramFileHelper.getNotionDiagramName(((IFile)element).getLocation().toOSString());
			}
			else if(((IFile)element).getFileExtension().equalsIgnoreCase("java")){
				return ((IFile)element).getName();
			}
		}
		else if(element instanceof IFolder){
			return ((IFolder)element).getName();
		}
		return element.toString();
	}

	@Override
	public void addListener(ILabelProviderListener listener) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isLabelProperty(Object element, String property) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void removeListener(ILabelProviderListener listener) {
		// TODO Auto-generated method stub

	}

}
