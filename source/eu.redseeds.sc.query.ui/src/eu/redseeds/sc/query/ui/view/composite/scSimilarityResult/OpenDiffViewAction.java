/**
 *
 */
package eu.redseeds.sc.query.ui.view.composite.scSimilarityResult;

import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.action.Action;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import eu.redseeds.sc.query.ui.Activator;
import eu.redseeds.sc.query.ui.view.ScenariosDiffView;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.UseCaseDTO;

class OpenDiffViewAction extends Action {

	private UseCaseDTO currentUseCaseDTO;
	private UseCaseDTO pastUseCaseDTO;

	private String currElmPath;
	private String pastElmPath;

	public OpenDiffViewAction() {
		super("diff scenerios");
	}

	@Override
	public void run() {
		IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();

		Assert.isNotNull(currentUseCaseDTO);
		Assert.isNotNull(pastUseCaseDTO);

		try {
			activePage.showView(ScenariosDiffView.VIEW_ID);
			IViewReference diffViewReference = activePage.findViewReference(ScenariosDiffView.VIEW_ID);
			IViewPart view = diffViewReference.getView(true);
			if (view instanceof ScenariosDiffView) {
				ScenariosDiffView scenariosDiffView = (ScenariosDiffView) view;
				scenariosDiffView.setInput(currentUseCaseDTO, pastUseCaseDTO,currElmPath,pastElmPath);
			}
		} catch (PartInitException e) {
			Activator.getDefault().getSimpleExceptionLogger().log(e);
		}

	}

	public void setInput(UseCaseDTO newCurrentUseCaseDTO, UseCaseDTO newPastUseCaseDTO,String currElmPath,String pastElmPath) {

		Assert.isNotNull(newCurrentUseCaseDTO);
		Assert.isNotNull(newPastUseCaseDTO);

		currentUseCaseDTO = newCurrentUseCaseDTO;
		pastUseCaseDTO = newPastUseCaseDTO;
		this.currElmPath=currElmPath;
		this.pastElmPath=pastElmPath;
	}

}