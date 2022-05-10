package eu.redseeds.rsldl.engine.inferenceengine;

import static eu.redseeds.rsldl.util.Util.toCamelCase;
import static eu.redseeds.rsldl.util.Util.toLowerCamelCase;
import static eu.redseeds.rsldl.engine.util.SimpleTypesHelper.isSimpleType;
import static eu.redseeds.rsldl.engine.util.SimpleTypesHelper.getInheritedValueTypeSource;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rsldl.DLAttributeLink;
import rsldl.DLEntity;
import rsldl.DLInheritanceCondition;
import rsldl.DLNotion;
import rsldl.DLProperty;
import rsldl.DLPropertyValueType;
import rsldl.DLRelationship;
import rsldl.DLRelationshipParticipant;
import rsldl.DLRelationshipParticipationDirection;
import rsldl.DLRelationshipParticipationMultiplicity;
import rsldl.DLTypeRole;
import rsldl.DLSubset;

import eu.redseeds.rsldl.engine.inferenceengine.structure.BackwardSubsolution;
import eu.redseeds.rsldl.engine.inferenceengine.structure.DLInferenceRule;
import eu.redseeds.rsldl.engine.inferenceengine.structure.DLSupplementRule;
import eu.redseeds.rsldl.engine.inferenceengine.structure.DLSupplementRuleSourceType;
import eu.redseeds.rsldl.engine.inferenceengine.structure.ForwardSubsolution;
import eu.redseeds.rsldl.engine.inferenceengine.structure.InferenceRule;
import eu.redseeds.rsldl.engine.inferenceengine.structure.InferenceRuleParticipation;
import eu.redseeds.rsldl.util.Util;

public class DLInferenceEngine {
	
	static boolean forward = false;
    
	public static ForwardSubsolution solve(DLNotion what, List<DLNotion> knows, DLSubset basedOn){
        return solve(what, knows, basedOn, new ArrayList<DLRelationship>());
    }
	
    public static ForwardSubsolution solve(DLNotion what, List<DLNotion> knows, DLSubset basedOn, List<DLRelationship> exclude){
        if (forward)
            return solveForward(what, knows, basedOn, exclude);
        BackwardSubsolution bs = solveBackward(what, knows, basedOn, exclude);
        return null!=bs?bs.getRoot():null;
    }  
    
    public static ForwardSubsolution solveForward(DLNotion what, List<DLNotion> knows, DLSubset basedOn, List<DLRelationship> exclude){
        Map<DLRelationshipParticipant,List<ForwardSubsolution>> mknows = new HashMap<DLRelationshipParticipant,List<ForwardSubsolution>>();
        for(DLNotion p:knows){
            List<ForwardSubsolution> tl = new ArrayList<ForwardSubsolution>();
            tl.add(new ForwardSubsolution(p,DLRelationshipParticipationMultiplicity.SINGLE));
            mknows.put(p,tl);
        }
        return solveForward(what, mknows, getInferenceRules(what,knows,basedOn,exclude));
    }
    
    private static ForwardSubsolution solveForward(DLNotion what, Map<DLRelationshipParticipant, List<ForwardSubsolution>> knows, List<InferenceRule> basedOn) {
        boolean progress = false;
        Map<DLRelationshipParticipant,DLRelationshipParticipationMultiplicity> modified = new HashMap<DLRelationshipParticipant,DLRelationshipParticipationMultiplicity>();
        for (InferenceRule r : basedOn) {
            for (InferenceRuleParticipation prv : r.getProvidedParticipations())
                if (prv.getParticipant() instanceof DLNotion && (!knows.keySet().contains(prv.getParticipant()) || knows.keySet().contains(prv.getParticipant()) && null==getFS(knows.get(prv.getParticipant()),prv.getMultiplicity()) || modified.keySet().contains(prv.getParticipant()) && Util.equals(modified.get(prv.getParticipant()), prv.getMultiplicity())) && containsAllParticipants(knows, r.getRequiredParticipations(prv.getParticipant()))) {
                	List<ForwardSubsolution> req = new ArrayList<ForwardSubsolution>();
                    for(InferenceRuleParticipation rq:r.getRequiredParticipations(prv.getParticipant())){
                    	if (rq.getParticipant() instanceof DLNotion || knows.containsKey(rq.getParticipant())){
                        	for (ForwardSubsolution e:knows.get(rq.getParticipant()))
                        		if(Util.equals(e.getMultiplicity(), prv.getMultiplicity()))
                        			req.add(e);
                        } else {
                        	List<ForwardSubsolution> tl = new ArrayList<ForwardSubsolution>();
                            tl.add(new ForwardSubsolution(rq.getParticipant(),DLRelationshipParticipationMultiplicity.SINGLE));
                            knows.put(rq.getParticipant(),tl);
                        }
                    }
                    ForwardSubsolution fs = new ForwardSubsolution(req, prv.getParticipant(), r,prv.getMultiplicity());
                    if (!knows.keySet().contains(prv.getParticipant()) || knows.keySet().contains(prv.getParticipant()) && null==getFS(knows.get(prv.getParticipant()),prv.getMultiplicity()) || fs.getDepth() < getFS(knows.get(prv.getParticipant()),prv.getMultiplicity()).getDepth()) {
                        if (knows.keySet().contains(prv.getParticipant()) && null!=getFS(knows.get(prv.getParticipant()),prv.getMultiplicity())) {
                            knows.get(prv.getParticipant()).remove(getFS(knows.get(prv.getParticipant()),prv.getMultiplicity()));
                            if (knows.get(prv.getParticipant()).isEmpty())
                                knows.remove(prv.getParticipant());
                        } else {
                            modified.put(prv.getParticipant(),prv.getMultiplicity());
                        }
                        if (!knows.keySet().contains(prv.getParticipant())){
                            List<ForwardSubsolution> tl = new ArrayList<ForwardSubsolution>();
                            tl.add(fs);
                            knows.put((DLNotion) prv.getParticipant(),tl);
                        } else
                            knows.get(prv.getParticipant()).add(fs);
                        progress = true;
                        if (prv.getParticipant().equals(what)) {
                            return fs;
                        }
                    }
                }
        }
        if (progress) {
            return solveForward(what, knows, basedOn);
        }
        return null;
    }

