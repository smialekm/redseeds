package eu.redseeds.rsldl.engine;

import static eu.redseeds.rsldl.engine.util.SimpleTypesHelper.isSimpleType;
import static eu.redseeds.rsldl.util.Util.toCamelCase;
import static eu.redseeds.rsldl.util.Util.toLowerCamelCase;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;

import rsldl.DLAlghoritmicTransition;
import rsldl.DLAttributeLink;
import rsldl.DLConditionPattern;
import rsldl.DLConditionPatternType;
import rsldl.DLDataBasedReference;
import rsldl.DLDomain;
import rsldl.DLEntity;
import rsldl.DLIdentityCondition;
import rsldl.DLInheritanceCondition;
import rsldl.DLNamedLink;
import rsldl.DLNotion;
import rsldl.DLPatternBasedReference;
import rsldl.DLPatternBasedTransition;
import rsldl.DLPrimitive;
import rsldl.DLProperty;
import rsldl.DLReference;
import rsldl.DLRelationship;
import rsldl.DLRelationshipParticipant;
import rsldl.DLRelationshipParticipation;
import rsldl.DLRelationshipParticipationDirection;
import rsldl.DLRelationshipParticipationMultiplicity;
import rsldl.DLSubset;
import rsldl.DLTransition;
import rsldl.DLTransitionPatternType;
import rsldl.DLTypeRole;
import rsldl.DLValidityCondition;
import eu.redseeds.rsldl.engine.code.GAttribute;
import eu.redseeds.rsldl.engine.code.GClass;
import eu.redseeds.rsldl.engine.code.GInterface;
import eu.redseeds.rsldl.engine.code.GMethod;
import eu.redseeds.rsldl.engine.code.GParameter;
import eu.redseeds.rsldl.engine.code.utilities.GMethodSequenceComparator;
import eu.redseeds.rsldl.engine.inferenceengine.DLInferenceEngine;
import eu.redseeds.rsldl.engine.inferenceengine.structure.DLInferenceRule;
import eu.redseeds.rsldl.engine.inferenceengine.structure.DLSupplementRule;
import eu.redseeds.rsldl.engine.inferenceengine.structure.ForwardSubsolution;
import eu.redseeds.rsldl.engine.structure.DLTransformationEngine;
import eu.redseeds.rsldl.engine.util.DLParticipationsHelper;
import eu.redseeds.rsldl.util.Util;
import eu.redseeds.scl.model.BusinessLayerFacade;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionDTO;

public class JavaDLTransformationEngine extends DLTransformationEngine {
	
	private static final String ITER = "$_iter", VAR = "$_var";
	
	@Override
    public GMethod solveQuery(String what,String [] knows, String basedOn){
        DLSubset domain = (null==basedOn || basedOn.isEmpty())?null:repository.getDomain(basedOn);
        if (null==domain)
            domain=repository;
        DLNotion pwhat = domain.getNotion(what);
        List<DLNotion> pknows = domain.getNotions(new BasicEList<String>(Arrays.asList(knows)));
        if (null==pwhat)
            return null;
        ForwardSubsolution solution = DLInferenceEngine.solve(pwhat, pknows, domain);
        if (null == solution) {
            return null;
        }
        if (!result.containsKey("M" + toCamelCase(pwhat.getName())) && !(isSimpleType(pwhat)))
    		generateStructureForNotion(pwhat);
        List<String> var = new ArrayList<String>();
        String s = recursiveDerivationCodeGeneration(solution, var, 0);
        GClass cl = getFinalClass(solution);
        if (null==cl && null==(cl=(GClass) result.get("MS"+toCamelCase(pwhat.getName())))){
        	cl = new GClass("MS" + toCamelCase(pwhat.getName()));
        	cl.setDLClass(false);
        	result.put(cl.getName(), cl);
        }
        if (!var.isEmpty()){
            String name = "get"+toCamelCase(solution.getProvided().getName());
            String type = isSimpleType(pwhat) ? getTypeText(pwhat,false) : "IM" + toCamelCase(pwhat.getName());
            GMethod mt = new GMethod(name, type);
            mt.setStatic(true);
            for(ForwardSubsolution p:solution.getFullRequired())
            	if(pknows.contains(p.getProvided())){
            		String ptype = getTypeFromParticipant(p.getProvided(), !DLRelationshipParticipationMultiplicity.SINGLE.equals(p.getMultiplicity()));
                	mt.addParameter(new GParameter(toLowerCamelCase(p.getProvided().getName()), ptype));
            	}
            if (!(solution.getRule() instanceof DLSupplementRule))
            	s = s.replace(" "+cl.getName()+".", " ");
            mt.setCode(s);
            cl.addMethod(mt);
            return mt;
        }
        String name=s.substring(s.indexOf('.')+1,s.indexOf('('));
        for (GMethod m:cl.getMethods())
            if (name.equals(m.getName()))
                return m;
        return null;
    }
	
	private GClass getFinalClass(ForwardSubsolution solution) {
		GClass cl = null;
		if (solution.getRule() instanceof DLInferenceRule) {
			cl = (GClass) result.get("MS"+toCamelCase(solution.getRule().getName()));
			if (null!=cl)
				return cl;
		}
		if (1==solution.getRequired().size() && null!=solution.getRequired().get(0).getRule())
			return getFinalClass(solution.getRequired().get(0));
		return null;
	}
	
	@Override
	public GMethod solveTransform(String to, String from, String basedOn) {
		DLSubset domain = (null==basedOn || basedOn.isEmpty())?null:repository.getDomain(basedOn);
        if (null==domain)
            domain=repository;
        DLNotion pto = domain.getNotion(to);
        DLNotion pfrom = domain.getNotion(from);
        if (isSimpleType(pto) || isSimpleType(pfrom) || !canTransform(pto,pfrom))
			return null;
        if (null==result.get("M" + toCamelCase(pfrom.getName())))
    		generateStructureForNotion(pfrom);
        if (null==result.get("M" + toCamelCase(pto.getName())))
    		generateStructureForNotion(pto);
        GClass cl = (GClass) result.get("MS" + toCamelCase(pto.getName()));
		if (null==cl) {
			cl = new GClass("MS" + toCamelCase(pto.getName()));
        	cl.setDLClass(false);
        	result.put(cl.getName(), cl);
		}
        GMethod tm = new GMethod("transform"+toCamelCase(pfrom.getName())+"To"+toCamelCase(pto.getName()), "M"+toCamelCase(pto.getName()));
		tm.setStatic(true);
		tm.addParameter(new GParameter(toLowerCamelCase(pfrom.getName()), toCamelCase(pfrom.getName())));
		String code = "/tif(check"+toCamelCase(pto.getName())+")\n\t\treturn new M"+toCamelCase(pto.getName())+"("+toLowerCamelCase(pfrom.getName())+");\n\treturn null;";
		tm.setCode(code);
		cl.addMethod(tm);
		return tm;
	}
	
	private boolean canTransform(DLNotion pto, DLNotion pfrom) {
		if(DLTypeRole.IDENTITY.equals(pto.getType()))
			return false;
		for(DLInheritanceCondition ic:pto.getInheritanceConditions())
			if (ic.getParents().contains(pfrom))
				return true;
		return false;
	}

	@Override
	public void solveCRUD(String what, String basedOn, BusinessLayerFacade bf) {
		DLSubset domain = (null==basedOn || basedOn.isEmpty())?null:repository.getDomain(basedOn);
        if (null==domain)
            domain=repository;
        NotionDTO not = bf.getNotionDTO(what);
        if(null==not.getMainConcept())
        	return;
        DLNotion pwhat = domain.getNotion(not.getMainConcept().getName());
        if (isSimpleType(pwhat))
			return;
		GClass cl = (GClass) result.get("M" + toCamelCase(pwhat.getName()));
		if (null==cl)
    		cl=generateStructureForNotion(pwhat);
	}
	
