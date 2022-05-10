package eu.remics.recovery.model.domainlogic.usecases;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import eu.redseeds.common.SCProjectHelper;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.DomainStatementDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.ComplexVerbPhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.NounPhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.SimpleVerbPhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.terms.NounDTO;
import eu.redseeds.scl.rsl.rsldomainelements.phrases.ComplexVerbPhrase;
import eu.redseeds.scl.rsl.rsldomainelements.phrases.SimpleVerbPhrase;
import eu.remics.recovery.model.RecoveryModelHelper;
import eu.remics.recovery.model.dto.TerminologyOperationFailureException;
import eu.remics.recovery.model.tests.AbstractRecoveryModelTest;

public class MNotionTest extends AbstractRecoveryModelTest {
	
	@Test
	public void testMergeDoubleNotions() throws TerminologyOperationFailureException{
		RecoveryModelHelper rmh = RecoveryModelHelper.instance();
		String name = "abc";
		NotionDTO not=facade.createNotionDTO();
		NounPhraseDTO np= facade.createNounPhraseDTO();
		NounDTO n1 = facade.createNounDTO();
		np.setNoun(n1);
		np.setNounText(name);
		n1.setName(name);
		not.setNamePhrase(np);
		rmh.getNotionsPackage("Notions").addNotionDTO(not);
		NotionDTO not2=facade.createNotionDTO();
		NounPhraseDTO np2= facade.createNounPhraseDTO();	
		NounDTO n2 = facade.createNounDTO();
		np2.setNoun(n2);
		np2.setNounText(name+"s");
		n2.setName(name+"s");
		not2.setNamePhrase(np2);
		rmh.getNotionsPackage("Notions").addNotionDTO(not2);
		MNotion.merges(not, not2, name);
		assertEquals(1,rmh.getBussinessLayerFacade().findNouns(name).size());
		assertEquals(1,facade.findNotions(n1).size());
	}
	
	@Test
	public void testMergeDoubleNotions2() throws TerminologyOperationFailureException{
		RecoveryModelHelper rmh = RecoveryModelHelper.instance();
		String name = "ab";
		NotionDTO not=facade.createNotionDTO();
		NounPhraseDTO np= facade.createNounPhraseDTO();
		NounDTO n1 = facade.createNounDTO();
		np.setNoun(n1);
		np.setNounText(name);
		n1.setName(name);
		not.setNamePhrase(np);
		rmh.getNotionsPackage("Notions").addNotionDTO(not);
		NotionDTO not2=facade.createNotionDTO();
		NounPhraseDTO np2= facade.createNounPhraseDTO();	
		NounDTO n2 = facade.createNounDTO();
		np2.setNoun(n2);
		np2.setNounText(name+"s");
		n2.setName(name+"s");
		not2.setNamePhrase(np2);
		rmh.getNotionsPackage("Notions").addNotionDTO(not2);
		NotionDTO not3=facade.createNotionDTO();
		not3.setName("notion3");
		if (SCProjectHelper.getSenseAutoAddAndAssigmentFlag()) not3.getNamePhrase().getNoun().autoAddAndAssignSense();
		else not3.getNamePhrase().getNoun().autoAssignSense();
		MNotion.merges(not, not3, name);
		assertEquals(1,rmh.getBussinessLayerFacade().findNouns(name).size());
		assertEquals(1,facade.findNotions(n1).size());
	}
	
