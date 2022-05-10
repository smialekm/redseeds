package eu.redseeds.scl.model.impl.rsl.rslrequirementrepresentationsentences.constrainedlanguagesentences;

import java.util.ArrayList;
import java.util.List;

import de.uni_koblenz.jgralab.Graph;
import eu.redseeds.scl.SCLGraph;
import eu.redseeds.scl.impl.rsl.rslrequirementrepresentationsentences.constrainedlanguagesentences.RejoinSentenceImpl;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentations.descriptiverequirementrepresentations.ConstrainedLanguageScenarioDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.constrainedlanguagesentences.ConstrainedLanguageSentenceDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.constrainedlanguagesentences.RejoinSentenceDTO;
import eu.redseeds.scl.rsl.rslrequirementrepresentations.descriptiverequirementrepresentations.ConstrainedLanguageScenario;
import eu.redseeds.scl.rsl.rslrequirementrepresentationsentences.constrainedlanguagesentences.ConstrainedLanguageSentence;

public class RejoinSentenceDTOImpl extends RejoinSentenceImpl implements
		RejoinSentenceDTO {

	@Override
	public ConstrainedLanguageSentenceDTO getRejoinedSentence() {
		if (!super.getRejoinedSentenceList().isEmpty())
			return (ConstrainedLanguageSentenceDTO) super
					.getRejoinedSentenceList().get(0);
		return null;
	}

	@Override
	public void setRejoinedSentence(ConstrainedLanguageSentenceDTO rj) {
		if (!super.getRejoinedSentenceList().isEmpty())
			super
					.removeRejoinedSentence(super.getRejoinedSentenceList()
							.get(0));
		if(rj!=null)
			super.addRejoinedSentence((ConstrainedLanguageSentence) rj);

	}

	public RejoinSentenceDTOImpl(int arg0, Graph arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<ConstrainedLanguageScenarioDTO> getOwningScenarios() {
		List<? extends ConstrainedLanguageScenario> l = super.getScenarioList();
		List<ConstrainedLanguageScenarioDTO> result = new ArrayList<ConstrainedLanguageScenarioDTO>();
		for (ConstrainedLanguageScenario s : l) {
			if (s instanceof ConstrainedLanguageScenarioDTO) {
				result.add((ConstrainedLanguageScenarioDTO) s);
			}
		}
		return result;
	}
	
	private SCLGraph getSCLGraph() {
		return (SCLGraph) getGraph();
	}
	
	@Override
	public RejoinSentenceDTO copy() {
		RejoinSentenceDTO sent = (RejoinSentenceDTO) getSCLGraph().createRejoinSentence();
		if (null!=getRejoinedSentence()) sent.setRejoinedSentence(getRejoinedSentence());
		return sent;
	}

}