/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package notiondiagram.impl;

import notiondiagram.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class NotiondiagramFactoryImpl extends EFactoryImpl implements NotiondiagramFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static NotiondiagramFactory init() {
		try {
			NotiondiagramFactory theNotiondiagramFactory = (NotiondiagramFactory)EPackage.Registry.INSTANCE.getEFactory("http://notiondiagram/1.0"); 
			if (theNotiondiagramFactory != null) {
				return theNotiondiagramFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new NotiondiagramFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotiondiagramFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case NotiondiagramPackage.NOTION_DIAGRAM: return createNotionDiagram();
			case NotiondiagramPackage.NOTION: return createNotion();
			case NotiondiagramPackage.PHRASE: return createPhrase();
			case NotiondiagramPackage.GENERALIZATION: return createGeneralization();
			case NotiondiagramPackage.ATTRIBUTE_RELATION: return createAttributeRelation();
			case NotiondiagramPackage.DIRECTED_RELATION: return createDirectedRelation();
			case NotiondiagramPackage.INDIRECT_RELATION: return createIndirectRelation();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotionDiagram createNotionDiagram() {
		NotionDiagramImpl notionDiagram = new NotionDiagramImpl();
		return notionDiagram;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Notion createNotion() {
		NotionImpl notion = new NotionImpl();
		return notion;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Phrase createPhrase() {
		PhraseImpl phrase = new PhraseImpl();
		return phrase;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Generalization createGeneralization() {
		GeneralizationImpl generalization = new GeneralizationImpl();
		return generalization;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AttributeRelation createAttributeRelation() {
		AttributeRelationImpl attributeRelation = new AttributeRelationImpl();
		return attributeRelation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DirectedRelation createDirectedRelation() {
		DirectedRelationImpl directedRelation = new DirectedRelationImpl();
		return directedRelation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IndirectRelation createIndirectRelation() {
		IndirectRelationImpl indirectRelation = new IndirectRelationImpl();
		return indirectRelation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotiondiagramPackage getNotiondiagramPackage() {
		return (NotiondiagramPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static NotiondiagramPackage getPackage() {
		return NotiondiagramPackage.eINSTANCE;
	}

} //NotiondiagramFactoryImpl
