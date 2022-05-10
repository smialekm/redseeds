package eu.redseeds.owl.reasoning;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.stanford.smi.protege.exception.OntologyException;
import edu.stanford.smi.protege.model.Cls;
import edu.stanford.smi.protege.model.Instance;
import edu.stanford.smi.protegex.owl.ProtegeOWL;
import edu.stanford.smi.protegex.owl.inference.dig.exception.DIGReasonerException;
import edu.stanford.smi.protegex.owl.inference.dig.reasoner.DIGReasonerIdentity;
import edu.stanford.smi.protegex.owl.inference.protegeowl.ProtegeOWLReasoner;
import edu.stanford.smi.protegex.owl.inference.protegeowl.ReasonerManager;
import edu.stanford.smi.protegex.owl.jena.JenaOWLModel;
import edu.stanford.smi.protegex.owl.model.OWLDatatypeProperty;
import edu.stanford.smi.protegex.owl.model.OWLIndividual;
import edu.stanford.smi.protegex.owl.model.OWLIntersectionClass;
import edu.stanford.smi.protegex.owl.model.OWLNamedClass;
import edu.stanford.smi.protegex.owl.model.OWLObjectProperty;
import edu.stanford.smi.protegex.owl.model.OWLSomeValuesFrom;
import edu.stanford.smi.protegex.owl.model.RDFProperty;
import edu.stanford.smi.protegex.owl.model.RDFResource;
import edu.stanford.smi.protegex.owl.model.RDFSClass;
import edu.stanford.smi.protegex.owl.model.impl.DefaultRDFProperty;
import eu.redseeds.owl.Activator;
import eu.redseeds.owl.Properties;
import eu.redseeds.owl.SupportFunctions;
import eu.redseeds.owl.converter.WordNet2OWL;
import eu.redseeds.scl.rsl.rsldomainelements.domainelements.DomainElement;
import eu.redseeds.scl.rsl.rslkernel.elements.RepresentableElement;
import eu.redseeds.scl.rsl.rslrequirements.requirementsspecifications.Requirement;


/**
 * This class provides the major functionality for the DL-based
 * similarity measure, such as comparing software cases or other
 * elements (i.e. OWL Classes), reasoning about the taxonomic or
 * the role-based properties of elements, etc. 
 * 
 * @author Thorsten Krebs, Arved Solth
 */
public class OWLReasoner {
	

	Map<OWLNamedClass, Map<OWLNamedClass, Double>> similarityMap;
	
	private double _similarity = -1;
	
	 public static final String workingDir = System.getProperty("user.dir");

	// SCL SCHEMA
	public static final String sclSchema  = "http://eu.redseeds.scl.SCLSchema#";
	public static final String wn20schema = "http://de.uni_koblenz.jgwnl.wordnetschema.WordNetSchema#";

	// Software Case
	public static final String SOFTWARE_CASE              = "sclkernel.SoftwareCase";
	public static final String REQUIREMENTS_SPECIFICATION = "rsl.rslrequirements.requirementsspecifications.RequirementsSpecification";
	// Representable Elements
	public static final String REQUIREMENT = "rsl.rslrequirements.requirementsspecifications.Requirement";
	public static final String DOMAIN_ELEMENT = "rsl.rsldomainelements.domainelements.DomainElement";
	// SCL Graph
	public static final String SCL_GRAPH          = "SCLGraph";
	public static final String SCL_GRAPH_HAS_UID  = "sCLGraphHasUid";
	public static final String VERTEX_IS_IN_GRAPH = "vertexIsInGraph";
	// Phrases
	public static final String NOUN_PHRASE         = "rsl.rsldomainelements.phrases.NounPhrase";
	public static final String SIMPLE_VERB_PHRASE  = "rsl.rsldomainelements.phrases.SimpleVerbPhrase";
	public static final String COMPLEX_VERB_PHRASE = "rsl.rsldomainelements.phrases.ComplexVerbPhrase";
	// Notions
	public static final String NOTION = "rsl.rsldomainelements.notions.Notion";
	// Word types
	public static final String NOUN = "rsl.rsldomainelements.terms.Noun";
	public static final String VERB = "rsl.rsldomainelements.terms.Verb";
	// UIElements
	public static final String UI_CONTAINER = "rsl.rsldomainelements.uielements.UIContainer";
	// Scenarios
	public static final String ACTIVITY_SCENARIO               = "rsl.rslrequirementrepresentations.activityrepresentations.ActivityScenario";
	public static final String CONSTRAINED_LANGUAGE_SCENARIO   = "rsl.rslrequirementrepresentations.descriptiverequirementrepresentations.ConstrainedLanguageScenario";
	public static final String INTERACTION_SCENARIO            = "rsl.rslrequirementrepresentations.interactionrepresentations.InteractionScenario";
	// SVO Sentence
	public static final String SVO_SENTENCE = "rsl.rslrequirementrepresentationsentences.svosentences.SVOSentence";
	
	private static JenaOWLModel ontology;
	private static OWLNamedClass softwareCaseClass;
	private static OWLNamedClass requirementsSpecificationClass;
	private static OWLNamedClass requirementClass;
	private static OWLNamedClass domainElementClass;
	private static OWLNamedClass sclGraphClass;
	private static OWLDatatypeProperty sclGraphHasUIDProperty;
	private static OWLObjectProperty vertexIsInGraphProperty;
	private static OWLNamedClass nounPhraseClass;
	private static OWLNamedClass simpleVerbPhraseClass;
	private static OWLNamedClass complexVerbPhraseClass;
	private static OWLNamedClass notionClass;
	private static OWLNamedClass uiContainerClass;
	private static OWLNamedClass activityScenarioClass;
	private static OWLNamedClass constrainedLanguageScenarioClass;
	private static OWLNamedClass interactionScenarioClass;
	private static OWLNamedClass svoSentenceClass;
	private static OWLNamedClass nounClass;
	private static OWLNamedClass verbClass;
		
	// concepts, for which taxonomic distance is interesting /for testing!
	private static Vector<OWLNamedClass> interestingConcepts = new Vector<OWLNamedClass>();
	
	// for performance checks of roleBasedSimilarity2
	private static int conceptSimCalls = 0;
	private static int roleSimCalls = 0;
	private static int preventedComparesCount = 0;
	private static int getAllMappingsCalls = 0;
	
	// for more convenient "system out printlining"!
	private static int recursionDepth = 0;
	private static String separationLine = "---------------------------------";
	private static String indent = "  ";
	private static String scOperand;
	private static String queryOperand;
	private static String firstLine;
	
	// weights to tune results of roleBasedSimilarity
	// for concept sim calculation:
	// weight for taxonomic similarity between concepts
	private static double taxSimWeight = 0.1;
	// weight for role similarity between concepts
	private static double roleSimWeight = 1-taxSimWeight;
	// for role sim calculation
	// weight for taxonomic similarity between roles
	private static double taxRoleSimWeight = 0.1;
	// weight for filler similarity between roles
	private static double fillerSimWeight = 1-taxRoleSimWeight;	
	
	// vector of owl classes, whose inheriting classes should be compared
	private static Vector<String> conceptsToBeCompared = new Vector<String>();
	
	// owl property used to identify word net entries (e.g. synsets)
	private static OWLObjectProperty termLinksToWordNetEntryOfProperty = null;
	@SuppressWarnings("unused")
	private static OWLObjectProperty termLinksToWordNetEntryProperty = null;

	// maps collecting the similarities between owlclasses representing 
	// representable element pairs of both graphs
	private static Map<OWLNamedClass, Map<OWLNamedClass, Double>> requirementSimilarityMap;
	private static Map<OWLNamedClass, Map<OWLNamedClass, Double>> domainElementSimilarityMap;
   	
