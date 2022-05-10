package eu.redseeds.sc.editor.rsl.editors.domain.controls.views;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import eu.redseeds.sc.editor.rsl.Activator;
import eu.redseeds.sc.editor.rsl.editors.IImageKeys;
import eu.redseeds.scl.model.rsl.rsldomainelements.actors.ActorDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.domainelements.DomainElementRelationshipDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.systemelements.SystemElementDTO;

public class RelatedDomainElementsLabelProvider extends LabelProvider implements
		ITableLabelProvider {
	
	private Object parentObject;
	
	public RelatedDomainElementsLabelProvider(Object parentObject) {
		super();
		this.parentObject = parentObject;
	}

	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		if(!(element instanceof DomainElementRelationshipDTO)) {
			return null;
		}
		Object relatedElem 
			= RelatedDomainElementsList.getElementToDisplay((
					DomainElementRelationshipDTO)element,
					this.parentObject);
		if(columnIndex == 0) {
			if (relatedElem instanceof ActorDTO) {
				return Activator.getDefault().
					createImageDescriptorFor(IImageKeys.ACTOR).createImage();
			}
			else if (relatedElem instanceof NotionDTO) {
				return Activator.getDefault().
				createImageDescriptorFor(IImageKeys.NOTION).createImage();
			}
			else if (relatedElem instanceof SystemElementDTO) {
				return Activator.getDefault().
				createImageDescriptorFor(IImageKeys.SYSTEM_ELEMENT).createImage();
			}
			else {
				return null;
			}
		}
		return null;
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		String result = "";
		if(!(element instanceof DomainElementRelationshipDTO)) {
			return result;
		}
		Object relatedElem 
			= RelatedDomainElementsList.getElementToDisplay((
					DomainElementRelationshipDTO)element, 
					this.parentObject);
		if (relatedElem instanceof ActorDTO) {
			switch (columnIndex) {
				case 0 :
					result = ((ActorDTO)relatedElem).getName();
					break;
	//			case 1:
	//				result = "--TODO--";
	//				break;
				case 1:
					result 
						= RelatedDomainElementsList.getThisMultiplicityToDisplay((
								DomainElementRelationshipDTO)element, 
								this.parentObject);
					break;
				case 2:
					result 
						= RelatedDomainElementsList.getOtherMultiplicityToDisplay((
								DomainElementRelationshipDTO)element, 
								this.parentObject);
					break;
				case 3:
					result	= String.valueOf(
							((DomainElementRelationshipDTO)element).isDirected());
					break;
				default:
					break;
			}
		}
		else if (relatedElem instanceof NotionDTO) {
			switch (columnIndex) {
			case 0:
				result = ((NotionDTO)relatedElem).getName();
				break;
//			case 1:
//				result = "--TODO--";
//				break;
			case 1:
				result 
					= RelatedDomainElementsList.getThisMultiplicityToDisplay((
							DomainElementRelationshipDTO)element, 
							this.parentObject);
				break;
			case 2:
				result 
					= RelatedDomainElementsList.getOtherMultiplicityToDisplay((
							DomainElementRelationshipDTO)element, 
							this.parentObject);
				break;
			case 3:
				result	= String.valueOf(
						((DomainElementRelationshipDTO)element).isDirected());
				break;
			default:
				break;
			}
		}
		else if (relatedElem instanceof SystemElementDTO) {
			switch (columnIndex) {
			case 0:
				result = ((SystemElementDTO)relatedElem).getName();
				break;
//			case 1:
//				result = "--TODO--";
//				break;
			case 1:
				result 
					= RelatedDomainElementsList.getThisMultiplicityToDisplay((
							DomainElementRelationshipDTO)element, 
							this.parentObject);
				break;
			case 2:
				result 
					= RelatedDomainElementsList.getOtherMultiplicityToDisplay((
							DomainElementRelationshipDTO)element, 
							this.parentObject);
				break;
			case 3:
				result	= String.valueOf(
						((DomainElementRelationshipDTO)element).isDirected());
				break;
			default:
				break;
			}
		}
		return result;
	}
	
}
