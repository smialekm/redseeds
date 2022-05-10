package eu.redseeds.java.generator.utils;

import java.util.List;

import eu.redseeds.java.repository.SCFacade;
import eu.redseeds.scl.model.rsl.rsldomainelements.domainelements.DomainElementRelationshipDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionSpecialisationDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionTypesEnum;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.PrimitiveDataTypeDTO;
import eu.redseeds.scl.model.sdsl.ClassDTO;
import eu.redseeds.scl.model.sdsl.InterfaceDTO;
import eu.redseeds.scl.model.sdsl.OperationDTO;
import eu.redseeds.scl.model.sdsl.PackageDTO;
import eu.redseeds.scl.rsl.rsldomainelements.notions.AttributeDataTypes;
import eu.redseeds.scl.rsl.rsldomainelements.notions.Notion;
import eu.redseeds.scl.uml.classes.interfaces.Interface;
import eu.redseeds.scl.uml.classes.kernel.Association;
import eu.redseeds.scl.uml.classes.kernel.Comment;
import eu.redseeds.scl.uml.classes.kernel.LiteralInteger;
import eu.redseeds.scl.uml.classes.kernel.LiteralUnlimitedNatural;
import eu.redseeds.scl.uml.classes.kernel.NamedElement;
import eu.redseeds.scl.uml.classes.kernel.Operation;
import eu.redseeds.scl.uml.classes.kernel.Class;
import eu.redseeds.scl.uml.classes.kernel.PrimitiveType;
import eu.redseeds.scl.uml.classes.kernel.Property;
import eu.redseeds.scl.uml.classes.kernel.VisibilityKind;
import eu.redseeds.scl.uml.classes.kernel.Package;

public class GeneratorUtils {
	
	private SCFacade scFacade;

	public GeneratorUtils() {
		scFacade = SCFacade.instance();
	}
	
	public String toCamelCase(String name) {
		String[] parts = name.split("\\s+");
		String camelCaseString = "";
		for (String part : parts){
			String subname = "";
			if(part.substring(1).endsWith("List")){
				subname = part.substring(1);
			}
			else{
				subname = part.substring(1).toLowerCase();
			}
			camelCaseString = camelCaseString + part.substring(0, 1).toUpperCase() + subname;
		}
		return camelCaseString;
	}
	
	public String toLowerCamelCase(String name) {
		String camelCase = toCamelCase(name);
		camelCase = camelCase.substring(0, 1).toLowerCase() + camelCase.substring(1);
		return camelCase;
	}
	
	public void genViewMain() {
		for(Notion notion : scFacade.getFacade().getNotionVertices()){
			String type = ((NotionDTO)notion).getType();
			if(type.equals("tagFrame") || type.equals("tagMessage") || type.equals("tagConfirmation") || type.equals("tagOption")){
				
			}
		}
	}

	public void genCode(Operation operation, String code) {
		if(operation.getOwnedCommentList() != null && !operation.getOwnedCommentList().isEmpty() && operation.getOwnedCommentList().size() == 1){
			Comment comment = operation.getOwnedCommentList().get(0);
			comment.setBody(comment.getBody() + code);
		}
		else{
			Comment comment = scFacade.getFacade().createUml$classes$kernel$Comment();
			comment.setBody("000code" + code);
			operation.addOwnedComment(comment);
		}
	}
	
