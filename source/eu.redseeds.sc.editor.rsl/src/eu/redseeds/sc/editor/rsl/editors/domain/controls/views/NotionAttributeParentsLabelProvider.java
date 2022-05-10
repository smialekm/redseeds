package eu.redseeds.sc.editor.rsl.editors.domain.controls.views;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import eu.redseeds.sc.editor.rsl.Activator;
import eu.redseeds.sc.editor.rsl.editors.IImageKeys;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionTypesEnum;

public class NotionAttributeParentsLabelProvider extends LabelProvider implements
		ITableLabelProvider {

	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		if(columnIndex == 0) {
			return Activator.getDefault()
				.createImageDescriptorFor(IImageKeys.NOTION).createImage();
		}
		else {
			return null;
		}
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		String result = "";
		if(!(element instanceof NotionDTO)) {
			return result;
		}
		switch (columnIndex) {
			case 0 :
				result = ((NotionDTO)element).getName();
				break;
			case 1:
				if(!((NotionDTO)element).getType().isEmpty()) {
					result=NotionTypesEnum.getTypeToDisplay((NotionDTO) element);
				}
				else {
					result = NotionTypesEnum.EMPTY;
				}
				break;
			default:
				result = "--TODO--";
				break;
		}
		return result;
	}

}
