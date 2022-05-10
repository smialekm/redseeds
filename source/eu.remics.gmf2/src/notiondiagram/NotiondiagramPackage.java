/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package notiondiagram;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see notiondiagram.NotiondiagramFactory
 * @model kind="package"
 * @generated
 */
public interface NotiondiagramPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "notiondiagram";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://notiondiagram/1.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "notiondiagram";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	NotiondiagramPackage eINSTANCE = notiondiagram.impl.NotiondiagramPackageImpl.init();

	/**
	 * The meta object id for the '{@link notiondiagram.impl.NotionDiagramImpl <em>Notion Diagram</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see notiondiagram.impl.NotionDiagramImpl
	 * @see notiondiagram.impl.NotiondiagramPackageImpl#getNotionDiagram()
	 * @generated
	 */
	int NOTION_DIAGRAM = 0;

	/**
	 * The feature id for the '<em><b>Relations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NOTION_DIAGRAM__RELATIONS = 0;

	/**
	 * The feature id for the '<em><b>Attribute Relations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NOTION_DIAGRAM__ATTRIBUTE_RELATIONS = 1;

	/**
	 * The feature id for the '<em><b>Generalizations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NOTION_DIAGRAM__GENERALIZATIONS = 2;

	/**
	 * The feature id for the '<em><b>Notions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NOTION_DIAGRAM__NOTIONS = 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NOTION_DIAGRAM__NAME = 4;

	/**
	 * The feature id for the '<em><b>Package</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NOTION_DIAGRAM__PACKAGE = 5;

	/**
	 * The number of structural features of the '<em>Notion Diagram</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NOTION_DIAGRAM_FEATURE_COUNT = 6;

	/**
	 * The meta object id for the '{@link notiondiagram.impl.NotionImpl <em>Notion</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see notiondiagram.impl.NotionImpl
	 * @see notiondiagram.impl.NotiondiagramPackageImpl#getNotion()
	 * @generated
	 */
	int NOTION = 1;

	/**
	 * The feature id for the '<em><b>Phrases</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NOTION__PHRASES = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NOTION__NAME = 1;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NOTION__ID = 2;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NOTION__TYPE = 3;

	/**
	 * The number of structural features of the '<em>Notion</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NOTION_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link notiondiagram.impl.PhraseImpl <em>Phrase</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see notiondiagram.impl.PhraseImpl
	 * @see notiondiagram.impl.NotiondiagramPackageImpl#getPhrase()
	 * @generated
	 */
	int PHRASE = 2;

	/**
	 * The feature id for the '<em><b>Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHRASE__TEXT = 0;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHRASE__ID = 1;

	/**
	 * The number of structural features of the '<em>Phrase</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHRASE_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link notiondiagram.impl.AbstractRelationImpl <em>Abstract Relation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see notiondiagram.impl.AbstractRelationImpl
	 * @see notiondiagram.impl.NotiondiagramPackageImpl#getAbstractRelation()
	 * @generated
	 */
	int ABSTRACT_RELATION = 5;

	/**
	 * The feature id for the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_RELATION__SOURCE = 0;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_RELATION__TARGET = 1;

	/**
	 * The number of structural features of the '<em>Abstract Relation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_RELATION_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link notiondiagram.impl.GeneralizationImpl <em>Generalization</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see notiondiagram.impl.GeneralizationImpl
	 * @see notiondiagram.impl.NotiondiagramPackageImpl#getGeneralization()
	 * @generated
	 */
	int GENERALIZATION = 3;

	/**
	 * The feature id for the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERALIZATION__SOURCE = ABSTRACT_RELATION__SOURCE;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERALIZATION__TARGET = ABSTRACT_RELATION__TARGET;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERALIZATION__ID = ABSTRACT_RELATION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Generalization</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GENERALIZATION_FEATURE_COUNT = ABSTRACT_RELATION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link notiondiagram.impl.AttributeRelationImpl <em>Attribute Relation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see notiondiagram.impl.AttributeRelationImpl
	 * @see notiondiagram.impl.NotiondiagramPackageImpl#getAttributeRelation()
	 * @generated
	 */
	int ATTRIBUTE_RELATION = 4;

	/**
	 * The feature id for the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_RELATION__SOURCE = ABSTRACT_RELATION__SOURCE;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_RELATION__TARGET = ABSTRACT_RELATION__TARGET;

	/**
	 * The feature id for the '<em><b>Source Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_RELATION__SOURCE_ID = ABSTRACT_RELATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Target Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_RELATION__TARGET_ID = ABSTRACT_RELATION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Attribute Relation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_RELATION_FEATURE_COUNT = ABSTRACT_RELATION_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link notiondiagram.impl.RelationImpl <em>Relation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see notiondiagram.impl.RelationImpl
	 * @see notiondiagram.impl.NotiondiagramPackageImpl#getRelation()
	 * @generated
	 */
	int RELATION = 7;

	/**
	 * The feature id for the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATION__SOURCE = ABSTRACT_RELATION__SOURCE;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATION__TARGET = ABSTRACT_RELATION__TARGET;

	/**
	 * The feature id for the '<em><b>Target Multiplicity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATION__TARGET_MULTIPLICITY = ABSTRACT_RELATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Source Multiplicity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATION__SOURCE_MULTIPLICITY = ABSTRACT_RELATION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATION__ID = ABSTRACT_RELATION_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Stereotype</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATION__STEREOTYPE = ABSTRACT_RELATION_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Relation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATION_FEATURE_COUNT = ABSTRACT_RELATION_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link notiondiagram.impl.DirectedRelationImpl <em>Directed Relation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see notiondiagram.impl.DirectedRelationImpl
	 * @see notiondiagram.impl.NotiondiagramPackageImpl#getDirectedRelation()
	 * @generated
	 */
	int DIRECTED_RELATION = 6;

	/**
	 * The feature id for the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIRECTED_RELATION__SOURCE = RELATION__SOURCE;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIRECTED_RELATION__TARGET = RELATION__TARGET;

	/**
	 * The feature id for the '<em><b>Target Multiplicity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIRECTED_RELATION__TARGET_MULTIPLICITY = RELATION__TARGET_MULTIPLICITY;

	/**
	 * The feature id for the '<em><b>Source Multiplicity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIRECTED_RELATION__SOURCE_MULTIPLICITY = RELATION__SOURCE_MULTIPLICITY;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIRECTED_RELATION__ID = RELATION__ID;

	/**
	 * The feature id for the '<em><b>Stereotype</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIRECTED_RELATION__STEREOTYPE = RELATION__STEREOTYPE;

	/**
	 * The number of structural features of the '<em>Directed Relation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIRECTED_RELATION_FEATURE_COUNT = RELATION_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link notiondiagram.impl.IndirectRelationImpl <em>Indirect Relation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see notiondiagram.impl.IndirectRelationImpl
	 * @see notiondiagram.impl.NotiondiagramPackageImpl#getIndirectRelation()
	 * @generated
	 */
	int INDIRECT_RELATION = 8;

	/**
	 * The feature id for the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INDIRECT_RELATION__SOURCE = RELATION__SOURCE;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INDIRECT_RELATION__TARGET = RELATION__TARGET;

	/**
	 * The feature id for the '<em><b>Target Multiplicity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INDIRECT_RELATION__TARGET_MULTIPLICITY = RELATION__TARGET_MULTIPLICITY;

	/**
	 * The feature id for the '<em><b>Source Multiplicity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INDIRECT_RELATION__SOURCE_MULTIPLICITY = RELATION__SOURCE_MULTIPLICITY;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INDIRECT_RELATION__ID = RELATION__ID;

	/**
	 * The feature id for the '<em><b>Stereotype</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INDIRECT_RELATION__STEREOTYPE = RELATION__STEREOTYPE;

	/**
	 * The number of structural features of the '<em>Indirect Relation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INDIRECT_RELATION_FEATURE_COUNT = RELATION_FEATURE_COUNT + 0;


	/**
	 * Returns the meta object for class '{@link notiondiagram.NotionDiagram <em>Notion Diagram</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Notion Diagram</em>'.
	 * @see notiondiagram.NotionDiagram
	 * @generated
	 */
	EClass getNotionDiagram();

	/**
	 * Returns the meta object for the containment reference list '{@link notiondiagram.NotionDiagram#getRelations <em>Relations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Relations</em>'.
	 * @see notiondiagram.NotionDiagram#getRelations()
	 * @see #getNotionDiagram()
	 * @generated
	 */
	EReference getNotionDiagram_Relations();

	/**
	 * Returns the meta object for the containment reference list '{@link notiondiagram.NotionDiagram#getAttributeRelations <em>Attribute Relations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Attribute Relations</em>'.
	 * @see notiondiagram.NotionDiagram#getAttributeRelations()
	 * @see #getNotionDiagram()
	 * @generated
	 */
	EReference getNotionDiagram_AttributeRelations();

	/**
	 * Returns the meta object for the containment reference list '{@link notiondiagram.NotionDiagram#getGeneralizations <em>Generalizations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Generalizations</em>'.
	 * @see notiondiagram.NotionDiagram#getGeneralizations()
	 * @see #getNotionDiagram()
	 * @generated
	 */
	EReference getNotionDiagram_Generalizations();

	/**
	 * Returns the meta object for the containment reference list '{@link notiondiagram.NotionDiagram#getNotions <em>Notions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Notions</em>'.
	 * @see notiondiagram.NotionDiagram#getNotions()
	 * @see #getNotionDiagram()
	 * @generated
	 */
	EReference getNotionDiagram_Notions();

	/**
	 * Returns the meta object for the attribute '{@link notiondiagram.NotionDiagram#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see notiondiagram.NotionDiagram#getName()
	 * @see #getNotionDiagram()
	 * @generated
	 */
	EAttribute getNotionDiagram_Name();

	/**
	 * Returns the meta object for the attribute '{@link notiondiagram.NotionDiagram#getPackage <em>Package</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Package</em>'.
	 * @see notiondiagram.NotionDiagram#getPackage()
	 * @see #getNotionDiagram()
	 * @generated
	 */
	EAttribute getNotionDiagram_Package();

	/**
	 * Returns the meta object for class '{@link notiondiagram.Notion <em>Notion</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Notion</em>'.
	 * @see notiondiagram.Notion
	 * @generated
	 */
	EClass getNotion();

	/**
	 * Returns the meta object for the containment reference list '{@link notiondiagram.Notion#getPhrases <em>Phrases</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Phrases</em>'.
	 * @see notiondiagram.Notion#getPhrases()
	 * @see #getNotion()
	 * @generated
	 */
	EReference getNotion_Phrases();

	/**
	 * Returns the meta object for the attribute '{@link notiondiagram.Notion#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see notiondiagram.Notion#getName()
	 * @see #getNotion()
	 * @generated
	 */
	EAttribute getNotion_Name();

	/**
	 * Returns the meta object for the attribute '{@link notiondiagram.Notion#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see notiondiagram.Notion#getId()
	 * @see #getNotion()
	 * @generated
	 */
	EAttribute getNotion_Id();

	/**
	 * Returns the meta object for the attribute '{@link notiondiagram.Notion#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see notiondiagram.Notion#getType()
	 * @see #getNotion()
	 * @generated
	 */
	EAttribute getNotion_Type();

	/**
	 * Returns the meta object for class '{@link notiondiagram.Phrase <em>Phrase</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Phrase</em>'.
	 * @see notiondiagram.Phrase
	 * @generated
	 */
	EClass getPhrase();

	/**
	 * Returns the meta object for the attribute '{@link notiondiagram.Phrase#getText <em>Text</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Text</em>'.
	 * @see notiondiagram.Phrase#getText()
	 * @see #getPhrase()
	 * @generated
	 */
	EAttribute getPhrase_Text();

	/**
	 * Returns the meta object for the attribute '{@link notiondiagram.Phrase#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see notiondiagram.Phrase#getId()
	 * @see #getPhrase()
	 * @generated
	 */
	EAttribute getPhrase_Id();

	/**
	 * Returns the meta object for class '{@link notiondiagram.Generalization <em>Generalization</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Generalization</em>'.
	 * @see notiondiagram.Generalization
	 * @generated
	 */
	EClass getGeneralization();

	/**
	 * Returns the meta object for the attribute '{@link notiondiagram.Generalization#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see notiondiagram.Generalization#getId()
	 * @see #getGeneralization()
	 * @generated
	 */
	EAttribute getGeneralization_Id();

	/**
	 * Returns the meta object for class '{@link notiondiagram.AttributeRelation <em>Attribute Relation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Attribute Relation</em>'.
	 * @see notiondiagram.AttributeRelation
	 * @generated
	 */
	EClass getAttributeRelation();

	/**
	 * Returns the meta object for the attribute '{@link notiondiagram.AttributeRelation#getSourceId <em>Source Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Source Id</em>'.
	 * @see notiondiagram.AttributeRelation#getSourceId()
	 * @see #getAttributeRelation()
	 * @generated
	 */
	EAttribute getAttributeRelation_SourceId();

	/**
	 * Returns the meta object for the attribute '{@link notiondiagram.AttributeRelation#getTargetId <em>Target Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Target Id</em>'.
	 * @see notiondiagram.AttributeRelation#getTargetId()
	 * @see #getAttributeRelation()
	 * @generated
	 */
	EAttribute getAttributeRelation_TargetId();

	/**
	 * Returns the meta object for class '{@link notiondiagram.AbstractRelation <em>Abstract Relation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Abstract Relation</em>'.
	 * @see notiondiagram.AbstractRelation
	 * @generated
	 */
	EClass getAbstractRelation();

	/**
	 * Returns the meta object for the reference '{@link notiondiagram.AbstractRelation#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Source</em>'.
	 * @see notiondiagram.AbstractRelation#getSource()
	 * @see #getAbstractRelation()
	 * @generated
	 */
	EReference getAbstractRelation_Source();

	/**
	 * Returns the meta object for the reference '{@link notiondiagram.AbstractRelation#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Target</em>'.
	 * @see notiondiagram.AbstractRelation#getTarget()
	 * @see #getAbstractRelation()
	 * @generated
	 */
	EReference getAbstractRelation_Target();

	/**
	 * Returns the meta object for class '{@link notiondiagram.DirectedRelation <em>Directed Relation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Directed Relation</em>'.
	 * @see notiondiagram.DirectedRelation
	 * @generated
	 */
	EClass getDirectedRelation();

	/**
	 * Returns the meta object for class '{@link notiondiagram.Relation <em>Relation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Relation</em>'.
	 * @see notiondiagram.Relation
	 * @generated
	 */
	EClass getRelation();

	/**
	 * Returns the meta object for the attribute '{@link notiondiagram.Relation#getTargetMultiplicity <em>Target Multiplicity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Target Multiplicity</em>'.
	 * @see notiondiagram.Relation#getTargetMultiplicity()
	 * @see #getRelation()
	 * @generated
	 */
	EAttribute getRelation_TargetMultiplicity();

	/**
	 * Returns the meta object for the attribute '{@link notiondiagram.Relation#getSourceMultiplicity <em>Source Multiplicity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Source Multiplicity</em>'.
	 * @see notiondiagram.Relation#getSourceMultiplicity()
	 * @see #getRelation()
	 * @generated
	 */
	EAttribute getRelation_SourceMultiplicity();

	/**
	 * Returns the meta object for the attribute '{@link notiondiagram.Relation#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see notiondiagram.Relation#getId()
	 * @see #getRelation()
	 * @generated
	 */
	EAttribute getRelation_Id();

	/**
	 * Returns the meta object for the attribute '{@link notiondiagram.Relation#getStereotype <em>Stereotype</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Stereotype</em>'.
	 * @see notiondiagram.Relation#getStereotype()
	 * @see #getRelation()
	 * @generated
	 */
	EAttribute getRelation_Stereotype();

	/**
	 * Returns the meta object for class '{@link notiondiagram.IndirectRelation <em>Indirect Relation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Indirect Relation</em>'.
	 * @see notiondiagram.IndirectRelation
	 * @generated
	 */
	EClass getIndirectRelation();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	NotiondiagramFactory getNotiondiagramFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link notiondiagram.impl.NotionDiagramImpl <em>Notion Diagram</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see notiondiagram.impl.NotionDiagramImpl
		 * @see notiondiagram.impl.NotiondiagramPackageImpl#getNotionDiagram()
		 * @generated
		 */
		EClass NOTION_DIAGRAM = eINSTANCE.getNotionDiagram();

		/**
		 * The meta object literal for the '<em><b>Relations</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NOTION_DIAGRAM__RELATIONS = eINSTANCE.getNotionDiagram_Relations();

		/**
		 * The meta object literal for the '<em><b>Attribute Relations</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NOTION_DIAGRAM__ATTRIBUTE_RELATIONS = eINSTANCE.getNotionDiagram_AttributeRelations();

		/**
		 * The meta object literal for the '<em><b>Generalizations</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NOTION_DIAGRAM__GENERALIZATIONS = eINSTANCE.getNotionDiagram_Generalizations();

		/**
		 * The meta object literal for the '<em><b>Notions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NOTION_DIAGRAM__NOTIONS = eINSTANCE.getNotionDiagram_Notions();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NOTION_DIAGRAM__NAME = eINSTANCE.getNotionDiagram_Name();

		/**
		 * The meta object literal for the '<em><b>Package</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NOTION_DIAGRAM__PACKAGE = eINSTANCE.getNotionDiagram_Package();

		/**
		 * The meta object literal for the '{@link notiondiagram.impl.NotionImpl <em>Notion</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see notiondiagram.impl.NotionImpl
		 * @see notiondiagram.impl.NotiondiagramPackageImpl#getNotion()
		 * @generated
		 */
		EClass NOTION = eINSTANCE.getNotion();

		/**
		 * The meta object literal for the '<em><b>Phrases</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NOTION__PHRASES = eINSTANCE.getNotion_Phrases();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NOTION__NAME = eINSTANCE.getNotion_Name();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NOTION__ID = eINSTANCE.getNotion_Id();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NOTION__TYPE = eINSTANCE.getNotion_Type();

		/**
		 * The meta object literal for the '{@link notiondiagram.impl.PhraseImpl <em>Phrase</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see notiondiagram.impl.PhraseImpl
		 * @see notiondiagram.impl.NotiondiagramPackageImpl#getPhrase()
		 * @generated
		 */
		EClass PHRASE = eINSTANCE.getPhrase();

		/**
		 * The meta object literal for the '<em><b>Text</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PHRASE__TEXT = eINSTANCE.getPhrase_Text();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PHRASE__ID = eINSTANCE.getPhrase_Id();

		/**
		 * The meta object literal for the '{@link notiondiagram.impl.GeneralizationImpl <em>Generalization</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see notiondiagram.impl.GeneralizationImpl
		 * @see notiondiagram.impl.NotiondiagramPackageImpl#getGeneralization()
		 * @generated
		 */
		EClass GENERALIZATION = eINSTANCE.getGeneralization();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GENERALIZATION__ID = eINSTANCE.getGeneralization_Id();

		/**
		 * The meta object literal for the '{@link notiondiagram.impl.AttributeRelationImpl <em>Attribute Relation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see notiondiagram.impl.AttributeRelationImpl
		 * @see notiondiagram.impl.NotiondiagramPackageImpl#getAttributeRelation()
		 * @generated
		 */
		EClass ATTRIBUTE_RELATION = eINSTANCE.getAttributeRelation();

		/**
		 * The meta object literal for the '<em><b>Source Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ATTRIBUTE_RELATION__SOURCE_ID = eINSTANCE.getAttributeRelation_SourceId();

		/**
		 * The meta object literal for the '<em><b>Target Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ATTRIBUTE_RELATION__TARGET_ID = eINSTANCE.getAttributeRelation_TargetId();

		/**
		 * The meta object literal for the '{@link notiondiagram.impl.AbstractRelationImpl <em>Abstract Relation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see notiondiagram.impl.AbstractRelationImpl
		 * @see notiondiagram.impl.NotiondiagramPackageImpl#getAbstractRelation()
		 * @generated
		 */
		EClass ABSTRACT_RELATION = eINSTANCE.getAbstractRelation();

		/**
		 * The meta object literal for the '<em><b>Source</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ABSTRACT_RELATION__SOURCE = eINSTANCE.getAbstractRelation_Source();

		/**
		 * The meta object literal for the '<em><b>Target</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ABSTRACT_RELATION__TARGET = eINSTANCE.getAbstractRelation_Target();

		/**
		 * The meta object literal for the '{@link notiondiagram.impl.DirectedRelationImpl <em>Directed Relation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see notiondiagram.impl.DirectedRelationImpl
		 * @see notiondiagram.impl.NotiondiagramPackageImpl#getDirectedRelation()
		 * @generated
		 */
		EClass DIRECTED_RELATION = eINSTANCE.getDirectedRelation();

		/**
		 * The meta object literal for the '{@link notiondiagram.impl.RelationImpl <em>Relation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see notiondiagram.impl.RelationImpl
		 * @see notiondiagram.impl.NotiondiagramPackageImpl#getRelation()
		 * @generated
		 */
		EClass RELATION = eINSTANCE.getRelation();

		/**
		 * The meta object literal for the '<em><b>Target Multiplicity</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RELATION__TARGET_MULTIPLICITY = eINSTANCE.getRelation_TargetMultiplicity();

		/**
		 * The meta object literal for the '<em><b>Source Multiplicity</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RELATION__SOURCE_MULTIPLICITY = eINSTANCE.getRelation_SourceMultiplicity();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RELATION__ID = eINSTANCE.getRelation_Id();

		/**
		 * The meta object literal for the '<em><b>Stereotype</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RELATION__STEREOTYPE = eINSTANCE.getRelation_Stereotype();

		/**
		 * The meta object literal for the '{@link notiondiagram.impl.IndirectRelationImpl <em>Indirect Relation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see notiondiagram.impl.IndirectRelationImpl
		 * @see notiondiagram.impl.NotiondiagramPackageImpl#getIndirectRelation()
		 * @generated
		 */
		EClass INDIRECT_RELATION = eINSTANCE.getIndirectRelation();

	}

} //NotiondiagramPackage
