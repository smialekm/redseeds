package eu.redseeds.rsldl.engine.inferenceengine.structure;

import rsldl.DLRelationshipParticipant;
import rsldl.DLRelationshipParticipationDirection;
import rsldl.DLRelationshipParticipationMultiplicity;

public abstract class InferenceRuleParticipation {
	
	public abstract DLRelationshipParticipationDirection getDirection();

	public abstract void setDirection(DLRelationshipParticipationDirection value);

	public abstract DLRelationshipParticipationMultiplicity getMultiplicity();

	public abstract void setMultiplicity(DLRelationshipParticipationMultiplicity value);

	public abstract DLRelationshipParticipant getParticipant();

	public abstract void setParticipant(DLRelationshipParticipant value);

}
