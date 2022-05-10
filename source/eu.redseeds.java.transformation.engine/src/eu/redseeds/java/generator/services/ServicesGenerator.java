package eu.redseeds.java.generator.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import eu.redseeds.java.generator.utils.GeneratorUtils;
import eu.redseeds.java.generator.utils.TraceTypes;
import eu.redseeds.java.parser.elements.UseCasesParser;
import eu.redseeds.java.repository.SCFacade;
import eu.redseeds.scl.model.rsl.rsldomainelements.domainelements.DomainElementRelationshipDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.ActionTypesEnum;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.DomainStatementDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionTypesEnum;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.PhraseDTO;
import eu.redseeds.scl.model.sdsl.ClassDTO;
import eu.redseeds.scl.model.sdsl.InterfaceDTO;
import eu.redseeds.scl.model.sdsl.OperationDTO;
import eu.redseeds.scl.model.sdsl.PackageDTO;
import eu.redseeds.scl.rsl.rsldomainelements.notions.Notion;
import eu.redseeds.scl.uml.classes.interfaces.Interface;
import eu.redseeds.scl.uml.classes.kernel.Class;
import eu.redseeds.scl.uml.classes.kernel.NamedElement;
import eu.redseeds.scl.uml.classes.kernel.Operation;
import eu.redseeds.scl.uml.classes.kernel.Package;
import eu.redseeds.scl.uml.classes.kernel.VisibilityKind;

public class ServicesGenerator {
	
	private SCFacade scFacade;
	private GeneratorUtils genUtils;
	private UseCasesParser ucParser;
	private SpringServicesGenerator springServiceGenerator;
	private HibernateServicesGenerator hibernateServiceGenerator;
	
	public ServicesGenerator() {
		scFacade = SCFacade.instance();
		genUtils = new GeneratorUtils();
		ucParser = new UseCasesParser();
		springServiceGenerator = new SpringServicesGenerator();
		hibernateServiceGenerator = new HibernateServicesGenerator();
	}

	public void genServicesLayer() {
		List<Notion> notList = new ArrayList<Notion>();
		for(Notion notion : scFacade.getFacade().getNotionVertices()){
			String type = ((NotionDTO)notion).getType();
			if(type.equals(NotionTypesEnum.List_View.tag()) || type.equals(NotionTypesEnum.Simple_View.tag()) || type.equals("")){
				notList.add(notion);
			}
		}
		
		for(Notion notion : notList){
			genService(notion);
		}
	}
	
