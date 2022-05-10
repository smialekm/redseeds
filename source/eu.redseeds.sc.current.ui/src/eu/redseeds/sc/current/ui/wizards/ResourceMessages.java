package eu.redseeds.sc.current.ui.wizards;

import org.eclipse.osgi.util.NLS;

public class ResourceMessages extends NLS {
	private static final String BUNDLE_NAME = "eu.redseeds.sc.current.ui.wizards.messages";
	
	public static String NewClipboard_windowTitle;
	public static String NewClipboard_title;
	public static String NewClipboard_description;

	public static String NewClipboardCreationOperation_creating;
	public static String NewClipboardCreationOperation_project;
	
	public static String NewClipboardCreationOperation_modelFile;
	public static String NewClipboardCreationOperation_clipboard;
	public static String NewClipboardCreationOperation_output;
	
	public static String NewSCLElement_windowTitle;
	public static String NewSCLElement_title;
	public static String NewSCLElement_description;
	
	public static String NewActor_windowTitle;
	public static String NewActor_title;
	public static String NewActor_description;
	
	public static String NewActorsPackage_windowTitle;
	public static String NewActorsPackage_title;
	public static String NewActorsPackage_description;
	
	public static String NewNotion_windowTitle;
	public static String NewNotion_title;
	public static String NewNotion_description;
	
	public static String NewNotionsPackage_windowTitle;
	public static String NewNotionsPackage_title;
	public static String NewNotionsPackage_description;
	
	public static String NewRequirement_windowTitle;
	public static String NewRequirement_title;
	public static String NewRequirement_description;
	
	public static String NewRequirementsPackage_windowTitle;
	public static String NewRequirementsPackage_title;
	public static String NewRequirementsPackage_description;
	
	public static String NewSystemElement_windowTitle;
	public static String NewSystemElement_title;
	public static String NewSystemElement_description;
	
	public static String NewSystemElementsPackage_windowTitle;
	public static String NewSystemElementsPackage_title;
	public static String NewSystemElementsPackage_description;
	
	public static String NewUseCase_windowTitle;
	public static String NewUseCase_title;
	public static String NewUseCase_description;
	
	public static String Rename_windowTitle;
	public static String Rename_title;
	public static String Rename_description;
	public static String Rename_notion_description;
	public static String Rename_forms_description;
	public static String Rename_label;
	
	public static String Validation_name;
	public static String Validation_uniqueName;
	
	public static String Validation_nameSense;

	public static String CreateDataViewFromConcepts_windowTitle;
	public static String CreateDataViewFromConcepts_title;
	public static String CreateDataViewFromConcepts_description;
	
	public static String CreateConceptFromDataViews_windowTitle;
	public static String CreateConceptFromDataViews_title;
	public static String CreateConceptFromDataViews_description;
	
	public static String ConceptDataViewName_description;
	
	static {
		// load message values from bundle file
		NLS.initializeMessages(BUNDLE_NAME, ResourceMessages.class);
	}
}