	public void genPrimitiveTypeAttribute(NotionDTO attrNotion, Class umlClass) {
		String notNameCamelCase = toLowerCamelCase(attrNotion.getName());
		String notNameUpperCamelCase = toCamelCase(attrNotion.getName());
		
		PrimitiveDataTypeDTO primType = attrNotion.getDataType();
		AttributeDataTypes type = primType.getTypeName();
		PrimitiveType dataType = null;
		PackageDTO javaPack = scFacade.getPackage("java");
		PackageDTO utilPack = (PackageDTO) scFacade.getElementFromPackage(javaPack, "util", Package.class);
		Class date = null;
		Class timer = null;
		if(type.equals(AttributeDataTypes.STRING)){
			dataType = scFacade.getPrimitiveType("String");
		} else if(type.equals(AttributeDataTypes.TIME)){
			timer = (Class) scFacade.getElementFromPackage(utilPack, "Timer", Class.class);
		} else if(type.equals(AttributeDataTypes.BOOLEAN)){
			dataType = scFacade.getPrimitiveType("boolean");
		} else if(type.equals(AttributeDataTypes.DATE)){
			date = (Class) scFacade.getElementFromPackage(utilPack, "Date", Class.class);
			scFacade.createDependency(umlClass, new NamedElement[]{date});
		} else if(type.equals(AttributeDataTypes.INTEGER)){
			dataType = scFacade.getPrimitiveType("int");
		} else if(type.equals(AttributeDataTypes.FLOAT)){
			dataType = scFacade.getPrimitiveType("float");
		}
		
		Property prop = scFacade.getFacade().createProperty();
		prop.setName(notNameCamelCase);
		prop.setVisibility(VisibilityKind.PRIVATE);
		prop.setIsUnique(true);
		
		Object datatype = null;
		if(date != null){
			datatype = date;
			prop.addType(date);
		}
		else if(timer != null){
			datatype = timer;
			prop.addType(timer);
		}
		else{
			PrimitiveType[] listOfPrimitiveType = null;
			NotionDTO listView = (NotionDTO) scFacade.getLinkedNotion((ClassDTO)umlClass, TraceTypes.DTO_LIST_ROW_MAPPING_NAME);
			NotionDTO treeView = (NotionDTO) scFacade.getLinkedNotion((ClassDTO)umlClass, TraceTypes.DTO_CONCRETE_TREE_MAPPING_NAME);
			NotionDTO simpleView = (NotionDTO) scFacade.getLinkedNotion((ClassDTO)umlClass, TraceTypes.DTO_MAPPING_NAME);
			if(listView != null){
				listOfPrimitiveType = checkPrimitiveTypeListForAttribute(listView, attrNotion, dataType.getName());
			}
			else if(treeView != null){
				listOfPrimitiveType = checkPrimitiveTypeListForAttribute(treeView, attrNotion, dataType.getName());
			}
			else if(simpleView != null){
				listOfPrimitiveType = checkPrimitiveTypeListForAttribute(simpleView, attrNotion, dataType.getName());
			}
			
			if(listOfPrimitiveType != null && listOfPrimitiveType[0] != null && listOfPrimitiveType[1] != null && listOfPrimitiveType[2] != null){
				//List<ConceptDTO> conceptDTOList/ConceptDTO conceptDTO
				prop.addType(listOfPrimitiveType[0]);
				datatype = listOfPrimitiveType[0].getName();
				String propName = "";
				if(listOfPrimitiveType[0].getName().startsWith("List<")){
					propName = toLowerCamelCase(listOfPrimitiveType[0].getName().replaceAll("List<", "").replaceAll(">", "")) + "List";
					prop.setName(propName);
				}
				else{
					propName = toLowerCamelCase(listOfPrimitiveType[0].getName());
					prop.setName(propName);
				}
				umlClass.addOwnedAttribute(prop);

				//List<ConceptDTO> getConceptDTOList()/ConceptDTO getConceptDTO()
				OperationDTO getter = (OperationDTO) scFacade.createOperation("get"+toCamelCase(propName), VisibilityKind.PUBLIC,
						new Object[][]{{null, datatype}});
				genCode((Operation)getter, "return " + prop.getName() + ";\n");
				umlClass.addOperation((Operation)getter);
				
				//void setConceptDTOList(List<ConceptDTO>)/void setConceptDTO(ConceptDTO)
				OperationDTO setter = (OperationDTO) scFacade.createOperation("set"+toCamelCase(propName), VisibilityKind.PUBLIC,
						new Object[][]{{prop.getName(), datatype}});
				genCode((Operation)setter, "this." + prop.getName() + " = " + prop.getName() + ";\n");
				umlClass.addOperation((Operation)setter);
				
				//List<PrimitiveType> getAttributeList()/PrimitiveType getAttribute()
				datatype = listOfPrimitiveType[1].getName();
				getter = (OperationDTO) scFacade.createOperation("get" + notNameUpperCamelCase, VisibilityKind.PUBLIC,
						new Object[][]{{null, datatype}});
				//List<PrimitiveType> attr = new ArrayList<PrimitiveType>()
				if(listOfPrimitiveType[0].getName().startsWith("List<")){
					//import ArrayList
					ClassDTO arraylist = (ClassDTO) scFacade.getElementFromPackage(utilPack, "ArrayList", Class.class);
					scFacade.createDependency(umlClass, new NamedElement[]{(Class)arraylist});
					
					String conceptType = listOfPrimitiveType[0].getName().replaceAll("List<", "").replaceAll(">", "");
					
					String code = new StringBuilder()
					.append(datatype + " " + toLowerCamelCase(notNameUpperCamelCase) + " = new Array" + datatype + "();\n")
					.append("for(" + conceptType + " elem : " + propName + "){\n")
					.append("	" + toLowerCamelCase(notNameUpperCamelCase) + ".add(elem." + "get" + notNameUpperCamelCase + "()" + ");\n")
					.append("}\n")
					.append("return " + toLowerCamelCase(notNameUpperCamelCase) + ";\n")
					.toString();
					
					genCode((Operation)getter, code);
					umlClass.addOperation((Operation)getter);
				}
				else{
					String code = new StringBuilder()
					.append("return " + propName + ".get" + notNameUpperCamelCase + "()" + ";\n")
					.toString();
					
					genCode((Operation)getter, code);
					umlClass.addOperation((Operation)getter);
				}
				
				//List<Long> getAttributeIds()/Long getAttributeID()
				String idFieldName = "";
				if(listOfPrimitiveType[0].getName().startsWith("List<")){
					idFieldName = toCamelCase(listOfPrimitiveType[0].getName().replaceAll("List<", "").replaceAll(">", "")) + "Ids";
				}
				else{
					idFieldName = toCamelCase(listOfPrimitiveType[0].getName()) + "Id";
				}
				
				datatype = listOfPrimitiveType[2].getName();
				getter = (OperationDTO) scFacade.createOperation("get"+idFieldName, VisibilityKind.PUBLIC,
						new Object[][]{{null, datatype}});
				//List<PrimitiveType> attr = new ArrayList<PrimitiveType>()
				if(listOfPrimitiveType[0].getName().startsWith("List<")){
					String conceptType = listOfPrimitiveType[0].getName().replaceAll("List<", "").replaceAll(">", "");
					
					String code = new StringBuilder()
					.append(datatype + " " + toLowerCamelCase(idFieldName) + " = new Array" + datatype + "();\n")
					.append("for(" + conceptType + " elem : " + propName + "){\n")
					.append("	" + toLowerCamelCase(idFieldName) + ".add(elem." + "getId()" + ");\n")
					.append("}\n")
					.append("return " + toLowerCamelCase(idFieldName) + ";\n")
					.toString();
					
					genCode((Operation)getter, code);
					umlClass.addOperation((Operation)getter);
				}
				else{
					String code = new StringBuilder()
					.append("return " + propName + ".getId()" + ";\n")
					.toString();
					
					genCode((Operation)getter, code);
					umlClass.addOperation((Operation)getter);
				}
				
				
				//void setAttributeIds()/void setAttributeId()
				if(listOfPrimitiveType[0].getName().startsWith("List<")){
					idFieldName = toCamelCase(listOfPrimitiveType[0].getName().replaceAll("List<", "").replaceAll(">", "")) + "Ids";
					datatype = listOfPrimitiveType[2].getName();
					setter = (OperationDTO) scFacade.createOperation("set"+idFieldName, VisibilityKind.PUBLIC,
							new Object[][]{{toLowerCamelCase(idFieldName), datatype}});
					
					String code = new StringBuilder()
					.append("for(int i=0; i < " + toLowerCamelCase(idFieldName) + ".size(); i++){\n")
					.append("	Long id = " + toLowerCamelCase(idFieldName) + ".get(i);\n")
					.append("	" + toLowerCamelCase(propName) + ".get(i).setId(id);\n")
					.append("}\n")
					.toString();
					
					genCode((Operation)setter, code);
					umlClass.addOperation((Operation)setter);
				}
				else{
					idFieldName = toCamelCase(listOfPrimitiveType[0].getName()) + "Id";
					datatype = listOfPrimitiveType[2].getName();
					setter = (OperationDTO) scFacade.createOperation("set"+idFieldName, VisibilityKind.PUBLIC,
							new Object[][]{{toLowerCamelCase(idFieldName), datatype}});
					
					String code = new StringBuilder()
					.append(propName + ".setId(" + toLowerCamelCase(idFieldName) + ");\n")
					.toString();
					
					genCode((Operation)setter, code);
					umlClass.addOperation((Operation)setter);
				}
				
				return;
			}
			else{
				prop.addType(dataType);
				datatype = dataType.getName();
			}
		}
		
		OperationDTO getter = (OperationDTO) scFacade.createOperation("get"+notNameUpperCamelCase, VisibilityKind.PUBLIC,
														new Object[][]{{null, datatype}});
		
		OperationDTO setter = (OperationDTO) scFacade.createOperation("set"+notNameUpperCamelCase, VisibilityKind.PUBLIC,
														new Object[][]{{notNameCamelCase, datatype}});
		
		genCode((Operation)getter, "return " + prop.getName() + ";\n");
		genCode((Operation)setter, "this." + prop.getName() + " = " + notNameCamelCase + ";\n");
		
		umlClass.addOwnedAttribute(prop);
		umlClass.addOperation((Operation)getter);
		umlClass.addOperation((Operation)setter);
	}
	
