package eu.redseeds.owl.connector;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.uni_koblenz.jgralab.Graph;
import edu.stanford.smi.protegex.owl.jena.JenaOWLModel;
import edu.stanford.smi.protegex.owl.model.OWLNamedClass;
import edu.stanford.smi.protegex.owl.model.RDFSClass;
import eu.redseeds.owl.Activator;
import eu.redseeds.owl.OWLSimilarity;
import eu.redseeds.owl.Properties;
import eu.redseeds.owl.SupportFunctions;
import eu.redseeds.owl.measures.DLSim;
import eu.redseeds.owl.reasoning.OWLReasoner;
import eu.redseeds.scl.SCLGraph;
import eu.redseeds.scl.rsl.rslkernel.elements.RepresentableElement;

public class Comparator {
		
	private SCLGraph queryGraph;
	private SCLGraph softwareCaseGraph;
	private JenaOWLModel ontology;
	private OWLReasoner reasoner = null;
	
	private List<OWLNamedClass> requirementsInSoftwareCase = null;
	private List<OWLNamedClass> requirementsInQuery = null;
	private List<OWLNamedClass> domainElementsInSoftwareCase = null;
	private List<OWLNamedClass> domainElementsInQuery = null;

	Map<RepresentableElement, Map<RepresentableElement, Double>> similarityMap;
	
	// -------------------------------------------------------------------------------
	
	public Comparator(SCLGraph qGraph){
		this(qGraph, null);
	}
	
	// -------------------------------------------------------------------------------
	
	public Comparator(SCLGraph qGraph, SCLGraph scGraph){
		queryGraph = qGraph;
		softwareCaseGraph = scGraph;	
		similarityMap = new HashMap<RepresentableElement, Map<RepresentableElement, Double>>();
		init();
	}
	
	
	public void clearComparator()  {		
		OWLSimilarity.clearTBox();
		// TEST
//		Properties.printHeapSize("Before setting ontology etc. to null");
		queryGraph = null;
		softwareCaseGraph = null;
		ontology = null;
		reasoner = null;
		requirementsInSoftwareCase = null;
		requirementsInQuery = null;
		domainElementsInSoftwareCase = null;
		domainElementsInQuery = null;
		similarityMap = null;
//		Properties.printHeapSize("After setting ontology etc. to null");
	}
	
	// -------------------------------------------------------------------------------
	
	public void init(){
		SupportFunctions.removeCaseContainerDirectories();

    	// starting pelletDigServer via main method of PelletDigServer class
		SupportFunctions.startPelletDigServer();    
		
		String scGraphId = SupportFunctions.getGraphHash(softwareCaseGraph);	
		Properties.printHeapSize("Before Case Container Creation");
		OWLSimilarity.ensureCaseContainersForSCLGraphsInitialisation((SCLGraph)softwareCaseGraph);
		String containingCaseContainer = OWLSimilarity.getContainingCaseContainer(scGraphId);;
		Properties.printHeapSize("After Case Container Creation");
//		Activator.logInfo(" GRAPH ID for container: " + scGraphId);
		if(containingCaseContainer.equals("")){
			Activator.logError(" WARNING! Case container for graph "+scGraphId+" didn't exist and wasn't created!", null);
		} else {
			Activator.logInfo(" Found graph in casecontainer "+containingCaseContainer);
		}
		Properties.printHeapSize("Before TBOX creation");
		ontology = OWLSimilarity.constructTBoxFromQueryAndCaseContainer(queryGraph, containingCaseContainer);	
		Properties.printHeapSize("After TBOX creation");
		reasoner = new OWLReasoner(ontology);
		// Classify all instances
		if (Properties.CLASSIFY){
			Activator.logInfo(" ***************");
			Activator.logInfo("   Classifying! ");
			Activator.logInfo(" ***************");
			reasoner.classify();
		}			
		Properties.printHeapSize("After reasoning");
		requirementsInSoftwareCase = getRequirementsFromGraph(softwareCaseGraph);
		requirementsInQuery = getRequirementsFromGraph(queryGraph);
		domainElementsInSoftwareCase = getDomainElementsFromGraph(softwareCaseGraph);
		domainElementsInQuery = getDomainElementsFromGraph(queryGraph);		
	}	
	
