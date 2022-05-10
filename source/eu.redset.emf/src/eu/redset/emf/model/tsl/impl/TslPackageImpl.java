/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package eu.redset.emf.model.tsl.impl;

import eu.redset.emf.model.tsl.BLTest;
import eu.redset.emf.model.tsl.BLTestInstance;
import eu.redset.emf.model.tsl.CCondition;
import eu.redset.emf.model.tsl.Condition;
import eu.redset.emf.model.tsl.ConditionSentence;
import eu.redset.emf.model.tsl.ControlSentence;
import eu.redset.emf.model.tsl.DomainObject;
import eu.redset.emf.model.tsl.DomainObjectType;
import eu.redset.emf.model.tsl.DomainStatement;
import eu.redset.emf.model.tsl.GUITest;
import eu.redset.emf.model.tsl.GUITestInstance;
import eu.redset.emf.model.tsl.NFTest;
import eu.redset.emf.model.tsl.NFTestInstance;
import eu.redset.emf.model.tsl.Notion;
import eu.redset.emf.model.tsl.NotionAttribute;
import eu.redset.emf.model.tsl.Postcondition;
import eu.redset.emf.model.tsl.Precondition;
import eu.redset.emf.model.tsl.SVOSentence;
import eu.redset.emf.model.tsl.ScenarioSentence;
import eu.redset.emf.model.tsl.Test;
import eu.redset.emf.model.tsl.TestCase;
import eu.redset.emf.model.tsl.TestCaseSentence;
import eu.redset.emf.model.tsl.TestDataValuesContext;
import eu.redset.emf.model.tsl.TestInstance;
import eu.redset.emf.model.tsl.TestInvocationRelationship;
import eu.redset.emf.model.tsl.TestPackage;
import eu.redset.emf.model.tsl.TestParameter;
import eu.redset.emf.model.tsl.TestRelationship;
import eu.redset.emf.model.tsl.TestScenario;
import eu.redset.emf.model.tsl.TestSpecification;
import eu.redset.emf.model.tsl.TestValue;
import eu.redset.emf.model.tsl.TslFactory;
import eu.redset.emf.model.tsl.TslPackage;

