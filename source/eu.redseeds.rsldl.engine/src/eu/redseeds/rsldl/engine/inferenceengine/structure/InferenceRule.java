package eu.redseeds.rsldl.engine.inferenceengine.structure;

import java.util.List;

import rsldl.DLRelationshipParticipant;
import eu.redseeds.rsldl.engine.util.DLParticipationsHelper;

public abstract class InferenceRule {
	
	public abstract List<InferenceRuleParticipation> getParticipations();
	public abstract String getName();
	public abstract void setName(String value);

	public List<InferenceRuleParticipation> getRequiredParticipations(DLRelationshipParticipant provided) {
		return DLParticipationsHelper.getRequiredParticipations(getParticipations(), provided);
	}

	public List<InferenceRuleParticipation> getProvidedParticipations() {
		return DLParticipationsHelper.getProvidedParticipations(getParticipations());
	}
	
	public InferenceRuleParticipation getProvidedParticipation(DLRelationshipParticipant participant) {
		return DLParticipationsHelper.getProvidedParticipation(getParticipations(),participant);
	}
	
	public List<InferenceRuleParticipation> getParticipations(DLRelationshipParticipant participant){
        return DLParticipationsHelper.getParticipations(getParticipations(), participant);
    }

	public List<DLRelationshipParticipant> getAllParticipants(){
        return DLParticipationsHelper.getAllParticipants(getParticipations());
    }
	
}
