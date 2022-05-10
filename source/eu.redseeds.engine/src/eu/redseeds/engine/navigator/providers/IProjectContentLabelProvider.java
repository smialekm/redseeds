package eu.redseeds.engine.navigator.providers;

import org.eclipse.core.commands.common.EventManager;
import org.eclipse.core.resources.IProject;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.graphics.Image;

import eu.redseeds.engine.Activator;
import eu.redseeds.sc.current.repository.SCProject;
import eu.redseeds.sc.current.repository.interfaces.ICurrRepoContent;
import eu.redseeds.scl.model.sclkernel.SoftwareCaseDTO;

public class IProjectContentLabelProvider extends EventManager
					implements IProvider  {

	@Override
	public Object[] getChildren(Object parentElement) {
		ICurrRepoContent currRepo 
			= Activator.getDefault().getICurrRepoContentInstance();

		if (parentElement instanceof IProject) {
			IProject eclipseProject = (IProject) parentElement;
			SCProject project = currRepo.getSCProject(eclipseProject);
			SoftwareCaseDTO sc = project.getMainCase();
			Object[] clipboards = project.getClipboardList().toArray();
			Object[] result = new Object[clipboards.length + 1];
			result[0] = sc;
			System.arraycopy(clipboards, 0, result, 1, clipboards.length);
			return result;
//			return new Object[] { sc };
		}
		return new Object[0];
	}

	@Override
	public Object getParent(Object element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		IProject eclipseProject = (IProject) element;
		if(!eclipseProject.isOpen()) {
			return false;
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
	
	//--------------
	//label provider
	//--------------
	
	@Override
	public Image getImage(Object element) {
		return null;
	}
	
	/* (non-Javadoc)
	 * just do toString for now
	 * @see org.eclipse.jface.viewers.ILabelProvider#getText(java.lang.Object)
	 */
	@Override
	public String getText(Object element) {
		return element.toString();
	}
	
	@Override
	public void addListener(ILabelProviderListener listener) {
		addListenerObject(listener);
	}
	
	@Override
	public void removeListener(ILabelProviderListener listener) {
		removeListener(listener);
		
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