	private PrimitiveType[] checkPrimitiveTypeListForAttribute(NotionDTO dataView, NotionDTO attrNotion, String attrType) {
		if(dataView == null || 
			(!dataView.getType().equals(NotionTypesEnum.Simple_View.tag()) 
			 && !dataView.getType().equals(NotionTypesEnum.List_View.tag())
			 && !dataView.getType().equals(NotionTypesEnum.Tree_View.tag())))
			return null;
			
		Notion mainConcept = scFacade.getMainConcept((Notion)dataView);
		if(mainConcept == null) return null;
		
		PrimitiveType[] listType = searchListTypeAmongAssociations((NotionDTO)mainConcept, attrNotion, attrType, null);
		if(listType != null && listType[0] != null && listType[1] != null && listType[2] != null) return listType;
		
		return null;
	}
	
	private PrimitiveType[] searchListTypeAmongAssociations(NotionDTO mainConcept, NotionDTO attrNotion, String attrType, NotionDTO visitedNode) {
		//check if main concept already share attribute with Data View
		//if so there is no point looking among associations
		for(NotionDTO attribute : ((NotionDTO)mainConcept).getNotionDTOAttributeList()){
			if(attrNotion.equals(attribute)){
				return new PrimitiveType[3];
			}
		}
		
		for(DomainElementRelationshipDTO rel : mainConcept.getDomainElementRelationshipDTOList()){
			//[SRC]------*-[TARGET]
			if(!rel.getTargetMultiplicity().equals("0") && !rel.getTargetMultiplicity().equals("1")){
				NotionDTO targetConcept = (NotionDTO) rel.getTarget();
				if(targetConcept.equals(mainConcept)){
					continue;
				}
				//check if Data View and target Concept share same attribute
				for(NotionDTO attribute : targetConcept.getNotionDTOAttributeList()){
					if(attribute.equals(attrNotion)){
						//get or create list for primitives types e.g List<String>
						Class targetConceptDTO = scFacade.getLinkedClass((Notion)targetConcept, TraceTypes.DTO_MAPPING_NAME);
						PrimitiveType listType = scFacade.getPrimitiveType("List<"+targetConceptDTO.getName()+">");
						PrimitiveType listType2 = scFacade.getPrimitiveType("List<"+attrType+">");
						PrimitiveType listType3 = scFacade.getPrimitiveType("List<Long>");
						if(listType == null){
							listType = scFacade.addPrimitiveType("List<"+targetConceptDTO.getName()+">");
						}
						if(listType2 == null){
							listType2 = scFacade.addPrimitiveType("List<"+attrType+">");
						}
						if(listType3 == null){
							listType3 = scFacade.addPrimitiveType("List<Long>");
						}
						PrimitiveType[] types = new PrimitiveType[3];
						types[0] = listType;
						types[1] = listType2;
						types[2] = listType3;
						return types;
					}
				}
			}
			//[SRC]------1-[TARGET]
			else if(rel.getTargetMultiplicity().equals("1")){
				NotionDTO targetConcept = (NotionDTO) rel.getTarget();
				if(targetConcept.equals(mainConcept)){
					continue;
				}
				//check if Data View and target Concept share same attribute
				for(NotionDTO attribute : targetConcept.getNotionDTOAttributeList()){
					if(attribute.equals(attrNotion)){
						//get or create primitive type e.g String, Long
						Class targetConceptDTO = scFacade.getLinkedClass((Notion)targetConcept, TraceTypes.DTO_MAPPING_NAME);
						PrimitiveType type = scFacade.getPrimitiveType(targetConceptDTO.getName());
						PrimitiveType type2 = scFacade.getPrimitiveType(attrType);
						PrimitiveType type3 = scFacade.getPrimitiveType("Long");
						if(type == null){
							type = scFacade.addPrimitiveType(targetConceptDTO.getName());
						}
						if(type2 == null){
							type2 = scFacade.addPrimitiveType(attrType);
						}
						if(type3 == null){
							type3 = scFacade.addPrimitiveType("Long");
						}
						PrimitiveType[] types = new PrimitiveType[3];
						types[0] = type;
						types[1] = type2;
						types[2] = type3;
						return types;
					}
				}
			}
		}
		
		for(NotionSpecialisationDTO specialization : mainConcept.getNotionSpecialisationDTOList()){
			//[SRC]<|-----[TARGET] generalization
			NotionDTO specialisedNotion = specialization.getSpecialisedNotion();
			if(specialisedNotion != null && !specialisedNotion.equals(mainConcept)){
				if(visitedNode != null && visitedNode.equals(specialisedNotion)){
					continue;
				}
				PrimitiveType[] listType = searchListTypeAmongAssociations(specialisedNotion, attrNotion, attrType, mainConcept);
				if(listType != null)
					return listType;
			}
			//[SRC]-----|>[TARGET] generalization
			NotionDTO generalNotion = specialization.getGeneralNotion();
			if(generalNotion != null && !generalNotion.equals(mainConcept)){
				if(visitedNode != null && visitedNode.equals(generalNotion)){
					continue;
				}
				PrimitiveType[] listType = searchListTypeAmongAssociations(generalNotion, attrNotion, attrType, mainConcept);
				if(listType != null)
					return listType;
			}
		}
		return null;
	}
	
