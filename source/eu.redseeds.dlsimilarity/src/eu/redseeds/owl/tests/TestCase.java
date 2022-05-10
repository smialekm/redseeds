package eu.redseeds.owl.tests;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import de.uni_koblenz.jgralab.GraphIO;
import de.uni_koblenz.jgralab.GraphIOException;
import edu.stanford.smi.protegex.owl.jena.JenaOWLModel;
import eu.redseeds.owl.Properties;
import eu.redseeds.owl.SupportFunctions;
import eu.redseeds.owl.connector.Comparator;
import eu.redseeds.scl.SCLGraph;

/**
 * This class defines Test Cases. It does not provide functionality to be
 * used within the ReDSeeDS Engine but to test the DL-based similarity
 * measure.
 * 
 * @author preliminary version: Thorsten Krebs
 * @author final version: Arved, Lothar
 */
public class TestCase {

//	public static final String tgDir = Properties.WORKING_DIR+"SoftwareCases"+File.separator+"cases"+File.separator;
	
//	public static final String queryDir = Properties.WORKING_DIR+"SoftwareCases"+File.separator+"queries"+File.separator;	
	
	public static final String commonCasesDir = Properties.WORKING_DIR+"SoftwareCases"+File.separator+"common"+File.separator;
	public static final String experiment16 = Properties.WORKING_DIR+"SoftwareCases"+File.separator+"experiment16cases"+File.separator;
	public static final String twoCases = Properties.WORKING_DIR+"SoftwareCases"+File.separator+"twoCases"+File.separator;
	public static final String taxDistExp = Properties.WORKING_DIR+"SoftwareCases"+File.separator+"taxDistExp"+File.separator;	
	public static final String caseLargerThanQuery = Properties.WORKING_DIR+"SoftwareCases"+File.separator+"caseLargerThanQuery"+File.separator;
	
	public static final boolean testClassifiedAndNotClassified = false;
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		
		Properties.test = true;
		
		// start pellet server
		SupportFunctions.startPelletDigServer();
		
