/**
 * 
 */
package eu.redseeds.scl.model.impl.rsl.rsldomainelements.actors;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import eu.redseeds.scl.model.AbstractModelTest;
import eu.redseeds.scl.model.rsl.rsldomainelements.actors.ActorDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.actors.ActorsPackageDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.systemelements.SystemElementDTO;
import eu.redseeds.scl.model.traceability.TraceableObjectDTO;
import eu.redseeds.scl.rsl.rsldomainelements.actors.Actor;

public class ActorDTOTest extends AbstractModelTest {
	
	private final static String TEST_NAME = "actor_name";

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
	 * Test method for {@link eu.redseeds.scl.model.rsl.rsldomainelements.actors.ActorDTO#ActorDTO()}.
	 */
	@Test
	public void testCreate() {
		ActorDTO actor = facade.createActorDTO();
		assertNotNull("creating Actor failed", actor);
		assertTrue(((Actor)actor).getId() > 0);
	}
	
	/**
	 * Test method for {@link eu.redseeds.scl.model.rsl.rsldomainelements.actors.ActorDTO#setName(java.lang.String)}.
	 */
	@Test
	public void testSetName() {
		ActorDTO actor = facade.createActorDTO();
		actor.setName(TEST_NAME);
		assertEquals("setting actor's name failed", TEST_NAME, actor.getName());
	}

	/**
	 * Test method for {@link eu.redseeds.scl.model.rsl.rsldomainelements.actors.ActorDTO#getName()}.
	 */
	@Test
	public void testGetName() {
		ActorDTO actor = facade.createActorDTO();
		actor.setName(TEST_NAME);
		assertEquals("getting actor's name failed", TEST_NAME, actor.getName());
	}

	/**
	 * Test method for {@link eu.redseeds.scl.model.rsl.rsldomainelements.actors.ActorDTO#delete()}.
	 */
	@Test
	public void testDelete() {
		ActorDTO actor = facade.createActorDTO();
		actor.delete();
//		System.out.print(((Actor)actor).getId());
//		assertNull(actor);
	}

	/**
	 * Test method for {@link eu.redseeds.scl.model.rsl.rsldomainelements.actors.ActorDTO#toString()}.
	 */
	@Test
	public void testToString() {
		ActorDTO actor = facade.createActorDTO();
		actor.setName(TEST_NAME);
		assertEquals("getting actor's name failed", TEST_NAME, actor.toString());
	}

	/**
	 * Test method for {@link eu.redseeds.scl.model.rsl.rsldomainelements.actors.ActorDTO#getParent()}.
	 */
	@Test
	public void testGetParent() {
		ActorDTO actor = facade.createActorDTO();
		ActorsPackageDTO actPack = facade.createActorsPackageDTO();
		actPack.addActorDTO(actor);
		assertNotNull("actor.getParent failed - null", actor.getParent());
		assertEquals("actor.getParent failed", actPack, actor.getParent());
	}

	/**
	 * Test method for {@link eu.redseeds.scl.model.rsl.rsldomainelements.actors.ActorDTO#setParent(eu.redseeds.scl.model.rsl.rsldomainelements.actors.ActorsPackageDTO)}.
	 */
	@Test
	public void testSetParent() {
		ActorDTO actor = facade.createActorDTO();
		ActorsPackageDTO actPack = facade.createActorsPackageDTO();
		actor.setParent(actPack);
		assertNotNull(actor.getParent());
	}

	/**
	 * Test method for {@link eu.redseeds.scl.model.rsl.rsldomainelements.actors.ActorDTO#getSpecificationPath()}.
	 */
	@Test
	public void testGetSpecificationPath() {
		ActorDTO actor = facade.createActorDTO();
		ActorsPackageDTO actPack = facade.createActorsPackageDTO();
		actPack.setName(TEST_NAME);
		actPack.addActorDTO(actor);
		assertNotNull(actor.getSpecificationPath());
		assertTrue(actor.getSpecificationPath().equalsIgnoreCase(("\\"+TEST_NAME)));
	}
	
	/**
	 * Test method for {@link eu.redseeds.scl.model.rsl.rsldomainelements.actors.ActorDTO#getDescription()}.
	 */
	@Test
	public void testGetDescription() {
		ActorDTO actor = facade.createActorDTO();
		assertEquals("actor's description is not null", "", actor.getDescription());
		actor.setDescription(TEST_NAME);
		assertEquals("setting actor's description failed", TEST_NAME+"\n", actor.getDescription());
	}

	/**
	 * Test method for {@link eu.redseeds.scl.model.rsl.rsldomainelements.actors.ActorDTO#setDescription(java.lang.String)}.
	 */
	@Test
	public void testSetDescription() {
		ActorDTO actor = facade.createActorDTO();
		assertEquals("actor's description is not null", "", actor.getDescription());
		actor.setDescription(TEST_NAME);
		assertEquals("setting actor's description failed", TEST_NAME+"\n", actor.getDescription());
		actor.setDescription(TEST_NAME + " " + TEST_NAME);
		assertEquals("re-setting actor's description failed", TEST_NAME + " " + TEST_NAME + "\n", actor.getDescription());
	}

	/**
	 * Test method for {@link eu.redseeds.scl.model.rsl.rsldomainelements.actors.ActorDTO#getDomainElementRelationshipDTOList()}.
	 */
	@Test
	public void testGetDomainElementRelationshipDTOList() {
		ActorDTO actor = facade.createActorDTO();
		ActorDTO actor2 = facade.createActorDTO();
		ActorDTO actor3 = facade.createActorDTO();
		assertTrue("List is not empty", actor.getDomainElementRelationshipDTOList().size() == 0);
		assertTrue("List is not empty", actor2.getDomainElementRelationshipDTOList().size() == 0);
		assertTrue("List is not empty", actor3.getDomainElementRelationshipDTOList().size() == 0);
		actor.addRelatedActor(actor2);
		assertTrue("List size is not equal 1", actor.getDomainElementRelationshipDTOList().size() == 1);
		assertTrue("List size is not equal 1", actor2.getDomainElementRelationshipDTOList().size() == 1);
		assertTrue("List is not empty", actor3.getDomainElementRelationshipDTOList().size() == 0);
		actor.addRelatedActor(actor3);
		assertTrue("List size is not equal 2", actor.getDomainElementRelationshipDTOList().size() == 2);
		assertTrue("List size is not equal 1", actor2.getDomainElementRelationshipDTOList().size() == 1);
		assertTrue("List size is not equal 1", actor3.getDomainElementRelationshipDTOList().size() == 1);
	}

	/**
	 * Test method for {@link eu.redseeds.scl.model.rsl.rsldomainelements.actors.ActorDTO#addRelatedActor(eu.redseeds.scl.model.rsl.rsldomainelements.actors.ActorDTO)}.
	 */
	@Test
	public void testAddRelatedActor() {
		ActorDTO actor = facade.createActorDTO();
		ActorDTO actor2 = facade.createActorDTO();
		assertTrue("List is not empty", actor.getDomainElementRelationshipDTOList().size() == 0);
		assertTrue("List is not empty", actor2.getDomainElementRelationshipDTOList().size() == 0);
		actor.addRelatedActor(actor2);
		assertTrue("List size is not equal 1", actor.getDomainElementRelationshipDTOList().size() == 1);
		assertTrue("List size is not equal 1", actor2.getDomainElementRelationshipDTOList().size() == 1);
	}

	/**
	 * Test method for {@link eu.redseeds.scl.model.rsl.rsldomainelements.actors.ActorDTO#addRelatedNotion(eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionDTO)}.
	 */
	@Test
	public void testAddRelatedNotion() {
		ActorDTO actor = facade.createActorDTO();
		NotionDTO notion = facade.createNotionDTO();
		assertTrue("List is not empty", actor.getDomainElementRelationshipDTOList().size() == 0);
		assertTrue("List is not empty", notion.getDomainElementRelationshipDTOList().size() == 0);
		actor.addRelatedNotion(notion);
		assertTrue("List size is not equal 1", actor.getDomainElementRelationshipDTOList().size() == 1);
		assertTrue("List size is not equal 1", notion.getDomainElementRelationshipDTOList().size() == 1);
	}

	/**
	 * Test method for {@link eu.redseeds.scl.model.rsl.rsldomainelements.actors.ActorDTO#addRelatedSystemElement(eu.redseeds.scl.model.rsl.rsldomainelements.systemelements.SystemElementDTO)}.
	 */
	@Test
	public void testAddRelatedSystemElement() {
		ActorDTO actor = facade.createActorDTO();
		SystemElementDTO sel = facade.createSystemElementDTO();
		assertTrue("List is not empty", actor.getDomainElementRelationshipDTOList().size() == 0);
		assertTrue("List is not empty", sel.getDomainElementRelationshipDTOList().size() == 0);
		actor.addRelatedSystemElement(sel);
		assertTrue("List size is not equal 1", actor.getDomainElementRelationshipDTOList().size() == 1);
		assertTrue("List size is not equal 1", sel.getDomainElementRelationshipDTOList().size() == 1);
	}

	/**
	 * Test method for {@link eu.redseeds.scl.model.rsl.rsldomainelements.actors.ActorDTO#isNameUnique(java.lang.String)}.
	 */
	@Test
	public void testIsNameUnique() {
		ActorDTO actor = facade.createActorDTO();
		actor.setName(TEST_NAME);
		assertEquals("setting actor's name failed", TEST_NAME, actor.getName());
		assertFalse("actor name's uniqueness failed", actor.isNameUnique(TEST_NAME));
	}
	
	/**
	 * Test method for {@link eu.redseeds.scl.model.rsl.rsldomainelements.actors.ActorDTO#checkIfRelated(Object)}.
	 */
	@Test
	public void testCheckIfRelated() {
		ActorDTO actor = facade.createActorDTO();
		SystemElementDTO sel = facade.createSystemElementDTO();
		NotionDTO notion = facade.createNotionDTO();
		ActorDTO actor2 = facade.createActorDTO();
		ActorDTO actor3 = facade.createActorDTO();
		actor.addRelatedActor(actor2);
		actor.addRelatedSystemElement(sel);
		actor.addRelatedNotion(notion);
		assertTrue("elements are related", actor.checkIfRelated(actor2));
		assertTrue("elements are related", actor.checkIfRelated(notion));
		assertTrue("elements are related", actor.checkIfRelated(sel));
		assertFalse("elements are not related", actor.checkIfRelated(actor3));
	}
	
	@Test
	public void testGetTraceabilityLinkDTOList() {
		//TODO add more tests
		ActorDTO actor = facade.createActorDTO();
		assertNotNull(actor);
		
		assertNotNull("List is null", ((TraceableObjectDTO)actor).getTraceabilityLinkDTOList());
	}

}
