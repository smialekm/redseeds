package eu.redseeds.engine.navigator.providers;

import org.eclipse.core.commands.common.EventManager;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.graphics.Image;

import eu.redseeds.engine.navigator.IImageKeys;
import eu.redseeds.scl.model.rsl.rsldomainelements.terms.TermDTO;

public class TerminologySimpleContentLabelProvider extends EventManager implements
													IProvider {
	@Override
	public Object[] getChildren(Object parentElement) {
		return new Object[0];
	}

	@Override
	public Object getParent(Object element) {
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		return false;
	}

	@Override
	public Object[] getElements(Object inputElement) {
		return new Object[0];
	}

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
		return ImageCache.getImage(IImageKeys.TERM);
	}

	@Override
	public String getText(Object element) {
		if(element instanceof TermDTO) {
			return ((TermDTO)element).getName();
		}
		return "";
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
