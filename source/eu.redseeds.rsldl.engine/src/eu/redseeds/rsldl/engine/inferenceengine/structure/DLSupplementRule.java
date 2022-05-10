package eu.redseeds.rsldl.engine.inferenceengine.structure;

import java.util.ArrayList;
import java.util.List;

import rsldl.DLRelationshipParticipant;
import rsldl.DLRelationshipParticipationDirection;
import rsldl.DLRelationshipParticipationMultiplicity;

public class DLSupplementRule extends InferenceRule {
	String name;
	List<InferenceRuleParticipation> participations = new ArrayList<InferenceRuleParticipation>();
	DLSupplementRuleSourceType sourceType;
	
	public DLSupplementRule(String name, DLSupplementRuleSourceType sourceType) {
		super();
		this.name = name;
		this.sourceType = sourceType;
	}

	@Override
	public List<InferenceRuleParticipation> getParticipations() {
		return participations;
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	public void addParticipant(DLRelationshipParticipant participant, DLRelationshipParticipationDirection direction) {
		addParticipant(participant, direction, DLRelationshipParticipationMultiplicity.SINGLE);
	}
		
	public void addParticipant(DLRelationshipParticipant participant, DLRelationshipParticipationDirection direction, DLRelationshipParticipationMultiplicity mlt) {
		participations.add((InferenceRuleParticipation) new DLSupplementRuleParticipation(direction, mlt, participant));
	}

}
