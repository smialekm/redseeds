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
import eu.redseeds.scl.model.sdsl.ClassDTO;
import eu.redseeds.scl.model.sdsl.OperationDTO;
import eu.redseeds.scl.model.sdsl.PackageDTO;
import eu.redseeds.scl.uml.classes.kernel.Class;

public class ClassDTOTest extends AbstractModelTest {

	private static String OPERATION_NAME = "method";

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
	 * Test method for {@link eu.redseeds.scl.model.sdsl.ClassDTO#ClassDTO()}.
	 */
	@Test
	public void testCreate() {
		ClassDTO cl = (ClassDTO) sclGraph.createClass();
		assertNotNull("Creation failed", cl);
		assertTrue("Creation failed", ((Class) cl).getId() > 0);

	}

	/**
	 * Test method for {@link eu.redseeds.scl.model.sdsl.ClassDTO#getParent()}.
	 */
	@Test
	public void testGetParent() {
		PackageDTO pack = (PackageDTO) sclGraph
				.createUml$classes$kernel$Package();
		assertNotNull("List is null", pack.getClassDTOList());
		assertTrue("List is not empty", pack.getClassDTOList().size() == 0);
		ClassDTO cl = (ClassDTO) sclGraph.createClass();
		assertTrue("Parent is not null", cl.getParent() == null);
		pack.addClass(cl);
		assertTrue("List is empty", pack.getClassDTOList().size() == 1);
		assertTrue("Parent is null", cl.getParent() != null);
		assertEquals("Wrong parent", pack, cl.getParent());
	}

	/**
	 * Test method for {@link eu.redseeds.scl.model.sdsl.ClassDTO#getName()}.
	 */
	@Test
	public void testGetName() {
		ClassDTO cl = (ClassDTO) sclGraph.createClass();
		assertNull("New name should be empty", cl.getName());
		String name = "name";
		cl.setName(name);
		assertEquals("Corrupt name after setting", name, cl.getName());
	}

	/**
	 * Test method for
	 * {@link eu.redseeds.scl.model.sdsl.ClassDTO#setName(String)}.
	 */
	@Test
	public void testSetName() {
		ClassDTO cl = (ClassDTO) sclGraph.createClass();
		assertNull("New name should be empty", cl.getName());
		String name = "name";
		cl.setName(name);
		assertEquals("Corrupt name after setting", name, cl.getName());
	}

	/**
	 * Test method for {@link eu.redseeds.scl.model.sdsl.ClassDTO#toString()}.
	 */
	@Test
	public void testToString() {
		ClassDTO cl = (ClassDTO) sclGraph.createClass();
		String name = "name";
		cl.setName(name);
		assertEquals("toString is not equal name", name, cl.toString());
		assertEquals("toString is not equal name", cl.getName(), cl.toString());
	}

	/**
	 * Test method for
	 * {@link eu.redseeds.scl.model.sdsl.ClassDTO#getOperationDTOList()}.
	 */
	@Test
	public void testGetOperationDTOList() {
		ClassDTO cl = (ClassDTO) sclGraph.createClass();
		OperationDTO oper1 = (OperationDTO) sclGraph.createOperation();
		assertNotNull("Created operation is null", oper1);
		oper1.setName(OPERATION_NAME);
		assertTrue("operation list is not empty", cl.getOperationDTOList()
				.size() == 0);
		cl.addOperation(oper1);
		assertTrue("operation list is empty",
				cl.getOperationDTOList().size() == 1);
		assertEquals("retrieved wrong operation", oper1, cl
				.getOperationDTOList().get(0));
		assertEquals("retrieved wrong operation name", OPERATION_NAME + "()",
				oper1.getName());
		// assertEquals("retrieved wrong operation name", OPERATION_NAME,
		// oper1.toString());
	}

}
