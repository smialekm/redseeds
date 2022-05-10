package eu.redseeds.java.generator.dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import eu.redseeds.java.generator.utils.GeneratorUtils;
import eu.redseeds.java.generator.utils.TraceTypes;
import eu.redseeds.java.repository.SCFacade;
import eu.redseeds.scl.model.rsl.rsldomainelements.domainelements.DomainSpecificationDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionTypesEnum;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionsPackageDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.PrimitiveDataTypeDTO;
import eu.redseeds.scl.model.sdsl.ClassDTO;
import eu.redseeds.scl.model.sdsl.InterfaceDTO;
import eu.redseeds.scl.model.sdsl.OperationDTO;
import eu.redseeds.scl.model.sdsl.PackageDTO;
import eu.redseeds.scl.rsl.rsldomainelements.notions.Notion;
import eu.redseeds.scl.uml.classes.interfaces.Interface;
import eu.redseeds.scl.uml.classes.kernel.Generalization;
import eu.redseeds.scl.uml.classes.kernel.NamedElement;
import eu.redseeds.scl.uml.classes.kernel.PrimitiveType;
import eu.redseeds.scl.uml.classes.kernel.Property;
import eu.redseeds.scl.uml.classes.kernel.Class;
import eu.redseeds.scl.uml.classes.kernel.Operation;
import eu.redseeds.scl.uml.classes.kernel.VisibilityKind;
import eu.redseeds.scl.uml.classes.kernel.Package;

public class DTOGenerator {
	
	private SCFacade scFacade;
	private GeneratorUtils genUtils;

	public DTOGenerator() {
		scFacade = SCFacade.instance();
		genUtils = new GeneratorUtils();
	}
	
	public void genDTOPackages() {
		PackageDTO dtoPack = scFacade.getPackage("DTO");
		DomainSpecificationDTO domainSpec = scFacade.getMainCase().getRequirementsSpecificationDTO().getDomainSpecificationDTO();
		for(NotionsPackageDTO notPack : domainSpec.getNotionsPackageDTOList()){
			if(scFacade.checkForNotionsRecursively(notPack)){
				String packNameCamelCase = genUtils.toCamelCase(notPack.getName());
				PackageDTO dtoPackPack = (PackageDTO) scFacade.getFacade().createUml$classes$kernel$Package();
				dtoPackPack.setName(packNameCamelCase);
				dtoPack.addPackage(dtoPackPack);
				genDTOChildrenPackages(notPack, dtoPackPack);
			}
		}
	}
	
	public void genDTOChildrenPackages(NotionsPackageDTO notPackParent, PackageDTO dtoPackParent) {
		for(NotionsPackageDTO notPackChild : notPackParent.getNotionsPackageDTOList()){
			if(scFacade.checkForNotionsRecursively(notPackChild)){
				String packNameCamelCase = genUtils.toCamelCase(notPackChild.getName());
				PackageDTO dtoPackChild = (PackageDTO) scFacade.getFacade().createUml$classes$kernel$Package();
				dtoPackChild.setName(packNameCamelCase);
				dtoPackParent.addPackage(dtoPackChild);
				genDTOChildrenPackages(notPackChild, dtoPackChild);
			}
		}
	}
	
	public void genDTOLayer() {
		List<Notion> notList = new ArrayList<Notion>();
		for(Notion notion : scFacade.getFacade().getNotionVertices()){
			String type = ((NotionDTO)notion).getType();
			
			if(Arrays.asList(new String[]{"", NotionTypesEnum.Simple_View.tag(), NotionTypesEnum.List_View.tag(), NotionTypesEnum.Tree_View.tag()}).contains(type)){
				notList.add(notion);
			}
		}
		for(Notion notion : notList){
			if(((NotionDTO)notion).getType().equals(NotionTypesEnum.List_View.tag())){
				genDTOForList(notion);
			}
			else if(((NotionDTO)notion).getType().equals(NotionTypesEnum.Tree_View.tag())){
				genDTOForTree(notion);
			}
			else{
				genDTO(notion);
			}
		}
		
		for(Notion notion : notList){
			genPrimitiveFieldsAndMutators(notion);
			genAggregattedFieldsAndMutators(notion);
			genConstructors(notion);
		}
	}
	
