package eu.redseeds.rsldl.engine.inferenceengine.structure;

import rsldl.DLRelationshipParticipant;
import rsldl.DLRelationshipParticipationDirection;
import rsldl.DLRelationshipParticipationMultiplicity;

public class DLSupplementRuleParticipation extends InferenceRuleParticipation {
	DLRelationshipParticipationDirection direction;
	DLRelationshipParticipationMultiplicity multiplicity;
	DLRelationshipParticipant participant;
	
	public DLSupplementRuleParticipation(DLRelationshipParticipationDirection direction, DLRelationshipParticipationMultiplicity multiplicity, DLRelationshipParticipant participant) {
		this.direction = direction;
		this.multiplicity = multiplicity;
		this.participant = participant;
	}

	@Override
	public DLRelationshipParticipationDirection getDirection() {
		return direction;
	}
	
	@Override
	public void setDirection(DLRelationshipParticipationDirection direction) {
		this.direction = direction;
	}
	
	@Override
	public DLRelationshipParticipationMultiplicity getMultiplicity() {
		return multiplicity;
	}
	
	@Override
	public void setMultiplicity(DLRelationshipParticipationMultiplicity multiplicity) {
		this.multiplicity = multiplicity;
	}
	
	@Override
	public DLRelationshipParticipant getParticipant() {
		return participant;
	}
	
	@Override
	public void setParticipant(DLRelationshipParticipant participant) {
		this.participant = participant;
	}

}
