package eu.redseeds.sc.query.engine.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import de.uni_koblenz.jgralab.Edge;
import de.uni_koblenz.jgralab.Vertex;
import de.uni_koblenz.jgralab.schema.VertexClass;
import eu.redseeds.owl.OWLSimilarity;
import eu.redseeds.sc.current.repository.SCProject;
import eu.redseeds.sc.current.repository.SCProjectContainer;
import eu.redseeds.sc.query.engine.Activator;
import eu.redseeds.sc.query.engine.ComparisonType;
import eu.redseeds.sc.query.engine.SCQueryEngine;
import eu.redseeds.sc.query.engine.SimilarSCProject;
import eu.redseeds.sc.query.engine.SimilarityException;
import eu.redseeds.sc.query.engine.SimilarityValue;
import eu.redseeds.sc.query.engine.ukocompare.UkoCompareEngine;
import eu.redseeds.scl.SCLGraph;
import eu.redseeds.scl.rsl.rsldomainelements.domainelements.DomainElement;
import eu.redseeds.scl.rsl.rsldomainelements.terms.Term;
import eu.redseeds.scl.rsl.rsldomainelements.terms.TermHyperlink;
import eu.redseeds.scl.rsl.rsldomainelements.terms.TerminologyContainsTerm;
import eu.redseeds.scl.rsl.rslkernel.elements.HyperlinkLinksToSource;
import eu.redseeds.scl.rsl.rslkernel.elements.HyperlinkLinksToTarget;
import eu.redseeds.scl.rsl.rslkernel.elements.HyperlinkedSentence;
import eu.redseeds.scl.rsl.rslrequirementrepresentations.descriptiverequirementrepresentations.SentenceList;
import eu.redseeds.scl.rsl.rslrequirementrepresentationsentences.svosentences.ModalSVOSentence;
import eu.redseeds.scl.rsl.rslrequirementrepresentationsentences.svosentences.Predicate;
import eu.redseeds.scl.rsl.rslrequirementrepresentationsentences.svosentences.PredicateContainsTarget;
import eu.redseeds.scl.rsl.rslrequirementrepresentationsentences.svosentences.PredicateIsPartOfSource;
import eu.redseeds.scl.rsl.rslrequirementrepresentationsentences.svosentences.SVOSentence;
import eu.redseeds.scl.rsl.rslrequirementrepresentationsentences.svosentences.Subject;
import eu.redseeds.scl.rsl.rslrequirementrepresentationsentences.svosentences.SubjectContainsTarget;
import eu.redseeds.scl.rsl.rslrequirementrepresentationsentences.svosentences.SubjectIsPartOfSource;
import eu.redseeds.scl.rsl.rslrequirements.requirementsspecifications.Requirement;

public class SCQueryEngineImpl implements SCQueryEngine {

	/**
	 * Toggles if the values of UKo and UH should be combined orif only the UKo/Fraunhofer meausures hould be used 
	 */
	private static final boolean COMBINE = true;
	
	//private static final boolean REPLACE_VEDGES = false;

	
	/**
	 * Removes empty sentences and scenarios from the case before comparison
	 * 
	 * @param graph
	 */
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
			if (v instanceof SentenceList) { //each use case contains a sentence list without any sentences
				if (v.getDegree() == 1) {
					verticesToDelete.add(v);
					continue;
				}
			}
		}
		
