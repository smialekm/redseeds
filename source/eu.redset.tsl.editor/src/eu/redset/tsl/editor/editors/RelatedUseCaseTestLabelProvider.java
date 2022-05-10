package eu.redset.tsl.editor.editors;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import eu.redset.emf.model.tsl.TestInvocationRelationship;
import eu.redset.emf.model.tsl.UseCaseTest;
import eu.redset.tsl.editor.Activator;
import eu.redset.tsl.editor.IImageKeys;


public class RelatedUseCaseTestLabelProvider extends LabelProvider implements
		org.eclipse.jface.viewers.ITableLabelProvider {

	//private Object parentObject;
	
	public RelatedUseCaseTestLabelProvider() {
		super();
		//this.parentObject = parentObject;
	}
	
	
	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		if (columnIndex == 0) {
			return Activator.getDefault().createImageDescriptorFor(
					IImageKeys.USE_CASE_TEST).createImage();
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
		
		if(!(element instanceof UseCaseTest)) {
			
			return result;
		}
		
		//UseCaseTest relationshipSource = ((TestInvocationRelationship)element).getSource();
		UseCaseTest uct = ((UseCaseTest)element);
		
		//if (relationshipSource == null || relationshipTarget == null)
			//return result;
		
		//String direction = "";
		//UseCaseTest uct = relationshipTarget;
		//if ( uc.equals(parentObject) ) {
		//	uc = relationshipTarget;
		//	direction = "target";
		//}
		//else {
		//	direction = "source";
		//}
		

		switch (columnIndex) {
		case 0:
			result = uct.getName();
			break;
		case 1:
			break;
		case 2:
			result = "";
			break;
		default:
			break;
		}

		return result;
		
	}

}
