package eu.redseeds.sc.query.ui.view.composite.scenariosDiff;

abstract class DiffMarkedWordFactory {

	public static IDiffMarkedWord getDefault(DiffType diffType, String word){
		return new SimpleDiffMarkedWord(diffType,word);
	}
}
