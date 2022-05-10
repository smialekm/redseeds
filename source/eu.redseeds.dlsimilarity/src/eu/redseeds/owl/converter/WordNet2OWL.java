package eu.redseeds.owl.converter;

import java.io.File;
import java.net.URI;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Vector;

import de.uni_koblenz.jgwnl.info.POS;
import de.uni_koblenz.jgwnl.info.SemanticRelationshipInfo;
import de.uni_koblenz.jgwnl.info.SemanticRelationshipType;
import de.uni_koblenz.jgwnl.info.SynonymInfo;
import de.uni_koblenz.jgwnl.info.SynsetInfo;
import edu.stanford.smi.protege.exception.OntologyException;
import edu.stanford.smi.protegex.owl.ProtegeOWL;
import edu.stanford.smi.protegex.owl.jena.JenaOWLModel;
import edu.stanford.smi.protegex.owl.model.OWLDatatypeProperty;
import edu.stanford.smi.protegex.owl.model.OWLIntersectionClass;
import edu.stanford.smi.protegex.owl.model.OWLNamedClass;
import edu.stanford.smi.protegex.owl.model.OWLObjectProperty;
import edu.stanford.smi.protegex.owl.model.OWLSomeValuesFrom;
import edu.stanford.smi.protegex.owl.model.OWLUnionClass;
import edu.stanford.smi.protegex.owl.model.RDFResource;
import edu.stanford.smi.protegex.owl.model.RDFSClass;
import edu.stanford.smi.protegex.owl.model.RDFSDatatype;
import eu.redseeds.owl.Activator;
import eu.redseeds.owl.Properties;
import eu.redseeds.owl.SupportFunctions;
import eu.redseeds.scl.rsl.rsldomainelements.actors.Actor;
import eu.redseeds.scl.rsl.rsldomainelements.notions.DomainStatement;
import eu.redseeds.scl.rsl.rsldomainelements.notions.Notion;
import eu.redseeds.scl.rsl.rsldomainelements.phrases.NounPhrase;
import eu.redseeds.scl.rsl.rsldomainelements.phrases.VerbPhrase;
import eu.redseeds.scl.rsl.rsldomainelements.systemelements.SystemElement;
import eu.redseeds.scl.rsl.rsldomainelements.terms.Determiner;
import eu.redseeds.scl.rsl.rsldomainelements.terms.Modifier;
import eu.redseeds.scl.rsl.rsldomainelements.terms.Noun;
import eu.redseeds.scl.rsl.rsldomainelements.terms.Preposition;
import eu.redseeds.scl.rsl.rsldomainelements.terms.Term;
import eu.redseeds.scl.rsl.rsldomainelements.terms.Verb;
import eu.redseeds.scl.sclkernel.SCLElement;
import eu.redseeds.wrapper.SoftwareCaseLoader;
import eu.redseeds.wrapper.WordNetQuery;

/**
 * This class provides methods for converting a TGraph into an OWL file.
 * Coverting all the classes and / or instances is done with the
 * <code>JGraLab2OWL</code> class but this class fetches all WordNet Terms
 * from the converted software cases and creates the corresponding OWL classes
 * and instances.
 * 
 * @author Thorsten Krebs
 */
public class WordNet2OWL {

	// WORDNET SCHEMA
	public static final String wn20schema = Properties.WN20SCHEMA;

	// Synonyms
	public static final String ADJECTIVE_SYNONYM = "dictionaryelements.AdjectiveSynonym";
	public static final String ADVERB_SYNONYM = "dictionaryelements.AdverbSynonym";
	public static final String NOUN_SYNONYM = "dictionaryelements.NounSynonym";
	public static final String VERB_SYNONYM = "dictionaryelements.VerbSynonym";
	public static final String PREPOSITION_SYNONYM = "dictionaryelements.PrepositionSynonym";
	public static final String DETERMINER_SYNONYM = "dictionaryelements.DeterminerSynonym";
	public static final String CONDITIONAL_CONJUNCTION_SYNONYM = "dictionaryelements.ConditionalConjunctionSynonym";
	// Synsets
	public static final String SYNSET = "dictionaryelements.Synset";
	public static final String ADJECTIVE_SYNSET = "dictionaryelements.AdjectiveSynset";
	public static final String ADVERB_SYNSET = "dictionaryelements.AdverbSynset";
	public static final String NOUN_SYNSET = "dictionaryelements.NounSynset";
	public static final String VERB_SYNSET = "dictionaryelements.VerbSynset";
	public static final String PREPOSITION_SYNSET = "dictionaryelements.PrepositionSynset";
	public static final String DETERMINER_SYNSET = "dictionaryelements.DeterminerSynset";
	public static final String CONDITIONAL_CONJUNCTION_SYNSET = "dictionaryelements.ConditionalConjunctionSynset";
	// Properties
	public static final String SYNONYM_HAS_SPELLING = "dictionaryelements.SynonymHasSpelling";
	public static final String HAS_HYPONYM = "relationships.HasHyponym";
	public static final String HAS_HYPONYM_OF = "relationships.HasHyponym-of";
	public static final String HAS_SYNONYM = "dictionaryelements.HasSynonym";
	public static final String CONTAINS_SYNONYM = "dictionaryelements.ContainsSynonym";
	public static final String CONTAINS_SYNONYM_OF = "dictionaryelements.ContainsSynonym-of";
	public static final String TERM_LINKS_2_WORDNET = "rsl.rsldomainelements.terms.TermLinksToWordNetEntry";
	public static final String TERM_LINKS_2_WORDNET_OF = "rsl.rsldomainelements.terms.TermLinksToWordNetEntry-of";

	private static final String WORDNET_WARNING = "  (This happens when the user adds a term to a local word net library but uses another word net library!)";

	private WordNetQuery wn;

	private JenaOWLModel ontology;
//	private Project project;
	private OWLNamedClass adjectiveSynonymClass;
	private OWLNamedClass adverbSynonymClass;
	private OWLNamedClass nounSynonymClass;
	private OWLNamedClass verbSynonymClass;
	private OWLNamedClass prepositionSynonymClass;
	private OWLNamedClass determinerSynonymClass;
	private OWLNamedClass conditionalConjunctionSynonymClass;
	private OWLNamedClass synsetClass;
	private OWLNamedClass adjectiveSynsetClass;
	private OWLNamedClass adverbSynsetClass;
	private OWLNamedClass nounSynsetClass;
	private OWLNamedClass verbSynsetClass;
	private OWLNamedClass prepositionSynsetClass;
	private OWLNamedClass determinerSynsetClass;
	private OWLNamedClass conditionalConjunctionSynsetClass;
	private OWLDatatypeProperty synsetRepresentsTermProperty;
	// private OWLDatatypeProperty spellingProperty;
	// private OWLObjectProperty hasSynonymProperty;
	// private OWLObjectProperty containsSynonymProperty;
	// private OWLObjectProperty containsSynonymOfProperty;

	// vector containing the owl classes of those word types (e.g. noun, verb,
	// determiner etc.) that should be included in TBox
	private Vector<OWLNamedClass> relevantWordTypesOWLClasses = new Vector<OWLNamedClass>();

	// vector containing the owl classes of all upper model concepts
	private Vector<OWLNamedClass> upperModelConcepts = new Vector<OWLNamedClass>();

	/**
	 * Constructor. Creates an instance of the <code>WordNetQuery</code> and
	 * loads the OWL ontology that is contained within the file with the given
	 * name.
	 * 
	 * @param wn
	 *            An instance of the <code>WordNetQuery</code> class.
	 * @param wnSchemaFileName
	 *            The name of the file that contains the terminological
	 *            knowledge and to which the OWL concepts representing WordNet
	 *            terms will be added.
	 */
	public WordNet2OWL(WordNetQuery wn, String wnSchemaFileName) {
		this.wn = wn;

		try {
			Properties.printHeapSize("Before Ontology file");
			// Activator.logInfo(" Before creating ontology from file
			// "+fileName2URIString(wnSchemaFileName)+"...");
			Properties.printHeapSize("Before jenaModel");

			// Problem: how to initialize ProtegeOWL?
			ontology = ProtegeOWL
					.createJenaOWLModelFromURI(fileName2URIString(wnSchemaFileName));
			// not used
			// project = ontology.getProject();
			// ProtegeOWL.initProject(project, wnSchemaFileName, "");

			Activator.logInfo(" ...creating ontology finished!");
			Properties.printHeapSize("After jenaModel");
			ontology.getNamespaceManager().setDefaultNamespace(wn20schema);
			Properties.printHeapSize("Before protege");
			// Activator.logInfo(" Plugin protege: "+
			// ProtegeOWL.getPluginFolder().toString());

			// Find the required CLASSES
			// Synonyms
			adjectiveSynonymClass = ontology.getOWLNamedClass(ADJECTIVE_SYNONYM);
			adverbSynonymClass = ontology.getOWLNamedClass(ADVERB_SYNONYM);
			nounSynonymClass = ontology.getOWLNamedClass(NOUN_SYNONYM);
			verbSynonymClass = ontology.getOWLNamedClass(VERB_SYNONYM);
			prepositionSynonymClass = ontology.getOWLNamedClass(PREPOSITION_SYNONYM);
			determinerSynonymClass = ontology.getOWLNamedClass(DETERMINER_SYNONYM);
			conditionalConjunctionSynonymClass = ontology.getOWLNamedClass(CONDITIONAL_CONJUNCTION_SYNONYM);
			// Synsets
			synsetClass = ontology.getOWLNamedClass(SYNSET);
			adjectiveSynsetClass = ontology.getOWLNamedClass(ADJECTIVE_SYNSET);
			adverbSynsetClass = ontology.getOWLNamedClass(ADVERB_SYNSET);
			nounSynsetClass = ontology.getOWLNamedClass(NOUN_SYNSET);
			verbSynsetClass = ontology.getOWLNamedClass(VERB_SYNSET);
			prepositionSynsetClass = ontology.getOWLNamedClass(PREPOSITION_SYNSET);
			determinerSynsetClass = ontology.getOWLNamedClass(DETERMINER_SYNSET);
			conditionalConjunctionSynsetClass = ontology.getOWLNamedClass(CONDITIONAL_CONJUNCTION_SYNSET);
			// Find the required ROLES
			synsetRepresentsTermProperty = ontology.getOWLDatatypeProperty(Properties.SYNSET_REPRESENTS_TERM_PROPERTY);
			
			if(synsetRepresentsTermProperty == null) System.err.println(" NO SYNSET REPRESENTS TERM PROPERTY FOUND IN ONTOLOGY!");
			else System.err.println(" SYNSET REPRESENTS TERM PROPERTY FOUND: "+synsetRepresentsTermProperty.getName());
			
			// spellingProperty =
			// ontology.getOWLDatatypeProperty(SYNONYM_HAS_SPELLING);
			// hasSynonymProperty = ontology.getOWLObjectProperty(HAS_SYNONYM);
			// containsSynonymProperty =
			// ontology.getOWLObjectProperty(CONTAINS_SYNONYM);
			// containsSynonymOfProperty =
			// ontology.getOWLObjectProperty(CONTAINS_SYNONYM_OF);

			gatherRelevantWordTypeClasses();
			computeUpperModelConcepts();

		} catch (OntologyException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} catch (Error e) {
			e.printStackTrace();
			System.exit(0);
		}

	}

