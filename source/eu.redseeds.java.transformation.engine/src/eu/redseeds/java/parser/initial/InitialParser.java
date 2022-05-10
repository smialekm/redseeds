package eu.redseeds.java.parser.initial;

import java.io.IOException;
import java.util.List;

import org.xml.sax.SAXException;

import de.uni_koblenz.jgralab.Vertex;
import eu.redseeds.java.repository.SCFacade;
import eu.redseeds.scl.model.sdsl.ClassDTO;
import eu.redseeds.scl.model.sdsl.InterfaceDTO;
import eu.redseeds.scl.model.sdsl.PackageDTO;
import eu.redseeds.scl.sclkernel.AllocationToRSLLinksToAllocationTarget;
import eu.redseeds.scl.sclkernel.AllocationToUMLLinksToAllocationSource;
import eu.redseeds.scl.sclkernel.DetailedDesignModel;
import eu.redseeds.scl.sclkernel.IsAllocatedTo;
import eu.redseeds.scl.sclkernel.Stereotype;
import eu.redseeds.scl.sclkernel.StereotypeLinksToRelationship;
import eu.redseeds.scl.uml.auxiliaryconstructs.models.Model;
import eu.redseeds.scl.uml.classes.dependencies.ClientDependencyLinksToClient;
import eu.redseeds.scl.uml.classes.dependencies.Dependency;
import eu.redseeds.scl.uml.classes.dependencies.SupplierDependencyLinksToSupplier;
import eu.redseeds.scl.uml.classes.interfaces.Interface;
import eu.redseeds.scl.uml.classes.interfaces.InterfaceRealization;
import eu.redseeds.scl.uml.classes.kernel.Association;
import eu.redseeds.scl.uml.classes.kernel.Class;
import eu.redseeds.scl.uml.classes.kernel.ClassContainsOwnedAttribute;
import eu.redseeds.scl.uml.classes.kernel.Comment;
import eu.redseeds.scl.uml.classes.kernel.Element;
import eu.redseeds.scl.uml.classes.kernel.GeneralLinksToSpecialization;
import eu.redseeds.scl.uml.classes.kernel.Generalization;
import eu.redseeds.scl.uml.classes.kernel.GeneralizationIsPartOfSpecific;
import eu.redseeds.scl.uml.classes.kernel.LiteralInteger;
import eu.redseeds.scl.uml.classes.kernel.LiteralUnlimitedNatural;
import eu.redseeds.scl.uml.classes.kernel.LowerValueIsPartOfOwningLower;
import eu.redseeds.scl.uml.classes.kernel.MemberEndLinksToAssociation;
import eu.redseeds.scl.uml.classes.kernel.Operation;
import eu.redseeds.scl.uml.classes.kernel.OwnedCommentIsPartOfOwningElement;
import eu.redseeds.scl.uml.classes.kernel.OwnedParameterIsPartOfOperation;
import eu.redseeds.scl.uml.classes.kernel.Package;
import eu.redseeds.scl.uml.classes.kernel.PackagedElementIsPartOfOwningPackage;
import eu.redseeds.scl.uml.classes.kernel.Parameter;
import eu.redseeds.scl.uml.classes.kernel.PrimitiveType;
import eu.redseeds.scl.uml.classes.kernel.Property;
import eu.redseeds.scl.uml.classes.kernel.Type;
import eu.redseeds.scl.uml.classes.kernel.TypeLinksToTyped;
import eu.redseeds.scl.uml.classes.kernel.UpperValueIsPartOfOwningUpper;
import eu.remics.recovery.model.TransformationHelper;

public class InitialParser {
	
	private SCFacade scFacade;

	public InitialParser() {
		scFacade = SCFacade.instance();
	}
	
	public void parse() {
		deleteGeneratedModel();
		genBasicStructure();
		genPrimitiveTypes();
		genImports();
	}
	