	// -------------------------------------------------------------------------------
	
	/**
	 * This method compares the requirement specification of the queryGraph
	 * with the requirement specification of the softwareCaseGraph 
	 * and returns the similarity between both as a double value.
	 */
	public double runCompare(){
//		OWLNamedClass queryRSClass = getReqSpecFromGraph(queryGraph);
		OWLNamedClass queryRSClass = SupportFunctions.getReqSpecFromGraph(queryGraph, reasoner);
//		Activator.logInfo("------------------------------------------------");
//		Activator.logInfo("  Query requirement specification: "+queryRSClass.getName());
//		OWLNamedClass scRSClass = getReqSpecFromGraph(softwareCaseGraph);
		OWLNamedClass scRSClass = SupportFunctions.getReqSpecFromGraph(softwareCaseGraph, reasoner);
//		Activator.logInfo("------------------------------------------------");		
//		Activator.logInfo("  Software case requirement specification: "+scRSClass.getName());

		DLSim dlsim = new DLSim(reasoner);
		double combinedSim = dlsim.compare(scRSClass, queryRSClass);
		
		
		// Test similarity between representable elements
//		log.info("------------------------------------------------");
//		log.info( "Stored similarity for "+reasoner.getRequirementsSimilarityMap().size()+" requirements of sc!");
//		Requirement queryReq = null;
//		Requirement scReq = null;
//		for(Vertex v : queryGraph.vertices()){
//			if(v instanceof Requirement){
//				queryReq = (Requirement)v;
//				log.info(" Found requirement in Query!");
//				break;
//			}
//		}
//		for(Vertex v : softwareCaseGraph.vertices()){
//			if(v instanceof Requirement){
//				scReq = (Requirement)v;
//				log.info(" Found requirement in SoftwareCase!");
//				break;
//			}
//		}
//		if(queryReq!=null && scReq!=null){
//			double reExampleSim = runCompare(queryReq, scReq);
//			Activator.logInfo(" Similarity between first requirements of query and softwarecase: "+reExampleSim);			
//		}
//		else{
//			Activator.logWarning(" WARNING: couldn't find requirements in software case or query!");
//		}				
		return combinedSim;
	}

	// -------------------------------------------------------------------------------
	
	/**
	 * This method compares the owl representations of the two given 
	 * representable elements r1 and r2 (mainly requirements or domainElements) 
	 * and returns the similarity between both as a double value.
	 * r1 is part of the query graph, r2 is part of the softwareCase graph.
	 */
	public double runCompare(RepresentableElement re1, RepresentableElement re2) {
		return reasoner.getRepresentableElementSimilarity(re1, re2);
	}
	
	// -------------------------------------------------------------------------------
	
	/**
	 * This method retrieves and returns the OWL class in the ontology which has the highest 
	 * similarity value when compared to queryGraph.
	 */
	public OWLNamedClass runRetrieve(){
		OWLNamedClass queryRequirementsspecificationsClass = SupportFunctions.getReqSpecFromGraph(queryGraph, reasoner);

		DLSim dlsim = new DLSim(reasoner);
		return dlsim.retrieve(queryRequirementsspecificationsClass);
	}

	
	// -------------------------------------------------------------------------------
	
	
//	private String getHashCode(String[] names) {
//		String hash = "";
//		for (int i = 0; i < names.length; i++) {
//			hash += names[i]+"\r\n";
//		}
//		return hash;
//	}

	// -------------------------------------------------------------------------------
//	
//	private void writeHash(File saveDir, String hash) {
//		// Create file
//		FileWriter fstream;
//		try {			
//			String hashName = saveDir+"\\"+subDirHash;
//			fstream = new FileWriter(hashName);
//			BufferedWriter out = new BufferedWriter(fstream);
//			out.write(hash);
//			out.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}

