package eu.redseeds.common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.swt.SWT;

public class Constants {
	
	public static String CURRENT_SC_FOLDER_NAME = "CurrentSC";
	public static String REQUIREMENTS_FOLDER_NAME = "Requirements";
	public static String REQUIREMENTS_NODE_NAME = "Requirements Specification";
	public static String DOMAIN_NAME = "Domain Specification";
	public static String ARCHITECTURE_FOLDER_NAME = "Architecture";
	public static String ARCHITECTURE_NODE_NAME = "Architecture";
	public static String DESIGN_FOLDER_NAME = "Design";
	public static String DESIGN_NODE_NAME = "Detailed Design";
	public static String CODE_FOLDER_NAME = "Code";
	public static String DL_CODE_FOLDER_NAME = "DLCode";
	public static String ATTACHMENTS_FOLDER_NAME = "Attachments";
	public static String TRANSFORMATIONS_FOLDER_NAME = "Transformations";
	public static String CLIPBOARD_FOLDER_NAME_PREFIX = "Clipboard ";
	public static String UCDIAGRAMS_FOLDER_NAME = "Use Cases Diagrams";
	public static String NOTIONSDIAGRAMS_FOLDER_NAME = "Notions Diagrams";
	public static String DOMAINDIAGRAMS_FOLDER_NAME = "Domain Diagrams";
	public static String DOMAINDIAGRAMS_NODE_NAME = "Extended Domain Specification";
	public static String DOMAINDIAGRAMS_FILE_NAME = "model.rsldl";
	public static String TESTS_FOLDER_NAME = "Tests";
	public static String TESTS_FILE_NAME = "Tests.xmi";
	
	//preferences names
	public static String SENSE_AUTO_ASSIGN = "senseAutoAssigmentFlag";
	public static String SENSE_AUTO_ADD_ASSIGN = "senseAutoAddAndAssigmentFlag";
	
	public static String CODE_GEN = "codeGenFlag";
	public static String SELECT_GEN_METHOD = "selectGenMethod";
	public static String EA_MODEL_GEN = "eaModelGenFlag";
	public static String MODELIO_MODEL_GEN = "modelioModelGenFlag";
	
	public static String SET_CONFIGURATION = "setConfiguration";
	public static String MODELIO_EXE_PATH = "modelioExePath";
	public static String MODELIO_WORKSPACE = "modelioWorkspaceLocation";
	
	public static String CREATE_MODELIO_PROJECT = "createProjectFlag";
	public static String CREATE_MODELIO_PROJECT_NAME = "createProjectName";
	
	public static String EXISTING_MODELIO_PROJECT = "existedProjectLocation";
	
	public static String SELECT_GEN_OUTPUT_METHOD = "selectGenOutputMethod";
	public static String CODE_GEN_SC_PROJECT_OUTPUT = "codeGenerationSCProject";
	public static String CODE_GEN_OTHER_OUTPUT = "codeGenerationOutputFlag";
	public static String CODE_GEN_OUTPUT_PATH = "codeGenerationOutputPath";

	
	public static String JGWNL_DEFAULT_ADDRESS = "rmi://127.0.0.1/JGWNL";
//	public static String workingdir = System.getProperty("user.dir");
//	public static String JGWNL_PROPERTIES_FILE_NAME = workingdir+"\\..\\eu.redseeds.build\\productBuilder\\jgwnl.properties";
	//aambroziewicz: reverted. 
	//workingdir+"\\..\\eu.redseeds.build\\productBuilder\\" does not exist on client machine after install so we can't use that. 
	//If you need this file in IDE just place it in your eclipse folder with contents as in the productBuilder one.
	public static String JGWNL_PROPERTIES_FILE_NAME = "jgwnl.properties";
	public static String JGWNL_PROPERTIES_FILE_COMMENT = "JGWNL  Configuration File";
	public static String JGWNL_SERVER_ADDRESS_PROPERTY_KEY = "url";
	
	//must have equal length
	public static String HYPERLINK_LEFT_MARKER = "[[";
	public static String HYPERLINK_RIGHT_MARKER = "]]";
	
	public static int HYPERLINK_FONT_COLOR_TARGET_EXISTS = SWT.COLOR_BLUE;
	public static int HYPERLINK_FONT_COLOR_TARGET_DOES_NOT_EXIST = SWT.COLOR_RED;
	public static int HYPERLINK_FONT_STYLE = SWT.BOLD;
	
	public static String TERM_NOUN = "Noun";
	public static String TERM_VERB = "Verb";
	public static String TERM_MODIFIER = "Modifier";
	public static String TERM_DETERMINER = "Determiner";
	public static String TERM_PREPOSITION = "Preposition";
	public static String TERM_ADJECTIVE = "Adjective";
	
	public static String DEFAULT_GLOSS = "Auto generated sense for word:";
	
	/**
	 * stereotype for Application Logic Patterns applied to Requirements Packages
	 */
	public static String ALP_STEREOTYPE = "pattern";
	
	/**
	 * pattern matching insertion points numbering
	 */
	public static String ALP_INSERTION_POINT_PATTERN = "\\.\\.\\.\\s\\(\\d+\\)";
	
	/**
	 * stereotype for Invoke relationship used to denote Insertion Point in Application Logic Patterns logic
	 */
	public static String ALP_INSERTION_POINT_RELATIONSHIP_STEREOTYPE = "invoke";
	
	/**
	 * Get JGWNL server address stored in JGWNL_PROPERTIES_FILE_NAME or 
	 * (if there are problems with reading file) returns JGWNL_DEFAULT_ADDRESS. 
	 * @return JGWNL server url in form "rmi://[ip address/hostname]/JGWNL"
	 */
	public static String getJGWNLAddress() {
		Properties jgwnlProps = new Properties();
		try {
			jgwnlProps.load(new FileInputStream(JGWNL_PROPERTIES_FILE_NAME));
		} catch (FileNotFoundException e) {
			System.out.println("In Constant:" + JGWNL_PROPERTIES_FILE_NAME + " Warning: " + IStatus.WARNING);
			Activator.log("File " + JGWNL_PROPERTIES_FILE_NAME 
					+ " not found. Using the default JGWNL host.", IStatus.WARNING);
			return JGWNL_DEFAULT_ADDRESS;
		} catch (IOException e) {
			Activator.log("Problem reading file " + JGWNL_PROPERTIES_FILE_NAME 
					+ ". Using the default JGWNL host.", IStatus.WARNING);
			return JGWNL_DEFAULT_ADDRESS;
		}
		String url = (String)jgwnlProps.get(JGWNL_SERVER_ADDRESS_PROPERTY_KEY);
		if(url == null) { 
			Activator.log("File " + JGWNL_PROPERTIES_FILE_NAME 
					+ " does not contain entry for " + JGWNL_SERVER_ADDRESS_PROPERTY_KEY 
					+ " property key. Using the default JGWNL host.", IStatus.WARNING);
			return JGWNL_DEFAULT_ADDRESS;
		}
		return url;
		
	}
}