	private void genDTO(Notion notion) {
		String notionName = genUtils.toCamelCase(((NotionDTO)notion).getName());
		ClassDTO dtoClass = (ClassDTO) scFacade.getFacade().createClass();
		dtoClass.setName(notionName+"DTO");
		
		scFacade.createMappingBetween(notion, (Class)dtoClass, TraceTypes.DTO_MAPPING_NAME);

		PackageDTO javaPack = scFacade.getPackage("java");
		PackageDTO utilPack = (PackageDTO) scFacade.getElementFromPackage(javaPack, "util", Package.class);
		InterfaceDTO list = (InterfaceDTO) scFacade.getElementFromPackage(utilPack, "List", Interface.class);
		scFacade.createDependency((Class)dtoClass, new NamedElement[]{(Interface)list});
		
		PrimitiveType intType = scFacade.getPrimitiveType("long");
		Property idAttr = scFacade.getFacade().createProperty();
		idAttr.setName(/*genUtils.toLowerCamelCase(notionName)+*/"id");
		idAttr.setVisibility(VisibilityKind.PRIVATE);
		idAttr.addType(intType);
		idAttr.setIsUnique(true);
		
		OperationDTO getter = (OperationDTO) scFacade.createOperation("getId", VisibilityKind.PUBLIC, new Object[][]{{null, "long"}});
		OperationDTO setter = (OperationDTO) scFacade.createOperation("setId", VisibilityKind.PUBLIC, new Object[][]{{"id", "long"}});
		genUtils.genCode((Operation)getter, "return " + idAttr.getName() + ";\n");
		genUtils.genCode((Operation)setter, "this." + idAttr.getName() + " = " + "id" + ";\n");

		((Class)dtoClass).addOwnedAttribute(idAttr);
		dtoClass.addOperation(getter);
		dtoClass.addOperation(setter);
		
		PackageDTO dtoPack = scFacade.getPackage("DTO");
		dtoPack.addClass(dtoClass);
		
		scFacade.addPrimitiveType("List<"+dtoClass.getName()+">");
	}
	
	private void genDTOForList(Notion notion) {
		String notionName = genUtils.toCamelCase(((NotionDTO)notion).getName());
		ClassDTO dtoListClass = (ClassDTO) scFacade.getFacade().createClass();
		ClassDTO dtoClass = (ClassDTO) scFacade.getFacade().createClass();
		dtoListClass.setName(notionName+"DTO");
		dtoClass.setName(notionName+"ItemDTO");
		
		scFacade.createMappingBetween(notion, (Class)dtoListClass, TraceTypes.DTO_LIST_MAPPING_NAME);
		scFacade.createMappingBetween(notion, (Class)dtoClass, TraceTypes.DTO_LIST_ROW_MAPPING_NAME);
		
		PackageDTO javaPack = scFacade.getPackage("java");
		PackageDTO utilPack = (PackageDTO) scFacade.getElementFromPackage(javaPack, "util", Package.class);
		InterfaceDTO list = (InterfaceDTO) scFacade.getElementFromPackage(utilPack, "List", Interface.class);
		scFacade.createDependency((Class)dtoListClass, new NamedElement[]{(Interface)list});
		scFacade.createDependency((Class)dtoClass, new NamedElement[]{(Interface)list});
		
		PrimitiveType intType = scFacade.getPrimitiveType("long");
		Property idAttr = scFacade.getFacade().createProperty();
		idAttr.setName(/*genUtils.toLowerCamelCase(notionName)+*/"id");
		idAttr.setVisibility(VisibilityKind.PRIVATE);
		idAttr.addType(intType);
		idAttr.setIsUnique(true);
		
		OperationDTO getter = (OperationDTO) scFacade.createOperation("getId", VisibilityKind.PUBLIC, new Object[][]{{null, "long"}});
		OperationDTO setter = (OperationDTO) scFacade.createOperation("setId", VisibilityKind.PUBLIC, new Object[][]{{"id", "long"}});
		genUtils.genCode((Operation)getter, "return " + idAttr.getName() + ";\n");
		genUtils.genCode((Operation)setter, "this." + idAttr.getName() + " = " + "id" + ";\n");

		((Class)dtoClass).addOwnedAttribute(idAttr);
		dtoClass.addOperation(getter);
		dtoClass.addOperation(setter);
		
		PackageDTO dtoPack = scFacade.getPackage("DTO");
		dtoPack.addClass(dtoListClass);
		dtoPack.addClass(dtoClass);
		
		scFacade.addPrimitiveType("List<"+dtoClass.getName()+">");
	}
	
