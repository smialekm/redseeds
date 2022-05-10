package eu.redseeds.scl.model.impl.rsl.rslrequirementrepresentationsentences.constrainedlanguagesentences;

import org.junit.Assert;
import org.junit.Test;

import eu.redseeds.scl.model.AbstractModelTest;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentations.descriptiverequirementrepresentations.ConstrainedLanguageScenarioDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.constrainedlanguagesentences.RejoinSentenceDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.svosentences.SVOSentenceDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.UseCaseDTO;

public class RejoinSentenceDTOTest extends AbstractModelTest {

	@Test
	public void testGetRejoinedSentenceNull() {
		RejoinSentenceDTO rejoin = facade.createRejoinSentenceDTO();
		Assert.assertNotNull("Creating RejoinSentenceDTO fails with null",
				rejoin);
		Assert.assertNull("Getting null RejoinedSentence fails ", rejoin
				.getRejoinedSentence());

	}

	@Test
	public void testGetSetRejoinedSentence() {
		RejoinSentenceDTO rejoin = facade.createRejoinSentenceDTO();
		Assert.assertNotNull("Creating RejoinSentenceDTO fails with null",
				rejoin);
		Assert.assertNull("Getting null RejoinedSentence fails ", rejoin
				.getRejoinedSentence());

		SVOSentenceDTO sent = facade.createSVOSentenceDTO();
		SVOSentenceDTO sent1 = facade.createSVOSentenceDTO();

		rejoin.setRejoinedSentence(sent);
		Assert.assertNotNull("Getting RejoinedSentence fails with null", rejoin
				.getRejoinedSentence());
		Assert.assertSame("Getting RejoinedSentence fails with wrong sentence",
				rejoin.getRejoinedSentence(), sent);

		rejoin.setRejoinedSentence(sent1);
		Assert.assertNotNull("Getting RejoinedSentence fails with null", rejoin
				.getRejoinedSentence());
		Assert.assertSame("Getting RejoinedSentence fails with wrong sentence",
				rejoin.getRejoinedSentence(), sent1);

	}

	@Test
	public void testGetOwningScenarios() {
		UseCaseDTO uc1 = facade.createUseCaseDTO();
		UseCaseDTO uc2 = facade.createUseCaseDTO();
		RejoinSentenceDTO rejoin = facade.createRejoinSentenceDTO();

		ConstrainedLanguageScenarioDTO scen1uc1 = facade
				.createConstrainedLanguageScenarioDTO();
		ConstrainedLanguageScenarioDTO scen2uc1 = facade
				.createConstrainedLanguageScenarioDTO();
		ConstrainedLanguageScenarioDTO scen1uc2 = facade
				.createConstrainedLanguageScenarioDTO();

		uc1.addConstrainedLanguageScenario(scen1uc1);
		uc1.addConstrainedLanguageScenario(scen2uc1);

		uc2.addConstrainedLanguageScenario(scen1uc2);

		scen1uc1.addScenarioStep(rejoin);
		Assert.assertNotNull("getOwningScenarios fails with null", rejoin
				.getOwningScenarios());
		Assert.assertEquals("getOwningScenarios fails with wrong size", rejoin
				.getOwningScenarios().size(), 1);
		Assert.assertSame("getOwningScenarios fails with wrong scenario",
				rejoin.getOwningScenarios().get(0), scen1uc1);

		scen2uc1.addScenarioStep(rejoin);
		Assert.assertNotNull("getOwningScenarios fails with null", rejoin
				.getOwningScenarios());
		Assert.assertEquals("getOwningScenarios fails with wrong size", rejoin
				.getOwningScenarios().size(), 2);
		Assert.assertSame("getOwningScenarios fails with wrong scenario",
				rejoin.getOwningScenarios().get(0), scen1uc1);
		Assert.assertSame("getOwningScenarios fails with wrong scenario",
				rejoin.getOwningScenarios().get(1), scen2uc1);

		scen1uc2.addScenarioStep(rejoin);
		Assert.assertNotNull("getOwningScenarios fails with null", rejoin
				.getOwningScenarios());
		Assert.assertEquals("getOwningScenarios fails with wrong size", rejoin
				.getOwningScenarios().size(), 3);
		Assert.assertSame("getOwningScenarios fails with wrong scenario",
				rejoin.getOwningScenarios().get(0), scen1uc1);
		Assert.assertSame("getOwningScenarios fails with wrong scenario",
				rejoin.getOwningScenarios().get(1), scen2uc1);
		Assert.assertSame("getOwningScenarios fails with wrong scenario",
				rejoin.getOwningScenarios().get(2), scen1uc2);

	}

}
