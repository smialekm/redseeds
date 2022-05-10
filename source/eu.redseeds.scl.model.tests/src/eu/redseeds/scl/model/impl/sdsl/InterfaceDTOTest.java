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
import eu.redseeds.scl.model.sdsl.InterfaceDTO;
import eu.redseeds.scl.model.sdsl.OperationDTO;
import eu.redseeds.scl.model.sdsl.PackageDTO;
import eu.redseeds.scl.uml.classes.interfaces.Interface;

public class InterfaceDTOTest extends AbstractModelTest {

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
	 * Test method for
	 * {@link eu.redseeds.scl.model.sdsl.InterfaceDTO#InterfaceDTO()}.
	 */
	@Test
	public void testCreate() {
		InterfaceDTO interf = (InterfaceDTO) sclGraph.createInterface();
		assertNotNull("Creation failed", interf);
		assertTrue("Creation failed", ((Interface) interf).getId() > 0);
	}

	/**
	 * Test method for {@link eu.redseeds.scl.model.sdsl.InterfaceDTO#getName()}
	 * .
	 */
	@Test
	public void testGetName() {
		InterfaceDTO interf = (InterfaceDTO) sclGraph.createInterface();
		assertNull("New name should be empty", interf.getName());
		String name = "name";
		interf.setName(name);
		assertEquals("Corrupt name after setting", name, interf.getName());
	}

	/**
	 * Test method for
	 * {@link eu.redseeds.scl.model.sdsl.InterfaceDTO#setName(java.lang.String)}
	 * .
	 */
	@Test
	public void testSetName() {
		InterfaceDTO interf = (InterfaceDTO) sclGraph.createInterface();
		assertNull("New name should be empty", interf.getName());
		String name = "name";
		interf.setName(name);
		assertEquals("Corrupt name after setting", name, interf.getName());
	}

	/**
	 * Test method for
	 * {@link eu.redseeds.scl.model.sdsl.InterfaceDTO#toString()}.
	 */
	@Test
	public void testToString() {
		InterfaceDTO interf = (InterfaceDTO) sclGraph.createInterface();
		String name = "name";
		interf.setName(name);
		assertEquals("toString is not equal name", name, interf.toString());
		assertEquals("toString is not equal name", interf.getName(), interf
				.toString());
	}

	/**
	 * Test method for
	 * {@link eu.redseeds.scl.model.sdsl.InterfaceDTO#getParent()}.
	 */
	@Test
	public void testGetParent() {
		PackageDTO pack = (PackageDTO) sclGraph
				.createUml$classes$kernel$Package();
		assertNotNull("List is null", pack.getClassDTOList());
		assertTrue("List is not empty", pack.getClassDTOList().size() == 0);
		InterfaceDTO interf = (InterfaceDTO) sclGraph.createInterface();
		assertTrue("Parent is not null", interf.getParent() == null);
		pack.addInterface(interf);
		assertTrue("List is empty", pack.getInterfaceDTOList().size() == 1);
		assertTrue("List is not empty", pack.getComponentDTOList().size() == 0);
		assertTrue("List is not empty", pack.getClassDTOList().size() == 0);
		assertTrue("Parent is null", interf.getParent() != null);
		assertEquals("Wrong parent", pack, interf.getParent());
	}

	/**
	 * Test method for
	 * {@link eu.redseeds.scl.model.sdsl.InterfaceDTO#getOperationDTOList()}.
	 */
	@Test
	public void testGetOperationDTOList() {
		InterfaceDTO interf = (InterfaceDTO) sclGraph.createInterface();
		OperationDTO oper1 = (OperationDTO) sclGraph.createOperation();
		assertNotNull("Created operation is null", oper1);
		oper1.setName(OPERATION_NAME);
		assertTrue("operation list is not empty", interf.getOperationDTOList()
				.size() == 0);
		interf.addOperation(oper1);
		assertTrue("operation list is empty", interf.getOperationDTOList()
				.size() == 1);
		assertEquals("retrieved wrong operation", oper1, interf
				.getOperationDTOList().get(0));
		assertEquals("retrieved wrong operation name", OPERATION_NAME + "()",
				oper1.getName());
		// assertEquals("retrieved wrong operation parent", interf,
		// oper1.getParent());
	}

}
