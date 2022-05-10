package eu.redseeds.sc.editor.rsl.editors.domain.controls.views;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import eu.redseeds.sc.editor.rsl.Activator;
import eu.redseeds.sc.editor.rsl.editors.IImageKeys;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.ActionTypesEnum;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.DomainStatementDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionDTO;

public class DomainStatementsLabelProvider extends LabelProvider implements
		ITableLabelProvider {

	
	public DomainStatementsLabelProvider(NotionDTO parentNotion) {
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
		if(!(element instanceof DomainStatementDTO)) {
			return result;
		}
		switch (columnIndex) {
			case 0 :
				result = ((DomainStatementDTO)element).getName();
				break;
			case 1:
				result = ActionTypesEnum.getActionTypeToDisplay((DomainStatementDTO) element);
				break;
			case 2:
				result = "Description --TODO--";
				break;
			default:
				result = "--TODO--";
				break;
		}
		return result;
	}

}