//		//remove sentences as name of requirements
//		if (REPLACE_VEDGES)
//		for (Requirement r : graph.getRequirementVertices()) {
//			RequirementContainsName edge = r.getFirstRequirementContainsName();
//			if (edge != null) {
//				NaturalLanguageHypertextSentence sentence = (NaturalLanguageHypertextSentence) edge.getThat();
//				r.setRequirementId(sentence.getSentenceText());
//				verticesToDelete.add(sentence);
//			}
//		}
//		
//		if (REPLACE_VEDGES) {
//			//remove all domain elements & statements
//			for (DomainElement e : graph.getDomainElementVertices())
//				verticesToDelete.add(e);
//			for (DomainStatement s : graph.getDomainStatementVertices())
//				verticesToDelete.add(s);
//		}
//		
//		
		//unify duplicated terms
		int duplicatedTerms = 0;
		for (Term t : graph.getTermVertices()) {
			if (t.getDegree() == 0)
				continue;
			for (Vertex v : graph.vertices((VertexClass)t.getAttributedElementClass())) {
				if ((t == v) || (verticesToDelete.contains(v)))
					continue;
				Term t2 = (Term) v;
				if (t2.getSynonymUid() == t.getSynonymUid()) {
					Edge e = t2.getFirstEdge();
					while (e != null) {
		                if (e instanceof TerminologyContainsTerm)
		                	e.delete();
		                else 	
		                	e.setThis(t);
					//	System.out.println("Changed edge " + e + " from " + t2 + " to " + t);
						e = t2.getFirstEdge();
					}
					verticesToDelete.add(t2);
					duplicatedTerms++;
				}
			}
		}
		
		//delete unconnected subjects
		int unconnectedSubjects = 0;
		for (Subject s : graph.getSubjectVertices()) {
			SubjectIsPartOfSource sl = s.getFirstSubjectIsPartOfSource();
			SubjectContainsTarget tl = s.getFirstSubjectContainsTarget();
			if ((sl == null) || (tl == null)) {
				unconnectedSubjects++;
				verticesToDelete.add(s);
//			} else if (REPLACE_VEDGES){
//				graph.createSubjectEdge((SVOSentence)sl.getThat(), (NounPhrase)tl.getThat());
//				verticesToDelete.add(s);
			}	
		}
		
		//delete unconnected predicates
		int unconnectedPredicates = 0;
		for (Predicate p : graph.getPredicateVertices()) {
			PredicateIsPartOfSource sl = p.getFirstPredicateIsPartOfSource();
			PredicateContainsTarget tl = p.getFirstPredicateContainsTarget();
			if ((sl == null) || (tl == null)) {
				unconnectedPredicates++;
				verticesToDelete.add(p);
//			} else if (REPLACE_VEDGES){
//				graph.createPredicateEdge((SVOSentence)sl.getThat(), (VerbPhrase)tl.getThat());
//				verticesToDelete.add(p);
			}	
		}
				
		int unconnectedTermLinks = 0;
		for (TermHyperlink t : graph.getTermHyperlinkVertices()) {
			HyperlinkLinksToSource sl = t.getFirstHyperlinkLinksToSource();
			HyperlinkLinksToTarget tl = t.getFirstHyperlinkLinksToTarget();
			if ((sl == null) || (tl == null)) {
				unconnectedTermLinks++;
				verticesToDelete.add(t);
//			} else if (REPLACE_VEDGES){
//				graph.createTermLinkEdge((Phrase)sl.getThat(), (Term)tl.getThat());					
//				verticesToDelete.add(t);
			}
		}
		
		
//		//remove all phrases with only one element
//		if (REPLACE_VEDGES)
//		for (NounPhrase np : graph.getNounPhraseVertices()) {
//			Set<Term> terms = new HashSet<Term>();
//			for (TermLinkEdge e : np.getTermLinkEdgeIncidences()) {
//				terms.add((Term)e.getThat());
//			}
//			if (terms.size() == 1) {
//				verticesToDelete.add(np);
//				SubjectEdge se = np.getFirstSubjectEdge();
//				if (se != null) {
//					se.setThis((Noun)terms.toArray()[0]);
//				}
//			}
//		}
////		
//		verticesToDelete.add(graph.getFirstDomainSpecification());
//		verticesToDelete.add(graph.getFirstArchitecturalModel());
//		verticesToDelete.add(graph.getFirstDetailedDesignModel());
		
//		System.out.println("Elements removed:");
//		System.out.println("   " + duplicatedTerms + " duplicated terms");
//		System.out.println("   " + unconnectedTermLinks + " unconnected Term Links");
//		System.out.println("   " + unconnectedSubjects + " unconnectedSubjects");
//		System.out.println("   " + unconnectedPredicates + " unconnectedPredicates");
		
		for (Vertex v : verticesToDelete) {
			if (graph.containsVertex(v))
				v.delete();
		}
