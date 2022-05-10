/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package rsldl;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>DL Condition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link rsldl.DLCondition#getType <em>Type</em>}</li>
 *   <li>{@link rsldl.DLCondition#getText <em>Text</em>}</li>
 * </ul>
 *
 * @see rsldl.RsldlPackage#getDLCondition()
 * @model abstract="true"
 * @generated
 */
public interface DLCondition extends DLFeature {
	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see rsldl.RsldlPackage#getDLCondition_Type()
	 * @model transient="true" changeable="false" volatile="true" derived="true"
	 * @generated
	 */
	String getType();

	/**
	 * Returns the value of the '<em><b>Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Text</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Text</em>' attribute.
	 * @see rsldl.RsldlPackage#getDLCondition_Text()
	 * @model transient="true" changeable="false" volatile="true" derived="true"
	 * @generated
	 */
	String getText();

} // DLCondition
