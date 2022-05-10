package eu.remics.domainlogic.translation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import eu.redseeds.common.SCProjectHelper;
import eu.redseeds.sc.current.repository.SCProject;
import eu.redseeds.sc.current.repository.SCProjectContainer;
import eu.redseeds.scl.model.BusinessLayerFacade;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.DomainStatementDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionDTO;
import eu.redseeds.scl.rsl.rsldomainelements.notions.Notion;
import eu.redseeds.scl.uml.classes.kernel.Operation;
import eu.redseeds.scl.uml.classes.kernel.Parameter;
import eu.redseeds.scl.uml.classes.kernel.Class;

public class ModelHelper {
	
	private static List<Parameter> parameters;
	private static Pair<Class, Operation> pair;
	
	public static void setOperationParameters(List<Parameter> params){
		parameters = params;
	}
	
	public static List<Parameter> getOperationParameters(){
		return parameters;
	}
	
	public static void setClassOperationPair(Pair<Class, Operation> p){
		pair = p;
	}
	
	public static Pair<Class, Operation> getClassOperationPair(){
		return pair;
	}
	
	public static BusinessLayerFacade getBussinessLayerFacade(){
		SCProject scproj = SCProjectContainer.instance().getSCProject(SCProjectHelper.getActiveProject());
		return scproj.getBusinessLayerFacade();
	}
	
	public static Map<Pair<Class,Operation>, String> getOperationsToFill(){
		Map<Pair<Class,Operation>, String> operationCodePairs = new HashMap<Pair<Class,Operation>, String>();
		
		for(Notion notion : getBussinessLayerFacade().getNotionVertices()){
			for(DomainStatementDTO ds : ((NotionDTO)notion).getDomainStatementDTOList()){
				if(!ds.getDescription().isEmpty()){
					String phraseName = ds.getName();
					String[] p = phraseName.split("[^a-zżźćńółęąśŻŹĆĄŚĘŁÓŃA-Z0-9]+");
					String operationName = "";
					for(int i=0; i < p.length; i++){
						operationName += p[i];
					}
					for(Class classObj : getBussinessLayerFacade().getClassVertices()){
						if(classObj.getName().startsWith("~")){
							continue;
						}
						for(Operation operation : classObj.getOwnedOperationList()){
							String tmp = operation.getName();
							String[] o = tmp.split("[(]");
							String methodName = o[0];
							if(methodName.equalsIgnoreCase(operationName)){
								Pair<Class, Operation> pair = new Pair<Class, Operation>(classObj, operation);
								operationCodePairs.put(pair, ds.getDescription());
							}
						}
					}
				}
			}
		}
		return operationCodePairs;
	}

	public static Operation searchForOperation(String description) {
		String[] s = description.substring(1, description.length()-1).split("[ \t\r\n]+");
		String operationName = "";
		for(int i=0; i < s.length; i++){
			operationName += s[i];
		}
		for(eu.redseeds.scl.uml.classes.kernel.Class classObj : getBussinessLayerFacade().getClassVertices()){
			for(Operation operation : classObj.getOwnedOperationList()){
				String tmp = operation.getName();
				String[] o = tmp.split("[(]");
				String methodName = o[0];
				if(methodName.equalsIgnoreCase(operationName)){
					return operation;
				}
			}
		}
		return null;
	}
	
	
	
	
	
	/*public static List<Operation> getOperationsFromDetailedDesign(){
		List<Operation> operations = new ArrayList<Operation>();
		Map<String, String> notionsMap = getOperationsFromNotionsPhrases();
		Set<String> notions = notionsMap.keySet();
		for(eu.redseeds.scl.uml.classes.kernel.Class classObj : getBussinessLayerFacade().getClassVertices()){
			for(String notionName : notions){
				String[] n = notionName.split("[^a-zA-Z0-9]+");
				String className = "M";
				for(int i=0; i < n.length; i++){
					className += n[i];
				}
				if(classObj.getName().equalsIgnoreCase(className)){
					String phraseName = notionsMap.get(notionName);
					String[] p = phraseName.split("[^a-zA-Z0-9]+");
					String operationName = "";
					for(int i=0; i < p.length; i++){
						operationName += p[i];
					}
					for(Operation operation : classObj.getOwnedOperationList()){
						if(operation.getName().equalsIgnoreCase(operationName)){
							operations.add(operation);
						}
					}
				}
			}
		}
		return operations;
	}*/
}