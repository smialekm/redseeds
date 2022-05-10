/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package notiondiagram;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Attribute Relation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link notiondiagram.AttributeRelation#getSourceId <em>Source Id</em>}</li>
 *   <li>{@link notiondiagram.AttributeRelation#getTargetId <em>Target Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see notiondiagram.NotiondiagramPackage#getAttributeRelation()
 * @model
 * @generated
 */
public interface AttributeRelation extends AbstractRelation {

	/**
	 * Returns the value of the '<em><b>Source Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Source Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Source Id</em>' attribute.
	 * @see notiondiagram.NotiondiagramPackage#getAttributeRelation_SourceId()
	 * @model changeable="false"
	 * @generated
	 */
	int getSourceId();

	/**
	 * Returns the value of the '<em><b>Target Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Target Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target Id</em>' attribute.
	 * @see notiondiagram.NotiondiagramPackage#getAttributeRelation_TargetId()
	 * @model changeable="false"
	 * @generated
	 */
	int getTargetId();
} // AttributeRelation
