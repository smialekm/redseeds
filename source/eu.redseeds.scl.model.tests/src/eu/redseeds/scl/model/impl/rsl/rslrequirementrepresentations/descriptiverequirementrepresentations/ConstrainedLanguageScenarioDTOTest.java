package eu.redseeds.scl.model.impl.rsl.rslrequirementrepresentations.descriptiverequirementrepresentations;

import org.junit.Assert;
import org.junit.Test;

import eu.redseeds.scl.model.AbstractModelTest;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentations.descriptiverequirementrepresentations.ConstrainedLanguageScenarioDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.svosentences.SVOSentenceDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.UseCaseDTO;

public class ConstrainedLanguageScenarioDTOTest extends AbstractModelTest{

	@Test
	public void testGetScenarioSentenceList() {
		ConstrainedLanguageScenarioDTO scen = facade.createConstrainedLanguageScenarioDTO();
		Assert.assertNotNull("Creating ConstrainedLanguageScenarioDTO fails with null",scen);
		Assert.assertNotNull("Getting empty ScenarioSentenceList fails with null", scen.getScenarioSentenceList());
		Assert.assertEquals("Getting empty ScenarioSentenceList fails with size!=0", scen.getScenarioSentenceList().size(),0);
		
		
		SVOSentenceDTO sent = facade.createSVOSentenceDTO();		
		Assert.assertNotNull("Creating SVOSentenceDTO fails with null",sent);
		scen.addScenarioStep(sent);
		Assert.assertNotNull("Getting ScenarioSentenceList fails with null", scen.getScenarioSentenceList());
		Assert.assertEquals("Getting ScenarioSentenceList fails with size!=1", scen.getScenarioSentenceList().size(),1);
		
	}
	
	@Test
	public void testAddScenarioStepConstrainedLanguageSentenceDTO() {
		ConstrainedLanguageScenarioDTO scen = facade.createConstrainedLanguageScenarioDTO();
		Assert.assertNotNull("Creating ConstrainedLanguageScenarioDTO fails with null",scen);
		Assert.assertNotNull("Getting empty ScenarioSentenceList fails with null", scen.getScenarioSentenceList());
		Assert.assertEquals("Getting empty ScenarioSentenceList fails with size!=0", scen.getScenarioSentenceList().size(),0);		
		
		SVOSentenceDTO sent = facade.createSVOSentenceDTO();		
		Assert.assertNotNull("Creating SVOSentenceDTO fails with null",sent);
		scen.addScenarioStep(sent);
		Assert.assertNotNull("Getting ScenarioSentenceList fails with null", scen.getScenarioSentenceList());
		Assert.assertEquals("Getting ScenarioSentenceList fails with size!=1", scen.getScenarioSentenceList().size(),1);
		
		Assert.assertNotNull("Getting Sentence from ScenarioSentenceList fails null", scen.getScenarioSentenceList().get(0));
		Assert.assertSame("Getting Sentence from ScenarioSentenceList fails different sentence", sent, scen.getScenarioSentenceList().get(0));
		
		SVOSentenceDTO sent1 = facade.createSVOSentenceDTO();
		scen.addScenarioStep(sent1);
		Assert.assertEquals("Getting ScenarioSentenceList fails with size!=2", scen.getScenarioSentenceList().size(),2);
		Assert.assertNotNull("Getting second Sentence from ScenarioSentenceList fails null", scen.getScenarioSentenceList().get(1));
		Assert.assertSame("Getting second Sentence from ScenarioSentenceList fails different sentence", sent1, scen.getScenarioSentenceList().get(1));
	
	}

