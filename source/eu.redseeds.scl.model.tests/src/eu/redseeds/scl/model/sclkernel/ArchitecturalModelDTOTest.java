/**
 * 
 */
package eu.redseeds.scl.model.sclkernel;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import eu.redseeds.scl.model.AbstractModelTest;
import eu.redseeds.scl.model.impl.sclkernel.ArchitecturalModelDTOImpl;
import eu.redseeds.scl.model.sdsl.PackageDTO;
import eu.redseeds.scl.sclkernel.ArchitecturalModel;

public class ArchitecturalModelDTOTest extends AbstractModelTest {

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
	 * {@link eu.redseeds.scl.model.sclkernel.ArchitecturalModelDTO#ArchitecturalModelDTO()}
	 * .
	 */
	@Test
	public void testCreate() {
		ArchitecturalModelDTO archm = (ArchitecturalModelDTO) sclGraph
				.createArchitecturalModel();
		assertNotNull("Creation failed", archm);
		assertTrue("Creation failed", ((ArchitecturalModel) archm).getId() > 0);
	}

	/**
	 * Test method for
	 * {@link eu.redseeds.scl.model.sclkernel.ArchitecturalModelDTO#toString()}.
	 */
	@Test
	public void testToString() {
		ArchitecturalModelDTO archm = (ArchitecturalModelDTO) sclGraph
				.createArchitecturalModel();
		archm.toString().equalsIgnoreCase(
				ArchitecturalModelDTOImpl.ARCHITECTURE_NAME);
	}

	/**
	 * Test method for
	 * {@link eu.redseeds.scl.model.sclkernel.ArchitecturalModelDTO#getPackageDTOList()}
	 * .
	 */
	@Test
	public void testGetPackageDTOList() {
		ArchitecturalModelDTO archm = (ArchitecturalModelDTO) sclGraph
				.createArchitecturalModel();
		assertNotNull("List is null", archm.getPackageDTOList());
		assertTrue("List is not empty", archm.getPackageDTOList().size() == 0);
	}

	/**
	 * Test method for
	 * {@link eu.redseeds.scl.model.sclkernel.ArchitecturalModelDTO#addPackage(eu.redseeds.scl.model.sdsl.PackageDTO)}
	 * .
	 */
	@Test
	public void testAddPackage() {
		ArchitecturalModelDTO archm = (ArchitecturalModelDTO) sclGraph
				.createArchitecturalModel();
		PackageDTO pack = (PackageDTO) sclGraph
				.createUml$classes$kernel$Package();
		archm.addPackage(pack);
		assertTrue("List is empty", archm.getPackageDTOList().size() > 0);
		assertTrue("List is corrupt", archm.getPackageDTOList().get(0).equals(
				pack));
	}

}
