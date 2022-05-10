package eu.redseeds.sc.query.ui.view.composite.scenariosDiff;

import java.util.ArrayList;
import java.util.List;

import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.constrainedlanguagesentences.ConstrainedLanguageSentenceDTO;

class SimpleDiffMarkedConstrainedLanguageSentence implements IDiffMarkedConstrainedLanguageSentence {

	private List<IDiffMarkedWord> diffMarkedWords=new ArrayList<IDiffMarkedWord>();
	private final ConstrainedLanguageSentenceDTO sentence;

	public SimpleDiffMarkedConstrainedLanguageSentence(ConstrainedLanguageSentenceDTO sentence) {
		super();
		this.sentence = sentence;
	}

	@Override
	public void addDiffMarkedWord(IDiffMarkedWord diffMarkedWord) {
		diffMarkedWords.add(diffMarkedWord);
	}

	@Override
	public List<IDiffMarkedWord> getDiffMarkedWords() {
		return diffMarkedWords;
	}

	@Override
	public ConstrainedLanguageSentenceDTO getConstrainedLanguageSentenceDTO() {
		return sentence;
	}

	@Override
	public void setDiffMarkedWords(List<IDiffMarkedWord> newDiffMarkedWords) {
		diffMarkedWords=newDiffMarkedWords;
	}

	@Override
	public String toString() {
		StringBuilder builder=new StringBuilder();
		builder.append("Sentence: "+sentence+"\n");
		builder.append("diffMarkedWords: \n"+diffMarkedWords+"\n");
		return builder.toString();
	}

	@Override
	public String getWordsAsStrng() {
		StringBuilder builder=new StringBuilder();
		for (IDiffMarkedWord word : diffMarkedWords) {
			builder.append(word.getWord());
			builder.append(" ");
		}
		return builder.toString();
	}



}
