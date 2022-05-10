/**
 * 
 */
package eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import eu.redseeds.scl.model.AbstractModelTest;

/**
 * @author Administrator
 *
 */
public class RequirementsPackageDTOTest extends AbstractModelTest {
	
	private final static String TEST_NAME = "req_package_name";

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
	 * Test method for {@link eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementsPackageDTO#addRequirement(eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementDTO)}.
	 */
	@Test
	public void testAddRequirement() {
		RequirementDTO req = facade.createRequirementDTO();
		RequirementDTO req2 = facade.createRequirementDTO();
		RequirementsPackageDTO reqPack = facade.createRequirementsPackageDTO();
		assertTrue("Incorrect number of child reqs", reqPack.getRequirements().size() == 0);
		reqPack.addRequirement(req);
		assertTrue("Incorrect number of child reqs", reqPack.getRequirements().size() == 1);
		reqPack.addRequirement(req2);
		assertTrue("Incorrect number of child reqs", reqPack.getRequirements().size() == 2);
	}

	/**
	 * Test method for {@link eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementsPackageDTO#addRequirementsPackage(eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementsPackageDTO)}.
	 */
	@Test
	public void testAddRequirementsPackage() {
		RequirementsPackageDTO reqPack = facade.createRequirementsPackageDTO();
		RequirementsPackageDTO reqPack2 = facade.createRequirementsPackageDTO();
		RequirementsPackageDTO reqPack3 = facade.createRequirementsPackageDTO();
		assertTrue("Incorrect number of child req packages", reqPack.getRequirementsPackages().size() == 0);
		reqPack.addRequirementsPackage(reqPack2);
		assertTrue("Incorrect number of child req packages", reqPack.getRequirementsPackages().size() == 1);
		reqPack.addRequirementsPackage(reqPack3);
		assertTrue("Incorrect number of child req packages", reqPack.getRequirementsPackages().size() == 2);
	}

	/**
	 * Test method for {@link eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementsPackageDTO#getParent()}.
	 */
	@Test
	public void testGetParent() {
		RequirementsPackageDTO reqPack = facade.createRequirementsPackageDTO();
		RequirementsPackageDTO reqPack2 = facade.createRequirementsPackageDTO();
		assertTrue("Incorrect number of child req packages", reqPack.getRequirementsPackages().size() == 0);
		assertEquals("incorrect parent", null, reqPack2.getParent());
		assertEquals("incorrect parent", null, reqPack.getParent());
		reqPack.addRequirementsPackage(reqPack2);
		assertTrue("Incorrect number of child req packages", reqPack.getRequirementsPackages().size() == 1);
		assertEquals("incorrect parent", reqPack, reqPack2.getParent());
	}

	/**
	 * Test method for {@link eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementsPackageDTO#getSpecificationPath()}.
	 */
	@Test
	public void testGetSpecificationPath() {
		RequirementsPackageDTO reqPack = facade.createRequirementsPackageDTO();
		RequirementsPackageDTO reqPack2 = facade.createRequirementsPackageDTO();
		reqPack.setName(TEST_NAME);
		assertEquals("Wrong specification path", "\\"+TEST_NAME, reqPack.getSpecificationPath());
		reqPack2.setName("sub"+TEST_NAME);
		reqPack.addRequirementsPackage(reqPack2);
		assertEquals("Wrong specification path", "\\"+TEST_NAME+"\\"+"sub"+TEST_NAME, reqPack2.getSpecificationPath());
	}

	/**
	 * Test method for {@link eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementsPackageDTO#getRequirements()}.
	 */
	@Test
	public void testGetRequirements() {
		RequirementDTO req = facade.createRequirementDTO();
		RequirementDTO req2 = facade.createRequirementDTO();
		RequirementsPackageDTO reqPack = facade.createRequirementsPackageDTO();
		assertTrue("Incorrect number of child reqs", reqPack.getRequirements().size() == 0);
		reqPack.addRequirement(req);
		assertTrue("Incorrect number of child reqs", reqPack.getRequirements().size() == 1);
		reqPack.addRequirement(req2);
		assertTrue("Incorrect number of child reqs", reqPack.getRequirements().size() == 2);
		assertEquals("Incorrect child req", req, reqPack.getRequirements().get(0));//order?
		assertEquals("Incorrect child req", req2, reqPack.getRequirements().get(1));
	}

	/**
	 * Test method for {@link eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementsPackageDTO#getRequirementsPackages()}.
	 */
	@Test
	public void testGetRequirementsPackages() {
		RequirementsPackageDTO reqPack = facade.createRequirementsPackageDTO();
		RequirementsPackageDTO reqPack2 = facade.createRequirementsPackageDTO();
		RequirementsPackageDTO reqPack3 = facade.createRequirementsPackageDTO();
		assertTrue("Incorrect number of child req packages", reqPack.getRequirementsPackages().size() == 0);
		reqPack.addRequirementsPackage(reqPack2);
		assertTrue("Incorrect number of child req packages", reqPack.getRequirementsPackages().size() == 1);
		reqPack.addRequirementsPackage(reqPack3);
		assertTrue("Incorrect number of child req packages", reqPack.getRequirementsPackages().size() == 2);
		assertEquals("Incorrect child req pack", reqPack2, reqPack.getRequirementsPackages().get(0));//order?
		assertEquals("Incorrect child req pack", reqPack3, reqPack.getRequirementsPackages().get(1));
	}

	/**
	 * Test method for {@link eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementsPackageDTO#setName(java.lang.String)}.
	 */
	@Test
	public void testSetName() {
		RequirementsPackageDTO reqPack = facade.createRequirementsPackageDTO();
		reqPack.setName(TEST_NAME);
		assertTrue("incorrect name", reqPack.getName().equalsIgnoreCase(TEST_NAME));
	}

	/**
	 * Test method for {@link eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementsPackageDTO#getName()}.
	 */
	@Test
	public void testGetName() {
		RequirementsPackageDTO reqPack = facade.createRequirementsPackageDTO();
		reqPack.setName(TEST_NAME);
		assertTrue("incorrect name", reqPack.getName().equalsIgnoreCase(TEST_NAME));
	}

	/**
	 * Test method for {@link eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementsPackageDTO#addUseCase(eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.UseCaseDTO)}.
	 */
	@Test
	public void testAddUseCase() {
		RequirementDTO req = facade.createRequirementDTO();
		RequirementDTO req2 = facade.createRequirementDTO();
		UseCaseDTO uc1 = facade.createUseCaseDTO();
		RequirementsPackageDTO reqPack = facade.createRequirementsPackageDTO();
		assertTrue("Incorrect number of child reqs", reqPack.getRequirements().size() == 0);
		reqPack.addRequirement(req);
		assertTrue("Incorrect number of child reqs", reqPack.getRequirements().size() == 1);
		reqPack.addUseCase(uc1);
		assertTrue("Incorrect number of child reqs", reqPack.getRequirements().size() == 2);
		reqPack.addRequirement(req2);
		assertTrue("Incorrect number of child reqs", reqPack.getRequirements().size() == 3);
	}

	/**
	 * Test method for {@link eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementsPackageDTO#delete()}.
	 */
	@Test
	public void testDelete() {
		RequirementsPackageDTO reqPack = facade.createRequirementsPackageDTO();
		RequirementsPackageDTO reqPack2 = facade.createRequirementsPackageDTO();
		RequirementsPackageDTO reqPack3 = facade.createRequirementsPackageDTO();
		assertTrue("Incorrect number of child req packages", reqPack.getRequirementsPackages().size() == 0);
		reqPack.addRequirementsPackage(reqPack2);
		assertTrue("Incorrect number of child req packages", reqPack.getRequirementsPackages().size() == 1);
		reqPack.addRequirementsPackage(reqPack3);
		assertTrue("Incorrect number of child req packages", reqPack.getRequirementsPackages().size() == 2);
		reqPack2.delete();
		assertTrue("Incorrect number of child req packages", reqPack.getRequirementsPackages().size() == 1);
		assertTrue("incorrect child pack", reqPack.getRequirementsPackages().get(0).equals(reqPack3));
		reqPack3.delete();
		assertTrue("Incorrect number of child req packages", reqPack.getRequirementsPackages().size() == 0);
	}

	/**
	 * Test method for {@link eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementsPackageDTO#deleteRecursively()}.
	 */
	@Test
	public void testDeleteRecursively() {
		RequirementsPackageDTO reqPack = facade.createRequirementsPackageDTO();
		RequirementsPackageDTO reqPack2 = facade.createRequirementsPackageDTO();
		RequirementsPackageDTO reqPack3 = facade.createRequirementsPackageDTO();
		RequirementsPackageDTO reqPack4 = facade.createRequirementsPackageDTO();
		assertTrue("Incorrect number of child req packages", reqPack.getRequirementsPackages().size() == 0);
		reqPack.addRequirementsPackage(reqPack2);
		assertTrue("Incorrect number of child req packages", reqPack.getRequirementsPackages().size() == 1);
		reqPack.addRequirementsPackage(reqPack3);
		reqPack3.addRequirementsPackage(reqPack4);
		assertTrue("Incorrect number of child req packages", reqPack.getRequirementsPackages().size() == 2);
		reqPack2.deleteRecursively();
		assertTrue("Incorrect number of child req packages", reqPack.getRequirementsPackages().size() == 1);
		assertTrue("incorrect child pack", reqPack.getRequirementsPackages().get(0).equals(reqPack3));
		reqPack3.deleteRecursively();
		assertTrue("Incorrect parent pack", reqPack4.getParent() == null);
		assertTrue("Incorrect number of child req packages", reqPack.getRequirementsPackages().size() == 0);
	}

//	/**
//	 * Test method for {@link eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementsPackageDTO#removeChildRequirementDTO(eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementDTO)}.
//	 */
//	@Test
//	public void testRemoveChildRequirementDTO() {
//		fail("Not yet implemented");
//	}
//
//	/**
//	 * Test method for {@link eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementsPackageDTO#removeChildRequirementsPackageDTO(eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementsPackageDTO)}.
//	 */
//	@Test
//	public void testRemoveChildRequirementsPackageDTO() {
//		fail("Not yet implemented");
//	}
//
	/**
	 * Test method for {@link eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementsPackageDTO#setParent(eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementsPackageDTO)}.
	 */
	@Test
	public void testSetParent() {
		RequirementsPackageDTO reqPack = facade.createRequirementsPackageDTO();
		RequirementsPackageDTO reqPack2 = facade.createRequirementsPackageDTO();
		RequirementsPackageDTO reqPack3 = facade.createRequirementsPackageDTO();
		assertTrue("Incorrect number of child req packages", reqPack.getRequirementsPackages().size() == 0);
		reqPack2.setParent(reqPack);
		assertTrue("Incorrect number of child req packages", reqPack.getRequirementsPackages().size() == 1);
		reqPack3.setParent(reqPack);
		assertTrue("Incorrect number of child req packages", reqPack.getRequirementsPackages().size() == 2);
	}

	/**
	 * Test method for {@link eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementsPackageDTO#toString()}.
	 */
	@Test
	public void testToString() {
		RequirementsPackageDTO reqPack = facade.createRequirementsPackageDTO();
		reqPack.setName(TEST_NAME);
		assertTrue("incorrect name", reqPack.getName().equalsIgnoreCase(TEST_NAME));
		assertTrue("incorrect name", reqPack.getName().equalsIgnoreCase(reqPack.toString()));
	}

	/**
	 * Test method for {@link eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementsPackageDTO#getRequirementsSpecificationParent()}.
	 */
	@Test
	public void testGetRequirementsSpecificationParent() {
		RequirementsSpecificationDTO reqSpec = (RequirementsSpecificationDTO)facade.createRequirementsSpecification();
		RequirementsPackageDTO reqPack = facade.createRequirementsPackageDTO();
		assertTrue("Incorrect number of child req packages", reqSpec.getRequirementsPackagesDTOList().size() == 0);
		reqSpec.addRequirementsPackageDTO(reqPack);
		assertTrue("Incorrect number of child req packages", reqSpec.getRequirementsPackagesDTOList().size() == 1);
		assertEquals("Incorrect child req pack", reqPack, reqSpec.getRequirementsPackagesDTOList().get(0));
	}

	/**
	 * Test method for {@link eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementsPackageDTO#setRequirementsSpecificationParent(eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.RequirementsSpecificationDTO)}.
	 */
	@Test
	public void testSetRequirementsSpecificationParent() {
		RequirementsSpecificationDTO reqSpec = (RequirementsSpecificationDTO)facade.createRequirementsSpecification();
		RequirementsSpecificationDTO reqSpec2 = (RequirementsSpecificationDTO)facade.createRequirementsSpecification();
		RequirementsPackageDTO reqPack = facade.createRequirementsPackageDTO();
		RequirementsPackageDTO reqPack2 = facade.createRequirementsPackageDTO();
		reqPack.addRequirementsPackage(reqPack2);
		
		assertTrue("Incorrect number of child req packages", reqSpec.getRequirementsPackagesDTOList().size() == 0);
		assertEquals("Incorrect parent req spec", null, reqPack.getRequirementsSpecificationParent());
		reqPack.setRequirementsSpecificationParent(reqSpec);
		assertTrue("Incorrect number of child req packages", reqSpec.getRequirementsPackagesDTOList().size() == 1);
		assertEquals("Incorrect child req pack", reqPack, reqSpec.getRequirementsPackagesDTOList().get(0));
		assertEquals("Incorrect parent req spec", reqSpec, reqPack.getRequirementsSpecificationParent());
		
		assertTrue("Incorrect number of child req packages", reqSpec2.getRequirementsPackagesDTOList().size() == 0);
		reqPack.setRequirementsSpecificationParent(reqSpec2);
		assertTrue("Incorrect number of child req packages", reqSpec2.getRequirementsPackagesDTOList().size() == 1);
		assertTrue("Incorrect number of child req packages", reqSpec.getRequirementsPackagesDTOList().size() == 0);
		assertEquals("Incorrect child req pack", reqPack, reqSpec2.getRequirementsPackagesDTOList().get(0));
		assertEquals("Incorrect parent req spec", reqSpec2, reqPack.getRequirementsSpecificationParent());
		
		assertTrue("Incorrect number of child req packages", reqSpec2.getRequirementsPackagesDTOList().size() == 1);
		assertTrue("Incorrect number of child req packages", reqPack.getRequirementsPackages().size() == 1);
		reqPack2.setRequirementsSpecificationParent(reqSpec2);
		assertTrue("Incorrect number of child req packages", reqSpec2.getRequirementsPackagesDTOList().size() == 2);
		assertTrue("Incorrect number of child req packages", reqPack.getRequirementsPackages().size() == 0);
		assertEquals("Incorrect child req pack", reqPack, reqSpec2.getRequirementsPackagesDTOList().get(0));
		assertEquals("Incorrect parent req spec", reqSpec2, reqPack2.getRequirementsSpecificationParent());
	}
	
	@Test
	public void testStereotypes() {
		RequirementsPackageDTO reqPack = facade.createRequirementsPackageDTO();
		String stereotype1Name = "stereotype1";
		String stereotype1NameCopy = "stereotype1";
		String stereotype2Name = "stereotype2";
		String stereotype3Name = "stereotype3";
		assertTrue("Incorrect number of stereotypes", reqPack.getStereotypes().size() == 0);
		reqPack.addStereotype(stereotype1Name);
		assertTrue("Incorrect number of stereotypes", reqPack.getStereotypes().size() == 1);
		reqPack.addStereotype(stereotype1NameCopy);
		assertTrue("Incorrect number of stereotypes", reqPack.getStereotypes().size() == 1);
		reqPack.addStereotype(stereotype2Name);
		assertTrue("Incorrect number of stereotypes", reqPack.getStereotypes().size() == 2);
		reqPack.addStereotype(stereotype3Name);
		assertTrue("Incorrect number of stereotypes", reqPack.getStereotypes().size() == 3);
		assertTrue("Does not list stereotypes properly", reqPack.getStereotypes().contains(stereotype1Name));
		assertTrue("Does not list stereotypes properly", reqPack.getStereotypes().contains(stereotype1NameCopy));
		reqPack.removeStereotype(stereotype1Name);
		assertFalse("Does not list stereotypes properly", reqPack.getStereotypes().contains(stereotype1Name));
		assertTrue("Incorrect number of stereotypes", reqPack.getStereotypes().size() == 2);
		reqPack.removeStereotype(stereotype3Name);
		assertTrue("Incorrect number of stereotypes", reqPack.getStereotypes().size() == 1);
		reqPack.removeStereotype(stereotype2Name);
		assertTrue("Incorrect number of stereotypes", reqPack.getStereotypes().size() == 0);
		
	}

}
