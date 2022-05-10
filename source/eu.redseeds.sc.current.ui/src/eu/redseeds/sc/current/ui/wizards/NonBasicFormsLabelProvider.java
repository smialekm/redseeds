package eu.redseeds.sc.current.ui.wizards;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.NounLinkDTO;

public class NonBasicFormsLabelProvider extends LabelProvider implements ITableLabelProvider{

	public NonBasicFormsLabelProvider(Object element) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		if (element instanceof NounLinkDTO) {
			NounLinkDTO link = (NounLinkDTO) element;
			if(columnIndex == 0) {
				return link.getValue();
			}
			if(columnIndex == 1) {
				return link.getNewValue();
			}
		}
		return null;
	}

}
