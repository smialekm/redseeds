package eu.redseeds.sc.editor.rsl.editors.domain.controls.views;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

import eu.redseeds.sc.current.repository.SCProject;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.PhraseDTO;

public class DomainPhrasesViewContentProvider implements IStructuredContentProvider {

	@Override
	public Object[] getElements(Object inputElement) {
		if(inputElement instanceof SCProject) {
			SCProject scProject = (SCProject) inputElement;
			List<PhraseDTO> result = new ArrayList<PhraseDTO>();
			for (PhraseDTO ph : scProject.getBusinessLayerFacade().getAllPhrases()){
				boolean duplicated = false;
				for (PhraseDTO p : result){
					if (p.equals(ph))
						duplicated = true;
				}
				if (!duplicated)
					result.add(ph);
			}
			return result.toArray();
		}
		return new Object[0];
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
