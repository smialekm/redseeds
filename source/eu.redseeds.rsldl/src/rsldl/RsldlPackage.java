/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package rsldl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
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
 * @see rsldl.RsldlFactory
 * @model kind="package"
 * @generated
 */
public interface RsldlPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "rsldl";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://rsldl/1.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "rsldl";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	RsldlPackage eINSTANCE = rsldl.impl.RsldlPackageImpl.init();

	/**
	 * The meta object id for the '{@link rsldl.impl.DLSubsetImpl <em>DL Subset</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see rsldl.impl.DLSubsetImpl
	 * @see rsldl.impl.RsldlPackageImpl#getDLSubset()
	 * @generated
	 */
	int DL_SUBSET = 24;

	/**
	 * The number of structural features of the '<em>DL Subset</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_SUBSET_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link rsldl.impl.DLDomainImpl <em>DL Domain</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see rsldl.impl.DLDomainImpl
	 * @see rsldl.impl.RsldlPackageImpl#getDLDomain()
	 * @generated
	 */
	int DL_DOMAIN = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_DOMAIN__NAME = DL_SUBSET_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Elements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_DOMAIN__ELEMENTS = DL_SUBSET_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>DL Domain</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_DOMAIN_FEATURE_COUNT = DL_SUBSET_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link rsldl.impl.DLRelationshipParticipantImpl <em>DL Relationship Participant</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see rsldl.impl.DLRelationshipParticipantImpl
	 * @see rsldl.impl.RsldlPackageImpl#getDLRelationshipParticipant()
	 * @generated
	 */
	int DL_RELATIONSHIP_PARTICIPANT = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_RELATIONSHIP_PARTICIPANT__NAME = 0;

	/**
	 * The number of structural features of the '<em>DL Relationship Participant</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_RELATIONSHIP_PARTICIPANT_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link rsldl.impl.DLNotionImpl <em>DL Notion</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see rsldl.impl.DLNotionImpl
	 * @see rsldl.impl.RsldlPackageImpl#getDLNotion()
	 * @generated
	 */
	int DL_NOTION = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_NOTION__NAME = DL_RELATIONSHIP_PARTICIPANT__NAME;

	/**
	 * The feature id for the '<em><b>Domains</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_NOTION__DOMAINS = DL_RELATIONSHIP_PARTICIPANT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_NOTION__TYPE = DL_RELATIONSHIP_PARTICIPANT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Features</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_NOTION__FEATURES = DL_RELATIONSHIP_PARTICIPANT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Dereferences</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_NOTION__DEREFERENCES = DL_RELATIONSHIP_PARTICIPANT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Derived</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_NOTION__DERIVED = DL_RELATIONSHIP_PARTICIPANT_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>DL Notion</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_NOTION_FEATURE_COUNT = DL_RELATIONSHIP_PARTICIPANT_FEATURE_COUNT + 5;

	/**
	 * The meta object id for the '{@link rsldl.impl.DLPrimitiveImpl <em>DL Primitive</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see rsldl.impl.DLPrimitiveImpl
	 * @see rsldl.impl.RsldlPackageImpl#getDLPrimitive()
	 * @generated
	 */
	int DL_PRIMITIVE = 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_PRIMITIVE__NAME = DL_RELATIONSHIP_PARTICIPANT__NAME;

	/**
	 * The number of structural features of the '<em>DL Primitive</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_PRIMITIVE_FEATURE_COUNT = DL_RELATIONSHIP_PARTICIPANT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link rsldl.impl.DLDomainElementImpl <em>DL Domain Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see rsldl.impl.DLDomainElementImpl
	 * @see rsldl.impl.RsldlPackageImpl#getDLDomainElement()
	 * @generated
	 */
	int DL_DOMAIN_ELEMENT = 26;

	/**
	 * The feature id for the '<em><b>Domains</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_DOMAIN_ELEMENT__DOMAINS = 0;

	/**
	 * The number of structural features of the '<em>DL Domain Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_DOMAIN_ELEMENT_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link rsldl.impl.DLRelationshipImpl <em>DL Relationship</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see rsldl.impl.DLRelationshipImpl
	 * @see rsldl.impl.RsldlPackageImpl#getDLRelationship()
	 * @generated
	 */
	int DL_RELATIONSHIP = 4;

	/**
	 * The feature id for the '<em><b>Domains</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_RELATIONSHIP__DOMAINS = DL_DOMAIN_ELEMENT__DOMAINS;

	/**
	 * The feature id for the '<em><b>Participations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_RELATIONSHIP__PARTICIPATIONS = DL_DOMAIN_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_RELATIONSHIP__NAME = DL_DOMAIN_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_RELATIONSHIP__TYPE = DL_DOMAIN_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Abbreviation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_RELATIONSHIP__ABBREVIATION = DL_DOMAIN_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>DL Relationship</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_RELATIONSHIP_FEATURE_COUNT = DL_DOMAIN_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link rsldl.impl.DLNamedLinkImpl <em>DL Named Link</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see rsldl.impl.DLNamedLinkImpl
	 * @see rsldl.impl.RsldlPackageImpl#getDLNamedLink()
	 * @generated
	 */
	int DL_NAMED_LINK = 30;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_NAMED_LINK__NAME = 0;

	/**
	 * The number of structural features of the '<em>DL Named Link</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_NAMED_LINK_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link rsldl.impl.DLRelationshipParticipationImpl <em>DL Relationship Participation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see rsldl.impl.DLRelationshipParticipationImpl
	 * @see rsldl.impl.RsldlPackageImpl#getDLRelationshipParticipation()
	 * @generated
	 */
	int DL_RELATIONSHIP_PARTICIPATION = 5;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_RELATIONSHIP_PARTICIPATION__NAME = DL_NAMED_LINK__NAME;

	/**
	 * The feature id for the '<em><b>Direction</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_RELATIONSHIP_PARTICIPATION__DIRECTION = DL_NAMED_LINK_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Multiplicity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_RELATIONSHIP_PARTICIPATION__MULTIPLICITY = DL_NAMED_LINK_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Participant</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_RELATIONSHIP_PARTICIPATION__PARTICIPANT = DL_NAMED_LINK_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Relationship</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_RELATIONSHIP_PARTICIPATION__RELATIONSHIP = DL_NAMED_LINK_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Parent Participation</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_RELATIONSHIP_PARTICIPATION__PARENT_PARTICIPATION = DL_NAMED_LINK_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_RELATIONSHIP_PARTICIPATION__TYPE = DL_NAMED_LINK_FEATURE_COUNT + 5;

	/**
	 * The number of structural features of the '<em>DL Relationship Participation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_RELATIONSHIP_PARTICIPATION_FEATURE_COUNT = DL_NAMED_LINK_FEATURE_COUNT + 6;

	/**
	 * The meta object id for the '{@link rsldl.impl.DLRepositoryImpl <em>DL Repository</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see rsldl.impl.DLRepositoryImpl
	 * @see rsldl.impl.RsldlPackageImpl#getDLRepository()
	 * @generated
	 */
	int DL_REPOSITORY = 25;

	/**
	 * The meta object id for the '{@link rsldl.impl.DLDiagramImpl <em>DL Diagram</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see rsldl.impl.DLDiagramImpl
	 * @see rsldl.impl.RsldlPackageImpl#getDLDiagram()
	 * @generated
	 */
	int DL_DIAGRAM = 6;

	/**
	 * The feature id for the '<em><b>Relationship Participants</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_DIAGRAM__RELATIONSHIP_PARTICIPANTS = 0;

	/**
	 * The feature id for the '<em><b>Relationships</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_DIAGRAM__RELATIONSHIPS = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_DIAGRAM__NAME = 2;

	/**
	 * The number of structural features of the '<em>DL Diagram</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_DIAGRAM_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link rsldl.impl.DLReferenceImpl <em>DL Reference</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see rsldl.impl.DLReferenceImpl
	 * @see rsldl.impl.RsldlPackageImpl#getDLReference()
	 * @generated
	 */
	int DL_REFERENCE = 7;

	/**
	 * The feature id for the '<em><b>Domains</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_REFERENCE__DOMAINS = DL_RELATIONSHIP__DOMAINS;

	/**
	 * The feature id for the '<em><b>Participations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_REFERENCE__PARTICIPATIONS = DL_RELATIONSHIP__PARTICIPATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_REFERENCE__NAME = DL_RELATIONSHIP__NAME;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_REFERENCE__TYPE = DL_RELATIONSHIP__TYPE;

	/**
	 * The feature id for the '<em><b>Abbreviation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_REFERENCE__ABBREVIATION = DL_RELATIONSHIP__ABBREVIATION;

	/**
	 * The number of structural features of the '<em>DL Reference</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_REFERENCE_FEATURE_COUNT = DL_RELATIONSHIP_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link rsldl.impl.DLTransitionImpl <em>DL Transition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see rsldl.impl.DLTransitionImpl
	 * @see rsldl.impl.RsldlPackageImpl#getDLTransition()
	 * @generated
	 */
	int DL_TRANSITION = 8;

	/**
	 * The feature id for the '<em><b>Domains</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_TRANSITION__DOMAINS = DL_RELATIONSHIP__DOMAINS;

	/**
	 * The feature id for the '<em><b>Participations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_TRANSITION__PARTICIPATIONS = DL_RELATIONSHIP__PARTICIPATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_TRANSITION__NAME = DL_RELATIONSHIP__NAME;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_TRANSITION__TYPE = DL_RELATIONSHIP__TYPE;

	/**
	 * The feature id for the '<em><b>Abbreviation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_TRANSITION__ABBREVIATION = DL_RELATIONSHIP__ABBREVIATION;

	/**
	 * The number of structural features of the '<em>DL Transition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_TRANSITION_FEATURE_COUNT = DL_RELATIONSHIP_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link rsldl.impl.DLAlghoritmicTransitionImpl <em>DL Alghoritmic Transition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see rsldl.impl.DLAlghoritmicTransitionImpl
	 * @see rsldl.impl.RsldlPackageImpl#getDLAlghoritmicTransition()
	 * @generated
	 */
	int DL_ALGHORITMIC_TRANSITION = 9;

	/**
	 * The feature id for the '<em><b>Domains</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_ALGHORITMIC_TRANSITION__DOMAINS = DL_TRANSITION__DOMAINS;

	/**
	 * The feature id for the '<em><b>Participations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_ALGHORITMIC_TRANSITION__PARTICIPATIONS = DL_TRANSITION__PARTICIPATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_ALGHORITMIC_TRANSITION__NAME = DL_TRANSITION__NAME;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_ALGHORITMIC_TRANSITION__TYPE = DL_TRANSITION__TYPE;

	/**
	 * The feature id for the '<em><b>Abbreviation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_ALGHORITMIC_TRANSITION__ABBREVIATION = DL_TRANSITION__ABBREVIATION;

	/**
	 * The feature id for the '<em><b>Sequence</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_ALGHORITMIC_TRANSITION__SEQUENCE = DL_TRANSITION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>DL Alghoritmic Transition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_ALGHORITMIC_TRANSITION_FEATURE_COUNT = DL_TRANSITION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link rsldl.impl.DLPatternBasedTransitionImpl <em>DL Pattern Based Transition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see rsldl.impl.DLPatternBasedTransitionImpl
	 * @see rsldl.impl.RsldlPackageImpl#getDLPatternBasedTransition()
	 * @generated
	 */
	int DL_PATTERN_BASED_TRANSITION = 10;

	/**
	 * The feature id for the '<em><b>Domains</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_PATTERN_BASED_TRANSITION__DOMAINS = DL_TRANSITION__DOMAINS;

	/**
	 * The feature id for the '<em><b>Participations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_PATTERN_BASED_TRANSITION__PARTICIPATIONS = DL_TRANSITION__PARTICIPATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_PATTERN_BASED_TRANSITION__NAME = DL_TRANSITION__NAME;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_PATTERN_BASED_TRANSITION__TYPE = DL_TRANSITION__TYPE;

	/**
	 * The feature id for the '<em><b>Abbreviation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_PATTERN_BASED_TRANSITION__ABBREVIATION = DL_TRANSITION__ABBREVIATION;

	/**
	 * The feature id for the '<em><b>Pattern</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_PATTERN_BASED_TRANSITION__PATTERN = DL_TRANSITION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>DL Pattern Based Transition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_PATTERN_BASED_TRANSITION_FEATURE_COUNT = DL_TRANSITION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link rsldl.impl.DLPatternBasedReferenceImpl <em>DL Pattern Based Reference</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see rsldl.impl.DLPatternBasedReferenceImpl
	 * @see rsldl.impl.RsldlPackageImpl#getDLPatternBasedReference()
	 * @generated
	 */
	int DL_PATTERN_BASED_REFERENCE = 11;

	/**
	 * The feature id for the '<em><b>Domains</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_PATTERN_BASED_REFERENCE__DOMAINS = DL_REFERENCE__DOMAINS;

	/**
	 * The feature id for the '<em><b>Participations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_PATTERN_BASED_REFERENCE__PARTICIPATIONS = DL_REFERENCE__PARTICIPATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_PATTERN_BASED_REFERENCE__NAME = DL_REFERENCE__NAME;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_PATTERN_BASED_REFERENCE__TYPE = DL_REFERENCE__TYPE;

	/**
	 * The feature id for the '<em><b>Abbreviation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_PATTERN_BASED_REFERENCE__ABBREVIATION = DL_REFERENCE__ABBREVIATION;

	/**
	 * The feature id for the '<em><b>Pattern</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_PATTERN_BASED_REFERENCE__PATTERN = DL_REFERENCE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>DL Pattern Based Reference</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_PATTERN_BASED_REFERENCE_FEATURE_COUNT = DL_REFERENCE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link rsldl.impl.DLDataBasedReferenceImpl <em>DL Data Based Reference</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see rsldl.impl.DLDataBasedReferenceImpl
	 * @see rsldl.impl.RsldlPackageImpl#getDLDataBasedReference()
	 * @generated
	 */
	int DL_DATA_BASED_REFERENCE = 12;

	/**
	 * The feature id for the '<em><b>Domains</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_DATA_BASED_REFERENCE__DOMAINS = DL_REFERENCE__DOMAINS;

	/**
	 * The feature id for the '<em><b>Participations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_DATA_BASED_REFERENCE__PARTICIPATIONS = DL_REFERENCE__PARTICIPATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_DATA_BASED_REFERENCE__NAME = DL_REFERENCE__NAME;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_DATA_BASED_REFERENCE__TYPE = DL_REFERENCE__TYPE;

	/**
	 * The feature id for the '<em><b>Abbreviation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_DATA_BASED_REFERENCE__ABBREVIATION = DL_REFERENCE__ABBREVIATION;

	/**
	 * The number of structural features of the '<em>DL Data Based Reference</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_DATA_BASED_REFERENCE_FEATURE_COUNT = DL_REFERENCE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link rsldl.impl.DLEntityImpl <em>DL Entity</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see rsldl.impl.DLEntityImpl
	 * @see rsldl.impl.RsldlPackageImpl#getDLEntity()
	 * @generated
	 */
	int DL_ENTITY = 13;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_ENTITY__NAME = DL_NOTION__NAME;

	/**
	 * The feature id for the '<em><b>Domains</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_ENTITY__DOMAINS = DL_NOTION__DOMAINS;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_ENTITY__TYPE = DL_NOTION__TYPE;

	/**
	 * The feature id for the '<em><b>Features</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_ENTITY__FEATURES = DL_NOTION__FEATURES;

	/**
	 * The feature id for the '<em><b>Dereferences</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_ENTITY__DEREFERENCES = DL_NOTION__DEREFERENCES;

	/**
	 * The feature id for the '<em><b>Derived</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_ENTITY__DERIVED = DL_NOTION__DERIVED;

	/**
	 * The number of structural features of the '<em>DL Entity</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_ENTITY_FEATURE_COUNT = DL_NOTION_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link rsldl.impl.DLPropertyImpl <em>DL Property</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see rsldl.impl.DLPropertyImpl
	 * @see rsldl.impl.RsldlPackageImpl#getDLProperty()
	 * @generated
	 */
	int DL_PROPERTY = 14;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_PROPERTY__NAME = DL_NOTION__NAME;

	/**
	 * The feature id for the '<em><b>Domains</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_PROPERTY__DOMAINS = DL_NOTION__DOMAINS;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_PROPERTY__TYPE = DL_NOTION__TYPE;

	/**
	 * The feature id for the '<em><b>Features</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_PROPERTY__FEATURES = DL_NOTION__FEATURES;

	/**
	 * The feature id for the '<em><b>Dereferences</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_PROPERTY__DEREFERENCES = DL_NOTION__DEREFERENCES;

	/**
	 * The feature id for the '<em><b>Derived</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_PROPERTY__DERIVED = DL_NOTION__DERIVED;

	/**
	 * The feature id for the '<em><b>Value Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_PROPERTY__VALUE_TYPE = DL_NOTION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Set Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_PROPERTY__SET_TYPE = DL_NOTION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>DL Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_PROPERTY_FEATURE_COUNT = DL_NOTION_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link rsldl.impl.DLFeatureImpl <em>DL Feature</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see rsldl.impl.DLFeatureImpl
	 * @see rsldl.impl.RsldlPackageImpl#getDLFeature()
	 * @generated
	 */
	int DL_FEATURE = 15;

	/**
	 * The feature id for the '<em><b>Notion</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_FEATURE__NOTION = 0;

	/**
	 * The number of structural features of the '<em>DL Feature</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_FEATURE_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link rsldl.impl.DLConditionImpl <em>DL Condition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see rsldl.impl.DLConditionImpl
	 * @see rsldl.impl.RsldlPackageImpl#getDLCondition()
	 * @generated
	 */
	int DL_CONDITION = 16;

	/**
	 * The feature id for the '<em><b>Notion</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_CONDITION__NOTION = DL_FEATURE__NOTION;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_CONDITION__TYPE = DL_FEATURE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_CONDITION__TEXT = DL_FEATURE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>DL Condition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_CONDITION_FEATURE_COUNT = DL_FEATURE_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link rsldl.impl.DLAttributeLinkImpl <em>DL Attribute Link</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see rsldl.impl.DLAttributeLinkImpl
	 * @see rsldl.impl.RsldlPackageImpl#getDLAttributeLink()
	 * @generated
	 */
	int DL_ATTRIBUTE_LINK = 17;

	/**
	 * The feature id for the '<em><b>Notion</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_ATTRIBUTE_LINK__NOTION = DL_FEATURE__NOTION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_ATTRIBUTE_LINK__NAME = DL_FEATURE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Attribute</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_ATTRIBUTE_LINK__ATTRIBUTE = DL_FEATURE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_ATTRIBUTE_LINK__TYPE = DL_FEATURE_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>DL Attribute Link</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_ATTRIBUTE_LINK_FEATURE_COUNT = DL_FEATURE_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link rsldl.impl.DLInheritanceConditionImpl <em>DL Inheritance Condition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see rsldl.impl.DLInheritanceConditionImpl
	 * @see rsldl.impl.RsldlPackageImpl#getDLInheritanceCondition()
	 * @generated
	 */
	int DL_INHERITANCE_CONDITION = 18;

	/**
	 * The feature id for the '<em><b>Notion</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_INHERITANCE_CONDITION__NOTION = DL_CONDITION__NOTION;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_INHERITANCE_CONDITION__TYPE = DL_CONDITION__TYPE;

	/**
	 * The feature id for the '<em><b>Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_INHERITANCE_CONDITION__TEXT = DL_CONDITION__TEXT;

	/**
	 * The feature id for the '<em><b>Parents</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_INHERITANCE_CONDITION__PARENTS = DL_CONDITION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Feature Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_INHERITANCE_CONDITION__FEATURE_TYPE = DL_CONDITION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>DL Inheritance Condition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_INHERITANCE_CONDITION_FEATURE_COUNT = DL_CONDITION_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link rsldl.impl.DLIdentityConditionImpl <em>DL Identity Condition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see rsldl.impl.DLIdentityConditionImpl
	 * @see rsldl.impl.RsldlPackageImpl#getDLIdentityCondition()
	 * @generated
	 */
	int DL_IDENTITY_CONDITION = 19;

	/**
	 * The feature id for the '<em><b>Notion</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_IDENTITY_CONDITION__NOTION = DL_CONDITION__NOTION;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_IDENTITY_CONDITION__TYPE = DL_CONDITION__TYPE;

	/**
	 * The feature id for the '<em><b>Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_IDENTITY_CONDITION__TEXT = DL_CONDITION__TEXT;

	/**
	 * The feature id for the '<em><b>Pattern</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_IDENTITY_CONDITION__PATTERN = DL_CONDITION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>DL Identity Condition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_IDENTITY_CONDITION_FEATURE_COUNT = DL_CONDITION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link rsldl.impl.DLValidityConditionImpl <em>DL Validity Condition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see rsldl.impl.DLValidityConditionImpl
	 * @see rsldl.impl.RsldlPackageImpl#getDLValidityCondition()
	 * @generated
	 */
	int DL_VALIDITY_CONDITION = 20;

	/**
	 * The feature id for the '<em><b>Notion</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_VALIDITY_CONDITION__NOTION = DL_CONDITION__NOTION;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_VALIDITY_CONDITION__TYPE = DL_CONDITION__TYPE;

	/**
	 * The feature id for the '<em><b>Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_VALIDITY_CONDITION__TEXT = DL_CONDITION__TEXT;

	/**
	 * The feature id for the '<em><b>Pattern</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_VALIDITY_CONDITION__PATTERN = DL_CONDITION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>DL Validity Condition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_VALIDITY_CONDITION_FEATURE_COUNT = DL_CONDITION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link rsldl.impl.DLReferencingElementImpl <em>DL Referencing Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see rsldl.impl.DLReferencingElementImpl
	 * @see rsldl.impl.RsldlPackageImpl#getDLReferencingElement()
	 * @generated
	 */
	int DL_REFERENCING_ELEMENT = 35;

	/**
	 * The feature id for the '<em><b>Subject Link</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_REFERENCING_ELEMENT__SUBJECT_LINK = 0;

	/**
	 * The number of structural features of the '<em>DL Referencing Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_REFERENCING_ELEMENT_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link rsldl.impl.DLPatternImpl <em>DL Pattern</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see rsldl.impl.DLPatternImpl
	 * @see rsldl.impl.RsldlPackageImpl#getDLPattern()
	 * @generated
	 */
	int DL_PATTERN = 23;

	/**
	 * The feature id for the '<em><b>Subject Link</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_PATTERN__SUBJECT_LINK = DL_REFERENCING_ELEMENT__SUBJECT_LINK;

	/**
	 * The feature id for the '<em><b>Pattern</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_PATTERN__PATTERN = DL_REFERENCING_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>DL Pattern</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_PATTERN_FEATURE_COUNT = DL_REFERENCING_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link rsldl.impl.DLTransitionPatternImpl <em>DL Transition Pattern</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see rsldl.impl.DLTransitionPatternImpl
	 * @see rsldl.impl.RsldlPackageImpl#getDLTransitionPattern()
	 * @generated
	 */
	int DL_TRANSITION_PATTERN = 21;

	/**
	 * The feature id for the '<em><b>Subject Link</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_TRANSITION_PATTERN__SUBJECT_LINK = DL_PATTERN__SUBJECT_LINK;

	/**
	 * The feature id for the '<em><b>Pattern</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_TRANSITION_PATTERN__PATTERN = DL_PATTERN__PATTERN;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_TRANSITION_PATTERN__TYPE = DL_PATTERN_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>DL Transition Pattern</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_TRANSITION_PATTERN_FEATURE_COUNT = DL_PATTERN_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link rsldl.impl.DLConditionPatternImpl <em>DL Condition Pattern</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see rsldl.impl.DLConditionPatternImpl
	 * @see rsldl.impl.RsldlPackageImpl#getDLConditionPattern()
	 * @generated
	 */
	int DL_CONDITION_PATTERN = 22;

	/**
	 * The feature id for the '<em><b>Subject Link</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_CONDITION_PATTERN__SUBJECT_LINK = DL_PATTERN__SUBJECT_LINK;

	/**
	 * The feature id for the '<em><b>Pattern</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_CONDITION_PATTERN__PATTERN = DL_PATTERN__PATTERN;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_CONDITION_PATTERN__TYPE = DL_PATTERN_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>DL Condition Pattern</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_CONDITION_PATTERN_FEATURE_COUNT = DL_PATTERN_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Relationship Participants</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_REPOSITORY__RELATIONSHIP_PARTICIPANTS = DL_SUBSET_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Relationships</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_REPOSITORY__RELATIONSHIPS = DL_SUBSET_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_REPOSITORY__NAME = DL_SUBSET_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Domains</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_REPOSITORY__DOMAINS = DL_SUBSET_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Diagrams</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_REPOSITORY__DIAGRAMS = DL_SUBSET_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>DL Repository</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_REPOSITORY_FEATURE_COUNT = DL_SUBSET_FEATURE_COUNT + 5;

	/**
	 * The meta object id for the '{@link rsldl.impl.DLPatternConditionImpl <em>DL Pattern Condition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see rsldl.impl.DLPatternConditionImpl
	 * @see rsldl.impl.RsldlPackageImpl#getDLPatternCondition()
	 * @generated
	 */
	int DL_PATTERN_CONDITION = 27;

	/**
	 * The feature id for the '<em><b>Pattern</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_PATTERN_CONDITION__PATTERN = 0;

	/**
	 * The number of structural features of the '<em>DL Pattern Condition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_PATTERN_CONDITION_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link rsldl.impl.DLAlghoritmicTransitionSequenceElementImpl <em>DL Alghoritmic Transition Sequence Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see rsldl.impl.DLAlghoritmicTransitionSequenceElementImpl
	 * @see rsldl.impl.RsldlPackageImpl#getDLAlghoritmicTransitionSequenceElement()
	 * @generated
	 */
	int DL_ALGHORITMIC_TRANSITION_SEQUENCE_ELEMENT = 28;

	/**
	 * The feature id for the '<em><b>Sequent</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_ALGHORITMIC_TRANSITION_SEQUENCE_ELEMENT__SEQUENT = 0;

	/**
	 * The number of structural features of the '<em>DL Alghoritmic Transition Sequence Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_ALGHORITMIC_TRANSITION_SEQUENCE_ELEMENT_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link rsldl.impl.DLAlghoritmicTransitionStepImpl <em>DL Alghoritmic Transition Step</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see rsldl.impl.DLAlghoritmicTransitionStepImpl
	 * @see rsldl.impl.RsldlPackageImpl#getDLAlghoritmicTransitionStep()
	 * @generated
	 */
	int DL_ALGHORITMIC_TRANSITION_STEP = 29;

	/**
	 * The feature id for the '<em><b>Sequent</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_ALGHORITMIC_TRANSITION_STEP__SEQUENT = DL_ALGHORITMIC_TRANSITION_SEQUENCE_ELEMENT__SEQUENT;

	/**
	 * The number of structural features of the '<em>DL Alghoritmic Transition Step</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_ALGHORITMIC_TRANSITION_STEP_FEATURE_COUNT = DL_ALGHORITMIC_TRANSITION_SEQUENCE_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link rsldl.impl.DLDereferenceLinkImpl <em>DL Dereference Link</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see rsldl.impl.DLDereferenceLinkImpl
	 * @see rsldl.impl.RsldlPackageImpl#getDLDereferenceLink()
	 * @generated
	 */
	int DL_DEREFERENCE_LINK = 31;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_DEREFERENCE_LINK__NAME = DL_NAMED_LINK__NAME;

	/**
	 * The feature id for the '<em><b>Element Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_DEREFERENCE_LINK__ELEMENT_TYPE = DL_NAMED_LINK_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Pattern</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_DEREFERENCE_LINK__PATTERN = DL_NAMED_LINK_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>DL Dereference Link</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_DEREFERENCE_LINK_FEATURE_COUNT = DL_NAMED_LINK_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link rsldl.impl.DLCustomAlghoritmicTransitionStepImpl <em>DL Custom Alghoritmic Transition Step</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see rsldl.impl.DLCustomAlghoritmicTransitionStepImpl
	 * @see rsldl.impl.RsldlPackageImpl#getDLCustomAlghoritmicTransitionStep()
	 * @generated
	 */
	int DL_CUSTOM_ALGHORITMIC_TRANSITION_STEP = 32;

	/**
	 * The feature id for the '<em><b>Sequent</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_CUSTOM_ALGHORITMIC_TRANSITION_STEP__SEQUENT = DL_ALGHORITMIC_TRANSITION_STEP__SEQUENT;

	/**
	 * The feature id for the '<em><b>Alternative</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_CUSTOM_ALGHORITMIC_TRANSITION_STEP__ALTERNATIVE = DL_ALGHORITMIC_TRANSITION_STEP_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Action</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_CUSTOM_ALGHORITMIC_TRANSITION_STEP__ACTION = DL_ALGHORITMIC_TRANSITION_STEP_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Condition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_CUSTOM_ALGHORITMIC_TRANSITION_STEP__CONDITION = DL_ALGHORITMIC_TRANSITION_STEP_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>DL Custom Alghoritmic Transition Step</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_CUSTOM_ALGHORITMIC_TRANSITION_STEP_FEATURE_COUNT = DL_ALGHORITMIC_TRANSITION_STEP_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link rsldl.impl.DLPredefinedAlghoritmicTransitionStepImpl <em>DL Predefined Alghoritmic Transition Step</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see rsldl.impl.DLPredefinedAlghoritmicTransitionStepImpl
	 * @see rsldl.impl.RsldlPackageImpl#getDLPredefinedAlghoritmicTransitionStep()
	 * @generated
	 */
	int DL_PREDEFINED_ALGHORITMIC_TRANSITION_STEP = 33;

	/**
	 * The feature id for the '<em><b>Sequent</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_PREDEFINED_ALGHORITMIC_TRANSITION_STEP__SEQUENT = DL_ALGHORITMIC_TRANSITION_STEP__SEQUENT;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_PREDEFINED_ALGHORITMIC_TRANSITION_STEP__TYPE = DL_ALGHORITMIC_TRANSITION_STEP_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Pattern</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_PREDEFINED_ALGHORITMIC_TRANSITION_STEP__PATTERN = DL_ALGHORITMIC_TRANSITION_STEP_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>DL Predefined Alghoritmic Transition Step</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_PREDEFINED_ALGHORITMIC_TRANSITION_STEP_FEATURE_COUNT = DL_ALGHORITMIC_TRANSITION_STEP_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link rsldl.impl.DLControlFlowStatementImpl <em>DL Control Flow Statement</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see rsldl.impl.DLControlFlowStatementImpl
	 * @see rsldl.impl.RsldlPackageImpl#getDLControlFlowStatement()
	 * @generated
	 */
	int DL_CONTROL_FLOW_STATEMENT = 34;

	/**
	 * The feature id for the '<em><b>Sequent</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_CONTROL_FLOW_STATEMENT__SEQUENT = DL_ALGHORITMIC_TRANSITION_SEQUENCE_ELEMENT__SEQUENT;

	/**
	 * The feature id for the '<em><b>Subject Link</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_CONTROL_FLOW_STATEMENT__SUBJECT_LINK = DL_ALGHORITMIC_TRANSITION_SEQUENCE_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_CONTROL_FLOW_STATEMENT__TYPE = DL_ALGHORITMIC_TRANSITION_SEQUENCE_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>DL Control Flow Statement</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_CONTROL_FLOW_STATEMENT_FEATURE_COUNT = DL_ALGHORITMIC_TRANSITION_SEQUENCE_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link rsldl.impl.DLPartLinkImpl <em>DL Part Link</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see rsldl.impl.DLPartLinkImpl
	 * @see rsldl.impl.RsldlPackageImpl#getDLPartLink()
	 * @generated
	 */
	int DL_PART_LINK = 36;

	/**
	 * The feature id for the '<em><b>Notion</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_PART_LINK__NOTION = DL_FEATURE__NOTION;

	/**
	 * The feature id for the '<em><b>Part Dereference</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_PART_LINK__PART_DEREFERENCE = DL_FEATURE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>DL Part Link</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DL_PART_LINK_FEATURE_COUNT = DL_FEATURE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link rsldl.DLTypeRole <em>DL Type Role</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see rsldl.DLTypeRole
	 * @see rsldl.impl.RsldlPackageImpl#getDLTypeRole()
	 * @generated
	 */
	int DL_TYPE_ROLE = 37;

	/**
	 * The meta object id for the '{@link rsldl.DLRelationshipParticipationDirection <em>DL Relationship Participation Direction</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see rsldl.DLRelationshipParticipationDirection
	 * @see rsldl.impl.RsldlPackageImpl#getDLRelationshipParticipationDirection()
	 * @generated
	 */
	int DL_RELATIONSHIP_PARTICIPATION_DIRECTION = 38;

	/**
	 * The meta object id for the '{@link rsldl.DLRelationshipParticipationMultiplicity <em>DL Relationship Participation Multiplicity</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see rsldl.DLRelationshipParticipationMultiplicity
	 * @see rsldl.impl.RsldlPackageImpl#getDLRelationshipParticipationMultiplicity()
	 * @generated
	 */
	int DL_RELATIONSHIP_PARTICIPATION_MULTIPLICITY = 39;

	/**
	 * The meta object id for the '{@link rsldl.DLPropertyValueType <em>DL Property Value Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see rsldl.DLPropertyValueType
	 * @see rsldl.impl.RsldlPackageImpl#getDLPropertyValueType()
	 * @generated
	 */
	int DL_PROPERTY_VALUE_TYPE = 40;

	/**
	 * The meta object id for the '{@link rsldl.DLTransitionPatternType <em>DL Transition Pattern Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see rsldl.DLTransitionPatternType
	 * @see rsldl.impl.RsldlPackageImpl#getDLTransitionPatternType()
	 * @generated
	 */
	int DL_TRANSITION_PATTERN_TYPE = 41;

	/**
	 * The meta object id for the '{@link rsldl.DLConditionPatternType <em>DL Condition Pattern Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see rsldl.DLConditionPatternType
	 * @see rsldl.impl.RsldlPackageImpl#getDLConditionPatternType()
	 * @generated
	 */
	int DL_CONDITION_PATTERN_TYPE = 42;

	/**
	 * The meta object id for the '{@link rsldl.DLFeatureType <em>DL Feature Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see rsldl.DLFeatureType
	 * @see rsldl.impl.RsldlPackageImpl#getDLFeatureType()
	 * @generated
	 */
	int DL_FEATURE_TYPE = 43;

	/**
	 * The meta object id for the '{@link rsldl.DLRelationshipParticipationType <em>DL Relationship Participation Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see rsldl.DLRelationshipParticipationType
	 * @see rsldl.impl.RsldlPackageImpl#getDLRelationshipParticipationType()
	 * @generated
	 */
	int DL_RELATIONSHIP_PARTICIPATION_TYPE = 44;

	/**
	 * The meta object id for the '{@link rsldl.DLControlFlowStatementType <em>DL Control Flow Statement Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see rsldl.DLControlFlowStatementType
	 * @see rsldl.impl.RsldlPackageImpl#getDLControlFlowStatementType()
	 * @generated
	 */
	int DL_CONTROL_FLOW_STATEMENT_TYPE = 45;

	/**
	 * The meta object id for the '{@link rsldl.DLPredefinedStepType <em>DL Predefined Step Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see rsldl.DLPredefinedStepType
	 * @see rsldl.impl.RsldlPackageImpl#getDLPredefinedStepType()
	 * @generated
	 */
	int DL_PREDEFINED_STEP_TYPE = 46;

	/**
	 * Returns the meta object for class '{@link rsldl.DLDomain <em>DL Domain</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>DL Domain</em>'.
	 * @see rsldl.DLDomain
	 * @generated
	 */
	EClass getDLDomain();

	/**
	 * Returns the meta object for the attribute '{@link rsldl.DLDomain#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see rsldl.DLDomain#getName()
	 * @see #getDLDomain()
	 * @generated
	 */
	EAttribute getDLDomain_Name();

	/**
	 * Returns the meta object for the reference list '{@link rsldl.DLDomain#getElements <em>Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Elements</em>'.
	 * @see rsldl.DLDomain#getElements()
	 * @see #getDLDomain()
	 * @generated
	 */
	EReference getDLDomain_Elements();

	/**
	 * Returns the meta object for class '{@link rsldl.DLRelationshipParticipant <em>DL Relationship Participant</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>DL Relationship Participant</em>'.
	 * @see rsldl.DLRelationshipParticipant
	 * @generated
	 */
	EClass getDLRelationshipParticipant();

	/**
	 * Returns the meta object for the attribute '{@link rsldl.DLRelationshipParticipant#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see rsldl.DLRelationshipParticipant#getName()
	 * @see #getDLRelationshipParticipant()
	 * @generated
	 */
	EAttribute getDLRelationshipParticipant_Name();

	/**
	 * Returns the meta object for class '{@link rsldl.DLNotion <em>DL Notion</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>DL Notion</em>'.
	 * @see rsldl.DLNotion
	 * @generated
	 */
	EClass getDLNotion();

	/**
	 * Returns the meta object for the attribute '{@link rsldl.DLNotion#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see rsldl.DLNotion#getType()
	 * @see #getDLNotion()
	 * @generated
	 */
	EAttribute getDLNotion_Type();

	/**
	 * Returns the meta object for the containment reference list '{@link rsldl.DLNotion#getFeatures <em>Features</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Features</em>'.
	 * @see rsldl.DLNotion#getFeatures()
	 * @see #getDLNotion()
	 * @generated
	 */
	EReference getDLNotion_Features();

	/**
	 * Returns the meta object for the containment reference list '{@link rsldl.DLNotion#getDereferences <em>Dereferences</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Dereferences</em>'.
	 * @see rsldl.DLNotion#getDereferences()
	 * @see #getDLNotion()
	 * @generated
	 */
	EReference getDLNotion_Dereferences();

	/**
	 * Returns the meta object for the attribute '{@link rsldl.DLNotion#isDerived <em>Derived</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Derived</em>'.
	 * @see rsldl.DLNotion#isDerived()
	 * @see #getDLNotion()
	 * @generated
	 */
	EAttribute getDLNotion_Derived();

	/**
	 * Returns the meta object for class '{@link rsldl.DLPrimitive <em>DL Primitive</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>DL Primitive</em>'.
	 * @see rsldl.DLPrimitive
	 * @generated
	 */
	EClass getDLPrimitive();

	/**
	 * Returns the meta object for class '{@link rsldl.DLRelationship <em>DL Relationship</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>DL Relationship</em>'.
	 * @see rsldl.DLRelationship
	 * @generated
	 */
	EClass getDLRelationship();

	/**
	 * Returns the meta object for the containment reference list '{@link rsldl.DLRelationship#getParticipations <em>Participations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Participations</em>'.
	 * @see rsldl.DLRelationship#getParticipations()
	 * @see #getDLRelationship()
	 * @generated
	 */
	EReference getDLRelationship_Participations();

	/**
	 * Returns the meta object for the attribute '{@link rsldl.DLRelationship#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see rsldl.DLRelationship#getName()
	 * @see #getDLRelationship()
	 * @generated
	 */
	EAttribute getDLRelationship_Name();

	/**
	 * Returns the meta object for the attribute '{@link rsldl.DLRelationship#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see rsldl.DLRelationship#getType()
	 * @see #getDLRelationship()
	 * @generated
	 */
	EAttribute getDLRelationship_Type();

	/**
	 * Returns the meta object for the attribute '{@link rsldl.DLRelationship#getAbbreviation <em>Abbreviation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Abbreviation</em>'.
	 * @see rsldl.DLRelationship#getAbbreviation()
	 * @see #getDLRelationship()
	 * @generated
	 */
	EAttribute getDLRelationship_Abbreviation();

	/**
	 * Returns the meta object for class '{@link rsldl.DLRelationshipParticipation <em>DL Relationship Participation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>DL Relationship Participation</em>'.
	 * @see rsldl.DLRelationshipParticipation
	 * @generated
	 */
	EClass getDLRelationshipParticipation();

	/**
	 * Returns the meta object for the attribute '{@link rsldl.DLRelationshipParticipation#getDirection <em>Direction</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Direction</em>'.
	 * @see rsldl.DLRelationshipParticipation#getDirection()
	 * @see #getDLRelationshipParticipation()
	 * @generated
	 */
	EAttribute getDLRelationshipParticipation_Direction();

	/**
	 * Returns the meta object for the attribute '{@link rsldl.DLRelationshipParticipation#getMultiplicity <em>Multiplicity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Multiplicity</em>'.
	 * @see rsldl.DLRelationshipParticipation#getMultiplicity()
	 * @see #getDLRelationshipParticipation()
	 * @generated
	 */
	EAttribute getDLRelationshipParticipation_Multiplicity();

	/**
	 * Returns the meta object for the reference '{@link rsldl.DLRelationshipParticipation#getParticipant <em>Participant</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Participant</em>'.
	 * @see rsldl.DLRelationshipParticipation#getParticipant()
	 * @see #getDLRelationshipParticipation()
	 * @generated
	 */
	EReference getDLRelationshipParticipation_Participant();

	/**
	 * Returns the meta object for the container reference '{@link rsldl.DLRelationshipParticipation#getRelationship <em>Relationship</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Relationship</em>'.
	 * @see rsldl.DLRelationshipParticipation#getRelationship()
	 * @see #getDLRelationshipParticipation()
	 * @generated
	 */
	EReference getDLRelationshipParticipation_Relationship();

	/**
	 * Returns the meta object for the reference '{@link rsldl.DLRelationshipParticipation#getParentParticipation <em>Parent Participation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Parent Participation</em>'.
	 * @see rsldl.DLRelationshipParticipation#getParentParticipation()
	 * @see #getDLRelationshipParticipation()
	 * @generated
	 */
	EReference getDLRelationshipParticipation_ParentParticipation();

	/**
	 * Returns the meta object for the attribute '{@link rsldl.DLRelationshipParticipation#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see rsldl.DLRelationshipParticipation#getType()
	 * @see #getDLRelationshipParticipation()
	 * @generated
	 */
	EAttribute getDLRelationshipParticipation_Type();

	/**
	 * Returns the meta object for class '{@link rsldl.DLDiagram <em>DL Diagram</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>DL Diagram</em>'.
	 * @see rsldl.DLDiagram
	 * @generated
	 */
	EClass getDLDiagram();

	/**
	 * Returns the meta object for the containment reference list '{@link rsldl.DLDiagram#getRelationshipParticipants <em>Relationship Participants</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Relationship Participants</em>'.
	 * @see rsldl.DLDiagram#getRelationshipParticipants()
	 * @see #getDLDiagram()
	 * @generated
	 */
	EReference getDLDiagram_RelationshipParticipants();

	/**
	 * Returns the meta object for the containment reference list '{@link rsldl.DLDiagram#getRelationships <em>Relationships</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Relationships</em>'.
	 * @see rsldl.DLDiagram#getRelationships()
	 * @see #getDLDiagram()
	 * @generated
	 */
	EReference getDLDiagram_Relationships();

	/**
	 * Returns the meta object for the attribute '{@link rsldl.DLDiagram#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see rsldl.DLDiagram#getName()
	 * @see #getDLDiagram()
	 * @generated
	 */
	EAttribute getDLDiagram_Name();

	/**
	 * Returns the meta object for class '{@link rsldl.DLReference <em>DL Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>DL Reference</em>'.
	 * @see rsldl.DLReference
	 * @generated
	 */
	EClass getDLReference();

	/**
	 * Returns the meta object for class '{@link rsldl.DLTransition <em>DL Transition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>DL Transition</em>'.
	 * @see rsldl.DLTransition
	 * @generated
	 */
	EClass getDLTransition();

	/**
	 * Returns the meta object for class '{@link rsldl.DLAlghoritmicTransition <em>DL Alghoritmic Transition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>DL Alghoritmic Transition</em>'.
	 * @see rsldl.DLAlghoritmicTransition
	 * @generated
	 */
	EClass getDLAlghoritmicTransition();

	/**
	 * Returns the meta object for the containment reference '{@link rsldl.DLAlghoritmicTransition#getSequence <em>Sequence</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Sequence</em>'.
	 * @see rsldl.DLAlghoritmicTransition#getSequence()
	 * @see #getDLAlghoritmicTransition()
	 * @generated
	 */
	EReference getDLAlghoritmicTransition_Sequence();

	/**
	 * Returns the meta object for class '{@link rsldl.DLPatternBasedTransition <em>DL Pattern Based Transition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>DL Pattern Based Transition</em>'.
	 * @see rsldl.DLPatternBasedTransition
	 * @generated
	 */
	EClass getDLPatternBasedTransition();

	/**
	 * Returns the meta object for the containment reference '{@link rsldl.DLPatternBasedTransition#getPattern <em>Pattern</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Pattern</em>'.
	 * @see rsldl.DLPatternBasedTransition#getPattern()
	 * @see #getDLPatternBasedTransition()
	 * @generated
	 */
	EReference getDLPatternBasedTransition_Pattern();

	/**
	 * Returns the meta object for class '{@link rsldl.DLPatternBasedReference <em>DL Pattern Based Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>DL Pattern Based Reference</em>'.
	 * @see rsldl.DLPatternBasedReference
	 * @generated
	 */
	EClass getDLPatternBasedReference();

	/**
	 * Returns the meta object for the containment reference '{@link rsldl.DLPatternBasedReference#getPattern <em>Pattern</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Pattern</em>'.
	 * @see rsldl.DLPatternBasedReference#getPattern()
	 * @see #getDLPatternBasedReference()
	 * @generated
	 */
	EReference getDLPatternBasedReference_Pattern();

	/**
	 * Returns the meta object for class '{@link rsldl.DLDataBasedReference <em>DL Data Based Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>DL Data Based Reference</em>'.
	 * @see rsldl.DLDataBasedReference
	 * @generated
	 */
	EClass getDLDataBasedReference();

	/**
	 * Returns the meta object for class '{@link rsldl.DLEntity <em>DL Entity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>DL Entity</em>'.
	 * @see rsldl.DLEntity
	 * @generated
	 */
	EClass getDLEntity();

	/**
	 * Returns the meta object for class '{@link rsldl.DLProperty <em>DL Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>DL Property</em>'.
	 * @see rsldl.DLProperty
	 * @generated
	 */
	EClass getDLProperty();

	/**
	 * Returns the meta object for the attribute '{@link rsldl.DLProperty#getValueType <em>Value Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value Type</em>'.
	 * @see rsldl.DLProperty#getValueType()
	 * @see #getDLProperty()
	 * @generated
	 */
	EAttribute getDLProperty_ValueType();

	/**
	 * Returns the meta object for the reference '{@link rsldl.DLProperty#getSetType <em>Set Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Set Type</em>'.
	 * @see rsldl.DLProperty#getSetType()
	 * @see #getDLProperty()
	 * @generated
	 */
	EReference getDLProperty_SetType();

	/**
	 * Returns the meta object for class '{@link rsldl.DLFeature <em>DL Feature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>DL Feature</em>'.
	 * @see rsldl.DLFeature
	 * @generated
	 */
	EClass getDLFeature();

	/**
	 * Returns the meta object for the container reference '{@link rsldl.DLFeature#getNotion <em>Notion</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Notion</em>'.
	 * @see rsldl.DLFeature#getNotion()
	 * @see #getDLFeature()
	 * @generated
	 */
	EReference getDLFeature_Notion();

	/**
	 * Returns the meta object for class '{@link rsldl.DLCondition <em>DL Condition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>DL Condition</em>'.
	 * @see rsldl.DLCondition
	 * @generated
	 */
	EClass getDLCondition();

	/**
	 * Returns the meta object for the attribute '{@link rsldl.DLCondition#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see rsldl.DLCondition#getType()
	 * @see #getDLCondition()
	 * @generated
	 */
	EAttribute getDLCondition_Type();

	/**
	 * Returns the meta object for the attribute '{@link rsldl.DLCondition#getText <em>Text</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Text</em>'.
	 * @see rsldl.DLCondition#getText()
	 * @see #getDLCondition()
	 * @generated
	 */
	EAttribute getDLCondition_Text();

	/**
	 * Returns the meta object for class '{@link rsldl.DLAttributeLink <em>DL Attribute Link</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>DL Attribute Link</em>'.
	 * @see rsldl.DLAttributeLink
	 * @generated
	 */
	EClass getDLAttributeLink();

	/**
	 * Returns the meta object for the reference '{@link rsldl.DLAttributeLink#getAttribute <em>Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Attribute</em>'.
	 * @see rsldl.DLAttributeLink#getAttribute()
	 * @see #getDLAttributeLink()
	 * @generated
	 */
	EReference getDLAttributeLink_Attribute();

	/**
	 * Returns the meta object for the attribute '{@link rsldl.DLAttributeLink#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see rsldl.DLAttributeLink#getType()
	 * @see #getDLAttributeLink()
	 * @generated
	 */
	EAttribute getDLAttributeLink_Type();

	/**
	 * Returns the meta object for class '{@link rsldl.DLInheritanceCondition <em>DL Inheritance Condition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>DL Inheritance Condition</em>'.
	 * @see rsldl.DLInheritanceCondition
	 * @generated
	 */
	EClass getDLInheritanceCondition();

	/**
	 * Returns the meta object for the reference list '{@link rsldl.DLInheritanceCondition#getParents <em>Parents</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Parents</em>'.
	 * @see rsldl.DLInheritanceCondition#getParents()
	 * @see #getDLInheritanceCondition()
	 * @generated
	 */
	EReference getDLInheritanceCondition_Parents();

	/**
	 * Returns the meta object for the attribute '{@link rsldl.DLInheritanceCondition#getFeatureType <em>Feature Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Feature Type</em>'.
	 * @see rsldl.DLInheritanceCondition#getFeatureType()
	 * @see #getDLInheritanceCondition()
	 * @generated
	 */
	EAttribute getDLInheritanceCondition_FeatureType();

	/**
	 * Returns the meta object for class '{@link rsldl.DLIdentityCondition <em>DL Identity Condition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>DL Identity Condition</em>'.
	 * @see rsldl.DLIdentityCondition
	 * @generated
	 */
	EClass getDLIdentityCondition();

	/**
	 * Returns the meta object for class '{@link rsldl.DLValidityCondition <em>DL Validity Condition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>DL Validity Condition</em>'.
	 * @see rsldl.DLValidityCondition
	 * @generated
	 */
	EClass getDLValidityCondition();

	/**
	 * Returns the meta object for class '{@link rsldl.DLTransitionPattern <em>DL Transition Pattern</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>DL Transition Pattern</em>'.
	 * @see rsldl.DLTransitionPattern
	 * @generated
	 */
	EClass getDLTransitionPattern();

	/**
	 * Returns the meta object for the attribute '{@link rsldl.DLTransitionPattern#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see rsldl.DLTransitionPattern#getType()
	 * @see #getDLTransitionPattern()
	 * @generated
	 */
	EAttribute getDLTransitionPattern_Type();

	/**
	 * Returns the meta object for class '{@link rsldl.DLConditionPattern <em>DL Condition Pattern</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>DL Condition Pattern</em>'.
	 * @see rsldl.DLConditionPattern
	 * @generated
	 */
	EClass getDLConditionPattern();

	/**
	 * Returns the meta object for the attribute '{@link rsldl.DLConditionPattern#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see rsldl.DLConditionPattern#getType()
	 * @see #getDLConditionPattern()
	 * @generated
	 */
	EAttribute getDLConditionPattern_Type();

	/**
	 * Returns the meta object for class '{@link rsldl.DLPattern <em>DL Pattern</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>DL Pattern</em>'.
	 * @see rsldl.DLPattern
	 * @generated
	 */
	EClass getDLPattern();

	/**
	 * Returns the meta object for the attribute '{@link rsldl.DLPattern#getPattern <em>Pattern</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Pattern</em>'.
	 * @see rsldl.DLPattern#getPattern()
	 * @see #getDLPattern()
	 * @generated
	 */
	EAttribute getDLPattern_Pattern();

	/**
	 * Returns the meta object for class '{@link rsldl.DLSubset <em>DL Subset</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>DL Subset</em>'.
	 * @see rsldl.DLSubset
	 * @generated
	 */
	EClass getDLSubset();

	/**
	 * Returns the meta object for class '{@link rsldl.DLRepository <em>DL Repository</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>DL Repository</em>'.
	 * @see rsldl.DLRepository
	 * @generated
	 */
	EClass getDLRepository();

	/**
	 * Returns the meta object for the containment reference list '{@link rsldl.DLRepository#getDomains <em>Domains</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Domains</em>'.
	 * @see rsldl.DLRepository#getDomains()
	 * @see #getDLRepository()
	 * @generated
	 */
	EReference getDLRepository_Domains();

	/**
	 * Returns the meta object for the containment reference list '{@link rsldl.DLRepository#getDiagrams <em>Diagrams</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Diagrams</em>'.
	 * @see rsldl.DLRepository#getDiagrams()
	 * @see #getDLRepository()
	 * @generated
	 */
	EReference getDLRepository_Diagrams();

	/**
	 * Returns the meta object for class '{@link rsldl.DLDomainElement <em>DL Domain Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>DL Domain Element</em>'.
	 * @see rsldl.DLDomainElement
	 * @generated
	 */
	EClass getDLDomainElement();

	/**
	 * Returns the meta object for the reference list '{@link rsldl.DLDomainElement#getDomains <em>Domains</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Domains</em>'.
	 * @see rsldl.DLDomainElement#getDomains()
	 * @see #getDLDomainElement()
	 * @generated
	 */
	EReference getDLDomainElement_Domains();

	/**
	 * Returns the meta object for class '{@link rsldl.DLPatternCondition <em>DL Pattern Condition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>DL Pattern Condition</em>'.
	 * @see rsldl.DLPatternCondition
	 * @generated
	 */
	EClass getDLPatternCondition();

	/**
	 * Returns the meta object for the containment reference '{@link rsldl.DLPatternCondition#getPattern <em>Pattern</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Pattern</em>'.
	 * @see rsldl.DLPatternCondition#getPattern()
	 * @see #getDLPatternCondition()
	 * @generated
	 */
	EReference getDLPatternCondition_Pattern();

	/**
	 * Returns the meta object for class '{@link rsldl.DLAlghoritmicTransitionSequenceElement <em>DL Alghoritmic Transition Sequence Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>DL Alghoritmic Transition Sequence Element</em>'.
	 * @see rsldl.DLAlghoritmicTransitionSequenceElement
	 * @generated
	 */
	EClass getDLAlghoritmicTransitionSequenceElement();

	/**
	 * Returns the meta object for the containment reference '{@link rsldl.DLAlghoritmicTransitionSequenceElement#getSequent <em>Sequent</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Sequent</em>'.
	 * @see rsldl.DLAlghoritmicTransitionSequenceElement#getSequent()
	 * @see #getDLAlghoritmicTransitionSequenceElement()
	 * @generated
	 */
	EReference getDLAlghoritmicTransitionSequenceElement_Sequent();

	/**
	 * Returns the meta object for class '{@link rsldl.DLAlghoritmicTransitionStep <em>DL Alghoritmic Transition Step</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>DL Alghoritmic Transition Step</em>'.
	 * @see rsldl.DLAlghoritmicTransitionStep
	 * @generated
	 */
	EClass getDLAlghoritmicTransitionStep();

	/**
	 * Returns the meta object for class '{@link rsldl.DLNamedLink <em>DL Named Link</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>DL Named Link</em>'.
	 * @see rsldl.DLNamedLink
	 * @generated
	 */
	EClass getDLNamedLink();

	/**
	 * Returns the meta object for the attribute '{@link rsldl.DLNamedLink#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see rsldl.DLNamedLink#getName()
	 * @see #getDLNamedLink()
	 * @generated
	 */
	EAttribute getDLNamedLink_Name();

	/**
	 * Returns the meta object for class '{@link rsldl.DLDereferenceLink <em>DL Dereference Link</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>DL Dereference Link</em>'.
	 * @see rsldl.DLDereferenceLink
	 * @generated
	 */
	EClass getDLDereferenceLink();

	/**
	 * Returns the meta object for the reference '{@link rsldl.DLDereferenceLink#getElementType <em>Element Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Element Type</em>'.
	 * @see rsldl.DLDereferenceLink#getElementType()
	 * @see #getDLDereferenceLink()
	 * @generated
	 */
	EReference getDLDereferenceLink_ElementType();

	/**
	 * Returns the meta object for the attribute '{@link rsldl.DLDereferenceLink#getPattern <em>Pattern</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Pattern</em>'.
	 * @see rsldl.DLDereferenceLink#getPattern()
	 * @see #getDLDereferenceLink()
	 * @generated
	 */
	EAttribute getDLDereferenceLink_Pattern();

	/**
	 * Returns the meta object for class '{@link rsldl.DLCustomAlghoritmicTransitionStep <em>DL Custom Alghoritmic Transition Step</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>DL Custom Alghoritmic Transition Step</em>'.
	 * @see rsldl.DLCustomAlghoritmicTransitionStep
	 * @generated
	 */
	EClass getDLCustomAlghoritmicTransitionStep();

	/**
	 * Returns the meta object for the containment reference '{@link rsldl.DLCustomAlghoritmicTransitionStep#getAlternative <em>Alternative</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Alternative</em>'.
	 * @see rsldl.DLCustomAlghoritmicTransitionStep#getAlternative()
	 * @see #getDLCustomAlghoritmicTransitionStep()
	 * @generated
	 */
	EReference getDLCustomAlghoritmicTransitionStep_Alternative();

	/**
	 * Returns the meta object for the containment reference '{@link rsldl.DLCustomAlghoritmicTransitionStep#getAction <em>Action</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Action</em>'.
	 * @see rsldl.DLCustomAlghoritmicTransitionStep#getAction()
	 * @see #getDLCustomAlghoritmicTransitionStep()
	 * @generated
	 */
	EReference getDLCustomAlghoritmicTransitionStep_Action();

	/**
	 * Returns the meta object for the containment reference '{@link rsldl.DLCustomAlghoritmicTransitionStep#getCondition <em>Condition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Condition</em>'.
	 * @see rsldl.DLCustomAlghoritmicTransitionStep#getCondition()
	 * @see #getDLCustomAlghoritmicTransitionStep()
	 * @generated
	 */
	EReference getDLCustomAlghoritmicTransitionStep_Condition();

	/**
	 * Returns the meta object for class '{@link rsldl.DLPredefinedAlghoritmicTransitionStep <em>DL Predefined Alghoritmic Transition Step</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>DL Predefined Alghoritmic Transition Step</em>'.
	 * @see rsldl.DLPredefinedAlghoritmicTransitionStep
	 * @generated
	 */
	EClass getDLPredefinedAlghoritmicTransitionStep();

	/**
	 * Returns the meta object for the attribute '{@link rsldl.DLPredefinedAlghoritmicTransitionStep#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see rsldl.DLPredefinedAlghoritmicTransitionStep#getType()
	 * @see #getDLPredefinedAlghoritmicTransitionStep()
	 * @generated
	 */
	EAttribute getDLPredefinedAlghoritmicTransitionStep_Type();

	/**
	 * Returns the meta object for the attribute '{@link rsldl.DLPredefinedAlghoritmicTransitionStep#getPattern <em>Pattern</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Pattern</em>'.
	 * @see rsldl.DLPredefinedAlghoritmicTransitionStep#getPattern()
	 * @see #getDLPredefinedAlghoritmicTransitionStep()
	 * @generated
	 */
	EAttribute getDLPredefinedAlghoritmicTransitionStep_Pattern();

	/**
	 * Returns the meta object for class '{@link rsldl.DLControlFlowStatement <em>DL Control Flow Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>DL Control Flow Statement</em>'.
	 * @see rsldl.DLControlFlowStatement
	 * @generated
	 */
	EClass getDLControlFlowStatement();

	/**
	 * Returns the meta object for the attribute '{@link rsldl.DLControlFlowStatement#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see rsldl.DLControlFlowStatement#getType()
	 * @see #getDLControlFlowStatement()
	 * @generated
	 */
	EAttribute getDLControlFlowStatement_Type();

	/**
	 * Returns the meta object for class '{@link rsldl.DLReferencingElement <em>DL Referencing Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>DL Referencing Element</em>'.
	 * @see rsldl.DLReferencingElement
	 * @generated
	 */
	EClass getDLReferencingElement();

	/**
	 * Returns the meta object for the reference '{@link rsldl.DLReferencingElement#getSubjectLink <em>Subject Link</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Subject Link</em>'.
	 * @see rsldl.DLReferencingElement#getSubjectLink()
	 * @see #getDLReferencingElement()
	 * @generated
	 */
	EReference getDLReferencingElement_SubjectLink();

	/**
	 * Returns the meta object for class '{@link rsldl.DLPartLink <em>DL Part Link</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>DL Part Link</em>'.
	 * @see rsldl.DLPartLink
	 * @generated
	 */
	EClass getDLPartLink();

	/**
	 * Returns the meta object for the reference '{@link rsldl.DLPartLink#getPartDereference <em>Part Dereference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Part Dereference</em>'.
	 * @see rsldl.DLPartLink#getPartDereference()
	 * @see #getDLPartLink()
	 * @generated
	 */
	EReference getDLPartLink_PartDereference();

	/**
	 * Returns the meta object for enum '{@link rsldl.DLTypeRole <em>DL Type Role</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>DL Type Role</em>'.
	 * @see rsldl.DLTypeRole
	 * @generated
	 */
	EEnum getDLTypeRole();

	/**
	 * Returns the meta object for enum '{@link rsldl.DLRelationshipParticipationDirection <em>DL Relationship Participation Direction</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>DL Relationship Participation Direction</em>'.
	 * @see rsldl.DLRelationshipParticipationDirection
	 * @generated
	 */
	EEnum getDLRelationshipParticipationDirection();

	/**
	 * Returns the meta object for enum '{@link rsldl.DLRelationshipParticipationMultiplicity <em>DL Relationship Participation Multiplicity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>DL Relationship Participation Multiplicity</em>'.
	 * @see rsldl.DLRelationshipParticipationMultiplicity
	 * @generated
	 */
	EEnum getDLRelationshipParticipationMultiplicity();

	/**
	 * Returns the meta object for enum '{@link rsldl.DLPropertyValueType <em>DL Property Value Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>DL Property Value Type</em>'.
	 * @see rsldl.DLPropertyValueType
	 * @generated
	 */
	EEnum getDLPropertyValueType();

	/**
	 * Returns the meta object for enum '{@link rsldl.DLTransitionPatternType <em>DL Transition Pattern Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>DL Transition Pattern Type</em>'.
	 * @see rsldl.DLTransitionPatternType
	 * @generated
	 */
	EEnum getDLTransitionPatternType();

	/**
	 * Returns the meta object for enum '{@link rsldl.DLConditionPatternType <em>DL Condition Pattern Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>DL Condition Pattern Type</em>'.
	 * @see rsldl.DLConditionPatternType
	 * @generated
	 */
	EEnum getDLConditionPatternType();

	/**
	 * Returns the meta object for enum '{@link rsldl.DLFeatureType <em>DL Feature Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>DL Feature Type</em>'.
	 * @see rsldl.DLFeatureType
	 * @generated
	 */
	EEnum getDLFeatureType();

	/**
	 * Returns the meta object for enum '{@link rsldl.DLRelationshipParticipationType <em>DL Relationship Participation Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>DL Relationship Participation Type</em>'.
	 * @see rsldl.DLRelationshipParticipationType
	 * @generated
	 */
	EEnum getDLRelationshipParticipationType();

	/**
	 * Returns the meta object for enum '{@link rsldl.DLControlFlowStatementType <em>DL Control Flow Statement Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>DL Control Flow Statement Type</em>'.
	 * @see rsldl.DLControlFlowStatementType
	 * @generated
	 */
	EEnum getDLControlFlowStatementType();

	/**
	 * Returns the meta object for enum '{@link rsldl.DLPredefinedStepType <em>DL Predefined Step Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>DL Predefined Step Type</em>'.
	 * @see rsldl.DLPredefinedStepType
	 * @generated
	 */
	EEnum getDLPredefinedStepType();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	RsldlFactory getRsldlFactory();

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
		 * The meta object literal for the '{@link rsldl.impl.DLDomainImpl <em>DL Domain</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see rsldl.impl.DLDomainImpl
		 * @see rsldl.impl.RsldlPackageImpl#getDLDomain()
		 * @generated
		 */
		EClass DL_DOMAIN = eINSTANCE.getDLDomain();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DL_DOMAIN__NAME = eINSTANCE.getDLDomain_Name();

		/**
		 * The meta object literal for the '<em><b>Elements</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DL_DOMAIN__ELEMENTS = eINSTANCE.getDLDomain_Elements();

		/**
		 * The meta object literal for the '{@link rsldl.impl.DLRelationshipParticipantImpl <em>DL Relationship Participant</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see rsldl.impl.DLRelationshipParticipantImpl
		 * @see rsldl.impl.RsldlPackageImpl#getDLRelationshipParticipant()
		 * @generated
		 */
		EClass DL_RELATIONSHIP_PARTICIPANT = eINSTANCE.getDLRelationshipParticipant();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DL_RELATIONSHIP_PARTICIPANT__NAME = eINSTANCE.getDLRelationshipParticipant_Name();

		/**
		 * The meta object literal for the '{@link rsldl.impl.DLNotionImpl <em>DL Notion</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see rsldl.impl.DLNotionImpl
		 * @see rsldl.impl.RsldlPackageImpl#getDLNotion()
		 * @generated
		 */
		EClass DL_NOTION = eINSTANCE.getDLNotion();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DL_NOTION__TYPE = eINSTANCE.getDLNotion_Type();

		/**
		 * The meta object literal for the '<em><b>Features</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DL_NOTION__FEATURES = eINSTANCE.getDLNotion_Features();

		/**
		 * The meta object literal for the '<em><b>Dereferences</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DL_NOTION__DEREFERENCES = eINSTANCE.getDLNotion_Dereferences();

		/**
		 * The meta object literal for the '<em><b>Derived</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DL_NOTION__DERIVED = eINSTANCE.getDLNotion_Derived();

		/**
		 * The meta object literal for the '{@link rsldl.impl.DLPrimitiveImpl <em>DL Primitive</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see rsldl.impl.DLPrimitiveImpl
		 * @see rsldl.impl.RsldlPackageImpl#getDLPrimitive()
		 * @generated
		 */
		EClass DL_PRIMITIVE = eINSTANCE.getDLPrimitive();

		/**
		 * The meta object literal for the '{@link rsldl.impl.DLRelationshipImpl <em>DL Relationship</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see rsldl.impl.DLRelationshipImpl
		 * @see rsldl.impl.RsldlPackageImpl#getDLRelationship()
		 * @generated
		 */
		EClass DL_RELATIONSHIP = eINSTANCE.getDLRelationship();

		/**
		 * The meta object literal for the '<em><b>Participations</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DL_RELATIONSHIP__PARTICIPATIONS = eINSTANCE.getDLRelationship_Participations();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DL_RELATIONSHIP__NAME = eINSTANCE.getDLRelationship_Name();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DL_RELATIONSHIP__TYPE = eINSTANCE.getDLRelationship_Type();

		/**
		 * The meta object literal for the '<em><b>Abbreviation</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DL_RELATIONSHIP__ABBREVIATION = eINSTANCE.getDLRelationship_Abbreviation();

		/**
		 * The meta object literal for the '{@link rsldl.impl.DLRelationshipParticipationImpl <em>DL Relationship Participation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see rsldl.impl.DLRelationshipParticipationImpl
		 * @see rsldl.impl.RsldlPackageImpl#getDLRelationshipParticipation()
		 * @generated
		 */
		EClass DL_RELATIONSHIP_PARTICIPATION = eINSTANCE.getDLRelationshipParticipation();

		/**
		 * The meta object literal for the '<em><b>Direction</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DL_RELATIONSHIP_PARTICIPATION__DIRECTION = eINSTANCE.getDLRelationshipParticipation_Direction();

		/**
		 * The meta object literal for the '<em><b>Multiplicity</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DL_RELATIONSHIP_PARTICIPATION__MULTIPLICITY = eINSTANCE.getDLRelationshipParticipation_Multiplicity();

		/**
		 * The meta object literal for the '<em><b>Participant</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DL_RELATIONSHIP_PARTICIPATION__PARTICIPANT = eINSTANCE.getDLRelationshipParticipation_Participant();

		/**
		 * The meta object literal for the '<em><b>Relationship</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DL_RELATIONSHIP_PARTICIPATION__RELATIONSHIP = eINSTANCE.getDLRelationshipParticipation_Relationship();

		/**
		 * The meta object literal for the '<em><b>Parent Participation</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DL_RELATIONSHIP_PARTICIPATION__PARENT_PARTICIPATION = eINSTANCE.getDLRelationshipParticipation_ParentParticipation();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DL_RELATIONSHIP_PARTICIPATION__TYPE = eINSTANCE.getDLRelationshipParticipation_Type();

		/**
		 * The meta object literal for the '{@link rsldl.impl.DLDiagramImpl <em>DL Diagram</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see rsldl.impl.DLDiagramImpl
		 * @see rsldl.impl.RsldlPackageImpl#getDLDiagram()
		 * @generated
		 */
		EClass DL_DIAGRAM = eINSTANCE.getDLDiagram();

		/**
		 * The meta object literal for the '<em><b>Relationship Participants</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DL_DIAGRAM__RELATIONSHIP_PARTICIPANTS = eINSTANCE.getDLDiagram_RelationshipParticipants();

		/**
		 * The meta object literal for the '<em><b>Relationships</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DL_DIAGRAM__RELATIONSHIPS = eINSTANCE.getDLDiagram_Relationships();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DL_DIAGRAM__NAME = eINSTANCE.getDLDiagram_Name();

		/**
		 * The meta object literal for the '{@link rsldl.impl.DLReferenceImpl <em>DL Reference</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see rsldl.impl.DLReferenceImpl
		 * @see rsldl.impl.RsldlPackageImpl#getDLReference()
		 * @generated
		 */
		EClass DL_REFERENCE = eINSTANCE.getDLReference();

		/**
		 * The meta object literal for the '{@link rsldl.impl.DLTransitionImpl <em>DL Transition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see rsldl.impl.DLTransitionImpl
		 * @see rsldl.impl.RsldlPackageImpl#getDLTransition()
		 * @generated
		 */
		EClass DL_TRANSITION = eINSTANCE.getDLTransition();

		/**
		 * The meta object literal for the '{@link rsldl.impl.DLAlghoritmicTransitionImpl <em>DL Alghoritmic Transition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see rsldl.impl.DLAlghoritmicTransitionImpl
		 * @see rsldl.impl.RsldlPackageImpl#getDLAlghoritmicTransition()
		 * @generated
		 */
		EClass DL_ALGHORITMIC_TRANSITION = eINSTANCE.getDLAlghoritmicTransition();

		/**
		 * The meta object literal for the '<em><b>Sequence</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DL_ALGHORITMIC_TRANSITION__SEQUENCE = eINSTANCE.getDLAlghoritmicTransition_Sequence();

		/**
		 * The meta object literal for the '{@link rsldl.impl.DLPatternBasedTransitionImpl <em>DL Pattern Based Transition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see rsldl.impl.DLPatternBasedTransitionImpl
		 * @see rsldl.impl.RsldlPackageImpl#getDLPatternBasedTransition()
		 * @generated
		 */
		EClass DL_PATTERN_BASED_TRANSITION = eINSTANCE.getDLPatternBasedTransition();

		/**
		 * The meta object literal for the '<em><b>Pattern</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DL_PATTERN_BASED_TRANSITION__PATTERN = eINSTANCE.getDLPatternBasedTransition_Pattern();

		/**
		 * The meta object literal for the '{@link rsldl.impl.DLPatternBasedReferenceImpl <em>DL Pattern Based Reference</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see rsldl.impl.DLPatternBasedReferenceImpl
		 * @see rsldl.impl.RsldlPackageImpl#getDLPatternBasedReference()
		 * @generated
		 */
		EClass DL_PATTERN_BASED_REFERENCE = eINSTANCE.getDLPatternBasedReference();

		/**
		 * The meta object literal for the '<em><b>Pattern</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DL_PATTERN_BASED_REFERENCE__PATTERN = eINSTANCE.getDLPatternBasedReference_Pattern();

		/**
		 * The meta object literal for the '{@link rsldl.impl.DLDataBasedReferenceImpl <em>DL Data Based Reference</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see rsldl.impl.DLDataBasedReferenceImpl
		 * @see rsldl.impl.RsldlPackageImpl#getDLDataBasedReference()
		 * @generated
		 */
		EClass DL_DATA_BASED_REFERENCE = eINSTANCE.getDLDataBasedReference();

		/**
		 * The meta object literal for the '{@link rsldl.impl.DLEntityImpl <em>DL Entity</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see rsldl.impl.DLEntityImpl
		 * @see rsldl.impl.RsldlPackageImpl#getDLEntity()
		 * @generated
		 */
		EClass DL_ENTITY = eINSTANCE.getDLEntity();

		/**
		 * The meta object literal for the '{@link rsldl.impl.DLPropertyImpl <em>DL Property</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see rsldl.impl.DLPropertyImpl
		 * @see rsldl.impl.RsldlPackageImpl#getDLProperty()
		 * @generated
		 */
		EClass DL_PROPERTY = eINSTANCE.getDLProperty();

		/**
		 * The meta object literal for the '<em><b>Value Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DL_PROPERTY__VALUE_TYPE = eINSTANCE.getDLProperty_ValueType();

		/**
		 * The meta object literal for the '<em><b>Set Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DL_PROPERTY__SET_TYPE = eINSTANCE.getDLProperty_SetType();

		/**
		 * The meta object literal for the '{@link rsldl.impl.DLFeatureImpl <em>DL Feature</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see rsldl.impl.DLFeatureImpl
		 * @see rsldl.impl.RsldlPackageImpl#getDLFeature()
		 * @generated
		 */
		EClass DL_FEATURE = eINSTANCE.getDLFeature();

		/**
		 * The meta object literal for the '<em><b>Notion</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DL_FEATURE__NOTION = eINSTANCE.getDLFeature_Notion();

		/**
		 * The meta object literal for the '{@link rsldl.impl.DLConditionImpl <em>DL Condition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see rsldl.impl.DLConditionImpl
		 * @see rsldl.impl.RsldlPackageImpl#getDLCondition()
		 * @generated
		 */
		EClass DL_CONDITION = eINSTANCE.getDLCondition();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DL_CONDITION__TYPE = eINSTANCE.getDLCondition_Type();

		/**
		 * The meta object literal for the '<em><b>Text</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DL_CONDITION__TEXT = eINSTANCE.getDLCondition_Text();

		/**
		 * The meta object literal for the '{@link rsldl.impl.DLAttributeLinkImpl <em>DL Attribute Link</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see rsldl.impl.DLAttributeLinkImpl
		 * @see rsldl.impl.RsldlPackageImpl#getDLAttributeLink()
		 * @generated
		 */
		EClass DL_ATTRIBUTE_LINK = eINSTANCE.getDLAttributeLink();

		/**
		 * The meta object literal for the '<em><b>Attribute</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DL_ATTRIBUTE_LINK__ATTRIBUTE = eINSTANCE.getDLAttributeLink_Attribute();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DL_ATTRIBUTE_LINK__TYPE = eINSTANCE.getDLAttributeLink_Type();

		/**
		 * The meta object literal for the '{@link rsldl.impl.DLInheritanceConditionImpl <em>DL Inheritance Condition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see rsldl.impl.DLInheritanceConditionImpl
		 * @see rsldl.impl.RsldlPackageImpl#getDLInheritanceCondition()
		 * @generated
		 */
		EClass DL_INHERITANCE_CONDITION = eINSTANCE.getDLInheritanceCondition();

		/**
		 * The meta object literal for the '<em><b>Parents</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DL_INHERITANCE_CONDITION__PARENTS = eINSTANCE.getDLInheritanceCondition_Parents();

		/**
		 * The meta object literal for the '<em><b>Feature Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DL_INHERITANCE_CONDITION__FEATURE_TYPE = eINSTANCE.getDLInheritanceCondition_FeatureType();

		/**
		 * The meta object literal for the '{@link rsldl.impl.DLIdentityConditionImpl <em>DL Identity Condition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see rsldl.impl.DLIdentityConditionImpl
		 * @see rsldl.impl.RsldlPackageImpl#getDLIdentityCondition()
		 * @generated
		 */
		EClass DL_IDENTITY_CONDITION = eINSTANCE.getDLIdentityCondition();

		/**
		 * The meta object literal for the '{@link rsldl.impl.DLValidityConditionImpl <em>DL Validity Condition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see rsldl.impl.DLValidityConditionImpl
		 * @see rsldl.impl.RsldlPackageImpl#getDLValidityCondition()
		 * @generated
		 */
		EClass DL_VALIDITY_CONDITION = eINSTANCE.getDLValidityCondition();

		/**
		 * The meta object literal for the '{@link rsldl.impl.DLTransitionPatternImpl <em>DL Transition Pattern</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see rsldl.impl.DLTransitionPatternImpl
		 * @see rsldl.impl.RsldlPackageImpl#getDLTransitionPattern()
		 * @generated
		 */
		EClass DL_TRANSITION_PATTERN = eINSTANCE.getDLTransitionPattern();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DL_TRANSITION_PATTERN__TYPE = eINSTANCE.getDLTransitionPattern_Type();

		/**
		 * The meta object literal for the '{@link rsldl.impl.DLConditionPatternImpl <em>DL Condition Pattern</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see rsldl.impl.DLConditionPatternImpl
		 * @see rsldl.impl.RsldlPackageImpl#getDLConditionPattern()
		 * @generated
		 */
		EClass DL_CONDITION_PATTERN = eINSTANCE.getDLConditionPattern();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DL_CONDITION_PATTERN__TYPE = eINSTANCE.getDLConditionPattern_Type();

		/**
		 * The meta object literal for the '{@link rsldl.impl.DLPatternImpl <em>DL Pattern</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see rsldl.impl.DLPatternImpl
		 * @see rsldl.impl.RsldlPackageImpl#getDLPattern()
		 * @generated
		 */
		EClass DL_PATTERN = eINSTANCE.getDLPattern();

		/**
		 * The meta object literal for the '<em><b>Pattern</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DL_PATTERN__PATTERN = eINSTANCE.getDLPattern_Pattern();

		/**
		 * The meta object literal for the '{@link rsldl.impl.DLSubsetImpl <em>DL Subset</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see rsldl.impl.DLSubsetImpl
		 * @see rsldl.impl.RsldlPackageImpl#getDLSubset()
		 * @generated
		 */
		EClass DL_SUBSET = eINSTANCE.getDLSubset();

		/**
		 * The meta object literal for the '{@link rsldl.impl.DLRepositoryImpl <em>DL Repository</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see rsldl.impl.DLRepositoryImpl
		 * @see rsldl.impl.RsldlPackageImpl#getDLRepository()
		 * @generated
		 */
		EClass DL_REPOSITORY = eINSTANCE.getDLRepository();

		/**
		 * The meta object literal for the '<em><b>Domains</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DL_REPOSITORY__DOMAINS = eINSTANCE.getDLRepository_Domains();

		/**
		 * The meta object literal for the '<em><b>Diagrams</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DL_REPOSITORY__DIAGRAMS = eINSTANCE.getDLRepository_Diagrams();

		/**
		 * The meta object literal for the '{@link rsldl.impl.DLDomainElementImpl <em>DL Domain Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see rsldl.impl.DLDomainElementImpl
		 * @see rsldl.impl.RsldlPackageImpl#getDLDomainElement()
		 * @generated
		 */
		EClass DL_DOMAIN_ELEMENT = eINSTANCE.getDLDomainElement();

		/**
		 * The meta object literal for the '<em><b>Domains</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DL_DOMAIN_ELEMENT__DOMAINS = eINSTANCE.getDLDomainElement_Domains();

		/**
		 * The meta object literal for the '{@link rsldl.impl.DLPatternConditionImpl <em>DL Pattern Condition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see rsldl.impl.DLPatternConditionImpl
		 * @see rsldl.impl.RsldlPackageImpl#getDLPatternCondition()
		 * @generated
		 */
		EClass DL_PATTERN_CONDITION = eINSTANCE.getDLPatternCondition();

		/**
		 * The meta object literal for the '<em><b>Pattern</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DL_PATTERN_CONDITION__PATTERN = eINSTANCE.getDLPatternCondition_Pattern();

		/**
		 * The meta object literal for the '{@link rsldl.impl.DLAlghoritmicTransitionSequenceElementImpl <em>DL Alghoritmic Transition Sequence Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see rsldl.impl.DLAlghoritmicTransitionSequenceElementImpl
		 * @see rsldl.impl.RsldlPackageImpl#getDLAlghoritmicTransitionSequenceElement()
		 * @generated
		 */
		EClass DL_ALGHORITMIC_TRANSITION_SEQUENCE_ELEMENT = eINSTANCE.getDLAlghoritmicTransitionSequenceElement();

		/**
		 * The meta object literal for the '<em><b>Sequent</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DL_ALGHORITMIC_TRANSITION_SEQUENCE_ELEMENT__SEQUENT = eINSTANCE.getDLAlghoritmicTransitionSequenceElement_Sequent();

		/**
		 * The meta object literal for the '{@link rsldl.impl.DLAlghoritmicTransitionStepImpl <em>DL Alghoritmic Transition Step</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see rsldl.impl.DLAlghoritmicTransitionStepImpl
		 * @see rsldl.impl.RsldlPackageImpl#getDLAlghoritmicTransitionStep()
		 * @generated
		 */
		EClass DL_ALGHORITMIC_TRANSITION_STEP = eINSTANCE.getDLAlghoritmicTransitionStep();

		/**
		 * The meta object literal for the '{@link rsldl.impl.DLNamedLinkImpl <em>DL Named Link</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see rsldl.impl.DLNamedLinkImpl
		 * @see rsldl.impl.RsldlPackageImpl#getDLNamedLink()
		 * @generated
		 */
		EClass DL_NAMED_LINK = eINSTANCE.getDLNamedLink();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DL_NAMED_LINK__NAME = eINSTANCE.getDLNamedLink_Name();

		/**
		 * The meta object literal for the '{@link rsldl.impl.DLDereferenceLinkImpl <em>DL Dereference Link</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see rsldl.impl.DLDereferenceLinkImpl
		 * @see rsldl.impl.RsldlPackageImpl#getDLDereferenceLink()
		 * @generated
		 */
		EClass DL_DEREFERENCE_LINK = eINSTANCE.getDLDereferenceLink();

		/**
		 * The meta object literal for the '<em><b>Element Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DL_DEREFERENCE_LINK__ELEMENT_TYPE = eINSTANCE.getDLDereferenceLink_ElementType();

		/**
		 * The meta object literal for the '<em><b>Pattern</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DL_DEREFERENCE_LINK__PATTERN = eINSTANCE.getDLDereferenceLink_Pattern();

		/**
		 * The meta object literal for the '{@link rsldl.impl.DLCustomAlghoritmicTransitionStepImpl <em>DL Custom Alghoritmic Transition Step</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see rsldl.impl.DLCustomAlghoritmicTransitionStepImpl
		 * @see rsldl.impl.RsldlPackageImpl#getDLCustomAlghoritmicTransitionStep()
		 * @generated
		 */
		EClass DL_CUSTOM_ALGHORITMIC_TRANSITION_STEP = eINSTANCE.getDLCustomAlghoritmicTransitionStep();

		/**
		 * The meta object literal for the '<em><b>Alternative</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DL_CUSTOM_ALGHORITMIC_TRANSITION_STEP__ALTERNATIVE = eINSTANCE.getDLCustomAlghoritmicTransitionStep_Alternative();

		/**
		 * The meta object literal for the '<em><b>Action</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DL_CUSTOM_ALGHORITMIC_TRANSITION_STEP__ACTION = eINSTANCE.getDLCustomAlghoritmicTransitionStep_Action();

		/**
		 * The meta object literal for the '<em><b>Condition</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DL_CUSTOM_ALGHORITMIC_TRANSITION_STEP__CONDITION = eINSTANCE.getDLCustomAlghoritmicTransitionStep_Condition();

		/**
		 * The meta object literal for the '{@link rsldl.impl.DLPredefinedAlghoritmicTransitionStepImpl <em>DL Predefined Alghoritmic Transition Step</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see rsldl.impl.DLPredefinedAlghoritmicTransitionStepImpl
		 * @see rsldl.impl.RsldlPackageImpl#getDLPredefinedAlghoritmicTransitionStep()
		 * @generated
		 */
		EClass DL_PREDEFINED_ALGHORITMIC_TRANSITION_STEP = eINSTANCE.getDLPredefinedAlghoritmicTransitionStep();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DL_PREDEFINED_ALGHORITMIC_TRANSITION_STEP__TYPE = eINSTANCE.getDLPredefinedAlghoritmicTransitionStep_Type();

		/**
		 * The meta object literal for the '<em><b>Pattern</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DL_PREDEFINED_ALGHORITMIC_TRANSITION_STEP__PATTERN = eINSTANCE.getDLPredefinedAlghoritmicTransitionStep_Pattern();

		/**
		 * The meta object literal for the '{@link rsldl.impl.DLControlFlowStatementImpl <em>DL Control Flow Statement</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see rsldl.impl.DLControlFlowStatementImpl
		 * @see rsldl.impl.RsldlPackageImpl#getDLControlFlowStatement()
		 * @generated
		 */
		EClass DL_CONTROL_FLOW_STATEMENT = eINSTANCE.getDLControlFlowStatement();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DL_CONTROL_FLOW_STATEMENT__TYPE = eINSTANCE.getDLControlFlowStatement_Type();

		/**
		 * The meta object literal for the '{@link rsldl.impl.DLReferencingElementImpl <em>DL Referencing Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see rsldl.impl.DLReferencingElementImpl
		 * @see rsldl.impl.RsldlPackageImpl#getDLReferencingElement()
		 * @generated
		 */
		EClass DL_REFERENCING_ELEMENT = eINSTANCE.getDLReferencingElement();

		/**
		 * The meta object literal for the '<em><b>Subject Link</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DL_REFERENCING_ELEMENT__SUBJECT_LINK = eINSTANCE.getDLReferencingElement_SubjectLink();

		/**
		 * The meta object literal for the '{@link rsldl.impl.DLPartLinkImpl <em>DL Part Link</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see rsldl.impl.DLPartLinkImpl
		 * @see rsldl.impl.RsldlPackageImpl#getDLPartLink()
		 * @generated
		 */
		EClass DL_PART_LINK = eINSTANCE.getDLPartLink();

		/**
		 * The meta object literal for the '<em><b>Part Dereference</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DL_PART_LINK__PART_DEREFERENCE = eINSTANCE.getDLPartLink_PartDereference();

		/**
		 * The meta object literal for the '{@link rsldl.DLTypeRole <em>DL Type Role</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see rsldl.DLTypeRole
		 * @see rsldl.impl.RsldlPackageImpl#getDLTypeRole()
		 * @generated
		 */
		EEnum DL_TYPE_ROLE = eINSTANCE.getDLTypeRole();

		/**
		 * The meta object literal for the '{@link rsldl.DLRelationshipParticipationDirection <em>DL Relationship Participation Direction</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see rsldl.DLRelationshipParticipationDirection
		 * @see rsldl.impl.RsldlPackageImpl#getDLRelationshipParticipationDirection()
		 * @generated
		 */
		EEnum DL_RELATIONSHIP_PARTICIPATION_DIRECTION = eINSTANCE.getDLRelationshipParticipationDirection();

		/**
		 * The meta object literal for the '{@link rsldl.DLRelationshipParticipationMultiplicity <em>DL Relationship Participation Multiplicity</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see rsldl.DLRelationshipParticipationMultiplicity
		 * @see rsldl.impl.RsldlPackageImpl#getDLRelationshipParticipationMultiplicity()
		 * @generated
		 */
		EEnum DL_RELATIONSHIP_PARTICIPATION_MULTIPLICITY = eINSTANCE.getDLRelationshipParticipationMultiplicity();

		/**
		 * The meta object literal for the '{@link rsldl.DLPropertyValueType <em>DL Property Value Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see rsldl.DLPropertyValueType
		 * @see rsldl.impl.RsldlPackageImpl#getDLPropertyValueType()
		 * @generated
		 */
		EEnum DL_PROPERTY_VALUE_TYPE = eINSTANCE.getDLPropertyValueType();

		/**
		 * The meta object literal for the '{@link rsldl.DLTransitionPatternType <em>DL Transition Pattern Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see rsldl.DLTransitionPatternType
		 * @see rsldl.impl.RsldlPackageImpl#getDLTransitionPatternType()
		 * @generated
		 */
		EEnum DL_TRANSITION_PATTERN_TYPE = eINSTANCE.getDLTransitionPatternType();

		/**
		 * The meta object literal for the '{@link rsldl.DLConditionPatternType <em>DL Condition Pattern Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see rsldl.DLConditionPatternType
		 * @see rsldl.impl.RsldlPackageImpl#getDLConditionPatternType()
		 * @generated
		 */
		EEnum DL_CONDITION_PATTERN_TYPE = eINSTANCE.getDLConditionPatternType();

		/**
		 * The meta object literal for the '{@link rsldl.DLFeatureType <em>DL Feature Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see rsldl.DLFeatureType
		 * @see rsldl.impl.RsldlPackageImpl#getDLFeatureType()
		 * @generated
		 */
		EEnum DL_FEATURE_TYPE = eINSTANCE.getDLFeatureType();

		/**
		 * The meta object literal for the '{@link rsldl.DLRelationshipParticipationType <em>DL Relationship Participation Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see rsldl.DLRelationshipParticipationType
		 * @see rsldl.impl.RsldlPackageImpl#getDLRelationshipParticipationType()
		 * @generated
		 */
		EEnum DL_RELATIONSHIP_PARTICIPATION_TYPE = eINSTANCE.getDLRelationshipParticipationType();

		/**
		 * The meta object literal for the '{@link rsldl.DLControlFlowStatementType <em>DL Control Flow Statement Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see rsldl.DLControlFlowStatementType
		 * @see rsldl.impl.RsldlPackageImpl#getDLControlFlowStatementType()
		 * @generated
		 */
		EEnum DL_CONTROL_FLOW_STATEMENT_TYPE = eINSTANCE.getDLControlFlowStatementType();

		/**
		 * The meta object literal for the '{@link rsldl.DLPredefinedStepType <em>DL Predefined Step Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see rsldl.DLPredefinedStepType
		 * @see rsldl.impl.RsldlPackageImpl#getDLPredefinedStepType()
		 * @generated
		 */
		EEnum DL_PREDEFINED_STEP_TYPE = eINSTANCE.getDLPredefinedStepType();

	}

} //RsldlPackage
