package eu.redseeds.java.parser.elements;

import eu.redseeds.java.generator.dao.DAOGenerator;
import eu.redseeds.java.generator.dto.DTOGenerator;
import eu.redseeds.java.generator.model.ModelGenerator;
import eu.redseeds.java.generator.services.ServicesGenerator;

public class NotionsParser {

	private DTOGenerator dtoGen;
	private DAOGenerator daoGen;
	private ModelGenerator modelGen;
	private ServicesGenerator serviceGen;

	public NotionsParser() {
		dtoGen = new DTOGenerator();
		daoGen = new DAOGenerator();
		modelGen = new ModelGenerator();
		serviceGen = new ServicesGenerator();
	}

	public void parse() {
		modelGen.genModelLayer();
		dtoGen.genDTOLayer();
		daoGen.genDAOLayer();
		serviceGen.genServicesLayer();
	}
}
