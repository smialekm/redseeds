/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package notiondiagram;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Notion</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link notiondiagram.Notion#getPhrases <em>Phrases</em>}</li>
 *   <li>{@link notiondiagram.Notion#getName <em>Name</em>}</li>
 *   <li>{@link notiondiagram.Notion#getId <em>Id</em>}</li>
 *   <li>{@link notiondiagram.Notion#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see notiondiagram.NotiondiagramPackage#getNotion()
 * @model
 * @generated
 */
public interface Notion extends EObject {
	/**
	 * Returns the value of the '<em><b>Phrases</b></em>' containment reference list.
	 * The list contents are of type {@link notiondiagram.Phrase}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Phrases</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Phrases</em>' containment reference list.
	 * @see notiondiagram.NotiondiagramPackage#getNotion_Phrases()
	 * @model containment="true" transient="true"
	 * @generated
	 */
	EList<Phrase> getPhrases();

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
	 * @see notiondiagram.NotiondiagramPackage#getNotion_Name()
	 * @model transient="true" derived="true"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link notiondiagram.Notion#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

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
	 * @see notiondiagram.NotiondiagramPackage#getNotion_Id()
	 * @model id="true"
	 * @generated
	 */
	int getId();

	/**
	 * Sets the value of the '{@link notiondiagram.Notion#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(int value);

	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see notiondiagram.NotiondiagramPackage#getNotion_Type()
	 * @model transient="true" changeable="false" derived="true"
	 * @generated
	 */
	String getType();

} // Notion