	private void generateStructureForRelationshipParticipant(DLRelationshipParticipant participant){
		if (participant instanceof DLNotion)
			generateStructureForNotion((DLNotion) participant);
		else
			generateStructureForPrimitive((DLPrimitive) participant);
	}

    private GClass generateStructureForNotion(DLNotion notion) {
    	GClass repr = new GClass("M" + toCamelCase(notion.getName()));
    	result.put(repr.getName(), repr);
        GInterface irepr = new GInterface("I" + repr.getName());
        repr.addImplementedInterface(irepr);
        for(DLInheritanceCondition i:notion.getInheritanceConditions())
            for(DLNotion p:i.getParents()){
                if (!result.containsKey("M" + toCamelCase(p.getName())))
                    generateStructureForNotion(p);
                irepr.addExtendedInterface((GInterface) result.get("IM" + toCamelCase(p.getName())));
            }
        if (notion instanceof DLProperty)
            addAttribute(repr, "value", getTypeText(notion,false));
        if (DLTypeRole.IDENTITY.equals(notion.getType()))
            for(DLInheritanceCondition i:notion.getInheritanceConditions())
                for(DLNotion p:i.getParents())
                    for(GAttribute a:((GClass) result.get("M" + toCamelCase(p.getName()))).getAttributes())
                    	if(!containsAttribute(repr, a.getName()))
                        	addAttribute(repr, a.getName(), a.getType());
        List<String[]> aa = new ArrayList<String[]>();
        for(DLProperty a:notion.getDirectAttributes()){
            String[] av = new String[2];
            av[0] = toLowerCamelCase(a.getName());
            av[1] = a.getDirectAttributes().isEmpty() ? getTypeText(a,false) : "IM" + toCamelCase(a.getName());
            if (!containsAttribute(repr, av[0])) {
                addAttribute(repr, a, notion);
                if (!DLTypeRole.TEMPORARY_ROLE.equals(a.getType())) {
                    aa.add(av);
                }
            } else {
                getAttribute(repr, av[0]).setType(av[1]);
            }
        }
        for(DLInheritanceCondition i:notion.getInheritanceConditions())
            for(DLNotion p:i.getParents())
                for(GMethod m:((GClass) result.get("M" + toCamelCase(p.getName()))).getMethods())
                	if(!m.getReturnType().isEmpty() && !m.isStatic() && !repr.getMethods().contains(m)){
                		GMethod nm = new GMethod(m.getName(), m.getReturnType());
                		for(GParameter pr:m.getParameters())
                			nm.addParameter(new GParameter(pr.getName(), pr.getType()));
                		nm.setCode(m.getCode());
                		repr.addMethod(nm);
                	}
        List<GMethod> cnstrs = new ArrayList<GMethod>();
        switch (notion.getType()) {
            case IDENTITY:
                List<List<DLNotion>> lp = new ArrayList<List<DLNotion>>();
                lp.add(new ArrayList<DLNotion>());
                for(DLInheritanceCondition i:notion.getInheritanceConditions()){
                    if (1 == i.getParents().size()) {
                        for(List<DLNotion> l:lp)
                            l.add(i.getParents().get(0));
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
                List<List<GAttribute>> la = new ArrayList<List<GAttribute>>();
                for(List<DLNotion> l:lp)
                	if(!l.isEmpty()){
                		ArrayList<GAttribute> lla = new ArrayList<GAttribute>();
                		for(DLNotion p:l)
                			for(GAttribute a:((GClass) result.get("M" + toCamelCase(p.getName()))).getAttributes())
                				if(!lla.contains(a))
                					lla.add(a);
                		if (!lla.isEmpty()) {
                			la.add(lla);
                		}
                	}
                for(List<GAttribute> l:la){
                    GMethod cnstr = new GMethod(repr.getName(), "");
                    boolean first = true;
                    for (GAttribute a : l) {
                        cnstr.addParameter(new GParameter(a.getName(), a.getType()));
                        if (first) {
                            cnstr.setCode("this." + a.getName() + "=" + a.getName() + ";");
                            first = false;
                        } else {
                            cnstr.setCode(cnstr.getCode() + "\nthis." + a.getName() + "=" + a.getName() + ";");
                        }
                    }
                    cnstrs.add(cnstr);
                }
                break;
            case PERSISTENT_ROLE:
            case TEMPORARY_ROLE:
                List<DLNotion> parents = new ArrayList<DLNotion>();
                for(DLInheritanceCondition i:notion.getInheritanceConditions())
                    for(DLNotion p:i.getParents())
                    	if(!parents.contains(p))
                    		parents.add(p);
                if (1 < notion.getInheritanceConditions().size()) {
                    for(DLNotion p:parents){
                        GMethod cm = new GMethod((DLTypeRole.TEMPORARY_ROLE.equals(notion.getType()) ? "canTreatAs" : "canBe") + repr.getName(), "boolean");
                        String name = toLowerCamelCase(p.getName());
                        cm.addParameter(new GParameter(name, "IM" + toCamelCase(p.getName())));
                        cm.setStatic(true);
                        String s = "List<Class> dlc = new ArrayList();\nfor (IDLClass cl:"
                                + name + ".getDLRoles())\n\tdlc.add(cl.getClass());\nif (dlc.contains("
                                + name + ".getClass()))\n\treturn false;\ndlc.add("
                                + name + ".getClass());\n";
                        for(DLInheritanceCondition i:notion.getInheritanceConditions()){
                            boolean first = true;
                            String s2 = "if (";
                            for (DLNotion pr : i.getParents()) {
                                if (first) {
                                    s2 += "!dlc.contains(M" + toCamelCase(pr.getName()) + ".class)";
                                    first = false;
                                } else {
                                    s2 += "&& !dlc.contains(M" + toCamelCase(pr.getName()) + ".class)";
                                }
                                s2 += ")\n\treturn false;";
                            }
                            s+=s2 + "\n";
                        }
                        cm.setCode(s + "return true;");
                        repr.addMethod(cm);
                    }
                }
                if (DLTypeRole.TEMPORARY_ROLE.equals(notion.getType())) {
                    break;
                }
                if (parents.isEmpty()) {
                    GMethod ecnstr = new GMethod(repr.getName(), "");
                    ecnstr.addParameter(new GParameter("dlClass", "IDLClass"));
                    ecnstr.setCode("if (!dlClass.getDLRoles().isEmpty()){\n\tdlRoles.addAll(dlClass.getDLRoles());\n\tdlClass.getDLRoles().clear();\n}\ndlRoles.add(dlClass);");
                    cnstrs.add(ecnstr);
                } else {
                    for(DLNotion p:parents){
                        GMethod cnstr = new GMethod(repr.getName(), "");
                        String name = toLowerCamelCase(p.getName());
                        cnstr.addParameter(new GParameter(name, "IM" + toCamelCase(p.getName())));
                        cnstr.setCode("if (!" + name + ".getDLRoles().isEmpty()){\n\tdlRoles.addAll(" + name + ".getDLRoles());\n\t"
                                + name + ".getDLRoles().clear();\n}\ndlRoles.add(" + name + ");");
                        cnstrs.add(cnstr);
                    }
                }
                break;
        }
        GMethod ecnstr = null;
        if (cnstrs.isEmpty()) {
            ecnstr = new GMethod(repr.getName(), "");
            cnstrs.add(ecnstr);
        }
        if (!aa.isEmpty() || notion instanceof DLProperty) {
            List<GMethod> tcnstrs = new ArrayList<GMethod>();
            String acode = "";
            if (!aa.isEmpty()) {
                boolean first = true;
                for (String[] a : aa) {
                    if (first) {
                        acode += "this." + a[0] + "=" + a[0] + ";";
                        first = false;
                    } else {
                        acode += "\nthis." + a[0] + "=" + a[0] + ";";
                    }
                }
            }
            for (GMethod c : cnstrs) {
                GMethod tcnstr;
                if (notion instanceof DLProperty && (aa.isEmpty() || !DLTypeRole.IDENTITY.equals(notion.getType()))) {
                    tcnstr = new GMethod(c.getName(), c.getReturnType());
                    for (GParameter p : c.getParameters()) {
                        tcnstr.addParameter(new GParameter(p.getName(), p.getType()));
                    }
                    tcnstr.addParameter(new GParameter("value", getTypeText(notion,false)));
                    tcnstr.setCode(c.getCode() + (c.getCode().isEmpty() ? "" : "\n") + "this.value=value;");
                    tcnstrs.add(tcnstr);
                }
                if (!aa.isEmpty()) {
                    tcnstr = new GMethod(c.getName(), c.getReturnType());
                    for (GParameter p : c.getParameters()) {
                        tcnstr.addParameter(new GParameter(p.getName(), p.getType()));
                    }
                    tcnstr.setCode(c.getCode());
                    if (notion instanceof DLProperty) {
                        tcnstr.addParameter(new GParameter("value", getTypeText(notion,false)));
                        tcnstr.setCode(tcnstr.getCode() + (tcnstr.getCode().isEmpty() ? "" : "\n") + "this.value=value;");
                    }
                    for (String[] a : aa) {
                        tcnstr.addParameter(new GParameter(a[0], a[1]));
                    }
                    tcnstr.setCode(tcnstr.getCode() + (tcnstr.getCode().isEmpty() ? "" : "\n") + acode);
                    tcnstrs.add(tcnstr);
                }
            }
            if (DLTypeRole.IDENTITY.equals(notion.getType())) {
                cnstrs.clear();
            }
            cnstrs.addAll(tcnstrs);
        }
        if (null != ecnstr && 1 < cnstrs.size()) {
            cnstrs.remove(ecnstr);
        }
        for(GMethod c:cnstrs)
            repr.addMethod(c);
        for(DLRelationship rel:repository.getDomainRelationships())
        	if(rel instanceof DLDataBasedReference && DLParticipationsHelper.getAllParticipants(rel.getParticipations()).contains(notion)){
        		DLDataBasedReference r = (DLDataBasedReference) rel;
        		boolean posibleSource = false;
        		for (DLRelationshipParticipation p : DLParticipationsHelper.getParticipations(r.getParticipations(),notion))
                	if (!DLRelationshipParticipationDirection.TARGET.equals(p.getDirection()))
                    	posibleSource = true;
            	if (posibleSource) {
                	for(DLRelationshipParticipation p:r.getParticipations())
                		if(!Util.equals(notion, p.getParticipant())){
                			boolean allPosibleSources = true;
                			for (DLRelationshipParticipation p2 : r.getParticipations()) {
                				if (!Util.equals(notion, p2.getParticipant()) && !Util.equals(p.getParticipant(), p2.getParticipant()) && DLRelationshipParticipationDirection.TARGET.equals(p2.getDirection())) {
                					allPosibleSources = false;
                				}
                			}
                			if (allPosibleSources) {
                				GMethod gm = new GMethod("get" + toCamelCase(r.getName()) + "s", "List<IM" + toCamelCase(p.getParticipant().getName()) + ">");
                				for(DLRelationshipParticipation p2:r.getParticipations())
                					if(!Util.equals(notion, p2.getParticipant()) && !Util.equals(p.getParticipant(), p2.getParticipant()))
                						gm.addParameter(new GParameter(toLowerCamelCase(p2.getName()), "IM" + toCamelCase(p2.getParticipant().getName())));
                				//TODO
                				
                				repr.addMethod(gm);
                				GMethod am = new GMethod("add" + toCamelCase(r.getName()), "void");
                				am.addParameter(new GParameter(toLowerCamelCase(p.getName()), "IM" + toCamelCase(p.getParticipant().getName())));
                				for(DLRelationshipParticipation p2:r.getParticipations())
                					if(!Util.equals(notion, p2.getParticipant()) && !Util.equals(p.getParticipant(), p2.getParticipant()))
                						am.addParameter(new GParameter(toLowerCamelCase(p2.getName()), "IM" + toCamelCase(p2.getParticipant().getName())));
                				//TODO
                				
                				repr.addMethod(am);
                			}
                		}
            	}
        	}
        if (needValidation(notion)) {
        	GMethod vm = new GMethod("isValid", "boolean");
        	String code = "\tboolean result = true;\n";
        	for(DLValidityCondition c:getAllValidityConditions(notion))
        		code+=generateCheckingCodeFromPattern(c.getPattern(),notion).replace("return", "result = result &&");
        	for(DLProperty a:notion.getAllAttributes())
        		if (needValidation(a)) {
        			if (!isSimpleType(a))
        				code+="\tresult = result && "+toLowerCamelCase(a.getName())+".isValid();\n";
        			else for (DLValidityCondition c:getAllValidityConditions(a))
                		code+=generateCheckingCodeFromPattern(c.getPattern(),notion).replace("return", "result = result &&");
        		}
        	code+="\treturn result;";
        	vm.setCode(code);
        	repr.addMethod(vm);
        }
        GClass srepr = null;
        if (isPersistant(notion) || needIdentityCheck(notion)) {
        	srepr = new GClass("MS" + toCamelCase(notion.getName()));
        	srepr.setDLClass(false);
        	result.put(srepr.getName(), srepr);
        }
        if (needIdentityCheck(notion)) {
        	String code = "\tboolean result = true;\n";
        	for(DLIdentityCondition c:DLTypeRole.IDENTITY.equals(notion.getType())?getAllIdentityConditions(notion):notion.getIdentityConditions())
        		code+=generateCheckingCodeFromPattern(c.getPattern(),notion).replace("return", "result = result &&");
        	code+="\treturn result;";
        	for(GMethod c:cnstrs)
        		if (!c.getParameters().isEmpty()) {
        			//TODO
        			GMethod im = new GMethod("check"+toCamelCase(notion.getName()), "boolean");
        			im.setStatic(true);
        			for (GParameter p:c.getParameters())
	        			im.addParameter(new GParameter(p.getName(), p.getType()));
        			im.setCode(code);
        			srepr.addMethod(im);
        		}
        }
        if (isPersistant(notion)) {
        	//TODO
        	GMethod cm = new GMethod("get"+toCamelCase(notion.getName())+"s", "List<"+repr.getName()+">");
        	cm.setStatic(true);
        	srepr.addMethod(cm);
        	cm = new GMethod("update", "void");
        	repr.addMethod(cm);
        	cm = new GMethod("delete", "void");
        	repr.addMethod(cm);
        }
        Collections.sort(repr.getMethods(),new GMethodSequenceComparator());
        fillInterface(irepr, repr);
        result.put(irepr.getName(), irepr);
        return repr;
    }
    
    private boolean isPersistant(DLNotion notion) {
    	 if (DLTypeRole.TEMPORARY_ROLE.equals(notion.getType()) || isSimpleType(notion))
    		 return false;
    	 //TODO
    	 
    	 return true;
    }
    
    private boolean needIdentityCheck(DLNotion n) {
    	if (!n.getIdentityConditions().isEmpty())
    		return true;
    	if (!DLTypeRole.IDENTITY.equals(n.getType()))
    		return false;
    	for (DLInheritanceCondition ic:n.getInheritanceConditions())
    		if (1==ic.getParents().size() && needIdentityCheck(ic.getParents().get(0)))
    			return true;
    	return false;
    }
    
    private boolean needValidation(DLNotion n) {
    	if(!n.getValidityConditions().isEmpty())
    		return true;
    	for (DLProperty a:n.getDirectAttributes())
    		if (needValidation(a))
    			return true;
    	for (DLInheritanceCondition ic:n.getInheritanceConditions())
    		if (1==ic.getParents().size() && needValidation(ic.getParents().get(0)))
    			return true;
    	return false;
    }
    
    private List<DLValidityCondition> getAllValidityConditions(DLNotion n) {
    	List<DLValidityCondition> r = new ArrayList<DLValidityCondition>();
    	r.addAll(n.getValidityConditions());
    	for(DLInheritanceCondition ic:n.getInheritanceConditions())
    		if (1==ic.getParents().size())
    			r.addAll(getAllValidityConditions(ic.getParents().get(0)));
    	return r;
    }
    
    private List<DLIdentityCondition> getAllIdentityConditions(DLNotion n) {
    	List<DLIdentityCondition> r = new ArrayList<DLIdentityCondition>();
    	r.addAll(n.getIdentityConditions());
    	for(DLInheritanceCondition ic:n.getInheritanceConditions())
    		if (1==ic.getParents().size())
    			r.addAll(getAllIdentityConditions(ic.getParents().get(0)));
    	return r;
    }
    
    private void generateStructureForPrimitive(DLPrimitive primitive){
    	//TODO
    	
    }

    private void generateStructureForRelationship(DLRelationship rule) {
        GClass repr = new GClass("MS" + toCamelCase(rule.getName()));
        repr.setDLClass(false);
        if (rule instanceof DLTransition)
            generateStructureForTransition(repr, (DLTransition) rule);
        else if (rule instanceof DLReference)
            generateStructureForReference(repr, (DLReference) rule);
        result.put(repr.getName(), repr);
    }

    private GInterface fillInterface(GInterface irepr, GClass repr) {
        for(GMethod m:repr.getMethods())
        	if(!m.getReturnType().isEmpty() && !m.isStatic()){
        		GMethod nm = new GMethod(m.getName(), m.getReturnType());
        		for(GParameter p:m.getParameters())
        			nm.addParameter(new GParameter(p.getName(), p.getType()));
        		irepr.addMethod(nm);
        	}
        return irepr;
    }

    private String getTypeText(DLNotion notion, boolean obj) {
    	DLProperty property = null;
    	if (notion instanceof DLProperty)
    		property = (DLProperty) notion;
    	else
    		property = getSimpleTypeParent((DLEntity) notion);
    	switch (property.getValueType()) {
            case BOOLEAN:
                return obj?"Boolean":"boolean";
            case INTEGER:
                return obj?"Integer":"int";
            case FLOAT:
                return obj?"Double":"double";
            case STRING:
                return "String";
            case DATE:
            case TIME:
                return "Date";
            case ORDERED_SET:
            case SET:
            	return "List<"+(null!=property.getSetType()?"M"+toCamelCase(property.getSetType().getName()):"Object")+">";
            case ENUMERATION:
            	//TODO
				return "Enumeration";
    	}
        return null;
    }
    
    private DLProperty getSimpleTypeParent(DLEntity entity){
		for (DLInheritanceCondition i:entity.getInheritanceConditions())
			for (DLNotion p:i.getParents()){
				if (p instanceof DLProperty)
					return (DLProperty) p;
				else if (isSimpleType(p))
					return getSimpleTypeParent((DLEntity) p);
			}
		return null;
    }

    private void addAttribute(GClass repr, DLProperty attribute, DLNotion parent) {
        String name = toLowerCamelCase(attribute.getName());
        String type = attribute.getDirectAttributes().isEmpty() ? getTypeText(attribute,false) : "IM" + toCamelCase(attribute.getName());
        if (DLTypeRole.TEMPORARY_ROLE.equals(attribute.getType())) {
            GMethod m = new GMethod(("boolean".equals(type) ? "is" : "get") + toCamelCase(name), type);
            m.setCode(getDerivedGetterCode(attribute, parent));
            repr.addMethod(m);
        } else {
            addAttribute(repr, name, type);
        }
    }

    private void addAttribute(GClass repr, String name, String type) {
        GAttribute a = new GAttribute(name, type);
        repr.addAttribute(a);
        GMethod m = new GMethod(("boolean".equals(type) ? "is" : "get") + toCamelCase(name), type);
        m.setCode("this." + name + "=" + name + ";");
        repr.addMethod(m);
        m = new GMethod("set" + toCamelCase(name), "void");
        m.addParameter(new GParameter(name, type));
        m.setCode("return " + name + ";");
        repr.addMethod(m);
    }

    private boolean containsAttribute(GClass repr, String name) {
    	for (GAttribute a:repr.getAttributes())
    		if(Util.equals(a.getName(), name))
    			return true;
    	return false;
    }

    private GAttribute getAttribute(GClass repr, String name) {
        for (GAttribute a : repr.getAttributes()) {
            if (Util.equals(a.getName(), name)) {
                return a;
            }
        }
        return null;
    }

    private String getDerivedGetterCode(DLProperty attribute, DLNotion parent) {
    	ForwardSubsolution solution = DLInferenceEngine.solve(attribute, Arrays.asList(new DLNotion[]{parent}), repository);
        if (null==solution)
            return "";
        List<String> var = new ArrayList<String>();
        String s = recursiveDerivationCodeGeneration(solution, var, 0);
        return s;
    }
    
    private String recursiveDerivationCodeGeneration(ForwardSubsolution solution, List<String> var, int depth) {
        String s, name, type;
        s = "";
        for (ForwardSubsolution fs:solution.getRequired()){
        	if (!result.containsKey("M" + toCamelCase(fs.getProvided().getName())) && !(fs.getProvided() instanceof DLNotion && isSimpleType((DLNotion) fs.getProvided())))
        		generateStructureForRelationshipParticipant(fs.getProvided());
        	if (!var.contains(toLowerCamelCase(fs.getProvided().getName())+(DLRelationshipParticipationMultiplicity.SINGLE.equals(fs.getMultiplicity())?"":"s")))
                s+=(s.isEmpty()?"":"\n")+recursiveDerivationCodeGeneration(fs, var, depth+1);
        }
        boolean mlt = !DLRelationshipParticipationMultiplicity.SINGLE.equals(solution.getMultiplicity());
        name = toLowerCamelCase(solution.getProvided().getName()) + (mlt?"s":"");
        if (null != solution.getRule() && !var.contains(name)) {
            if (0!=depth)
                var.add(name);
            type = (solution.getProvided() instanceof DLNotion && isSimpleType((DLNotion) solution.getProvided())) ? getTypeText((DLNotion) solution.getProvided(),false) : "M" + toCamelCase(solution.getProvided().getName());
            if (mlt)
                type = "List<"+type+">";
            if (solution.getRule() instanceof DLSupplementRule)
                s += (s.isEmpty() ? "" : "\n") + (0!=depth?type + " " + name + " = ":"return ") + solution.getRule().getName();
            else {
                 if (!result.containsKey("MS" + toCamelCase(solution.getRule().getName())))
                    generateStructureForRelationship(((DLInferenceRule) solution.getRule()).getRelationship());
                s += (s.isEmpty() ? "" : "\n") + (0!=depth?type + " " + name + " =":"return") + " MS" + toCamelCase(solution.getRule().getName()) + ".get" + toCamelCase(solution.getRule().getProvidedParticipation(solution.getProvided()).getParticipant().getName()) + (mlt?"s":"") + "(";
                boolean first = true;
                for (ForwardSubsolution r : solution.getRequired()) { 
                    if (first) {
                        s += toLowerCamelCase(r.getProvided().getName())+(!DLRelationshipParticipationMultiplicity.SINGLE.equals(r.getMultiplicity())?"s":"");
                        first = false;
                    } else {
                        s += ", " + toLowerCamelCase(r.getProvided().getName()+(!DLRelationshipParticipationMultiplicity.SINGLE.equals(r.getMultiplicity())?"s":""));
                    }
                }
                s += ");";
            }
        }
        return s;
    }
    
    private void generateStructureForReference(GClass repr, DLReference relationship) {
        for(DLRelationshipParticipation p:DLParticipationsHelper.getProvidedParticipations(relationship.getParticipations())){
            GMethod gm = new GMethod("get" + toCamelCase(relationship.getName()) + toCamelCase(p.getParticipant().getName())+(!DLRelationshipParticipationMultiplicity.SINGLE.equals(p.getMultiplicity())?"s":""), getTypeFromParticipation(p));
            gm.setStatic(true);
            for(DLRelationshipParticipation r:DLParticipationsHelper.getRequiredParticipations(relationship.getParticipations(),p.getParticipant()))
                gm.addParameter(new GParameter(toLowerCamelCase(r.getName()), getTypeFromParticipation(r)));
            gm.setCode((relationship instanceof DLPatternBasedReference)?generateCodeFromPattern(((DLPatternBasedReference) relationship),p,true):generateCodeForData(((DLDataBasedReference) relationship),p));
            repr.addMethod(gm);
            if (DLRelationshipParticipationDirection.UNDEFINED.equals(p.getDirection())){
                GMethod gm2 = new GMethod("get" + toCamelCase(relationship.getName()) + toCamelCase(p.getParticipant().getName())+(!DLRelationshipParticipationMultiplicity.SINGLE.equals(p.getMultiplicity())?"s":""), getTypeFromParticipation(p));
                gm2.setStatic(true);
                for(DLRelationshipParticipation r:DLParticipationsHelper.getRequiredParticipations(relationship.getParticipations(),p.getParticipant()))
                    gm2.addParameter(new GParameter(toLowerCamelCase(r.getName()), getTypeFromParticipation(r)));
                gm2.addParameter(new GParameter(toLowerCamelCase(p.getName()), getTypeFromParticipant(p.getParticipant(),true)));
                gm2.setCode((relationship instanceof DLPatternBasedReference)?generateCodeFromPattern(((DLPatternBasedReference) relationship),p,false):generateCodeForData(((DLDataBasedReference) relationship),p));
                repr.addMethod(gm2);
            }
        }
    }
    
    private void generateStructureForTransition(GClass repr, DLTransition relationship) {
        for(DLRelationshipParticipation p:DLParticipationsHelper.getProvidedParticipations(relationship.getParticipations())){
            GMethod gm = new GMethod("get" + toCamelCase(p.getParticipant().getName())+(!DLRelationshipParticipationMultiplicity.SINGLE.equals(p.getMultiplicity())?"s":""), getTypeFromParticipation(p));
            gm.setStatic(true);
            for(DLRelationshipParticipation r:DLParticipationsHelper.getRequiredParticipations(relationship.getParticipations(),p.getParticipant()))
                gm.addParameter(new GParameter(toLowerCamelCase(r.getName()), getTypeFromParticipation(r)));
            gm.setCode((relationship instanceof DLPatternBasedTransition)?generateCodeFromFormula(((DLPatternBasedTransition) relationship),p):generateCodeFromAlghoritm(((DLAlghoritmicTransition) relationship),p));
            repr.addMethod(gm);
        }
    }
    
    private String getTypeFromNamedLink(DLNamedLink l){
        if (l instanceof DLRelationshipParticipation)
        	return getTypeFromParticipation((DLRelationshipParticipation) l);
        return getTypeFromParticipant(l.getElement(), false);	
    }
    
    private String getTypeFromParticipation(DLRelationshipParticipation p){
        boolean mlt = !DLRelationshipParticipationMultiplicity.SINGLE.equals(p.getMultiplicity());
        return getTypeFromParticipant(p.getParticipant(), mlt);
    }
    
    private String getObjectTypeFromParticipant(DLRelationshipParticipant p){
    	if (p instanceof DLPrimitive)
    		return getTypeFromPrimitive((DLPrimitive) p,true);
        return isSimpleType((DLNotion) p) ? getTypeText((DLNotion) p,true) : "IM" + toCamelCase(p.getName());
    }
    
    private String getTypeFromParticipant(DLRelationshipParticipant p, boolean mlt){
    	if (p instanceof DLPrimitive)
    		return getTypeFromPrimitive((DLPrimitive) p,false);
    	String type = p instanceof DLNotion && isSimpleType((DLNotion) p) ? getTypeText((DLNotion) p,mlt) : "IM" + toCamelCase(p.getName());
        return mlt?"List<"+type+">":type;
    }

    private String getTypeFromPrimitive(DLPrimitive p, boolean b) {
    	if ("CURRENT_DATE".equals(p.getName()))
    		return "LocalDate";
    	//TODO
    	
    	return "Object";
    }
    
    private String generateCodeFromFormula(DLPatternBasedTransition relationship, DLRelationshipParticipation p) {
        if (null==relationship.getPattern() || DLTransitionPatternType.EMPTY.equals(relationship.getPattern().getType()))
            return "";
        List<DLDomain> domains = relationship.getDomains();
        Map<String,DLNamedLink> knows = getKnowsMapFromRelationship(relationship);
        if(null!=relationship.getPattern().getSubjectLink())
        	knows.put("$",relationship.getPattern().getSubjectLink());
        String code = generateCodeForPrimitives(relationship);
        switch(relationship.getPattern().getType()){
        	case SIMPLE:
        		String[] sltns = symbolicEngine.solve(relationship.getPattern().getPattern(), p.getName());
                code="return "+solutionToCode(restoreCaseToSolution(sltns[0],getVariables(relationship)),domains,false,knows)+";";
                break;
        	case SUMMATION:
        		if (null==relationship.getPattern().getSubjectLink()) return "";
                code=(p.getParticipant() instanceof DLNotion && isSimpleType((DLNotion) p.getParticipant()) ? getTypeText((DLNotion) p.getParticipant(),false) : "M" + toCamelCase(p.getParticipant().getName()))+" result = 0;\n";
                code+="for("+getObjectTypeFromParticipant(relationship.getPattern().getSubjectLink().getElement())+" "+ITER+":"+toLowerCamelCase(relationship.getPattern().getSubjectLink().getName())+")\n";
                code+="\tresult+="+solutionToCode(relationship.getPattern().getPattern(),domains,false,knows).replaceAll(toLowerCamelCase(relationship.getPattern().getSubjectLink().getElement().getName()), "\\$_iter")+";";
                break;
            case MULTIPLICATION:
        		if (null==relationship.getPattern().getSubjectLink()) return "";
                code=(p.getParticipant() instanceof DLNotion && isSimpleType((DLNotion) p.getParticipant()) ? getTypeText((DLNotion) p.getParticipant(),false) : "M" + toCamelCase(p.getParticipant().getName()))+" result = 0;\n";
                code+="for("+getObjectTypeFromParticipant(relationship.getPattern().getSubjectLink().getElement())+" "+ITER+":"+toLowerCamelCase(relationship.getPattern().getSubjectLink().getName())+")\n";
                code+="\tresult*="+solutionToCode(relationship.getPattern().getPattern(),domains,false,knows).replaceAll(toLowerCamelCase(relationship.getPattern().getSubjectLink().getElement().getName()), "\\$_iter")+";";
                break;
            case QUERY:
            	Matcher m = Pattern.compile("^([a-zA-Z][a-z]*) ([a-zA-Z][a-zA-Z0-9 ]*) based on ([a-zA-Z][a-zA-Z0-9 ]*)$").matcher(relationship.getPattern().getPattern());
            	if (!m.find()) return "";
            	String[] q = new String[3];
            	q[0]=m.group(1);
            	q[1]=m.group(2);
            	q[2]=m.group(3).trim();
            	DLSubset qdomain = repository.getDomain(q[2]);
                if (null==qdomain)
                    qdomain=repository;
                DLNotion what = qdomain.getNotion(q[1]);
                if (null==what)
                	return "";
                List<DLNotion> basedOn = new ArrayList<DLNotion>();
                if (1!=DLParticipationsHelper.getProvidedParticipations(relationship.getParticipations()).size())
                	return "";
                DLNotion trgt = (DLNotion) DLParticipationsHelper.getProvidedParticipations(relationship.getParticipations()).get(0).getParticipant();
                for (DLRelationshipParticipation prt:DLParticipationsHelper.getRequiredParticipations(relationship.getParticipations(),trgt))
                	if (prt.getParticipant() instanceof DLNotion)
                			basedOn.add((DLNotion) prt.getParticipant());
                ForwardSubsolution solution = DLInferenceEngine.solve(what, basedOn, qdomain, Arrays.asList(new DLRelationship[]{relationship}));
            	if (null==solution)
                    return "";
                List<String> var = new ArrayList<String>();
                code = recursiveDerivationCodeGeneration(solution, var, 0);
                if (!Util.equals(what, trgt) && !(isSimpleType(what) && isSimpleType(trgt) && Util.equals(getTypeText(what,false),getTypeText(trgt, false))))
                    code.replace("return ", "return ("+(isSimpleType(trgt) ? getTypeText(trgt,false) : "M" + toCamelCase(trgt.getName()))+") ");
                break;
            case EVOCATION:
            	code=relationship.getPattern().getPattern();
            	break;
            case MAPPING:
            	//TODO
            	
            	break;
            default:
            	break;
        }
        return code;
    }
    
    private String generateCodeForPrimitives(DLRelationship rel) {
    	String s = "";
    	for (DLRelationshipParticipation rp:rel.getParticipations())
    		if (rp.getElement() instanceof DLPrimitive) 
    			s+=getTypeFromParticipation(rp)+" "+rp.getName()+"="+getCodeFromPrimitive((DLPrimitive) rp.getElement())+";\n";
    	return s;
    }
    
    private String getCodeFromPrimitive(DLPrimitive p) {
		if("CURRENT_DATE".equals(p.getName()))
			return "LocalDate.now()";
    	//TODO
    	
		return "null";
	}

	private List<String> getVariables(DLPatternBasedTransition relationship) {
		List<String> variables = new ArrayList<String>();
		for(DLRelationshipParticipation r:relationship.getParticipations())
			variables.add(r.getName());
		return variables;
	}

	private String generateCodeFromPattern(DLPatternBasedReference relationship, DLRelationshipParticipation p, boolean all) {
        if (null==relationship.getPattern() || DLConditionPatternType.EMPTY.equals(relationship.getPattern().getType()))
            return "";
        List<DLDomain> domains = relationship.getDomains();
        Map<String,DLNamedLink> knows = getKnowsMapFromRelationship(relationship);
        DLNamedLink subjectLink=relationship.getPattern().getSubjectLink();
        if(null!=subjectLink)
        	knows.put("$",subjectLink);
        String code = generateCodeForPrimitives(relationship);
        if (all)
        	code+="List<IM"+toCamelCase(p.getParticipant().getName())+"> "+toLowerCamelCase(p.getName())+" = MS"+toCamelCase(p.getParticipant().getName())+".get"+toCamelCase(p.getParticipant().getName())+"s();\n";
	    code+="for (IM"+toCamelCase(p.getParticipant().getName())+" "+ITER+":"+toLowerCamelCase(p.getName())+"){\n";
	    
	    String cnd;
	    switch(relationship.getPattern().getType()){
        	case SIMPLE:
        		code+="\tif ("+solutionToCode(refactorSolution(relationship.getPattern().getPattern(),toLowerCamelCase(p.getName()), ITER),domains,true,knows)+")\n";
        	    break;
        	case UNIVERSAL_QUANTIFICATION:
        		if (null==subjectLink) return "";
        		cnd=solutionToCode(refactorSolution(refactorSolution(relationship.getPattern().getPattern(),toLowerCamelCase(p.getName()), ITER), toLowerCamelCase(subjectLink.getName()), ITER+"2"),domains,true,knows);
        		code+="\tboolean "+VAR+" = true;\n\tfor("+getObjectTypeFromParticipant(subjectLink.getElement())+" "+ITER+"2:"+solutionToCode(subjectLink.getName(),domains,true,knows)+")\n\t\tif (!"+cnd+"){\n\t\t\t"+VAR+"=false;\n\t\t\tbreak;\n\t\t}\n\tif ("+VAR+")\n";
        	    break;
        	case EXISTENTIAL_QUANTIFICATION:
        		if (null==subjectLink) return "";
        		cnd=solutionToCode(refactorSolution(refactorSolution(relationship.getPattern().getPattern(),toLowerCamelCase(p.getName()), ITER), toLowerCamelCase(subjectLink.getName()), ITER+"2"),domains,true,knows);
        		code+="\tboolean "+VAR+" = false;\n\tfor("+getObjectTypeFromParticipant(subjectLink.getElement())+" "+ITER+"2:"+solutionToCode(subjectLink.getName(),domains,true,knows)+")\n\t\tif ("+cnd+"){\n\t\t\t"+VAR+"=true;\n\t\t\tbreak;\n\t\t}\n\tif ("+VAR+")\n";
        		break;
        	default:
        		break;
        }
        if(!DLRelationshipParticipationMultiplicity.SINGLE.equals(p.getMultiplicity())){
	        code = "List<IM"+toCamelCase(p.getParticipant().getName())+"> res = new ArrayList();\n"+code;
	        code+="\t\tres.add("+ITER+");\n}\nreturn res;";
	    } else
	        code+="\t\treturn "+ITER+";\n}\n";
        return code;
    }
	
	private String generateCheckingCodeFromPattern(DLConditionPattern pattern, DLNotion n) {
        if (null==pattern || DLConditionPatternType.EMPTY.equals(pattern.getType()))
            return "";
        List<DLDomain> domains = n.getDomains();
        Map<String,DLNamedLink> knows = getKnowsMapFromNotion(n);
        DLNamedLink subjectLink = pattern.getSubjectLink();
        if(null!=subjectLink)
        	knows.put("$",subjectLink);
        String code = "";
        
	    String cnd;
	    switch(pattern.getType()){
        	case SIMPLE:
        		code+="\treturn "+solutionToCode(pattern.getPattern(),domains,true,knows)+";\n";
        	    break;
        	case UNIVERSAL_QUANTIFICATION:
        		if (null==subjectLink) return "";
        		cnd=solutionToCode(refactorSolution(pattern.getPattern(), toLowerCamelCase(subjectLink.getName()), ITER),domains,true,knows);
        		code+="\tfor("+getObjectTypeFromParticipant(subjectLink.getElement())+" "+ITER+"2:"+solutionToCode(subjectLink.getName(),domains,true,knows)+")\n\t\tif (!"+cnd+")\n\t\treturn false;\n\treturn true;\n";
        	    break;
        	case EXISTENTIAL_QUANTIFICATION:
        		if (null==subjectLink) return "";
        		cnd=solutionToCode(refactorSolution(pattern.getPattern(), toLowerCamelCase(subjectLink.getName()), ITER),domains,true,knows);
        		code+="\tfor("+getObjectTypeFromParticipant(subjectLink.getElement())+" "+ITER+"2:"+solutionToCode(subjectLink.getName(),domains,true,knows)+")\n\t\tif ("+cnd+")\n\t\treturn true;\n\treturn false;\n";
        		break;
        	case MEMBERSHIP:
        		//TODO
        		break;
        	default:
        		break;
        }
        return code;
    }

	private String generateCodeForData(DLDataBasedReference relationship, DLRelationshipParticipation p) {
        //TODO
        
        return "";
    }

    private String generateCodeFromAlghoritm(DLAlghoritmicTransition relationship, DLRelationshipParticipation p) {
        String code = "";
        //TODO
        
        return code;
    }
    
    private String solutionToCode(String solution, List<DLDomain> domains, boolean checkContext, Map<String,DLNamedLink> knows){
        solution=solution.replaceAll("Sqrt", "Math.sqrt");
        Pattern p = Pattern.compile("Sqr");
        Matcher m = p.matcher(solution);
        while(m.find()){
            int i,sc=1;
            for (i = m.start()+4;i<solution.length();i++){
                if('(' == solution.charAt(i))
                    sc++;
                if(')' == solution.charAt(i)) sc--;
                if (0==sc) break;
            }
            solution = solution.substring(0,m.start())+"Power("+solution.substring(m.start()+4,i)+",2"+solution.substring(i);
            m = p.matcher(solution);
        }
        solution=solution.replaceAll("Power", "Math.pow");
        solution=processCalls(solution,domains,checkContext,knows);
        //TODO equals zamiast == gdzie potrzeba
        //TODO
        
        return solution;
    }
    
    private String processCalls(String solution, List<DLDomain> domains, boolean checkContext, Map<String, DLNamedLink> knows) {
    	Pattern p = Pattern.compile("(^[^\\+\\-\\*/\\^\\(\\)\\|&\\s\\.=!<>]*\\s*\\()|([\\+\\-\\*/\\^\\(\\|&]\\s*[^\\+\\-\\*/\\^\\(\\)\\|&\\s\\.=!<>]*\\s*\\()");
        Matcher m = p.matcher(solution);
        if(!m.find())
        	return solution;
        int start = "+-*/^(|&".contains(""+solution.charAt(m.start()))?m.start()+1:m.start();
        String subject = solution.substring(start, m.end()-1).trim();
        List<String> values = new ArrayList<String>();
        int end = m.end();
        for(int i=1,k=end;i>0&&end<solution.length();end++) {
            if (')'==solution.charAt(end)) {
            	i--;
            	if (0==i)
            		values.add(solution.substring(k,end).trim());
            } else if ('('==solution.charAt(end))
            	i++;
            else if(1==i && ','==solution.charAt(end)) {
            	values.add(solution.substring(k,end).trim());
            	k=end+1;
             }
        }
        end=end+1<solution.length()?end+1:solution.length();
        if(1==values.size() && null!=knows.get(values.get(0)) && knows.get(values.get(0)).getElement() instanceof DLNotion &&  isAttribute(subject,(DLNotion) knows.get(values.get(0)).getElement()))
            return solution.substring(0,start)+values.get(0)+".get"+toCamelCase(subject)+"()"+processCalls(solution.substring(end),domains,checkContext,knows);
        List<String> uv = new ArrayList<String>();
        for (String v:values)
        	uv.add(processCalls(v,domains,false,knows));
        String type = getResultType(subject,uv,domains,knows);
        //TODO null
        String res=solution.substring(0,start)+"MS"+toCamelCase(subject)+"."+((isCheckTypeFormula(solution, start,end,checkContext) || type.isEmpty())?"check":"get"+toCamelCase(type))+"(";
        boolean first=true;
        for (String v:uv) {
        	if(first) {
        		res+=v;
        		first=false;
        	} else
        		res+=v+",";
        }
        return res+")"+processCalls(solution.substring(end),domains,checkContext,knows);
    }
    
    private boolean isCheckTypeFormula(String s, int start, int end, boolean checkContext) {
    	String before="",after="";
    	if (end<s.length()) {
    		String sf=s.substring(end);
    		Pattern p = Pattern.compile("[<>=!][=]|[\\+\\-\\*/\\^<>!\\)]|&&|\\|\\|");
    		Matcher m = p.matcher(sf);
    		if (m.find())
    			after=m.group(0);
    	}
    	if (start>0) {
    		String sb = new StringBuilder(s.substring(0, start)).reverse().toString();
    		Pattern p = Pattern.compile("[=][<>=!]|[\\+\\-\\*/\\^<>!\\(]|&&|\\|\\|");
    		Matcher m = p.matcher(sb);
    		if (m.find())
    			before=m.group(0).length()>1?new StringBuilder(m.group(0)).reverse().toString():m.group(0);
    	}
    	if (before.isEmpty() && after.isEmpty())
    		return checkContext;
    	if("&&||!".contains(before)&&"&&||!".contains(after))
    		return true;
    	return false;
    }
    
    private String getResultType(String subject, List<String> values, List<DLDomain> domains, Map<String,DLNamedLink> knows) {
    	String type,result=null;
    	if (domains.isEmpty())
    		return getResultType(repository.getRelationshipByAbbreviation(subject),values,knows,repository);
    	for (DLDomain d:domains) {
    		type = getResultType(d.getRelationshipByAbbreviation(subject),values,knows,d);
    		if (null!=type) {
    			result=type;
    			if (!result.isEmpty())
    				return result;
    		}
    	}
    	return result;
    }
    
    private String getResultType(DLRelationship relationship, List<String> values, Map<String,DLNamedLink> knows, DLSubset subs) {
    	if(null==relationship || relationship.getParticipations().size()<values.size())
    		return null;
    	if(DLParticipationsHelper.getRequiredParticipations(relationship.getParticipations()).size()<values.size())
    		return "";
    	List<String> valuesTypes = new ArrayList<String>();
    	for (String v:values)
    		valuesTypes.add(solveTypeFromFormula(v,knows,subs));
    	EList<DLRelationshipParticipation> prov = DLParticipationsHelper.getProvidedParticipations(relationship.getParticipations());
    	String result = null;
    	for (DLRelationshipParticipation p:prov) 
    		if (machTypes(DLParticipationsHelper.getRequiredParticipations(relationship.getParticipations(), p.getParticipant()),valuesTypes)){
    			if (null==result)
    				result = p.getParticipant().getName();
    			else
    				return null;
    		}
    	return result;
    }
    
    private boolean machTypes(EList<DLRelationshipParticipation> req, List<String> valuesTypes) {
    	List<String> reqValuesTypes = new ArrayList<String>();
    	for (DLRelationshipParticipation p:req)
    		reqValuesTypes.add(getTypeFromParticipation(p));
    	return valuesTypes.size()==reqValuesTypes.size() && valuesTypes.containsAll(reqValuesTypes);
    }
    
    private String solveTypeFromFormula(String formula, Map<String,DLNamedLink> knows, DLSubset subs) {
    	formula=formula.replace("!(?!=)", "");
    	Pattern p = Pattern.compile("[<>=!][=]|[\\+\\-\\*/\\^<>]|&&|\\|\\|");
		Matcher m = p.matcher(formula);
		if (!m.find()) {
			if(formula.contains(".")) {
				if(formula.contains(".is"))
					return "boolean";
				p=Pattern.compile("MS(\\w*)\\.get(\\w*)\\(");
				m=p.matcher(formula);
				if(m.find()) {
					for (DLRelationship r:subs.getDomainRelationships())
						if (toCamelCase(r.getName()).equals(m.group(1)))
							for (DLRelationshipParticipation pr:r.getParticipations())
								if(toCamelCase(pr.getName()).equals(m.group(2)))
									return getTypeFromParticipation(pr);
				}
				p=Pattern.compile("(\\w*)\\.get(\\w*)\\(");
				m=p.matcher(formula);
				if(m.find() && knows.keySet().contains(m.group(1)))
					for(DLAttributeLink al:((DLNotion) knows.get(m.group(1)).getElement()).getAttributesLinks())
						if(toCamelCase(al.getName()).equals(m.group(2)))
							return getTypeFromNamedLink(al);
				return null;
			}
			if(knows.keySet().contains(formula))
				return getTypeFromNamedLink(knows.get(formula));
			//TODO stale
			return null;
		}
		String t="";
		int k = 0;
		boolean b = true;
		while (true) {
			if ('('==formula.charAt(k)) {
				int i = k+1;
				for (int j=1;i<formula.length()&&j>0;i++) {
					if ('('==formula.charAt(i))
						j++;
					else if (')'==formula.charAt(i))
						j--;
				}
				t+= solveTypeFromFormula(formula.substring(k+1, i-1), knows, subs);
				if (i+1<formula.length() && m.find(i+1) && m.end()<formula.length()) {
					t+=m.group(0);
					k=m.end();
				} else
					break;
			} else {
				t+= solveTypeFromFormula(formula.substring(k, b?m.start()-1:formula.length()-1), knows, subs);
				if (b && m.end()<formula.length()) {
					t+=m.group(0);
					k=m.end();
				} else
					break;
			}
			b=m.find();
		}
		t=replaceReallyAll(t,"(int|Integer)[\\+\\-\\*/\\^](int|Integer)", "int");
		t=replaceReallyAll(t,"(double|Double)[\\+\\-\\*/\\^](int|Integer)|(int|Integer)[\\+\\-\\*/\\^](double|Double)|(double|Double)[\\+\\-\\*/\\^](double|Double)", "double");
		t=replaceReallyAll(t,"(Date)[\\+\\-](Date)", "Date");
		t=replaceReallyAll(t,"[^\\+\\-\\*/\\^\\|&=!<>]*\\+String|String\\+[^\\\\+\\\\-\\\\*/\\\\^\\\\|&=!<>]*", "String");
		t=replaceReallyAll(t,"[^\\+\\-\\*/\\^\\|&=!<>]*([<>=!][=]|[<>])[^\\\\+\\\\-\\\\*/\\\\^\\\\|&=!<>]*", "boolean");
		t=replaceReallyAll(t,"(boolean|Boolean)(&&|\\|\\|)(boolean|Boolean)", "boolean");
		m=p.matcher(t);
		if (!m.find())
			return t;
    	return null;
    }
    
    private String replaceReallyAll(String s, String p, String r) {
    	String s2 = null; 
    	while (!s.equals(s2)) {
    		s2=s;
    		s=s.replaceAll(p, r);
    	}
    	return s;
    }
    
    private Map<String,DLNamedLink> getKnowsMapFromRelationship(DLRelationship r){
    	Map<String,DLNamedLink> knows = new HashMap<String,DLNamedLink>();
    	for (DLRelationshipParticipation p:r.getParticipations())
    		knows.put(p.getName(), p);
    	return knows;
    }
    
    private Map<String,DLNamedLink> getKnowsMapFromNotion(DLNotion n){
    	Map<String,DLNamedLink> knows = new HashMap<String,DLNamedLink>();
    	//TODO all
    	for (DLAttributeLink al:n.getAttributesLinks())
    		knows.put(al.getName(), al);
    	return knows;
    }
    
    private boolean isAttribute(String attribute, DLNotion not) {
    	if ("value".equals(attribute) && hasValue(not))
			return true;
		for(DLAttributeLink al:not.getAllAttributesLinks())
			if (attribute.equals(al.getName()))
				return true;
    	return false;
    }
    
    private boolean hasValue(DLNotion not) {
    	if (not instanceof DLProperty)
    		return true;
    	for(DLInheritanceCondition ic:not.getInheritanceConditions()) {
    		boolean hv = true;
    		for (DLNotion p:ic.getParents())
    			if (!hasValue(p)) {
    				hv = false;
    				break;
    			}
    		if (hv) return true;
    	}
    	return false;
    }
    
    private String restoreCaseToSolution(String s, List<String> variables) {
    	for (String v:variables)
    		s=refactorSolution(s, v.toLowerCase(), v);
    	return s;
    }
    
    private String refactorSolution(String s, String from, String to){
        return collapseSolution(expandSolution(s).replace(" "+from+" ", " "+to+" "));
    }
    
    //TODO wiêcej znaków
    private String expandSolution(String s){
        String e=" ";
        boolean w = true,pd = false;
        for (int i=0;i<s.length();i++){
            if ("=<>".contains(""+s.charAt(i))){
                e+=pd?s.charAt(i)+" ":w?s.charAt(i):" "+s.charAt(i);
                w = pd;
                pd = !pd;
            } else if ("+-*/^()".contains(""+s.charAt(i))){
                e+=w?s.charAt(i)+" ":" "+s.charAt(i)+" ";
                w = true;
            } else if (' '==s.charAt(i)){
                e+=w?"":" ";
                w = true;
            } else {
                e+=pd?" "+s.charAt(i):s.charAt(i);
                if (pd)
                    pd = false;
                w=false;
            }
        }
        return w?e:e+" ";
    }
    
    private String collapseSolution(String s){
        String e = "";
        boolean pd=false;
        for (int i=2;i<s.length();i++){ 
            if (' '!=s.charAt(i-1) || "=<>".contains(""+s.charAt(i)) || pd){
                e+=s.charAt(i-1);
                if ('=' == s.charAt(i-1))
                    pd = true;
                else if (pd)
                    pd = false;
            }
        }
        return e;
    }
	
	final String c1 = "\nimport java.util.List;\n\npublic interface IDLClass {\n\n\tpublic List<IDLClass> getDLRoles();\n\n}";
	final String c2 = "\nimport java.util.ArrayList;\nimport java.util.List;\n\npublic abstract class DLClass implements IDLClass {\n\n\tList<IDLClass> dlRoles = new ArrayList<>();\n\n\t@Override\n\tpublic List<IDLClass> getDLRoles() {\n\t\treturn dlRoles;\n\t}\n\n}";
	final String c3 = "\nimport java.util.List;\n\npublic class DLHelper {\n\n\tpublic static IDLClass treatAs(IDLClass dlClass, Class role){\n\t\tList<IDLClass> dlRoles = dlClass.getDLRoles();\n\t\tdlClass.getDLRoles().clear();\n\t\tdlRoles.add(dlClass);\n\t\tfor (IDLClass cRole:dlRoles)\n\t\t\tif (cRole.getClass().equals(role))\n\t\t\t\tdlClass = cRole;\n\t\tdlRoles.remove(dlClass);\n\t\tdlClass.getDLRoles().addAll(dlRoles);\n\t\treturn dlClass;\n\t}\n\n}";
    
    public void generateFilesForResult(String dir) throws IOException{
        (new File(dir)).mkdirs();
        if (!dir.endsWith("\\"))
            dir+="\\";
        saveClass(dir+"IDLClass.java",c1);
        saveClass(dir+"DLClass.java",c2);
        saveClass(dir+"DLHelper.java",c3);
        for (String ek:result.keySet())
            saveClass(dir+ek+".java",result.get(ek).toString());
        
    }
    
    private void saveClass(String name, String code) throws IOException{
    	File f = new File(name);
    	f.delete();
    	BufferedWriter bw = null;
        FileWriter fw = null;
        try {
            fw = new FileWriter(f);
            bw = new BufferedWriter(fw);
            bw.write(code);
        } finally {
            if (bw != null)
                bw.close();
            if (fw != null)
                fw.close();
        }
    }
    
}