		String arg = args[0];
		Vector<String> scFiles = new Vector<String>();
		List<SCLGraph> testGraphs = new ArrayList<SCLGraph>();		
		if (arg.equals("testInterface")) {
//			String queryFileName = "Query1_Actor_Client.scl.tg";
//			String queryFileName = "Query3_Actor_Client.scl.tg";
//			String queryFileName = "Query5_DomainStatements.scl.tg";
//			String queryFileName = "Query6_DomainStatements.scl.tg";
//			String queryFileName = "Query14_DomainStatement_convertRawData.scl.tg";
//			String queryFileName = "Query16_SVO_NLHS.scl.tg";
			
//			String queryFileName = "Case1_OnlineShop.scl.tg";
//			String queryFileName = "Case2_OnlineBanking.scl.tg";
//			String queryFileName = "Case3_OnlinePizzaService.scl.tg";
//			String queryFileName = "Case4_ClientManagementSystem.scl.tg";
//			String queryFileName = "Case5_Route-planner.scl.tg";
//			String queryFileName = "Case6_CASE-Tool.scl.tg";
//			String queryFileName = "Case7_UserChangesData.scl.tg";
			
//			String queryFileName = "sclgraph_ex07.tg";
//			String queryFileName = "sclgraph_ex1_rq.tg";
//			String queryFileName = "Reg2.tg";
//			String queryFileName = "new_sclgraph_q.tg";
//			String queryFileName = "revisited_Ex1Q.tg";
//			String queryFileName = "sclgraph_ex16c.tg";
//			String queryFileName = "sclgraph_ex7q.tg";
//			String queryFileName = "sclgraph_29c.tg";
//			String queryFileName = "Ex1C.tg";
			
			String queryFileName = "sclgraph1.tg";
			
//			String queryFileName = "PRODV_GIS4FA_sclgraph.tg";
//			String queryFileName = "PRODV_GIS4ER_sclgraph.tg";
//			String queryFileName = "sclgraph_ex01Q.tg";	
//			String queryFileName = "testQuery-sclgraph.tg";
			
			// new industry partner cases
//			String queryFileName = "GIS4EmergencyResponse.tg";	
//			String queryFileName = "CS_MaterialRequirementandManagementSystem.tg";
//			String queryFileName = "AS_FinancialContractsManagementSystem.tg";
//			String queryFileName = "AS_EuropeanFundingAdministrationSystemDuplicate.tg";
//			String queryFileName = "IVMX_CreditCardModule0.tg";		
			
			SCLGraph queryGraph = null;
			Integer queryNumber = 0;
			try {
				if (!(queryFileName.equals(""))) {
					queryNumber = 1;
//					System.out.println("Loading graph from file "+queryDir+queryFileName+"...");
//					queryGraph = (SCLGraph)GraphIO.loadGraphFromFile(queryDir + queryFileName, null);

//					System.out.println("Loading graph from file "+commonCasesDir+queryFileName+"...");
//					queryGraph = (SCLGraph)GraphIO.loadGraphFromFile(commonCasesDir + queryFileName, null);
					
					System.out.println("Loading graph from file "+twoCases+queryFileName+"...");
					queryGraph = (SCLGraph)GraphIO.loadGraphFromFile(twoCases + queryFileName, null);					
					
				}
			} catch (GraphIOException e) {
				System.out.println(" WARNING: unable to open graph file!");
				e.printStackTrace();
			}
			
			// eigene test cases
//			scFiles.add("Case1_OnlineShop.scl.tg");
//			scFiles.add("Case2_OnlineBanking.scl.tg");
//			scFiles.add("Case3_OnlinePizzaService.scl.tg");
//			scFiles.add("Case4_ClientManagementSystem.scl.tg");
//			scFiles.add("Case5_Route-planner.scl.tg");
//			scFiles.add("Case6_CASE-Tool.scl.tg");
//			scFiles.add("Case7_UserChangesData.scl.tg");
			
			// neu erstellte test cases
//			scFiles.add("sclgraph_ex05.tg");
//			scFiles.add("sclgraph_ex01C.tg");
//			scFiles.add("testCase-sclgraph.tg");
//			scFiles.add("sclgraph_ex1_rc.tg");
//			scFiles.add("Reg.tg");
//			scFiles.add("new_sclgraph_c.tg");
//			scFiles.add("revisited_Ex1C.tg");
//			scFiles.add("revisited_Ex1C.tg");
//			scFiles.add("revisited_Ex1C.tg");
//			scFiles.add("revisited_Ex1C.tg");


// ****************************************************************			
			
			// folgende cases sollten zu query Ex1C sehr ähnlich sein
//			scFiles.add("Ex1Q.tg");
//			scFiles.add("Ex34Q.tg");
//			scFiles.add("Ex35Q.tg");
//
//			scFiles.add("Ex3Q.tg");
//			scFiles.add("Ex36Q.tg");
//			scFiles.add("Ex38Q.tg");
//			scFiles.add("Ex39Q.tg");
//			scFiles.add("Ex37Q.tg");
//			scFiles.add("Ex48Q.tg");
//			scFiles.add("Ex15Q.tg");
//			
//			scFiles.add("Ex5Q.tg");
//			scFiles.add("Ex9Q.tg");
//			scFiles.add("Ex42Q.tg");
			
			// folgende cases sollten zu query Ex1C ähnlich sein
//			scFiles.add("Ex40Q.tg");
//			scFiles.add("Ex4Q.tg");
//			scFiles.add("Ex7Q.tg");
//			
//			scFiles.add("Ex13Q.tg");
//			scFiles.add("Ex46Q.tg");	
//			
//			scFiles.add("Ex11Q.tg");
//			scFiles.add("Ex43Q.tg");
//			scFiles.add("Ex44Q.tg");		
//			
//			scFiles.add("Ex14Q.tg");
//			scFiles.add("Ex45Q.tg");
//			scFiles.add("Ex47Q.tg");	
//			
//			scFiles.add("Ex2Q.tg");
//			scFiles.add("Ex10Q.tg");				
			
			// folgende cases sollten zu query Ex1C unähnlich sein
//			scFiles.add("Ex8Q.tg");
//			scFiles.add("Ex12Q.tg");
//			scFiles.add("Ex41Q.tg");
			
// ****************************************************************		
			
			// alle Uni Hamburg experimental cases
//			File caseDir = new File(commonCasesDir);
//			for(File f : caseDir.listFiles()){
//				if(f.isFile() && f.getName().endsWith(".tg")){
//					scFiles.add(f.getName());
//				}
//			}

// ****************************************************************	
			
			scFiles.add("sclgraph2.tg");
			
//			scFiles.add("sclgraph_ex3q.tg");
//			scFiles.add("sclgraph_ex16c.tg");
//			scFiles.add("sclgraph_ex7q.tg");
//			scFiles.add("sclgraph_14q.tg");
			
// Industriepartner cases
//			scFiles.add("AS_EFAS_sclgraph.tg");
//			scFiles.add("AS_EFASD_sclgraph.tg");
//			scFiles.add("AS_FCMS_sclgraph.tg");
//			scFiles.add("AS_FCMSD_sclgraph.tg");
//			scFiles.add("CS_PS2_sclgraph.tg");
//			scFiles.add("CS_PS_sclgraph.tg");
//			scFiles.add("CS_SCS2_sclgraph.tg");
//			scFiles.add("CS_SCS_sclgraph.tg");
//			scFiles.add("HWU_SLROL_sclgraph.tg");
//			scFiles.add("HWU_TTCC_sclgraph.tg");
//			scFiles.add("IVMX_A_sclgraph.tg");
//			scFiles.add("IVMX_IF2_sclgraph.tg");
//			scFiles.add("IVMX_IF_sclgraph.tg");
//			scFiles.add("PRODV_GIS4ER_sclgraph.tg");
//			scFiles.add("PRODV_GIS4FA_sclgraph.tg");
//			scFiles.add("PRODV_GIS4FAD_sclgraph.tg");			
//			scFiles.add("GIS4ForestryApplications_sclgraph.tg");
//			scFiles.add("InternetBankingSystemSclgraph.tg");
			
			// new ip cases			
//			scFiles.add("GIS4ForestryApplications.tg");
//			scFiles.add("AS_FinancialContractsManagementSystem.tg");
//			scFiles.add("AS_EuropeanFundingAdministrationSystemDuplicate.tg");
//			scFiles.add("CS_MaterialRequirementandManagementSystem.tg");
//			scFiles.add("IVMX_CreditCardModule0.tg");
			             			
			String outfileName;
			JenaOWLModel ontology;
			Integer sizeOfTBox, sizeOfTBox2;
			HashMap<String,Integer> allSizes = new HashMap<String,Integer>();
			if (Properties.CREATE_SINGLE_FILES == true) {
//				for(String n : scFiles){
//					testGraphs = new ArrayList<SCLGraph>();
//					SCLGraph scGraph = null;
//					try {
////						System.out.println("Loading graph from file "+tgDir+n+"...");						
////						scGraph = (SCLGraph)GraphIO.loadGraphFromFile(tgDir	+ n, null);
//						System.out.println("Loading graph from file "+commonCasesDir+n+"...");						
//						scGraph = (SCLGraph)GraphIO.loadGraphFromFile(commonCasesDir	+ n, null);						
//						testGraphs.add((SCLGraph)scGraph);
//					} catch (GraphIOException e) {
//						e.printStackTrace();
//					};
//					System.out.println("Number of loaded Graphs: " + testGraphs.size());
////					System.out.println("Number of Queries: " + queryNumber);					
//					OWLSimilarity.ensureCaseContainersForSCLGraphsInitialisation(testGraphs);
//					Comparator comp = new Comparator(queryGraph, testGraphs.get(0));
//					
//					sizeOfTBox = computeTBoxSize(comp.getOntology());
//					outfileName = Properties.OWL_DIR + "TBox"+ "_" + n +".owl";					
//					allSizes.put(outfileName, sizeOfTBox);
//					System.out.println(" Tbox: "+Properties.OWL_DIR + "TBox.owl" + " Size: " + sizeOfTBox + " OWL classes. ");
//					
//					double erg = comp.runCompare();
//					ergs.add(erg);
////					System.out.println("Similarity between query and software case: "+erg);
//
//					try {copy(Properties.OWL_DIR + "TBox.owl", outfileName); 
//					} catch (IOException e) {
//						System.out.println("TBox not copied into "+ outfileName);
//						e.printStackTrace();
//					}
//					};
//				printSizes(allSizes);
			}
			else {
								
//				File expDir = new File(commonCasesDir);
//				File[] files = expDir.listFiles();
//				File f;
//				for(int i=0; i<files.length; i++){
//					f = files[i];
//					if(f.isFile() && f.getName().endsWith(".tg")){
//						currentExperiment = new OWLSimilarityExperiment(commonCasesDir, f.getName());
//					}
//				}					
				
				// Graphs are loaded here ***************************************
				testGraphs = new ArrayList<SCLGraph>();
				for(String n : scFiles){
					SCLGraph scGraph = null;					
					try {
//						System.out.println("Loading graph from file "+tgDir+n+"...");
//						scGraph = (SCLGraph)GraphIO.loadGraphFromFile(tgDir	+ n, null);

//						System.out.println("Loading graph from file "+commonCasesDir+n+"...");
//						scGraph = (SCLGraph)GraphIO.loadGraphFromFile(commonCasesDir	+ n, null);

						System.out.println("Loading graph from file "+twoCases+n+"...");
						scGraph = (SCLGraph)GraphIO.loadGraphFromFile(twoCases	+ n, null);						
						
						testGraphs.add((SCLGraph)scGraph);
					} catch (GraphIOException e) {
						e.printStackTrace();
					}
				}
				System.out.println("Number of loaded Graphs: " + testGraphs.size());
				Comparator myComparator;
				
				if(Properties.COMPUTE_SIMILARITY){
					// Similarity computation begins here ****************************
					assert testGraphs.size() == scFiles.size();
					// first compare without classification
					Properties.CLASSIFY = false;					
					for(int i = 0; i<testGraphs.size(); i++){
						System.out.println(" Starting OWL similarity computation for case "+scFiles.elementAt(i)+" and query "+queryFileName);
						myComparator = new Comparator(queryGraph, testGraphs.get(i));
						System.out.println(" Similarity: " + myComparator.runCompare());
					}
					
					// then compare with classification
					Properties.CLASSIFY = true;
					for(int i = 0; i<testGraphs.size(); i++){
						System.out.println(" Starting OWL similarity experiment for case "+scFiles.elementAt(i)+" and query "+queryFileName);
						myComparator = new Comparator(queryGraph, testGraphs.get(i));
						System.out.println(" Similarity: " + myComparator.runCompare());						
					}					
//					printResults(exps);
//					printResults(exps2);
					
				}
//				else{
//					System.out.println(" Finished with creating case container(s) for loaded graphs!");
				}
			}		
			
