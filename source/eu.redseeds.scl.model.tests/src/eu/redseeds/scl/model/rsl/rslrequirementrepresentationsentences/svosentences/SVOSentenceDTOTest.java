/**
 * 
 */
package eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.svosentences;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import eu.redseeds.scl.model.AbstractModelTest;
import eu.redseeds.scl.model.rsl.rsldomainelements.actors.ActorDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.ComplexVerbPhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.NounPhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.SimpleVerbPhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.systemelements.SystemElementDTO;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentations.descriptiverequirementrepresentations.ConstrainedLanguageScenarioDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.UseCaseDTO;

/**
 * @author bojarsj1
 *
 */
public class SVOSentenceDTOTest extends AbstractModelTest {

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
	 * Test method for {@link eu.redseeds.scl.impl.rsl.rslrequirementrepresentationsentences.svosentences.SVOSentenceImpl#getUid()}.
	 */
	@Test
	public void testCreate() {
		SVOSentenceDTO sent = facade.createSVOSentenceDTO();
		Assert.assertNotNull("creating SVOSentnce failed", sent);
	}

	/**
	 * Test method for {@link eu.redseeds.scl.impl.rsl.rslrequirementrepresentationsentences.svosentences.SVOSentenceImpl#setUid(java.lang.String)}.
	 */
	@Test
	public void testSetSentnceText() {
		SVOSentenceDTO sent = facade.createSVOSentenceDTO();
		sent.setSentenceText("test");
		Assert.assertEquals("setting SentenceText in  SVOSentenceDTO failed","test", sent.getSentenceText());
	}
	
	/**
	 * Test method for {@link eu.redseeds.scl.impl.rsl.rslrequirementrepresentationsentences.svosentences.SVOSentenceImpl#setUid(java.lang.String)}.
	 */
	@Test
	public void testSetPredicate() {
		SVOSentenceDTO sentSimple = facade.createSVOSentenceDTO();
		SVOSentenceDTO sentComplex = facade.createSVOSentenceDTO();
		SimpleVerbPhraseDTO simpl = facade.createSimpleVerbPhraseDTO();
		ComplexVerbPhraseDTO compl = facade.createComplexVerbPhraseDTO();
		
		Assert.assertNull("getting unset simpleVerbPhrase failed with not null",sentSimple.getPredicate());
//		Assert.assertTrue(sentSimple.getPredicate() instanceof SimpleVerbPhraseDTO);
		
		sentSimple.setPredicate(simpl);
		Assert.assertNotNull("getting simpleVerbPhrase failed with null",sentSimple.getPredicate());
		Assert.assertEquals("getting simpleVerbPhrase failed",simpl,sentSimple.getPredicate());
		
		sentComplex.setPredicate(compl);
		Assert.assertNotNull("getting simpleVerbPhrase failed with null",sentSimple.getPredicate());
		Assert.assertEquals("setting ComplexVerbPhrase failed",compl,sentComplex.getPredicate());
		
		sentSimple.setPredicate(compl);
		Assert.assertNotNull("getting simpleVerbPhrase failed with null",sentSimple.getPredicate());
		Assert.assertEquals("setting ComplexVerbPhrase failed",compl,sentComplex.getPredicate());
	}
	
	/**
	 * Test method for {@link eu.redseeds.scl.impl.rsl.rslrequirementrepresentationsentences.svosentences.SVOSentenceImpl#setUid(java.lang.String)}.
	 */
	@Test
	public void testSetSubject() {
		SVOSentenceDTO sent = facade.createSVOSentenceDTO();
		NounPhraseDTO noun = facade.createNounPhraseDTO();	
		
		Assert.assertNull("getting unset nounPhrase failed with not null",sent.getSubject());		
		
		sent.setSubject(noun);
		Assert.assertEquals("setting nounPhrase failed",noun,sent.getSubject());
	}
	
	@Test
	public void testGetOwningScenarios() {
		UseCaseDTO uc1 = facade.createUseCaseDTO();	
		UseCaseDTO uc2 = facade.createUseCaseDTO();
		SVOSentenceDTO svo = facade.createSVOSentenceDTO();

		ConstrainedLanguageScenarioDTO scen1uc1 = facade.createConstrainedLanguageScenarioDTO();
		ConstrainedLanguageScenarioDTO scen2uc1 = facade.createConstrainedLanguageScenarioDTO();
		ConstrainedLanguageScenarioDTO scen1uc2 = facade.createConstrainedLanguageScenarioDTO();
		
		uc1.addConstrainedLanguageScenario(scen1uc1);
		uc1.addConstrainedLanguageScenario(scen2uc1);
		
		uc2.addConstrainedLanguageScenario(scen1uc2);
		
		scen1uc1.addScenarioStep(svo);
		Assert.assertNotNull("getOwningScenarios fails with null",svo.getOwningScenarios());
		Assert.assertEquals("getOwningScenarios fails with wrong size",svo.getOwningScenarios().size(), 1);
		Assert.assertSame("getOwningScenarios fails with wrong scenario",svo.getOwningScenarios().get(0),scen1uc1);
		
		scen2uc1.addScenarioStep(svo);
		Assert.assertNotNull("getOwningScenarios fails with null",svo.getOwningScenarios());
		Assert.assertEquals("getOwningScenarios fails with wrong size",svo.getOwningScenarios().size(), 2);
		Assert.assertSame("getOwningScenarios fails with wrong scenario",svo.getOwningScenarios().get(0),scen1uc1);
		Assert.assertSame("getOwningScenarios fails with wrong scenario",svo.getOwningScenarios().get(1),scen2uc1);
		
		scen1uc2.addScenarioStep(svo);
		Assert.assertNotNull("getOwningScenarios fails with null",svo.getOwningScenarios());
		Assert.assertEquals("getOwningScenarios fails with wrong size",svo.getOwningScenarios().size(), 3);
		Assert.assertSame("getOwningScenarios fails with wrong scenario",svo.getOwningScenarios().get(0),scen1uc1);
		Assert.assertSame("getOwningScenarios fails with wrong scenario",svo.getOwningScenarios().get(1),scen2uc1);
		Assert.assertSame("getOwningScenarios fails with wrong scenario",svo.getOwningScenarios().get(2),scen1uc2);
		
		
	}
	
	/**
	 * Test method for {@link eu.redseeds.scl.impl.rsl.rslrequirementrepresentationsentences.svosentences.SVOSentenceImpl#setUid(java.lang.String)}.
	 */
	@Test
	public void testSetRecipient() {
		SVOSentenceDTO sent = facade.createSVOSentenceDTO();
		SystemElementDTO sysEl = facade.createSystemElementDTO();
		ActorDTO act = facade.createActorDTO();
		
		Assert.assertNull("getting unset Recipient failed ",sent.getRecipient());		
		
		sent.setRecipient(sysEl);
		Assert.assertNotNull("getting  Recipient failed with null",sent.getRecipient());
		Assert.assertSame("getting  Recipient failed ",sysEl,sent.getRecipient());
		
		sent.setRecipient(act);
		Assert.assertNotNull("getting  Recipient failed with null",sent.getRecipient());
		Assert.assertSame("getting  Recipient failed ",act,sent.getRecipient());
		
		sent.setRecipient(null);
		Assert.assertNull("getting  Recipient failed with null",sent.getRecipient());		
	}
	
	
	@Test
	public void testtoString() {
		SVOSentenceDTO sentSimple = facade.createSVOSentenceDTO();
		//SVOSentenceDTO sentComplex = facade.createSVOSentenceDTO();
		
		SimpleVerbPhraseDTO simpl = facade.createSimpleVerbPhraseDTO();
		ComplexVerbPhraseDTO compl = facade.createComplexVerbPhraseDTO();
		NounPhraseDTO pre = facade.createNounPhraseDTO();
		
		Assert.assertNotNull("getting empty sentence text failed with null",sentSimple.toString());
		Assert.assertEquals("getting empty sentence text failed with wrong string","", sentSimple.toString());
		
		
		sentSimple.setSubject(pre);
		compl.setSimpleVerbPhrase(simpl);
		sentSimple.setPredicate(compl);
		
		Assert.assertNotNull("getting empty sentence text failed with null",sentSimple.toString());
		Assert.assertEquals("getting empty sentence text failed with wrong string","", sentSimple.toString());
		
		
		pre.setNoun(facade.createNounDTO("User"));
		Assert.assertNotNull("getting sentence text failed with null",sentSimple.toString());
		Assert.assertEquals("getting sentence text failed with wrong string","User", sentSimple.toString());
		
		//why? because simplSent contains COPY of simpl
		((ComplexVerbPhraseDTO)sentSimple.getPredicate()).getSimpleVerbPhrase().setVerb(facade.createVerbDTO("adds"));
		Assert.assertNotNull("getting sentence text failed with null",sentSimple.toString());
		Assert.assertEquals("getting sentence text failed with wrong string","User adds", sentSimple.toString());
		
		((ComplexVerbPhraseDTO)sentSimple.getPredicate()).getSimpleVerbPhrase().setObject(pre);
		compl.setObject(pre);
		
		pre.setNounText("User");
		compl.setPreposition(facade.createPrepostitionDTO("to"));
		Assert.assertNotNull("getting sentence text failed with null",sentSimple.toString());
		Assert.assertEquals("getting sentence text failed with wrong string","User adds User to User", sentSimple.toString());
				
	}


}