	private void genService(Notion notion) {
		List<OperationDTO> operations = new ArrayList<OperationDTO>();
		List<DomainStatementDTO> domainStms = ((NotionDTO)notion).getDomainStatementDTOList();
		OperationDTO operation = null;
		
		for(DomainStatementDTO ds : domainStms){
			if(ds.getName().equalsIgnoreCase(((NotionDTO)notion).getName())){
				continue;
			}
			
			String operationName = genUtils.toLowerCamelCase(ds.getName());
			
			//find verb phrase among scenarios
			PhraseDTO phrase = ds.getPhraseDTO();
			ucParser.setPhraseToFind(phrase);
			ucParser.parse();
			
			//check if sentence (where verb phrase was found) is type of System->System
			if(ucParser.isSystem2SystemSentenceType()){
				Object[][] params = getParamsAndReturnType(notion, ds);
				
				//full operation signature
				if(params != null){
					operation = (OperationDTO) scFacade.createOperation(operationName, VisibilityKind.PUBLIC, params);
				}
				//empty operation signature
				else{
					operation = (OperationDTO) scFacade.createOperation(operationName, VisibilityKind.PUBLIC);
				}
			}
			//domain statement must have been added manually and was not used in scenarios
			else{
				//for Concepts those operations is generated in ModelImpl classes
				if(((NotionDTO)notion).getType().equals("")){
					
					//Concept operation()
					if(ds.getCRUD().equals(ActionTypesEnum.Read.tag())){
						Class returnType = scFacade.getLinkedClass(notion, TraceTypes.ENTITY_MAPPING_NAME);
						operation = (OperationDTO) scFacade.createOperation(operationName, VisibilityKind.PUBLIC, new Object[][]{{null, returnType}});
					}
					//void operation(Concept param)
					else if(Arrays.asList(new String[]{ActionTypesEnum.Create.tag(), ActionTypesEnum.Delete.tag(), ActionTypesEnum.Update.tag()}).contains(ds.getCRUD())){
						Class paramType = scFacade.getLinkedClass(notion, TraceTypes.ENTITY_MAPPING_NAME);
						String name = paramType.getName().substring(0, 1).toLowerCase() + paramType.getName().substring(1);
						operation = (OperationDTO) scFacade.createOperation(operationName, VisibilityKind.PUBLIC, new Object[][]{{name, paramType}});
					}
					//void operation()
					else{
						operation = (OperationDTO) scFacade.createOperation(operationName, VisibilityKind.PUBLIC);						
					}
					
					ClassDTO modelImplClass = (ClassDTO) scFacade.getLinkedClass(notion, TraceTypes.ENTITY_IMPL_MAPPING_NAME);
					if(modelImplClass != null){
						modelImplClass.addOperation(operation);
					}
					
					continue;
				}
				//for Data Views those operations is generated in Service classes
				else if(Arrays.asList(new String[]{NotionTypesEnum.Simple_View.tag(), NotionTypesEnum.List_View.tag(), NotionTypesEnum.Tree_View.tag()}).contains(((NotionDTO)notion).getType())){
					
					//check if domain statement was added manually
					if(!ucParser.verbPhraseWasFoundInScenarios()){
						
						//DataView operation()
						if(ds.getCRUD().equals(ActionTypesEnum.Read.tag())){
							Class returnType = scFacade.getLinkedClass(notion, TraceTypes.DTO_MAPPING_NAME);
							operation = (OperationDTO) scFacade.createOperation(operationName, VisibilityKind.PUBLIC, new Object[][]{{null, returnType}});
						}
						//void operation(DataView param)
						else if(Arrays.asList(new String[]{ActionTypesEnum.Create.tag(), ActionTypesEnum.Delete.tag(), ActionTypesEnum.Update.tag()}).contains(ds.getCRUD())){
							Class paramType = scFacade.getLinkedClass(notion, TraceTypes.DTO_MAPPING_NAME);
							String name = paramType.getName().substring(0, 1).toLowerCase() + paramType.getName().substring(1);
							operation = (OperationDTO) scFacade.createOperation(operationName, VisibilityKind.PUBLIC, new Object[][]{{name, paramType}});
						}
						//void operation()
						else{
							operation = (OperationDTO) scFacade.createOperation(operationName, VisibilityKind.PUBLIC);
						}
					}
					else{
						continue;
					}
				}
			}
			
			eu.redseeds.scl.uml.classes.kernel.Stereotype stereotype = scFacade.getFacade().createUml$classes$kernel$Stereotype();
			stereotype.setName(ds.getCRUD());
			((Operation)operation).addStereotype(stereotype);
			
			operations.add(operation);
		}
		
		if(operations == null || operations.isEmpty()){
			return;
		}
		String notionName = genUtils.toCamelCase(((NotionDTO)notion).getName());
		InterfaceDTO service = (InterfaceDTO) scFacade.getFacade().createInterface();
		service.setName(notionName+"Service");
		
		scFacade.createMappingBetween(notion, (Interface)service, TraceTypes.SERVICE_MAPPING_NAME);
		
		PackageDTO javaPack = scFacade.getPackage("java");
		PackageDTO utilPack = (PackageDTO) scFacade.getElementFromPackage(javaPack, "util", Package.class);
		InterfaceDTO list = (InterfaceDTO) scFacade.getElementFromPackage(utilPack, "List", Interface.class);
		scFacade.createDependency((Interface)service, new NamedElement[]{(Interface)list});
		
		//if needed generate helper utility methods e.g getAllConceptDTOs(), getDTOById()
		List<OperationDTO> getAllOperations = genHelperGetAllMethodSignature(notion, service);
		if(!getAllOperations.isEmpty())
			operations.addAll(getAllOperations);
		
		//add all operations to service interface
		for(OperationDTO op : operations){
			service.addOperation(op);
		}
		PackageDTO servicesPack = scFacade.getPackage("Services");
		servicesPack.addInterface(service);
		
		//call for concrete implementations
		hibernateServiceGenerator.genHibernateServiceImpl(notion, service);
		springServiceGenerator.genSpringServiceImpl(notion, service);
	}
	