    public static BackwardSubsolution solveBackward(DLNotion what, List<DLNotion> knows, DLSubset basedOn, List<DLRelationship> exclude) {
        List<BackwardSubsolution> lwhat = new ArrayList<BackwardSubsolution>();
        lwhat.add(new BackwardSubsolution(what,DLRelationshipParticipationMultiplicity.SINGLE));
        return solveBackward(lwhat, knows, getInferenceRules(what,knows,basedOn,exclude));
    }
    
    private static BackwardSubsolution solveBackward(List<BackwardSubsolution> what, List<DLNotion> knows, List<InferenceRule> basedOn){
        Map<DLNotion,DLRelationshipParticipationMultiplicity> req = new HashMap<DLNotion,DLRelationshipParticipationMultiplicity>();
        for (BackwardSubsolution r:what){
            if (0<r.getDepth() &&  knows.containsAll(getNotionsFromRelationshipParticipants(r.getRequired().keySet())))
                return r;
            for(DLRelationshipParticipant rq:r.getRequired().keySet())
            	if(rq instanceof DLNotion && !knows.contains(rq) && !(req.keySet().contains((DLNotion) rq) && Util.equals(req.get((DLNotion) rq),r.getRequired().get(rq))))
            		req.put((DLNotion) rq,r.getRequired().get(rq));
        }
        List<BackwardSubsolution> del = new ArrayList<BackwardSubsolution>();
        del.addAll(what);
        for (DLNotion rq:req.keySet()){
            List<InferenceRule> tr = new ArrayList<InferenceRule>();
            for (InferenceRule r:basedOn){
            	boolean b = false;
            	for(InferenceRuleParticipation p:r.getProvidedParticipations())
            		if (Util.equals(p.getParticipant(), rq) && Util.equals(p.getMultiplicity(), req.get(rq))){
            			b=true;
            			break;
            		}
            	if (b)
            		tr.add(r);
            }
            if (!(tr.isEmpty())) {
                List<BackwardSubsolution> tmp = new ArrayList<BackwardSubsolution>();
                List<BackwardSubsolution> tmp2 = new ArrayList<BackwardSubsolution>();
                for(BackwardSubsolution r:what){
                	boolean b = false;
                	for (DLRelationshipParticipant p:r.getRequired().keySet())
                		if(Util.equals(p, rq) && Util.equals(r.getRequired().get(p), req.get(rq))){
                			b = true;
                			break;
                		}
                	if(b){
                		for(InferenceRule trn:tr)
                        	if(r.canExpandSubsolution(rq, trn))
                        		tmp.add(r.getExpandedSubsolution(rq, trn));
                		if(!tmp.isEmpty()){
                			tmp2.add(r);
                			if(del.contains(r))
                				del.remove(r);
                		}
                	}
                }
                what.removeAll(tmp2);
                what.addAll(tmp);
            }
        }
        what.removeAll(del);
        if (!what.isEmpty())
            return solveBackward(what,knows,basedOn);
        return null;
    }

