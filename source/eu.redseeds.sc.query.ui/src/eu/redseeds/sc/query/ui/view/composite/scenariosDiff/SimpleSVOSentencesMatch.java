package eu.redseeds.sc.query.ui.view.composite.scenariosDiff;

class ConstrainedLanguageSentenceMatch implements IConstrainedLanguageSentenceMatch {

	private final IDiffMarkedConstrainedLanguageSentence currentDiffMarkedSentence;
	private final IDiffMarkedConstrainedLanguageSentence pastDiffMarkedSentence;

	public ConstrainedLanguageSentenceMatch(IDiffMarkedConstrainedLanguageSentence currentDiffMarkedSentence, IDiffMarkedConstrainedLanguageSentence pastDiffMarkedSentence) {
		super();
		this.currentDiffMarkedSentence = currentDiffMarkedSentence;
		this.pastDiffMarkedSentence = pastDiffMarkedSentence;
	}

	@Override
	public IDiffMarkedConstrainedLanguageSentence getCurrentDiffMarkedConstrainedLanguageSentence() {
		return currentDiffMarkedSentence;
	}

	@Override
	public IDiffMarkedConstrainedLanguageSentence getPastDiffMarkedConstrainedLanguageSentence() {
		return pastDiffMarkedSentence;
	}

	@Override
	public boolean isAdded() {
		return currentDiffMarkedSentence==null&&pastDiffMarkedSentence!=null;
	}

	@Override
	public boolean isMissing() {
		return currentDiffMarkedSentence!=null&&pastDiffMarkedSentence==null;
	}

	@Override
	public boolean isSimilar() {
		return currentDiffMarkedSentence!=null&&pastDiffMarkedSentence!=null;
	}


	@Override
	public String toString() {
		StringBuilder builder=new StringBuilder();
		builder.append("currentDiffMarkedSentence: \n");
		if(isMissing()){
			builder.append("IS MISSING\n");
		}
		builder.append(currentDiffMarkedSentence+"\n");
		builder.append("pastDiffMarkedSentence: \n");
		if(isAdded()){
			builder.append("IS ADDED\n");
		}
		builder.append(pastDiffMarkedSentence+"\n");
		builder.append("=============================\n");
		return builder.toString();
	}

}
