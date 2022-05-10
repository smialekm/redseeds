/**
 * 
 */
package eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import eu.redseeds.scl.model.AbstractModelTest;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsrelationships.RequirementVocabularyRelationshipDTO;

/**
 * @author Administrator
 *
 */
public class RequirementDTOTest extends AbstractModelTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementDTO#getRelatedNotions()}.
	 */
	@Test
	public void testGetRelatedNotions() {
		RequirementDTO req = facade.createRequirementDTO();
		NotionDTO notion = facade.createNotionDTO();
		NotionDTO notion2 = facade.createNotionDTO();
		req.addRelatedNotion(notion);
		assertTrue(req.checkIfRelated(notion));
		assertTrue(req.getRelatedNotions().size() == 1); 
		req.addRelatedNotion(notion2);
		assertTrue(req.checkIfRelated(notion2));
		assertTrue(req.getRelatedNotions().size() == 2); 
	}

	/**
	 * Test method for {@link eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementDTO#checkIfRelated(eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionDTO)}.
	 */
	@Test
	public void testCheckIfRelated() {
		RequirementDTO req = facade.createRequirementDTO();
		NotionDTO notion = facade.createNotionDTO();
		NotionDTO notion2 = facade.createNotionDTO();
		req.addRelatedNotion(notion);
		assertTrue(req.checkIfRelated(notion));
		assertTrue(req.getRelatedNotions().size() == 1); 
		assertFalse(req.checkIfRelated(notion2));
	}

	/**
	 * Test method for {@link eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementDTO#addRelatedNotion(eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionDTO)}.
	 */
	@Test
	public void testAddRelatedNotion() {
		RequirementDTO req = facade.createRequirementDTO();
		NotionDTO notion = facade.createNotionDTO();
		NotionDTO notion2 = facade.createNotionDTO();
		assertNotNull(req.addRelatedNotion(notion));
		assertTrue(req.checkIfRelated(notion));
		assertTrue(req.getRelatedNotions().size() == 1); 
		RequirementVocabularyRelationshipDTO rel = req.addRelatedNotion(notion2);
		assertTrue(rel.getNotion().equals(notion2));
		assertTrue(req.checkIfRelated(notion2));
		assertTrue(req.getRelatedNotions().size() == 2); 
		assertNull(req.addRelatedNotion(null));
	}

}
