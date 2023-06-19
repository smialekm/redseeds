package eu.redseeds.sc.current.ui.validation;

/**
 * An interface for all validators used in 
 * determining if SCL element/SCL sub-tree is correct. 
 * 
 * Also, a container for validation-related constants.
 *
 */
public interface IValidate {
	
	/**
	 * validates this element
	 * @return
	 */
	public ValidationResult[] validate(Object element);
	
	/**
	 * validates this element and it's children
	 * @return
	 */
	public ValidationResult[] validateRecursively(Object element);
	
	/**
	 * used in determining complexness of this validation task
	 * @return Should return an integer reflecting a number of tasks to be performed during validation
	 */
	public int getComplexness();
	
	/**
	 * Gets label to display when running this validator
	 * @return
	 */
	public String getLabel();
	
	/**
	 * Info severity constant (value 0) indicating low severity. See IMarker constants.
	 */
	public int SEVERITY_INFO = 0;
	/**
	 * Warning severity constant (value 1) indicating medium severity. See IMarker constants.
	 */
	public int SEVERITY_WARNING = 1;
	/**
	 * Error severity constant (value 2) indicating high severity. See IMarker constants.
	 */
	public int SEVERITY_ERROR = 2;
	
	/**
	 * same as in plugin.xml
	 */
	public String VALIDATION_RESULT_ATTRIBUTE_ELEMENT_ID = "sclElementID";
	/**
	 * same as in plugin.xml
	 */
	public String VALIDATION_RESULT_ATTRIBUTE_PROBLEM_ID = "validationProblemID";
	
	//general validation constants
	public String MSG_WRONG_TYPE = "SCL element of a wrong type supplied";
	public int ID_WRONG_TYPE = 1;
	public String FIX_WRONG_TYPE = "Remove this validation result and take no other action";
	public String MSG_NOT_IMPLEMENTED = "Validation rules not implemented for this element";
	public int ID_NOT_IMPLEMENTED = 2; 

	//use case validation constants
	public String MSG_UC_NO_SCENARIOS = "At least one scenario has to exist in a Use Case";
	public int ID_UC_NO_SCENARIOS = 11;
	public String FIX_MSG_UC_NO_SCENARIOS = "Add one empty scenario";
	
	//scenario validation constants
	/**
	 * Used for all/most scenario and sentence problems
	 */
	public String FIX_MSG_UC_SCENARIOS = "Open Use Case editor to edit problematic scenario";
	
	public String MSG_SENT_COND_EMPTY = "The condition sentence text is empty";
	public int ID_SENT_COND_EMPTY = 21;
	public String FIX_SENT_COND_EMPTY = "TODO";
	public String MSG_SCEN_EMPTY = "The scenario does not contain any sentences";
	public int ID_SCEN_EMPTY = 22;
	public String FIX_SCEN_EMPTY = "TODO";
	public String MSG_SCEN_END_NO_POSTCOND_OR_REJOIN = "The scenario does not end with a post-condition or rejoin";
	public int ID_SCEN_END_NO_POSTCOND_OR_REJOIN = 23;
	public String FIX_SCEN_END_NO_POSTCOND = "TODO";
	public String MSG_SCEN_NAME_EMPTY = "The scenario does not have a name specified";
	public int ID_SCEN_NAME_EMPTY = 24;
	public String FIX_SCEN_NAME_EMPTY = "TODO";
	public String MSG_SENT_INV_UC_NOT_SET = "The invoked use case is not set";
	public int ID_SENT_INV_UC_NOT_SET = 25;
	public String FIX_SENT_INV_UC_NOT_SET = "TODO";
	public String MSG_SENT_INV_UC_SAME = "The invoked use has to be other than invoking one";
	public int ID_SENT_INV_UC_SAME = 26;
	public String FIX_SENT_INV_UC_SAME = "TODO";
	public String MSG_SENT_POSTCOND_EMPTY = "The post-condition sentence text is empty";
	public int ID_SENT_POSTCOND_EMPTY = 27;
	public String FIX_SENT_POSTCOND_EMPTY = "TODO";
	public String MSG_SENT_PRECOND_EMPTY = "The pre-condition sentence text is empty";
	public int ID_SENT_PRECOND_EMPTY = 28;
	public String FIX_SENT_PRECOND_EMPTY = "TODO";
	public String MSG_SENT_SVO_STRUCT = "The sentence is not a valid SVO(O) sentence";
	public int ID_SENT_SVO_STRUCT = 29;
	public String FIX_SENT_SVO_STRUCT = "TODO";
	