	/**
	 * Function to retrieve ontology.
	 */
	public JenaOWLModel getOntology() {
		return ontology;
	}

	public void clearOntology() {
		Activator.logInfo("deleting ontology for next run:" + ontology.toString());
		ontology.close();
		ontology = null;
	}

	/**
	 * Adds a synset and all relevant information to the TBox. The relevant
	 * information is all parent synsets up of the given synset, to the root
	 * synset, and all synonyms that belong to the synsets.
	 * 
	 * @param synset
	 *            The <code>SynsetInfo</code> that contains all information
	 *            about the synset to be added.
	 */
	private void addSynsetAndAllRelevantInformation(SynsetInfo synset) {
		long synsetID = synset.getUid();
		int countHyponyms = 0;
		// look for parents		
		for (SemanticRelationshipInfo sr : wn.getSemanticRelationshipInfos(synsetID)) {
			if ((sr.getType().equals(SemanticRelationshipType.HAS_HYPONYM) || sr.getType().equals(
							SemanticRelationshipType.HAS_INSTANCE_HYPONYM))&& sr.getTarget() == synsetID) {
				SynsetInfo parentSynset = wn.retrieveSynset(sr.getSource());

				String parentSynsetClassName = constructSynsetClassnameFromSynset(parentSynset);
				OWLNamedClass parentSynsetClass = ontology.getOWLNamedClass(parentSynsetClassName);
				
				long parentID = parentSynset.getUid();
				// second condition parentID!=synsetID is to prevent endless
				// recursion, where parent synset is same as synset
				if (parentSynsetClass == null && parentID != synsetID) {
					// if (!synsets.containsKey(parentID)) {
					// Activator.logInfo(" adding parent synset with id
					// "+parentID+" of synset with id "+synsetID);
					addSynsetAndAllRelevantInformation(parentSynset);
				}

				// TESTING
				// if(parentID == synsetID){
				// Activator.logInfo(" **********");
				// Activator.logInfo(" synset "+synsetID+" and parent synset
				// "+parentID+" are connected over: ");
				// Activator.logInfo(" "+sr.getType().getName(sr.getType()));
				// Activator.logInfo(" **********");
				// }

				// addSynset(parentSynset, synset);
				addSynsetWithoutSynonyms(parentSynset, synset);						
			}
		}
		// This synset has no parent entry in WordNet
		if (countHyponyms == 0) {
			// addSynset(null, synset);
			addSynsetWithoutSynonyms(null, synset);
		}
	}

	/**
	 * Adds a given synset to a given parent synset without adding synsets
	 * synonyms.
	 * 
	 * @param parentSynset
	 * @param synset
	 */

	private void addSynsetWithoutSynonyms(SynsetInfo parentSynset,
			SynsetInfo synset) {
		// long synsetId = synset.getUid();

		OWLNamedClass synsetClass = null;
		OWLNamedClass parentClass = null;

		boolean relevantWordType = true;

		// use new function
		String synsetClassName = constructSynsetClassnameFromSynset(synset);
		synsetClass = ontology.getOWLNamedClass(synsetClassName);

		if (synsetClass == null) {
			POS pos = synset.getPos();
			// Add synset
			if (parentSynset == null) {
				if (pos.equals(POS.ADJECTIVE)) {
					parentClass = adjectiveSynsetClass;
				} else if (pos.equals(POS.ADVERB)) {
					parentClass = adverbSynsetClass;
				} else if (pos.equals(POS.NOUN)) {
					parentClass = nounSynsetClass;
				} else if (pos.equals(POS.VERB)) {
					parentClass = verbSynsetClass;
				} else if (pos.equals(POS.PREPOSITION)) {
					parentClass = prepositionSynsetClass;
				} else if (pos.equals(POS.DETERMINER)) {
					parentClass = determinerSynsetClass;
				} else if (pos.equals(POS.CONDITIONAL_CONJUNCTION)) {
					parentClass = conditionalConjunctionSynsetClass;
				}
				relevantWordType = relevantWordTypesOWLClasses
						.contains(parentClass);
			} else {
				// use new function!
				String parentSynsetClassName = constructSynsetClassnameFromSynset(parentSynset);
				OWLNamedClass upperModelClass = retrieveUpperModelConceptOfSynsetType(parentSynset);
				if (relevantWordTypesOWLClasses.contains(upperModelClass)) {
					parentClass = ontology
							.getOWLNamedClass(parentSynsetClassName);
					// create owl class for parent synset if it doesn't exist
					if (parentClass == null) {
						Activator.logInfo( " No parent class for parent class found: Adding parent synset class as child to upperModel class "+upperModelClass.getName());
						parentClass = addSynsetToParentSynset(
								parentSynsetClassName, upperModelClass);
					} else {
						Activator.logInfo(" Found parent class: "+parentSynsetClassName);
					}
				} else {
					relevantWordType = false;
				}				
			}
			// only add owl class for synset if it's parents are relevant for
			// similarity measure!
			// this is false, if a term was linked incorrectly to a phrase, e.g.
			// "determiner" linked as "modifier"
			if (relevantWordType) {
				synsetClass = addSynsetToParentSynset(synsetClassName, parentClass);
			} else {
				Activator.logWarning(" WARNING: Synset " + synsetClassName + " was not added to TBox, because it's parent seems to be irrelevant for similarity measure!");
				Activator.logWarning("   -> " + synsetClassName + " seems to be linked incorrectly in graph!");
			}
		}
		
		// add term name to synset for debugging
		if(Properties.ADD_TERM_NAME_TO_SYNSET){
			SynonymInfo firstSynonym = (synset.getSynonymInfos()).get(0);
			String synsetTermName = null;
			if(firstSynonym != null){				
				synsetTermName = firstSynonym.getLemma();
//				System.out.println(" synset represents synonym for term "+synsetTermName);
				if(synsetTermName != null){
					addSynsetRepresentsTermPropertyToSynset(synsetClass, synsetTermName);
				}
			}
		}
	}

