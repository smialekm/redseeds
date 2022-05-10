/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package eu.redset.emf.model.tsl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see eu.redset.emf.model.tsl.TslFactory
 * @model kind="package"
 * @generated
 */
public interface TslPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "tsl";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://tsl/1.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "tsl";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	TslPackage eINSTANCE = eu.redset.emf.model.tsl.impl.TslPackageImpl.init();

	/**
	 * The meta object id for the '{@link eu.redset.emf.model.tsl.impl.TestSpecificationImpl <em>Test Specification</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.redset.emf.model.tsl.impl.TestSpecificationImpl
	 * @see eu.redset.emf.model.tsl.impl.TslPackageImpl#getTestSpecification()
	 * @generated
	 */
	int TEST_SPECIFICATION = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_SPECIFICATION__NAME = 0;

	/**
	 * The feature id for the '<em><b>EReference0</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_SPECIFICATION__EREFERENCE0 = 1;

	/**
	 * The feature id for the '<em><b>Sc Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_SPECIFICATION__SC_UID = 2;

	/**
	 * The feature id for the '<em><b>Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_SPECIFICATION__UID = 3;

	/**
	 * The number of structural features of the '<em>Test Specification</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_SPECIFICATION_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link eu.redset.emf.model.tsl.impl.TestPackageImpl <em>Test Package</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.redset.emf.model.tsl.impl.TestPackageImpl
	 * @see eu.redset.emf.model.tsl.impl.TslPackageImpl#getTestPackage()
	 * @generated
	 */
	int TEST_PACKAGE = 1;

	/**
	 * The feature id for the '<em><b>EReference0</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_PACKAGE__EREFERENCE0 = 0;

	/**
	 * The feature id for the '<em><b>EReference1</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_PACKAGE__EREFERENCE1 = 1;

	/**
	 * The feature id for the '<em><b>EReference3</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_PACKAGE__EREFERENCE3 = 2;

	/**
	 * The feature id for the '<em><b>Notions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_PACKAGE__NOTIONS = 3;

	/**
	 * The feature id for the '<em><b>NF Tests</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_PACKAGE__NF_TESTS = 4;

	/**
	 * The feature id for the '<em><b>GUI Tests</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_PACKAGE__GUI_TESTS = 5;

	/**
	 * The feature id for the '<em><b>BL Tests</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_PACKAGE__BL_TESTS = 6;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_PACKAGE__NAME = 7;

	/**
	 * The number of structural features of the '<em>Test Package</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_PACKAGE_FEATURE_COUNT = 8;

	/**
	 * The meta object id for the '{@link eu.redset.emf.model.tsl.impl.TestImpl <em>Test</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.redset.emf.model.tsl.impl.TestImpl
	 * @see eu.redset.emf.model.tsl.impl.TslPackageImpl#getTest()
	 * @generated
	 */
	int TEST = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST__NAME = 0;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST__TYPE = 1;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST__DESCRIPTION = 2;

	/**
	 * The feature id for the '<em><b>Classifier Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST__CLASSIFIER_ID = 3;

	/**
	 * The feature id for the '<em><b>Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST__CONTENT = 4;

	/**
	 * The feature id for the '<em><b>Test Precondition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST__TEST_PRECONDITION = 5;

	/**
	 * The feature id for the '<em><b>Test Postcondition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST__TEST_POSTCONDITION = 6;

	/**
	 * The feature id for the '<em><b>Test Result</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST__TEST_RESULT = 7;

	/**
	 * The feature id for the '<em><b>Source</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST__SOURCE = 8;

	/**
	 * The feature id for the '<em><b>Test Parameters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST__TEST_PARAMETERS = 9;

	/**
	 * The number of structural features of the '<em>Test</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_FEATURE_COUNT = 10;


	/**
	 * The meta object id for the '{@link eu.redset.emf.model.tsl.impl.UseCaseTestImpl <em>Use Case Test</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.redset.emf.model.tsl.impl.UseCaseTestImpl
	 * @see eu.redset.emf.model.tsl.impl.TslPackageImpl#getUseCaseTest()
	 * @generated
	 */
	int USE_CASE_TEST = 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USE_CASE_TEST__NAME = TEST__NAME;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USE_CASE_TEST__TYPE = TEST__TYPE;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USE_CASE_TEST__DESCRIPTION = TEST__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Classifier Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USE_CASE_TEST__CLASSIFIER_ID = TEST__CLASSIFIER_ID;

	/**
	 * The feature id for the '<em><b>Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USE_CASE_TEST__CONTENT = TEST__CONTENT;

	/**
	 * The feature id for the '<em><b>Test Precondition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USE_CASE_TEST__TEST_PRECONDITION = TEST__TEST_PRECONDITION;

	/**
	 * The feature id for the '<em><b>Test Postcondition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USE_CASE_TEST__TEST_POSTCONDITION = TEST__TEST_POSTCONDITION;

	/**
	 * The feature id for the '<em><b>Test Result</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USE_CASE_TEST__TEST_RESULT = TEST__TEST_RESULT;

	/**
	 * The feature id for the '<em><b>Source</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USE_CASE_TEST__SOURCE = TEST__SOURCE;

	/**
	 * The feature id for the '<em><b>Test Parameters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USE_CASE_TEST__TEST_PARAMETERS = TEST__TEST_PARAMETERS;

	/**
	 * The feature id for the '<em><b>EReference0</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USE_CASE_TEST__EREFERENCE0 = TEST_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Uc Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USE_CASE_TEST__UC_NAME = TEST_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Uc Trail</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USE_CASE_TEST__UC_TRAIL = TEST_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Invocation Source</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USE_CASE_TEST__INVOCATION_SOURCE = TEST_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USE_CASE_TEST__UID = TEST_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Use Case Test</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USE_CASE_TEST_FEATURE_COUNT = TEST_FEATURE_COUNT + 5;


	/**
	 * The meta object id for the '{@link eu.redset.emf.model.tsl.impl.UseCaseTestScenarioImpl <em>Use Case Test Scenario</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.redset.emf.model.tsl.impl.UseCaseTestScenarioImpl
	 * @see eu.redset.emf.model.tsl.impl.TslPackageImpl#getUseCaseTestScenario()
	 * @generated
	 */
	int USE_CASE_TEST_SCENARIO = 4;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USE_CASE_TEST_SCENARIO__NAME = TEST__NAME;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USE_CASE_TEST_SCENARIO__TYPE = TEST__TYPE;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USE_CASE_TEST_SCENARIO__DESCRIPTION = TEST__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Classifier Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USE_CASE_TEST_SCENARIO__CLASSIFIER_ID = TEST__CLASSIFIER_ID;

	/**
	 * The feature id for the '<em><b>Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USE_CASE_TEST_SCENARIO__CONTENT = TEST__CONTENT;

	/**
	 * The feature id for the '<em><b>Test Precondition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USE_CASE_TEST_SCENARIO__TEST_PRECONDITION = TEST__TEST_PRECONDITION;

	/**
	 * The feature id for the '<em><b>Test Postcondition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USE_CASE_TEST_SCENARIO__TEST_POSTCONDITION = TEST__TEST_POSTCONDITION;

	/**
	 * The feature id for the '<em><b>Test Result</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USE_CASE_TEST_SCENARIO__TEST_RESULT = TEST__TEST_RESULT;

	/**
	 * The feature id for the '<em><b>Source</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USE_CASE_TEST_SCENARIO__SOURCE = TEST__SOURCE;

	/**
	 * The feature id for the '<em><b>Test Parameters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USE_CASE_TEST_SCENARIO__TEST_PARAMETERS = TEST__TEST_PARAMETERS;

	/**
	 * The feature id for the '<em><b>Sentences</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USE_CASE_TEST_SCENARIO__SENTENCES = TEST_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Actor</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USE_CASE_TEST_SCENARIO__ACTOR = TEST_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Precondition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USE_CASE_TEST_SCENARIO__PRECONDITION = TEST_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Postcondition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USE_CASE_TEST_SCENARIO__POSTCONDITION = TEST_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Use Case Test Scenario</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USE_CASE_TEST_SCENARIO_FEATURE_COUNT = TEST_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link eu.redset.emf.model.tsl.impl.UseCaseTestScenarioSentenceImpl <em>Use Case Test Scenario Sentence</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.redset.emf.model.tsl.impl.UseCaseTestScenarioSentenceImpl
	 * @see eu.redset.emf.model.tsl.impl.TslPackageImpl#getUseCaseTestScenarioSentence()
	 * @generated
	 */
	int USE_CASE_TEST_SCENARIO_SENTENCE = 5;

	/**
	 * The feature id for the '<em><b>EReference0</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USE_CASE_TEST_SCENARIO_SENTENCE__EREFERENCE0 = 0;

	/**
	 * The feature id for the '<em><b>EReference1</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USE_CASE_TEST_SCENARIO_SENTENCE__EREFERENCE1 = 1;

	/**
	 * The feature id for the '<em><b>Scenario Sentence</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USE_CASE_TEST_SCENARIO_SENTENCE__SCENARIO_SENTENCE = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USE_CASE_TEST_SCENARIO_SENTENCE__NAME = 3;

	/**
	 * The number of structural features of the '<em>Use Case Test Scenario Sentence</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USE_CASE_TEST_SCENARIO_SENTENCE_FEATURE_COUNT = 4;


	/**
	 * The meta object id for the '{@link eu.redset.emf.model.tsl.impl.TestScenarioImpl <em>Test Scenario</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.redset.emf.model.tsl.impl.TestScenarioImpl
	 * @see eu.redset.emf.model.tsl.impl.TslPackageImpl#getTestScenario()
	 * @generated
	 */
	int TEST_SCENARIO = 6;

	/**
	 * The meta object id for the '{@link eu.redset.emf.model.tsl.impl.TestCaseImpl <em>Test Case</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.redset.emf.model.tsl.impl.TestCaseImpl
	 * @see eu.redset.emf.model.tsl.impl.TslPackageImpl#getTestCase()
	 * @generated
	 */
	int TEST_CASE = 7;

	/**
	 * The meta object id for the '{@link eu.redset.emf.model.tsl.impl.TestCaseSentenceImpl <em>Test Case Sentence</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.redset.emf.model.tsl.impl.TestCaseSentenceImpl
	 * @see eu.redset.emf.model.tsl.impl.TslPackageImpl#getTestCaseSentence()
	 * @generated
	 */
	int TEST_CASE_SENTENCE = 8;

	/**
	 * The meta object id for the '{@link eu.redset.emf.model.tsl.impl.UseCaseTestScenarioInstanceImpl <em>Use Case Test Scenario Instance</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.redset.emf.model.tsl.impl.UseCaseTestScenarioInstanceImpl
	 * @see eu.redset.emf.model.tsl.impl.TslPackageImpl#getUseCaseTestScenarioInstance()
	 * @generated
	 */
	int USE_CASE_TEST_SCENARIO_INSTANCE = 9;

	/**
	 * The meta object id for the '{@link eu.redset.emf.model.tsl.impl.ScenarioSentenceImpl <em>Scenario Sentence</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.redset.emf.model.tsl.impl.ScenarioSentenceImpl
	 * @see eu.redset.emf.model.tsl.impl.TslPackageImpl#getScenarioSentence()
	 * @generated
	 */
	int SCENARIO_SENTENCE = 10;

	/**
	 * The meta object id for the '{@link eu.redset.emf.model.tsl.impl.SVOSentenceImpl <em>SVO Sentence</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.redset.emf.model.tsl.impl.SVOSentenceImpl
	 * @see eu.redset.emf.model.tsl.impl.TslPackageImpl#getSVOSentence()
	 * @generated
	 */
	int SVO_SENTENCE = 11;

	/**
	 * The meta object id for the '{@link eu.redset.emf.model.tsl.impl.ControlSentenceImpl <em>Control Sentence</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.redset.emf.model.tsl.impl.ControlSentenceImpl
	 * @see eu.redset.emf.model.tsl.impl.TslPackageImpl#getControlSentence()
	 * @generated
	 */
	int CONTROL_SENTENCE = 12;

	/**
	 * The meta object id for the '{@link eu.redset.emf.model.tsl.impl.ConditionSentenceImpl <em>Condition Sentence</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.redset.emf.model.tsl.impl.ConditionSentenceImpl
	 * @see eu.redset.emf.model.tsl.impl.TslPackageImpl#getConditionSentence()
	 * @generated
	 */
	int CONDITION_SENTENCE = 13;

	/**
	 * The meta object id for the '{@link eu.redset.emf.model.tsl.impl.ConditionImpl <em>Condition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.redset.emf.model.tsl.impl.ConditionImpl
	 * @see eu.redset.emf.model.tsl.impl.TslPackageImpl#getCondition()
	 * @generated
	 */
	int CONDITION = 16;

	/**
	 * The meta object id for the '{@link eu.redset.emf.model.tsl.impl.PreconditionImpl <em>Precondition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.redset.emf.model.tsl.impl.PreconditionImpl
	 * @see eu.redset.emf.model.tsl.impl.TslPackageImpl#getPrecondition()
	 * @generated
	 */
	int PRECONDITION = 14;

	/**
	 * The meta object id for the '{@link eu.redset.emf.model.tsl.impl.PostconditionImpl <em>Postcondition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.redset.emf.model.tsl.impl.PostconditionImpl
	 * @see eu.redset.emf.model.tsl.impl.TslPackageImpl#getPostcondition()
	 * @generated
	 */
	int POSTCONDITION = 15;

	/**
	 * The meta object id for the '{@link eu.redset.emf.model.tsl.impl.DomainObjectImpl <em>Domain Object</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.redset.emf.model.tsl.impl.DomainObjectImpl
	 * @see eu.redset.emf.model.tsl.impl.TslPackageImpl#getDomainObject()
	 * @generated
	 */
	int DOMAIN_OBJECT = 19;

	/**
	 * The meta object id for the '{@link eu.redset.emf.model.tsl.impl.TestRelationshipImpl <em>Test Relationship</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.redset.emf.model.tsl.impl.TestRelationshipImpl
	 * @see eu.redset.emf.model.tsl.impl.TslPackageImpl#getTestRelationship()
	 * @generated
	 */
	int TEST_RELATIONSHIP = 17;

	/**
	 * The meta object id for the '{@link eu.redset.emf.model.tsl.impl.TestInvocationRelationshipImpl <em>Test Invocation Relationship</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.redset.emf.model.tsl.impl.TestInvocationRelationshipImpl
	 * @see eu.redset.emf.model.tsl.impl.TslPackageImpl#getTestInvocationRelationship()
	 * @generated
	 */
	int TEST_INVOCATION_RELATIONSHIP = 18;

	/**
	 * The meta object id for the '{@link eu.redset.emf.model.tsl.impl.NotionAttributeImpl <em>Notion Attribute</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.redset.emf.model.tsl.impl.NotionAttributeImpl
	 * @see eu.redset.emf.model.tsl.impl.TslPackageImpl#getNotionAttribute()
	 * @generated
	 */
	int NOTION_ATTRIBUTE = 20;

	/**
	 * The meta object id for the '{@link eu.redset.emf.model.tsl.impl.NFTestImpl <em>NF Test</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.redset.emf.model.tsl.impl.NFTestImpl
	 * @see eu.redset.emf.model.tsl.impl.TslPackageImpl#getNFTest()
	 * @generated
	 */
	int NF_TEST = 21;

	/**
	 * The meta object id for the '{@link eu.redset.emf.model.tsl.impl.GUITestImpl <em>GUI Test</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.redset.emf.model.tsl.impl.GUITestImpl
	 * @see eu.redset.emf.model.tsl.impl.TslPackageImpl#getGUITest()
	 * @generated
	 */
	int GUI_TEST = 22;

	/**
	 * The meta object id for the '{@link eu.redset.emf.model.tsl.impl.BLTestImpl <em>BL Test</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.redset.emf.model.tsl.impl.BLTestImpl
	 * @see eu.redset.emf.model.tsl.impl.TslPackageImpl#getBLTest()
	 * @generated
	 */
	int BL_TEST = 23;

	/**
	 * The meta object id for the '{@link eu.redset.emf.model.tsl.impl.NotionImpl <em>Notion</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.redset.emf.model.tsl.impl.NotionImpl
	 * @see eu.redset.emf.model.tsl.impl.TslPackageImpl#getNotion()
	 * @generated
	 */
	int NOTION = 24;

	/**
	 * The meta object id for the '{@link eu.redset.emf.model.tsl.impl.DomainStatementImpl <em>Domain Statement</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.redset.emf.model.tsl.impl.DomainStatementImpl
	 * @see eu.redset.emf.model.tsl.impl.TslPackageImpl#getDomainStatement()
	 * @generated
	 */
	int DOMAIN_STATEMENT = 25;

	/**
	 * The meta object id for the '{@link eu.redset.emf.model.tsl.impl.CConditionImpl <em>CCondition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.redset.emf.model.tsl.impl.CConditionImpl
	 * @see eu.redset.emf.model.tsl.impl.TslPackageImpl#getCCondition()
	 * @generated
	 */
	int CCONDITION = 26;

	/**
	 * The meta object id for the '{@link eu.redset.emf.model.tsl.impl.TestValueImpl <em>Test Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.redset.emf.model.tsl.impl.TestValueImpl
	 * @see eu.redset.emf.model.tsl.impl.TslPackageImpl#getTestValue()
	 * @generated
	 */
	int TEST_VALUE = 27;

	/**
	 * The meta object id for the '{@link eu.redset.emf.model.tsl.impl.TestParameterImpl <em>Test Parameter</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.redset.emf.model.tsl.impl.TestParameterImpl
	 * @see eu.redset.emf.model.tsl.impl.TslPackageImpl#getTestParameter()
	 * @generated
	 */
	int TEST_PARAMETER = 28;

	/**
	 * The meta object id for the '{@link eu.redset.emf.model.tsl.impl.TestInstanceImpl <em>Test Instance</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.redset.emf.model.tsl.impl.TestInstanceImpl
	 * @see eu.redset.emf.model.tsl.impl.TslPackageImpl#getTestInstance()
	 * @generated
	 */
	int TEST_INSTANCE = 29;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_SCENARIO__NAME = TEST__NAME;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_SCENARIO__TYPE = TEST__TYPE;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_SCENARIO__DESCRIPTION = TEST__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Classifier Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_SCENARIO__CLASSIFIER_ID = TEST__CLASSIFIER_ID;

	/**
	 * The feature id for the '<em><b>Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_SCENARIO__CONTENT = TEST__CONTENT;

	/**
	 * The feature id for the '<em><b>Test Precondition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_SCENARIO__TEST_PRECONDITION = TEST__TEST_PRECONDITION;

	/**
	 * The feature id for the '<em><b>Test Postcondition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_SCENARIO__TEST_POSTCONDITION = TEST__TEST_POSTCONDITION;

	/**
	 * The feature id for the '<em><b>Test Result</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_SCENARIO__TEST_RESULT = TEST__TEST_RESULT;

	/**
	 * The feature id for the '<em><b>Source</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_SCENARIO__SOURCE = TEST__SOURCE;

	/**
	 * The feature id for the '<em><b>Test Parameters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_SCENARIO__TEST_PARAMETERS = TEST__TEST_PARAMETERS;

	/**
	 * The feature id for the '<em><b>EReference0</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_SCENARIO__EREFERENCE0 = TEST_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>EReference1</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_SCENARIO__EREFERENCE1 = TEST_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Test Scenario</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_SCENARIO_FEATURE_COUNT = TEST_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_INSTANCE__NAME = TEST__NAME;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_INSTANCE__TYPE = TEST__TYPE;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_INSTANCE__DESCRIPTION = TEST__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Classifier Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_INSTANCE__CLASSIFIER_ID = TEST__CLASSIFIER_ID;

	/**
	 * The feature id for the '<em><b>Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_INSTANCE__CONTENT = TEST__CONTENT;

	/**
	 * The feature id for the '<em><b>Test Precondition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_INSTANCE__TEST_PRECONDITION = TEST__TEST_PRECONDITION;

	/**
	 * The feature id for the '<em><b>Test Postcondition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_INSTANCE__TEST_POSTCONDITION = TEST__TEST_POSTCONDITION;

	/**
	 * The feature id for the '<em><b>Test Result</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_INSTANCE__TEST_RESULT = TEST__TEST_RESULT;

	/**
	 * The feature id for the '<em><b>Source</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_INSTANCE__SOURCE = TEST__SOURCE;

	/**
	 * The feature id for the '<em><b>Test Parameters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_INSTANCE__TEST_PARAMETERS = TEST__TEST_PARAMETERS;

	/**
	 * The feature id for the '<em><b>Attached Tests</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_INSTANCE__ATTACHED_TESTS = TEST_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Test Instance</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_INSTANCE_FEATURE_COUNT = TEST_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CASE__NAME = TEST_INSTANCE__NAME;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CASE__TYPE = TEST_INSTANCE__TYPE;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CASE__DESCRIPTION = TEST_INSTANCE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Classifier Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CASE__CLASSIFIER_ID = TEST_INSTANCE__CLASSIFIER_ID;

	/**
	 * The feature id for the '<em><b>Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CASE__CONTENT = TEST_INSTANCE__CONTENT;

	/**
	 * The feature id for the '<em><b>Test Precondition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CASE__TEST_PRECONDITION = TEST_INSTANCE__TEST_PRECONDITION;

	/**
	 * The feature id for the '<em><b>Test Postcondition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CASE__TEST_POSTCONDITION = TEST_INSTANCE__TEST_POSTCONDITION;

	/**
	 * The feature id for the '<em><b>Test Result</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CASE__TEST_RESULT = TEST_INSTANCE__TEST_RESULT;

	/**
	 * The feature id for the '<em><b>Source</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CASE__SOURCE = TEST_INSTANCE__SOURCE;

	/**
	 * The feature id for the '<em><b>Test Parameters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CASE__TEST_PARAMETERS = TEST_INSTANCE__TEST_PARAMETERS;

	/**
	 * The feature id for the '<em><b>Attached Tests</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CASE__ATTACHED_TESTS = TEST_INSTANCE__ATTACHED_TESTS;

	/**
	 * The feature id for the '<em><b>EReference1</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CASE__EREFERENCE1 = TEST_INSTANCE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Order Number</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CASE__ORDER_NUMBER = TEST_INSTANCE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Precondition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CASE__PRECONDITION = TEST_INSTANCE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Postcondition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CASE__POSTCONDITION = TEST_INSTANCE_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Uc Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CASE__UC_NAME = TEST_INSTANCE_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Uc Trail</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CASE__UC_TRAIL = TEST_INSTANCE_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Uc Scenario Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CASE__UC_SCENARIO_NAME = TEST_INSTANCE_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Sentences</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CASE__SENTENCES = TEST_INSTANCE_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Order Number Global</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CASE__ORDER_NUMBER_GLOBAL = TEST_INSTANCE_FEATURE_COUNT + 8;

	/**
	 * The number of structural features of the '<em>Test Case</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CASE_FEATURE_COUNT = TEST_INSTANCE_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>EReference0</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CASE_SENTENCE__EREFERENCE0 = 0;

	/**
	 * The feature id for the '<em><b>Scenario Sentence</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CASE_SENTENCE__SCENARIO_SENTENCE = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CASE_SENTENCE__NAME = 2;

	/**
	 * The feature id for the '<em><b>Context</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CASE_SENTENCE__CONTEXT = 3;

	/**
	 * The number of structural features of the '<em>Test Case Sentence</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CASE_SENTENCE_FEATURE_COUNT = 4;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USE_CASE_TEST_SCENARIO_INSTANCE__NAME = USE_CASE_TEST_SCENARIO__NAME;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USE_CASE_TEST_SCENARIO_INSTANCE__TYPE = USE_CASE_TEST_SCENARIO__TYPE;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USE_CASE_TEST_SCENARIO_INSTANCE__DESCRIPTION = USE_CASE_TEST_SCENARIO__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Classifier Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USE_CASE_TEST_SCENARIO_INSTANCE__CLASSIFIER_ID = USE_CASE_TEST_SCENARIO__CLASSIFIER_ID;

	/**
	 * The feature id for the '<em><b>Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USE_CASE_TEST_SCENARIO_INSTANCE__CONTENT = USE_CASE_TEST_SCENARIO__CONTENT;

	/**
	 * The feature id for the '<em><b>Test Precondition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USE_CASE_TEST_SCENARIO_INSTANCE__TEST_PRECONDITION = USE_CASE_TEST_SCENARIO__TEST_PRECONDITION;

	/**
	 * The feature id for the '<em><b>Test Postcondition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USE_CASE_TEST_SCENARIO_INSTANCE__TEST_POSTCONDITION = USE_CASE_TEST_SCENARIO__TEST_POSTCONDITION;

	/**
	 * The feature id for the '<em><b>Test Result</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USE_CASE_TEST_SCENARIO_INSTANCE__TEST_RESULT = USE_CASE_TEST_SCENARIO__TEST_RESULT;

	/**
	 * The feature id for the '<em><b>Source</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USE_CASE_TEST_SCENARIO_INSTANCE__SOURCE = USE_CASE_TEST_SCENARIO__SOURCE;

	/**
	 * The feature id for the '<em><b>Test Parameters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USE_CASE_TEST_SCENARIO_INSTANCE__TEST_PARAMETERS = USE_CASE_TEST_SCENARIO__TEST_PARAMETERS;

	/**
	 * The feature id for the '<em><b>Sentences</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USE_CASE_TEST_SCENARIO_INSTANCE__SENTENCES = USE_CASE_TEST_SCENARIO__SENTENCES;

	/**
	 * The feature id for the '<em><b>Actor</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USE_CASE_TEST_SCENARIO_INSTANCE__ACTOR = USE_CASE_TEST_SCENARIO__ACTOR;

	/**
	 * The feature id for the '<em><b>Precondition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USE_CASE_TEST_SCENARIO_INSTANCE__PRECONDITION = USE_CASE_TEST_SCENARIO__PRECONDITION;

	/**
	 * The feature id for the '<em><b>Postcondition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USE_CASE_TEST_SCENARIO_INSTANCE__POSTCONDITION = USE_CASE_TEST_SCENARIO__POSTCONDITION;

	/**
	 * The feature id for the '<em><b>EReference3</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USE_CASE_TEST_SCENARIO_INSTANCE__EREFERENCE3 = USE_CASE_TEST_SCENARIO_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Uc Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USE_CASE_TEST_SCENARIO_INSTANCE__UC_NAME = USE_CASE_TEST_SCENARIO_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Uc Trail</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USE_CASE_TEST_SCENARIO_INSTANCE__UC_TRAIL = USE_CASE_TEST_SCENARIO_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Use Case Test Scenario Instance</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USE_CASE_TEST_SCENARIO_INSTANCE_FEATURE_COUNT = USE_CASE_TEST_SCENARIO_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCENARIO_SENTENCE__NAME = TEST_INSTANCE__NAME;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCENARIO_SENTENCE__TYPE = TEST_INSTANCE__TYPE;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCENARIO_SENTENCE__DESCRIPTION = TEST_INSTANCE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Classifier Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCENARIO_SENTENCE__CLASSIFIER_ID = TEST_INSTANCE__CLASSIFIER_ID;

	/**
	 * The feature id for the '<em><b>Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCENARIO_SENTENCE__CONTENT = TEST_INSTANCE__CONTENT;

	/**
	 * The feature id for the '<em><b>Test Precondition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCENARIO_SENTENCE__TEST_PRECONDITION = TEST_INSTANCE__TEST_PRECONDITION;

	/**
	 * The feature id for the '<em><b>Test Postcondition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCENARIO_SENTENCE__TEST_POSTCONDITION = TEST_INSTANCE__TEST_POSTCONDITION;

	/**
	 * The feature id for the '<em><b>Test Result</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCENARIO_SENTENCE__TEST_RESULT = TEST_INSTANCE__TEST_RESULT;

	/**
	 * The feature id for the '<em><b>Source</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCENARIO_SENTENCE__SOURCE = TEST_INSTANCE__SOURCE;

	/**
	 * The feature id for the '<em><b>Test Parameters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCENARIO_SENTENCE__TEST_PARAMETERS = TEST_INSTANCE__TEST_PARAMETERS;

	/**
	 * The feature id for the '<em><b>Attached Tests</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCENARIO_SENTENCE__ATTACHED_TESTS = TEST_INSTANCE__ATTACHED_TESTS;

	/**
	 * The number of structural features of the '<em>Scenario Sentence</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCENARIO_SENTENCE_FEATURE_COUNT = TEST_INSTANCE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SVO_SENTENCE__NAME = SCENARIO_SENTENCE__NAME;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SVO_SENTENCE__TYPE = SCENARIO_SENTENCE__TYPE;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SVO_SENTENCE__DESCRIPTION = SCENARIO_SENTENCE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Classifier Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SVO_SENTENCE__CLASSIFIER_ID = SCENARIO_SENTENCE__CLASSIFIER_ID;

	/**
	 * The feature id for the '<em><b>Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SVO_SENTENCE__CONTENT = SCENARIO_SENTENCE__CONTENT;

	/**
	 * The feature id for the '<em><b>Test Precondition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SVO_SENTENCE__TEST_PRECONDITION = SCENARIO_SENTENCE__TEST_PRECONDITION;

	/**
	 * The feature id for the '<em><b>Test Postcondition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SVO_SENTENCE__TEST_POSTCONDITION = SCENARIO_SENTENCE__TEST_POSTCONDITION;

	/**
	 * The feature id for the '<em><b>Test Result</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SVO_SENTENCE__TEST_RESULT = SCENARIO_SENTENCE__TEST_RESULT;

	/**
	 * The feature id for the '<em><b>Source</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SVO_SENTENCE__SOURCE = SCENARIO_SENTENCE__SOURCE;

	/**
	 * The feature id for the '<em><b>Test Parameters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SVO_SENTENCE__TEST_PARAMETERS = SCENARIO_SENTENCE__TEST_PARAMETERS;

	/**
	 * The feature id for the '<em><b>Attached Tests</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SVO_SENTENCE__ATTACHED_TESTS = SCENARIO_SENTENCE__ATTACHED_TESTS;

	/**
	 * The feature id for the '<em><b>Direct Object</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SVO_SENTENCE__DIRECT_OBJECT = SCENARIO_SENTENCE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Indirect Object</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SVO_SENTENCE__INDIRECT_OBJECT = SCENARIO_SENTENCE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Number</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SVO_SENTENCE__NUMBER = SCENARIO_SENTENCE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Predicate</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SVO_SENTENCE__PREDICATE = SCENARIO_SENTENCE_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>SVO Sentence</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SVO_SENTENCE_FEATURE_COUNT = SCENARIO_SENTENCE_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTROL_SENTENCE__NAME = SCENARIO_SENTENCE__NAME;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTROL_SENTENCE__TYPE = SCENARIO_SENTENCE__TYPE;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTROL_SENTENCE__DESCRIPTION = SCENARIO_SENTENCE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Classifier Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTROL_SENTENCE__CLASSIFIER_ID = SCENARIO_SENTENCE__CLASSIFIER_ID;

	/**
	 * The feature id for the '<em><b>Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTROL_SENTENCE__CONTENT = SCENARIO_SENTENCE__CONTENT;

	/**
	 * The feature id for the '<em><b>Test Precondition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTROL_SENTENCE__TEST_PRECONDITION = SCENARIO_SENTENCE__TEST_PRECONDITION;

	/**
	 * The feature id for the '<em><b>Test Postcondition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTROL_SENTENCE__TEST_POSTCONDITION = SCENARIO_SENTENCE__TEST_POSTCONDITION;

	/**
	 * The feature id for the '<em><b>Test Result</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTROL_SENTENCE__TEST_RESULT = SCENARIO_SENTENCE__TEST_RESULT;

	/**
	 * The feature id for the '<em><b>Source</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTROL_SENTENCE__SOURCE = SCENARIO_SENTENCE__SOURCE;

	/**
	 * The feature id for the '<em><b>Test Parameters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTROL_SENTENCE__TEST_PARAMETERS = SCENARIO_SENTENCE__TEST_PARAMETERS;

	/**
	 * The feature id for the '<em><b>Attached Tests</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTROL_SENTENCE__ATTACHED_TESTS = SCENARIO_SENTENCE__ATTACHED_TESTS;

	/**
	 * The feature id for the '<em><b>Invocation</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTROL_SENTENCE__INVOCATION = SCENARIO_SENTENCE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Control Sentence</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTROL_SENTENCE_FEATURE_COUNT = SCENARIO_SENTENCE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITION_SENTENCE__NAME = SCENARIO_SENTENCE__NAME;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITION_SENTENCE__TYPE = SCENARIO_SENTENCE__TYPE;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITION_SENTENCE__DESCRIPTION = SCENARIO_SENTENCE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Classifier Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITION_SENTENCE__CLASSIFIER_ID = SCENARIO_SENTENCE__CLASSIFIER_ID;

	/**
	 * The feature id for the '<em><b>Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITION_SENTENCE__CONTENT = SCENARIO_SENTENCE__CONTENT;

	/**
	 * The feature id for the '<em><b>Test Precondition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITION_SENTENCE__TEST_PRECONDITION = SCENARIO_SENTENCE__TEST_PRECONDITION;

	/**
	 * The feature id for the '<em><b>Test Postcondition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITION_SENTENCE__TEST_POSTCONDITION = SCENARIO_SENTENCE__TEST_POSTCONDITION;

	/**
	 * The feature id for the '<em><b>Test Result</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITION_SENTENCE__TEST_RESULT = SCENARIO_SENTENCE__TEST_RESULT;

	/**
	 * The feature id for the '<em><b>Source</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITION_SENTENCE__SOURCE = SCENARIO_SENTENCE__SOURCE;

	/**
	 * The feature id for the '<em><b>Test Parameters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITION_SENTENCE__TEST_PARAMETERS = SCENARIO_SENTENCE__TEST_PARAMETERS;

	/**
	 * The feature id for the '<em><b>Attached Tests</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITION_SENTENCE__ATTACHED_TESTS = SCENARIO_SENTENCE__ATTACHED_TESTS;

	/**
	 * The number of structural features of the '<em>Condition Sentence</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITION_SENTENCE_FEATURE_COUNT = SCENARIO_SENTENCE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITION__NAME = 0;

	/**
	 * The number of structural features of the '<em>Condition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITION_FEATURE_COUNT = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRECONDITION__NAME = CONDITION__NAME;

	/**
	 * The number of structural features of the '<em>Precondition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRECONDITION_FEATURE_COUNT = CONDITION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POSTCONDITION__NAME = CONDITION__NAME;

	/**
	 * The number of structural features of the '<em>Postcondition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POSTCONDITION_FEATURE_COUNT = CONDITION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_RELATIONSHIP__TARGET = 0;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_RELATIONSHIP__TYPE = 1;

	/**
	 * The number of structural features of the '<em>Test Relationship</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_RELATIONSHIP_FEATURE_COUNT = 2;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_INVOCATION_RELATIONSHIP__TARGET = TEST_RELATIONSHIP__TARGET;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_INVOCATION_RELATIONSHIP__TYPE = TEST_RELATIONSHIP__TYPE;

	/**
	 * The feature id for the '<em><b>EReference0</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_INVOCATION_RELATIONSHIP__EREFERENCE0 = TEST_RELATIONSHIP_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Invocation Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_INVOCATION_RELATIONSHIP__INVOCATION_TARGET = TEST_RELATIONSHIP_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_INVOCATION_RELATIONSHIP__UID = TEST_RELATIONSHIP_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Include Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_INVOCATION_RELATIONSHIP__INCLUDE_TYPE = TEST_RELATIONSHIP_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Test Invocation Relationship</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_INVOCATION_RELATIONSHIP_FEATURE_COUNT = TEST_RELATIONSHIP_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN_OBJECT__NAME = TEST_INSTANCE__NAME;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN_OBJECT__TYPE = TEST_INSTANCE__TYPE;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN_OBJECT__DESCRIPTION = TEST_INSTANCE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Classifier Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN_OBJECT__CLASSIFIER_ID = TEST_INSTANCE__CLASSIFIER_ID;

	/**
	 * The feature id for the '<em><b>Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN_OBJECT__CONTENT = TEST_INSTANCE__CONTENT;

	/**
	 * The feature id for the '<em><b>Test Precondition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN_OBJECT__TEST_PRECONDITION = TEST_INSTANCE__TEST_PRECONDITION;

	/**
	 * The feature id for the '<em><b>Test Postcondition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN_OBJECT__TEST_POSTCONDITION = TEST_INSTANCE__TEST_POSTCONDITION;

	/**
	 * The feature id for the '<em><b>Test Result</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN_OBJECT__TEST_RESULT = TEST_INSTANCE__TEST_RESULT;

	/**
	 * The feature id for the '<em><b>Source</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN_OBJECT__SOURCE = TEST_INSTANCE__SOURCE;

	/**
	 * The feature id for the '<em><b>Test Parameters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN_OBJECT__TEST_PARAMETERS = TEST_INSTANCE__TEST_PARAMETERS;

	/**
	 * The feature id for the '<em><b>Attached Tests</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN_OBJECT__ATTACHED_TESTS = TEST_INSTANCE__ATTACHED_TESTS;

	/**
	 * The feature id for the '<em><b>Input Data</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN_OBJECT__INPUT_DATA = TEST_INSTANCE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Domain Object Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN_OBJECT__DOMAIN_OBJECT_TYPE = TEST_INSTANCE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Domain Object</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN_OBJECT_FEATURE_COUNT = TEST_INSTANCE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NOTION_ATTRIBUTE__TYPE = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NOTION_ATTRIBUTE__NAME = 1;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NOTION_ATTRIBUTE__DESCRIPTION = 2;

	/**
	 * The number of structural features of the '<em>Notion Attribute</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NOTION_ATTRIBUTE_FEATURE_COUNT = 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NF_TEST__NAME = TEST__NAME;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NF_TEST__TYPE = TEST__TYPE;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NF_TEST__DESCRIPTION = TEST__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Classifier Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NF_TEST__CLASSIFIER_ID = TEST__CLASSIFIER_ID;

	/**
	 * The feature id for the '<em><b>Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NF_TEST__CONTENT = TEST__CONTENT;

	/**
	 * The feature id for the '<em><b>Test Precondition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NF_TEST__TEST_PRECONDITION = TEST__TEST_PRECONDITION;

	/**
	 * The feature id for the '<em><b>Test Postcondition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NF_TEST__TEST_POSTCONDITION = TEST__TEST_POSTCONDITION;

	/**
	 * The feature id for the '<em><b>Test Result</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NF_TEST__TEST_RESULT = TEST__TEST_RESULT;

	/**
	 * The feature id for the '<em><b>Source</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NF_TEST__SOURCE = TEST__SOURCE;

	/**
	 * The feature id for the '<em><b>Test Parameters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NF_TEST__TEST_PARAMETERS = TEST__TEST_PARAMETERS;

	/**
	 * The feature id for the '<em><b>Nfr Trail</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NF_TEST__NFR_TRAIL = TEST_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NF_TEST__UID = TEST_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>NF Test</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NF_TEST_FEATURE_COUNT = TEST_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUI_TEST__NAME = TEST__NAME;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUI_TEST__TYPE = TEST__TYPE;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUI_TEST__DESCRIPTION = TEST__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Classifier Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUI_TEST__CLASSIFIER_ID = TEST__CLASSIFIER_ID;

	/**
	 * The feature id for the '<em><b>Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUI_TEST__CONTENT = TEST__CONTENT;

	/**
	 * The feature id for the '<em><b>Test Precondition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUI_TEST__TEST_PRECONDITION = TEST__TEST_PRECONDITION;

	/**
	 * The feature id for the '<em><b>Test Postcondition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUI_TEST__TEST_POSTCONDITION = TEST__TEST_POSTCONDITION;

	/**
	 * The feature id for the '<em><b>Test Result</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUI_TEST__TEST_RESULT = TEST__TEST_RESULT;

	/**
	 * The feature id for the '<em><b>Source</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUI_TEST__SOURCE = TEST__SOURCE;

	/**
	 * The feature id for the '<em><b>Test Parameters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUI_TEST__TEST_PARAMETERS = TEST__TEST_PARAMETERS;

	/**
	 * The number of structural features of the '<em>GUI Test</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUI_TEST_FEATURE_COUNT = TEST_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BL_TEST__NAME = TEST__NAME;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BL_TEST__TYPE = TEST__TYPE;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BL_TEST__DESCRIPTION = TEST__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Classifier Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BL_TEST__CLASSIFIER_ID = TEST__CLASSIFIER_ID;

	/**
	 * The feature id for the '<em><b>Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BL_TEST__CONTENT = TEST__CONTENT;

	/**
	 * The feature id for the '<em><b>Test Precondition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BL_TEST__TEST_PRECONDITION = TEST__TEST_PRECONDITION;

	/**
	 * The feature id for the '<em><b>Test Postcondition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BL_TEST__TEST_POSTCONDITION = TEST__TEST_POSTCONDITION;

	/**
	 * The feature id for the '<em><b>Test Result</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BL_TEST__TEST_RESULT = TEST__TEST_RESULT;

	/**
	 * The feature id for the '<em><b>Source</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BL_TEST__SOURCE = TEST__SOURCE;

	/**
	 * The feature id for the '<em><b>Test Parameters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BL_TEST__TEST_PARAMETERS = TEST__TEST_PARAMETERS;

	/**
	 * The number of structural features of the '<em>BL Test</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BL_TEST_FEATURE_COUNT = TEST_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NOTION__NAME = TEST__NAME;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NOTION__TYPE = TEST__TYPE;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NOTION__DESCRIPTION = TEST__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Classifier Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NOTION__CLASSIFIER_ID = TEST__CLASSIFIER_ID;

	/**
	 * The feature id for the '<em><b>Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NOTION__CONTENT = TEST__CONTENT;

	/**
	 * The feature id for the '<em><b>Test Precondition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NOTION__TEST_PRECONDITION = TEST__TEST_PRECONDITION;

	/**
	 * The feature id for the '<em><b>Test Postcondition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NOTION__TEST_POSTCONDITION = TEST__TEST_POSTCONDITION;

	/**
	 * The feature id for the '<em><b>Test Result</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NOTION__TEST_RESULT = TEST__TEST_RESULT;

	/**
	 * The feature id for the '<em><b>Source</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NOTION__SOURCE = TEST__SOURCE;

	/**
	 * The feature id for the '<em><b>Test Parameters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NOTION__TEST_PARAMETERS = TEST__TEST_PARAMETERS;

	/**
	 * The feature id for the '<em><b>Domain Statements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NOTION__DOMAIN_STATEMENTS = TEST_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Notion Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NOTION__NOTION_NAME = TEST_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Notion Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NOTION__NOTION_DESCRIPTION = TEST_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Notion Trail</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NOTION__NOTION_TRAIL = TEST_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Attributes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NOTION__ATTRIBUTES = TEST_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NOTION__UID = TEST_FEATURE_COUNT + 5;

	/**
	 * The number of structural features of the '<em>Notion</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NOTION_FEATURE_COUNT = TEST_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN_STATEMENT__NAME = TEST__NAME;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN_STATEMENT__TYPE = TEST__TYPE;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN_STATEMENT__DESCRIPTION = TEST__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Classifier Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN_STATEMENT__CLASSIFIER_ID = TEST__CLASSIFIER_ID;

	/**
	 * The feature id for the '<em><b>Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN_STATEMENT__CONTENT = TEST__CONTENT;

	/**
	 * The feature id for the '<em><b>Test Precondition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN_STATEMENT__TEST_PRECONDITION = TEST__TEST_PRECONDITION;

	/**
	 * The feature id for the '<em><b>Test Postcondition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN_STATEMENT__TEST_POSTCONDITION = TEST__TEST_POSTCONDITION;

	/**
	 * The feature id for the '<em><b>Test Result</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN_STATEMENT__TEST_RESULT = TEST__TEST_RESULT;

	/**
	 * The feature id for the '<em><b>Source</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN_STATEMENT__SOURCE = TEST__SOURCE;

	/**
	 * The feature id for the '<em><b>Test Parameters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN_STATEMENT__TEST_PARAMETERS = TEST__TEST_PARAMETERS;

	/**
	 * The feature id for the '<em><b>Direct Notion</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN_STATEMENT__DIRECT_NOTION = TEST_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Indirect Notion</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN_STATEMENT__INDIRECT_NOTION = TEST_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Phrase Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN_STATEMENT__PHRASE_NAME = TEST_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Phrase Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN_STATEMENT__PHRASE_DESCRIPTION = TEST_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN_STATEMENT__UID = TEST_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Domain Statement</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOMAIN_STATEMENT_FEATURE_COUNT = TEST_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CCONDITION__NAME = 0;

	/**
	 * The number of structural features of the '<em>CCondition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CCONDITION_FEATURE_COUNT = 1;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_VALUE__VALUE = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_VALUE__NAME = 1;

	/**
	 * The number of structural features of the '<em>Test Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_VALUE_FEATURE_COUNT = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_PARAMETER__NAME = 0;

	/**
	 * The feature id for the '<em><b>Domain</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_PARAMETER__DOMAIN = 1;

	/**
	 * The feature id for the '<em><b>Expected Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_PARAMETER__EXPECTED_VALUE = 2;

	/**
	 * The number of structural features of the '<em>Test Parameter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_PARAMETER_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link eu.redset.emf.model.tsl.impl.NFTestInstanceImpl <em>NF Test Instance</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.redset.emf.model.tsl.impl.NFTestInstanceImpl
	 * @see eu.redset.emf.model.tsl.impl.TslPackageImpl#getNFTestInstance()
	 * @generated
	 */
	int NF_TEST_INSTANCE = 30;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NF_TEST_INSTANCE__NAME = TEST_INSTANCE__NAME;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NF_TEST_INSTANCE__TYPE = TEST_INSTANCE__TYPE;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NF_TEST_INSTANCE__DESCRIPTION = TEST_INSTANCE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Classifier Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NF_TEST_INSTANCE__CLASSIFIER_ID = TEST_INSTANCE__CLASSIFIER_ID;

	/**
	 * The feature id for the '<em><b>Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NF_TEST_INSTANCE__CONTENT = TEST_INSTANCE__CONTENT;

	/**
	 * The feature id for the '<em><b>Test Precondition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NF_TEST_INSTANCE__TEST_PRECONDITION = TEST_INSTANCE__TEST_PRECONDITION;

	/**
	 * The feature id for the '<em><b>Test Postcondition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NF_TEST_INSTANCE__TEST_POSTCONDITION = TEST_INSTANCE__TEST_POSTCONDITION;

	/**
	 * The feature id for the '<em><b>Test Result</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NF_TEST_INSTANCE__TEST_RESULT = TEST_INSTANCE__TEST_RESULT;

	/**
	 * The feature id for the '<em><b>Source</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NF_TEST_INSTANCE__SOURCE = TEST_INSTANCE__SOURCE;

	/**
	 * The feature id for the '<em><b>Test Parameters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NF_TEST_INSTANCE__TEST_PARAMETERS = TEST_INSTANCE__TEST_PARAMETERS;

	/**
	 * The feature id for the '<em><b>Attached Tests</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NF_TEST_INSTANCE__ATTACHED_TESTS = TEST_INSTANCE__ATTACHED_TESTS;

	/**
	 * The number of structural features of the '<em>NF Test Instance</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NF_TEST_INSTANCE_FEATURE_COUNT = TEST_INSTANCE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link eu.redset.emf.model.tsl.impl.GUITestInstanceImpl <em>GUI Test Instance</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.redset.emf.model.tsl.impl.GUITestInstanceImpl
	 * @see eu.redset.emf.model.tsl.impl.TslPackageImpl#getGUITestInstance()
	 * @generated
	 */
	int GUI_TEST_INSTANCE = 31;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUI_TEST_INSTANCE__NAME = TEST_INSTANCE__NAME;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUI_TEST_INSTANCE__TYPE = TEST_INSTANCE__TYPE;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUI_TEST_INSTANCE__DESCRIPTION = TEST_INSTANCE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Classifier Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUI_TEST_INSTANCE__CLASSIFIER_ID = TEST_INSTANCE__CLASSIFIER_ID;

	/**
	 * The feature id for the '<em><b>Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUI_TEST_INSTANCE__CONTENT = TEST_INSTANCE__CONTENT;

	/**
	 * The feature id for the '<em><b>Test Precondition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUI_TEST_INSTANCE__TEST_PRECONDITION = TEST_INSTANCE__TEST_PRECONDITION;

	/**
	 * The feature id for the '<em><b>Test Postcondition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUI_TEST_INSTANCE__TEST_POSTCONDITION = TEST_INSTANCE__TEST_POSTCONDITION;

	/**
	 * The feature id for the '<em><b>Test Result</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUI_TEST_INSTANCE__TEST_RESULT = TEST_INSTANCE__TEST_RESULT;

	/**
	 * The feature id for the '<em><b>Source</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUI_TEST_INSTANCE__SOURCE = TEST_INSTANCE__SOURCE;

	/**
	 * The feature id for the '<em><b>Test Parameters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUI_TEST_INSTANCE__TEST_PARAMETERS = TEST_INSTANCE__TEST_PARAMETERS;

	/**
	 * The feature id for the '<em><b>Attached Tests</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUI_TEST_INSTANCE__ATTACHED_TESTS = TEST_INSTANCE__ATTACHED_TESTS;

	/**
	 * The number of structural features of the '<em>GUI Test Instance</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GUI_TEST_INSTANCE_FEATURE_COUNT = TEST_INSTANCE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link eu.redset.emf.model.tsl.impl.BLTestInstanceImpl <em>BL Test Instance</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.redset.emf.model.tsl.impl.BLTestInstanceImpl
	 * @see eu.redset.emf.model.tsl.impl.TslPackageImpl#getBLTestInstance()
	 * @generated
	 */
	int BL_TEST_INSTANCE = 32;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BL_TEST_INSTANCE__NAME = TEST_INSTANCE__NAME;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BL_TEST_INSTANCE__TYPE = TEST_INSTANCE__TYPE;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BL_TEST_INSTANCE__DESCRIPTION = TEST_INSTANCE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Classifier Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BL_TEST_INSTANCE__CLASSIFIER_ID = TEST_INSTANCE__CLASSIFIER_ID;

	/**
	 * The feature id for the '<em><b>Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BL_TEST_INSTANCE__CONTENT = TEST_INSTANCE__CONTENT;

	/**
	 * The feature id for the '<em><b>Test Precondition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BL_TEST_INSTANCE__TEST_PRECONDITION = TEST_INSTANCE__TEST_PRECONDITION;

	/**
	 * The feature id for the '<em><b>Test Postcondition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BL_TEST_INSTANCE__TEST_POSTCONDITION = TEST_INSTANCE__TEST_POSTCONDITION;

	/**
	 * The feature id for the '<em><b>Test Result</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BL_TEST_INSTANCE__TEST_RESULT = TEST_INSTANCE__TEST_RESULT;

	/**
	 * The feature id for the '<em><b>Source</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BL_TEST_INSTANCE__SOURCE = TEST_INSTANCE__SOURCE;

	/**
	 * The feature id for the '<em><b>Test Parameters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BL_TEST_INSTANCE__TEST_PARAMETERS = TEST_INSTANCE__TEST_PARAMETERS;

	/**
	 * The feature id for the '<em><b>Attached Tests</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BL_TEST_INSTANCE__ATTACHED_TESTS = TEST_INSTANCE__ATTACHED_TESTS;

	/**
	 * The number of structural features of the '<em>BL Test Instance</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BL_TEST_INSTANCE_FEATURE_COUNT = TEST_INSTANCE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link eu.redset.emf.model.tsl.impl.TestDataValuesContextImpl <em>Test Data Values Context</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.redset.emf.model.tsl.impl.TestDataValuesContextImpl
	 * @see eu.redset.emf.model.tsl.impl.TslPackageImpl#getTestDataValuesContext()
	 * @generated
	 */
	int TEST_DATA_VALUES_CONTEXT = 33;

	/**
	 * The number of structural features of the '<em>Test Data Values Context</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_DATA_VALUES_CONTEXT_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link eu.redset.emf.model.tsl.DomainObjectType <em>Domain Object Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eu.redset.emf.model.tsl.DomainObjectType
	 * @see eu.redset.emf.model.tsl.impl.TslPackageImpl#getDomainObjectType()
	 * @generated
	 */
	int DOMAIN_OBJECT_TYPE = 34;

	/**
	 * Returns the meta object for class '{@link eu.redset.emf.model.tsl.TestSpecification <em>Test Specification</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Test Specification</em>'.
	 * @see eu.redset.emf.model.tsl.TestSpecification
	 * @generated
	 */
	EClass getTestSpecification();

	/**
	 * Returns the meta object for the attribute '{@link eu.redset.emf.model.tsl.TestSpecification#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see eu.redset.emf.model.tsl.TestSpecification#getName()
	 * @see #getTestSpecification()
	 * @generated
	 */
	EAttribute getTestSpecification_Name();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.redset.emf.model.tsl.TestSpecification#getEReference0 <em>EReference0</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>EReference0</em>'.
	 * @see eu.redset.emf.model.tsl.TestSpecification#getEReference0()
	 * @see #getTestSpecification()
	 * @generated
	 */
	EReference getTestSpecification_EReference0();

	/**
	 * Returns the meta object for the attribute '{@link eu.redset.emf.model.tsl.TestSpecification#getScUid <em>Sc Uid</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Sc Uid</em>'.
	 * @see eu.redset.emf.model.tsl.TestSpecification#getScUid()
	 * @see #getTestSpecification()
	 * @generated
	 */
	EAttribute getTestSpecification_ScUid();

	/**
	 * Returns the meta object for the attribute '{@link eu.redset.emf.model.tsl.TestSpecification#getUid <em>Uid</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Uid</em>'.
	 * @see eu.redset.emf.model.tsl.TestSpecification#getUid()
	 * @see #getTestSpecification()
	 * @generated
	 */
	EAttribute getTestSpecification_Uid();

	/**
	 * Returns the meta object for class '{@link eu.redset.emf.model.tsl.TestPackage <em>Test Package</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Test Package</em>'.
	 * @see eu.redset.emf.model.tsl.TestPackage
	 * @generated
	 */
	EClass getTestPackage();

	/**
	 * Returns the meta object for the attribute '{@link eu.redset.emf.model.tsl.TestPackage#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see eu.redset.emf.model.tsl.TestPackage#getName()
	 * @see #getTestPackage()
	 * @generated
	 */
	EAttribute getTestPackage_Name();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.redset.emf.model.tsl.TestPackage#getNotions <em>Notions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Notions</em>'.
	 * @see eu.redset.emf.model.tsl.TestPackage#getNotions()
	 * @see #getTestPackage()
	 * @generated
	 */
	EReference getTestPackage_Notions();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.redset.emf.model.tsl.TestPackage#getNFTests <em>NF Tests</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>NF Tests</em>'.
	 * @see eu.redset.emf.model.tsl.TestPackage#getNFTests()
	 * @see #getTestPackage()
	 * @generated
	 */
	EReference getTestPackage_NFTests();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.redset.emf.model.tsl.TestPackage#getGUITests <em>GUI Tests</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>GUI Tests</em>'.
	 * @see eu.redset.emf.model.tsl.TestPackage#getGUITests()
	 * @see #getTestPackage()
	 * @generated
	 */
	EReference getTestPackage_GUITests();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.redset.emf.model.tsl.TestPackage#getBLTests <em>BL Tests</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>BL Tests</em>'.
	 * @see eu.redset.emf.model.tsl.TestPackage#getBLTests()
	 * @see #getTestPackage()
	 * @generated
	 */
	EReference getTestPackage_BLTests();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.redset.emf.model.tsl.TestPackage#getEReference3 <em>EReference3</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>EReference3</em>'.
	 * @see eu.redset.emf.model.tsl.TestPackage#getEReference3()
	 * @see #getTestPackage()
	 * @generated
	 */
	EReference getTestPackage_EReference3();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.redset.emf.model.tsl.TestPackage#getEReference0 <em>EReference0</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>EReference0</em>'.
	 * @see eu.redset.emf.model.tsl.TestPackage#getEReference0()
	 * @see #getTestPackage()
	 * @generated
	 */
	EReference getTestPackage_EReference0();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.redset.emf.model.tsl.TestPackage#getEReference1 <em>EReference1</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>EReference1</em>'.
	 * @see eu.redset.emf.model.tsl.TestPackage#getEReference1()
	 * @see #getTestPackage()
	 * @generated
	 */
	EReference getTestPackage_EReference1();

	/**
	 * Returns the meta object for class '{@link eu.redset.emf.model.tsl.Test <em>Test</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Test</em>'.
	 * @see eu.redset.emf.model.tsl.Test
	 * @generated
	 */
	EClass getTest();

	/**
	 * Returns the meta object for the attribute '{@link eu.redset.emf.model.tsl.Test#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see eu.redset.emf.model.tsl.Test#getName()
	 * @see #getTest()
	 * @generated
	 */
	EAttribute getTest_Name();

	/**
	 * Returns the meta object for the attribute '{@link eu.redset.emf.model.tsl.Test#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see eu.redset.emf.model.tsl.Test#getType()
	 * @see #getTest()
	 * @generated
	 */
	EAttribute getTest_Type();

	/**
	 * Returns the meta object for the attribute '{@link eu.redset.emf.model.tsl.Test#getClassifierId <em>Classifier Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Classifier Id</em>'.
	 * @see eu.redset.emf.model.tsl.Test#getClassifierId()
	 * @see #getTest()
	 * @generated
	 */
	EAttribute getTest_ClassifierId();

	/**
	 * Returns the meta object for the attribute '{@link eu.redset.emf.model.tsl.Test#getContent <em>Content</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Content</em>'.
	 * @see eu.redset.emf.model.tsl.Test#getContent()
	 * @see #getTest()
	 * @generated
	 */
	EAttribute getTest_Content();

	/**
	 * Returns the meta object for the attribute '{@link eu.redset.emf.model.tsl.Test#getTestPrecondition <em>Test Precondition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Test Precondition</em>'.
	 * @see eu.redset.emf.model.tsl.Test#getTestPrecondition()
	 * @see #getTest()
	 * @generated
	 */
	EAttribute getTest_TestPrecondition();

	/**
	 * Returns the meta object for the attribute '{@link eu.redset.emf.model.tsl.Test#getTestPostcondition <em>Test Postcondition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Test Postcondition</em>'.
	 * @see eu.redset.emf.model.tsl.Test#getTestPostcondition()
	 * @see #getTest()
	 * @generated
	 */
	EAttribute getTest_TestPostcondition();

	/**
	 * Returns the meta object for the attribute '{@link eu.redset.emf.model.tsl.Test#getTestResult <em>Test Result</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Test Result</em>'.
	 * @see eu.redset.emf.model.tsl.Test#getTestResult()
	 * @see #getTest()
	 * @generated
	 */
	EAttribute getTest_TestResult();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.redset.emf.model.tsl.Test#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Source</em>'.
	 * @see eu.redset.emf.model.tsl.Test#getSource()
	 * @see #getTest()
	 * @generated
	 */
	EReference getTest_Source();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.redset.emf.model.tsl.Test#getTestParameters <em>Test Parameters</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Test Parameters</em>'.
	 * @see eu.redset.emf.model.tsl.Test#getTestParameters()
	 * @see #getTest()
	 * @generated
	 */
	EReference getTest_TestParameters();

	/**
	 * Returns the meta object for the attribute '{@link eu.redset.emf.model.tsl.Test#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see eu.redset.emf.model.tsl.Test#getDescription()
	 * @see #getTest()
	 * @generated
	 */
	EAttribute getTest_Description();

	/**
	 * Returns the meta object for class '{@link eu.redset.emf.model.tsl.UseCaseTest <em>Use Case Test</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Use Case Test</em>'.
	 * @see eu.redset.emf.model.tsl.UseCaseTest
	 * @generated
	 */
	EClass getUseCaseTest();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.redset.emf.model.tsl.UseCaseTest#getEReference0 <em>EReference0</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>EReference0</em>'.
	 * @see eu.redset.emf.model.tsl.UseCaseTest#getEReference0()
	 * @see #getUseCaseTest()
	 * @generated
	 */
	EReference getUseCaseTest_EReference0();

	/**
	 * Returns the meta object for the attribute '{@link eu.redset.emf.model.tsl.UseCaseTest#getUcName <em>Uc Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Uc Name</em>'.
	 * @see eu.redset.emf.model.tsl.UseCaseTest#getUcName()
	 * @see #getUseCaseTest()
	 * @generated
	 */
	EAttribute getUseCaseTest_UcName();

	/**
	 * Returns the meta object for the attribute '{@link eu.redset.emf.model.tsl.UseCaseTest#getUcTrail <em>Uc Trail</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Uc Trail</em>'.
	 * @see eu.redset.emf.model.tsl.UseCaseTest#getUcTrail()
	 * @see #getUseCaseTest()
	 * @generated
	 */
	EAttribute getUseCaseTest_UcTrail();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.redset.emf.model.tsl.UseCaseTest#getInvocationSource <em>Invocation Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Invocation Source</em>'.
	 * @see eu.redset.emf.model.tsl.UseCaseTest#getInvocationSource()
	 * @see #getUseCaseTest()
	 * @generated
	 */
	EReference getUseCaseTest_InvocationSource();

	/**
	 * Returns the meta object for the attribute '{@link eu.redset.emf.model.tsl.UseCaseTest#getUid <em>Uid</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Uid</em>'.
	 * @see eu.redset.emf.model.tsl.UseCaseTest#getUid()
	 * @see #getUseCaseTest()
	 * @generated
	 */
	EAttribute getUseCaseTest_Uid();

	/**
	 * Returns the meta object for class '{@link eu.redset.emf.model.tsl.UseCaseTestScenario <em>Use Case Test Scenario</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Use Case Test Scenario</em>'.
	 * @see eu.redset.emf.model.tsl.UseCaseTestScenario
	 * @generated
	 */
	EClass getUseCaseTestScenario();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.redset.emf.model.tsl.UseCaseTestScenario#getSentences <em>Sentences</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Sentences</em>'.
	 * @see eu.redset.emf.model.tsl.UseCaseTestScenario#getSentences()
	 * @see #getUseCaseTestScenario()
	 * @generated
	 */
	EReference getUseCaseTestScenario_Sentences();

	/**
	 * Returns the meta object for the containment reference '{@link eu.redset.emf.model.tsl.UseCaseTestScenario#getPrecondition <em>Precondition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Precondition</em>'.
	 * @see eu.redset.emf.model.tsl.UseCaseTestScenario#getPrecondition()
	 * @see #getUseCaseTestScenario()
	 * @generated
	 */
	EReference getUseCaseTestScenario_Precondition();

	/**
	 * Returns the meta object for the containment reference '{@link eu.redset.emf.model.tsl.UseCaseTestScenario#getPostcondition <em>Postcondition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Postcondition</em>'.
	 * @see eu.redset.emf.model.tsl.UseCaseTestScenario#getPostcondition()
	 * @see #getUseCaseTestScenario()
	 * @generated
	 */
	EReference getUseCaseTestScenario_Postcondition();

	/**
	 * Returns the meta object for the attribute '{@link eu.redset.emf.model.tsl.UseCaseTestScenario#getActor <em>Actor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Actor</em>'.
	 * @see eu.redset.emf.model.tsl.UseCaseTestScenario#getActor()
	 * @see #getUseCaseTestScenario()
	 * @generated
	 */
	EAttribute getUseCaseTestScenario_Actor();

	/**
	 * Returns the meta object for class '{@link eu.redset.emf.model.tsl.UseCaseTestScenarioSentence <em>Use Case Test Scenario Sentence</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Use Case Test Scenario Sentence</em>'.
	 * @see eu.redset.emf.model.tsl.UseCaseTestScenarioSentence
	 * @generated
	 */
	EClass getUseCaseTestScenarioSentence();

	/**
	 * Returns the meta object for the reference '{@link eu.redset.emf.model.tsl.UseCaseTestScenarioSentence#getEReference0 <em>EReference0</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>EReference0</em>'.
	 * @see eu.redset.emf.model.tsl.UseCaseTestScenarioSentence#getEReference0()
	 * @see #getUseCaseTestScenarioSentence()
	 * @generated
	 */
	EReference getUseCaseTestScenarioSentence_EReference0();

	/**
	 * Returns the meta object for the reference '{@link eu.redset.emf.model.tsl.UseCaseTestScenarioSentence#getEReference1 <em>EReference1</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>EReference1</em>'.
	 * @see eu.redset.emf.model.tsl.UseCaseTestScenarioSentence#getEReference1()
	 * @see #getUseCaseTestScenarioSentence()
	 * @generated
	 */
	EReference getUseCaseTestScenarioSentence_EReference1();

	/**
	 * Returns the meta object for the attribute '{@link eu.redset.emf.model.tsl.UseCaseTestScenarioSentence#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see eu.redset.emf.model.tsl.UseCaseTestScenarioSentence#getName()
	 * @see #getUseCaseTestScenarioSentence()
	 * @generated
	 */
	EAttribute getUseCaseTestScenarioSentence_Name();

	/**
	 * Returns the meta object for the containment reference '{@link eu.redset.emf.model.tsl.UseCaseTestScenarioSentence#getScenarioSentence <em>Scenario Sentence</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Scenario Sentence</em>'.
	 * @see eu.redset.emf.model.tsl.UseCaseTestScenarioSentence#getScenarioSentence()
	 * @see #getUseCaseTestScenarioSentence()
	 * @generated
	 */
	EReference getUseCaseTestScenarioSentence_ScenarioSentence();

	/**
	 * Returns the meta object for class '{@link eu.redset.emf.model.tsl.TestScenario <em>Test Scenario</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Test Scenario</em>'.
	 * @see eu.redset.emf.model.tsl.TestScenario
	 * @generated
	 */
	EClass getTestScenario();

	/**
	 * Returns the meta object for the reference '{@link eu.redset.emf.model.tsl.TestScenario#getEReference0 <em>EReference0</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>EReference0</em>'.
	 * @see eu.redset.emf.model.tsl.TestScenario#getEReference0()
	 * @see #getTestScenario()
	 * @generated
	 */
	EReference getTestScenario_EReference0();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.redset.emf.model.tsl.TestScenario#getEReference1 <em>EReference1</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>EReference1</em>'.
	 * @see eu.redset.emf.model.tsl.TestScenario#getEReference1()
	 * @see #getTestScenario()
	 * @generated
	 */
	EReference getTestScenario_EReference1();

	/**
	 * Returns the meta object for class '{@link eu.redset.emf.model.tsl.TestCase <em>Test Case</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Test Case</em>'.
	 * @see eu.redset.emf.model.tsl.TestCase
	 * @generated
	 */
	EClass getTestCase();

	/**
	 * Returns the meta object for the containment reference '{@link eu.redset.emf.model.tsl.TestCase#getEReference1 <em>EReference1</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>EReference1</em>'.
	 * @see eu.redset.emf.model.tsl.TestCase#getEReference1()
	 * @see #getTestCase()
	 * @generated
	 */
	EReference getTestCase_EReference1();

	/**
	 * Returns the meta object for the attribute '{@link eu.redset.emf.model.tsl.TestCase#getOrderNumber <em>Order Number</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Order Number</em>'.
	 * @see eu.redset.emf.model.tsl.TestCase#getOrderNumber()
	 * @see #getTestCase()
	 * @generated
	 */
	EAttribute getTestCase_OrderNumber();

	/**
	 * Returns the meta object for the containment reference '{@link eu.redset.emf.model.tsl.TestCase#getPrecondition <em>Precondition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Precondition</em>'.
	 * @see eu.redset.emf.model.tsl.TestCase#getPrecondition()
	 * @see #getTestCase()
	 * @generated
	 */
	EReference getTestCase_Precondition();

	/**
	 * Returns the meta object for the containment reference '{@link eu.redset.emf.model.tsl.TestCase#getPostcondition <em>Postcondition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Postcondition</em>'.
	 * @see eu.redset.emf.model.tsl.TestCase#getPostcondition()
	 * @see #getTestCase()
	 * @generated
	 */
	EReference getTestCase_Postcondition();

	/**
	 * Returns the meta object for the attribute '{@link eu.redset.emf.model.tsl.TestCase#getUcName <em>Uc Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Uc Name</em>'.
	 * @see eu.redset.emf.model.tsl.TestCase#getUcName()
	 * @see #getTestCase()
	 * @generated
	 */
	EAttribute getTestCase_UcName();

	/**
	 * Returns the meta object for the attribute '{@link eu.redset.emf.model.tsl.TestCase#getUcTrail <em>Uc Trail</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Uc Trail</em>'.
	 * @see eu.redset.emf.model.tsl.TestCase#getUcTrail()
	 * @see #getTestCase()
	 * @generated
	 */
	EAttribute getTestCase_UcTrail();

	/**
	 * Returns the meta object for the attribute '{@link eu.redset.emf.model.tsl.TestCase#getUcScenarioName <em>Uc Scenario Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Uc Scenario Name</em>'.
	 * @see eu.redset.emf.model.tsl.TestCase#getUcScenarioName()
	 * @see #getTestCase()
	 * @generated
	 */
	EAttribute getTestCase_UcScenarioName();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.redset.emf.model.tsl.TestCase#getSentences <em>Sentences</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Sentences</em>'.
	 * @see eu.redset.emf.model.tsl.TestCase#getSentences()
	 * @see #getTestCase()
	 * @generated
	 */
	EReference getTestCase_Sentences();

	/**
	 * Returns the meta object for the attribute '{@link eu.redset.emf.model.tsl.TestCase#getOrderNumberGlobal <em>Order Number Global</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Order Number Global</em>'.
	 * @see eu.redset.emf.model.tsl.TestCase#getOrderNumberGlobal()
	 * @see #getTestCase()
	 * @generated
	 */
	EAttribute getTestCase_OrderNumberGlobal();

	/**
	 * Returns the meta object for class '{@link eu.redset.emf.model.tsl.TestCaseSentence <em>Test Case Sentence</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Test Case Sentence</em>'.
	 * @see eu.redset.emf.model.tsl.TestCaseSentence
	 * @generated
	 */
	EClass getTestCaseSentence();

	/**
	 * Returns the meta object for the reference '{@link eu.redset.emf.model.tsl.TestCaseSentence#getEReference0 <em>EReference0</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>EReference0</em>'.
	 * @see eu.redset.emf.model.tsl.TestCaseSentence#getEReference0()
	 * @see #getTestCaseSentence()
	 * @generated
	 */
	EReference getTestCaseSentence_EReference0();

	/**
	 * Returns the meta object for the containment reference '{@link eu.redset.emf.model.tsl.TestCaseSentence#getScenarioSentence <em>Scenario Sentence</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Scenario Sentence</em>'.
	 * @see eu.redset.emf.model.tsl.TestCaseSentence#getScenarioSentence()
	 * @see #getTestCaseSentence()
	 * @generated
	 */
	EReference getTestCaseSentence_ScenarioSentence();

	/**
	 * Returns the meta object for the attribute '{@link eu.redset.emf.model.tsl.TestCaseSentence#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see eu.redset.emf.model.tsl.TestCaseSentence#getName()
	 * @see #getTestCaseSentence()
	 * @generated
	 */
	EAttribute getTestCaseSentence_Name();

	/**
	 * Returns the meta object for the containment reference '{@link eu.redset.emf.model.tsl.TestCaseSentence#getContext <em>Context</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Context</em>'.
	 * @see eu.redset.emf.model.tsl.TestCaseSentence#getContext()
	 * @see #getTestCaseSentence()
	 * @generated
	 */
	EReference getTestCaseSentence_Context();

	/**
	 * Returns the meta object for class '{@link eu.redset.emf.model.tsl.UseCaseTestScenarioInstance <em>Use Case Test Scenario Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Use Case Test Scenario Instance</em>'.
	 * @see eu.redset.emf.model.tsl.UseCaseTestScenarioInstance
	 * @generated
	 */
	EClass getUseCaseTestScenarioInstance();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.redset.emf.model.tsl.UseCaseTestScenarioInstance#getEReference3 <em>EReference3</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>EReference3</em>'.
	 * @see eu.redset.emf.model.tsl.UseCaseTestScenarioInstance#getEReference3()
	 * @see #getUseCaseTestScenarioInstance()
	 * @generated
	 */
	EReference getUseCaseTestScenarioInstance_EReference3();

	/**
	 * Returns the meta object for the attribute '{@link eu.redset.emf.model.tsl.UseCaseTestScenarioInstance#getUcName <em>Uc Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Uc Name</em>'.
	 * @see eu.redset.emf.model.tsl.UseCaseTestScenarioInstance#getUcName()
	 * @see #getUseCaseTestScenarioInstance()
	 * @generated
	 */
	EAttribute getUseCaseTestScenarioInstance_UcName();

	/**
	 * Returns the meta object for the attribute '{@link eu.redset.emf.model.tsl.UseCaseTestScenarioInstance#getUcTrail <em>Uc Trail</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Uc Trail</em>'.
	 * @see eu.redset.emf.model.tsl.UseCaseTestScenarioInstance#getUcTrail()
	 * @see #getUseCaseTestScenarioInstance()
	 * @generated
	 */
	EAttribute getUseCaseTestScenarioInstance_UcTrail();

	/**
	 * Returns the meta object for class '{@link eu.redset.emf.model.tsl.ScenarioSentence <em>Scenario Sentence</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Scenario Sentence</em>'.
	 * @see eu.redset.emf.model.tsl.ScenarioSentence
	 * @generated
	 */
	EClass getScenarioSentence();

	/**
	 * Returns the meta object for class '{@link eu.redset.emf.model.tsl.SVOSentence <em>SVO Sentence</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>SVO Sentence</em>'.
	 * @see eu.redset.emf.model.tsl.SVOSentence
	 * @generated
	 */
	EClass getSVOSentence();

	/**
	 * Returns the meta object for the attribute '{@link eu.redset.emf.model.tsl.SVOSentence#getNumber <em>Number</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Number</em>'.
	 * @see eu.redset.emf.model.tsl.SVOSentence#getNumber()
	 * @see #getSVOSentence()
	 * @generated
	 */
	EAttribute getSVOSentence_Number();

	/**
	 * Returns the meta object for the reference '{@link eu.redset.emf.model.tsl.SVOSentence#getPredicate <em>Predicate</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Predicate</em>'.
	 * @see eu.redset.emf.model.tsl.SVOSentence#getPredicate()
	 * @see #getSVOSentence()
	 * @generated
	 */
	EReference getSVOSentence_Predicate();

	/**
	 * Returns the meta object for the containment reference '{@link eu.redset.emf.model.tsl.SVOSentence#getDirectObject <em>Direct Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Direct Object</em>'.
	 * @see eu.redset.emf.model.tsl.SVOSentence#getDirectObject()
	 * @see #getSVOSentence()
	 * @generated
	 */
	EReference getSVOSentence_DirectObject();

	/**
	 * Returns the meta object for the containment reference '{@link eu.redset.emf.model.tsl.SVOSentence#getIndirectObject <em>Indirect Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Indirect Object</em>'.
	 * @see eu.redset.emf.model.tsl.SVOSentence#getIndirectObject()
	 * @see #getSVOSentence()
	 * @generated
	 */
	EReference getSVOSentence_IndirectObject();

	/**
	 * Returns the meta object for class '{@link eu.redset.emf.model.tsl.ControlSentence <em>Control Sentence</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Control Sentence</em>'.
	 * @see eu.redset.emf.model.tsl.ControlSentence
	 * @generated
	 */
	EClass getControlSentence();

	/**
	 * Returns the meta object for the reference '{@link eu.redset.emf.model.tsl.ControlSentence#getInvocation <em>Invocation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Invocation</em>'.
	 * @see eu.redset.emf.model.tsl.ControlSentence#getInvocation()
	 * @see #getControlSentence()
	 * @generated
	 */
	EReference getControlSentence_Invocation();

	/**
	 * Returns the meta object for class '{@link eu.redset.emf.model.tsl.ConditionSentence <em>Condition Sentence</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Condition Sentence</em>'.
	 * @see eu.redset.emf.model.tsl.ConditionSentence
	 * @generated
	 */
	EClass getConditionSentence();

	/**
	 * Returns the meta object for class '{@link eu.redset.emf.model.tsl.Precondition <em>Precondition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Precondition</em>'.
	 * @see eu.redset.emf.model.tsl.Precondition
	 * @generated
	 */
	EClass getPrecondition();

	/**
	 * Returns the meta object for class '{@link eu.redset.emf.model.tsl.Postcondition <em>Postcondition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Postcondition</em>'.
	 * @see eu.redset.emf.model.tsl.Postcondition
	 * @generated
	 */
	EClass getPostcondition();

	/**
	 * Returns the meta object for class '{@link eu.redset.emf.model.tsl.Condition <em>Condition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Condition</em>'.
	 * @see eu.redset.emf.model.tsl.Condition
	 * @generated
	 */
	EClass getCondition();

	/**
	 * Returns the meta object for the attribute '{@link eu.redset.emf.model.tsl.Condition#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see eu.redset.emf.model.tsl.Condition#getName()
	 * @see #getCondition()
	 * @generated
	 */
	EAttribute getCondition_Name();

	/**
	 * Returns the meta object for class '{@link eu.redset.emf.model.tsl.TestRelationship <em>Test Relationship</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Test Relationship</em>'.
	 * @see eu.redset.emf.model.tsl.TestRelationship
	 * @generated
	 */
	EClass getTestRelationship();

	/**
	 * Returns the meta object for the reference '{@link eu.redset.emf.model.tsl.TestRelationship#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Target</em>'.
	 * @see eu.redset.emf.model.tsl.TestRelationship#getTarget()
	 * @see #getTestRelationship()
	 * @generated
	 */
	EReference getTestRelationship_Target();

	/**
	 * Returns the meta object for the attribute '{@link eu.redset.emf.model.tsl.TestRelationship#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see eu.redset.emf.model.tsl.TestRelationship#getType()
	 * @see #getTestRelationship()
	 * @generated
	 */
	EAttribute getTestRelationship_Type();

	/**
	 * Returns the meta object for class '{@link eu.redset.emf.model.tsl.TestInvocationRelationship <em>Test Invocation Relationship</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Test Invocation Relationship</em>'.
	 * @see eu.redset.emf.model.tsl.TestInvocationRelationship
	 * @generated
	 */
	EClass getTestInvocationRelationship();

	/**
	 * Returns the meta object for the reference '{@link eu.redset.emf.model.tsl.TestInvocationRelationship#getEReference0 <em>EReference0</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>EReference0</em>'.
	 * @see eu.redset.emf.model.tsl.TestInvocationRelationship#getEReference0()
	 * @see #getTestInvocationRelationship()
	 * @generated
	 */
	EReference getTestInvocationRelationship_EReference0();

	/**
	 * Returns the meta object for the reference '{@link eu.redset.emf.model.tsl.TestInvocationRelationship#getInvocationTarget <em>Invocation Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Invocation Target</em>'.
	 * @see eu.redset.emf.model.tsl.TestInvocationRelationship#getInvocationTarget()
	 * @see #getTestInvocationRelationship()
	 * @generated
	 */
	EReference getTestInvocationRelationship_InvocationTarget();

	/**
	 * Returns the meta object for the attribute '{@link eu.redset.emf.model.tsl.TestInvocationRelationship#getUid <em>Uid</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Uid</em>'.
	 * @see eu.redset.emf.model.tsl.TestInvocationRelationship#getUid()
	 * @see #getTestInvocationRelationship()
	 * @generated
	 */
	EAttribute getTestInvocationRelationship_Uid();

	/**
	 * Returns the meta object for the attribute '{@link eu.redset.emf.model.tsl.TestInvocationRelationship#getIncludeType <em>Include Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Include Type</em>'.
	 * @see eu.redset.emf.model.tsl.TestInvocationRelationship#getIncludeType()
	 * @see #getTestInvocationRelationship()
	 * @generated
	 */
	EAttribute getTestInvocationRelationship_IncludeType();

	/**
	 * Returns the meta object for class '{@link eu.redset.emf.model.tsl.DomainObject <em>Domain Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Domain Object</em>'.
	 * @see eu.redset.emf.model.tsl.DomainObject
	 * @generated
	 */
	EClass getDomainObject();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.redset.emf.model.tsl.DomainObject#getInputData <em>Input Data</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Input Data</em>'.
	 * @see eu.redset.emf.model.tsl.DomainObject#getInputData()
	 * @see #getDomainObject()
	 * @generated
	 */
	EReference getDomainObject_InputData();

	/**
	 * Returns the meta object for the attribute '{@link eu.redset.emf.model.tsl.DomainObject#getDomainObjectType <em>Domain Object Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Domain Object Type</em>'.
	 * @see eu.redset.emf.model.tsl.DomainObject#getDomainObjectType()
	 * @see #getDomainObject()
	 * @generated
	 */
	EAttribute getDomainObject_DomainObjectType();

	/**
	 * Returns the meta object for class '{@link eu.redset.emf.model.tsl.NotionAttribute <em>Notion Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Notion Attribute</em>'.
	 * @see eu.redset.emf.model.tsl.NotionAttribute
	 * @generated
	 */
	EClass getNotionAttribute();

	/**
	 * Returns the meta object for the attribute '{@link eu.redset.emf.model.tsl.NotionAttribute#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see eu.redset.emf.model.tsl.NotionAttribute#getType()
	 * @see #getNotionAttribute()
	 * @generated
	 */
	EAttribute getNotionAttribute_Type();

	/**
	 * Returns the meta object for the attribute '{@link eu.redset.emf.model.tsl.NotionAttribute#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see eu.redset.emf.model.tsl.NotionAttribute#getName()
	 * @see #getNotionAttribute()
	 * @generated
	 */
	EAttribute getNotionAttribute_Name();

	/**
	 * Returns the meta object for the attribute '{@link eu.redset.emf.model.tsl.NotionAttribute#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see eu.redset.emf.model.tsl.NotionAttribute#getDescription()
	 * @see #getNotionAttribute()
	 * @generated
	 */
	EAttribute getNotionAttribute_Description();

	/**
	 * Returns the meta object for class '{@link eu.redset.emf.model.tsl.NFTest <em>NF Test</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>NF Test</em>'.
	 * @see eu.redset.emf.model.tsl.NFTest
	 * @generated
	 */
	EClass getNFTest();

	/**
	 * Returns the meta object for the attribute '{@link eu.redset.emf.model.tsl.NFTest#getNfrTrail <em>Nfr Trail</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Nfr Trail</em>'.
	 * @see eu.redset.emf.model.tsl.NFTest#getNfrTrail()
	 * @see #getNFTest()
	 * @generated
	 */
	EAttribute getNFTest_NfrTrail();

	/**
	 * Returns the meta object for the attribute '{@link eu.redset.emf.model.tsl.NFTest#getUid <em>Uid</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Uid</em>'.
	 * @see eu.redset.emf.model.tsl.NFTest#getUid()
	 * @see #getNFTest()
	 * @generated
	 */
	EAttribute getNFTest_Uid();

	/**
	 * Returns the meta object for class '{@link eu.redset.emf.model.tsl.GUITest <em>GUI Test</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>GUI Test</em>'.
	 * @see eu.redset.emf.model.tsl.GUITest
	 * @generated
	 */
	EClass getGUITest();

	/**
	 * Returns the meta object for class '{@link eu.redset.emf.model.tsl.BLTest <em>BL Test</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>BL Test</em>'.
	 * @see eu.redset.emf.model.tsl.BLTest
	 * @generated
	 */
	EClass getBLTest();

	/**
	 * Returns the meta object for class '{@link eu.redset.emf.model.tsl.Notion <em>Notion</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Notion</em>'.
	 * @see eu.redset.emf.model.tsl.Notion
	 * @generated
	 */
	EClass getNotion();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.redset.emf.model.tsl.Notion#getDomainStatements <em>Domain Statements</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Domain Statements</em>'.
	 * @see eu.redset.emf.model.tsl.Notion#getDomainStatements()
	 * @see #getNotion()
	 * @generated
	 */
	EReference getNotion_DomainStatements();

	/**
	 * Returns the meta object for the attribute '{@link eu.redset.emf.model.tsl.Notion#getNotionName <em>Notion Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Notion Name</em>'.
	 * @see eu.redset.emf.model.tsl.Notion#getNotionName()
	 * @see #getNotion()
	 * @generated
	 */
	EAttribute getNotion_NotionName();

	/**
	 * Returns the meta object for the attribute '{@link eu.redset.emf.model.tsl.Notion#getNotionDescription <em>Notion Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Notion Description</em>'.
	 * @see eu.redset.emf.model.tsl.Notion#getNotionDescription()
	 * @see #getNotion()
	 * @generated
	 */
	EAttribute getNotion_NotionDescription();

	/**
	 * Returns the meta object for the attribute '{@link eu.redset.emf.model.tsl.Notion#getNotionTrail <em>Notion Trail</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Notion Trail</em>'.
	 * @see eu.redset.emf.model.tsl.Notion#getNotionTrail()
	 * @see #getNotion()
	 * @generated
	 */
	EAttribute getNotion_NotionTrail();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.redset.emf.model.tsl.Notion#getAttributes <em>Attributes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Attributes</em>'.
	 * @see eu.redset.emf.model.tsl.Notion#getAttributes()
	 * @see #getNotion()
	 * @generated
	 */
	EReference getNotion_Attributes();

	/**
	 * Returns the meta object for the attribute '{@link eu.redset.emf.model.tsl.Notion#getUid <em>Uid</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Uid</em>'.
	 * @see eu.redset.emf.model.tsl.Notion#getUid()
	 * @see #getNotion()
	 * @generated
	 */
	EAttribute getNotion_Uid();

	/**
	 * Returns the meta object for class '{@link eu.redset.emf.model.tsl.DomainStatement <em>Domain Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Domain Statement</em>'.
	 * @see eu.redset.emf.model.tsl.DomainStatement
	 * @generated
	 */
	EClass getDomainStatement();

	/**
	 * Returns the meta object for the reference '{@link eu.redset.emf.model.tsl.DomainStatement#getDirectNotion <em>Direct Notion</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Direct Notion</em>'.
	 * @see eu.redset.emf.model.tsl.DomainStatement#getDirectNotion()
	 * @see #getDomainStatement()
	 * @generated
	 */
	EReference getDomainStatement_DirectNotion();

	/**
	 * Returns the meta object for the reference '{@link eu.redset.emf.model.tsl.DomainStatement#getIndirectNotion <em>Indirect Notion</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Indirect Notion</em>'.
	 * @see eu.redset.emf.model.tsl.DomainStatement#getIndirectNotion()
	 * @see #getDomainStatement()
	 * @generated
	 */
	EReference getDomainStatement_IndirectNotion();

	/**
	 * Returns the meta object for the attribute '{@link eu.redset.emf.model.tsl.DomainStatement#getPhraseName <em>Phrase Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Phrase Name</em>'.
	 * @see eu.redset.emf.model.tsl.DomainStatement#getPhraseName()
	 * @see #getDomainStatement()
	 * @generated
	 */
	EAttribute getDomainStatement_PhraseName();

	/**
	 * Returns the meta object for the attribute '{@link eu.redset.emf.model.tsl.DomainStatement#getPhraseDescription <em>Phrase Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Phrase Description</em>'.
	 * @see eu.redset.emf.model.tsl.DomainStatement#getPhraseDescription()
	 * @see #getDomainStatement()
	 * @generated
	 */
	EAttribute getDomainStatement_PhraseDescription();

	/**
	 * Returns the meta object for the attribute '{@link eu.redset.emf.model.tsl.DomainStatement#getUid <em>Uid</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Uid</em>'.
	 * @see eu.redset.emf.model.tsl.DomainStatement#getUid()
	 * @see #getDomainStatement()
	 * @generated
	 */
	EAttribute getDomainStatement_Uid();

	/**
	 * Returns the meta object for class '{@link eu.redset.emf.model.tsl.CCondition <em>CCondition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>CCondition</em>'.
	 * @see eu.redset.emf.model.tsl.CCondition
	 * @generated
	 */
	EClass getCCondition();

	/**
	 * Returns the meta object for the attribute '{@link eu.redset.emf.model.tsl.CCondition#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see eu.redset.emf.model.tsl.CCondition#getName()
	 * @see #getCCondition()
	 * @generated
	 */
	EAttribute getCCondition_Name();

	/**
	 * Returns the meta object for class '{@link eu.redset.emf.model.tsl.TestValue <em>Test Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Test Value</em>'.
	 * @see eu.redset.emf.model.tsl.TestValue
	 * @generated
	 */
	EClass getTestValue();

	/**
	 * Returns the meta object for the attribute '{@link eu.redset.emf.model.tsl.TestValue#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see eu.redset.emf.model.tsl.TestValue#getValue()
	 * @see #getTestValue()
	 * @generated
	 */
	EAttribute getTestValue_Value();

	/**
	 * Returns the meta object for the attribute '{@link eu.redset.emf.model.tsl.TestValue#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see eu.redset.emf.model.tsl.TestValue#getName()
	 * @see #getTestValue()
	 * @generated
	 */
	EAttribute getTestValue_Name();

	/**
	 * Returns the meta object for class '{@link eu.redset.emf.model.tsl.TestParameter <em>Test Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Test Parameter</em>'.
	 * @see eu.redset.emf.model.tsl.TestParameter
	 * @generated
	 */
	EClass getTestParameter();

	/**
	 * Returns the meta object for the attribute '{@link eu.redset.emf.model.tsl.TestParameter#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see eu.redset.emf.model.tsl.TestParameter#getName()
	 * @see #getTestParameter()
	 * @generated
	 */
	EAttribute getTestParameter_Name();

	/**
	 * Returns the meta object for the attribute '{@link eu.redset.emf.model.tsl.TestParameter#getDomain <em>Domain</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Domain</em>'.
	 * @see eu.redset.emf.model.tsl.TestParameter#getDomain()
	 * @see #getTestParameter()
	 * @generated
	 */
	EAttribute getTestParameter_Domain();

	/**
	 * Returns the meta object for the attribute '{@link eu.redset.emf.model.tsl.TestParameter#getExpectedValue <em>Expected Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Expected Value</em>'.
	 * @see eu.redset.emf.model.tsl.TestParameter#getExpectedValue()
	 * @see #getTestParameter()
	 * @generated
	 */
	EAttribute getTestParameter_ExpectedValue();

	/**
	 * Returns the meta object for class '{@link eu.redset.emf.model.tsl.TestInstance <em>Test Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Test Instance</em>'.
	 * @see eu.redset.emf.model.tsl.TestInstance
	 * @generated
	 */
	EClass getTestInstance();

	/**
	 * Returns the meta object for the containment reference list '{@link eu.redset.emf.model.tsl.TestInstance#getAttachedTests <em>Attached Tests</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Attached Tests</em>'.
	 * @see eu.redset.emf.model.tsl.TestInstance#getAttachedTests()
	 * @see #getTestInstance()
	 * @generated
	 */
	EReference getTestInstance_AttachedTests();

	/**
	 * Returns the meta object for class '{@link eu.redset.emf.model.tsl.NFTestInstance <em>NF Test Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>NF Test Instance</em>'.
	 * @see eu.redset.emf.model.tsl.NFTestInstance
	 * @generated
	 */
	EClass getNFTestInstance();

	/**
	 * Returns the meta object for class '{@link eu.redset.emf.model.tsl.GUITestInstance <em>GUI Test Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>GUI Test Instance</em>'.
	 * @see eu.redset.emf.model.tsl.GUITestInstance
	 * @generated
	 */
	EClass getGUITestInstance();

	/**
	 * Returns the meta object for class '{@link eu.redset.emf.model.tsl.BLTestInstance <em>BL Test Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>BL Test Instance</em>'.
	 * @see eu.redset.emf.model.tsl.BLTestInstance
	 * @generated
	 */
	EClass getBLTestInstance();

	/**
	 * Returns the meta object for class '{@link eu.redset.emf.model.tsl.TestDataValuesContext <em>Test Data Values Context</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Test Data Values Context</em>'.
	 * @see eu.redset.emf.model.tsl.TestDataValuesContext
	 * @generated
	 */
	EClass getTestDataValuesContext();

	/**
	 * Returns the meta object for enum '{@link eu.redset.emf.model.tsl.DomainObjectType <em>Domain Object Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Domain Object Type</em>'.
	 * @see eu.redset.emf.model.tsl.DomainObjectType
	 * @generated
	 */
	EEnum getDomainObjectType();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	TslFactory getTslFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link eu.redset.emf.model.tsl.impl.TestSpecificationImpl <em>Test Specification</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.redset.emf.model.tsl.impl.TestSpecificationImpl
		 * @see eu.redset.emf.model.tsl.impl.TslPackageImpl#getTestSpecification()
		 * @generated
		 */
		EClass TEST_SPECIFICATION = eINSTANCE.getTestSpecification();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEST_SPECIFICATION__NAME = eINSTANCE.getTestSpecification_Name();

		/**
		 * The meta object literal for the '<em><b>EReference0</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TEST_SPECIFICATION__EREFERENCE0 = eINSTANCE.getTestSpecification_EReference0();

		/**
		 * The meta object literal for the '<em><b>Sc Uid</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEST_SPECIFICATION__SC_UID = eINSTANCE.getTestSpecification_ScUid();

		/**
		 * The meta object literal for the '<em><b>Uid</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEST_SPECIFICATION__UID = eINSTANCE.getTestSpecification_Uid();

		/**
		 * The meta object literal for the '{@link eu.redset.emf.model.tsl.impl.TestPackageImpl <em>Test Package</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.redset.emf.model.tsl.impl.TestPackageImpl
		 * @see eu.redset.emf.model.tsl.impl.TslPackageImpl#getTestPackage()
		 * @generated
		 */
		EClass TEST_PACKAGE = eINSTANCE.getTestPackage();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEST_PACKAGE__NAME = eINSTANCE.getTestPackage_Name();

		/**
		 * The meta object literal for the '<em><b>Notions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TEST_PACKAGE__NOTIONS = eINSTANCE.getTestPackage_Notions();

		/**
		 * The meta object literal for the '<em><b>NF Tests</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TEST_PACKAGE__NF_TESTS = eINSTANCE.getTestPackage_NFTests();

		/**
		 * The meta object literal for the '<em><b>GUI Tests</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TEST_PACKAGE__GUI_TESTS = eINSTANCE.getTestPackage_GUITests();

		/**
		 * The meta object literal for the '<em><b>BL Tests</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TEST_PACKAGE__BL_TESTS = eINSTANCE.getTestPackage_BLTests();

		/**
		 * The meta object literal for the '<em><b>EReference3</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TEST_PACKAGE__EREFERENCE3 = eINSTANCE.getTestPackage_EReference3();

		/**
		 * The meta object literal for the '<em><b>EReference0</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TEST_PACKAGE__EREFERENCE0 = eINSTANCE.getTestPackage_EReference0();

		/**
		 * The meta object literal for the '<em><b>EReference1</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TEST_PACKAGE__EREFERENCE1 = eINSTANCE.getTestPackage_EReference1();

		/**
		 * The meta object literal for the '{@link eu.redset.emf.model.tsl.impl.TestImpl <em>Test</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.redset.emf.model.tsl.impl.TestImpl
		 * @see eu.redset.emf.model.tsl.impl.TslPackageImpl#getTest()
		 * @generated
		 */
		EClass TEST = eINSTANCE.getTest();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEST__NAME = eINSTANCE.getTest_Name();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEST__TYPE = eINSTANCE.getTest_Type();

		/**
		 * The meta object literal for the '<em><b>Classifier Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEST__CLASSIFIER_ID = eINSTANCE.getTest_ClassifierId();

		/**
		 * The meta object literal for the '<em><b>Content</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEST__CONTENT = eINSTANCE.getTest_Content();

		/**
		 * The meta object literal for the '<em><b>Test Precondition</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEST__TEST_PRECONDITION = eINSTANCE.getTest_TestPrecondition();

		/**
		 * The meta object literal for the '<em><b>Test Postcondition</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEST__TEST_POSTCONDITION = eINSTANCE.getTest_TestPostcondition();

		/**
		 * The meta object literal for the '<em><b>Test Result</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEST__TEST_RESULT = eINSTANCE.getTest_TestResult();

		/**
		 * The meta object literal for the '<em><b>Source</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TEST__SOURCE = eINSTANCE.getTest_Source();

		/**
		 * The meta object literal for the '<em><b>Test Parameters</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TEST__TEST_PARAMETERS = eINSTANCE.getTest_TestParameters();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEST__DESCRIPTION = eINSTANCE.getTest_Description();

		/**
		 * The meta object literal for the '{@link eu.redset.emf.model.tsl.impl.UseCaseTestImpl <em>Use Case Test</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.redset.emf.model.tsl.impl.UseCaseTestImpl
		 * @see eu.redset.emf.model.tsl.impl.TslPackageImpl#getUseCaseTest()
		 * @generated
		 */
		EClass USE_CASE_TEST = eINSTANCE.getUseCaseTest();

		/**
		 * The meta object literal for the '<em><b>EReference0</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference USE_CASE_TEST__EREFERENCE0 = eINSTANCE.getUseCaseTest_EReference0();

		/**
		 * The meta object literal for the '<em><b>Uc Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute USE_CASE_TEST__UC_NAME = eINSTANCE.getUseCaseTest_UcName();

		/**
		 * The meta object literal for the '<em><b>Uc Trail</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute USE_CASE_TEST__UC_TRAIL = eINSTANCE.getUseCaseTest_UcTrail();

		/**
		 * The meta object literal for the '<em><b>Invocation Source</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference USE_CASE_TEST__INVOCATION_SOURCE = eINSTANCE.getUseCaseTest_InvocationSource();

		/**
		 * The meta object literal for the '<em><b>Uid</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute USE_CASE_TEST__UID = eINSTANCE.getUseCaseTest_Uid();

		/**
		 * The meta object literal for the '{@link eu.redset.emf.model.tsl.impl.UseCaseTestScenarioImpl <em>Use Case Test Scenario</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.redset.emf.model.tsl.impl.UseCaseTestScenarioImpl
		 * @see eu.redset.emf.model.tsl.impl.TslPackageImpl#getUseCaseTestScenario()
		 * @generated
		 */
		EClass USE_CASE_TEST_SCENARIO = eINSTANCE.getUseCaseTestScenario();

		/**
		 * The meta object literal for the '<em><b>Sentences</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference USE_CASE_TEST_SCENARIO__SENTENCES = eINSTANCE.getUseCaseTestScenario_Sentences();

		/**
		 * The meta object literal for the '<em><b>Precondition</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference USE_CASE_TEST_SCENARIO__PRECONDITION = eINSTANCE.getUseCaseTestScenario_Precondition();

		/**
		 * The meta object literal for the '<em><b>Postcondition</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference USE_CASE_TEST_SCENARIO__POSTCONDITION = eINSTANCE.getUseCaseTestScenario_Postcondition();

		/**
		 * The meta object literal for the '<em><b>Actor</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute USE_CASE_TEST_SCENARIO__ACTOR = eINSTANCE.getUseCaseTestScenario_Actor();

		/**
		 * The meta object literal for the '{@link eu.redset.emf.model.tsl.impl.UseCaseTestScenarioSentenceImpl <em>Use Case Test Scenario Sentence</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.redset.emf.model.tsl.impl.UseCaseTestScenarioSentenceImpl
		 * @see eu.redset.emf.model.tsl.impl.TslPackageImpl#getUseCaseTestScenarioSentence()
		 * @generated
		 */
		EClass USE_CASE_TEST_SCENARIO_SENTENCE = eINSTANCE.getUseCaseTestScenarioSentence();

		/**
		 * The meta object literal for the '<em><b>EReference0</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference USE_CASE_TEST_SCENARIO_SENTENCE__EREFERENCE0 = eINSTANCE.getUseCaseTestScenarioSentence_EReference0();

		/**
		 * The meta object literal for the '<em><b>EReference1</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference USE_CASE_TEST_SCENARIO_SENTENCE__EREFERENCE1 = eINSTANCE.getUseCaseTestScenarioSentence_EReference1();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute USE_CASE_TEST_SCENARIO_SENTENCE__NAME = eINSTANCE.getUseCaseTestScenarioSentence_Name();

		/**
		 * The meta object literal for the '<em><b>Scenario Sentence</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference USE_CASE_TEST_SCENARIO_SENTENCE__SCENARIO_SENTENCE = eINSTANCE.getUseCaseTestScenarioSentence_ScenarioSentence();

		/**
		 * The meta object literal for the '{@link eu.redset.emf.model.tsl.impl.TestScenarioImpl <em>Test Scenario</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.redset.emf.model.tsl.impl.TestScenarioImpl
		 * @see eu.redset.emf.model.tsl.impl.TslPackageImpl#getTestScenario()
		 * @generated
		 */
		EClass TEST_SCENARIO = eINSTANCE.getTestScenario();

		/**
		 * The meta object literal for the '<em><b>EReference0</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TEST_SCENARIO__EREFERENCE0 = eINSTANCE.getTestScenario_EReference0();

		/**
		 * The meta object literal for the '<em><b>EReference1</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TEST_SCENARIO__EREFERENCE1 = eINSTANCE.getTestScenario_EReference1();

		/**
		 * The meta object literal for the '{@link eu.redset.emf.model.tsl.impl.TestCaseImpl <em>Test Case</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.redset.emf.model.tsl.impl.TestCaseImpl
		 * @see eu.redset.emf.model.tsl.impl.TslPackageImpl#getTestCase()
		 * @generated
		 */
		EClass TEST_CASE = eINSTANCE.getTestCase();

		/**
		 * The meta object literal for the '<em><b>EReference1</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TEST_CASE__EREFERENCE1 = eINSTANCE.getTestCase_EReference1();

		/**
		 * The meta object literal for the '<em><b>Order Number</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEST_CASE__ORDER_NUMBER = eINSTANCE.getTestCase_OrderNumber();

		/**
		 * The meta object literal for the '<em><b>Precondition</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TEST_CASE__PRECONDITION = eINSTANCE.getTestCase_Precondition();

		/**
		 * The meta object literal for the '<em><b>Postcondition</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TEST_CASE__POSTCONDITION = eINSTANCE.getTestCase_Postcondition();

		/**
		 * The meta object literal for the '<em><b>Uc Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEST_CASE__UC_NAME = eINSTANCE.getTestCase_UcName();

		/**
		 * The meta object literal for the '<em><b>Uc Trail</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEST_CASE__UC_TRAIL = eINSTANCE.getTestCase_UcTrail();

		/**
		 * The meta object literal for the '<em><b>Uc Scenario Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEST_CASE__UC_SCENARIO_NAME = eINSTANCE.getTestCase_UcScenarioName();

		/**
		 * The meta object literal for the '<em><b>Sentences</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TEST_CASE__SENTENCES = eINSTANCE.getTestCase_Sentences();

		/**
		 * The meta object literal for the '<em><b>Order Number Global</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEST_CASE__ORDER_NUMBER_GLOBAL = eINSTANCE.getTestCase_OrderNumberGlobal();

		/**
		 * The meta object literal for the '{@link eu.redset.emf.model.tsl.impl.TestCaseSentenceImpl <em>Test Case Sentence</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.redset.emf.model.tsl.impl.TestCaseSentenceImpl
		 * @see eu.redset.emf.model.tsl.impl.TslPackageImpl#getTestCaseSentence()
		 * @generated
		 */
		EClass TEST_CASE_SENTENCE = eINSTANCE.getTestCaseSentence();

		/**
		 * The meta object literal for the '<em><b>EReference0</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TEST_CASE_SENTENCE__EREFERENCE0 = eINSTANCE.getTestCaseSentence_EReference0();

		/**
		 * The meta object literal for the '<em><b>Scenario Sentence</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TEST_CASE_SENTENCE__SCENARIO_SENTENCE = eINSTANCE.getTestCaseSentence_ScenarioSentence();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEST_CASE_SENTENCE__NAME = eINSTANCE.getTestCaseSentence_Name();

		/**
		 * The meta object literal for the '<em><b>Context</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TEST_CASE_SENTENCE__CONTEXT = eINSTANCE.getTestCaseSentence_Context();

		/**
		 * The meta object literal for the '{@link eu.redset.emf.model.tsl.impl.UseCaseTestScenarioInstanceImpl <em>Use Case Test Scenario Instance</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.redset.emf.model.tsl.impl.UseCaseTestScenarioInstanceImpl
		 * @see eu.redset.emf.model.tsl.impl.TslPackageImpl#getUseCaseTestScenarioInstance()
		 * @generated
		 */
		EClass USE_CASE_TEST_SCENARIO_INSTANCE = eINSTANCE.getUseCaseTestScenarioInstance();

		/**
		 * The meta object literal for the '<em><b>EReference3</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference USE_CASE_TEST_SCENARIO_INSTANCE__EREFERENCE3 = eINSTANCE.getUseCaseTestScenarioInstance_EReference3();

		/**
		 * The meta object literal for the '<em><b>Uc Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute USE_CASE_TEST_SCENARIO_INSTANCE__UC_NAME = eINSTANCE.getUseCaseTestScenarioInstance_UcName();

		/**
		 * The meta object literal for the '<em><b>Uc Trail</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute USE_CASE_TEST_SCENARIO_INSTANCE__UC_TRAIL = eINSTANCE.getUseCaseTestScenarioInstance_UcTrail();

		/**
		 * The meta object literal for the '{@link eu.redset.emf.model.tsl.impl.ScenarioSentenceImpl <em>Scenario Sentence</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.redset.emf.model.tsl.impl.ScenarioSentenceImpl
		 * @see eu.redset.emf.model.tsl.impl.TslPackageImpl#getScenarioSentence()
		 * @generated
		 */
		EClass SCENARIO_SENTENCE = eINSTANCE.getScenarioSentence();

		/**
		 * The meta object literal for the '{@link eu.redset.emf.model.tsl.impl.SVOSentenceImpl <em>SVO Sentence</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.redset.emf.model.tsl.impl.SVOSentenceImpl
		 * @see eu.redset.emf.model.tsl.impl.TslPackageImpl#getSVOSentence()
		 * @generated
		 */
		EClass SVO_SENTENCE = eINSTANCE.getSVOSentence();

		/**
		 * The meta object literal for the '<em><b>Number</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SVO_SENTENCE__NUMBER = eINSTANCE.getSVOSentence_Number();

		/**
		 * The meta object literal for the '<em><b>Predicate</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SVO_SENTENCE__PREDICATE = eINSTANCE.getSVOSentence_Predicate();

		/**
		 * The meta object literal for the '<em><b>Direct Object</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SVO_SENTENCE__DIRECT_OBJECT = eINSTANCE.getSVOSentence_DirectObject();

		/**
		 * The meta object literal for the '<em><b>Indirect Object</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SVO_SENTENCE__INDIRECT_OBJECT = eINSTANCE.getSVOSentence_IndirectObject();

		/**
		 * The meta object literal for the '{@link eu.redset.emf.model.tsl.impl.ControlSentenceImpl <em>Control Sentence</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.redset.emf.model.tsl.impl.ControlSentenceImpl
		 * @see eu.redset.emf.model.tsl.impl.TslPackageImpl#getControlSentence()
		 * @generated
		 */
		EClass CONTROL_SENTENCE = eINSTANCE.getControlSentence();

		/**
		 * The meta object literal for the '<em><b>Invocation</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTROL_SENTENCE__INVOCATION = eINSTANCE.getControlSentence_Invocation();

		/**
		 * The meta object literal for the '{@link eu.redset.emf.model.tsl.impl.ConditionSentenceImpl <em>Condition Sentence</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.redset.emf.model.tsl.impl.ConditionSentenceImpl
		 * @see eu.redset.emf.model.tsl.impl.TslPackageImpl#getConditionSentence()
		 * @generated
		 */
		EClass CONDITION_SENTENCE = eINSTANCE.getConditionSentence();

		/**
		 * The meta object literal for the '{@link eu.redset.emf.model.tsl.impl.PreconditionImpl <em>Precondition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.redset.emf.model.tsl.impl.PreconditionImpl
		 * @see eu.redset.emf.model.tsl.impl.TslPackageImpl#getPrecondition()
		 * @generated
		 */
		EClass PRECONDITION = eINSTANCE.getPrecondition();

		/**
		 * The meta object literal for the '{@link eu.redset.emf.model.tsl.impl.PostconditionImpl <em>Postcondition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.redset.emf.model.tsl.impl.PostconditionImpl
		 * @see eu.redset.emf.model.tsl.impl.TslPackageImpl#getPostcondition()
		 * @generated
		 */
		EClass POSTCONDITION = eINSTANCE.getPostcondition();

		/**
		 * The meta object literal for the '{@link eu.redset.emf.model.tsl.impl.ConditionImpl <em>Condition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.redset.emf.model.tsl.impl.ConditionImpl
		 * @see eu.redset.emf.model.tsl.impl.TslPackageImpl#getCondition()
		 * @generated
		 */
		EClass CONDITION = eINSTANCE.getCondition();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONDITION__NAME = eINSTANCE.getCondition_Name();

		/**
		 * The meta object literal for the '{@link eu.redset.emf.model.tsl.impl.TestRelationshipImpl <em>Test Relationship</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.redset.emf.model.tsl.impl.TestRelationshipImpl
		 * @see eu.redset.emf.model.tsl.impl.TslPackageImpl#getTestRelationship()
		 * @generated
		 */
		EClass TEST_RELATIONSHIP = eINSTANCE.getTestRelationship();

		/**
		 * The meta object literal for the '<em><b>Target</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TEST_RELATIONSHIP__TARGET = eINSTANCE.getTestRelationship_Target();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEST_RELATIONSHIP__TYPE = eINSTANCE.getTestRelationship_Type();

		/**
		 * The meta object literal for the '{@link eu.redset.emf.model.tsl.impl.TestInvocationRelationshipImpl <em>Test Invocation Relationship</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.redset.emf.model.tsl.impl.TestInvocationRelationshipImpl
		 * @see eu.redset.emf.model.tsl.impl.TslPackageImpl#getTestInvocationRelationship()
		 * @generated
		 */
		EClass TEST_INVOCATION_RELATIONSHIP = eINSTANCE.getTestInvocationRelationship();

		/**
		 * The meta object literal for the '<em><b>EReference0</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TEST_INVOCATION_RELATIONSHIP__EREFERENCE0 = eINSTANCE.getTestInvocationRelationship_EReference0();

		/**
		 * The meta object literal for the '<em><b>Invocation Target</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TEST_INVOCATION_RELATIONSHIP__INVOCATION_TARGET = eINSTANCE.getTestInvocationRelationship_InvocationTarget();

		/**
		 * The meta object literal for the '<em><b>Uid</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEST_INVOCATION_RELATIONSHIP__UID = eINSTANCE.getTestInvocationRelationship_Uid();

		/**
		 * The meta object literal for the '<em><b>Include Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEST_INVOCATION_RELATIONSHIP__INCLUDE_TYPE = eINSTANCE.getTestInvocationRelationship_IncludeType();

		/**
		 * The meta object literal for the '{@link eu.redset.emf.model.tsl.impl.DomainObjectImpl <em>Domain Object</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.redset.emf.model.tsl.impl.DomainObjectImpl
		 * @see eu.redset.emf.model.tsl.impl.TslPackageImpl#getDomainObject()
		 * @generated
		 */
		EClass DOMAIN_OBJECT = eINSTANCE.getDomainObject();

		/**
		 * The meta object literal for the '<em><b>Input Data</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOMAIN_OBJECT__INPUT_DATA = eINSTANCE.getDomainObject_InputData();

		/**
		 * The meta object literal for the '<em><b>Domain Object Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOMAIN_OBJECT__DOMAIN_OBJECT_TYPE = eINSTANCE.getDomainObject_DomainObjectType();

		/**
		 * The meta object literal for the '{@link eu.redset.emf.model.tsl.impl.NotionAttributeImpl <em>Notion Attribute</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.redset.emf.model.tsl.impl.NotionAttributeImpl
		 * @see eu.redset.emf.model.tsl.impl.TslPackageImpl#getNotionAttribute()
		 * @generated
		 */
		EClass NOTION_ATTRIBUTE = eINSTANCE.getNotionAttribute();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NOTION_ATTRIBUTE__TYPE = eINSTANCE.getNotionAttribute_Type();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NOTION_ATTRIBUTE__NAME = eINSTANCE.getNotionAttribute_Name();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NOTION_ATTRIBUTE__DESCRIPTION = eINSTANCE.getNotionAttribute_Description();

		/**
		 * The meta object literal for the '{@link eu.redset.emf.model.tsl.impl.NFTestImpl <em>NF Test</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.redset.emf.model.tsl.impl.NFTestImpl
		 * @see eu.redset.emf.model.tsl.impl.TslPackageImpl#getNFTest()
		 * @generated
		 */
		EClass NF_TEST = eINSTANCE.getNFTest();

		/**
		 * The meta object literal for the '<em><b>Nfr Trail</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NF_TEST__NFR_TRAIL = eINSTANCE.getNFTest_NfrTrail();

		/**
		 * The meta object literal for the '<em><b>Uid</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NF_TEST__UID = eINSTANCE.getNFTest_Uid();

		/**
		 * The meta object literal for the '{@link eu.redset.emf.model.tsl.impl.GUITestImpl <em>GUI Test</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.redset.emf.model.tsl.impl.GUITestImpl
		 * @see eu.redset.emf.model.tsl.impl.TslPackageImpl#getGUITest()
		 * @generated
		 */
		EClass GUI_TEST = eINSTANCE.getGUITest();

		/**
		 * The meta object literal for the '{@link eu.redset.emf.model.tsl.impl.BLTestImpl <em>BL Test</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.redset.emf.model.tsl.impl.BLTestImpl
		 * @see eu.redset.emf.model.tsl.impl.TslPackageImpl#getBLTest()
		 * @generated
		 */
		EClass BL_TEST = eINSTANCE.getBLTest();

		/**
		 * The meta object literal for the '{@link eu.redset.emf.model.tsl.impl.NotionImpl <em>Notion</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.redset.emf.model.tsl.impl.NotionImpl
		 * @see eu.redset.emf.model.tsl.impl.TslPackageImpl#getNotion()
		 * @generated
		 */
		EClass NOTION = eINSTANCE.getNotion();

		/**
		 * The meta object literal for the '<em><b>Domain Statements</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NOTION__DOMAIN_STATEMENTS = eINSTANCE.getNotion_DomainStatements();

		/**
		 * The meta object literal for the '<em><b>Notion Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NOTION__NOTION_NAME = eINSTANCE.getNotion_NotionName();

		/**
		 * The meta object literal for the '<em><b>Notion Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NOTION__NOTION_DESCRIPTION = eINSTANCE.getNotion_NotionDescription();

		/**
		 * The meta object literal for the '<em><b>Notion Trail</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NOTION__NOTION_TRAIL = eINSTANCE.getNotion_NotionTrail();

		/**
		 * The meta object literal for the '<em><b>Attributes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NOTION__ATTRIBUTES = eINSTANCE.getNotion_Attributes();

		/**
		 * The meta object literal for the '<em><b>Uid</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NOTION__UID = eINSTANCE.getNotion_Uid();

		/**
		 * The meta object literal for the '{@link eu.redset.emf.model.tsl.impl.DomainStatementImpl <em>Domain Statement</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.redset.emf.model.tsl.impl.DomainStatementImpl
		 * @see eu.redset.emf.model.tsl.impl.TslPackageImpl#getDomainStatement()
		 * @generated
		 */
		EClass DOMAIN_STATEMENT = eINSTANCE.getDomainStatement();

		/**
		 * The meta object literal for the '<em><b>Direct Notion</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOMAIN_STATEMENT__DIRECT_NOTION = eINSTANCE.getDomainStatement_DirectNotion();

		/**
		 * The meta object literal for the '<em><b>Indirect Notion</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOMAIN_STATEMENT__INDIRECT_NOTION = eINSTANCE.getDomainStatement_IndirectNotion();

		/**
		 * The meta object literal for the '<em><b>Phrase Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOMAIN_STATEMENT__PHRASE_NAME = eINSTANCE.getDomainStatement_PhraseName();

		/**
		 * The meta object literal for the '<em><b>Phrase Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOMAIN_STATEMENT__PHRASE_DESCRIPTION = eINSTANCE.getDomainStatement_PhraseDescription();

		/**
		 * The meta object literal for the '<em><b>Uid</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOMAIN_STATEMENT__UID = eINSTANCE.getDomainStatement_Uid();

		/**
		 * The meta object literal for the '{@link eu.redset.emf.model.tsl.impl.CConditionImpl <em>CCondition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.redset.emf.model.tsl.impl.CConditionImpl
		 * @see eu.redset.emf.model.tsl.impl.TslPackageImpl#getCCondition()
		 * @generated
		 */
		EClass CCONDITION = eINSTANCE.getCCondition();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CCONDITION__NAME = eINSTANCE.getCCondition_Name();

		/**
		 * The meta object literal for the '{@link eu.redset.emf.model.tsl.impl.TestValueImpl <em>Test Value</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.redset.emf.model.tsl.impl.TestValueImpl
		 * @see eu.redset.emf.model.tsl.impl.TslPackageImpl#getTestValue()
		 * @generated
		 */
		EClass TEST_VALUE = eINSTANCE.getTestValue();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEST_VALUE__VALUE = eINSTANCE.getTestValue_Value();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEST_VALUE__NAME = eINSTANCE.getTestValue_Name();

		/**
		 * The meta object literal for the '{@link eu.redset.emf.model.tsl.impl.TestParameterImpl <em>Test Parameter</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.redset.emf.model.tsl.impl.TestParameterImpl
		 * @see eu.redset.emf.model.tsl.impl.TslPackageImpl#getTestParameter()
		 * @generated
		 */
		EClass TEST_PARAMETER = eINSTANCE.getTestParameter();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEST_PARAMETER__NAME = eINSTANCE.getTestParameter_Name();

		/**
		 * The meta object literal for the '<em><b>Domain</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEST_PARAMETER__DOMAIN = eINSTANCE.getTestParameter_Domain();

		/**
		 * The meta object literal for the '<em><b>Expected Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEST_PARAMETER__EXPECTED_VALUE = eINSTANCE.getTestParameter_ExpectedValue();

		/**
		 * The meta object literal for the '{@link eu.redset.emf.model.tsl.impl.TestInstanceImpl <em>Test Instance</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.redset.emf.model.tsl.impl.TestInstanceImpl
		 * @see eu.redset.emf.model.tsl.impl.TslPackageImpl#getTestInstance()
		 * @generated
		 */
		EClass TEST_INSTANCE = eINSTANCE.getTestInstance();

		/**
		 * The meta object literal for the '<em><b>Attached Tests</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TEST_INSTANCE__ATTACHED_TESTS = eINSTANCE.getTestInstance_AttachedTests();

		/**
		 * The meta object literal for the '{@link eu.redset.emf.model.tsl.impl.NFTestInstanceImpl <em>NF Test Instance</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.redset.emf.model.tsl.impl.NFTestInstanceImpl
		 * @see eu.redset.emf.model.tsl.impl.TslPackageImpl#getNFTestInstance()
		 * @generated
		 */
		EClass NF_TEST_INSTANCE = eINSTANCE.getNFTestInstance();

		/**
		 * The meta object literal for the '{@link eu.redset.emf.model.tsl.impl.GUITestInstanceImpl <em>GUI Test Instance</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.redset.emf.model.tsl.impl.GUITestInstanceImpl
		 * @see eu.redset.emf.model.tsl.impl.TslPackageImpl#getGUITestInstance()
		 * @generated
		 */
		EClass GUI_TEST_INSTANCE = eINSTANCE.getGUITestInstance();

		/**
		 * The meta object literal for the '{@link eu.redset.emf.model.tsl.impl.BLTestInstanceImpl <em>BL Test Instance</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.redset.emf.model.tsl.impl.BLTestInstanceImpl
		 * @see eu.redset.emf.model.tsl.impl.TslPackageImpl#getBLTestInstance()
		 * @generated
		 */
		EClass BL_TEST_INSTANCE = eINSTANCE.getBLTestInstance();

		/**
		 * The meta object literal for the '{@link eu.redset.emf.model.tsl.impl.TestDataValuesContextImpl <em>Test Data Values Context</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.redset.emf.model.tsl.impl.TestDataValuesContextImpl
		 * @see eu.redset.emf.model.tsl.impl.TslPackageImpl#getTestDataValuesContext()
		 * @generated
		 */
		EClass TEST_DATA_VALUES_CONTEXT = eINSTANCE.getTestDataValuesContext();

		/**
		 * The meta object literal for the '{@link eu.redset.emf.model.tsl.DomainObjectType <em>Domain Object Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eu.redset.emf.model.tsl.DomainObjectType
		 * @see eu.redset.emf.model.tsl.impl.TslPackageImpl#getDomainObjectType()
		 * @generated
		 */
		EEnum DOMAIN_OBJECT_TYPE = eINSTANCE.getDomainObjectType();

	}

} //TslPackage
