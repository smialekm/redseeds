package eu.redseeds.scl.model.impl.rsl.rsldomainelements.domainelements;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import eu.redseeds.scl.model.AbstractModelTest;
import eu.redseeds.scl.model.rsl.rsldomainelements.actors.ActorDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.domainelements.DomainElementRelationshipDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionDTO;
import eu.redseeds.scl.rsl.rsldomainelements.domainelements.DomainElementRelationship;

public class DomainElementRelationshipDTOTest extends AbstractModelTest {

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
		DomainElementRelationshipDTO rel = facade.createDomainElementRelationshipDTO();
		assertNotNull("creating relationship failed", rel);
		assertTrue("creating relationship failed", ((DomainElementRelationship)rel).getId() > 0);
	}

	/**
	 * Test method for {@link eu.redseeds.scl.model.rsl.rsldomainelements.domainelements.DomainElementRelationshipDTO#getSource()}.
	 */
	@Test
	public void testGetSource() {
		DomainElementRelationshipDTO rel = facade.createDomainElementRelationshipDTO();
		ActorDTO actor = facade.createActorDTO();
		rel.setSource(actor);
		assertNotNull("Relationship source is null", rel.getSource());
		assertEquals("Relationship source not retrieved properly", actor, rel.getSource()); 
	}

	/**
	 * Test method for {@link eu.redseeds.scl.model.rsl.rsldomainelements.domainelements.DomainElementRelationshipDTO#getTarget()}.
	 */
	@Test
	public void testGetTarget() {
		DomainElementRelationshipDTO rel = facade.createDomainElementRelationshipDTO();
		ActorDTO actor = facade.createActorDTO();
		rel.setTarget(actor);
		assertNotNull("Relationship target is null", rel.getTarget());
		assertEquals("Relationship target not retrieved properly", actor, rel.getTarget()); 
	}

	/**
	 * Test method for {@link eu.redseeds.scl.model.rsl.rsldomainelements.domainelements.DomainElementRelationshipDTO#setSource(java.lang.Object)}.
	 */
	@Test
	public void testSetSource() {
		DomainElementRelationshipDTO rel = facade.createDomainElementRelationshipDTO();
		ActorDTO actor = facade.createActorDTO();
		rel.setSource(actor);
		assertNotNull("Relationship source is null", rel.getSource());
		assertEquals("Relationship source not set properly", actor, rel.getSource()); 
	}

	/**
	 * Test method for {@link eu.redseeds.scl.model.rsl.rsldomainelements.domainelements.DomainElementRelationshipDTO#setTarget(java.lang.Object)}.
	 */
	@Test
	public void testSetTarget() {
		DomainElementRelationshipDTO rel = facade.createDomainElementRelationshipDTO();
		ActorDTO actor = facade.createActorDTO();
		rel.setTarget(actor);
		assertNotNull("Relationship target is null", rel.getTarget());
		assertEquals("Relationship target not set properly", actor, rel.getTarget()); 
	}

	/**
	 * Test method for {@link eu.redseeds.scl.model.rsl.rsldomainelements.domainelements.DomainElementRelationshipDTO#getSourceMultiplicity()}.
	 */
	@Test
	public void testGetSourceMultiplicity() {
		DomainElementRelationshipDTO rel = facade.createDomainElementRelationshipDTO();
		ActorDTO actor = facade.createActorDTO();
		rel.setSource(actor);
		assertNull("Relationship source multiplicity is not null", rel.getSourceMultiplicity());
		ActorDTO actor2 = facade.createActorDTO();
		DomainElementRelationshipDTO rel2 = actor2.addRelatedActor(actor);
		assertNotNull("Relationship source multiplicity is null", rel2.getSourceMultiplicity());
		assertEquals("Relationship source multiplicity not set properly", DomainElementRelationshipDTO.DEFAULT_MULTIPLICITY, rel2.getSourceMultiplicity());
		rel2.setSourceMultiplicity("1..*");
		assertEquals("Relationship source multiplicity not set properly", "1..*", rel2.getSourceMultiplicity());
	}

	/**
	 * Test method for {@link eu.redseeds.scl.model.rsl.rsldomainelements.domainelements.DomainElementRelationshipDTO#getTargetMultiplicity()}.
	 */
	@Test
	public void testGetTargetMultiplicity() {
		DomainElementRelationshipDTO rel = facade.createDomainElementRelationshipDTO();
		ActorDTO actor = facade.createActorDTO();
		rel.setSource(actor);
		assertNull("Relationship source multiplicity is not null", rel.getSourceMultiplicity());
		ActorDTO actor2 = facade.createActorDTO();
		DomainElementRelationshipDTO rel2 = actor2.addRelatedActor(actor);
		assertNotNull("Relationship source multiplicity is null", rel2.getTargetMultiplicity());
		assertEquals("Relationship source multiplicity not set properly", DomainElementRelationshipDTO.DEFAULT_MULTIPLICITY, rel2.getTargetMultiplicity());
		rel2.setTargetMultiplicity("1..*");
		assertEquals("Relationship source multiplicity not set properly", "1..*", rel2.getTargetMultiplicity());
	}

	/**
	 * Test method for {@link eu.redseeds.scl.model.rsl.rsldomainelements.domainelements.DomainElementRelationshipDTO#setSourceMultiplicity(java.lang.String)}.
	 */
	@Test
	public void testSetSourceMultiplicity() {
		DomainElementRelationshipDTO rel = facade.createDomainElementRelationshipDTO();
		ActorDTO actor = facade.createActorDTO();
		rel.setSource(actor);
		assertNull("Relationship source multiplicity is not null", rel.getSourceMultiplicity());
		ActorDTO actor2 = facade.createActorDTO();
		DomainElementRelationshipDTO rel2 = actor2.addRelatedActor(actor);
		assertNotNull("Relationship source multiplicity is null", rel2.getSourceMultiplicity());
		assertEquals("Relationship source multiplicity not set properly", DomainElementRelationshipDTO.DEFAULT_MULTIPLICITY, rel2.getSourceMultiplicity());
		rel2.setSourceMultiplicity("1..*");
		assertEquals("Relationship source multiplicity not set properly", "1..*", rel2.getSourceMultiplicity());
	}

	/**
	 * Test method for {@link eu.redseeds.scl.model.rsl.rsldomainelements.domainelements.DomainElementRelationshipDTO#setTargetMultiplicity(java.lang.String)}.
	 */
	@Test
	public void testSetTargetMultiplicity() {
		DomainElementRelationshipDTO rel = facade.createDomainElementRelationshipDTO();
		ActorDTO actor = facade.createActorDTO();
		rel.setSource(actor);
		assertNull("Relationship source multiplicity is not null", rel.getSourceMultiplicity());
		ActorDTO actor2 = facade.createActorDTO();
		DomainElementRelationshipDTO rel2 = actor2.addRelatedActor(actor);
		assertNotNull("Relationship source multiplicity is null", rel2.getTargetMultiplicity());
		assertEquals("Relationship source multiplicity not set properly", DomainElementRelationshipDTO.DEFAULT_MULTIPLICITY, rel2.getTargetMultiplicity());
		rel2.setTargetMultiplicity("1..*");
		assertEquals("Relationship source multiplicity not set properly", "1..*", rel2.getTargetMultiplicity());
	}

	/**
	 * Test method for {@link eu.redseeds.scl.model.rsl.rsldomainelements.domainelements.DomainElementRelationshipDTO#delete()}.
	 */
	@Test
	public void testDelete() {
		ActorDTO actor = facade.createActorDTO();
		NotionDTO notion = facade.createNotionDTO();
		DomainElementRelationshipDTO rel = notion.addRelatedActor(actor);
		assertEquals("DomainElementRelationshipDTOList does not have expected size", 1, notion.getDomainElementRelationshipDTOList().size());
		assertEquals("DomainElementRelationshipDTOList does not have expected size", 1, actor.getDomainElementRelationshipDTOList().size());
		rel.delete();
		assertEquals("DomainElementRelationshipDTOList does not have expected size", 0, notion.getDomainElementRelationshipDTOList().size());
		assertEquals("DomainElementRelationshipDTOList does not have expected size", 0, actor.getDomainElementRelationshipDTOList().size());
	}

	/**
	 * Test method for {@link eu.redseeds.scl.model.rsl.rsldomainelements.domainelements.DomainElementRelationshipDTO#isDirected()}.
	 */
	@Test
	public void testIsDirected() {
		DomainElementRelationshipDTO rel = facade.createDomainElementRelationshipDTO();
		rel.setDirected(true);
		assertEquals("directed property not retrieved properly", true, rel.isDirected());
		rel.setDirected(false);
		assertEquals("directed property not retrieved properly", false, rel.isDirected());
	}

	/**
	 * Test method for {@link eu.redseeds.scl.model.rsl.rsldomainelements.domainelements.DomainElementRelationshipDTO#setDirected(boolean)}.
	 */
	@Test
	public void testSetDirected() {
		DomainElementRelationshipDTO rel = facade.createDomainElementRelationshipDTO();
		rel.setDirected(true);
		assertEquals("directed property not set properly", true, rel.isDirected());
		rel.setDirected(false);
		assertEquals("directed property not set properly", false, rel.isDirected());
	}

}