	/**
	 * Adds a given synset to a given parent synset.
	 * 
	 * @param parentSynset
	 *            The parent <code>SynsetInfo</code> of which the synset to be
	 *            added is a child synset.
	 * @param synset
	 *            The <code>SynsetInfo</code> that contains all information
	 *            about the synset to be added.
	 * @deprecated
	 */
	@SuppressWarnings("unused")
	private void addSynset(SynsetInfo parentSynset, SynsetInfo synset) {
		long synsetId = synset.getUid();

		OWLNamedClass synsetClass = null;
		OWLNamedClass parentClass = null;

		// use new function
		String synsetClassName = constructSynsetClassnameFromSynset(synset);
		synsetClass = ontology.getOWLNamedClass(synsetClassName);

		if (synsetClass == null) {
			// if (!synsets.containsKey(synsetId)) {
			POS pos = synset.getPos();
			// Add synset
			if (parentSynset == null) {
				if (pos.equals(POS.ADJECTIVE)) {
					parentClass = adjectiveSynsetClass;
				} else if (pos.equals(POS.ADVERB)) {
					parentClass = adverbSynsetClass;
				} else if (pos.equals(POS.NOUN)) {
					parentClass = nounSynsetClass;
				} else if (pos.equals(POS.VERB)) {
					parentClass = verbSynsetClass;
				} else if (pos.equals(POS.PREPOSITION)) {
					parentClass = prepositionSynsetClass;
				} else if (pos.equals(POS.DETERMINER)) {
					parentClass = determinerSynsetClass;
				} else if (pos.equals(POS.CONDITIONAL_CONJUNCTION)) {
					parentClass = conditionalConjunctionSynsetClass;
				}
			} else {
				// use new function!
				String parentSynsetClassName = constructSynsetClassnameFromSynset(parentSynset);
				parentClass = ontology.getOWLNamedClass(parentSynsetClassName);
				OWLNamedClass upperModelClass = retrieveUpperModelConceptOfSynsetType(parentSynset);
				// create owl class for parent synset if it doesn't exist
				if (parentClass == null) {
					parentClass = addSynsetToParentSynset(
							parentSynsetClassName, upperModelClass);
				}
				/*
				 * long parentId = parentSynset.getUid(); parentClass =
				 * synsets.get(parentId); Activator.logInfo(" Retrieved parent
				 * class: "+parentClass.getName()); parentClass =
				 * ontology.getOWLNamedClass(parentClass.getName()); // This
				 * happens when the uppermost level is reached, e.g. "entity"
				 * for nouns... if (parentClass == null) { if
				 * (pos.equals(POS.ADJECTIVE)) { parentClass =
				 * addSynsetToParentSynset(ADJECTIVE_SYNSET + "." + parentId,
				 * adjectiveSynsetClass); //parentClass =
				 * ontology.createOWLNamedSubclass(ADJECTIVE_SYNSET + "." +
				 * parentId, adjectiveSynsetClass); } else if
				 * (pos.equals(POS.ADVERB)) { parentClass =
				 * addSynsetToParentSynset(ADVERB_SYNSET + "." + parentId,
				 * adverbSynsetClass); } else if (pos.equals(POS.NOUN)) {
				 * parentClass = addSynsetToParentSynset(NOUN_SYNSET + "." +
				 * parentId, nounSynsetClass); } else if (pos.equals(POS.VERB)) {
				 * parentClass = addSynsetToParentSynset(VERB_SYNSET + "." +
				 * parentId, verbSynsetClass); } else if
				 * (pos.equals(POS.PREPOSITION)) { parentClass =
				 * addSynsetToParentSynset(PREPOSITION_SYNSET + "." + parentId,
				 * prepositionSynsetClass); } else if
				 * (pos.equals(POS.DETERMINER)) { parentClass =
				 * addSynsetToParentSynset(DETERMINER_SYNSET + "." + parentId,
				 * determinerSynsetClass); } else if
				 * (pos.equals(POS.CONDITIONAL_CONJUNCTION)) { parentClass =
				 * addSynsetToParentSynset(CONDITIONAL_CONJUNCTION_SYNSET + "." +
				 * parentId, conditionalConjunctionSynsetClass); }
				 * parentClass.addPropertyValue(glossProperty,
				 * parentSynset.getGloss()); synsets.put(parentId, parentClass); }
				 */
			}
			synsetClass = addSynsetToParentSynset(synsetClassName, parentClass);
			/*
			 * // TESTING : SHOULD REPLACE EXISTING SYNONYM WITH SYNSET HERE!
			 * String retrievedsynonymClassName =
			 * constructSynonymClassnameFromSynonym(si); OWLNamedClass
			 * retrievedsynonymClass =
			 * ontology.getOWLNamedClass(retrievedsynonymClassName);
			 * if(retrievedsynonymClass == null){ Activator.logInfo(" WARNING:
			 * Synonym doesn't exist yet!"); } else { Activator.logInfo(" FOUND
			 * synonym: "+retrievedsynonymClass.getName()); RDFSClass def =
			 * retrievedsynonymClass.getDefinition(); Activator.logInfo(" with
			 * definition: "+def.toString()); } // TESTING ENDS HERE!
			 */
			/*
			 * if (pos.equals(POS.ADJECTIVE)) { synsetClass =
			 * addSynsetToParentSynset(ADJECTIVE_SYNSET + "." + synsetId,
			 * parentClass); //synsetClass =
			 * ontology.createOWLNamedSubclass(ADJECTIVE_SYNSET + "." +
			 * synsetId, parentClass); } else if (pos.equals(POS.ADVERB)) {
			 * synsetClass = addSynsetToParentSynset(ADVERB_SYNSET + "." +
			 * synsetId, parentClass); } else if (pos.equals(POS.NOUN)) {
			 * synsetClass = addSynsetToParentSynset(NOUN_SYNSET + "." +
			 * synsetId, parentClass); } else if (pos.equals(POS.VERB)) {
			 * synsetClass = addSynsetToParentSynset(VERB_SYNSET + "." +
			 * synsetId, parentClass); } else if (pos.equals(POS.PREPOSITION)) {
			 * synsetClass = addSynsetToParentSynset(PREPOSITION_SYNSET + "." +
			 * synsetId, parentClass); } else if (pos.equals(POS.DETERMINER)) {
			 * synsetClass = addSynsetToParentSynset(DETERMINER_SYNSET + "." +
			 * synsetId, parentClass); } else if
			 * (pos.equals(POS.CONDITIONAL_CONJUNCTION)) { synsetClass =
			 * addSynsetToParentSynset(CONDITIONAL_CONJUNCTION_SYNSET + "." +
			 * synsetId, parentClass); }
			 */
			/*
			 * List<SynonymInfo> synonyms = synset.getSynonymInfos();
			 * if(synonyms.size() == 0){ Activator.logInfo(" WARNING:
			 * Synset"+synsetClass.getName()+" has no synonyms!"); }
			 * 
			 * for (SynonymInfo synonym : synset.getSynonymInfos()){ long
			 * synonymId = synonym.getUid(); OWLNamedClass synonymClass = null;
			 * String synonymClassName =
			 * constructSynonymClassnameFromSynonym(synonym); synonymClass =
			 * ontology.getOWLNamedClass(synonymClassName);
			 * 
			 * OWLNamedClass upperModelClass =
			 * retrieveUpperModelConceptOfSynonymType(synonym); synonymClass =
			 * addSynonymToSynset(synonymClassName, upperModelClass,
			 * synsetClass); // set the HAS_SYNONYM Properties between synonyms
			 * 
			 * for (SynonymInfo si : synset.getSynonymInfos()) { OWLNamedClass
			 * siblingSynonymClass = null; String siblingSynonymClassName =
			 * constructSynonymClassnameFromSynonym(si); siblingSynonymClass =
			 * ontology.getOWLNamedClass(siblingSynonymClassName); long siId =
			 * si.getUid(); if (synonymId != siId && siblingSynonymClass !=
			 * null) { addNecessaryObjectProperty(synonymClass,
			 * hasSynonymProperty, siblingSynonymClass); } }
			 * 
			 * //} }
			 */

			/*
			 * //fix owl syntax of datatype property glossProperty
			 * //synsetClass.addPropertyValue(glossProperty, synset.getGloss());
			 * synsets.put(synsetId, synsetClass); Activator.logInfo("");
			 * Activator.logInfo("Adding synonyms from synset:
			 * "+synset.toString()); // Add synonyms for (SynonymInfo synonym :
			 * synset.getSynonymInfos()) { long synonymId = synonym.getUid(); if
			 * (!synonyms.containsKey(synonymId)) { // We assume all synonyms
			 * have the same POS as their synset! OWLNamedClass synonymClass =
			 * null; if (pos.equals(POS.ADJECTIVE)) {
			 * Activator.logInfo("Adjective-Synonym: "+synonym.toString());
			 * synonymClass = addSynonymToSynset(ADJECTIVE_SYNONYM + "." +
			 * synonymId, adjectiveSynonymClass, synsetClass); } else if
			 * (pos.equals(POS.ADVERB)) { Activator.logInfo("Adverb-Synonym:
			 * "+synonym.toString()); synonymClass =
			 * addSynonymToSynset(ADVERB_SYNONYM + "." + synonymId,
			 * adverbSynonymClass, synsetClass); //synonymClass =
			 * ontology.createOWLNamedSubclass(ADVERB_SYNONYM + "." + synonymId,
			 * adverbSynonymClass); } else if (pos.equals(POS.NOUN)) {
			 * Activator.logInfo("Noun-Synonym: "+synonym.toString());
			 * synonymClass = addSynonymToSynset(NOUN_SYNONYM + "." + synonymId,
			 * nounSynonymClass, synsetClass); } else if (pos.equals(POS.VERB)) {
			 * Activator.logInfo("Verb-Synonym: "+synonym.toString());
			 * synonymClass = addSynonymToSynset(VERB_SYNONYM + "." + synonymId,
			 * verbSynonymClass, synsetClass); } else if
			 * (pos.equals(POS.PREPOSITION)) {
			 * Activator.logInfo("Preposition-Synonym: "+synonym.toString());
			 * synonymClass = addSynonymToSynset(PREPOSITION_SYNONYM + "." +
			 * synonymId, prepositionSynonymClass, synsetClass); } else if
			 * (pos.equals(POS.DETERMINER)) {
			 * Activator.logInfo("Determiner-Synonym: "+synonym.toString());
			 * synonymClass = addSynonymToSynset(DETERMINER_SYNONYM + "." +
			 * synonymId, determinerSynonymClass, synsetClass); } else if
			 * (pos.equals(POS.CONDITIONAL_CONJUNCTION)) {
			 * Activator.logInfo("Conditional-Conjunction-Synonym:
			 * "+synonym.toString()); synonymClass =
			 * addSynonymToSynset(CONDITIONAL_CONJUNCTION_SYNONYM + "." +
			 * synonymId, conditionalConjunctionSynonymClass, synsetClass); }
			 * synonyms.put(synonymId, synonymClass); // fix owl syntax
			 * of datatype property glossProperty // set the
			 * SYNONYM_HAS_SPELLING Property //
			 * synonymClass.addPropertyValue(spellingProperty,
			 * synonym.getLemma()); // set the HAS_SYNONYM Properties between
			 * synonyms for (SynonymInfo si : synset.getSynonymInfos()) { long
			 * siId = si.getUid(); if ((synonymId != siId) &&
			 * synonyms.containsKey(siId)) { OWLNamedClass siClass =
			 * synonyms.get(siId); addNecessaryObjectProperty(synonymClass,
			 * hasSynonymProperty, siClass); //if
			 * (!synonymClass.hasPropertyValue(hasSynonymProperty, siClass)) //
			 * synonymClass.addPropertyValue(hasSynonymProperty, siClass); //if
			 * (!siClass.hasPropertyValue(hasSynonymProperty, synonymClass)) //
			 * siClass.addPropertyValue(hasSynonymProperty, synonymClass); } } } }
			 */
		}
	}

	/**
	 * Adds instances of a given synonym and its synset.
	 * 
	 * @param synset
	 *            The <code>SynsetInfo</code> from which an instance shall be
	 *            added.
	 * @deprecated
	 */

