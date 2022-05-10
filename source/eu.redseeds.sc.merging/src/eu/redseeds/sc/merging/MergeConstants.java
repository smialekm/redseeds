package eu.redseeds.sc.merging;

/**
 * This class contains constants used in the merge process.
 *
 */
public class MergeConstants {
	
	//constant for user actions
	public static int MERGE_ACTION_BASE_ID = 100;
	public static int MERGE_ACTION_SKIP_ID = 101;
	public static int MERGE_ACTION_OVERWRITE_ID = 102;
	public static int MERGE_ACTION_AUTOSOLVE_ID = 103;
	public static int MERGE_ACTION_DEFAULT_ID = 101;
	public static String MERGE_ACTION_SKIP_LABEL = "skip";
	public static String MERGE_ACTION_OVERWRITE_LABEL = "overwrite";
	public static String MERGE_ACTION_AUTOSOLVE_LABEL = "autosolve";
	
	public static String[] MERGE_ACTION_LABELS = {
		MERGE_ACTION_SKIP_LABEL,
		MERGE_ACTION_OVERWRITE_LABEL,
		MERGE_ACTION_AUTOSOLVE_LABEL
	};
	
	//conflict type constants
	public static int MERGE_CONFLICT_NAMING_USECASE_ID = 0;
	public static String MERGE_CONFLICT_NAMING_USECASE_LABEL = "Use case naming conflict";
	
	public static int MERGE_CONFLICT_NAMING_REQPACKAGE_ID = 1;
	public static String MERGE_CONFLICT_NAMING_REQPACKAGE_LABEL = "Root requirements packages naming conflict";
	
	public static int MERGE_CONFLICT_ID_USECASE_ID = 2;
	public static String MERGE_CONFLICT_ID_USECASE_LABEL = "Use case/requirement id conflict";
	
	public static int MERGE_CONFLICT_NAMING_REQUIREMENT_ID = 3;
	public static String MERGE_CONFLICT_NAMING_REQUIREMENT_LABEL = "Requirement naming conflict";
	
	public static int MERGE_CONFLICT_ID_REQUIREMENT_ID = 4;
	public static String MERGE_CONFLICT_ID_REQUIREMENT_LABEL = "Requirement/use case id conflict";
	
	public static int MERGE_CONFLICT_NAMING_ACTOR_ID = 5;
	public static String MERGE_CONFLICT_NAMING_ACTOR_LABEL = "Actor naming conflict";
	
	public static int MERGE_CONFLICT_NAMING_NOTION_ID = 6;
	public static String MERGE_CONFLICT_NAMING_NOTION_LABEL = "Notion naming conflict";
	
	public static int MERGE_CONFLICT_NAMING_SYSTEM_ELEMENT_ID = 7;
	public static String MERGE_CONFLICT_NAMING_SYSTEM_ELEMENT_LABEL = "System element naming conflict";
	
	public static int MERGE_CONFLICT_NAMING_DOMAIN_STATEMENT_ID = 8;
	public static String MERGE_CONFLICT_NAMING_DOMAIN_STATEMENT_LABEL = "Domain statement naming conflict";
	
	public static int MERGE_CONFLICT_NAMING_PACKAGE_ID = 9;
	public static String MERGE_CONFLICT_NAMING_PACKAGE_LABEL = "Root UML package naming conflict";
	
	public static int MERGE_CONFLICT_NAMING_ACTORS_PACKAGE_ID = 10;
	public static String MERGE_CONFLICT_NAMING_ACTORS_PACKAGE_LABEL = "Sub-root actors package naming conflict";
	
	public static int MERGE_CONFLICT_NAMING_NOTIONS_PACKAGE_ID = 11;
	public static String MERGE_CONFLICT_NAMING_NOTIONS_PACKAGE_LABEL = "Sub-root notions package naming conflict";
	
	public static int MERGE_CONFLICT_NAMING_SYSTEM_ELEMENTS_PACKAGE_ID = 12;
	public static String MERGE_CONFLICT_NAMING_SYSTEM_ELEMENTS_PACKAGE_LABEL = "Sub-root system elements package naming conflict";
	
	public static int MERGE_CONFLICT_MEANING_TERM_ID = 13;
	public static String MERGE_CONFLICT_MEANING_TERM_LABEL = "Term meaning conflict";
	
	public static String[] MERGE_CONFLICT_LABELS = {
		MERGE_CONFLICT_NAMING_USECASE_LABEL,
		MERGE_CONFLICT_NAMING_REQPACKAGE_LABEL,
		MERGE_CONFLICT_ID_USECASE_LABEL,
		MERGE_CONFLICT_NAMING_REQUIREMENT_LABEL,
		MERGE_CONFLICT_ID_REQUIREMENT_LABEL,
		MERGE_CONFLICT_NAMING_ACTOR_LABEL,
		MERGE_CONFLICT_NAMING_NOTION_LABEL,
		MERGE_CONFLICT_NAMING_SYSTEM_ELEMENT_LABEL,
		MERGE_CONFLICT_NAMING_DOMAIN_STATEMENT_LABEL,
		MERGE_CONFLICT_NAMING_PACKAGE_LABEL,
		MERGE_CONFLICT_NAMING_ACTORS_PACKAGE_LABEL,
		MERGE_CONFLICT_NAMING_NOTIONS_PACKAGE_LABEL,
		MERGE_CONFLICT_NAMING_SYSTEM_ELEMENTS_PACKAGE_LABEL,
		MERGE_CONFLICT_MEANING_TERM_LABEL
	};
	
	public static String MERGE_CONFLICT_NAMING_RESOLVER_POSTFIX = " (imported)";
	public static String MERGE_CONFLICT_ID_RESOLVER_POSTFIX = " (imported)";
	
	
	

}
