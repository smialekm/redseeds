package eu.redseeds.scl.model.impl.rsl.rslrequirements.requirementsrelationships;

import java.util.List;

import de.uni_koblenz.jgralab.Graph;
import eu.redseeds.scl.impl.rsl.rslrequirements.requirementrelationships.FulfilsImpl;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsrelationships.NonInvocationRelationshipDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementDTO;
import eu.redseeds.scl.rsl.rslrequirements.requirementsspecifications.Requirement;
import eu.redseeds.scl.sclkernel.SCLElement;

public class FulfilsDTOImpl extends FulfilsImpl implements NonInvocationRelationshipDTO {

	public FulfilsDTOImpl(int arg0, Graph arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setSource(RequirementDTO req) {
		if (req != null)
			this.addSource((Requirement) req);
	}

	@Override
	public void setTarget(RequirementDTO req) {
		if (req != null)
			this.addTarget((Requirement) req);
	}

	@Override
	public RequirementDTO getSource() {
		List<? extends SCLElement> sourceList = getSourceList();
		if(sourceList != null) {
			if(!sourceList.isEmpty()) {
				return (RequirementDTO) sourceList.get(0);
			}
		}
		return null;
	}

	@Override
	public RequirementDTO getTarget() {
		List<? extends SCLElement> targetList = getTargetList();
		if(targetList != null) {
			if(!targetList.isEmpty()) {
				return (RequirementDTO) targetList.get(0);
			}
		}
		return null;
	}

	@Override
	public int getRelationshipType() {
		return NonInvocationRelationshipDTO.FULFILS;
	}
	
	@Override
	public void deleteRelationship() {
		this.delete();
	}

}
