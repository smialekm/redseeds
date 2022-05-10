package eu.redseeds.owl;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import de.uni_koblenz.jgralab.Graph;
import de.uni_koblenz.jgralab.GraphIO;
import de.uni_koblenz.jgralab.GraphIOException;
import de.uni_koblenz.jgralab.ProgressFunction;
import de.uni_koblenz.jgralab.schema.Schema;
import edu.stanford.smi.protegex.owl.jena.JenaOWLModel;
import eu.redseeds.owl.converter.WordNet2OWL;
import eu.redseeds.owl.converter.uko_adapted.Graph2OWLConcept;
import eu.redseeds.owl.converter.uko_adapted.JGraLab2OWL;
import eu.redseeds.owl.converter.uko_adapted.Schema2OWL;
import eu.redseeds.sc.terminology.model.RemoteJGWNL;
import eu.redseeds.scl.SCLGraph;
import eu.redseeds.wrapper.SoftwareCaseLoader;
import eu.redseeds.wrapper.WordNetQuery;

public class OWLSimilarity {
	private static final String CASE_CONTAINER_DIRNAME = "CaseContainer";

	private static WordNetQuery wn;
	private static WordNet2OWL wn2owl;

	public static void ensureCaseContainersForSCLGraphsInitialisation(
			SCLGraph initialGraph) {
		List<SCLGraph> pseudoList = new ArrayList<SCLGraph>();
		pseudoList.add(initialGraph);
		ensureCaseContainersForSCLGraphsInitialisation(pseudoList);
	}

	// -------------------------------------------------------------------------------

