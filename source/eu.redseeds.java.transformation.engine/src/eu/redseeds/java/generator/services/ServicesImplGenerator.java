package eu.redseeds.java.generator.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import eu.redseeds.java.generator.utils.GeneratorUtils;
import eu.redseeds.java.generator.utils.TraceTypes;
import eu.redseeds.java.repository.SCFacade;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionSpecialisationDTO;
import eu.redseeds.scl.model.sdsl.ClassDTO;
import eu.redseeds.scl.rsl.rsldomainelements.notions.Notion;
import eu.redseeds.scl.sclkernel.Stereotype;
import eu.redseeds.scl.sclkernel.StereotypeLinksToElement;
import eu.redseeds.scl.uml.classes.interfaces.Interface;
import eu.redseeds.scl.uml.classes.kernel.Class;
import eu.redseeds.scl.uml.classes.kernel.NamedElement;
import eu.redseeds.scl.uml.classes.kernel.Operation;
import eu.redseeds.scl.uml.classes.kernel.Parameter;
import eu.redseeds.scl.uml.classes.kernel.ParameterDirectionKind;
import eu.redseeds.scl.uml.classes.kernel.PrimitiveType;
import eu.redseeds.scl.uml.classes.kernel.Property;
import eu.redseeds.scl.uml.classes.kernel.Type;

public class ServicesImplGenerator {
	
	protected SCFacade scFacade;
	protected GeneratorUtils genUtils;
	
	public ServicesImplGenerator() {
		scFacade = SCFacade.instance();
		genUtils = new GeneratorUtils();
	}
	
	protected void deleteStereotypesVertices(Operation op) {
		for(StereotypeLinksToElement stLink : op.getStereotypeLinksToElementIncidences()){
			scFacade.getFacade().deleteEdge(stLink);
		}
		for(Stereotype st : op.getStereotypeList()){
			scFacade.getFacade().deleteVertex(st);
		}
	}
	
