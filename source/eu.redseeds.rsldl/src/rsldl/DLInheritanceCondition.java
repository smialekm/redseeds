/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package rsldl;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>DL Inheritance Condition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link rsldl.DLInheritanceCondition#getParents <em>Parents</em>}</li>
 *   <li>{@link rsldl.DLInheritanceCondition#getFeatureType <em>Feature Type</em>}</li>
 * </ul>
 *
 * @see rsldl.RsldlPackage#getDLInheritanceCondition()
 * @model
 * @generated
 */
public interface DLInheritanceCondition extends DLCondition {
	/**
	 * Returns the value of the '<em><b>Parents</b></em>' reference list.
	 * The list contents are of type {@link rsldl.DLNotion}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parents</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parents</em>' reference list.
	 * @see rsldl.RsldlPackage#getDLInheritanceCondition_Parents()
	 * @model
	 * @generated
	 */
	EList<DLNotion> getParents();

	/**
	 * Returns the value of the '<em><b>Feature Type</b></em>' attribute.
	 * The literals are from the enumeration {@link rsldl.DLFeatureType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Feature Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Feature Type</em>' attribute.
	 * @see rsldl.DLFeatureType
	 * @see #setFeatureType(DLFeatureType)
	 * @see rsldl.RsldlPackage#getDLInheritanceCondition_FeatureType()
	 * @model
	 * @generated
	 */
	DLFeatureType getFeatureType();

	/**
	 * Sets the value of the '{@link rsldl.DLInheritanceCondition#getFeatureType <em>Feature Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Feature Type</em>' attribute.
	 * @see rsldl.DLFeatureType
	 * @see #getFeatureType()
	 * @generated
	 */
	void setFeatureType(DLFeatureType value);

} // DLInheritanceCondition