//		for (SCLElement v : graph.getSCLElementVertices()) {
//			v.setUid("");
//		}
//		try {
//			GraphIO.saveGraphToFile("/home/dbildh/SCs/"+graph.getFirstSoftwareCase().getName()+".tg", graph, null);
//		} catch (GraphIOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
	/**
	 * Compares two cases using UKos ReDSeeDs specific difference algorithm
	 * 
	 * @param currentCase
	 * @param pastCase
	 * @return
	 */
	private SimilarityValueUKo compareCasesWithUkoDiff(SCLGraph currentCase,
			SCLGraph pastCase, Set<Requirement> requirementsUsedForComparison,
			Set<DomainElement> domainElementsUsedForComparison,
			ComparisonType comparisonType) throws SimilarityException {
		System.out.println("Compare cases with uko diff");
		UkoCompareEngine engine = new UkoCompareEngine(currentCase, pastCase);
		SimilarityValueUKo val = engine.getCaseSimilarity(requirementsUsedForComparison, domainElementsUsedForComparison, comparisonType);
		System.out.println("Finished comparison of cases with uko diff, value " + val.getSimilarityValue());
		return val;
	}
	
	/*
	 * Compares two cases using OWL and description logics mechanisms
	 */
	private SimilarityValueUHHAdapted compareCasesWithOWL(SCLGraph currentCase,
			SCLGraph pastCase, Set<Requirement> requirementsUsedForComparison,
			Set<DomainElement> domainElementsUsedForComparison,
			ComparisonType comparisonType) {
		try {
			System.err.println("Comparing cases with owl");
			SimilarityValueUHHAdapted newSimVal = new SimilarityValueUHHAdapted(currentCase, pastCase,
					requirementsUsedForComparison, domainElementsUsedForComparison, comparisonType);
			System.err.println("Calculated similarity is : " + newSimVal.getSimilarityValue());
			return newSimVal;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}



	public SimilarityValue compareCases(SCLGraph currentCase,
			SCLGraph pastCase, Set<Requirement> requirementsUsedForComparison,
			Set<DomainElement> domainElementsForComparison,
			ComparisonType comparisonType)  {
		SimilarityValueUKo ukoVal;
		try {
			if (COMBINE) {
				Activator.logInfo( "Comparing: GraphDiff '" + currentCase + "' vs. '" + pastCase + "'" );
			}
			ukoVal = compareCasesWithUkoDiff(currentCase, pastCase,
			  	  requirementsUsedForComparison, domainElementsForComparison, comparisonType);
			if (COMBINE) {
				Activator.logInfo( "Comparing: OWL '" + currentCase + "' vs. '" + pastCase + "'" );
				SimilarityValueUHHAdapted uhVal = compareCasesWithOWL(currentCase, pastCase,
						requirementsUsedForComparison, domainElementsForComparison, comparisonType);
				return new CombinedSimilarityValue(ukoVal, uhVal);
			} else {
				System.out.println("Finished comparison");
				return ukoVal;
			}
		} catch (SimilarityException e) {
			e.printStackTrace();
			return null;
		}

	}

	


	@Override
	public List<SimilarSCProject> findCases(SCProject baseSC,
			double similarityLevel, ComparisonType comparisonType,
			Set<Requirement> requirementsToUse,
			Set<DomainElement> domainElementsToUse) {
		try {
		List<SimilarSCProject> resultList = new ArrayList<SimilarSCProject>();

		cleanGraph(baseSC.getSCLGraph());
		List<SCLGraph> graphs = new ArrayList<SCLGraph>();
        for (SCProject sc : SCProjectContainer.instance().getSCProjects()) {
                if (!sc.equals(baseSC)) {
                		cleanGraph(sc.getSCLGraph());
                        graphs.add(sc.getSCLGraph());
                }
        }    

        if (COMBINE)
        	OWLSimilarity.ensureCaseContainersForSCLGraphsInitialisation(graphs);

		
        for (SCProject sc : SCProjectContainer.instance().getSCProjects()) {
			if (!sc.equals(baseSC)) {
				Activator.logInfo( "Comparing: '" + baseSC.getName() + "' vs. '" + sc.getName() + "'" );
				SimilarityValue sv = compareCases(baseSC.getSCLGraph(), sc
						.getSCLGraph(), requirementsToUse, domainElementsToUse,
						comparisonType);
				int roundedSim = (int) (sv.getSimilarityValue() * 1000);
				int roundedLevel = ((int) (similarityLevel * 10)) * 100;
				if (roundedSim >= roundedLevel) {
					resultList.add(new SimilarSCProjectImpl(baseSC, sv, sc));
				}
			}
		}
        Activator.logInfo("Comparision finished!");
		return resultList;
		} catch (Exception ex) {
			Activator.logError("Error in SCQueryEngine: " + ex.getMessage(), ex);
			System.out.println("Exception occured at " + ex.getMessage());
			ex.printStackTrace();
		}
		return null;
	}



}
