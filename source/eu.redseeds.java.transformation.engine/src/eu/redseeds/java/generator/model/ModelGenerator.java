package eu.redseeds.java.generator.model;

import java.util.ArrayList;
import java.util.List;

import eu.redseeds.java.generator.utils.GeneratorUtils;
import eu.redseeds.java.generator.utils.TraceTypes;
import eu.redseeds.java.repository.SCFacade;
import eu.redseeds.scl.model.rsl.rsldomainelements.domainelements.DomainSpecificationDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionSpecialisationDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionsPackageDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.PrimitiveDataTypeDTO;
import eu.redseeds.scl.model.sdsl.ClassDTO;
import eu.redseeds.scl.model.sdsl.InterfaceDTO;
import eu.redseeds.scl.model.sdsl.OperationDTO;
import eu.redseeds.scl.model.sdsl.PackageDTO;
import eu.redseeds.scl.rsl.rsldomainelements.notions.Notion;
import eu.redseeds.scl.uml.classes.interfaces.Interface;
import eu.redseeds.scl.uml.classes.kernel.Class;
import eu.redseeds.scl.uml.classes.kernel.Generalization;
import eu.redseeds.scl.uml.classes.kernel.NamedElement;
import eu.redseeds.scl.uml.classes.kernel.Operation;
import eu.redseeds.scl.uml.classes.kernel.Package;
import eu.redseeds.scl.uml.classes.kernel.PrimitiveType;
import eu.redseeds.scl.uml.classes.kernel.Property;
import eu.redseeds.scl.uml.classes.kernel.VisibilityKind;

public class ModelGenerator {
	
	private SCFacade scFacade;
	private GeneratorUtils genUtils;

	public ModelGenerator() {
		scFacade = SCFacade.instance();
		genUtils = new GeneratorUtils();
	}

	public void genModelPackages() {
		PackageDTO modelPack = scFacade.getPackage("Model");
		DomainSpecificationDTO domainSpec = scFacade.getMainCase().getRequirementsSpecificationDTO().getDomainSpecificationDTO();
		for(NotionsPackageDTO notPack : domainSpec.getNotionsPackageDTOList()){
			if(scFacade.checkForNotionsRecursively(notPack)){
				String packNameCamelCase = genUtils.toCamelCase(notPack.getName());
				PackageDTO modelPackPack = (PackageDTO) scFacade.getFacade().createUml$classes$kernel$Package();
				modelPackPack.setName(packNameCamelCase);
				modelPack.addPackage(modelPackPack);
				genModelChildrenPackages(notPack, modelPackPack);
			}
		}
	}
	
	public void genModelChildrenPackages(NotionsPackageDTO notPackParent, PackageDTO modelPackParent) {
		for(NotionsPackageDTO notPackChild : notPackParent.getNotionsPackageDTOList()){
			if(scFacade.checkForNotionsRecursively(notPackChild)){
				String packNameCamelCase = genUtils.toCamelCase(notPackChild.getName());
				PackageDTO modelPackChild = (PackageDTO) scFacade.getFacade().createUml$classes$kernel$Package();
				modelPackChild.setName(packNameCamelCase);
				modelPackParent.addPackage(modelPackChild);
				genModelChildrenPackages(notPackChild, modelPackChild);
			}
		}
	}
	
	public void genModelLayer(){
		List<Notion> notList = new ArrayList<Notion>();
		for(Notion notion : scFacade.getFacade().getNotionVertices()){
			String type = ((NotionDTO)notion).getType();
			if(type.equals("")){
				notList.add(notion);
			}
		}
		for(Notion notion : notList){
			genEntity(notion);
			genEntityImpl(notion);
		}
		
		for(Notion notion : notList){
			genPrimitiveFieldsAndMutators(notion);
			genAggregattedFieldsAndMutators(notion);
			genFactoryInnerClass(notion);
		}
	}
	