    public static boolean containsAllParticipants(Map<DLRelationshipParticipant, List<ForwardSubsolution>> slt, List<InferenceRuleParticipation> prt){
        for (InferenceRuleParticipation p:prt)
        	if(p.getParticipant() instanceof DLNotion){
        		List<ForwardSubsolution> fs;
        		if (null==(fs=slt.get(p.getParticipant())) || fs.isEmpty())
        			return false;
        		if (null==getFS(fs,p.getMultiplicity()))
        			return false;
        	}
        return true;
    }
	
	public static ForwardSubsolution getFS(List<ForwardSubsolution> slst, DLRelationshipParticipationMultiplicity mlt){
        for(ForwardSubsolution s:slst)
            if (Util.equals(s.getMultiplicity(), mlt))
                return s;
        return null;
    }
	
	public static boolean isForward() {
        return forward;
    }

    public static void setForward(boolean forward) {
        DLInferenceEngine.forward = forward;
    }
    
    private static List<InferenceRule> getInferenceRules(DLNotion what, List<DLNotion> knows, DLSubset basedOn, List<DLRelationship> exclude){
    	List<InferenceRule> rules = new ArrayList<InferenceRule>();	
    	for (DLRelationship r:basedOn.getDomainRelationships())
    		if(!exclude.contains(r))
    			rules.add(new DLInferenceRule(r));
    	List<DLNotion> notions = basedOn.getDomainNotions();
    	//TODO parents
    	if (!notions.contains(what))
    		notions.add(what);
    	for(DLNotion n:knows)
    		if (!notions.contains(n))
    			notions.add(n);
        for(DLNotion n:notions){
            for(DLAttributeLink al:n.getAttributesLinks())
            	if(!DLTypeRole.TEMPORARY_ROLE.equals(al.getAttribute().getType())){
            		DLSupplementRule sr = new DLSupplementRule(toLowerCamelCase(n.getName())+".get"+toCamelCase(al.getAttribute().getName())+"();", DLSupplementRuleSourceType.ATTRIBUTE);
            		sr.addParticipant(n, DLRelationshipParticipationDirection.SOURCE);
            		sr.addParticipant(al.getAttribute(), DLRelationshipParticipationDirection.TARGET);
            		rules.add(sr);
            	}
            if (n instanceof DLEntity && DLTypeRole.IDENTITY.equals(n.getType()) && !isSimpleType(n)) {
                List<DLNotion> base = new ArrayList<DLNotion>();
                for(DLProperty a:n.getDirectAttributes()){
                    if (!DLTypeRole.TEMPORARY_ROLE.equals(a.getType()) && !base.contains(a)) {
                        base.add(a);
                    }
                }
                List<List<DLNotion>> lac = new ArrayList<List<DLNotion>>();
                List<List<DLNotion>> lp = new ArrayList<List<DLNotion>>();
                lp.add(new ArrayList<DLNotion>());
                for(DLInheritanceCondition i:n.getInheritanceConditions()){
                    if (1 == i.getParents().size()) {
                        for(List<DLNotion> l:lp){
                            l.add(i.getParents().get(0));
                        }
                    } else {
                        List<List<DLNotion>> tlp = new ArrayList<List<DLNotion>>();
                        for(List<DLNotion> l:lp){
                            for(DLNotion p:i.getParents()){
                                List<DLNotion> nl = new ArrayList<DLNotion>();
                                nl.addAll(l);
                                nl.add(p);
                                tlp.add(nl);
                            }
                        }
                        lp.clear();
                        lp.addAll(tlp);
                    }
                }
                for(List<DLNotion> l:lp)
                	if(!l.isEmpty()){
                    	List<DLNotion> lla = new ArrayList<DLNotion>();
                    	lla.addAll(base);
                    	for(DLNotion p:l)
                    		for(DLProperty a:p.getAllAttributes())
                    			if(!lla.contains(a) && !DLTypeRole.TEMPORARY_ROLE.equals(a.getType()))
                    				lla.add(a);
                    	if (!lla.isEmpty()) {
                    		lac.add(lla);
                    	}
                }
                for (List<DLNotion> ac:lac){
                    DLSupplementRule sr = new DLSupplementRule("new M"+toCamelCase(n.getName())+"(", DLSupplementRuleSourceType.CONSTRUCTOR);
                    sr.addParticipant(n, DLRelationshipParticipationDirection.TARGET);
                    boolean first = true;
                    DLNotion sts = getInheritedValueTypeSource((DLEntity) n);
                    if (null!=sts) {
                    	 sr.addParticipant(sts, DLRelationshipParticipationDirection.SOURCE);
                    	 sr.setName(sr.getName()+toLowerCamelCase(sts.getName()));
                         first = false;
                    }
                    for (DLNotion a:ac){
                        sr.addParticipant(a, DLRelationshipParticipationDirection.SOURCE);
                        if (first){
                            sr.setName(sr.getName()+toLowerCamelCase(a.getName()));
                            first = false;
                        } else sr.setName(sr.getName()+","+toLowerCamelCase(a.getName()));
                    }
                    sr.setName(sr.getName()+");");
                    rules.add(sr);
                }
            }
            for (DLInheritanceCondition ic:n.getInheritanceConditions())
            	if(1==ic.getParents().size()) {
            		if (DLTypeRole.IDENTITY.equals(n.getType())) {
            			if (!isSimpleType(n)) {
            				DLSupplementRule sr = new DLSupplementRule("(M"+toCamelCase(ic.getParents().get(0).getName())+") "+toLowerCamelCase(n.getName())+";",DLSupplementRuleSourceType.STATIC_CAST);
            				sr.addParticipant(n, DLRelationshipParticipationDirection.SOURCE);
            				sr.addParticipant(ic.getParents().get(0), DLRelationshipParticipationDirection.TARGET);
            				rules.add(sr);
            				sr = new DLSupplementRule("new List<M"+toCamelCase(ic.getParents().get(0).getName())+">("+toLowerCamelCase(n.getName())+"s);",DLSupplementRuleSourceType.STATIC_CAST);
            				sr.addParticipant(n, DLRelationshipParticipationDirection.SOURCE,DLRelationshipParticipationMultiplicity.MULTIPLE);
            				sr.addParticipant(ic.getParents().get(0), DLRelationshipParticipationDirection.TARGET,DLRelationshipParticipationMultiplicity.MULTIPLE);
            				rules.add(sr);
            			} else {
            				DLSupplementRule sr = new DLSupplementRule(toLowerCamelCase(n.getName())+";",DLSupplementRuleSourceType.STATIC_CAST);
            				sr.addParticipant(n, DLRelationshipParticipationDirection.SOURCE);
            				sr.addParticipant(ic.getParents().get(0), DLRelationshipParticipationDirection.TARGET);
            				rules.add(sr);
            				sr = new DLSupplementRule(toLowerCamelCase(n.getName())+"s;",DLSupplementRuleSourceType.STATIC_CAST);
            				sr.addParticipant(n, DLRelationshipParticipationDirection.SOURCE,DLRelationshipParticipationMultiplicity.MULTIPLE);
            				sr.addParticipant(ic.getParents().get(0), DLRelationshipParticipationDirection.TARGET,DLRelationshipParticipationMultiplicity.MULTIPLE);
            				rules.add(sr);
            			}
            		} else {
            			//TODO
            			
            		}
            	}
            if (n instanceof DLProperty && (DLPropertyValueType.SET.equals(((DLProperty) n).getValueType()) || DLPropertyValueType.ORDERED_SET.equals(((DLProperty) n).getValueType())) && null!=((DLProperty) n).getSetType()) {
            	DLSupplementRule sr = new DLSupplementRule("new M"+toCamelCase(n.getName())+"("+toLowerCamelCase(((DLProperty) n).getSetType().getName())+"s);", DLSupplementRuleSourceType.CONSTRUCTOR);
            	sr.addParticipant(n, DLRelationshipParticipationDirection.TARGET);
            	sr.addParticipant(((DLProperty) n).getSetType(), DLRelationshipParticipationDirection.SOURCE,DLPropertyValueType.SET.equals(((DLProperty) n).getValueType())?DLRelationshipParticipationMultiplicity.MULTIPLE:DLRelationshipParticipationMultiplicity.ORDERED_MULTIPLE);
            	rules.add(sr);
            	sr = new DLSupplementRule(toLowerCamelCase(n.getName())+".getValue();", DLSupplementRuleSourceType.PROPERTY_SET_VALUE);
            	sr.addParticipant(n, DLRelationshipParticipationDirection.SOURCE);
            	sr.addParticipant(((DLProperty) n).getSetType(), DLRelationshipParticipationDirection.TARGET,DLPropertyValueType.SET.equals(((DLProperty) n).getValueType())?DLRelationshipParticipationMultiplicity.MULTIPLE:DLRelationshipParticipationMultiplicity.ORDERED_MULTIPLE);
            	rules.add(sr);
            }
            if(DLTypeRole.TEMPORARY_ROLE.equals(n.getType()) && !n.isDerived()) {
            	//TODO
            	
            }
        }
        return rules;
    }
	
    private static List<DLNotion> getNotionsFromRelationshipParticipants(Collection<DLRelationshipParticipant> participants){
    	List<DLNotion> notions = new ArrayList<DLNotion>();
    	for(DLRelationshipParticipant p:participants)
    		if(p instanceof DLNotion)
    			notions.add((DLNotion) p);
    	return notions;
    }
    
}