	private void genDTOForTree(Notion notion) {
		String notionName = genUtils.toCamelCase(((NotionDTO)notion).getName());
		ClassDTO abstractTreeDTOClass = (ClassDTO) scFacade.getLinkedClass(notion, TraceTypes.DTO_ABSTRACT_TREE_MAPPING_NAME);
		
		if(abstractTreeDTOClass == null){
			abstractTreeDTOClass = (ClassDTO) scFacade.getFacade().createClass();
			abstractTreeDTOClass.setName("AbstractTreeNodeDTO");
			((Class)abstractTreeDTOClass).setIsAbstract(true);
			
			scFacade.createMappingBetween(notion, (Class)abstractTreeDTOClass, TraceTypes.DTO_ABSTRACT_TREE_MAPPING_NAME);
			
			PackageDTO javaPack = scFacade.getPackage("java");
			PackageDTO utilPack = (PackageDTO) scFacade.getElementFromPackage(javaPack, "util", Package.class);
			InterfaceDTO list = (InterfaceDTO) scFacade.getElementFromPackage(utilPack, "List", Interface.class);
			scFacade.createDependency((Class)abstractTreeDTOClass, new NamedElement[]{(Interface)list});
			
			//private AbstractTreeNodeDTO parent = null;
			Property treeNodeParentField = scFacade.getFacade().createProperty();
			treeNodeParentField.setName("parent");
			treeNodeParentField.setVisibility(VisibilityKind.PRIVATE);
			treeNodeParentField.addType((Class)abstractTreeDTOClass);
			
			//private List<AbstractTreeNodeDTO> children = new ArrayList<AbstractTreeNodeDTO>();
			/*Property treeNodeChildrenField = scFacade.getFacade().createProperty();
			treeNodeChildrenField.setName("children");
			treeNodeChildrenField.setVisibility(VisibilityKind.PRIVATE);
			PrimitiveType type = scFacade.addPrimitiveType("List<"+abstractTreeDTOClass.getName()+">");
			treeNodeChildrenField.addType(type);*/
			
			//public boolean isRoot()
			OperationDTO isRoot = (OperationDTO) scFacade.createOperation("isRoot", VisibilityKind.PUBLIC, new Object[][]{{null, "boolean"}});
			//public AbstractTreeNodeDTO getParent()
			OperationDTO getParent = (OperationDTO) scFacade.createOperation("getParent", VisibilityKind.PUBLIC, new Object[][]{{null, abstractTreeDTOClass}});
			//public void setParent(AbstractTreeNodeDTO parent)
			OperationDTO setParent = (OperationDTO) scFacade.createOperation("setParent", VisibilityKind.PUBLIC, new Object[][]{{"parent", abstractTreeDTOClass}});
			//public boolean hasChildren()
			OperationDTO hasChildren = (OperationDTO) scFacade.createOperation("hasChildren", VisibilityKind.PUBLIC, new Object[][]{{null, "boolean"}});
			//public List<AbstractTreeNodeDTO> getChildren()
			OperationDTO getChildren = (OperationDTO) scFacade.createOperation("getChildren", VisibilityKind.PUBLIC, new Object[][]{{null, "List<"+abstractTreeDTOClass.getName()+">"}});
			//public void addChild(AbstractTreeNodeDTO child)
			OperationDTO addChild = (OperationDTO) scFacade.createOperation("addChild", VisibilityKind.PUBLIC, new Object[][]{{"child", abstractTreeDTOClass}});
			//public void setChildren(List<AbstractTreeNodeDTO> children)
			OperationDTO setChildren = (OperationDTO) scFacade.createOperation("setChildren", VisibilityKind.PUBLIC, new Object[][]{{"children", "List<"+abstractTreeDTOClass.getName()+">"}});
			//public int getChildCount()
			OperationDTO getChildCount = (OperationDTO) scFacade.createOperation("getChildCount", VisibilityKind.PUBLIC, new Object[][]{{null, "int"}});
			
			genUtils.genCode((Operation)isRoot, "return parent != null ? false : true;\n");
			genUtils.genCode((Operation)getParent, "return parent;\n");
			genUtils.genCode((Operation)setParent, "this.parent = parent;\n");
			genUtils.genCode((Operation)hasChildren, "return (children != null && children.size() != 0) ? true : false;\n");
			genUtils.genCode((Operation)getChildren, "return children;\n");
			genUtils.genCode((Operation)addChild, "if(children != null) children.add(child);\n");
			genUtils.genCode((Operation)setChildren, "this.children = children;\n");
			genUtils.genCode((Operation)getChildCount, "return children != null ? children.size() : 0;\n");
			
			((Class)abstractTreeDTOClass).addOwnedAttribute(treeNodeParentField);
			//((Class)abstractTreeDTOClass).addOwnedAttribute(treeNodeChildrenField);
			abstractTreeDTOClass.addOperation(isRoot);
			abstractTreeDTOClass.addOperation(getParent);
			abstractTreeDTOClass.addOperation(setParent);
			abstractTreeDTOClass.addOperation(hasChildren);
			abstractTreeDTOClass.addOperation(getChildren);
			abstractTreeDTOClass.addOperation(addChild);
			abstractTreeDTOClass.addOperation(setChildren);
			abstractTreeDTOClass.addOperation(getChildCount);
			
			PackageDTO dtoPack = scFacade.getPackage("DTO");
			dtoPack.addClass(abstractTreeDTOClass);
		}
		
		ClassDTO concreteTreeDTOClass = (ClassDTO) scFacade.getFacade().createClass();
		concreteTreeDTOClass.setName(notionName+"NodeDTO");
		
		scFacade.createMappingBetween(notion, (Class)concreteTreeDTOClass, TraceTypes.DTO_CONCRETE_TREE_MAPPING_NAME);
		
		Generalization generalization = scFacade.getFacade().createGeneralization();
		generalization.addGeneral((Class)abstractTreeDTOClass);
		generalization.addSpecific((Class)concreteTreeDTOClass);
		
		//private long id
		PrimitiveType longType = scFacade.getPrimitiveType("long");
		Property idAttr = scFacade.getFacade().createProperty();
		idAttr.setName(/*genUtils.toLowerCamelCase(notionName)+*/"id");
		idAttr.setVisibility(VisibilityKind.PRIVATE);
		idAttr.addType(longType);
		idAttr.setIsUnique(true);
		
		//public getID() & public setID()
		OperationDTO getter = (OperationDTO) scFacade.createOperation("getId", VisibilityKind.PUBLIC, new Object[][]{{null, "long"}});
		OperationDTO setter = (OperationDTO) scFacade.createOperation("setId", VisibilityKind.PUBLIC, new Object[][]{{"id", "long"}});
		genUtils.genCode((Operation)getter, "return " + idAttr.getName() + ";\n");
		genUtils.genCode((Operation)setter, "this." + idAttr.getName() + " = " + "id" + ";\n");
		
		((Class)concreteTreeDTOClass).addOwnedAttribute(idAttr);
		concreteTreeDTOClass.addOperation(getter);
		concreteTreeDTOClass.addOperation(setter);
		
		PackageDTO dtoPack = scFacade.getPackage("DTO");
		dtoPack.addClass(concreteTreeDTOClass);
	}
	
