package eu.redseeds.rsldl.engine.code;

import java.util.ArrayList;
import java.util.List;

public class GInterface implements IGElement {
    String name;
    List<GMethod> methods = new ArrayList<GMethod>();
    List<GInterface> extendedInterfaces = new ArrayList<GInterface>();

    public GInterface(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<GMethod> getMethods() {
        return methods;
    }
    
    public void addMethod(GMethod method){
        methods.add(method);
    }

    public List<GInterface> getExtendedInterfaces() {
        return extendedInterfaces;
    }
    
    public void addExtendedInterface(GInterface extendedInterface){
        extendedInterfaces.add(extendedInterface);
    }

    @Override
    public String toString() {
        String s = "public interface "+getName()+" extends";
        if (!extendedInterfaces.isEmpty()){
            boolean first = true;
            for (GInterface i:extendedInterfaces)
                if (first){
                    s+=" "+i.getName();
                    first=false;
                } else s+=", "+i.getName();
        } else s+=" IDLClass";
        s+=" {\n\n";
        for(GMethod m:methods)
        	s+="\t"+m.toString().replace("{}", ";");
        return s+"}\n";
    }
    
}
