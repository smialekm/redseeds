package eu.redseeds.java.generator.services;

import java.util.List;

import eu.redseeds.java.generator.utils.TraceTypes;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.ActionTypesEnum;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionDTO;
import eu.redseeds.scl.model.sdsl.ClassDTO;
import eu.redseeds.scl.model.sdsl.InterfaceDTO;
import eu.redseeds.scl.model.sdsl.OperationDTO;
import eu.redseeds.scl.model.sdsl.PackageDTO;
import eu.redseeds.scl.rsl.rsldomainelements.notions.Notion;
import eu.redseeds.scl.sclkernel.Stereotype;
import eu.redseeds.scl.uml.classes.interfaces.Interface;
import eu.redseeds.scl.uml.classes.interfaces.InterfaceRealization;
import eu.redseeds.scl.uml.classes.kernel.Class;
import eu.redseeds.scl.uml.classes.kernel.NamedElement;
import eu.redseeds.scl.uml.classes.kernel.Operation;
import eu.redseeds.scl.uml.classes.kernel.Package;
import eu.redseeds.scl.uml.classes.kernel.Property;
import eu.redseeds.scl.uml.classes.kernel.Type;
import eu.redseeds.scl.uml.classes.kernel.VisibilityKind;

public class SpringServicesGenerator extends ServicesImplGenerator {
	
	public SpringServicesGenerator() {
		super();
	}
	
	public void genSpringServiceImpl(Notion notion, InterfaceDTO service) {
		ClassDTO springServiceImpl = (ClassDTO) scFacade.getFacade().createClass();
		springServiceImpl.setName(service.getName()+"Impl");
		
		scFacade.createMappingBetween(notion, (Class)springServiceImpl, TraceTypes.SPRING_SERVICE_IMPL_MAPPING_NAME);
		
		PackageDTO javaPack = scFacade.getPackage("java");
		PackageDTO utilPack = (PackageDTO) scFacade.getElementFromPackage(javaPack, "util", Package.class);
		InterfaceDTO list = (InterfaceDTO) scFacade.getElementFromPackage(utilPack, "List", Interface.class);
		ClassDTO arraylist = (ClassDTO) scFacade.getElementFromPackage(utilPack, "ArrayList", Class.class);
		scFacade.createDependency((Class)springServiceImpl, new NamedElement[]{(Interface)list});
		scFacade.createDependency((Class)springServiceImpl, new NamedElement[]{(Class)arraylist});
		
		InterfaceRealization interfaceRealization = scFacade.getFacade().createInterfaceRealization();
		interfaceRealization.addClient((Class)springServiceImpl);
		interfaceRealization.addSupplier((Interface)service);
		
		if(((NotionDTO)notion).getType().equals("")){
			genSpringDAOTemplate(notion, springServiceImpl);
		}
		else{
			Notion mainConcept = scFacade.getMainConcept(notion);
			if(mainConcept != null)
				genSpringDAOTemplate(mainConcept, springServiceImpl);
		}
		
		for(OperationDTO op : service.getOperationDTOList()){
			Operation operationImpl = scFacade.createOperation((Operation)op);
			List<? extends Stereotype> stereotypes = ((Operation)op).getStereotypeList();
			for(Stereotype st : stereotypes){
				if(st.getName().equals(ActionTypesEnum.Read.tag())){
					
				}
				else if(st.getName().equals(ActionTypesEnum.Create.tag())){
					
				}
				else if(st.getName().equals(ActionTypesEnum.Update.tag())){
					
				}
				else if(st.getName().equals(ActionTypesEnum.Delete.tag())){
					
				}
				else if(st.getName().equals(ActionTypesEnum.Validate.tag())){
					
				}
				else if(st.getName().equals(HelperActionTypes.GET_ALL_CONCEPTS)){
					genHelperReadAllMethods(operationImpl, notion, springServiceImpl);
				}
			}
			
			//after generating services, delete stereotypes indicating operations type
			deleteStereotypesVertices((Operation)op);
			springServiceImpl.addOperation((OperationDTO)operationImpl);
		}
		
		OperationDTO finalize = genFinalizeMethod();
		springServiceImpl.addOperation(finalize);
		
		PackageDTO servicesPack = scFacade.getPackage("Services");
		PackageDTO implPack = (PackageDTO) scFacade.getElementFromPackage(servicesPack, "impl", Package.class);
		PackageDTO springImplPack = (PackageDTO) scFacade.getElementFromPackage(implPack, "spring", Package.class);
		springImplPack.addClass(springServiceImpl);
	}
	
	private OperationDTO genFinalizeMethod() {
		OperationDTO op = (OperationDTO) scFacade.createOperation("finalize", VisibilityKind.PROTECTED);
		genUtils.genCode((Operation)op, "DaoFactory.getInstance().shutdown();\n");
		return op;
	}
	
