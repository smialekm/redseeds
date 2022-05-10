package eu.redseeds.java.generator.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import eu.redseeds.scl.uml.classes.kernel.Parameter;
import eu.redseeds.scl.uml.classes.kernel.ParameterDirectionKind;
import eu.redseeds.scl.uml.classes.kernel.Property;
import eu.redseeds.scl.uml.classes.kernel.Type;
import eu.redseeds.scl.uml.classes.kernel.VisibilityKind;

public class HibernateServicesGenerator extends ServicesImplGenerator {
	
	public HibernateServicesGenerator() {
		super();
	}
	
	public void genHibernateServiceImpl(Notion notion, InterfaceDTO service) {
		ClassDTO serviceImpl = (ClassDTO) scFacade.getFacade().createClass();
		serviceImpl.setName(service.getName()+"Impl");
		
		scFacade.createMappingBetween(notion, (Class)serviceImpl, TraceTypes.HIBERNATE_SERVICE_IMPL_MAPPING_NAME);
		
		PackageDTO javaPack = scFacade.getPackage("java");
		PackageDTO utilPack = (PackageDTO) scFacade.getElementFromPackage(javaPack, "util", Package.class);
		InterfaceDTO list = (InterfaceDTO) scFacade.getElementFromPackage(utilPack, "List", Interface.class);
		ClassDTO arraylist = (ClassDTO) scFacade.getElementFromPackage(utilPack, "ArrayList", Class.class);
		scFacade.createDependency((Class)serviceImpl, new NamedElement[]{(Interface)list});
		scFacade.createDependency((Class)serviceImpl, new NamedElement[]{(Class)arraylist});
		
		InterfaceRealization interfaceRealization = scFacade.getFacade().createInterfaceRealization();
		interfaceRealization.addClient((Class)serviceImpl);
		interfaceRealization.addSupplier((Interface)service);
		
		if(((NotionDTO)notion).getType().equals("")){
			genHibernateDAOTemplate(notion, serviceImpl);
		}
		else{
			Notion mainConcept = scFacade.getMainConcept(notion);
			if(mainConcept != null)
				genHibernateDAOTemplate(mainConcept, serviceImpl);
		}
		
		for(OperationDTO op : service.getOperationDTOList()){
			Operation operationImpl = scFacade.createOperation((Operation)op);
			List<? extends Stereotype> stereotypes = ((Operation)op).getStereotypeList();
			for(Stereotype st : stereotypes){
				if(st.getName().equals(ActionTypesEnum.Read.tag())){
					genReadMethodBody(operationImpl, (Class)serviceImpl);
				}
				else if(st.getName().equals(ActionTypesEnum.Create.tag())){
					genCreateMethodBody(operationImpl, (Class)serviceImpl);
				}
				else if(st.getName().equals(ActionTypesEnum.Update.tag())){
					genUpdateMethodBody(operationImpl, (Class)serviceImpl);
				}
				else if(st.getName().equals(ActionTypesEnum.Delete.tag())){
					genDeleteMethodBody(operationImpl, (Class)serviceImpl);
				}
				else if(st.getName().equals(ActionTypesEnum.Validate.tag())){
					
				}
				else if(st.getName().equals(HelperActionTypes.GET_ALL_CONCEPTS)){
					genHelperReadAllMethods(operationImpl, notion, serviceImpl);
				}
			}
			
			serviceImpl.addOperation((OperationDTO)operationImpl);
		}
		
		OperationDTO finalize = genFinalizeMethod();
		serviceImpl.addOperation(finalize);
		
		PackageDTO servicesPack = scFacade.getPackage("Services");
		PackageDTO implPack = (PackageDTO) scFacade.getElementFromPackage(servicesPack, "impl", Package.class);
		PackageDTO hibernateImplPack = (PackageDTO) scFacade.getElementFromPackage(implPack, "hibernate", Package.class);
		hibernateImplPack.addClass(serviceImpl);
	}
	
	private OperationDTO genFinalizeMethod() {
		OperationDTO op = (OperationDTO) scFacade.createOperation("finalize", VisibilityKind.PROTECTED);
		genUtils.genCode((Operation)op, "HibernateDaoFactory.getInstance().shutdown();\n");
		return op;
	}
	
