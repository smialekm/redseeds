package eu.redseeds.sc.editor.rsl.editors.domain.controls.views;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;

import eu.redseeds.sc.terminology.model.RemoteJGWNL;
import eu.redseeds.scl.model.rsl.rsldomainelements.terms.NounDTO;

public class DomainSensesViewLabelProvider extends LabelProvider implements
		ITableLabelProvider {

	
	public DomainSensesViewLabelProvider() {
		super();
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		String result = "";
		if(!(element instanceof NounDTO)) {
			return result;
		}
		switch (columnIndex) {
			case 0 :
				result = ((NounDTO)element).getName();
				break;
			case 1:
				if (RemoteJGWNL.getInstance().getTermSenseDTO(((NounDTO)element).getSynonymUid()) == null){
					result = "---";
				} else{
					result = RemoteJGWNL.getInstance().getTermSenseDTO(((NounDTO)element).getSynonymUid()).getSense();
				}
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
	
	public Color getBackground(Object element) {
		if (element instanceof NounDTO){
			if (RemoteJGWNL.getInstance().getTermSenseDTO(((NounDTO)element).getSynonymUid()) == null)
				return new Color(null, 200, 200, 255);
		}
		
		return new Color(null, 255, 255, 255);
	}

}

