/**
 * 
 */
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
import eu.redseeds.scl.model.sdsl.ClassDTO;
import eu.redseeds.scl.model.sdsl.ComponentDTO;
import eu.redseeds.scl.model.sdsl.InterfaceDTO;
import eu.redseeds.scl.model.sdsl.PackageDTO;

public class PackageDTOTest extends AbstractModelTest {

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
	 * Test method for
	 * {@link eu.redseeds.scl.model.sdsl.PackageDTO#PackageDTO()}.
	 */
	@Test
	public void testCreate() {
		PackageDTO pack = (PackageDTO) sclGraph
				.createUml$classes$kernel$Package();
		assertNotNull("Creation failed", pack);
	}

	/**
	 * Test method for {@link eu.redseeds.scl.model.sdsl.PackageDTO#getName()}.
	 */
	@Test
	public void testGetName() {
		PackageDTO pack = (PackageDTO) sclGraph
				.createUml$classes$kernel$Package();
		assertNull("New name should be empty", pack.getName());
		String name = "name";
		pack.setName(name);
		assertEquals("Corrupt name after setting", name, pack.getName());
	}

	/**
	 * Test method for
	 * {@link eu.redseeds.scl.model.sdsl.PackageDTO#setName(String)}.
	 */
	@Test
	public void testSetName() {
		PackageDTO pack = (PackageDTO) sclGraph
				.createUml$classes$kernel$Package();
		String name = "name";
		pack.setName(name);
		assertEquals("Corrupt name after setting", name, pack.getName());
	}

	/**
	 * Test method for
	 * {@link eu.redseeds.scl.model.sdsl.PackageDTO#getPackageDTOList()}.
	 */
	@Test
	public void testGetPackageDTOList() {
		PackageDTO pack = (PackageDTO) sclGraph
				.createUml$classes$kernel$Package();
		assertNotNull("List is null", pack.getPackageDTOList());
		assertTrue("List is not empty", pack.getPackageDTOList().size() == 0);
		PackageDTO pack2 = (PackageDTO) sclGraph
				.createUml$classes$kernel$Package();
		pack.addPackage(pack2);
		assertTrue("List is not empty", pack.getPackageDTOList().size() == 1);
	}

	/**
	 * Test method for
	 * {@link eu.redseeds.scl.model.sdsl.PackageDTO#getActorDTOList()}.
	 */
	@Test
	public void testGetActorDTOList() {
		PackageDTO pack = (PackageDTO) sclGraph
				.createUml$classes$kernel$Package();
		assertNotNull("List is null", pack.getActorDTOList());
		assertTrue("List is not empty", pack.getActorDTOList().size() == 0);
	}

	/**
	 * Test method for
	 * {@link eu.redseeds.scl.model.sdsl.PackageDTO#getClassDTOList()}.
	 */
	@Test
	public void testGetClassDTOList() {
		PackageDTO pack = (PackageDTO) sclGraph
				.createUml$classes$kernel$Package();
		assertNotNull("List is null", pack.getClassDTOList());
		assertTrue("List is not empty", pack.getClassDTOList().size() == 0);
	}

	/**
	 * Test method for
	 * {@link eu.redseeds.scl.model.sdsl.PackageDTO#getComponentDTOList()}.
	 */
	@Test
	public void testGetComponentDTOList() {
		PackageDTO pack = (PackageDTO) sclGraph
				.createUml$classes$kernel$Package();
		assertNotNull("List is null", pack.getComponentDTOList());
		assertTrue("List is not empty", pack.getComponentDTOList().size() == 0);
	}

	/**
	 * Test method for
	 * {@link eu.redseeds.scl.model.sdsl.PackageDTO#getInterfaceDTOList()}.
	 */
	@Test
	public void testGetInterfaceDTOList() {
		PackageDTO pack = (PackageDTO) sclGraph
				.createUml$classes$kernel$Package();
		assertNotNull("List is null", pack.getInterfaceDTOList());
		assertTrue("List is not empty", pack.getInterfaceDTOList().size() == 0);
	}

	/**
	 * Test method for {@link eu.redseeds.scl.model.sdsl.PackageDTO#toString()}.
	 */
	@Test
	public void testToString() {
		PackageDTO pack = (PackageDTO) sclGraph
				.createUml$classes$kernel$Package();
		String name = "name";
		pack.setName(name);
		assertEquals("toString is not equal name", name, pack.toString());
		assertEquals("toString is not equal name", pack.getName(), pack
				.toString());
	}

	/**
	 * Test method for {@link eu.redseeds.scl.model.sdsl.PackageDTO#getParent()}
	 * .
	 */
	@Test
	public void testGetParent() {
		PackageDTO pack = (PackageDTO) sclGraph
				.createUml$classes$kernel$Package();
		assertNotNull("List is null", pack.getPackageDTOList());
		assertTrue("List is not empty", pack.getPackageDTOList().size() == 0);
		PackageDTO packOther = (PackageDTO) sclGraph
				.createUml$classes$kernel$Package();
		PackageDTO packOther2 = (PackageDTO) sclGraph
				.createUml$classes$kernel$Package();
		pack.addPackage(packOther);
		packOther.addPackage(packOther2);
		assertTrue("List is empty", pack.getPackageDTOList().size() == 1);
		assertTrue("List is empty", packOther.getPackageDTOList().size() == 1);
		assertTrue("List is not empty",
				packOther2.getPackageDTOList().size() == 0);
		assertTrue("Parent is not null", pack.getParent() == null);
		assertTrue("Parent is null", packOther.getParent() != null);
		assertTrue("Parent is null", packOther2.getParent() != null);
		assertEquals("Wrong parent", pack, packOther.getParent());
		assertEquals("Wrong parent", packOther, packOther2.getParent());
	}

	/**
	 * Test method for
	 * {@link eu.redseeds.scl.model.sdsl.PackageDTO#addActor(eu.redseeds.scl.model.sdsl.ActorDTO)}
	 * .
	 */
	@Test
	public void testAddActor() {
		PackageDTO pack = (PackageDTO) sclGraph
				.createUml$classes$kernel$Package();
		assertNotNull("List is null", pack.getActorDTOList());
		assertTrue("List is not empty", pack.getActorDTOList().size() == 0);
		ActorDTO act = (ActorDTO) sclGraph.createUml$usecases$Actor();
		pack.addActor(act);
		assertTrue("List is empty", pack.getActorDTOList().size() == 1);
		assertTrue("List is not empty", pack.getComponentDTOList().size() == 0);
		assertTrue("List is not empty", pack.getInterfaceDTOList().size() == 0);
	}

	/**
	 * Test method for
	 * {@link eu.redseeds.scl.model.sdsl.PackageDTO#addClass(eu.redseeds.scl.model.sdsl.ClassDTO)}
	 * .
	 */
	@Test
	public void testAddClass() {
		PackageDTO pack = (PackageDTO) sclGraph
				.createUml$classes$kernel$Package();
		assertNotNull("List is null", pack.getClassDTOList());
		assertTrue("List is not empty", pack.getClassDTOList().size() == 0);
		ClassDTO cl = (ClassDTO) sclGraph.createClass();
		pack.addClass(cl);
		assertTrue("List is empty", pack.getClassDTOList().size() == 1);
		assertTrue("List is not empty", pack.getComponentDTOList().size() == 0);
		assertTrue("List is not empty", pack.getInterfaceDTOList().size() == 0);
	}

	/**
	 * Test method for
	 * {@link eu.redseeds.scl.model.sdsl.PackageDTO#addInterface(eu.redseeds.scl.model.sdsl.InterfaceDTO)}
	 * .
	 */
	@Test
	public void testAddInterface() {
		PackageDTO pack = (PackageDTO) sclGraph
				.createUml$classes$kernel$Package();
		assertNotNull("List is null", pack.getInterfaceDTOList());
		assertTrue("List is not empty", pack.getInterfaceDTOList().size() == 0);
		InterfaceDTO interf = (InterfaceDTO) sclGraph.createInterface();
		pack.addInterface(interf);
		assertTrue("List is empty", pack.getInterfaceDTOList().size() == 1);
		assertTrue("List is not empty", pack.getComponentDTOList().size() == 0);
		assertTrue("List is not empty", pack.getClassDTOList().size() == 0);
	}

	/**
	 * Test method for
	 * {@link eu.redseeds.scl.model.sdsl.PackageDTO#addComponent(eu.redseeds.scl.model.sdsl.ComponentDTO)}
	 * .
	 */
	@Test
	public void testAddComponent() {
		PackageDTO pack = (PackageDTO) sclGraph
				.createUml$classes$kernel$Package();
		assertNotNull("List is null", pack.getComponentDTOList());
		assertTrue("List is not empty", pack.getComponentDTOList().size() == 0);
		ComponentDTO component = (ComponentDTO) sclGraph.createComponent();
		pack.addComponent(component);
		assertTrue("List is empty", pack.getComponentDTOList().size() == 1);
		assertTrue("List is not empty", pack.getClassDTOList().size() == 0);
		assertTrue("List is not empty", pack.getInterfaceDTOList().size() == 0);
	}

	/**
	 * Test method for
	 * {@link eu.redseeds.scl.model.sdsl.PackageDTO#addPackage(eu.redseeds.scl.model.sdsl.PackageDTO)}
	 * .
	 */
	@Test
	public void testAddPackage() {
		PackageDTO pack = (PackageDTO) sclGraph
				.createUml$classes$kernel$Package();
		assertNotNull("List is null", pack.getPackageDTOList());
		assertTrue("List is not empty", pack.getPackageDTOList().size() == 0);
		PackageDTO packOther = (PackageDTO) sclGraph
				.createUml$classes$kernel$Package();
		PackageDTO packOther2 = (PackageDTO) sclGraph
				.createUml$classes$kernel$Package();
		pack.addPackage(packOther);
		pack.addPackage(packOther2);
		assertTrue("List is empty", pack.getPackageDTOList().size() == 2);
		assertTrue("List is not empty", pack.getComponentDTOList().size() == 0);
		assertTrue("List is not empty", pack.getClassDTOList().size() == 0);
		assertTrue("List is not empty", pack.getInterfaceDTOList().size() == 0);
	}

}
