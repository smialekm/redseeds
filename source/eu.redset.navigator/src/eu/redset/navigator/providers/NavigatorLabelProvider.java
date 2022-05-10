package eu.redset.navigator.providers;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.graphics.Image;

public class NavigatorLabelProvider extends org.eclipse.jface.viewers.LabelProvider {
	
	@Override
	public String getText(Object element) {
		ILabelProvider provider 
			= AdapterFactory.adapt(element, element.getClass());
		return provider.getText(element);
	}
	
	@Override
	public Image getImage(Object obj) {
		ILabelProvider provider 
			= AdapterFactory.adapt(obj, obj.getClass());
		return provider.getImage(obj);
	}
}