	protected void transformDTOtoEntity(Parameter param, boolean isCreateOperation, StringBuilder code, Map<Notion, Class> mainConcepts, Class serviceImpl) {
		if(param.getDirection() == null || !param.getDirection().equals(ParameterDirectionKind.RETURN)){
			String paramName = param.getName();
			List<? extends Type> types = param.getTypeList();
			Class dtoType = null;
			for(Type type : types){
				if(type instanceof Class){
					dtoType = (Class) type;
					break;
				}
			}
			
			Notion dataView = scFacade.getLinkedNotion((ClassDTO) dtoType, TraceTypes.DTO_MAPPING_NAME);
			if(((NotionDTO)dataView).getType().equals("")){
				if(isCreateOperation){
					return;
				}
				Class dto = scFacade.getLinkedClass(dataView, TraceTypes.DTO_MAPPING_NAME);
				Class entity = scFacade.getLinkedClass(dataView, TraceTypes.ENTITY_MAPPING_NAME);
				mainConcepts.put(dataView, entity);
				
				scFacade.createDependency(serviceImpl, new NamedElement[]{dto});
				scFacade.createDependency(serviceImpl, new NamedElement[]{entity});
				
				code
				.append(genDTOtoEntityCode(dto, entity, paramName, true));
			}
			else{
				Notion mainConcept = scFacade.getMainConcept(dataView);
				Class mainConceptEntity = scFacade.getLinkedClass((Notion)mainConcept, TraceTypes.ENTITY_MAPPING_NAME);
				mainConcepts.put(mainConcept, mainConceptEntity);
				
				List<NotionDTO> dataViewAttrList = ((NotionDTO)dataView).getNotionDTOAttributeList();
				List<NotionDTO> mainConceptAttrList = ((NotionDTO)mainConcept).getNotionDTOAttributeList();
				List<NotionSpecialisationDTO> generalizations = ((NotionDTO)mainConcept).getNotionSpecialisationDTOList();
				StringBuilder entitySetters = new StringBuilder();
				for(NotionDTO attr : dataViewAttrList){
					//check if Main Concept and Data View contain same attribute
					if(mainConceptAttrList.contains(attr)){
						String setterName = "set" + genUtils.toCamelCase(attr.getName());
						String getterName = "get" + genUtils.toCamelCase(attr.getName());
						entitySetters
						.append(mainConceptEntity.getName().toLowerCase() + "." + setterName + "(" + paramName + "." + getterName + "()" + ");\n");
					}
					//check if Main Concept base parent and Data View contain same attribute
					for(NotionSpecialisationDTO gen : generalizations){
						if(gen.getGeneralNotion() != null){
							NotionDTO general = gen.getGeneralNotion();
							if(general.getNotionDTOAttributeList().contains(attr)){
								String setterName = "set" + genUtils.toCamelCase(attr.getName());
								String getterName = "get" + genUtils.toCamelCase(attr.getName());
								entitySetters
								.append(mainConceptEntity.getName().toLowerCase() + "." + setterName + "(" + paramName + "." + getterName + "()" + ");\n");
							}
						}
					}
					//check for concepts related to Data View through attributes
					Notion sharedConcept = scFacade.searchConceptWithAttrSharedByDataView((NotionDTO)mainConcept, attr, null);
					if(sharedConcept != null){
						Class sharedDTO = scFacade.getLinkedClass(sharedConcept, TraceTypes.DTO_MAPPING_NAME);
						Class sharedEntity = scFacade.getLinkedClass(sharedConcept, TraceTypes.ENTITY_MAPPING_NAME);
						
						scFacade.createDependency(serviceImpl, new NamedElement[]{sharedDTO});
						scFacade.createDependency(serviceImpl, new NamedElement[]{sharedEntity});
						
						boolean relIsListType = scFacade.relationBeetwenIsList((NotionDTO)mainConcept, (NotionDTO)sharedConcept, null);
						if(relIsListType){
							//entity.setConceptList(dto.getConceptList())
							String setterName = "set" + sharedEntity.getName() + "List";
							String getterName = "get" + genUtils.toCamelCase(genUtils.toLowerCamelCase(sharedDTO.getName())) + "List";
							String conceptsList = genUtils.toLowerCamelCase(sharedEntity.getName()) + "s";
							
							entitySetters
							.append("List<"+sharedEntity.getName()+"> "+conceptsList+" = new ArrayList<"+sharedEntity.getName()+">();\n")
							.append("for("+sharedDTO.getName()+" "+genUtils.toLowerCamelCase(sharedDTO.getName())+" : "+paramName+"."+getterName+"()){\n")
							.append("	"+genDTOtoEntityCode(sharedDTO, sharedEntity, "", false))
							.append("	"+conceptsList+".add("+genUtils.toLowerCamelCase(sharedEntity.getName())+");\n")
							.append("}\n")
							.append(mainConceptEntity.getName().toLowerCase() + "." + setterName + "(" + conceptsList + ");\n");
						}
						else{
							//entity.setConcept(dto.getConcept())
							String setterName = "set" + sharedEntity.getName();
							
							entitySetters
							.append(sharedDTO.getName()+" "+genUtils.toLowerCamelCase(sharedDTO.getName())+" = "+paramName+".get"+genUtils.toCamelCase(sharedDTO.getName())+"();\n")
							.append(genDTOtoEntityCode(sharedDTO, sharedEntity, "", false))
							.append(mainConceptEntity.getName().toLowerCase() + "." + setterName + "(" + genUtils.toLowerCamelCase(sharedEntity.getName())+ ");\n");
						}
					}
				}
				
				//Entity entity = Entity.Factory.newInstance();
				//entity.set(dto.get());
				code
				.append(mainConceptEntity.getName() + " " + mainConceptEntity.getName().toLowerCase() + " = " + mainConceptEntity.getName() + ".Factory.newInstance();\n")
				.append(entitySetters.toString());
			}
			
		}
	}
	
