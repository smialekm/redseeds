package eu.redseeds.rsldl.engine.util;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;

import eu.redseeds.rsldl.engine.inferenceengine.structure.InferenceRuleParticipation;
import eu.redseeds.rsldl.util.Util;

import rsldl.DLNotion;
import rsldl.DLRelationshipParticipant;
import rsldl.DLRelationshipParticipation;
import rsldl.DLRelationshipParticipationDirection;

public class DLParticipationsHelper {
	
	public static List<InferenceRuleParticipation> getRequiredParticipations(List<InferenceRuleParticipation> participations, DLRelationshipParticipant provided) {
		List<InferenceRuleParticipation> required = new ArrayList<InferenceRuleParticipation>();
        //TODO
		for (InferenceRuleParticipation p:participations)
			if(!p.getDirection().equals(DLRelationshipParticipationDirection.TARGET) && !Util.equals(p.getParticipant(), provided)){
				//TODO
				required.add(p);
			}
        return required;
	}

	public static List<InferenceRuleParticipation> getProvidedParticipations(List<InferenceRuleParticipation> participations) {
		List<InferenceRuleParticipation> provided = new ArrayList<InferenceRuleParticipation>();
        for (InferenceRuleParticipation p:participations)
        	if(p.getParticipant() instanceof DLNotion && p.getDirection().equals(DLRelationshipParticipationDirection.TARGET))
        		provided.add(p);
        //TODO
        for (InferenceRuleParticipation p:participations)
        	if(p.getParticipant() instanceof DLNotion && p.getDirection().equals(DLRelationshipParticipationDirection.UNDEFINED))
        		provided.add(p);
        return provided;
	}

	public static InferenceRuleParticipation getProvidedParticipation(List<InferenceRuleParticipation> participations, DLRelationshipParticipant participant) {
		for (InferenceRuleParticipation p:getProvidedParticipations(participations))
            if (Util.equals(participant,p.getParticipant()))
                return p;
        return null;
	}

	public static List<InferenceRuleParticipation> getParticipations(List<InferenceRuleParticipation> participations, DLRelationshipParticipant participant){
		List<InferenceRuleParticipation> result = new ArrayList<InferenceRuleParticipation>();
        for(InferenceRuleParticipation participation:participations)
        	if(participation.getParticipant().equals(participant))
        		result.add(participation);
        return result;
    }

	public static List<DLRelationshipParticipant> getAllParticipants(List<InferenceRuleParticipation> participations){
        List<DLRelationshipParticipant> r = new ArrayList<DLRelationshipParticipant>();
        for(InferenceRuleParticipation p:participations)
        	if(!r.contains(p.getParticipant()))
        		r.add(p.getParticipant());
        return r;
    }
	
	public static EList<DLRelationshipParticipation> getRequiredParticipations(EList<DLRelationshipParticipation> participations, DLRelationshipParticipant provided) {
		EList<DLRelationshipParticipation> required = new BasicEList<DLRelationshipParticipation>();
        //TODO
		for (DLRelationshipParticipation p:participations)
			if(!p.getDirection().equals(DLRelationshipParticipationDirection.TARGET) && !Util.equals(p.getParticipant(), provided)){
				//TODO
				required.add(p);
			}
        return required;
	}

	public static EList<DLRelationshipParticipation> getRequiredParticipations(EList<DLRelationshipParticipation> participations) {
		EList<DLRelationshipParticipation> provided = new BasicEList<DLRelationshipParticipation>();
        for (DLRelationshipParticipation p:participations)
        	if(p.getParticipant() instanceof DLNotion && p.getDirection().equals(DLRelationshipParticipationDirection.SOURCE))
        		provided.add(p);
        //TODO
        for (DLRelationshipParticipation p:participations)
        	if(p.getParticipant() instanceof DLNotion && p.getDirection().equals(DLRelationshipParticipationDirection.UNDEFINED))
        		provided.add(p);
        return provided;
	}
	
	public static EList<DLRelationshipParticipation> getProvidedParticipations(EList<DLRelationshipParticipation> participations) {
		EList<DLRelationshipParticipation> provided = new BasicEList<DLRelationshipParticipation>();
        for (DLRelationshipParticipation p:participations)
        	if(p.getParticipant() instanceof DLNotion && p.getDirection().equals(DLRelationshipParticipationDirection.TARGET))
        		provided.add(p);
        //TODO
        for (DLRelationshipParticipation p:participations)
        	if(p.getParticipant() instanceof DLNotion && p.getDirection().equals(DLRelationshipParticipationDirection.UNDEFINED))
        		provided.add(p);
        return provided;
	}

	public static DLRelationshipParticipation getProvidedParticipation(EList<DLRelationshipParticipation> participations, DLRelationshipParticipant participant) {
		for (DLRelationshipParticipation p:getProvidedParticipations(participations))
            if (Util.equals(participant,p.getParticipant()))
                return p;
        return null;
	}

	public static EList<DLRelationshipParticipation> getParticipations(EList<DLRelationshipParticipation> participations, DLRelationshipParticipant participant){
        EList<DLRelationshipParticipation> result = new BasicEList<DLRelationshipParticipation>();
        for(DLRelationshipParticipation participation:participations)
        	if(participation.getParticipant().equals(participant))
        		result.add(participation);
        return result;
    }

	public static EList<DLRelationshipParticipant> getAllParticipants(EList<DLRelationshipParticipation> participations){
        EList<DLRelationshipParticipant> r = new BasicEList<DLRelationshipParticipant>();
        for(DLRelationshipParticipation p:participations)
        	if(!r.contains(p.getParticipant()))
        		r.add(p.getParticipant());
        return r;
    }
	
}