	private void genPrimitiveFieldsAndMutators(Notion notion) {
		Class dtoClass = scFacade.getLinkedClass(notion, TraceTypes.DTO_MAPPING_NAME);
		for(NotionDTO attrNotion : ((NotionDTO)notion).getNotionDTOAttributeList()){
			PrimitiveDataTypeDTO primType = attrNotion.getDataType();
			if(primType != null){
				if(((NotionDTO)notion).getType().equals(NotionTypesEnum.List_View.tag())){
					Class dtoListRowClass = scFacade.getLinkedClass(notion, TraceTypes.DTO_LIST_ROW_MAPPING_NAME);
					genUtils.genPrimitiveTypeAttribute(attrNotion, dtoListRowClass);
				}
				else if(((NotionDTO)notion).getType().equals(NotionTypesEnum.Tree_View.tag())){
					Class treeDTOClass = scFacade.getLinkedClass(notion, TraceTypes.DTO_CONCRETE_TREE_MAPPING_NAME);
					genUtils.genPrimitiveTypeAttribute(attrNotion, treeDTOClass);
				}
				else{
					genUtils.genPrimitiveTypeAttribute(attrNotion, dtoClass);
				}
			}
		}
	}
	
	private void genAggregattedFieldsAndMutators(Notion notion) {
		if(((NotionDTO)notion).getType().equals(NotionTypesEnum.List_View.tag())){
			genUtils.genAssociationsForList(notion, TraceTypes.DTO_LIST_MAPPING_NAME);
		}
		else if(((NotionDTO)notion).getType().equals(NotionTypesEnum.Tree_View.tag())){
			genUtils.genAssociationsForAbstractTree(notion, TraceTypes.DTO_ABSTRACT_TREE_MAPPING_NAME);
			genUtils.genAssociations(notion, TraceTypes.DTO_CONCRETE_TREE_MAPPING_NAME);
		}
		else{
			genUtils.genAssociations(notion, TraceTypes.DTO_MAPPING_NAME);
		}
	}
	