	private String genDTOtoEntityCode(Class dto, Class entity, String dtoName, boolean withListGetter) {
		StringBuilder code = new StringBuilder();
		String params = "";
		if(dtoName.isEmpty()){
			dtoName = genUtils.toLowerCamelCase(dto.getName());
		}
		
		for(Operation operation : dto.getOperationList()){
			if(operation.getName().substring(1).startsWith("get")){
				//take also List<?> getters
				if(scFacade.isOperationReturnsObjectsList(operation)){
					if(!withListGetter){
						continue;
					}
					else{
						String opName = operation.getName().substring(1, operation.getName().indexOf("("));
						Type type = scFacade.getOperationReturnType(operation);
						String dtoType = ((PrimitiveType)type).getName().substring(((PrimitiveType)type).getName().indexOf("<")+1, ((PrimitiveType)type).getName().indexOf(">"));
						String dtoTypeLower = genUtils.toLowerCamelCase(dtoType);
						Class nestedDto = scFacade.getEntityOrDTOByType(dtoType);
						Class nestedEntity = scFacade.getEntityOrDTOByType(dtoType.replace("DTO", ""));
						String nestedEntityListName = genUtils.toLowerCamelCase(nestedEntity.getName()) + "s";
						
						code
						.append("List<"+nestedEntity.getName()+"> "+nestedEntityListName+" = new ArrayList<"+nestedEntity.getName()+">();\n")
						.append("for("+dtoType+" "+dtoTypeLower+" : "+dtoName+"."+opName+"()){\n")
						.append("	"+genDTOtoEntityCode(nestedDto, nestedEntity, dtoTypeLower, false))
						.append("	"+nestedEntityListName+".add("+genUtils.toLowerCamelCase(nestedEntity.getName())+");\n")
						.append("}\n");
						
						params += nestedEntityListName + ", ";
					}
				}
				//take only primitive type getters
				else{
					String opName = operation.getName().substring(1, operation.getName().indexOf("("));
					params += dtoName + "." + opName + "()" + ", ";
				}
			}
		}
		
		if(params.endsWith(", ")){
			int idx = params.lastIndexOf(", ");
			params = params.substring(0, idx);
		}
		
		//Entity entity = Entity.Factory.newInstance(dtoName.getX(), dtoName.getY(), ...)
		code
		.append(entity.getName()+" "+genUtils.toLowerCamelCase(entity.getName())+" = "+entity.getName()+".Factory.newInstance(")
		.append(params)
		.append(");\n");
		
		return code.toString();
	}
	
	protected void genAllDTOsGetter(Operation getAllConcepts, Notion sharedConcept, Class serviceImpl) {
		Class sharedDTO = scFacade.getLinkedClass(sharedConcept, TraceTypes.DTO_MAPPING_NAME);
		Class sharedEntity = scFacade.getLinkedClass(sharedConcept, TraceTypes.ENTITY_MAPPING_NAME);
		Interface daoInterface = scFacade.getLinkedInterface(sharedConcept, TraceTypes.DAO_MAPPING_NAME);
		String conceptsList = sharedEntity.getName()+"s";
		
		//OperationDTO getAllConcepts = (OperationDTO) scFacade.createOperation("getAll"+conceptsList, VisibilityKind.PUBLIC, new Object[][]{{null, "List<"+sharedDTO.getName()+">"}});
		
		//import SharedDTO, SharedEntity
		scFacade.createDependency(serviceImpl, new NamedElement[]{sharedDTO});
		scFacade.createDependency(serviceImpl, new NamedElement[]{sharedEntity});
		
		String c1 = "List<"+sharedDTO.getName()+"> results = new ArrayList<"+sharedDTO.getName()+">();\n";
		
		String c2 = new StringBuilder()
		.append("	List<"+sharedEntity.getName()+"> "+genUtils.toLowerCamelCase(conceptsList)+" = get"+daoInterface.getName()+"().findAll();\n")
		.append("	for("+sharedEntity.getName()+" "+genUtils.toLowerCamelCase(sharedEntity.getName())+" : "+genUtils.toLowerCamelCase(conceptsList)+"){\n")
		.append("		"+genEntityToDTOCode(sharedEntity, sharedDTO))
		.append("		results.add("+genUtils.toLowerCamelCase(sharedDTO.getName())+");\n")
		.append("	}\n")
		.toString();
		
		String c3 = "return results;\n";
		
		c2 = genTryCatch(c2);
		
		String code = new StringBuilder().append(c1).append(c2).append(c3).toString();
		
		genUtils.genCode((Operation)getAllConcepts, code);
	}
	
