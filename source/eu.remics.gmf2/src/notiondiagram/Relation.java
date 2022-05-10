/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package notiondiagram;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Relation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link notiondiagram.Relation#getTargetMultiplicity <em>Target Multiplicity</em>}</li>
 *   <li>{@link notiondiagram.Relation#getSourceMultiplicity <em>Source Multiplicity</em>}</li>
 *   <li>{@link notiondiagram.Relation#getId <em>Id</em>}</li>
 *   <li>{@link notiondiagram.Relation#getStereotype <em>Stereotype</em>}</li>
 * </ul>
 * </p>
 *
 * @see notiondiagram.NotiondiagramPackage#getRelation()
 * @model abstract="true"
 * @generated
 */
public interface Relation extends AbstractRelation {
	/**
	 * Returns the value of the '<em><b>Target Multiplicity</b></em>' attribute.
	 * The default value is <code>"1"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Target Multiplicity</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target Multiplicity</em>' attribute.
	 * @see #setTargetMultiplicity(String)
	 * @see notiondiagram.NotiondiagramPackage#getRelation_TargetMultiplicity()
	 * @model default="1"
	 * @generated
	 */
	String getTargetMultiplicity();

	/**
	 * Sets the value of the '{@link notiondiagram.Relation#getTargetMultiplicity <em>Target Multiplicity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target Multiplicity</em>' attribute.
	 * @see #getTargetMultiplicity()
	 * @generated
	 */
	void setTargetMultiplicity(String value);

	/**
	 * Returns the value of the '<em><b>Source Multiplicity</b></em>' attribute.
	 * The default value is <code>"1"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Source Multiplicity</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Source Multiplicity</em>' attribute.
	 * @see #setSourceMultiplicity(String)
	 * @see notiondiagram.NotiondiagramPackage#getRelation_SourceMultiplicity()
	 * @model default="1"
	 * @generated
	 */
	String getSourceMultiplicity();

	/**
	 * Sets the value of the '{@link notiondiagram.Relation#getSourceMultiplicity <em>Source Multiplicity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source Multiplicity</em>' attribute.
	 * @see #getSourceMultiplicity()
	 * @generated
	 */
	void setSourceMultiplicity(String value);

	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(int)
	 * @see notiondiagram.NotiondiagramPackage#getRelation_Id()
	 * @model id="true"
	 * @generated
	 */
	int getId();

	/**
	 * Sets the value of the '{@link notiondiagram.Relation#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(int value);

	/**
	 * Returns the value of the '<em><b>Stereotype</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Stereotype</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Stereotype</em>' attribute.
	 * @see notiondiagram.NotiondiagramPackage#getRelation_Stereotype()
	 * @model default="" transient="true" changeable="false" derived="true"
	 * @generated
	 */
	String getStereotype();

} // Relation
