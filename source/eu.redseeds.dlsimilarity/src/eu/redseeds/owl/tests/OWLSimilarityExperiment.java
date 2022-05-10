package eu.redseeds.owl.tests;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Vector;

import de.uni_koblenz.jgralab.GraphIO;
import edu.stanford.smi.protegex.owl.jena.JenaOWLModel;
import edu.stanford.smi.protegex.owl.model.OWLNamedClass;
import eu.redseeds.owl.OWLSimilarity;
import eu.redseeds.owl.Properties;
import eu.redseeds.owl.SupportFunctions;
import eu.redseeds.owl.measures.DLSim;
import eu.redseeds.owl.reasoning.OWLReasoner;
import eu.redseeds.scl.SCLGraph;

/**
 * This class stores the results of calculating the owl similarity between a query and a case
 * before and after classification by pellet to be able to compare both similarity 
 * values and evaluate the impact that classification by pellet has on the calculation.
 * 
 * @author Arved Solth
 *
 */
public class OWLSimilarityExperiment {
	/**
	 * 
	 */
	private String directory;
	/**
	 * <code>Vector</code> of <SCLGraph>s containing the software cases that will be compared to
	 * the software case contained in the query graph.
	 * @see #queryGraph
	 */
	private HashMap<String, SCLGraph> caseGraphs;
	/**
	 * <code>SCLGraph</code> containing the software case that will be compared to all software
	 * cases contained in caseGraphs.
	 * @see #caseGraphs
	 */
	private SCLGraph queryGraph;
	/**
	 * <code>Vector</code> of <code>OWLSimilarityExperimentResult</code>s containing the results
	 * of similarity computations between the query graph and the case graphs.
	 * @see #queryGraph
	 * @see #caseGraphs
	 * @see eu.redseeds.owl.tests.OWLSimilarityExperimentResult
	 */
	private HashMap<String, OWLSimilarityExperimentResult> experimentResults;
	/**
	 * <code>Vector<OWLSimilarityExperimentResult>[]</code> containing the results of all experiments
	 * before classification as individual <code>Vector<OWLSimilarityExperimentResult></code>s.
	 * @see #experimentResults
	 */
	private HashMap<String, HashMap<String, OWLSimilarityExperimentResult>> finalResults;	
	/**
	 * <code>JenaOWLModel</code> containing the TBox constructed from the loaded software case graphs
	 * and the query graph.
	 */
	private JenaOWLModel tBox;
	/**
	 * <code>OWLReasoner</code> used to compute the similarity between software cases.
	 */
	private OWLReasoner reasoner;
	/**
	 * <code>DLSim</code> object used to call the compare method of reasoner to compare to cases.
	 * @see #reasoner
	 */
	private DLSim dlsim;
	/**
	 * Initializes the OWLSimilarityExperiment by initializing its private members.
	 * @see #caseGraphs
	 * @see #experimentResults
	 * @see #experimentResultsClassified
	 */
	private void initializeExperiment(){		
		experimentResults = new HashMap<String, OWLSimilarityExperimentResult>();
		finalResults = new HashMap<String, HashMap<String, OWLSimilarityExperimentResult>>();
		loadGraphs();
	}
	/**
	 * Reads the graph files of the cases that will be compared to the query.
	 * @param folderName Name of the folder containing the graph files that will be read.
	 * @see #caseGraphs
	 */	
	private void loadGraphs(){
		try{
			// read all graphs in folder
			File caseDir = new File(directory);
			String fileName;
			SCLGraph currentGraph;
			caseGraphs = new HashMap<String, SCLGraph>();
			for(File f : caseDir.listFiles()){				
				if(f.isFile() && f.getName().endsWith(".tg")){
					fileName = directory + f.getName();
					System.out.println("   Trying to load graph from file "+fileName);
					currentGraph = (SCLGraph)GraphIO.loadGraphFromFile(fileName, null);
					caseGraphs.put(f.getName(), currentGraph);
				}
			}
		}
		catch(Exception e){
			System.err.println(" ERROR: unable to open graph file!");
			e.printStackTrace();			
		}
		
		OWLSimilarityExperimentResult currentResult;
		
		for(String queryName : caseGraphs.keySet()){
			experimentResults = new HashMap<String, OWLSimilarityExperimentResult>();
			for(String caseName : caseGraphs.keySet()){
				// don't compare query class to itself!
				if(caseName.equals(queryName)) continue;
				currentResult = new OWLSimilarityExperimentResult(queryName, 
																  caseName,
																  caseGraphs.get(queryName),
																  caseGraphs.get(caseName));				
				experimentResults.put(caseName, currentResult);
			}
			finalResults.put(queryName, experimentResults);
		}				
	}	
	/**
	 * Constructs the <i>TBox.owl</i> file by first constructing case containers for all loaded 
	 * case graphs and then adding the query graph to the first case container.
	 */
	private void constructTBox(){
		System.out.println(" Setting max nr of graphs per case container to "+caseGraphs.size());
		Properties.MAX_NR_OF_GRAPHS_PER_CASECONTAINER = caseGraphs.size();
		LinkedList<SCLGraph> cases = new LinkedList<SCLGraph>(caseGraphs.values());
		System.out.println(" ***** Constructing TBox from "+cases.size()+" cases.");
		OWLSimilarity.ensureCaseContainersForSCLGraphsInitialisation(cases);
		String graphID = SupportFunctions.getGraphHash((SCLGraph)cases.getFirst());
		String containingCaseContainer = OWLSimilarity.getContainingCaseContainer(graphID);
//		tBox = OWLSimilarity.constructTBoxFromQueryAndCaseContainer(queryGraph, containingCaseContainer);
		tBox = OWLSimilarity.constructTBoxFromCaseContainer(containingCaseContainer);
		reasoner = new OWLReasoner(tBox);
		dlsim = new DLSim(reasoner);
	}	
	/**
	 * Sorts the <code>OWLSimilarityExperimentResult</code>s according to their similarity
	 * values. Reverses the sorted lists of results, so that the results with the highest
	 * similarity values come first and the results with the lowest similarity values come
	 * last.
	 * @see #experimentResults
	 * @see #experimentResultsClassified
	 */
	private void sortExperimentResults(Vector<OWLSimilarityExperimentResultTuple> resultTuples){
		OWLSimilarityExperimentResultTupleComparator tupleComparator = new OWLSimilarityExperimentResultTupleComparator();
		Collections.sort(resultTuples, tupleComparator);
		Collections.reverse(resultTuples);
	}
	/**
	 * 	
	 */
	private void computeSimilarities(){
		
		OWLNamedClass queryClass;
		OWLNamedClass caseClass;

		for(HashMap<String, OWLSimilarityExperimentResult> currentMap : finalResults.values()){
			for(OWLSimilarityExperimentResult result : currentMap.values()){
				queryClass = SupportFunctions.getReqSpecFromGraph(result.getQueryGraph(), reasoner);
				caseClass = SupportFunctions.getReqSpecFromGraph(result.getCaseGraph(), reasoner);
				System.out.println(" -------------------------------------------------------------------------------------------");
				System.out.println(" Computing Similarity for query "+result.getQueryName()+" and case "+result.getCaseName()+"!");
				System.out.println(" -------------------------------------------------------------------------------------------");
				if(Properties.CLASSIFY) result.setClassifiedSimilarity(dlsim.compare(caseClass, queryClass));				
				else result.setUnclassifiedSimilarity(dlsim.compare(caseClass, queryClass));				
			}
		}						
//		SupportFunctions.removeCaseContainerDirectories();
	}
	
