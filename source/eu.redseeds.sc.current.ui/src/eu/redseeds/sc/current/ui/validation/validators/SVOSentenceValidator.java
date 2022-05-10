package eu.redseeds.sc.current.ui.validation.validators;

import java.util.ArrayList;
import java.util.List;

import eu.redseeds.sc.current.repository.SCProjectContainer;
import eu.redseeds.sc.current.ui.validation.IValidate;
import eu.redseeds.sc.current.ui.validation.ValidationResult;
import eu.redseeds.scl.model.BusinessLayerFacade;
import eu.redseeds.scl.model.rsl.rsldomainelements.actors.ActorDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.ComplexVerbPhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.NounPhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.SimpleVerbPhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.VerbPhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.systemelements.SystemElementDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentations.descriptiverequirementrepresentations.ConstrainedLanguageScenarioDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.svosentences.SVOSentenceDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.UseCaseDTO;

public class SVOSentenceValidator implements IValidate {

	@Override
	public int getComplexness() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public String getLabel() {
		return "Validating SVOSentence";
	}

	@Override
	public ValidationResult[] validate(Object element) {
		if (!(element instanceof SVOSentenceDTO)) {
			ValidationResult vr = new ValidationResult();
			vr.setProblemID(IValidate.ID_WRONG_TYPE);
			vr.setMessage(MSG_WRONG_TYPE);
			vr.setSclElement(element);
			return new ValidationResult[] { vr };
		}
		SVOSentenceDTO svoSent = (SVOSentenceDTO)element;
		if(!validateSVOSentence(svoSent)) {
			ValidationResult vr = new ValidationResult();
			vr.setProblemID(IValidate.ID_SENT_SVO_STRUCT);
			vr.setMessage(MSG_SENT_SVO_STRUCT);
			if (!svoSent.getOwningScenarios().isEmpty()) {
				ConstrainedLanguageScenarioDTO scen 
					= svoSent.getOwningScenarios().get(0);
				UseCaseDTO uc = scen.getParent();
				vr.setPath(uc.getSpecificationPath()+"\\"+uc.getName()
						+"\\"+scen.getName()+"\\"+svoSent.getFullSentenceText());
			}
			vr.setSeverity(IValidate.SEVERITY_ERROR);
			vr.setSclElement(svoSent);
			return new ValidationResult[] { vr };
		}
		
		
		// Validation of existence elements in vocabulary
		ValidationResult subjectResult = null;
		ValidationResult predicateResult = null;
		List<ValidationResult> resultsList = new ArrayList<ValidationResult>();
		if(!checkExistenceOfNotions(svoSent.getPredicate())) {
			predicateResult = new ValidationResult();
			predicateResult.setProblemID(IValidate.ID_SENT_SVO_NOTIONS_VOC_MISSING);
			predicateResult.setMessage(MSG_SENT_SVO_NOTIONS_VOC_MISSING);
			if (!svoSent.getOwningScenarios().isEmpty()) {
				ConstrainedLanguageScenarioDTO scen 
					= svoSent.getOwningScenarios().get(0);
				UseCaseDTO uc = scen.getParent();
				predicateResult.setPath(uc.getSpecificationPath()+"\\"+uc.getName()
						+"\\"+scen.getName()+"\\"+svoSent.getFullSentenceText());
			}
			predicateResult.setSclElement(svoSent);
			predicateResult.setSeverity(IValidate.SEVERITY_ERROR);
			resultsList.add(predicateResult);
		} else if (!checkPhrasesInVocabulary(svoSent.getPredicate())){
			predicateResult = new ValidationResult();
			predicateResult.setProblemID(IValidate.ID_SENT_SVO_PHRASES_VOC_MISSING);
			predicateResult.setMessage(MSG_SENT_SVO_PHRASES_VOC_MISSING);
			if (!svoSent.getOwningScenarios().isEmpty()) {
				ConstrainedLanguageScenarioDTO scen 
					= svoSent.getOwningScenarios().get(0);
				UseCaseDTO uc = scen.getParent();
				predicateResult.setPath(uc.getSpecificationPath()+"\\"+uc.getName()
						+"\\"+scen.getName()+"\\"+svoSent.getFullSentenceText());
			}
			predicateResult.setSclElement(svoSent);
			resultsList.add(predicateResult);
		}
		
		if(!checkExistenceOfActorSystemElement(svoSent.getSubject())) {
			
			subjectResult = new ValidationResult();
			subjectResult.setProblemID(IValidate.ID_SENT_SVO_SUBJECT_VOC_MISSING);
			subjectResult.setMessage(MSG_SENT_SVO_SUBJECT_VOC_MISSING);
			if (!svoSent.getOwningScenarios().isEmpty()) {
				ConstrainedLanguageScenarioDTO scen 
					= svoSent.getOwningScenarios().get(0);
				UseCaseDTO uc = scen.getParent();
				subjectResult.setPath(uc.getSpecificationPath()+"\\"+uc.getName()
						+"\\"+scen.getName()+"\\"+svoSent.getFullSentenceText());
			}
			subjectResult.setSeverity(IValidate.SEVERITY_ERROR);
			subjectResult.setSclElement(svoSent);
			resultsList.add(subjectResult);
		}
		ValidationResult[] results = resultsList.toArray(new ValidationResult[resultsList.size()]);	
		if (results.length >0){	
			return results;
		}
		return new ValidationResult[0];
	}

