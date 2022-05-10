package eu.redseeds.sc.editor.rsl.editors;

import java.util.List;
import java.util.Vector;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.UseCaseDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.usecaserelationships.InvocationRelationshipDTO;

public class RelatedUseCaseContentProvider implements
		IStructuredContentProvider {

	@Override
	public Object[] getElements(Object inputElement) {
		
		if (inputElement instanceof UseCaseDTO) {
			UseCaseDTO uc = (UseCaseDTO)inputElement;
			List<InvocationRelationshipDTO> result = new Vector<InvocationRelationshipDTO>();
			result.addAll(uc.getInvocationRelationshipFromList());
			result.addAll(uc.getInvocationRelationshipToList());
			return result.toArray();
		}
		else {
			throw new IllegalArgumentException(
					"Wrong argument. Expecting UseCaseDTO, got "
							+ inputElement.toString());
		}
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		// TODO Auto-generated method stub

	}

}
