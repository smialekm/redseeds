package eu.redseeds.sc.query.ui.view.composite.scenariosDiff;

import java.util.ArrayList;
import java.util.List;

import eu.redseeds.sc.editor.rsl.editors.sentencewidgets.grammars.SVOSentenceGrammar;
import eu.redseeds.sc.editor.rsl.editors.sentencewidgets.grammars.SVOSentenceGrammar.States;
import eu.redseeds.sc.editor.rsl.editors.sentencewidgets.providers.ISVOSentenceProviderHelper;
import eu.redseeds.sc.editor.rsl.editors.sentencewidgets.providers.SVOSentenceProviderHelperFactory;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.svosentences.SVOSentenceDTO;

class SVOSentenceToWordsByGramma implements ISVOSentenceToWords{

	private SVOSentenceGrammar grammar = new SVOSentenceGrammar();
	private ISVOSentenceProviderHelper sentenceProviderHelper=SVOSentenceProviderHelperFactory.getDefault();

	@Override
	public String[] splitSentenceToWords(SVOSentenceDTO sentenceDTO) {
		List<String> result=new ArrayList<String>();
		List<States> statesList = grammar.getStatesList();
		for (States state : statesList) {
			String word=sentenceProviderHelper.getStateText(sentenceDTO, state);
			if(word!=null){
				result.add(word);
			}
		}
		return result.toArray(new String[0]);
	}

}
