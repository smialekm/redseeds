package eu.redseeds.scl.model.impl.rsl.rslrequirementrepresentations.descriptiverequirementrepresentations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import de.uni_koblenz.jgralab.EdgeDirection;
import de.uni_koblenz.jgralab.Graph;
import eu.redseeds.scl.impl.rsl.rslrequirementrepresentations.descriptiverequirementrepresentations.ConstrainedLanguageScenarioImpl;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentations.descriptiverequirementrepresentations.ConstrainedLanguageScenarioDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.constrainedlanguagesentences.ConstrainedLanguageSentenceDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.UseCaseDTO;
import eu.redseeds.scl.rsl.rslrequirementrepresentations.descriptiverequirementrepresentations.ConstrainedLanguageScenario;
import eu.redseeds.scl.rsl.rslrequirementrepresentations.descriptiverequirementrepresentations.ScenarioContainsScenarioStep;
import eu.redseeds.scl.rsl.rslrequirementrepresentationsentences.constrainedlanguagesentences.ConstrainedLanguageSentence;
import eu.redseeds.scl.rsl.rslrequirements.requirementsspecifications.Requirement;

public class ConstrainedLanguageScenarioDTOImpl extends
		ConstrainedLanguageScenarioImpl implements
		ConstrainedLanguageScenarioDTO {

	@Override
	public void removeScenarioStep(ConstrainedLanguageSentenceDTO step) {
		this.removeScenarioStep((ConstrainedLanguageSentence) step);
		
	}

	public ConstrainedLanguageScenarioDTOImpl(int arg0, Graph arg1) {
		super(arg0, arg1);
	}

	@Override
	public String toString() {
		return this.getName();
	}

	@Override
	public void addScenarioStep(ConstrainedLanguageSentenceDTO step) {
		this.addScenarioStep((ConstrainedLanguageSentence) step);
	}
	
	@Override
	public void delete(){
		for (ConstrainedLanguageSentenceDTO sent : this
				.getScenarioSentenceList()) {
			this.removeScenarioStep(sent);
			if (sent.getOwningScenarios().isEmpty())
				((ConstrainedLanguageSentence) sent).delete();
		}
		if(this.getRequirementList()!=null)
			if(this.getRequirementList().size()>0)
					this.getRequirementList().get(0).removeRepresentation((ConstrainedLanguageScenario) this);
		super.delete();
	}

	@Override
	public java.util.List<ConstrainedLanguageSentenceDTO> getScenarioSentenceList() {
		// TODO Auto-generated method stub
		// return new
		// ArrayList<ConstrainedLanguageSentenceDTO>(this.getScenarioStepList());
		List<? extends ConstrainedLanguageSentence> l = super
				.getScenarioStepList();
		List<ConstrainedLanguageSentenceDTO> result = new ArrayList<ConstrainedLanguageSentenceDTO>();
		for (ConstrainedLanguageSentence s : l) {
			if (s instanceof ConstrainedLanguageSentenceDTO) {
				result.add((ConstrainedLanguageSentenceDTO) s);
			}
		}
		return result;

	}

	@Override
	public ConstrainedLanguageSentenceDTO getPreviousSentence(
			ConstrainedLanguageSentenceDTO curent) {

		List<? extends ConstrainedLanguageSentence> sentences = this
				.getScenarioStepList();

		boolean thisSentence = false;

		Collections.reverse(sentences);

		for (ConstrainedLanguageSentence s : sentences) {
			if (thisSentence
					&& s instanceof ConstrainedLanguageSentence) {
				return (ConstrainedLanguageSentenceDTO) s;
			}
			if (s.equals((ConstrainedLanguageSentence) (curent))) {
				thisSentence = true;
			}
		}
		return null;
	}
	
	@Override
	public ConstrainedLanguageSentenceDTO getNextSentence(
			ConstrainedLanguageSentenceDTO curent) {

		List<? extends ConstrainedLanguageSentence> sentences = this
				.getScenarioStepList();

		boolean thisSentence = false;

		for (ConstrainedLanguageSentence s : sentences) {
			if (thisSentence
					&& s instanceof ConstrainedLanguageSentence) {
				return (ConstrainedLanguageSentenceDTO) s;
			}
			if (s.equals((ConstrainedLanguageSentence) (curent))) {
				thisSentence = true;
			}
		}
		return null;
	}

	@Override
	public void insertScenarioStepAfter(ConstrainedLanguageSentenceDTO after,
			ConstrainedLanguageSentenceDTO step) {
		ScenarioContainsScenarioStep newEdge = this
				.addScenarioStep((ConstrainedLanguageSentence) step);

		for (ConstrainedLanguageSentence s : getScenarioStepList()) {
			if (s.equals(after)) {
				ScenarioContainsScenarioStep scs = s
						.getFirstScenarioContainsScenarioStep(EdgeDirection.IN);
				while (!this.equals((ConstrainedLanguageScenario) scs
						.getAlpha())) {
					scs = scs
							.getNextScenarioContainsScenarioStep(EdgeDirection.IN);
					if (scs == null)
						return;
				}

				ScenarioContainsScenarioStep oldEdge = (ScenarioContainsScenarioStep) scs
						.getNormalEdge();
				newEdge.putEdgeAfter(oldEdge);
				break;
			}
		}
	}

	@Override
	public UseCaseDTO getParent() {
		
		for (Requirement r : getRequirementList()) {
			if (r instanceof UseCaseDTO) {
				return (UseCaseDTO)r;
			}
		}
		
		return null;
	}
	
	@Override
	public ConstrainedLanguageSentenceDTO getLastSentence(){		
		return getScenarioStepList().size()>0?(ConstrainedLanguageSentenceDTO)getScenarioStepList().get(getScenarioStepList().size()-1): null;
	}

}