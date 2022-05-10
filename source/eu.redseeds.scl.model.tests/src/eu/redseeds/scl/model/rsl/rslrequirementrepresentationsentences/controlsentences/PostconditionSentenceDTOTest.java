package eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.controlsentences;

import org.junit.Assert;
import org.junit.Test;

import eu.redseeds.scl.model.AbstractModelTest;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentations.descriptiverequirementrepresentations.ConstrainedLanguageScenarioDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.UseCaseDTO;

public class PostconditionSentenceDTOTest extends AbstractModelTest {

	
	@Test
	public void testGetOwningScenarios() {
		UseCaseDTO uc1 = facade.createUseCaseDTO();	
		UseCaseDTO uc2 = facade.createUseCaseDTO();
		PostconditionSentenceDTO postCond = facade.createPostconditionSentenceDTO();

		ConstrainedLanguageScenarioDTO scen1uc1 = facade.createConstrainedLanguageScenarioDTO();
		ConstrainedLanguageScenarioDTO scen2uc1 = facade.createConstrainedLanguageScenarioDTO();
		ConstrainedLanguageScenarioDTO scen1uc2 = facade.createConstrainedLanguageScenarioDTO();
		
		uc1.addConstrainedLanguageScenario(scen1uc1);
		uc1.addConstrainedLanguageScenario(scen2uc1);
		
		uc2.addConstrainedLanguageScenario(scen1uc2);
		
		scen1uc1.addScenarioStep(postCond);
		Assert.assertNotNull("getOwningScenarios fails with null",postCond.getOwningScenarios());
		Assert.assertEquals("getOwningScenarios fails with wrong size",postCond.getOwningScenarios().size(), 1);
		Assert.assertSame("getOwningScenarios fails with wrong scenario",postCond.getOwningScenarios().get(0),scen1uc1);
		
		scen2uc1.addScenarioStep(postCond);
		Assert.assertNotNull("getOwningScenarios fails with null",postCond.getOwningScenarios());
		Assert.assertEquals("getOwningScenarios fails with wrong size",postCond.getOwningScenarios().size(), 2);
		Assert.assertSame("getOwningScenarios fails with wrong scenario",postCond.getOwningScenarios().get(0),scen1uc1);
		Assert.assertSame("getOwningScenarios fails with wrong scenario",postCond.getOwningScenarios().get(1),scen2uc1);
		
		scen1uc2.addScenarioStep(postCond);
		Assert.assertNotNull("getOwningScenarios fails with null",postCond.getOwningScenarios());
		Assert.assertEquals("getOwningScenarios fails with wrong size",postCond.getOwningScenarios().size(), 3);
		Assert.assertSame("getOwningScenarios fails with wrong scenario",postCond.getOwningScenarios().get(0),scen1uc1);
		Assert.assertSame("getOwningScenarios fails with wrong scenario",postCond.getOwningScenarios().get(1),scen2uc1);
		Assert.assertSame("getOwningScenarios fails with wrong scenario",postCond.getOwningScenarios().get(2),scen1uc2);
		
		
	}
	
	@Test
	public void testSetSuccess() {
		PostconditionSentenceDTO postCond = facade.createPostconditionSentenceDTO();
		Assert.assertFalse("setting succes failed",postCond.isSuccess());
		postCond.setSuccess(true);
		Assert.assertTrue("setting succes failed",postCond.isSuccess());
		postCond.setSuccess(false);
		Assert.assertFalse("setting succes failed",postCond.isSuccess());
	}
	
	@Test
	public void testSetSentenceText() {
		PostconditionSentenceDTO postCond = facade.createPostconditionSentenceDTO();
		String sentenceText = "postcondition";
		postCond.setSentenceText(sentenceText);
		Assert.assertEquals("setting sentence text failed",sentenceText,postCond.getSentenceText());

	}

}
