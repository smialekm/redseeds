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
 * A representation of the model object '<em><b>Domain Object</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.redset.emf.model.tsl.DomainObject#getInputData <em>Input Data</em>}</li>
 *   <li>{@link eu.redset.emf.model.tsl.DomainObject#getDomainObjectType <em>Domain Object Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.redset.emf.model.tsl.TslPackage#getDomainObject()
 * @model
 * @generated
 */
public interface DomainObject extends TestInstance {

	/**
	 * Returns the value of the '<em><b>Input Data</b></em>' containment reference list.
	 * The list contents are of type {@link eu.redset.emf.model.tsl.TestValue}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Input Data</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Input Data</em>' containment reference list.
	 * @see eu.redset.emf.model.tsl.TslPackage#getDomainObject_InputData()
	 * @model containment="true"
	 * @generated
	 */
	EList<TestValue> getInputData();

	/**
	 * Returns the value of the '<em><b>Domain Object Type</b></em>' attribute.
	 * The literals are from the enumeration {@link eu.redset.emf.model.tsl.DomainObjectType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Domain Object Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Domain Object Type</em>' attribute.
	 * @see eu.redset.emf.model.tsl.DomainObjectType
	 * @see #setDomainObjectType(DomainObjectType)
	 * @see eu.redset.emf.model.tsl.TslPackage#getDomainObject_DomainObjectType()
	 * @model
	 * @generated
	 */
	DomainObjectType getDomainObjectType();

	/**
	 * Sets the value of the '{@link eu.redset.emf.model.tsl.DomainObject#getDomainObjectType <em>Domain Object Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Domain Object Type</em>' attribute.
	 * @see eu.redset.emf.model.tsl.DomainObjectType
	 * @see #getDomainObjectType()
	 * @generated
	 */
	void setDomainObjectType(DomainObjectType value);

} // DomainObject
