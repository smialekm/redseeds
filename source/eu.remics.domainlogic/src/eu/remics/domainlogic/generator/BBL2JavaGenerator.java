package eu.remics.domainlogic.generator;

import eu.redseeds.scl.uml.classes.kernel.Operation;
import eu.redseeds.scl.uml.classes.kernel.Parameter;
import eu.redseeds.scl.uml.classes.kernel.Type;
import eu.redseeds.scl.uml.classes.kernel.Class;
import eu.redseeds.scl.uml.classes.kernel.TypedElement;
import eu.remics.domainlogic.translation.ContextManager;
import eu.remics.domainlogic.translation.ModelHelper;
import eu.remics.domainlogic.translation.Variable;

public class BBL2JavaGenerator {
	
	private ContextManager contex = new ContextManager();
	
	public ContextManager getContext(){
		return contex;
	}

	public String generate(){
		return "";
	}
	
	public String returnStatement(String msg){
		return "return " + msg + ";\n";
	}
	
	public String condition(String operand1, String operator, String operand2){
		String operation1 = getOperationFromModel(operand1);
		if(!operation1.isEmpty()){
			operand1 = operation1;
		}
		else{
			Variable var = getContext().findVariableByType(getOperationReturnType(operand1));
			if(var != null)
				operand1 = var.getName();
		}
		
		String operation2 = getOperationFromModel(operand2);
		if(!operation2.isEmpty()){
			operand2 = operation2;
		}
		else{
			Variable var = getContext().findVariableByType(getOperationReturnType(operand2));
			if(var != null)
				operand2 = var.getName();
		}
		
		if(operator.equals("is equal to") || operator.equals("are equal to")){
			operator = "==";
		}
		else if(operator.equals("is not equal to") || operator.equals("are not equal to")){
			operator = "!=";
		}
		else if(operator.equals("is greater or equal to") || operator.equals("are greater or equal to")){
			operator = ">=";
		}
		else if(operator.equals("is greater than") || operator.equals("are greater than")){
			operator = ">";
		}
		else if(operator.equals("is lower or equal to") || operator.equals("are lower or equal to")){
			operator = "<=";
		}
		else{
			operator = "<";
		}
		return operand1 + " " + operator + " " + operand2;
	}
	
	public String conditionsSUM(String cond, String condsAND){
		if(condsAND != null){
			return cond + " " + "&&" + " " + condsAND;
		}
		return cond + " ";
	}
	
	public String conditions(String condsAND, String conds){
		if(conds != null){
			return condsAND + " " + "||" + " " + conds;
		}
		return condsAND + " ";
	}
	
	public String error(String show, String str){
		if(show.equalsIgnoreCase("show error")){
			return "System.err.println(" + str + ");\n";
		}
		else{
			return "System.out.println(" + str + ");\n";
		}
	}
	
	public String except(String str/*, List<String> sentences*/){
		String res = "try{\n" + "}" + "catch(" + str + "){\n";
		/*for(String sentence : sentences){
			res += sentence + "\n";
		}*/
		res += "}\n";
		
		return res;
	}
	
	public String getOperationReturnType(String description){
		Operation operation = ModelHelper.searchForOperation(description);
		if(operation != null){
			if(!operation.getOwnedParameterList().isEmpty()){
				for(Parameter param : operation.getOwnedParameterList()){
					Type type = param.getTypeList().get(0);
					//Variable var = getContext().findVariableByType(type.getName());
					//if(var != null){
						if(param.getDirection().toString().equalsIgnoreCase("RETURN")){
							return type.getName();
						}
					//}
				}
			}
		}
		return null;
	}
	
	public String getOperationFromModel(String description){
		String result = "";
		Operation operation = ModelHelper.searchForOperation(description);
		if(operation == null) return "";
		String[] tmp = operation.getName().split("[(]");
		String operationName = tmp[0];
		if(operation != null){
			String className = operation.getClassList().get(0).getName();
			Class classOwnsOperation = ModelHelper.getClassOperationPair().getClassObject();
			if(operation.getClassList().get(0).equals(classOwnsOperation)){
				className = classOwnsOperation.getName();
			}
			
			String operParams = "";
			if(!operation.getOwnedParameterList().isEmpty()){
				//search in context for variable same as param
				int k = 0;
				for(Parameter param : operation.getOwnedParameterList()){
					k++;
					if(k > 1) operParams += ", ";
					Type type = param.getTypeList().get(0);
					Variable var = getContext().findVariableByType(type.getName());
					if(var != null){
						operParams += var.getName();
						if(param.getDirection().toString().equalsIgnoreCase("RETURN")){
							String returnVariable = param.getName()+"AsReturnedVar";
							getContext().addVariable(returnVariable, type.getName());
						}
					}
					else{
						operParams += "new " + type.getName() + "(" + ")";
					}
				}
			}
			result += "new " + className + "()" + "." + operationName + "(" + operParams + ")";
		}
		return result;
	}
	
	public String operation(String description){
		String result = "";
		Operation operation = ModelHelper.searchForOperation(description);
		String[] tmp = operation.getName().split("[(]");
		String operationName = tmp[0];
		if(operation != null){
			String className = operation.getClassList().get(0).getName();
			Class classOwnsOperation = ModelHelper.getClassOperationPair().getClassObject();
			if(operation.getClassList().get(0).equals(classOwnsOperation)){
				className = classOwnsOperation.getName();
			}
			
			String operParams = "";
			if(!operation.getOwnedParameterList().isEmpty()){
				//search in context for variable same as param
				int k = 0;
				for(Parameter param : operation.getOwnedParameterList()){
					k++;
					if(k > 1) operParams += ", ";
					Type type = param.getTypeList().get(0);
					Variable var = getContext().findVariableByType(type.getName());
					if(var != null){
						operParams += var.getName();
						if(param.getDirection().toString().equalsIgnoreCase("RETURN")){
							String returnVariable = param.getName()+"AsReturnedVar";
							getContext().addVariable(returnVariable, type.getName());
							result += type.getName() + " " + returnVariable + " = ";
						}
					}
					else{
						operParams += "new " + type.getName() + "(" + ")";
					}
				}
			}
			result += "new " + className + "()" + "." + operationName + "(" + operParams + ")" + ";";
		}
		return result;
	}
	
	public String sentences(String sentence, String and, String sentences){
		if(and != null && (and.equalsIgnoreCase(",") || and.equalsIgnoreCase("and"))){
			return sentence + "\n" + sentences;
		}
		return sentence + "\n";
	}
	
	public String conditional(String conditions, String sentences){
		return "if(" + conditions + "){\n\t" + sentences + "}\n";
	}
	
	public String alternative(String alternative){
		return "else{\n\t" + alternative + "}\n";
	}
	
	public String ifElse(String conditional, String alternative){
		if(alternative != null){
			return conditional.concat(alternative);
		}
		return conditional;
	}
	
	public String loop(String item, String collection, String sentences){
		String[] tmp = collection.split("[ \t\r\n]+");
		String collectionName = "";
		for(int i=0; i < tmp.length; i++){
			collectionName += tmp[i];
		}
		Variable var = getContext().findVariableByName(collectionName);
		if(var != null){
			
		}
		else{
			String operationWhichReturnsCollection = getOperationFromModel(collectionName);
			//String collectionType = getOperationReturnType(collectionName);
			getContext().addVariable("elem", "Object");
			return "for(Object elem : " + operationWhichReturnsCollection + "){\n\t" + sentences + "}\n";
		}
		return null;
		
	}
}