	private void addSynonymInstance(SynonymInfo synonym) {
		/*
		 * POS pos = synonym.getPos(); // Add synonym... long synonymId =
		 * synonym.getUid(); if(!synonym_instances.containsKey(synonymId)){
		 * OWLNamedClass synonymClass = null; OWLIndividual synonymInstance =
		 * null; if (pos.equals(POS.ADJECTIVE)) {
		 * //Activator.logInfo("Adjective-Synonym-Instanz:
		 * "+synonym.toString()+" hinzugef�gt!"); synonymClass =
		 * ontology.getOWLNamedClass(ADJECTIVE_SYNONYM + "." + synonymId);
		 * synonymInstance = synonymClass.createOWLIndividual(ADJECTIVE_SYNONYM +
		 * "." + synonymId + "_instance"); } else if (pos.equals(POS.ADVERB)) {
		 * //Activator.logInfo("Adverb-Synonym-Instanz: "+synonym.toString()+"
		 * hinzugef�gt!"); synonymClass =
		 * ontology.getOWLNamedClass(ADVERB_SYNONYM + "." + synonymId);
		 * synonymInstance = synonymClass.createOWLIndividual(ADVERB_SYNONYM +
		 * "." + synonymId + "_instance"); } else if (pos.equals(POS.NOUN)) {
		 * //Activator.logInfo("Noun-Synonym-Instanz: "+synonym.toString()+"
		 * hinzugef�gt!"); synonymClass =
		 * ontology.getOWLNamedClass(NOUN_SYNONYM + "." + synonymId);
		 * synonymInstance = synonymClass.createOWLIndividual(NOUN_SYNONYM + "." +
		 * synonymId + "_instance"); } else if (pos.equals(POS.VERB)) {
		 * //Activator.logInfo("Verb-Synonym-Instanz: "+synonym.toString()+"
		 * hinzugef�gt!"); synonymClass =
		 * ontology.getOWLNamedClass(VERB_SYNONYM + "." + synonymId);
		 * synonymInstance = synonymClass.createOWLIndividual(VERB_SYNONYM + "." +
		 * synonymId + "_instance"); } else if (pos.equals(POS.PREPOSITION)) {
		 * //Activator.logInfo("Preposition-Synonym-Instanz:
		 * "+synonym.toString()+" hinzugef�gt!"); synonymClass =
		 * ontology.getOWLNamedClass(PREPOSITION_SYNONYM + "." + synonymId);
		 * synonymInstance =
		 * synonymClass.createOWLIndividual(PREPOSITION_SYNONYM + "." +
		 * synonymId + "_instance"); } else if (pos.equals(POS.DETERMINER)) {
		 * //Activator.logInfo("Determiner-Synonym-UID: "+synonym.getUid());
		 * Activator.logInfo("Determiner-Synonym-Instanz:
		 * "+synonym.toString()+" hinzugef�gt!"); synonymClass =
		 * ontology.getOWLNamedClass(DETERMINER_SYNONYM + "." + synonymId);
		 * synonymInstance = synonymClass.createOWLIndividual(DETERMINER_SYNONYM +
		 * "." + synonymId + "_instance"); } else if
		 * (pos.equals(POS.CONDITIONAL_CONJUNCTION)) {
		 * //Activator.logInfo("Conditional-Conjunktion-Synonym-Instanz:
		 * "+synonym.toString()+" hinzugef�gt!"); synonymClass =
		 * ontology.getOWLNamedClass(CONDITIONAL_CONJUNCTION_SYNONYM + "." +
		 * synonymId); synonymInstance =
		 * synonymClass.createOWLIndividual(CONDITIONAL_CONJUNCTION_SYNONYM +
		 * "." + synonymId + "_instance"); } synonym_instances.put(synonymId,
		 * synonymInstance);
		 * 
		 * //...and synset... SynsetInfo synset = synonym.getParentSynsetInfo();
		 * long synsetId = synset.getUid(); OWLNamedClass synsetClass = null;
		 * OWLIndividual synsetInstance = null;
		 * if(!synset_instances.containsKey(synsetId)){ if
		 * (pos.equals(POS.ADJECTIVE)) {
		 * //Activator.logInfo("Adjective-Synset-Instanz: "+synset.toString()+"
		 * hinzugef�gt!"); synsetClass =
		 * ontology.getOWLNamedClass(ADJECTIVE_SYNSET + "." + synsetId);
		 * synsetInstance = synsetClass.createOWLIndividual(ADJECTIVE_SYNSET +
		 * "." + synsetId + "_instance"); } else if (pos.equals(POS.ADVERB)) {
		 * //Activator.logInfo("Adverb-Synset-Instanz: "+synset.toString()+"
		 * hinzugef�gt!"); synsetClass =
		 * ontology.getOWLNamedClass(ADVERB_SYNSET + "." + synsetId);
		 * synsetInstance = synsetClass.createOWLIndividual(ADVERB_SYNSET + "." +
		 * synsetId + "_instance"); } else if (pos.equals(POS.NOUN)) {
		 * //Activator.logInfo("Noun-Synset-Instanz: "+synset.toString()+"
		 * hinzugef�gt!"); synsetClass = ontology.getOWLNamedClass(NOUN_SYNSET +
		 * "." + synsetId); synsetInstance =
		 * synsetClass.createOWLIndividual(NOUN_SYNSET + "." + synsetId +
		 * "_instance"); } else if (pos.equals(POS.VERB)) {
		 * //Activator.logInfo("Verb-Synset-Instanz: "+synset.toString()+"
		 * hinzugef�gt!"); synsetClass = ontology.getOWLNamedClass(VERB_SYNSET +
		 * "." + synsetId); synsetInstance =
		 * synsetClass.createOWLIndividual(VERB_SYNSET + "." + synsetId +
		 * "_instance"); } else if (pos.equals(POS.PREPOSITION)) {
		 * //Activator.logInfo("Preposition-Synset-Instanz:
		 * "+synset.toString()+" hinzugef�gt!"); synsetClass =
		 * ontology.getOWLNamedClass(PREPOSITION_SYNSET + "." + synsetId);
		 * synsetInstance = synsetClass.createOWLIndividual(PREPOSITION_SYNSET +
		 * "." + synsetId + "_instance"); } else if (pos.equals(POS.DETERMINER)) {
		 * //Activator.logInfo("Determiner-Synset-Instanz:
		 * "+synset.toString()+" hinzugef�gt!"); synsetClass =
		 * ontology.getOWLNamedClass(DETERMINER_SYNSET + "." + synsetId);
		 * synsetInstance = synsetClass.createOWLIndividual(DETERMINER_SYNSET +
		 * "." + synsetId + "_instance"); } else if
		 * (pos.equals(POS.CONDITIONAL_CONJUNCTION)) {
		 * //Activator.logInfo("Conditional Conjunction-Synset-Instanz:
		 * "+synset.toString()+" hinzugef�gt!"); synsetClass =
		 * ontology.getOWLNamedClass(CONDITIONAL_CONJUNCTION_SYNSET + "." +
		 * synsetId); synsetInstance =
		 * synsetClass.createOWLIndividual(CONDITIONAL_CONJUNCTION_SYNSET + "." +
		 * synsetId + "_instance"); } synset_instances.put(synsetId,
		 * synsetInstance); synsetInstance.addPropertyValue(glossProperty,
		 * synset.getGloss()); // set the SYNONYM_HAS_SPELLING Property
		 * synonymInstance.addPropertyValue(spellingProperty,
		 * synonym.getLemma()); } else { synsetInstance =
		 * synset_instances.get(synsetId); } // set the CONTAINS_SYNONYM
		 * Properties
		 * synonymInstance.addPropertyValue(containsSynonymOfProperty,
		 * synsetInstance); if
		 * (!synsetInstance.hasPropertyValue(containsSynonymProperty,
		 * synonymInstance))
		 * synsetInstance.addPropertyValue(containsSynonymProperty,
		 * synonymInstance); }
		 */
	}

	/**
	 * Parses a given software case. Parsing includes traversing all
	 * <code>Actor</code>s, <code>SystemElement</code>s and
	 * <code>Notion</code>s of the software case and adding OWL classes for
	 * all synsets and synonyms to the TBox.
	 * 
	 * @param loader
	 *            An instance of the class <code>SoftwareCaseLoader</code>.
	 * @param isQuery
	 *            Sets a boolean value indicating if the software case is a
	 *            former case or a query. In case it is a query (<code>isQuery</code> =
	 *            <code>true</code>), then OWL individuals of the synonyms
	 *            and corresponding synsets are created, else (<code>isQuery</code> =
	 *            <code>false</code>)not.
	 */
	public void parse(SoftwareCaseLoader loader, boolean isQuery) {
		for (Actor a : loader.getAllActors()) {
			for (SCLElement sclElem : a.getNameList()) {
				if (sclElem instanceof NounPhrase) {
					NounPhrase np = (NounPhrase) sclElem;
					parseNounPhrase(np, isQuery);
				}
			}
		}
		for (SystemElement se : loader.getAllSystemElements()) {
			for (SCLElement sclElem : se.getNameList()) {
				if (sclElem instanceof NounPhrase) {
					NounPhrase np = (NounPhrase) sclElem;
					parseNounPhrase(np, isQuery);
				}
			}
		}
		for (Notion n : loader.getAllNotions()) {
			for (DomainStatement ds : n.getStatementList()) {
				for (SCLElement sclElem : ds.getNameList()) {
					if (sclElem instanceof NounPhrase) {
						NounPhrase phrase = (NounPhrase) sclElem;
						parseNounPhrase(phrase, isQuery);
					} else if (sclElem instanceof VerbPhrase) {
						VerbPhrase vp = (VerbPhrase) sclElem;
						parseVerbPhrase(vp, isQuery);
						// A VerbPhrase has at most one NounPhrase!
						SCLElement sclNoun = vp.getObjectList().get(0);
						if (sclNoun instanceof NounPhrase) {
							NounPhrase np = (NounPhrase) sclNoun;
							parseNounPhrase(np, isQuery);
						}
					}
				}
			}
		}
		// remove orphaned owl classes; not used anymore, since only wanted
		// concepts are converted now
		// removeOrphanedChildrenOfOWLThing();

		// Add disjoints between specializations of upper model concepts
		if (Properties.ADD_DISJOINTS) {
			addDisjointsToUpperModelConcepts();
		}

		// Add disjoints between all extracted synsets of one word type
		if (Properties.ADD_DISJOINTS) {
			addDisjointsToSynsets();
		}
	}

