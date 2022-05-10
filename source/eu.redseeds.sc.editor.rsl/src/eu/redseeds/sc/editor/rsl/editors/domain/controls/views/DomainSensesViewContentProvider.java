package eu.redseeds.sc.editor.rsl.editors.domain.controls.views;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

import eu.redseeds.sc.current.repository.SCProject;

public class DomainSensesViewContentProvider implements IStructuredContentProvider {

	@Override
	public Object[] getElements(Object inputElement) {
		if(inputElement instanceof SCProject) {
			SCProject scProject = (SCProject) inputElement;
			return scProject.getBusinessLayerFacade().getAllNouns().toArray();
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