			// ------------------------------------------------
			// Testing new function comparing two requirements!
			//RepresentableElement req1 = (RepresentableElement)queryGraph.getVertex(13);
			//RepresentableElement req2 = (RepresentableElement)scGraph.getVertex(20);			
			//double erg = comp.runCompare(req1, req2);
						
			/*
			// ------------------------------------------------
			// Testing new function of retrieving and counting the requirements and domain elements in a graph
			int nrOfDEQuery = comp.getNrOfDomainElementsInQuery();
			int nrOfDESC = comp.getNrOfDomainElementsInSoftwareCase();
			int nrOfReqQuery = comp.getNrOfRequirementsInQuery();			
			int nrOfReqSC = comp.getNrOfRequirementsInSoftwareCase();
			
			Vector<OWLNamedClass> queryDEs = comp.getDomainElementsInQuery();
			Vector<OWLNamedClass> scDEs = comp.getDomainElementsInSoftwareCase();
			Vector<OWLNamedClass> queryReqs = comp.getRequirementsInQuery();
			Vector<OWLNamedClass> scReqs = comp.getRequirementsInSoftwareCase();
			
			System.out.println("Found "+nrOfDEQuery+" domain elements in Query: ");
			for(OWLNamedClass c : queryDEs){
				System.out.println("  - "+c.getName());
			}
			System.out.println("Found "+nrOfDESC+" domain elements in software case: ");
			for(OWLNamedClass c : scDEs){
				System.out.println("  - "+c.getName());
			}
			System.out.println("Found "+nrOfReqQuery+" requirements in Query: ");
			for(OWLNamedClass c : queryReqs){
				System.out.println("  - "+c.getName());
			}
			System.out.println("Found "+nrOfReqSC+" requirements in software case: ");
			for(OWLNamedClass c : scReqs){
				System.out.println("  - "+c.getName());
			}			
			*/			
			
