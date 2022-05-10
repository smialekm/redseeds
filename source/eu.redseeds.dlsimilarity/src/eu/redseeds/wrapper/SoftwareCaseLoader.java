package eu.redseeds.wrapper;

import java.util.Vector;

import de.uni_koblenz.jgralab.Graph;
import de.uni_koblenz.jgralab.GraphIO;
import de.uni_koblenz.jgralab.GraphIOException;

import eu.redseeds.scl.SCLGraph;
import eu.redseeds.scl.rsl.rsldomainelements.actors.Actor;
import eu.redseeds.scl.rsl.rsldomainelements.actors.ActorsPackage;
import eu.redseeds.scl.rsl.rsldomainelements.notions.Notion;
import eu.redseeds.scl.rsl.rsldomainelements.notions.NotionsPackage;
import eu.redseeds.scl.rsl.rsldomainelements.phrases.ComplexVerbPhrase;
import eu.redseeds.scl.rsl.rsldomainelements.phrases.DeterminerLink;
import eu.redseeds.scl.rsl.rsldomainelements.phrases.ModifierLink;
import eu.redseeds.scl.rsl.rsldomainelements.phrases.NounLink;
import eu.redseeds.scl.rsl.rsldomainelements.phrases.NounPhrase;
import eu.redseeds.scl.rsl.rsldomainelements.phrases.PhrasePrepositionLink;
import eu.redseeds.scl.rsl.rsldomainelements.phrases.PhraseVerbLink;
import eu.redseeds.scl.rsl.rsldomainelements.phrases.SimpleVerbPhrase;
import eu.redseeds.scl.rsl.rsldomainelements.phrases.VerbPhrase;
import eu.redseeds.scl.rsl.rsldomainelements.systemelements.SystemElement;
import eu.redseeds.scl.rsl.rsldomainelements.systemelements.SystemElementsPackage;
import eu.redseeds.scl.rsl.rsldomainelements.terms.Determiner;
import eu.redseeds.scl.rsl.rsldomainelements.terms.Modifier;
import eu.redseeds.scl.rsl.rsldomainelements.terms.Noun;
import eu.redseeds.scl.rsl.rsldomainelements.terms.Preposition;
import eu.redseeds.scl.rsl.rsldomainelements.terms.Term;
import eu.redseeds.scl.rsl.rsldomainelements.terms.Verb;
import eu.redseeds.scl.rsl.rslrequirementrepresentationsentences.svosentences.Predicate;
import eu.redseeds.scl.rsl.rslrequirementrepresentationsentences.svosentences.SVOSentence;
import eu.redseeds.scl.rsl.rslrequirementrepresentationsentences.svosentences.Subject;
import eu.redseeds.scl.rsl.rslrequirements.requirementsspecifications.RSLUseCase;
import eu.redseeds.scl.sclkernel.SCLElement;

/**
 * This class provides some wrapper methods that are useful to
 * load, traverse and convert TGraphs.
 * 
 * @author Thorsten Krebs
 */
public class SoftwareCaseLoader {
	
	/**
	 * An instance of the <code>SCLGraph</code> that represents
	 * the software case (in TGraph format).
	 */
	private SCLGraph sclGraph = null;
	
