import static junit.framework.Assert.fail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.uni_koblenz.jgralab.GraphIO;
import de.uni_koblenz.jgralab.GraphIOException;
import de.uni_koblenz.jgralab.Vertex;
import eu.redseeds.common.Constants;
import eu.redseeds.sc.current.repository.SCProject;
import eu.redseeds.sc.query.comparefunctions.AbstractCompareFunction;
import eu.redseeds.sc.query.engine.ComparisonType;
import eu.redseeds.sc.query.engine.SimilarityValue;
import eu.redseeds.sc.query.engine.impl.SCQueryEngineImpl;
import eu.redseeds.sc.query.engine.impl.SimilarityValueUKo;
import eu.redseeds.scl.SCLGraph;
import eu.redseeds.scl.SCLSchema;
import eu.redseeds.scl.rsl.rsldomainelements.terms.Term;
import eu.redseeds.scl.rsl.rslkernel.elements.ElementRepresentation;
import eu.redseeds.scl.rsl.rslkernel.elements.HyperlinkedSentence;
import eu.redseeds.scl.rsl.rslkernel.elements.RepresentableElement;
import eu.redseeds.scl.rsl.rslrequirementrepresentationsentences.svosentences.ModalSVOSentence;
import eu.redseeds.scl.rsl.rslrequirementrepresentationsentences.svosentences.SVOSentence;
import eu.redseeds.scl.sclkernel.SCLElement;

public class QueryEngineTest {

	SCQueryEngineImpl queryEngine;

	@Before
	public void startUp() {
		Constants.JGWNL_DEFAULT_ADDRESS = "rmi://helena.uni-koblenz.de/JGWNL";
		queryEngine = new eu.redseeds.sc.query.engine.impl.SCQueryEngineImpl();
		eu.redseeds.common.Activator.initExternally();
		eu.redseeds.sc.terminology.model.Activator.initExternally();
		SimilarityValueUKo.setDEBUG(true);
		AbstractCompareFunction.DEBUG = true;
	}

	@After
	public void tearDown() {
	}

	private void cleanGraph(SCLGraph graph) {
		Set<Vertex> verticesToDelete = new HashSet<Vertex>();
		for (de.uni_koblenz.jgralab.Vertex v : graph.vertices()) {
			if (v.getDegree() == 0) {
				verticesToDelete.add(v);
				continue;
			}
			if ((v instanceof HyperlinkedSentence)
					&& (!(v instanceof SVOSentence))
					&& (!(v instanceof ModalSVOSentence))) {
				HyperlinkedSentence sentence = (HyperlinkedSentence) v;
				if ((sentence.getSentenceText() == null)
						|| (sentence.getSentenceText().trim().equals(""))) {
					verticesToDelete.add(v);
					continue;
				}
			}
		}
		System.out.println("Removing " + verticesToDelete.size() + " Nodes.");
		for (Vertex v : verticesToDelete) {
			v.delete();
		}
	}

	private void compareGraphs(String graphFile1, String graphFile2) {
		SCLGraph graph1 = null;
		SCLGraph graph2 = null;
		SCLSchema schema = SCLSchema.instance();
		try {
			graph1 = (SCLGraph) GraphIO.loadGraphFromFile(graphFile1, schema,
					null);
			graph2 = (SCLGraph) GraphIO.loadGraphFromFile(graphFile2, schema,
					null);
			cleanGraph(graph1);
			cleanGraph(graph2);
		} catch (GraphIOException ex) {
			ex.printStackTrace();
			fail();
		}
		SimilarityValue sim = queryEngine.compareCases(graph1, graph2, null, null, ComparisonType.REQUIREMENTS_AND_DOMAIN);
		System.out.println("Similarity of both cases:");
		System.out.println("--------------------------");
		System.out.println(graphFile1 + " <=> " + graphFile2);
		// System.out.println(sim);

		// System.out.println();
		// System.out.println("Elements in first graph:");
		// System.out.println("------------------------");
		// int total = printStats(collectStats(graph1));
		// System.out.println("Total: " + total);
		//
		// System.out.println();
		// System.out.println("Elements in second graph:");
		// System.out.println("-------------------------");
		// total = printStats(collectStats(graph2));
		// System.out.println("Total: " + total);
	}

	private int printStats(HashMap<String, Integer> m) {
		int total = 0;
		for (Entry<String, Integer> e : m.entrySet()) {
			System.out.println(e.getKey() + ": \t" + e.getValue());
			total += e.getValue();
		}
		return total;
	}

