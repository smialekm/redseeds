package eu.redseeds.sc.query.ui.view.composite.scenariosDiff;

abstract class ScenariosDiffStrategyFactory {
	public static IScenariosDiffStrategy getDefaultStrategy(){
		return new ForwardScenariosDiffStrategy();
	}
}
