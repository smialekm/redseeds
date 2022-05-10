package eu.redset.tsl.editor.editors;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

import eu.redseeds.sc.current.repository.SCProject;
import eu.redseeds.sc.current.repository.SCProjectContainer;
import eu.redset.emf.model.tsl.TestSpecification;
import eu.redset.emf.model.tsl.UseCaseTest;


public class UseCaseTestScenarioChooseContentProvider implements IStructuredContentProvider {

	@Override
	public Object[] getElements(Object inputElement) {
		if(inputElement instanceof TestSpecification) {
			SCProject proj = SCProjectContainer.instance().getSCProject(inputElement);
			return proj.getTestSpecificationContainer().getTSLFacade(inputElement).getAllUseCaseTestScenarios().toArray(); 
		} else if (inputElement instanceof UseCaseTest) {
			return ((UseCaseTest)inputElement).getEReference0().toArray();
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
