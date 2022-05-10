package eu.redset.navigator.wizards;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.ui.actions.WorkspaceModifyOperation;

import eu.redseeds.sc.current.repository.SCProject;
import eu.redseeds.sc.current.repository.SCProjectContainer;
import eu.redset.emf.model.tsl.TestCase;
import eu.redset.emf.model.tsl.TestScenario;
import eu.redset.tsl.editors.actions.TestCaseInstantationAction;
import eu.redset.emf.model.tsl.ControlSentence;
import eu.redset.emf.model.tsl.UseCaseTest;


public class UseCaseTestScenarioSelectionOperation extends WorkspaceModifyOperation{

	private UseCaseTest uct;
	private TestScenario testScenario;
	private ControlSentence sentence;
	private String name, description;
	private IProject eclipseProject;
	private UseCaseTestScenarioSelectionWizardPage page;
	private Map<ControlSentence, UseCaseTest> invokesMap;
	
	public UseCaseTestScenarioSelectionOperation(UseCaseTest uct, ControlSentence sentence, TestScenario testScenario, UseCaseTestScenarioSelectionWizardPage page) {
		this.uct = uct;
		this.testScenario = testScenario;
		this.sentence = sentence;
		this.page = page;
		this.eclipseProject = SCProjectContainer.instance().getSCProject(sentence).getEclipseProject();
	}
	
	@Override
	protected void execute(IProgressMonitor monitor) throws CoreException,
			InvocationTargetException, InterruptedException {
		
		// start task
		monitor.beginTask(
				ResourceMessages.NewTestCreationOperation_creating, 2);
		monitor.subTask(ResourceMessages.NewTestCreationOperation_project);
		createTestCase();
		monitor.worked(1);		
	}
	
	private void createTestCase() {
		SCProject scProject = SCProjectContainer.instance().getSCProject(eclipseProject);
		TestCaseInstantationAction instanceAction = new TestCaseInstantationAction(testScenario);
		TestCase tc = instanceAction.createTestCase(sentence, page.getUseCaseTestScenario());
		invokesMap = instanceAction.getInvokesMap();
		scProject.saveTS(sentence);
	}

	public Map<ControlSentence, UseCaseTest> getInvokesMap() {
		return invokesMap;
	}

	
}