			/*
			// ------------------------------------------------
			// Testing function of retrieving the case most similar to the given query
			OWLNamedClass similarCase = comp.runRetrieve();
			System.out.println(" Most similar software case: "+similarCase.getName());
			*/
		else if(arg.equals("owlExperiment")){
			OWLSimilarityExperiment currentExperiment = null;

			currentExperiment = new OWLSimilarityExperiment(commonCasesDir);
//			currentExperiment = new OWLSimilarityExperiment(twoCases);
//			currentExperiment = new OWLSimilarityExperiment(experiment16);
//			currentExperiment = new OWLSimilarityExperiment(taxDistExp);
//			currentExperiment = new OWLSimilarityExperiment(caseLargerThanQuery);
		}  
		else{		 
			System.out.println("Argument has to be \"testInterface\" or \"owlExperiment\"!");
		}
	}
	
	

	public static void copy(String file1, String file2) throws IOException {
	    File inputFile = new File(file1);
	    File outputFile = new File(file2);

	    FileReader in = new FileReader(inputFile);
	    FileWriter out = new FileWriter(outputFile);
	    System.out.println("Copy file from: " + file1 +  " to: " + file2);
	    int c;

	    while ((c = in.read()) != -1)
	      out.write(c);

	    in.close();
	    out.close();
	  }
	
	@SuppressWarnings("unused")
	private static void printSizes(HashMap<String,Integer> allSizes) {
	    Set<Map.Entry<String,Integer>> set = allSizes.entrySet();
	    Iterator<Map.Entry<String,Integer>> i = set.iterator();

	    while(i.hasNext()){
	      Map.Entry<String,Integer> me = (Map.Entry<String,Integer>)i.next();
	      System.out.println(me.getKey() + "  Size: " + me.getValue() );
	    }

	}
	
	@SuppressWarnings("unused")
	private static Integer computeTBoxSize(JenaOWLModel ontology) {
		///return ontology.getClsCount();
		int count = 0; //ontology.getOWLClasses().size();
		Iterator<?> allConcepts = ontology.getRDFSClasses().iterator(); 		
		while(allConcepts.hasNext()) {
			if (allConcepts.next().getClass().getName().equals("edu.stanford.smi.protegex.owl.model.impl.DefaultOWLNamedClass")) 
				count = count +1;
		}
		return count;
	}
	
	/*
	 * %TBox sizes (including case7 as query
	 * %TBox_CS_SCS2_sclgraph.tg.owl  Size: 379
	 * %TBox_AS_EFAS_sclgraph.tg.owl  Size: 754
	 * %TBox_AS_FCMS_sclgraph.tg.owl  Size: 612
	 * %TBox_CS_PS_sclgraph.tg.owl  Size: 544
	 * %TBox_AS_FCMSD_sclgraph.tg.owl  Size: 637
	 * %TBox_HWU_SLROL_sclgraph.tg.owl  Size: 170
	 * %TBox_HWU_TTCC_sclgraph.tg.owl  Size: 430
	 * %TBox_CS_SCS_sclgraph.tg.owl  Size: 384
	 * %TBox_IVMX_IF2_sclgraph.tg.owl  Size: 678
	 * %TBox_IVMX_A_sclgraph.tg.owl  Size: 987
	 * %TBox_IVMX_IF_sclgraph.tg.owl  Size: 487
	 * %TBox_PRODV_GIS4FAD_sclgraph.tg.owl  Size: 237
	 * %TBox_PRODV_GIS4ER_sclgraph.tg.owl  Size: 439
	 * %TBox_PRODV_GIS4FA_sclgraph.tg.owl  Size: 254
	 * %TBox_CS_PS2_sclgraph.tg.owl  Size: 540
	 * %TBox_AS_EFASD_sclgraph.tg.owl  Size: 793
	 */
	
}