	//requirements specification validation constants
	public String MSG_REQPACK_DUPLICATE_NAME = "A requirements package with the same name exist at the same level of the requirements specification";
	public int ID_REQPACK_DUPLICATE_NAME = 31;
	public String FIX_REQPACK_DUPLICATE_NAME = "TODO";
	public String MSG_NO_DEFAULT_USE_CASES_PACKAGE = "Default highest level requirements package \"Use Cases\" not exist. Some default transformations may not working.";
	public int ID_NO_DEFAULT_USE_CASES_PACKAGE = 32;
	public String MSG_OTHER_THAN_DEFAULT_USE_CASES_PACKAGE = "Othaer than default highest level requirements package exist. Their content will be ignored by some default transformations.";
	public int ID_OTHER_THAN_DEFAULT_USE_CASES_PACKAGE = 32;
	
	
	//domain specification validation constants
	public String MSG_ACTPACK_DUPLICATE_NAME = "An actors package with the same name exist at the same level of the requirements specification";
	public int ID_ACTPACK_DUPLICATE_NAME = 41;
	public String FIX_ACTPACK_DUPLICATE_NAME = "TODO";
	public String MSG_NOTPACK_DUPLICATE_NAME = "A notions package with the same name exist at the same level of the requirements specification";
	public int ID_NOTPACK_DUPLICATE_NAME = 42;
	public String FIX_NOTPACK_DUPLICATE_NAME = "TODO";
	public String MSG_SYSELTPACK_DUPLICATE_NAME = "A system elements package with the same name exist at the same level of the requirements specification";
	public int ID_SYSELPACK_DUPLICATE_NAME = 43;
	public String FIX_SYSELPACK_DUPLICATE_NAME = "TODO";
	public String MSG_DOM_STAT_NOT_LINKED = "Domain statement is not linked to the terminology";
	public int ID_DOM_STAT_NOT_LINKED = 44;
	public String FIX_DOM_STAT_NOT_LINKED = "TODO";
	
	//svo sentences vocabulary validation constants
	public String MSG_SENT_SVO_SUBJECT_VOC_MISSING = "The Actor/SystemElement used in the Subject of SVO(O) sentence does not exist";
	public int ID_SENT_SVO_SUBJECT_VOC_MISSING = 51;
	public String FIX_SENT_SVO_SUBJECT_VOC_MISSING = "TODO";
	public String MSG_SENT_SVO_NOTIONS_VOC_MISSING = "The Notion(s) used in the Predicate of SVO(O) sentence does not exist";
	public int ID_SENT_SVO_NOTIONS_VOC_MISSING = 52;
	public String FIX_SENT_SVO_NOTIONS_VOC_MISSING = "TODO";
	public String MSG_SENT_SVO_PHRASES_VOC_MISSING = "The Phrase(s) used in the Predicate of SVO(O) sentence not defined";
	public int ID_SENT_SVO_PHRASES_VOC_MISSING = 53;
	public String FIX_SENT_SVO_PHRASES_VOC_MISSING = "TODO";
	
	//terminology validation constants
	public String MSG_TERM_SYN_ID_EQ_ZERO = "Synonym id not set for the term";
	public int ID_TERM_SYN_ID_EQ_ZERO = 61;
	public String MSG_TERM_NOT_IN_TERMINOLOGY = "Term does not exist in the terminology";
	public int ID_TERM_NOT_IN_TERMINOLOGY = 62;
	public String FIX_TERM_NOT_IN_TERMINOLOGY = "Remove invalid reference to the terminology";
	public String MSG_TERM_ISOLATED_IN_TERMINOLOGY = "Term is isolated inside the terminology (it has no lexical relationships and no semantic relationships).  This term is virtualy useless for similarity measures, so the linkage inside the terminology has to be fixed using the JGWNL Client.";
	public int ID_TERM_ISOLATED_TERMINOLOGY = 63;
	
	//rejoin sentence validation constants
	public String MSG_SENT_REJOIN_EMPTY = "The rejoin sentence has no target set";
	public int ID_SENT_REJOIN_EMPTY = 64;
	public String FIX_SENT_REJOIN_EMPTY = "TODO";
	