	private void saveSimilarityExperimentAsCSVFile(){
		try{
			LinkedList<OWLSimilarityExperimentResult> results;
			for(HashMap<String, OWLSimilarityExperimentResult> currentMap : finalResults.values()){
				results = new LinkedList<OWLSimilarityExperimentResult>(currentMap.values());
				String fileName = directory
				   + ((results.getFirst().getQueryName()).split("\\."))[0]
				   + ".csv";
				System.out.println(" Saving results under "+fileName);
				FileWriter w = new FileWriter(fileName);
				BufferedWriter out = new BufferedWriter(w);
				out.write("Query:;"+results.getFirst().getQueryName()+"; ; "+System.getProperty("line.separator"));					  
				out.write("Case  ; unclassified similarity ; case ; classified similarity"+System.getProperty("line.separator"));
				
				Vector<OWLSimilarityExperimentResultTuple> unclassifiedTuple = new Vector<OWLSimilarityExperimentResultTuple>();
				Vector<OWLSimilarityExperimentResultTuple> classifiedTuple = new Vector<OWLSimilarityExperimentResultTuple>();
				for(OWLSimilarityExperimentResult r : results){
					unclassifiedTuple.add(new OWLSimilarityExperimentResultTuple(r, false));
					classifiedTuple.add(new OWLSimilarityExperimentResultTuple(r, true));
				}			
				sortExperimentResults(unclassifiedTuple);
				sortExperimentResults(classifiedTuple);
				
				assert unclassifiedTuple.size() == classifiedTuple.size();
				for(int i=0; i<classifiedTuple.size(); i++){
					out.write(unclassifiedTuple.elementAt(i).getName()
					  + ";" + double2String(unclassifiedTuple.elementAt(i).getSimilarity())
					  + ";" + classifiedTuple.elementAt(i).getName()
					  + ";" + double2String(classifiedTuple.elementAt(i).getSimilarity())
					  + System.getProperty("line.separator"));
				}
				out.close();				
			}
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
	private String double2String(double sim){
		String stringSim = "";
		stringSim += sim;
		stringSim = stringSim.replace(".", ",");
		return stringSim;
	}
	
	private void runExperiments(){
		SupportFunctions.removeCaseContainerDirectories();		
			
		constructTBox();

		// first compute similarities without classification
		Properties.CLASSIFY = false;
		computeSimilarities();
		
		System.out.println(" *******************");
		System.out.println("   Classifying  !!!!");
		System.out.println(" *******************");
		reasoner.classify();		
		
		// then compute similarites with classification
		Properties.CLASSIFY = true;
		computeSimilarities();
		saveSimilarityExperimentAsCSVFile();
	}
	/**
	 * Constructor for <code>OWLSimilarityExperiment</code>.
	 * @param folderName
	 * @param queryName
	 */
	public OWLSimilarityExperiment(String folderName){
		System.out.println(" Trying to load graphs from "+folderName);
		directory = folderName;
		initializeExperiment();		
		runExperiments();
	}		
}
