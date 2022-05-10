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
 * A representation of the model object '<em><b>Test Instance</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.redset.emf.model.tsl.TestInstance#getAttachedTests <em>Attached Tests</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.redset.emf.model.tsl.TslPackage#getTestInstance()
 * @model
 * @generated
 */
public interface TestInstance extends Test {
	/**
	 * Returns the value of the '<em><b>Attached Tests</b></em>' containment reference list.
	 * The list contents are of type {@link eu.redset.emf.model.tsl.TestInstance}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Attached Tests</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Attached Tests</em>' containment reference list.
	 * @see eu.redset.emf.model.tsl.TslPackage#getTestInstance_AttachedTests()
	 * @model containment="true"
	 * @generated
	 */
	EList<TestInstance> getAttachedTests();

} // TestInstance
