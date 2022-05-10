package eu.redset.tsl.editor.editors;


import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

import eu.redset.emf.model.tsl.TestScenario;
import eu.redset.emf.model.tsl.util.TSLModelHelper;


public class TestScenarioEditorInput implements IEditorInput {


	private TestScenario testScenario;
	
	public TestScenarioEditorInput() {
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
		if (testScenario != null){
			if (TSLModelHelper.instance().getName(testScenario.eContainer()) != null) {
				return testScenario.getName();
			} 
			return "Default Test Scenario";
		}
		return "Default Test Scenario";
	}

	@Override
	public IPersistableElement getPersistable() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getToolTipText() {
		if (testScenario != null){
			if (TSLModelHelper.instance().getName(testScenario.eContainer()) != null) {
				return TSLModelHelper.instance().getName(testScenario.eContainer());
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

	public TestScenario getTestScenario() {
		return testScenario;
	}

	public void setTestScenario(TestScenario testScenario) {
		this.testScenario = testScenario;
	}

	@Override
	public boolean equals(Object input){
		if (! (input instanceof TestScenarioEditorInput)){
			return false;
		}
		if (this.testScenario.equals(((TestScenarioEditorInput)input).getTestScenario())){
			return true;
		}
		return false;
	}
	
}
