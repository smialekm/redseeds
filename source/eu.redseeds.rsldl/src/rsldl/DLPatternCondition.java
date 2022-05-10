/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package rsldl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>DL Pattern Condition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link rsldl.DLPatternCondition#getPattern <em>Pattern</em>}</li>
 * </ul>
 *
 * @see rsldl.RsldlPackage#getDLPatternCondition()
 * @model abstract="true"
 * @generated
 */
public interface DLPatternCondition extends EObject {
	/**
	 * Returns the value of the '<em><b>Pattern</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Pattern</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Pattern</em>' containment reference.
	 * @see #setPattern(DLConditionPattern)
	 * @see rsldl.RsldlPackage#getDLPatternCondition_Pattern()
	 * @model containment="true" required="true"
	 * @generated
	 */
	DLConditionPattern getPattern();

	/**
	 * Sets the value of the '{@link rsldl.DLPatternCondition#getPattern <em>Pattern</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Pattern</em>' containment reference.
	 * @see #getPattern()
	 * @generated
	 */
	void setPattern(DLConditionPattern value);

} // DLPatternCondition
