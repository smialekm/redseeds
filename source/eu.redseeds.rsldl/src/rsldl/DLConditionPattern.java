/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package rsldl;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>DL Condition Pattern</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link rsldl.DLConditionPattern#getType <em>Type</em>}</li>
 * </ul>
 *
 * @see rsldl.RsldlPackage#getDLConditionPattern()
 * @model
 * @generated
 */
public interface DLConditionPattern extends DLPattern {

	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * The literals are from the enumeration {@link rsldl.DLConditionPatternType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see rsldl.DLConditionPatternType
	 * @see #setType(DLConditionPatternType)
	 * @see rsldl.RsldlPackage#getDLConditionPattern_Type()
	 * @model
	 * @generated
	 */
	DLConditionPatternType getType();

	/**
	 * Sets the value of the '{@link rsldl.DLConditionPattern#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see rsldl.DLConditionPatternType
	 * @see #getType()
	 * @generated
	 */
	void setType(DLConditionPatternType value);
} // DLConditionPattern
