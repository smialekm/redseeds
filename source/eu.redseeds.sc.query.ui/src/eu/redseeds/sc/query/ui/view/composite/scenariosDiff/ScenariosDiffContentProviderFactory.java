package eu.redseeds.sc.query.ui.view.composite.scenariosDiff;

import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.UseCaseDTO;

public abstract class ScenariosDiffContentProviderFactory {
	public static IScenariosDiffContentProvider getDefault(UseCaseDTO currentUseCaseDTO, UseCaseDTO pastUseCaseDTO,String currElmPath,String pastElmPath){
		return new ScenariosDiffContentProvider(currentUseCaseDTO,pastUseCaseDTO,currElmPath,pastElmPath);
	}
}
