package eu.redseeds.sc.query.ui.view.composite.scenariosDiff;

abstract class DiffScenariosResultFactory {

	public static IDiffScenariosResult getDefault(){
		return new SimpleDiffScenariosResult();
	}
}
