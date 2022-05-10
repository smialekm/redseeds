/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package notiondiagram;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see notiondiagram.NotiondiagramPackage
 * @generated
 */
public interface NotiondiagramFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	NotiondiagramFactory eINSTANCE = notiondiagram.impl.NotiondiagramFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Notion Diagram</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Notion Diagram</em>'.
	 * @generated
	 */
	NotionDiagram createNotionDiagram();

	/**
	 * Returns a new object of class '<em>Notion</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Notion</em>'.
	 * @generated
	 */
	Notion createNotion();

	/**
	 * Returns a new object of class '<em>Phrase</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Phrase</em>'.
	 * @generated
	 */
	Phrase createPhrase();

	/**
	 * Returns a new object of class '<em>Generalization</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Generalization</em>'.
	 * @generated
	 */
	Generalization createGeneralization();

	/**
	 * Returns a new object of class '<em>Attribute Relation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Attribute Relation</em>'.
	 * @generated
	 */
	AttributeRelation createAttributeRelation();

	/**
	 * Returns a new object of class '<em>Directed Relation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Directed Relation</em>'.
	 * @generated
	 */
	DirectedRelation createDirectedRelation();

	/**
	 * Returns a new object of class '<em>Indirect Relation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Indirect Relation</em>'.
	 * @generated
	 */
	IndirectRelation createIndirectRelation();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	NotiondiagramPackage getNotiondiagramPackage();

} //NotiondiagramFactory
