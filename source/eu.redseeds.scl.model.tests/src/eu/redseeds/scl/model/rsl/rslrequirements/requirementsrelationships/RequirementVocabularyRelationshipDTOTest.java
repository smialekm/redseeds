/**
 * 
 */
package eu.redseeds.scl.model.rsl.rslrequirements.requirementsrelationships;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import eu.redseeds.scl.model.AbstractModelTest;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementDTO;

/**
 * @author Administrator
 *
 */
public class RequirementVocabularyRelationshipDTOTest extends AbstractModelTest {

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
	 * Test method for {@link eu.redseeds.scl.model.rsl.rslrequirements.requirementsrelationships.RequirementVocabularyRelationshipDTO#RequirementVocabularyRelationshipDTO()}.
	 */
	@Test
	public void testCreate() {
		RequirementVocabularyRelationshipDTO reqVRel = facade.createRequirementVocabularyRelationshipDTO();
		assertNotNull("Creation of RequirementVocabularyRelationshipDTO failed", reqVRel);
	}

	/**
	 * Test method for {@link eu.redseeds.scl.model.rsl.rslrequirements.requirementsrelationships.RequirementVocabularyRelationshipDTO#setRequirement(eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementDTO)}.
	 */
	@Test
	public void testSetRequirement() {
		RequirementVocabularyRelationshipDTO reqVRel = facade.createRequirementVocabularyRelationshipDTO();
		assertNotNull("Creation of RequirementVocabularyRelationshipDTO failed", reqVRel);
		RequirementDTO req = facade.createRequirementDTO();
		assertNull(reqVRel.getRequirement());
		reqVRel.setRequirement(req);
		assertNotNull("Getting of Requirement from RequirementVocabularyRelationshipDTO failed", 
				reqVRel.getRequirement());
		assertEquals("Getting of Requirement from RequirementVocabularyRelationshipDTO failed", 
				reqVRel.getRequirement(), req);
	}

	/**
	 * Test method for {@link eu.redseeds.scl.model.rsl.rslrequirements.requirementsrelationships.RequirementVocabularyRelationshipDTO#setNotion(eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionDTO)}.
	 */
	@Test
	public void testSetNotion() {
		RequirementVocabularyRelationshipDTO reqVRel = facade.createRequirementVocabularyRelationshipDTO();
		assertNotNull("Creation of RequirementVocabularyRelationshipDTO failed", reqVRel);
		NotionDTO not = facade.createNotionDTO();
		assertNull(reqVRel.getRequirement());
		reqVRel.setNotion(not);
		assertNotNull("Getting of Notion from RequirementVocabularyRelationshipDTO failed", 
				reqVRel.getNotion());
		assertEquals("Getting of Notion from RequirementVocabularyRelationshipDTO failed", 
				reqVRel.getNotion(), not);
	}

	/**
	 * Test method for {@link eu.redseeds.scl.model.rsl.rslrequirements.requirementsrelationships.RequirementVocabularyRelationshipDTO#getRequirement()}.
	 */
	@Test
	public void testGetRequirement() {
		testSetRequirement();
	}

	/**
	 * Test method for {@link eu.redseeds.scl.model.rsl.rslrequirements.requirementsrelationships.RequirementVocabularyRelationshipDTO#getNotion()}.
	 */
	@Test
	public void testGetNotion() {
		testSetNotion();
	}

//	/**
//	 * Test method for {@link eu.redseeds.scl.model.rsl.rslrequirements.requirementsrelationships.RequirementVocabularyRelationshipDTO#deleteRelationship()}.
//	 */
//	@Test
//	public void testDeleteRelationship() {
//		fail("Not yet implemented");
//	}

}
