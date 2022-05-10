package eu.redseeds.sc.query.ui.view.composite.scenariosDiff;

import java.util.ArrayList;
import java.util.List;

import eu.redseeds.scl.model.rsl.rslrequirementrepresentations.descriptiverequirementrepresentations.ConstrainedLanguageScenarioDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.constrainedlanguagesentences.ConstrainedLanguageSentenceDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.controlsentences.ConditionSentenceDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.controlsentences.PostconditionSentenceDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.controlsentences.PreconditionSentenceDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.svosentences.SVOSentenceDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.UseCaseDTO;

class ScenariosDiffContentProvider implements IScenariosDiffContentProvider {
	private final UseCaseDTO currentUseCaseDTO;
	private final UseCaseDTO pastUseCaseDTO;
	private final String currElmPath;
	private final String pastElmPath;
	public ScenariosDiffContentProvider(UseCaseDTO currentUseCaseDTO, UseCaseDTO pastUseCaseDTO,String currElmPath,String pastElmPath) {
		super();
		if(currentUseCaseDTO==null){
			throw new NullPointerException("currentUseCaseDTO cannot be null");
		}
		if(pastUseCaseDTO==null){
			throw new NullPointerException("pastUseCaseDTO cannot be null");
		}
		this.currentUseCaseDTO = currentUseCaseDTO;
		this.pastUseCaseDTO = pastUseCaseDTO;
		this.currElmPath=currElmPath;
		this.pastElmPath=pastElmPath;
	}

	@Override
	public List<ConstrainedLanguageSentenceDTO> getCurrentSentences(){
		return getSentences(currentUseCaseDTO);
	}
	@Override
	public List<ConstrainedLanguageSentenceDTO> getPastSentences(){
		return getSentences(pastUseCaseDTO);
	}

	private List<ConstrainedLanguageSentenceDTO> getSentences(UseCaseDTO useCaseDTO){
		List<ConstrainedLanguageSentenceDTO> result=new ArrayList<ConstrainedLanguageSentenceDTO>();
		List<ConstrainedLanguageScenarioDTO> constrainedLanguageScenarios = useCaseDTO.getConstrainedLanguageScenarioDTOList();
		if(constrainedLanguageScenarios!=null){
			for (ConstrainedLanguageScenarioDTO constrainedLanguageScenarioDTO : constrainedLanguageScenarios) {
				List<ConstrainedLanguageSentenceDTO> scenarioSentenceList = constrainedLanguageScenarioDTO.getScenarioSentenceList();
				for (ConstrainedLanguageSentenceDTO constrainedLanguageSentenceDTO : scenarioSentenceList) {
					if(constrainedLanguageSentenceDTO instanceof SVOSentenceDTO){
						result.add(constrainedLanguageSentenceDTO);
					}
					if(constrainedLanguageSentenceDTO instanceof ConditionSentenceDTO){
						result.add(constrainedLanguageSentenceDTO);
					}
					if(constrainedLanguageSentenceDTO instanceof PostconditionSentenceDTO){
						result.add(constrainedLanguageSentenceDTO);
					}
					if(constrainedLanguageSentenceDTO instanceof PreconditionSentenceDTO){
						result.add(constrainedLanguageSentenceDTO);
					}
				}
			}
		}
		return result;
	}

	public String getCurrElmPath() {
		return currElmPath;
	}

	public String getPastElmPath() {
		return pastElmPath;
	}

}
