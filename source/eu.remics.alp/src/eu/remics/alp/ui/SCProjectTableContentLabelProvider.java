package eu.remics.alp.ui;

import java.util.ArrayList;

import java.util.Collection;
import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.graphics.Image;

import eu.redseeds.sc.current.repository.SCProject;
import eu.redseeds.sc.current.repository.SCProjectContainer;

/**
 * Content and label provider for pattern selection of the ALP import wizard (manages SCProjects data)
 * @author aambroziewicz
 *
 */
public class SCProjectTableContentLabelProvider extends LabelProvider implements IStructuredContentProvider, ITableLabelProvider {
	
	@Override
	public Object[] getElements(Object inputElement) {
		//input - current SC project
		Collection<SCProject> projects = SCProjectContainer.instance().getSCProjects();
		List<Object> result = new ArrayList<Object>();
		for(SCProject project : projects) {
			if(project != inputElement) {
				result.add(project);
			}
		}
		return result.toArray();
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