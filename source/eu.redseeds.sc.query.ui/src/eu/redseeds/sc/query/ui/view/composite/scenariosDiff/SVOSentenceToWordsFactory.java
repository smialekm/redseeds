package eu.redseeds.sc.query.ui.view.composite.scenariosDiff;

abstract class SVOSentenceToWordsFactory {

	public static ISVOSentenceToWords getDefault(){
		return getSVOSentenceToWordsByGramma();
	}

	public static ISVOSentenceToWords getSVOSentenceToWordsByRegExp(){
		return new SVOSentenceToWordsByRegExp();
	}

	public static ISVOSentenceToWords getSVOSentenceToWordsBySpaces(){
		return new SVOSentenceToWordsBySpaces();
	}

	public static ISVOSentenceToWords getSVOSentenceToWordsByGramma(){
		return new SVOSentenceToWordsByGramma();
	}
}
