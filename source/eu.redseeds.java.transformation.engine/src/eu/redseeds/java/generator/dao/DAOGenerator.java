package eu.redseeds.java.generator.dao;

import java.util.ArrayList;
import java.util.List;

import eu.redseeds.java.generator.utils.GeneratorUtils;
import eu.redseeds.java.generator.utils.TraceTypes;
import eu.redseeds.java.repository.SCFacade;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionDTO;
import eu.redseeds.scl.model.sdsl.ClassDTO;
import eu.redseeds.scl.model.sdsl.InterfaceDTO;
import eu.redseeds.scl.rsl.rsldomainelements.notions.Notion;
import eu.redseeds.scl.uml.classes.interfaces.Interface;

public class DAOGenerator {
	
	private SCFacade scFacade;
	private GeneratorUtils genUtils;
	private SpringDAOGenerator springGen;

	public DAOGenerator() {
		scFacade = SCFacade.instance();
		genUtils = new GeneratorUtils();
		springGen = new SpringDAOGenerator();
	}

	public void genDAOLayer() {
		List<Notion> notList = new ArrayList<Notion>();
		for(Notion notion : scFacade.getFacade().getNotionVertices()){
			String type = ((NotionDTO)notion).getType();
			if(type.equals("")){
				notList.add(notion);
			}
		}
		
		for(Notion notion : notList){
			genDAOInterface(notion);
		}
		
		springGen.genDaoFactory(notList);
	}
	
	private void genDAOInterface(Notion notion) {
		String notionName = genUtils.toCamelCase(((NotionDTO)notion).getName());
		ClassDTO entity = (ClassDTO) scFacade.getLinkedClass(notion, TraceTypes.ENTITY_MAPPING_NAME);
		InterfaceDTO entityDaoInterface = (InterfaceDTO) scFacade.getFacade().createInterface();
		entityDaoInterface.setName(notionName+"DAO");
		InterfaceDTO genericDaoInterface = (InterfaceDTO) scFacade.getFacade().createInterface();
		genericDaoInterface.setName("IGenericDao<Long, " + entity.getName() + ">");
		
		scFacade.createMappingBetween(notion, (Interface)entityDaoInterface, TraceTypes.DAO_MAPPING_NAME);
		scFacade.createMappingBetween(notion, (Interface)genericDaoInterface, TraceTypes.GENERIC_DAO_MAPPING_NAME);
		
		/*PackageDTO javaPack = scFacade.getPackage("java");
		PackageDTO utilPack = (PackageDTO) scFacade.getElementFromPackage(javaPack, "util", Package.class);
		InterfaceDTO list = (InterfaceDTO) scFacade.getElementFromPackage(utilPack, "List", Interface.class);
		scFacade.createDependency((Interface)entityDaoInterface, new NamedElement[]{(Interface)list});*/
		
		springGen.genDAO(entityDaoInterface, genericDaoInterface, entity);
	}
	
}
