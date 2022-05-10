package eu.redseeds.owl;

import java.io.File;
import java.util.Vector;

/**
 * This class defines static, final properties for use in other classes.
 * 
 * @author Thorsten Krebs, Arved Solth
 */
public class Properties {
		
// --- WordNet ---

//ReDSeeDS Server
//	public static final String serverName = "redseeds.ivmx.pl";
//	public static final String serverName = "dev.redseeds.eu";

//WordNetSchema	
//	public static final String WN20SCHEMA = "http://de.uni_koblenz.jgwnl.wordnetschema.WordNetSchema#";
	public static final String WN20SCHEMA = "http://eu.redseeds.scl.SCLWordNetSchema#";
	
	public static final int DEFAULT_PORT = 8081;
	
// --- OWL ---
	public static final String WORKING_DIR = System.getProperty("user.dir") + File.separator;
	
	public static final String OWL_DIR = System.getProperty("java.io.tmpdir") + File.separator + "redseeds_owl" + File.separator;
	
	public static final String SCHEMATA_DIR = "schemata/";
	
	public static final String PELLET_INST = "pellet" + File.separator + "pellet.txt";
	
	public static final String WHITE_LIST_DIR = "doc/";
	
	public static final String SHORTCUTS_FILE = WHITE_LIST_DIR+"Shortcuts.txt";
	public static final String VERY_SHORT_SHORTCUTS_FILE = WHITE_LIST_DIR+"ShortcutsVeryShort.txt";
	
	public static final String DEFINING_RSL_PROPERTIES_FILE = WHITE_LIST_DIR+"DefiningPropertiesInRSL.txt";	
	public static final String DEFINING_RSL_PROPERTIES_WITH_SHORTCUTS_FILE = WHITE_LIST_DIR+"DefiningPropertiesInRSLShort.txt";
	public static final String DEFINING_RSL_PROPERTIES_WITH_VERY_SHORT_SHORTCUTS_FILE = WHITE_LIST_DIR+"DefiningPropertiesInRSLVeryShort.txt"; 
	
	public static final String NECESSARY_RSL_PROPERTIES_FILE = WHITE_LIST_DIR+"NecessaryPropertiesInRSL.txt";
	
	public static final String DL_RELEVANT_CONCEPTS_FILE = WHITE_LIST_DIR+"DLRelevantConcepts.txt";
	public static final String DL_RELEVANT_CONCEPTS_WITH_SHORTCUTS_FILE = WHITE_LIST_DIR+"DLRelevantConceptsShort.txt";
	public static final String DL_RELEVANT_CONCEPTS_WITH_VERY_SHORT_SHORTCUTS_FILE = WHITE_LIST_DIR+"DLRelevantConceptsVeryShort.txt";	
		
	// for owl experiments
	public static final String CASE_SENTENCES_FILE = WHITE_LIST_DIR+"ExperimentalCasesSentences.txt";
	
	public static final String REQUIREMENT_SPECIFICARION_TYPE = "rsl.rslrequirements.requirementsspecifications.RequirementsSpecification";
	public static final String REQUIREMENT_TYPE = "rsl.rslrequirements.requirementsspecifications.Requirement";
	public static final String DOMAIN_ELEMENT_TYPE = "rsl.rsldomainelements.domainelements.DomainElement";
	
	// add term names to synsets for debugging via the following new datatype property
	public static final String SYNSET_REPRESENTS_TERM_PROPERTY = "dictionaryelements.SynsetRepresentsTerm";
	
	// if set to false, case container will be constructed but similarity won't be computed
	public static boolean COMPUTE_SIMILARITY = true;
	
	public static boolean CLASSIFY = true;
	
	public static boolean test = false;
	
	// load files containing lists of defining and necessary properties and concepts relevant for similarity measure

	/* static variable indicating if shortcuts are used or not
	 * 0 = don't use shortcuts
	 * 1 = use shortcuts
	 * 2 = use very short shortcuts - still experimental!
	 */	
	public static int TAKE_SHORTCUTS = 1;
	
	public static boolean ADD_DISJOINTS = true;
	
	public static boolean ADD_COVER_AXIOMS = false;
		
	public static boolean ADD_TERM_NAME_TO_SYNSET = true;
	
	public static boolean HEAP_INFO = false;
	
	// static variable that - if set true - copies one case with one query in one TBox
	public static boolean CREATE_SINGLE_FILES = false;
	
	public static Vector<ShortCut> SHORTCUTS;
	public static Vector<String> DEFINING_RSL_PROPERTIES;
	public static Vector<String> NECESSARY_RSL_PROPERTIES;
	public static Vector<String> DL_RELEVANT_CONCEPTS;
	
	static{
		SupportFunctions.computeLists();	
	}
	
	public static Vector<String> UNNECESSARY_PROPERTY_PREFIXES = SupportFunctions.loadElementsFromFile(WHITE_LIST_DIR+"unnecessaryPropertyPrefixes.txt");
	public static Vector<String> CONCEPTS_TO_BE_COMPARED = SupportFunctions.loadElementsFromFile(WHITE_LIST_DIR+"ConceptsToBeCompared.txt");
	
	// add dl relevant concepts to list of "concept to be compared" because "concepts to be compared"
	// list only contains the most common concepts (for example, "concepts to be compared" contains
	// HyperLinkedSentence, but doesn't contain svo sentence or constrained language sentence!)
	static{
		CONCEPTS_TO_BE_COMPARED.addAll(DL_RELEVANT_CONCEPTS);
	}
	
	// Terms
	public static final String DETERMINER = "rsl.rsldomainelements.terms.Determiner";
	public static final String MODIFIER = "rsl.rsldomainelements.terms.Modifier";
	public static final String PREPOSITION = "rsl.rsldomainelements.terms.Preposition";
	
	// if set to "true", function verbosePrint will output given strings to standard output
	public static boolean VERBOSE = false;

	// if set to "true", OWL Property elements are created, else not   lh
	public static boolean CREATE_OWL_PROPERTIES = false;
	
	// sets the maximum number of graphs included in one CaseContainer
	public static int MAX_NR_OF_GRAPHS_PER_CASECONTAINER =8;
	
	
	static {
		File f = new File(OWL_DIR);
		f.mkdir();
	}


	static final String CASE_AND_QUERY_FILENAME = "CaseAndQuery.owl";


	static final String TBOX_FILENAME = "TBox.owl";


	static final String ALL_CASES_FILENAME = "all_cases.owl";


	public static final String CASE_CONTAINER_FILENAME = "CaseContainer.owl";


	public static final String HASHFILE_NAME = "caseContainerContainingCases.hash";
	
	public static void printHeapSize(String info){
		if (HEAP_INFO == true) {
			long heapSize = Runtime.getRuntime().totalMemory();			
	    
	    // Get maximum size of heap in bytes. The heap cannot grow beyond this size.
	    // Any attempt will result in an OutOfMemoryException.
			long heapMaxSize = Runtime.getRuntime().maxMemory();
	    
	    // Get amount of free memory within the heap in bytes. This size will increase
	    // after garbage collection and decrease as new objects are created.
			long heapFreeSize = Runtime.getRuntime().freeMemory();
			Activator.logInfo("--------------- HEAP INFO----------------------");
			Activator.logInfo("         " + info + "" + " CurrentHeap: " +  heapSize + "; Max Size: " + heapMaxSize + "; Free Size: " + heapFreeSize);
		}
	}

	
	
}
