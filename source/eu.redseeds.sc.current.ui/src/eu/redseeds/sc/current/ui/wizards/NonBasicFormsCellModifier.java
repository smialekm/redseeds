package eu.redseeds.sc.current.ui.wizards;

import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TableItem;

import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.NounLinkDTO;

public class NonBasicFormsCellModifier implements ICellModifier {
	
	TableViewer viewer;

	public NonBasicFormsCellModifier(Object element, TableViewer formsViewer,
			Composite container) {
		viewer = formsViewer;
	}

	@Override
	public boolean canModify(Object element, String property) {
		return true;
	}

	@Override
	public Object getValue(Object element, String property) {
		return ((NounLinkDTO)element).getNewValue();
	}

	@Override
	public void modify(Object element, String property, Object value) {
		if(value.toString().equals("")) { //do not allow empty values
			return;
		}
		((NounLinkDTO)((TableItem)element).getData()).setNewValue(value.toString());
		viewer.refresh();
	}

}
