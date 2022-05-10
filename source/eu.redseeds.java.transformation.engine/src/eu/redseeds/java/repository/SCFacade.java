package eu.redseeds.java.repository;

import java.util.ArrayList;
import java.util.List;
import eu.redseeds.common.SCProjectHelper;
import eu.redseeds.java.generator.utils.TraceTypes;
import eu.redseeds.sc.current.repository.SCProject;
import eu.redseeds.sc.current.repository.SCProjectContainer;
import eu.redseeds.scl.model.BusinessLayerFacade;
import eu.redseeds.scl.model.impl.rsl.rsldomainelements.notions.NotionDTOImpl;
import eu.redseeds.scl.model.impl.sdsl.ClassDTOImpl;
import eu.redseeds.scl.model.impl.sdsl.InterfaceDTOImpl;
import eu.redseeds.scl.model.rsl.rsldomainelements.domainelements.DomainElementRelationshipDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionSpecialisationDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionTypesEnum;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionsPackageDTO;
import eu.redseeds.scl.model.sclkernel.DetailedDesignModelDTO;
import eu.redseeds.scl.model.sclkernel.SoftwareCaseDTO;
import eu.redseeds.scl.model.sdsl.ClassDTO;
import eu.redseeds.scl.model.sdsl.InterfaceDTO;
import eu.redseeds.scl.model.sdsl.PackageDTO;
import eu.redseeds.scl.model.traceability.TraceabilityLinkDTO;
import eu.redseeds.scl.rsl.rsldomainelements.notions.Notion;
import eu.redseeds.scl.sclkernel.DetailedDesignModel;
import eu.redseeds.scl.sclkernel.IsAllocatedTo;
import eu.redseeds.scl.sclkernel.Stereotype;
import eu.redseeds.scl.uml.auxiliaryconstructs.models.Model;
import eu.redseeds.scl.uml.classes.dependencies.Dependency;
import eu.redseeds.scl.uml.classes.interfaces.Interface;
import eu.redseeds.scl.uml.classes.kernel.Class;
import eu.redseeds.scl.uml.classes.kernel.NamedElement;
import eu.redseeds.scl.uml.classes.kernel.Operation;
import eu.redseeds.scl.uml.classes.kernel.PackagableElement;
import eu.redseeds.scl.uml.classes.kernel.Parameter;
import eu.redseeds.scl.uml.classes.kernel.ParameterDirectionKind;
import eu.redseeds.scl.uml.classes.kernel.PrimitiveType;
import eu.redseeds.scl.uml.classes.kernel.Type;
import eu.redseeds.scl.uml.classes.kernel.VisibilityKind;
import eu.redseeds.scl.uml.classes.kernel.Package;

public class SCFacade {

	private BusinessLayerFacade bussinessFacade;
	private SoftwareCaseDTO mainCase;

	private SCFacade(SCProject proj) {
		bussinessFacade = proj.getBusinessLayerFacade();
		mainCase = proj.getMainCase();
	}
	
	public static SCFacade instance() {
		return new SCFacade(SCProjectContainer.instance().getSCProject(SCProjectHelper.getActiveProject()));
	}
	
	public BusinessLayerFacade getFacade() {
		return bussinessFacade;
	}
	
	public SoftwareCaseDTO getMainCase() {
		return mainCase;
	}
	
	/**
	 * 
	 * @param name package name
	 * @return package under Detailed Design
	 */
	public PackageDTO getPackage(String name) {
		DetailedDesignModelDTO designModel = getMainCase().getDetailedDesignModelDTO();
		for(PackageDTO p : designModel.getPackageDTOList()){
			if(p.getName().equals(name)){
				return p;
			}
			PackageDTO pack = getPackageRecursively(p, name);
			if(pack == null){
				continue;
			}
			else{
				return pack;
			}
		}
		return null;
	}
	
	private PackageDTO getPackageRecursively(PackageDTO pack, String name) {
		for(PackageDTO child : pack.getPackageDTOList()){
			if(child.getName().equals(name)){
				return child;
			}
			PackageDTO p = getPackageRecursively(child, name);
			if(p == null){
				continue;
			}
			else{
				return p;
			}
		}
		return null;
	}
	/**
	 * 
	 * @param pack element parent
	 * @param elemName element name
	 * @param instanceType element classifier e.g. interface, class or package
	 * @return element from parent package
	 */
	public NamedElement getElementFromPackage(PackageDTO pack, String elemName, java.lang.Class<?> instanceType) {
		if(instanceType.equals(Class.class)){
			for(ClassDTO umlClass : pack.getClassDTOList()){
				if(umlClass.getName().equals(elemName)){
					return (NamedElement) umlClass;
				}
			}
		}
		else if(instanceType.equals(Interface.class)){
			for(InterfaceDTO umlInterface : pack.getInterfaceDTOList()){
				if(umlInterface.getName().equals(elemName)){
					return (NamedElement) umlInterface;
				}
			}
		}
		else if(instanceType.equals(Package.class)){
			for(PackageDTO umlPackage : pack.getPackageDTOList()){
				if(umlPackage.getName().equals(elemName)){
					return (NamedElement) umlPackage;
				}
			}
		}
		return null;
	}
	
