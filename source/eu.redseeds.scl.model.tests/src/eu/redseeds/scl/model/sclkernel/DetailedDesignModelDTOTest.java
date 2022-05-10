package eu.redseeds.scl.model.sclkernel;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import eu.redseeds.scl.model.AbstractModelTest;
import eu.redseeds.scl.model.impl.sclkernel.DetailedDesignModelDTOImpl;
import eu.redseeds.scl.model.sdsl.PackageDTO;
import eu.redseeds.scl.sclkernel.DetailedDesignModel;

public class DetailedDesignModelDTOTest extends AbstractModelTest {

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
	 * {@link eu.redseeds.scl.model.sclkernel.DetailedDesignModelDTO#DetailedDesignModelDTO()}
	 * .
	 */
	@Test
	public void testCreate() {
		DetailedDesignModelDTO detd = (DetailedDesignModelDTO) sclGraph
				.createDetailedDesignModel();
		assertNotNull("Creation failed", detd);
		assertTrue("Creation failed", ((DetailedDesignModel) detd).getId() > 0);
	}

	/**
	 * Test method for
	 * {@link eu.redseeds.scl.model.sclkernel.DetailedDesignModelDTO#toString()}
	 * .
	 */
	@Test
	public void testToString() {
		DetailedDesignModelDTO detd = (DetailedDesignModelDTO) sclGraph
				.createDetailedDesignModel();
		detd.toString().equalsIgnoreCase(
				DetailedDesignModelDTOImpl.DETAILED_DESIGN_NAME);
	}

	/**
	 * Test method for
	 * {@link eu.redseeds.scl.model.sclkernel.DetailedDesignModelDTO#getPackageDTOList()}
	 * .
	 */
	@Test
	public void testGetPackageDTOList() {
		DetailedDesignModelDTO detd = (DetailedDesignModelDTO) sclGraph
				.createDetailedDesignModel();
		assertNotNull("List is null", detd.getPackageDTOList());
		assertTrue("List is not empty", detd.getPackageDTOList().size() == 0);
	}

	/**
	 * Test method for
	 * {@link eu.redseeds.scl.model.sclkernel.DetailedDesignModelDTO#addPackage(eu.redseeds.scl.model.sdsl.PackageDTO)}
	 * .
	 */
	@Test
	public void testAddPackage() {
		DetailedDesignModelDTO detd = (DetailedDesignModelDTO) sclGraph
				.createDetailedDesignModel();
		PackageDTO pack = (PackageDTO) sclGraph
				.createUml$classes$kernel$Package();
		detd.addPackage(pack);
		assertTrue("List is empty", detd.getPackageDTOList().size() > 0);
		assertTrue("List is corrupt", detd.getPackageDTOList().get(0).equals(
				pack));
	}

}