	/**
	 * Parses all <code>Term</code>s of a given software case. Therefor adds
	 * the synset belonging to the term, if the terms pos is identified as dl
	 * relevant.
	 * 
	 * @param loader
	 * @param isQuery
	 */
	public void parseAllTerms(SoftwareCaseLoader loader, boolean isQuery) {
//		System.out.println(" Retrieving and parsing following term vertices from graph:");
		for (Term t : loader.getAllTerms()) {
			// Activator.logInfo(" - "+t.getName());
			parseTerm(t, isQuery);
		}

		removeUnusedTerms();

		// Add disjoints between specializations of upper model concepts
		// and between all extracted synsets of one word type
		if (Properties.ADD_DISJOINTS) {
			Activator.logInfo(" Adding disjoints...");
			addDisjointsToUpperModelConcepts();
			addDisjointsToSynsets();
		}

		// Add unionOf elements to all upper model concepts representing CWA
		if (isQuery && Properties.ADD_COVER_AXIOMS) {
			Activator.logInfo(" Adding cover axioms...");
			closeTBox();
		}
		Activator.logInfo(" Finished parsing terms!");
	}

	/**
	 * Parses the given <code>Term</code> t. Therefor adds the synset
	 * belonging to the given term to t via the OWL property
	 * "TermLinksToWordNetEntry".
	 * 
	 * @param t
	 * @param isQuery
	 */
	private void parseTerm(Term t, boolean isQuery) {
		SynonymInfo si = wn.retrieveSynonym(t.getSynonymUid());
		if (si != null
				&& Properties.DL_RELEVANT_CONCEPTS
						.contains(convertPOSToRSLSynsetType(si.getPos()))
				&& !(convertPOSToRSLSynsetType(si.getPos()).equals(""))) {
			SynsetInfo synset = si.getParentSynsetInfo();
			// Activator.logInfo(t.getName()+ " is dl relevant and has pos "+si.getPos());
			addSynsetAndAllRelevantInformation(synset);
			addSynsetToTerm(t, synset);
			// if(isQuery)
			// addSynonymInstance(si);
		}
	}

	/**
	 * Parses a <code>NounPhrase</code>. The synsets and synonyms for all
	 * nouns, modifier and determiner contained within the phrase are added to
	 * the TBox.
	 * 
	 * @param phrase
	 *            The <code>NounPhrase</code> to be parsed.
	 * @param isQuery
	 *            Sets a boolean value indicating if the software case is a
	 *            former case or a query. In case it is a query (<code>isQuery</code> =
	 *            <code>true</code>), then OWL individuals of the synonyms
	 *            and corresponding synsets are created, else (<code>isQuery</code> =
	 *            <code>false</code>)not.
	 */
	private void parseNounPhrase(NounPhrase phrase, boolean isQuery) {
		// add noun
		Noun noun = SoftwareCaseLoader.getNounFromNounPhrase(phrase);

		Activator.logInfo(" Trying to retrieve noun synonym " + noun.getName()+ " with uid " + noun.getSynonymUid() + "...");

		SynonymInfo si = wn.retrieveSynonym(noun.getSynonymUid());
		// proceed only if noun synonym was retrieved from WN with valid uid
		if (si != null) {
			SynsetInfo synset = si.getParentSynsetInfo();
			addSynsetAndAllRelevantInformation(synset);
			if (isQuery)
				addSynonymInstance(si);
			// add modifier if: 1. modifier is defined as dl-relevant concept
			// and 2. there is one
			if (Properties.DL_RELEVANT_CONCEPTS.contains(Properties.MODIFIER)) {
				Modifier modifier = SoftwareCaseLoader
						.getModifierFromNounPhrase(phrase);
				if (modifier != null) {
					SynonymInfo mSi = wn.retrieveSynonym(modifier
							.getSynonymUid());
					// Modifier has no entry in WordNet?
					if (mSi != null) {
						SynsetInfo mSynset = mSi.getParentSynsetInfo();
						addSynsetAndAllRelevantInformation(mSynset);
						// replacing modifier synonym created by Graph2OWL with
						// modifier synset
						// replaceSynonymBySynset(mSi, mSynset);
						addSynsetToTerm(modifier, mSynset);
						if (isQuery)
							addSynonymInstance(mSi);
					} else {
						Activator.logWarning(" WARNING: Couldn't retrieve modifier synonym with UID "
										+ modifier.getSynonymUid()
										+ "!"
										+ WORDNET_WARNING);
					}
				}
			}
			// add determiner if: 1. determiner is defined as dl-relevant
			// concept and 2. there is one
			if (Properties.DL_RELEVANT_CONCEPTS.contains(Properties.DETERMINER)) {
				Determiner determiner = SoftwareCaseLoader
						.getDeterminerFromNounPhrase(phrase);
				if (determiner != null) {
					SynonymInfo dSi = wn.retrieveSynonym(determiner
							.getSynonymUid());
					// Determiner has no entry in WordNet?
					if (dSi != null) {
						SynsetInfo dSynset = dSi.getParentSynsetInfo();
						addSynsetAndAllRelevantInformation(dSynset);
						// replacing modifier synonym created by Graph2OWL with
						// modifier synset
						// replaceSynonymBySynset(dSi, dSynset);
						// TESTING!!!
						addSynsetToTerm(determiner, dSynset);
						if (isQuery)
							addSynonymInstance(dSi);
					} else {
						Activator.logError(" WARNING: Couldn't retrieve determiner synonym with UID "
										+ determiner.getSynonymUid()
										+ "!"
										+ WORDNET_WARNING, null);
					}
				}
			}
			// replacing noun synonym created by Graph2OWL with noun synset
			// replaceSynonymBySynset(si, synset);
			// TESTING!
			addSynsetToTerm(noun, synset);
		} else {
			Activator.logWarning(" WARNING: Couldn't retrieve noun synonym with UID "
							+ noun.getSynonymUid()
							+ " from Word Net!"
							+ WORDNET_WARNING);
		}
	}

	/**
	 * Parses a <code>VerbPhrase</code>. The synsets and synonyms for all
	 * verbs and prepositions contained within the phrase are added to the TBox.
	 * 
	 * @param phrase
	 *            The <code>VerbPhrase</code> to be parsed.
	 * @param isQuery
	 *            Sets a boolean value indicating if the software case is a
	 *            former case or a query. In case it is a query (<code>isQuery</code> =
	 *            <code>true</code>), then OWL individuals of the synonyms
	 *            and corresponding synsets are created, else (<code>isQuery</code> =
	 *            <code>false</code>)not.
	 */
	private void parseVerbPhrase(VerbPhrase phrase, boolean isQuery) {
		// add verb
		Verb verb = SoftwareCaseLoader.getVerbFromVerbPhrase(phrase);

		Activator.logInfo(" Trying to retrieve verb synonym " + verb.getName()+ " with uid " + verb.getSynonymUid() + "...");

		SynonymInfo si = wn.retrieveSynonym(verb.getSynonymUid());
		// proceed only if verb synonym was retrieved from WN with valid uid
		if (si != null) {
			SynsetInfo synset = si.getParentSynsetInfo();
			addSynsetAndAllRelevantInformation(synset);
			if (isQuery)
				addSynonymInstance(si);
			// add preposition if: 1. preposition is defined as dl-relevant
			// concept and 2. there is one
			if (Properties.DL_RELEVANT_CONCEPTS
					.contains(Properties.PREPOSITION)) {
				Preposition preposition = SoftwareCaseLoader
						.getPrepositionFromVerbPhrase(phrase);
				if (preposition != null) {
					SynonymInfo pSi = wn.retrieveSynonym(preposition
							.getSynonymUid());
					// Preposition has no entry in WordNet?
					if (pSi != null) {
						SynsetInfo pSynset = pSi.getParentSynsetInfo();
						addSynsetAndAllRelevantInformation(pSynset);
						// replacing preposition synonym created by Graph2OWL
						// with preposition synset
						// replaceSynonymBySynset(pSi, pSynset);
						addSynsetToTerm(preposition, pSynset);
						if (isQuery)
							addSynonymInstance(pSi);
					} else {
						Activator.logWarning(" WARNING: Couldn't retrieve preposition synonym with UID "
										+ preposition.getSynonymUid()
										+ "!"
										+ WORDNET_WARNING);
					}
				}
			}
			// replacing verb synonym created by Graph2OWL with verb synset
			// replaceSynonymBySynset(si, synset);
			addSynsetToTerm(verb, synset);
		} else {
			Activator.logWarning(" WARNING: Couldn't retrieve verb synonym with UID "
							+ verb.getSynonymUid()
							+ " from Word Net!"
							+ WORDNET_WARNING);
		}
	}