	//notion validation constants
	public String MSG_NOTION_DUPLICATE = "Other Notion with the same name exist";
	public int ID_NOTION_DUPLICATE = 71;
	public String MSG_NOTION_LIST_WITHOUT_ELEMENTS = "List without definied elements";
	public int ID_NOTION_LIST_WITHOUT_ELEMENTS = 72;
	public String MSG_NOTION_ATTRIBUTE_WITHOUT_PARENT = "Attribute without parent";
	public int ID_NOTION_ATTRIBUTE_WITHOUT_PARENT = 73;
	public String MSG_NOTION_MULTIPLE_GENERALIZATION = "Multiple inheritance";
	public int ID_NOTION_MULTIPLE_GENERALIZATION = 74;
	public String MSG_DOMAIN_ELEMENT_RELATIONSHIP = "Inproper relation";
	public int ID_DOMAIN_ELEMENT_RELATIONSHIP = 75;
	public String MSG_NOTION_SPECIALISATION = "Inproper generalization";
	public int ID_NOTION_SPECIALISATION = 76;
	public String MSG_ATTRIBUTE = "Inproper attribute";
	public int ID_ATTRIBUTE = 77;
	public String MSG_INVISIBLE_NOTION_ATTRIBUTE = "Invisible notion attribute (not attach to any dataview)";
	public int ID_INVISIBLE_NOTION_ATTRIBUTE = 78;
	public String MSG_UNATTACH_NOTION_ATTRIBUTE = "Unattach notion attribute (not attach to any concept)";
	public int ID_UNATTACH_NOTION_ATTRIBUTE = 79;
	public String MSG_NOTION_CONCEPT = "Concept type notion with improper relations";
	public int ID_NOTION_CONCEPT = 80;
	public String MSG_NOTION_SCREEN = "Screen type notion with improper relations";
	public int ID_NOTION_SCREEN = 81;
	public String MSG_NOTION_MESSAGE = "Message type notion with improper relations";
	public int ID_NOTION_MESSAGE = 82;
	public String MSG_NOTION_CONFIRMATION_DIALOG = "Confirmation dialog type notion with improper relations";
	public int ID_NOTION_CONFIRMATION_DIALOG = 83;
	public String MSG_NOTION_TRIGGER = "Trigger type notion with improper relations";
	public int ID_NOTION_TRIGGER = 84;
	public String MSG_NOTION_ATTRIBUTE = "Attribute type notion with improper relations";
	public int ID_NOTION_ATTRIBUTE = 85;
	public String MSG_NOTION_OPTION = "Option type notion with improper relations";
	public int ID_NOTION_OPTION = 86;
	public String MSG_NOTION_LIST_VIEW = "List view type notion with improper relations";
	public int ID_NOTION_LIST_VIEW = 87;
	public String MSG_NOTION_SIMPLE_VIEW = "Simple view type notion with improper relations";
	public int ID_NOTION_SIMPLE_VIEW = 88;
	public String MSG_NOTION_TREE_VIEW = "Tree view type notion with improper relations";
	public int ID_NOTION_TREE_VIEW = 89;
	public String MSG_NOTION_SIMPLE_VIEW_WITHOUT_ELEMENTS = "Simple view without definied data";
	public int ID_NOTION_SIMPLE_VIEW_WITHOUT_ELEMENTS = 90;
	public String MSG_NOTION_DATA_VIEW_WITHOUT_MAIN_CONCEPT = "Data view without main concept";
	public int ID_NOTION_DATA_VIEW_WITHOUT_MAIN_CONCEPT = 91;
	
	//exceptions
	public String MSG_NOTION_EXCEPTION = "Exception was thrown during notion validation";
	public int ID_NOTION_EXCEPTION = 101;
	public String MSG_DOMAIN_RELATIONSHIP_EXCEPTION = "Exception was thrown during domain relationship validation";
	public int ID_DOMAIN_RELATIONSHIP_EXCEPTION = 102;
	public String MSG_NOTION_SPECIALIZATION_EXCEPTION = "Exception was thrown during notion specialization validation";
	public int ID_NOTION_SPECIALIZATION_EXCEPTION = 103;

}
