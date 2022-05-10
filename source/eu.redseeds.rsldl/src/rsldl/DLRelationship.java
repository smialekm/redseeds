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
 * A representation of the model object '<em><b>DL Relationship</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link rsldl.DLRelationship#getParticipations <em>Participations</em>}</li>
 *   <li>{@link rsldl.DLRelationship#getName <em>Name</em>}</li>
 *   <li>{@link rsldl.DLRelationship#getType <em>Type</em>}</li>
 *   <li>{@link rsldl.DLRelationship#getAbbreviation <em>Abbreviation</em>}</li>
 * </ul>
 *
 * @see rsldl.RsldlPackage#getDLRelationship()
 * @model abstract="true"
 * @generated
 */
public interface DLRelationship extends DLDomainElement {
	/**
	 * Returns the value of the '<em><b>Participations</b></em>' containment reference list.
	 * The list contents are of type {@link rsldl.DLRelationshipParticipation}.
	 * It is bidirectional and its opposite is '{@link rsldl.DLRelationshipParticipation#getRelationship <em>Relationship</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Participations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Participations</em>' containment reference list.
	 * @see rsldl.RsldlPackage#getDLRelationship_Participations()
	 * @see rsldl.DLRelationshipParticipation#getRelationship
	 * @model opposite="relationship" containment="true"
	 * @generated
	 */
	EList<DLRelationshipParticipation> getParticipations();

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see rsldl.RsldlPackage#getDLRelationship_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link rsldl.DLRelationship#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see rsldl.RsldlPackage#getDLRelationship_Type()
	 * @model transient="true" changeable="false" volatile="true" derived="true"
	 * @generated
	 */
	String getType();

	/**
	 * Returns the value of the '<em><b>Abbreviation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Abbreviation</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Abbreviation</em>' attribute.
	 * @see #setAbbreviation(String)
	 * @see rsldl.RsldlPackage#getDLRelationship_Abbreviation()
	 * @model
	 * @generated
	 */
	String getAbbreviation();

	/**
	 * Sets the value of the '{@link rsldl.DLRelationship#getAbbreviation <em>Abbreviation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Abbreviation</em>' attribute.
	 * @see #getAbbreviation()
	 * @generated
	 */
	void setAbbreviation(String value);

} // DLRelationship
