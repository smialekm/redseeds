package eu.redseeds.scl.model.impl.rsl.rslrequirementrepresentationsentences.controlsentences;

import java.util.ArrayList;
import java.util.List;

import de.uni_koblenz.jgralab.Graph;
import eu.redseeds.scl.SCLGraph;
import eu.redseeds.scl.impl.SCLGraphImpl;
import eu.redseeds.scl.impl.rsl.rslrequirementrepresentationsentences.controlsentences.InvocationSentenceImpl;
import eu.redseeds.scl.model.BusinessLayerFacade;
import eu.redseeds.scl.model.impl.rsl.rslrequirements.requirementsspecifications.UseCaseDTOImpl;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentations.descriptiverequirementrepresentations.ConstrainedLanguageScenarioDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.controlsentences.InvocationSentenceDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.UseCaseDTO;
import eu.redseeds.scl.model.sclkernel.StereotypeDTO;
import eu.redseeds.scl.rsl.rslrequirementrepresentations.descriptiverequirementrepresentations.ConstrainedLanguageScenario;
import eu.redseeds.scl.rsl.rslrequirements.requirementsspecifications.RSLUseCase;
import eu.redseeds.scl.rsl.rslrequirements.usecaserelationships.InvocationRelationship;
import eu.redseeds.scl.sclkernel.Stereotype;


public class InvocationSentenceDTOImpl extends InvocationSentenceImpl implements InvocationSentenceDTO {

	@Override
	public UseCaseDTO getInvokedUseCase() {
		List<? extends InvocationRelationship> invokes  = super.getInvocationList();		
		if (!invokes.isEmpty()){			
			InvocationRelationship rel = invokes.get(0); 			
			if (rel.getTargetList().size()>0){
				if (rel.getTargetList().get(0) instanceof UseCaseDTOImpl){
				return (UseCaseDTOImpl)rel.getTargetList().get(0);
				}
			}
		}
		return null;
	}

	@Override
	public void setInvokedUseCase(UseCaseDTO uc){
		setInvokedUseCase(uc,null);
	}
	
	@Override
	public void setInvokedUseCase(UseCaseDTO uc, UseCaseDTO uc2) {		
		if (!getInvocationList().isEmpty()){
			if (getInvocationList().get(0).getSourceList().isEmpty() || null==getInvocationList().get(0).getSourceList().get(0) || getInvocationList().get(0).getTargetList().isEmpty() || null==getInvocationList().get(0).getTargetList().get(0) || ((BusinessLayerFacade) getGraph()).getInvocationSentences((UseCaseDTO) getInvocationList().get(0).getSourceList().get(0), (UseCaseDTO) getInvocationList().get(0).getTargetList().get(0)).size()<2){
				if (getInvokedUseCase().getConstrainedLanguageScenarioDTOList().isEmpty() && null==getInvokedUseCase().getParent() && ((RSLUseCase) getInvokedUseCase()).getInvokedList().size()==1) getInvokedUseCase().delete();
				else getInvocationList().get(0).delete();
			}
			else removeInvocation(getInvocationList().get(0));
		}
		UseCaseDTOImpl source=(UseCaseDTOImpl) uc2;
		// TODO: replace if...if...if with try catch clause				
		if(null==source)
			if (getScenarioList()!=null)
				if (getScenarioList().size()>0)
					if (getScenarioList().get(0)!=null)
						if (getScenarioList().get(0).getRequirementList()!=null)
							if (getScenarioList().get(0).getRequirementList().get(0)!=null)
								if (getScenarioList().get(0).getRequirementList().get(0) instanceof UseCaseDTOImpl){
									source=(UseCaseDTOImpl)super.getScenarioList().get(0).getRequirementList().get(0);		
								}
		InvocationRelationship rel;
		if(null==source || null==uc || null==(rel=((BusinessLayerFacade)getGraph()).getInvocationRelationship(source, uc))){
			rel=((SCLGraph)super.getGraph()).createInvocationRelationship();
			rel.addTarget((UseCaseDTOImpl)uc);	
			if (null!=source) rel.addSource(source);
		}
		this.addInvocation(rel);
	}

	public InvocationSentenceDTOImpl(int arg0, Graph arg1) {
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

	@Override
	public void addStereotype(String stereotypeName) {
		if(getStereotypes().contains(stereotypeName)) {
			return;
		}
		
		StereotypeDTO stereotype = (StereotypeDTO)(((SCLGraphImpl)super.getGraph()).createSclkernel$Stereotype());
		stereotype.setName(stereotypeName);
		super.addStereotype((Stereotype)stereotype);
	}

	@Override
	public List<String> getStereotypes() {
		List<String> result = new ArrayList<String>();

		List<? extends Stereotype> stereotypes = super.getStereotypeList();
		if(stereotypes == null) {
			return result;
		}
		if(stereotypes.size() == 0) {
			return result;
		}
		for(Stereotype stereotype : stereotypes) {
			result.add(stereotype.getName());
		}
		return result;
	}

	@Override
	public void removeStereotype(String stereotypeName) {
		List<? extends Stereotype> stereotypes = super.getStereotypeList();
		if(stereotypes == null) {
			return;
		}
		if(stereotypes.size() == 0) {
			return;
		}
		for(Stereotype stereotype : stereotypes) {
			if(stereotype.getName().equalsIgnoreCase(stereotypeName)) {
				super.removeStereotype(stereotype);
				return;
			}
		}
	}

	@Override
	public void delete(){
		if (!getInvocationList().isEmpty() && (getInvocationList().get(0).getTargetList().isEmpty() || null==getInvocationList().get(0).getTargetList().get(0) || getInvocationList().get(0).getSourceList().isEmpty() || null==getInvocationList().get(0).getSourceList().get(0) || ((BusinessLayerFacade)getGraph()).getInvocationSentences((UseCaseDTO) getInvocationList().get(0).getSourceList().get(0), (UseCaseDTO) getInvocationList().get(0).getTargetList().get(0)).size()<2)){
			if (getInvokedUseCase().getConstrainedLanguageScenarioDTOList().isEmpty() && null==getInvokedUseCase().getParent() && ((RSLUseCase) getInvokedUseCase()).getInvokedList().size()==1) getInvokedUseCase().delete();
			else getInvocationList().get(0).delete();
		}
		super.delete();
	}

	private SCLGraph getSCLGraph() {
		return (SCLGraph) getGraph();
	}
	
	@Override
	public InvocationSentenceDTO copy() {
		InvocationSentenceDTO sent = (InvocationSentenceDTO) getSCLGraph().createInvocationSentence();
		if (null!=getInclusionType()) sent.setInclusionType(getInclusionType());
		if (null!=getInvokedUseCase()) sent.setInvokedUseCase(getInvokedUseCase(),getInvocationSource());
		return sent;
	}
	
	@Override
	public UseCaseDTO getInvocationSource() {
		List<? extends InvocationRelationship> invokes  = super.getInvocationList();		
		if (!invokes.isEmpty()){			
			InvocationRelationship rel = invokes.get(0); 			
			if (rel.getSourceList().size()>0){
				if (rel.getSourceList().get(0) instanceof UseCaseDTOImpl){
				return (UseCaseDTOImpl)rel.getSourceList().get(0);
				}
			}
		}
		return null;
	}

}