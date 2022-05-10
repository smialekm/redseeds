package eu.redseeds.sc.query.ui.view.composite.scenariosDiff;

class SimpleDiffMarkedWord implements IDiffMarkedWord {

	private final DiffType diffType;
	private final String word;

	public SimpleDiffMarkedWord(DiffType diffType, String word) {
		super();
		this.diffType = diffType;
		this.word = word;
	}

	@Override
	public DiffType getDiffType() {
		return diffType;
	}

	@Override
	public String getWord() {
		return word;
	}

	@Override
	public String toString() {
		StringBuilder builder=new StringBuilder();
		builder.append("{"+diffType+"}"+word+"\n");
		return builder.toString();
	}

}
