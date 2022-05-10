/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package eu.redset.emf.model.tsl;

import org.eclipse.emf.common.util.EList;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Test Case</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.redset.emf.model.tsl.TestCase#getEReference1 <em>EReference1</em>}</li>
 *   <li>{@link eu.redset.emf.model.tsl.TestCase#getOrderNumber <em>Order Number</em>}</li>
 *   <li>{@link eu.redset.emf.model.tsl.TestCase#getPrecondition <em>Precondition</em>}</li>
 *   <li>{@link eu.redset.emf.model.tsl.TestCase#getPostcondition <em>Postcondition</em>}</li>
 *   <li>{@link eu.redset.emf.model.tsl.TestCase#getUcName <em>Uc Name</em>}</li>
 *   <li>{@link eu.redset.emf.model.tsl.TestCase#getUcTrail <em>Uc Trail</em>}</li>
 *   <li>{@link eu.redset.emf.model.tsl.TestCase#getUcScenarioName <em>Uc Scenario Name</em>}</li>
 *   <li>{@link eu.redset.emf.model.tsl.TestCase#getSentences <em>Sentences</em>}</li>
 *   <li>{@link eu.redset.emf.model.tsl.TestCase#getOrderNumberGlobal <em>Order Number Global</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.redset.emf.model.tsl.TslPackage#getTestCase()
 * @model
 * @generated
 */
public interface TestCase extends TestInstance {

	/**
	 * Returns the value of the '<em><b>EReference1</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>EReference1</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>EReference1</em>' containment reference.
	 * @see #setEReference1(UseCaseTestScenarioInstance)
	 * @see eu.redset.emf.model.tsl.TslPackage#getTestCase_EReference1()
	 * @model containment="true"
	 * @generated
	 */
	UseCaseTestScenarioInstance getEReference1();

	/**
	 * Sets the value of the '{@link eu.redset.emf.model.tsl.TestCase#getEReference1 <em>EReference1</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>EReference1</em>' containment reference.
	 * @see #getEReference1()
	 * @generated
	 */
	void setEReference1(UseCaseTestScenarioInstance value);

	/**
	 * Returns the value of the '<em><b>Order Number</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Order Number</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Order Number</em>' attribute.
	 * @see #setOrderNumber(int)
	 * @see eu.redset.emf.model.tsl.TslPackage#getTestCase_OrderNumber()
	 * @model
	 * @generated
	 */
	int getOrderNumber();

	/**
	 * Sets the value of the '{@link eu.redset.emf.model.tsl.TestCase#getOrderNumber <em>Order Number</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Order Number</em>' attribute.
	 * @see #getOrderNumber()
	 * @generated
	 */
	void setOrderNumber(int value);

	/**
	 * Returns the value of the '<em><b>Precondition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Precondition</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Precondition</em>' containment reference.
	 * @see #setPrecondition(CCondition)
	 * @see eu.redset.emf.model.tsl.TslPackage#getTestCase_Precondition()
	 * @model containment="true"
	 * @generated
	 */
	CCondition getPrecondition();

	/**
	 * Sets the value of the '{@link eu.redset.emf.model.tsl.TestCase#getPrecondition <em>Precondition</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Precondition</em>' containment reference.
	 * @see #getPrecondition()
	 * @generated
	 */
	void setPrecondition(CCondition value);

	/**
	 * Returns the value of the '<em><b>Postcondition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Postcondition</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Postcondition</em>' containment reference.
	 * @see #setPostcondition(CCondition)
	 * @see eu.redset.emf.model.tsl.TslPackage#getTestCase_Postcondition()
	 * @model containment="true"
	 * @generated
	 */
	CCondition getPostcondition();

	/**
	 * Sets the value of the '{@link eu.redset.emf.model.tsl.TestCase#getPostcondition <em>Postcondition</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Postcondition</em>' containment reference.
	 * @see #getPostcondition()
	 * @generated
	 */
	void setPostcondition(CCondition value);

	/**
	 * Returns the value of the '<em><b>Uc Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Uc Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Uc Name</em>' attribute.
	 * @see #setUcName(String)
	 * @see eu.redset.emf.model.tsl.TslPackage#getTestCase_UcName()
	 * @model
	 * @generated
	 */
	String getUcName();

	/**
	 * Sets the value of the '{@link eu.redset.emf.model.tsl.TestCase#getUcName <em>Uc Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Uc Name</em>' attribute.
	 * @see #getUcName()
	 * @generated
	 */
	void setUcName(String value);

	/**
	 * Returns the value of the '<em><b>Uc Trail</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Uc Trail</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Uc Trail</em>' attribute.
	 * @see #setUcTrail(String)
	 * @see eu.redset.emf.model.tsl.TslPackage#getTestCase_UcTrail()
	 * @model
	 * @generated
	 */
	String getUcTrail();

	/**
	 * Sets the value of the '{@link eu.redset.emf.model.tsl.TestCase#getUcTrail <em>Uc Trail</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Uc Trail</em>' attribute.
	 * @see #getUcTrail()
	 * @generated
	 */
	void setUcTrail(String value);

	/**
	 * Returns the value of the '<em><b>Uc Scenario Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Uc Scenario Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Uc Scenario Name</em>' attribute.
	 * @see #setUcScenarioName(String)
	 * @see eu.redset.emf.model.tsl.TslPackage#getTestCase_UcScenarioName()
	 * @model
	 * @generated
	 */
	String getUcScenarioName();

	/**
	 * Sets the value of the '{@link eu.redset.emf.model.tsl.TestCase#getUcScenarioName <em>Uc Scenario Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Uc Scenario Name</em>' attribute.
	 * @see #getUcScenarioName()
	 * @generated
	 */
	void setUcScenarioName(String value);

	/**
	 * Returns the value of the '<em><b>Sentences</b></em>' containment reference list.
	 * The list contents are of type {@link eu.redset.emf.model.tsl.TestCaseSentence}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sentences</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sentences</em>' containment reference list.
	 * @see eu.redset.emf.model.tsl.TslPackage#getTestCase_Sentences()
	 * @model containment="true"
	 * @generated
	 */
	EList<TestCaseSentence> getSentences();

	/**
	 * Returns the value of the '<em><b>Order Number Global</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Order Number Global</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Order Number Global</em>' attribute.
	 * @see #setOrderNumberGlobal(String)
	 * @see eu.redset.emf.model.tsl.TslPackage#getTestCase_OrderNumberGlobal()
	 * @model
	 * @generated
	 */
	String getOrderNumberGlobal();

	/**
	 * Sets the value of the '{@link eu.redset.emf.model.tsl.TestCase#getOrderNumberGlobal <em>Order Number Global</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Order Number Global</em>' attribute.
	 * @see #getOrderNumberGlobal()
	 * @generated
	 */
	void setOrderNumberGlobal(String value);
} // TestCase
