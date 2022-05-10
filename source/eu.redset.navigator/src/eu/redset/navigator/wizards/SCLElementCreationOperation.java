package eu.redset.navigator.wizards;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.ui.actions.WorkspaceModifyOperation;

import eu.redseeds.common.SCProjectHelper;
import eu.redseeds.sc.current.repository.SCProject;
import eu.redseeds.sc.current.repository.SCProjectContainer;
import eu.redseeds.scl.SCLGraph;
import eu.redseeds.scl.impl.rsl.rsldomainelements.terms.NounImpl;
import eu.redset.emf.model.tsl.TSLBusinessLayerFacade;
import eu.redset.emf.model.tsl.TestCase;
import eu.redset.emf.model.tsl.TestPackage;
import eu.redset.emf.model.tsl.TestScenario;
import eu.redset.emf.model.tsl.TestSpecification;
import eu.redset.emf.model.tsl.UseCaseTestScenario;
import eu.redset.emf.model.tsl.UseCaseTest;
import eu.redset.emf.model.tsl.ControlSentence;
import eu.redset.tsl.editors.actions.TestCaseInstantationAction;


public class SCLElementCreationOperation extends WorkspaceModifyOperation{

	private java.lang.Object parent, type;
	private String name, description;
	private IProject eclipseProject;
	private SCLElementWizardPage page;
	private Map<ControlSentence, UseCaseTest> invokesMap;
	
	public SCLElementCreationOperation(IProject eclipseProject, java.lang.Object parent, java.lang.Object type, SCLElementWizardPage page) {
		this.parent = parent;
		this.type = type;
		this.name = page.getSCLElementName();
		this.description = page.getTSElementDescription();
		this.page = page;
		this.eclipseProject = eclipseProject;
	}
	
	@Override
	protected void execute(IProgressMonitor monitor) throws CoreException,
			InvocationTargetException, InterruptedException {
		
		// start task
		monitor.beginTask(
				ResourceMessages.NewTestCreationOperation_creating, 2);
		monitor.subTask(ResourceMessages.NewTestCreationOperation_project);
		createSCLElement();
		monitor.worked(1);		
	}
	
	private void createSCLElement() {
		
		SCProject scProject = SCProjectContainer.instance().getSCProject(eclipseProject);
		
		if(type == TestPackage.class) {
			
			if (parent instanceof TestSpecification){

			} else if (parent instanceof TestPackage){
				TestPackage tp = scProject.getTestSpecificationContainer().getTSLFacade(parent).createTestPackage((TestPackage)parent, name);
			}
			
		} else if (type == TestScenario.class){
			if (parent instanceof TestPackage){
				TestScenario ts = scProject.getTestSpecificationContainer().getTSLFacade(parent).createTestScenario((TestPackage)parent, name);
				ts.setDescription(this.description);
			}
		} else if (type == TestCase.class){
			if (parent instanceof TestScenario){
				TestCaseInstantationAction tcia = new TestCaseInstantationAction((TestScenario)parent);
				tcia.createTestCase((TestScenario)parent, page.getUseCaseTestScenario());	
				invokesMap = tcia.getInvokesMap();			
			}
		}
	
		scProject.saveTS(parent);
	}

	public Map<ControlSentence, UseCaseTest> getInvokesMap() {
		return invokesMap;
	}

	public java.lang.Object getParent() {
		return parent;
	}

	
	
}