	/**
	 * Replaces the OWLNamedClass of the given synonym by the OWLNamedClass of
	 * the given synset. Sets properties "TermLinksToWordNetEntry" and
	 * "TermLinksToWordNetEntry-of" between synset and term connected to
	 * synonym.
	 * 
	 * @param synonym
	 * @param synset
	 */
	@SuppressWarnings("unused")
	private void replaceSynonymBySynset(SynonymInfo synonym, SynsetInfo synset) {
		String synonymClassName = constructSynonymClassnameFromSynonym(synonym);
		String synsetClassName = constructSynsetClassnameFromSynset(synset);

		OWLNamedClass synonymClass = ontology
				.getOWLNamedClass(synonymClassName);
		OWLNamedClass synsetClass = ontology.getOWLNamedClass(synsetClassName);

		if (synonymClass == null) {
			// Activator.logWarning(" WARNING: Synonym "+synonymClassName+"
			// doesn't exist!");
		} else {
			// Activator.logInfo(" FOUND synonym: "+synonymClass.getName());

			OWLObjectProperty termOfProp = ontology
					.getOWLObjectProperty(TERM_LINKS_2_WORDNET_OF);
			if (termOfProp != null) {
				// retrieve term which is connected to synonym via
				// TermLinksToWordNetEntry-of property
				RDFResource term = synonymClass.getSomeValuesFrom(termOfProp);
				if (term != null) {
					if (term instanceof OWLNamedClass) {
						OWLNamedClass owlterm = (OWLNamedClass) term;
						// Activator.logInfo(" with term (OWLNamedClass):
						// "+owlterm.getName());
						// Activator.logInfo(" Replacing definition of
						// "+owlterm.getName()+":");
						// Activator.logInfo(" Removing SYNONYM definition with
						// "+synonymClassName);
						if (synsetClass != null) {
							// Activator.logInfo(" Adding SYNSET definition
							// with "+synsetClassName);

							// Connect Term and Synset via properties
							// "termLinksToWordNetEntry" and
							// "termLinksToWordNetEntry-of"
							OWLIntersectionClass intersectionOf = createSingleIntersectionOfWithSomeValuesFromElement(
									TERM_LINKS_2_WORDNET, synsetClass);
							owlterm.setDefinition(intersectionOf);

							// TESTING!
							OWLObjectProperty containsSynonymOf = ontology
									.getOWLObjectProperty(TERM_LINKS_2_WORDNET);
							if (containsSynonymOf != null) {
								OWLNamedClass value = (OWLNamedClass) owlterm
										.getPropertyValue(containsSynonymOf);
								if (value != null) {
									// Activator.logInfo("
									// Term("+owlterm.getName()+") -
									// TERM_LINKS_2_WORDNET -
									// Synset("+value.getName()+")");
								}
							} else {
								// Activator.logError(" ONTOLOGY DOES NOT
								// CONTAIN \"TERM_LINKS_2_WORDNET\"-PROPERTY!");
							}

							OWLSomeValuesFrom some = createSomeValuesFromRestrictionElement(
									TERM_LINKS_2_WORDNET_OF, owlterm);
							synsetClass.addSuperclass(some);
						} else {
							// Activator.logWarning(" WARNING: Synset
							// "+synsetClassName+" of synonym doesn't exist
							// yet!");
						}
					}
				}
			}
			ontology.deleteCls(synonymClass);
		}
	}

	/**
	 * This function constructs a owl class for the given synset and adds a
	 * definition to the given term with the owl property
	 * "TermLinksToWordNetEntry" and the created synset class as value.
	 * 
	 * @param synonym
	 * @param synset
	 */
	@SuppressWarnings("deprecation")
	private void addSynsetToTerm(Term term, SynsetInfo synset) {
		// Activator.logInfo(" Adding synset "+synset.getUid()+" to term "+term.getName());
		OWLNamedClass termClass = null;
		OWLNamedClass synsetClass = null;
		String synsetClassName = constructSynsetClassnameFromSynset(synset);

		// String termClassName = constructTermClassnameFromTerm(term);
		String termClassName = SupportFunctions
				.constructConceptNameForVertex(term);

		termClass = ontology.getOWLNamedClass(termClassName);
		if (termClass != null) {
			// Activator.logInfo(" retrieved Term-Class "+termClassName+" from
			// ontology!");
			synsetClass = ontology.getOWLNamedClass(synsetClassName);
			if (synsetClass != null) {
				// Activator.logInfo(" retrieved synset-Class
				// "+synsetClassName+" from ontology!");
				// Activator.logInfo(" setting definition for term
				// "+termClassName+": termLinksToWordNetEntry
				// "+synsetClassName);
				// add restriction with termLinksToWordNetEntry-property and
				// synsetClass as value to termClass
				OWLIntersectionClass intersectionOf = createSingleIntersectionOfWithSomeValuesFromElement(
						TERM_LINKS_2_WORDNET, synsetClass);
				termClass.setDefinition(intersectionOf);
				// add term as synsets superclass only if it's not already set!
				OWLSomeValuesFrom some = createSomeValuesFromRestrictionElement(
						TERM_LINKS_2_WORDNET_OF, termClass);
				if (!synsetClass.hasSuperclass(some)) {
					synsetClass.addSuperclass(some);
				}
				
				// for debugging
				if(Properties.ADD_TERM_NAME_TO_SYNSET){
					addSynsetRepresentsTermPropertyToSynset(synsetClass, term.getName());
				}
				
			} else {
				Activator.logWarning(" WARNING: couldn't find synset-class " + synsetClassName + " in ontology!");
			}
		} else {
//			Activator.logWarning(" WARNING: couldn't retrieve Term-Class " + termClassName + " for term \"" + term.getName() + "\"(POS: " + convertPOSToRSLSynsetType(synset.getPos()) + ") from ontology!");
		}
	}

	private OWLNamedClass addSynsetToParentSynset(String synsetId,
			OWLNamedClass parentSynsetClass) {

		// Create synset class and add property "rdfs:subClassOf" if it doesn't
		// exist yet
		OWLNamedClass synsetClass = ontology.getOWLNamedClass(synsetId);
		if (synsetClass == null) {
			try {
				synsetClass = ontology.createOWLNamedSubclass(synsetId,
						parentSynsetClass);
			} catch (NullPointerException e) {
				Activator.logWarning(" WARNING: Couldn't retrieve parent synset class: " + parentSynsetClass + " of "+synsetId+" from ontology!");
				e.printStackTrace();
			}
		}
		return synsetClass;
	}

	/**
	 * Adds synonym to it's containing synset and creates properties
	 * "ContainsSynonym" and "ContainsSynonym-Of" between added synonym and
	 * synset. Also adds synonym to it's parent synonym class.
	 * 
	 * @param synonymId
	 * @param superClass
	 * @param synsetClass
	 * @return
	 */
	@SuppressWarnings("unused")
	private OWLNamedClass addSynonymToSynset(String synonymId,
			OWLNamedClass superClass, OWLNamedClass synsetClass) {
		// Create synonym class if it doesn't exist yet
		OWLNamedClass synonymClass = ontology.getOWLNamedClass(synonymId);
		if (synonymClass == null) {
			synonymClass = ontology.createOWLNamedSubclass(synonymId,
					superClass);
		}

		// Add property ContainsSynonym-of with value "synsetClass" to synonym
		OWLIntersectionClass intersectionClass = createSingleIntersectionOfWithSomeValuesFromElement(
				CONTAINS_SYNONYM_OF, synsetClass);
		synonymClass.setDefinition(intersectionClass);

		// Add property ContainsSynonym with value synonymClass to synset
		OWLSomeValuesFrom someValuesFromRestriction = createSomeValuesFromRestrictionElement(
				CONTAINS_SYNONYM, synonymClass);
		synsetClass.addSuperclass(someValuesFromRestriction);

		return synonymClass;
	}

	private OWLIntersectionClass createSingleIntersectionOfWithSomeValuesFromElement(
			String propertyName, OWLNamedClass value) {
		OWLSomeValuesFrom someValuesFromRestriction = createSomeValuesFromRestrictionElement(
				propertyName, value);
		OWLIntersectionClass intersectionOf = ontology
				.createOWLIntersectionClass();
		intersectionOf.addOperand(someValuesFromRestriction);
		return intersectionOf;
	}

	private OWLSomeValuesFrom createSomeValuesFromRestrictionElement(String propertyName, OWLNamedClass value) {
		OWLObjectProperty property = getOWLObjectProperty(propertyName);
		OWLSomeValuesFrom someValuesFromRestriction = ontology.createOWLSomeValuesFrom(property, value);
		return someValuesFromRestriction;
	}
	
	private OWLSomeValuesFrom createSomeValuesFromRestrictionElement(String propertyName, String value){
		OWLDatatypeProperty property = getOWLDatatypeProperty(propertyName);
		RDFSDatatype valueTextResource = ontology.getRDFSDatatypeByName(value);
		if(valueTextResource == null) valueTextResource = ontology.createRDFSDatatype(value);
		OWLSomeValuesFrom someValuesFromRestriction = ontology.createOWLSomeValuesFrom(property, valueTextResource);
		return someValuesFromRestriction;
	}

	private OWLObjectProperty getOWLObjectProperty(String propertyName) {
		OWLObjectProperty property = null;
		property = ontology.getOWLObjectProperty(propertyName);
		if (property == null) {
			property = ontology.createOWLObjectProperty(propertyName);
		}
		return property;
	}
	
	private OWLDatatypeProperty getOWLDatatypeProperty(String propertyName) {
		OWLDatatypeProperty property = null;
		property = ontology.getOWLDatatypeProperty(propertyName);
		if (property == null){
			property = ontology.createOWLDatatypeProperty(propertyName);
		}
		return property;
	}
	
	/**
	 * Creates a URI representation of a given filename.
	 * 
	 * @param fileName
	 *            The name of the file.
	 * @return A string containing the URI representation of
	 *         <code>fileName</code>.
	 */
	public static String fileName2URIString(String fileName) {
		if (!fileName.startsWith("/")) {
			fileName = "/" + fileName;
		}
		fileName = fileName.replace(File.separator, "/");
		return "file://" + fileName;
	}

