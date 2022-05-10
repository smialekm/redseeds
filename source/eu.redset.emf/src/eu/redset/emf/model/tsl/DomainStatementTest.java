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
 * A representation of the model object '<em><b>Domain Statement Test</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eu.redset.emf.model.tsl.DomainStatementTest#getGUITests <em>GUI Tests</em>}</li>
 *   <li>{@link eu.redset.emf.model.tsl.DomainStatementTest#getBLTests <em>BL Tests</em>}</li>
 * </ul>
 * </p>
 *
 * @see eu.redset.emf.model.tsl.TslPackage#getDomainStatementTest()
 * @model
 * @generated
 */
public interface DomainStatementTest extends Test {
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
	 * @see eu.redset.emf.model.tsl.TslPackage#getDomainStatementTest_GUITests()
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
	 * @see eu.redset.emf.model.tsl.TslPackage#getDomainStatementTest_BLTests()
	 * @model containment="true"
	 * @generated
	 */
	EList<BLTest> getBLTests();

} // DomainStatementTest
