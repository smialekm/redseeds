package eu.redseeds.owl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

import javax.net.ServerSocketFactory;

import org.mindswap.pellet.dig.PelletDIGServer;

import de.uni_koblenz.jgralab.Vertex;
import edu.stanford.smi.protegex.owl.model.OWLNamedClass;
import edu.stanford.smi.protegex.owl.model.RDFResource;
import eu.redseeds.owl.reasoning.OWLReasoner;
import eu.redseeds.scl.SCLGraph;


/**
 * This class provides several helping functions like loading text lines from a file
 * into a string vector and a couple of hash functions for use in other classes.
 * 
 * @author Arved Solth
 *
 */
public class SupportFunctions {
	
	private static String commentString = "/";
	private static Process process = null;
	private static Scanner scanner = null;
	
	
	// -------------------------------------------------------------------------------
	
	/**
	 * Helping function loading white and black lists of properties and concepts from files and
	 * returning vectors containing the names of those elements.
	 * @param fileName
	 * @return stringVector
	 */
	public static Vector<String> loadElementsFromFile(String fileName){
        Vector<String> elements = new Vector<String>();
		
//        File f = new File(fileName);
//        Activator.logInfo(" Trying to load file "+f.getAbsolutePath()+"...");
//        System.out.println(" Trying to load file "+f.getAbsolutePath()+"...");
        
        try {     	
        	InputStream stream = SupportFunctions.class.getClassLoader().getResourceAsStream(fileName);
        	if (stream == null) {
//        		Activator.logWarning("Input resource " + fileName + " not found in system configuration path");
        		stream = new FileInputStream(fileName);
        	}	
//        	Activator.logInfo("Input stream is: " + stream);
	        BufferedReader bReader = new BufferedReader(new InputStreamReader(stream));
//	        Activator.logInfo("Opened buffered reader for input stream");
	        String strLine;
	        while((strLine = bReader.readLine())!=null){
	        	if(!strLine.startsWith(commentString)){
	        		elements.add(strLine.trim());	
	        	}	        	
	        }
	        bReader.close();
        } catch(Exception e){	
        	Activator.logError("Error: "+e.getMessage(), e);
        }
        
        return elements;
	}	

	
	// -------------------------------------------------------------------------------
	
	/**
	 * Reading structural shortcuts from file and creating ShortCut objects.
	 */
	
	public static Vector<ShortCut> loadShortCuts(String fileName){
		
		Vector<ShortCut> shortCuts = new Vector<ShortCut>();
	    	// read one string and create one ShortCut 		
		Vector<String> shortCutLines = SupportFunctions.loadElementsFromFile(fileName);
		String fromConceptName;
		String toConceptName;		
		String newRole;		
		String delimiter = " ";
        String [] elements;
        String path;
        // Activator.logInfo(shortCutLines.size() + " shortcuts found in file.");
		for (String shortCutLine : shortCutLines){
		    elements = shortCutLine.split(delimiter);
//	        Activator.logInfo(elements.length + " one shortcut.");
		    if (elements.length >= 3){
		    	fromConceptName = elements[0];
		    	toConceptName = elements[1];
		    	newRole= elements[2];						
		    	path= elements[3];								    
		    	ShortCut shortCut = new ShortCut(fromConceptName, toConceptName, newRole, path);
		    	shortCuts.add(shortCut);
		    }}
		if (shortCuts.size() == 0) 
			return null;
		else 		
			return shortCuts;
	}


	// -------------------------------------------------------------------------------


	
	// -------------------------------------------------------------------------------
	
//	/**
//	 * This function computes and returns a hash for a String[] as the concatenation
//	 * of all strings in the array.
//	 */
//	public static String getHashCode(String[] names) {
//		String hash = "";
//		for (int i = 0; i < names.length; i++) {
//			if (!(names[i] == null))  // if maxNrOfGraphsPerCaseContainer is larger than number of graphs this may be null
//				hash += names[i]+"\r\n";
//		}
//		return hash;
//	}
	
	/**
	 * This function computes and returns a hash for a List of SCLGraphs as the concatenation
	 * of all graphs hashed in the array.
	 * @author dbildh@uni-koblenz.de
	 */
	public static String getHashCode(List<SCLGraph> graphs) {
		StringBuilder hash = new StringBuilder();
		for (SCLGraph graph : graphs) {
			hash.append(getGraphHash(graph));
			hash.append("\r\n");
		}
		return hash.toString();
	}
	
	// -------------------------------------------------------------------------------
		
