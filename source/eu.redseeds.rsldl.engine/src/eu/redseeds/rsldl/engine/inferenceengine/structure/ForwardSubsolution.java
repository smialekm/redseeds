package eu.redseeds.rsldl.engine.inferenceengine.structure;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import eu.redseeds.rsldl.engine.inferenceengine.DLInferenceEngine;

import rsldl.DLRelationshipParticipant;
import rsldl.DLRelationshipParticipationMultiplicity;

public class ForwardSubsolution {
	List<ForwardSubsolution> required;
    DLRelationshipParticipant provided;
    InferenceRule rule;
    DLRelationshipParticipationMultiplicity multiplicity;
    int depth = 0;
    
    
    public ForwardSubsolution(DLRelationshipParticipant provided, DLRelationshipParticipationMultiplicity multiplicity){
        required = new ArrayList<ForwardSubsolution>();
        this.provided = provided;
        rule = null;
        this.multiplicity = multiplicity;
    }
    
    private ForwardSubsolution(DLRelationshipParticipant provided, int depth, DLRelationshipParticipationMultiplicity multiplicity){
        required = new ArrayList<ForwardSubsolution>();
        this.provided = provided;
        rule = null;
        this.depth = depth;
        this.multiplicity = multiplicity;
    }

    public ForwardSubsolution(List<ForwardSubsolution> required, DLRelationshipParticipant provided, InferenceRule rule, DLRelationshipParticipationMultiplicity multiplicity) {
        this.required = required;
        this.provided = provided;
        this.rule = rule;
        this.multiplicity = multiplicity;
        for(ForwardSubsolution req:required)
        	if(req.getDepth()+1>depth)
		        depth=req.getDepth()+1;
    }

    public DLRelationshipParticipant getProvided() {
        return provided;
    }

    public List<ForwardSubsolution> getRequired() {
        return required;
    }

    public InferenceRule getRule() {
        return rule;
    }
    
    public int getDepth() {
        return depth;
    }

    public DLRelationshipParticipationMultiplicity getMultiplicity() {
        return multiplicity;
    }

    public void setMultiplicity(DLRelationshipParticipationMultiplicity multiplicity) {
        this.multiplicity = multiplicity;
    }

    @Override
    public String toString() {
        String s = required.isEmpty()?"":"[";
        if (!required.isEmpty()){
        	for(ForwardSubsolution r:required)
        		s+=r.toString();
        	s+="::=";
        }
        s+=provided.getName()+(required.isEmpty()?"":"]");
        return s;
    }
    
    public void expandLeaf(InferenceRule rule, Map<DLRelationshipParticipant,List<ForwardSubsolution>> nodes){
        if (null!=this.rule || !this.required.isEmpty()) return;
        this.rule = rule;
        for(InferenceRuleParticipation rq:rule.getRequiredParticipations(provided)){
            ForwardSubsolution fs;
            if (nodes.containsKey(rq.getParticipant()) && null!=(fs=DLInferenceEngine.getFS(nodes.get(rq.getParticipant()),rq.getMultiplicity())))
                required.add(fs);
            else {
                fs = new ForwardSubsolution(rq.getParticipant(),depth-1,rq.getMultiplicity());
                required.add(fs);
                if (nodes.containsKey(rq.getParticipant()))
                    nodes.get(rq.getParticipant()).add(fs);
                else {
                    List<ForwardSubsolution> tl = new ArrayList<ForwardSubsolution>();
                    tl.add(fs);
                    nodes.put(rq.getParticipant(), tl); 
                }
            }
        }
    }
    
    public List<ForwardSubsolution> getFullRequired(){
        List<ForwardSubsolution> l = new ArrayList<ForwardSubsolution>();
        for(ForwardSubsolution s:required){
            if (null==s.getRule()){
                if (!l.contains(s))
                    l.add(s);
            } else
                for(ForwardSubsolution ss:s.getFullRequired())
                	if(!l.contains(ss))
                		l.add(ss);
        }
        return l;
    }
    
    public boolean deepContains(ForwardSubsolution fs) {
    	if (required.contains(fs))
    		return true;
    	for (ForwardSubsolution r:required)
    		if (r.deepContains(fs))
    			return true;
    	return false;
    }
    
}
