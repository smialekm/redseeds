package eu.redset.navigator.wizards;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IWorkbench;

import eu.redseeds.common.SCProjectHelper;
import eu.redset.emf.model.tsl.ControlSentence;
import eu.redset.emf.model.tsl.TestCase;
import eu.redset.emf.model.tsl.TestScenario;
import eu.redset.emf.model.tsl.UseCaseTest;
import eu.redset.emf.model.tsl.UseCaseTestScenario;
import eu.redset.navigator.Activator;

public class UseCaseTestScenarioSelectionWizard extends Wizard {
	
	private UseCaseTestScenarioSelectionWizardPage pageOne;
	private UseCaseTest uct;
	private ControlSentence sentence;
	private TestScenario scenario;
	private UseCaseTestScenarioSelectionOperation operation;

	public UseCaseTestScenarioSelectionWizard(ControlSentence sentence, UseCaseTest uct, TestScenario scenario){
		super();
		this.uct = uct;
		this.sentence = sentence;
		this.scenario = scenario;
	}
	
	@Override
	public boolean performFinish() {
		try {
			if (sentence != null) {	
				if(uct != null) {
					if (pageOne.getUseCaseTestScenario() != null){
						operation = new UseCaseTestScenarioSelectionOperation(uct, sentence, scenario, pageOne);
						getContainer().run(false, true,	operation);
						//SCProjectHelper.refreshSCNavigator();
					} else {
						sentence.setName("NO "+sentence.getName());
					}
					return true;
				}
				else return false;
			}
		} catch (InvocationTargetException e) {
			Activator.log(e.getMessage(), Status.ERROR); 
		} catch (InterruptedException e) {
			Activator.log(e.getMessage(), Status.ERROR);
		}
		return false;
	}
	
	@Override
	public boolean performCancel() {
        return true;
    }
	
	@Override
	public void addPages() {
		pageOne = new UseCaseTestScenarioSelectionWizardPage(uct, sentence);
		addPage(pageOne);
	}

	public UseCaseTestScenarioSelectionWizardPage getUseCaseTestScenarioSelectionWizardPage() {
		return pageOne;
	}

	public UseCaseTestScenarioSelectionOperation getUseCaseTestScenarioSelectionOperation() {
		return operation;
	}
	
	

}
