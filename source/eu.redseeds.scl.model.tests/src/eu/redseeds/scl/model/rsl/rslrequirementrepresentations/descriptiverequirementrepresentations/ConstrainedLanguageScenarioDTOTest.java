package eu.redseeds.scl.model.rsl.rslrequirementrepresentations.descriptiverequirementrepresentations;

import org.junit.Test;

import eu.redseeds.scl.model.AbstractModelTest;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentations.descriptiverequirementrepresentations.ConstrainedLanguageScenarioDTO;

import static org.junit.Assert.*;

public class ConstrainedLanguageScenarioDTOTest extends AbstractModelTest {

	private static final String CLS_NAME = "ConstrainedLanguageScenario:";

	@Test
	public void createTest() {
		ConstrainedLanguageScenarioDTO cls = facade.createConstrainedLanguageScenarioDTO();
		assertNotNull(cls);
	}
	
	@Test
	public void setGetName() {
		ConstrainedLanguageScenarioDTO cls = facade.createConstrainedLanguageScenarioDTO();
		assertNotNull(cls);
		
		String name = getUniqueName();
		
		cls.setName(name);
		
		assertEquals(cls.getName(),name);
		
		
	}
	
	private String getUniqueName() {
		int i = 0;
		//TODO create one use case in setup and work on it
		while (facade.getUseCaseByName(CLS_NAME + i) != null) {
			i++;
		}

		return CLS_NAME + i;
	}
}
