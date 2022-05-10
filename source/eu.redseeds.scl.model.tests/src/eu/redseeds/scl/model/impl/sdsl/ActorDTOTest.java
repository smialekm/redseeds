package eu.redseeds.scl.model.impl.sdsl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import eu.redseeds.scl.model.AbstractModelTest;
import eu.redseeds.scl.model.sdsl.ActorDTO;
import eu.redseeds.scl.model.sdsl.PackageDTO;
import eu.redseeds.scl.uml.usecases.Actor;

public class ActorDTOTest extends AbstractModelTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Override
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
	 * Test method for {@link eu.redseeds.scl.model.sdsl.ActorDTO#ActorDTO()}.
	 */
	@Test
	public void testCreate() {
		ActorDTO act = (ActorDTO) sclGraph.createUml$usecases$Actor();
		assertNotNull("Creation failed", act);
		assertTrue("Creation failed", ((Actor) act).getId() > 0);

	}

	/**
	 * Test method for {@link eu.redseeds.scl.model.sdsl.ActorDTO#getParent()}.
	 */
	@Test
	public void testGetParent() {
		PackageDTO pack = (PackageDTO) sclGraph
				.createUml$classes$kernel$Package();
		assertNotNull("List is null", pack.getActorDTOList());
		assertTrue("List is not empty", pack.getActorDTOList().size() == 0);
		ActorDTO act = (ActorDTO) sclGraph.createUml$usecases$Actor();
		assertTrue("Parent is not null", act.getParent() == null);
		pack.addActor(act);
		assertTrue("List is empty", pack.getActorDTOList().size() == 1);
		assertTrue("Parent is null", act.getParent() != null);
		assertEquals("Wrong parent", pack, act.getParent());
	}

	/**
	 * Test method for {@link eu.redseeds.scl.model.sdsl.ActorDTO#getName()}.
	 */
	@Test
	public void testGetName() {
		ActorDTO act = (ActorDTO) sclGraph.createUml$usecases$Actor();
		assertNull("New name should be empty", act.getName());
		String name = "name";
		act.setName(name);
		assertEquals("Corrupt name after setting", name, act.getName());
	}

	/**
	 * Test method for
	 * {@link eu.redseeds.scl.model.sdsl.ActorDTO#setName(String)}.
	 */
	@Test
	public void testSetName() {
		ActorDTO act = (ActorDTO) sclGraph.createUml$usecases$Actor();
		assertNull("New name should be empty", act.getName());
		String name = "name";
		act.setName(name);
		assertEquals("Corrupt name after setting", name, act.getName());
	}

	/**
	 * Test method for {@link eu.redseeds.scl.model.sdsl.ActorDTO#toString()}.
	 */
	@Test
	public void testToString() {
		ActorDTO act = (ActorDTO) sclGraph.createUml$usecases$Actor();
		String name = "name";
		act.setName(name);
		assertEquals("toString is not equal name", name, act.toString());
		assertEquals("toString is not equal name", act.getName(), act
				.toString());
	}

}