	public boolean checkForNotionsRecursively(NotionsPackageDTO notPack) {
		if(notPack.getNotionDTOList() == null || notPack.getNotionDTOList().isEmpty()){
			if(notPack.getNotionsPackageDTOList() == null || notPack.getNotionsPackageDTOList().isEmpty()){
				return false;
			}
			for(NotionsPackageDTO child : notPack.getNotionsPackageDTOList()){
				return checkForNotionsRecursively(child);
			}
		}
		return true;
	}
	/**
	 * Create mapping between notion and UML class
	 * @param notion
	 * @param umlClass
	 * @param mappingName stereotype name
	 */
	public void createMappingBetween(Notion notion, Class umlClass, String mappingName) {
		IsAllocatedTo alloc = getFacade().createIsAllocatedTo();
		umlClass.addAllocationToRSL(alloc);
		notion.addAllocationToUML(alloc);
		eu.redseeds.scl.uml.classes.kernel.Stereotype stereotype = getFacade().createUml$classes$kernel$Stereotype();
		stereotype.setName(mappingName);
		alloc.addStereotype(stereotype);
	}
	/**
	 * Create mapping between notion and UML interface
	 * @param notion
	 * @param interfaceElem
	 * @param mappingName stereotype name
	 */
	public void createMappingBetween(Notion notion, Interface interfaceElem, String mappingName) {
		IsAllocatedTo alloc = getFacade().createIsAllocatedTo();
		interfaceElem.addAllocationToRSL(alloc);
		notion.addAllocationToUML(alloc);
		eu.redseeds.scl.uml.classes.kernel.Stereotype stereotype = getFacade().createUml$classes$kernel$Stereotype();
		stereotype.setName(mappingName);
		alloc.addStereotype(stereotype);
	}
	/**
	 * Returns element mapped from notion
	 * @param notion
	 * @return UML class
	 */
	public Class getLinkedClass(Notion notion, String stereotypeName) {
		List<TraceabilityLinkDTO> traceLinks = ((NotionDTOImpl)notion).getTraceabilityLinkDTOList();
 		for(TraceabilityLinkDTO trace : traceLinks){
 			for( Stereotype st : ((IsAllocatedTo)trace).getStereotypeList()){
 				if(st.getName().equals(stereotypeName)){
 					Object target = trace.getTarget();
 		 			if(target != null && target instanceof Class){
 		 				return (Class)target;
 		 			}
 				}
 			}
 			
 		}
		return null;
	}
	/**
	 * Returns element mapped from notion
	 * @param notion
	 * @return UML interface
	 */
	public Interface getLinkedInterface(Notion notion, String stereotypeName) {
		List<TraceabilityLinkDTO> traceLinks = ((NotionDTOImpl)notion).getTraceabilityLinkDTOList();
 		for(TraceabilityLinkDTO trace : traceLinks){
 			for( Stereotype st : ((IsAllocatedTo)trace).getStereotypeList()){
 				if(st.getName().equals(stereotypeName)){
 					Object target = trace.getTarget();
 		 			if(target != null && target instanceof Interface){
 		 				return (Interface)target;
 		 			}
 				}
 			}
 			
 		}
		return null;
	}
	/**
	 * Return notion mapped from class
	 * @param UML class
	 * @return associated notion
	 */
	public Notion getLinkedNotion(ClassDTO umlClass, String stereotypeName) {
		List<TraceabilityLinkDTO> traceLinks = ((ClassDTOImpl)umlClass).getTraceabilityLinkDTOList();
 		for(TraceabilityLinkDTO trace : traceLinks){
 			for( Stereotype st : ((IsAllocatedTo)trace).getStereotypeList()){
 				if(st.getName().equals(stereotypeName)){
 					Object src = trace.getSource();
 		 			if(src != null && src instanceof Notion){
 		 				return (Notion)src;
 		 			}
 				}
 			}
 			
 		}
		return null;
	}
	/**
	 * Return notion mapped from interface
	 * @param UML interface
	 * @return associated notion
	 */
	public Notion getLinkedNotion(InterfaceDTO umlInterface, String stereotypeName) {
		List<TraceabilityLinkDTO> traceLinks = ((InterfaceDTOImpl)umlInterface).getTraceabilityLinkDTOList();
 		for(TraceabilityLinkDTO trace : traceLinks){
 			for( Stereotype st : ((IsAllocatedTo)trace).getStereotypeList()){
 				if(st.getName().equals(stereotypeName)){
 					Object src = trace.getSource();
 		 			if(src != null && src instanceof Notion){
 		 				return (Notion)src;
 		 			}
 				}
 			}
 			
 		}
		return null;
	}
	/**
	 * Create UML operation
	 * @param name operation name
	 * @param kind operation visibility
	 * @param parameters list of operation parameters descriptions as {parameterName, parameterType}.
	 * Parameter name equals to NULL indicates RETURN direction.
	 * @return operation
	 */
	public Operation createOperation(String name, VisibilityKind kind, Object[]... parameters) {
		Operation operation = getFacade().createOperation();
		operation.setName(name);
		operation.setVisibility(kind);
		for(int i=0; i < parameters.length; i++){
			String paramName = (String) parameters[i][0];
			Parameter param = getFacade().createUml$classes$kernel$Parameter();
			if(paramName == null){
				param.setDirection(ParameterDirectionKind.RETURN);
			}
			else{
				param.setName(paramName);
			}
			if(parameters[i][1] instanceof String){
				String paramType = (String) parameters[i][1];
				PrimitiveType type = getPrimitiveType(paramType);
				if(type == null){
					type = addPrimitiveType(paramType);
				}
				param.addType(type);
			}
			else if(parameters[i][1] instanceof Class){
				Class type = (Class) parameters[i][1];
				param.addType((Class)type);
			}
			else if(parameters[i][1] instanceof Interface){
				Interface type = (Interface) parameters[i][1];
				param.addType((Interface)type);
			}
			operation.addOwnedParameter(param);
		}
		return operation;
	}
	/**
	 * Create empty UML operation e.g void operation()
	 * @param name
	 * @param kind
	 * @return empty operation
	 */
	public Operation createOperation(String name, VisibilityKind kind) {
		Operation operation = getFacade().createOperation();
		operation.setName(name);
		operation.setVisibility(kind);
		return operation;
	}
	/**
	 * Create UML operation
	 * @param op operation
	 * @return operation based on parameter
	 */
	public Operation createOperation(Operation op) {
		Operation operation = getFacade().createOperation();
		String operationName = op.getName().substring(1, op.getName().lastIndexOf('('));
		operation.setName(operationName);
		operation.setVisibility(op.getVisibility());
		for(Parameter param : op.getOwnedParameterList()){
			operation.addOwnedParameter(param);
		}
		return operation;
	}
	/**
	 * Returns UML operation return parameter
	 * @param operation
	 * @return return parameter
	 */
	public Type getOperationReturnType(Operation operation) {
		for(Parameter param : operation.getOwnedParameterList()){
			if(param.getDirection() != null && param.getDirection().equals(ParameterDirectionKind.RETURN)){
				if(param.getTypeList() != null && param.getTypeList().size() == 1){
					return param.getTypeList().get(0);
				}
			}
		}
		return null;
	}
	/**
	 * Returns UML operation arguments types
	 * @param operation
	 * @return arguments types
	 */
	public List<Type> getOperationArgumentsTypes(Operation operation) {
		List<Type> argsTypes = new ArrayList<Type>();
		for(Parameter param : operation.getOwnedParameterList()){
			if(param.getDirection() == null || !param.getDirection().equals(ParameterDirectionKind.RETURN)){
				if(param.getTypeList() != null && param.getTypeList().size() == 1){
					argsTypes.add(param.getTypeList().get(0));
				}
			}
		}
		return argsTypes;
	}
	/**
	 * Returns UML operation arguments names
	 * @param operation
	 * @return arguments names
	 */
	public List<String> getOperationArgumentsNames(Operation operation) {
		List<String> argsNames = new ArrayList<String>();
		for(Parameter param : operation.getOwnedParameterList()){
			if(param.getDirection() == null || !param.getDirection().equals(ParameterDirectionKind.RETURN)){
				argsNames.add(param.getName());
			}
		}
		return argsNames;
	}
	/**
	 * Checking for List of non-primitive types returning from operation
	 * @param operation
	 * @return true if operation returns List of Objects
	 */
	public boolean isOperationReturnsObjectsList(Operation operation) {
		Type type = getOperationReturnType(operation);
		if(type != null){
			if(type instanceof PrimitiveType){
				String typeName = ((PrimitiveType)type).getName();
				if(typeName.startsWith("List<")){
					String objectName = typeName.substring(typeName.indexOf("<")+1, typeName.indexOf(">"));
					for(Class c : getFacade().getClassVertices()){
						if(c.getName().equals(objectName)){
							Notion concept = getLinkedNotion((ClassDTO)c, TraceTypes.ENTITY_MAPPING_NAME);
							Notion simpleview = getLinkedNotion((ClassDTO)c, TraceTypes.DTO_MAPPING_NAME);
							if(concept != null || simpleview != null)
								return true;
						}
					}
				}
			}
		}
		return false;
	}
	/**
	 * Checking for DTO type returning from operation
	 * @param operation
	 * @return true if operation returns DTO
	 */
	public boolean isOperationReturnsDTO(Operation operation) {
		Type type = getOperationReturnType(operation);
		if(type != null){
			if(type instanceof PrimitiveType){
				String typeName = ((PrimitiveType)type).getName();
				if(isDTOType(typeName)){
					return true;
				}
			}
		}
		return false;
	}
	/**
	 * Return Entity or DTO class by name
	 * @param name of class to search for
	 * @return Entity class or DTO class
	 */
	public Class getEntityOrDTOByType(String type) {
		for(Class c : getFacade().getClassVertices()){
			if(c.getName().equals(type)){
				return c;
			}
		}
		return null;
	}
	