	private void genEntity(Notion notion) {
		String notionName = genUtils.toCamelCase(((NotionDTO)notion).getName());
		ClassDTO entityClass = (ClassDTO) scFacade.getFacade().createClass();
		entityClass.setName(notionName);
		((Class)entityClass).setIsAbstract(true);
		
		scFacade.createMappingBetween(notion, (Class)entityClass, TraceTypes.ENTITY_MAPPING_NAME);
		
		for(NotionSpecialisationDTO notionGeneralization : ((NotionDTO)notion).getNotionSpecialisationDTOList()){
			if(notionGeneralization.getGeneralNotion() != null){
				NotionDTO generalNotion = notionGeneralization.getGeneralNotion();
				if(generalNotion != null && !notion.equals(generalNotion)){
					ClassDTO generalEntityClass = (ClassDTO) scFacade.getLinkedClass((Notion)generalNotion, TraceTypes.ENTITY_MAPPING_NAME);
					if(generalEntityClass != null){
						Generalization generalization = scFacade.getFacade().createGeneralization();
						generalization.addGeneral((Class)generalEntityClass);
						generalization.addSpecific((Class)entityClass);
					}
				}
			}
		}

		PackageDTO javaPack = scFacade.getPackage("java");
		PackageDTO utilPack = (PackageDTO) scFacade.getElementFromPackage(javaPack, "util", Package.class);
		InterfaceDTO list = (InterfaceDTO) scFacade.getElementFromPackage(utilPack, "List", Interface.class);
		scFacade.createDependency((Class)entityClass, new NamedElement[]{(Interface)list});
		
		PrimitiveType longType = scFacade.getPrimitiveType("long");
		
		Property idAttr = scFacade.getFacade().createProperty();
		idAttr.setName(/*genUtils.toLowerCamelCase(notionName)+*/"id");
		idAttr.setVisibility(VisibilityKind.PRIVATE);
		idAttr.addType(longType);
		idAttr.setIsUnique(true);
		
		OperationDTO getter = (OperationDTO) scFacade.createOperation("getId", VisibilityKind.PUBLIC, new Object[][]{{null, "long"}});
		OperationDTO setter = (OperationDTO) scFacade.createOperation("setId", VisibilityKind.PUBLIC, new Object[][]{{"id", "long"}});
		
		genUtils.genCode((Operation)getter, "return " + idAttr.getName() + ";\n");
		genUtils.genCode((Operation)setter, "this." + idAttr.getName() + " = " + "id" + ";\n");
		
		((Class)entityClass).addOwnedAttribute(idAttr);
		entityClass.addOperation(getter);
		entityClass.addOperation(setter);
		
		PackageDTO modelPack = scFacade.getPackage("Model");
		modelPack.addClass(entityClass);
		
		scFacade.addPrimitiveType("List<"+entityClass.getName()+">");
	}
	
	private void genEntityImpl(Notion notion) {
		String notionName = genUtils.toCamelCase(((NotionDTO)notion).getName());
		ClassDTO entityImplClass = (ClassDTO) scFacade.getFacade().createClass();
		entityImplClass.setName(notionName + "Impl");
		
		scFacade.createMappingBetween(notion, (Class)entityImplClass, TraceTypes.ENTITY_IMPL_MAPPING_NAME);
		
		Class entityClass = scFacade.getLinkedClass(notion, TraceTypes.ENTITY_MAPPING_NAME);
		Generalization generalization = scFacade.getFacade().createGeneralization();
		generalization.addGeneral(entityClass);
		generalization.addSpecific((Class)entityImplClass);
		
		PackageDTO modelPack = scFacade.getPackage("Model");
		//PackageDTO implPack = (PackageDTO) scFacade.getElementFromPackage(modelPack, "impl", Package.class);
		modelPack.addClass(entityImplClass);
	}
	
	private void genPrimitiveFieldsAndMutators(Notion notion) {
		Class entityClass = scFacade.getLinkedClass(notion, TraceTypes.ENTITY_MAPPING_NAME);
		for(NotionDTO attrNotion : ((NotionDTO)notion).getNotionDTOAttributeList()){
			PrimitiveDataTypeDTO primType = attrNotion.getDataType();
			if(primType != null){
				genUtils.genPrimitiveTypeAttribute(attrNotion, entityClass);
			}
		}
	}
	
	private void genAggregattedFieldsAndMutators(Notion notion) {
		genUtils.genAssociations(notion, TraceTypes.ENTITY_MAPPING_NAME);
	}
	
