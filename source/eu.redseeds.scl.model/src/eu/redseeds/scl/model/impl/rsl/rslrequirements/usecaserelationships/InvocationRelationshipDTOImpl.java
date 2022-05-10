package eu.redseeds.scl.model.impl.rsl.rslrequirements.usecaserelationships;

import java.util.List;

import de.uni_koblenz.jgralab.Graph;
import eu.redseeds.scl.impl.rsl.rslrequirements.usecaserelationships.InvocationRelationshipImpl;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.UseCaseDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.usecaserelationships.InvocationRelationshipDTO;
import eu.redseeds.scl.rsl.rslrequirements.requirementsspecifications.RSLUseCase;
import eu.redseeds.scl.sclkernel.SCLElement;

public class InvocationRelationshipDTOImpl extends InvocationRelationshipImpl implements InvocationRelationshipDTO{

	
	public InvocationRelationshipDTOImpl(int arg0, Graph arg1) {
		super(arg0, arg1);
	}

	
	@Override
	public void deleteRelationship() {
		this.delete();
	}


	@Override
	public int getInvocationType() {
		// TODO
		return 0;
	}


	@Override
	public UseCaseDTO getSource() {
		List<? extends SCLElement> sourceList = getSourceList();
		if(sourceList != null) {
			if(!sourceList.isEmpty()) {
				return (UseCaseDTO) sourceList.get(0);
			}
		}
		return null;
	}

	
	@Override
	public UseCaseDTO getTarget() {
		List<? extends SCLElement> targetList = getTargetList();
		if(targetList != null) {
			if(!targetList.isEmpty()) {
				return (UseCaseDTO) targetList.get(0);
			}
		}
		return null;
	}

	
	@Override
	public void setSource(UseCaseDTO uc) {
		if (uc != null)
			this.addSource((RSLUseCase) uc);
	}

	
	@Override
	public void setTarget(UseCaseDTO uc) {
		if (uc != null)
			this.addTarget((RSLUseCase) uc);		
	}

}
