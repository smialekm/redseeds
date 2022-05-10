/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package eu.redset.emf.model.tsl;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Test Package</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.redset.emf.model.tsl.TestPackage#getEReference0 <em>EReference0</em>}</li>
 *   <li>{@link eu.redset.emf.model.tsl.TestPackage#getEReference1 <em>EReference1</em>}</li>
 *   <li>{@link eu.redset.emf.model.tsl.TestPackage#getEReference3 <em>EReference3</em>}</li>
 *   <li>{@link eu.redset.emf.model.tsl.TestPackage#getNotions <em>Notions</em>}</li>
 *   <li>{@link eu.redset.emf.model.tsl.TestPackage#getNFTests <em>NF Tests</em>}</li>
 *   <li>{@link eu.redset.emf.model.tsl.TestPackage#getGUITests <em>GUI Tests</em>}</li>
 *   <li>{@link eu.redset.emf.model.tsl.TestPackage#getBLTests <em>BL Tests</em>}</li>
 *   <li>{@link eu.redset.emf.model.tsl.TestPackage#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.redset.emf.model.tsl.TslPackage#getTestPackage()
 * @model
 * @generated
 */
public interface TestPackage extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see eu.redset.emf.model.tsl.TslPackage#getTestPackage_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link eu.redset.emf.model.tsl.TestPackage#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Notions</b></em>' containment reference list.
	 * The list contents are of type {@link eu.redset.emf.model.tsl.Notion}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Notions</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Notions</em>' containment reference list.
	 * @see eu.redset.emf.model.tsl.TslPackage#getTestPackage_Notions()
	 * @model containment="true"
	 * @generated
	 */
	EList<Notion> getNotions();

	/**
	 * Returns the value of the '<em><b>NF Tests</b></em>' containment reference list.
	 * The list contents are of type {@link eu.redset.emf.model.tsl.NFTest}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>NF Tests</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>NF Tests</em>' containment reference list.
	 * @see eu.redset.emf.model.tsl.TslPackage#getTestPackage_NFTests()
	 * @model containment="true"
	 * @generated
	 */
	EList<NFTest> getNFTests();

	/**
	 * Returns the value of the '<em><b>GUI Tests</b></em>' containment reference list.
	 * The list contents are of type {@link eu.redset.emf.model.tsl.GUITest}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>GUI Tests</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>GUI Tests</em>' containment reference list.
	 * @see eu.redset.emf.model.tsl.TslPackage#getTestPackage_GUITests()
	 * @model containment="true"
	 * @generated
	 */
	EList<GUITest> getGUITests();

	/**
	 * Returns the value of the '<em><b>BL Tests</b></em>' containment reference list.
	 * The list contents are of type {@link eu.redset.emf.model.tsl.BLTest}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>BL Tests</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>BL Tests</em>' containment reference list.
	 * @see eu.redset.emf.model.tsl.TslPackage#getTestPackage_BLTests()
	 * @model containment="true"
	 * @generated
	 */
	EList<BLTest> getBLTests();

	/**
	 * Returns the value of the '<em><b>EReference3</b></em>' containment reference list.
	 * The list contents are of type {@link eu.redset.emf.model.tsl.TestScenario}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>EReference3</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>EReference3</em>' containment reference list.
	 * @see eu.redset.emf.model.tsl.TslPackage#getTestPackage_EReference3()
	 * @model containment="true"
	 * @generated
	 */
	EList<TestScenario> getEReference3();

	/**
	 * Returns the value of the '<em><b>EReference0</b></em>' containment reference list.
	 * The list contents are of type {@link eu.redset.emf.model.tsl.TestPackage}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>EReference0</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>EReference0</em>' containment reference list.
	 * @see eu.redset.emf.model.tsl.TslPackage#getTestPackage_EReference0()
	 * @model containment="true"
	 * @generated
	 */
	EList<TestPackage> getEReference0();

	/**
	 * Returns the value of the '<em><b>EReference1</b></em>' containment reference list.
	 * The list contents are of type {@link eu.redset.emf.model.tsl.UseCaseTest}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>EReference1</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>EReference1</em>' containment reference list.
	 * @see eu.redset.emf.model.tsl.TslPackage#getTestPackage_EReference1()
	 * @model containment="true"
	 * @generated
	 */
	EList<UseCaseTest> getEReference1();

} // TestPackage