	/**
	 * Constructor. Loads the TBox and initializes all required private
	 * variables for the instance of this class. 
	 * 
	 * @param scFileName The name of the file containing the OWL ontology
	 *                   ("TBox"), incl. the software cases and the WordNet
	 *                   terms.
	 */
	public OWLReasoner(String scFileName) {
		try {			
			ontology = ProtegeOWL.createJenaOWLModelFromURI(WordNet2OWL.fileName2URIString(scFileName));	       
	        initializeReasoner();
		} catch (OntologyException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Constructor. Loads the TBox and initializes all required private
	 * variables for the instance of this class. 
	 * 
	 * @param scFileName The name of the file containing the OWL ontology
	 *                   ("TBox"), incl. the software cases and the WordNet
	 *                   terms.
	 */
	public OWLReasoner(JenaOWLModel ont) {
		try {
			ontology = ont;
	        initializeReasoner();
		} catch (OntologyException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
	private void initializeReasoner(){
		similarityMap = new HashMap<OWLNamedClass, Map<OWLNamedClass, Double>>();
		ontology.getNamespaceManager().setDefaultNamespace(sclSchema);
		
        softwareCaseClass = ontology.getOWLNamedClass(SOFTWARE_CASE);
        requirementsSpecificationClass = ontology.getOWLNamedClass(REQUIREMENTS_SPECIFICATION);
        requirementClass = ontology.getOWLNamedClass(REQUIREMENT);
        domainElementClass = ontology.getOWLNamedClass(DOMAIN_ELEMENT);
        sclGraphClass = ontology.getOWLNamedClass(SCL_GRAPH);
        sclGraphHasUIDProperty = ontology.getOWLDatatypeProperty(SCL_GRAPH_HAS_UID);
        vertexIsInGraphProperty = ontology.getOWLObjectProperty(VERTEX_IS_IN_GRAPH);
        nounPhraseClass = ontology.getOWLNamedClass(NOUN_PHRASE);
        simpleVerbPhraseClass = ontology.getOWLNamedClass(SIMPLE_VERB_PHRASE);
        complexVerbPhraseClass = ontology.getOWLNamedClass(COMPLEX_VERB_PHRASE);
        notionClass = ontology.getOWLNamedClass(NOTION);
        uiContainerClass = ontology.getOWLNamedClass(UI_CONTAINER);
        activityScenarioClass = ontology.getOWLNamedClass(ACTIVITY_SCENARIO);
        constrainedLanguageScenarioClass = ontology.getOWLNamedClass(CONSTRAINED_LANGUAGE_SCENARIO);
        interactionScenarioClass = ontology.getOWLNamedClass(INTERACTION_SCENARIO);
        svoSentenceClass = ontology.getOWLNamedClass(SVO_SENTENCE);
        nounClass = ontology.getOWLNamedClass(NOUN);
        verbClass = ontology.getOWLNamedClass(VERB);
        
        conceptsToBeCompared = Properties.CONCEPTS_TO_BE_COMPARED;
        
        requirementSimilarityMap = new HashMap<OWLNamedClass, Map<OWLNamedClass, Double>>();
        domainElementSimilarityMap = new HashMap<OWLNamedClass, Map<OWLNamedClass, Double>>();
        
		termLinksToWordNetEntryOfProperty = ontology.getOWLObjectProperty("rsl.rsldomainelements.terms.TermLinksToWordNetEntry-of");
		termLinksToWordNetEntryProperty = ontology.getOWLObjectProperty("rsl.rsldomainelements.terms.TermLinksToWordNetEntry");        
		
		// gather interesting upper models for taxonomic distance testing
		interestingConcepts.add(svoSentenceClass);
		interestingConcepts.add(nounClass);
		interestingConcepts.add(verbClass);        
	}
	
	/**
	 * Wrapper method that calls the <code>classify()</code> method of the
	 * underlying Pellet reasoner.
	 */
    public void classify() {
    	
        try {        	        	        	        	
             final String REASONER_URL = "http://localhost:"+Properties.DEFAULT_PORT;
             
             // Get the reasoner manager and obtain a reasoner for the OWL model.
             ReasonerManager reasonerManager = ReasonerManager.getInstance();
             ProtegeOWLReasoner reasoner = reasonerManager.getReasoner(ontology);             
             
             // Set the reasoner URL and test the connection
             reasoner.setURL(REASONER_URL);
             if(reasoner.isConnected()) {
                 // Get the reasoner identity - this contains information
                 // about the reasoner, such as it's name and version,
                 // and the tell and ask operations that it supports.
                 DIGReasonerIdentity reasonerIdentity = reasoner.getIdentity();
                 Activator.logInfo("Connected to " + reasonerIdentity.getName());
                
                 // We can classify the whole ontology, which will put the
                 // inferred class hierarchy information directly into the Prot�g�-OWL model.
//                 Activator.logInfo("Classifying taxonomy...");
//                 String testClass = "rsl.rsldomainelements.terms.Noun_68d91225-47993145-151078a3-68fa67db_7a25be32-739931ce-298f1044-e3f40d73";
//                 String testClassForChangedSuper = "rsl.rsldomainelements.terms.Verb_68d91225-47993145-151078a3-68fa67db_21d28d83-b01f8589-fb4e2f56-68cbe32b";
//                 printBeforeClassify(testClass);
//                 printBeforeClassify(testClassForChangedSuper);                 
                 
                 reasoner.classifyTaxonomy(null);
//                 printAfterClassify(testClass);
//                 printAfterClassify(testClassForChangedSuper);
                 Activator.logInfo("...Classified taxonomy!");
             }
             else {    
            	 Activator.logWarning("WARNING: No reasoner service connected!");
             }
         }
         catch(DIGReasonerException e){
        	 Activator.logError("WARNING: Out of memory...abandoning classification of taxonomy!", e);
        	 e.printStackTrace();
         }
         catch(Exception e) {
             e.printStackTrace();
        }

    }

    /**
     * Gets the unique ID of the software case to which a given
     * <code>SCLElement</code> (represented as OWL class) belongs.
     * 
     * @param class1 The OWL class representing some <code>SCLElement</code>.
     * @return The unique ID of <code>class1</code>.
     */
    public String getSoftwareCaseUid(OWLNamedClass class1) {
    	String uid = null;
    	//Activator.logInfo("Class1: "+class1.getName());
    	if (class1.isSubclassOf(sclGraphClass)) {
//    		Activator.logInfo("Class "+class1.getName()+" is subclass of scl graph!");
    		uid = (String)class1.getPropertyValue(sclGraphHasUIDProperty);
    	} else {
//    		Activator.logInfo("Class "+class1.getName()+" is NOT subclass of scl graph!");
    		OWLNamedClass graphClass = (OWLNamedClass)class1.getPropertyValue(vertexIsInGraphProperty);
    		uid = (String)graphClass.getPropertyValue(sclGraphHasUIDProperty);
    		if (uid == null) {
    			// Dirty hack, but works when the graph has no UID set! ;-)
        		uid = graphClass.getName().substring(graphClass.getName().lastIndexOf("#") + 1, graphClass.getName().length());    			
    		}    		
    	}
		return uid;
    }
    
    /**
     * Gets the OWL individual with the given name.
     * 
     * @param name The name of the sought OWL individual. 
     * @return The OWL individual with the name <code>name>/code>.
     *         Returns <ocde>null</code> if there is no OWL individual
     *         with that name.
     */
    public OWLIndividual getInstanceWithName(String name) {
    	Iterator<Instance> instances = ontology.getInstances().iterator();
    	while (instances.hasNext()) {
    		Instance current = instances.next();
    		if (current instanceof OWLIndividual) {
    			OWLIndividual instance = (OWLIndividual)current;
    			String instanceName = instance.getName();
    			if (instanceName.equals(name))
    				return instance;
    		}
    	}
    	return null;
    }

    /**
     * Gets all subconcepts of an "upper model" concept with a given name.
	 * <br>
	 * <br>
     * The name has to be one of the following static, final constants
     * (defined within this class):
     * <ul>
     *   <li>NOUN_PHRASE
     *   <li>SIMPLE_VERB_PHRASE
     *   <li>COMPLEX_VERB_PHRASE
     *   <li>NOTION
     *   <li>UI_CONTAINER
     *   <li>ACTIVITY_SCENARIO
     *   <li>CONSTRAINED_LANGUAGE_SCENARIO
     *   <li>INTERACTION_SCENARIO
     *   <li>SOFTWARE_CASE
     *   <li>REQUIREMENTS_SPECIFICATION
     * </ul>
     * 
     * @param type The name of the "upper model" concept.
     * @return An <code>Iterator</code> containing all subcocnepts of the
     *         given "upper model" concept. Returns <code>null</code> if
     *         there is no concept with the given name.
     */    
	@SuppressWarnings("unchecked")
	public Iterable<RDFResource> getAllSubclassesOfType(String type) {
    	OWLNamedClass superclass = null;
    	if (type.equals(NOUN_PHRASE))
    		superclass = nounPhraseClass;
    	else if (type.equals(SIMPLE_VERB_PHRASE))
    		superclass = simpleVerbPhraseClass;
    	else if (type.equals(COMPLEX_VERB_PHRASE))
    		superclass = complexVerbPhraseClass;
    	else if (type.equals(NOTION))
    		superclass = notionClass;
    	else if (type.equals(UI_CONTAINER))
    		superclass = uiContainerClass;
    	else if (type.equals(ACTIVITY_SCENARIO))
    		superclass = activityScenarioClass;
    	else if (type.equals(CONSTRAINED_LANGUAGE_SCENARIO))
    		superclass = constrainedLanguageScenarioClass;
    	else if (type.equals(INTERACTION_SCENARIO))
    		superclass = interactionScenarioClass;
    	else if (type.equals(SOFTWARE_CASE))
    		superclass = softwareCaseClass;
    	else if (type.equals(REQUIREMENTS_SPECIFICATION))
    		superclass = requirementsSpecificationClass;
    	if (superclass != null)
    		return superclass.getSubclasses(false); 
    	else return null;
    }
    
    /**
     * Creates an OWL instance of the given OWL class. The instance will
     * carry the same name as the class, suffixed by "_instance".
     * 
     * @param class1 The given OWL class.
     * @return The new OWL individual.
     */
	public OWLIndividual createIndividual(OWLNamedClass class1) {
		return class1.createOWLIndividual(class1.getName() + "_instance");
	}

	/**
	 * Checks if two given OWLNamedClasses are equivalent. 
	 * 
	 * @param class1 
	 * @param class2
	 * 
	 * @return true, if class1 is equivalent to class2
	 * @return false else
	 */
	public static boolean equivalentClasses(RDFSClass class1, OWLNamedClass class2){
		// check if class1 and class2 are same owl class
		if(class1.equals(class2)){
			return true;
		}
		else{
			if(class1 instanceof OWLNamedClass){
				// check, if class1 is equivalent to class2 in dl
				if(((OWLNamedClass)class1).getInferredEquivalentClasses().contains(class2) || class1.getEquivalentClasses().contains(class2)){
					return true;				
				}			
				else return false;				
			}
			else return false;
		}					
	}
	
	/**
	 * Computes the least common subsumer of two given OWL classes.
	 * 
	 * @param class1 The first given OWL class.
	 * @param class2 The second given OWL class.
	 * @return The least common subsumer of <code>class1</code> and
	 *         <code>class2</code>, i.e. the most specific concept that
	 *         is superconcept of both.
	 */
	@SuppressWarnings("unchecked")
	protected static OWLNamedClass leastCommonSubsumer(OWLNamedClass class1, OWLNamedClass class2) {
//		Activator.logInfo(" --- Searching least common subsumer of classes ");
//		Activator.logInfo("   - "+getShortName(class1)+" and");
//		Activator.logInfo("   - "+getShortName(class2));		

		Collection<?> class1Path = class1.getSuperclasses(true);
		Collection<?> class2Path = class2.getSuperclasses(true);
		// Ensure correct behavior if nothing is inferred!
		if(Properties.CLASSIFY){
//			Collection<OWLNamedClass> c1InferredSCs = class1.getInferredSuperclasses();
//			Collection<OWLNamedClass> c2InferredSCs = class2.getInferredSuperclasses();
//			Activator.logInfo("  -- Found "+c1InferredSCs.size()+" inferred SUPERCLASSES of class "+class1.getName()+":");
//			for(OWLNamedClass currentClass : c1InferredSCs) Activator.logInfo("    - "+currentClass.getName());			
//			Activator.logInfo("  -- Found "+c2InferredSCs.size()+" inferred SUPERCLASSES of class "+class2.getName()+":");
//			for(OWLNamedClass currentClass : c2InferredSCs) Activator.logInfo("    - "+currentClass.getName());
			
			class1Path.addAll(class1.getInferredSuperclasses());
			class2Path.addAll(class2.getInferredSuperclasses());
			
//			boolean addedInfToPath1 = class1Path.addAll(class1.getInferredSuperclasses());
//			boolean addedInfToPath2 = class2Path.addAll(class2.getInferredSuperclasses());
			
//			Activator.logInfo(" Added inferred super classes to path of class 1: "+addedInfToPath1);
//			Activator.logInfo(" Added inferred super classes to path of class 2: "+addedInfToPath2);
			
		}
		
		// Check if one of the two concepts is the LCS!
		if (class1Path.contains(class2)){
//			Activator.logInfo(" - Path of "+getShortName(class1)+" contains "+getShortName(class2));
			return class2;
		}			
		else if (class2Path.contains(class1)){
//			Activator.logInfo(" - Path of "+getShortName(class2)+" contains "+getShortName(class1));
			return class1;
		}
			
		// OK, now check which one of the parents is the LCS!
		Iterator<?> class1Iterator = class1Path.iterator();
		int minDist = Integer.MAX_VALUE;
		int currentDist = 0;
		OWLNamedClass lcs = null;
		while (class1Iterator.hasNext()) {
			Object current = class1Iterator.next();
			// Why-ever there are superclasses different than classes (e.g. cardinality)!?
			if (current instanceof OWLNamedClass) {
				if (class2Path.contains(current)){
//					Activator.logInfo(" - Found "+getShortName((OWLNamedClass)current));
					currentDist = pathDistance2(class2, (OWLNamedClass)current);
					if(currentDist<minDist){
						minDist = currentDist;
						lcs = (OWLNamedClass)current;
					}					
				}
			}
		}	
		if(lcs!=null){
//			Activator.logInfo(" -> LCS: "+getShortName(lcs));
			return lcs;				
		}
		Activator.logError(" *****  ERROR! No LCS found! Should alway find at least OWL:Thing! ***** ", null);
		assert false;
		return null;
	}
	
	/**
	 * Calculates the distance between two OWL classes that are on one
	 * taxonomic path.
	 * 
	 * @param subclass The more specific class of the two classes.
	 * @param superclass The more generic class of the two classes.
	 * @return An integer denoting the taxonomic distance between
	 *         <code>subclass</code> and <code>superclass</code>. The
	 *         integer is the number of is-a relations between them.
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	private static int pathDistance(OWLNamedClass subclass, OWLNamedClass superclass) {
		Activator.logInfo(" Computing path distance between "+getShortName(subclass)+" and LCS "+getShortName(superclass));
		int distance = 0;
		if (subclass.equals(superclass)){
			Activator.logInfo("   "+getShortName(subclass)+" IS the LCS, returning distance 0!");
			return distance;
		}
		OWLNamedClass parent = subclass;
		while (parent.hasNamedSuperclass()) {
			Activator.logInfo("   "+getShortName(parent)+" has following named super class(es):");
			// TODO: inferred super classes have to be iterated as well, if classify was called!
			Collection<Cls> allSuperclasses = parent.getSuperclasses(true);
//			allSuperclasses.addAll(parent.getInferredSuperclasses());
			
			for(Cls p : allSuperclasses){
				if(p instanceof OWLNamedClass){
					Activator.logInfo("      - "+getShortName((OWLNamedClass)p));					
				}
			}
			
			Iterator<Cls> parents = allSuperclasses.iterator();
			while (parents.hasNext()) {
				Cls current = parents.next();
				if (current instanceof OWLNamedClass) {
					parent = (OWLNamedClass)current;
					break;
				}
			}
			distance++;
			if (parent.equals(superclass))
				return distance;
		}
		Activator.logInfo("   "+getShortName(parent)+" doesn't have named super class(es)!");
		return -1;
	}	
	private final static int CHILDREN_OF_THE_SAME_UPPER_MODEL_CONCEPT = -2;
	private final static int CHILD_OF_UPPER_MODEL_CONCPET_AND_INSTANCE_OF_THE_SAME_UPPER_MODEL_CONCEPT = -3;
	
	
	@SuppressWarnings("unchecked")
	private static int pathDistance2(OWLNamedClass class1, OWLNamedClass lcs){	
//		Activator.logInfo(" ---------");
//		Activator.logInfo(" Computing path distance between "+getShortName(class1)+" and LCS "+getShortName(lcs)+"...");		
		if(equivalentClasses(class1, lcs)){
//			Activator.logInfo(getShortName(class1)+" and "+getShortName(lcs)+" are equivalent: returning distance 0!");
			return 0;
		}
		Collection<OWLNamedClass> directSuperclasses = class1.getSuperclasses(false);
		Collection<OWLNamedClass> inferredSuperclasses = class1.getInferredSuperclasses();		
		
		if(inferredSuperclasses.contains(lcs)){
			return 1;
		}	
		
		// check inferred superclasses as well!
		directSuperclasses.addAll(inferredSuperclasses);
		
//		if(directSuperclasses.contains(lcs)){
//			return 1;
//		}
				
		// it is expected that lcs is always a superclass of class1; but it may occur in the
		// inferred hierachy that class1 has more than one superclasses that can be superclass
		// of lcs as well! if lcs is subclass is subclass of class1 you will never reach lcs with
		// pathDistance2!
		if((lcs.getSuperclasses(true)).contains(class1)){
//			Activator.Warning(" WARNING: Returning path distance -1!");
			return -1;
		}
		
		// check direct superclasses if no superclasses where inferred 
		// (that is, before classification);
		// check inferred superclasses only(!) after classification
		Collection<OWLNamedClass> superclasses;
		if(inferredSuperclasses.isEmpty()) 	superclasses = directSuperclasses;
		else 								superclasses = inferredSuperclasses;
		
		for(RDFSClass current : superclasses){
//			Activator.logInfo("    checking "+getShortName(class1)+"'s superclass "+current.getName());
			if(current instanceof OWLNamedClass){				
//				Activator.logInfo("     ... is owl named class!");
				int result = pathDistance2((OWLNamedClass)current,lcs);
				if(result==-1)
					continue;
				else
					return result+1;
			}
			else{
//				Activator.logInfo("     ... is NOT owl named class!");
			}
		}
		Activator.logWarning(" ERROR: couldn't compute path distance between "+getShortName(class1)+" and "+getShortName(lcs));
//		Activator.logInfo("  "+getShortName(class1)+" is OWLNamedClass: "+(class1 instanceof OWLNamedClass));
//		Activator.logInfo("  "+getShortName(lcs)+" is OWLNamedClass: "+(lcs instanceof OWLNamedClass));
		assert false;
		return -1;
	}
	
	/**
	 * Calculates the taxonomic distance between two OWL classes. The
	 * classes need not be on the same taxonomic path.
	 * <br>
	 * <br>
	 * Basically, the taxonomic distance between the two classes is the
	 * sum of the distances between either of them to their least common
	 * subsumer.
	 * 
	 * @param class1 The first OWL class.
	 * @param class2 The second OWL class.
	 * @return An integer that denotes the taxonomic distance between
	 *         <code>class1</code> and <code>class2</code>.
	 */
	private static int taxonomicDistance(OWLNamedClass class1, OWLNamedClass class2) {		
		// return distance 0 if classes are equivalent
		if(equivalentClasses(class1, class2)){
//			printTaxonomicDistanceBetweenInterestingConcepts(class1, class2, 0);
			return 0;
		}
		OWLNamedClass lcs = leastCommonSubsumer(class1, class2);
		if (lcs == null){
//			printTaxonomicDistanceBetweenInterestingConcepts(class1, class2, -1);
			return -1;	
		}
		int class1Distance = pathDistance2(class1, lcs);		
		int class2Distance = pathDistance2(class2, lcs);
//		if(isInterestingForTaxonomicDistance(class1)) Activator.logInfo(" Computing path distance between "+getShortName(class1)+" and LCS "+getShortName(lcs)+": "+class1Distance);
//		if(isInterestingForTaxonomicDistance(class2)) Activator.logInfo(" Computing path distance between "+getShortName(class2)+" and LCS "+getShortName(lcs)+": "+class2Distance);
//		printTaxonomicDistanceBetweenInterestingConcepts(class1, class2, (class1Distance+class2Distance));
		return class1Distance + class2Distance;
	}
	
	/**
	 * Calculates the taxonomic similarity between two OWL classes.
	 * <br>
	 * <br>
	 * The taxonomic similarity is based on the taxonomic distance, but
	 * the return value is a normalized value between 0 and 1. 
	 * 
	 * @param class1 The first OWL class.
	 * @param class2 The second OWL class.
	 * @return A value between 0 and 1 that denotes the similarity of
	 *         <code>class1</code> and <code>class2</code>. The larger
	 *         the value, the more similar and the smaller the value,
	 *         the less similar the two classes are. A value of 0 denotes
	 *         that both classes have nothing in common and a value of 1
	 *         denotes identical classes.
	 */
	private static double taxonomicSimilarity(OWLNamedClass class1, OWLNamedClass class2) {
		int taxDistance = taxonomicDistance(class1, class2);
//		Activator.logInfo(" Tax dist between "+getShortName(class1)+" and "+getShortName(class2)+": "+taxDistance);
		if (taxDistance == CHILDREN_OF_THE_SAME_UPPER_MODEL_CONCEPT)
			return 1;
		else if (taxDistance == CHILD_OF_UPPER_MODEL_CONCPET_AND_INSTANCE_OF_THE_SAME_UPPER_MODEL_CONCEPT)
			return 1;
		else {
//			Activator.logInfo(" Math.log("+taxDistance+"+"+Math.E+") = "+ (double)Math.log(taxDistance + Math.E));
//			Activator.logInfo(" 1/ Math.log("+taxDistance+"+"+Math.E+") = "+ 1/(double)Math.log(taxDistance + Math.E));			
			return 1 / (double)Math.log(taxDistance + Math.E);
		}
	}
	
	/**
	 * Computes the least common subsumer of two given OWL properties.
	 * 
	 * @param individual The first given OWL property.
	 * @param class2 The second given OWL property.
	 * @return The least common subsumer of <code>property1</code> and
	 *         <code>property2</code>, i.e. the most specific property that
	 *         is super-property of both.
	 */
	@SuppressWarnings("unchecked")
	protected static OWLObjectProperty leastCommonSubsumer(OWLObjectProperty property1, OWLObjectProperty property2) {
		if (property1.equals(property2)){
			return property1;			
		}
		else if(property1.getName().equals(property2.getName())){
			return property1;
		}
		Iterator<RDFProperty> property1Path = property1.getSuperproperties(true).iterator();
		Collection<RDFProperty> property2Path = property2.getSuperproperties(true);
		while (property1Path.hasNext()) {
			RDFProperty current = property1Path.next();
			// Can there be superproperties different than OWL object properties!?
			if (current instanceof OWLObjectProperty) {
				if (property2Path.contains(current))
					return (OWLObjectProperty)current;
			}
		}
		return null;
	}

	/**
	 * Calculates the distance between two OWL properties that are on one
	 * taxonomic path.
	 * 
	 * @param subproperty The more specific property of the two properties.
	 * @param superproperty The more generic property of the two properties.
	 * @return An integer denoting the taxonomic distance between
	 *         <code>subproperty</code> and <code>superproperty</code>. The
	 *         integer is the number of is-a relations between them.
	 */
	@SuppressWarnings("unchecked")
	private static int pathDistance(OWLObjectProperty subproperty, OWLObjectProperty superproperty) {
		int distance = 0;
		if (subproperty.equals(superproperty))
			return distance;
		OWLObjectProperty parent = subproperty;
		while (parent.getSuperpropertyCount() > 0) {
			// TODO Danger! This assumes there is exactly(?) one superproperty!!
			Iterator<RDFProperty> parents = parent.getSuperproperties(false).iterator();
			while (parents.hasNext()) {
				RDFProperty current = parents.next();
				if (current instanceof OWLObjectProperty) {
					parent = (OWLObjectProperty)current;
					break;
				}
			}
			distance++;
			if (parent.equals(superproperty))
				return distance;
		}
		return -1;
	}

	/**
	 * Calculates the taxonomic distance between two OWL properties. The
	 * properties need not be on the same taxonomic path.
	 * <br>
	 * <br>
	 * Basically, the taxonomic distance between the two properties is the
	 * sum of the distances between either of them to their least common
	 * subsumer.
	 * 
	 * @param property1 The first OWL property.
	 * @param property2 The second OWL property.
	 * @return An integer that denotes the taxonomic distance between
	 *         <code>property1</code> and <code>property2</code>.
	 */
	private static int taxonomicDistance(OWLObjectProperty property1, OWLObjectProperty property2) {
		OWLObjectProperty lcs = leastCommonSubsumer(property1, property2);
		if (lcs == null)
			return -1;
		int distance = 0;
		// TODO There is one example where I find an LCS that actually is not an LCS!?!?
		int pathDist1 = pathDistance(property1, lcs);
		if (pathDist1 != -1)
			distance += pathDist1;
		else return -1;
		int pathDist2 = pathDistance(property2, lcs);
		if (pathDist2 != -1)
			distance += pathDist2;
		else return -1;
		// TODO "Normally": distance = pathDist1 + pathDist2;
		return distance;
	}

	/**
	 * Calculates the taxonomic similarity between two OWL properties.
	 * <br>
	 * <br>
	 * The taxonomic similarity is based on the taxonomic distance, but
	 * the return value is a normalized value between 0 and 1. 
	 * 
	 * @param property1 The first OWL property.
	 * @param property2 The second OWL property.
	 * @return A value between 0 and 1 that denotes the similarity of
	 *         <code>property1</code> and <code>property2</code>. The larger
	 *         the value, the more similar and the smaller the value,
	 *         the less similar the two properties are. A value of 0 denotes
	 *         that both properties have nothing in common and a value of 1
	 *         denotes identical properties.
	 */
	private static double taxonomicSimilarity(OWLObjectProperty property1, OWLObjectProperty property2) {
		int taxDist = taxonomicDistance(property1, property2);
		if (taxDist == -1)
			return 0;
		return 1 / (double)Math.log(taxDist + Math.E);
	}
	
	/**
	 * Gets the filler of a given property within a given OWL class that
	 * is the most similar to a given filler.  
	 * 
	 * @param class1 The class for which the most similar filler for a
	 *               property of type <code>property</code> is sought.
	 * @param property The property for which the filler that is the most
	 *                 similar to <code>filler</code> within
	 *                 <code>class1</code> is sought.
	 * @param filler The filler for which the most similar one within
	 *               <code>class1</code> is sought.
	 * @param usedFillers A <code>Vector</code> of OWL classes that have 
	 *                    already been compared - no class shall be the
	 *                    most similar more than once!
	 * @return The property of <code>class1</code> that is the most
	 *         similar to <code>property</code>.
	 */
	@SuppressWarnings({ "unused" })
	private static OWLNamedClass getMostSimilarFiller(OWLNamedClass class1, OWLObjectProperty property, RDFResource filler, Vector<OWLNamedClass> usedFillers) {
		OWLNamedClass mostSimilarFiller = null;
		double mostSimilarTaxSim = 0;
		Object currentFiller;
		Iterator<?> fillers = class1.getPropertyValues(property, false).iterator();
		while (fillers.hasNext()) {
			currentFiller = fillers.next();	
			if (currentFiller instanceof OWLNamedClass &&
					!usedFillers.contains((OWLNamedClass)currentFiller)) {
				double taxSim = taxonomicSimilarity((OWLNamedClass)currentFiller, filler);
				if (taxSim > mostSimilarTaxSim) {
					mostSimilarFiller = (OWLNamedClass)currentFiller;
					mostSimilarTaxSim = taxSim;
				}
			}
		}
		return mostSimilarFiller;
	}
	
	/**
	 * Gets the OWL property of an OWL class that is the most similar
	 * to a given OWL property. 
	 * 
	 * @param class1 The class for which the most similar property is
	 *               sought.
	 * @param property The property for which the most similar one within
	 *                 <code>class1</code> is sought.
	 * @return The property of <code>class1</code> that is the most
	 *         similar to <code>property</code>.
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	private static OWLObjectProperty getMostSimilarRole(OWLNamedClass class1, OWLObjectProperty property) {
		OWLObjectProperty mostSimilarProperty = null;
		double mostSimilarTaxSim = 0;
		Iterator<RDFProperty> properties = class1.getRDFProperties().iterator();
		while (properties.hasNext()) {
			RDFProperty current = properties.next();
			// No "uml.", "java5.", "ea.", etc. roles
//			if (!isInterestingProperty(current))
			if (!isInterestingProperty(current))			
				continue;
			if (current instanceof OWLObjectProperty) {
				double taxSim = taxonomicSimilarity((OWLObjectProperty)current, property);
				if (taxSim > mostSimilarTaxSim) {
					mostSimilarProperty = (OWLObjectProperty)current;
					mostSimilarTaxSim = taxSim;
				}
			}
		}
		return mostSimilarProperty;
	}

	/**
	 * This method checks for a given OWL property if it is of interest
	 * for the DL-based similarity measure.
	 * <br>
	 * <br>
	 * Basically, all properties like "hasParts" are of interest and all
	 * inverse properties like "hasParts-of" are not. But due to the metamodel
	 * not all of the inverse properties can be ignored. The check that is
	 * implemented in this methods provides some sort of "white list" of
	 * properties that are of interest. 
	 * 
	 * @param property The OWL property which shall be checked.
	 * @return <code>True</code> if <code>property</code> is of interest,
	 *         <code>false</code> otherwise.
	 * @deprecated
	 */
	protected static boolean isInterestingProperty_old(RDFProperty property) {
				
		if (property instanceof OWLObjectProperty == false){
			return false;
		}
		
		String name = property.getName();
		// rule out all unwanted roles
		if (!name.startsWith("rsl.") &&
				!name.startsWith("dictionaryelements.") &&
				!name.startsWith("relationships.") &&
				!name.startsWith("sclkernel."))
			return false;
		else { 
			if (name.endsWith("-of")) {
				// Whitelist: interesting inverse-roles (because the non-inverse roles - see below - are uninteresting)
				// Notions			
				if (name.equals("rsl.rsldomainelements.phrases.DeterminerIsPartOfSource-of") ||
						name.equals("rsl.rsldomainelements.phrases.ModifierIsPartOfSource-of") ||
						name.equals("rsl.rsldomainelements.phrases.NounIsPartOfSource-of") ||
						name.equals("rsl.rsldomainelements.phrases.PrepositionIsPartOfSource-of") ||
						name.equals("rsl.rsldomainelements.phrases.VerbIsPartOfSource-of") ||
					// Sentences / Hyperlinks
						name.equals("rsl.rslkernel.elements.HyperlinkLinksToSource-of") ||
					// SVO Sentences
						name.equals("rsl.rslrequirementrepresentationsentences.svosentences.PredicateIsPartOfSource-of") ||
						name.equals("rsl.rslrequirementrepresentationsentences.svosentences.SubjectIsPartOfSource-of") ||
					// Synonyms
						name.equals("dictionaryelements.ContainsSynonym-of"))
					return true;				
				else return false;
			}
			else {
				// Blacklist: uninteresting non-inverse-roles (because the inverse roles - see above - are interesing)
				if (name.equals("rsl.rsldomainelements.phrases.DeterminerIsPartOfSource") ||
						name.equals("rsl.rsldomainelements.phrases.ModifierIsPartOfSource") ||
						name.equals("rsl.rsldomainelements.phrases.NounIsPartOfSource") ||
						name.equals("rsl.rsldomainelements.phrases.PrepositionIsPartOfSource") ||
						name.equals("rsl.rsldomainelements.phrases.VerbIsPartOfSource") ||
					// Sentences / Hyperlinks
						name.equals("rsl.rslkernel.elements.HyperlinkLinksToSource") ||
					// SVO Sentences
						name.equals("rsl.rslrequirementrepresentationsentences.svosentences.PredicateIsPartOfSource") ||
						name.equals("rsl.rslrequirementrepresentationsentences.svosentences.SubjectIsPartOfSource") ||
					// Synonyms
						name.equals("dictionaryelements.ContainsSynonym") ||
						name.equals("dictionaryelements.HasSynonym"))
					return false;
			}
			return true;
		}
	}
	
	
	protected static boolean isInterestingProperty(RDFProperty property){
		if (property instanceof OWLObjectProperty == false){
			return false;
		}
		String propertyName = property.getName();
		for(String interestingProperty : Properties.DEFINING_RSL_PROPERTIES){
			if(propertyName.equals(interestingProperty)){
				return true;
			}
		}
		return false;	
	}

	// --- Classes and Instances ---	
	
	/**
	 * Compares two software cases (or any other OWL classes). Basically,
	 * for the two given OWL classes the taxonomic similarity and the role-
	 * based similarity are computed and both values are combined.
	 * 
	 * @see OWLReasoner#taxonomicDistance(OWLNamedClass, OWLNamedClass)
	 * @see OWLReasoner#roleBasedSimilarity(OWLNamedClass, OWLNamedClass)
	 * 
	 * @param sc The first OWL class.
	 * @param query The second OWL class.
	 * @return A value between 0 and 1 that denotes the similarity of
	 *         <code>class1</code> and <code>class2</code>. The larger
	 *         the value, the more similar and the smaller the value,
	 *         the less similar the two classes are. A value of 0 denotes
	 *         that both classes have nothing in common and a value of 1
	 *         denotes identical classes.
	 */
	public double compare(OWLNamedClass sc, RDFResource query){
		Map<OWLNamedClass, Double> simMap = similarityMap.get(query);
		if (simMap == null)
			simMap = new HashMap<OWLNamedClass, Double>();
		Double similarity = simMap.get(sc);
		if (similarity != null){
			Activator.logInfo(" Found similarity value for query: returning "+similarity);
			return similarity;
		}
		// reset variables for performance checking
		preventedComparesCount = 0;		
		conceptSimCalls = 0;
		roleSimCalls = 0;
		
		double roleSim = 0.0;
		int usedTime = 0;
		
		// query is OWLNamedClass
		if(query instanceof OWLNamedClass){
			// Checking performance
			double startTime = java.lang.System.currentTimeMillis();
//			Activator.logInfo(" ******************************* ");
//			Activator.logInfo(" Start of role based similarity from compare!");
//			Activator.logInfo(" ******************************* ");
			roleSim = roleBasedSimilarity(sc, (OWLNamedClass)query);
//			Activator.logInfo(" ******************************* ");
//			Activator.logInfo(" End of role based similarity from compare!");
//			Activator.logInfo(" ******************************* ");			
			double endTime = java.lang.System.currentTimeMillis();		
			usedTime = (int)(endTime-startTime)/1000;	
		}
		
		// query is OWLIndividual
		else if(query instanceof OWLIndividual){
			// Checking performance
			double startTime = java.lang.System.currentTimeMillis();
			roleSim = roleBasedSimilarity(sc, (OWLIndividual)query);
			double endTime = java.lang.System.currentTimeMillis();		
			usedTime = (int)(endTime-startTime)/1000;				
		}
		
		else {
			Activator.logWarning(" ERROR: Query is neither OWLNamedClass nor OWLIndividual!");
			return 0.0;
		}
		
		//double roleSim = roleBasedSimilarity(class1, individual);
		// Caution: roleSim includes taxSim as well!
		similarity = roleSim;

		String softwareCase1 = "_6939b0cb-80703d0f-866cb97d-962a80cb"; // OnlineShop
		String softwareCase2 = "_2c95a89c-dd5cb3d6-2c77124f-fbd261f4"; // OnlineBanking
		String softwareCase3 = "_726b8f8f-4a261a5c-27acae3e-dfdbb7b5"; // OnlinePizzaService
		String softwareCase4 = "_68d91225-47993145-151078a3-68fa67db"; // ClientManagementSystem
		String softwareCase5 = "_9574c952-6ca7554d-30f0f0e5-bebf1874"; // Route-Planer
		String softwareCase6 = "dbc31495-c6ef78e0-2d8868b2-a8bb4263"; // CASE-Tool
		String softwareCase7 = "_395fbfaf-5e7a4f3e-808184b2-46f11df6"; // ChangeData
		
		String softwareCaseUID = getSoftwareCaseUid(sc);		
		String class1Name = sc.getName().substring(sc.getName().lastIndexOf(".") + 1 , sc.getName().length());
		if(softwareCaseUID.equals(softwareCase1)) class1Name = "SC1: OnlineShop";
		else if(softwareCaseUID.equals(softwareCase2)) class1Name = "SC2: OnlineBanking";
		else if(softwareCaseUID.equals(softwareCase3)) class1Name = "SC3: OnlinePizzaService";
		else if(softwareCaseUID.equals(softwareCase4)) class1Name = "SC4: ClientManagementSystem";
		else if(softwareCaseUID.equals(softwareCase5)) class1Name = "SC5: Routen-Planer";
		else if(softwareCaseUID.equals(softwareCase6)) class1Name = "SC6: Case-Tool";
		else if(softwareCaseUID.equals(softwareCase7)) class1Name = "SC7: ChangeData";
				
		String queryUID = "";
		if(query instanceof OWLNamedClass){
			queryUID = getSoftwareCaseUid((OWLNamedClass)query);
		}			
		String class2Name = query.getName().substring(query.getName().lastIndexOf(".") + 1 , query.getName().length());
		if(queryUID.equals(softwareCase1)) class2Name = "SC1: OnlineShop";
		else if(queryUID.equals(softwareCase2)) class2Name = "SC2: OnlineBanking";
		else if(queryUID.equals(softwareCase3)) class2Name = "SC3: OnlinePizzaService";
		else if(queryUID.equals(softwareCase4)) class2Name = "SC4: ClientManagementSystem";
		else if(queryUID.equals(softwareCase5)) class2Name = "SC5: Routen-Planer";
		else if(queryUID.equals(softwareCase6)) class2Name = "SC6: Case-Tool";
		else if(queryUID.equals(softwareCase7)) class2Name = "SC7: ChangeData";
		
		System.out.println("-------------------------------------------------------------------------");
		System.out.println("-------------------------------------------------------------------------");
		System.out.println("ROLE-BASED SIMILARITY: " + class1Name + " (UID: " + getSoftwareCaseUid(sc) + ")");
		System.out.println("          & " + class2Name);
		System.out.println("                 = " + roleSim);
		//System.out.println("                 = " + similarity);
		//System.out.println(" - TAXONOMIC SIM: " + distSim);
		//System.out.println(" - ROLE-BASED SIM 1: " + roleSim);
		//System.out.println(" - ROLE-BASED SIM 2: " + roleSim2);
		System.out.println(" - Calculation of ROLE-BASED SIM took "+usedTime+" seconds.");
		System.out.println(" - getConceptSimilarity was called "+conceptSimCalls+" times.");
		System.out.println(" - getRoleSimilarity was called "+roleSimCalls+" times.");
		System.out.println(" - skipped "+preventedComparesCount+" comparisons between different concepts.");
		System.out.println("-------------------------------------------------------------------------");
		System.out.println("-------------------------------------------------------------------------");
		
		// reset indentation and recursion depth
		indent = "";
		recursionDepth = 0;
		
		// remember similarity (in our case roleSim2)
		_similarity = roleSim;
		simMap.put(sc, similarity);
		return similarity; 
	}
	
	
	/**
	 * Calculates the taxonomic similarity between an OWL classes and
	 * an OWL resource (i.e. one of OWL class and OWL individual).
	 *
	 * @see OWLReasoner#taxonomicSimilarity(OWLNamedClass, OWLNamedClass)
	 * @see OWLReasoner#taxonomicSimilarity(OWLNamedClass, OWLIndividual)
	 */
	private static double taxonomicSimilarity(OWLNamedClass class1, RDFResource resource) {
		if (resource instanceof OWLNamedClass)
			return taxonomicSimilarity(class1, (OWLNamedClass)resource);
		else if (resource instanceof OWLIndividual) 
			return taxonomicSimilarity(class1, (OWLIndividual)resource);
		else return (double)0;
	}

	/**
	 * Calculates the taxonomic similarity between an OWL classes and
	 * an OWL resource (i.e. one of OWL class and OWL individual).
	 *
	 * @see OWLReasoner#taxonomicSimilarity(OWLNamedClass, OWLNamedClass)
	 * @see OWLReasoner#taxonomicSimilarity(OWLNamedClass, OWLIndividual)
	 */
	private static double taxonomicSimilarity(RDFResource class1, RDFResource resource) {
		if (class1 instanceof OWLNamedClass && resource instanceof OWLNamedClass)
			return taxonomicSimilarity((OWLNamedClass)class1, (OWLNamedClass)resource);
		else if (class1 instanceof OWLNamedClass && resource instanceof OWLIndividual) 
			return taxonomicSimilarity((OWLNamedClass)class1, getOWLClassOf((OWLIndividual)resource));
		else if (class1 instanceof OWLIndividual && resource instanceof OWLNamedClass) 
			return taxonomicSimilarity(getOWLClassOf((OWLIndividual)class1), (OWLNamedClass)resource);
		else if (class1 instanceof OWLIndividual && resource instanceof OWLIndividual) 
			return taxonomicSimilarity(getOWLClassOf((OWLIndividual)class1), getOWLClassOf((OWLIndividual)resource));
		
		else if (class1 instanceof OWLObjectProperty && resource instanceof OWLObjectProperty){
			return taxonomicSimilarity((OWLObjectProperty)class1, (OWLObjectProperty)resource);
		}

		else return (double)0;
	}
	
	/**
	 * Calculates the taxonomic similarity between an OWL class and an
	 * OWL individual.
	 * <br>
	 * <br>
	 * The taxonomic similarity is based on the taxonomic distance, but
	 * the return value is a normalized value between 0 and 1. 
	 * 
	 * @param class1 The OWL class.
	 * @param individual The OWL individual.
	 * @return A value between 0 and 1 that denotes the similarity of
	 *         <code>class1</code> and <code>individual</code>. The larger
	 *         the value, the more similar and the smaller the value,
	 *         the less similar the class and individual are. A value of
	 *         0 denotes that both have nothing in common and a value of 1
	 *         denotes that <code>individual</code> is instance-of
	 *         <code>class1</code>.
	 */
	private static double taxonomicSimilarity(OWLNamedClass class1, OWLIndividual individual) {
		OWLNamedClass class2 = getOWLClassOf(individual);
		return taxonomicSimilarity(class1, class2);
	}

	/**
	 * Gets the OWL class of which a given OWL individual is an instance.
	 * <br>
	 * <br>
	 * Find "the one" OWL class of which a given individual is an instance
	 * is not trivial due to multiple classing! The approach that is followed
	 * in this method traverses over all classes of which the given individual
	 * is an instance and returns the one class that is the most specific one.
	 * This means that more specific OWL classes are preferred over more
	 * general OWL classes.
	 * 
	 * @param individual The individual of which the corresponding OWL
	 *                   class is sought. 
	 * @return The OWL class of which <code>individual</code> is an
	 *         instance.
	 */
	public static OWLNamedClass getOWLClassOf(OWLIndividual individual) {
		if(individual instanceof OWLNamedClass){
			return (OWLNamedClass)individual;
		}
		// Get inferred types...
		OWLNamedClass class1 = null;
		Object current = null;
		OWLNamedClass currentClass = null;
		Iterator<?> infTypes = individual.getInferredTypes().iterator();
		while (infTypes.hasNext()) {
			current = infTypes.next();
			if(current instanceof OWLNamedClass){
				currentClass = (OWLNamedClass)current;
				if (class1 == null)
					class1 = currentClass;
				else if (currentClass.isSubclassOf(class1))
					class1 = currentClass;				
			}
		}
		// ... nothing inferred? > then use the type with which it was instantiated!
		if (class1 == null) {
			Iterator<?> instanceOfs = individual.getProtegeTypes().iterator();
			while (instanceOfs.hasNext()) {
				current = instanceOfs.next();
				if(current instanceof OWLNamedClass){
					currentClass = (OWLNamedClass)current;
					if (class1 == null)
						class1 = currentClass;
					else if (currentClass.isSubclassOf(class1))
						class1 = currentClass;
				}
			}
		}
		return class1;
	}
	
	/**
	 * Checks if a given OWL individual is instance of an "upper model"
	 * concept.
	 * 
	 * @param instance The OWL individual to be checked.
	 * @return <code>True</code> if <code>individual</code> is instance
	 *         of an "upper model" concept, <code>false</code> otherwise.
	 */
	protected static boolean isInstanceOfUpperModelConcept(OWLIndividual instance) {
		OWLNamedClass class1 = (OWLNamedClass)instance.getProtegeType();
		return SupportFunctions.isUpperModelConcept(class1);
	}
	
	
	
	/**
	 * Retrieves OWLNamedClass most similar to given RDFResource queryResource
	 * by calling the methods <code>retrieve(OWLNamedClass)</code> and <code>retrieve(OWLIndividual)</code>.
	 * 
	 * @param queryResource The RDF Resource for which the most similar OWLNamedClass is retrieved. 
	 * 
	 * @return OWLNamedClass that is most similar to given RDFResource queryResource.
	 */
	public OWLNamedClass retrieve(RDFResource queryResource){
		if(queryResource instanceof OWLNamedClass){
			return retrieve((OWLNamedClass)queryResource);
		}
		else if (queryResource instanceof OWLIndividual){
			return retrieve((OWLIndividual)queryResource);
		}
		else {
			Activator.logError("  ERROR: Query resource is neither OWLNamedClass nor OWLIndividual!", null);
			return null;
		}
	}
	
		
	/**
	 * Retrieves the software case (or any other OWL class) that is the most
	 * similar to a given OWL individual.
	 * <br>
	 * <br>
	 * Basically, this method calls the <code>compare()</code> method for all
	 * known software cases (or any other OWl classes) and returns the one
	 * for which the largest similarity values was computed.
	 * 
	 * @see OWLReasoner#compare(OWLNamedClass, OWLIndividual)
	 * 
	 * @param individual The OWL individual for which the most similar OWL
	 *                   class is sought.
	 * @return The OWL class that is the most similar to
	 *         <code>individual</code>.
	 *         
	 *         
	 * FIXME: getOWLClassOf returns superclass, because class for individual 
	 * isn't created in initializer! 
	 * 
	 * This method is not used atm, because queries are not converted to 
	 * OWL individuals anymore but to OWL named classes (like the software cases
	 * to which they are compared) 
	 */
	
	public OWLNamedClass retrieve(OWLIndividual individual) {		
		OWLNamedClass queryClass = getOWLClassOf(individual);
		Vector<RDFSClass> querySuperClasses = new Vector<RDFSClass>();
		querySuperClasses.add(queryClass);		
		if (!SupportFunctions.isUpperModelConcept(queryClass)) {
			// The "instance-of" is the best match! 
			return queryClass;
		} else {
			return getMostSimilarClass(querySuperClasses, queryClass);
		}
	}

	
	/**
	 * Retrieves the software case (or any other OWL class) that is the most
	 * similar to a given OWL individual.
	 * <br>
	 * <br>
	 * Basically, this method calls the <code>compare()</code> method for all
	 * known software cases (or any other OWl classes) and returns the one
	 * for which the largest similarity values was computed.
	 * 
	 * @see OWLReasoner#compare(OWLNamedClass, OWLIndividual)
	 * 
	 * @param individual The OWL individual for which the most similar OWL
	 *                   class is sought.
	 * @return The OWL class that is the most similar to
	 *         <code>individual</code>.
	 */	
	public OWLNamedClass retrieve(OWLNamedClass queryClass) {		
		Collection<?> querySuperClasses = queryClass.getSuperclasses(false);
		Collection<RDFSClass> rdfsSuperClasses = new Vector<RDFSClass>();
		Collection<RDFSClass> subclassesOfSuperClasses = new Vector<RDFSClass>();
		Collection<RDFSClass> unrelevantQuerySuperClasses = new Vector<RDFSClass>();
		OWLNamedClass currentSuperClass = null;
		
		for (Object superClass : querySuperClasses){
			if(superClass instanceof OWLNamedClass){
				if(SupportFunctions.isUpperModelConcept((OWLNamedClass)superClass)){
					// Activator.logInfo("   found super class of query that is upper model: "+superClass.getName());
					// Activator.logInfo("    --> compare query with all subclasses of superclass:");
					currentSuperClass = (OWLNamedClass)superClass;
					Collection<?> subClasses = currentSuperClass.getSubclasses(false);
					for(Object cl : subClasses){
						if(cl instanceof OWLNamedClass){
							subclassesOfSuperClasses.add((OWLNamedClass)cl);
						}
						else{
							// Activator.logInfo("  "+cl.getName()+" not added to subclassesofsuperclasses!");
						}
					}
											
				}
			}
			else{
				if(superClass instanceof RDFSClass)
					unrelevantQuerySuperClasses.add((RDFSClass)superClass);
			}
		}
		// get all super classes that are rdfs classes
		for(Object c : querySuperClasses){
			if(c instanceof RDFSClass)
				rdfsSuperClasses.add((RDFSClass)c);
		}
		// remove unrelevant super classes from list of query super classes
		rdfsSuperClasses.removeAll(unrelevantQuerySuperClasses);
		
		// query class is child class of at least on upper model class
		if(subclassesOfSuperClasses.size() > 0){
//			Activator.logInfo("   Querys superclass is upper model concept: ");
//			Activator.logInfo("    --> compare query with all subclasses of superclass!");
//			print subclassesofsuperclasses BEFORE removing query class from list
//			Activator.logInfo("       Sub classes from super classes from query:");
//			for (RDFSClass cl : subclassesOfSuperClasses){
//				Activator.logInfo("          - "+cl.getName());
//			}			
			// remove query class from list of subclasses from superclasses
//			while (subclassesOfSuperClasses.remove(queryClass)){
//				Activator.logInfo("        Removed query requirement specification ("+queryClass.getName()+") from list of classes to which query is compared!");									
//			}			
			// print subclassesofsuperclasses AFTER removing query class from list
//			Activator.logInfo("       Remaining subclasses after query is removed from list:");			
//			for (RDFSClass cl : subclassesOfSuperClasses){
//				Activator.logInfo("          - "+cl.getName());				
//			}				
			return getMostSimilarClass(subclassesOfSuperClasses, queryClass);
		}
		// query class is NOT a child class of upper model classes
		else{
			if(rdfsSuperClasses.size() > 0){
				// Activator.logInfo("   Query's superclasses are NOT an upper model concept: ");				
				// Activator.logInfo("    --> compare query's direct superclasses only!");							
				return getMostSimilarClass(rdfsSuperClasses, queryClass);
			}
			else{
				// Activator.logWarning("   ERROR: none of query's super classes is OWLNamedClass!");				
				// Activator.logWarning("    --> can't compare query to anything!");				
				return null;
			}
		}
	}	
	
	
	/**
	 * Retrieves the owlclass from given classesToBeComparedToQuery that has the highest similarity
	 * when compared to the given queryClass.
	 * 
	 * @param classesToBeComparedToQery
	 * @param queryClass
	 * @return class that is most similar to given query class
	 */
	private OWLNamedClass getMostSimilarClass(Collection<RDFSClass> classesToBeComparedToQuery, OWLNamedClass queryClass){
		
		OWLNamedClass mostSimilarClass = null;
		double similarity = 0;
		
		// Activator.logInfo(" QueryClass-Name: "+queryClass.getName());
		
		Iterator<RDFSClass> classIterator = classesToBeComparedToQuery.iterator();
		while (classIterator.hasNext()) {			
			RDFSClass current = classIterator.next();
			// Activator.logInfo("   is compared to class: "+current.getName());
		
			if (current instanceof OWLNamedClass) {
				OWLNamedClass child = (OWLNamedClass)current;
				double childSimilarity = compare(child, queryClass);
				if (childSimilarity > similarity) {
					similarity = childSimilarity;
					mostSimilarClass = child;
				}
			}
		}
		return mostSimilarClass;
		
	}
	
	// ####################################################################################
	// Extension by A. Solth

	
	/**
	 * Updatet version of roleBasedSimilarity.
	 * Calculates the role-based similarity between two given 
	 * RDFResources, normaly OWLNamedClasses
	 * <br>
	 * <br>
	 * The role-based similarity is based on a recursive approach in
	 * which all fillers are compared based on their taxonomic and
	 * role-based similarity.
	 * 
	 * @param resource1 First resource to be compared
	 * @param resource2 Second resource to be compared
	 * @return A value between 0 and 1 that denotes the similarity of
	 *         <code>class1</code> and <code>individual</code>. The larger
	 *         the value, the more similar and the smaller the value,
	 *         the less similar the class and individual are. A value of
	 *         0 denotes that both have nothing in common and a value of 1
	 *         denotes that <code>individual</code> is instance-of
	 *         <code>class1</code>.
	 */	
	protected static double roleBasedSimilarity(RDFResource resource1, RDFResource resource2){

		// remember all compared classes to avoid infinite recursion
		Vector<RDFResource> comparedResources = new Vector<RDFResource>();
		if (resource1 instanceof OWLNamedClass){
			if (resource2 instanceof OWLNamedClass){
				return roleBasedSimilarity((OWLNamedClass)resource1, (OWLNamedClass)resource2, comparedResources);
			}
			else if (resource2 instanceof OWLIndividual){
				return roleBasedSimilarity((OWLNamedClass)resource1, (OWLIndividual)resource2, comparedResources);
			}
		}
		return 0;
	}
	
	protected static double roleBasedSimilarity(OWLNamedClass class1, OWLIndividual individual, Vector<RDFResource> comparedResources){
		OWLNamedClass query = getOWLClassOf(individual);
		double sim = getConceptSimilarity(class1, query);
		if(		(getShortName(class1)).startsWith("SVO") ||
				(getShortName(class1)).startsWith("Requirements") ||
				(getShortName(class1)).startsWith("RSLUseCase")){
//			System.out.println(" Sim between "+getShortName(class1)+" and "+getShortName(individual)+" is: "+sim);
		}
		return sim; 		
	}

	protected static double roleBasedSimilarity(OWLNamedClass class1, OWLNamedClass class2, Vector<RDFResource> comparedResources){
		double sim = getConceptSimilarity(class1, class2);
		if(		(getShortName(class1)).startsWith("SVO") ||
				(getShortName(class1)).startsWith("Requirements") ||
				(getShortName(class1)).startsWith("RSLUseCase")){		
//			System.out.println(" Sim between "+getShortName(class1)+" and "+getShortName(class2)+" is: "+sim);
		}
		return sim;
	}
	
	
	/**
	 * This function checks if software case concept and query concept should be compared. 
	 * Therefor the function retrieves the superclasses of both concepts and checks, 
	 * if software case concept and query concept have a common super class which is element of the
	 * whitelist containing all OWLNamedClasses that should be compared. If so, returns true, else return false. 
	 * 
	 * @param softwareCaseConcept
	 * @param queryConcept
	 * @return
	 */	
	@SuppressWarnings("unchecked")
	protected static boolean compareConcepts(OWLNamedClass softwareCaseConcept, RDFResource queryConcept){		
		OWLNamedClass queryClass = null;		
		if(queryConcept instanceof OWLNamedClass){
			queryClass = (OWLNamedClass)queryConcept; 
		}
		else if(queryConcept instanceof OWLIndividual){
			queryClass = getOWLClassOf((OWLIndividual)queryConcept);
		}
		else return false;		
		Collection<RDFSClass> scSuperClasses = softwareCaseConcept.getNamedSuperclasses(true);
		Collection<RDFSClass> querySuperClasses = queryClass.getNamedSuperclasses(true);
		// compute intersection of both super class collections
		scSuperClasses.retainAll(querySuperClasses);
//		Activator.logInfo(" Checking if "+softwareCaseConcept.getName()+" and "+queryClass.getName()+" should be compared by checking the intersection of their superclasses!");
		for (RDFSClass c : scSuperClasses){			
			if(c==null) continue;			
			else {				
//				Activator.logInfo("  checking, if "+getShortName(c)+" is on list of concepts to be compared...");
				if(conceptsToBeCompared.contains(c.getName())){
//					Activator.logInfo("     ...true! Comparing concepts!");
					return true;
				}		
			}			
		}
		return false;
	}
	
	@SuppressWarnings("unused")
	private static Vector<OWLObjectProperty> getEqualProperties(Vector<OWLSomeValuesFrom> intSCProps, Vector<OWLSomeValuesFrom> intQProps){
		Vector<OWLObjectProperty> equalProps = new Vector<OWLObjectProperty>();		
		for(OWLSomeValuesFrom sc_rest : intSCProps){
			for(OWLSomeValuesFrom q_rest : intQProps){
				if(sc_rest.getOnProperty().equals(q_rest.getOnProperty()) && !equalProps.contains(sc_rest.getOnProperty())){
					if(sc_rest.getOnProperty() instanceof OWLObjectProperty){
						equalProps.add((OWLObjectProperty)sc_rest.getOnProperty());	
					}					
				}
			}
		}		
		return equalProps;
	}
	
	
	/** 
	 * Computes the similarity between a software case concept and a query instance
	 * based on their taxonomic similarity and the similarity between their roles.
	 * 
	 * @param softwareCaseConcept
	 * @param queryConcept
	 * @return Similarity between the SC concept and query instance.
	 *  
	 * 
	 */
	protected static double getConceptSimilarity(OWLNamedClass softwareCaseConcept, OWLNamedClass queryConcept) {
//		Activator.logInfo("-------------------------------");
//		Activator.logInfo("Computing concept similarity of: " + getShortName(softwareCaseConcept) + "(sc)  " + getShortName(queryConcept)+"(query)");
		// count up number of getConceptSimilarity calls
		conceptSimCalls++;		
		recursionDepth++;
		//printStartOfSimilarityComputation(softwareCaseConcept, queryInstance, true);
		
		//Activator.logInfo(" Requirement class: "+requirementClass.getName());
//		Activator.logInfo(" SC Concept "+softwareCaseConcept.getName()+" has following superclasses:");
//		for(Object superClass : softwareCaseConcept.getSuperclasses(true)){
//			if(superClass instanceof OWLNamedClass){
//				Activator.logInfo("   - "+((OWLNamedClass)superClass).getName());	
//			}
//		}				
				
//		Activator.logInfo(" Computing taxonomic similarity between concepts...");
		double taxSim = taxonomicSimilarity(softwareCaseConcept, queryConcept);

//		Activator.logInfo("   Retrieving interesting properties of sc concept:");
		Vector<OWLSomeValuesFrom>interestingSCProperties = retrieveInterestingProperties(softwareCaseConcept);
//		Activator.logInfo("   Retrieving interesting properties of query concept:");
		Vector<OWLSomeValuesFrom>interestingQueryProperties = retrieveInterestingProperties(queryConcept);		
		
		int nrOfSCProps = interestingSCProperties.size();
		int nrOfQueryProps = interestingQueryProperties.size();
		
//		Activator.logInfo("  Found "+nrOfSCProps+" interesting properties in SoftwareCase concept "+ getShortName(softwareCaseConcept) +"!");
//		Activator.logInfo("  Found "+nrOfQueryProps+" interesting properties in Query concept "+ getShortName(queryConcept) + "!");		
		
		// Distinguish between different cases of how to calculate similarity between scConcept and queryInstance:
		// case 1: query instance has no interesting properties or query and sc concepts are word net terms
		// -> return only taxSim of software case and query
		if((isWordNetTerm(softwareCaseConcept) && (isWordNetTerm(queryConcept))) || nrOfQueryProps<1 ){
			// Activator.logInfo("! Nr of query properties < 1!");
			// Activator.logInfo(" -> sim = taxSim = "+taxSim);
			// Activator.logInfo("-------------------------------");
			addToRepresentableElementMaps(softwareCaseConcept, queryConcept, taxSim);
			
			if(isWordNetTerm(softwareCaseConcept) && (isWordNetTerm(queryConcept))){
//				Activator.logInfo(" (case1) sim of wordnet terms "+getShortName(softwareCaseConcept)+" and "+getShortName(queryConcept)+":"+taxSim);
			}
			else{
//				Activator.logInfo(" (case1) sim of sc concept "+getShortName(softwareCaseConcept)+" and query concept "+getShortName(queryConcept)+"(doesn't have interesting properties!):"+taxSim);
			}			
			return taxSim;
		}
		
 		// case 2: given concepts are of different types -> don't compare concepts and return 0 similarity
		else if(!compareConcepts(softwareCaseConcept, queryConcept)){
			addToRepresentableElementMaps(softwareCaseConcept, queryConcept, 0);
			preventedComparesCount++;
			// Activator.logInfo("! software case concept and query concept shouldn't be compared!");
			// Activator.logInfo(" -> sim = 0");
			// Activator.logInfo("-------------------------------");
			
//			Activator.logInfo(" (case2) sim of sc concept "+getShortName(softwareCaseConcept)+" and query concept "+getShortName(queryConcept)+": 0");
			
			return 0;
		}		
		// case 3: query concept has intereting properties but software case concept hasn't 
		// -> return (roleSimWeight*roleSim) + (taxSimWeight*taxSim) = 0 + taxSimWeight*taxSim = taxSimWeight*taxSim
		else if(nrOfQueryProps>=1 && nrOfSCProps<1){
			// Activator.logInfo("! Nr of query properties >=1 and nr of softwarecase properties <1!");
			// Activator.logInfo(" ->sim = taxSimWeight*taxSim = "+(taxSimWeight*taxSim));
			// Activator.logInfo("-------------------------------");
			addToRepresentableElementMaps(softwareCaseConcept, queryConcept, taxSimWeight*taxSim);
			
//			Activator.logInfo(" (case3) sim of sc concept "+getShortName(softwareCaseConcept)+" and query concept "+getShortName(queryConcept)+":"+(taxSimWeight*taxSim));
			
			return taxSimWeight*taxSim;
		}
		// case 4: query instance and software case concept have interesting roles 
		// -> return roleSim of software case and query 	
		// first compare identical roles of concept and instance 
//		double equalPropertySim = 0;
//		int equalPropertiesCountInt = 0;
		// properties that are equal; will be removed from interesting property vectors after 
		// comparision to see if any interesting properties remain that have to be compared 
//		Vector<OWLSomeValuesFrom> equalSoftwareCaseProperties = new Vector<OWLSomeValuesFrom>();
//		Vector<OWLSomeValuesFrom> equalQueryProperties = new Vector<OWLSomeValuesFrom>();	

//		OWLObjectProperty currentSCProperty = null;
//		OWLObjectProperty currentQueryProperty = null;
		
		nrOfSCProps = interestingSCProperties.size();
		nrOfQueryProps = interestingQueryProperties.size();

		double[][] roleSimilarities = new double[nrOfQueryProps][nrOfSCProps];
		
		double currentRoleSim = 0.0;
		
		for (int classRoleIndex = 0; classRoleIndex < nrOfSCProps; classRoleIndex++){
			for (int queryRoleIndex = 0; queryRoleIndex < nrOfQueryProps; queryRoleIndex++){
				OWLSomeValuesFrom classRole = interestingSCProperties.elementAt(classRoleIndex);				
				OWLSomeValuesFrom queryRole = interestingQueryProperties.elementAt(queryRoleIndex);
				if(queryRole.getOnProperty() instanceof OWLObjectProperty && classRole.getOnProperty() instanceof OWLObjectProperty){
					// Fill role similarity table
					currentRoleSim = getRoleSimilarity(classRole, queryRole);
					// Activator.logInfo(" Similarity between "+classRole.getName()+" and "+ queryRole.getName()+": "+currentRoleSim);
					roleSimilarities[queryRoleIndex][classRoleIndex] = currentRoleSim;					
				}
			}
		}
		// Retrieve property mapping with highest total similarity
//		Activator.logInfo(" Computing best mapping between sc concept "+getShortName(softwareCaseConcept)+" and query concept "+getShortName(queryConcept)+"...");		
		int[] bestMapping = getBestMapping(nrOfSCProps, nrOfQueryProps, roleSimilarities);
		double bestMappingSim = getMappingSim(bestMapping, roleSimilarities);
//		System.out.print("    found best mapping between concepts roles("+bestMapping.toString()+"):");
//	    for(int x=0;x<bestMapping.length;x++)
//	        System.out.print(bestMapping[x]);
//	    Activator.logInfo("");
		// TODO: move this part to new method!
//		if((getShortName(softwareCaseConcept)).startsWith("RSLUseCase")){
//			System.out.println(" Nr of query properties: "+nrOfQueryProps);
//			System.out.println(" Nr of software case properties: "+nrOfSCProps);
//			System.out.print(" Best Mapping: ");
//			for(int caseIndex : bestMapping) System.out.print(caseIndex);
//			System.out.print(" length: "+bestMapping.length);
//			System.out.println("");
//			for(int queryIndex=0; queryIndex < bestMapping.length; queryIndex++){
//				Activator.logInfo("    - mapped query role "+getShortName(interestingQueryProperties.elementAt(queryIndex).getOnProperty())+" with filler "+getShortName(interestingQueryProperties.elementAt(queryIndex).getFiller()));
//				Activator.logInfo("           onto sc role "+getShortName(interestingSCProperties.elementAt(bestMapping[queryIndex]).getOnProperty())+" with filler "+getShortName(interestingSCProperties.elementAt(bestMapping[queryIndex]).getFiller()));
//				System.out.println(" queryIndex = "+queryIndex);
//				if(bestMapping[queryIndex] >= 0){
//					System.out.println("    - mapped query role "+getShortName(interestingQueryProperties.elementAt(queryIndex).getOnProperty())+" with filler "+getShortName(interestingQueryProperties.elementAt(queryIndex).getFiller()));
//					System.out.println("           onto sc role "+getShortName(interestingSCProperties.elementAt(bestMapping[queryIndex]).getOnProperty())+" with filler "+getShortName(interestingSCProperties.elementAt(bestMapping[queryIndex]).getFiller()));							
//				}
//				else{
//					System.out.println("    - query role "+getShortName(interestingQueryProperties.elementAt(queryIndex).getOnProperty())+" with filler "+getShortName(interestingQueryProperties.elementAt(queryIndex).getFiller()));
//					System.out.println("           couldn't be mapped onto any software case role, because sc has less roles than query!");					
//				}
//			}
//		}			
//		Activator.logInfo("  -- > best mapping sim "+bestMappingSim);
		// return combined similarity between equal and different roles
		/*
		Activator.logInfo(" allQueryRolesCount = equalPropertiesCount + nrOfQueryProps ("+allQueryRolesCount+"="+equalPropertiesCount+"+"+nrOfQueryProps+")");
		Activator.logInfo(" equalPropertySim: "+equalPropertySim);
		Activator.logInfo(" nrOfQueryProps/allQueryRolesCount = "+ (nrOfQueryProps/allQueryRolesCount));
		Activator.logInfo(" equalPropertiesCount/allQueryRolesCount = " + (equalPropertiesCount/allQueryRolesCount));
		*/
		double roleSim = bestMappingSim;
		double sim = taxSimWeight*taxSim + roleSimWeight*roleSim;
		
		// Activator.logInfo(" Concept-Similarity between "+softwareCaseConcept.getName()+" and "+ queryConcept.getName()+": "+sim);
		// Activator.logInfo("    Taxonomic Sim: "+taxSimWeight*taxSim+"("+taxSimWeight+"*"+taxSim+")");
		// Activator.logInfo("    Role-Sim: "+roleSimWeight*roleSim+"("+roleSimWeight+"*"+roleSim+")");		
		
		// return similarity between software case concept and query instance
		//printResultOfSimilarityComputation(softwareCaseConcept, queryInstance, true, mappingSim);
		recursionDepth--;
//		Activator.logInfo("normal concept compare finished.");
		// Activator.logInfo(" ->sim = "+sim);
		// Activator.logInfo("-------------------------------");
		addToRepresentableElementMaps(softwareCaseConcept, queryConcept, sim);	

//		Activator.logInfo(" (case4) sim of sc concept "+getShortName(softwareCaseConcept)+" and query concept "+getShortName(queryConcept)+":"+sim);		
		
		return sim;
	}
	
	
	@SuppressWarnings("unused")
	private static void printStartOfSimilarityComputation(RDFResource scElement, RDFResource queryElement, boolean isConcept){
		
		indent ="";
		for (int i=0; i<recursionDepth; i++){
			indent += "   ";
		}		
		scOperand = getShortName(scElement);
		queryOperand = getShortName(queryElement);
		if(isConcept){
			firstLine = "getting concept similarity between concepts:";
		}
		else{
			firstLine = "getting role similarity between roles:";
		}
		Activator.logInfo(indent+separationLine);
		Activator.logInfo(indent+firstLine);
		Activator.logInfo(indent+" "+scOperand+"(sc) and");
		Activator.logInfo(indent+" "+queryOperand+"(query):");
		
	}
	
	@SuppressWarnings("unused")
	private static void printResultOfSimilarityComputation(RDFResource scElement, RDFResource queryElement, boolean isConcept, double mappingSim){
		indent ="";
		for (int i=0; i<recursionDepth; i++){
			indent += "   ";
		}		
		scOperand = getShortName(scElement);
		queryOperand = getShortName(queryElement);
		if(isConcept){
			firstLine = "filler similarity between roles:";
		}
		else{
			firstLine = "role similarity between different roles of concepts:";
		}		
		
		Activator.logInfo(indent+firstLine);
		Activator.logInfo(indent+" "+scOperand+"(sc) and");
		Activator.logInfo(indent+" "+queryOperand+"(query):");
		Activator.logInfo(indent+mappingSim);		
		Activator.logInfo(indent+separationLine);		
	}
		
	/**
	 * 
	 * Computes the similarity between between two given roles/properties 
	 * based on their taxonomic similarity and the similarity between their fillers.  
	 * 
	 * @param OWLNamedClass software case concept
	 * @param RDFResource query instance
	 * @param OWLObjectProperty software case property
	 * @param OWLObjectProperty query property
	 * 
	 * @return similarity between fillers of given sc role/property and query role/property
	 *  
	 * 
	 */
	protected static double getRoleSimilarity(OWLSomeValuesFrom scSomeValuesFrom, OWLSomeValuesFrom querySomeValuesFrom) {
//		Activator.logInfo("-------------------------------");
//		Activator.logInfo("Computing role similarity of: " + getShortName(scSomeValuesFrom.getOnProperty()) + "(sc)  " + getShortName(querySomeValuesFrom.getOnProperty())+"(query)");
		// count up number of getRoleSimilarity calls
		roleSimCalls++;
		recursionDepth++;				
		//printStartOfSimilarityComputation(scProperty, queryProperty, false);
		OWLNamedClass scPropertyFiller = null;
		OWLNamedClass queryPropertyFiller = null;
		if(scSomeValuesFrom.getFiller() instanceof OWLNamedClass && querySomeValuesFrom.getFiller() instanceof OWLNamedClass){
			scPropertyFiller = (OWLNamedClass)scSomeValuesFrom.getFiller();
			queryPropertyFiller = (OWLNamedClass)querySomeValuesFrom.getFiller();
		}
		// taxonomic similarity between roles/properties
		double taxRoleSim = taxonomicSimilarity(scSomeValuesFrom.getOnProperty(), querySomeValuesFrom.getOnProperty());		
		// Distinguish between different cases of how to calculate similarity between classRole and queryRole
		// case 1: queryRole has no filler
		// -> return taxSim of classRole and queryRole
		if(queryPropertyFiller==null){
			// Activator.logInfo("Computing role similarity of: " + getShortName(scSomeValuesFrom.getOnProperty()) + "(sc)  " + getShortName(querySomeValuesFrom.getOnProperty())+"(query)");			
			// Activator.logInfo("! Nr of query fillers < 0!");
			// Activator.logInfo(" ->sim = taxRoleSim = "+taxRoleSim);
			// Activator.logInfo("-------------------------------");
			return taxRoleSim;
		}
		// case 2: queryRole has filler but classRole hasn't 
		// -> return (taxRoleSimWeight*taxRoleSim) + (fillerSimWeight*fillerSim) = taxRoleSimWeight*taxRoleSim 
		else if(queryPropertyFiller!=null && scPropertyFiller==null){
			// Activator.logInfo("Computing role similarity of: " + getShortName(scSomeValuesFrom.getOnProperty()) + "(sc)  " + getShortName(querySomeValuesFrom.getOnProperty())+"(query)");
			// Activator.logInfo("! Nr of query fillers >= 0 and nr of sc fillers < 0!");
			// Activator.logInfo(" ->sim = taxRoleSimWeight*taxRoleSim = "+(taxRoleSimWeight*taxRoleSim));
			// Activator.logInfo("-------------------------------");
			return taxRoleSimWeight*taxRoleSim;	
		}
		// case 3: queryRole and classRole have fillers 
		//-> return roleSim of classRole and queryRole
		// table containing similarities between every class role filler and every query role filler
		double fillerSim = roleBasedSimilarity(scPropertyFiller, queryPropertyFiller);
		double sim = fillerSimWeight*fillerSim + taxRoleSimWeight*taxRoleSim;
		recursionDepth--;

		// Activator.logInfo("Computing role similarity of: " + getShortName(scSomeValuesFrom.getOnProperty()) + "(sc)  " + getShortName(querySomeValuesFrom.getOnProperty())+"(query)");
		// Activator.logInfo("normal compare finished!");
		// Activator.logInfo(" fillerSimWeight*fillerSim + taxRoleSimWeight*taxRoleSim = "+ fillerSimWeight+ "*"+fillerSim+ "+" + taxRoleSimWeight+ "*"+taxRoleSim);		
		// Activator.logInfo(" ->sim = "+sim);		
		// Activator.logInfo("-------------------------------");		
		return sim;		
	}
	
	
	/**
	 * This method retrieves all someValuesFrom restrictions from the given RDFResource concept on the given
	 * RDFProperty defaultPropery and returns them as a list. 
	 * 
	 * @param defaultProperty The RDFProperty for which existing someValuesFrom restrictions are retrieved
	 * @param concept The RDFResource representing the concept whose someValuesFrom restrictions are retrieved
	 * 
	 * @return List of someValuesFrom restrictions retrieved from the given RDFResource concept 
	 */
	@SuppressWarnings("unchecked")
	private static Vector<OWLSomeValuesFrom> retrieveOWLObjectProperties(RDFProperty defaultProperty, RDFResource concept){				
		Vector<OWLSomeValuesFrom> someValuesFromRestrictions = new Vector<OWLSomeValuesFrom>();		
		if(defaultProperty.getName().equals("owl:equivalentClass")){
			
			Collection<RDFResource> propertyValues= concept.getPropertyValues(defaultProperty, false); 
			Iterator<RDFResource> propValIt = propertyValues.iterator();			
			if(propertyValues.size()>1){
				System.err.println(" ERROR: Equivalent class of concept "+concept.getName()+" has more than one property value!");
			}
			if(propValIt.hasNext()){
				RDFResource currentEqResource = propValIt.next();
				if(currentEqResource instanceof OWLIntersectionClass){
					currentEqResource = (OWLIntersectionClass)currentEqResource;
					Collection<RDFResource> operands = ((OWLIntersectionClass) currentEqResource).getOperands();
					Iterator<RDFResource> operandsIt = operands.iterator(); 
					while(operandsIt.hasNext()){
						OWLSomeValuesFrom currentOperand = (OWLSomeValuesFrom)operandsIt.next();
						//RDFResource currentOperandFiller = currentOperand.getFiller();
						//RDFProperty currentOperandFillerProperty = currentOperand.getFillerProperty();
						RDFProperty currentOperandOnProperty = currentOperand.getOnProperty();						
						if(currentOperandOnProperty instanceof OWLObjectProperty && isInterestingProperty(currentOperandOnProperty)){
//							Activator.logInfo("     InterestingProperty (DefaultRDFProperty) found: " + currentOperandOnProperty.getName()  + " (with filler "+currentOperand.getFiller()+")");							
							someValuesFromRestrictions.add(currentOperand);
						}
					}
				}				
			}
		}
		return someValuesFromRestrictions;
	}
	
	
	/**
	 * Helping method for getConceptSimilarity retrieving all interesting properties of a given 
	 * RDFResource concept. 
	 * Uses isInterestingProperty to determine which properties of element should be returned.
	 * 
	 * @param RDFResource concept
	 * @return Vector<OWLObjectProperty> interestingRoles Vector containing all interesting roles of concept 
	 */
	@SuppressWarnings("unchecked")	
	private static Vector<OWLSomeValuesFrom> retrieveInterestingProperties(RDFResource concept){
		Vector<OWLSomeValuesFrom> interestingProperties = new Vector<OWLSomeValuesFrom>();
		
		Collection<RDFProperty> allProperties = concept.getRDFProperties();
		for(RDFProperty currentProperty : allProperties){
			if(currentProperty instanceof DefaultRDFProperty){
				Vector<OWLSomeValuesFrom> equivalentClassProperties = retrieveOWLObjectProperties(currentProperty, concept);
				interestingProperties.addAll(equivalentClassProperties);				
			}
		}		
		return interestingProperties;
	}
	
	/**
	 * Helping method to retrieve all possible mappings (Arrays) from query elements to class elements. 
	 * Elements can be roles or fillers (concepts). Returns 2D-Array containing all possible mappings.
	 * 
	 * @param nrOfClassElements
	 * @param nrOfQueryElements
	 * 
	 * @return 2D-Array containing all mappings as arrays
	 */
	protected static int[][] getAllMappings(int nrOfClassElements, int nrOfQueryElements){
		
		getAllMappingsCalls++;		
		int possibleMappingsCount = 0;
		int[] currentMapping = new int[nrOfQueryElements];
		// Initialize first ("smallest") mapping and nr of possible mappings
		if (nrOfQueryElements > nrOfClassElements){
			possibleMappingsCount = (int)(fac(nrOfQueryElements)/(fac(nrOfQueryElements-nrOfClassElements)));
			for (int i = 0; i<currentMapping.length; i++){				
				if (nrOfClassElements-(i+1)>=0){
					currentMapping[currentMapping.length-(i+1)] = nrOfClassElements-(i+1);
				}
				// mark query elements that couldn't be mapped on class elements with "-1"
				else currentMapping[currentMapping.length-(i+1)] = -1;
			}
		}		
		else {
			possibleMappingsCount = (int)(fac(nrOfClassElements)/(fac(nrOfClassElements-nrOfQueryElements)));
			for (int i = 0; i<currentMapping.length; i++){
				currentMapping[i] = i;
			}
		}	
		// Activator.logInfo("Nr of Class Elements: "+nrOfClassElements);
		// Activator.logInfo("Nr of Query Elements: "+nrOfQueryElements);
		// Activator.logInfo(" Possible mappings count: "+ possibleMappingsCount);
		// Activator.logInfo("GetAllMappings rekursiv "+getAllMappingsCalls+"mal aufgerufen!");
		// Initialize array containing all possible mappings from query-elements to class-elements		
		int[][] mappings = new int[possibleMappingsCount][nrOfQueryElements];
		// Add first mapping to vector of possible mappings
		mappings[0] = currentMapping.clone();
		// Get remaining mappings
		for (int i = 1; i<possibleMappingsCount; i++){
			currentMapping = add1(currentMapping, nrOfClassElements);
			mappings[i] = currentMapping.clone();
		}
		getAllMappingsCalls--;
		return mappings;		
	}
	
	
	/**
	 * Computes the number of possible mapping between two lists of roles or role fillers.
	 * 
	 * @param nrOfClassElements The number of roles or role fillers in the first list
	 * @param nrOfQueryElements The number of roles or role fillers in the second list
	 * @return numberOfPossibleMappings 
	 */
	protected static long computeNumberOfPossibleMappings(int nrOfClassElements, int nrOfQueryElements){
		long possibleMappingsCount = 0;
		possibleMappingsCount = (int)(fac(nrOfClassElements)/(fac(nrOfClassElements-nrOfQueryElements)));		
		return possibleMappingsCount;
	}
	
	/**
	 * Computes the initial mapping from query elements to software case elements as an integer array
	 * and returns the array.
	 * 
	 * @return initialMapping The integer array representing the initial mapping from query elements
	 *         to software case elements  
	 */
	protected static int[] getInitialMapping(int nrOfClassElements, int nrOfQueryElements){
		int[] initialMapping = new int[nrOfQueryElements];
		// Initialize first ("smallest") mapping and nr of possible mappings
		if (nrOfQueryElements > nrOfClassElements){
			for (int i = 0; i<initialMapping.length; i++){				
				if (nrOfClassElements-(i+1)>=0){
					initialMapping[initialMapping.length-(i+1)] = nrOfClassElements-(i+1);
				}
				// mark query elements that couldn't be mapped on class elements with "-1"
				else initialMapping[initialMapping.length-(i+1)] = -1;
			}
		}		
		else {
			for (int i = 0; i<initialMapping.length; i++){
				initialMapping[i] = i;
			}
		}		
		return initialMapping;
	}
	
	/**
	 * Helping method to retrieve and return bestMapping (int[]) of given "mappings" (int[][])
	 * between roles or fillers, that has the highest similarity (double[]) 
	 * according to given "similarities" (double[][])
	 * 
	 * @param mappings
	 * @param similarities
	 * @return
	 */
	protected static int[] getBestMapping(int nrOfClassElements, int nrOfQueryElements, double[][] similarities){
		
		long possibleMappingsCount = 0;
		if (nrOfQueryElements > nrOfClassElements){
			possibleMappingsCount = computeNumberOfPossibleMappings(nrOfQueryElements, nrOfClassElements);
		}
		else{
			possibleMappingsCount = computeNumberOfPossibleMappings(nrOfClassElements, nrOfQueryElements);			
		}
		
		if(possibleMappingsCount > 10000){
			// Activator.logInfo(" Number of possible mappings between SC and query elements: "+possibleMappingsCount);
			// Activator.logInfo(" Setting number to 10000");
			possibleMappingsCount = 10000;
		}
		
		// Mapping will be returned if it is needed later.
		int[] bestMapping = null;
		double bestMappingSim = 0;
		
		// Activator.logInfo(" ---------------------------------------------------");
		// Activator.logInfo(" Number of class elements: "+nrOfClassElements);
		// Activator.logInfo(" Number of query elements: "+nrOfQueryElements);
		// Activator.logInfo(" Number of possible mappings: "+possibleMappingsCount);

		int[] currentMapping = getInitialMapping(nrOfClassElements, nrOfQueryElements);
		double currentMappingSim = 0;
		for(int i=0; i<possibleMappingsCount; i++){
			currentMappingSim = getMappingSim(currentMapping, similarities);
//			Activator.logInfo(" Current mapping sim: "+currentMappingSim);
//			Activator.logInfo(" best mapping sim: "+bestMappingSim);
			if(currentMappingSim > bestMappingSim){
				bestMappingSim = currentMappingSim;
				bestMapping = currentMapping.clone();
//				Activator.logInfo(" Updating best mapping and best mapping sim...");
//				System.out.print("    new best mapping("+bestMapping.toString()+"):");
//			    for(int x=0;x<bestMapping.length;x++)
//			        System.out.print(bestMapping[x]);
//			    Activator.logInfo("");
//				Activator.logInfo("    has sim: "+bestMappingSim);
			}			
			currentMapping = add1(currentMapping, nrOfClassElements);
		}
//		System.out.print(" returning following mapping as best mapping: ");
//	    for(int x=0;x<bestMapping.length;x++)
//	        System.out.print(bestMapping[x]);
//	    Activator.logInfo("");
		return bestMapping;
	}
	
	/**
	 * Method computing the similarity of the given mapping from the given similarities.
	 * @param mapping
	 * @param similarities
	 * @return mapping similarity
	 */
	private static double getMappingSim(int[] mapping, double[][] similarities){
		double sim = 0;
		int mappingLength = mapping.length;
		for(int j=0; j<mappingLength; j++){
			if(mapping[j]>=0){
//				Activator.logInfo(" similarities[j][mapping[j]]: "+similarities[j][mapping[j]]);
				sim += (double)similarities[j][mapping[j]];
			}
		}
		sim /= mappingLength;					
		return sim;
	}
	
	
	/**
	 * Helping method to determine next mapping (between fillers or roles) 
	 * from given mapping
	 * 
	 * @param mapping
	 * @param nrOfCF
	 * 
	 *  @return mapping
	 */
	protected static int[] add1(int[] mapping, int nrOfCF){		
		// Vector, which holds the class-role-fillers already used in the current mapping
		Vector<Integer> usedClassFillers = new Vector<Integer>();		
		for (int i=0; i<mapping.length-1; i++){
			if (mapping[i]!=-1){
				usedClassFillers.add(mapping[i]);
			}			
		}		
		// Boolean value indicating if one query filler was successfully mapped on another class filler without carry over 
		boolean successfulCountUp = false;
		int i = mapping.length;
		// Go through mapping from last query filler to first query filler
		while (successfulCountUp==false && i>0){
			i--;
			// Remove current mapping from usedClassFillers
			if (usedClassFillers.contains(mapping[i])) {				
				usedClassFillers.removeAllElements();
				for(int j = 0; j<i; j++){
					if (mapping[j] != -1){
						usedClassFillers.add(mapping[j]);
					}
				}
			}			
			int j = 1;
			while(mapping[i]+j<nrOfCF){
				//Mapping is counted up on current index				
				if (!usedClassFillers.contains(mapping[i]+j)){
					mapping[i] += j;
					usedClassFillers.add(mapping[i]);
					successfulCountUp = true;
					break;		
				}
				j++;
			}
		}		
		i++;		
		// Go through mapping from successfully updated until last query filler
		while (i < mapping.length){
			//More query fillers than class fillers -> 	go right in mapping, until remaining query fillers 
			//											can all be mapped on class fillers
			mapping[i] = -1;
			int unusedClassFillers = nrOfCF-usedClassFillers.size();
			if (unusedClassFillers >= mapping.length-i){
				for (int j=0; j<nrOfCF; j++){
					if (!usedClassFillers.contains(j)){
						mapping[i] = j;			
						usedClassFillers.add(j);
						break;
					}
				}
			}
			i++;
		}
		return mapping;
	}
	
	/**
	 * Helper function to calculate and return
	 * the factorial of the given integer n
	 * 
	 * @param n
	 * 
	 * @return result
	 */
	protected static double fac(int n){		
		if(n<0) return -1;
		double result = 1;
		while (n>0){
			result = result*n;
			n -= 1;
		}
		return result;
	}
	
	
	/**
	 * Function testing if given RDFResource is a WordNet-Entry (e.g. a synset) or not.
	 * Therefor tests, if given RDFResource has property with name "dictionaryelements.ContainsSynonym"
	 * yes: return true
	 * no: return false
	 * 
	 * @param element
	 * @return 
	 */
	@SuppressWarnings("unchecked")
	protected static boolean isWordNetTerm(RDFResource element){
		OWLNamedClass elementClass = null;
		if (element instanceof OWLNamedClass){
			elementClass = (OWLNamedClass)element;			
			if (termLinksToWordNetEntryOfProperty != null){				
				for(RDFResource superClass : (Collection<RDFResource>)elementClass.getSuperclasses(false)){
					if(superClass instanceof OWLSomeValuesFrom){
						OWLSomeValuesFrom restriction = (OWLSomeValuesFrom)superClass;
						RDFProperty onproperty = restriction.getOnProperty();
						if(onproperty instanceof OWLObjectProperty){
							OWLObjectProperty owlonproperty = (OWLObjectProperty)onproperty;
							if(owlonproperty.equals(termLinksToWordNetEntryOfProperty))
								return true;
						}										
					}
				}
			}
		}
		return false;
	}
	
	
	/**
	 * Helperfunction to retrieve name of a RDFResource for formatted output
	 * @param element
	 * @return shortName of element
	 */
	private static String getShortName(RDFResource element){
		//Activator.logInfo("Elements full name: "+element.getName());
		String[] nameParts = element.getName().split("[:.]");
		//Activator.logInfo("Elements short name: "+nameParts[nameParts.length-1]);
		return nameParts[nameParts.length-1];
	}
	
	/**
	 * Helperfunction to retrieve name of a RDFProperty for formatted output
	 * @param property
	 * @return shortName of property
	 */
	@SuppressWarnings("unused")
	private static String getShortName(RDFProperty property){
		String[] nameParts = property.getName().split("[:.]");
		return nameParts[nameParts.length-1];
	}
	
	/**
	 * Helperfunction to retrieve type of a RDFProperty for formatted output
	 * @param property
	 * @return type of property
	 */
	@SuppressWarnings("unused")
	private static String getType(RDFResource element){		
		String[] nameParts = element.getName().split("[:.]");
		String result = ""; 
		for(int i = 0; i<nameParts.length-2; i++){
			result = result+nameParts[i]+".";
		}
		result = result+nameParts[nameParts.length-2];
		return result;
	}
	
	/**
	 * Retrieves UID from given objectString if there is any
	 * @param objectString
	 * @return UID
	 */
	@SuppressWarnings("unused")
	private static long getUID(String objectString){
		String regex = "-?[0-9]+";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(objectString);
		if(matcher.find()){
			return Long.valueOf(matcher.group()).longValue();
		}
		else return 0;
	}
	
	/**
	 *  Recursive method for printing arbitrary hierarchies of given 
	 *  RDFSClass and subclasses.
	 */
	@SuppressWarnings({ "unused", "unchecked" })
	private static void printClassTree(RDFSClass cl, String indentation){
		Activator.logInfo(indentation+cl.getName());
		for(Iterator<RDFSClass> it = cl.getSubclasses(false).iterator(); it.hasNext();){
			RDFSClass subclass = (RDFSClass)it.next();
			printClassTree(subclass, indentation);
		}
	}
	
	
	/**
	 * This method deletes all concepts of synonyms that are undefined in the 
	 * ontology.  
	 */
	@SuppressWarnings("unchecked")
	public static void removeUndefinedSynonymConcepts(){
		Collection<RDFSClass> concepts = ontology.getRDFSClasses();
		for(RDFSClass cl : concepts){
			if(cl instanceof OWLNamedClass){
				OWLNamedClass owlCl = (OWLNamedClass) cl;
				if(!owlCl.isDefinedClass()){					
					// Activator.logInfo(" OWLNamedClass "+owlCl.getName()+" is undefined.");
					owlCl.delete();
				}
			}
		}		
	}

	/**
	 * Returns roleSim2 from compare()
	 * @return similarity
	 */
	public double getSimilarity() {
		return _similarity;
	}	
	
	public void printBeforeClassify (String testClass) {
		try {
	    Activator.logInfo("Testing: " + testClass.toString());
	    Object supersBefore[] = ontology.getOWLNamedClass(testClass).getSuperclasses(true).toArray();
		Object equivalentBefore[]= ontology.getOWLNamedClass(testClass).getEquivalentClasses().toArray();	    
		for (Integer i = 0; i < supersBefore.length; i++) {
			Activator.logInfo("Before supers: " + supersBefore[i].toString());
		}
		for (Integer i = 0; i < equivalentBefore.length; i++) {
			Activator.logInfo("Before equiv.: " + equivalentBefore[i].toString());
		}
		if (supersBefore.length == 0) 
			Activator.logInfo("No supers before classification??");
		}
        catch(Exception e) {
        	Activator.logInfo("TestClass not in cases, ok...");
        }
	}
	
	public void printAfterClassify (String testClass) {
		try {
		Object supersAfter[] = ontology.getOWLNamedClass(testClass).getSuperclasses(true).toArray();                                  
		Object inferredSupersAfter[] = ontology.getOWLNamedClass(testClass).getInferredSuperclasses().toArray();
		Object equivalentAfter[]= ontology.getOWLNamedClass(testClass).getInferredEquivalentClasses().toArray();
		
		
		for (Integer i = 0; i < supersAfter.length; i++) {
			Activator.logInfo("After supers: " + supersAfter[i].toString());
		}
		for (Integer i = 0; i < inferredSupersAfter.length; i++) {
			Activator.logInfo("After inferred: " + inferredSupersAfter[i].toString());
		}
		
		for (Integer i = 0; i < equivalentAfter.length; i++) {
			Activator.logInfo("After equiv.: " + equivalentAfter[i].toString());
		}
		
		}
        catch(Exception e) {
        	Activator.logInfo("TestClass not in cases, ok...");
        }
	}

	/**
	 * Returns the hashMap containing the similarities of all combinations 
	 * of requirements from the compared graphs. 
	 * 
	 * @return requirementSimilarityMap
	 */
	public Map<OWLNamedClass, Map<OWLNamedClass, Double>> getRequirementsSimilarityMap(){
		return requirementSimilarityMap;
	}
	
	/**
	 * Returns the hashMap containing the similarities of all combinations 
	 * of domainelements from the compared graphs. 
	 * 
	 * @return requirementSimilarityMap
	 */	
	public Map<OWLNamedClass, Map<OWLNamedClass, Double>> getDomainElementsSimilarityMap(){
		return domainElementSimilarityMap;
	}	
	
	/**
	 * Retrieves the similarity of the two given representable elements from the
	 * corresponding similary-hashMap and returns the double value.
	 * <br>
	 * <br>
	 * Returns 0.0, if 
	 *   - the given representable elements are neither requirements nor domainElements
	 *   - owl classes of the given elements can't be retrieved from the ontology
	 * <br>
	 * <br>
	 * If the OWLNamedClasses for both elements were found but no corresponding similarity
	 * could be retrieved from the hashMap, the similarity is first computed by calling 
	 * getConceptSimilarity with the OWLNamedClasses of the elements as parameters before
	 * it is returned.
	 * 
	 * @param r1 First representable element (from first graph) 
	 * @param r2 Second representable element (from second graph)
	 * @return similarity between elements
	 * 
	 */
	public double getRepresentableElementSimilarity(RepresentableElement r1, RepresentableElement r2){
		double sim = 0.0;		
		String r1ClassName = SupportFunctions.constructConceptNameForVertex(r1);
		String r2ClassName = SupportFunctions.constructConceptNameForVertex(r2);
				
		OWLNamedClass r1Class = ontology.getOWLNamedClass(r1ClassName);
		OWLNamedClass r2Class = ontology.getOWLNamedClass(r2ClassName);
						
		if(r1 instanceof Requirement && r2 instanceof Requirement){
			if(requirementSimilarityMap.containsKey(r1Class) && (requirementSimilarityMap.get(r1Class)).containsKey(r2Class)){
				sim = (requirementSimilarityMap.get(r1Class)).get(r2Class);
			}
			else if(requirementSimilarityMap.containsKey(r2Class) && (requirementSimilarityMap.get(r2Class)).containsKey(r1Class)){
				sim = (requirementSimilarityMap.get(r2Class)).get(r1Class);
			}
			else{
				sim = getConceptSimilarity(r1Class, r2Class);				
			}
		}
		else if(r1 instanceof DomainElement && r2 instanceof DomainElement){
			if(domainElementSimilarityMap.containsKey(r1Class) && (domainElementSimilarityMap.get(r1Class)).containsKey(r2Class)){
				sim = (domainElementSimilarityMap.get(r1Class).get(r2Class));
			}
			else if(domainElementSimilarityMap.containsKey(r2Class) && (domainElementSimilarityMap.get(r2Class)).containsKey(r2Class)){
				sim = (domainElementSimilarityMap.get(r2Class).get(r1Class));
			}
			else{
				sim = getConceptSimilarity(r1Class, r2Class);				
			}			
		}
		else{
			// Activator.logInfo(" Given elements "+r1+" and "+r2+" are neither requirements nor domainelements, returning 0.0!");
		}		
		return sim;
	}

	/**
	 * Helping method that stores the given similarity between two given concepts in the 
	 *  - requirementSimilarityMap, if both are of the type Requirement
	 *  - domainElemenSimilarityMap, if both are of the type DomainElement
	 *  - nowhere else
	 *  
	 * @param softwareCaseConcept
	 * @param queryConcept
	 * @param sim
	 */
	private static void addToRepresentableElementMaps(OWLNamedClass softwareCaseConcept, OWLNamedClass queryConcept, double sim){
		// if both compared concepts are of types "Requirement" or "DomainElements", add them to the
		// respective hashMap containing similarities between instances of those types!
		// first check if concepts are requirements
		if(softwareCaseConcept.getSuperclasses(true).contains(requirementClass) && queryConcept.getSuperclasses(true).contains(requirementClass)){
			if(!requirementSimilarityMap.containsKey(softwareCaseConcept)){
				requirementSimilarityMap.put(softwareCaseConcept, new HashMap<OWLNamedClass, Double>());								
			}
			(requirementSimilarityMap.get(softwareCaseConcept)).put(queryConcept, sim);
		}
		// then check if concepts are domainelements
		else if(softwareCaseConcept.getSuperclasses(true).contains(domainElementClass) && queryConcept.getSuperclasses(true).contains(domainElementClass)){
				if(!domainElementSimilarityMap.containsKey(softwareCaseConcept)){
					domainElementSimilarityMap.put(softwareCaseConcept, new HashMap<OWLNamedClass, Double>());								
				}
				(domainElementSimilarityMap.get(softwareCaseConcept)).put(queryConcept, sim);
			}		
	}
	
	/**
	 * Helping method printing the given taxonomic distance dist between the two given <code>OWLNamedClass</code>es
	 * concept1 and concept2 if they are subclasses of the upper model concepts defined as interesting.
	 * 
	 * @param concept1
	 * @param concept2
	 * @param dist
	 */
	@SuppressWarnings("unused")
	private static void printTaxonomicDistanceBetweenInterestingConcepts(OWLNamedClass concept1, OWLNamedClass concept2, int dist){
		if(isInterestingForTaxonomicDistance(concept1) && isInterestingForTaxonomicDistance(concept2)){
			Activator.logInfo(" Taxonomic distance between concepts "+getShortName(concept1)+" and "+getShortName(concept2)+": "+dist);			
		}		
	}
	
	
	@SuppressWarnings("unchecked")
	private static Collection<OWLNamedClass> getAllSuperclasses(OWLNamedClass concept){
		Collection<OWLNamedClass> superclasses = concept.getSuperclasses(true);
		superclasses.addAll(concept.getInferredSuperclasses());
		return superclasses;
	}
	
	private static boolean isInterestingForTaxonomicDistance(OWLNamedClass concept){
		Collection<OWLNamedClass> superclasses = getAllSuperclasses(concept);
		for(RDFSClass currentConcept : superclasses){
			if(currentConcept instanceof OWLNamedClass && interestingConcepts.contains(currentConcept)){
				return true;
			}			
		}
		return false;
	}
	
}