	private HashMap<String, Integer> collectStats(SCLGraph graph) {
		HashMap<String, Integer> m = new HashMap<String, Integer>();
		for (SCLElement se : graph.getSCLElementVertices()) {
			String s = null;
			if (se instanceof RepresentableElement
					|| se instanceof ElementRepresentation
					|| se instanceof HyperlinkedSentence || se instanceof Term) {
				s = se.getM1Class().getSimpleName();
				if (m.containsKey(s)) {
					m.put(s, m.get(s) + 1);
				} else {
					m.put(s, 1);
				}
			}
		}
		return m;
	}

	@Test
	public void testCompare() {
		SCProject.setGraphImplementationClasses();
		List<String> sclgraphs = new ArrayList<String>();

		sclgraphs
				.add("C:\\Dokumente und Einstellungen\\elkennung\\Eigene Dateien\\ivmx-cases\\AS\\European Funding Administration System\\sclgraph.tg");
		sclgraphs
				.add("C:\\Dokumente und Einstellungen\\elkennung\\Eigene Dateien\\ivmx-cases\\AS\\European Funding Administration System Duplicate\\sclgraph.tg");
		sclgraphs
				.add("C:\\Dokumente und Einstellungen\\elkennung\\Eigene Dateien\\ivmx-cases\\AS\\Financial Contracts Management System\\sclgraph.tg");
		sclgraphs
				.add("C:\\Dokumente und Einstellungen\\elkennung\\Eigene Dateien\\ivmx-cases\\AS\\Financial Contracts Management System Duplicate\\sclgraph.tg");
		sclgraphs
				.add("C:\\Dokumente und Einstellungen\\elkennung\\Eigene Dateien\\ivmx-cases\\CS\\ProcurementSystem\\sclgraph.tg");
		sclgraphs
				.add("C:\\Dokumente und Einstellungen\\elkennung\\Eigene Dateien\\ivmx-cases\\CS\\ProcurementSystem 2\\sclgraph.tg");
		sclgraphs
				.add("C:\\Dokumente und Einstellungen\\elkennung\\Eigene Dateien\\ivmx-cases\\CS\\StockControlSystem\\sclgraph.tg");
		sclgraphs
				.add("C:\\Dokumente und Einstellungen\\elkennung\\Eigene Dateien\\ivmx-cases\\CS\\StockControlSystem 2\\sclgraph.tg");
		sclgraphs
				.add("C:\\Dokumente und Einstellungen\\elkennung\\Eigene Dateien\\ivmx-cases\\PRODV\\GIS4EmergencyResponse\\sclgraph.tg");
		sclgraphs
				.add("C:\\Dokumente und Einstellungen\\elkennung\\Eigene Dateien\\ivmx-cases\\PRODV\\GIS4EmergencyResponseDuplicate\\sclgraph.tg");
		sclgraphs
				.add("C:\\Dokumente und Einstellungen\\elkennung\\Eigene Dateien\\ivmx-cases\\PRODV\\GIS4ForestryApplications\\sclgraph.tg");
		sclgraphs
				.add("C:\\Dokumente und Einstellungen\\elkennung\\Eigene Dateien\\ivmx-cases\\PRODV\\GIS4ForestryApplicationsDuplicate\\sclgraph.tg");
		sclgraphs
				.add("C:\\Dokumente und Einstellungen\\elkennung\\Eigene Dateien\\ivmx-cases\\IVMX\\Internet Banking System\\sclgraph.tg");
		sclgraphs
				.add("C:\\Dokumente und Einstellungen\\elkennung\\Eigene Dateien\\ivmx-cases\\IVMX\\Internet Banking System 2\\sclgraph.tg");
		sclgraphs
				.add("C:\\Dokumente und Einstellungen\\elkennung\\Eigene Dateien\\ivmx-cases\\IVMX\\Investment Funds\\sclgraph.tg");
		sclgraphs
				.add("C:\\Dokumente und Einstellungen\\elkennung\\Eigene Dateien\\ivmx-cases\\IVMX\\Investment Funds 2\\sclgraph.tg");
		sclgraphs
				.add("C:\\Dokumente und Einstellungen\\elkennung\\Eigene Dateien\\ivmx-cases\\IVMX\\Autodealing\\sclgraph.tg");

		long startTime = System.currentTimeMillis();

		for (String currentFile : sclgraphs) {
			for (String pastFile : sclgraphs) {
				if (!currentFile.equals(pastFile)) {
					System.out.println("Comparing graphs:\n\t" + currentFile
							+ "\n\t" + pastFile);
					compareGraphs(currentFile, pastFile);
				}
			}
		}

		System.out.println("Finished comparing all graphs.  It took "
				+ ((System.currentTimeMillis() - startTime) / 1000.0)
				+ " secs.");
		AbstractCompareFunction.printComputationTimes();
	}
}