	protected String genTryCatch(String codeInjection) {
		StringBuilder code = new StringBuilder();
		code
		.append("try{\n")
		.append(codeInjection)
		.append("} catch(Exception e){\n")
		.append("	e.printStackTrace();\n")
		.append("}\n");
		
		return code.toString();
	}
	
	private String genEntityToDTOCode(Class entity, Class dto) {
		StringBuilder code = new StringBuilder();
		String params = "";
		
		for(Operation operation : entity.getOperationList()){
			if(operation.getName().substring(1).startsWith("get")){
				//skipping List<Concept/ConceptDTO> operation
				if(scFacade.isOperationReturnsObjectsList(operation)){
					continue;
				}
				String opName = operation.getName().substring(1, operation.getName().indexOf("("));
				params += genUtils.toLowerCamelCase(entity.getName()) + "." + opName + "()" + ", ";
			}
		}
		
		if(params.endsWith(", ")){
			int idx = params.lastIndexOf(", ");
			params = params.substring(0, idx);
		}
		
		//ConceptDTO conceptdto = new ConceptDTO(entityName.getX(), entityName.getY(), ...)
		code
		.append(dto.getName()+" "+genUtils.toLowerCamelCase(dto.getName())+" = new "+dto.getName()+"(")
		.append(params)
		.append(");\n");
		
		return code.toString();
	}
	
	public String genReadListMethod(Class listDto, Notion listView) {
		Notion mainConcept = scFacade.getMainConcept(listView);
		Class entity = scFacade.getLinkedClass(mainConcept, TraceTypes.ENTITY_MAPPING_NAME);
		Interface dao = scFacade.getLinkedInterface(mainConcept, TraceTypes.DAO_MAPPING_NAME);
		Class listItemDto = scFacade.getLinkedClass(listView, TraceTypes.DTO_LIST_ROW_MAPPING_NAME);
		
		String addListItemOperation = null;
		for(Operation operation : listDto.getOperationList()){
			if(operation.getName().substring(1).startsWith("add")){
				addListItemOperation = operation.getName().substring(1, operation.getName().indexOf("("));
				break;
			}
		}
		
		String c1 = listDto.getName()+" "+genUtils.toLowerCamelCase(listDto.getName())+" = new "+listDto.getName()+"();\n";
		
		StringBuilder code = new StringBuilder();
		code
		.append("List<"+entity.getName()+"> "+genUtils.toLowerCamelCase(entity.getName()+"s")+" = get"+dao.getName()+"().findAll();\n")
		.append("for("+entity.getName()+" "+genUtils.toLowerCamelCase(entity.getName())+" : "+genUtils.toLowerCamelCase(entity.getName()+"s")+"){\n")
		.append("	"+listItemDto.getName()+" "+genUtils.toLowerCamelCase(listItemDto.getName())+" = new "+listItemDto.getName()+"();\n")
		.append(genDTOSettersForEntity(listItemDto, entity))
		.append("	"+genUtils.toLowerCamelCase(listDto.getName())+"."+addListItemOperation+"("+genUtils.toLowerCamelCase(listItemDto.getName())+");\n")
		.append("}\n")
		.append("get"+dao.getName()+"()"+".getSession().clear();\n");
		
		return c1 + genTryCatch(code.toString()) + "return " + genUtils.toLowerCamelCase(listDto.getName()) + ";\n";
	}
	
