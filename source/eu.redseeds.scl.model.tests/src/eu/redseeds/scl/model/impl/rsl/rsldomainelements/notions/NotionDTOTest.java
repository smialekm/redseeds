package eu.redseeds.scl.model.impl.rsl.rsldomainelements.notions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import eu.redseeds.common.Constants;
import eu.redseeds.sc.terminology.model.RemoteJGWNL;
import eu.redseeds.sc.terminology.model.TermSenseDTO;
import eu.redseeds.scl.model.AbstractModelTest;
import eu.redseeds.scl.model.rsl.rsldomainelements.actors.ActorDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.DomainStatementDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionsPackageDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.PrimitiveDataTypeDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.ComplexVerbPhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.NounPhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.SimpleVerbPhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.systemelements.SystemElementDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.terms.NounDTO;
import eu.redseeds.scl.model.traceability.TraceableObjectDTO;
import eu.redseeds.scl.rsl.rsldomainelements.notions.AttributeDataTypes;
import eu.redseeds.scl.rsl.rsldomainelements.notions.Notion;

public class NotionDTOTest extends AbstractModelTest {
	
	private final static String TEST_NAME = "notion_name";
	private final static String TEST_WORDNET_NAME = "running"; //has to exist in wordnet/terminology

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
	 * Test method for {@link eu.redseeds.scl.model.impl.rsl.rsldomainelements.notions.NotionDTOImpl#NotionDTOImpl()}.
	 */
	@Test
	public void testCreate() {
		NotionDTO notion = facade.createNotionDTO();
		assertNotNull("creating Actor failed", notion);
		assertTrue(((Notion)notion).getId() > 0);
	}

	/**
	 * Test method for {@link eu.redseeds.scl.model.impl.rsl.rsldomainelements.notions.NotionDTOImpl#delete()}.
	 */
	@Test
	public void testDelete() {
		NotionDTO notion = facade.createNotionDTO();
		notion.delete();
		NounPhraseDTO phrase = facade.createNounPhraseDTO();
		DomainStatementDTO stat = facade.createDomainStatementDTO(phrase);
		NotionDTO notion2 = facade.createNotionDTO();
		notion2.addDomainStatementDTO(stat);
		notion2.delete();
	}

	/**
	 * Test method for {@link eu.redseeds.scl.model.impl.rsl.rsldomainelements.notions.NotionDTOImpl#getName()}.
	 */
	@Test
	public void testGetName() {
		NotionDTO notion = facade.createNotionDTO();
		assertEquals("notion's name is not null", null, notion.getName());
		notion.setName(TEST_NAME);
		assertEquals("setting notion's name failed", TEST_NAME, notion.getName());
	}

	/**
	 * Test method for {@link eu.redseeds.scl.model.impl.rsl.rsldomainelements.notions.NotionDTOImpl#setName(java.lang.String)}.
	 */
	@Test
	public void testSetName() {
		NotionDTO notion = facade.createNotionDTO();
		assertEquals("notion's name is not null", null, notion.getName());
		notion.setName(TEST_NAME);
		assertEquals("setting notion's name failed", TEST_NAME, notion.getName());
		notion.setName(TEST_WORDNET_NAME);
		assertEquals("re-setting notion's name failed", TEST_WORDNET_NAME, notion.getName());
		
	}

	/**
	 * Test method for {@link eu.redseeds.scl.model.impl.rsl.rsldomainelements.notions.NotionDTOImpl#toString()}.
	 */
	@Test
	public void testToString() {
		NotionDTO notion = facade.createNotionDTO();
		notion.setName(TEST_NAME);
		assertEquals("setting notion's name failed", TEST_NAME, notion.toString());
		assertEquals("setting notion's name failed", notion.getName(), notion.toString());
	}

	/**
	 * Test method for {@link eu.redseeds.scl.model.impl.rsl.rsldomainelements.notions.NotionDTOImpl#getDomainStatementDTOList()}.
	 */
	@Test
	public void testGetDomainStatementDTOList() {
		NotionDTO notion = facade.createNotionDTO();
		NounPhraseDTO phrase = facade.createNounPhraseDTO();
		DomainStatementDTO stat = facade.createDomainStatementDTO(phrase);
		assertTrue("List is not empty", notion.getDomainStatementDTOList().size() == 0);
		notion.addDomainStatementDTO(stat);
		assertTrue("List size is not equal 1", notion.getDomainStatementDTOList().size() == 1);
	}

	/**
	 * Test method for {@link eu.redseeds.scl.model.impl.rsl.rsldomainelements.notions.NotionDTOImpl#getParent()}.
	 */
	@Test
	public void testGetParent() {
		NotionDTO notion = facade.createNotionDTO();
		NotionsPackageDTO notPack = facade.createNotionsPackageDTO();
		notPack.addNotionDTO(notion);
		assertNotNull("notion.getParent failed - null", notion.getParent());
		assertEquals("notion.getParent failed", notPack, notion.getParent());
	}

	/**
	 * Test method for {@link eu.redseeds.scl.model.impl.rsl.rsldomainelements.notions.NotionDTOImpl#setParent(eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionsPackageDTO)}.
	 */
	@Test
	public void testSetParent() {
		NotionDTO notion = facade.createNotionDTO();
		NotionsPackageDTO notPack = facade.createNotionsPackageDTO();
		notion.setParent(notPack);
		assertNotNull("notion.getParent failed - null", notion.getParent());
		assertEquals("notion.getParent failed", notPack, notion.getParent());
	}

	/**
	 * Test method for {@link eu.redseeds.scl.model.impl.rsl.rsldomainelements.notions.NotionDTOImpl#getSpecificationPath()}.
	 */
	@Test
	public void testGetSpecificationPath() {
		NotionDTO notion = facade.createNotionDTO();
		NotionsPackageDTO notPack = facade.createNotionsPackageDTO();
		notPack.setName(TEST_NAME);
		notPack.addNotionDTO(notion);
		assertEquals("Wrong specification path", "\\"+TEST_NAME, notion.getSpecificationPath());
	}

	/**
	 * Test method for {@link eu.redseeds.scl.model.impl.rsl.rsldomainelements.notions.NotionDTOImpl#getDescription()}.
	 */
	@Test
	public void testGetDescription() {
		NotionDTO notion = facade.createNotionDTO();
		assertEquals("notion's description is not null", "", notion.getDescription());
		notion.setDescription(TEST_NAME);
		assertEquals("setting notion's description failed", TEST_NAME+"\n", notion.getDescription());
	}

	/**
	 * Test method for {@link eu.redseeds.scl.model.impl.rsl.rsldomainelements.notions.NotionDTOImpl#setDescription(java.lang.String)}.
	 */
	@Test
	public void testSetDescription() {
		NotionDTO notion = facade.createNotionDTO();
		assertEquals("notion's description is not null", "", notion.getDescription());
		notion.setDescription(TEST_NAME);
		assertEquals("setting notion's description failed", TEST_NAME+"\n", notion.getDescription());
		notion.setDescription(TEST_NAME + " " + TEST_NAME);
		assertEquals("re-setting notion's description failed", TEST_NAME + " " + TEST_NAME + "\n", notion.getDescription());
	}

	/**
	 * Test method for {@link eu.redseeds.scl.model.impl.rsl.rsldomainelements.notions.NotionDTOImpl#getDomainElementRelationshipDTOList()}.
	 */
	@Test
	public void testGetDomainElementRelationshipDTOList() {
		NotionDTO notion = facade.createNotionDTO();
		NotionDTO notion2 = facade.createNotionDTO();
		NotionDTO notion3 = facade.createNotionDTO();
		assertTrue("List is not empty", notion.getDomainElementRelationshipDTOList().size() == 0);
		assertTrue("List is not empty", notion2.getDomainElementRelationshipDTOList().size() == 0);
		assertTrue("List is not empty", notion3.getDomainElementRelationshipDTOList().size() == 0);
		notion.addRelatedNotion(notion2);
		assertTrue("List size is not equal 1", notion.getDomainElementRelationshipDTOList().size() == 1);
		assertTrue("List size is not equal 1", notion2.getDomainElementRelationshipDTOList().size() == 1);
		assertTrue("List is not empty", notion3.getDomainElementRelationshipDTOList().size() == 0);
		notion.addRelatedNotion(notion3);
		assertTrue("List size is not equal 2", notion.getDomainElementRelationshipDTOList().size() == 2);
		assertTrue("List size is not equal 1", notion2.getDomainElementRelationshipDTOList().size() == 1);
		assertTrue("List size is not equal 1", notion3.getDomainElementRelationshipDTOList().size() == 1);
		
	}

	/**
	 * Test method for {@link eu.redseeds.scl.model.impl.rsl.rsldomainelements.notions.NotionDTOImpl#addRelatedActor(eu.redseeds.scl.model.rsl.rsldomainelements.actors.ActorDTO)}.
	 */
	@Test
	public void testAddRelatedActor() {
		NotionDTO notion = facade.createNotionDTO();
		ActorDTO actor = facade.createActorDTO();
		assertTrue("List is not empty", notion.getDomainElementRelationshipDTOList().size() == 0);
		assertTrue("List is not empty", actor.getDomainElementRelationshipDTOList().size() == 0);
		notion.addRelatedActor(actor);
		assertTrue("List size is not equal 1", notion.getDomainElementRelationshipDTOList().size() == 1);
		assertTrue("List size is not equal 1", actor.getDomainElementRelationshipDTOList().size() == 1);
	}

	/**
	 * Test method for {@link eu.redseeds.scl.model.impl.rsl.rsldomainelements.notions.NotionDTOImpl#addRelatedNotion(eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionDTO)}.
	 */
	@Test
	public void testAddRelatedNotion() {
		NotionDTO notion = facade.createNotionDTO();
		NotionDTO notion2 = facade.createNotionDTO();
		assertTrue("List is not empty", notion.getDomainElementRelationshipDTOList().size() == 0);
		assertTrue("List is not empty", notion2.getDomainElementRelationshipDTOList().size() == 0);
		notion.addRelatedNotion(notion2);
		assertTrue("List size is not equal 1", notion.getDomainElementRelationshipDTOList().size() == 1);
		assertTrue("List size is not equal 1", notion2.getDomainElementRelationshipDTOList().size() == 1);
	}

	/**
	 * Test method for {@link eu.redseeds.scl.model.impl.rsl.rsldomainelements.notions.NotionDTOImpl#addRelatedSystemElement(eu.redseeds.scl.model.rsl.rsldomainelements.systemelements.SystemElementDTO)}.
	 */
	@Test
	public void testAddRelatedSystemElement() {
		NotionDTO notion = facade.createNotionDTO();
		SystemElementDTO sel = facade.createSystemElementDTO();
		assertTrue("List is not empty", notion.getDomainElementRelationshipDTOList().size() == 0);
		assertTrue("List is not empty", sel.getDomainElementRelationshipDTOList().size() == 0);
		notion.addRelatedSystemElement(sel);
		assertTrue("List size is not equal 1", notion.getDomainElementRelationshipDTOList().size() == 1);
		assertTrue("List size is not equal 1", sel.getDomainElementRelationshipDTOList().size() == 1);
	}

	/**
	 * Test method for {@link eu.redseeds.scl.model.impl.rsl.rsldomainelements.notions.NotionDTOImpl#getNotionSpecialisationDTOList()}.
	 */
	@Test
	public void testGetNotionSpecialisationDTOList() {
		NotionDTO notion = facade.createNotionDTO();
		NotionDTO notion2 = facade.createNotionDTO();
		NotionDTO notion3 = facade.createNotionDTO();
		assertTrue("List is not empty", notion.getDomainElementRelationshipDTOList().size() == 0);
		assertTrue("List is not empty", notion2.getDomainElementRelationshipDTOList().size() == 0);
		assertTrue("List is not empty", notion3.getDomainElementRelationshipDTOList().size() == 0);
		notion.addSpecialisedNotion(notion2);
		assertTrue("List size is not equal 1", notion.getNotionSpecialisationDTOList().size() == 1);
		assertTrue("List size is not equal 1", notion2.getNotionSpecialisationDTOList().size() == 1);
		assertTrue("List is not empty", notion3.getNotionSpecialisationDTOList().size() == 0);
		notion.addGeneralNotion(notion3);
		assertTrue("List size is not equal 2", notion.getNotionSpecialisationDTOList().size() == 2);
		assertTrue("List size is not equal 1", notion2.getNotionSpecialisationDTOList().size() == 1);
		assertTrue("List size is not equal 1", notion3.getNotionSpecialisationDTOList().size() == 1);
	}

	/**
	 * Test method for {@link eu.redseeds.scl.model.impl.rsl.rsldomainelements.notions.NotionDTOImpl#addGeneralNotion(eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionDTO)}.
	 */
	@Test
	public void testAddGeneralNotion() {
		NotionDTO notion = facade.createNotionDTO();
		NotionDTO notion2 = facade.createNotionDTO();
		assertTrue("List is not empty", notion.getNotionSpecialisationDTOList().size() == 0);
		assertTrue("List is not empty", notion2.getNotionSpecialisationDTOList().size() == 0);
		notion.addGeneralNotion(notion2);
		assertTrue("List size is not equal 1", notion.getNotionSpecialisationDTOList().size() == 1);
		assertTrue("List size is not equal 1", notion2.getNotionSpecialisationDTOList().size() == 1);
		assertTrue("Wring general notion", notion.getNotionSpecialisationDTOList().get(0).getGeneralNotion().equals(notion2));
	}

	/**
	 * Test method for {@link eu.redseeds.scl.model.impl.rsl.rsldomainelements.notions.NotionDTOImpl#addSpecialisedNotion(eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionDTO)}.
	 */
	@Test
	public void testAddSpecialisedNotion() {
		NotionDTO notion = facade.createNotionDTO();
		NotionDTO notion2 = facade.createNotionDTO();
		assertTrue("List is not empty", notion.getNotionSpecialisationDTOList().size() == 0);
		assertTrue("List is not empty", notion2.getNotionSpecialisationDTOList().size() == 0);
		notion.addSpecialisedNotion(notion2);
		assertTrue("List size is not equal 1", notion.getNotionSpecialisationDTOList().size() == 1);
		assertTrue("List size is not equal 1", notion2.getNotionSpecialisationDTOList().size() == 1);
		assertTrue("Wrong specialized notion", notion.getNotionSpecialisationDTOList().get(0).getSpecialisedNotion().equals(notion2));
	}

	/**
	 * Test method for {@link eu.redseeds.scl.model.impl.rsl.rsldomainelements.notions.NotionDTOImpl#isNameUnique(java.lang.String)}.
	 */
	@Test
	public void testIsNameUnique() {
		NotionDTO notion = facade.createNotionDTO();
		notion.setName(TEST_NAME);
		assertEquals("setting notion's name failed", TEST_NAME, notion.getName());
		assertFalse("notion name's uniqueness failed", notion.isNameUnique(TEST_NAME));
	}

	/**
	 * Test method for {@link eu.redseeds.scl.model.impl.rsl.rsldomainelements.notions.NotionDTOImpl#addDomainStatementDTO(eu.redseeds.scl.model.rsl.rsldomainelements.notions.DomainStatementDTO)}.
	 */
	@Test
	public void testAddDomainStatementDTO() {
		NotionDTO notion = facade.createNotionDTO();
		assertTrue("List is not empty", notion.getDomainStatementDTOList().size() == 0);
		notion.setName(TEST_WORDNET_NAME);
		NounPhraseDTO phrase = facade.createNounPhraseDTO();
		NounDTO noun = facade.createNounDTO(TEST_WORDNET_NAME);
		phrase.setNoun(noun);
		assertTrue("List size is not equal 1", notion.getDomainStatementDTOList().size() == 1);
		
		NotionDTO notion2 = facade.createNotionDTO();
		assertTrue("List is not empty", notion2.getDomainStatementDTOList().size() == 0);
		assertTrue("List is not empty", notion2.getDomainElementRelationshipDTOList().size() == 0);
		notion2.setName(TEST_WORDNET_NAME+"2");
		ComplexVerbPhraseDTO cvPhrase = facade.createComplexVerbPhraseDTO();
		SimpleVerbPhraseDTO svPhrase = facade.createSimpleVerbPhraseDTO();
		svPhrase.setObject(phrase);
		cvPhrase.setSimpleVerbPhrase(svPhrase);
		DomainStatementDTO stat2 = facade.createDomainStatementDTO(cvPhrase);
		notion2.addDomainStatementDTO(stat2);
		assertTrue("List size is not equal 2", notion2.getDomainStatementDTOList().size() == 2);
		assertTrue("List size is not equal 1", notion2.getDomainElementRelationshipDTOList().size() == 1);
	}

	/**
	 * Test method for {@link eu.redseeds.scl.model.impl.rsl.rsldomainelements.notions.NotionDTOImpl#getDomainStatementDTO(eu.redseeds.scl.model.rsl.rsldomainelements.phrases.PhraseDTO)}.
	 */
	@Test
	public void testGetDomainStatementDTO() {
		assertTrue("Not connected to the Terminology", RemoteJGWNL.getInstance().isConnected());
		NotionDTO notion = facade.createNotionDTO();
		NounPhraseDTO phrase = facade.createNounPhraseDTO();
		NounDTO noun = facade.createNounDTO(TEST_WORDNET_NAME);
		phrase.setNoun(noun);
		TermSenseDTO[] senses = RemoteJGWNL.getInstance().getTermSenses(TEST_WORDNET_NAME, Constants.TERM_NOUN);
		assertTrue("The word "+ TEST_WORDNET_NAME + " has no sense in the Terminology", senses.length > 0);
		noun.setSynonymUid(senses[0].getUid());
		DomainStatementDTO stat = facade.createDomainStatementDTO(phrase);
		assertTrue("List is not empty", notion.getDomainStatementDTOList().size() == 0);
		notion.addDomainStatementDTO(stat);
		assertTrue("List is empty", notion.getDomainStatementDTOList().size() == 1);
		assertNotNull("Domain statement is null", notion.getDomainStatementDTO(phrase));
		assertTrue("Wrong domain statement", stat.getPhraseDTO().equals(notion.getDomainStatementDTO(phrase).getPhraseDTO()));
	}
	
	/**
	 * Test method for {@link eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionDTOImpl#checkIfRelated(Object)}.
	 */
	@Test
	public void testCheckIfRelated() {
		NotionDTO notion = facade.createNotionDTO();
		ActorDTO actor = facade.createActorDTO();
		SystemElementDTO sel = facade.createSystemElementDTO();
		ActorDTO actor2 = facade.createActorDTO();
		notion.addRelatedActor(actor);
		notion.addRelatedSystemElement(sel);
		notion.addRelatedNotion(notion);
		assertTrue("elements are related", notion.checkIfRelated(actor));
		assertTrue("elements are related", notion.checkIfRelated(notion));
		assertTrue("elements are related", notion.checkIfRelated(sel));
		assertFalse("elements are not related", notion.checkIfRelated(actor2));
	}
	
	@Test
	public void testGetTraceabilityLinkDTOList() {
		//TODO add more tests
		NotionDTO notion = facade.createNotionDTO();
		assertNotNull(notion);
		
		assertNotNull("List is null", ((TraceableObjectDTO)notion).getTraceabilityLinkDTOList());
	}
	
	@Test
	public void testGetDataType() {
		NotionDTO notion = facade.createNotionDTO();
		assertNotNull(notion);
		PrimitiveDataTypeDTO dataTypeBool = facade.createPrimitiveDataTypeDTO();
		assertNotNull(dataTypeBool);
		dataTypeBool.setTypeName(AttributeDataTypes.BOOLEAN);
		assertEquals("incorrect datatype", AttributeDataTypes.BOOLEAN, dataTypeBool.getTypeName());
		assertNull("data type already set", notion.getDataType());
		notion.setDataType(dataTypeBool);
		assertNotNull("data type not set", notion.getDataType());
		assertEquals("incorrect datatype", AttributeDataTypes.BOOLEAN, notion.getDataType().getTypeName());
		PrimitiveDataTypeDTO dataTypeInt = facade.createPrimitiveDataTypeDTO();
		assertNotNull(dataTypeInt);
		dataTypeInt.setTypeName(AttributeDataTypes.INTEGER);
		assertEquals("incorrect datatype", AttributeDataTypes.INTEGER, dataTypeInt.getTypeName());
		notion.setDataType(dataTypeInt);
		assertNotNull("data type not set", notion.getDataType());
		assertEquals("incorrect datatype", AttributeDataTypes.INTEGER, notion.getDataType().getTypeName());
//		notion.setDataType(null);
//		assertNull("data type still set", notion.getDataType());
	}
	
	@Test
	public void testSetDataType() {
		testGetDataType();
	}
	
	@Test
	public void testGetNotionDTOAttributeList() {
		NotionDTO notion1 = facade.createNotionDTO();
		NotionDTO notion2 = facade.createNotionDTO();
		NotionDTO notion3 = facade.createNotionDTO();
		PrimitiveDataTypeDTO dataTypeBool = facade.createPrimitiveDataTypeDTO();
		PrimitiveDataTypeDTO dataTypeInt = facade.createPrimitiveDataTypeDTO();
		assertNotNull(notion1);
		assertNotNull(notion2);
		assertNotNull(notion3);
		assertNotNull(dataTypeBool);
		assertNotNull(dataTypeInt);
		notion2.setDataType(dataTypeBool);
		notion3.setDataType(dataTypeInt);
		assertTrue("List is not empty", notion1.getNotionDTOAttributeList().size() == 0);
		assertTrue("Parent not null", notion2.getNotionAttributeParents().isEmpty());
		assertTrue("Parent not null", notion3.getNotionAttributeParents().isEmpty());
		notion1.addNotionDTOAttribute(notion2);
		assertTrue("List has wrong size", notion1.getNotionDTOAttributeList().size() == 1);
		assertTrue("Wrong element in the list", notion1.getNotionDTOAttributeList().get(0).equals(notion2));
		assertTrue("Wrong parent", notion2.getNotionAttributeParents().contains(notion1));
		notion1.addNotionDTOAttribute(notion3);
		assertTrue("Wrong parent", notion3.getNotionAttributeParents().contains(notion1));
		assertTrue("List has wrong size", notion1.getNotionDTOAttributeList().size() == 2);
		notion1.removeNotionDTOAttribute(notion2);
		assertTrue("List has wrong size", notion1.getNotionDTOAttributeList().size() == 1);
		assertTrue("Wrong element in the list", notion1.getNotionDTOAttributeList().get(0).equals(notion3));
		assertTrue("Parent not null", notion2.getNotionAttributeParents().isEmpty());
		notion1.removeNotionDTOAttribute(notion3);
		assertTrue("List has wrong size", notion1.getNotionDTOAttributeList().size() == 0);
		notion1.removeNotionDTOAttribute(notion3);
		assertTrue("List has wrong size", notion1.getNotionDTOAttributeList().size() == 0);
	}
	
	@Test
	public void testAddNotionDTOAttribute() {
		testGetNotionDTOAttributeList();
	}
	
	@Test
	public void testRemoveNotionDTOAttribute() {
		testGetNotionDTOAttributeList();
	}
	
	@Test
	public void testGetNotionAttributeParent() {
		testGetNotionDTOAttributeList();
	}

}
