package eu.redseeds.engine.navigator.providers;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class NavigatorContentProvider implements ITreeContentProvider {

	@Override
	public Object[] getChildren(Object parentElement) {
		ITreeContentProvider provider 
			= AdapterFactory.adapt(parentElement, parentElement.getClass());
		return provider.getChildren(parentElement);
	}

	@Override
	public Object getParent(Object element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		ITreeContentProvider provider 
			= AdapterFactory.adapt(element, element.getClass());
		return provider.hasChildren(element);
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

}
