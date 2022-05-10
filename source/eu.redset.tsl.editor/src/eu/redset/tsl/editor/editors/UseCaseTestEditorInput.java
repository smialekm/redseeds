package eu.redset.tsl.editor.editors;


import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

import eu.redset.emf.model.tsl.UseCaseTest;
import eu.redset.emf.model.tsl.util.TSLModelHelper;


public class UseCaseTestEditorInput implements IEditorInput {


	private UseCaseTest useCaseTest;
	
	public UseCaseTestEditorInput() {
		super();
	}
	
	@Override
	public boolean exists() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ImageDescriptor getImageDescriptor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		if (useCaseTest != null){
			if (TSLModelHelper.instance().getName(useCaseTest.eContainer()) != null) {
				return useCaseTest.getName();
			} 
			return "Default Use Case Test";
		}
		return "Default Use Case Test";
	}

	@Override
	public IPersistableElement getPersistable() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getToolTipText() {
		if (useCaseTest != null){
			if (TSLModelHelper.instance().getName(useCaseTest.eContainer()) != null) {
				return TSLModelHelper.instance().getName(useCaseTest.eContainer());
			} 
			return "Default Use Case Test";
		}
		return "Default Use Case Test";
	}

	@Override
	public Object getAdapter(Class adapter) {
		// TODO Auto-generated method stub
		return null;
	}

	public UseCaseTest getUseCaseTest() {
		return useCaseTest;
	}

	public void setUseCaseTest(UseCaseTest useCaseTest) {
		this.useCaseTest = useCaseTest;
	}

	@Override
	public boolean equals(Object input){
		if (! (input instanceof UseCaseTestEditorInput)){
			return false;
		}
		if (this.useCaseTest.equals(((UseCaseTestEditorInput)input).getUseCaseTest())){
			return true;
		}
		return false;
	}
	
}