	// -------------------------------------------------------------------------------
	/*
	private String readHash() {
		// Open the file
		FileInputStream fstream;
		try {
			fstream = new FileInputStream(tboxHashFileName);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			while ((strLine = br.readLine()) != null) {
				return strLine;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}
	 */
	// -------------------------------------------------------------------------------
	
//	private String[] getTgFilesFromDir(String tgDirectory) {
//		// load all tg file names from given directory
//		// in case of new case files just save them in this dir
//		File dir = new File(tgDirectory);
//
//		FilenameFilter filter = new FilenameFilter() {
//			public boolean accept(File dir, String name) {
//				return name.endsWith(".tg");
//			}
//		};
//		String[] temp = dir.list(filter);
//		Arrays.sort(temp);
//		return temp;
//	}
	
	// -------------------------------------------------------------------------------
	
	/**
	 * Helping method checking if given owl class c represents an upper model concept.
	 * This is accomplished by checking, if the name of c is on the list of 
	 * dlRelevantConcepts, which are all upper models.
	 * Returns true, if c represents an upper model concept
	 * Returns false else
	 */
	private boolean isUpperModelConcept(OWLNamedClass c){
		if(Properties.DL_RELEVANT_CONCEPTS.contains(c.getName())){
			return true;
		}
		else{
			return false;
		}
	}
	
	// -------------------------------------------------------------------------------
	
	@SuppressWarnings("unchecked")
	private List<OWLNamedClass> getConceptsOfTypeFromGraph(Graph g, String conceptType){
		// Activator.logInfo(" Retrieving concepts of type "+conceptType+" from graph...");
		OWLNamedClass upperModelClass = ontology.getOWLNamedClass(conceptType);
		Collection<RDFSClass> retrievedConceptsCollection = upperModelClass.getSubclasses(true);
		List<OWLNamedClass> retrievedConcepts = new ArrayList<OWLNamedClass>();
		for(RDFSClass c: retrievedConceptsCollection){
			// Activator.logInfo("  ... current class: "+c.getName());
			if(c instanceof OWLNamedClass){
				if (!isUpperModelConcept((OWLNamedClass)c)){			
					retrievedConcepts.add((OWLNamedClass)c);
					// Activator.logInfo("    -> add to retrieved concepts!");
				}
				else{
					// Activator.logInfo("    -> don't add to retrieved concepts!");
				}
			}
			else{
				// Activator.logInfo("    -> don't add to retrieved concepts!");
			}
		}
		return retrievedConcepts;
	}
	
	// -------------------------------------------------------------------------------
	
	private List<OWLNamedClass> getRequirementsFromGraph(Graph g){		
		return getConceptsOfTypeFromGraph(g, Properties.REQUIREMENT_TYPE);		
	}
	
	// -------------------------------------------------------------------------------
	
	private List<OWLNamedClass> getDomainElementsFromGraph(Graph g){
		return getConceptsOfTypeFromGraph(g, Properties.DOMAIN_ELEMENT_TYPE);
	}
	
	// -------------------------------------------------------------------------------
	
	public List<OWLNamedClass> getDomainElementsInQuery(){
		return domainElementsInQuery;
	}
	
	// -------------------------------------------------------------------------------
	
	public List<OWLNamedClass> getDomainElementsInSoftwareCase(){
		return domainElementsInSoftwareCase;
	}

	// -------------------------------------------------------------------------------
	
	public List<OWLNamedClass> getRequirementsInQuery(){
		return requirementsInQuery;
	}	

	// -------------------------------------------------------------------------------
	
	public List<OWLNamedClass> getRequirementsInSoftwareCase(){
		return requirementsInSoftwareCase;
	}
	
	// -------------------------------------------------------------------------------
	
	public int getNrOfDomainElementsInSoftwareCase(){
		return domainElementsInSoftwareCase.size();
	}	

	// -------------------------------------------------------------------------------
	
	public int getNrOfDomainElementsInQuery(){
		return domainElementsInQuery.size();
	}	
	
	// -------------------------------------------------------------------------------
	
	public int getNrOfRequirementsInSoftwareCase(){
		return requirementsInSoftwareCase.size();
	}	
	
	// -------------------------------------------------------------------------------
	
	public int getNrOfRequirementsInQuery(){
		return requirementsInQuery.size();
	}
	
	public JenaOWLModel getOntology(){
		return ontology;
	}
}

