/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package notiondiagram;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Phrase</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link notiondiagram.Phrase#getText <em>Text</em>}</li>
 *   <li>{@link notiondiagram.Phrase#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see notiondiagram.NotiondiagramPackage#getPhrase()
 * @model
 * @generated
 */
public interface Phrase extends EObject {
	/**
	 * Returns the value of the '<em><b>Text</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Text</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Text</em>' attribute.
	 * @see notiondiagram.NotiondiagramPackage#getPhrase_Text()
	 * @model default="" transient="true" changeable="false" derived="true"
	 * @generated
	 */
	String getText();

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
	 * @see notiondiagram.NotiondiagramPackage#getPhrase_Id()
	 * @model id="true"
	 * @generated
	 */
	int getId();

	/**
	 * Sets the value of the '{@link notiondiagram.Phrase#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(int value);

} // Phrase
