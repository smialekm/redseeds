package eu.redseeds.sc.query.ui.editors;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.graphics.Image;
import eu.redseeds.engine.navigator.providers.AdapterFactory;
import eu.redseeds.engine.navigator.providers.IProvider;
import eu.redseeds.scl.model.sclkernel.SoftwareCaseArtifact;

public class TreeViewContentProvider implements IStructuredContentProvider,
		ITreeContentProvider, IProvider {
	private SoftwareCaseArtifact invisibleRoot;

	public TreeViewContentProvider(
			SoftwareCaseArtifact model) {
		super();
		invisibleRoot = model;
	}

	public Object[] getChildren(Object parent) {

		ITreeContentProvider provider = AdapterFactory.adapt(parent, null);
		return provider.getChildren(parent);
		
	}

	public void inputChanged(Viewer v, Object oldInput, Object newInput) {
	}

	public void dispose() {
	}

	public Object[] getElements(Object parent) {

		if (invisibleRoot != null) {
			return getChildren(invisibleRoot);
		}
		return new Object[0];

	}

	public Object getParent(Object child) {
		ITreeContentProvider provider = AdapterFactory.adapt(child, null);
		return provider.getParent(child);
	}

	@Override
	public boolean hasChildren(Object element) {
		ITreeContentProvider provider = AdapterFactory.adapt(element, null);
		return provider.hasChildren(element);
	}

	@Override
	public String getText(Object element) {

		IProvider provider = AdapterFactory.adapt(element, null);
		return provider.getText(element);
	}

	@Override
	public Image getImage(Object obj) {
		IProvider provider = AdapterFactory.adapt(obj, null);
		return provider.getImage(obj);
	}

	/* (non-Javadoc)
	 * return true
	 * @see org.eclipse.jface.viewers.IBaseLabelProvider#isLabelProperty(java.lang.Object, java.lang.String)
	 */
	@Override
	public boolean isLabelProperty(Object element, String property) {
		return true;
	}

	@Override
	public void addListener(ILabelProviderListener listener) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeListener(ILabelProviderListener listener) {
		// TODO Auto-generated method stub

	}

}