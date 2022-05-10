package eu.redseeds.java.generator.dao;

import java.util.List;

import eu.redseeds.java.generator.utils.GeneratorUtils;
import eu.redseeds.java.generator.utils.TraceTypes;
import eu.redseeds.java.repository.SCFacade;
import eu.redseeds.scl.model.sdsl.ClassDTO;
import eu.redseeds.scl.model.sdsl.InterfaceDTO;
import eu.redseeds.scl.model.sdsl.OperationDTO;
import eu.redseeds.scl.model.sdsl.PackageDTO;
import eu.redseeds.scl.rsl.rsldomainelements.notions.Notion;
import eu.redseeds.scl.uml.classes.interfaces.Interface;
import eu.redseeds.scl.uml.classes.kernel.Class;
import eu.redseeds.scl.uml.classes.kernel.Generalization;
import eu.redseeds.scl.uml.classes.kernel.LiteralString;
import eu.redseeds.scl.uml.classes.kernel.NamedElement;
import eu.redseeds.scl.uml.classes.kernel.Operation;
import eu.redseeds.scl.uml.classes.kernel.Package;
import eu.redseeds.scl.uml.classes.kernel.Property;
import eu.redseeds.scl.uml.classes.kernel.VisibilityKind;

public class SpringDAOGenerator {
	
	private SCFacade scFacade;
	private GeneratorUtils genUtils;
	
	public SpringDAOGenerator() {
		scFacade = SCFacade.instance();
		genUtils = new GeneratorUtils();
	}
	
	public void genDAO(InterfaceDTO entityDaoInterface, InterfaceDTO genericDaoInterface, ClassDTO entity) {
		Generalization generalization = scFacade.getFacade().createGeneralization();
		generalization.addGeneral((Interface)genericDaoInterface);
		generalization.addSpecific((Interface)entityDaoInterface);
		
		//import org.perfectjpattern.jee.api.integration.dao.IGenericDao;
		PackageDTO orgPack = scFacade.getPackage("org");
		PackageDTO perfectjpatternPack = (PackageDTO) scFacade.getElementFromPackage(orgPack, "perfectjpattern", Package.class);
		PackageDTO jeePack = (PackageDTO) scFacade.getElementFromPackage(perfectjpatternPack, "jee", Package.class);
		PackageDTO apiPack = (PackageDTO) scFacade.getElementFromPackage(jeePack, "api", Package.class);
		PackageDTO integrationPack = (PackageDTO) scFacade.getElementFromPackage(apiPack, "integration", Package.class);
		PackageDTO daoPack = (PackageDTO) scFacade.getElementFromPackage(integrationPack, "dao", Package.class);
		InterfaceDTO iGenericDao = (InterfaceDTO) scFacade.getElementFromPackage(daoPack, "IGenericDao", Interface.class);
		
		scFacade.createDependency((Interface)entityDaoInterface, new NamedElement[]{(Class)entity, (Interface)iGenericDao});
		
		PackageDTO dao = scFacade.getPackage("DAO");
		PackageDTO springPack = (PackageDTO) scFacade.getElementFromPackage(dao, "spring", Package.class);
		springPack.addInterface(entityDaoInterface);
	}
	
	public void genDaoFactory(List<Notion> notions) {
		Class daoFactory = scFacade.getFacade().createClass();
		daoFactory.setName("DaoFactory");
		
		//import org.perfectjpattern.jee.integration.dao.HibernateDaoFactory;
		PackageDTO orgPack = scFacade.getPackage("org");
		PackageDTO perfectjpatternPack = (PackageDTO) scFacade.getElementFromPackage(orgPack, "perfectjpattern", Package.class);
		PackageDTO jeePack = (PackageDTO) scFacade.getElementFromPackage(perfectjpatternPack, "jee", Package.class);
		PackageDTO integrationPack = (PackageDTO) scFacade.getElementFromPackage(jeePack, "integration", Package.class);
		PackageDTO daoPack = (PackageDTO) scFacade.getElementFromPackage(integrationPack, "dao", Package.class);
		Class hibernateDaoFactory = (Class) scFacade.getElementFromPackage(daoPack, "HibernateDaoFactory", Class.class);
		
		scFacade.createDependency(daoFactory, new NamedElement[]{hibernateDaoFactory});
		
		//DaoFactory extends HibernateDaoFactory
		Generalization generalization = scFacade.getFacade().createGeneralization();
		generalization.addGeneral(hibernateDaoFactory);
		generalization.addSpecific(daoFactory);
		
		//private static DaoFactory INSTANCE = new DaoFactory()
		Property daoFactoryInstance = scFacade.getFacade().createProperty();
		daoFactoryInstance.setName("INSTANCE");
		daoFactoryInstance.setIsStatic(true);
		daoFactoryInstance.setVisibility(VisibilityKind.PRIVATE);
		daoFactoryInstance.addType(daoFactory);
		LiteralString t = scFacade.getFacade().createLiteralString();
		t.setValue("new DaoFactory()");
		daoFactoryInstance.addDefaultValue(t);
		
		//public static DaoFactory getInstance()
		OperationDTO getInstance = (OperationDTO) scFacade.createOperation("getInstance", VisibilityKind.PUBLIC, new Object[][]{{null, daoFactory}});
		((Operation)getInstance).setIsStatic(true);
		
		genUtils.genCode((Operation)getInstance, "return "+daoFactoryInstance.getName()+";\n");
		
		for(Notion notion : notions){
			scFacade.createMappingBetween(notion, daoFactory, TraceTypes.DAO_FACTORY_MAPPING_NAME);
			
			InterfaceDTO dao = (InterfaceDTO) scFacade.getLinkedInterface(notion, TraceTypes.DAO_MAPPING_NAME);
			//private ConceptDAO conceptdao
			Property daoField = scFacade.getFacade().createProperty();
			daoField.setName(genUtils.toLowerCamelCase(dao.getName()));
			daoField.setVisibility(VisibilityKind.PRIVATE);
			daoField.addType((Interface)dao);
			
			//public final ConceptDAO createConceptDAO()
			OperationDTO createDAO = (OperationDTO) scFacade.createOperation("create"+dao.getName(), VisibilityKind.PUBLIC, new Object[][]{{null, dao}});
			((Operation)createDAO).setIsLeaf(true);
			//public final void setConceptdao(ConceptDAO conceptdao)
			String setterName = dao.getName().substring(0, 1) + genUtils.toLowerCamelCase(dao.getName().substring(1));
			OperationDTO setDAO = (OperationDTO) scFacade.createOperation("set"+setterName, VisibilityKind.PUBLIC, new Object[][]{{daoField.getName(), dao}});
			((Operation)setDAO).setIsLeaf(true);
			
			genUtils.genCode((Operation)createDAO, "return "+daoField.getName()+";\n");
			genUtils.genCode((Operation)setDAO, "this."+daoField.getName()+" = "+daoField.getName()+";\n");
			
			daoFactory.addOwnedAttribute(daoField);
			daoFactory.addOwnedOperation((Operation) createDAO);
			daoFactory.addOwnedOperation((Operation) setDAO);
		}
		
		
		daoFactory.addOwnedAttribute(daoFactoryInstance);
		daoFactory.addOwnedOperation((Operation) getInstance);
		
		PackageDTO dao = scFacade.getPackage("DAO");
		PackageDTO springPack = (PackageDTO) scFacade.getElementFromPackage(dao, "spring", Package.class);
		springPack.addClass((ClassDTO)daoFactory);
	}
}
