package eu.redseeds.sc.editor.rsl.editors.domain.controls.views;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

import eu.redseeds.scl.model.rsl.rsldomainelements.actors.ActorDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.systemelements.SystemElementDTO;

public class RelatedDomainElementsContentProvider implements IStructuredContentProvider{

	@Override
	public Object[] getElements(Object inputElement) {
		if (inputElement instanceof ActorDTO) {
			ActorDTO actor = (ActorDTO) inputElement;
			return actor.getDomainElementRelationshipDTOList().toArray();
		}
		else if(inputElement instanceof NotionDTO) {
			NotionDTO notion = (NotionDTO) inputElement;
			return notion.getDomainElementRelationshipDTOList().toArray();
		}
		else if(inputElement instanceof SystemElementDTO) {
			SystemElementDTO sysel = (SystemElementDTO) inputElement;
			return sysel.getDomainElementRelationshipDTOList().toArray();
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
//		if (newInput != null)
//            ((ActorDTO) newInput).addChangeListener(viewer);
//        if (oldInput != null)
//            ((ActorDTO) oldInput).removeChangeListener(viewer);
	}

}
