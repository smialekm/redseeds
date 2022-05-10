package eu.redseeds.sc.query.ui.view.composite.scenariosDiff;

import java.util.List;

import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.constrainedlanguagesentences.ConstrainedLanguageSentenceDTO;

interface IDiffMarkedConstrainedLanguageSentence {
	public ConstrainedLanguageSentenceDTO getConstrainedLanguageSentenceDTO();
	public List<IDiffMarkedWord> getDiffMarkedWords();
	public String getWordsAsStrng();
	public void setDiffMarkedWords(List<IDiffMarkedWord> diffMarkedWords);
	public void addDiffMarkedWord(IDiffMarkedWord diffMarkedWord);
}
