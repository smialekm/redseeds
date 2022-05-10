package eu.redseeds.rsldl.engine.inferenceengine.structure;

import rsldl.DLRelationship;
import rsldl.DLRelationshipParticipant;
import rsldl.DLRelationshipParticipation;
import rsldl.DLRelationshipParticipationDirection;
import rsldl.DLRelationshipParticipationMultiplicity;

public class DLInferenceRuleParticipation extends InferenceRuleParticipation {
	DLRelationshipParticipation participation;
	
	public DLInferenceRuleParticipation(DLRelationshipParticipation participation) {
		this.participation = participation;
	}

	public DLRelationshipParticipation getParticipation() {
		return participation;
	}

	public String getName() {
		return participation.getName();
	}

	public void setName(String value) {
		participation.setName(value);
	}

	@Override
	public DLRelationshipParticipationDirection getDirection() {
		return participation.getDirection();
	}

	@Override
	public void setDirection(DLRelationshipParticipationDirection value) {
		participation.setDirection(value);
	}

	@Override
	public DLRelationshipParticipationMultiplicity getMultiplicity() {
		return participation.getMultiplicity();
	}

	@Override
	public void setMultiplicity(DLRelationshipParticipationMultiplicity value) {
		participation.setMultiplicity(value);
	}

	@Override
	public DLRelationshipParticipant getParticipant() {
		return participation.getParticipant();
	}

	@Override
	public void setParticipant(DLRelationshipParticipant value) {
		participation.setParticipant(value);
	}

	public DLRelationship getRelationship() {
		return getRelationship();
	}

	public void setRelationship(DLRelationship value) {
		participation.setRelationship(value);
	}

}
