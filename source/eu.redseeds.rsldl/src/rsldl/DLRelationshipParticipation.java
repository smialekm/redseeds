/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package rsldl;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>DL Relationship Participation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link rsldl.DLRelationshipParticipation#getDirection <em>Direction</em>}</li>
 *   <li>{@link rsldl.DLRelationshipParticipation#getMultiplicity <em>Multiplicity</em>}</li>
 *   <li>{@link rsldl.DLRelationshipParticipation#getParticipant <em>Participant</em>}</li>
 *   <li>{@link rsldl.DLRelationshipParticipation#getRelationship <em>Relationship</em>}</li>
 *   <li>{@link rsldl.DLRelationshipParticipation#getParentParticipation <em>Parent Participation</em>}</li>
 *   <li>{@link rsldl.DLRelationshipParticipation#getType <em>Type</em>}</li>
 * </ul>
 *
 * @see rsldl.RsldlPackage#getDLRelationshipParticipation()
 * @model
 * @generated
 */
public interface DLRelationshipParticipation extends DLNamedLink {
	/**
	 * Returns the value of the '<em><b>Direction</b></em>' attribute.
	 * The literals are from the enumeration {@link rsldl.DLRelationshipParticipationDirection}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Direction</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Direction</em>' attribute.
	 * @see rsldl.DLRelationshipParticipationDirection
	 * @see #setDirection(DLRelationshipParticipationDirection)
	 * @see rsldl.RsldlPackage#getDLRelationshipParticipation_Direction()
	 * @model
	 * @generated
	 */
	DLRelationshipParticipationDirection getDirection();

	/**
	 * Sets the value of the '{@link rsldl.DLRelationshipParticipation#getDirection <em>Direction</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Direction</em>' attribute.
	 * @see rsldl.DLRelationshipParticipationDirection
	 * @see #getDirection()
	 * @generated
	 */
	void setDirection(DLRelationshipParticipationDirection value);

	/**
	 * Returns the value of the '<em><b>Multiplicity</b></em>' attribute.
	 * The literals are from the enumeration {@link rsldl.DLRelationshipParticipationMultiplicity}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Multiplicity</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Multiplicity</em>' attribute.
	 * @see rsldl.DLRelationshipParticipationMultiplicity
	 * @see #setMultiplicity(DLRelationshipParticipationMultiplicity)
	 * @see rsldl.RsldlPackage#getDLRelationshipParticipation_Multiplicity()
	 * @model
	 * @generated
	 */
	DLRelationshipParticipationMultiplicity getMultiplicity();

	/**
	 * Sets the value of the '{@link rsldl.DLRelationshipParticipation#getMultiplicity <em>Multiplicity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Multiplicity</em>' attribute.
	 * @see rsldl.DLRelationshipParticipationMultiplicity
	 * @see #getMultiplicity()
	 * @generated
	 */
	void setMultiplicity(DLRelationshipParticipationMultiplicity value);

	/**
	 * Returns the value of the '<em><b>Participant</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Participant</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Participant</em>' reference.
	 * @see #setParticipant(DLRelationshipParticipant)
	 * @see rsldl.RsldlPackage#getDLRelationshipParticipation_Participant()
	 * @model required="true"
	 * @generated
	 */
	DLRelationshipParticipant getParticipant();

	/**
	 * Sets the value of the '{@link rsldl.DLRelationshipParticipation#getParticipant <em>Participant</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Participant</em>' reference.
	 * @see #getParticipant()
	 * @generated
	 */
	void setParticipant(DLRelationshipParticipant value);

	/**
	 * Returns the value of the '<em><b>Relationship</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link rsldl.DLRelationship#getParticipations <em>Participations</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Relationship</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Relationship</em>' container reference.
	 * @see #setRelationship(DLRelationship)
	 * @see rsldl.RsldlPackage#getDLRelationshipParticipation_Relationship()
	 * @see rsldl.DLRelationship#getParticipations
	 * @model opposite="participations" required="true" transient="false"
	 * @generated
	 */
	DLRelationship getRelationship();

	/**
	 * Sets the value of the '{@link rsldl.DLRelationshipParticipation#getRelationship <em>Relationship</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Relationship</em>' container reference.
	 * @see #getRelationship()
	 * @generated
	 */
	void setRelationship(DLRelationship value);

	/**
	 * Returns the value of the '<em><b>Parent Participation</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parent Participation</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parent Participation</em>' reference.
	 * @see #setParentParticipation(DLRelationshipParticipation)
	 * @see rsldl.RsldlPackage#getDLRelationshipParticipation_ParentParticipation()
	 * @model
	 * @generated
	 */
	DLRelationshipParticipation getParentParticipation();

	/**
	 * Sets the value of the '{@link rsldl.DLRelationshipParticipation#getParentParticipation <em>Parent Participation</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parent Participation</em>' reference.
	 * @see #getParentParticipation()
	 * @generated
	 */
	void setParentParticipation(DLRelationshipParticipation value);

	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * The literals are from the enumeration {@link rsldl.DLRelationshipParticipationType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see rsldl.DLRelationshipParticipationType
	 * @see #setType(DLRelationshipParticipationType)
	 * @see rsldl.RsldlPackage#getDLRelationshipParticipation_Type()
	 * @model
	 * @generated
	 */
	DLRelationshipParticipationType getType();

	/**
	 * Sets the value of the '{@link rsldl.DLRelationshipParticipation#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see rsldl.DLRelationshipParticipationType
	 * @see #getType()
	 * @generated
	 */
	void setType(DLRelationshipParticipationType value);

} // DLRelationshipParticipation
