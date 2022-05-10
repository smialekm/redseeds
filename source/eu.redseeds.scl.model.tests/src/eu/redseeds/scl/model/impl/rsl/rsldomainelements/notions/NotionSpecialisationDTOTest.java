package eu.redseeds.scl.model.impl.rsl.rsldomainelements.notions;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import eu.redseeds.scl.model.AbstractModelTest;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionSpecialisationDTO;
import eu.redseeds.scl.rsl.rsldomainelements.notions.NotionSpecialisation;

public class NotionSpecialisationDTOTest extends AbstractModelTest {

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
	 * Test method for {@link eu.redseeds.scl.model.rsl.rsldomainelements.notions.DomainElementRelationshipDTO#DomainElementRelationshipDTO()}.
	 */
	@Test
	public void testCreate() {
		NotionSpecialisationDTO rel = facade.createNotionSpecialisationDTO();
		assertNotNull("creating specialisation failed", rel);
		assertTrue("creating specialisation failed", ((NotionSpecialisation)rel).getId() > 0);
	}

	/**
	 * Test method for {@link eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionSpecialisationDTO#getGeneralNotion()}.
	 */
	@Test
	public void testGetGeneralNotion() {
		NotionSpecialisationDTO rel = facade.createNotionSpecialisationDTO();
		NotionDTO notion = facade.createNotionDTO();
		rel.setGeneralNotion(notion);
		assertNotNull("general notion is null", rel.getGeneralNotion());
		assertEquals("general notion not retreived properly", notion, rel.getGeneralNotion());
	}

	/**
	 * Test method for {@link eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionSpecialisationDTO#getSpecialisedNotion()}.
	 */
	@Test
	public void testGetSpecialisedNotion() {
		NotionSpecialisationDTO rel = facade.createNotionSpecialisationDTO();
		NotionDTO notion = facade.createNotionDTO();
		rel.setGeneralNotion(notion);
		assertNotNull("specialised notion is null", rel.getGeneralNotion());
		assertEquals("specialised notion not retrieved properly", notion, rel.getGeneralNotion());
	}

	/**
	 * Test method for {@link eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionSpecialisationDTO#setGeneralNotion(eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionDTO)}.
	 */
	@Test
	public void testSetGeneralNotion() {
		NotionSpecialisationDTO rel = facade.createNotionSpecialisationDTO();
		NotionDTO notion = facade.createNotionDTO();
		rel.setGeneralNotion(notion);
		assertNotNull("general notion is null", rel.getGeneralNotion());
		assertEquals("general notion not retreived properly", notion, rel.getGeneralNotion());
	}

	/**
	 * Test method for {@link eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionSpecialisationDTO#setSpecialisedNotion(eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionDTO)}.
	 */
	@Test
	public void testSetSpecialisedNotion() {
		NotionSpecialisationDTO rel = facade.createNotionSpecialisationDTO();
		NotionDTO notion = facade.createNotionDTO();
		rel.setGeneralNotion(notion);
		assertNotNull("specialised notion is null", rel.getGeneralNotion());
		assertEquals("specialised notion not set properly", notion, rel.getGeneralNotion());
	}

	/**
	 * Test method for {@link eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionSpecialisationDTO#delete()}.
	 */
	@Test
	public void testDelete() {
		NotionDTO notion = facade.createNotionDTO();
		NotionDTO notion2 = facade.createNotionDTO();
		notion.addGeneralNotion(notion2);
		assertEquals("NotionSpecialisationDTOList does not have expected size", 1, notion.getNotionSpecialisationDTOList().size());
		notion.getNotionSpecialisationDTOList().get(0).delete();
		assertEquals("NotionSpecialisationDTOList does not have expected size", 0, notion.getNotionSpecialisationDTOList().size());
	}

	/**
	 * Test method for {@link eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionSpecialisationDTO#reverse()}.
	 */
	@Test
	public void testReverse() {
		NotionDTO notion = facade.createNotionDTO();
		NotionDTO notion2 = facade.createNotionDTO();
		notion.addGeneralNotion(notion2);
		assertEquals("NotionSpecialisationDTOList does not have expected size", 1, notion.getNotionSpecialisationDTOList().size());
		assertEquals("NotionSpecialisationDTOList does not have expected size", 1, notion2.getNotionSpecialisationDTOList().size());
		NotionSpecialisationDTO spec = notion.getNotionSpecialisationDTOList().get(0);
		assertEquals("NotionSpecialisationDTO is not properly set/or retrieved", spec, notion2.getNotionSpecialisationDTOList().get(0));
		assertEquals("NotionSpecialisationDTO is not properly set/or retrieved", notion2, spec.getGeneralNotion());
		assertEquals("NotionSpecialisationDTO is not properly set/or retrieved", notion, spec.getSpecialisedNotion());
//		NotionDTO notion11 = spec.getSpecialisedNotion();
//		NotionDTO notion21 = spec.getGeneralNotion();
		spec.reverse();
		assertNotNull("reversing specialisation failed", spec);
		assertNotNull("reversing specialisation failed", spec.getGeneralNotion());
		assertNotNull("reversing specialisation failed", spec.getSpecialisedNotion());
		assertNotNull("reversing specialisation failed", notion);
		assertNotNull("reversing specialisation failed", notion2);
//		NotionDTO notion12 = spec.getGeneralNotion();
//		NotionDTO notion22 = spec.getSpecialisedNotion();
		assertEquals("NotionSpecialisationDTO was not properly reversed", notion, spec.getGeneralNotion());
		assertEquals("NotionSpecialisationDTO was not properly reversed", notion2, spec.getSpecialisedNotion());
		
	}

}