	public void genAssociations(Notion notion, String mappingName) {
		Class umlClass = scFacade.getLinkedClass(notion, mappingName);
		List<DomainElementRelationshipDTO> relations = ((NotionDTO)notion).getDomainElementRelationshipDTOList();
		String notName = ((NotionDTO)notion).getName();
		
		for(DomainElementRelationshipDTO rel : relations){
			NotionDTO relatedNotion = (NotionDTO) rel.getTarget();
			String relNotionName = relatedNotion.getName();
			Class relatedUmlClass = scFacade.getLinkedClass((Notion)relatedNotion, mappingName);
			
			//for Data Views do not process main concept
			if(!((NotionDTO)notion).getType().equals("")){
				NotionDTO mainConcept = (NotionDTO) scFacade.getMainConcept(notion);
				if(mainConcept != null && relatedNotion.equals(mainConcept)){
					continue;
				}
			}
			//check for tree relationship
			if(relatedNotion.equals(notion)){
				NotionDTO src = (NotionDTO) rel.getSource();
				if(src.equals(notion)){
					//parent-child relationship
				}
				else{
					continue;
				}
			}
			if(relatedUmlClass == null){
				continue;
			}
			
			Property p1 = scFacade.getFacade().createProperty();
			p1.addType(umlClass);
			Property p2 = scFacade.getFacade().createProperty();
			p2.addType(relatedUmlClass);
			Association a = scFacade.getFacade().createAssociation();
			a.addMemberEnd(p1);
			a.addMemberEnd(p2);
			
			String propertyName1 = toLowerCamelCase(notName);
			String propertyName2 = toLowerCamelCase(relNotionName);
			
			if(!rel.isDirected()){
				//[SRC]--------[TARGET]
				if( (rel.getSourceMultiplicity().equals("0") || rel.getSourceMultiplicity().equals("1")) &&
					(rel.getTargetMultiplicity().equals("0") || rel.getTargetMultiplicity().equals("1")) ){
					
					genFieldsForIndirected(umlClass, relatedUmlClass, p1, p2, "0..0", propertyName1, propertyName2);
					genGetterAndSetterForIndirected(umlClass, relatedUmlClass, p1, p2, "0..0");
				}
				//[SRC]------*-[TARGET]
				else if( (rel.getSourceMultiplicity().equals("0") || rel.getSourceMultiplicity().equals("1")) &&
					(!rel.getTargetMultiplicity().equals("0") && !rel.getTargetMultiplicity().equals("1")) ){
					
					genFieldsForIndirected(umlClass, relatedUmlClass, p1, p2, "0..*", propertyName1, propertyName2);
					genGetterAndSetterForIndirected(umlClass, relatedUmlClass, p1, p2, "0..*");
				}
				//[SRC]-*------[TARGET]
				else if( (!rel.getSourceMultiplicity().equals("0") && !rel.getSourceMultiplicity().equals("1")) &&
					(rel.getTargetMultiplicity().equals("0") || rel.getTargetMultiplicity().equals("1")) ){
					
					genFieldsForIndirected(umlClass, relatedUmlClass, p1, p2, "*..0", propertyName1, propertyName2);
					genGetterAndSetterForIndirected(umlClass, relatedUmlClass, p1, p2, "*..0");
				}
				//[SRC]-*----*-[TARGET]
				else if( (!rel.getSourceMultiplicity().equals("0") && !rel.getSourceMultiplicity().equals("1")) &&
					(!rel.getTargetMultiplicity().equals("0") && !rel.getTargetMultiplicity().equals("1")) ){
					
					genFieldsForIndirected(umlClass, relatedUmlClass, p1, p2, "*..*", propertyName1, propertyName2);
					genGetterAndSetterForIndirected(umlClass, relatedUmlClass, p1, p2, "*..*");
				}
			}
			else{
				//[SRC]------->[TARGET]
				if( (rel.getSourceMultiplicity().equals("0") || rel.getSourceMultiplicity().equals("1")) &&
					(rel.getTargetMultiplicity().equals("0") || rel.getTargetMultiplicity().equals("1")) ){
					
					genFieldsForDirected(umlClass, p1, p2, "0..0", propertyName1, propertyName2);
					genGetterAndSetterForDirected(umlClass, relatedUmlClass, p2, "0..0");
				}
				//[SRC]------*>[TARGET]
				else if( (rel.getSourceMultiplicity().equals("0") || rel.getSourceMultiplicity().equals("1")) &&
					(!rel.getTargetMultiplicity().equals("0") && !rel.getTargetMultiplicity().equals("1")) ){
					
					genFieldsForDirected(umlClass, p1, p2, "0..*", propertyName1, propertyName2);
					genGetterAndSetterForDirected(umlClass, relatedUmlClass, p2, "0..*");
				}
				//[SRC]-*----->[TARGET]
				else if( (!rel.getSourceMultiplicity().equals("0") && !rel.getSourceMultiplicity().equals("1")) &&
					(rel.getTargetMultiplicity().equals("0") || rel.getTargetMultiplicity().equals("1")) ){
					
					genFieldsForDirected(umlClass, p1, p2, "*..0", propertyName1, propertyName2);
					genGetterAndSetterForDirected(umlClass, relatedUmlClass, p2, "*..0");
				}
				//[SRC]-*----*>[TARGET]
				else if( (!rel.getSourceMultiplicity().equals("0") && !rel.getSourceMultiplicity().equals("1")) &&
					(!rel.getTargetMultiplicity().equals("0") && !rel.getTargetMultiplicity().equals("1")) ){
					
					genFieldsForDirected(umlClass, p1, p2, "*..*", propertyName1, propertyName2);
					genGetterAndSetterForDirected(umlClass, relatedUmlClass, p2, "*..*");
				}
			}
		}
	}
	
