package eu.remics.domainlogic.translation;

import java.util.ArrayList;
import java.util.List;

import eu.redseeds.scl.uml.classes.kernel.Operation;
import eu.redseeds.scl.uml.classes.kernel.Parameter;
import eu.redseeds.scl.uml.classes.kernel.Type;
import eu.redseeds.scl.uml.classes.kernel.Class;

public class ContextManager {
	
	private ArrayList<Variable> variables = new ArrayList<Variable>();
	private Pair<Class, Operation> pair;
	
	public void initWithOperationParameters(List<Parameter> parameters){
		for(Parameter param : parameters){
        	for(Type type : param.getTypeList()){
        		addVariable(param.getName(), type.getName());
        	}
        }
		/*String name = "exam";
		String type = "ExamFactory";
		addVariable(name, type);*/
	}
	
	public void addVariable(String name, String type){
		Variable var = new Variable(name, type);
		variables.add(var);
	}
	
	public Variable findVariableByType(String type){
		for(Variable var : variables){
			if(var.getType().equals(type)){
				return var;
			}
		}
		return null;
	}
	
	public Variable findVariableByName(String name){
		for(Variable var : variables){
			if(var.getName().equalsIgnoreCase(name)){
				return var;
			}
		}
		return null;
	}
}
