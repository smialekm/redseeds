/**
 * 
 */
package eu.redseeds.scl.model.impl.rsl.rsldomainelements.systemelements;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import eu.redseeds.scl.model.AbstractModelTest;
import eu.redseeds.scl.model.rsl.rsldomainelements.actors.ActorDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.systemelements.SystemElementDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.systemelements.SystemElementsPackageDTO;
import eu.redseeds.scl.rsl.rsldomainelements.systemelements.SystemElement;

public class SystemElementDTOTest extends AbstractModelTest {
	
	private final static String TEST_NAME = "system_element_name";

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
	 * Test method for {@link eu.redseeds.scl.model.impl.rsl.rsldomainelements.systemelements.SystemElementDTOImpl#SystemElementDTOImpl(int, de.uni_koblenz.jgralab.Graph)}.
	 */
	@Test
	public void testCreate() {
		SystemElementDTO sel = facade.createSystemElementDTO();
		assertNotNull("creating SystemElement failed", sel);
		assertTrue(((SystemElement)sel).getId() > 0);
	}

	/**
	 * Test method for {@link eu.redseeds.scl.model.impl.rsl.rsldomainelements.systemelements.SystemElementDTOImpl#getName()}.
	 */
	@Test
	public void testGetName() {
		SystemElementDTO sel = facade.createSystemElementDTO();
		sel.setName(TEST_NAME);
		assertEquals("setting system element's name failed", TEST_NAME, sel.getName());
	}

	/**
	 * Test method for {@link eu.redseeds.scl.model.impl.rsl.rsldomainelements.systemelements.SystemElementDTOImpl#setName(java.lang.String)}.
	 */
	@Test
	public void testSetName() {
		SystemElementDTO sel = facade.createSystemElementDTO();
		sel.setName(TEST_NAME);
		assertEquals("setting system element's name failed", TEST_NAME, sel.getName());
	}

	/**
	 * Test method for {@link eu.redseeds.scl.model.impl.rsl.rsldomainelements.systemelements.SystemElementDTOImpl#toString()}.
	 */
	@Test
	public void testToString() {
		SystemElementDTO sel = facade.createSystemElementDTO();
		sel.setName(TEST_NAME);
		assertEquals("setting system element's name failed", TEST_NAME, sel.toString());
	}

	/**
	 * Test method for {@link eu.redseeds.scl.model.impl.rsl.rsldomainelements.systemelements.SystemElementDTOImpl#getParent()}.
	 */
	@Test
	public void testGetParent() {
		SystemElementDTO sel = facade.createSystemElementDTO();
		SystemElementsPackageDTO selPack = facade.createSystemElementsPackageDTO();
		selPack.addSystemElementDTO(sel);
		assertNotNull("SystemElement.getParent failed - null", sel.getParent());
		assertEquals("SystemElement.getParent failed", selPack, sel.getParent());
	}

	/**
	 * Test method for {@link eu.redseeds.scl.model.impl.rsl.rsldomainelements.systemelements.SystemElementDTOImpl#setParent(eu.redseeds.scl.model.rsl.rsldomainelements.systemelements.SystemElementsPackageDTO)}.
	 */
	@Test
	public void testSetParent() {
		SystemElementDTO sel = facade.createSystemElementDTO();
		SystemElementsPackageDTO selPack = facade.createSystemElementsPackageDTO();
		sel.setParent(selPack);
		assertNotNull("SystemElement.getParent failed - null", sel.getParent());
		assertEquals("SystemElement.getParent failed", selPack, sel.getParent());
	}

	/**
	 * Test method for {@link eu.redseeds.scl.model.impl.rsl.rsldomainelements.systemelements.SystemElementDTOImpl#getSpecificationPath()}.
	 */
	@Test
	public void testGetSpecificationPath() {
		SystemElementDTO sel = facade.createSystemElementDTO();
		SystemElementsPackageDTO selPack = facade.createSystemElementsPackageDTO();
		selPack.setName(TEST_NAME);
		selPack.addSystemElementDTO(sel);
		assertNotNull(sel.getSpecificationPath());
		assertTrue(sel.getSpecificationPath().equalsIgnoreCase(("\\"+TEST_NAME)));
	}

	/**
	 * Test method for {@link eu.redseeds.scl.model.impl.rsl.rsldomainelements.systemelements.SystemElementDTOImpl#getDescription()}.
	 */
	@Test
	public void testGetDescription() {
		SystemElementDTO sel = facade.createSystemElementDTO();
		assertEquals("system element's description is not null", "", sel.getDescription());
		sel.setDescription(TEST_NAME);
		assertEquals("setting system element's description failed", TEST_NAME+"\n", sel.getDescription());
	}

	/**
	 * Test method for {@link eu.redseeds.scl.model.impl.rsl.rsldomainelements.systemelements.SystemElementDTOImpl#setDescription(java.lang.String)}.
	 */
	@Test
	public void testSetDescription() {
		SystemElementDTO sel = facade.createSystemElementDTO();
		assertEquals("system element's description is not null", "", sel.getDescription());
		sel.setDescription(TEST_NAME);
		assertEquals("setting system element's description failed", TEST_NAME + "\n", sel.getDescription());
		//re-set the description
		sel.setDescription(TEST_NAME + " " + TEST_NAME);
		assertEquals("re-setting system element's description failed", TEST_NAME+ " " + TEST_NAME + "\n", sel.getDescription());
	}

	/**
	 * Test method for {@link eu.redseeds.scl.model.impl.rsl.rsldomainelements.systemelements.SystemElementDTOImpl#getDomainElementRelationshipDTOList()}.
	 */
	@Test
	public void testGetDomainElementRelationshipDTOList() {
		SystemElementDTO sel = facade.createSystemElementDTO();
		SystemElementDTO sel2 = facade.createSystemElementDTO();
		SystemElementDTO sel3 = facade.createSystemElementDTO();
		assertTrue("List is not empty", sel.getDomainElementRelationshipDTOList().size() == 0);
		assertTrue("List is not empty", sel2.getDomainElementRelationshipDTOList().size() == 0);
		assertTrue("List is not empty", sel3.getDomainElementRelationshipDTOList().size() == 0);
		sel.addRelatedSystemElement(sel2);
		assertTrue("List size is not equal 1", sel.getDomainElementRelationshipDTOList().size() == 1);
		assertTrue("List size is not equal 1", sel2.getDomainElementRelationshipDTOList().size() == 1);
		assertTrue("List is not empty", sel3.getDomainElementRelationshipDTOList().size() == 0);
		sel.addRelatedSystemElement(sel3);
		assertTrue("List size is not equal 2", sel.getDomainElementRelationshipDTOList().size() == 2);
		assertTrue("List size is not equal 1", sel2.getDomainElementRelationshipDTOList().size() == 1);
		assertTrue("List size is not equal 1", sel3.getDomainElementRelationshipDTOList().size() == 1);
	}

	/**
	 * Test method for {@link eu.redseeds.scl.model.impl.rsl.rsldomainelements.systemelements.SystemElementDTOImpl#addRelatedActor(eu.redseeds.scl.model.rsl.rsldomainelements.actors.ActorDTO)}.
	 */
	@Test
	public void testAddRelatedActor() {
		SystemElementDTO sel = facade.createSystemElementDTO();
		ActorDTO actor = facade.createActorDTO();
		assertTrue("List is not empty", sel.getDomainElementRelationshipDTOList().size() == 0);
		assertTrue("List is not empty", actor.getDomainElementRelationshipDTOList().size() == 0);
		sel.addRelatedActor(actor);
		assertTrue("List size is not equal 1", sel.getDomainElementRelationshipDTOList().size() == 1);
		assertTrue("List size is not equal 1", actor.getDomainElementRelationshipDTOList().size() == 1);
	}

	/**
	 * Test method for {@link eu.redseeds.scl.model.impl.rsl.rsldomainelements.systemelements.SystemElementDTOImpl#addRelatedNotion(eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionDTO)}.
	 */
	@Test
	public void testAddRelatedNotion() {
		SystemElementDTO sel = facade.createSystemElementDTO();
		NotionDTO notion = facade.createNotionDTO();
		assertTrue("List is not empty", sel.getDomainElementRelationshipDTOList().size() == 0);
		assertTrue("List is not empty", notion.getDomainElementRelationshipDTOList().size() == 0);
		sel.addRelatedNotion(notion);
		assertTrue("List size is not equal 1", sel.getDomainElementRelationshipDTOList().size() == 1);
		assertTrue("List size is not equal 1", notion.getDomainElementRelationshipDTOList().size() == 1);
	}

	/**
	 * Test method for {@link eu.redseeds.scl.model.impl.rsl.rsldomainelements.systemelements.SystemElementDTOImpl#addRelatedSystemElement(eu.redseeds.scl.model.rsl.rsldomainelements.systemelements.SystemElementDTO)}.
	 */
	@Test
	public void testAddRelatedSystemElement() {
		SystemElementDTO sel = facade.createSystemElementDTO();
		SystemElementDTO sel2 = facade.createSystemElementDTO();
		assertTrue("List is not empty", sel.getDomainElementRelationshipDTOList().size() == 0);
		assertTrue("List is not empty", sel2.getDomainElementRelationshipDTOList().size() == 0);
		sel.addRelatedSystemElement(sel2);
		assertTrue("List size is not equal 1", sel.getDomainElementRelationshipDTOList().size() == 1);
		assertTrue("List size is not equal 1", sel2.getDomainElementRelationshipDTOList().size() == 1);
	}

	/**
	 * Test method for {@link eu.redseeds.scl.model.impl.rsl.rsldomainelements.systemelements.SystemElementDTOImpl#isNameUnique(java.lang.String)}.
	 */
	@Test
	public void testIsNameUnique() {
		SystemElementDTO sel = facade.createSystemElementDTO();
		sel.setName(TEST_NAME);
		assertEquals("setting system element's name failed", TEST_NAME, sel.getName());
		assertFalse("system element name's uniqueness failed", sel.isNameUnique(TEST_NAME));
	}
	
	/**
	 * Test method for {@link eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionDTOImpl#checkIfRelated(Object)}.
	 */
	@Test
	public void testCheckIfRelated() {
		SystemElementDTO sel = facade.createSystemElementDTO();
		NotionDTO notion = facade.createNotionDTO();
		ActorDTO actor = facade.createActorDTO();
		ActorDTO actor2 = facade.createActorDTO();
		sel.addRelatedActor(actor);
		sel.addRelatedSystemElement(sel);
		sel.addRelatedNotion(notion);
		assertTrue("elements are related", sel.checkIfRelated(actor));
		assertTrue("elements are related", sel.checkIfRelated(notion));
		assertTrue("elements are related", sel.checkIfRelated(sel));
		assertFalse("elements are not related", sel.checkIfRelated(actor2));
	}

}
