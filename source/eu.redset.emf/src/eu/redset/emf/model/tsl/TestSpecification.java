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
 * A representation of the model object '<em><b>Test Specification</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.redset.emf.model.tsl.TestSpecification#getName <em>Name</em>}</li>
 *   <li>{@link eu.redset.emf.model.tsl.TestSpecification#getEReference0 <em>EReference0</em>}</li>
 *   <li>{@link eu.redset.emf.model.tsl.TestSpecification#getScUid <em>Sc Uid</em>}</li>
 *   <li>{@link eu.redset.emf.model.tsl.TestSpecification#getUid <em>Uid</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.redset.emf.model.tsl.TslPackage#getTestSpecification()
 * @model
 * @generated
 */
public interface TestSpecification extends EObject {
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
	 * @see eu.redset.emf.model.tsl.TslPackage#getTestSpecification_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link eu.redset.emf.model.tsl.TestSpecification#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

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
	 * @see eu.redset.emf.model.tsl.TslPackage#getTestSpecification_EReference0()
	 * @model containment="true"
	 * @generated
	 */
	EList<TestPackage> getEReference0();

	/**
	 * Returns the value of the '<em><b>Sc Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sc Uid</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sc Uid</em>' attribute.
	 * @see #setScUid(String)
	 * @see eu.redset.emf.model.tsl.TslPackage#getTestSpecification_ScUid()
	 * @model
	 * @generated
	 */
	String getScUid();

	/**
	 * Sets the value of the '{@link eu.redset.emf.model.tsl.TestSpecification#getScUid <em>Sc Uid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Sc Uid</em>' attribute.
	 * @see #getScUid()
	 * @generated
	 */
	void setScUid(String value);

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
	 * @see eu.redset.emf.model.tsl.TslPackage#getTestSpecification_Uid()
	 * @model
	 * @generated
	 */
	String getUid();

	/**
	 * Sets the value of the '{@link eu.redset.emf.model.tsl.TestSpecification#getUid <em>Uid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Uid</em>' attribute.
	 * @see #getUid()
	 * @generated
	 */
	void setUid(String value);

} // TestSpecification
