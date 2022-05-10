package eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import eu.redseeds.scl.model.AbstractModelTest;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentations.descriptiverequirementrepresentations.ConstrainedLanguageScenarioDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsrelationships.RequirementVocabularyRelationshipDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.UseCaseDTO;
import eu.redseeds.scl.model.traceability.TraceableObjectDTO;

import static org.junit.Assert.*;

public class UseCaseDTOTest extends AbstractModelTest {

	private static final String EMPTY = "";

	private static final String UC_NAME = "UseCaseName:";

	private static final String DESCRIPTION = "Unit test (z ang. test jednostkowy, test podzespo�u) to w programowaniu obiektowym,\n"
			+ " a w szczeg�lno�ci programowaniu ekstremalnym, kod, kt�ry uruchamia fragment testowanego programu i por�wnuje jego wynik z oczekiwanym.\n"
			+ " Testy takie s� podstawowym sprawdzianem poprawno�ci programu - dobry zestaw test�w pozwala wykry� o wiele wi�cej b��d�w ni� metody statyczne (silna typizacja itd.)\n"
			+ " - i co wa�niejsze wszystkie wykryte b��dy s� rzeczywiste.";

	@Test
	public void createUseCaseDTO() {
		UseCaseDTO uc = facade.createUseCaseDTO();
		assertNotNull(uc);
	}

	@Test
	public void setGetName() {
		UseCaseDTO uc = facade.createUseCaseDTO();
		assertNotNull(uc);

		// get unique name for usecase
		String name = getUniqueName();

		// set the name
		uc.setName(name);

		// check if it's set
		assertEquals(name, uc.getName());

		// get it from facade and check if the same object was returned
		assertEquals(uc, facade.getUseCaseByName(name));
		
		//again, now with the name reset
		name = name+"new";
		uc.setName(name);
		assertEquals(name, uc.getName());
		assertEquals(uc, facade.getUseCaseByName(name));
	}

	@Test
	public void setGetDescription() {
		UseCaseDTO uc = facade.createUseCaseDTO();
		assertNotNull(uc);

		uc.setDescription(DESCRIPTION);

		// check if it's the same
		assertEquals(DESCRIPTION, uc.getDescription());
	}
	
	@Test
	public void setGetEmptyDescription() {
		UseCaseDTO uc = facade.createUseCaseDTO();
		assertNotNull(uc);
		
		uc.setDescription(EMPTY);
		
		// check if not null
		assertNotNull( uc.getDescription());

		// check if it's the same
		assertEquals(EMPTY, uc.getDescription());
	}

	@Test
	public void addGetSceanarios() {
		UseCaseDTO uc = facade.createUseCaseDTO();
		assertNotNull(uc);

		// should be no scenarios
		assertEquals(0, uc.getConstrainedLanguageScenarioDTOList().size());

		// create new scenario and add it
		ConstrainedLanguageScenarioDTO scen = facade
				.createConstrainedLanguageScenarioDTO();

		uc.addConstrainedLanguageScenario(scen);

		// one scenario
		assertEquals(1, uc.getConstrainedLanguageScenarioDTOList().size());

		// add 3 more
		ConstrainedLanguageScenarioDTO scen2 = facade
				.createConstrainedLanguageScenarioDTO();
		ConstrainedLanguageScenarioDTO scen3 = facade
				.createConstrainedLanguageScenarioDTO();
		ConstrainedLanguageScenarioDTO scen4 = facade
				.createConstrainedLanguageScenarioDTO();

		uc.addConstrainedLanguageScenario(scen2);
		uc.addConstrainedLanguageScenario(scen3);
		uc.addConstrainedLanguageScenario(scen4);

		// scenarios should be returned in given order and should have 4 elements
		assertEquals(4, uc.getConstrainedLanguageScenarioDTOList().size());
		
		List<ConstrainedLanguageScenarioDTO> scenarios = Arrays.asList(scen,
				scen2, scen3, scen4);
		assertEquals(scenarios, uc.getConstrainedLanguageScenarioDTOList());
	}

	private String getUniqueName() {
		int i = 0;
		while (facade.getUseCaseByName(UC_NAME + i) != null) {
			i++;
		}

		return UC_NAME + i;
	}
	
	@Test
	public void testGetTraceabilityLinkDTOList() {
		//TODO add more tests
		//create uc
		UseCaseDTO uc = facade.createUseCaseDTO();
		assertNotNull(uc);
		
		assertNotNull("List is null", ((TraceableObjectDTO)uc).getTraceabilityLinkDTOList());
	}
	
	/**
	 * Test method for {@link eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.UseCaseDTO#getRelatedNotions()}.
	 */
	@Test
	public void testGetRelatedNotions() {
		UseCaseDTO uc = facade.createUseCaseDTO();
		NotionDTO notion = facade.createNotionDTO();
		NotionDTO notion2 = facade.createNotionDTO();
		uc.addRelatedNotion(notion);
		assertTrue(uc.checkIfRelated(notion));
		assertTrue(uc.getRelatedNotions().size() == 1); 
		uc.addRelatedNotion(notion2);
		assertTrue(uc.checkIfRelated(notion2));
		assertTrue(uc.getRelatedNotions().size() == 2); 
	}

	/**
	 * Test method for {@link eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.UseCaseDTO#checkIfRelated(eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionDTO)}.
	 */
	@Test
	public void testCheckIfRelated() {
		UseCaseDTO uc = facade.createUseCaseDTO();
		NotionDTO notion = facade.createNotionDTO();
		NotionDTO notion2 = facade.createNotionDTO();
		uc.addRelatedNotion(notion);
		assertTrue(uc.checkIfRelated(notion));
		assertTrue(uc.getRelatedNotions().size() == 1); 
		assertFalse(uc.checkIfRelated(notion2));
	}

	/**
	 * Test method for {@link eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.UseCaseDTO#addRelatedNotion(eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionDTO)}.
	 */
	@Test
	public void testAddRelatedNotion() {
		UseCaseDTO uc = facade.createUseCaseDTO();
		NotionDTO notion = facade.createNotionDTO();
		NotionDTO notion2 = facade.createNotionDTO();
		assertNotNull(uc.addRelatedNotion(notion));
		assertTrue(uc.checkIfRelated(notion));
		assertTrue(uc.getRelatedNotions().size() == 1); 
		RequirementVocabularyRelationshipDTO rel = uc.addRelatedNotion(notion2);
		assertTrue(rel.getNotion().equals(notion2));
		assertTrue(uc.checkIfRelated(notion2));
		assertTrue(uc.getRelatedNotions().size() == 2); 
		assertNull(uc.addRelatedNotion(null));
	}
}
