package eu.redseeds.rsldl.engine.inferenceengine.structure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import eu.redseeds.rsldl.engine.inferenceengine.DLInferenceEngine;
import eu.redseeds.rsldl.util.Util;

import rsldl.DLRelationshipParticipant;
import rsldl.DLRelationshipParticipationMultiplicity;

public class BackwardSubsolution {
	ForwardSubsolution root;
    Map<DLRelationshipParticipant,List<ForwardSubsolution>> nodes = new HashMap<DLRelationshipParticipant,List<ForwardSubsolution>>();
    List<ForwardSubsolution> leafs = new ArrayList<ForwardSubsolution>();
    int depth = 0;

    public BackwardSubsolution(DLRelationshipParticipant goal, DLRelationshipParticipationMultiplicity mlt){
        root = new ForwardSubsolution(goal, mlt);
        List<ForwardSubsolution> tl = new ArrayList<ForwardSubsolution>();
        tl.add(root);
        nodes.put(goal, tl);
        leafs.add(root);
    }
    
    private BackwardSubsolution(BackwardSubsolution bs){
        this.root = new ForwardSubsolution(bs.root.provided,bs.root.multiplicity);
        List<ForwardSubsolution> tl = new ArrayList<ForwardSubsolution>();
        tl.add(root);
        nodes.put(bs.root.provided, tl);
        copyTree(this.root,bs.root);
        this.depth=bs.depth+1;
    }
    
    private void copyTree(ForwardSubsolution destination, ForwardSubsolution source){
        if (null==source.getRule()){
            leafs.add(destination);
            return;
        }
        destination.expandLeaf(source.getRule(),nodes);
        Map<DLRelationshipParticipant,ForwardSubsolution> lu = new HashMap<DLRelationshipParticipant,ForwardSubsolution>();
        for (ForwardSubsolution rq:source.getRequired())
            lu.put(rq.getProvided(), rq);
        for (ForwardSubsolution rq:destination.getRequired())
        	if (null==rq.getRule())
        		copyTree(rq,lu.get(rq.getProvided()));
    }
    
    public Map<DLRelationshipParticipant,DLRelationshipParticipationMultiplicity> getRequired() {
        Map<DLRelationshipParticipant,DLRelationshipParticipationMultiplicity> required = new HashMap<DLRelationshipParticipant,DLRelationshipParticipationMultiplicity>();
        for (ForwardSubsolution leaf:leafs){
        	boolean b = true;
        	for (DLRelationshipParticipant p:required.keySet())
        		if(Util.equals(p, leaf.getProvided()) && Util.equals(required.get(p), leaf.getMultiplicity())){
        			b = false;
        			break;
        		}
        	if(b)
        		required.put(leaf.getProvided(),leaf.getMultiplicity());
        }
        return required;
    }

    public int getDepth() {
        return depth;
    }
    
    @Override
    public String toString() {
        return root.toString();
    }
    
    public BackwardSubsolution getExpandedSubsolution(DLRelationshipParticipant element, InferenceRule rule){
        BackwardSubsolution r = new BackwardSubsolution(this);
        List<ForwardSubsolution> toAdd = new ArrayList<ForwardSubsolution>();
        List<ForwardSubsolution> toRemove = new ArrayList<ForwardSubsolution>();
        for (ForwardSubsolution leaf:r.leafs)
        	if(leaf.getProvided().equals(element)){
        		leaf.expandLeaf(rule,r.nodes);
        		for (ForwardSubsolution rq:leaf.getRequired())
        			if(null==rq.getRule())
        				toAdd.add(rq);
        		toRemove.add(leaf);
        }
        r.leafs.removeAll(toRemove);
        r.leafs.addAll(toAdd);
        return r;
    }
    
    public boolean canExpandSubsolution(DLRelationshipParticipant element, InferenceRule rule){
        for (ForwardSubsolution leaf:leafs)
    		if(leaf.getProvided().equals(element))
    			for(InferenceRuleParticipation rq:rule.getRequiredParticipations(element)) {
    				ForwardSubsolution fs;
    				if (nodes.containsKey(rq.getParticipant()) && null!=(fs=DLInferenceEngine.getFS(nodes.get(rq.getParticipant()),rq.getMultiplicity())) && fs.deepContains(leaf))	
    					return false;
    			}
        return true;
    }
    
    public ForwardSubsolution getRoot(){
        return root;
    }
    
}
