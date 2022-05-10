package eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.controlsentences;

import org.junit.Assert;
import org.junit.Test;

import eu.redseeds.scl.model.AbstractModelTest;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentations.descriptiverequirementrepresentations.ConstrainedLanguageScenarioDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.UseCaseDTO;

public class ConditionSentenceDTOTest extends AbstractModelTest {

	
	@Test
	public void testGetOwningScenarios() {
		UseCaseDTO uc1 = facade.createUseCaseDTO();	
		UseCaseDTO uc2 = facade.createUseCaseDTO();
		ConditionSentenceDTO cond = facade.createConditionSentenceDTO();

		ConstrainedLanguageScenarioDTO scen1uc1 = facade.createConstrainedLanguageScenarioDTO();
		ConstrainedLanguageScenarioDTO scen2uc1 = facade.createConstrainedLanguageScenarioDTO();
		ConstrainedLanguageScenarioDTO scen1uc2 = facade.createConstrainedLanguageScenarioDTO();
		
		uc1.addConstrainedLanguageScenario(scen1uc1);
		uc1.addConstrainedLanguageScenario(scen2uc1);
		
		uc2.addConstrainedLanguageScenario(scen1uc2);
		
		scen1uc1.addScenarioStep(cond);
		Assert.assertNotNull("getOwningScenarios fails with null",cond.getOwningScenarios());
		Assert.assertEquals("getOwningScenarios fails with wrong size",cond.getOwningScenarios().size(), 1);
		Assert.assertSame("getOwningScenarios fails with wrong scenario",cond.getOwningScenarios().get(0),scen1uc1);
		
		scen2uc1.addScenarioStep(cond);
		Assert.assertNotNull("getOwningScenarios fails with null",cond.getOwningScenarios());
		Assert.assertEquals("getOwningScenarios fails with wrong size",cond.getOwningScenarios().size(), 2);
		Assert.assertSame("getOwningScenarios fails with wrong scenario",cond.getOwningScenarios().get(0),scen1uc1);
		Assert.assertSame("getOwningScenarios fails with wrong scenario",cond.getOwningScenarios().get(1),scen2uc1);
		
		scen1uc2.addScenarioStep(cond);
		Assert.assertNotNull("getOwningScenarios fails with null",cond.getOwningScenarios());
		Assert.assertEquals("getOwningScenarios fails with wrong size",cond.getOwningScenarios().size(), 3);
		Assert.assertSame("getOwningScenarios fails with wrong scenario",cond.getOwningScenarios().get(0),scen1uc1);
		Assert.assertSame("getOwningScenarios fails with wrong scenario",cond.getOwningScenarios().get(1),scen2uc1);
		Assert.assertSame("getOwningScenarios fails with wrong scenario",cond.getOwningScenarios().get(2),scen1uc2);
		
		
	}
	
	@Test
	public void testSetSentenceText() {
		ConditionSentenceDTO cond = facade.createConditionSentenceDTO();
		String sentenceText = "condition";
		cond.setSentenceText(sentenceText);
		Assert.assertEquals("setting sentence text failed",sentenceText,cond.getSentenceText());

	}

}