	@Override
	public ValidationResult[] validateRecursively(Object element) {
		// TODO validate children
		return validate(element);
	}
	
	/**
	 * validates if sentence is an svo sentence
	 * severity: high
	 * @param svoSent
	 * @return
	 */
	private boolean validateSVOSentence(SVOSentenceDTO svoSent) {
		if(svoSent.getSubject() != null) {
			if(svoSent.getSubject().getNoun() == null) {
				return false;
			}
		}
		else {
			return false;
		}
		if(svoSent.getPredicate() != null) {
			if(svoSent.getPredicate() instanceof SimpleVerbPhraseDTO) {
				return checkSimpleVerbPhrase(svoSent.getPredicate());
			}
			else if (svoSent.getPredicate() instanceof ComplexVerbPhraseDTO) {
				ComplexVerbPhraseDTO cvph = (ComplexVerbPhraseDTO)svoSent.getPredicate();
				if(cvph.getPreposition() == null) {
					return false;
				}
				if(cvph.getSimpleVerbPhrase() == null) {
					return false;
				}
				if(cvph.getObject() == null) {
					return false;
				}
				else if(cvph.getObject() instanceof NounPhraseDTO) {
					NounPhraseDTO nph = (NounPhraseDTO)cvph.getObject();
					if(nph.getNoun() == null) {
						return false;
					}
				}
				return checkSimpleVerbPhrase(cvph.getSimpleVerbPhrase());
			}
		}
		else {
			return false;
		}
		return true;
	}
	
	private boolean checkSimpleVerbPhrase(VerbPhraseDTO vph) {
		if(vph instanceof SimpleVerbPhraseDTO) {
			SimpleVerbPhraseDTO simpleP 
				= (SimpleVerbPhraseDTO)vph;
			if(simpleP.getVerb() == null) {
				return false;
			}
			if(simpleP.getObject() != null) {
				if(!(simpleP.getObject() instanceof NounPhraseDTO)) {
					return false;
				}
				else {
					if(simpleP.getObject().getNoun() == null) {
						return false;
					}
				}
			}
			else {
				return false;
			}
			return true;
		} 
		else {
			return false;
		}
	}
	
	private boolean checkExistenceOfActorSystemElement(NounPhraseDTO nph){
		//TODO: albert should validate these changes
		if(nph==null)
			return false;
		BusinessLayerFacade facade = SCProjectContainer.instance().getSCProject(nph).getBusinessLayerFacade();
		ActorDTO actor = facade.getActorDTO(nph);
		SystemElementDTO sysElem = facade.getSystemElementDTO(nph);
		if (actor != null)
			return true;
		if (sysElem != null)
			return true;
		return false;
	}
	
	/**
	 * severity: high
	 * @param vph
	 * @return
	 */
	private boolean checkExistenceOfNotions(VerbPhraseDTO vph){
		if(vph==null) //TODO: albert must verify this code
			return false;
		SimpleVerbPhraseDTO svph;
		ComplexVerbPhraseDTO cvph;
		NounPhraseDTO snph = null, cnph = null;
		
		if (vph instanceof ComplexVerbPhraseDTO){
			cvph = (ComplexVerbPhraseDTO)vph;
			svph = cvph.getSimpleVerbPhrase();
			snph = svph.getObject();
			cnph = cvph.getObject();
		}
		
		if (vph instanceof SimpleVerbPhraseDTO){
			svph = (SimpleVerbPhraseDTO)vph;
			snph = svph.getObject();
		}
		BusinessLayerFacade facade = SCProjectContainer.instance().getSCProject(vph).getBusinessLayerFacade();
		if (snph != null){
			if (facade.getNotionDTO(snph) == null){
				return false;
			}
		}
		
		if (cnph != null){
			if (facade.getNotionDTO(cnph) == null){
				return false;
			}
		}
		
		return true;
	}
	
	private boolean checkPhrasesInVocabulary(VerbPhraseDTO vph){
		if(vph==null) //TODO: albert must verify these changes
			return false;
		SimpleVerbPhraseDTO svph = null;
		ComplexVerbPhraseDTO cvph =  null;
		NounPhraseDTO snph = null, cnph = null;
		
		if (vph instanceof ComplexVerbPhraseDTO){
			cvph = (ComplexVerbPhraseDTO)vph;
			svph = cvph.getSimpleVerbPhrase();
			snph = svph.getObject();
			cnph = cvph.getObject();
		}
		
		if (vph instanceof SimpleVerbPhraseDTO){
			svph = (SimpleVerbPhraseDTO)vph;
			snph = svph.getObject();
		}
		BusinessLayerFacade facade = SCProjectContainer.instance().getSCProject(vph).getBusinessLayerFacade();
		if (snph != null){
			NotionDTO simpleNotion = facade.getNotionDTO(snph);
			if (simpleNotion != null){
				if (simpleNotion.getDomainStatementDTO(svph) == null){
					return false;
				}
			}
		}
		
		if (cnph != null){
			NotionDTO complexNotion = facade.getNotionDTO(cnph);
			if (complexNotion != null){
				if (complexNotion.getDomainStatementDTO(cvph) == null){
					return false;
				}
			}
		}
		return true;
	}
	
	

}