	public void deleteGeneratedModel() {
		String cache = TransformationHelper.backupDuplicateUseCaseStructure();
		while(modelNotEmpty()){
			deleteSCLGraphEdges();
			//monitor.worked(40);
			deleteSCLGraphVertices();
		}
		/*Job job = new Job("Deleting detail design model") {
			
			@Override
			protected IStatus run(IProgressMonitor monitor) {
				monitor.beginTask("Deleting detail design model", 100);
				
				
				if(monitor.isCanceled())
					return Status.CANCEL_STATUS;
				return Status.OK_STATUS;
			}
		};
		job.schedule();*/
		try {
			TransformationHelper.restoreDuplicatedUseCaseStructure(cache);
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private boolean modelNotEmpty() {
		if(scFacade.getFacade().getAllocationToRSLLinksToAllocationTargetEdges().iterator().hasNext() 
			|| scFacade.getFacade().getAllocationToUMLLinksToAllocationSourceEdges().iterator().hasNext()
			|| scFacade.getFacade().getTypeLinksToTypedEdges().iterator().hasNext()
			|| scFacade.getFacade().getOwnedParameterIsPartOfOperationEdges().iterator().hasNext()
			|| scFacade.getFacade().getOwnedCommentIsPartOfOwningElementEdges().iterator().hasNext()
			|| scFacade.getFacade().getClassContainsOwnedAttributeEdges().iterator().hasNext()
			|| scFacade.getFacade().getStereotypeLinksToRelationshipEdges().iterator().hasNext()
			|| scFacade.getFacade().getGeneralLinksToSpecializationEdges().iterator().hasNext()
			|| scFacade.getFacade().getGeneralizationIsPartOfSpecificEdges().iterator().hasNext()
			|| scFacade.getFacade().getPackagedElementIsPartOfOwningPackageEdges().iterator().hasNext()
			|| scFacade.getFacade().getMemberEndLinksToAssociationEdges().iterator().hasNext()
			|| scFacade.getFacade().getLowerValueIsPartOfOwningLowerEdges().iterator().hasNext()
			|| scFacade.getFacade().getUpperValueIsPartOfOwningUpperEdges().iterator().hasNext()
			|| scFacade.getFacade().getClientDependencyLinksToClientEdges().iterator().hasNext()
			|| scFacade.getFacade().getSupplierDependencyLinksToSupplierEdges().iterator().hasNext()
			|| scFacade.getFacade().getIsAllocatedToVertices().iterator().hasNext()
			|| scFacade.getFacade().getDependencyVertices().iterator().hasNext()
			|| scFacade.getFacade().getGeneralizationVertices().iterator().hasNext()
			|| scFacade.getFacade().getAssociationVertices().iterator().hasNext()
			|| scFacade.getFacade().getLiteralIntegerVertices().iterator().hasNext()
			|| scFacade.getFacade().getLiteralUnlimitedNaturalVertices().iterator().hasNext()
			|| scFacade.getFacade().getUml$classes$kernel$StereotypeVertices().iterator().hasNext()
			|| scFacade.getFacade().getUml$classes$kernel$TypeVertices().iterator().hasNext()
			|| scFacade.getFacade().getUml$classes$kernel$CommentVertices().iterator().hasNext()
			|| scFacade.getFacade().getUml$classes$kernel$ParameterVertices().iterator().hasNext()
			|| scFacade.getFacade().getOperationVertices().iterator().hasNext()
			|| scFacade.getFacade().getPropertyVertices().iterator().hasNext()
			|| scFacade.getFacade().getClassVertices().iterator().hasNext()
			|| scFacade.getFacade().getInterfaceVertices().iterator().hasNext()
			|| scFacade.getFacade().getUml$classes$kernel$ElementVertices().iterator().hasNext()
			|| scFacade.getFacade().getUml$classes$kernel$PackageVertices().iterator().hasNext()
			|| scFacade.getFacade().getInterfaceRealizationVertices().iterator().hasNext()
			/*|| scFacade.getFacade().getUml$auxiliaryconstructs$models$ModelVertices().iterator().hasNext()*/){
			
			return true;
		}
		return false;
	}
	
	private void deleteSCLGraphEdges() {
		for(AllocationToRSLLinksToAllocationTarget obj : scFacade.getFacade().getAllocationToRSLLinksToAllocationTargetEdges()){
			scFacade.getFacade().deleteEdge(obj);
		}
		for(AllocationToUMLLinksToAllocationSource obj : scFacade.getFacade().getAllocationToUMLLinksToAllocationSourceEdges()){
			scFacade.getFacade().deleteEdge(obj);
		}
		for(TypeLinksToTyped obj : scFacade.getFacade().getTypeLinksToTypedEdges()){
			scFacade.getFacade().deleteEdge(obj);
		}
		for(OwnedParameterIsPartOfOperation obj : scFacade.getFacade().getOwnedParameterIsPartOfOperationEdges()){
			scFacade.getFacade().deleteEdge(obj);
		}
		for(OwnedCommentIsPartOfOwningElement obj : scFacade.getFacade().getOwnedCommentIsPartOfOwningElementEdges()){
			scFacade.getFacade().deleteEdge(obj);
		}
		for(ClassContainsOwnedAttribute obj : scFacade.getFacade().getClassContainsOwnedAttributeEdges()){
			scFacade.getFacade().deleteEdge(obj);
		}
		for(StereotypeLinksToRelationship obj : scFacade.getFacade().getStereotypeLinksToRelationshipEdges()){
			scFacade.getFacade().deleteEdge(obj);
		}
		for(GeneralLinksToSpecialization obj : scFacade.getFacade().getGeneralLinksToSpecializationEdges()){
			scFacade.getFacade().deleteEdge(obj);
		}
		for(GeneralizationIsPartOfSpecific obj : scFacade.getFacade().getGeneralizationIsPartOfSpecificEdges()){
			scFacade.getFacade().deleteEdge(obj);
		}
		for(PackagedElementIsPartOfOwningPackage obj : scFacade.getFacade().getPackagedElementIsPartOfOwningPackageEdges()){
			scFacade.getFacade().deleteEdge(obj);
		}
		for(MemberEndLinksToAssociation obj : scFacade.getFacade().getMemberEndLinksToAssociationEdges()){
			scFacade.getFacade().deleteEdge(obj);
		}
		for(LowerValueIsPartOfOwningLower obj : scFacade.getFacade().getLowerValueIsPartOfOwningLowerEdges()){
			scFacade.getFacade().deleteEdge(obj);
		}
		for(UpperValueIsPartOfOwningUpper obj : scFacade.getFacade().getUpperValueIsPartOfOwningUpperEdges()){
			scFacade.getFacade().deleteEdge(obj);
		}
		for(ClientDependencyLinksToClient obj : scFacade.getFacade().getClientDependencyLinksToClientEdges()){
			scFacade.getFacade().deleteEdge(obj);
		}
		for(SupplierDependencyLinksToSupplier obj : scFacade.getFacade().getSupplierDependencyLinksToSupplierEdges()){
			scFacade.getFacade().deleteEdge(obj);
		}
	}
	
	private void deleteSCLGraphVertices() {
		for(IsAllocatedTo alloc : scFacade.getFacade().getIsAllocatedToVertices()){
			for(Stereotype st : alloc.getStereotypeList()){
				scFacade.getFacade().deleteVertex(st);
			}
			scFacade.getFacade().deleteVertex(alloc);
		}
		for(Dependency dep : scFacade.getFacade().getDependencyVertices()){
			scFacade.getFacade().deleteVertex(dep);
		}
		for(Generalization general : scFacade.getFacade().getGeneralizationVertices()){
			scFacade.getFacade().deleteVertex(general);
		}
		for(Association assoc : scFacade.getFacade().getAssociationVertices()){
			scFacade.getFacade().deleteVertex(assoc);
		}
		for(LiteralInteger litint : scFacade.getFacade().getLiteralIntegerVertices()){
			scFacade.getFacade().deleteVertex(litint);
		}
		for(LiteralUnlimitedNatural litnat : scFacade.getFacade().getLiteralUnlimitedNaturalVertices()){
			scFacade.getFacade().deleteVertex(litnat);
		}
		for(eu.redseeds.scl.uml.classes.kernel.Stereotype obj : scFacade.getFacade().getUml$classes$kernel$StereotypeVertices()){
			scFacade.getFacade().deleteVertex(obj);
		}
		for(Type obj : scFacade.getFacade().getUml$classes$kernel$TypeVertices()){
			scFacade.getFacade().deleteVertex(obj);
		}
		for(Comment obj : scFacade.getFacade().getUml$classes$kernel$CommentVertices()){
			scFacade.getFacade().deleteVertex(obj);
		}
		for(Parameter obj : scFacade.getFacade().getUml$classes$kernel$ParameterVertices()) {
			scFacade.getFacade().deleteVertex(obj);
		}
		for(Operation o : scFacade.getFacade().getOperationVertices()){
			scFacade.getFacade().deleteVertex(o);
		}
		for(Property p : scFacade.getFacade().getPropertyVertices()){
			scFacade.getFacade().deleteVertex(p);
		}
		for(Class c : scFacade.getFacade().getClassVertices()){
			scFacade.getFacade().deleteVertex(c);
		}
		for(Interface i : scFacade.getFacade().getInterfaceVertices()){
			scFacade.getFacade().deleteVertex(i);
		}
		for(Element obj : scFacade.getFacade().getUml$classes$kernel$ElementVertices()){
			scFacade.getFacade().deleteVertex(obj);
		}
		for(Package obj : scFacade.getFacade().getUml$classes$kernel$PackageVertices()){
			scFacade.getFacade().deleteVertex(obj);
		}
		for(InterfaceRealization obj : scFacade.getFacade().getInterfaceRealizationVertices()){
			scFacade.getFacade().deleteVertex(obj);
		}
		/*for(Model obj : scFacade.getFacade().getUml$auxiliaryconstructs$models$ModelVertices()){
			scFacade.getFacade().deleteVertex(obj);
		}*/
	}
	
	private void deleteElements(PackageDTO pack) {
		for(PackageDTO p : pack.getPackageDTOList()){
			deleteElements(p);
			p.delete();
			scFacade.getFacade().deleteVertex((Vertex) p);
		}
		/*for(ClassDTO c : pack.getClassDTOList()){
			for(Property prop : ((Class)c).getOwnedAttributeList()){
				prop.delete();
			}
			for(OperationDTO op : c.getOperationDTOList()){
				for(Parameter param : ((Operation)op).getOwnedParameterList()){
					param.delete();
				}
				for(Comment comment : ((Operation)op).getOwnedCommentList()){
					comment.delete();
				}
				((Operation)op).delete();
			}
			((Class)c).delete();
		}
		for(InterfaceDTO i : pack.getInterfaceDTOList()){
			for(OperationDTO op : i.getOperationDTOList()){
				for(Parameter param : ((Operation)op).getOwnedParameterList()){
					param.delete();
				}
				for(Comment comment : ((Operation)op).getOwnedCommentList()){
					comment.delete();
				}
				((Operation)op).delete();
			}
			((Interface)i).delete();
		}
		for(ActorDTO a : pack.getActorDTOList()){
			((Actor)a).delete();
		}
		for(ComponentDTO com : pack.getComponentDTOList()){
			((Component)com).delete();
		}*/
	}
	
	private void genBasicStructure() {
		PackageDTO app = (PackageDTO) scFacade.getFacade().createUml$classes$kernel$Package();
		PackageDTO presenter = (PackageDTO) scFacade.getFacade().createUml$classes$kernel$Package();
		PackageDTO view = (PackageDTO) scFacade.getFacade().createUml$classes$kernel$Package();
		PackageDTO model = (PackageDTO) scFacade.getFacade().createUml$classes$kernel$Package();
		//PackageDTO modelImpl = (PackageDTO) scFacade.getFacade().createUml$classes$kernel$Package();
		PackageDTO dto = (PackageDTO) scFacade.getFacade().createUml$classes$kernel$Package();
		PackageDTO dao = (PackageDTO) scFacade.getFacade().createUml$classes$kernel$Package();
		PackageDTO jpaDao = (PackageDTO) scFacade.getFacade().createUml$classes$kernel$Package();
		PackageDTO springDao = (PackageDTO) scFacade.getFacade().createUml$classes$kernel$Package();
		PackageDTO eclipseLinkDao = (PackageDTO) scFacade.getFacade().createUml$classes$kernel$Package();
		PackageDTO services = (PackageDTO) scFacade.getFacade().createUml$classes$kernel$Package();
		PackageDTO serviceImpl = (PackageDTO) scFacade.getFacade().createUml$classes$kernel$Package();
		PackageDTO serviceImplHibernate = (PackageDTO) scFacade.getFacade().createUml$classes$kernel$Package();
		PackageDTO serviceImplSpring = (PackageDTO) scFacade.getFacade().createUml$classes$kernel$Package();
		
		app.setName("App");
		presenter.setName("Presenter");
		view.setName("View");
		model.setName("Model");
		//modelImpl.setName("impl");
		//model.addPackage(modelImpl);
		dto.setName("DTO");
		dao.setName("DAO");
		jpaDao.setName("jpa");
		springDao.setName("spring");
		eclipseLinkDao.setName("eclipse link");
		dao.addPackage(jpaDao);
		dao.addPackage(springDao);
		dao.addPackage(eclipseLinkDao);
		services.setName("Services");
		serviceImpl.setName("impl");
		services.addPackage(serviceImpl);
		serviceImplHibernate.setName("hibernate");
		serviceImpl.addPackage(serviceImplHibernate);
		serviceImplSpring.setName("spring");
		serviceImpl.addPackage(serviceImplSpring);
		
		app.addPackage(presenter);
		app.addPackage(view);
		app.addPackage(model);
		app.addPackage(dto);
		app.addPackage(dao);
		app.addPackage(services);
		scFacade.getMainCase().getDetailedDesignModelDTO().addPackage(app);
	}
	
	private void genPrimitiveTypes() {
		PrimitiveType stringType = scFacade.getFacade().createPrimitiveType();
		stringType.setName("String");
		PrimitiveType booleanType = scFacade.getFacade().createPrimitiveType();
		booleanType.setName("boolean");
		PrimitiveType objectType = scFacade.getFacade().createPrimitiveType();
		objectType.setName("Object");
		PrimitiveType intType = scFacade.getFacade().createPrimitiveType();
		intType.setName("int");
		PrimitiveType longType = scFacade.getFacade().createPrimitiveType();
		longType.setName("long");
		PrimitiveType floatType = scFacade.getFacade().createPrimitiveType();
		floatType.setName("float");
		PrimitiveType intArrayType = scFacade.getFacade().createPrimitiveType();
		intArrayType.setName("int[]");
		PrimitiveType collectionType = scFacade.getFacade().createPrimitiveType();
		collectionType.setName("Collection<?>");
		PrimitiveType listType = scFacade.getFacade().createPrimitiveType();
		listType.setName("List<?>");
		PrimitiveType constructorType = scFacade.getFacade().createPrimitiveType();
		constructorType.setName("");
		
		Model detDesignModel = null;
    	for (DetailedDesignModel m : scFacade.getFacade().getDetailedDesignModelVertices()) {
    		List<? extends Model> modelList = m.getUmlModelList();
    		detDesignModel = modelList.get(0);
    	}
		detDesignModel.addPackagedElement(stringType);
		detDesignModel.addPackagedElement(booleanType);
		detDesignModel.addPackagedElement(objectType);
		detDesignModel.addPackagedElement(intType);
		detDesignModel.addPackagedElement(longType);
		detDesignModel.addPackagedElement(floatType);
		detDesignModel.addPackagedElement(intArrayType);
		detDesignModel.addPackagedElement(collectionType);
		detDesignModel.addPackagedElement(listType);
		detDesignModel.addPackagedElement(constructorType);
	}
	
	private void genImports() {
		genJavaUtilImports();
		genPerfectJPatternImports();
		genJavaxJeeImports();
		genSpringImports();
	}
	
	private void genJavaUtilImports() {
		PackageDTO java = (PackageDTO) scFacade.getFacade().createUml$classes$kernel$Package();
		java.setName("java");
		PackageDTO util = (PackageDTO) scFacade.getFacade().createUml$classes$kernel$Package();
		util.setName("util");
		PackageDTO sql = (PackageDTO) scFacade.getFacade().createUml$classes$kernel$Package();
		sql.setName("sql");
		PackageDTO lang = (PackageDTO) scFacade.getFacade().createUml$classes$kernel$Package();
		lang.setName("lang");
		PackageDTO text = (PackageDTO) scFacade.getFacade().createUml$classes$kernel$Package();
		text.setName("text");
		PackageDTO star = (PackageDTO) scFacade.getFacade().createUml$classes$kernel$Package();
		star.setName("*");
		
		InterfaceDTO collection = (InterfaceDTO) scFacade.getFacade().createInterface();
		collection.setName("Collection");
		InterfaceDTO list = (InterfaceDTO) scFacade.getFacade().createInterface();
		list.setName("List");
		ClassDTO arrayList = (ClassDTO) scFacade.getFacade().createClass();
		arrayList.setName("ArrayList");
		ClassDTO date = (ClassDTO) scFacade.getFacade().createClass();
		date.setName("Date");
		ClassDTO stack = (ClassDTO) scFacade.getFacade().createClass();
		stack.setName("Stack");
		ClassDTO timer = (ClassDTO) scFacade.getFacade().createClass();
		timer.setName("Timer");
		ClassDTO properties = (ClassDTO) scFacade.getFacade().createClass();
		properties.setName("Properties");
		util.addInterface(collection);
		util.addInterface(list);
		util.addClass(arrayList);
		util.addClass(date);
		util.addClass(stack);
		util.addClass(timer);
		util.addClass(properties);
		util.addPackage(star);
		
		InterfaceDTO connection = (InterfaceDTO) scFacade.getFacade().createInterface();
		connection.setName("Connection");
		InterfaceDTO statement = (InterfaceDTO) scFacade.getFacade().createInterface();
		statement.setName("Statement");
		ClassDTO driverManager = (ClassDTO) scFacade.getFacade().createClass();
		driverManager.setName("DriverManager");
		ClassDTO sqlException = (ClassDTO) scFacade.getFacade().createClass();
		sqlException.setName("SQLException");
		ClassDTO resultSet = (ClassDTO) scFacade.getFacade().createClass();
		resultSet.setName("ResultSet");
		sql.addInterface(connection);
		sql.addInterface(statement);
		sql.addClass(driverManager);
		sql.addClass(sqlException);
		sql.addClass(resultSet);
		
		PackageDTO reflect = (PackageDTO) scFacade.getFacade().createUml$classes$kernel$Package();
		reflect.setName("reflect");
		ClassDTO constructor = (ClassDTO) scFacade.getFacade().createClass();
		constructor.setName("Constructor");
		ClassDTO field = (ClassDTO) scFacade.getFacade().createClass();
		field.setName("Field");
		ClassDTO invocationTargetException = (ClassDTO) scFacade.getFacade().createClass();
		invocationTargetException.setName("InvocationTargetException");
		reflect.addClass(constructor);
		reflect.addClass(field);
		reflect.addClass(invocationTargetException);
		lang.addPackage(reflect);
		
		ClassDTO simpleDateFormat = (ClassDTO) scFacade.getFacade().createClass();
		simpleDateFormat.setName("SimpleDateFormat");
		text.addClass(simpleDateFormat);
		text.addPackage(star);
		
		java.addPackage(util);
		java.addPackage(sql);
		java.addPackage(lang);
		java.addPackage(text);
		scFacade.getMainCase().getDetailedDesignModelDTO().addPackage(java);
	}
	
	private void genPerfectJPatternImports() {
		PackageDTO org = (PackageDTO) scFacade.getFacade().createUml$classes$kernel$Package();
		org.setName("org");
		PackageDTO perfectjpattern = (PackageDTO) scFacade.getFacade().createUml$classes$kernel$Package();
		perfectjpattern.setName("perfectjpattern");
		PackageDTO jee = (PackageDTO) scFacade.getFacade().createUml$classes$kernel$Package();
		jee.setName("jee");
		PackageDTO api = (PackageDTO) scFacade.getFacade().createUml$classes$kernel$Package();
		api.setName("api");
		PackageDTO business = (PackageDTO) scFacade.getFacade().createUml$classes$kernel$Package();
		business.setName("business");
		InterfaceDTO star = (InterfaceDTO) scFacade.getFacade().createInterface();
		star.setName("*");
		
		//import org.perfectjpattern.jee.api.business.servicelocator.*;
		/*PackageDTO servicelocator = (PackageDTO) scFacade.getFacade().createUml$classes$kernel$Package();
		servicelocator.setName("servicelocator");
		servicelocator.addInterface(star);
		business.addPackage(servicelocator);
		api.addPackage(business);
		jee.addPackage(api);*/
		
		//import org.perfectjpattern.jee.business.servicelocator.*;
		//jee.addPackage(business);
		
		//import org.perfectjpattern.jee.api.integration.dao.IGenericDao;
		InterfaceDTO iGenericDao = (InterfaceDTO) scFacade.getFacade().createInterface();
		iGenericDao.setName("IGenericDao");
		PackageDTO integration = (PackageDTO) scFacade.getFacade().createUml$classes$kernel$Package();
		integration.setName("integration");
		PackageDTO dao = (PackageDTO) scFacade.getFacade().createUml$classes$kernel$Package();
		dao.setName("dao");
		dao.addInterface(iGenericDao);
		integration.addPackage(dao);
		api.addPackage(integration);
		jee.addPackage(api);
		perfectjpattern.addPackage(jee);
		org.addPackage(perfectjpattern);
		
		//import org.perfectjpattern.jee.integration.dao.HibernateDaoFactory;
		ClassDTO hibernateDaoFactory = (ClassDTO) scFacade.getFacade().createClass();
		hibernateDaoFactory.setName("HibernateDaoFactory");
		dao.addClass(hibernateDaoFactory);
		jee.addPackage(integration);
		
		//import org.slf4j.*;
		/*PackageDTO slf4j = (PackageDTO) scFacade.getFacade().createUml$classes$kernel$Package();
		slf4j.setName("slf4j");
		//slf4j.addPackage(star);
		slf4j.addInterface(star);
		org.addPackage(slf4j);*/
		
		scFacade.getMainCase().getDetailedDesignModelDTO().addPackage(org);
	}
	
	private void genJavaxJeeImports() {
		PackageDTO javax = (PackageDTO) scFacade.getFacade().createUml$classes$kernel$Package();
		javax.setName("javax");
		PackageDTO ejb = (PackageDTO) scFacade.getFacade().createUml$classes$kernel$Package();
		ejb.setName("ejb");
		PackageDTO persistence = (PackageDTO) scFacade.getFacade().createUml$classes$kernel$Package();
		persistence.setName("persistence");
		PackageDTO star = (PackageDTO) scFacade.getFacade().createUml$classes$kernel$Package();
		star.setName("*");
		
		//import javax.ejb.*;
		ejb.addPackage(star);
		javax.addPackage(ejb);
		
		//import javax.persistence.*;
		persistence.addPackage(star);
		javax.addPackage(persistence);
		scFacade.getMainCase().getDetailedDesignModelDTO().addPackage(javax);
	}
	
	private void genSpringImports() {
		PackageDTO org = (PackageDTO) scFacade.getPackage("org");
		PackageDTO springframework = (PackageDTO) scFacade.getFacade().createUml$classes$kernel$Package();
		springframework.setName("springframework");
		PackageDTO context = (PackageDTO) scFacade.getFacade().createUml$classes$kernel$Package();
		context.setName("context");
		PackageDTO support = (PackageDTO) scFacade.getFacade().createUml$classes$kernel$Package();
		support.setName("support");
		PackageDTO transaction = (PackageDTO) scFacade.getFacade().createUml$classes$kernel$Package();
		transaction.setName("transaction");
		PackageDTO annotation = (PackageDTO) scFacade.getFacade().createUml$classes$kernel$Package();
		annotation.setName("annotation");
		PackageDTO star = (PackageDTO) scFacade.getFacade().createUml$classes$kernel$Package();
		star.setName("*");
		
		//import org.springframework.context.*;
		context.addPackage(star);
		
		//import org.springframework.context.support.*;
		support.addPackage(star);
		context.addPackage(support);
		springframework.addPackage(context);
		
		//import org.springframework.transaction.*;
		transaction.addPackage(star);
		
		//import org.springframework.transaction.annotation.*;
		annotation.addPackage(star);
		transaction.addPackage(annotation);
		
		//import org.springframework.transaction.support.*;
		support.addPackage(star);
		transaction.addPackage(support);
		springframework.addPackage(transaction);
		org.addPackage(springframework);
		
	}
	
}