import eu.redset.emf.model.tsl.UseCaseTest;
import eu.redset.emf.model.tsl.UseCaseTestScenario;
import eu.redset.emf.model.tsl.UseCaseTestScenarioInstance;
import eu.redset.emf.model.tsl.UseCaseTestScenarioSentence;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class TslPackageImpl extends EPackageImpl implements TslPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass testSpecificationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass testPackageEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass testEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass useCaseTestEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass useCaseTestScenarioEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass useCaseTestScenarioSentenceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass testScenarioEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass testCaseEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass testCaseSentenceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass useCaseTestScenarioInstanceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass scenarioSentenceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass svoSentenceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass controlSentenceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass conditionSentenceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass preconditionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass postconditionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass conditionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass testRelationshipEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass testInvocationRelationshipEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass domainObjectEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass notionAttributeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass nfTestEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass guiTestEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass blTestEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass notionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass domainStatementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cConditionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass testValueEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass testParameterEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass testInstanceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass nfTestInstanceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass guiTestInstanceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass blTestInstanceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass testDataValuesContextEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum domainObjectTypeEEnum = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see eu.redset.emf.model.tsl.TslPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private TslPackageImpl() {
		super(eNS_URI, TslFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link TslPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static TslPackage init() {
		if (isInited) return (TslPackage)EPackage.Registry.INSTANCE.getEPackage(TslPackage.eNS_URI);

		// Obtain or create and register package
		TslPackageImpl theTslPackage = (TslPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof TslPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new TslPackageImpl());

		isInited = true;

		// Create package meta-data objects
		theTslPackage.createPackageContents();

		// Initialize created meta-data
		theTslPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theTslPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(TslPackage.eNS_URI, theTslPackage);
		return theTslPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTestSpecification() {
		return testSpecificationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTestSpecification_Name() {
		return (EAttribute)testSpecificationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTestSpecification_EReference0() {
		return (EReference)testSpecificationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTestSpecification_ScUid() {
		return (EAttribute)testSpecificationEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTestSpecification_Uid() {
		return (EAttribute)testSpecificationEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTestPackage() {
		return testPackageEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTestPackage_Name() {
		return (EAttribute)testPackageEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTestPackage_Notions() {
		return (EReference)testPackageEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTestPackage_NFTests() {
		return (EReference)testPackageEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTestPackage_GUITests() {
		return (EReference)testPackageEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTestPackage_BLTests() {
		return (EReference)testPackageEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTestPackage_EReference3() {
		return (EReference)testPackageEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTestPackage_EReference0() {
		return (EReference)testPackageEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTestPackage_EReference1() {
		return (EReference)testPackageEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTest() {
		return testEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTest_Name() {
		return (EAttribute)testEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTest_Type() {
		return (EAttribute)testEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTest_ClassifierId() {
		return (EAttribute)testEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTest_Content() {
		return (EAttribute)testEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTest_TestPrecondition() {
		return (EAttribute)testEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTest_TestPostcondition() {
		return (EAttribute)testEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTest_TestResult() {
		return (EAttribute)testEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTest_Source() {
		return (EReference)testEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTest_TestParameters() {
		return (EReference)testEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTest_Description() {
		return (EAttribute)testEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUseCaseTest() {
		return useCaseTestEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getUseCaseTest_EReference0() {
		return (EReference)useCaseTestEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getUseCaseTest_UcName() {
		return (EAttribute)useCaseTestEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getUseCaseTest_UcTrail() {
		return (EAttribute)useCaseTestEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getUseCaseTest_InvocationSource() {
		return (EReference)useCaseTestEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getUseCaseTest_Uid() {
		return (EAttribute)useCaseTestEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUseCaseTestScenario() {
		return useCaseTestScenarioEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getUseCaseTestScenario_Sentences() {
		return (EReference)useCaseTestScenarioEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getUseCaseTestScenario_Precondition() {
		return (EReference)useCaseTestScenarioEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getUseCaseTestScenario_Postcondition() {
		return (EReference)useCaseTestScenarioEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getUseCaseTestScenario_Actor() {
		return (EAttribute)useCaseTestScenarioEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUseCaseTestScenarioSentence() {
		return useCaseTestScenarioSentenceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getUseCaseTestScenarioSentence_EReference0() {
		return (EReference)useCaseTestScenarioSentenceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getUseCaseTestScenarioSentence_EReference1() {
		return (EReference)useCaseTestScenarioSentenceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getUseCaseTestScenarioSentence_Name() {
		return (EAttribute)useCaseTestScenarioSentenceEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getUseCaseTestScenarioSentence_ScenarioSentence() {
		return (EReference)useCaseTestScenarioSentenceEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTestScenario() {
		return testScenarioEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTestScenario_EReference0() {
		return (EReference)testScenarioEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTestScenario_EReference1() {
		return (EReference)testScenarioEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTestCase() {
		return testCaseEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTestCase_EReference1() {
		return (EReference)testCaseEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTestCase_OrderNumber() {
		return (EAttribute)testCaseEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTestCase_Precondition() {
		return (EReference)testCaseEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTestCase_Postcondition() {
		return (EReference)testCaseEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTestCase_UcName() {
		return (EAttribute)testCaseEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTestCase_UcTrail() {
		return (EAttribute)testCaseEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTestCase_UcScenarioName() {
		return (EAttribute)testCaseEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTestCase_Sentences() {
		return (EReference)testCaseEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTestCase_OrderNumberGlobal() {
		return (EAttribute)testCaseEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTestCaseSentence() {
		return testCaseSentenceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTestCaseSentence_EReference0() {
		return (EReference)testCaseSentenceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTestCaseSentence_ScenarioSentence() {
		return (EReference)testCaseSentenceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTestCaseSentence_Name() {
		return (EAttribute)testCaseSentenceEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTestCaseSentence_Context() {
		return (EReference)testCaseSentenceEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUseCaseTestScenarioInstance() {
		return useCaseTestScenarioInstanceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getUseCaseTestScenarioInstance_EReference3() {
		return (EReference)useCaseTestScenarioInstanceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getUseCaseTestScenarioInstance_UcName() {
		return (EAttribute)useCaseTestScenarioInstanceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getUseCaseTestScenarioInstance_UcTrail() {
		return (EAttribute)useCaseTestScenarioInstanceEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getScenarioSentence() {
		return scenarioSentenceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSVOSentence() {
		return svoSentenceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSVOSentence_Number() {
		return (EAttribute)svoSentenceEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSVOSentence_Predicate() {
		return (EReference)svoSentenceEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSVOSentence_DirectObject() {
		return (EReference)svoSentenceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSVOSentence_IndirectObject() {
		return (EReference)svoSentenceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getControlSentence() {
		return controlSentenceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getControlSentence_Invocation() {
		return (EReference)controlSentenceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getConditionSentence() {
		return conditionSentenceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPrecondition() {
		return preconditionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPostcondition() {
		return postconditionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCondition() {
		return conditionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCondition_Name() {
		return (EAttribute)conditionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTestRelationship() {
		return testRelationshipEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTestRelationship_Target() {
		return (EReference)testRelationshipEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTestRelationship_Type() {
		return (EAttribute)testRelationshipEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTestInvocationRelationship() {
		return testInvocationRelationshipEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTestInvocationRelationship_EReference0() {
		return (EReference)testInvocationRelationshipEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTestInvocationRelationship_InvocationTarget() {
		return (EReference)testInvocationRelationshipEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTestInvocationRelationship_Uid() {
		return (EAttribute)testInvocationRelationshipEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTestInvocationRelationship_IncludeType() {
		return (EAttribute)testInvocationRelationshipEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDomainObject() {
		return domainObjectEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDomainObject_InputData() {
		return (EReference)domainObjectEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDomainObject_DomainObjectType() {
		return (EAttribute)domainObjectEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getNotionAttribute() {
		return notionAttributeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getNotionAttribute_Type() {
		return (EAttribute)notionAttributeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getNotionAttribute_Name() {
		return (EAttribute)notionAttributeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getNotionAttribute_Description() {
		return (EAttribute)notionAttributeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getNFTest() {
		return nfTestEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getNFTest_NfrTrail() {
		return (EAttribute)nfTestEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getNFTest_Uid() {
		return (EAttribute)nfTestEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getGUITest() {
		return guiTestEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBLTest() {
		return blTestEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getNotion() {
		return notionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getNotion_DomainStatements() {
		return (EReference)notionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getNotion_NotionName() {
		return (EAttribute)notionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getNotion_NotionDescription() {
		return (EAttribute)notionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getNotion_NotionTrail() {
		return (EAttribute)notionEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getNotion_Attributes() {
		return (EReference)notionEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getNotion_Uid() {
		return (EAttribute)notionEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDomainStatement() {
		return domainStatementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDomainStatement_DirectNotion() {
		return (EReference)domainStatementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDomainStatement_IndirectNotion() {
		return (EReference)domainStatementEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDomainStatement_PhraseName() {
		return (EAttribute)domainStatementEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDomainStatement_PhraseDescription() {
		return (EAttribute)domainStatementEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDomainStatement_Uid() {
		return (EAttribute)domainStatementEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCCondition() {
		return cConditionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCCondition_Name() {
		return (EAttribute)cConditionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTestValue() {
		return testValueEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTestValue_Value() {
		return (EAttribute)testValueEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTestValue_Name() {
		return (EAttribute)testValueEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTestParameter() {
		return testParameterEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTestParameter_Name() {
		return (EAttribute)testParameterEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTestParameter_Domain() {
		return (EAttribute)testParameterEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTestParameter_ExpectedValue() {
		return (EAttribute)testParameterEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTestInstance() {
		return testInstanceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTestInstance_AttachedTests() {
		return (EReference)testInstanceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getNFTestInstance() {
		return nfTestInstanceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getGUITestInstance() {
		return guiTestInstanceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBLTestInstance() {
		return blTestInstanceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTestDataValuesContext() {
		return testDataValuesContextEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getDomainObjectType() {
		return domainObjectTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TslFactory getTslFactory() {
		return (TslFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		testSpecificationEClass = createEClass(TEST_SPECIFICATION);
		createEAttribute(testSpecificationEClass, TEST_SPECIFICATION__NAME);
		createEReference(testSpecificationEClass, TEST_SPECIFICATION__EREFERENCE0);
		createEAttribute(testSpecificationEClass, TEST_SPECIFICATION__SC_UID);
		createEAttribute(testSpecificationEClass, TEST_SPECIFICATION__UID);

		testPackageEClass = createEClass(TEST_PACKAGE);
		createEReference(testPackageEClass, TEST_PACKAGE__EREFERENCE0);
		createEReference(testPackageEClass, TEST_PACKAGE__EREFERENCE1);
		createEReference(testPackageEClass, TEST_PACKAGE__EREFERENCE3);
		createEReference(testPackageEClass, TEST_PACKAGE__NOTIONS);
		createEReference(testPackageEClass, TEST_PACKAGE__NF_TESTS);
		createEReference(testPackageEClass, TEST_PACKAGE__GUI_TESTS);
		createEReference(testPackageEClass, TEST_PACKAGE__BL_TESTS);
		createEAttribute(testPackageEClass, TEST_PACKAGE__NAME);

		testEClass = createEClass(TEST);
		createEAttribute(testEClass, TEST__NAME);
		createEAttribute(testEClass, TEST__TYPE);
		createEAttribute(testEClass, TEST__DESCRIPTION);
		createEAttribute(testEClass, TEST__CLASSIFIER_ID);
		createEAttribute(testEClass, TEST__CONTENT);
		createEAttribute(testEClass, TEST__TEST_PRECONDITION);
		createEAttribute(testEClass, TEST__TEST_POSTCONDITION);
		createEAttribute(testEClass, TEST__TEST_RESULT);
		createEReference(testEClass, TEST__SOURCE);
		createEReference(testEClass, TEST__TEST_PARAMETERS);

		useCaseTestEClass = createEClass(USE_CASE_TEST);
		createEReference(useCaseTestEClass, USE_CASE_TEST__EREFERENCE0);
		createEAttribute(useCaseTestEClass, USE_CASE_TEST__UC_NAME);
		createEAttribute(useCaseTestEClass, USE_CASE_TEST__UC_TRAIL);
		createEReference(useCaseTestEClass, USE_CASE_TEST__INVOCATION_SOURCE);
		createEAttribute(useCaseTestEClass, USE_CASE_TEST__UID);

		useCaseTestScenarioEClass = createEClass(USE_CASE_TEST_SCENARIO);
		createEReference(useCaseTestScenarioEClass, USE_CASE_TEST_SCENARIO__SENTENCES);
		createEAttribute(useCaseTestScenarioEClass, USE_CASE_TEST_SCENARIO__ACTOR);
		createEReference(useCaseTestScenarioEClass, USE_CASE_TEST_SCENARIO__PRECONDITION);
		createEReference(useCaseTestScenarioEClass, USE_CASE_TEST_SCENARIO__POSTCONDITION);

		useCaseTestScenarioSentenceEClass = createEClass(USE_CASE_TEST_SCENARIO_SENTENCE);
		createEReference(useCaseTestScenarioSentenceEClass, USE_CASE_TEST_SCENARIO_SENTENCE__EREFERENCE0);
		createEReference(useCaseTestScenarioSentenceEClass, USE_CASE_TEST_SCENARIO_SENTENCE__EREFERENCE1);
		createEReference(useCaseTestScenarioSentenceEClass, USE_CASE_TEST_SCENARIO_SENTENCE__SCENARIO_SENTENCE);
		createEAttribute(useCaseTestScenarioSentenceEClass, USE_CASE_TEST_SCENARIO_SENTENCE__NAME);

		testScenarioEClass = createEClass(TEST_SCENARIO);
		createEReference(testScenarioEClass, TEST_SCENARIO__EREFERENCE0);
		createEReference(testScenarioEClass, TEST_SCENARIO__EREFERENCE1);

		testCaseEClass = createEClass(TEST_CASE);
		createEReference(testCaseEClass, TEST_CASE__EREFERENCE1);
		createEAttribute(testCaseEClass, TEST_CASE__ORDER_NUMBER);
		createEReference(testCaseEClass, TEST_CASE__PRECONDITION);
		createEReference(testCaseEClass, TEST_CASE__POSTCONDITION);
		createEAttribute(testCaseEClass, TEST_CASE__UC_NAME);
		createEAttribute(testCaseEClass, TEST_CASE__UC_TRAIL);
		createEAttribute(testCaseEClass, TEST_CASE__UC_SCENARIO_NAME);
		createEReference(testCaseEClass, TEST_CASE__SENTENCES);
		createEAttribute(testCaseEClass, TEST_CASE__ORDER_NUMBER_GLOBAL);

		testCaseSentenceEClass = createEClass(TEST_CASE_SENTENCE);
		createEReference(testCaseSentenceEClass, TEST_CASE_SENTENCE__EREFERENCE0);
		createEReference(testCaseSentenceEClass, TEST_CASE_SENTENCE__SCENARIO_SENTENCE);
		createEAttribute(testCaseSentenceEClass, TEST_CASE_SENTENCE__NAME);
		createEReference(testCaseSentenceEClass, TEST_CASE_SENTENCE__CONTEXT);

		useCaseTestScenarioInstanceEClass = createEClass(USE_CASE_TEST_SCENARIO_INSTANCE);
		createEReference(useCaseTestScenarioInstanceEClass, USE_CASE_TEST_SCENARIO_INSTANCE__EREFERENCE3);
		createEAttribute(useCaseTestScenarioInstanceEClass, USE_CASE_TEST_SCENARIO_INSTANCE__UC_NAME);
		createEAttribute(useCaseTestScenarioInstanceEClass, USE_CASE_TEST_SCENARIO_INSTANCE__UC_TRAIL);

		scenarioSentenceEClass = createEClass(SCENARIO_SENTENCE);

		svoSentenceEClass = createEClass(SVO_SENTENCE);
		createEReference(svoSentenceEClass, SVO_SENTENCE__DIRECT_OBJECT);
		createEReference(svoSentenceEClass, SVO_SENTENCE__INDIRECT_OBJECT);
		createEAttribute(svoSentenceEClass, SVO_SENTENCE__NUMBER);
		createEReference(svoSentenceEClass, SVO_SENTENCE__PREDICATE);

		controlSentenceEClass = createEClass(CONTROL_SENTENCE);
		createEReference(controlSentenceEClass, CONTROL_SENTENCE__INVOCATION);

		conditionSentenceEClass = createEClass(CONDITION_SENTENCE);

		preconditionEClass = createEClass(PRECONDITION);

		postconditionEClass = createEClass(POSTCONDITION);

		conditionEClass = createEClass(CONDITION);
		createEAttribute(conditionEClass, CONDITION__NAME);

		testRelationshipEClass = createEClass(TEST_RELATIONSHIP);
		createEReference(testRelationshipEClass, TEST_RELATIONSHIP__TARGET);
		createEAttribute(testRelationshipEClass, TEST_RELATIONSHIP__TYPE);

		testInvocationRelationshipEClass = createEClass(TEST_INVOCATION_RELATIONSHIP);
		createEReference(testInvocationRelationshipEClass, TEST_INVOCATION_RELATIONSHIP__EREFERENCE0);
		createEReference(testInvocationRelationshipEClass, TEST_INVOCATION_RELATIONSHIP__INVOCATION_TARGET);
		createEAttribute(testInvocationRelationshipEClass, TEST_INVOCATION_RELATIONSHIP__UID);
		createEAttribute(testInvocationRelationshipEClass, TEST_INVOCATION_RELATIONSHIP__INCLUDE_TYPE);

		domainObjectEClass = createEClass(DOMAIN_OBJECT);
		createEReference(domainObjectEClass, DOMAIN_OBJECT__INPUT_DATA);
		createEAttribute(domainObjectEClass, DOMAIN_OBJECT__DOMAIN_OBJECT_TYPE);

		notionAttributeEClass = createEClass(NOTION_ATTRIBUTE);
		createEAttribute(notionAttributeEClass, NOTION_ATTRIBUTE__TYPE);
		createEAttribute(notionAttributeEClass, NOTION_ATTRIBUTE__NAME);
		createEAttribute(notionAttributeEClass, NOTION_ATTRIBUTE__DESCRIPTION);

		nfTestEClass = createEClass(NF_TEST);
		createEAttribute(nfTestEClass, NF_TEST__NFR_TRAIL);
		createEAttribute(nfTestEClass, NF_TEST__UID);

		guiTestEClass = createEClass(GUI_TEST);

		blTestEClass = createEClass(BL_TEST);

		notionEClass = createEClass(NOTION);
		createEReference(notionEClass, NOTION__DOMAIN_STATEMENTS);
		createEAttribute(notionEClass, NOTION__NOTION_NAME);
		createEAttribute(notionEClass, NOTION__NOTION_DESCRIPTION);
		createEAttribute(notionEClass, NOTION__NOTION_TRAIL);
		createEReference(notionEClass, NOTION__ATTRIBUTES);
		createEAttribute(notionEClass, NOTION__UID);

		domainStatementEClass = createEClass(DOMAIN_STATEMENT);
		createEReference(domainStatementEClass, DOMAIN_STATEMENT__DIRECT_NOTION);
		createEReference(domainStatementEClass, DOMAIN_STATEMENT__INDIRECT_NOTION);
		createEAttribute(domainStatementEClass, DOMAIN_STATEMENT__PHRASE_NAME);
		createEAttribute(domainStatementEClass, DOMAIN_STATEMENT__PHRASE_DESCRIPTION);
		createEAttribute(domainStatementEClass, DOMAIN_STATEMENT__UID);

		cConditionEClass = createEClass(CCONDITION);
		createEAttribute(cConditionEClass, CCONDITION__NAME);

		testValueEClass = createEClass(TEST_VALUE);
		createEAttribute(testValueEClass, TEST_VALUE__VALUE);
		createEAttribute(testValueEClass, TEST_VALUE__NAME);

		testParameterEClass = createEClass(TEST_PARAMETER);
		createEAttribute(testParameterEClass, TEST_PARAMETER__NAME);
		createEAttribute(testParameterEClass, TEST_PARAMETER__DOMAIN);
		createEAttribute(testParameterEClass, TEST_PARAMETER__EXPECTED_VALUE);

		testInstanceEClass = createEClass(TEST_INSTANCE);
		createEReference(testInstanceEClass, TEST_INSTANCE__ATTACHED_TESTS);

		nfTestInstanceEClass = createEClass(NF_TEST_INSTANCE);

		guiTestInstanceEClass = createEClass(GUI_TEST_INSTANCE);

		blTestInstanceEClass = createEClass(BL_TEST_INSTANCE);

		testDataValuesContextEClass = createEClass(TEST_DATA_VALUES_CONTEXT);

		// Create enums
		domainObjectTypeEEnum = createEEnum(DOMAIN_OBJECT_TYPE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		useCaseTestEClass.getESuperTypes().add(this.getTest());
		useCaseTestScenarioEClass.getESuperTypes().add(this.getTest());
		testScenarioEClass.getESuperTypes().add(this.getTest());
		testCaseEClass.getESuperTypes().add(this.getTestInstance());
		useCaseTestScenarioInstanceEClass.getESuperTypes().add(this.getUseCaseTestScenario());
		scenarioSentenceEClass.getESuperTypes().add(this.getTestInstance());
		svoSentenceEClass.getESuperTypes().add(this.getScenarioSentence());
		controlSentenceEClass.getESuperTypes().add(this.getScenarioSentence());
		conditionSentenceEClass.getESuperTypes().add(this.getScenarioSentence());
		preconditionEClass.getESuperTypes().add(this.getCondition());
		postconditionEClass.getESuperTypes().add(this.getCondition());
		testInvocationRelationshipEClass.getESuperTypes().add(this.getTestRelationship());
		domainObjectEClass.getESuperTypes().add(this.getTestInstance());
		nfTestEClass.getESuperTypes().add(this.getTest());
		guiTestEClass.getESuperTypes().add(this.getTest());
		blTestEClass.getESuperTypes().add(this.getTest());
		notionEClass.getESuperTypes().add(this.getTest());
		domainStatementEClass.getESuperTypes().add(this.getTest());
		testInstanceEClass.getESuperTypes().add(this.getTest());
		nfTestInstanceEClass.getESuperTypes().add(this.getTestInstance());
		guiTestInstanceEClass.getESuperTypes().add(this.getTestInstance());
		blTestInstanceEClass.getESuperTypes().add(this.getTestInstance());

		// Initialize classes and features; add operations and parameters
		initEClass(testSpecificationEClass, TestSpecification.class, "TestSpecification", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTestSpecification_Name(), ecorePackage.getEString(), "name", null, 0, 1, TestSpecification.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTestSpecification_EReference0(), this.getTestPackage(), null, "EReference0", null, 0, -1, TestSpecification.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTestSpecification_ScUid(), ecorePackage.getEString(), "scUid", null, 0, 1, TestSpecification.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTestSpecification_Uid(), ecorePackage.getEString(), "uid", null, 0, 1, TestSpecification.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(testPackageEClass, TestPackage.class, "TestPackage", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTestPackage_EReference0(), this.getTestPackage(), null, "EReference0", null, 0, -1, TestPackage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTestPackage_EReference1(), this.getUseCaseTest(), null, "EReference1", null, 0, -1, TestPackage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTestPackage_EReference3(), this.getTestScenario(), null, "EReference3", null, 0, -1, TestPackage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTestPackage_Notions(), this.getNotion(), null, "Notions", null, 0, -1, TestPackage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTestPackage_NFTests(), this.getNFTest(), null, "NFTests", null, 0, -1, TestPackage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTestPackage_GUITests(), this.getGUITest(), null, "GUITests", null, 0, -1, TestPackage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTestPackage_BLTests(), this.getBLTest(), null, "BLTests", null, 0, -1, TestPackage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTestPackage_Name(), ecorePackage.getEString(), "name", null, 0, 1, TestPackage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(testEClass, Test.class, "Test", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTest_Name(), ecorePackage.getEString(), "name", null, 0, 1, Test.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTest_Type(), ecorePackage.getEString(), "type", null, 0, 1, Test.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTest_Description(), ecorePackage.getEString(), "description", null, 0, 1, Test.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTest_ClassifierId(), ecorePackage.getELongObject(), "classifierId", null, 0, 1, Test.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTest_Content(), ecorePackage.getEString(), "content", null, 0, 1, Test.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTest_TestPrecondition(), ecorePackage.getEString(), "testPrecondition", null, 0, 1, Test.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTest_TestPostcondition(), ecorePackage.getEString(), "testPostcondition", null, 0, 1, Test.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTest_TestResult(), ecorePackage.getEString(), "testResult", null, 0, 1, Test.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTest_Source(), this.getTestRelationship(), null, "source", null, 0, -1, Test.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTest_TestParameters(), this.getTestParameter(), null, "testParameters", null, 0, -1, Test.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(useCaseTestEClass, UseCaseTest.class, "UseCaseTest", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getUseCaseTest_EReference0(), this.getUseCaseTestScenario(), null, "EReference0", null, 0, -1, UseCaseTest.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getUseCaseTest_UcName(), ecorePackage.getEString(), "ucName", null, 0, 1, UseCaseTest.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getUseCaseTest_UcTrail(), ecorePackage.getEString(), "ucTrail", null, 0, 1, UseCaseTest.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getUseCaseTest_InvocationSource(), this.getTestInvocationRelationship(), null, "invocationSource", null, 0, -1, UseCaseTest.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getUseCaseTest_Uid(), ecorePackage.getEString(), "uid", null, 0, 1, UseCaseTest.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(useCaseTestScenarioEClass, UseCaseTestScenario.class, "UseCaseTestScenario", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getUseCaseTestScenario_Sentences(), this.getUseCaseTestScenarioSentence(), null, "sentences", null, 0, -1, UseCaseTestScenario.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getUseCaseTestScenario_Actor(), ecorePackage.getEString(), "actor", null, 0, 1, UseCaseTestScenario.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getUseCaseTestScenario_Precondition(), this.getCondition(), null, "precondition", null, 0, 1, UseCaseTestScenario.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getUseCaseTestScenario_Postcondition(), this.getCondition(), null, "postcondition", null, 0, 1, UseCaseTestScenario.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(useCaseTestScenarioSentenceEClass, UseCaseTestScenarioSentence.class, "UseCaseTestScenarioSentence", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getUseCaseTestScenarioSentence_EReference0(), this.getUseCaseTestScenario(), null, "EReference0", null, 0, 1, UseCaseTestScenarioSentence.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getUseCaseTestScenarioSentence_EReference1(), this.getUseCaseTestScenario(), null, "EReference1", null, 0, 1, UseCaseTestScenarioSentence.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getUseCaseTestScenarioSentence_ScenarioSentence(), this.getScenarioSentence(), null, "ScenarioSentence", null, 0, 1, UseCaseTestScenarioSentence.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getUseCaseTestScenarioSentence_Name(), ecorePackage.getEString(), "name", null, 0, 1, UseCaseTestScenarioSentence.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(testScenarioEClass, TestScenario.class, "TestScenario", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTestScenario_EReference0(), this.getTestPackage(), null, "EReference0", null, 0, 1, TestScenario.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTestScenario_EReference1(), this.getTestCase(), null, "EReference1", null, 0, -1, TestScenario.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(testCaseEClass, TestCase.class, "TestCase", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTestCase_EReference1(), this.getUseCaseTestScenarioInstance(), null, "EReference1", null, 0, 1, TestCase.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTestCase_OrderNumber(), ecorePackage.getEInt(), "orderNumber", null, 0, 1, TestCase.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTestCase_Precondition(), this.getCCondition(), null, "precondition", null, 0, 1, TestCase.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTestCase_Postcondition(), this.getCCondition(), null, "postcondition", null, 0, 1, TestCase.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTestCase_UcName(), ecorePackage.getEString(), "ucName", null, 0, 1, TestCase.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTestCase_UcTrail(), ecorePackage.getEString(), "ucTrail", null, 0, 1, TestCase.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTestCase_UcScenarioName(), ecorePackage.getEString(), "ucScenarioName", null, 0, 1, TestCase.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTestCase_Sentences(), this.getTestCaseSentence(), null, "sentences", null, 0, -1, TestCase.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTestCase_OrderNumberGlobal(), ecorePackage.getEString(), "orderNumberGlobal", null, 0, 1, TestCase.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(testCaseSentenceEClass, TestCaseSentence.class, "TestCaseSentence", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTestCaseSentence_EReference0(), this.getUseCaseTestScenarioSentence(), null, "EReference0", null, 0, 1, TestCaseSentence.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTestCaseSentence_ScenarioSentence(), this.getScenarioSentence(), null, "scenarioSentence", null, 0, 1, TestCaseSentence.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTestCaseSentence_Name(), ecorePackage.getEString(), "name", null, 0, 1, TestCaseSentence.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTestCaseSentence_Context(), this.getTestDataValuesContext(), null, "context", null, 0, 1, TestCaseSentence.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(useCaseTestScenarioInstanceEClass, UseCaseTestScenarioInstance.class, "UseCaseTestScenarioInstance", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getUseCaseTestScenarioInstance_EReference3(), this.getTestCaseSentence(), null, "EReference3", null, 0, -1, UseCaseTestScenarioInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getUseCaseTestScenarioInstance_UcName(), ecorePackage.getEString(), "ucName", null, 0, 1, UseCaseTestScenarioInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getUseCaseTestScenarioInstance_UcTrail(), ecorePackage.getEString(), "ucTrail", null, 0, 1, UseCaseTestScenarioInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(scenarioSentenceEClass, ScenarioSentence.class, "ScenarioSentence", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(svoSentenceEClass, SVOSentence.class, "SVOSentence", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSVOSentence_DirectObject(), this.getDomainObject(), null, "directObject", null, 0, 1, SVOSentence.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSVOSentence_IndirectObject(), this.getDomainObject(), null, "indirectObject", null, 0, 1, SVOSentence.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSVOSentence_Number(), ecorePackage.getEInt(), "number", null, 0, 1, SVOSentence.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSVOSentence_Predicate(), this.getDomainStatement(), null, "predicate", null, 0, 1, SVOSentence.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(controlSentenceEClass, ControlSentence.class, "ControlSentence", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getControlSentence_Invocation(), this.getTestInvocationRelationship(), null, "invocation", null, 0, 1, ControlSentence.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(conditionSentenceEClass, ConditionSentence.class, "ConditionSentence", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(preconditionEClass, Precondition.class, "Precondition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(postconditionEClass, Postcondition.class, "Postcondition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(conditionEClass, Condition.class, "Condition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getCondition_Name(), ecorePackage.getEString(), "name", null, 0, 1, Condition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(testRelationshipEClass, TestRelationship.class, "TestRelationship", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTestRelationship_Target(), this.getTest(), null, "target", null, 0, 1, TestRelationship.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTestRelationship_Type(), ecorePackage.getEString(), "type", null, 0, 1, TestRelationship.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(testInvocationRelationshipEClass, TestInvocationRelationship.class, "TestInvocationRelationship", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTestInvocationRelationship_EReference0(), this.getTestRelationship(), null, "EReference0", null, 0, 1, TestInvocationRelationship.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTestInvocationRelationship_InvocationTarget(), this.getUseCaseTest(), null, "invocationTarget", null, 0, 1, TestInvocationRelationship.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTestInvocationRelationship_Uid(), ecorePackage.getEString(), "uid", null, 0, 1, TestInvocationRelationship.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTestInvocationRelationship_IncludeType(), ecorePackage.getEString(), "includeType", null, 0, 1, TestInvocationRelationship.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(domainObjectEClass, DomainObject.class, "DomainObject", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getDomainObject_InputData(), this.getTestValue(), null, "inputData", null, 0, -1, DomainObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDomainObject_DomainObjectType(), this.getDomainObjectType(), "domainObjectType", null, 0, 1, DomainObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(notionAttributeEClass, NotionAttribute.class, "NotionAttribute", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getNotionAttribute_Type(), ecorePackage.getEString(), "type", null, 0, 1, NotionAttribute.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getNotionAttribute_Name(), ecorePackage.getEString(), "name", null, 0, 1, NotionAttribute.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getNotionAttribute_Description(), ecorePackage.getEString(), "description", null, 0, 1, NotionAttribute.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(nfTestEClass, NFTest.class, "NFTest", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getNFTest_NfrTrail(), ecorePackage.getEString(), "nfrTrail", null, 0, 1, NFTest.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getNFTest_Uid(), ecorePackage.getEString(), "uid", null, 0, 1, NFTest.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(guiTestEClass, GUITest.class, "GUITest", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(blTestEClass, BLTest.class, "BLTest", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(notionEClass, Notion.class, "Notion", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getNotion_DomainStatements(), this.getDomainStatement(), null, "DomainStatements", null, 0, -1, Notion.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getNotion_NotionName(), ecorePackage.getEString(), "notionName", null, 0, 1, Notion.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getNotion_NotionDescription(), ecorePackage.getEString(), "notionDescription", null, 0, 1, Notion.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getNotion_NotionTrail(), ecorePackage.getEString(), "notionTrail", null, 0, 1, Notion.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getNotion_Attributes(), this.getNotionAttribute(), null, "attributes", null, 0, -1, Notion.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getNotion_Uid(), ecorePackage.getEString(), "uid", null, 0, 1, Notion.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(domainStatementEClass, DomainStatement.class, "DomainStatement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getDomainStatement_DirectNotion(), this.getNotion(), null, "DirectNotion", null, 0, 1, DomainStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDomainStatement_IndirectNotion(), this.getNotion(), null, "IndirectNotion", null, 0, 1, DomainStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDomainStatement_PhraseName(), ecorePackage.getEString(), "phraseName", null, 0, 1, DomainStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDomainStatement_PhraseDescription(), ecorePackage.getEString(), "phraseDescription", null, 0, 1, DomainStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDomainStatement_Uid(), ecorePackage.getEString(), "uid", null, 0, 1, DomainStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(cConditionEClass, CCondition.class, "CCondition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getCCondition_Name(), ecorePackage.getEString(), "name", null, 0, 1, CCondition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(testValueEClass, TestValue.class, "TestValue", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTestValue_Value(), ecorePackage.getEString(), "value", null, 0, 1, TestValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTestValue_Name(), ecorePackage.getEString(), "name", null, 0, 1, TestValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(testParameterEClass, TestParameter.class, "TestParameter", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTestParameter_Name(), ecorePackage.getEString(), "name", null, 0, 1, TestParameter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTestParameter_Domain(), ecorePackage.getEString(), "domain", null, 0, 1, TestParameter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTestParameter_ExpectedValue(), ecorePackage.getEString(), "expectedValue", null, 0, 1, TestParameter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(testInstanceEClass, TestInstance.class, "TestInstance", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTestInstance_AttachedTests(), this.getTestInstance(), null, "attachedTests", null, 0, -1, TestInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(nfTestInstanceEClass, NFTestInstance.class, "NFTestInstance", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(guiTestInstanceEClass, GUITestInstance.class, "GUITestInstance", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(blTestInstanceEClass, BLTestInstance.class, "BLTestInstance", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(testDataValuesContextEClass, TestDataValuesContext.class, "TestDataValuesContext", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		// Initialize enums and add enum literals
		initEEnum(domainObjectTypeEEnum, DomainObjectType.class, "DomainObjectType");
		addEEnumLiteral(domainObjectTypeEEnum, DomainObjectType.INPUT_DATA);
		addEEnumLiteral(domainObjectTypeEEnum, DomainObjectType.ACTION_DESCRIPTION);

		// Create resource
		createResource(eNS_URI);
	}

} //TslPackageImpl