	public void genAssociationsForList(Notion notion, String mappingName) {
		Class umlClass = scFacade.getLinkedClass(notion, mappingName);
		Class umlListRowClass = scFacade.getLinkedClass(notion, mappingName+"Row");
		String notName = ((NotionDTO)notion).getName();
		
		Property p1 = scFacade.getFacade().createProperty();
		p1.addType(umlClass);
		Property p2 = scFacade.getFacade().createProperty();
		p2.addType(umlListRowClass);
		Association a = scFacade.getFacade().createAssociation();
		a.addMemberEnd(p1);
		a.addMemberEnd(p2);
		
		String propertyName1 = toLowerCamelCase(notName);
		String propertyName2 = umlListRowClass.getName().substring(0, 1).toLowerCase() + umlListRowClass.getName().substring(1);
		
		//[SRC]------*>[TARGET]
		genFieldsForDirected(umlClass, p1, p2, "0..*", propertyName1, propertyName2);
		genGetterAndSetterForDirected(umlClass, umlListRowClass, p2, "0..*");
		
		//void addConceptListItemDTO(ConceptListItemDTO)
		Operation addItem = scFacade.createOperation("add"+toCamelCase(p2.getName()), VisibilityKind.PUBLIC, new Object[][]{{p2.getName(), umlListRowClass}});
		genCode(addItem, "this." + p2.getName() + ".add(" + p2.getName() + ");\n");
		umlClass.addOperation(addItem);
	}
	