	private void genHelperReadAllMethods(Operation operation, Notion dataView, ClassDTO serviceImpl) {
		Type listReturnType = scFacade.getOperationReturnType(operation);
		String returnTypeName = listReturnType.getName();
		String dtoType = returnTypeName.substring(returnTypeName.indexOf("<")+1, returnTypeName.indexOf(">"));
		Class dto = scFacade.getEntityOrDTOByType(dtoType);
		Notion sharedConcept = scFacade.getLinkedNotion((ClassDTO)dto, TraceTypes.DTO_MAPPING_NAME);
		if(sharedConcept != null){
			if(!isServiceContainsHibernateDAOField(serviceImpl, sharedConcept)){
				//ConceptDAO getConceptDAO()
				genHibernateDAOTemplate(sharedConcept, serviceImpl);
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
							if(!isServiceContainsHibernateDAOField(serviceImpl, sharedConcept)){
								//ConceptDAO getConceptDAO()
								genHibernateDAOTemplate(sharedConcept, serviceImpl);
								//List<ConceptDTO> getAllConcepts()
								OperationDTO getAllConcepts = genAllDTOsGetter(sharedConcept, true);
								
								serviceImpl.addOperation(getAllConcepts);
							}
							
						}
					}
				}
			}
		}
	}*/
	
	private boolean isServiceContainsHibernateDAOField(ClassDTO serviceImpl, Notion concept) {
		Interface iGenericDao = scFacade.getLinkedInterface(concept, TraceTypes.GENERIC_DAO_MAPPING_NAME);
		for(Property prop : ((Class)serviceImpl).getOwnedAttributeList()){
			for(Type type : prop.getTypeList()){
				if(type.getName().equals(iGenericDao.getName())){
					return true;
				}
			}
		}
		return false;
	}
	
	private void genHibernateDAOTemplate(Notion mainConcept, ClassDTO serviceImpl) {
		//import org.perfectjpattern.jee.integration.dao.HibernateDaoFactory;
		PackageDTO orgPack = scFacade.getPackage("org");
		PackageDTO perfectjpatternPack = (PackageDTO) scFacade.getElementFromPackage(orgPack, "perfectjpattern", Package.class);
		PackageDTO jeePack = (PackageDTO) scFacade.getElementFromPackage(perfectjpatternPack, "jee", Package.class);
		PackageDTO integrationPack = (PackageDTO) scFacade.getElementFromPackage(jeePack, "integration", Package.class);
		PackageDTO daoPack = (PackageDTO) scFacade.getElementFromPackage(integrationPack, "dao", Package.class);
		ClassDTO hibernateDaoFactory = (ClassDTO) scFacade.getElementFromPackage(daoPack, "HibernateDaoFactory", Class.class);
		
		//private IGenericDao<Long, Concept> conceptDAO;
		Interface daoInterface = scFacade.getLinkedInterface(mainConcept, TraceTypes.DAO_MAPPING_NAME);
		Interface iGenericDao = scFacade.getLinkedInterface(mainConcept, TraceTypes.GENERIC_DAO_MAPPING_NAME);
		Property daoField = scFacade.getFacade().createProperty();
		daoField.setName(genUtils.toLowerCamelCase(daoInterface.getName()));
		daoField.setVisibility(VisibilityKind.PRIVATE);
		daoField.addType(iGenericDao);
		
		Class entity = scFacade.getLinkedClass(mainConcept, TraceTypes.ENTITY_MAPPING_NAME);
		
		scFacade.createDependency((Class)serviceImpl, new NamedElement[]{(Class)hibernateDaoFactory});
		scFacade.createDependency((Class)serviceImpl, new NamedElement[]{entity});
		
		//public IGenericDao<Long, Concept> getConceptDAO() & public void setConceptDAO(IGenericDao<Long, Concept>)
		OperationDTO getterDAO = (OperationDTO) scFacade.createOperation("get"+daoInterface.getName(), VisibilityKind.PUBLIC, new Object[][]{{null, iGenericDao}});
		OperationDTO setterDAO = (OperationDTO) scFacade.createOperation("set"+daoInterface.getName(), VisibilityKind.PUBLIC, new Object[][]{{daoField.getName(), iGenericDao}});
		
		String code = new StringBuilder()
		.append("if(" + daoField.getName() + " == null){\n")
		.append("	" + daoField.getName() + " = HibernateDaoFactory.getInstance().createDao(" + entity.getName() + ".class);\n")
		.append("}\n")
		.append("return " + daoField.getName() + ";\n")
		.toString();
		
		genUtils.genCode((Operation)getterDAO, code);
		genUtils.genCode((Operation)setterDAO, "this." + daoField.getName() + " = " + daoField.getName() + ";\n");
		
		((Class)serviceImpl).addOwnedAttribute(daoField);
		serviceImpl.addOperation(getterDAO);
		serviceImpl.addOperation(setterDAO);
	}
	
	private void genReadMethodBody(Operation operation, Class serviceImpl) {
		List<? extends Parameter> params = operation.getOwnedParameterList();
		Type returnType = scFacade.getOperationReturnType(operation);
		//READ operation with no arguments
		if(returnType != null && params.size() == 1){
			String returnTypeName = returnType.getName();
			//only check if return param is ListDTO (should be true for no arguments)
			if(scFacade.isListViewType(returnTypeName)){
				//construct body for ListDTO readList()
				Class listDto = scFacade.getEntityOrDTOByType(returnTypeName);
				Notion listView = scFacade.getLinkedNotion((ClassDTO)listDto, TraceTypes.DTO_LIST_MAPPING_NAME);
				
				String code = genReadListMethod(listDto, listView);
				genUtils.genCode(operation, code);
			}
		}
		//READ operation with 1 argument
		else if(returnType != null && params.size() == 2){
			String returnTypeName = returnType.getName();
			Type argument = scFacade.getOperationArgumentsTypes(operation).get(0);
			String argumentTypeName = argument.getName();
			String argumentName = scFacade.getOperationArgumentsNames(operation).get(0);
			//check if return param is ListDTO
			if(scFacade.isListViewType(returnTypeName)){
				//check if param is SimpleDTO
				if(scFacade.isSimpleViewType(argumentTypeName)){
					//construct body for ListDTO readListByCriteria(SimpleDTO)
					Class simpleDto = scFacade.getEntityOrDTOByType(argumentTypeName);
					Class listDto = scFacade.getEntityOrDTOByType(returnTypeName);
					Notion listView = scFacade.getLinkedNotion((ClassDTO)listDto, TraceTypes.DTO_LIST_MAPPING_NAME);
					
					String code = genReadListByCriteriaMethod(argumentName, simpleDto, listDto, listView);
					genUtils.genCode(operation, code);
				}
				//check if param is ConceptDTO
				else if(scFacade.isDefaultSimpleViewType(argumentTypeName)){
					//TODO (is it necessary?) construct body for ListDTO readListById(ConceptDTO)
				}
			}
			//check if return param is SimpleDTO
			else if(scFacade.isSimpleViewType(returnTypeName)){
				//check if param is SimpleDTO
				if(scFacade.isSimpleViewType(argumentTypeName)){
					//TODO (is it necessary?) construct body for SimpleDTO readByCriteria(SimpleDTO)
				}
				//check if param is ConceptDTO
				else if(scFacade.isDefaultSimpleViewType(argumentTypeName)){
					//construct body for SimpleDTO readById(ConceptDTO)
					Class conceptDto = scFacade.getEntityOrDTOByType(argumentTypeName);
					Class simpleDto = scFacade.getEntityOrDTOByType(returnTypeName);
					Notion simpleView = scFacade.getLinkedNotion((ClassDTO)simpleDto, TraceTypes.DTO_MAPPING_NAME);
					
					String code = genReadByIdMethod(argumentName, conceptDto, simpleDto, simpleView);
					genUtils.genCode(operation, code);
				}
			}
			//TODO (is it necessary?) check if return param is ConceptDTO
			else if(scFacade.isDefaultSimpleViewType(returnTypeName)){
				
			}
		}
	}
	
	private void genCreateMethodBody(Operation operation, Class serviceImpl) {
		List<? extends Parameter> params = operation.getOwnedParameterList();
		StringBuilder code = new StringBuilder();
		Map<Notion, Class> mainConcepts = new HashMap<Notion, Class>();
		
		for(Parameter param : params){
			//create domain model
			transformDTOtoEntity(param, true, code, mainConcepts, serviceImpl);
		}
		
		StringBuilder code2 = new StringBuilder();
		
		//call DAO stuff
		for(Notion mainConcept : mainConcepts.keySet()){
			Interface dao = scFacade.getLinkedInterface(mainConcept, TraceTypes.DAO_MAPPING_NAME);
			
			//check if ServiceImpl has every DAO's, if not, generate appropriate field and getter/setter
			if(!isServiceContainsHibernateDAOField((ClassDTO)serviceImpl, mainConcept)){
				genHibernateDAOTemplate(mainConcept, (ClassDTO)serviceImpl);
			}
			
			//long id = getDAO().create(entity);
			Class mainConceptEntity = mainConcepts.get(mainConcept);
			code2.append("	long id = get" + dao.getName() + "().create(" + mainConceptEntity.getName().toLowerCase() + ");\n");
			//getDAO().getTransaction().commit();
			code2.append("	get" + dao.getName() + "().getTransaction().commit();\n");
			//dto.setId(id);
			code2.append("	"+getParamIdSetterCode(params.get(0)) + "(id);\n");
		}
		
		StringBuilder wrappedCode = code.append(genTryCatch(code2.toString()));
		
		genUtils.genCode(operation, wrappedCode.toString());
	}
	
	private String getParamIdSetterCode(Parameter param) {
		if(param.getDirection() == null || !param.getDirection().equals(ParameterDirectionKind.RETURN)){
			String paramName = param.getName();
			return paramName + ".setId";
		}
		return null;
	}
	
	private void genDeleteMethodBody(Operation operation, Class serviceImpl) {
		List<? extends Parameter> params = operation.getOwnedParameterList();
		StringBuilder code = new StringBuilder();
		Map<Notion, Class> mainConcepts = new HashMap<Notion, Class>();
		
		for(Parameter param : params){
			//create domain model
			transformDTOtoEntity(param, false, code, mainConcepts, serviceImpl);
		}
		
		StringBuilder code2 = new StringBuilder();
		
		//call DAO stuff
		for(Notion mainConcept : mainConcepts.keySet()){
			Interface dao = scFacade.getLinkedInterface(mainConcept, TraceTypes.DAO_MAPPING_NAME);
			
			//check if ServiceImpl has every DAO's, if not, generate appropriate field and getter/setter
			if(!isServiceContainsHibernateDAOField((ClassDTO)serviceImpl, mainConcept)){
				genHibernateDAOTemplate(mainConcept, (ClassDTO)serviceImpl);
			}
			
			//getDAO().delete(entity);
			Class mainConceptEntity = mainConcepts.get(mainConcept);
			code2.append("	get" + dao.getName() + "().delete(" + mainConceptEntity.getName().toLowerCase() + ");\n");
			//getDAO().getTransaction().commit();
			code2.append("	get" + dao.getName() + "().getTransaction().commit();\n");
		}
		
		StringBuilder wrappedCode = code.append(genTryCatch(code2.toString()));
		
		genUtils.genCode(operation, wrappedCode.toString());
	}
	
	private void genUpdateMethodBody(Operation operation, Class serviceImpl) {
		List<? extends Parameter> params = operation.getOwnedParameterList();
		StringBuilder code = new StringBuilder();
		Map<Notion, Class> mainConcepts = new HashMap<Notion, Class>();
		
		for(Parameter param : params){
			//create domain model
			transformDTOtoEntity(param, true, code, mainConcepts, serviceImpl);
		}
		
		StringBuilder code2 = new StringBuilder();
		
		//call DAO stuff
		for(Notion mainConcept : mainConcepts.keySet()){
			Interface dao = scFacade.getLinkedInterface(mainConcept, TraceTypes.DAO_MAPPING_NAME);
			
			//check if ServiceImpl has every DAO's, if not, generate appropriate field and getter/setter
			if(!isServiceContainsHibernateDAOField((ClassDTO)serviceImpl, mainConcept)){
				genHibernateDAOTemplate(mainConcept, (ClassDTO)serviceImpl);
			}
			
			Class mainConceptEntity = mainConcepts.get(mainConcept);
			//entity.setId must be called in first place
			code2.append("	"+mainConceptEntity.getName().toLowerCase()+".setId("+params.get(0).getName()+".getId());\n");
			//getDAO().update(entity);
			code2.append("	get" + dao.getName() + "().update(" + mainConceptEntity.getName().toLowerCase() + ");\n");
			//getDAO().getTransaction().commit();
			code2.append("	get" + dao.getName() + "().getTransaction().commit();\n");
		}
		
		StringBuilder wrappedCode = code.append(genTryCatch(code2.toString()));
		
		genUtils.genCode(operation, wrappedCode.toString());
	}
	
}
