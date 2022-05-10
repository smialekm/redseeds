package eu.redseeds.scl.model.rsl.rslrequirementrepresentationsentences.controlsentences;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Test;

import eu.redseeds.scl.model.AbstractModelTest;
import eu.redseeds.scl.model.rsl.rslrequirementrepresentations.descriptiverequirementrepresentations.ConstrainedLanguageScenarioDTO;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.UseCaseDTO;
import eu.redseeds.scl.rsl.rslrequirementrepresentationsentences.controlsentences.InclusionType;

public class InvocationSentenceDTOTest extends AbstractModelTest {

	@Test
	public void testGetInvokedUseCase() {
		UseCaseDTO invokedUc = facade.createUseCaseDTO();	
		UseCaseDTO invokedUc1 = facade.createUseCaseDTO();	
		UseCaseDTO invokingUc = facade.createUseCaseDTO();
		ConstrainedLanguageScenarioDTO scen = facade.createConstrainedLanguageScenarioDTO();
		
		invokingUc.addConstrainedLanguageScenario(scen);
		
		
		InvocationSentenceDTO req = facade.createInvocationSentenceDTO();
		Assert.assertNotNull("creating uc failed", invokedUc);	
		
		scen.addScenarioStep(req);
		
		invokedUc.setName("test uc");
		Assert.assertNotNull("creating Invoke failed", req);			
		req.setInclusionType(InclusionType.REQUEST);
		Assert.assertEquals("getting Invoke type failed", req.getInclusionType(),InclusionType.REQUEST);	
		
		InvocationSentenceDTO ins = facade.createInvocationSentenceDTO();
		Assert.assertNotNull("creating Invoke failed", ins);
		scen.addScenarioStep(ins);
		req.setInclusionType(InclusionType.INSERT);
		Assert.assertEquals("getting Invoke type failed", req.getInclusionType(),InclusionType.INSERT);
		
		
		Assert.assertNull("getting unset Invoked UseCase failed with not null", req.getInvokedUseCase());
		req.setInvokedUseCase(invokedUc);
		Assert.assertNotNull("getting Invoked UseCase failed with null", req.getInvokedUseCase());
		Assert.assertSame("getting Invoked UseCase failed", req.getInvokedUseCase(),invokedUc);
		
		req.setInvokedUseCase(invokedUc1);
		Assert.assertNotNull("getting Invoked UseCase failed with null", req.getInvokedUseCase());
		Assert.assertSame("getting Invoked UseCase failed", req.getInvokedUseCase(),invokedUc1);
	
	}

/*	@Test
	public void testSetInvokedUseCase() {
		fail("Not yet implemented");
	}
	*/

	@Test
	public void testGetOwningScenarios() {
		UseCaseDTO uc1 = facade.createUseCaseDTO();	
		UseCaseDTO uc2 = facade.createUseCaseDTO();
		InvocationSentenceDTO inv = facade.createInvocationSentenceDTO();

		ConstrainedLanguageScenarioDTO scen1uc1 = facade.createConstrainedLanguageScenarioDTO();
		ConstrainedLanguageScenarioDTO scen2uc1 = facade.createConstrainedLanguageScenarioDTO();
		ConstrainedLanguageScenarioDTO scen1uc2 = facade.createConstrainedLanguageScenarioDTO();
		
		uc1.addConstrainedLanguageScenario(scen1uc1);
		uc1.addConstrainedLanguageScenario(scen2uc1);
		
		uc2.addConstrainedLanguageScenario(scen1uc2);
		
		scen1uc1.addScenarioStep(inv);
		Assert.assertNotNull("getOwningScenarios fails with null",inv.getOwningScenarios());
		Assert.assertEquals("getOwningScenarios fails with wrong size",inv.getOwningScenarios().size(), 1);
		Assert.assertSame("getOwningScenarios fails with wrong scenario",inv.getOwningScenarios().get(0),scen1uc1);
		
		scen2uc1.addScenarioStep(inv);
		Assert.assertNotNull("getOwningScenarios fails with null",inv.getOwningScenarios());
		Assert.assertEquals("getOwningScenarios fails with wrong size",inv.getOwningScenarios().size(), 2);
		Assert.assertSame("getOwningScenarios fails with wrong scenario",inv.getOwningScenarios().get(0),scen1uc1);
		Assert.assertSame("getOwningScenarios fails with wrong scenario",inv.getOwningScenarios().get(1),scen2uc1);
		
		scen1uc2.addScenarioStep(inv);
		Assert.assertNotNull("getOwningScenarios fails with null",inv.getOwningScenarios());
		Assert.assertEquals("getOwningScenarios fails with wrong size",inv.getOwningScenarios().size(), 3);
		Assert.assertSame("getOwningScenarios fails with wrong scenario",inv.getOwningScenarios().get(0),scen1uc1);
		Assert.assertSame("getOwningScenarios fails with wrong scenario",inv.getOwningScenarios().get(1),scen2uc1);
		Assert.assertSame("getOwningScenarios fails with wrong scenario",inv.getOwningScenarios().get(2),scen1uc2);
		
		
	}
	
	@Test
	public void testStereotypes() {
		InvocationSentenceDTO inv = facade.createInvocationSentenceDTO();
		String stereotype1Name = "stereotype1";
		String stereotype1NameCopy = "stereotype1";
		String stereotype2Name = "stereotype2";
		String stereotype3Name = "stereotype3";
		assertTrue("Incorrect number of stereotypes", inv.getStereotypes().size() == 0);
		inv.addStereotype(stereotype1Name);
		assertTrue("Incorrect number of stereotypes", inv.getStereotypes().size() == 1);
		inv.addStereotype(stereotype1NameCopy);
		assertTrue("Incorrect number of stereotypes", inv.getStereotypes().size() == 1);
		inv.addStereotype(stereotype2Name);
		assertTrue("Incorrect number of stereotypes", inv.getStereotypes().size() == 2);
		inv.addStereotype(stereotype3Name);
		assertTrue("Incorrect number of stereotypes", inv.getStereotypes().size() == 3);
		assertTrue("Does not list stereotypes properly", inv.getStereotypes().contains(stereotype1Name));
		assertTrue("Does not list stereotypes properly", inv.getStereotypes().contains(stereotype1NameCopy));
		inv.removeStereotype(stereotype1Name);
		assertFalse("Does not list stereotypes properly", inv.getStereotypes().contains(stereotype1Name));
		assertTrue("Incorrect number of stereotypes", inv.getStereotypes().size() == 2);
		inv.removeStereotype(stereotype3Name);
		assertTrue("Incorrect number of stereotypes", inv.getStereotypes().size() == 1);
		inv.removeStereotype(stereotype2Name);
		assertTrue("Incorrect number of stereotypes", inv.getStereotypes().size() == 0);
		
	}

}