	private Object[][] getParamsAndReturnType(Notion notion, DomainStatementDTO ds) {
		Object[][] paramsForVoid = new Object[1][2];
		Object[][] paramsForNotVoid = new Object[2][2];
		Object[][] paramsForNotVoidWithPrecondConcept = new Object[3][2];
		Object[][] paramsForFrame = null;
		
		NotionDTO concept = ucParser.getActor2ConceptNotion();
		
		//check what kind of parameter should be generated in operation
		if(ucParser.paramIsEmpty()){
			
			if(((NotionDTO)notion).getType().equals(NotionTypesEnum.Simple_View.tag())){
				//might be Master-Detail relationship -> Actor selects [simple view] from [list view]
				Class paramType = getRelatedListRowDTOForValueObject(notion);
				
				//concept might be specified in precondition
				if(ucParser.conceptInPrecondition()){
					NotionDTO precondConcept = ucParser.getConceptFromPrecondition();
					paramType = scFacade.getLinkedClass((Notion)precondConcept, TraceTypes.DTO_MAPPING_NAME);
				}
				//if precondition is empty, get main concept
				else{
					paramType = getRelatedMainConceptDTO(notion);
				}
				
				if(paramType != null){
					String name = paramType.getName().substring(0, 1).toLowerCase() + paramType.getName().substring(1);
					
					//check Domain Statement for CRUD_READ to determine return type
					if(ds.getCRUD().equals(ActionTypesEnum.Read.tag())){
						Class returnType = scFacade.getLinkedClass(notion, TraceTypes.DTO_MAPPING_NAME);
						paramsForNotVoid[0][0] = null;
						paramsForNotVoid[0][1] = returnType;
						paramsForNotVoid[1][0] = name;
						paramsForNotVoid[1][1] = paramType;
					}
					else{
						//cannot determine return type - by default set to void
						paramsForVoid[0][0] = name;
						paramsForVoid[0][1] = paramType;
					}
				}
			}
			else if(((NotionDTO)notion).getType().equals(NotionTypesEnum.List_View.tag())){
				//concept might be specified in precondition
				Class paramType = null;
				
				if(ucParser.conceptInPrecondition()){
					NotionDTO precondConcept = ucParser.getConceptFromPrecondition();
					paramType = scFacade.getLinkedClass((Notion)precondConcept, TraceTypes.DTO_MAPPING_NAME);
				}
				if(paramType == null){
					
					if(ucParser.returnIsVoid() || 
						Arrays.asList(new String[]{ActionTypesEnum.Create.tag(), ActionTypesEnum.Delete.tag(), ActionTypesEnum.Update.tag()}).contains(ds.getCRUD())){
						
					}
					else if(ucParser.returnIsValueObject() || ds.getCRUD().equals(ActionTypesEnum.Read.tag())){
						Class returnType = scFacade.getLinkedClass(notion, TraceTypes.DTO_LIST_MAPPING_NAME);
						paramsForVoid[0][0] = null;
						paramsForVoid[0][1] = returnType;
					}
				}
				else{
					
					if(ucParser.returnIsVoid() || 
						Arrays.asList(new String[]{ActionTypesEnum.Create.tag(), ActionTypesEnum.Delete.tag(), ActionTypesEnum.Update.tag()}).contains(ds.getCRUD())){
						
						if(paramType != null){
							String name = paramType.getName().substring(0, 1).toLowerCase() + paramType.getName().substring(1);
							paramsForVoid[0][0] = name;
							paramsForVoid[0][1] = paramType;
						}
					}
					else if(ucParser.returnIsValueObject() || ds.getCRUD().equals(ActionTypesEnum.Read.tag())){
						Class returnType = scFacade.getLinkedClass(notion, TraceTypes.DTO_LIST_MAPPING_NAME);
						
						if(paramType != null){
							String name = paramType.getName().substring(0, 1).toLowerCase() + paramType.getName().substring(1);
							
							paramsForNotVoid[0][0] = null;
							paramsForNotVoid[0][1] = returnType;

							paramsForNotVoid[1][0] = name;
							paramsForNotVoid[1][1] = paramType;
						}
					}
				}
			}
		}
		else if(ucParser.paramIsEntityType()){
			Class paramType = scFacade.getLinkedClass((Notion)concept, TraceTypes.DTO_MAPPING_NAME);
			String name = paramType.getName().substring(0, 1).toLowerCase() + paramType.getName().substring(1);
			
			//check what kind of return value should be generated in operation
			if(ucParser.returnIsVoid() || 
				Arrays.asList(new String[]{ActionTypesEnum.Create.tag(), ActionTypesEnum.Delete.tag(), ActionTypesEnum.Update.tag()}).contains(ds.getCRUD())){
				paramsForVoid[0][0] = name;
				paramsForVoid[0][1] = paramType;
			}
			else if(ucParser.returnIsValueObject() || ds.getCRUD().equals(ActionTypesEnum.Read.tag())){
				Class returnType = scFacade.getLinkedClass(notion, TraceTypes.DTO_MAPPING_NAME);
				paramsForNotVoid[0][0] = null;
				paramsForNotVoid[0][1] = returnType;

				paramsForNotVoid[1][0] = name;
				paramsForNotVoid[1][1] = paramType;
			}
		}
		else if(ucParser.paramIsValueObjectType()){
			Class paramType = scFacade.getLinkedClass((Notion)concept, TraceTypes.DTO_MAPPING_NAME);
			String name = paramType.getName().substring(0, 1).toLowerCase() + paramType.getName().substring(1);
			
			//check what kind of return value should be generated in operation
			if(ucParser.returnIsVoid() || 
				Arrays.asList(new String[]{ActionTypesEnum.Create.tag(), ActionTypesEnum.Delete.tag(), ActionTypesEnum.Update.tag()}).contains(ds.getCRUD())){
				
				//concept might be specified in precondition
				if(ucParser.conceptInPrecondition()){
					NotionDTO precondConcept = ucParser.getConceptFromPrecondition();
					Class paramType2 = scFacade.getLinkedClass((Notion)precondConcept, TraceTypes.DTO_MAPPING_NAME);
					String name2 = paramType2.getName().substring(0, 1).toLowerCase() + paramType2.getName().substring(1);
					
					paramsForNotVoid[0][0] = name;
					paramsForNotVoid[0][1] = paramType;

					paramsForNotVoid[1][0] = name2;
					paramsForNotVoid[1][1] = paramType2;
				}
				else{
					paramsForVoid[0][0] = name;
					paramsForVoid[0][1] = paramType;
				}
			}
			else if(ucParser.returnIsValueObject() || ds.getCRUD().equals(ActionTypesEnum.Read.tag())){
				Class returnType = scFacade.getLinkedClass(notion, TraceTypes.DTO_LIST_MAPPING_NAME);
				
				//concept might be specified in precondition
				if(ucParser.conceptInPrecondition()){
					NotionDTO precondConcept = ucParser.getConceptFromPrecondition();
					Class paramType2 = scFacade.getLinkedClass((Notion)precondConcept, TraceTypes.DTO_MAPPING_NAME);
					String name2 = paramType2.getName().substring(0, 1).toLowerCase() + paramType2.getName().substring(1);
					
					paramsForNotVoidWithPrecondConcept[0][0] = null;
					paramsForNotVoidWithPrecondConcept[0][1] = returnType;

					paramsForNotVoidWithPrecondConcept[1][0] = name;
					paramsForNotVoidWithPrecondConcept[1][1] = paramType;
					
					paramsForNotVoidWithPrecondConcept[2][0] = name2;
					paramsForNotVoidWithPrecondConcept[2][1] = paramType2;
				}
				else{
					paramsForNotVoid[0][0] = null;
					paramsForNotVoid[0][1] = returnType;

					paramsForNotVoid[1][0] = name;
					paramsForNotVoid[1][1] = paramType;
				}
			}
		}
		else if(ucParser.paramIsFrameType()){
			List<Class> params = getRelatedDTOForFrame((Notion)concept);
			
			//check what kind of return value should be generated in operation
			if(ucParser.returnIsVoid() || 
				Arrays.asList(new String[]{ActionTypesEnum.Create.tag(), ActionTypesEnum.Delete.tag(), ActionTypesEnum.Update.tag()}).contains(ds.getCRUD())){
				
				//concept might be specified in precondition
				if(ucParser.conceptInPrecondition()){
					NotionDTO precondConcept = ucParser.getConceptFromPrecondition();
					Class paramType2 = scFacade.getLinkedClass((Notion)precondConcept, TraceTypes.DTO_MAPPING_NAME);
					String name2 = paramType2.getName().substring(0, 1).toLowerCase() + paramType2.getName().substring(1);
					
					paramsForFrame = new Object[params.size()+1][2];
					for(int i=0; i < params.size(); i++){
						Class paramType = params.get(i);
						String name = paramType.getName().substring(0, 1).toLowerCase() + paramType.getName().substring(1);
						paramsForFrame[i][0] = name;
						paramsForFrame[i][1] = paramType;
					}
					paramsForFrame[params.size()][0] = name2;
					paramsForFrame[params.size()][1] = paramType2;
				}
				else{
					paramsForFrame = new Object[params.size()][2];
					for(int i=0; i < params.size(); i++){
						Class paramType = params.get(i);
						String name = paramType.getName().substring(0, 1).toLowerCase() + paramType.getName().substring(1);
						paramsForFrame[i][0] = name;
						paramsForFrame[i][1] = paramType;
					}
				}
			}
			else if(ucParser.returnIsValueObject() || ds.getCRUD().equals(ActionTypesEnum.Read.tag())){
				
				//concept might be specified in precondition
				if(ucParser.conceptInPrecondition()){
					NotionDTO precondConcept = ucParser.getConceptFromPrecondition();
					Class paramType2 = scFacade.getLinkedClass((Notion)precondConcept, TraceTypes.DTO_MAPPING_NAME);
					String name2 = paramType2.getName().substring(0, 1).toLowerCase() + paramType2.getName().substring(1);
					
					paramsForFrame = new Object[params.size()+2][2];
					Class returnType = scFacade.getLinkedClass(notion, TraceTypes.DTO_MAPPING_NAME);
					paramsForFrame[0][0] = null;
					paramsForFrame[0][1] = returnType;
					
					for(int i=0; i < params.size()+1; i++){
						Class paramType = params.get(i);
						String name = paramType.getName().substring(0, 1).toLowerCase() + paramType.getName().substring(1);
						paramsForFrame[i+1][0] = name;
						paramsForFrame[i+1][1] = paramType;
					}
					
					paramsForFrame[params.size()+1][0] = name2;
					paramsForFrame[params.size()+1][1] = paramType2;
				}
				else{
					paramsForFrame = new Object[params.size()+1][2];
					Class returnType = scFacade.getLinkedClass(notion, TraceTypes.DTO_MAPPING_NAME);
					paramsForFrame[0][0] = null;
					paramsForFrame[0][1] = returnType;
					
					for(int i=0; i < params.size()+1; i++){
						Class paramType = params.get(i);
						String name = paramType.getName().substring(0, 1).toLowerCase() + paramType.getName().substring(1);
						paramsForFrame[i+1][0] = name;
						paramsForFrame[i+1][1] = paramType;
					}
				}
			}
		}
		
		if(paramsForVoid[0][1] != null){
			return paramsForVoid;
		}
		else if(paramsForNotVoid[0][1] != null && paramsForNotVoid[1][1] != null){
			return paramsForNotVoid;
		}
		else if(paramsForNotVoidWithPrecondConcept[0][1] != null && paramsForNotVoidWithPrecondConcept[1][1] != null && paramsForNotVoidWithPrecondConcept[2][1] != null){
			return paramsForNotVoidWithPrecondConcept;
		}
		else if(paramsForFrame != null){
			return paramsForFrame;
		}
		return null;
	}
	
