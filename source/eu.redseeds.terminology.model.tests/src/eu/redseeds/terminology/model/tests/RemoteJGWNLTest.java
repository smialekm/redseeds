package eu.redseeds.terminology.model.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import eu.redseeds.common.Constants;
import eu.redseeds.sc.terminology.model.RemoteJGWNL;

/**
 * tests for RemoteJGWNL class
 *
 */
public class RemoteJGWNLTest {
	
	private static String invalidRMIAddress1 = "1.0.0.10";
	private static String invalidRMIAddress2 = "blablah";
	private static String invalidRMIAddress3 = "http://192.0.0.1/JGWNL";
	
	private static String testTermNameNounVerb = "running";
	private static String testTermNameNoun = "system";
	private static String testTermNameModifierDeterminer = "big";
//	private static String testTermNamePreposition = "to";
	private static String testTermNameDoesNotExit = "a long name that does nor exist";

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
	 * Test method for {@link eu.redseeds.sc.terminology.model.RemoteJGWNL#getInstance()}.
	 */
	@Test
	public void testGetInstance() {
		assertNotNull("Instance is null", RemoteJGWNL.getInstance()); 
	}

//	/**
//	 * Test method for {@link eu.redseeds.sc.terminology.model.RemoteJGWNL#RemoteJGWNL()}.
//	 */
//	@Test
//	public void testRemoteJGWNL() {
//		fail("Not yet implemented");
//	}

	/**
	 * Test method for {@link eu.redseeds.sc.terminology.model.RemoteJGWNL#connect(java.lang.String)}.
	 */
	@Test
	public void testConnect() {
		assertNotNull("Instance is null", RemoteJGWNL.getInstance());
		assertFalse("Connected, should be not", RemoteJGWNL.getInstance().connect(invalidRMIAddress1));
		assertFalse("Connected, should be not", RemoteJGWNL.getInstance().connect(invalidRMIAddress2));
		assertFalse("Connected, should be not", RemoteJGWNL.getInstance().connect(invalidRMIAddress3));
	}

	/**
	 * Test method for {@link eu.redseeds.sc.terminology.model.RemoteJGWNL#getTermSenses(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testGetTermSenses() {
		assertNotNull("Instance is null", RemoteJGWNL.getInstance());
		assertTrue("Array is empty", RemoteJGWNL.getInstance().getTermSenses(testTermNameNounVerb, Constants.TERM_NOUN).length > 0);
		assertTrue("Array is empty", RemoteJGWNL.getInstance().getTermSenses(testTermNameNounVerb, Constants.TERM_VERB).length > 0);
		assertTrue("Array is empty", RemoteJGWNL.getInstance().getTermSenses(testTermNameNounVerb, Constants.TERM_VERB).length 
				== RemoteJGWNL.getInstance().getTermSenses(testTermNameNounVerb, Constants.TERM_VERB).length);
		assertTrue("Array is empty", RemoteJGWNL.getInstance().getTermSenses(testTermNameNoun, Constants.TERM_NOUN).length > 0);
		assertTrue("Array is not empty", RemoteJGWNL.getInstance().getTermSenses(testTermNameNoun, Constants.TERM_VERB).length == 0);
		assertTrue("Array is not empty", RemoteJGWNL.getInstance().getTermSenses(testTermNameDoesNotExit, Constants.TERM_PREPOSITION).length == 0);
		assertTrue("Array is empty", RemoteJGWNL.getInstance().getTermSenses(testTermNameNoun, Constants.TERM_NOUN).length > 0);
		assertTrue("Array is empty", RemoteJGWNL.getInstance().getTermSenses(testTermNameModifierDeterminer, Constants.TERM_MODIFIER).length > 0);
		assertTrue("Array is empty", RemoteJGWNL.getInstance().getTermSenses(testTermNameModifierDeterminer, Constants.TERM_DETERMINER).length > 0);
		assertTrue("Array is not empty", RemoteJGWNL.getInstance().getTermSenses(testTermNameModifierDeterminer, Constants.TERM_VERB).length == 0);
		assertTrue("Array is empty", RemoteJGWNL.getInstance().getTermSenses(testTermNameModifierDeterminer, Constants.TERM_DETERMINER).length > 0);
//		assertTrue("Array is empty", RemoteJGWNL.getInstance().getTermSenses(testTermNamePreposition, Constants.TERM_PREPOSITION).length > 0);
	}

	/**
	 * Test method for {@link eu.redseeds.sc.terminology.model.RemoteJGWNL#getTermSenseDTO(long)}.
	 */
	@Test
	public void testGetTermSenseDTO() {
		assertNotNull("Instance is null", RemoteJGWNL.getInstance());
		assertTrue("Array is empty", RemoteJGWNL.getInstance().getTermSenses(testTermNameNounVerb, Constants.TERM_NOUN).length > 0);
		long uid = RemoteJGWNL.getInstance().getTermSenses(testTermNameNounVerb, Constants.TERM_NOUN)[0].getUid();
		assertTrue("Term Uid is equal 0", uid != 0);
		assertNotNull("Term is null", RemoteJGWNL.getInstance().getTermSenseDTO(uid));
		assertNotNull("Lemma is null", RemoteJGWNL.getInstance().getTermSenseDTO(uid).getLemma());
		assertNotNull("Sense is null", RemoteJGWNL.getInstance().getTermSenseDTO(uid).getSense());
		assertNotNull("Type is null", RemoteJGWNL.getInstance().getTermSenseDTO(uid).getType());
		assertNotNull("toString() is null", RemoteJGWNL.getInstance().getTermSenseDTO(uid).toString());
	}

	/**
	 * Test method for {@link eu.redseeds.sc.terminology.model.RemoteJGWNL#isConnected()}.
	 */
	@Test
	public void testIsConnected() {
		assertNotNull("Instance is null", RemoteJGWNL.getInstance()); 
		assertTrue("Not connected", RemoteJGWNL.getInstance().isConnected()); 
	}

}
