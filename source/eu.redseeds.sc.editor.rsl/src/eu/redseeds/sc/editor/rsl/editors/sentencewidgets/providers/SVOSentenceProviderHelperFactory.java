package eu.redseeds.sc.editor.rsl.editors.sentencewidgets.providers;


public class SVOSentenceProviderHelperFactory {

	private static final ISVOSentenceProviderHelper defaultSVOSentenceProviderHelper=new SVOSentenceProviderHelper();

	private SVOSentenceProviderHelperFactory() {
	}

	public static ISVOSentenceProviderHelper getDefault(){
		return defaultSVOSentenceProviderHelper;
	}
}
