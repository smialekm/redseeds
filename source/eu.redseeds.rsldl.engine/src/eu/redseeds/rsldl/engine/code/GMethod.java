package eu.redseeds.rsldl.engine.code;

import java.util.ArrayList;
import java.util.List;

public class GMethod implements IGElement {
	String name;
    String returnType;
    List<GParameter> parameters = new ArrayList<GParameter>();
    String code="";
    GClass parentClass;
    boolean isStatic = false;
    boolean isPrivate = false;

    public GMethod(String name, String returnType) {
        this.name = name;
        this.returnType = returnType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    public List<GParameter> getParameters() {
        return parameters;
    }
    
    public void addParameter(GParameter parameter){
        parameters.add(parameter);
    }

    public boolean isStatic() {
        return isStatic;
    }

    public void setStatic(boolean isStatic) {
        this.isStatic = isStatic;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean isPrivate) {
        this.isPrivate = isPrivate;
    }

    public GClass getParentClass() {
		return parentClass;
	}

	public void setParentClass(GClass parentClass) {
		this.parentClass = parentClass;
	}

	public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        String s =(isPrivate?"private ":"public ")+(isStatic?"static ":"")+(returnType.isEmpty()?"":returnType+" ")+name+"(";
        boolean first = true;
        for (GParameter p:parameters){
            if (first){
                s+=p.toString();
                first=false;
            } else s+=", "+p.toString();
        }
        return code.isEmpty()?s+"){}\n\n":s+"){\n\t\t"+code.replace("\n", "\n\t\t")+"\n\t}\n\n";
    }
    
    public String toSimplifiedString(){
    	String s = (null!=parentClass?parentClass.getName()+".":"")+name+"(";
    	boolean first = true;
        for (GParameter p:parameters){
            if (first){
                s+=p.getName();
                first=false;
            } else s+=", "+p.getName();
        }
    	return s+")";
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((parameters == null) ? 0 : parameters.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GMethod other = (GMethod) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (parameters == null) {
			if (other.parameters != null)
				return false;
		} else if (!parameters.equals(other.parameters))
			return false;
		return true;
	}
    
}
