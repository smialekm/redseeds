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
 * A representation of the model object '<em><b>Use Case Test</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.redset.emf.model.tsl.UseCaseTest#getEReference0 <em>EReference0</em>}</li>
 *   <li>{@link eu.redset.emf.model.tsl.UseCaseTest#getUcName <em>Uc Name</em>}</li>
 *   <li>{@link eu.redset.emf.model.tsl.UseCaseTest#getUcTrail <em>Uc Trail</em>}</li>
 *   <li>{@link eu.redset.emf.model.tsl.UseCaseTest#getInvocationSource <em>Invocation Source</em>}</li>
 *   <li>{@link eu.redset.emf.model.tsl.UseCaseTest#getUid <em>Uid</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.redset.emf.model.tsl.TslPackage#getUseCaseTest()
 * @model
 * @generated
 */
public interface UseCaseTest extends Test {

	/**
	 * Returns the value of the '<em><b>EReference0</b></em>' containment reference list.
	 * The list contents are of type {@link eu.redset.emf.model.tsl.UseCaseTestScenario}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>EReference0</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>EReference0</em>' containment reference list.
	 * @see eu.redset.emf.model.tsl.TslPackage#getUseCaseTest_EReference0()
	 * @model containment="true"
	 * @generated
	 */
	EList<UseCaseTestScenario> getEReference0();

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
	 * @see eu.redset.emf.model.tsl.TslPackage#getUseCaseTest_UcName()
	 * @model
	 * @generated
	 */
	String getUcName();

	/**
	 * Sets the value of the '{@link eu.redset.emf.model.tsl.UseCaseTest#getUcName <em>Uc Name</em>}' attribute.
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
	 * @see eu.redset.emf.model.tsl.TslPackage#getUseCaseTest_UcTrail()
	 * @model
	 * @generated
	 */
	String getUcTrail();

	/**
	 * Sets the value of the '{@link eu.redset.emf.model.tsl.UseCaseTest#getUcTrail <em>Uc Trail</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Uc Trail</em>' attribute.
	 * @see #getUcTrail()
	 * @generated
	 */
	void setUcTrail(String value);

	/**
	 * Returns the value of the '<em><b>Invocation Source</b></em>' containment reference list.
	 * The list contents are of type {@link eu.redset.emf.model.tsl.TestInvocationRelationship}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Invocation Source</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Invocation Source</em>' containment reference list.
	 * @see eu.redset.emf.model.tsl.TslPackage#getUseCaseTest_InvocationSource()
	 * @model containment="true"
	 * @generated
	 */
	EList<TestInvocationRelationship> getInvocationSource();

	/**
	 * Returns the value of the '<em><b>Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Uid</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Uid</em>' attribute.
	 * @see #setUid(String)
	 * @see eu.redset.emf.model.tsl.TslPackage#getUseCaseTest_Uid()
	 * @model
	 * @generated
	 */
	String getUid();

	/**
	 * Sets the value of the '{@link eu.redset.emf.model.tsl.UseCaseTest#getUid <em>Uid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Uid</em>' attribute.
	 * @see #getUid()
	 * @generated
	 */
	void setUid(String value);
} // UseCaseTest