	@Test
	public void testMerge() throws TerminologyOperationFailureException{
		NotionDTO notion1 = facade.createNotionDTO();
		notion1.setName("notion1");
		if (SCProjectHelper.getSenseAutoAddAndAssigmentFlag()) notion1.getNamePhrase().getNoun().autoAddAndAssignSense();
		else notion1.getNamePhrase().getNoun().autoAssignSense();
		NotionDTO notion2 = facade.createNotionDTO();
		notion2.setName("notion2");
		if (SCProjectHelper.getSenseAutoAddAndAssigmentFlag()) notion2.getNamePhrase().getNoun().autoAddAndAssignSense();
		else notion2.getNamePhrase().getNoun().autoAssignSense();
		
		SimpleVerbPhraseDTO svp = facade.createSimpleVerbPhraseDTO();
		svp.setVerb(facade.createVerbDTO("selects"));
		svp.getVerb().autoAssignSense();
		svp.setObject(facade.createNounPhraseDTO());
		svp.getObject().setNounText("Notion1");
		svp.getObject().connect();
		svp.getObject().getNoun().autoAssignSense();
		DomainStatementDTO ds1=facade.createDomainStatementDTO(svp.copy(true));
		notion1.addDomainStatementDTO(ds1);
		
		svp = facade.createSimpleVerbPhraseDTO();
		svp.setVerb(facade.createVerbDTO("enters"));
		svp.getVerb().autoAssignSense();
		svp.setObject(facade.createNounPhraseDTO());
		svp.getObject().setNounText("Notion1");
		svp.getObject().connect();
		svp.getObject().getNoun().autoAssignSense();
		DomainStatementDTO ds2=facade.createDomainStatementDTO(svp.copy(true));
		notion1.addDomainStatementDTO(ds2);
		
		ComplexVerbPhraseDTO cvp = facade.createComplexVerbPhraseDTO();
		cvp.setPreposition(facade.createPrepostitionDTO("from"));
		cvp.getPreposition().autoAssignSense();
		cvp.setObject(facade.createNounPhraseDTO());
		cvp.getObject().setNounText("Notion2");
		cvp.getObject().connect();
		cvp.getObject().getNoun().autoAssignSense();
		svp = facade.createSimpleVerbPhraseDTO();
		cvp.setSimpleVerbPhrase(svp);
		svp.setVerb(facade.createVerbDTO("selects"));
		svp.getVerb().autoAssignSense();
		svp.setObject(facade.createNounPhraseDTO());
		svp.getObject().setNounText("something");
		svp.getObject().connect();
		if (SCProjectHelper.getSenseAutoAddAndAssigmentFlag()) svp.getObject().getNoun().autoAddAndAssignSense();
		else svp.getObject().getNoun().autoAssignSense();
		DomainStatementDTO ds3=facade.createDomainStatementDTO(cvp.copy(true));
		notion2.addDomainStatementDTO(ds3);
		
		ComplexVerbPhraseDTO cvpp=(ComplexVerbPhraseDTO) cvp.copy(true);
		cvpp.getObject().setNoun(facade.createNounDTO("notion12"));
		cvpp.getObject().setNounText("notion12");
		if (SCProjectHelper.getSenseAutoAddAndAssigmentFlag()) cvpp.getObject().getNoun().autoAddAndAssignSense();
		else cvpp.getObject().getNoun().autoAssignSense();
		
		svp = facade.createSimpleVerbPhraseDTO();
		svp.setVerb(facade.createVerbDTO("enters"));
		svp.getVerb().autoAssignSense();
		svp.setObject(facade.createNounPhraseDTO());
		svp.getObject().setNounText("Notion2");
		svp.getObject().connect();
		svp.getObject().getNoun().autoAssignSense();
		DomainStatementDTO ds4=facade.createDomainStatementDTO(svp.copy(true));
		notion2.addDomainStatementDTO(ds4);
		
		NotionDTO attr1 = facade.createNotionDTO();
		attr1.setName("attr1");
		NotionDTO attr2 = facade.createNotionDTO();
		attr2.setName("attr2");
		NotionDTO attr3 = facade.createNotionDTO();
		attr3.setName("attr3");
		notion1.addNotionDTOAttribute(attr1);
		notion1.addNotionDTOAttribute(attr2);
		notion2.addNotionDTOAttribute(attr2);
		notion2.addNotionDTOAttribute(attr3);		
		notion1=MNotion.merges(notion1, notion2, "notion12");
		assertEquals("notion12",notion1.getName());
		assertEquals(4,notion1.getDomainStatementDTOList().size());
		assertTrue(notion1.getDomainStatementDTOList().contains(ds1));
		assertNotNull(notion1.getDomainStatementDTO(cvpp));
		assertTrue(notion1.getDomainStatementDTOList().contains(ds2));
		for (DomainStatementDTO ds: notion1.getDomainStatementDTOList()){
			if (ds instanceof ComplexVerbPhraseDTO){
				assertEquals("notion12",((ComplexVerbPhraseDTO) ds.getPhraseDTO()).getObject().getNoun().getName());
				assertEquals("notion12",((ComplexVerbPhraseDTO) ds.getPhraseDTO()).getObject().getNounText());
			}
			else if (ds instanceof SimpleVerbPhraseDTO){
				assertEquals("notion12",((SimpleVerbPhraseDTO) ds.getPhraseDTO()).getObject().getNoun().getName());
				assertEquals("notion12",((SimpleVerbPhraseDTO) ds.getPhraseDTO()).getObject().getNounText());
			}
		}
		assertEquals(3,notion1.getNotionDTOAttributeList().size());
		assertTrue(notion1.getNotionDTOAttributeList().contains(attr1));
		assertTrue(notion1.getNotionDTOAttributeList().contains(attr2));
		assertTrue(notion1.getNotionDTOAttributeList().contains(attr3));
		long uid=notion1.getNamePhrase().getNoun().getSynonymUid();
		for (DomainStatementDTO ds :notion1.getDomainStatementDTOList()) if (!(ds.getPhraseDTO() instanceof NounPhraseDTO)) assertEquals(uid,(ds.getPhraseDTO() instanceof SimpleVerbPhraseDTO)?((SimpleVerbPhraseDTO) ds.getPhraseDTO()).getObject().getNoun().getSynonymUid():((ComplexVerbPhraseDTO) ds.getPhraseDTO()).getObject().getNoun().getSynonymUid());
		for (SimpleVerbPhrase phr:facade.getSimpleVerbPhraseVertices()) if (!((SimpleVerbPhraseDTO)phr).getObject().getNounText().equals("something")) assertEquals("notion12",((SimpleVerbPhraseDTO)phr).getObject().getNounText());
		for (ComplexVerbPhrase phr:facade.getComplexVerbPhraseVertices()) assertEquals("notion12",((ComplexVerbPhraseDTO)phr).getObject().getNounText());
	}
	
}