	private static void createGraphCaseContainers(
			List<SCLGraph> graphsForContainer) {
		// Activator.logInfo(" CREATING CASE CONTAINER...");
		File newDir = addNewCaseContainerDirectory();
		File tmpFile = new File(newDir + File.separator+ Properties.ALL_CASES_FILENAME);
		// Activator.logInfo(" CONVERTING GRAPHS TO OWL...");
		try {
			OWLSimilarity.saveGraphsToOWLConcepts(tmpFile.getAbsolutePath(), graphsForContainer, true, false, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
//		Activator.logInfo(" ...FINISHED CONVERTING GRAPHS TO OWL!");
//		Activator.logInfo(" PARSING SOFTWARE CASES WITH WORDNET... ");
		WordNet2OWL w2o = new WordNet2OWL(wn, tmpFile.getAbsolutePath());
		for (Graph gr : graphsForContainer) {
			// w2o.parse(new SoftwareCaseLoader(gr) , false);
			// TESTING NEW WAY TO ADD SYNSETS TO ALL TERMS OF THE CURRENT GRAPH!
			w2o.parseAllTerms(new SoftwareCaseLoader(gr), false);
		}
//		Activator.logInfo(" ...FINISHED PARSING SOFTWARE CASES!");
		// Activator.logInfo(" WRITING HASH FOR CASE CONTAINER...");
		w2o.write(newDir + File.separator + Properties.CASE_CONTAINER_FILENAME);
		// Activator.logInfo(" ...FINISHED WRITING HASH FOR CASE CONTAINER!");
		// Activator.logInfo(" DELETING TEMP FILE...");
		// tmpFile.delete();
		// Activator.logInfo(" ...FINISHED!");
		// only save hash if all steps finished correctly
		SupportFunctions.writeHash(newDir, SupportFunctions
				.getHashCode(graphsForContainer));
//		Activator.logInfo(" ...FINISHED CASE CONTAINER CREATION!");
	}

	public static void ensureCaseContainersForSCLGraphsInitialisation(List<SCLGraph> initialGraphs) {
		Activator.logInfo("********************************");
		Activator.logInfo("Beginning initialization of owl!");
		// Activator.logInfo(" - "+initialGraphs.size()+" graphs BEFORE filtering!");
		List<SCLGraph> graphs = filterNewGraphs(initialGraphs);
		// Activator.logInfo(" - "+graphs.size()+" graphs AFTER filtering!");
		wn = new WordNetQuery(RemoteJGWNL.getInstance().getJGWNLClient());

		List<SCLGraph> graphsInCurrentContainer = new ArrayList<SCLGraph>();
		// Activator.logInfo(" After wordnetquery");
		for (SCLGraph currentGraph : graphs) {
			graphsInCurrentContainer.add(currentGraph);
			if (graphsInCurrentContainer.size() >= Properties.MAX_NR_OF_GRAPHS_PER_CASECONTAINER) {				
				createGraphCaseContainers(graphsInCurrentContainer);
				graphsInCurrentContainer.clear();
			}
		}
		// Activator.logInfo(" After case container");
		// only create new case container, if there are any graphs left which
		// don't already have a container
		if (graphsInCurrentContainer.size() > 0) {
			// Activator.logInfo(" Before createGraphCaseContainers");
			createGraphCaseContainers(graphsInCurrentContainer);
			// Activator.logInfo(" After createGraphCaseContainers");
		} else {
			// Activator.logInfo("! Case containers already exist for all
			// loaded graphs!");
		}
		Activator.logInfo("Finished initialization of owl!");
		Activator.logInfo("********************************");
	}

	private static File addNewCaseContainerDirectory() {
		File owlDirectory = new File(Properties.OWL_DIR);
		File[] owlSubDirs = SupportFunctions.getSubdirectoriesOf(owlDirectory);

		String newDirName = Properties.OWL_DIR + CASE_CONTAINER_DIRNAME
				+ (owlSubDirs.length + 1);
		File newDir = new File(newDirName);
		if (!newDir.isDirectory()) {
			newDir.mkdir();
		}
		return newDir;
	}

	// -------------------------------------------------------------------------------

	private static List<SCLGraph> filterNewGraphs(List<SCLGraph> initialGraphs) {
		List<SCLGraph> filteredGraphs = new ArrayList<SCLGraph>();
		for (SCLGraph g : initialGraphs) {
			String graphName = SupportFunctions.getGraphHash(g);
			if (getContainingCaseContainer(graphName).isEmpty()) {
				filteredGraphs.add(g);
			}
		}
		return filteredGraphs;
	}

	// -------------------------------------------------------------------------------

	public static String getContainingCaseContainer(String scGraphId) {
		// Activator.logInfo(" Getting subdirs of "+Properties.OWL_DIR);
		File[] subDirs = SupportFunctions.getSubdirectoriesOf(new File(
				Properties.OWL_DIR));
		Vector<String> tGraphsInSubDir = new Vector<String>();
		for (File subDir : subDirs) {
			File[] files = SupportFunctions.getFilesFrom(subDir);
			if (files != null)
				for (File f : files) {
					String name = f.getName();
					if (name.startsWith(Properties.HASHFILE_NAME)) {
						tGraphsInSubDir = SupportFunctions
								.loadElementsFromFile(f.getAbsolutePath());
						if (tGraphsInSubDir.contains(scGraphId)) {
							Activator.logInfo(" +++  FOUND Case Container containing current case: "
											+ scGraphId);
							return subDir.getAbsolutePath() + File.separator
									+ Properties.CASE_CONTAINER_FILENAME;
						}
					}
				}
		}
		return "";
	}

	// -------------------------------------------------------------------------------

	public static JenaOWLModel constructTBoxFromQueryAndCaseContainer(
			Graph queryGraph, String containingCaseContainer) {
		appendQuery2TBox(queryGraph, containingCaseContainer,
				Properties.CASE_AND_QUERY_FILENAME);
		String owlFilename = Properties.OWL_DIR
				+ Properties.CASE_AND_QUERY_FILENAME;
		Properties.printHeapSize("Before WordNetQuery");
		WordNetQuery wn = new WordNetQuery(RemoteJGWNL.getInstance()
				.getJGWNLClient());
		Properties.printHeapSize("Before WordNet2OWL");
		WordNet2OWL w2o = new WordNet2OWL(wn, owlFilename);
		// ...parse WordNet Terms of query
		Properties.printHeapSize("Before CaseLoader");
		SoftwareCaseLoader loader = new SoftwareCaseLoader(queryGraph);
		// w2o.parse(loader, false);
		w2o.parseAllTerms(loader, true);
		Properties.printHeapSize("After Parse");
		w2o.write(Properties.OWL_DIR + Properties.TBOX_FILENAME);
		wn2owl = w2o;
		return w2o.getOntology();
	}

	public static void clearTBox() {
		wn2owl.clearOntology();
		wn2owl = null;
	}

	// -------------------------------------------------------------------------------

	public static JenaOWLModel constructTBoxFromCaseContainer(
			String caseContainer) {
		WordNetQuery wn = new WordNetQuery(RemoteJGWNL.getInstance()
				.getJGWNLClient());
		WordNet2OWL w2o = new WordNet2OWL(wn, caseContainer);
		return w2o.getOntology();
	}

	// -------------------------------------------------------------------------------

	private static void appendQuery2TBox(Graph query, String tBoxfileName,
			String outputName) {
		// load DOM tree and append...
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder loader;
		try {
			// load DOM document
			loader = factory.newDocumentBuilder();
			// Activator.logInfo("TBox filename is: " + tBoxfileName);
			Document document = loader.parse(tBoxfileName);

			// append query to existing document (cases)... see JGraLab2OWL
			// new Graph2OWLInstance(document, query, true, false, null);
			// new Graph2OWLConcept(document, query, true, false, null,
			// "http://de.uni_koblenz.jgwnl.wordnetschema.WordNetSchema#");
			new Graph2OWLConcept(document, query, true, false, null,
					Properties.WN20SCHEMA);
			// new Graph2OWLConcept(document, query, true, false, null, null);

			// save DOM tree (TBOX)
			Transformer transformer = TransformerFactory.newInstance()
					.newTransformer();
			DOMSource source = new DOMSource(document);
			// log.info(" Output path: "+Properties.OWL_DIR + outputName);

			DataOutputStream out = new DataOutputStream(
					new BufferedOutputStream(new FileOutputStream(new File(
							Properties.OWL_DIR + outputName))));
			// System.err.println("WRiting case and query to '" +
			// Properties.OWL_DIR + outputName + "'");
			StreamResult result = new StreamResult(out);

			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(
					"{http://xml.apache.org/xslt}indent-amount", "4");
			transformer.transform(source, result);

			out.flush();
			out.close();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}
		Activator.logInfo("Saved TBox.owl!");
	}

	public static void saveGraphsToOWLConcepts(String filename,
			List<? extends Graph> graphs, boolean edgeClasses2Properties,
			boolean appendSuffix2EdgeClassName, ProgressFunction pf)
			throws IOException {
		JGraLab2OWL j2o = new JGraLab2OWL(filename);
		// this schema does not contain any WordNet classes! e.g. nounSynset
		// Schema schema = graphs[0].getSchema(); DONOT use!
		// System.err.println("Schema from Graph: " + schema);
		Schema schema = null;
		// Activator.logInfo("DLSim: Trying to load SCLWordNetSchema.tg");
//		Activator.logInfo("Trying to load SCLWordNetSchema.tg");
		try {
			// if testCase is started, schema will be loaded from file
			if(Properties.test){
				schema = GraphIO.loadSchemaFromFile("schemata"+File.separator+"SCLWordNetSchema.tg");
			}
			// if engine is started, schema will be loaded from stream
			else{
				InputStream newStream = OWLSimilarity.class.getClassLoader().getResourceAsStream(JGraLab2OWL.SCLWORDNET_COMBIED_SCHEMA_FILE);				
				schema = GraphIO.loadSchemaFromStream(newStream);				
			}
		} catch (GraphIOException e) {
			Activator.logError("Error while loading SCLWordNetSchema.tg (GraphIO)", e);
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (Exception e) {
			Activator.logError("Error while loading SCLWordNetSchema.tg (non-GraphIO)", e);
			throw new RuntimeException(e);
		}
//		Activator.logInfo("SCLWordNetSchema.tg loaded");
		// Activator.logInfo("DLSim: SCLWordNetSchema.tg loaded");

		// Activator.logInfo(" qualified name: " + schema.getQualifiedName());

		// System.err.println("Schema from file: " + schema);
		j2o.initializeDocument();
		j2o.createOntologyHeader(schema);
		new Schema2OWL(j2o.doc, schema, edgeClasses2Properties,
				appendSuffix2EdgeClassName);
		// Activator.logInfo("Number of graphs:" + graphs.size());
		for (Graph graph : graphs) {
			// new Graph2OWLConcept(j2o.doc, graph, edgeClasses2Properties,
			// appendSuffix2EdgeClassName, pf, null); //lh namespace
			// p1=eu.redseeds...SCLSchema occurs in Tbox??
			if (!(graph == null))
				// new Graph2OWLConcept(j2o.doc, graph, edgeClasses2Properties,
				// appendSuffix2EdgeClassName, pf,
				// "http://de.uni_koblenz.jgwnl.wordnetschema.WordNetSchema#");
				new Graph2OWLConcept(j2o.doc, graph, edgeClasses2Properties,
						appendSuffix2EdgeClassName, pf, "http://"
								+ schema.getQualifiedName() + "#");
		}

		j2o.createOwlFile(j2o.doc);
		j2o.out.flush();
		j2o.out.close();
	}

}
