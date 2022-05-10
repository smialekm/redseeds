/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package rsldl;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>DL Diagram</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link rsldl.DLDiagram#getRelationshipParticipants <em>Relationship Participants</em>}</li>
 *   <li>{@link rsldl.DLDiagram#getRelationships <em>Relationships</em>}</li>
 *   <li>{@link rsldl.DLDiagram#getName <em>Name</em>}</li>
 * </ul>
 *
 * @see rsldl.RsldlPackage#getDLDiagram()
 * @model
 * @generated
 */
public interface DLDiagram extends EObject {
	/**
	 * Returns the value of the '<em><b>Relationship Participants</b></em>' containment reference list.
	 * The list contents are of type {@link rsldl.DLRelationshipParticipant}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Relationship Participants</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Relationship Participants</em>' containment reference list.
	 * @see rsldl.RsldlPackage#getDLDiagram_RelationshipParticipants()
	 * @model containment="true"
	 * @generated
	 */
	EList<DLRelationshipParticipant> getRelationshipParticipants();

	/**
	 * Returns the value of the '<em><b>Relationships</b></em>' containment reference list.
	 * The list contents are of type {@link rsldl.DLRelationship}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Relationships</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Relationships</em>' containment reference list.
	 * @see rsldl.RsldlPackage#getDLDiagram_Relationships()
	 * @model containment="true"
	 * @generated
	 */
	EList<DLRelationship> getRelationships();

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
	 * @see rsldl.RsldlPackage#getDLDiagram_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link rsldl.DLDiagram#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

} // DLDiagram
