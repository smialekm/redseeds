/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package rsldl;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>DL Pattern Based Transition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link rsldl.DLPatternBasedTransition#getPattern <em>Pattern</em>}</li>
 * </ul>
 *
 * @see rsldl.RsldlPackage#getDLPatternBasedTransition()
 * @model
 * @generated
 */
public interface DLPatternBasedTransition extends DLTransition {
	/**
	 * Returns the value of the '<em><b>Pattern</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Pattern</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Pattern</em>' containment reference.
	 * @see #setPattern(DLTransitionPattern)
	 * @see rsldl.RsldlPackage#getDLPatternBasedTransition_Pattern()
	 * @model containment="true" required="true"
	 * @generated
	 */
	DLTransitionPattern getPattern();

	/**
	 * Sets the value of the '{@link rsldl.DLPatternBasedTransition#getPattern <em>Pattern</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Pattern</em>' containment reference.
	 * @see #getPattern()
	 * @generated
	 */
	void setPattern(DLTransitionPattern value);

} // DLPatternBasedTransition
