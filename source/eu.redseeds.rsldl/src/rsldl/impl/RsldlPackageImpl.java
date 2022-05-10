/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package rsldl.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;

import rsldl.DLAlghoritmicTransition;
import rsldl.DLAlghoritmicTransitionSequenceElement;
import rsldl.DLAlghoritmicTransitionStep;
import rsldl.DLAttributeLink;
import rsldl.DLCondition;
import rsldl.DLConditionPattern;
import rsldl.DLConditionPatternType;
import rsldl.DLControlFlowStatement;
import rsldl.DLControlFlowStatementType;
import rsldl.DLCustomAlghoritmicTransitionStep;
import rsldl.DLDataBasedReference;
import rsldl.DLDereferenceLink;
import rsldl.DLDiagram;
import rsldl.DLDomain;
import rsldl.DLDomainElement;
import rsldl.DLEntity;
import rsldl.DLFeature;
import rsldl.DLFeatureType;
import rsldl.DLIdentityCondition;
import rsldl.DLInheritanceCondition;
import rsldl.DLNamedLink;
import rsldl.DLNotion;
import rsldl.DLPartLink;
import rsldl.DLPattern;
import rsldl.DLPatternBasedReference;
import rsldl.DLPatternBasedTransition;
import rsldl.DLPatternCondition;
import rsldl.DLPredefinedAlghoritmicTransitionStep;
import rsldl.DLPredefinedStepType;
import rsldl.DLPrimitive;
import rsldl.DLProperty;
import rsldl.DLPropertyValueType;
import rsldl.DLReference;
import rsldl.DLReferencingElement;
import rsldl.DLRelationship;
import rsldl.DLRelationshipParticipant;
import rsldl.DLRelationshipParticipation;
import rsldl.DLRelationshipParticipationDirection;
import rsldl.DLRelationshipParticipationMultiplicity;
import rsldl.DLRelationshipParticipationType;
import rsldl.DLRepository;
import rsldl.DLSubset;
import rsldl.DLTransition;
import rsldl.DLTransitionPattern;
import rsldl.DLTransitionPatternType;
import rsldl.DLTypeRole;
import rsldl.DLValidityCondition;
import rsldl.RsldlFactory;
import rsldl.RsldlPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class RsldlPackageImpl extends EPackageImpl implements RsldlPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dlDomainEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dlRelationshipParticipantEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dlNotionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dlPrimitiveEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dlRelationshipEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dlRelationshipParticipationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dlDiagramEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dlReferenceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dlTransitionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dlAlghoritmicTransitionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dlPatternBasedTransitionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dlPatternBasedReferenceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dlDataBasedReferenceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dlEntityEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dlPropertyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dlFeatureEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dlConditionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dlAttributeLinkEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dlInheritanceConditionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dlIdentityConditionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dlValidityConditionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dlTransitionPatternEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dlConditionPatternEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dlPatternEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dlSubsetEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dlRepositoryEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dlDomainElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dlPatternConditionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dlAlghoritmicTransitionSequenceElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dlAlghoritmicTransitionStepEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dlNamedLinkEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dlDereferenceLinkEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dlCustomAlghoritmicTransitionStepEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dlPredefinedAlghoritmicTransitionStepEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dlControlFlowStatementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dlReferencingElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dlPartLinkEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum dlTypeRoleEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum dlRelationshipParticipationDirectionEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum dlRelationshipParticipationMultiplicityEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum dlPropertyValueTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum dlTransitionPatternTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum dlConditionPatternTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum dlFeatureTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum dlRelationshipParticipationTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum dlControlFlowStatementTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum dlPredefinedStepTypeEEnum = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see rsldl.RsldlPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private RsldlPackageImpl() {
		super(eNS_URI, RsldlFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link RsldlPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static RsldlPackage init() {
		if (isInited) return (RsldlPackage)EPackage.Registry.INSTANCE.getEPackage(RsldlPackage.eNS_URI);

		// Obtain or create and register package
		RsldlPackageImpl theRsldlPackage = (RsldlPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof RsldlPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new RsldlPackageImpl());

		isInited = true;

		// Create package meta-data objects
		theRsldlPackage.createPackageContents();

		// Initialize created meta-data
		theRsldlPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theRsldlPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(RsldlPackage.eNS_URI, theRsldlPackage);
		return theRsldlPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDLDomain() {
		return dlDomainEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDLDomain_Name() {
		return (EAttribute)dlDomainEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDLDomain_Elements() {
		return (EReference)dlDomainEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDLRelationshipParticipant() {
		return dlRelationshipParticipantEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDLRelationshipParticipant_Name() {
		return (EAttribute)dlRelationshipParticipantEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDLNotion() {
		return dlNotionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDLNotion_Type() {
		return (EAttribute)dlNotionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDLNotion_Features() {
		return (EReference)dlNotionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDLNotion_Dereferences() {
		return (EReference)dlNotionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDLNotion_Derived() {
		return (EAttribute)dlNotionEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDLPrimitive() {
		return dlPrimitiveEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDLRelationship() {
		return dlRelationshipEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDLRelationship_Participations() {
		return (EReference)dlRelationshipEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDLRelationship_Name() {
		return (EAttribute)dlRelationshipEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDLRelationship_Type() {
		return (EAttribute)dlRelationshipEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDLRelationship_Abbreviation() {
		return (EAttribute)dlRelationshipEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDLRelationshipParticipation() {
		return dlRelationshipParticipationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDLRelationshipParticipation_Direction() {
		return (EAttribute)dlRelationshipParticipationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDLRelationshipParticipation_Multiplicity() {
		return (EAttribute)dlRelationshipParticipationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDLRelationshipParticipation_Participant() {
		return (EReference)dlRelationshipParticipationEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDLRelationshipParticipation_Relationship() {
		return (EReference)dlRelationshipParticipationEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDLRelationshipParticipation_ParentParticipation() {
		return (EReference)dlRelationshipParticipationEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDLRelationshipParticipation_Type() {
		return (EAttribute)dlRelationshipParticipationEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDLDiagram() {
		return dlDiagramEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDLDiagram_RelationshipParticipants() {
		return (EReference)dlDiagramEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDLDiagram_Relationships() {
		return (EReference)dlDiagramEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDLDiagram_Name() {
		return (EAttribute)dlDiagramEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDLReference() {
		return dlReferenceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDLTransition() {
		return dlTransitionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDLAlghoritmicTransition() {
		return dlAlghoritmicTransitionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDLAlghoritmicTransition_Sequence() {
		return (EReference)dlAlghoritmicTransitionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDLPatternBasedTransition() {
		return dlPatternBasedTransitionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDLPatternBasedTransition_Pattern() {
		return (EReference)dlPatternBasedTransitionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDLPatternBasedReference() {
		return dlPatternBasedReferenceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDLPatternBasedReference_Pattern() {
		return (EReference)dlPatternBasedReferenceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDLDataBasedReference() {
		return dlDataBasedReferenceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDLEntity() {
		return dlEntityEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDLProperty() {
		return dlPropertyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDLProperty_ValueType() {
		return (EAttribute)dlPropertyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDLProperty_SetType() {
		return (EReference)dlPropertyEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDLFeature() {
		return dlFeatureEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDLFeature_Notion() {
		return (EReference)dlFeatureEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDLCondition() {
		return dlConditionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDLCondition_Type() {
		return (EAttribute)dlConditionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDLCondition_Text() {
		return (EAttribute)dlConditionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDLAttributeLink() {
		return dlAttributeLinkEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDLAttributeLink_Attribute() {
		return (EReference)dlAttributeLinkEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDLAttributeLink_Type() {
		return (EAttribute)dlAttributeLinkEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDLInheritanceCondition() {
		return dlInheritanceConditionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDLInheritanceCondition_Parents() {
		return (EReference)dlInheritanceConditionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDLInheritanceCondition_FeatureType() {
		return (EAttribute)dlInheritanceConditionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDLIdentityCondition() {
		return dlIdentityConditionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDLValidityCondition() {
		return dlValidityConditionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDLTransitionPattern() {
		return dlTransitionPatternEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDLTransitionPattern_Type() {
		return (EAttribute)dlTransitionPatternEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDLConditionPattern() {
		return dlConditionPatternEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDLConditionPattern_Type() {
		return (EAttribute)dlConditionPatternEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDLPattern() {
		return dlPatternEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDLPattern_Pattern() {
		return (EAttribute)dlPatternEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDLSubset() {
		return dlSubsetEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDLRepository() {
		return dlRepositoryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDLRepository_Domains() {
		return (EReference)dlRepositoryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDLRepository_Diagrams() {
		return (EReference)dlRepositoryEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDLDomainElement() {
		return dlDomainElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDLDomainElement_Domains() {
		return (EReference)dlDomainElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDLPatternCondition() {
		return dlPatternConditionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDLPatternCondition_Pattern() {
		return (EReference)dlPatternConditionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDLAlghoritmicTransitionSequenceElement() {
		return dlAlghoritmicTransitionSequenceElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDLAlghoritmicTransitionSequenceElement_Sequent() {
		return (EReference)dlAlghoritmicTransitionSequenceElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDLAlghoritmicTransitionStep() {
		return dlAlghoritmicTransitionStepEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDLNamedLink() {
		return dlNamedLinkEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDLNamedLink_Name() {
		return (EAttribute)dlNamedLinkEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDLDereferenceLink() {
		return dlDereferenceLinkEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDLDereferenceLink_ElementType() {
		return (EReference)dlDereferenceLinkEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDLDereferenceLink_Pattern() {
		return (EAttribute)dlDereferenceLinkEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDLCustomAlghoritmicTransitionStep() {
		return dlCustomAlghoritmicTransitionStepEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDLCustomAlghoritmicTransitionStep_Alternative() {
		return (EReference)dlCustomAlghoritmicTransitionStepEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDLCustomAlghoritmicTransitionStep_Action() {
		return (EReference)dlCustomAlghoritmicTransitionStepEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDLCustomAlghoritmicTransitionStep_Condition() {
		return (EReference)dlCustomAlghoritmicTransitionStepEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDLPredefinedAlghoritmicTransitionStep() {
		return dlPredefinedAlghoritmicTransitionStepEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDLPredefinedAlghoritmicTransitionStep_Type() {
		return (EAttribute)dlPredefinedAlghoritmicTransitionStepEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDLPredefinedAlghoritmicTransitionStep_Pattern() {
		return (EAttribute)dlPredefinedAlghoritmicTransitionStepEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDLControlFlowStatement() {
		return dlControlFlowStatementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDLControlFlowStatement_Type() {
		return (EAttribute)dlControlFlowStatementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDLReferencingElement() {
		return dlReferencingElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDLReferencingElement_SubjectLink() {
		return (EReference)dlReferencingElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDLPartLink() {
		return dlPartLinkEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDLPartLink_PartDereference() {
		return (EReference)dlPartLinkEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getDLTypeRole() {
		return dlTypeRoleEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getDLRelationshipParticipationDirection() {
		return dlRelationshipParticipationDirectionEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getDLRelationshipParticipationMultiplicity() {
		return dlRelationshipParticipationMultiplicityEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getDLPropertyValueType() {
		return dlPropertyValueTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getDLTransitionPatternType() {
		return dlTransitionPatternTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getDLConditionPatternType() {
		return dlConditionPatternTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getDLFeatureType() {
		return dlFeatureTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getDLRelationshipParticipationType() {
		return dlRelationshipParticipationTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getDLControlFlowStatementType() {
		return dlControlFlowStatementTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getDLPredefinedStepType() {
		return dlPredefinedStepTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RsldlFactory getRsldlFactory() {
		return (RsldlFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		dlDomainEClass = createEClass(DL_DOMAIN);
		createEAttribute(dlDomainEClass, DL_DOMAIN__NAME);
		createEReference(dlDomainEClass, DL_DOMAIN__ELEMENTS);

		dlRelationshipParticipantEClass = createEClass(DL_RELATIONSHIP_PARTICIPANT);
		createEAttribute(dlRelationshipParticipantEClass, DL_RELATIONSHIP_PARTICIPANT__NAME);

		dlNotionEClass = createEClass(DL_NOTION);
		createEAttribute(dlNotionEClass, DL_NOTION__TYPE);
		createEReference(dlNotionEClass, DL_NOTION__FEATURES);
		createEReference(dlNotionEClass, DL_NOTION__DEREFERENCES);
		createEAttribute(dlNotionEClass, DL_NOTION__DERIVED);

		dlPrimitiveEClass = createEClass(DL_PRIMITIVE);

		dlRelationshipEClass = createEClass(DL_RELATIONSHIP);
		createEReference(dlRelationshipEClass, DL_RELATIONSHIP__PARTICIPATIONS);
		createEAttribute(dlRelationshipEClass, DL_RELATIONSHIP__NAME);
		createEAttribute(dlRelationshipEClass, DL_RELATIONSHIP__TYPE);
		createEAttribute(dlRelationshipEClass, DL_RELATIONSHIP__ABBREVIATION);

		dlRelationshipParticipationEClass = createEClass(DL_RELATIONSHIP_PARTICIPATION);
		createEAttribute(dlRelationshipParticipationEClass, DL_RELATIONSHIP_PARTICIPATION__DIRECTION);
		createEAttribute(dlRelationshipParticipationEClass, DL_RELATIONSHIP_PARTICIPATION__MULTIPLICITY);
		createEReference(dlRelationshipParticipationEClass, DL_RELATIONSHIP_PARTICIPATION__PARTICIPANT);
		createEReference(dlRelationshipParticipationEClass, DL_RELATIONSHIP_PARTICIPATION__RELATIONSHIP);
		createEReference(dlRelationshipParticipationEClass, DL_RELATIONSHIP_PARTICIPATION__PARENT_PARTICIPATION);
		createEAttribute(dlRelationshipParticipationEClass, DL_RELATIONSHIP_PARTICIPATION__TYPE);

		dlDiagramEClass = createEClass(DL_DIAGRAM);
		createEReference(dlDiagramEClass, DL_DIAGRAM__RELATIONSHIP_PARTICIPANTS);
		createEReference(dlDiagramEClass, DL_DIAGRAM__RELATIONSHIPS);
		createEAttribute(dlDiagramEClass, DL_DIAGRAM__NAME);

		dlReferenceEClass = createEClass(DL_REFERENCE);

		dlTransitionEClass = createEClass(DL_TRANSITION);

		dlAlghoritmicTransitionEClass = createEClass(DL_ALGHORITMIC_TRANSITION);
		createEReference(dlAlghoritmicTransitionEClass, DL_ALGHORITMIC_TRANSITION__SEQUENCE);

		dlPatternBasedTransitionEClass = createEClass(DL_PATTERN_BASED_TRANSITION);
		createEReference(dlPatternBasedTransitionEClass, DL_PATTERN_BASED_TRANSITION__PATTERN);

		dlPatternBasedReferenceEClass = createEClass(DL_PATTERN_BASED_REFERENCE);
		createEReference(dlPatternBasedReferenceEClass, DL_PATTERN_BASED_REFERENCE__PATTERN);

		dlDataBasedReferenceEClass = createEClass(DL_DATA_BASED_REFERENCE);

		dlEntityEClass = createEClass(DL_ENTITY);

		dlPropertyEClass = createEClass(DL_PROPERTY);
		createEAttribute(dlPropertyEClass, DL_PROPERTY__VALUE_TYPE);
		createEReference(dlPropertyEClass, DL_PROPERTY__SET_TYPE);

		dlFeatureEClass = createEClass(DL_FEATURE);
		createEReference(dlFeatureEClass, DL_FEATURE__NOTION);

		dlConditionEClass = createEClass(DL_CONDITION);
		createEAttribute(dlConditionEClass, DL_CONDITION__TYPE);
		createEAttribute(dlConditionEClass, DL_CONDITION__TEXT);

		dlAttributeLinkEClass = createEClass(DL_ATTRIBUTE_LINK);
		createEReference(dlAttributeLinkEClass, DL_ATTRIBUTE_LINK__ATTRIBUTE);
		createEAttribute(dlAttributeLinkEClass, DL_ATTRIBUTE_LINK__TYPE);

		dlInheritanceConditionEClass = createEClass(DL_INHERITANCE_CONDITION);
		createEReference(dlInheritanceConditionEClass, DL_INHERITANCE_CONDITION__PARENTS);
		createEAttribute(dlInheritanceConditionEClass, DL_INHERITANCE_CONDITION__FEATURE_TYPE);

		dlIdentityConditionEClass = createEClass(DL_IDENTITY_CONDITION);

		dlValidityConditionEClass = createEClass(DL_VALIDITY_CONDITION);

		dlTransitionPatternEClass = createEClass(DL_TRANSITION_PATTERN);
		createEAttribute(dlTransitionPatternEClass, DL_TRANSITION_PATTERN__TYPE);

		dlConditionPatternEClass = createEClass(DL_CONDITION_PATTERN);
		createEAttribute(dlConditionPatternEClass, DL_CONDITION_PATTERN__TYPE);

		dlPatternEClass = createEClass(DL_PATTERN);
		createEAttribute(dlPatternEClass, DL_PATTERN__PATTERN);

		dlSubsetEClass = createEClass(DL_SUBSET);

		dlRepositoryEClass = createEClass(DL_REPOSITORY);
		createEReference(dlRepositoryEClass, DL_REPOSITORY__DOMAINS);
		createEReference(dlRepositoryEClass, DL_REPOSITORY__DIAGRAMS);

		dlDomainElementEClass = createEClass(DL_DOMAIN_ELEMENT);
		createEReference(dlDomainElementEClass, DL_DOMAIN_ELEMENT__DOMAINS);

		dlPatternConditionEClass = createEClass(DL_PATTERN_CONDITION);
		createEReference(dlPatternConditionEClass, DL_PATTERN_CONDITION__PATTERN);

		dlAlghoritmicTransitionSequenceElementEClass = createEClass(DL_ALGHORITMIC_TRANSITION_SEQUENCE_ELEMENT);
		createEReference(dlAlghoritmicTransitionSequenceElementEClass, DL_ALGHORITMIC_TRANSITION_SEQUENCE_ELEMENT__SEQUENT);

		dlAlghoritmicTransitionStepEClass = createEClass(DL_ALGHORITMIC_TRANSITION_STEP);

		dlNamedLinkEClass = createEClass(DL_NAMED_LINK);
		createEAttribute(dlNamedLinkEClass, DL_NAMED_LINK__NAME);

		dlDereferenceLinkEClass = createEClass(DL_DEREFERENCE_LINK);
		createEReference(dlDereferenceLinkEClass, DL_DEREFERENCE_LINK__ELEMENT_TYPE);
		createEAttribute(dlDereferenceLinkEClass, DL_DEREFERENCE_LINK__PATTERN);

		dlCustomAlghoritmicTransitionStepEClass = createEClass(DL_CUSTOM_ALGHORITMIC_TRANSITION_STEP);
		createEReference(dlCustomAlghoritmicTransitionStepEClass, DL_CUSTOM_ALGHORITMIC_TRANSITION_STEP__ALTERNATIVE);
		createEReference(dlCustomAlghoritmicTransitionStepEClass, DL_CUSTOM_ALGHORITMIC_TRANSITION_STEP__ACTION);
		createEReference(dlCustomAlghoritmicTransitionStepEClass, DL_CUSTOM_ALGHORITMIC_TRANSITION_STEP__CONDITION);

		dlPredefinedAlghoritmicTransitionStepEClass = createEClass(DL_PREDEFINED_ALGHORITMIC_TRANSITION_STEP);
		createEAttribute(dlPredefinedAlghoritmicTransitionStepEClass, DL_PREDEFINED_ALGHORITMIC_TRANSITION_STEP__TYPE);
		createEAttribute(dlPredefinedAlghoritmicTransitionStepEClass, DL_PREDEFINED_ALGHORITMIC_TRANSITION_STEP__PATTERN);

		dlControlFlowStatementEClass = createEClass(DL_CONTROL_FLOW_STATEMENT);
		createEAttribute(dlControlFlowStatementEClass, DL_CONTROL_FLOW_STATEMENT__TYPE);

		dlReferencingElementEClass = createEClass(DL_REFERENCING_ELEMENT);
		createEReference(dlReferencingElementEClass, DL_REFERENCING_ELEMENT__SUBJECT_LINK);

		dlPartLinkEClass = createEClass(DL_PART_LINK);
		createEReference(dlPartLinkEClass, DL_PART_LINK__PART_DEREFERENCE);

		// Create enums
		dlTypeRoleEEnum = createEEnum(DL_TYPE_ROLE);
		dlRelationshipParticipationDirectionEEnum = createEEnum(DL_RELATIONSHIP_PARTICIPATION_DIRECTION);
		dlRelationshipParticipationMultiplicityEEnum = createEEnum(DL_RELATIONSHIP_PARTICIPATION_MULTIPLICITY);
		dlPropertyValueTypeEEnum = createEEnum(DL_PROPERTY_VALUE_TYPE);
		dlTransitionPatternTypeEEnum = createEEnum(DL_TRANSITION_PATTERN_TYPE);
		dlConditionPatternTypeEEnum = createEEnum(DL_CONDITION_PATTERN_TYPE);
		dlFeatureTypeEEnum = createEEnum(DL_FEATURE_TYPE);
		dlRelationshipParticipationTypeEEnum = createEEnum(DL_RELATIONSHIP_PARTICIPATION_TYPE);
		dlControlFlowStatementTypeEEnum = createEEnum(DL_CONTROL_FLOW_STATEMENT_TYPE);
		dlPredefinedStepTypeEEnum = createEEnum(DL_PREDEFINED_STEP_TYPE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		dlDomainEClass.getESuperTypes().add(this.getDLSubset());
		dlNotionEClass.getESuperTypes().add(this.getDLRelationshipParticipant());
		dlNotionEClass.getESuperTypes().add(this.getDLDomainElement());
		dlPrimitiveEClass.getESuperTypes().add(this.getDLRelationshipParticipant());
		dlRelationshipEClass.getESuperTypes().add(this.getDLDomainElement());
		dlRelationshipParticipationEClass.getESuperTypes().add(this.getDLNamedLink());
		dlReferenceEClass.getESuperTypes().add(this.getDLRelationship());
		dlTransitionEClass.getESuperTypes().add(this.getDLRelationship());
		dlAlghoritmicTransitionEClass.getESuperTypes().add(this.getDLTransition());
		dlPatternBasedTransitionEClass.getESuperTypes().add(this.getDLTransition());
		dlPatternBasedReferenceEClass.getESuperTypes().add(this.getDLReference());
		dlDataBasedReferenceEClass.getESuperTypes().add(this.getDLReference());
		dlEntityEClass.getESuperTypes().add(this.getDLNotion());
		dlPropertyEClass.getESuperTypes().add(this.getDLNotion());
		dlConditionEClass.getESuperTypes().add(this.getDLFeature());
		dlAttributeLinkEClass.getESuperTypes().add(this.getDLFeature());
		dlAttributeLinkEClass.getESuperTypes().add(this.getDLNamedLink());
		dlInheritanceConditionEClass.getESuperTypes().add(this.getDLCondition());
		dlIdentityConditionEClass.getESuperTypes().add(this.getDLCondition());
		dlIdentityConditionEClass.getESuperTypes().add(this.getDLPatternCondition());
		dlValidityConditionEClass.getESuperTypes().add(this.getDLCondition());
		dlValidityConditionEClass.getESuperTypes().add(this.getDLPatternCondition());
		dlTransitionPatternEClass.getESuperTypes().add(this.getDLPattern());
		dlConditionPatternEClass.getESuperTypes().add(this.getDLPattern());
		dlPatternEClass.getESuperTypes().add(this.getDLReferencingElement());
		dlRepositoryEClass.getESuperTypes().add(this.getDLSubset());
		dlRepositoryEClass.getESuperTypes().add(this.getDLDiagram());
		dlAlghoritmicTransitionStepEClass.getESuperTypes().add(this.getDLAlghoritmicTransitionSequenceElement());
		dlDereferenceLinkEClass.getESuperTypes().add(this.getDLNamedLink());
		dlCustomAlghoritmicTransitionStepEClass.getESuperTypes().add(this.getDLAlghoritmicTransitionStep());
		dlPredefinedAlghoritmicTransitionStepEClass.getESuperTypes().add(this.getDLAlghoritmicTransitionStep());
		dlControlFlowStatementEClass.getESuperTypes().add(this.getDLAlghoritmicTransitionSequenceElement());
		dlControlFlowStatementEClass.getESuperTypes().add(this.getDLReferencingElement());
		dlPartLinkEClass.getESuperTypes().add(this.getDLFeature());

		// Initialize classes and features; add operations and parameters
		initEClass(dlDomainEClass, DLDomain.class, "DLDomain", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDLDomain_Name(), ecorePackage.getEString(), "name", null, 0, 1, DLDomain.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDLDomain_Elements(), this.getDLDomainElement(), this.getDLDomainElement_Domains(), "elements", null, 0, -1, DLDomain.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(dlRelationshipParticipantEClass, DLRelationshipParticipant.class, "DLRelationshipParticipant", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDLRelationshipParticipant_Name(), ecorePackage.getEString(), "name", null, 0, 1, DLRelationshipParticipant.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(dlNotionEClass, DLNotion.class, "DLNotion", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDLNotion_Type(), this.getDLTypeRole(), "type", null, 0, 1, DLNotion.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDLNotion_Features(), this.getDLFeature(), this.getDLFeature_Notion(), "features", null, 0, -1, DLNotion.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDLNotion_Dereferences(), this.getDLDereferenceLink(), null, "dereferences", null, 0, -1, DLNotion.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDLNotion_Derived(), ecorePackage.getEBoolean(), "derived", null, 0, 1, DLNotion.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		EOperation op = addEOperation(dlNotionEClass, null, "getDirectAttributes", 0, 1, IS_UNIQUE, IS_ORDERED);
		EGenericType g1 = createEGenericType(ecorePackage.getEEList());
		EGenericType g2 = createEGenericType(this.getDLProperty());
		g1.getETypeArguments().add(g2);
		initEOperation(op, g1);

		op = addEOperation(dlNotionEClass, null, "getAllAttributes", 0, 1, IS_UNIQUE, IS_ORDERED);
		g1 = createEGenericType(ecorePackage.getEEList());
		g2 = createEGenericType(this.getDLProperty());
		g1.getETypeArguments().add(g2);
		initEOperation(op, g1);

		op = addEOperation(dlNotionEClass, null, "getInheritanceConditions", 0, 1, IS_UNIQUE, IS_ORDERED);
		g1 = createEGenericType(ecorePackage.getEEList());
		g2 = createEGenericType(this.getDLInheritanceCondition());
		g1.getETypeArguments().add(g2);
		initEOperation(op, g1);

		op = addEOperation(dlNotionEClass, null, "getAttributesLinks", 0, 1, IS_UNIQUE, IS_ORDERED);
		g1 = createEGenericType(ecorePackage.getEEList());
		g2 = createEGenericType(this.getDLAttributeLink());
		g1.getETypeArguments().add(g2);
		initEOperation(op, g1);

		op = addEOperation(dlNotionEClass, null, "getValidityConditions", 0, 1, IS_UNIQUE, IS_ORDERED);
		g1 = createEGenericType(ecorePackage.getEEList());
		g2 = createEGenericType(this.getDLValidityCondition());
		g1.getETypeArguments().add(g2);
		initEOperation(op, g1);

		op = addEOperation(dlNotionEClass, null, "getIdentityConditions", 0, 1, IS_UNIQUE, IS_ORDERED);
		g1 = createEGenericType(ecorePackage.getEEList());
		g2 = createEGenericType(this.getDLIdentityCondition());
		g1.getETypeArguments().add(g2);
		initEOperation(op, g1);

		op = addEOperation(dlNotionEClass, null, "getAllAttributesLinks", 0, 1, IS_UNIQUE, IS_ORDERED);
		g1 = createEGenericType(ecorePackage.getEEList());
		g2 = createEGenericType(this.getDLAttributeLink());
		g1.getETypeArguments().add(g2);
		initEOperation(op, g1);

		initEClass(dlPrimitiveEClass, DLPrimitive.class, "DLPrimitive", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(dlRelationshipEClass, DLRelationship.class, "DLRelationship", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getDLRelationship_Participations(), this.getDLRelationshipParticipation(), this.getDLRelationshipParticipation_Relationship(), "participations", null, 0, -1, DLRelationship.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDLRelationship_Name(), ecorePackage.getEString(), "name", null, 0, 1, DLRelationship.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDLRelationship_Type(), ecorePackage.getEString(), "type", null, 0, 1, DLRelationship.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getDLRelationship_Abbreviation(), ecorePackage.getEString(), "abbreviation", null, 0, 1, DLRelationship.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(dlRelationshipParticipationEClass, DLRelationshipParticipation.class, "DLRelationshipParticipation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDLRelationshipParticipation_Direction(), this.getDLRelationshipParticipationDirection(), "direction", null, 0, 1, DLRelationshipParticipation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDLRelationshipParticipation_Multiplicity(), this.getDLRelationshipParticipationMultiplicity(), "multiplicity", null, 0, 1, DLRelationshipParticipation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDLRelationshipParticipation_Participant(), this.getDLRelationshipParticipant(), null, "participant", null, 1, 1, DLRelationshipParticipation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDLRelationshipParticipation_Relationship(), this.getDLRelationship(), this.getDLRelationship_Participations(), "relationship", null, 1, 1, DLRelationshipParticipation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDLRelationshipParticipation_ParentParticipation(), this.getDLRelationshipParticipation(), null, "parentParticipation", null, 0, 1, DLRelationshipParticipation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDLRelationshipParticipation_Type(), this.getDLRelationshipParticipationType(), "type", null, 0, 1, DLRelationshipParticipation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(dlDiagramEClass, DLDiagram.class, "DLDiagram", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getDLDiagram_RelationshipParticipants(), this.getDLRelationshipParticipant(), null, "relationshipParticipants", null, 0, -1, DLDiagram.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDLDiagram_Relationships(), this.getDLRelationship(), null, "relationships", null, 0, -1, DLDiagram.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDLDiagram_Name(), ecorePackage.getEString(), "name", null, 0, 1, DLDiagram.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(dlReferenceEClass, DLReference.class, "DLReference", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(dlTransitionEClass, DLTransition.class, "DLTransition", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(dlAlghoritmicTransitionEClass, DLAlghoritmicTransition.class, "DLAlghoritmicTransition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getDLAlghoritmicTransition_Sequence(), this.getDLAlghoritmicTransitionSequenceElement(), null, "sequence", null, 1, 1, DLAlghoritmicTransition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(dlPatternBasedTransitionEClass, DLPatternBasedTransition.class, "DLPatternBasedTransition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getDLPatternBasedTransition_Pattern(), this.getDLTransitionPattern(), null, "pattern", null, 1, 1, DLPatternBasedTransition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(dlPatternBasedReferenceEClass, DLPatternBasedReference.class, "DLPatternBasedReference", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getDLPatternBasedReference_Pattern(), this.getDLConditionPattern(), null, "pattern", null, 1, 1, DLPatternBasedReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(dlDataBasedReferenceEClass, DLDataBasedReference.class, "DLDataBasedReference", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(dlEntityEClass, DLEntity.class, "DLEntity", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(dlPropertyEClass, DLProperty.class, "DLProperty", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDLProperty_ValueType(), this.getDLPropertyValueType(), "valueType", null, 0, 1, DLProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDLProperty_SetType(), this.getDLNotion(), null, "setType", null, 0, 1, DLProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(dlFeatureEClass, DLFeature.class, "DLFeature", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getDLFeature_Notion(), this.getDLNotion(), this.getDLNotion_Features(), "notion", null, 1, 1, DLFeature.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(dlConditionEClass, DLCondition.class, "DLCondition", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDLCondition_Type(), ecorePackage.getEString(), "type", null, 0, 1, DLCondition.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getDLCondition_Text(), ecorePackage.getEString(), "text", null, 0, 1, DLCondition.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(dlAttributeLinkEClass, DLAttributeLink.class, "DLAttributeLink", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getDLAttributeLink_Attribute(), this.getDLProperty(), null, "attribute", null, 1, 1, DLAttributeLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDLAttributeLink_Type(), this.getDLFeatureType(), "type", null, 0, 1, DLAttributeLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(dlInheritanceConditionEClass, DLInheritanceCondition.class, "DLInheritanceCondition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getDLInheritanceCondition_Parents(), this.getDLNotion(), null, "parents", null, 0, -1, DLInheritanceCondition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDLInheritanceCondition_FeatureType(), this.getDLFeatureType(), "featureType", null, 0, 1, DLInheritanceCondition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(dlIdentityConditionEClass, DLIdentityCondition.class, "DLIdentityCondition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(dlValidityConditionEClass, DLValidityCondition.class, "DLValidityCondition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(dlTransitionPatternEClass, DLTransitionPattern.class, "DLTransitionPattern", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDLTransitionPattern_Type(), this.getDLTransitionPatternType(), "type", null, 0, 1, DLTransitionPattern.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(dlConditionPatternEClass, DLConditionPattern.class, "DLConditionPattern", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDLConditionPattern_Type(), this.getDLConditionPatternType(), "type", null, 0, 1, DLConditionPattern.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(dlPatternEClass, DLPattern.class, "DLPattern", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDLPattern_Pattern(), ecorePackage.getEString(), "pattern", null, 0, 1, DLPattern.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(dlSubsetEClass, DLSubset.class, "DLSubset", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		op = addEOperation(dlSubsetEClass, null, "getDomainNotions", 0, 1, IS_UNIQUE, IS_ORDERED);
		g1 = createEGenericType(ecorePackage.getEEList());
		g2 = createEGenericType(this.getDLNotion());
		g1.getETypeArguments().add(g2);
		initEOperation(op, g1);

		op = addEOperation(dlSubsetEClass, null, "getDomainRelationships", 0, 1, IS_UNIQUE, IS_ORDERED);
		g1 = createEGenericType(ecorePackage.getEEList());
		g2 = createEGenericType(this.getDLRelationship());
		g1.getETypeArguments().add(g2);
		initEOperation(op, g1);

		op = addEOperation(dlSubsetEClass, this.getDLNotion(), "getNotion", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "name", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(dlSubsetEClass, null, "getNotions", 0, 1, IS_UNIQUE, IS_ORDERED);
		g1 = createEGenericType(ecorePackage.getEEList());
		g2 = createEGenericType(ecorePackage.getEString());
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "names", 0, 1, IS_UNIQUE, IS_ORDERED);
		g1 = createEGenericType(ecorePackage.getEEList());
		g2 = createEGenericType(this.getDLNotion());
		g1.getETypeArguments().add(g2);
		initEOperation(op, g1);

		op = addEOperation(dlSubsetEClass, this.getDLRelationship(), "getRelationshipByAbbreviation", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "abbreviation", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(dlRepositoryEClass, DLRepository.class, "DLRepository", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getDLRepository_Domains(), this.getDLDomain(), null, "domains", null, 0, -1, DLRepository.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDLRepository_Diagrams(), this.getDLDiagram(), null, "diagrams", null, 0, -1, DLRepository.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		op = addEOperation(dlRepositoryEClass, this.getDLDomain(), "getDomain", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "name", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(dlRepositoryEClass, this.getDLDiagram(), "getDiagram", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "name", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(dlDomainElementEClass, DLDomainElement.class, "DLDomainElement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getDLDomainElement_Domains(), this.getDLDomain(), this.getDLDomain_Elements(), "domains", null, 0, -1, DLDomainElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(dlPatternConditionEClass, DLPatternCondition.class, "DLPatternCondition", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getDLPatternCondition_Pattern(), this.getDLConditionPattern(), null, "pattern", null, 1, 1, DLPatternCondition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(dlAlghoritmicTransitionSequenceElementEClass, DLAlghoritmicTransitionSequenceElement.class, "DLAlghoritmicTransitionSequenceElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getDLAlghoritmicTransitionSequenceElement_Sequent(), this.getDLAlghoritmicTransitionSequenceElement(), null, "sequent", null, 0, 1, DLAlghoritmicTransitionSequenceElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(dlAlghoritmicTransitionStepEClass, DLAlghoritmicTransitionStep.class, "DLAlghoritmicTransitionStep", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(dlNamedLinkEClass, DLNamedLink.class, "DLNamedLink", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDLNamedLink_Name(), ecorePackage.getEString(), "name", null, 0, 1, DLNamedLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		addEOperation(dlNamedLinkEClass, this.getDLRelationshipParticipant(), "getElement", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(dlNamedLinkEClass, ecorePackage.getEString(), "getText", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(dlDereferenceLinkEClass, DLDereferenceLink.class, "DLDereferenceLink", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getDLDereferenceLink_ElementType(), this.getDLNotion(), null, "elementType", null, 1, 1, DLDereferenceLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDLDereferenceLink_Pattern(), ecorePackage.getEString(), "pattern", null, 0, 1, DLDereferenceLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(dlCustomAlghoritmicTransitionStepEClass, DLCustomAlghoritmicTransitionStep.class, "DLCustomAlghoritmicTransitionStep", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getDLCustomAlghoritmicTransitionStep_Alternative(), this.getDLAlghoritmicTransitionSequenceElement(), null, "alternative", null, 0, 1, DLCustomAlghoritmicTransitionStep.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDLCustomAlghoritmicTransitionStep_Action(), this.getDLTransitionPattern(), null, "action", null, 1, 1, DLCustomAlghoritmicTransitionStep.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDLCustomAlghoritmicTransitionStep_Condition(), this.getDLConditionPattern(), null, "condition", null, 0, 1, DLCustomAlghoritmicTransitionStep.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(dlPredefinedAlghoritmicTransitionStepEClass, DLPredefinedAlghoritmicTransitionStep.class, "DLPredefinedAlghoritmicTransitionStep", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDLPredefinedAlghoritmicTransitionStep_Type(), this.getDLPredefinedStepType(), "type", null, 0, 1, DLPredefinedAlghoritmicTransitionStep.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDLPredefinedAlghoritmicTransitionStep_Pattern(), ecorePackage.getEString(), "pattern", null, 0, 1, DLPredefinedAlghoritmicTransitionStep.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(dlControlFlowStatementEClass, DLControlFlowStatement.class, "DLControlFlowStatement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDLControlFlowStatement_Type(), this.getDLControlFlowStatementType(), "type", null, 0, 1, DLControlFlowStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(dlReferencingElementEClass, DLReferencingElement.class, "DLReferencingElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getDLReferencingElement_SubjectLink(), this.getDLNamedLink(), null, "subjectLink", null, 0, 1, DLReferencingElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(dlPartLinkEClass, DLPartLink.class, "DLPartLink", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getDLPartLink_PartDereference(), this.getDLDereferenceLink(), null, "partDereference", null, 1, 1, DLPartLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(dlTypeRoleEEnum, DLTypeRole.class, "DLTypeRole");
		addEEnumLiteral(dlTypeRoleEEnum, DLTypeRole.IDENTITY);
		addEEnumLiteral(dlTypeRoleEEnum, DLTypeRole.PERSISTENT_ROLE);
		addEEnumLiteral(dlTypeRoleEEnum, DLTypeRole.TEMPORARY_ROLE);
		addEEnumLiteral(dlTypeRoleEEnum, DLTypeRole.TEMPLATE);

		initEEnum(dlRelationshipParticipationDirectionEEnum, DLRelationshipParticipationDirection.class, "DLRelationshipParticipationDirection");
		addEEnumLiteral(dlRelationshipParticipationDirectionEEnum, DLRelationshipParticipationDirection.UNDEFINED);
		addEEnumLiteral(dlRelationshipParticipationDirectionEEnum, DLRelationshipParticipationDirection.SOURCE);
		addEEnumLiteral(dlRelationshipParticipationDirectionEEnum, DLRelationshipParticipationDirection.TARGET);
		addEEnumLiteral(dlRelationshipParticipationDirectionEEnum, DLRelationshipParticipationDirection.DOUBLE_SIDED);

		initEEnum(dlRelationshipParticipationMultiplicityEEnum, DLRelationshipParticipationMultiplicity.class, "DLRelationshipParticipationMultiplicity");
		addEEnumLiteral(dlRelationshipParticipationMultiplicityEEnum, DLRelationshipParticipationMultiplicity.SINGLE);
		addEEnumLiteral(dlRelationshipParticipationMultiplicityEEnum, DLRelationshipParticipationMultiplicity.MULTIPLE);
		addEEnumLiteral(dlRelationshipParticipationMultiplicityEEnum, DLRelationshipParticipationMultiplicity.ORDERED_MULTIPLE);

		initEEnum(dlPropertyValueTypeEEnum, DLPropertyValueType.class, "DLPropertyValueType");
		addEEnumLiteral(dlPropertyValueTypeEEnum, DLPropertyValueType.STRING);
		addEEnumLiteral(dlPropertyValueTypeEEnum, DLPropertyValueType.BOOLEAN);
		addEEnumLiteral(dlPropertyValueTypeEEnum, DLPropertyValueType.INTEGER);
		addEEnumLiteral(dlPropertyValueTypeEEnum, DLPropertyValueType.FLOAT);
		addEEnumLiteral(dlPropertyValueTypeEEnum, DLPropertyValueType.DATE);
		addEEnumLiteral(dlPropertyValueTypeEEnum, DLPropertyValueType.SET);
		addEEnumLiteral(dlPropertyValueTypeEEnum, DLPropertyValueType.ORDERED_SET);
		addEEnumLiteral(dlPropertyValueTypeEEnum, DLPropertyValueType.TIME);
		addEEnumLiteral(dlPropertyValueTypeEEnum, DLPropertyValueType.ENUMERATION);
		addEEnumLiteral(dlPropertyValueTypeEEnum, DLPropertyValueType.COMPOSITE);
		addEEnumLiteral(dlPropertyValueTypeEEnum, DLPropertyValueType.INHERITED);

		initEEnum(dlTransitionPatternTypeEEnum, DLTransitionPatternType.class, "DLTransitionPatternType");
		addEEnumLiteral(dlTransitionPatternTypeEEnum, DLTransitionPatternType.EMPTY);
		addEEnumLiteral(dlTransitionPatternTypeEEnum, DLTransitionPatternType.SIMPLE);
		addEEnumLiteral(dlTransitionPatternTypeEEnum, DLTransitionPatternType.SUMMATION);
		addEEnumLiteral(dlTransitionPatternTypeEEnum, DLTransitionPatternType.MULTIPLICATION);
		addEEnumLiteral(dlTransitionPatternTypeEEnum, DLTransitionPatternType.SYSTEM);
		addEEnumLiteral(dlTransitionPatternTypeEEnum, DLTransitionPatternType.QUERY);
		addEEnumLiteral(dlTransitionPatternTypeEEnum, DLTransitionPatternType.MAPPING);
		addEEnumLiteral(dlTransitionPatternTypeEEnum, DLTransitionPatternType.INDIRECT);
		addEEnumLiteral(dlTransitionPatternTypeEEnum, DLTransitionPatternType.EVOCATION);

		initEEnum(dlConditionPatternTypeEEnum, DLConditionPatternType.class, "DLConditionPatternType");
		addEEnumLiteral(dlConditionPatternTypeEEnum, DLConditionPatternType.EMPTY);
		addEEnumLiteral(dlConditionPatternTypeEEnum, DLConditionPatternType.SIMPLE);
		addEEnumLiteral(dlConditionPatternTypeEEnum, DLConditionPatternType.UNIVERSAL_QUANTIFICATION);
		addEEnumLiteral(dlConditionPatternTypeEEnum, DLConditionPatternType.EXISTENTIAL_QUANTIFICATION);
		addEEnumLiteral(dlConditionPatternTypeEEnum, DLConditionPatternType.MEMBERSHIP);

		initEEnum(dlFeatureTypeEEnum, DLFeatureType.class, "DLFeatureType");
		addEEnumLiteral(dlFeatureTypeEEnum, DLFeatureType.PROVIDED);
		addEEnumLiteral(dlFeatureTypeEEnum, DLFeatureType.REQUIRED);

		initEEnum(dlRelationshipParticipationTypeEEnum, DLRelationshipParticipationType.class, "DLRelationshipParticipationType");
		addEEnumLiteral(dlRelationshipParticipationTypeEEnum, DLRelationshipParticipationType.STANDARD);
		addEEnumLiteral(dlRelationshipParticipationTypeEEnum, DLRelationshipParticipationType.AUXILIARY_PARENT);
		addEEnumLiteral(dlRelationshipParticipationTypeEEnum, DLRelationshipParticipationType.AUXILIARY_ROLE);

		initEEnum(dlControlFlowStatementTypeEEnum, DLControlFlowStatementType.class, "DLControlFlowStatementType");

		initEEnum(dlPredefinedStepTypeEEnum, DLPredefinedStepType.class, "DLPredefinedStepType");

		// Create resource
		createResource(eNS_URI);
	}

} //RsldlPackageImpl