	public String genReadListByCriteriaMethod(String paramName, Class simpleDto, Class listDto, Notion listView) {
		Notion mainConcept = scFacade.getMainConcept(listView);
		Class entity = scFacade.getLinkedClass(mainConcept, TraceTypes.ENTITY_MAPPING_NAME);
		Interface dao = scFacade.getLinkedInterface(mainConcept, TraceTypes.DAO_MAPPING_NAME);
		Class listItemDto = scFacade.getLinkedClass(listView, TraceTypes.DTO_LIST_ROW_MAPPING_NAME);
		
		String addListItemOperation = null;
		for(Operation operation : listDto.getOperationList()){
			if(operation.getName().substring(1).startsWith("add")){
				addListItemOperation = operation.getName().substring(1, operation.getName().indexOf("("));
				break;
			}
		}
		
		String entityVariable = genUtils.toLowerCamelCase(entity.getName()) + "Criteria";
		
		StringBuilder setters = new StringBuilder()
		.append(entity.getName()+" "+entityVariable+" = "+entity.getName()+".Factory.newInstance();\n")
		.append(genEntitySettersForDTO(paramName, simpleDto, entity, entityVariable));
		
		String c1 = listDto.getName()+" "+genUtils.toLowerCamelCase(listDto.getName())+" = new "+listDto.getName()+"();\n";
		
		StringBuilder code = new StringBuilder();
		code
		.append("List<"+entity.getName()+"> "+genUtils.toLowerCamelCase(entity.getName()+"s")+" = get"+dao.getName()+"().findByExample("+entityVariable+");\n")
		.append("for("+entity.getName()+" "+genUtils.toLowerCamelCase(entity.getName())+" : "+genUtils.toLowerCamelCase(entity.getName()+"s")+"){\n")
		.append("	"+listItemDto.getName()+" "+genUtils.toLowerCamelCase(listItemDto.getName())+" = new "+listItemDto.getName()+"();\n")
		.append(genDTOSettersForEntity(listItemDto, entity))
		.append("	"+genUtils.toLowerCamelCase(listDto.getName())+"."+addListItemOperation+"("+genUtils.toLowerCamelCase(listItemDto.getName())+");\n")
		.append("}\n")
		.append("get"+dao.getName()+"()"+".getSession().clear();\n");
		
		return setters + c1 + genTryCatch(code.toString()) + "return " + genUtils.toLowerCamelCase(listDto.getName()) + ";\n";
	}
	
	public String genReadByIdMethod(String paramName, Class conceptDto, Class simpleDto, Notion simpleView) {
		Notion mainConcept = scFacade.getMainConcept(simpleView);
		Class entity = scFacade.getLinkedClass(mainConcept, TraceTypes.ENTITY_MAPPING_NAME);
		Interface dao = scFacade.getLinkedInterface(mainConcept, TraceTypes.DAO_MAPPING_NAME);
		
		String c1 = simpleDto.getName()+" "+genUtils.toLowerCamelCase(simpleDto.getName())+" = new "+simpleDto.getName()+"();\n";
		
		StringBuilder code = new StringBuilder();
		code
		.append(entity.getName()+" "+genUtils.toLowerCamelCase(entity.getName())+" = get"+dao.getName()+"().findById("+paramName+".getId()"+");\n")
		.append(genDTOSettersForEntity(simpleDto, entity))
		.append("get"+dao.getName()+"()"+".getSession().clear();\n");
		
		return c1 + genTryCatch(code.toString()) + "return " + genUtils.toLowerCamelCase(simpleDto.getName()) + ";\n";
	}
	
