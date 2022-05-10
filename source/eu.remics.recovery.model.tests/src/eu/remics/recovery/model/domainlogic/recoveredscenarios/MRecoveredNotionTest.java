package eu.remics.recovery.model.domainlogic.recoveredscenarios;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import eu.redseeds.common.SCProjectHelper;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.ComplexVerbPhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.NounPhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.SimpleVerbPhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.terms.NounDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.svosentences.SVOSentenceDTO;
import eu.remics.recovery.model.RecoveryModelHelper;
import eu.remics.recovery.model.dto.TerminologyOperationFailureException;
import eu.remics.recovery.model.tests.AbstractRecoveryModelTest;

public class MRecoveredNotionTest extends AbstractRecoveryModelTest {
		
	@Test
	public void testClean() throws TerminologyOperationFailureException{
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
		assertEquals(2,facade.findNotions(n1).size());
		facade.cleanNouns(n1);
		assertEquals(1,rmh.getBussinessLayerFacade().findNouns(name).size());
		assertEquals(2,facade.findNotions(n1).size());
		MRecoveredNotion.cleanNouns(n1);
		assertEquals(1,rmh.getBussinessLayerFacade().findNouns(name).size());
		assertEquals(1,facade.findNotions(n1).size());
	}
	
	@Test
	public void testAdd() throws TerminologyOperationFailureException{
		RecoveryModelHelper rmh = RecoveryModelHelper.instance();
		rmh.createPackagesSystemElementsAndActors();
		SVOSentenceDTO sent = facade.createSVOSentenceDTO();
		SimpleVerbPhraseDTO svp = facade.createSimpleVerbPhraseDTO();
		svp.setVerb(facade.createVerbDTO("selects"));
		svp.getVerb().autoAssignSense();
		svp.setObject(facade.createNounPhraseDTO());
		svp.getObject().setNounText("Something");
		svp.getObject().connect();
		if (SCProjectHelper.getSenseAutoAddAndAssigmentFlag()) svp.getObject().getNoun().autoAddAndAssignSense();
		else if (!svp.getObject().getNoun().autoAssignSense()) svp.getObject().getNoun().setName(svp.getObject().getNoun().getName().toLowerCase());
		ComplexVerbPhraseDTO cvp = facade.createComplexVerbPhraseDTO();
		cvp.setSimpleVerbPhrase(svp);
		cvp.setPreposition(facade.createPrepostitionDTO("from"));
		cvp.setObject(facade.createNounPhraseDTO());
		cvp.getObject().setNounText("Somewhere");
		cvp.getObject().connect();
		if (SCProjectHelper.getSenseAutoAddAndAssigmentFlag()) cvp.getObject().getNoun().autoAddAndAssignSense();
		else if (!svp.getObject().getNoun().autoAssignSense()) cvp.getObject().getNoun().setName(cvp.getObject().getNoun().getName().toLowerCase());
		sent.setPredicate(cvp);
		MRecoveredNotion.addNotion(sent, null);
		assertEquals(1,facade.findNouns(svp.getObject().getNoun()).size());
		NotionDTO not=facade.getNotionDTO(svp.getObject().getNoun());
		assertNotNull(not);
		assertEquals(not.getDomainStatementDTOList().size(),2);
		assertNotNull(not.getDomainStatementDTO(svp));
		assertEquals("select",((SimpleVerbPhraseDTO) not.getDomainStatementDTO(svp).getPhraseDTO()).getVerb().getName());
		assertEquals("something",not.getName());
		assertEquals(1,facade.findNouns(cvp.getObject().getNoun()).size());
		not=facade.getNotionDTO(cvp.getObject().getNoun());
		assertNotNull(not);
		assertEquals(not.getDomainStatementDTOList().size(),2);
		assertNotNull(not.getDomainStatementDTO(cvp));
		assertEquals("from",((ComplexVerbPhraseDTO) not.getDomainStatementDTO(cvp).getPhraseDTO()).getPreposition().getName());
		assertEquals("select",((ComplexVerbPhraseDTO) not.getDomainStatementDTO(cvp).getPhraseDTO()).getSimpleVerbPhrase().getVerb().getName());
		assertEquals("something",((ComplexVerbPhraseDTO) not.getDomainStatementDTO(cvp).getPhraseDTO()).getSimpleVerbPhrase().getObject().getNounText());
		assertEquals("somewhere",not.getName());
	}
	
}
