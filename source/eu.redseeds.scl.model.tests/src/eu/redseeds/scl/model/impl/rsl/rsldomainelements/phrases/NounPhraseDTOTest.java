package eu.redseeds.scl.model.impl.rsl.rsldomainelements.phrases;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import eu.redseeds.scl.model.AbstractModelTest;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.NounPhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.terms.DeterminerDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.terms.ModifierDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.terms.NounDTO;
import eu.redseeds.scl.rsl.rsldomainelements.phrases.DeterminerLink;
import eu.redseeds.scl.rsl.rsldomainelements.phrases.ModifierLink;
import eu.redseeds.scl.rsl.rsldomainelements.phrases.NounLink;
import eu.redseeds.scl.rsl.rsldomainelements.terms.Determiner;
import eu.redseeds.scl.rsl.rsldomainelements.terms.Modifier;
import eu.redseeds.scl.rsl.rsldomainelements.terms.Noun;

public class NounPhraseDTOTest extends AbstractModelTest{
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
		NounPhraseDTO nf = facade.createNounPhraseDTO();
		Assert.assertNotNull("creating NounPhrase failed", nf);
		
	}

	/**
	 * Test method for {@link eu.redseeds.scl.impl.rsl.rslrequirementrepresentationsentences.svosentences.SVOSentenceImpl#setUid(java.lang.String)}.
	 */
	@Test
	public void testSetDeterminer() {		
		NounPhraseDTO nf = facade.createNounPhraseDTO();
		
		DeterminerDTO det = facade.createDeterminerDTO();
		
		String name = "determiner";
		
		det.setName(name);
		
		nf.setDeterminer(det);
		
		Assert.assertEquals("setting determiner failed",det,nf.getDeterminer());
		
		Assert.assertEquals("setting determiner name failed",name,nf.getDeterminer().getName());
	}
	
	/**
	 * Test method for {@link eu.redseeds.scl.impl.rsl.rslrequirementrepresentationsentences.svosentences.SVOSentenceImpl#setUid(java.lang.String)}.
	 */
	@Test
	public void testReSetDeterminer() {		
		NounPhraseDTO nf = facade.createNounPhraseDTO();
		
		String name = "determiner";
		String name1 = "determiner1";
		
		DeterminerDTO det = facade.createDeterminerDTO(name);
		DeterminerDTO det1 = facade.createDeterminerDTO(name1);		
		
		nf.setDeterminer(det);
		
		Assert.assertEquals("setting determiner failed",det,nf.getDeterminer());
		
		Assert.assertEquals("setting determiner name failed",name,nf.getDeterminer().getName());
		
		nf.setDeterminer(det1);
		
		Assert.assertEquals("setting determiner failed",det1,nf.getDeterminer());
		
		Assert.assertEquals("setting determiner name failed",name1,nf.getDeterminer().getName());
	}
	
	/**
	 * Test method for {@link eu.redseeds.scl.impl.rsl.rslrequirementrepresentationsentences.svosentences.SVOSentenceImpl#setUid(java.lang.String)}.
	 */
	@Test
	public void testSetNoun() {		
		NounPhraseDTO nf = facade.createNounPhraseDTO();
		
		NounDTO noun = facade.createNounDTO();
		
		String name = "noun";
		
		noun.setName(name);
		
		nf.setNoun(noun);
		
		Assert.assertEquals("setting determiner failed",noun,nf.getNoun());
		
		Assert.assertEquals("setting determiner name failed",name,nf.getNoun().getName());
	}
	
	/**
	 * Test method for {@link eu.redseeds.scl.impl.rsl.rslrequirementrepresentationsentences.svosentences.SVOSentenceImpl#setUid(java.lang.String)}.
	 */
	@Test
	public void testReSetNoun() {		
		NounPhraseDTO nf = facade.createNounPhraseDTO();
		
		String name = "noun";
		String name1 = "noun1";
		
		NounDTO noun = facade.createNounDTO(name);
		NounDTO noun1 = facade.createNounDTO(name1);		
		
		
		nf.setNoun(noun);
		
		Assert.assertEquals("setting determiner failed",noun,nf.getNoun());
		
		Assert.assertEquals("setting determiner name failed",name,nf.getNoun().getName());
		
		nf.setNoun(noun1);
		
		Assert.assertEquals("setting determiner failed",noun1,nf.getNoun());
		
		Assert.assertEquals("setting determiner name failed",name1,nf.getNoun().getName());
	}
	
	/**
	 * Test method for {@link eu.redseeds.scl.impl.rsl.rslrequirementrepresentationsentences.svosentences.SVOSentenceImpl#setUid(java.lang.String)}.
	 */
	@Test
	public void testSetModifier() {		
		NounPhraseDTO nf = facade.createNounPhraseDTO();
		
		ModifierDTO mod = facade.createModifierDTO();
		
		String name = "modifier";
		
		mod.setName(name);
		
		nf.setModifier(mod);
		
		Assert.assertEquals("setting determiner failed",mod,nf.getModifier());
		
		Assert.assertEquals("setting determiner name failed",name,nf.getModifier().getName());
	}
	
	/**
	 * Test method for {@link eu.redseeds.scl.impl.rsl.rslrequirementrepresentationsentences.svosentences.SVOSentenceImpl#setUid(java.lang.String)}.
	 */
	@Test
	public void testReSetModifier() {		
		NounPhraseDTO nf = facade.createNounPhraseDTO();
		
		String name = "modifier";
		String name1 = "modifier1";
		
		ModifierDTO mod = facade.createModifierDTO(name);
		ModifierDTO mod1 = facade.createModifierDTO(name1);	
		
		
		nf.setModifier(mod);
		
		Assert.assertEquals("setting determiner failed",mod,nf.getModifier());
		
		Assert.assertEquals("setting determiner name failed",name,nf.getModifier().getName());
		
		nf.setModifier(mod1);
		
		Assert.assertEquals("setting determiner failed",mod1,nf.getModifier());
		
		Assert.assertEquals("setting determiner name failed",name1,nf.getModifier().getName());
	}
	
	/**
	 * Test method for {@link eu.redseeds.scl.impl.rsl.rslrequirementrepresentationsentences.svosentences.SVOSentenceImpl#getUid()}.
	 */
	@Test
	public void testSettingLinkValue() {
		
		NounPhraseDTO nf = facade.createNounPhraseDTO();
		
		String nname = "noun";				
		String mname = "modifier";
		String dname = "determiner";
		
		NounDTO n = facade.createNounDTO(nname);
		Assert.assertNotNull("Creating named Noun failed with null",n);
		Assert.assertEquals("Creating Noun failed with different names",n.getName(),nname);		
		
		ModifierDTO m = facade.createModifierDTO(mname);
		Assert.assertNotNull("Creating named Modifier failed with null",m);
		Assert.assertEquals("Creating Noun failed with different names",m.getName(),mname);
		
		DeterminerDTO d = facade.createDeterminerDTO(dname);
		Assert.assertNotNull("Creating named Determiner failed with null",d);
		Assert.assertEquals("Creating Noun failed with different names",d.getName(),dname);
		
		nf.setNoun(n);
		nf.setDeterminer(d);
		nf.setModifier(m);
		
		Assert.assertNotNull("Checking NounLink value failed with null NounLink",((Noun)n).getFirstNounLinkLinksToTarget().getAlpha());
		Assert.assertTrue("Checking NounLink value failed with wrong class",((Noun)n).getFirstNounLinkLinksToTarget().getAlpha() instanceof NounLink);
		
		NounLink nl = (NounLink)((Noun)n).getFirstNounLinkLinksToTarget().getAlpha();
		Assert.assertNotNull("Checking NounLink value failed with null value",nl.getValue());
		Assert.assertEquals("Checking NounLink failed with different names",nl.getValue(),nname);
		

		Assert.assertNotNull("Checking DeterminerLink value failed with null DeterminerLink",((Determiner)d).getFirstDeterminerLinkLinksToTarget().getAlpha());
		Assert.assertTrue("Checking DeterminerLink value failed with wrong class",((Determiner)d).getFirstDeterminerLinkLinksToTarget().getAlpha() instanceof DeterminerLink);
		DeterminerLink dl = (DeterminerLink)((Determiner)d).getFirstDeterminerLinkLinksToTarget().getAlpha();
		Assert.assertNotNull("Checking DeterminerLink value failed with null value",dl.getValue());
		Assert.assertEquals("Checking DeterminerLink failed with different names",dl.getValue(),dname);
		

		Assert.assertNotNull("Checking ModifierLink value failed with null ModifierLink",((Modifier)m).getFirstModifierLinkLinksToTarget().getAlpha());
		Assert.assertTrue("Checking ModifierLink value failed with wrong class",((Modifier)m).getFirstModifierLinkLinksToTarget().getAlpha() instanceof ModifierLink);
		ModifierLink ml = (ModifierLink)((Modifier)m).getFirstModifierLinkLinksToTarget().getAlpha();
		Assert.assertNotNull("Checking ModifierLink value failed with null value",ml.getValue());
		Assert.assertEquals("Checking ModifierLink failed with different names",ml.getValue(),mname);		

	}
	
	@Test
	public void testToString() {
		
		NounPhraseDTO nf = facade.createNounPhraseDTO();		
		
		String nname = "noun";				
		String mname = "modifier";
		String dname = "determiner";
		
		NounDTO n = facade.createNounDTO(nname);
		ModifierDTO m = facade.createModifierDTO(mname);
		DeterminerDTO d = facade.createDeterminerDTO(dname);
		
		Assert.assertEquals("NounPhraseDTO.toString() fails for empty phrase","", nf.toString());
		
		nf.setNoun(n);
		Assert.assertEquals("NounPhraseDTO.toString() fails",nname, nf.toString());
		
		nf.setModifier(m);
		Assert.assertEquals("NounPhraseDTO.toString() fails",mname+" "+nname, nf.toString());
		
		nf.setDeterminer(d);
		Assert.assertEquals("NounPhraseDTO.toString() fails",dname+" "+mname+" "+nname, nf.toString());
		
	}

}
