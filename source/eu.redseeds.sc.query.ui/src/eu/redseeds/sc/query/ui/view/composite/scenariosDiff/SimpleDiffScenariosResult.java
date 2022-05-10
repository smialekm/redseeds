package eu.redseeds.sc.query.ui.view.composite.scenariosDiff;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

class SimpleDiffScenariosResult implements IDiffScenariosResult {

	private List<IConstrainedLanguageSentenceMatch> svoSentencesMatchs=new ArrayList<IConstrainedLanguageSentenceMatch>();
	private String currElmPath;
	private String pastElmPath;

	@Override
	public void addConstrainedLanguageSentenceMatchs(IConstrainedLanguageSentenceMatch sentencesMatch) {
		svoSentencesMatchs.add(sentencesMatch);
	}

	@Override
	public List<IConstrainedLanguageSentenceMatch> getConstrainedLanguageSentenceMatchs() {
		return new ArrayList<IConstrainedLanguageSentenceMatch>(svoSentencesMatchs);
	}

	@Override
	public void addAllConstrainedLanguageSentenceMatchs(Collection<IConstrainedLanguageSentenceMatch> sentencesMatchs) {
		svoSentencesMatchs.addAll(sentencesMatchs);
	}

	@Override
	public String toString() {
		return svoSentencesMatchs.toString();
	}

	@Override
	public String getCurrentElementPath() {
		return currElmPath;
	}

	@Override
	public String getPastElementPath() {
		return pastElmPath;
	}

	@Override
	public void setCurrentElementPath(String path) {
		currElmPath=path;
	}

	@Override
	public void setPastElementPath(String path) {
		pastElmPath=path;
	}



}