	private Class getRelatedListRowDTOForValueObject(Notion notion) {
		List<DomainElementRelationshipDTO> relations = ((NotionDTO)notion).getDomainElementRelationshipDTOList();
		for(DomainElementRelationshipDTO rel : relations){
			NotionDTO relatedNotion = (NotionDTO) rel.getSource();
			if(relatedNotion.equals(notion) || !relatedNotion.getType().equals(NotionTypesEnum.List_View.tag())){
				continue;
			}
			Class relatedDTOClass = scFacade.getLinkedClass((Notion)relatedNotion, TraceTypes.DTO_LIST_ROW_MAPPING_NAME);
			if(relatedDTOClass != null){
				return relatedDTOClass;
			}
		}
		return null;
	}
	
	private Class getRelatedMainConceptDTO(Notion notion) {
		NotionDTO mainConcept = (NotionDTO) scFacade.getMainConcept(notion);
		Class mainConceptDTOClass = scFacade.getLinkedClass((Notion)mainConcept, TraceTypes.DTO_MAPPING_NAME);
		
		return mainConceptDTOClass;
	}
	
	private List<Class> getRelatedDTOForFrame(Notion notion) {
		List<Class> relatedDTOList = new ArrayList<Class>();
		List<DomainElementRelationshipDTO> relations = ((NotionDTO)notion).getDomainElementRelationshipDTOList();
		for(DomainElementRelationshipDTO rel : relations){
			NotionDTO relatedNotion = (NotionDTO) rel.getTarget();
			if(relatedNotion.equals(notion) ||
				(!relatedNotion.getType().equals(NotionTypesEnum.Simple_View.tag()) &&
				!relatedNotion.getType().equals("")) ){
				continue;
			}
			
			Class relatedDTOClass = scFacade.getLinkedClass((Notion)relatedNotion, TraceTypes.DTO_MAPPING_NAME);
			if(relatedDTOClass == null){
				continue;
			}
			relatedDTOList.add(relatedDTOClass);
		}
		return relatedDTOList;
	}
	
