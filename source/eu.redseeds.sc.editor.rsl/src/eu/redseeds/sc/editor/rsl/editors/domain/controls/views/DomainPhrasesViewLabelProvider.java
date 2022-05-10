package eu.redseeds.sc.editor.rsl.editors.domain.controls.views;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.PhraseDTO;

public class DomainPhrasesViewLabelProvider extends LabelProvider implements
		ITableLabelProvider {

	
	public DomainPhrasesViewLabelProvider() {
		super();
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		String result = "";
		if(!(element instanceof PhraseDTO)) {
			return result;
		}
		switch (columnIndex) {
			case 0 :
				result = "---";
				break;
			case 1:
				result = ((PhraseDTO)element).toString();
				break;
			default:
				result = "--TODO--";
				break;
		}
		return result;
	}

	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}
	

}

