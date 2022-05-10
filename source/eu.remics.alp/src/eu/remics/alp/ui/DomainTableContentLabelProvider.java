package eu.remics.alp.ui;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.graphics.Image;

import eu.remics.alp.ImportManager;
import eu.remics.alp.PatternSlice;
import eu.redseeds.engine.navigator.providers.AdapterFactory;

/**
 * Content and label provider for domain instantiation page of the ALP import wizard
 * @author aambroziewicz
 *
 */
public class DomainTableContentLabelProvider extends LabelProvider implements IStructuredContentProvider, ITableLabelProvider {
	
	protected ImportALPWizard wizard;

	public DomainTableContentLabelProvider(ImportALPWizard wizard) {
		super();
		this.wizard = wizard;
	}

	@Override
	public void dispose() {
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	}

	@Override
	public Object[] getElements(Object inputElement) {
		if (inputElement instanceof PatternSlice) {
			PatternSlice pSlice = (PatternSlice)inputElement;
			
			//process slice
			List<Object> domainElements = ImportManager.getDomainElementsForSlice(pSlice);
			Object[] result = domainElements.toArray();
			
			List<Object[]> resultList 
				= new ArrayList<Object[]>(domainElements.size());
			for(int i = 0; i < domainElements.size(); i++) {
				Object[] elemPair = new Object[] { result[i], "" };
				resultList.add(i, elemPair);
			}
			return resultList.toArray();
		}
		return null;
	}

	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		if(element instanceof Object[]) {
			Object[] obj = (Object[])element;
			if(columnIndex == 0) {
				return AdapterFactory.adapt(obj[0], null).getImage(obj[0]);
			}
		}
		return null;
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		Object[] obj = (Object[])element;
		if(columnIndex == 0) {
			return AdapterFactory.adapt(obj[0], null).getText(obj[0]);
		}
		if(columnIndex == 1) {
			return (String)obj[1];
		}
		return null;
	}

}
