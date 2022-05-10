package eu.redseeds.wrapper;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Vector;

import de.uni_koblenz.jgwnl.JGWNL;
import de.uni_koblenz.jgwnl.exceptions.JGWNLException;
import de.uni_koblenz.jgwnl.info.SemanticRelationshipInfo;
import de.uni_koblenz.jgwnl.info.SynonymInfo;
import de.uni_koblenz.jgwnl.info.SynsetInfo;
import de.uni_koblenz.jgwnl.info.WordInfo;

/**
 * This class provides some wrapper methods that are useful to
 * query the JGraLab WordNet Library (<code>JGWNL</code>).
 * 
 * @author Thorsten Krebs
 */
public class WordNetQuery {

	/**
	 * An instance of the JGraLab WordNet Library (<code>JGWNL</code>).
	 */
	private JGWNL jgwnl = null;

	/**
	 * Constructor. Creates an instance of the <code>JGWNL</code>
	 * and connects to the server with the given URL.
	 *   
	 * @param serverName The name of the <code>JGWNL</code> server. 
	 */
	public WordNetQuery(String serverName) {
		try {
			jgwnl = (JGWNL)Naming.lookup("rmi://" + serverName + "/JGWNL");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Constructor. Instead of taking a <code>String</code> for creating
	 * an <code>JGWNL</code> instance, takes an existing <code>RemoteJGWNL</code>
	 * instance and typecasts it to an <JGWNL> instance to which the WordNetQuery 
	 * will connect.
	 * 
	 */
	public WordNetQuery(JGWNL j){
		jgwnl = j;
	}
	
	
	/**
	 * Retrieves the <code>SynonymInfo</code> with the given unique ID
	 * from the <code>JGWNL</code> server.
	 *  
	 * @param synonymUID The unique of the sought <code>SynonymInfo</code>.
	 * @return The <code>SynonymInfo</code> with the unique ID
	 *         <code>synonymUID</code>. Returns <code>null</code> when there
	 *         is no synonym with the corresponding unique ID.
	 */
	public SynonymInfo retrieveSynonym(long synonymUID) {
		SynonymInfo si = null;
		try {
			si = jgwnl.getSynonymInfo(synonymUID);
		} catch (RemoteException e) {
			System.out.println("RemoteException: couldn't retrieve synonym with uid: " + synonymUID);			
			//e.printStackTrace();
		} catch (JGWNLException e) {
			System.out.println("JGWNLException: couldn't retrieve synonym with uid: " + synonymUID);
			//e.printStackTrace();
		} catch (NullPointerException e) {
			System.out.println("NullPointerException: couldn't retrieve synonym with uid: " + synonymUID);
			//e.printStackTrace();
		}
		return si;
	}
	
	/**
	 * Retrieves the <code>SynsetInfo</code> with the given unique ID
	 * from the <code>JGWNL</code> server.
	 *  
	 * @param synonymUID The unique ID of the sought <code>SynsetInfo</code>.
	 * @return The <code>SynsetInfo</code> with the unique ID
	 *         <code>synonymUID</code>. Returns <code>null</code> when there
	 *         is no synset with the corresponding unique ID.
	 */
	public SynsetInfo retrieveSynset(long synsetUID) {
		SynsetInfo si = null;
		try {
			si = jgwnl.getSynsetInfo(synsetUID);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JGWNLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return si;
	}
	
	/**
	 * Retrieves a <code>WordInfo</code> for the given <code>word</code>
	 * from the <code>JGWNL</code> server.
	 *  
	 * @param word A string representation of the sought <code>WordInfo</code>.
	 * @return The <code>WordInfo</code> for <code>word</code>.
	 */
	public WordInfo retrieveWord(String word) {
		WordInfo wi = null;
		try {
			wi = jgwnl.lookupWord(word);
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (JGWNLException e) {
			e.printStackTrace();
		}
		return wi;
	}
	
	/**
	 * Gets all <code>SemanticRelationshipInfo</code>s of a synset with
	 * the given unique ID from the <code>JGWNL</code> server.
	 *  
	 * @param synsetUID The unique ID of the sought <code>SynonymInfo</code>.
	 * @return The <code>SynonymInfo</code> with the unique ID
	 *         <code>synonymUID</code>. Returns <code>null</code> when there
	 *         is no <code>SynonymInfo</code> with the corresponding unique ID.
	 */
	public Iterable<SemanticRelationshipInfo> getSemanticRelationshipInfos(long synsetUID) {
		try {
			return jgwnl.getSemanticRelationshipInfos(synsetUID);
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (JGWNLException e) {
			e.printStackTrace();
		}
		return new Vector<SemanticRelationshipInfo>();
	}
	
	/**
	 * Retrieves the similarity of two given <code>SynonymInfo</code>s
	 * from the <code>JGWNL</code> server.
	 *  
	 * @param si1 The first <code>SynonymInfo</code> to be compared.
	 * @param si2 The second <code>SynonymInfo</code> to be compared.
	 * @return A value between 0 and 1. The smaller the value, the less
	 *         similar and the larger the value, the more similar the two
	 *         <code>SynonymInfo</code>s are.
	 */
	public double wordnetSimilarity(SynonymInfo si1, SynonymInfo si2) {
		double sim = -1;
		try {
			sim = jgwnl.similarity(si1, si2);
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (JGWNLException e) {
			e.printStackTrace();
		}
		return sim;
	}
	
}
