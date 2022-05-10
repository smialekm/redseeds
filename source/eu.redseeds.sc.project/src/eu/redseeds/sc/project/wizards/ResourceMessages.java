package eu.redseeds.sc.project.wizards;

import org.eclipse.osgi.util.NLS;

public class ResourceMessages extends NLS {
	private static final String BUNDLE_NAME = "eu.redseeds.sc.project.wizards.messages";
	
	public static String NewProject_windowTitle;
	public static String NewProject_title;
	public static String NewProject_description;

	public static String NewProjectCreationOperation_creating;
	public static String NewProjectCreationOperation_project;
	
	public static String NewProjectCreationOperation_modelFile;
	public static String NewProjectCreationOperation_clipboard;
	public static String NewProjectCreationOperation_output;

	static {
		// load message values from bundle file
		NLS.initializeMessages(BUNDLE_NAME, ResourceMessages.class);
	}
}
