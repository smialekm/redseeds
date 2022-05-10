package eu.redseeds.sc.editor.rsl.editors.domain.controls.views;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import eu.redseeds.sc.editor.rsl.Activator;
import eu.redseeds.sc.editor.rsl.editors.IImageKeys;
import eu.redseeds.sc.terminology.model.TermSenseDTO;

public class TermsLabelProvider extends LabelProvider implements
		ITableLabelProvider {

	
	public TermsLabelProvider() {
		super();
	}
	
	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		if(columnIndex == 0) {
			return Activator.getDefault().
			createImageDescriptorFor(IImageKeys.DOMAIN_STATEMENT).createImage();
		}
		else {
			return null;
		}
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		String result = "";
		if(!(element instanceof TermSenseDTO)) {
			return result;
		}
		switch (columnIndex) {
			case 0 :
				result = ((TermSenseDTO)element).getType();
				break;
			case 1:
				result = ((TermSenseDTO)element).getLemma();
				break;
			case 2:
				result = ((TermSenseDTO)element).getSense();
				break;
			default:
				result = "--TODO--";
				break;
		}
		return result;
	}

}