	public void genAssociationsForAbstractTree(Notion notion, String mappingName) {
		Class umlClass = scFacade.getLinkedClass(notion, mappingName);
		
		Property p1 = scFacade.getFacade().createProperty();
		p1.addType(umlClass);
		Property p2 = scFacade.getFacade().createProperty();
		p2.addType(umlClass);
		Association a = scFacade.getFacade().createAssociation();
		a.addMemberEnd(p1);
		a.addMemberEnd(p2);
		
		String propertyName1 = "children";
		String propertyName2 = "children";
		
		//[SRC]------*>[TARGET]
		genFieldsForDirected(umlClass, p1, p2, "0..*", propertyName1, propertyName2);
	}
	
	private void genFieldsForIndirected(Class umlClass, Class relatedUmlClass, Property p1, Property p2, 
			String multiplicityType, String propertyName1, String propertyName2) {
		
		p2.setVisibility(VisibilityKind.PRIVATE);
		p1.setVisibility(VisibilityKind.PRIVATE);
		
		if(multiplicityType.equals("0..0")){
			p2.setName(propertyName2);
			p1.setName(propertyName1);
			
		} else if(multiplicityType.equals("0..*")){
			p2.setName(propertyName2 + "List");
			p1.setName(propertyName1);
			
			LiteralInteger lowerVal2 = scFacade.getFacade().createLiteralInteger();
			lowerVal2.setValue(0);
			LiteralUnlimitedNatural upperVal2 = scFacade.getFacade().createLiteralUnlimitedNatural();
			upperVal2.setValue("*");
			p2.addLowerValue(lowerVal2);
			p2.addUpperValue(upperVal2);
			LiteralInteger lowerVal1 = scFacade.getFacade().createLiteralInteger();
			lowerVal1.setValue(0);
			LiteralUnlimitedNatural upperVal1 = scFacade.getFacade().createLiteralUnlimitedNatural();
			upperVal1.setValue("*");
			p1.addLowerValue(lowerVal1);
			p1.addUpperValue(upperVal1);
			
		} else if(multiplicityType.equals("*..0")){
			p2.setName(propertyName2);
			p1.setName(propertyName1 + "List");
			
			LiteralUnlimitedNatural upperVal2 = scFacade.getFacade().createLiteralUnlimitedNatural();
			upperVal2.setValue("");
			p2.addUpperValue(upperVal2);
			LiteralInteger lowerVal1 = scFacade.getFacade().createLiteralInteger();
			lowerVal1.setValue(0);
			LiteralUnlimitedNatural upperVal1 = scFacade.getFacade().createLiteralUnlimitedNatural();
			upperVal1.setValue("*");
			p1.addLowerValue(lowerVal1);
			p1.addUpperValue(upperVal1);
			
		} else if(multiplicityType.equals("*..*")){
			p2.setName(propertyName2 + "List");
			p1.setName(propertyName1 + "List");
			
			LiteralInteger lowerVal2 = scFacade.getFacade().createLiteralInteger();
			lowerVal2.setValue(0);
			LiteralUnlimitedNatural upperVal2 = scFacade.getFacade().createLiteralUnlimitedNatural();
			upperVal2.setValue("*");
			p2.addLowerValue(lowerVal2);
			p2.addUpperValue(upperVal2);
			LiteralInteger lowerVal1 = scFacade.getFacade().createLiteralInteger();
			lowerVal1.setValue(0);
			LiteralUnlimitedNatural upperVal1 = scFacade.getFacade().createLiteralUnlimitedNatural();
			upperVal1.setValue("*");
			p1.addLowerValue(lowerVal1);
			p1.addUpperValue(upperVal1);
		}
		
		umlClass.addOwnedAttribute(p2);
		relatedUmlClass.addOwnedAttribute(p1);
	}
	
