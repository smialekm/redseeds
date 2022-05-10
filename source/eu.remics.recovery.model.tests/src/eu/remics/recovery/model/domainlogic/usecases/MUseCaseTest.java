package eu.remics.recovery.model.domainlogic.usecases;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.SimpleVerbPhraseDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentations.descriptiverequirementrepresentations.ConstrainedLanguageScenarioDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.controlsentences.ConditionSentenceDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.svosentences.SVOSentenceDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.UseCaseDTO;
import eu.redseeds.scl.rsl.rslrequirementrepresentationsentences.constrainedlanguagesentences.ConstrainedLanguageSentence;
import eu.remics.recovery.model.RecoveryModelHelper;
import eu.remics.recovery.model.tests.AbstractRecoveryModelTest;

public class MUseCaseTest extends AbstractRecoveryModelTest {
	
	@Test
	public void testMerge(){
		RecoveryModelHelper rmh = RecoveryModelHelper.instance();
		SVOSentenceDTO sent1 = facade.createSVOSentenceDTO();
		SimpleVerbPhraseDTO svp = facade.createSimpleVerbPhraseDTO();
		svp.setVerb(facade.createVerbDTO("selects"));
		svp.getVerb().autoAssignSense();
		svp.setObject(facade.createNounPhraseDTO());
		svp.getObject().setNounText("Notion1");
		svp.getObject().connect();
		svp.getObject().getNoun().autoAssignSense();
		sent1.setPredicate(svp);
		sent1.setSubject(facade.createNounPhraseDTO());
		sent1.getSubject().setNounText("User");
		sent1.getSubject().connect();
		sent1.getSubject().getNoun().autoAssignSense();
		SVOSentenceDTO sent2 = facade.createSVOSentenceDTO();
		svp = facade.createSimpleVerbPhraseDTO();
		svp.setVerb(facade.createVerbDTO("enters"));
		svp.getVerb().autoAssignSense();
		svp.setObject(facade.createNounPhraseDTO());
		svp.getObject().setNounText("Notion2");
		svp.getObject().connect();
		svp.getObject().getNoun().autoAssignSense();
		sent2.setPredicate(svp);
		sent2.setSubject(facade.createNounPhraseDTO());
		sent2.getSubject().setNounText("User");
		sent2.getSubject().connect();
		sent2.getSubject().getNoun().autoAssignSense();
		UseCaseDTO uc1 = facade.createUseCaseDTO();
		ConditionSentenceDTO con;
		ConstrainedLanguageScenarioDTO scen1 = facade.createConstrainedLanguageScenarioDTO();
		scen1.setName("scenariusz 1");
		scen1.addScenarioStep(facade.createPreconditionSentenceDTO());
		scen1.addScenarioStep(sent1.copy());
		con= rmh.getBussinessLayerFacade().createConditionSentenceDTO();
		con.setSentenceText("");
		scen1.addScenarioStep(con);
		scen1.addScenarioStep(sent1.copy());
		scen1.addScenarioStep(sent1.copy());
		scen1.addScenarioStep(sent2.copy());
		uc1.addConstrainedLanguageScenario(scen1);
		ConstrainedLanguageScenarioDTO scen1b = facade.createConstrainedLanguageScenarioDTO();
		scen1b.setName("scenariusz 1b");
		scen1b.addScenarioStep(scen1.getScenarioSentenceList().get(0));
		scen1b.addScenarioStep(scen1.getScenarioSentenceList().get(1));
		con= rmh.getBussinessLayerFacade().createConditionSentenceDTO();
		con.setSentenceText("");
		scen1b.addScenarioStep(con);
		scen1b.addScenarioStep(sent2.copy());
		scen1b.addScenarioStep(sent1.copy());
		scen1b.addScenarioStep(sent2.copy());
		uc1.addConstrainedLanguageScenario(scen1b);
		UseCaseDTO uc2 = facade.createUseCaseDTO();
		ConstrainedLanguageScenarioDTO scen2 = facade.createConstrainedLanguageScenarioDTO();
		scen2.setName("scenariusz 2");
		scen2.addScenarioStep(facade.createPreconditionSentenceDTO());
		scen2.addScenarioStep(sent2.copy());
		scen2.addScenarioStep(sent1.copy());
		con= rmh.getBussinessLayerFacade().createConditionSentenceDTO();
		con.setSentenceText("");
		scen2.addScenarioStep(con);
		scen2.addScenarioStep(sent2.copy());
		uc2.addConstrainedLanguageScenario(scen2);
		ConstrainedLanguageScenarioDTO scen2b = facade.createConstrainedLanguageScenarioDTO();
		scen2b.setName("scenariusz 2b");
		scen2b.addScenarioStep(scen2.getScenarioSentenceList().get(0));
		scen2b.addScenarioStep(scen2.getScenarioSentenceList().get(1));
		scen2b.addScenarioStep(scen2.getScenarioSentenceList().get(2));
		con= rmh.getBussinessLayerFacade().createConditionSentenceDTO();
		con.setSentenceText("");
		scen2b.addScenarioStep(con);
		scen2b.addScenarioStep(sent1.copy());
		uc2.addConstrainedLanguageScenario(scen2b);
		uc1=MUseCase.merges(uc1, uc2, "merged", 3, scen1b);
		assertEquals(4,uc1.getConstrainedLanguageScenarioDTOList().size());
		assertEquals(6,uc1.getConstrainedLanguageScenarioDTOList().get(0).getScenarioSentenceList().size());
		assertEquals(7,uc1.getConstrainedLanguageScenarioDTOList().get(1).getScenarioSentenceList().size());
		assertEquals(8,uc1.getConstrainedLanguageScenarioDTOList().get(2).getScenarioSentenceList().size());
		assertEquals(8,uc1.getConstrainedLanguageScenarioDTOList().get(3).getScenarioSentenceList().size());
		assertEquals(((ConstrainedLanguageSentence)uc1.getConstrainedLanguageScenarioDTOList().get(0).getScenarioSentenceList().get(1)).getId(),((ConstrainedLanguageSentence)uc1.getConstrainedLanguageScenarioDTOList().get(1).getScenarioSentenceList().get(1)).getId());
		assertTrue(uc1.getConstrainedLanguageScenarioDTOList().get(0).getScenarioSentenceList().get(2) instanceof ConditionSentenceDTO);
		assertTrue(uc1.getConstrainedLanguageScenarioDTOList().get(1).getScenarioSentenceList().get(2) instanceof ConditionSentenceDTO);
		for (int i =1;i<5;i++){
			assertEquals(((ConstrainedLanguageSentence)uc1.getConstrainedLanguageScenarioDTOList().get(1).getScenarioSentenceList().get(i)).getId(),((ConstrainedLanguageSentence)uc1.getConstrainedLanguageScenarioDTOList().get(2).getScenarioSentenceList().get(i)).getId());
		}
		assertTrue(uc1.getConstrainedLanguageScenarioDTOList().get(1).getScenarioSentenceList().get(5) instanceof ConditionSentenceDTO);
		assertTrue(uc1.getConstrainedLanguageScenarioDTOList().get(2).getScenarioSentenceList().get(5) instanceof ConditionSentenceDTO);
		for (int i =1;i<6;i++){
			assertEquals(((ConstrainedLanguageSentence)uc1.getConstrainedLanguageScenarioDTOList().get(2).getScenarioSentenceList().get(i)).getId(),((ConstrainedLanguageSentence)uc1.getConstrainedLanguageScenarioDTOList().get(3).getScenarioSentenceList().get(i)).getId());
		}
		assertTrue(uc1.getConstrainedLanguageScenarioDTOList().get(2).getScenarioSentenceList().get(6) instanceof ConditionSentenceDTO);
		assertTrue(uc1.getConstrainedLanguageScenarioDTOList().get(3).getScenarioSentenceList().get(6) instanceof ConditionSentenceDTO);
	}