	@Test
	public void testGetPreviousSentence() {
		ConstrainedLanguageScenarioDTO scen = facade.createConstrainedLanguageScenarioDTO();
		Assert.assertNotNull("Creating ConstrainedLanguageScenarioDTO fails with null",scen);
		Assert.assertNotNull("Getting empty ScenarioSentenceList fails with null", scen.getScenarioSentenceList());
		Assert.assertEquals("Getting empty ScenarioSentenceList fails with size!=0", scen.getScenarioSentenceList().size(),0);		
		
		SVOSentenceDTO sent = facade.createSVOSentenceDTO();		
		Assert.assertNotNull("Creating SVOSentenceDTO fails with null",sent);
		scen.addScenarioStep(sent);
		Assert.assertNotNull("Getting ScenarioSentenceList fails with null", scen.getScenarioSentenceList());
		Assert.assertEquals("Getting ScenarioSentenceList fails with size!=1", scen.getScenarioSentenceList().size(),1);
		
		Assert.assertNotNull("Getting Sentence from ScenarioSentenceList fails null", scen.getScenarioSentenceList().get(0));
		Assert.assertSame("Getting Sentence from ScenarioSentenceList fails different sentence", sent, scen.getScenarioSentenceList().get(0));
		
		SVOSentenceDTO sent1 = facade.createSVOSentenceDTO();
		scen.addScenarioStep(sent1);
		
		SVOSentenceDTO sent2 = facade.createSVOSentenceDTO();
		scen.addScenarioStep(sent2);
		
		Assert.assertNotNull("Getting previous Sentence from ScenarioSentenceList fails null",scen.getPreviousSentence(sent2));
		Assert.assertSame("Getting previous Sentence from ScenarioSentenceList with different sentence",sent1,scen.getPreviousSentence(sent2));
		
		Assert.assertNotNull("Getting previous Sentence from ScenarioSentenceList fails null",scen.getPreviousSentence(sent1));
		Assert.assertSame("Getting previous Sentence from ScenarioSentenceList with different sentence",sent,scen.getPreviousSentence(sent1));
		
		
		Assert.assertNull("Getting previous Sentence of first sentence fails",scen.getPreviousSentence(sent));
		
		SVOSentenceDTO sent3 = facade.createSVOSentenceDTO();
		
		Assert.assertNull("Getting previous Sentence of sentence not in scenario fails",scen.getPreviousSentence(sent3));
		
		Assert.assertNull("Getting previous Sentence null sentence fails",scen.getPreviousSentence(null));
		
	}

