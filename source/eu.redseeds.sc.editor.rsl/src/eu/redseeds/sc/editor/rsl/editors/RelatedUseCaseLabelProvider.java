package eu.redseeds.sc.editor.rsl.editors;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import eu.redseeds.sc.editor.rsl.Activator;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.UseCaseDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.usecaserelationships.InvocationRelationshipDTO;

public class RelatedUseCaseLabelProvider extends LabelProvider implements
		org.eclipse.jface.viewers.ITableLabelProvider {

	private Object parentObject;
	
	public RelatedUseCaseLabelProvider(Object parentObject) {
		super();
		this.parentObject = parentObject;
	}
	
	
	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		if (columnIndex == 0) {
			return Activator.getDefault().createImageDescriptorFor(
					IImageKeys.USE_CASE).createImage();
		} else {
			return null;
		}
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		
		/*
		if (element instanceof Entry) {
			Entry<UseCaseDTO, NonInvocationRelationshipDTO> entry = (Entry<UseCaseDTO, NonInvocationRelationshipDTO>) element;

			switch (columnIndex) {
			case 0:
				return entry.getKey().getName();
			case 1:
				return entry.getValue().toString();
			}

		}

		return null;
		*/
		
		String result = "";
		
		if (element == null)
			return result;
		
		if(!(element instanceof InvocationRelationshipDTO)) {
			return result;
		}
		
		UseCaseDTO relationshipSource = ((InvocationRelationshipDTO)element).getSource();
		UseCaseDTO relationshipTarget = ((InvocationRelationshipDTO)element).getTarget();
		
		if (relationshipSource == null || relationshipTarget == null)
			return result;
		
		String direction = "";
		UseCaseDTO uc = relationshipSource;
		if ( uc.equals(parentObject) ) {
			uc = relationshipTarget;
			direction = "target";
		}
		else {
			direction = "source";
		}
		

		switch (columnIndex) {
		case 0:
			result = uc.getName();
			break;
		case 1:
			break;
		case 2:
			result = direction;
			break;
		default:
			break;
		}

		return result;
		
	}

}