	private void genFactoryInnerClass(Notion notion) {
		//public static final class Factory
		ClassDTO innerFactoryClass = (ClassDTO) scFacade.getFacade().createClass();
		innerFactoryClass.setName("Factory");
		((Class)innerFactoryClass).setIsLeaf(true);
		scFacade.createMappingBetween(notion, (Class)innerFactoryClass, TraceTypes.ENTITY_INNER_FACTORY_MAPPING_NAME);
		Class entityClass = scFacade.getLinkedClass(notion, TraceTypes.ENTITY_MAPPING_NAME);
		Class entityImplClass = scFacade.getLinkedClass(notion, TraceTypes.ENTITY_IMPL_MAPPING_NAME);
		
		//public static Entity newInstance() {return new EntityImpl();}
		OperationDTO newInstance = (OperationDTO) scFacade.createOperation("newInstance", VisibilityKind.PUBLIC, new Object[][]{{null, entityClass}});
		((Operation)newInstance).setIsStatic(true);
		genUtils.genCode((Operation)newInstance, "return new " + entityImplClass.getName() + "();\n");
		innerFactoryClass.addOperation(newInstance);
		
		//public static Entity newInstance(params[]) {final Entity entity = new EntityImpl(); entity.set(...); return entity;}
		Object[][] parameters = getOperationParameters(entityClass, false, false);
		OperationDTO newInstanceWithParams = (OperationDTO) scFacade.createOperation("newInstance", VisibilityKind.PUBLIC, 
											parameters);
		((Operation)newInstanceWithParams).setIsStatic(true);
		
		String code = getBodyForOperationWithParameters(entityClass, entityImplClass, parameters, false);
		genUtils.genCode((Operation)newInstanceWithParams, code);
		innerFactoryClass.addOperation(newInstanceWithParams);
		
		//public static Entity newInstance(paramsWithID[]) {final Entity entity = new EntityImpl(); entity.set(...); return entity;}
		parameters = getOperationParameters(entityClass, true, false);
		OperationDTO newInstanceWithParamsWithID = (OperationDTO) scFacade.createOperation("newInstance", VisibilityKind.PUBLIC, 
											parameters);
		((Operation)newInstanceWithParamsWithID).setIsStatic(true);
		
		code = getBodyForOperationWithParameters(entityClass, entityImplClass, parameters, true);
		genUtils.genCode((Operation)newInstanceWithParamsWithID, code);
		innerFactoryClass.addOperation(newInstanceWithParamsWithID);
		
		//public static Entity newInstance(paramsWithIDAndWithoutLists[]) {final Entity entity = new EntityImpl(); entity.set(...); return entity;}
		boolean withoutLists = false;
		for(int i=0; i < parameters.length; i++){
			String paramName = (String) parameters[i][0];
			if(paramName != null && paramName.endsWith("List")){
				withoutLists = true;
				break;
			}
		}
		if(withoutLists){
			parameters = getOperationParameters(entityClass, true, true);
			OperationDTO newInstanceWithParamsWithIDWithoutLists = (OperationDTO) scFacade.createOperation("newInstance", VisibilityKind.PUBLIC, 
												parameters);
			((Operation)newInstanceWithParamsWithIDWithoutLists).setIsStatic(true);
			
			code = getBodyForOperationWithParameters(entityClass, entityImplClass, parameters, true);
			genUtils.genCode((Operation)newInstanceWithParamsWithIDWithoutLists, code);
			innerFactoryClass.addOperation(newInstanceWithParamsWithIDWithoutLists);
		}
		
		
		System.out.println("ModelGenerator.java->genFactoryInnerClass(): "+((Class)innerFactoryClass).getId());
	}
	
	private Object[][] getOperationParameters(Class entityClass, boolean withIDfield, boolean withoutListFields) {
		List<? extends Property> fields = entityClass.getOwnedAttributeList();
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
			if(fieldName.endsWith("List")){
				if(withoutListFields){
					continue;
				}
				else{
					fieldType = "List<"+fieldType+">";
				}
			}
			
			Object[] pair = new Object[]{fieldName, fieldType};
			parameters[idx] = pair;
			idx++;
		}
		parameters[idx] = new Object[]{null, entityClass};
		
		return parameters;
	}
	
	private String getBodyForOperationWithParameters(Class entityClass, Class entityImplClass, Object[][] parameters, boolean withIDfield) {
		StringBuilder code = new StringBuilder()
		.append("final "+entityClass.getName()+" entity = new "+entityImplClass.getName()+"();\n");
		for(Operation op : entityClass.getOperationList()){
			String operationName = op.getName().substring(1, op.getName().lastIndexOf('('));
			if(op.getName().substring(1).startsWith("set")){
				for(int i=0; i < parameters.length; i++){
					if(parameters[i][0] == null){
						continue;
					}
					String fieldName = (String) parameters[i][0];
					if(fieldName.endsWith("id")){
						if(!withIDfield){
							continue;
						}
						String operFieldName = genUtils.toLowerCamelCase(operationName.substring(3));
						if(operFieldName.equals("id")){
							code.append("entity."+operationName+"("+fieldName+");\n");
							break;
						}
					}
					String operFieldName = genUtils.toLowerCamelCase(operationName.substring(3));
					if(operFieldName.equalsIgnoreCase(fieldName)){
						code.append("entity."+operationName+"("+fieldName+");\n");
						break;
					}
				}
			}
		}
		code.append("return entity;\n");
		return code.toString();
	}
}
