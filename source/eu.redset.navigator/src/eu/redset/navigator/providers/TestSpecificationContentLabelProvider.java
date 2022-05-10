package eu.redset.navigator.providers;

import org.eclipse.core.commands.common.EventManager;
import org.eclipse.core.resources.IFolder;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

import eu.redseeds.common.Constants;
import eu.redseeds.sc.current.repository.SCProjectContainer;
import eu.redset.emf.model.tsl.TestSpecification;


public class TestSpecificationContentLabelProvider extends EventManager implements
		IProvider {

	@Override
	public Object[] getChildren(Object parentElement) {
		if (parentElement instanceof TestSpecification) {
			TestSpecification ts = (TestSpecification) parentElement;
			Object[] children = ts.getEReference0().toArray();
			return children;
		}
		return new Object[0];
	}

	@Override
	public Object getParent(Object element) {
		if (element instanceof TestSpecification) {
			TestSpecification ts = (TestSpecification) element;
			return SCProjectContainer.instance().getSCProject(ts).getEclipseProject();
		}
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		if (element instanceof TestSpecification) {
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
		if (element instanceof TestSpecification) {
			TestSpecification parent = (TestSpecification) element;
			return parent.getName();
		}   
		else {
			return "";
		}
	}

	@Override
	public Image getImage(Object obj) {
		if (obj instanceof TestSpecification) {
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