	private void genHelperReadAllMethods(Operation operation, Notion dataView, ClassDTO serviceImpl) {
		Type listReturnType = scFacade.getOperationReturnType(operation);
		String returnTypeName = listReturnType.getName();
		String dtoType = returnTypeName.substring(returnTypeName.indexOf("<")+1, returnTypeName.indexOf(">"));
		Class dto = scFacade.getEntityOrDTOByType(dtoType);
		Notion sharedConcept = scFacade.getLinkedNotion((ClassDTO)dto, TraceTypes.DTO_MAPPING_NAME);
		if(sharedConcept != null){
			if(!isServiceContainsSpringDAOField(serviceImpl, sharedConcept)){
				//ConceptDAO getConceptDAO()
				genSpringDAOTemplate(sharedConcept, serviceImpl);
				//List<ConceptDTO> getAllConcepts()
				genAllDTOsGetter(operation, sharedConcept, (Class)serviceImpl);
			}
		}
	}
	
	/*private void genHelperReadAllMethods(Notion dataView, ClassDTO serviceImpl) {
		//check among relationships for Screen directed relation
		for(DomainElementRelationshipDTO rel : ((NotionDTO)dataView).getDomainElementRelationshipDTOList()){
			//[SRC]--create-->[TARGET]
			if(rel.isDirected()){
				NotionDTO src = (NotionDTO) rel.getSource();
				if(src.equals(dataView)){
					continue;
				}
				
				if(src.getType().equals(NotionTypesEnum.Screen.tag())){
					NotionDTO mainConcept = (NotionDTO) scFacade.getMainConcept(dataView);
					for(NotionDTO attribute : ((NotionDTO)dataView).getNotionDTOAttributeList()){
						Notion sharedConcept = scFacade.searchConceptWithAttrSharedByDataView(mainConcept, attribute, null);
						if(sharedConcept != null){
							if(!isServiceContainsSpringDAOField(serviceImpl, sharedConcept)){
								//ConceptDAO getConceptDAO()
								genSpringDAOTemplate(sharedConcept, serviceImpl);
								//List<ConceptDTO> getAllConcepts()
								OperationDTO getAllConcepts = genAllDTOsGetter(sharedConcept, false);
								
								serviceImpl.addOperation(getAllConcepts);
							}
							
						}
					}
				}
			}
		}
	}*/
	
	private boolean isServiceContainsSpringDAOField(ClassDTO serviceImpl, Notion concept) {
		Interface dao = scFacade.getLinkedInterface(concept, TraceTypes.DAO_MAPPING_NAME);
		for(Property prop : ((Class)serviceImpl).getOwnedAttributeList()){
			for(Type type : prop.getTypeList()){
				if(type.getName().equals(dao.getName())){
					return true;
				}
			}
		}
		return false;
	}
	
	private void genSpringDAOTemplate(Notion mainConcept, ClassDTO serviceImpl) {
		//private ConceptDAO conceptDAO;
		Interface daoInterface = scFacade.getLinkedInterface(mainConcept, TraceTypes.DAO_MAPPING_NAME);
		Property daoField = scFacade.getFacade().createProperty();
		daoField.setName(genUtils.toLowerCamelCase(daoInterface.getName()));
		daoField.setVisibility(VisibilityKind.PRIVATE);
		daoField.addType(daoInterface);
		
		//public ConceptDAO getConceptDAO() & public void setConceptDAO()
		OperationDTO getterDAO = (OperationDTO) scFacade.createOperation("get"+daoInterface.getName(), VisibilityKind.PUBLIC, new Object[][]{{null, daoInterface}});
		OperationDTO setterDAO = (OperationDTO) scFacade.createOperation("set"+daoInterface.getName(), VisibilityKind.PUBLIC, new Object[][]{{daoField.getName(), daoInterface}});
		
		//import DaoFactory
		Class daoFactory = scFacade.getLinkedClass(mainConcept, TraceTypes.DAO_FACTORY_MAPPING_NAME);
		scFacade.createDependency((Class)serviceImpl, new NamedElement[]{daoFactory});
		
		String code = new StringBuilder()
		.append("if(" + daoField.getName() + " == null){\n")
		.append("	" + daoField.getName() + " = "+daoFactory.getName()+".getInstance().create" + daoInterface.getName() + "();\n")
		.append("}\n")
		.append("return " + daoField.getName() + ";\n")
		.toString();
		
		genUtils.genCode((Operation)getterDAO, code);
		genUtils.genCode((Operation)setterDAO, "this." + daoField.getName() + " = " + daoField.getName() + ";\n");
		
		((Class)serviceImpl).addOwnedAttribute(daoField);
		serviceImpl.addOperation(getterDAO);
		serviceImpl.addOperation(setterDAO);
	}
}
