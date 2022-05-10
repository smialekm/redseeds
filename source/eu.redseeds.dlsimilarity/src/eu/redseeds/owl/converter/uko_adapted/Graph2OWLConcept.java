/*
 * JGraLab - The Java graph laboratory
 * (c) 2006-2007 Institute for Software Technology
 *               University of Koblenz-Landau, Germany
 *
 *               ist@uni-koblenz.de
 *
 * Please report bugs to http://serres.uni-koblenz.de/bugzilla
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
 
package eu.redseeds.owl.converter.uko_adapted;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import de.uni_koblenz.jgralab.Aggregation;
import de.uni_koblenz.jgralab.Attribute;
import de.uni_koblenz.jgralab.AttributedElement;
import de.uni_koblenz.jgralab.Edge;
import de.uni_koblenz.jgralab.Graph;
import de.uni_koblenz.jgralab.ProgressFunction;
import de.uni_koblenz.jgralab.Vertex;
import de.uni_koblenz.jgralab.schema.AggregationClass;
import de.uni_koblenz.jgralab.schema.AttributedElementClass;
import de.uni_koblenz.jgralab.schema.Domain;
import de.uni_koblenz.jgralab.schema.EdgeClass;
import de.uni_koblenz.jgralab.schema.ListDomain;
import de.uni_koblenz.jgralab.schema.RecordDomain;
import de.uni_koblenz.jgralab.schema.Schema;
import de.uni_koblenz.jgralab.schema.SetDomain;
import de.uni_koblenz.jgralab.schema.VertexClass;
import eu.redseeds.owl.Activator;
import eu.redseeds.owl.Properties;
import eu.redseeds.owl.ShortCut;
import eu.redseeds.owl.Step;
import eu.redseeds.owl.SupportFunctions;


public class Graph2OWLConcept {

	Vector<ShortCut> shortCuts = Properties.SHORTCUTS;
	Vector<String> detectedShortCuts = new Vector<String>();
	
	// vector of properties that should be added to owl as defining properties
	Vector<String> definingRSLProperties = Properties.DEFINING_RSL_PROPERTIES;
	// vector of properties that should be added to owl as necessary properties
	Vector<String> necessaryRSLProperties = Properties.NECESSARY_RSL_PROPERTIES;
	// vector containing names of concepts that are relevant for DL similarity measure
	Vector<String> dlRelevantConcepts = Properties.DL_RELEVANT_CONCEPTS;
	
	
	// for testing purposes only
	Vector<String> notDefiningNorNecessaryRSLProperties = new Vector<String>();
	Vector<String> detectedDefiningRSLProperties = new Vector<String>();
	Vector<String> detectedNecessaryRSLProperties = new Vector<String>();
	/**
	 * Represents the root of the namespace.
	 */
	private String namespace;
	
	
	/**
	 * Represents the root of the DOM-tree.
	 */
	private Document doc;
	
	/**
	 * The direct child element of the root node
	 */
	private Element rdfElem;
	
	/**
	 * If {@code true}, an EdgeClass is converted to exactly one
	 * property, discarding possible attributes. If {@code false}, an EdgeClass is converted
	 * to an OWL class and two Properties.
	 */
	private boolean edgeClasses2Properties;
	
	/**
	 * The suffix appended to the OWL construct representing an EdgeClass. It is an
	 * empty string if the parameter {@code appendSuffix2EdgeClassName} given to the
	 * constructor is {@code false}.
	 * 
	 * @see #Schema2OWL(Document doc, Schema schema, boolean edgeClasses2Properties, 
			boolean appendSuffix2EdgeClassName)
	 */
	private String edgeClassNameSuffix;
	
	/**
	 * Creates an instance of {@code Graph2OWL}, assigns values to the member
	 * variables.
	 * 
	 * @param doc The root node of the DOM-tree.
	 * @param g The graph which shall be converted to OWL.
	 * @param pf An instance of {@code ProgressFunction} to display the progress of the
	 * conversion in a status bar.
	 */
	public Graph2OWLConcept(Document doc, Graph g, boolean edgeClasses2Properties, 
			boolean appendSuffix2EdgeClassName, ProgressFunction pf, String namespace) {
		
//		Activator.logInfo("Graph2OWL: converting graph "+ g.toString() + "...");
		if (namespace == null) {
			namespace = "http://eu.redseeds.scl.SCLSchema#";
		}
		this.namespace = namespace;
		
		//Activator.logInfo("NameSpace in Graph2OWLConcept: " + namespace);
		this.doc = doc;
		this.edgeClasses2Properties = edgeClasses2Properties;
		if (appendSuffix2EdgeClassName) {
			edgeClassNameSuffix = "EC";
		} else {
			edgeClassNameSuffix = "";
		}
		
		this.rdfElem = (Element)doc.getElementsByTagName("rdf:RDF").item(0);		
		
//		Activator.logInfo("Constructing the following RSL properties as DEFINING properties in OWL:");
//		for (String prop : definingRSLProperties){
//			 Activator.logInfo("   "+prop);
//		}
//		Activator.logInfo("Constructing the following RSL properties as NECESSARY properties in OWL:");
//		for (String prop : necessaryRSLProperties){
//			 Activator.logInfo("   "+prop);
//		}
//		
//		Activator.logInfo("Following concepts are relevant for the DL similarity measure and therefor will be constructed:");
//		for (String concept : dlRelevantConcepts){
//			 Activator.logInfo("   "+concept);
//		}
//
//		
//		Activator.logInfo("Following concepts have shortcusts:");
//		for (ShortCut shortCut: shortCuts){
//			 Activator.logInfo("   "+shortCut.toString());
//		}
		
		saveGraph(g, pf);
		
		
//		Activator.logInfo("  Following properties were recognized as \"defining\":");
//		for (String prop : detectedDefiningRSLProperties){
//			 Activator.logInfo("     "+prop);
//		}
//		
//		Activator.logInfo("  Following properties were recognized as \"necessary\":");
//		for (String prop : detectedNecessaryRSLProperties){
//			 Activator.logInfo("     "+prop);
//		}		
//		
//		Activator.logInfo("  Following properties were recognized as neither \"defining\" nor \"necessary\":");
//		for (String prop : notDefiningNorNecessaryRSLProperties){
//			 Activator.logInfo("     "+prop);
//		}
//
//   	    Activator.logInfo("  Following shortcuts were recognized:");
//		for (String prop : detectedShortCuts){
//			 Activator.logInfo("     "+prop);
//		}
	}

	/**
	 * Initializes {@code pf} and starts the conversion of the {@code Graph graph} to OWL.
	 * 
	 * @param g The graph which shall be converted to OWL.
	 * @param pf An instance of {@code ProgressFunction} to display the progress of the
	 * conversion in a status bar.
	 * 
	 * @see #convertGraph(Graph g, ProgressFunction pf)
	 */
	private void saveGraph(Graph g, ProgressFunction pf) {
		// initialize progress bar for graph
		int graphElements = 0, currentCount = 0, interval = 1;
		if (pf != null) {
			pf.init(g.getVCount() + g.getECount());
			interval = (int)pf.getUpdateInterval();
		}
		
		// update progress bar
		if (pf != null) {
			graphElements++;
			currentCount++;
			if (currentCount == interval) {
				pf.progress(graphElements);
				currentCount = 0;
			}
		}
				
		convertGraph(g, pf);
		
		// which RSL properties were created as defining, necessary? which were not created?
//		Activator.logInfo("Following edges were converted to defining RSL properties:");
//		for(String def : definingRSLProperties){
//			Activator.logInfo("  "+def);
//		}
//		Activator.logInfo("Following edges were converted to necessary RSL properties:");
//		for(String nec : necessaryRSLProperties){
//			Activator.logInfo("  "+nec);
//		}
//		
//		Activator.logInfo("Following edges were NOT converted at all:");
//		for(String not : notDefiningNorNecessaryRSLProperties){
//			Activator.logInfo("  "+not);
//		}		
		
		// finish progress bar
		if (pf != null) {
			pf.finished();
		}
	}
	
	/**
	 * Converts the {@code Graph g} to an individual of the OWL class representing the
	 * graph's {@code AttributedElementClass}. The individual contains properties
	 * relating the graph to its attributes and its contained vertices and edges.<br>
	 * <br>
	 * XML code written:
	 * <br>
	 * <pre>
	 *     &lt;<i>g.getAttributedElementClass().getName()</i> rdf:ID="<i>g.getId()</i>"&gt;
     *         <i>convertAttributeValue(g, g.getAttributedElementClass().getAttributeList().toArray(new Attribute[0])[0])</i>
     *         <i>convertAttributeValue(g, g.getAttributedElementClass().getAttributeList().toArray(new Attribute[0])[1])</i>
     *         <i>...</i>
     * </pre>
     * ------------------------------------<br>
	 * This part is written for every {@code Vertex v} contained in {@code g}, where
	 * <i>vElemId</i> is the id of the individual representing that 
	 * {@code Vertex}. 
	 * <pre>
	 *         &lt;graphContainsVertex rdf:resource="#<i>vElemId</i>"/&gt;
	 * </pre>
	 * If {@code edgeClasses2Properties = false}, this part is written for every
	 * {@code Edge e} contained in {@code g}, where <i>eElemId</i> is the id of the
	 * individual representing that {@code Edge}. 
	 * <pre>
     *         &lt;graphContainsEdge rdf:resource="#<i>eElemId</i>"/&gt;
	 * </pre>
	 * ------------------------------------<br>
	 * <pre>
	 *    &lt;/<i>g.getAttributedElementClass().getName()</i>&gt;
     * </pre> 
	 * 
	 * @param g The graph which shall be converted to OWL.
	 * @param pf An instance of {@code ProgressFunction} to display the progress of the
	 * conversion in a status bar.
	 * 
	 * @see #convertAttributeValue(AttributedElement ownerAe, Attribute attr)
	 */
	private void convertGraph(Graph g, ProgressFunction pf) {
		String eElemId;
		String vElemId;
		
		// create Individual for Graph g
//		Element gElem = createIndividualElement(g.getAttributedElementClass().getQualifiedName(),
//				g.getId());
		Element gElem;
//		Element subClassOf = doc.createElement("rdfs:subClassOf");
//		Element unionClass = doc.createElement("owl:Class");
		Element unionOf = doc.createElement("owl:unionOf");
		unionOf.setAttribute("rdf:parseType", "Collection");

		
		String graphName = g.getAttributedElementClass().getQualifiedName();
		String graphId = "#" + g.getId();
		String graphUid = "";
		
		// FIXME: should use graphId, when graphUid is "null"; but using id instead of uid creates problems
		// in OWLReasoner.getSoftwareCaseUid!
		try {
			graphUid = String.valueOf(g.getAttribute("uid"));
//			if(graphUid.equals("null")){
//				gElem = createClassElement(graphName, graphId);
//			}
//			else{
				gElem = createClassElement(graphName, "#" + graphUid);	
//			}						
			// Activator.logInfo(" Graph-uid-attribute: "+String.valueOf(g.getAttribute("uid")));
		} catch (NoSuchFieldException e1) {
			gElem = createClassElement(graphName, graphId);
		}
		
		// convert attributes of g
		try {
			for (Attribute attr : g.getAttributedElementClass().getAttributeList()) {
				// Activator.logInfo("Attribut: "+attr.getName());
				
				Domain dom = attr.getDomain();
				if (dom.isComposite() && !dom.getTGTypeName(null).equals("Object")){
					// Activator.logInfo("is object property");
				}
				else {
					// Activator.logInfo("is datatype property");
				}
				    	
				
				if (g.getAttribute(attr.getName()) != null) {
					//gElem.appendChild(convertAttributeValue(g, attr));
					Element newAttributElement = convertAttributeValue(g, attr);
					if(newAttributElement!=null){
						gElem.appendChild(newAttributElement);
					}					
				}
			}
		} catch (NoSuchFieldException nsfe) {
			nsfe.printStackTrace();
		}
		
		// convert vertices
		for (Vertex v : g.vertices()) {
			vElemId = SupportFunctions.constructConceptNameForVertex(v);
			
			unionOf.appendChild(createDatatypeProperty("graphContainsVertex", "#" + vElemId));
			//unionOf.appendChild(createIndividualObjectPropertyElement("graphContainsVertex", "#" + vElemId));
			convertVertex(v, vElemId, pf);
		}		
		
		if (!edgeClasses2Properties) {
			// convert edges
			for (Edge e : g.edges()) {
				eElemId = HelperMethods.firstToLowerCase(
						e.getAttributedElementClass().getQualifiedName())
								+ edgeClassNameSuffix
								+ String.valueOf(e.getId());
					gElem.appendChild(createRestrictionFromObjectPropertyElement(
							"graphContainsEdge" + edgeClassNameSuffix, eElemId));
				
				convertEdge(e, eElemId, pf);
			}
		}
		// only append "unionOf" when it's not empty, e.g. "resource=nil"
		/*
		NodeList unionOfChilds = unionOf.getChildNodes();
		if (unionOfChilds.getLength()>0){
			unionClass.appendChild(unionOf);
			subClassOf.appendChild(unionClass);
			gElem.appendChild(subClassOf);
		}
		*/
		// append Individual for Graph g to element <rdf:RDF>
		rdfElem.appendChild(gElem);
	}


	/**
	 * Converts the {@code Vertex v} to an individual of the OWL class representing the
	 * vertex' {@code AttributedElementClass}. {@code vElemId} specifies the individual's
	 * id. The individual contains properties relating it to its attributes, its
	 * containing graph, and its incident edges.<br>
	 * <br>
	 * XML code written if {@code appendSuffix2EdgeClassName = false}:
	 * <br>
	 * <pre>
	 *     &lt;<i>v.getAttributedElementClass().getName()</i> rdf:ID="<i>vElemId</i>"&gt;
     *         <i>convertAttributeValue(v, v.getAttributedElementClass().getAttributeList().toArray(new Attribute[0])[0])</i>
     *         <i>convertAttributeValue(v, v.getAttributedElementClass().getAttributeList().toArray(new Attribute[0])[1])</i>
     *         <i>...</i>
     *         &lt;vertexIsInGraph rdf:resource="#<i>v.getGraph().getId()</i>"/&gt;
     * </pre> 
     * ------------------------------------<br>
	 * This part is only written if {@code edgeClasses2Properties = false} and {@code e}
	 * is an {@code Edge} incident to {@code v} with {@code v} on its "from" side.
	 * <i>eElemId</i> is the id of the individual representing that {@code Edge}:
	 * <pre>
	 *         &lt;<i>e.getAttributedElementClass().getName() + edgeClassNameSuffix</i>Out rdf:resource="#<i>eElemId</i>"/&gt;
	 * </pre>
	 * If {@code v} is on the "to" side, replace 
	 * <i>e.getAttributedElementClass().getName() + edgeClassNameSuffix</i>{@code Out} with 
	 * <i>e.getAttributedElementClass().getName() + edgeClassNameSuffix</i>{@code In}.<br>
	 * ------------------------------------<br>
	 * This part is only written if {@code edgeClasses2Properties = true} and {@code e}
	 * is an {@code Edge} incident to {@code v} with {@code v} on its "from" side.
	 * <i>eElemId</i> is the id of the individual representing that {@code Edge}:
	 * <pre>
	 *         &lt;<i>e.getAttributedElementClass().getName() + edgeClassNameSuffix</i> rdf:resource="#<i>eElemId</i>"/&gt;
	 * </pre>
	 * If {@code v} is on the "to" side, replace 
	 * <i>e.getAttributedElementClass().getName() + edgeClassNameSuffix</i> with 
	 * <i>e.getAttributedElementClass().getName() + edgeClassNameSuffix</i>{@code -of}.<br>
	 * ------------------------------------<br>
	 * <pre>                
     *     &lt;/<i>v.getAttributedElementClass().getName()</i>&gt;
     * </pre>
	 * 
	 * @param v The vertex which shall be converted.
	 * @param vElemId The id of the individual representing the vertex in OWL.
	 * @param pf An instance of {@code ProgressFunction} to display the progress of the
	 * conversion in a status bar.
	 * 
	 * @see #convertAttributeValue(AttributedElement ownerAe, Attribute attr)
	 */
	private void convertVertex(Vertex v, String vElemId, ProgressFunction pf) {
		AttributedElementClass vc = v.getAttributedElementClass();
		Edge e = v.getFirstEdge();
		String owlClass = vc.getQualifiedName();
//		Activator.logInfo(" Start Converting "+vElemId+" of class " +v);  // lh
		// create the Individual representing v
//		Element vElem = createIndividualElement(vc.getQualifiedName(), vElemId);
		if(isDLRelevantConcept(vElemId)){
			
			// TESTING
//			if(owlClass.startsWith("rsl.rsldomainelements.terms.")){
//				Activator.logInfo(" converting vertex: "+vElemId);				
//			}
			
			Element vElem = createClassElement(owlClass, "#" + vElemId);
	
			Element equivalentClass = doc.createElement("owl:equivalentClass");		
			Element intersectionClass = doc.createElement("owl:Class");
			Element intersectionOf = doc.createElement("owl:intersectionOf");
			
			Element subClassOf = doc.createElement("rdfs:subClassOf");
			Element unionClass = doc.createElement("owl:Class");
			Element unionOf = doc.createElement("owl:unionOf");
			
			intersectionOf.setAttribute("rdf:parseType", "Collection");
			unionOf.setAttribute("rdf:parseType", "Collection");
			
			// set the WodNet link (this code block is commented because wordnet links are now 
			// created in WordNet2OWL entirely!
//			if (owlClass.startsWith("rsl.rsldomainelements.terms.")) {			
//				String synonymUid = null;
//				String rdfAbout = null;
//				//String wn20schema = "http://de.uni_koblenz.jgwnl.wordnetschema.WordNetSchema#";
//				String wn20schema = "#";
//				//String wn20schema = "http://eu.redseeds.scl.SCLSchema#";
//				String synonymParentClassName = "";
//				
//				String rdfID = null;			
//				String wordType = null;
//				
//				// TODO ADJECTIVE / ADVERB / PREPOSITION
//				
//				if (owlClass.endsWith("Noun")) {
//					NounImpl noun = (NounImpl)v;
//					synonymUid = "" + noun.getSynonymUid();
//					rdfAbout = "#" + WordNet2OWL.NOUN_SYNONYM + "."+ synonymUid;;
//					rdfID = wn20schema + WordNet2OWL.NOUN_SYNONYM + "."+ synonymUid;
//					synonymParentClassName = "#" + WordNet2OWL.NOUN_SYNONYM; 
//					wordType = "Noun";
//				} else if (owlClass.endsWith("Verb")) {
//					VerbImpl verb = (VerbImpl)v;
//					synonymUid = "" + verb.getSynonymUid();
//					rdfAbout = "#" + WordNet2OWL.VERB_SYNONYM + "."+ synonymUid;
//					rdfID = wn20schema + WordNet2OWL.VERB_SYNONYM + "."+ synonymUid;
//					synonymParentClassName = "#" + WordNet2OWL.VERB_SYNONYM;
//					wordType = "Verb";
//				} else if (owlClass.endsWith("Determiner")) {
//					DeterminerImpl det = (DeterminerImpl)v;
//					synonymUid = "" + det.getSynonymUid();
//					rdfAbout = "#" + WordNet2OWL.DETERMINER_SYNONYM + "."+ synonymUid;
//					rdfID = wn20schema + WordNet2OWL.DETERMINER_SYNONYM + "."+ synonymUid;
//					synonymParentClassName = "#" + WordNet2OWL.DETERMINER_SYNONYM;
//					wordType = "Determiner";
//				} else if (owlClass.endsWith("ConditionalConjunction")) {
//					ConditionalConjunctionImpl cond = (ConditionalConjunctionImpl)v;
//					synonymUid = "" + cond.getSynonymUid();
//					rdfAbout = "#" + WordNet2OWL.CONDITIONAL_CONJUNCTION_SYNONYM + "."+ synonymUid;
//					rdfID = wn20schema + WordNet2OWL.CONDITIONAL_CONJUNCTION_SYNONYM + "."+ synonymUid;
//					synonymParentClassName = "#" + WordNet2OWL.CONDITIONAL_CONJUNCTION_SYNONYM;
//					wordType = "ConditionalConjunction";
//				}			
//				if (synonymUid != null) {
//					
//					Element wnLink = doc.createElement("rsl.rsldomainelements.terms.TermLinksToWordNetEntry");
//					vElem.appendChild(wnLink);
//					Element wnTerm = doc.createElement("owl:Class");												
//					wnTerm.setAttribute("rdf:about", rdfAbout + "." + synonymUid);
//					wnLink.appendChild(wnTerm);					
//					
//					// This version creates a link to the synonym via the OWLObjectProperty TermLinksToWordNetEntry
//				
//					// subclass is implicitly created when calling "createClassElement"				
//					// create property pointing from term to wordNet entry ("TermLinksToWordNetEntry")
//					Element restriction = doc.createElement("owl:Restriction");								
//					Element onProperty = doc.createElement("owl:onProperty");				
//					Element wnLinkObjectProperty = doc.createElement("owl:ObjectProperty");
//					//wnLinkObjectProperty.setAttribute("rdf:ID", "rsl.rsldomainelements.terms.TermLinksToWordNetEntry");				
//					wnLinkObjectProperty.setAttribute("rdf:about", "#rsl.rsldomainelements.terms.TermLinksToWordNetEntry");
//					Element someValuesFrom = doc.createElement("owl:someValuesFrom");
//	
//					//someValuesFrom.setAttribute("rdf:resource", rdfID);				
//					Element synonymClass = doc.createElement("owl:Class");
//					synonymClass.setAttribute("rdf:about", rdfID);
//					Element synonymSubClassOf = doc.createElement("rdfs:subClassOf");
//					Element synonymParentClass = doc.createElement("owl:Class");
//					synonymParentClass.setAttribute("rdf:about", synonymParentClassName);
//					synonymSubClassOf.appendChild(synonymParentClass);
//					synonymClass.appendChild(synonymSubClassOf);
//					
//					// create restriction on property TermLinksToWordNetEntry-of, pointing from synonym to term
//					Element termLinksToWordNetEntryOfRestriction = createRestrictionFromObjectPropertyElement("rsl.rsldomainelements.terms.TermLinksToWordNetEntry-of", vElemId);					
//					Element subClassOfSynonym = doc.createElement("rdfs:subClassOf");									
//					subClassOfSynonym.appendChild(termLinksToWordNetEntryOfRestriction);
//					synonymClass.appendChild(subClassOfSynonym);										
//					
//					someValuesFrom.appendChild(synonymClass);
//					onProperty.appendChild(wnLinkObjectProperty);				
//					restriction.appendChild(onProperty);
//					restriction.appendChild(someValuesFrom);
//					intersectionOf.appendChild(restriction);
//								
//				}
//			}
	
			String eElemClass;
			String eElemId; 
			
			String eElemClassOf;  // lh
			
			// convert Attributes of v
			try {
				for (Attribute attr : v.getAttributedElementClass().getAttributeList()) {
					if (v.getAttribute(attr.getName()) != null) {
						Element newAttributElement = convertAttributeValue(v, attr);
						if(newAttributElement!=null){
							vElem.appendChild(newAttributElement);
						}
					}
				}
			} catch (NoSuchFieldException nsfe) {
				nsfe.printStackTrace();
			}
			//Activator.logInfo(" Adding vertixIsInGraph to vertex "+vElem.);
			
			try {
				vElem.appendChild(createDatatypeProperty(
				//vElem.appendChild(createIndividualObjectPropertyElement(
				//unionOf.appendChild(createIndividualObjectPropertyElement(
						"vertexIsInGraph", "#" + String.valueOf(v.getGraph().getAttribute("uid"))));
			} catch (NoSuchFieldException e1) {
				vElem.appendChild(createDatatypeProperty(			
				//vElem.appendChild(createIndividualObjectPropertyElement(
				//unionOf.appendChild(createIndividualObjectPropertyElement(
						"vertexIsInGraph", "#" + v.getGraph().getId()));
			} catch (DOMException e1) {
				e1.printStackTrace();
			}
				 		 		
			// create individual properties referring to individuals representing incident edges
//			Activator.logInfo(" Converting "+e);  // lh
//			Activator.logInfo(" IntersectionOf Before ShortCut: " + intersectionOf.getChildNodes().getLength());
			if (Properties.TAKE_SHORTCUTS > 0){
				intersectionOf = ensureShortCut(intersectionOf, v, owlClass, vElemId); //lh
			}
//				Activator.logInfo(" IntersectionOf after ShortCut: " + intersectionOf.getChildNodes().getLength());
			while (e != null) {
				eElemClass = HelperMethods.firstToLowerCase(
						e.getAttributedElementClass().getQualifiedName())+ edgeClassNameSuffix;
				eElemId = eElemClass + String.valueOf(e.getNormalEdge().getId());
				//Activator.logInfo(" Converting Class: "+eElemClass);  // lh
				eElemClassOf = eElemClass + "-of"; // lh
				// create property only if it is defining or necessary

				if (definingRSLProperties.contains(eElemClass) || necessaryRSLProperties.contains(eElemClass) ||
					definingRSLProperties.contains(eElemClassOf) || necessaryRSLProperties.contains(eElemClassOf)){  // lh
					if (edgeClasses2Properties) {
						String toConceptName = "";
						if (e.getAlpha() == v) {
							toConceptName = SupportFunctions.constructConceptNameForVertex(e.getAlpha());
						}
						else{
							eElemClass += "-of";
							toConceptName = SupportFunctions.constructConceptNameForVertex(e.getOmega());
						}
						try{							
							Element newObjectProperty = createRestrictionFromObjectPropertyElement(eElemClass, toConceptName);
//							Activator.logInfo("Role created to: " + toConceptName);
							if(newObjectProperty!=null){
								if(definingRSLProperties.contains(eElemClass)){
									if(!detectedDefiningRSLProperties.contains(eElemClass)){
										detectedDefiningRSLProperties.add(eElemClass);
									}
									intersectionOf.appendChild(newObjectProperty);
//									Activator.logInfo(" ..... IntersectionOf1: " + intersectionOf.getChildNodes().getLength());
								}
								else if(necessaryRSLProperties.contains(eElemClass)){
									if(!detectedNecessaryRSLProperties.contains(eElemClass)){
										detectedNecessaryRSLProperties.add(eElemClass);
									}
									unionOf.appendChild(newObjectProperty);
								}
							}								
						}
						catch (DOMException e1) {
							e1.printStackTrace();
						}	
					} else {
						if (e.getAlpha() == v) {
		//					vElem.appendChild(createIndividualObjectPropertyElement(
							intersectionOf.appendChild(createRestrictionFromObjectPropertyElement(
									HelperMethods.firstToLowerCase(
											e.getAttributedElementClass().getQualifiedName()
													+ edgeClassNameSuffix
													+ "Out"),
											eElemId));
							Activator.logInfo(" ..... IntersectionOf5: " + intersectionOf.getChildNodes().getLength());
						} else {
		//					vElem.appendChild(createIndividualObjectPropertyElement(
							intersectionOf.appendChild(createRestrictionFromObjectPropertyElement(
									HelperMethods.firstToLowerCase(
											e.getAttributedElementClass().getQualifiedName()
													+ edgeClassNameSuffix
													+ "In"),
											eElemId));
						}
					}
				}	
				else {
					if(!notDefiningNorNecessaryRSLProperties.contains(eElemClass)){
						notDefiningNorNecessaryRSLProperties.add(eElemClass);
					}
				}
				
				e = e.getNextEdge();
			}
			
			// check if "unionOf" and "intersectionOf" are empty, 
			// e.g. vertex has defining and necessary properties or not
			NodeList unionOfChilds = unionOf.getChildNodes();
			if (unionOfChilds.getLength()>0){
				unionClass.appendChild(unionOf);
				subClassOf.appendChild(unionClass);
				vElem.appendChild(subClassOf);
			}
//			Activator.logInfo(" ..... IntersectionOfFinal: " + intersectionOf.getChildNodes().getLength());			
			NodeList intersectionOfChilds = intersectionOf.getChildNodes();
			if (intersectionOfChilds.getLength()>0){
				intersectionClass.appendChild(intersectionOf);
				equivalentClass.appendChild(intersectionClass);
				// add intersection as equivalent class and as superclass!   // no more lh
//				Element equivalentClassSubClassOf = doc.createElement("rdfs:subClassOf");
//				Element subClassIntersection = (Element)intersectionClass.cloneNode(true);
//				equivalentClassSubClassOf.appendChild(subClassIntersection);
				vElem.appendChild(equivalentClass);			
//				vElem.appendChild(equivalentClassSubClassOf);
			}
			else{
				// Activator.logInfo(" No properties created for "+vElemId+" because intersection has no operands!");
			}
			rdfElem.appendChild(vElem);
//			Activator.logInfo("Created element: "+vElemId);
		}
		else{
			// Activator.logInfo("NOT a DL relevant concept: "+vElemId);
		}
	}
	
	/**
	 * Converts the {@code Edge e} to an individual of the OWL class representing the
	 * edge's {@code AttributedElementClass}. {@code eElemId} specifies the individual's
	 * id. The individual contains properties relating it to its attributes, its
	 * containing graph, the role names on its "from" and "to" sides and, if {@code e}
	 * constitutes an {@code Aggregation} or {@code Composition}, to the {@code Vertex}
	 * forming the aggregate.<br>
	 * <br>
	 * XML code written if:
	 * <br>
	 * <pre>
	 *     &lt;<i>e.getAttributedElementClass().getName() + edgeClassNameSuffix</i> rdf:ID="<i>eElemId</i>"&gt;
     *         <i>convertAttributeValue(e, e.getAttributedElementClass().getAttributeList().toArray(new Attribute[0])[0])</i>
     *         <i>convertAttributeValue(e, e.getAttributedElementClass().getAttributeList().toArray(new Attribute[0])[1])</i>
     *         <i>...</i>
     *         &lt;edgeIsInGraph rdf:resource="#<i>e.getGraph().getId()</i>"/&gt;
     *         &lt;edgeFromRole rdf:datatype="http://www.w3.org/2001/XMLSchema#string"&gt;<i>e.getAttributedElementClass().getFromRolename()</i>&lt;/edgeFromRole&gt;
     *         &lt;edgeToRole rdf:datatype="http://www.w3.org/2001/XMLSchema#string"&gt;<i>e.getAttributedElementClass()).getToRolename()</i>&lt;/edgeToRole&gt;
     * </pre>
     * ------------------------------------<br>
	 * This part is only written if {@code e} is an {@code Aggregation} or {@code 
	 * Composition} and the {@code Vertex} on the "from" side is the aggregate, where
	 * <i>fromElemId</i> is the id is the id of the individual representing that 
	 * {@code Vertex}:
	 * <pre>
	 *         &lt;aggregate rdf:resource="#<i>fromElemId</i>"/&gt;
	 * </pre>
	 * If the aggregate is on the "to" side, replace <i>fromElemId</i> with 
	 * <i>toElemId</i>.
	 * ------------------------------------<br>         
     * <pre>        
     *     &lt;/<i>e.getAttributedElementClass().getName() + edgeClassNameSuffix</i>&gt;
     * </pre>
	 * 
	 * @param e The edge which shall be converted.
	 * @param eElemId The id of the individual representing the edge in OWL.
	 * @param pf An instance of {@code ProgressFunction} to display the progress of the
	 * conversion in a status bar.
	 * 
	 * @see #convertAttributeValue(AttributedElement ownerAe, Attribute attr)
	 */
	private void convertEdge(Edge e, String eElemId, ProgressFunction pf) {
		AttributedElementClass ec = e.getAttributedElementClass();
		Vertex fromVertex = e.getAlpha();
		Vertex toVertex = e.getOmega();
		
//		Element eElem = createIndividualElement(
//				ec.getQualifiedName() + edgeClassNameSuffix, eElemId);

		Element eElem = createClassElement(
				ec.getQualifiedName() /* + edgeClassNameSuffix*/, "#" + eElemId);

		// compute the ids of the individuals representing the out and the in vertices
		String fromElemId;
		try {
			fromElemId = HelperMethods.firstToLowerCase(
						fromVertex.getAttributedElementClass().getQualifiedName())
						+ "_" + String.valueOf(fromVertex.getGraph().getAttribute("uid"))
						+ "_" + String.valueOf(fromVertex.getAttribute("uid"));
		} catch (NoSuchFieldException e1) {
			fromElemId = HelperMethods.firstToLowerCase(
					fromVertex.getAttributedElementClass().getQualifiedName())
					+ fromVertex.getId();;
		};
		String toElemId;
		try {
			toElemId = HelperMethods.firstToLowerCase(
						toVertex.getAttributedElementClass().getQualifiedName())
						+ "_" + String.valueOf(toVertex.getGraph().getAttribute("uid"))
						+ "_" + String.valueOf(toVertex.getAttribute("uid"));
		} catch (NoSuchFieldException e1) {
			toElemId = HelperMethods.firstToLowerCase(
					toVertex.getAttributedElementClass().getQualifiedName())
					+ toVertex.getId();;
		};
		
		// convert attributes of e
		try {
			for (Attribute attr : e.getAttributedElementClass().getAttributeList()) {
				if (e.getAttribute(attr.getName()) != null) {
					eElem.appendChild(convertAttributeValue(e, attr));
				}
			}
		} catch (NoSuchFieldException nsfe) {
			nsfe.printStackTrace();
		}
			
		try {
			eElem.appendChild(createRestrictionFromObjectPropertyElement(
					"edge" + edgeClassNameSuffix + "IsInGraph", String.valueOf(e.getGraph().getAttribute("uid"))));
		} catch (NoSuchFieldException e1) {
			eElem.appendChild(createRestrictionFromObjectPropertyElement(
					"edge" + edgeClassNameSuffix + "IsInGraph", "#" + e.getGraph().getId()));
		} catch (DOMException e1) {
			e1.printStackTrace();
		}
		
		// create properties for role names
		eElem.appendChild(createIndividualDatatypePropertyElement(
				"edge" + edgeClassNameSuffix + "OutRole",
				"http://www.w3.org/2001/XMLSchema#string", 
				((EdgeClass)e.getAttributedElementClass()).getFromRolename()));
		eElem.appendChild(createIndividualDatatypePropertyElement(
				"edge" + edgeClassNameSuffix + "InRole",
				"http://www.w3.org/2001/XMLSchema#string", 
				((EdgeClass)e.getAttributedElementClass()).getToRolename()));

		// create properties for aggregate if e is an Aggregation or Composition
		if (e instanceof Aggregation) {
			if (((AggregationClass)ec).isAggregateFrom()) {
				eElem.appendChild(createRestrictionFromObjectPropertyElement(
						"aggregate", "#" + fromElemId));
			} else {
				eElem.appendChild(createRestrictionFromObjectPropertyElement(
						"aggregate", "#" + toElemId));
			}
		}
			
		rdfElem.appendChild(eElem);
	}
	
	/**
	 * Converts the value of the {@code Attribute attr} to an individual of a Property.
	 * See {@link #createAttributeIndividualObjectPropertyElement(String name, Object
	 * value, Domain dom)} and {@link #createAttributeIndividualDatatypePropertyElement(
	 * String name, Object value, Domain dom)} for the created XML code.
	 * 
	 * @param ownerAe The {@code AttributedElementClass} containing {@code attr}.
	 * @param attr The {@code Attribute} which shall be converted.
	 * @return An individual property representing {@code attr}.
	 * 
	 * @throws NoSuchFieldException
	 * 
	 * @see #createAttributeIndividualObjectPropertyElement(String name, Object value, Domain dom)
	 * @see #createAttributeIndividualDatatypePropertyElement(String name, Object value, Domain dom)
	 */
	private Element convertAttributeValue(AttributedElement ownerAe, Attribute attr)
			throws NoSuchFieldException {
		String attrPropertyName;
		String attrName = attr.getName();
		Object value = ownerAe.getAttribute(attrName);
		
		AttributedElementClass ownersAec = ownerAe.getAttributedElementClass();
		Domain dom = attr.getDomain();
		
		// The name of the Property representing the attribute
		if (ownersAec instanceof EdgeClass) {
			attrPropertyName = 	HelperMethods.firstToLowerCase(ownersAec.getQualifiedName()) 
					+ edgeClassNameSuffix
					+ "Has" 
					+ HelperMethods.firstToUpperCase(attrName);
		} else {
			attrPropertyName = HelperMethods.firstToLowerCase(ownersAec.getQualifiedName()) 
				+ "Has" 
				+ HelperMethods.firstToUpperCase(attrName);
		}
		//Activator.logInfo(" Found object property represented by vertex attribute (property: "+attrPropertyName);		
		// if "attr" has a CompositeDomain as type (no Object)
		if ((dom.isComposite() 
			    	&& !dom.getTGTypeName(null).equals("Object"))) {			
			return createAttributeIndividualObjectPropertyElement(
					attrPropertyName, value, dom);
		// if "attr" has a BasicDomain as type
		} else {
			//return createAttributeIndividualDatatypePropertyElement(
			//		attrPropertyName, value, dom);
			//Activator.logInfo(" Datatype property "+attrPropertyName+" ignored (not needed for classification!)");
			return null;
		}
	}
	
	/**
	 * Creates an individual of an ObjectProperty representing the value of a composite
	 * {@code Attribute}. See {@link #createListIndividualObjectPropertyElement(String
	 * name, Object value, Domain dom)}, {@link #createSetIndividualObjectPropertyElement(
	 * String name, Object value, Domain dom)} and
	 * {@link #createRecordIndividualObjectPropertyElement(String name,
	 * Object value, Domain dom)} for the created XML code.
	 * 
	 * @param propertyName The name of the ObjectProperty which shall be created.
	 * @param value The value of the composite attribute which shall be converted.
	 * @param dom The {@code Domain} of the composite attribute which shall be converted.
	 * @return An individual ObjectProperty representing a composite attribute.
	 * 
	 * @see #createListIndividualObjectPropertyElement(String name, Object value, Domain dom)
	 * @see #createSetIndividualObjectPropertyElement(String name, Object value, Domain dom)
	 * @see #createRecordIndividualObjectPropertyElement(String name, Object value, Domain dom)
	 */
	private Element createAttributeIndividualObjectPropertyElement(
			String propertyName, Object value, Domain dom) {						
		if (dom.getTGTypeName(null).startsWith("List<")) {
			return createListIndividualObjectPropertyElement(
					propertyName, value, dom);
		} else if (dom.getTGTypeName(null).startsWith("Set<")) {
			return createSetIndividualObjectPropertyElement(
					propertyName, value, dom);
		} else {
			return createRecordIndividualObjectPropertyElement(
					propertyName, value, dom);
		}
	}
	
	/**
	 * Creates an individual of an ObjectProperty representing the value of an
	 * {@code Attribute} of a list type.
	 * <br>
     * XML-code written if the members of the list are of composite type:
	 * <br>
	 * <pre>
	 *     &lt;<i>propertyName</i>&gt;
     *         &lt;ListElement&gt;
     *             &lt;listElementHasObject <i>...</i>
     *             &lt;hasNextListElement&gt;
     *                 &lt;ListElement&gt;
     *                     &lt;listElementHasObject <i>...</i>
     *                     &lt;hasNextListElement&gt;
     *                         <i>...</i>
     *                     &lt;/hasNextListElement&gt; 
     *             &lt;/hasNextListElement&gt;
     *         &lt;ListElement&gt;
     *          
     *     &lt;<i>propertyName</i>&gt;
     * </pre>
     * XML-code written if the members of the list are of basic type:
	 * <br>
	 * <pre>
	 *     &lt;<i>propertyName</i>&gt;
     *         &lt;ListElement&gt;
     *             &lt;listElementHasDatatype <i>...</i>
     *             &lt;hasNextListElement&gt;
     *                 &lt;ListElement&gt;
     *                     &lt;listElementHasDatatype <i>...</i>
     *                     &lt;hasNextListElement&gt;
     *                         <i>...</i>
     *                     &lt;/hasNextListElement&gt; 
     *             &lt;/hasNextListElement&gt;
     *         &lt;/ListElement&gt;
     *     &lt;/<i>propertyName</i>&gt;
     * </pre>
     *  
     * The child elements and/or attributes of the individuals {@code <Set>} are
     * determined by yet another call of
     * {@link #createAttributeIndividualObjectPropertyElement(String propertyName, Object
     * value, Domain dom)} or 
	 * {@link #createAttributeIndividualDatatypePropertyElement(String propertyName,
	 * Object value, Domain dom)}, respectively.
     * 
	 * @param propertyName The name of the ObjectProperty which shall be created.
	 * @param value The value of the attribute of type List which shall be converted.
	 * @param dom The {@code Domain} of the attribute of type List which shall be
	 * converted.
	 * @return An individual ObjectProperty representing an attribute of type List.
	 * 
 	 * @see #createAttributeIndividualObjectPropertyElement(String propertyName, Object value, Domain dom)
	 * @see #createAttributeIndividualDatatypePropertyElement(String propertyName, Object value, Domain dom)
	 */	
	private Element createListIndividualObjectPropertyElement (
			String propertyName, Object value, Domain dom) {
		Object componentValue;
		
		// get the base domain of the list
		Domain baseDomain = ((ListDomain)dom).getBaseDomain();;
		
		Element attrIndividualPropertyElem = createIndividualPropertyElement(propertyName);
		Element nextListElementProperty = attrIndividualPropertyElem;
		Element compositeIndividualElem;
		
		// for each value inside the list
		for (int i = 0; i < ((List<?>)value).size(); i++) {
			componentValue = ((List<?>)value).get(i);
			
			// create an individual of owl-class "ListElement" and append it as child of
			// the "hasNextListElement" individual property of the last iteration
			compositeIndividualElem = createAnonymousIndividualElement("ListElement");
			nextListElementProperty.appendChild(compositeIndividualElem);

			// create individual properties for the ListElement's value
			if (((baseDomain.isComposite() 
			    	&& !baseDomain.getTGTypeName(null).equals("Object")))) {
				compositeIndividualElem.appendChild(
						createAttributeIndividualObjectPropertyElement(
								"listElementHasObject",	componentValue, baseDomain));
			} else {			
				compositeIndividualElem.appendChild(
						createAttributeIndividualDatatypePropertyElement(
								"listElementHasDatatype", componentValue, 
								baseDomain));
			}
			
			// if i is not the index of the last ListElement, create property
			// "hasNextListElement"
			if (i < ((List<?>)value).size() - 1) {
				nextListElementProperty = 
						createIndividualPropertyElement("hasNextListElement");
				compositeIndividualElem.appendChild(nextListElementProperty);
			}			
		}
		
		return attrIndividualPropertyElem;
	}
	
	/**
	 * Creates an individual of an ObjectProperty representing the value of an
	 * {@code Attribute} of a set type.
	 * <br>
     * XML-code written if the members of the set members are of composite type:
	 * <br>
	 * <pre>
	 *     &lt;<i>propertyName</i>&gt;
     *         &lt;Set&gt;
     *             &lt;setHasObject <i>...</i>
     *         &lt;/Set&gt;    
     *     &lt;/<i>propertyName</i>&gt;
     * </pre>
     * XML-code written if the members of the set members are of basic type:
	 * <br>
	 * <pre>
	 *     &lt;<i>propertyName</i>&gt;
     *         &lt;Set&gt;
     *             &lt;setHasDatatype <i>...</i>
     *         &lt;/Set&gt;    
     *     &lt;/<i>propertyName</i>&gt;
     * </pre>
     * 
     * The child elements and/or attributes of the individuals {@code <Set>} are
     * determined by yet another call of
     * {@link #createAttributeIndividualObjectPropertyElement(String propertyName,
     * Object value, Domain dom)} or 
	 * {@link #createAttributeIndividualDatatypePropertyElement(String propertyName,
	 * Object value, Domain dom)}, respectively.
	 * 
	 * @param propertyName The name of the ObjectProperty which shall be created.
	 * @param value The value of the attribute of type Set which shall be converted.
	 * @param dom The {@code Domain} of the attribute of type Set which shall be
	 * converted.
	 * @return An individual ObjectProperty representing an attribute of type Set.
	 * 
	 * @see #createAttributeIndividualObjectPropertyElement(String propertyName, Object value, Domain dom)
	 * @see #createAttributeIndividualDatatypePropertyElement(String propertyName, Object value, Domain dom)
	 */
	private Element createSetIndividualObjectPropertyElement (
			String propertyName, Object value, Domain dom) {
		// get the base domain of the Set
		Domain baseDomain = ((SetDomain)dom).getBaseDomain();
		
		Element compositeIndividualElem = createAnonymousIndividualElement("Set");
		
		Element attrIndividualPropertyElem = createIndividualPropertyElement(propertyName);
		attrIndividualPropertyElem.appendChild(compositeIndividualElem);

		// if the base domain is a composite domain
		if ((baseDomain.isComposite() 
		    	&& !dom.getTGTypeName(null).equals("Object"))) {
			// for each value inside the Set
			for (Object componentValue : (Set<?>)value) {
				compositeIndividualElem.appendChild(
						createAttributeIndividualObjectPropertyElement(
								"setHasObject",	componentValue, baseDomain));
			}
		// if the base domain is a basic domain
		} else {
			// for each value inside the Set
			for (Object componentValue : (Set<?>)value) {
				compositeIndividualElem.appendChild(
						createAttributeIndividualDatatypePropertyElement(
								"setHasDatatype", componentValue, baseDomain));
			}
		}
		
		return attrIndividualPropertyElem;
	}

	/**
	 * Creates an individual of an ObjectProperty representing the value of an
	 * {@code Attribute} of a record type.
	 * <br>
	 * XML-code written, where <i>componentNameX</i> denotes the names of the components:
	 * <br>
	 * <pre>
	 *     &lt;<i>propertyName</i>&gt;
     *         &lt;<i>dom.getName()</i>&gt;
     *             &lt;<i>dom.getName()</i>Has<i>componentName1</i> <i>...</i>
     *             &lt;<i>dom.getName()</i>Has<i>componentName2</i> <i>...</i>
     *         &lt;/<i>dom.getName()</i>&gt;    
     *     &lt;/<i>propertyName</i>&gt;
     * </pre>
     * 
     * The child elements and/or attributes of the individuals {@code <}<i>dom.getName()
	 * </i>{@code Has}<i>componentNameX</i> are determined by a call of 
	 * {@link #createAttributeIndividualObjectPropertyElement(String propertyName,
	 * Object value, Domain dom)} or 
	 * {@link #createAttributeIndividualDatatypePropertyElement(String propertyName,
	 * Object value, Domain dom)}, respectively<br>
	 * <br>
	 * An example:<br>
	 * <pre>
	 *     &lt;carParkHasParking&gt;
	 *         &lt;Parking&gt;
	 *             &lt;parkingHasCost rdf:datatype="http://www.w3.org/2001/XMLSchema#double"&gt;3.5&lt;/parkingHasCost&gt;
     *             &lt;parkingHasDate rdf:datatype="http://www.w3.org/2001/XMLSchema#string"&gt;08.03.2006&lt;/parkingHasDate&gt;
     *             &lt;parkingHasDuration rdf:datatype="http://www.w3.org/2001/XMLSchema#int"&gt;90&lt;/parkingHasDuration&gt;
     *         &lt;/Parking&gt;
     *     &lt;/carParkHasParking&gt;
	 * </pre>
	 * 
	 * @param propertyName The name of the ObjectProperty which shall be created.
	 * @param value The value of the attribute of type Record which shall be converted.
	 * @param dom The {@code Domain} of the attribute of type Record which shall be
	 * converted.
	 * @return An individual ObjectProperty representing an attribute of type Record.
	 * 
 	 * @see #createAttributeIndividualObjectPropertyElement(String propertyName, Object value, Domain dom)
	 * @see #createAttributeIndividualDatatypePropertyElement(String propertyName, Object value, Domain dom)
	 */
	private Element createRecordIndividualObjectPropertyElement (
			String propertyName, Object value, Domain dom) {
		Element compositeIndividualElem = createAnonymousIndividualElement(dom.getQualifiedName());
		
		Element attrIndividualPropertyElem = createIndividualPropertyElement(propertyName);
		attrIndividualPropertyElem.appendChild(compositeIndividualElem);
	
		// for every component of the Record
		for (Map.Entry<String, Domain> component : ((RecordDomain)dom).getComponents().entrySet()) {
			Object componentValue = null;
			
			// get the value of the record component
			try {
				componentValue = value.getClass().getField(component.getKey()).get(value);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			// if the component is of composite type
			if (component.getValue().isComposite() 
					&& !component.getValue().getTGTypeName(null).equals("Object")) {
				compositeIndividualElem.appendChild(
						createAttributeIndividualObjectPropertyElement(
									HelperMethods.firstToLowerCase(dom.getQualifiedName()) 
									+ "Has" 
									+ HelperMethods.firstToUpperCase(component.getKey()),
								componentValue, component.getValue()));
			// if the component is of basic type
			} else {			
				compositeIndividualElem.appendChild(
						createAttributeIndividualDatatypePropertyElement(
									HelperMethods.firstToLowerCase(dom.getQualifiedName()) 
									+ "Has" 
									+ HelperMethods.firstToUpperCase(component.getKey()),
								componentValue, component.getValue()));
			}
		}
		
		return attrIndividualPropertyElem;
	}
	
	/**
	 * Creates an individual of a DatatypeProperty representing the value of an
	 * {@code Attribute} of a basic type.
	 * <br>
	 * XML-code written:<br>
	 * <br>
	 * <pre>
	 *     &lt;<i>propertyName</i> rdf:datatype="#<i>dom.getTGTypeName()</i>&gt;<i>value.toString()</i>&lt;/<i>propertyName</i>&gt;
	 * </pre>
	 *         
	 * @param propertyName The name of the DatatypeProperty which shall be created.
	 * @param value The value of the attribute of basic type which shall be converted.
	 * @param dom The {@code Domain} of the attribute of basic type which shall be
	 * converted.
	 * @return An individual DatatypeProperty representing an attribute of basic type.
	 */
	private Element createAttributeIndividualDatatypePropertyElement(
			String propertyName, Object value, Domain dom) {
		Element attrIndividualPropertyElem = createIndividualPropertyElement(
				propertyName);		
		if (dom.getTGTypeName(null).equals("String")) {
			attrIndividualPropertyElem.setAttribute("rdf:datatype", 
					"http://www.w3.org/2001/XMLSchema#string");
			attrIndividualPropertyElem.appendChild(doc.createTextNode(
					(String)value));
		} else if (dom.getTGTypeName(null).equals("Object")) {
			attrIndividualPropertyElem.setAttribute("rdf:datatype", 
					"http://www.w3.org/2001/XMLSchema#base64Binary");
			attrIndividualPropertyElem.appendChild(doc.createTextNode(
					HelperMethods.createBase64Representation(value)));
		} else if (dom.toString().startsWith("Enum")){
			attrIndividualPropertyElem.setAttribute("rdf:resource", 
					"#" + ((Enum<?>)value).toString());
		} else {
			attrIndividualPropertyElem.setAttribute("rdf:datatype", 
					"http://www.w3.org/2001/XMLSchema#"
							+ dom.getJavaAttributeImplementationTypeName("")); 
			attrIndividualPropertyElem.appendChild(doc.createTextNode(
					(value.toString())));
		}
		
		return attrIndividualPropertyElem;
	}
	
	/**
	 * Creates and returns an element {@code <}<i>owlClass</i>{@code />}, representing an
	 * individual.
	 *  
	 * @param owlClass The element's tag.
	 * @return The created element.
	 */
	private Element createAnonymousIndividualElement(String owlClass) {
		Element elem = doc.createElement(owlClass);
		
		return elem;
	}
	
	/**
	 * Creates and returns an element {@code <}<i>owlClass</i> {@code rdf:ID = }
	 * <i>id</i>{@code />}, representing an individual.
	 *  
	 * @param owlClass The element's tag.
	 * @param id The value of the "rdf:ID" attribute.
	 * @return The created element.
	 */	
	@SuppressWarnings("unused")
	private Element createIndividualElement(String owlClass, String id) {
		Element elem = doc.createElement(owlClass);
		elem.setAttribute("rdf:ID", id);
		
		return elem;
	}
	
	private Element createClassElement(String owlClass, String id) {
		//String wn20schema = "http://de.uni_koblenz.jgwnl.wordnetschema.WordNetSchema#";
		String sclSchema = this.namespace;
		
		// create a CLASS
		Element elem = doc.createElement("owl:Class");
		elem.setAttribute("rdf:about", id);
		
		// set the subclass PROPERTY
		Element subclass = doc.createElement("rdfs:subClassOf");
		
		// instead of creating a child element for "subclass", connect the filler owl class as property "rdf:resource"
		//subclass.setAttribute("rdf:resource", wn20schema+owlClass);
		//subclass.setAttribute("rdf:resource", sclSchema+owlClass);
		Element subClassElement = doc.createElement("owl:Class");
		subClassElement.setAttribute("rdf:about", sclSchema+owlClass);
		
		subclass.appendChild(subClassElement);		
		elem.appendChild(subclass);
		
		
//		Element parent = doc.createElement("owl:Class");
//		parent.setAttribute("rdf:ID", owlClass);
//		subclass.appendChild(parent);
				
		return elem;		
	}
	
	/**
	 * Creates and returns an element {@code <}<i>owlProperty</i>{@code />},
	 * representing an individual's Property.
	 *  
	 * @param owlProperty The element's tag.
	 * @return The created element.
	 */
	private Element createIndividualPropertyElement(String owlProperty) {
		Element elem = doc.createElement(owlProperty);		
		return elem;
	}	
	
	/**
	 * Creates and returns an element {@code <}<i>owlProperty</i> {@code rdf:resource = }
	 * <i>id</i>{@code />}, representing an individual's ObjectProperty.
	 *  
	 * @param owlProperty The element's tag.
	 * @param resource The value of the "rdf:resource" attribute.
	 * @return The created element.
	 */
	private Element createRestrictionFromObjectPropertyElement(String owlProperty, 
			String resource) {
		// Create restriction with property only if its value, e.g. the relations filler, is dl relevant 
		if(!isDLRelevantConcept(resource)){
			// Activator.logInfo(" Not converting property "+owlProperty+" because value "+resource+" is not a DL relevant concept!");
			return null;
		}		
		// Activator.logInfo(" Converting property "+owlProperty+"!");
		// Activator.logInfo("    ... with resource: "+resource+"!");
		Element restriction = doc.createElement("owl:Restriction");
		Element onProperty = doc.createElement("owl:onProperty");
		
		Element objectProperty = doc.createElement("owl:ObjectProperty");
		objectProperty.setAttribute("rdf:ID", owlProperty);
		onProperty.appendChild(objectProperty);
		
		Element someValuesFrom = doc.createElement("owl:someValuesFrom");
		//someValuesFrom.setAttribute("rdf:resource", resource);
		
		Element value = doc.createElement("owl:Class");
		value.setAttribute("rdf:about", "#"+resource);
		
		someValuesFrom.appendChild(value);
		restriction.appendChild(onProperty);
		restriction.appendChild(someValuesFrom);
		return restriction;
	}
	
	
	
	/**
	 * Creates datatype property element for vertexIsInGraph and graphIsInVertex 
	 * and perhaps other datatype properties (who knows?)
	 */
	private Element createDatatypeProperty(String tagName, String resource){
		Element elem = doc.createElement(tagName);
		elem.setAttribute("rdf:resource", resource);		
		return elem;
	}
	
	
	
	
	/**
	 * Creates and returns an element {@code <}<i>owlProperty</i> {@code rdf:datatype = }
	 * <i>datatype</i>{@code />}<i>value</i>{@code </}<i>owlProperty</i>{@code >},
	 * representing an individual's DatatypeProperty.
	 *  
	 * @param owlProperty The element's tag.
	 * @param datatype The property's datatype.
	 * @param value The text node representing the property's value.
	 * @return The created element.
	 */
	private Element createIndividualDatatypePropertyElement(String owlProperty, 
			String datatype, String value) {

		Element elem = doc.createElement(owlProperty);
		elem.setAttribute("rdf:datatype", datatype);
		elem.appendChild(doc.createTextNode(value));
		
		return elem;

	}
	
	/**
	 * Helping method that checks, if the given concept name is on the list of concepts, 
	 * that should be converted. If so, returns true; returns false else.
	 * @param conceptName
	 * @return
	 */
	
	private boolean isDLRelevantConcept(String conceptName){
		for(String dlRelevantConcept : dlRelevantConcepts){			
			if(conceptName.contains(dlRelevantConcept)){
				return true;
			}
		}
		return false;
	}

/* V1 edge V2
 * V1 = edge.getAlpha
 * V2 = edge.getOmega
 */
	
	/**
	 * 
	 */
    private Element ensureShortCut(Element intersectionOf, Vertex v, String owlClass, String vElemId){
		// Activator.logInfo("Search shortcut for: " + owlClass);
    	Vector<ShortCut> foundShortCuts = findAllShortCuts(owlClass, v);
    	Vector<Vertex> toConceptList;
    	
    	// vector containing already added to concepts
    	Vector<Vertex> alreadyAddedToConcepts = new Vector<Vertex>();
    	
    	String shortCutReference;
    	for (ShortCut foundShortCut : foundShortCuts) {
//    		Activator.logInfo("   ----------------- ");        		
//    		Activator.logInfo("   Found shortcut: " + foundShortCut.getNewRole());    		
			String newRoleName = foundShortCut.getNewRole();
			String eElemClass = HelperMethods.firstToLowerCase(newRoleName);
			
			toConceptList = new Vector<Vertex>();
			toConceptList = getToConceptList(v, toConceptList, foundShortCut);
			
			//Vertex toConcept = getToConcept(v, foundShortCut);
			if (toConceptList.size() > 0) {
//				Activator.logInfo("     found "+toConceptList.size()+" toConcepts for shortcut!");
				for(Vertex toConcept : toConceptList){
   					//********** NEW ******************
   					
					String toConceptCaseName = SupportFunctions.constructConceptNameForVertex(toConcept);
					
   					// skip current toConcept if it was already added with another shortcut
   					if(alreadyAddedToConcepts.contains(toConcept)){
//   					Activator.logInfo("    not adding concept "+toConceptCaseName+" to list of toConcepts!");
   						continue;
   					}
   					
   					else{
   						alreadyAddedToConcepts.add(toConcept);
   					}
   					
   					// check if there is a more specific shortcut for the current toConcept
   					for (ShortCut s2 : foundShortCuts){
   						if (s2.getFromConceptName().equals(foundShortCut.getFromConceptName()) && s2.getToConceptName().equals(foundShortCut.getToConceptName())){
   							continue;
   						}
   						String currentToConceptName = s2.getToConceptName();
   						Vertex toConceptVertex = null;
   						Graph g = toConcept.getGraph();
   						for (Vertex v2 : g.vertices()){
   							if (v2.getAttributedElementClass().getQualifiedName().equals(currentToConceptName)){
   								toConceptVertex = v2;
   								break;
   							}
   						}
   						// take the current shortcut s2 if it's toConcept is a specialisation of the foundShortcut's toConcept
   						if(toConceptVertex!=null && isSubclassOf(toConceptVertex, foundShortCut.getToConceptName())){
   							newRoleName = s2.getNewRole();
   							eElemClass = HelperMethods.firstToLowerCase(newRoleName);
   						}
   					}
   					
   					//********** NEW END ***************   					   					
   					   
					shortCutReference = vElemId + " " + newRoleName + " " + toConceptCaseName;
					// Avoid double restrictions
					if (!(detectedShortCuts.contains(shortCutReference))) {
					
						Element newObjectProperty = createRestrictionFromObjectPropertyElement(
									       eElemClass, toConceptCaseName);
						if (newObjectProperty == null) {
							// Activator.logInfo("Classes part of shortcuts should be in dlRelevantConcepts!" + toConceptCaseName);
						}
//						Activator.logInfo("   Added shortcut pointing to concept: "+toConceptCaseName);
						intersectionOf.appendChild(newObjectProperty);
//    						Activator.logInfo("newObjectProperty for ShortCut:" + newObjectProperty);
//            				Activator.logInfo("   ----------------- ");
						detectedShortCuts.add(shortCutReference);
						// Activator.logInfo("   Shortcut "+newRoleName+" constructed!");						
   					}
				}
   			}
			else{
				// Activator.logInfo("   \"toConcept\" "+foundShortCut.getToConceptName()+" of shortcut "+newRoleName+" not found in graph");
				// Activator.logInfo("   --> shortcut NOT constructed!");
			}
		}
		return intersectionOf;

    }
	
	private Vector<ShortCut>findAllShortCuts(String conceptName, Vertex v){
		Vector<ShortCut> allShortCuts = new Vector<ShortCut>();
		for(ShortCut shortCut : shortCuts){			
			if(shortCut.isFromConcept(conceptName) || isSubclassOf(v, shortCut.getFromConceptName())){
				allShortCuts.add(shortCut);
			}
		}
		return allShortCuts;
	}
	
	
	private boolean isSubclassOf(Vertex v, String concept2){
		AttributedElementClass vc = v.getAttributedElementClass();
		HashSet<AttributedElementClass> vcSuperClasses = (HashSet<AttributedElementClass>)vc.getAllSuperClasses();
		Vector<String> superClassNames = getVertexNames(vcSuperClasses);
		boolean result = superClassNames.contains(concept2); 
		return result;
	}

	private Vector<Vertex> getToConceptList(Vertex v, Vector<Vertex> toConcepts, ShortCut sc) {
		
		String upperModelToClassName = v.getAttributedElementClass().getQualifiedName();
		// Activator.logInfo("  searching in shortcut for current vertex "+upperModelToClassName+" of graph");
		boolean isPartOfStep = false;
		// is the current vertex a sub-concept of any of the shortcuts steps concepts?
		for(Step s : sc.getPath()){
			if(isSubclassOf(v, s.getConcept())){
				// Activator.logInfo("   found concept in shortcut corresponding to "+upperModelToClassName+": "+s.getConcept());
				upperModelToClassName = s.getConcept();
				isPartOfStep = true;
				break;
			}
			else{
				// Activator.logInfo("   "+upperModelToClassName+" is not a sub-concept of "+s.getConcept());
			}
		}
		// is the current vertex a sub-concept of the shortcuts "toConcept"?
		if(!isPartOfStep){
			if(isSubclassOf(v, sc.getToConceptName())){
				// Activator.logInfo("   vertex "+upperModelToClassName+" is sub-concept of toConcept "+sc.getToConceptName()+" of shortcut");
				upperModelToClassName = sc.getToConceptName();
			}
			else{
				// Activator.logInfo("   "+upperModelToClassName+" is not a sub-concept of toConcept "+sc.getToConceptName()+" of shortcut");
			}
		}
		
		
		String nextRole;
		Vector<Vertex> nextVertices;		
		String stepRole = sc.getNextRole(upperModelToClassName);
		if (stepRole.equals("")){
			toConcepts.add(v);
//			Activator.logInfo("    Adding vertex "+SupportFunctions.constructConceptNameForVertex(v)+" to toConceptsList of shortcut");
			return toConcepts;
		}
		else{
			if (stepRole.equals("false")){
				return toConcepts;
			}
			else {
				nextRole = stepRole;
				nextVertices = getFillersOfRole(v, nextRole);
				if (nextVertices.size()==0) { 
					// Activator.logInfo("Shortcut via " + nextRole + " is not present in graph");
					return toConcepts;
				}
	//			Activator.logInfo("Edges of nextVertex " + nextVertex.getAttributedElementClass().getQualifiedName() + ", possibly candidates for shortcuts:");			
	//			for (Edge e : nextVertex.incidences()) {
	//				Activator.logInfo("...." + e.getAttributedElementClass().getQualifiedName());
	//				Activator.logInfo("........ from class." + e.getAlpha().getAttributedElementClass().getQualifiedName());
	//				Activator.logInfo("........ to class." + e.getOmega().getAttributedElementClass().getQualifiedName());
	//			}
				for (Vertex nv : nextVertices){
					toConcepts = getToConceptList(nv, toConcepts, sc); 					
				}
			}
			return toConcepts;
		}
	}	
	
	private Vector<Vertex> getFillersOfRole (Vertex v, String edgeName) {
		
		Vector<Vertex> fillers = new Vector<Vertex>(); 
		
		String foundEdgeName;
		String vName = v.getAttributedElementClass().getQualifiedName();
		String otherName;
//		Activator.logInfo("Searching an edge: " + edgeName);
		EdgeClass ec;
		HashSet<AttributedElementClass> superEdges = new HashSet<AttributedElementClass>();
		Vector<String> superEdgeNames = new Vector<String>();
		for (Edge e : v.incidences()) {
			foundEdgeName = e.getAttributedElementClass().getQualifiedName();
//			Activator.logInfo("..... perhaps: " + foundEdgeName);	
			ec = (EdgeClass)e.getAttributedElementClass();
			superEdges = (HashSet<AttributedElementClass>)ec.getAllSuperClasses();
			superEdgeNames = getEdgeNames(superEdges);
						
			if(edgeName.endsWith("-of")){
				edgeName = edgeName.split("-of")[0];
			}
			
			if (foundEdgeName.equals(edgeName) || (superEdgeNames.contains(edgeName))){
//				Activator.logInfo("......... found edge: " + foundEdgeName);			

				otherName = e.getAlpha().getAttributedElementClass().getQualifiedName();
				if (vName.equals(otherName)) {
					fillers.add(e.getOmega());
					//return e.getOmega();
				}
				else {
					otherName = e.getOmega().getAttributedElementClass().getQualifiedName();					
					if (vName.equals(otherName))
						fillers.add(e.getAlpha());
						//return e.getAlpha();
				}
			}
		}
		return fillers;
	}
	
	private Vector<String> getEdgeNames (HashSet<AttributedElementClass> edges) {
		Vector<String> edgeNames = new Vector<String>();
		for (AttributedElementClass e : edges) {
			if (e instanceof EdgeClass)
				edgeNames.add(e.getQualifiedName());
		}
		return edgeNames;
	}
	
	private Vector<String> getVertexNames(HashSet<AttributedElementClass> vertices){
		Vector<String> vertexNames = new Vector<String>();
		for(AttributedElementClass v : vertices){
			if(v instanceof VertexClass) vertexNames.add(v.getQualifiedName());
		}
		return vertexNames;
	}
}
