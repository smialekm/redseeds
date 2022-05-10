/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package rsldl;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>DL Transition Pattern</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link rsldl.DLTransitionPattern#getType <em>Type</em>}</li>
 * </ul>
 *
 * @see rsldl.RsldlPackage#getDLTransitionPattern()
 * @model
 * @generated
 */
public interface DLTransitionPattern extends DLPattern {
	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * The literals are from the enumeration {@link rsldl.DLTransitionPatternType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see rsldl.DLTransitionPatternType
	 * @see #setType(DLTransitionPatternType)
	 * @see rsldl.RsldlPackage#getDLTransitionPattern_Type()
	 * @model
	 * @generated
	 */
	DLTransitionPatternType getType();

	/**
	 * Sets the value of the '{@link rsldl.DLTransitionPattern#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see rsldl.DLTransitionPatternType
	 * @see #getType()
	 * @generated
	 */
	void setType(DLTransitionPatternType value);

} // DLTransitionPattern
