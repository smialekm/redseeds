package eu.redseeds.rsldl.engine.code;

import java.util.ArrayList;
import java.util.List;

public class GClass implements IGElement {
    String name;
    List<GAttribute> attributes = new ArrayList<GAttribute>();
    List<GMethod> methods = new ArrayList<GMethod>();
    List<GInterface> implementedInterfaces = new ArrayList<GInterface>();
    boolean dLClass = true;

    public GClass(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<GAttribute> getAttributes() {
        return attributes;
    }
    
    public void addAttribute(GAttribute attribute){
        attributes.add(attribute);
    }

    public List<GMethod> getMethods() {
        return methods;
    }
    
    public void addMethod(GMethod method){
        methods.add(method);
        method.setParentClass(this);
    }

    public List<GInterface> getImplementedInterfaces() {
        return implementedInterfaces;
    }
    
    public void addImplementedInterface(GInterface implementedInterface){
        implementedInterfaces.add(implementedInterface);
    }

    public boolean isDLClass() {
        return dLClass;
    }

    public void setDLClass(boolean dLClass) {
        this.dLClass = dLClass;
    }

    @Override
    public String toString() {
        String s = "public class "+getName()+(dLClass?" extends DLClass":"");
        if (!implementedInterfaces.isEmpty()){
            s+= " implements";
            boolean first = true;
            for (GInterface i:implementedInterfaces)
                if (first){
                    s+=" "+i.getName();
                    first=false;
                } else s+=", "+i.getName();
        }
        s+=" {\n\n";
        for(GAttribute a:attributes)
        	s+="\t"+a.toString();
        if (!attributes.isEmpty())
            s+="\n";
        for(GMethod m:methods)
        	s+="\t"+m.toString();
        return s+"}\n";
    }
    
}