	private void genConstructors(Notion notion) {
		String trace_name;
		if(((NotionDTO)notion).getType().equals(NotionTypesEnum.List_View.tag())){
			trace_name = TraceTypes.DTO_LIST_ROW_MAPPING_NAME;
		}
		else if(((NotionDTO)notion).getType().equals(NotionTypesEnum.Tree_View.tag())){
			trace_name = TraceTypes.DTO_CONCRETE_TREE_MAPPING_NAME;
		}
		else{
			trace_name = TraceTypes.DTO_MAPPING_NAME;
		}
		ClassDTO dtoClass = (ClassDTO) scFacade.getLinkedClass(notion, trace_name);
		
		//public DTO()
		OperationDTO emptyConstructor = (OperationDTO) scFacade.createOperation(dtoClass.getName(), VisibilityKind.PUBLIC,
				new Object[][]{ {null, ""} });
		dtoClass.addOperation(emptyConstructor);

		//public DTO(params)
		Object[][] parameters = getOperationParameters((Class)dtoClass, trace_name, false);
		if(parameters[0].length == 0){
			return;
		}
		OperationDTO nonEmptyConstructor = (OperationDTO) scFacade.createOperation(dtoClass.getName(), VisibilityKind.PUBLIC,
				parameters);
		String code = getBodyForOperationWithParameters(parameters, false);
		genUtils.genCode((Operation)nonEmptyConstructor, code);
		dtoClass.addOperation(nonEmptyConstructor);

		//public DTO(paramsWithIDField)
		parameters = getOperationParameters((Class)dtoClass, trace_name, true);
		if(parameters.length == 0){
			return;
		}
		OperationDTO nonEmptyConstructorWithID = (OperationDTO) scFacade.createOperation(dtoClass.getName(), VisibilityKind.PUBLIC,
				parameters);
		code = getBodyForOperationWithParameters(parameters, true);
		genUtils.genCode((Operation)nonEmptyConstructorWithID, code);
		dtoClass.addOperation(nonEmptyConstructorWithID);
	}
	
	private Object[][] getOperationParameters(Class umlClass, String trace_name, boolean withIDfield) {
		boolean dtoBasedOnConcept = false;
		Notion not = scFacade.getLinkedNotion((ClassDTO)umlClass, trace_name);
		if(((NotionDTO)not).getType().equals("")){
			dtoBasedOnConcept = true;
		}
		
		List<? extends Property> fields = umlClass.getOwnedAttributeList();
		if(fields == null || fields.isEmpty()){
			return new Object[][]{{}};
		}
		Object[][] parameters = new Object[fields.size()+1][2];
		int idx = 0;
		for(Property field : fields){
			String fieldName = field.getName();
			String fieldType = field.getTypeList() != null && !field.getTypeList().isEmpty() ? field.getTypeList().get(0).getName() : null;
			if(fieldName.endsWith("id") && fieldType.equals("long")){
				if(!withIDfield){
					continue;
				}
			}
			//true for fields belonging to DTO created from Concept (not from Data View)
			if(dtoBasedOnConcept && fieldName.endsWith("List")){
				//fieldType = "List<"+fieldType+">";
				continue;
			}
			Object[] pair = new Object[]{fieldName, fieldType};
			parameters[idx] = pair;
			idx++;
		}
		parameters[idx] = new Object[]{null, ""};
		
		return parameters;
	}
	
	private String getBodyForOperationWithParameters(Object[][] parameters, boolean withIDfield) {
		StringBuilder code = new StringBuilder();
		for(int i=0; i < parameters.length; i++){
			if(parameters[i][0] == null){
				continue;
			}
			String fieldName = (String) parameters[i][0];
			if(fieldName.endsWith("id")){
				if(!withIDfield){
					continue;
				}
			}
			code.append("this."+fieldName+" = "+fieldName+";\n");
		}
		return code.toString();
	}
}
