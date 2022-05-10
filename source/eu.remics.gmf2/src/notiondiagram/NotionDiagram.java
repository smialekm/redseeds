/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package notiondiagram;

import java.util.List;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

import de.uni_koblenz.jgralab.GraphElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Notion Diagram</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link notiondiagram.NotionDiagram#getRelations <em>Relations</em>}</li>
 *   <li>{@link notiondiagram.NotionDiagram#getAttributeRelations <em>Attribute Relations</em>}</li>
 *   <li>{@link notiondiagram.NotionDiagram#getGeneralizations <em>Generalizations</em>}</li>
 *   <li>{@link notiondiagram.NotionDiagram#getNotions <em>Notions</em>}</li>
 *   <li>{@link notiondiagram.NotionDiagram#getName <em>Name</em>}</li>
 *   <li>{@link notiondiagram.NotionDiagram#getPackage <em>Package</em>}</li>
 * </ul>
 * </p>
 *
 * @see notiondiagram.NotiondiagramPackage#getNotionDiagram()
 * @model
 * @generated
 */
public interface NotionDiagram extends EObject {
	/**
	 * Returns the value of the '<em><b>Relations</b></em>' containment reference list.
	 * The list contents are of type {@link notiondiagram.Relation}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Relations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Relations</em>' containment reference list.
	 * @see notiondiagram.NotiondiagramPackage#getNotionDiagram_Relations()
	 * @model containment="true" transient="true"
	 * @generated
	 */
	EList<Relation> getRelations();

	/**
	 * Returns the value of the '<em><b>Attribute Relations</b></em>' containment reference list.
	 * The list contents are of type {@link notiondiagram.AttributeRelation}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Attribute Relations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Attribute Relations</em>' containment reference list.
	 * @see notiondiagram.NotiondiagramPackage#getNotionDiagram_AttributeRelations()
	 * @model containment="true" transient="true"
	 * @generated
	 */
	EList<AttributeRelation> getAttributeRelations();

	/**
	 * Returns the value of the '<em><b>Generalizations</b></em>' containment reference list.
	 * The list contents are of type {@link notiondiagram.Generalization}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Generalizations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Generalizations</em>' containment reference list.
	 * @see notiondiagram.NotiondiagramPackage#getNotionDiagram_Generalizations()
	 * @model containment="true" transient="true"
	 * @generated
	 */
	EList<Generalization> getGeneralizations();

	/**
	 * Returns the value of the '<em><b>Notions</b></em>' containment reference list.
	 * The list contents are of type {@link notiondiagram.Notion}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Notions</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Notions</em>' containment reference list.
	 * @see notiondiagram.NotiondiagramPackage#getNotionDiagram_Notions()
	 * @model containment="true"
	 * @generated
	 */
	EList<Notion> getNotions();

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
	 * @see notiondiagram.NotiondiagramPackage#getNotionDiagram_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link notiondiagram.NotionDiagram#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Package</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Package</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Package</em>' attribute.
	 * @see #setPackage(int)
	 * @see notiondiagram.NotiondiagramPackage#getNotionDiagram_Package()
	 * @model
	 * @generated
	 */
	int getPackage();

	/**
	 * Sets the value of the '{@link notiondiagram.NotionDiagram#getPackage <em>Package</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Package</em>' attribute.
	 * @see #getPackage()
	 * @generated
	 */
	void setPackage(int value);
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public List<GraphElement> getGraphElements();

} // NotionDiagram
