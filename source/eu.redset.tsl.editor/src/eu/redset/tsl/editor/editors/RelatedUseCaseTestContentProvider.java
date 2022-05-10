package eu.redset.tsl.editor.editors;

import java.util.List;
import java.util.Vector;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

import eu.redset.emf.model.tsl.TestInvocationRelationship;
import eu.redset.emf.model.tsl.UseCaseTest;


public class RelatedUseCaseTestContentProvider implements
		IStructuredContentProvider {

	@Override
	public Object[] getElements(Object inputElement) {
		
		if (inputElement instanceof UseCaseTest) {
			UseCaseTest uct = (UseCaseTest)inputElement;
			List<UseCaseTest> result = new Vector<UseCaseTest>();
			for (TestInvocationRelationship tir : uct.getInvocationSource()){
				result.add(tir.getInvocationTarget());
			}
			//result.addAll(uc.getInvocationRelationshipToList());
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
