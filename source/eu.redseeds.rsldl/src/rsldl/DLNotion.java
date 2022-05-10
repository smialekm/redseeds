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
 * A representation of the model object '<em><b>DL Notion</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link rsldl.DLNotion#getType <em>Type</em>}</li>
 *   <li>{@link rsldl.DLNotion#getFeatures <em>Features</em>}</li>
 *   <li>{@link rsldl.DLNotion#getDereferences <em>Dereferences</em>}</li>
 *   <li>{@link rsldl.DLNotion#isDerived <em>Derived</em>}</li>
 * </ul>
 *
 * @see rsldl.RsldlPackage#getDLNotion()
 * @model abstract="true"
 * @generated
 */
public interface DLNotion extends DLRelationshipParticipant, DLDomainElement {
	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * The literals are from the enumeration {@link rsldl.DLTypeRole}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see rsldl.DLTypeRole
	 * @see #setType(DLTypeRole)
	 * @see rsldl.RsldlPackage#getDLNotion_Type()
	 * @model
	 * @generated
	 */
	DLTypeRole getType();

	/**
	 * Sets the value of the '{@link rsldl.DLNotion#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see rsldl.DLTypeRole
	 * @see #getType()
	 * @generated
	 */
	void setType(DLTypeRole value);

	/**
	 * Returns the value of the '<em><b>Features</b></em>' containment reference list.
	 * The list contents are of type {@link rsldl.DLFeature}.
	 * It is bidirectional and its opposite is '{@link rsldl.DLFeature#getNotion <em>Notion</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Features</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Features</em>' containment reference list.
	 * @see rsldl.RsldlPackage#getDLNotion_Features()
	 * @see rsldl.DLFeature#getNotion
	 * @model opposite="notion" containment="true"
	 * @generated
	 */
	EList<DLFeature> getFeatures();

	/**
	 * Returns the value of the '<em><b>Dereferences</b></em>' containment reference list.
	 * The list contents are of type {@link rsldl.DLDereferenceLink}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Dereferences</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Dereferences</em>' containment reference list.
	 * @see rsldl.RsldlPackage#getDLNotion_Dereferences()
	 * @model containment="true"
	 * @generated
	 */
	EList<DLDereferenceLink> getDereferences();

	/**
	 * Returns the value of the '<em><b>Derived</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Derived</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Derived</em>' attribute.
	 * @see #setDerived(boolean)
	 * @see rsldl.RsldlPackage#getDLNotion_Derived()
	 * @model
	 * @generated
	 */
	boolean isDerived();

	/**
	 * Sets the value of the '{@link rsldl.DLNotion#isDerived <em>Derived</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Derived</em>' attribute.
	 * @see #isDerived()
	 * @generated
	 */
	void setDerived(boolean value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation" many="false"
	 * @generated
	 */
	EList<DLProperty> getDirectAttributes();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation" many="false"
	 * @generated
	 */
	EList<DLProperty> getAllAttributes();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation" many="false"
	 * @generated
	 */
	EList<DLInheritanceCondition> getInheritanceConditions();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation" many="false"
	 * @generated
	 */
	EList<DLAttributeLink> getAttributesLinks();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation" many="false"
	 * @generated
	 */
	EList<DLValidityCondition> getValidityConditions();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation" many="false"
	 * @generated
	 */
	EList<DLIdentityCondition> getIdentityConditions();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation" many="false"
	 * @generated
	 */
	EList<DLAttributeLink> getAllAttributesLinks();

} // DLNotion
