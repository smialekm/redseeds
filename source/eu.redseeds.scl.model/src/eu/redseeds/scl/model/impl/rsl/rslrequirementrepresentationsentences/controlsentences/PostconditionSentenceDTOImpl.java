package eu.redseeds.scl.model.impl.rsl.rslrequirementrepresentationsentences.controlsentences;

import java.util.ArrayList;
import java.util.List;

import de.uni_koblenz.jgralab.Graph;
import eu.redseeds.scl.SCLGraph;
import eu.redseeds.scl.impl.rsl.rslrequirementrepresentationsentences.controlsentences.PostconditionSentenceImpl;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentations.descriptiverequirementrepresentations.ConstrainedLanguageScenarioDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.controlsentences.PostconditionSentenceDTO;
import eu.redseeds.scl.rsl.rslrequirementrepresentations.descriptiverequirementrepresentations.ConstrainedLanguageScenario;


public class PostconditionSentenceDTOImpl extends PostconditionSentenceImpl implements PostconditionSentenceDTO {

	public PostconditionSentenceDTOImpl(int arg0, Graph arg1) {
		super(arg0, arg1);		
	}
	
	public void setSuccess(boolean isSuccess){
		setIsSuccess(isSuccess);
	}
	public boolean isSuccess(){
		return isIsSuccess();
	}
	
	@Override
	public List<ConstrainedLanguageScenarioDTO> getOwningScenarios() {
		//TODO: copied from InvocationSentenceDTOImpl
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
	public PostconditionSentenceDTO copy() {
		PostconditionSentenceDTO sent = (PostconditionSentenceDTO) getSCLGraph().createPostconditionSentence();
		if (null!=getSentenceText()) sent.setSentenceText(getSentenceText());
		sent.setSuccess(isSuccess());
		return sent;
	}
	
}