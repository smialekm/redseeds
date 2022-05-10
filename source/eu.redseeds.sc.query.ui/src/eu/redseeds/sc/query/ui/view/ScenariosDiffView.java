package eu.redseeds.sc.query.ui.view;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import eu.redseeds.sc.query.ui.view.composite.scenariosDiff.ScenariosDiffContentProviderFactory;
import eu.redseeds.sc.query.ui.view.composite.scenariosDiff.ScenariosDiffViewComposite;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.UseCaseDTO;

public class ScenariosDiffView extends ViewPart {

	public static String VIEW_ID="eu.redseeds.sc.query.ui.view.ScenariosDiffView";

	private ScenariosDiffViewComposite scenariosDiffViewComposite;

	public ScenariosDiffView() {
	}

	@Override
	public void createPartControl(Composite parent) {
		scenariosDiffViewComposite=new ScenariosDiffViewComposite(parent, this);
	}

	@Override
	public void setFocus() {
	}

	public void setInput(UseCaseDTO currentUseCaseDTO,UseCaseDTO pastUseCaseDTO,String currElmPath,String pastElmPath){
		scenariosDiffViewComposite.setScenariosDiffContentProvider(ScenariosDiffContentProviderFactory.getDefault(currentUseCaseDTO, pastUseCaseDTO,currElmPath,pastElmPath));
	}

}