	private List<OperationDTO> genHelperGetAllMethodSignature(Notion dataView, InterfaceDTO service) {
		List<OperationDTO> operations = new ArrayList<OperationDTO>();
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
							Class sharedDTO = scFacade.getLinkedClass(sharedConcept, TraceTypes.DTO_MAPPING_NAME);
							
							scFacade.createDependency((Interface)service, new NamedElement[]{sharedDTO});
							
							Class sharedEntity = scFacade.getLinkedClass(sharedConcept, TraceTypes.ENTITY_MAPPING_NAME);
							String conceptsList = sharedEntity.getName()+"s";
							String getAllOperationName = "getAll"+conceptsList;
							
							boolean operationExists = false;
							for(OperationDTO op : operations){
								String operationName = op.getName().substring(1, op.getName().lastIndexOf('('));
								if(operationName.equals(getAllOperationName)){
									operationExists = true;
									break;
								}
							}
							
							if(!operationExists){
								OperationDTO getAllConcepts = (OperationDTO) scFacade.createOperation(getAllOperationName, VisibilityKind.PUBLIC, new Object[][]{{null, "List<"+sharedDTO.getName()+">"}});
								
								eu.redseeds.scl.uml.classes.kernel.Stereotype stereotype = scFacade.getFacade().createUml$classes$kernel$Stereotype();
								stereotype.setName(HelperActionTypes.GET_ALL_CONCEPTS);
								((Operation)getAllConcepts).addStereotype(stereotype);
								
								operations.add(getAllConcepts);
							}
						}
					}
				}
			}
		}
		
		return operations;
	}
	
}