	private void genGetterAndSetterForIndirected(Class umlClass, Class relatedUmlClass, 
			Property p1, Property p2, String multiplicityType) {
		String propNameCamel = toCamelCase(p1.getName());
		String propNameCamel2 = toCamelCase(p2.getName());
		Operation getter1 = null;
		Operation setter1 = null;
		Operation getter2 = null;
		Operation setter2 = null;
		
		if(multiplicityType.equals("0..0")){
			getter1 = scFacade.createOperation("get"+propNameCamel2, VisibilityKind.PUBLIC, new Object[][]{{null, relatedUmlClass}});
			setter1 = scFacade.createOperation("set"+propNameCamel2, VisibilityKind.PUBLIC, new Object[][]{{p2.getName(), relatedUmlClass}});
			getter2 = scFacade.createOperation("get"+propNameCamel, VisibilityKind.PUBLIC, new Object[][]{{null, umlClass}});
			setter2 = scFacade.createOperation("set"+propNameCamel, VisibilityKind.PUBLIC, new Object[][]{{p1.getName(), umlClass}});
			
		} else if(multiplicityType.equals("0..*")){
			getter1 = scFacade.createOperation("get"+propNameCamel2, VisibilityKind.PUBLIC, new Object[][]{{null, "List<"+relatedUmlClass.getName()+">"}});
			setter1 = scFacade.createOperation("set"+propNameCamel2, VisibilityKind.PUBLIC, new Object[][]{{p2.getName(), "List<"+relatedUmlClass.getName()+">"}});
			getter2 = scFacade.createOperation("get"+propNameCamel, VisibilityKind.PUBLIC, new Object[][]{{null, umlClass}});
			setter2 = scFacade.createOperation("set"+propNameCamel, VisibilityKind.PUBLIC, new Object[][]{{p1.getName(), umlClass}});
			
		} else if(multiplicityType.equals("*..0")){
			getter1 = scFacade.createOperation("get"+propNameCamel2, VisibilityKind.PUBLIC, new Object[][]{{null, relatedUmlClass}});
			setter1 = scFacade.createOperation("set"+propNameCamel2, VisibilityKind.PUBLIC, new Object[][]{{p2.getName(), relatedUmlClass}});
			getter2 = scFacade.createOperation("get"+propNameCamel, VisibilityKind.PUBLIC, new Object[][]{{null, "List<"+umlClass.getName()+">"}});
			setter2 = scFacade.createOperation("set"+propNameCamel, VisibilityKind.PUBLIC, new Object[][]{{p1.getName(), "List<"+umlClass.getName()+">"}});
			
		} else if(multiplicityType.equals("*..*")){
			getter1 = scFacade.createOperation("get"+propNameCamel2, VisibilityKind.PUBLIC, new Object[][]{{null, "List<"+relatedUmlClass.getName()+">"}});
			setter1 = scFacade.createOperation("set"+propNameCamel2, VisibilityKind.PUBLIC, new Object[][]{{p2.getName(), "List<"+relatedUmlClass.getName()+">"}});
			getter2 = scFacade.createOperation("get"+propNameCamel, VisibilityKind.PUBLIC, new Object[][]{{null, "List<"+umlClass.getName()+">"}});
			setter2 = scFacade.createOperation("set"+propNameCamel, VisibilityKind.PUBLIC, new Object[][]{{p1.getName(), "List<"+umlClass.getName()+">"}});
		}
		
		umlClass.addOperation(getter1);
		umlClass.addOperation(setter1);
		relatedUmlClass.addOperation(getter2);
		relatedUmlClass.addOperation(setter2);
		
		genCode(getter1, "return " + p2.getName() + ";\n");
		genCode(setter1, "this." + p2.getName() + " = " + p2.getName() + ";\n");
		genCode(getter2, "return " + p1.getName() + ";\n");
		genCode(setter2, "this." + p1.getName() + " = " + p1.getName() + ";\n");
	}
	