	public static void writeHash(File saveDir, String hash) {
		// Create file
		FileWriter fstream;
		try {			
			String hashName = saveDir+File.separator+Properties.HASHFILE_NAME;
			fstream = new FileWriter(hashName);
			BufferedWriter out = new BufferedWriter(fstream);
			out.write(hash);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
	
	// -------------------------------------------------------------------------------

	public static File[] getSubdirectoriesOf(File dir){
		FileFilter subDirFilter = new FileFilter(){
			public boolean accept(File file) {
				return file.isDirectory();
			}
		};		
		File[] subDirs = dir.listFiles(subDirFilter); 
		return subDirs;
	}
	
	// -------------------------------------------------------------------------------
	
	public static boolean removeCaseContainerDirectories(){
		boolean success = false;
		File owlDir = new File(Properties.OWL_DIR);
		for(File dir : owlDir.listFiles()){
			if(dir.isDirectory() && dir.getName().startsWith("CaseContainer") && dir.canWrite()){
				for(File f : dir.listFiles()){
					success = f.delete();					
				}
				Activator.logInfo(" Removing folder "+dir.getName());
				success = dir.delete();				
			}
		}
		Activator.logInfo(" Removed folder: "+success);
		return success;
	}
	
	// -------------------------------------------------------------------------------
	
	public static File[] getFilesFrom(File dir){
		FileFilter fileFilter = new FileFilter(){
			public boolean accept(File file){
				return file.isFile();
			}
		};
		File[] files = dir.listFiles(fileFilter);
		return files;
		
	}	

	// -------------------------------------------------------------------------------
	
	/* Add Shortcuts as defining properties
	 * Shortcuts must be loaded first
	 */
	public static boolean computeLists(){
		Activator.logInfo(" Loading lists for computation...");
		Properties.NECESSARY_RSL_PROPERTIES = loadElementsFromFile(Properties.NECESSARY_RSL_PROPERTIES_FILE);
		// if "takeShortCuts>0" combine list of items in files definingRSLProperties and ShortCuts
		switch(Properties.TAKE_SHORTCUTS){
			// don't use shortcuts
			case 0:
				Properties.SHORTCUTS = loadShortCuts(Properties.SHORTCUTS_FILE);
				Properties.DEFINING_RSL_PROPERTIES = loadElementsFromFile(Properties.DEFINING_RSL_PROPERTIES_FILE);
				Properties.DL_RELEVANT_CONCEPTS = loadElementsFromFile(Properties.DL_RELEVANT_CONCEPTS_FILE);
				return true;
			// use normal shortcuts
			case 1:
				Properties.SHORTCUTS = loadShortCuts(Properties.SHORTCUTS_FILE);
				Properties.DEFINING_RSL_PROPERTIES = loadElementsFromFile(Properties.DEFINING_RSL_PROPERTIES_WITH_SHORTCUTS_FILE);
				for(ShortCut shortCut : Properties.SHORTCUTS){	
					Properties.DEFINING_RSL_PROPERTIES.add(shortCut.getNewRole());
				}
				Properties.DL_RELEVANT_CONCEPTS = loadElementsFromFile(Properties.DL_RELEVANT_CONCEPTS_WITH_SHORTCUTS_FILE);
				return true;
				
			// use very short shortcuts
			case 2:
				Properties.SHORTCUTS = loadShortCuts(Properties.VERY_SHORT_SHORTCUTS_FILE);
				Properties.DEFINING_RSL_PROPERTIES = loadElementsFromFile(Properties.DEFINING_RSL_PROPERTIES_WITH_VERY_SHORT_SHORTCUTS_FILE);
				for(ShortCut shortCut : Properties.SHORTCUTS){	
					Properties.DEFINING_RSL_PROPERTIES.add(shortCut.getNewRole());
				}	
				Properties.DL_RELEVANT_CONCEPTS = loadElementsFromFile(Properties.DL_RELEVANT_CONCEPTS_WITH_VERY_SHORT_SHORTCUTS_FILE);
				return true;
		}
		return false;
	}

	public static String getGraphHash(SCLGraph g) {
		return g.getId() + "_"	+ g.getGraphVersion();
	}
	
	// -------------------------------------------------------------------------------
	
	
	/**
	 * Checks if a given OWL class is an "upper model" concept.
	 * 
	 * @param class1 The OWL class to be checked.
	 * @return <code>True</code> if <code>class1</code> is an "upper
	 *         model" concept, <code>false</code> otherwise.
	 */	
	public static boolean isUpperModelConcept(OWLNamedClass owlClass){
		if (!owlClass.getName().contains("_") &&    // Names are concatenated name_caseID_vertexID
				!endsWithDigit(owlClass.getName())) // This one is important for Synsets and Synonyms
			return true;
		else return false;		
	}
	
	// -------------------------------------------------------------------------------

	
	/**
	 * Checks whether a given <code>String</code> end with a digit. 
	 * 
	 * @param s The string to be checked.
	 * @return <code>True</code> if <code>s</code> ends with a digit,
	 *         <code>false</code> otherwise.
	 */	
	private static boolean endsWithDigit(String s){
		s = s.substring(s.length() - 1, s.length());
		if (s.equals("0") ||
				s.equals("1") ||
				s.equals("2") ||
				s.equals("3") ||
				s.equals("4") ||
				s.equals("5") ||
				s.equals("6") ||
				s.equals("7") ||
				s.equals("8") ||
				s.equals("9"))
			return true;
		return false;		
	}
	
	// -------------------------------------------------------------------------------
	
	public static String constructConceptNameForVertex(Vertex v){
		String conceptName = "";
		String graphPart = "";
		String vertexPart = ""; 
		// construct graph part of name
		try{
			graphPart = String.valueOf(v.getGraph().getAttribute("uid"));
			if(graphPart.equals("null")){
				graphPart = String.valueOf(v.getGraph().getId());				
			}
//			Activator.logInfo(" ----- graph-part: "+graphPart);
		}
		catch(NoSuchFieldException e){			
			graphPart = String.valueOf(v.getGraph().getId());
//			Activator.logInfo(" ----- graph-part: "+graphPart);
		}		
		// construct vertex part of name
		try{
			vertexPart = String.valueOf(v.getAttribute("uid"));
			if(vertexPart.equals("null")){
				vertexPart = String.valueOf(v.getId());
			}
		}
		catch(NoSuchFieldException e){
			vertexPart = String.valueOf(v.getId());
		}		
		// put together concept name
		conceptName = v.getAttributedElementClass().getQualifiedName()+"_"+graphPart+"_"+vertexPart;
		
//		if(v instanceof Term){
//			Activator.logInfo(" Constructed concept name \""+conceptName+"\" for term \""+((Term)v).getName()+"\"");
//		}
		
//		Activator.logInfo(" qualified name: "+v.getAttributedElementClass().getQualifiedName());
//		if(v.getAttributedElementClass().getQualifiedName().equals("rsl.rslrequirements.requirementsspecifications.RequirementsSpecification")){			
//			Activator.logInfo("-------------------------------");	
//			Activator.logInfo("-------------------------------");
//			Activator.logInfo(" Constructing owl class for req spec with name:");
//			Activator.logInfo("   "+ conceptName);
//			Activator.logInfo("-------------------------------");	
//			Activator.logInfo("-------------------------------");
//		}
		return conceptName;
	}	
	
	// -------------------------------------------------------------------------------
	
	public static OWLNamedClass getReqSpecFromGraph(SCLGraph g, OWLReasoner reasoner){
		String reqSpecID = "";
		if(String.valueOf(g.getUid()).equals("null")){
			reqSpecID = Properties.REQUIREMENT_SPECIFICARION_TYPE + "_" + g.getId();
//			Activator.logInfo(" Searching for graph with ID "+reqSpecID);			
		}
		else{
			reqSpecID = Properties.REQUIREMENT_SPECIFICARION_TYPE + "_" + g.getUid();
//			Activator.logInfo(" Searching for graph with UID "+reqSpecID);
		}		
		for (RDFResource rs : reasoner.getAllSubclassesOfType(Properties.REQUIREMENT_SPECIFICARION_TYPE)) {
//			Activator.logInfo(" Checking "+rs.getName());
			if(rs.getName().startsWith(reqSpecID)){
				return (OWLNamedClass) rs;
			}
		}
		Activator.logError(" WARNING: Couldn't retrieve Requirement Specification starting with " + reqSpecID +" from ontology!", null);
		return null;
	}
	
	// -------------------------------------------------------------------------------
	
	
	public static String getPelletInst() {
		String strLine = null;
		try {
			FileInputStream fstream = new FileInputStream(
					Properties.PELLET_INST);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			// String strLine;
			while ((strLine = br.readLine()) != null) {
				return strLine;
			}
			fstream.close();
			in.close();
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return strLine;
	}
	
	// -------------------------------------------------------------------------------
	
	/**
	 * Starts the pellet dig server by running the pelletDigServer.bat file.
	 */
	public static void startpellet() {
		Activator.logInfo(" Trying to start pellet dig server using the pelletDigServer.bat file...");
		String Inst = getPelletInst();
		try {
			File batFile = new File(Inst);
			ProcessBuilder processBuilder = new ProcessBuilder(batFile
					.getAbsolutePath());
			processBuilder.directory(batFile.getParentFile());
			process = processBuilder.start();
			scanner = new Scanner(process.getInputStream());
					
			while (scanner.hasNextLine()) {
				Activator.logInfo(scanner.nextLine());
			}
			
		} catch (IOException e) {
			Activator.logError("WARNING: Unable to locate pellet dig server file!", e);
			e.printStackTrace();
			

//			try {
//				scanner.close();
//				process.destroy();
//				Activator.logInfo(process.waitFor());
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}

		}
	}
	
	// -------------------------------------------------------------------------------
	
	/**
	 * Starts the pellet dig server using the pellet api.
	 */
	public static void startPelletDigServer(){
		Activator.logInfo(" Trying to start pellet dig server...");		
		try{
			ServerSocket tempSocket = ServerSocketFactory.getDefault().createServerSocket(Properties.DEFAULT_PORT);
			tempSocket.close();
//			PelletDigServerThread digServer = new PelletDigServerThread();
			Activator.logInfo(" Port "+Properties.DEFAULT_PORT+" free!");
			PelletDIGServer.main(new String[] {});			
			Activator.logInfo(" ...success!");
		}
		catch(Exception e){
			Activator.logError(" CAUTION: Port "+Properties.DEFAULT_PORT+" already in use!", e);
		}
	}
	
	// -------------------------------------------------------------------------------
	
	public static void stopPelletDigServer(){
		Activator.logInfo(" Trying to stop pellet dig server...");
		try{
			Activator.logInfo(" ...success!");
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}

	