	/**
	 * Create dependency
	 * @param client element
	 * @param suppliers element list
	 * @return dependency
	 */
	public Dependency createDependency(NamedElement client, NamedElement[] suppliers) {
		Dependency dep = getFacade().createDependency();
		dep.addClient(client);
		for(int i=0; i < suppliers.length; i++){
			dep.addSupplier(suppliers[i]);
		}
		return dep;
	}
	/**
	 * @param name primitive type name
	 * @return primitive type
	 */
	public PrimitiveType getPrimitiveType(String name) {
		DetailedDesignModelDTO designModel = getMainCase().getDetailedDesignModelDTO();
		Model model = ((DetailedDesignModel)designModel).getUmlModelList().get(0);
		for(PackagableElement elem : model.getPackagedElementList()){
			if(elem.getName().equals(name)){
				return (PrimitiveType) elem;
			}
		}
		return null;
	}
	/**
	 * @param name primitive type name
	 * @return created type
	 */
	public PrimitiveType addPrimitiveType(String name) {
		PrimitiveType dtoList = getFacade().createPrimitiveType();
		dtoList.setName(name);
		DetailedDesignModelDTO designModel = getMainCase().getDetailedDesignModelDTO();
		Model model = ((DetailedDesignModel)designModel).getUmlModelList().get(0);
		model.addPackagedElement(dtoList);
		return dtoList;
	}
	/**
	 * @param name type name
	 * @return true for int, float, String etc.
	 */
	public boolean isPrimitiveType(String name) {
		if(!isDTOType(name) && !isListOfDTOType(name)){
			return true;
		}
		return false;
	}
	/**
	 * @param name type name
	 * @return true for all DTOs
	 */
	public boolean isDTOType(String name) {
		if(isSimpleViewType(name) || isListViewType(name) || isDefaultSimpleViewType(name)){
			return true;
		}
		return false;
	}
	/**
	 * @param name type name
	 * @return true for List<DTO>
	 */
	public boolean isListOfDTOType(String name) {
		if(name.startsWith("List<")){
			String dtoName = name.substring(name.indexOf("<")+1, name.indexOf(">"));
			if(isSimpleViewType(dtoName) || isListViewType(dtoName) || isDefaultSimpleViewType(dtoName)){
				return true;
			}
		}
		return false;
	}
	/**
	 * @param name type name
	 * @return true if name refers to DTO derived from Simple View
	 */
	public boolean isSimpleViewType(String name) {
		Class dto = getEntityOrDTOByType(name);
		if(dto != null){
			Notion simpleView = getLinkedNotion((ClassDTO)dto, TraceTypes.DTO_MAPPING_NAME);
			if(simpleView != null && ((NotionDTO)simpleView).getType().equals(NotionTypesEnum.Simple_View.tag())){
				return true;
			}
		}
		return false;
	}
	/**
	 * @param name type name
	 * @return true if name refers to DTO derived from List View
	 */
	public boolean isListViewType(String name) {
		Class dto = getEntityOrDTOByType(name);
		if(dto != null){
			Notion listView = getLinkedNotion((ClassDTO)dto, TraceTypes.DTO_LIST_MAPPING_NAME);
			if(listView != null && ((NotionDTO)listView).getType().equals(NotionTypesEnum.List_View.tag())){
				return true;
			}
		}
		return false;
	}
	/**
	 * @param name type name
	 * @return true if name refers to DTO derived by default from Concept
	 */
	public boolean isDefaultSimpleViewType(String name) {
		Class dto = getEntityOrDTOByType(name);
		if(dto != null){
			Notion concept = getLinkedNotion((ClassDTO)dto, TraceTypes.DTO_MAPPING_NAME);
			if(concept != null && ((NotionDTO)concept).getType().equals("")){
				return true;
			}
		}
		return false;
	}
	/**
	 * @param dataView Simple View or List View
	 * @return main concept for data view
	 */
	public Notion getMainConcept(Notion dataView) {
		for(DomainElementRelationshipDTO rel : ((NotionDTO)dataView).getDomainElementRelationshipDTOList()){
			NotionDTO relatedNotion = (NotionDTO) rel.getTarget();
			if(relatedNotion.equals(dataView)){
				continue;
			}
			//search for Concept only
			if(relatedNotion.getType().equals("")){
				return (Notion) relatedNotion;
			}
		}
		return null;
	}
	/**
	 * @param mainConcept Main Concept for Data View containing attribute shared by Concept to found
	 * @param attribute Attribute for which Concept need to be found
	 * @param visitedNode Only for internal use! Should be set to NULL.
	 * @return Concept sharing same attribute as dataView
	 */
	public Notion searchConceptWithAttrSharedByDataView(NotionDTO mainConcept, NotionDTO attribute, NotionDTO visitedNode) {
		for(NotionDTO attr : mainConcept.getNotionDTOAttributeList()){
			if(attr.equals(attribute)){
				return null;
			}
		}

		for(DomainElementRelationshipDTO rel : mainConcept.getDomainElementRelationshipDTOList()){
			NotionDTO targetConcept = (NotionDTO) rel.getTarget();
			if(targetConcept.equals(mainConcept)){
				continue;
			}
			for(NotionDTO attr : targetConcept.getNotionDTOAttributeList()){
				if(attr.equals(attribute)){
					return (Notion) targetConcept;
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
				Notion concept = searchConceptWithAttrSharedByDataView(specialisedNotion, attribute, mainConcept);
				if(concept != null)
					return concept;
			}
			//[SRC]-----|>[TARGET] generalization
			NotionDTO generalNotion = specialization.getGeneralNotion();
			if(generalNotion != null && !generalNotion.equals(mainConcept)){
				if(visitedNode != null && visitedNode.equals(generalNotion)){
					continue;
				}
				Notion concept = searchConceptWithAttrSharedByDataView(generalNotion, attribute, mainConcept);
				if(concept != null)
					return concept;
			}
		}
		return null;
	}
	/**
	 * @param mainConcept Main Concept for Data View
	 * @param targetConcept Concept related to Main Concept
	 * @param visitedNode Only for internal use! Should be set to NULL.
	 * @return true if mainConcept and targetConcept is related as list
	 */
	public boolean relationBeetwenIsList(NotionDTO mainConcept, NotionDTO targetConcept, NotionDTO visitedNode) {
		for(DomainElementRelationshipDTO rel : mainConcept.getDomainElementRelationshipDTOList()){
			NotionDTO target = (NotionDTO) rel.getTarget();
			if(target.equals(mainConcept)){
				continue;
			}
			if(target.equals(targetConcept)){
				if(!rel.getTargetMultiplicity().equals("0") && !rel.getTargetMultiplicity().equals("1"))
					return true;
			}
		}

		for(NotionSpecialisationDTO specialization : mainConcept.getNotionSpecialisationDTOList()){
			//[SRC]<|-----[TARGET] generalization
			NotionDTO specialisedNotion = specialization.getSpecialisedNotion();
			if(specialisedNotion != null && !specialisedNotion.equals(mainConcept)){
				if(visitedNode != null && visitedNode.equals(specialisedNotion)){
					continue;
				}
				boolean res = relationBeetwenIsList(specialisedNotion, targetConcept, mainConcept);
				if(res)
					return res;
			}
			//[SRC]-----|>[TARGET] generalization
			NotionDTO generalNotion = specialization.getGeneralNotion();
			if(generalNotion != null && !generalNotion.equals(mainConcept)){
				if(visitedNode != null && visitedNode.equals(generalNotion)){
					continue;
				}
				boolean res = relationBeetwenIsList(generalNotion, targetConcept, mainConcept);
				if(res)
					return res;
			}
		}
		return false;
	}
}
