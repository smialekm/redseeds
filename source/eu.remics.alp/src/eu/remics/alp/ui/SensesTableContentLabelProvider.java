package eu.remics.alp.ui;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.graphics.Image;

import eu.redseeds.engine.navigator.providers.AdapterFactory;

/**
 * Content and label provider for senses assignment page of the ALP import wizard
 * @author aambroziewicz
 *
 */
public class SensesTableContentLabelProvider implements IStructuredContentProvider, ITableLabelProvider {

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		// TODO Auto-generated method stub

	}

	@Override
	public Object[] getElements(Object inputElement) {
		//TODO
		if(inputElement instanceof Object[]) {
			return (Object[])inputElement;
		}
		return new Object[0];
	}

	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		if(columnIndex == 0) { //first column
			if(element instanceof Object[]) {
				Object[] obj = (Object[])element;
				if(columnIndex == 0) {
					return AdapterFactory.adapt(obj[0], null).getImage(obj[0]);
				}
			}
		}
		return null;
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		//TODO
		if(columnIndex == 0) { //first column
			if(element instanceof Object[]) {
				return ((Object[])element)[1].toString();
			}
		}
		if(columnIndex == 1) { //second column
			if(element instanceof Object[]) {
				return ((Object[])element)[2].toString();
			}
		}
		return null;
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