	@Test
	public void testSequentialMerge(){
		RecoveryModelHelper rmh = RecoveryModelHelper.instance();
		SVOSentenceDTO sent1 = facade.createSVOSentenceDTO();
		SimpleVerbPhraseDTO svp = facade.createSimpleVerbPhraseDTO();
		svp.setVerb(facade.createVerbDTO("selects"));
		svp.getVerb().autoAssignSense();
		svp.setObject(facade.createNounPhraseDTO());
		svp.getObject().setNounText("Notion1");
		svp.getObject().connect();
		svp.getObject().getNoun().autoAssignSense();
		sent1.setPredicate(svp);
		sent1.setSubject(facade.createNounPhraseDTO());
		sent1.getSubject().setNounText("User");
		sent1.getSubject().connect();
		sent1.getSubject().getNoun().autoAssignSense();
		SVOSentenceDTO sent2 = facade.createSVOSentenceDTO();
		svp = facade.createSimpleVerbPhraseDTO();
		svp.setVerb(facade.createVerbDTO("enters"));
		svp.getVerb().autoAssignSense();
		svp.setObject(facade.createNounPhraseDTO());
		svp.getObject().setNounText("Notion2");
		svp.getObject().connect();
		svp.getObject().getNoun().autoAssignSense();
		sent2.setPredicate(svp);
		sent2.setSubject(facade.createNounPhraseDTO());
		sent2.getSubject().setNounText("User");
		sent2.getSubject().connect();
		sent2.getSubject().getNoun().autoAssignSense();
		UseCaseDTO uc1 = facade.createUseCaseDTO();
		ConditionSentenceDTO con;
		ConstrainedLanguageScenarioDTO scen1 = facade.createConstrainedLanguageScenarioDTO();
		scen1.setName("scenariusz 1");
		scen1.addScenarioStep(facade.createPreconditionSentenceDTO());
		scen1.addScenarioStep(sent1.copy());
		con= rmh.getBussinessLayerFacade().createConditionSentenceDTO();
		con.setSentenceText("");
		scen1.addScenarioStep(con);
		scen1.addScenarioStep(sent1.copy());
		scen1.addScenarioStep(sent1.copy());
		scen1.addScenarioStep(sent2.copy());
		uc1.addConstrainedLanguageScenario(scen1);
		ConstrainedLanguageScenarioDTO scen1b = facade.createConstrainedLanguageScenarioDTO();
		scen1b.setName("scenariusz 1b");
		scen1b.addScenarioStep(scen1.getScenarioSentenceList().get(0));
		scen1b.addScenarioStep(scen1.getScenarioSentenceList().get(1));
		con= rmh.getBussinessLayerFacade().createConditionSentenceDTO();
		con.setSentenceText("");
		scen1b.addScenarioStep(con);
		scen1b.addScenarioStep(sent2.copy());
		scen1b.addScenarioStep(sent1.copy());
		scen1b.addScenarioStep(sent2.copy());
		uc1.addConstrainedLanguageScenario(scen1b);
		UseCaseDTO uc2 = facade.createUseCaseDTO();
		ConstrainedLanguageScenarioDTO scen2 = facade.createConstrainedLanguageScenarioDTO();
		scen2.setName("scenariusz 2");
		scen2.addScenarioStep(facade.createPreconditionSentenceDTO());
		scen2.addScenarioStep(sent2.copy());
		scen2.addScenarioStep(sent1.copy());
		con= rmh.getBussinessLayerFacade().createConditionSentenceDTO();
		con.setSentenceText("");
		scen2.addScenarioStep(con);
		scen2.addScenarioStep(sent2.copy());
		uc2.addConstrainedLanguageScenario(scen2);
		ConstrainedLanguageScenarioDTO scen2b = facade.createConstrainedLanguageScenarioDTO();
		scen2b.setName("scenariusz 2b");
		scen2b.addScenarioStep(scen2.getScenarioSentenceList().get(0));
		scen2b.addScenarioStep(scen2.getScenarioSentenceList().get(1));
		scen2b.addScenarioStep(scen2.getScenarioSentenceList().get(2));
		con= rmh.getBussinessLayerFacade().createConditionSentenceDTO();
		con.setSentenceText("");
		scen2b.addScenarioStep(con);
		scen2b.addScenarioStep(sent1.copy());
		uc2.addConstrainedLanguageScenario(scen2b);
		MUseCase.partialMerges(uc1, uc2.getConstrainedLanguageScenarioDTOList().get(0), 3, scen1b);
		MUseCase.partialMerges(uc1, uc2.getConstrainedLanguageScenarioDTOList().get(0), 3, uc1.getConstrainedLanguageScenarioDTOList().get(1));
		uc1=MUseCase.endMerges(uc1, uc2, "merged");
		assertEquals(3,uc1.getConstrainedLanguageScenarioDTOList().size());
		assertEquals(6,uc1.getConstrainedLanguageScenarioDTOList().get(0).getScenarioSentenceList().size());
		assertEquals(7,uc1.getConstrainedLanguageScenarioDTOList().get(1).getScenarioSentenceList().size());
		assertEquals(7,uc1.getConstrainedLanguageScenarioDTOList().get(2).getScenarioSentenceList().size());
		assertEquals(((ConstrainedLanguageSentence)uc1.getConstrainedLanguageScenarioDTOList().get(0).getScenarioSentenceList().get(1)).getId(),((ConstrainedLanguageSentence)uc1.getConstrainedLanguageScenarioDTOList().get(1).getScenarioSentenceList().get(1)).getId());
		assertTrue(uc1.getConstrainedLanguageScenarioDTOList().get(0).getScenarioSentenceList().get(2) instanceof ConditionSentenceDTO);
		assertTrue(uc1.getConstrainedLanguageScenarioDTOList().get(1).getScenarioSentenceList().get(2) instanceof ConditionSentenceDTO);
		for (int i =1;i<5;i++){
			assertEquals(((ConstrainedLanguageSentence)uc1.getConstrainedLanguageScenarioDTOList().get(1).getScenarioSentenceList().get(i)).getId(),((ConstrainedLanguageSentence)uc1.getConstrainedLanguageScenarioDTOList().get(2).getScenarioSentenceList().get(i)).getId());
		}
		assertTrue(uc1.getConstrainedLanguageScenarioDTOList().get(1).getScenarioSentenceList().get(5) instanceof ConditionSentenceDTO);
		assertTrue(uc1.getConstrainedLanguageScenarioDTOList().get(2).getScenarioSentenceList().get(5) instanceof ConditionSentenceDTO);
	}

}
