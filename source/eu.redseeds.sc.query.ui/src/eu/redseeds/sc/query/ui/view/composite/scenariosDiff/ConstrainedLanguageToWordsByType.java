package eu.redseeds.sc.query.ui.view.composite.scenariosDiff;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.constrainedlanguagesentences.ConstrainedLanguageSentenceDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.controlsentences.ConditionSentenceDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.controlsentences.PostconditionSentenceDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.controlsentences.PreconditionSentenceDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.svosentences.SVOSentenceDTO;
/**
 * For svoSentence split by grammar for other (ex: condition) return whole sentence as word
 */
class ConstrainedLanguageToWordsByType implements IConstrainedLanguageToWords{

	private final ISVOSentenceToWords svoSentenceToWords=SVOSentenceToWordsFactory.getSVOSentenceToWordsByGramma();

	@Override
	public String[] splitSentenceToWords(ConstrainedLanguageSentenceDTO sentence) {
		List<String> result=new ArrayList<String>();
		if(sentence!=null){
			if(sentence instanceof SVOSentenceDTO){
				SVOSentenceDTO sentenceDTO = (SVOSentenceDTO) sentence;
				result.addAll(Arrays.asList(svoSentenceToWords.splitSentenceToWords(sentenceDTO)));
			}
			if(sentence instanceof ConditionSentenceDTO){
				ConditionSentenceDTO conditionSentenceDTO = (ConditionSentenceDTO) sentence;
				result.add(ConditionPrefix.CONDITION_PREFIX+conditionSentenceDTO.getSentenceText());
			}
			if(sentence instanceof PostconditionSentenceDTO){
				PostconditionSentenceDTO postconditionSentenceDTO = (PostconditionSentenceDTO) sentence;
				result.add((postconditionSentenceDTO.isSuccess()?ConditionPrefix.END_SUCCESS_PREFIX+postconditionSentenceDTO.getSentenceText():
					ConditionPrefix.END_FAIL_PREFIX+postconditionSentenceDTO.getSentenceText()));
			}
			if(sentence instanceof PreconditionSentenceDTO){
				PreconditionSentenceDTO preconditionSentenceDTO = (PreconditionSentenceDTO) sentence;
				result.add(ConditionPrefix.PRE_COND_PREFIX+preconditionSentenceDTO.getSentenceText());
			}
		}
		return result.toArray(new String[0]);
	}

}
