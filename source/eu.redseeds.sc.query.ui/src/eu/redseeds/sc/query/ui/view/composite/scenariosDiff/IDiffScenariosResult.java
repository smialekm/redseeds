package eu.redseeds.sc.query.ui.view.composite.scenariosDiff;

import java.util.Collection;
import java.util.List;

interface IDiffScenariosResult {
	public String getCurrentElementPath();
	public String getPastElementPath();
	public void setCurrentElementPath(String path);
	public void setPastElementPath(String path);

	public List<IConstrainedLanguageSentenceMatch> getConstrainedLanguageSentenceMatchs();
	public void addConstrainedLanguageSentenceMatchs(IConstrainedLanguageSentenceMatch sentencesMatch);
	public void addAllConstrainedLanguageSentenceMatchs(Collection<IConstrainedLanguageSentenceMatch> sentencesMatchs);
}
