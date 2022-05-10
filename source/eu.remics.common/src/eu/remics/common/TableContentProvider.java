package eu.remics.common;

import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentations.descriptiverequirementrepresentations.ConstrainedLanguageScenarioDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.UseCaseDTO;

public class TableContentProvider implements IStructuredContentProvider{

	@Override
	public void dispose() {
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	}

	@Override
	public Object[] getElements(Object inputElement) {
		if (inputElement instanceof NotionDTO) {
			return ((NotionDTO)inputElement).getNotionDTOAttributeList().toArray();
		}
		else if(inputElement instanceof UseCaseDTO){
			return ((UseCaseDTO)inputElement).getConstrainedLanguageScenarioDTOList().toArray();
		}
		else if(inputElement instanceof ConstrainedLanguageScenarioDTO){
			return ((ConstrainedLanguageScenarioDTO)inputElement).getScenarioSentenceList().toArray();
		}
		else if(inputElement instanceof List<?>){
			return ((List<?>)inputElement).toArray();
		}
		return new Object[0];
	}

}