	@Test
	public void testInsertScenarioStepAfter() {
		ConstrainedLanguageScenarioDTO scen = facade.createConstrainedLanguageScenarioDTO();
		ConstrainedLanguageScenarioDTO scen1 = facade.createConstrainedLanguageScenarioDTO();
		
		SVOSentenceDTO sent = facade.createSVOSentenceDTO();		
		SVOSentenceDTO sent1 = facade.createSVOSentenceDTO();		
		SVOSentenceDTO sent2 = facade.createSVOSentenceDTO();
		SVOSentenceDTO sent3 = facade.createSVOSentenceDTO();
		SVOSentenceDTO sent4 = facade.createSVOSentenceDTO();
		
		scen.insertScenarioStepAfter(sent1, sent);		
		
		Assert.assertNotNull("Getting ScenarioSentenceList fails with null", scen.getScenarioSentenceList());
		Assert.assertEquals("Getting ScenarioSentenceList fails with size!=1", scen.getScenarioSentenceList().size(),1);
		Assert.assertSame("Getting Sentence from ScenarioSentenceList with different sentence",sent,scen.getScenarioSentenceList().get(0));
		
		scen.insertScenarioStepAfter(sent, sent2);	
		Assert.assertNotNull("Getting ScenarioSentenceList fails with null", scen.getScenarioSentenceList());
		Assert.assertEquals("Getting ScenarioSentenceList fails with size!=2", scen.getScenarioSentenceList().size(),2);
		Assert.assertSame("Getting Sentence from ScenarioSentenceList with different sentence",sent,scen.getScenarioSentenceList().get(0));
		Assert.assertSame("Getting Sentence from ScenarioSentenceList with different sentence",sent2,scen.getScenarioSentenceList().get(1));
		
		
		scen.insertScenarioStepAfter(sent, sent1);	
		Assert.assertNotNull("Getting ScenarioSentenceList fails with null", scen.getScenarioSentenceList());
		Assert.assertEquals("Getting ScenarioSentenceList fails with size!=2", scen.getScenarioSentenceList().size(),3);
		Assert.assertSame("Getting Sentence from ScenarioSentenceList with different sentence",sent,scen.getScenarioSentenceList().get(0));
		Assert.assertSame("Getting Sentence from ScenarioSentenceList with different sentence",sent1,scen.getScenarioSentenceList().get(1));
		Assert.assertSame("Getting Sentence from ScenarioSentenceList with different sentence",sent2,scen.getScenarioSentenceList().get(2));
		
		scen.insertScenarioStepAfter(sent4, sent3);
		Assert.assertNotNull("Getting ScenarioSentenceList fails with null", scen.getScenarioSentenceList());
		Assert.assertEquals("Getting ScenarioSentenceList fails with size!=4", scen.getScenarioSentenceList().size(),4);
		Assert.assertSame("Getting Sentence from ScenarioSentenceList with different sentence",sent,scen.getScenarioSentenceList().get(0));
		Assert.assertSame("Getting Sentence from ScenarioSentenceList with different sentence",sent1,scen.getScenarioSentenceList().get(1));
		Assert.assertSame("Getting Sentence from ScenarioSentenceList with different sentence",sent2,scen.getScenarioSentenceList().get(2));
		Assert.assertSame("Getting Sentence from ScenarioSentenceList with different sentence",sent3,scen.getScenarioSentenceList().get(3));

		
		scen.insertScenarioStepAfter(sent3, sent4);		
		Assert.assertNotNull("Getting ScenarioSentenceList fails with null", scen.getScenarioSentenceList());
		Assert.assertEquals("Getting ScenarioSentenceList fails with size!=5", scen.getScenarioSentenceList().size(),5);
		Assert.assertSame("Getting Sentence from ScenarioSentenceList fails with different sentence",sent,scen.getScenarioSentenceList().get(0));
		Assert.assertSame("Getting Sentence from ScenarioSentenceList fails with different sentence",sent1,scen.getScenarioSentenceList().get(1));
		Assert.assertSame("Getting Sentence from ScenarioSentenceList fails with different sentence",sent2,scen.getScenarioSentenceList().get(2));
		Assert.assertSame("Getting Sentence from ScenarioSentenceList fails with different sentence",sent3,scen.getScenarioSentenceList().get(3));
		Assert.assertSame("Getting Sentence from ScenarioSentenceList fails with different sentence",sent4,scen.getScenarioSentenceList().get(4));
		
		scen1.addScenarioStep(sent);
		scen1.insertScenarioStepAfter(sent, sent1);
		
		Assert.assertSame("Getting Sentence from another ScenarioSentenceList fails with different sentence",sent, scen.getScenarioSentenceList().get(0));
		Assert.assertSame("Getting Sentence from another ScenarioSentenceList fails with different sentence",sent1, scen.getScenarioSentenceList().get(1));
		
	}
	
	@Test
	public void testGetParent() {
		ConstrainedLanguageScenarioDTO scen = facade.createConstrainedLanguageScenarioDTO();
		UseCaseDTO uc = facade.createUseCaseDTO();
		
		Assert.assertNull("Getting Scenario parent fails with null",scen.getParent());
		
		uc.addConstrainedLanguageScenario(scen);
		
		Assert.assertNotNull("Getting Scenario parent fails with null",scen.getParent());
		Assert.assertSame("Getting Scenario parent fails with different use case",uc, scen.getParent());		
	}
	
	@Test
	public void testGetNameToString() {
		ConstrainedLanguageScenarioDTO scen = facade.createConstrainedLanguageScenarioDTO();
		
		String scenname = "scenario1";
		
		scen.setName(scenname);
		
		Assert.assertEquals("Getting scenario name fails with wrong name",scenname, scen.getName());
		Assert.assertEquals("Scenario toString() fails with wrong string",scenname, scen.toString());
		
	}

}