	/**
	 * Saves the OWL ontology to the a file with the given name.
	 * 
	 * @param owlFilename
	 *            The name of the file to which the OWL ontology should be
	 *            saved.
	 * @return <code>True</code> if saving the file does not cause any
	 *         exception, <code>false</code> otherwise.
	 */
	public boolean write(String owlFilename) {
		try {
			ontology.save(URI.create(fileName2URIString(owlFilename)));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Helping function that creates the name of an owlnamedclass for a given
	 * synset.
	 * 
	 * @param synset
	 * @return String owlClassName name of the OWLClass representing the synset
	 */
	private String constructSynsetClassnameFromSynset(SynsetInfo synset) {
		String owlClassName = "";
		POS pos = synset.getPos();
		long synsetId = synset.getUid();

		if (pos.equals(POS.ADJECTIVE)) {
			owlClassName = ADJECTIVE_SYNSET + "." + synsetId;
			// parentClass = ontology.createOWLNamedSubclass(ADJECTIVE_SYNSET +
			// "." + parentId, adjectiveSynsetClass);
		} else if (pos.equals(POS.ADVERB)) {
			owlClassName = ADVERB_SYNSET + "." + synsetId;
		} else if (pos.equals(POS.NOUN)) {
			owlClassName = NOUN_SYNSET + "." + synsetId;
		} else if (pos.equals(POS.VERB)) {
			owlClassName = VERB_SYNSET + "." + synsetId;
		} else if (pos.equals(POS.PREPOSITION)) {
			owlClassName = PREPOSITION_SYNSET + "." + synsetId;
		} else if (pos.equals(POS.DETERMINER)) {
			owlClassName = DETERMINER_SYNSET + "." + synsetId;
		} else if (pos.equals(POS.CONDITIONAL_CONJUNCTION)) {
			owlClassName = CONDITIONAL_CONJUNCTION_SYNSET + "." + synsetId;
		}
		return owlClassName;
	}

	/**
	 * Helping function that creates the name of an owlnamedclass for a given
	 * synonym.
	 * 
	 * @param synset
	 * @return String owlClassName name of the OWLClass representing the synset
	 */
	private String constructSynonymClassnameFromSynonym(SynonymInfo synonym) {
		String owlClassName = "";
		POS pos = synonym.getPos();
		long synonymId = synonym.getUid();

		if (pos.equals(POS.ADJECTIVE)) {
			// Activator.logInfo("Adjective-Synonym: "+synonym.toString());
			owlClassName = ADJECTIVE_SYNONYM + "." + synonymId;
		} else if (pos.equals(POS.ADVERB)) {
			// Activator.logInfo("Adverb-Synonym: "+synonym.toString());
			owlClassName = ADVERB_SYNONYM + "." + synonymId;
		} else if (pos.equals(POS.NOUN)) {
			// Activator.logInfo("Noun-Synonym: "+synonym.toString());
			owlClassName = NOUN_SYNONYM + "." + synonymId;
		} else if (pos.equals(POS.VERB)) {
			// Activator.logInfo("Verb-Synonym: "+synonym.toString());
			owlClassName = VERB_SYNONYM + "." + synonymId;
		} else if (pos.equals(POS.PREPOSITION)) {
			// Activator.logInfo("Preposition-Synonym: "+synonym.toString());
			owlClassName = PREPOSITION_SYNONYM + "." + synonymId;
		} else if (pos.equals(POS.DETERMINER)) {
			// Activator.logInfo("Determiner-Synonym: "+synonym.toString());
			owlClassName = DETERMINER_SYNONYM + "." + synonymId;
		} else if (pos.equals(POS.CONDITIONAL_CONJUNCTION)) {
			// Activator.logInfo("Conditional Conjunction-Synonym: "+synonym.toString());
			owlClassName = CONDITIONAL_CONJUNCTION_SYNONYM + "." + synonymId;
		}
		return owlClassName;
	}

	/**
	 * Helping function to retrieve name of owl class representing given term in
	 * ontology.
	 * 
	 * @param term
	 * @return String id of the owl class representing the term
	 */
	@SuppressWarnings("unused")
	private String constructTermClassnameFromTerm(Term term) {
		String owlClassName = "";

		String graphUid = "";
		String termUid = "_" + term.getUid();
		String wordType = term.getAttributedElementClass().getSimpleName();
		String termPrefix = "rsl.rsldomainelements.terms.";

		try {
			graphUid += "_" + term.getGraph().getAttribute("uid");
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}

		owlClassName = termPrefix + wordType + graphUid + termUid;

		return owlClassName;
	}

	/**
	 * Helping function to retrieve owl class of concept representing upper
	 * model ancestor of given synset (e.g. "noun")
	 * 
	 * @param synset
	 * @return
	 */
	private OWLNamedClass retrieveUpperModelConceptOfSynsetType(
			SynsetInfo synset) {
		OWLNamedClass upperModelClass = null;
		POS pos = synset.getPos();

		if (pos.equals(POS.ADJECTIVE)) {
			return adjectiveSynsetClass;
		} else if (pos.equals(POS.ADVERB)) {
			return adverbSynsetClass;
		} else if (pos.equals(POS.NOUN)) {
			return nounSynsetClass;
		} else if (pos.equals(POS.VERB)) {
			return verbSynsetClass;
		} else if (pos.equals(POS.PREPOSITION)) {
			return prepositionSynsetClass;
		} else if (pos.equals(POS.DETERMINER)) {
			return determinerSynsetClass;
		} else if (pos.equals(POS.CONDITIONAL_CONJUNCTION)) {
			return conditionalConjunctionSynsetClass;
		}
		return upperModelClass;
	}

	/**
	 * Helping function to retrieve owl class of concept representing upper
	 * model ancestor of given synonym
	 * 
	 * @param synset
	 * @return
	 */
	@SuppressWarnings("unused")
	private OWLNamedClass retrieveUpperModelConceptOfSynonymType(
			SynonymInfo synonym) {
		OWLNamedClass upperModelClass = null;
		POS pos = synonym.getPos();

		if (pos.equals(POS.ADJECTIVE)) {
			return adjectiveSynonymClass;
		} else if (pos.equals(POS.ADVERB)) {
			return adverbSynonymClass;
		} else if (pos.equals(POS.NOUN)) {
			return nounSynonymClass;
		} else if (pos.equals(POS.VERB)) {
			return verbSynonymClass;
		} else if (pos.equals(POS.PREPOSITION)) {
			return prepositionSynonymClass;
		} else if (pos.equals(POS.DETERMINER)) {
			return determinerSynonymClass;
		} else if (pos.equals(POS.CONDITIONAL_CONJUNCTION)) {
			return conditionalConjunctionSynonymClass;
		}
		return upperModelClass;
	}

	private void gatherRelevantWordTypeClasses() {
		if (Properties.DL_RELEVANT_CONCEPTS
				.contains("dictionaryelements.AdjectiveSynset")) {
			relevantWordTypesOWLClasses.add(adjectiveSynsetClass);
		}
		if (Properties.DL_RELEVANT_CONCEPTS
				.contains("dictionaryelements.AdverbSynset")) {
			relevantWordTypesOWLClasses.add(adverbSynsetClass);
		}
		if (Properties.DL_RELEVANT_CONCEPTS
				.contains("dictionaryelements.NounSynset")) {
			relevantWordTypesOWLClasses.add(nounSynsetClass);
		}
		if (Properties.DL_RELEVANT_CONCEPTS
				.contains("dictionaryelements.VerbSynset")) {
			relevantWordTypesOWLClasses.add(verbSynsetClass);
		}
		if (Properties.DL_RELEVANT_CONCEPTS
				.contains("dictionaryelements.PrepositionSynset")) {
			relevantWordTypesOWLClasses.add(prepositionSynsetClass);
		}
		if (Properties.DL_RELEVANT_CONCEPTS
				.contains("dictionaryelements.DeterminerSynset")) {
			relevantWordTypesOWLClasses.add(determinerSynsetClass);
		}
		if (Properties.DL_RELEVANT_CONCEPTS
				.contains("dictionaryelements.ConditionalConjunctionSynset")) {
			relevantWordTypesOWLClasses.add(conditionalConjunctionSynsetClass);
		}
	}

	private String convertPOSToRSLSynsetType(POS pos) {
		if (pos.equals(POS.ADJECTIVE)) {
			return ADJECTIVE_SYNSET;
		} else if (pos.equals(POS.VERB)) {
			return VERB_SYNSET;
		} else if (pos.equals(POS.NOUN)) {
			return NOUN_SYNSET;
		} else if (pos.equals(POS.ADVERB)) {
			return ADVERB_SYNSET;
		} else if (pos.equals(POS.PREPOSITION)) {
			return PREPOSITION_SYNSET;
		} else if (pos.equals(POS.CONDITIONAL_CONJUNCTION)) {
			return CONDITIONAL_CONJUNCTION_SYNSET;
		} else if (pos.equals(POS.DETERMINER)) {
			return DETERMINER_SYNSET;
		} else
			return "";
	}

	/**
	 * Adds <code>OWLDisjointWith</code> elements to all synset
	 * specializations that are not upper model concepts (e.g. "nounSynset",
	 * "verbSynset"). Therefor effectively making concepts like "nounSynsetX"
	 * and "nounSynsetY" disjoint.
	 */
	private void addDisjointsToSynsets() {
		// ALL synset classes (including upper model classes "verbSynset" etc.)
		Object[] synsetClasses = synsetClass.getSubclasses(true).toArray();
		Object[] synsetSpecializations = null;
		OWLNamedClass currentSynsetClass = null;
		OWLNamedClass classSpecialization = null;

		for (Object synsetClass : synsetClasses) {
			if (synsetClass != null && synsetClass instanceof OWLNamedClass) {
				currentSynsetClass = (OWLNamedClass) synsetClass;
				// get only direct specializations of current synset class
				synsetSpecializations = currentSynsetClass.getSubclasses(false)
						.toArray();
				for (int i = 0; i < synsetSpecializations.length; i++) {
					if (synsetSpecializations[i] instanceof OWLNamedClass) {
						classSpecialization = (OWLNamedClass) synsetSpecializations[i];
						// for(int j=i+1; j<synsetSpecializations.length;j++){
						for (int j = 0; j < synsetSpecializations.length; j++) {
							// don't add disjoint to itself!
							if (j == i)
								continue;
							if (synsetSpecializations[j] instanceof OWLNamedClass
									&& !classSpecialization
											.getDisjointClasses()
											.contains(
													(OWLNamedClass) synsetSpecializations[j])) {
								classSpecialization
										.addDisjointClass((OWLNamedClass) synsetSpecializations[j]);
							}
						}
					}
				}
			}
		}
	}

	/**
	 * Adds <code>OWLDisjointWith</code> elements to all upper model concepts,
	 * making them pairwise disjoint.
	 */
	private void addDisjointsToUpperModelConcepts() {

		Object[] conceptSpecializations = null;
		OWLNamedClass currentSpec = null;

		for (OWLNamedClass currentUpperModelConcept : upperModelConcepts) {
			conceptSpecializations = currentUpperModelConcept.getSubclasses(
					false).toArray();
			for (int i = 0; i < conceptSpecializations.length; i++) {
				if (conceptSpecializations[i] instanceof OWLNamedClass
						&& SupportFunctions
								.isUpperModelConcept((OWLNamedClass) conceptSpecializations[i])) {
					currentSpec = (OWLNamedClass) conceptSpecializations[i];
					// for(int j=i+1; j<conceptSpecializations.length; j++){
					for (int j = 0; j < conceptSpecializations.length; j++) {
						if (i == j)
							continue;
						if (conceptSpecializations[j] instanceof OWLNamedClass
								&& SupportFunctions
										.isUpperModelConcept((OWLNamedClass) conceptSpecializations[j])
								&& !currentSpec
										.getDisjointClasses()
										.contains(
												(OWLNamedClass) conceptSpecializations[j])) {
							currentSpec
									.addDisjointClass((OWLNamedClass) conceptSpecializations[j]);
						}
					}
				}
			}
		}
	}

	/**
	 * Adds unionsOf elements to all upper model concepts. The unionOf element
	 * of an upper model concept contains all subclasses of the upper model
	 * concept that are upper model concepts themselfs as operands.
	 */
	@SuppressWarnings("unused")
	private void addCoverAxiomsToUpperModelConcepts() {

		OWLUnionClass unionClass = null;
		Vector<OWLNamedClass> operands = null;

		for (OWLNamedClass currentUpperModelConcept : upperModelConcepts) {
			operands = new Vector<OWLNamedClass>();
			for (Object currentSubclass : currentUpperModelConcept
					.getSubclasses(false)) {
				if (currentSubclass instanceof OWLNamedClass
						&& SupportFunctions
								.isUpperModelConcept((OWLNamedClass) currentSubclass)
						&& !(((OWLNamedClass) currentSubclass).getName())
								.equals("null")) {
					operands.add((OWLNamedClass) currentSubclass);
				}
			}
			if (operands.size() > 0) {
				unionClass = ontology.createOWLUnionClass(operands);
				currentUpperModelConcept.addSuperclass(unionClass);
			}
		}
	}

	private void addCoverAxiomsToAllConcepts() {

		OWLUnionClass unionClass = null;
		Vector<OWLNamedClass> operands = null;
		OWLNamedClass owlThing = ontology.getOWLThingClass();
		OWLNamedClass currentOWLConcept = null;
		Vector<Object> alreadyVisitedClasses = new Vector<Object>();

		for (Object currentConcept : owlThing.getSubclasses(true)) {
			if (currentConcept instanceof OWLNamedClass
					&& !alreadyVisitedClasses.contains(currentConcept)) {
				currentOWLConcept = (OWLNamedClass) currentConcept;
				// Activator.logInfo(" COVER AXIOM: Adding cover axiom to class "+currentOWLConcept.getName());
				operands = new Vector<OWLNamedClass>();
				operands.add(currentOWLConcept);
				for (Object currentSubclass : currentOWLConcept
						.getSubclasses(false)) {
					if (currentSubclass instanceof OWLNamedClass
							&& !(((OWLNamedClass) currentSubclass).getName())
									.equals("null")) {
						operands.add((OWLNamedClass) currentSubclass);
					}
				}

				// TEST
//				for (Object superClass : currentOWLConcept.getSuperclasses(false)) {
//					if (superClass instanceof OWLUnionClass) {
//						Activator.logInfo(" Class already has union class as super class!");
//					}
//				}

				if (operands.size() > 1) {
					unionClass = ontology.createOWLUnionClass(operands);
					currentOWLConcept.addSuperclass(unionClass);
				}
				alreadyVisitedClasses.add(currentConcept);
			}
		}
	}

	/**
	 * Helping method to add terms name to synsetRepresentsTerm property.
	 * @param synsetClass
	 * @param t
	 */
	private void addSynsetRepresentsTermPropertyToSynset(OWLNamedClass synsetClass, String name){
		// only add SynsetRepresentsTerm property to synset if it doesn't have one already
//		System.out.println(" SynsetClass == null: " + (synsetClass == null));
//		System.out.println(" Term name == null: " + (name == null));
//		System.out.println(" SynsetRepresentsTermProperty == null: " + (synsetRepresentsTermProperty == null));
		
//		Collection<?> rdfProps = synsetClass.getRDFProperties();
//		System.out.println(" Current synset has SynsetRepresentsTerm property: "+rdfProps.contains(synsetRepresentsTermProperty));
		
		if (!synsetClass.hasPropertyValue(synsetRepresentsTermProperty)){
			synsetClass.addSuperclass(createSomeValuesFromRestrictionElement(Properties.SYNSET_REPRESENTS_TERM_PROPERTY, name));			
		}
	}
	
	/**
	 * Closes the TBox by adding cover axioms to all concepts.
	 */
	public void closeTBox() {
		// addCoverAxiomsToUpperModelConcepts();
		addCoverAxiomsToAllConcepts();
	}

	private void computeUpperModelConcepts() {
		OWLNamedClass owlThing = ontology.getOWLThingClass();
		Object[] allConcepts = owlThing.getSubclasses(true).toArray();
		for (Object concept : allConcepts) {
			if (concept instanceof OWLNamedClass
					&& SupportFunctions
							.isUpperModelConcept((OWLNamedClass) concept)) {
				upperModelConcepts.add((OWLNamedClass) concept);
			}
		}
	}

	/**
	 * Helping function that removes all unwanted children of owl:thing from the
	 * TBox.
	 */
	@SuppressWarnings( { "unchecked", "unused" })
	private void removeOrphanedChildrenOfOWLThing() {
		OWLNamedClass thing = ontology.getOWLNamedClass("owl:Thing");
		if (thing != null) {
			// Activator.logInfo("FOUND OWL THINGs CHILDREN:");
			Collection<RDFResource> orphanedSynonyms = thing
					.getSubclasses(false);
			for (RDFResource child : orphanedSynonyms) {
				if (child instanceof OWLNamedClass) {
					OWLNamedClass owlChild = (OWLNamedClass) child;
					// Activator.logInfo(" Child: "+owlChild.getName()+", "+owlChild.getLocalName()+", "+owlChild.toString());
					if (owlChild.getName().endsWith("Synonym")
							|| owlChild.getName().startsWith("rsl.")) {
						// Activator.logInfo(" --> remove child!");
						owlChild.delete();
					}
				}
			}
		}
	}

	/**
	 * Method removing all owlnamed classes of terms that were created in
	 * Graph2OWLConcept but are not filler of any property or role other than
	 * "termLinksToWordNetEntry-of"
	 */
	@SuppressWarnings( { "unchecked", "unused" })
	private void removeUnusedTerms() {
		// Activator.logInfo(" Removing unused terms!");

		Collection<RDFSClass> allTermClasses = new LinkedList<RDFSClass>();
		Collection<RDFSClass> allNounClasses = new LinkedList<RDFSClass>();
		Collection<RDFSClass> allVerbClasses = new LinkedList<RDFSClass>();

		RDFSClass termClass = ontology
				.getRDFSNamedClass("rsl.rsldomainelements.terms.Term");
		if (termClass != null) {
			// Activator.logInfo(" found term class!");
			allTermClasses = termClass.getSubclasses(true);
		}
		RDFSClass nounClass = ontology
				.getRDFSNamedClass("rsl.rsldomainelements.terms.Noun");
		if (nounClass != null) {
			// Activator.logInfo(" found noun class!");
			allNounClasses = nounClass.getSubclasses(true);
		}
		RDFSClass verbClass = ontology
				.getRDFSNamedClass("rsl.rsldomainelements.terms.Verb");
		if (verbClass != null) {
			// Activator.logInfo(" found verb class!");
			allVerbClasses = verbClass.getSubclasses(true);
		}

		Collection<RDFSClass> allClasses = ontology.getRDFSClasses();
		Vector<OWLNamedClass> termsToBeRemoved = new Vector<OWLNamedClass>();
		OWLSomeValuesFrom currentRestriction;
		OWLObjectProperty currentOnProperty;
		OWLNamedClass currentFiller;
		boolean removeTerm = true;
		for (RDFSClass c : allVerbClasses) {
			if (c instanceof OWLNamedClass) {
				// Activator.logInfo(" checking, if owl class "+c.getName()+"
				// can be removed...");

				for (RDFSClass c2 : allClasses) {
					if (!(c2 instanceof OWLNamedClass) || c.equals(c2))
						continue;
					for (Object r : ((OWLNamedClass) c2).getRestrictions()) {
						if (r instanceof OWLSomeValuesFrom) {
							currentRestriction = (OWLSomeValuesFrom) r;
							if (currentRestriction.getOnProperty() instanceof OWLObjectProperty
									&& currentRestriction.getFiller() instanceof OWLNamedClass) {
								currentOnProperty = (OWLObjectProperty) currentRestriction
										.getOnProperty();
								currentFiller = (OWLNamedClass) currentRestriction
										.getFiller();
								if (currentFiller.equals(c)
										&& !((currentOnProperty.getName())
												.equals(TERM_LINKS_2_WORDNET_OF))) {
									// Activator.logInfo(" term "+c.getName()+"
									// will not be removed!");
									removeTerm = false;
									break;
								}
							}
						}
					}
				}
				if (removeTerm) {
					// Activator.logInfo(" removing term "+c.getName()+"!");
					termsToBeRemoved.add((OWLNamedClass) c);
				}
			}
			removeTerm = true;
		}
		// Activator.logInfo(" "+termsToBeRemoved.size()+" owl classes for
		// terms can be removed!");
		OWLNamedClass deletableClass;
		Iterator<OWLNamedClass> termIt = termsToBeRemoved.iterator();
		while (termIt.hasNext()) {
			deletableClass = termIt.next();
			// Activator.logInfo(" deleting owl class
			// "+deletableClass.getName());
			deletableClass.delete();
		}
	}

}
