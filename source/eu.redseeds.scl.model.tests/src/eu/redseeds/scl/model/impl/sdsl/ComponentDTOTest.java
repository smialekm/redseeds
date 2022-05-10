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
import eu.redseeds.scl.model.sdsl.ComponentDTO;
import eu.redseeds.scl.model.sdsl.PackageDTO;
import eu.redseeds.scl.uml.components.basiccomponents.Component;

public class ComponentDTOTest extends AbstractModelTest {

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
	 * {@link eu.redseeds.scl.model.sdsl.ComponentDTO#ComponentDTO()}.
	 */
	@Test
	public void testCreate() {
		ComponentDTO component = (ComponentDTO) sclGraph.createComponent();
		assertNotNull("Creation failed", component);
		assertTrue("Creation failed", ((Component) component).getId() > 0);
	}

	/**
	 * Test method for {@link eu.redseeds.scl.model.sdsl.ComponentDTO#getName()}
	 * .
	 */
	@Test
	public void testGetName() {
		ComponentDTO component = (ComponentDTO) sclGraph.createComponent();
		assertNull("New name should be empty", component.getName());
		String name = "name";
		component.setName(name);
		assertEquals("Corrupt name after setting", name, component.getName());
	}

	/**
	 * Test method for
	 * {@link eu.redseeds.scl.model.sdsl.ComponentDTO#setName(java.lang.String)}
	 * .
	 */
	@Test
	public void testSetName() {
		ComponentDTO component = (ComponentDTO) sclGraph.createComponent();
		assertNull("New name should be empty", component.getName());
		String name = "name";
		component.setName(name);
		assertEquals("Corrupt name after setting", name, component.getName());
	}

	/**
	 * Test method for
	 * {@link eu.redseeds.scl.model.sdsl.ComponentDTO#toString()}.
	 */
	@Test
	public void testToString() {
		ComponentDTO component = (ComponentDTO) sclGraph.createComponent();
		String name = "name";
		component.setName(name);
		assertEquals("toString is not equal name", name, component.toString());
		assertEquals("toString is not equal name", component.getName(),
				component.toString());
	}

	/**
	 * Test method for
	 * {@link eu.redseeds.scl.model.sdsl.ComponentDTO#getParent()}.
	 */
	@Test
	public void testGetParent() {
		PackageDTO pack = (PackageDTO) sclGraph
				.createUml$classes$kernel$Package();
		assertNotNull("List is null", pack.getClassDTOList());
		assertTrue("List is not empty", pack.getClassDTOList().size() == 0);
		ComponentDTO component = (ComponentDTO) sclGraph.createComponent();
		assertTrue("Parent is not null", component.getParent() == null);
		pack.addComponent(component);
		assertTrue("List is empty", pack.getComponentDTOList().size() == 1);
		assertTrue("List is not empty", pack.getClassDTOList().size() == 0);
		assertTrue("Parent is null", component.getParent() != null);
		assertEquals("Wrong parent", pack, component.getParent());
	}

}
