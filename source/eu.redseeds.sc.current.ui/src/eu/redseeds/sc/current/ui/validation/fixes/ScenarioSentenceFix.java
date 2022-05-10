package eu.redseeds.sc.current.ui.validation.fixes;

import org.eclipse.core.runtime.Status;
import org.eclipse.ui.IMarkerResolution;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import eu.redseeds.sc.current.repository.SCProject;
import eu.redseeds.sc.current.ui.Activator;
import eu.redseeds.sc.current.ui.validation.IValidate;
import eu.redseeds.sc.editor.rsl.editors.UseCaseEditor;
import eu.redseeds.sc.editor.rsl.editors.UseCaseEditorInput;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentations.descriptiverequirementrepresentations.ConstrainedLanguageScenarioDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.UseCaseDTO;

public abstract class ScenarioSentenceFix implements IMarkerResolution {
	
	//TODO: possible problem when changed somewhere else
	protected static String ID_USECASE_EDITOR 
		= "eu.redseeds.sc.editor.rsl.editors.UseCaseEditor";

	@Override
	public String getLabel() {
		return IValidate.FIX_MSG_UC_SCENARIOS;
	}
	
	/**
	 * Opens UseCase editor with given scenario tab activated
	 * @param scenario
	 * @param scProject
	 * @return false if opening was not successful
	 */
	protected boolean openScenarioEditor(ConstrainedLanguageScenarioDTO scenario, SCProject scProject) {
		if(scenario != null && scProject != null) {
			
			UseCaseEditorInput useCaseInput = new UseCaseEditorInput();
			UseCaseDTO useCase = scenario.getParent();
			useCaseInput.setUseCaseDTO(useCase);	
			IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
			UseCaseEditor useCaseEditor = null;
			try {
				useCaseEditor = (UseCaseEditor)activePage.openEditor(useCaseInput, ID_USECASE_EDITOR, false, 1);
			} catch (PartInitException e) {
				Activator.log("Error occured during resolving validation problem: " + e.getMessage(), Status.WARNING);
			}
			if(useCaseEditor != null) {
				useCaseEditor.setFacade(scProject.getBusinessLayerFacade());
				useCaseEditor.setScProject(scProject);
				useCaseEditor.setUseCase(useCase);
				useCaseEditor.setTab(scenario);
				return true;
			}
			return false;
		}
		return false;
	}

}