	/**
	 * Constructor. Loads a software case (in TGraph format). 
	 * 
	 * @param graphFileName The name of the file that represents the
	 *                      software case to be loaded.
	 */
	public SoftwareCaseLoader(String graphFileName) {
		try {
			Graph graph = GraphIO.loadGraphFromFile(graphFileName, null);
			if (graph.getSchema().getQualifiedName().equals("eu.redseeds.scl.SCLSchema"))
				sclGraph = (SCLGraph)graph;
			else
				System.err.println("Error: imported TGraph is not an SCL Graph!!");			
		} catch (GraphIOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	/** new:
	 * Constructor. If we don't have a file but a graph variable... "load" this one
	 * @param graph
	 */
	public SoftwareCaseLoader(Graph graph) {
		if (graph.getSchema().getQualifiedName().equals("eu.redseeds.scl.SCLSchema"))
			sclGraph = (SCLGraph)graph;
		else
			System.err.println("Error: imported TGraph is not an SCL Graph!!");		
	}
	
	
	/**
	 * Gets the instance of the <code>SCLGraph</code> that represents
	 * the software case (in TGraph format).
	 * 
	 * @return The SCLGraph that represents the loaded software case.
	 */
	public SCLGraph getSCLGraph() {
		return sclGraph;
	}

// -------------
//     NOUNS
// -------------

	/**
	 * Gets the subject of a given instance of an <code>SVOSentence</code>.
	 * 
	 * @param svo The given <code>SVOSentence</code>.
	 * @return The subject that is contained in <code>svo</code>.
	 */
	public static Noun getSubjectFromSVOSentence(SVOSentence svo) {
				// We assume that a SVOSentence has exactly one Subject!
		Subject s = svo.getSubjectList().get(0);
		// We assume that a Subject has exactly one NounPhrase!
		SCLElement sclElem = s.getTargetList().get(0);
		if (sclElem instanceof NounPhrase) {
			NounPhrase np = (NounPhrase)sclElem;
			return getNounFromNounPhrase(np);
		} else //System.out.println("SCLElement \"" + sclElem + "\" is not a noun phrase!?");
			//System.out.println("SCLElement \"" + sclElem + "\" is not a noun phrase!?");
		return null;
	}
	
	/**
	 * Gets the object of a given instance of an <code>SVOSentence</code>.
	 * 
	 * @param svo The given <code>SVOSentence</code>.
	 * @return The object that is contained in <code>svo</code>.
	 */
	public static Noun getObjectFromSVOSentence(SVOSentence svo) {
		//Logger log = Logger.getLogger("eu.redseeds.wrapper.SoftwareCaseLoader.getObjFromSVO");
		// We assume that a SVOSentence has exactly one Predicate!
		Predicate p = svo.getPredicateList().get(0);
		// We assume that a Predicate has exactly one VerbPhrase!
		SCLElement sclVerb = p.getTargetList().get(0);
		if (sclVerb instanceof VerbPhrase) {
			VerbPhrase vp = (VerbPhrase)sclVerb;
			// We assume that a VerbPhrase has exactly one NounPhrase!
			// TODO :-)
			SCLElement sclNoun = vp.getObjectList().get(0);
			if (sclNoun instanceof NounPhrase) {
				NounPhrase np = (NounPhrase)sclNoun;
				return getNounFromNounPhrase(np);
			} else 
				
			System.out.println("SCLElement \"" + sclNoun + "\" is not a noun phrase!?");
		} else 
			
		System.out.println("SCLElement \"" + sclVerb + "\" is not a verb phrase!?");
		return null;
	}
	
	/**
	 * Gets the <code>Noun</code> of a given <code>NounPhrase</code>.
	 * 
	 * @param phrase The given <code>NounPhrase</code>.
	 * @return The <code>Noun</code> that is contained in
	 *         <code>phrase</code>.
	 */
	public static Noun getNounFromNounPhrase(NounPhrase phrase) {
		// We assume a NounPhrase has exactly one NounLink!
		NounLink nl = phrase.getNounList().get(0);
		// We assume a NounLink has exactly one Noun!
		Noun n = (Noun)nl.getTargetList().get(0);
		return n;
	}
	
//-------------
//    VERBS
//-------------

	/**
	 * Gets the verb of a given instance of an <code>SVOSentence</code>.
	 * 
	 * @param svo The given <code>SVOSentence</code>.
	 * @return The verb that is contained in <code>svo</code>.
	 */
	public static Verb getVerbFromSVOSentence(SVOSentence svo) {
		
		// We assume that a SVOSentence has exactly one Predicate!
		Predicate p = svo.getPredicateList().get(0);
		// We assume that a Predicate has exactly one VerbPhrase!
		SCLElement sclElem = p.getTargetList().get(0);
		if (sclElem instanceof VerbPhrase) {
			VerbPhrase vp = (VerbPhrase)sclElem;
			return getVerbFromVerbPhrase(vp);
		} else 
			System.out.println("SCLElement \"" + sclElem + "\" is not a verb phrase!?");
		return null;
	}
	
	/**
	 * Gets the <code>Verb</code> of a given <code>VerbPhrase</code>.
	 * 
	 * @param phrase The given <code>VerbPhrase</code>.
	 * @return The <code>Verb</code> that is contained in
	 *         <code>phrase</code>.
	 */
	public static Verb getVerbFromVerbPhrase(VerbPhrase phrase) {
		
		SimpleVerbPhrase simple = null;
		if (phrase instanceof SimpleVerbPhrase)
			simple = (SimpleVerbPhrase)phrase;
		else if (phrase instanceof ComplexVerbPhrase)
			// We assume that a ComplexVerbPhrase has exactly one SimpleVerbPhrase!
			simple = ((ComplexVerbPhrase)phrase).getSimpleVerbPhraseList().get(0);
		else 
		System.out.println("Phrase \"" + phrase + "\" is neither a simple nor a complex verb phrase!?"); 
		// We assume a SimpleVerbPhrase has exactly one PhraseVerbLink!
		PhraseVerbLink pvl = simple.getVerbList().get(0);
		// We assume a PhraseVerbLink has exactly one Verb!
		Verb v = (Verb)pvl.getTargetList().get(0);
		return v;
	}
	
//-------------
//  MODIFIER
//-------------
	
	/**
	 * Gets the <code>Modifier</code> of a given <code>NounPhrase</code>.
	 * 
	 * @param phrase The given <code>NounPhrase</code>.
	 * @return The <code>Modifier</code> that is contained in
	 *         <code>phrase</code>.
	 */
	public static Modifier getModifierFromNounPhrase(NounPhrase phrase) {
		// We assume a NounPhrase has at most one ModifierLink!
		for (ModifierLink link : phrase.getModifierList()) {
			// We assume a ModifierLink has exactly one Modifier!
			Modifier m = (Modifier)link.getTargetList().get(0);
			return m;
		}
		return null;
	}

//-------------
// DETERMINER
//-------------
	
	/**
	 * Gets the <code>Determiner</code> of a given <code>NounPhrase</code>.
	 * 
	 * @param phrase The given <code>NounPhrase</code>.
	 * @return The <code>Determiner</code> that is contained in
	 *         <code>phrase</code>.
	 */
	public static Determiner getDeterminerFromNounPhrase(NounPhrase phrase) {
		// We assume a NounPhrase has at most one DeterminerLink!
		for (DeterminerLink link : phrase.getDeterminerList()) {
			// We assume a DeterminerLink has exactly one Modifier!
			Determiner d = (Determiner)link.getTargetList().get(0);
			return d;			
		}
		return null;
	}

//-------------
// PREPOSITION
//-------------

	/**
	 * Gets the <code>Preposition</code> of a given <code>VerbPhrase</code>.
	 * 
	 * @param phrase The given <code>VerbPhrase</code>.
	 * @return The <code>Preposition</code> that is contained in
	 *         <code>phrase</code>.
	 */
	public static Preposition getPrepositionFromVerbPhrase(VerbPhrase phrase) {
		// We assume a VerbPhrase has at most one PhrasePrepositionLink!
		for (PhrasePrepositionLink link : phrase.getPrepositionList()) {
			// We assume a PhrasePrepositionLink has exactly one Preposition!
			Preposition p = (Preposition)link.getTargetList().get(0);
			return p;			
		}
		return null;
	}

// ----------------
	
	/**
	 * Gets the unique ID of a given <code>SCLElement</code>.
	 * 
	 * @param element The given <code>SCLElement</code>.
	 * @return The unique ID of <code>element</code>.
	 */
	public static String getSoftwareCaseUid(SCLElement element) {
		return ((SCLGraph)element.getGraph()).getUid();
	}
	
// ----------------
	
	/**
	 *  Gets all <code>Term</code>s of the currently loaded software case.
	 */
	public Iterable<Term> getAllTerms(){
		return sclGraph.getTermVertices();
	}
	
	/**
	 * Gets all <code>Actor</code>s of the currently loaded software case.
	 * 
	 * @return An <code>Iterable</code> that contains all instances of
	 *         <code>Actor</code>.
	 */
	public Iterable<Actor> getAllActors() {
		Vector<Actor> actors = new Vector<Actor>();
		for (ActorsPackage ap : sclGraph.getActorsPackageVertices()) {
			for (Actor a : ap.getActorList()) {
				actors.add(a);
			}
		}
		return actors;
	}

	/**
	 * Gets all <code>SystemElement</code>s of the currently loaded software case.
	 * 
	 * @return An <code>Iterable</code> that contains all instances of
	 *         <code>SystemElement</code>.
	 */
	public Iterable<SystemElement> getAllSystemElements() {
		Vector<SystemElement> systemElements = new Vector<SystemElement>();
		for (SystemElementsPackage sep : sclGraph.getSystemElementsPackageVertices()) {
			for (SystemElement se : sep.getSystemElementList()) {
				systemElements.add(se);
			}
		}
		return systemElements;
	}

	/**
	 * Gets all <code>Notion</code>s of the currently loaded software case.
	 * 
	 * @return An <code>Iterable</code> that contains all instances of
	 *         <code>Notion</code>.
	 */
	public Iterable<Notion> getAllNotions() {
		Vector<Notion> notions = new Vector<Notion>();
		for (NotionsPackage np : sclGraph.getNotionsPackageVertices()) {
			for (Notion n : np.getNotionList()) {
				notions.add(n);
			}
		}
		return notions;
	}

	/**
	 * Gets all <code>UseCase</code>s of the currently loaded software case.
	 * 
	 * @return An <code>Iterable</code> that contains all instances of
	 *         <code>UseCase</code>.
	 */
	public Iterable<RSLUseCase> getAllUseCases() {
		return sclGraph.getRSLUseCaseVertices();
	}
	
}