	private void genFieldsForDirected(Class umlClass, Property p1, Property p2, 
			String multiplicityType, String propertyName1, String propertyName2){
		
		p2.setVisibility(VisibilityKind.PRIVATE);
		
		if(multiplicityType.equals("0..0")){
			p2.setName(propertyName2);
		} else if(multiplicityType.equals("0..*")){
			//special case for ListViewItemDTO
			if(propertyName2.endsWith("ItemDTO")){
				String items = propertyName2.replaceAll("ItemDTO", "Items");
				p2.setName(items);
			}
			//special case for AbstractTreeNodeDTO
			else if(propertyName2.equals("children")){
				p2.setName(propertyName2);
			}
			else{
				p2.setName(propertyName2 + "List");
			}
			LiteralInteger lowerVal2 = scFacade.getFacade().createLiteralInteger();
			lowerVal2.setValue(0);
			LiteralUnlimitedNatural upperVal2 = scFacade.getFacade().createLiteralUnlimitedNatural();
			upperVal2.setValue("*");
			p2.addLowerValue(lowerVal2);
			p2.addUpperValue(upperVal2);
			
			LiteralUnlimitedNatural upperVal1 = scFacade.getFacade().createLiteralUnlimitedNatural();
			upperVal1.setValue("");
			p1.addUpperValue(upperVal1);
			
		} else if(multiplicityType.equals("*..0")){
			p2.setName(propertyName2);
			LiteralUnlimitedNatural upperVal2 = scFacade.getFacade().createLiteralUnlimitedNatural();
			upperVal2.setValue("");
			p2.addUpperValue(upperVal2);

			LiteralInteger lowerVal1 = scFacade.getFacade().createLiteralInteger();
			lowerVal1.setValue(0);
			LiteralUnlimitedNatural upperVal1 = scFacade.getFacade().createLiteralUnlimitedNatural();
			upperVal1.setValue("*");
			p1.addLowerValue(lowerVal1);
			p1.addUpperValue(upperVal1);
			
		} else if(multiplicityType.equals("*..*")){
			p2.setName(propertyName2 + "List");
			LiteralInteger lowerVal2 = scFacade.getFacade().createLiteralInteger();
			lowerVal2.setValue(0);
			LiteralUnlimitedNatural upperVal2 = scFacade.getFacade().createLiteralUnlimitedNatural();
			upperVal2.setValue("*");
			p2.addLowerValue(lowerVal2);
			p2.addUpperValue(upperVal2);
			
			LiteralInteger lowerVal1 = scFacade.getFacade().createLiteralInteger();
			lowerVal1.setValue(0);
			LiteralUnlimitedNatural upperVal1 = scFacade.getFacade().createLiteralUnlimitedNatural();
			upperVal1.setValue("*");
			p1.addLowerValue(lowerVal1);
			p1.addUpperValue(upperVal1);
		}
		
		umlClass.addOwnedAttribute(p2);
	}
	
	private void genGetterAndSetterForDirected(Class umlClass, Class relatedUmlClass, 
			Property p2, String multiplicityType) {
		String propNameCamel2 = toCamelCase(p2.getName());
		Operation getter1 = null;
		Operation setter1 = null;
		
		if(multiplicityType.equals("0..0") || multiplicityType.equals("*..0")){
			getter1 = scFacade.createOperation("get"+propNameCamel2, VisibilityKind.PUBLIC, new Object[][]{{null, relatedUmlClass}});
			setter1 = scFacade.createOperation("set"+propNameCamel2, VisibilityKind.PUBLIC, new Object[][]{{p2.getName(), relatedUmlClass}});
			
		} else if(multiplicityType.equals("0..*") || multiplicityType.equals("*..*")){
			getter1 = scFacade.createOperation("get"+propNameCamel2, VisibilityKind.PUBLIC, new Object[][]{{null, "List<"+relatedUmlClass.getName()+">"}});
			setter1 = scFacade.createOperation("set"+propNameCamel2, VisibilityKind.PUBLIC, new Object[][]{{p2.getName(), "List<"+relatedUmlClass.getName()+">"}});
		}
		
		umlClass.addOperation(getter1);
		umlClass.addOperation(setter1);
		
		genCode(getter1, "return " + p2.getName() + ";\n");
		genCode(setter1, "this." + p2.getName() + " = " + p2.getName() + ";\n");
	}
}