	private String genDTOSettersForEntity(Class listItemDto, Class mainEntity) {
		StringBuilder code = new StringBuilder();
		List<? extends Property> attrs = listItemDto.getOwnedAttributeList();
		List<String> attrsNames = new ArrayList<String>();
		for(Property prop : attrs){
			attrsNames.add(prop.getName().toLowerCase());
		}
		
		for(Operation operation : listItemDto.getOperationList()){
			if(operation.getName().substring(1).startsWith("get")){
				String propertyNameFromOperation = operation.getName().substring(4, operation.getName().indexOf("(")).toLowerCase();
				if(scFacade.isOperationReturnsObjectsList(operation) || scFacade.isOperationReturnsDTO(operation) || !attrsNames.contains(propertyNameFromOperation)){
					continue;
				}
				String getterName = operation.getName().substring(1, operation.getName().indexOf("("));
				String setterName = getterName.replaceFirst("g", "s");
				code
				.append("	"+genUtils.toLowerCamelCase(listItemDto.getName())+"."+setterName+"("+genUtils.toLowerCamelCase(mainEntity.getName())+"."+getterName+"()"+");\n");
			}
		}
		
		for(Property property : listItemDto.getOwnedAttributeList()){
			/*if(scFacade.isPrimitiveType(property.getTypeList().get(0).getName())){
				code
				.append("	"+genUtils.toLowerCamelCase(listItemDto.getName())+".set"+genUtils.toCamelCase(property.getName())+"("+genUtils.toLowerCamelCase(mainEntity.getName())+".get"+genUtils.toCamelCase(property.getName())+"()"+");\n");
			}*/
			
			if(scFacade.isDTOType(property.getTypeList().get(0).getName())){
				Class dto = scFacade.getEntityOrDTOByType(property.getTypeList().get(0).getName());
				Notion concept = scFacade.getLinkedNotion((ClassDTO)dto, TraceTypes.DTO_MAPPING_NAME);
				Class entity = scFacade.getLinkedClass(concept, TraceTypes.ENTITY_MAPPING_NAME);
				
				code
				.append("	"+entity.getName()+" "+genUtils.toLowerCamelCase(entity.getName())+" = "+genUtils.toLowerCamelCase(mainEntity.getName())+".get"+entity.getName()+"();\n")
				.append("	"+genEntityToDTOCode(entity, dto))
				.append("	"+genUtils.toLowerCamelCase(listItemDto.getName())+".set"+genUtils.toCamelCase(dto.getName())+"("+genUtils.toLowerCamelCase(dto.getName())+");\n");
			}
			
			if(scFacade.isListOfDTOType(property.getTypeList().get(0).getName())){
				String name = property.getTypeList().get(0).getName();
				String dtoName = name.substring(name.indexOf("<")+1, name.indexOf(">"));
				Class dto = scFacade.getEntityOrDTOByType(dtoName);
				Notion concept = scFacade.getLinkedNotion((ClassDTO)dto, TraceTypes.DTO_MAPPING_NAME);
				Class entity = scFacade.getLinkedClass(concept, TraceTypes.ENTITY_MAPPING_NAME);
				
				String dtoList = genUtils.toLowerCamelCase(dto.getName()) + "List";
				code
				.append("	"+"List<"+dto.getName()+"> "+dtoList+" = new ArrayList<"+dto.getName()+">();\n")
				.append("	"+"for("+entity.getName()+" "+genUtils.toLowerCamelCase(entity.getName())+" : "+genUtils.toLowerCamelCase(mainEntity.getName())+".get"+entity.getName()+"List()){\n")
				.append("	"+"	"+genEntityToDTOCode(entity, dto))
				.append("	"+"	"+dtoList+".add("+genUtils.toLowerCamelCase(dto.getName())+");\n")
				.append("	"+"}\n")
				.append("	"+genUtils.toLowerCamelCase(listItemDto.getName())+".set"+genUtils.toCamelCase( genUtils.toLowerCamelCase(dto.getName())+"List")+"("+dtoList+");\n");
			}
		}
		
		return code.toString();
	}
	
	private String genEntitySettersForDTO(String paramName, Class dto, Class entity, String entityName) {
		StringBuilder code = new StringBuilder();
		List<? extends Property> attrs = dto.getOwnedAttributeList();
		List<String> attrsNames = new ArrayList<String>();
		for(Property prop : attrs){
			attrsNames.add(prop.getName().toLowerCase());
		}
		
		for(Operation operation : dto.getOperationList()){
			if(operation.getName().substring(1).startsWith("get")){
				String propertyNameFromOperation = operation.getName().substring(4, operation.getName().indexOf("(")).toLowerCase();
				if(scFacade.isOperationReturnsObjectsList(operation) || scFacade.isOperationReturnsDTO(operation) || !attrsNames.contains(propertyNameFromOperation)){
					continue;
				}
				String getterName = operation.getName().substring(1, operation.getName().indexOf("("));
				String setterName = getterName.replaceFirst("g", "s");
				code
				.append(entityName+"."+setterName+"("+paramName+"."+getterName+"()"+");\n");
			}
		}
		
		return code.toString();
	}
}
