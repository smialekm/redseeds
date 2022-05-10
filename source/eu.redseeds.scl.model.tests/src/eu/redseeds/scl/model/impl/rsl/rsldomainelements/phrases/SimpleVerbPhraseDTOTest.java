package eu.redseeds.scl.model.impl.rsl.rsldomainelements.phrases;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import eu.redseeds.common.Constants;
import eu.redseeds.sc.terminology.model.RemoteJGWNL;
import eu.redseeds.scl.SCLSchema;
import eu.redseeds.scl.model.AbstractModelTest;
import eu.redseeds.scl.model.impl.BusinessLayerFacadeImpl;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.NounPhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.SimpleVerbPhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.terms.DeterminerDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.terms.ModifierDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.terms.NounDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.terms.VerbDTO;
import eu.redseeds.scl.rsl.rsldomainelements.phrases.PhraseVerbLink;
import eu.redseeds.scl.rsl.rsldomainelements.terms.Verb;


public class SimpleVerbPhraseDTOTest extends AbstractModelTest{
	private static final String ADD = "add";
	private static final String USERS = "users";

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
		SimpleVerbPhraseDTO vf = facade.createSimpleVerbPhraseDTO();
		Assert.assertNotNull("Creating VerbPhrase failed with null",vf);
	}

	/**
	 * Test method for {@link eu.redseeds.scl.impl.rsl.rslrequirementrepresentationsentences.svosentences.SVOSentenceImpl#setUid(java.lang.String)}.
	 */
	@Test
	public void testSetVerb() {		
		SimpleVerbPhraseDTO vf = facade.createSimpleVerbPhraseDTO();
		
		VerbDTO verb = facade.createVerbDTO();
		
		String name = "verb";
		
		verb.setName(name);
		
		vf.setVerb(verb);
		
		Assert.assertEquals("setting determiner failed",verb,vf.getVerb());
		
		Assert.assertEquals("setting determiner name failed",name,vf.getVerb().getName());
	}
	
	/**
	 * Test method for {@link eu.redseeds.scl.impl.rsl.rslrequirementrepresentationsentences.svosentences.SVOSentenceImpl#setUid(java.lang.String)}.
	 */
	@Test
	public void testReSetVerb() {		
		SimpleVerbPhraseDTO vf = facade.createSimpleVerbPhraseDTO();
		
		String name = "verb";
		String name1 = "verb1";
		
		VerbDTO verb = facade.createVerbDTO(name);
		VerbDTO verb1 = facade.createVerbDTO(name1);	
		
		
		vf.setVerb(verb);
		
		Assert.assertEquals("setting determiner failed",verb,vf.getVerb());
		
		Assert.assertEquals("setting determiner name failed",name,vf.getVerb().getName());
		
		vf.setVerb(verb1);
		
		Assert.assertEquals("setting determiner failed",verb1,vf.getVerb());
		
		Assert.assertEquals("setting determiner name failed",name1,vf.getVerb().getName());
	}
	
	/**
	 * Test method for {@link eu.redseeds.scl.impl.rsl.rslrequirementrepresentationsentences.svosentences.SVOSentenceImpl#setUid(java.lang.String)}.
	 */
	@Test
	public void testSetObject() {
		SimpleVerbPhraseDTO vf = facade.createSimpleVerbPhraseDTO();
		NounPhraseDTO noun = facade.createNounPhraseDTO();	
		NounPhraseDTO noun1 = facade.createNounPhraseDTO();	
		NounDTO n = facade.createNounDTO();
		
		vf.setObject(noun);
		Assert.assertEquals("setting nounPhrase in simpleVerbPhrase failed",noun,vf.getObject());
		vf.getObject().setNoun(n);
		Assert.assertEquals("setting nounPhrase in simpleVerbPhrase failed",noun,vf.getObject());
		vf.getObject().getNoun().setName("name:");
		Assert.assertEquals("setting nounPhrase in simpleVerbPhrase failed",noun,vf.getObject());
		
		vf.setObject(noun1);
		Assert.assertEquals("setting another nounPhrase in simpleVerbPhrase failed",noun1,vf.getObject());
	}
	
	/**
	 * Test method for {@link eu.redseeds.scl.impl.rsl.rslrequirementrepresentationsentences.svosentences.SVOSentenceImpl#getUid()}.
	 */
	@Test
	public void testSettingLinkValue() {
		
		SimpleVerbPhraseDTO vf = facade.createSimpleVerbPhraseDTO();		
		
		String vname = "verb";				
		
		VerbDTO v = facade.createVerbDTO(vname);
		Assert.assertNotNull("Creating named Verb failed with null",v);
		Assert.assertEquals("Creating Verb failed with different names",v.getName(),vname);		
		
		vf.setVerb(v);		
		
		Assert.assertNotNull("Checking PhraseVerbLink value failed with null PhraseVerbLink",((Verb)v).getFirstVerbLinkLinksToTarget().getAlpha());
		Assert.assertTrue("Checking PhraseVerbLink value failed with wrong class",((Verb)v).getFirstVerbLinkLinksToTarget().getAlpha() instanceof PhraseVerbLink);
		PhraseVerbLink vl = (PhraseVerbLink)((Verb)v).getFirstVerbLinkLinksToTarget().getAlpha();
		Assert.assertNotNull("Checking PhraseVerbLink value failed with null value",vl.getValue());
		Assert.assertEquals("Checking PhraseVerbLink failed with different names",vl.getValue(),vname);

	}
	
	@Test
	public void testToString() {
		
		SimpleVerbPhraseDTO svf = facade.createSimpleVerbPhraseDTO();
		
		NounPhraseDTO nf = facade.createNounPhraseDTO();		
		
		String nname = "noun";				
		String mname = "modifier";
		String dname = "determiner";
		String vname = "verb";
		
		NounDTO n = facade.createNounDTO(nname);
		ModifierDTO m = facade.createModifierDTO(mname);
		DeterminerDTO d = facade.createDeterminerDTO(dname);
		VerbDTO v = facade.createVerbDTO(vname);
		
		Assert.assertEquals("SimpleVerbPhraseDTO.toString() fails for empty phrase","", svf.toString());
		
		svf.setVerb(v);		
		Assert.assertEquals("SimpleVerbPhraseDTO.toString()",vname, svf.toString());
		
		svf.setObject(nf);
		Assert.assertEquals("SimpleVerbPhraseDTO.toString()",vname, svf.toString());
		
		nf.setNoun(n);
		Assert.assertEquals("SimpleVerbPhraseDTO.toString() fails",vname+" "+nname, svf.toString());
		
		nf.setModifier(m);
		Assert.assertEquals("SimpleVerbPhraseDTO.toString() fails",vname+" "+mname+" "+nname, svf.toString());
		
		nf.setDeterminer(d);
		Assert.assertEquals("SimpleVerbPhraseDTO.toString() fails",vname+" "+dname+" "+mname+" "+nname, svf.toString());
		
	}
	
	@Test
	public void testCopy() {
		SimpleVerbPhraseDTO svf = facade.createSimpleVerbPhraseDTO();
		NounPhraseDTO nf = facade.createNounPhraseDTO();
		svf.setVerb(facade.createVerbDTO(ADD));
		nf.setNounText(USERS);	
		svf.setObject(nf);
	
		
		svf.connect();
		Assert.assertTrue(RemoteJGWNL.getInstance().getTermSenses(USERS,
						Constants.TERM_NOUN).length>0);
		nf.getNoun().setSynonymUid(
				RemoteJGWNL.getInstance().getTermSenses(USERS,
						Constants.TERM_NOUN)[0].getUid());
		Assert.assertTrue(RemoteJGWNL.getInstance().getTermSenses(ADD,
						Constants.TERM_VERB).length>0);
		svf.getVerb().setSynonymUid(
				RemoteJGWNL.getInstance().getTermSenses(ADD,
						Constants.TERM_VERB)[0].getUid());
		
		SimpleVerbPhraseDTO svf1 = (SimpleVerbPhraseDTO)svf.copy(false);
		SimpleVerbPhraseDTO svf2 = (SimpleVerbPhraseDTO)svf1.copy(true);
		
		Assert.assertTrue("Copying SimpleVerbPhrase fails ", svf.equals(svf1));
		Assert.assertTrue("Copying SimpleVerbPhrase fails ", svf.equals(svf2));
		Assert.assertTrue("Copying SimpleVerbPhrase fails ", svf1.equals(svf2));
	}
	
	@Test
	public void testConnect() {
		//empty graph required
		sclGraph = SCLSchema.instance().createSCLGraph(100, 200);
		facade = (BusinessLayerFacadeImpl)sclGraph;
		
		SimpleVerbPhraseDTO svf = facade.createSimpleVerbPhraseDTO();
		SimpleVerbPhraseDTO svf1 = facade.createSimpleVerbPhraseDTO();
		NounPhraseDTO nf = facade.createNounPhraseDTO();
		NounPhraseDTO nf1 = facade.createNounPhraseDTO();
		svf.setVerb(facade.createVerbDTO(ADD));
		svf1.setVerb(facade.createVerbDTO(ADD));
		nf.setNounText(USERS);	
		nf1.setNounText(USERS);	
		svf.setObject(nf);
		svf1.setObject(nf1);	
		
		svf.connect();
		svf1.connect();
		
		Assert.assertNotNull("connect fails with null noun",svf.getObject().getNoun());
		Assert.assertNotNull("connect fails with null noun",svf1.getObject().getNoun());
		
		Assert.assertNotNull("connect fails with null verb",svf.getVerb());
		Assert.assertNotNull("connect fails with null verb",svf1.getVerb());
		
		Assert.assertTrue("connect fails", svf.getVerb().getSynonymUid()==0);
		Assert.assertTrue("connect fails", svf.getObject().getNoun().getSynonymUid()==0);
		
		Assert.assertTrue("connect fails", svf1.getVerb().getSynonymUid()==0);
		Assert.assertTrue("connect fails", svf1.getObject().getNoun().getSynonymUid()==0);		
		
		
		
		Assert.assertTrue(RemoteJGWNL.getInstance().getTermSenses(USERS,
						Constants.TERM_NOUN).length>0);
		nf.getNoun().setSynonymUid(
				RemoteJGWNL.getInstance().getTermSenses(USERS,
						Constants.TERM_NOUN)[0].getUid());
		Assert.assertTrue(RemoteJGWNL.getInstance().getTermSenses(ADD,
						Constants.TERM_VERB).length>0);
		svf.getVerb().setSynonymUid(
				RemoteJGWNL.getInstance().getTermSenses(ADD,
						Constants.TERM_VERB)[0].getUid());
		
		svf.connect();		
		Assert.assertFalse("connect fails", svf.getVerb().getSynonymUid()==0);
		Assert.assertFalse("connect fails", svf.getObject().getNoun().getSynonymUid()==0);
		
		Assert.assertTrue("connect fails", svf1.getVerb().getSynonymUid()==0);
		Assert.assertFalse("connect fails", svf1.getObject().getNoun().getSynonymUid()==0);		
		
		svf1.connect();
		
		Assert.assertFalse("connect fails", svf.getVerb().getSynonymUid()==0);
		Assert.assertFalse("connect fails", svf.getObject().getNoun().getSynonymUid()==0);
		
		Assert.assertFalse("connect fails", svf1.getVerb().getSynonymUid()==0);
		Assert.assertFalse("connect fails", svf1.getObject().getNoun().getSynonymUid()==0);		
		
	}
	
	/*@Test
	public void testDeleteRecursivly() {
		SimpleVerbPhraseDTO svf = facade.createSimpleVerbPhraseDTO();
		NounPhraseDTO nf = facade.createNounPhraseDTO();
		VerbDTO verb = facade.createVerbDTO(ADD);
		svf.setVerb(verb);			
		svf.setObject(nf);
		svf.deleteRecursively();		
		
		Assert.assertNull("deleteRecursively failed",svf);
		Assert.assertNull("deleteRecursively failed",nf);
		Assert.assertNull("deleteRecursively failed",verb);
	}*/
	
	
	@Test
	public void testHasSense() {
		//empty graph required
		sclGraph = SCLSchema.instance().createSCLGraph(100, 200);
		facade = (BusinessLayerFacadeImpl)sclGraph;
		
		SimpleVerbPhraseDTO svf = facade.createSimpleVerbPhraseDTO();
		NounPhraseDTO nf = facade.createNounPhraseDTO();
		svf.setVerb(facade.createVerbDTO(ADD));
		nf.setNounText(USERS);	
		svf.setObject(nf);	
		
		Assert.assertFalse("test HasSense failed", svf.hasSenses());		
		svf.connect();
		
		Assert.assertFalse("test HasSense failed", svf.hasSenses());
		
		
		Assert.assertTrue(RemoteJGWNL.getInstance().getTermSenses(ADD,
						Constants.TERM_VERB).length>0);
		svf.getVerb().setSynonymUid(
				RemoteJGWNL.getInstance().getTermSenses(ADD,
						Constants.TERM_VERB)[0].getUid());
		
		Assert.assertFalse("test HasSense failed", svf.hasSenses());
		
		Assert.assertTrue(RemoteJGWNL.getInstance().getTermSenses(USERS,
				Constants.TERM_NOUN).length>0);
nf.getNoun().setSynonymUid(
		RemoteJGWNL.getInstance().getTermSenses(USERS,
				Constants.TERM_NOUN)[0].getUid());

Assert.assertTrue("test HasSense failed", svf.hasSenses());		
				
		
	}

}
