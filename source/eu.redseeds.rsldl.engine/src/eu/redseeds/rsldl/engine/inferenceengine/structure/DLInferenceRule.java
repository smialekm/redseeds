package eu.redseeds.rsldl.engine.inferenceengine.structure;

import java.util.ArrayList;
import java.util.List;

import rsldl.DLRelationship;
import rsldl.DLRelationshipParticipation;

public class DLInferenceRule extends InferenceRule {
	DLRelationship relationship;
	
	public DLInferenceRule(DLRelationship relationship) {
		this.relationship = relationship;
	}
	
	public DLRelationship getRelationship() {
		return relationship;
	}
	
	@Override
	public List<InferenceRuleParticipation> getParticipations() {
		List<InferenceRuleParticipation> participations = new ArrayList<InferenceRuleParticipation>();
		for (DLRelationshipParticipation p:relationship.getParticipations())
			participations.add(new DLInferenceRuleParticipation(p));
		return participations;
	}

	@Override
	public String getName() {
		return relationship.getName();
	}

	@Override
	public void setName(String value) {
		relationship.setName(value);
	}
	
}
