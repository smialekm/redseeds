package eu.redseeds.sc.editor.rsl.editors;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import eu.redseeds.sc.editor.rsl.Activator;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsrelationships.NonInvocationRelationshipDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.UseCaseDTO;
import eu.remics.common.RecoveryManagerHelper;

public class RelatedRequirementsLabelProvider extends LabelProvider implements
		ITableLabelProvider {

	private Object parentObject;
	
	public RelatedRequirementsLabelProvider(Object parentObject) {
		super();
		this.parentObject = parentObject;
	}
	
	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		
		if(!(element instanceof NonInvocationRelationshipDTO)) {
			return null;
		}
		
		RequirementDTO req = ((NonInvocationRelationshipDTO)element).getSource();
		if (req == null)
			return null;
		
		if ( req.equals(parentObject) )
			req = ((NonInvocationRelationshipDTO)element).getTarget();
		
		if(columnIndex == 0) {
			if (req instanceof UseCaseDTO) {
				return Activator.getDefault().
					createImageDescriptorFor(IImageKeys.USE_CASE).createImage();
			}
			else if (req instanceof RequirementDTO) {
				return Activator.getDefault().
				createImageDescriptorFor(IImageKeys.REQUIREMENT).createImage();
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
		
		if (element == null)
			return result;
		
		if(!(element instanceof NonInvocationRelationshipDTO)) {
			return result;
		}
		
		String direction = "";
		RequirementDTO req = ((NonInvocationRelationshipDTO)element).getSource();

		if (req == null)
			return result;
		if ( req.equals(parentObject) ) {
			req = ((NonInvocationRelationshipDTO)element).getTarget();
			if ( req.equals(parentObject) ) {
				RecoveryManagerHelper.incrementCounter();
				int labelProviderClassInvocationsCount = RecoveryManagerHelper.getCounter();
				if(labelProviderClassInvocationsCount <= 3)
					direction = "source";
				else
					direction = "target";
				if(labelProviderClassInvocationsCount == 6)
					RecoveryManagerHelper.resetCounter();
			}
			else{
				direction = "target";
			}
		}
		else {
			direction = "source";
		}
				

		switch (columnIndex) {
		case 0:
			result = req.getName();
			break;
		case 1:
			if (((NonInvocationRelationshipDTO)element).getRelationshipType() == NonInvocationRelationshipDTO.CONFLICTS_WITH)
				result = "<<Conflicts with>>";
			else if (((NonInvocationRelationshipDTO)element).getRelationshipType() == NonInvocationRelationshipDTO.CONSTRAINS)
				result = "<<Constrains>>";
			else if (((NonInvocationRelationshipDTO)element).getRelationshipType() == NonInvocationRelationshipDTO.DEPENDS_ON)
				result = "<<Depends on>>";
			else if (((NonInvocationRelationshipDTO)element).getRelationshipType() == NonInvocationRelationshipDTO.DERIVES_FROM)
				result = "<<Derives from>>";
			else if (((NonInvocationRelationshipDTO)element).getRelationshipType() == NonInvocationRelationshipDTO.ELABORATES)
				result = "<<Elaborates>>";
			else if (((NonInvocationRelationshipDTO)element).getRelationshipType() == NonInvocationRelationshipDTO.FULFILS)
				result = "<<Fulfils>>";
			else if (((NonInvocationRelationshipDTO)element).getRelationshipType() == NonInvocationRelationshipDTO.IS_SIMILAR_TO)
				result = "<<Is similar to>>";
			else if (((NonInvocationRelationshipDTO)element).getRelationshipType() == NonInvocationRelationshipDTO.MAKES_POSSIBLE)
				result = "<<Makes possible>>";
			else if (((NonInvocationRelationshipDTO)element).getRelationshipType() == NonInvocationRelationshipDTO.OPERATIONALIZES)
				result = "<<Operationalizes>>";
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
