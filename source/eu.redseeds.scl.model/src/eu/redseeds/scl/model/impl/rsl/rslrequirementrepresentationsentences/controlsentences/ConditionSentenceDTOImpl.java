package eu.redseeds.scl.model.impl.rsl.rslrequirementrepresentationsentences.controlsentences;

import java.util.ArrayList;
import java.util.List;

import de.uni_koblenz.jgralab.Graph;
import eu.redseeds.scl.SCLGraph;
import eu.redseeds.scl.impl.rsl.rslrequirementrepresentationsentences.controlsentences.ConditionSentenceImpl;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentations.descriptiverequirementrepresentations.ConstrainedLanguageScenarioDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.controlsentences.ConditionSentenceDTO;
import eu.redseeds.scl.rsl.rslrequirementrepresentations.descriptiverequirementrepresentations.ConstrainedLanguageScenario;


public class ConditionSentenceDTOImpl extends ConditionSentenceImpl implements ConditionSentenceDTO {

	public ConditionSentenceDTOImpl(int arg0, Graph arg1) {
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
	public ConditionSentenceDTO copy() {
		ConditionSentenceDTO sent = (ConditionSentenceDTO) getSCLGraph().createConditionSentence();
		if(null!=getSentenceText()) sent.setSentenceText(getSentenceText());
		return sent;
	}

}