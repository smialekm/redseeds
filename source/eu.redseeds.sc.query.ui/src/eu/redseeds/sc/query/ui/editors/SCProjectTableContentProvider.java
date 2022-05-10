package eu.redseeds.sc.query.ui.editors;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.graphics.Image;

import eu.redseeds.sc.current.repository.SCProject;
import eu.redseeds.sc.current.repository.SCProjectContainer;


public class SCProjectTableContentProvider extends LabelProvider implements IStructuredContentProvider, ITableLabelProvider {
	
	@Override
	public Object[] getElements(Object inputElement) {
		return SCProjectContainer.instance().getSCProjects().toArray();
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	}

	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		return null;
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		String result = "";
		
		if (element instanceof SCProject){
			result = ((SCProject)element).getMainCase().getName();			
		}

		return result;
	}
}