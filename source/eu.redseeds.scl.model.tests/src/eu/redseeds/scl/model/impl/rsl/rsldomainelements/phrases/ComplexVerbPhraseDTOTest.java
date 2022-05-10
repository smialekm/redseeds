package eu.redseeds.scl.model.impl.rsl.rsldomainelements.phrases;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import eu.redseeds.scl.model.AbstractModelTest;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.ComplexVerbPhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.NounPhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.phrases.SimpleVerbPhraseDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.terms.DeterminerDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.terms.ModifierDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.terms.NounDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.terms.PrepositionDTO;
import eu.redseeds.scl.model.rsl.rsldomainelements.terms.VerbDTO;
import eu.redseeds.scl.rsl.rsldomainelements.phrases.PhrasePrepositionLink;
import eu.redseeds.scl.rsl.rsldomainelements.terms.Preposition;


public class ComplexVerbPhraseDTOTest extends AbstractModelTest{
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
		ComplexVerbPhraseDTO vf = facade.createComplexVerbPhraseDTO();
		Assert.assertNotNull("Creating VerbPhrase failed with null",vf);
	}

	/**
	 * Test method for {@link eu.redseeds.scl.impl.rsl.rslrequirementrepresentationsentences.svosentences.SVOSentenceImpl#setUid(java.lang.String)}.
	 */
	@Test
	public void testSetPreposition() {		
		ComplexVerbPhraseDTO vf = facade.createComplexVerbPhraseDTO();
		
		PrepositionDTO prep = facade.createPrepostitionDTO();
		
		String name = "preposition";
		
		prep.setName(name);
		
		vf.setPreposition(prep);
		
		Assert.assertEquals("setting Preposition failed",prep,vf.getPreposition());
		
		Assert.assertEquals("setting Preposition name failed",name,vf.getPreposition().getName());
	}
	
	/**
	 * Test method for {@link eu.redseeds.scl.impl.rsl.rslrequirementrepresentationsentences.svosentences.SVOSentenceImpl#setUid(java.lang.String)}.
	 */
	@Test
	public void testReSetPreposition() {		
		ComplexVerbPhraseDTO vf = facade.createComplexVerbPhraseDTO();
		
		String name = "preposition";
		String name1 = "preposition1";
		
		PrepositionDTO prep = facade.createPrepostitionDTO(name);
		PrepositionDTO prep1 = facade.createPrepostitionDTO(name1);		
		
		vf.setPreposition(prep);
		
		Assert.assertEquals("setting Preposition failed",prep,vf.getPreposition());
		
		Assert.assertEquals("setting Preposition name failed",name,vf.getPreposition().getName());
		
		vf.setPreposition(prep1);
		
		Assert.assertEquals("setting Preposition failed",prep1,vf.getPreposition());
		
		Assert.assertEquals("setting Preposition name failed",name1,vf.getPreposition().getName());
	}
	
	/**
	 * Test method for {@link eu.redseeds.scl.impl.rsl.rslrequirementrepresentationsentences.svosentences.SVOSentenceImpl#setUid(java.lang.String)}.
	 */
	@Test
	public void testSetObject() {
		ComplexVerbPhraseDTO vf = facade.createComplexVerbPhraseDTO();
		NounPhraseDTO noun = facade.createNounPhraseDTO();	
		NounPhraseDTO noun1 = facade.createNounPhraseDTO();	
		NounDTO n = facade.createNounDTO();
		
		vf.setObject(noun);
		Assert.assertEquals("setting nounPhrase in ComplexVerbPhrase failed",noun,vf.getObject());
		vf.getObject().setNoun(n);
		Assert.assertEquals("setting nounPhrase in ComplexVerbPhrase failed",noun,vf.getObject());
		vf.getObject().getNoun().setName("name:");
		Assert.assertEquals("setting nounPhrase in ComplexVerbPhrase failed",noun,vf.getObject());
		
		vf.setObject(noun1);
		Assert.assertEquals("setting another nounPhrase in ComplexVerbPhrase failed",noun1,vf.getObject());
	}
	
	/**
	 * Test method for {@link eu.redseeds.scl.impl.rsl.rslrequirementrepresentationsentences.svosentences.SVOSentenceImpl#setUid(java.lang.String)}.
	 */
	@Test
	public void testSetSimpleVerbPhrase() {
		ComplexVerbPhraseDTO cvf = facade.createComplexVerbPhraseDTO();
		SimpleVerbPhraseDTO vf = facade.createSimpleVerbPhraseDTO();
		
		cvf.setSimpleVerbPhrase(vf);
		Assert.assertEquals("setting simpleVerbPhrase in ComplexVerbPhrase failed",vf,cvf.getSimpleVerbPhrase());

	}
	
	/**
	 * Test method for {@link eu.redseeds.scl.impl.rsl.rslrequirementrepresentationsentences.svosentences.SVOSentenceImpl#getUid()}.
	 */
	@Test
	public void testSettingLinkValue() {
		
		ComplexVerbPhraseDTO vf = facade.createComplexVerbPhraseDTO();		
		
		String pname = "preposition";				
		
		PrepositionDTO p = facade.createPrepostitionDTO(pname);
		Assert.assertNotNull("Creating named Preposition failed with null",p);
		Assert.assertEquals("Creating Preposition failed with different names",p.getName(),pname);		
		
		vf.setPreposition(p);		
		
		Assert.assertNotNull("Checking PhrasePrepositionLink value failed with null PhrasePrepositionLink",((Preposition)p).getFirstPrepositionLinkLinksToTarget().getAlpha());
		Assert.assertTrue("Checking PhrasePrepositionLink value failed with wrong class",((Preposition)p).getFirstPrepositionLinkLinksToTarget().getAlpha() instanceof PhrasePrepositionLink);
		PhrasePrepositionLink pl = (PhrasePrepositionLink)((Preposition)p).getFirstPrepositionLinkLinksToTarget().getAlpha();
		Assert.assertNotNull("Checking PhrasePrepositionLink value failed with null value",pl.getValue());
		Assert.assertEquals("Checking PhrasePrepositionLink failed with different names",pl.getValue(),pname);

	}
	
	@Test
	public void testToString() {
		
		ComplexVerbPhraseDTO cvf = facade.createComplexVerbPhraseDTO();
		
		SimpleVerbPhraseDTO svf = facade.createSimpleVerbPhraseDTO();
		
		NounPhraseDTO nf = facade.createNounPhraseDTO();
		NounPhraseDTO nf1 = facade.createNounPhraseDTO();
		
		String nname = "noun";				
		String mname = "modifier";
		String dname = "determiner";
		String vname = "verb";
		String pname = "preposition";
		
		NounDTO n = facade.createNounDTO(nname);
		ModifierDTO m = facade.createModifierDTO(mname);
		DeterminerDTO d = facade.createDeterminerDTO(dname);
		VerbDTO v = facade.createVerbDTO(vname);
		PrepositionDTO p = facade.createPrepostitionDTO(pname);
		
		
		Assert.assertEquals("ComplexVerbPhraseDTO.toString() fails for empty phrase","", cvf.toString());
		
		cvf.setSimpleVerbPhrase(svf);
		Assert.assertEquals("ComplexVerbPhraseDTO.toString() fails for empty phrase","", cvf.toString());
		
		svf.setVerb(v);			
		Assert.assertEquals("ComplexVerbPhraseDTO.toString()",vname, cvf.toString());
		
		cvf.setPreposition(p);			
		Assert.assertEquals("ComplexVerbPhraseDTO.toString()",vname+" "+pname, cvf.toString());
		
		svf.setObject(nf);
		Assert.assertEquals("ComplexVerbPhraseDTO.toString()",vname+" "+pname, cvf.toString());
		
		nf.setNoun(n);
		Assert.assertEquals("ComplexVerbPhraseDTO.toString() fails",vname+" "+nname+" "+pname, cvf.toString());
		
		nf.setModifier(m);
		Assert.assertEquals("ComplexVerbPhraseDTO.toString() fails",vname+" "+mname+" "+nname+" "+pname, cvf.toString());
		
		nf.setDeterminer(d);
		Assert.assertEquals("ComplexVerbPhraseDTO.toString() fails",vname+" "+dname+" "+mname+" "+nname+" "+pname, cvf.toString());
		
		cvf.setObject(nf1);
		Assert.assertEquals("ComplexVerbPhraseDTO.toString()",vname+" "+dname+" "+mname+" "+nname+" "+pname, cvf.toString());
		
		nf1.setNoun(n);
		Assert.assertEquals("ComplexVerbPhraseDTO.toString() fails",vname+" "+dname+" "+mname+" "+nname+" "+pname+" "+nname, cvf.toString());
		
		nf1.setModifier(m);
		Assert.assertEquals("ComplexVerbPhraseDTO.toString() fails",vname+" "+dname+" "+mname+" "+nname+" "+pname+" "+mname+" "+nname, cvf.toString());
		
		nf1.setDeterminer(d);
		Assert.assertEquals("ComplexVerbPhraseDTO.toString() fails",vname+" "+dname+" "+mname+" "+nname+" "+pname+" "+dname+" "+mname+" "+nname, cvf.toString());
		
	}

}
