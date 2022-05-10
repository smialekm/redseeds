package eu.redseeds.scl.model.impl.rsl.rsldomainelements.actors;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import eu.redseeds.scl.model.AbstractModelTest;
import eu.redseeds.scl.model.rsl.rsldomainelements.actors.ActorDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.actors.ActorsPackageDTO;
import eu.redseeds.scl.rsl.rsldomainelements.actors.ActorsPackage;

public class ActorsPackageDTOTest extends AbstractModelTest {
	
	private final static String TEST_NAME = "actor_package_name";

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
	 * Test method for {@link eu.redseeds.scl.model.rsl.rsldomainelements.actors.ActorsPackageDTO#.ActorsPackageDTO()}.
	 */
	@Test
	public void testCreate() {
		ActorsPackageDTO actPack = facade.createActorsPackageDTO();
		assertNotNull("creating Actors Package failed", actPack);
		assertTrue("creating Actors Package failed", ((ActorsPackage)actPack).getId() > 0);
	}

	/**
	 * Test method for {@link eu.redseeds.scl.model.rsl.rsldomainelements.actors.ActorsPackageDTO#getName()}.
	 */
	@Test
	public void testGetName() {
		ActorsPackageDTO actPack = facade.createActorsPackageDTO();
		assertEquals("getting actor package's name failed", null, actPack.getName());
		actPack.setName(TEST_NAME);
		assertEquals("getting actor package's name failed", TEST_NAME, actPack.getName());
	}

	/**
	 * Test method for {@link eu.redseeds.scl.model.rsl.rsldomainelements.actors.ActorsPackageDTO#getActorsPackageDTOList()}.
	 */
	@Test
	public void testGetActorsPackageDTOList() {
		ActorsPackageDTO pack = facade.createActorsPackageDTO();
		assertNotNull("List is null", pack.getActorsPackageDTOList());
		assertTrue("List is not empty", pack.getActorsPackageDTOList().size() == 0);
		ActorsPackageDTO pack2 = facade.createActorsPackageDTO();
		pack.addActorsPackageDTO(pack2);
		assertTrue("List is empty", pack.getActorsPackageDTOList().size() == 1);
	}

	/**
	 * Test method for {@link eu.redseeds.scl.model.rsl.rsldomainelements.actors.ActorsPackageDTO#getActorDTOList()}.
	 */
	@Test
	public void testGetActorDTOList() {
		ActorsPackageDTO pack = facade.createActorsPackageDTO();
		assertNotNull("List is null", pack.getActorDTOList());
		assertTrue("List is not empty", pack.getActorDTOList().size() == 0);
	}

	/**
	 * Test method for {@link eu.redseeds.scl.model.rsl.rsldomainelements.actors.ActorsPackageDTO#delete()}.
	 */
	@Test
	public void testDelete() {
		ActorsPackageDTO pack = facade.createActorsPackageDTO();
		ActorsPackageDTO pack2 = facade.createActorsPackageDTO();
		assertTrue("List is not empty", pack.getActorsPackageDTOList().size() == 0);
		pack.addActorsPackageDTO(pack2);
		assertTrue("List is empty", pack.getActorsPackageDTOList().size() == 1);
		pack2.delete();
		assertTrue("List is not empty", pack.getActorsPackageDTOList().size() == 0);
		
	}

	/**
	 * Test method for {@link eu.redseeds.scl.model.rsl.rsldomainelements.actors.ActorsPackageDTO#deleteRecursively()}.
	 */
	@Test
	public void testDeleteRecursively() {
		ActorsPackageDTO pack = facade.createActorsPackageDTO();
		ActorsPackageDTO pack2 = facade.createActorsPackageDTO();
		ActorsPackageDTO pack3 = facade.createActorsPackageDTO();
		ActorDTO actor = facade.createActorDTO();
		assertTrue("List is not empty", pack.getActorsPackageDTOList().size() == 0);
		pack.addActorsPackageDTO(pack2);
		pack2.addActorsPackageDTO(pack3);
		pack.addActorDTO(actor);
		assertTrue("List is empty", pack.getActorsPackageDTOList().size() == 1);
		pack.deleteRecursively();
		assertTrue("List is not empty", pack.getActorsPackageDTOList().size() == 0);
		assertTrue("List is not empty", pack2.getActorsPackageDTOList().size() == 0);
	}

	/**
	 * Test method for {@link eu.redseeds.scl.model.rsl.rsldomainelements.actors.ActorsPackageDTO#addActorDTO(eu.redseeds.scl.model.rsl.rsldomainelements.actors.ActorDTO)}.
	 */
	@Test
	public void testAddActorDTO() {
		ActorDTO actor = facade.createActorDTO();
		ActorsPackageDTO actPack = facade.createActorsPackageDTO();
		assertNotNull("List is null", actPack.getActorDTOList());
		assertTrue("List is not empty", actPack.getActorDTOList().size() == 0);
		actPack.addActorDTO(actor);
		assertTrue("List is empty", actPack.getActorDTOList().size() == 1);
	}

	/**
	 * Test method for {@link eu.redseeds.scl.model.rsl.rsldomainelements.actors.ActorsPackageDTO#setName(java.lang.String)}.
	 */
	@Test
	public void testSetName() {
		ActorsPackageDTO actPack = facade.createActorsPackageDTO();
		assertEquals("getting actor package's name failed", null, actPack.getName());
		actPack.setName(TEST_NAME);
		assertEquals("getting actor package's name failed", TEST_NAME, actPack.getName());
	}

	/**
	 * Test method for {@link eu.redseeds.scl.model.rsl.rsldomainelements.actors.ActorsPackageDTO#addActorsPackageDTO(eu.redseeds.scl.model.rsl.rsldomainelements.actors.ActorsPackageDTO)}.
	 */
	@Test
	public void testAddActorsPackageDTO() {
		ActorsPackageDTO pack = facade.createActorsPackageDTO();
		assertNotNull("List is null", pack.getActorsPackageDTOList());
		assertTrue("List is not empty", pack.getActorsPackageDTOList().size() == 0);
		ActorsPackageDTO pack2 = facade.createActorsPackageDTO();
		pack.addActorsPackageDTO(pack2);
		assertTrue("List is empty", pack.getActorsPackageDTOList().size() == 1);
	}

	/**
	 * Test method for {@link eu.redseeds.scl.model.rsl.rsldomainelements.actors.ActorsPackageDTO#removeChildActorDTO(eu.redseeds.scl.model.rsl.rsldomainelements.actors.ActorDTO)}.
	 */
	@Test
	public void testRemoveChildActorDTO() {
		ActorDTO actor = facade.createActorDTO();
		ActorsPackageDTO actPack = facade.createActorsPackageDTO();
		actPack.addActorDTO(actor);
		assertTrue("List is empty", actPack.getActorDTOList().size() == 1);
		actPack.removeChildActorDTO(actor);
		assertNotNull("List is null", actPack.getActorDTOList());
		assertTrue("List is not empty", actPack.getActorDTOList().size() == 0);
	}

	/**
	 * Test method for {@link eu.redseeds.scl.model.rsl.rsldomainelements.actors.ActorsPackageDTO#removeChildActorsPackageDTO(eu.redseeds.scl.model.rsl.rsldomainelements.actors.ActorsPackageDTO)}.
	 */
	@Test
	public void testRemoveChildActorsPackageDTO() {
		ActorsPackageDTO pack = facade.createActorsPackageDTO();
		ActorsPackageDTO pack2 = facade.createActorsPackageDTO();
		pack.addActorsPackageDTO(pack2);
		assertTrue("List is empty", pack.getActorsPackageDTOList().size() == 1);
		pack.removeChildActorsPackageDTO(pack2);
		assertNotNull("List is null", pack.getActorsPackageDTOList());
		assertTrue("List is not empty", pack.getActorsPackageDTOList().size() == 0);
	}

	/**
	 * Test method for {@link eu.redseeds.scl.model.rsl.rsldomainelements.actors.ActorsPackageDTO#getParent()}.
	 */
	@Test
	public void testGetParent() {
		ActorsPackageDTO pack = facade.createActorsPackageDTO();
		assertNotNull("List is null", pack.getActorsPackageDTOList());
		assertTrue("List is not empty", pack.getActorsPackageDTOList().size() == 0);
		ActorsPackageDTO pack2 = facade.createActorsPackageDTO();
		pack.addActorsPackageDTO(pack2);
		assertTrue("List is empty", pack.getActorsPackageDTOList().size() == 1);
		assertTrue("Parent is not equal real parent", pack2.getParent().equals(pack));
	}

	/**
	 * Test method for {@link eu.redseeds.scl.model.rsl.rsldomainelements.actors.ActorsPackageDTO#setParent(eu.redseeds.scl.model.rsl.rsldomainelements.actors.ActorsPackageDTO)}.
	 */
	@Test
	public void testSetParent() {
		ActorsPackageDTO pack = facade.createActorsPackageDTO();
		assertNotNull("List is null", pack.getActorsPackageDTOList());
		assertTrue("List is not empty", pack.getActorsPackageDTOList().size() == 0);
		ActorsPackageDTO pack2 = facade.createActorsPackageDTO();
		pack2.setParent(pack);
		assertTrue("List is empty", pack.getActorsPackageDTOList().size() == 1);
		assertTrue("Parent is not equal real parent", pack2.getParent().equals(pack));
		ActorsPackageDTO pack3 = facade.createActorsPackageDTO();
		pack3.setParent(pack2);
		assertTrue("Parent is not equal real parent", pack3.getParent().equals(pack2));
		pack2.setParent(pack3);
		assertTrue("Parent is not equal real parent", pack2.getParent().equals(pack3));
		assertTrue("Parent is not equal real parent", pack3.getParent().equals(pack));
	}

	/**
	 * Test method for {@link eu.redseeds.scl.model.rsl.rsldomainelements.actors.ActorsPackageDTO#toString()}.
	 */
	@Test
	public void testToString() {
		ActorsPackageDTO actPack = facade.createActorsPackageDTO();
		assertEquals("getting actor package's name failed", null, actPack.getName());
		actPack.setName(TEST_NAME);
		assertEquals("getting actor package's name failed", TEST_NAME, actPack.toString());
		assertEquals("getting actor package's name failed", actPack.getName(), actPack.toString());
	}

	/**
	 * Test method for {@link eu.redseeds.scl.model.rsl.rsldomainelements.actors.ActorsPackageDTO#getSpecificationPath()}.
	 */
	@Test
	public void testGetSpecificationPath() {
		ActorsPackageDTO pack = facade.createActorsPackageDTO();
		pack.setName(TEST_NAME);
		assertEquals("Wrong specification path", "\\"+TEST_NAME, pack.getSpecificationPath());
		ActorsPackageDTO pack2 = facade.createActorsPackageDTO();
		pack2.setName("sub"+TEST_NAME);
		pack.addActorsPackageDTO(pack2);
		assertEquals("Wrong specification path", "\\"+TEST_NAME+"\\"+"sub"+TEST_NAME, pack2.getSpecificationPath());
	}

}
