/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package rsldl.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import rsldl.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class RsldlFactoryImpl extends EFactoryImpl implements RsldlFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static RsldlFactory init() {
		try {
			RsldlFactory theRsldlFactory = (RsldlFactory)EPackage.Registry.INSTANCE.getEFactory(RsldlPackage.eNS_URI);
			if (theRsldlFactory != null) {
				return theRsldlFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new RsldlFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RsldlFactoryImpl() {
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
			case RsldlPackage.DL_DOMAIN: return createDLDomain();
			case RsldlPackage.DL_PRIMITIVE: return createDLPrimitive();
			case RsldlPackage.DL_RELATIONSHIP_PARTICIPATION: return createDLRelationshipParticipation();
			case RsldlPackage.DL_DIAGRAM: return createDLDiagram();
			case RsldlPackage.DL_ALGHORITMIC_TRANSITION: return createDLAlghoritmicTransition();
			case RsldlPackage.DL_PATTERN_BASED_TRANSITION: return createDLPatternBasedTransition();
			case RsldlPackage.DL_PATTERN_BASED_REFERENCE: return createDLPatternBasedReference();
			case RsldlPackage.DL_DATA_BASED_REFERENCE: return createDLDataBasedReference();
			case RsldlPackage.DL_ENTITY: return createDLEntity();
			case RsldlPackage.DL_PROPERTY: return createDLProperty();
			case RsldlPackage.DL_ATTRIBUTE_LINK: return createDLAttributeLink();
			case RsldlPackage.DL_INHERITANCE_CONDITION: return createDLInheritanceCondition();
			case RsldlPackage.DL_IDENTITY_CONDITION: return createDLIdentityCondition();
			case RsldlPackage.DL_VALIDITY_CONDITION: return createDLValidityCondition();
			case RsldlPackage.DL_TRANSITION_PATTERN: return createDLTransitionPattern();
			case RsldlPackage.DL_CONDITION_PATTERN: return createDLConditionPattern();
			case RsldlPackage.DL_REPOSITORY: return createDLRepository();
			case RsldlPackage.DL_DOMAIN_ELEMENT: return createDLDomainElement();
			case RsldlPackage.DL_DEREFERENCE_LINK: return createDLDereferenceLink();
			case RsldlPackage.DL_CUSTOM_ALGHORITMIC_TRANSITION_STEP: return createDLCustomAlghoritmicTransitionStep();
			case RsldlPackage.DL_PREDEFINED_ALGHORITMIC_TRANSITION_STEP: return createDLPredefinedAlghoritmicTransitionStep();
			case RsldlPackage.DL_CONTROL_FLOW_STATEMENT: return createDLControlFlowStatement();
			case RsldlPackage.DL_PART_LINK: return createDLPartLink();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case RsldlPackage.DL_TYPE_ROLE:
				return createDLTypeRoleFromString(eDataType, initialValue);
			case RsldlPackage.DL_RELATIONSHIP_PARTICIPATION_DIRECTION:
				return createDLRelationshipParticipationDirectionFromString(eDataType, initialValue);
			case RsldlPackage.DL_RELATIONSHIP_PARTICIPATION_MULTIPLICITY:
				return createDLRelationshipParticipationMultiplicityFromString(eDataType, initialValue);
			case RsldlPackage.DL_PROPERTY_VALUE_TYPE:
				return createDLPropertyValueTypeFromString(eDataType, initialValue);
			case RsldlPackage.DL_TRANSITION_PATTERN_TYPE:
				return createDLTransitionPatternTypeFromString(eDataType, initialValue);
			case RsldlPackage.DL_CONDITION_PATTERN_TYPE:
				return createDLConditionPatternTypeFromString(eDataType, initialValue);
			case RsldlPackage.DL_FEATURE_TYPE:
				return createDLFeatureTypeFromString(eDataType, initialValue);
			case RsldlPackage.DL_RELATIONSHIP_PARTICIPATION_TYPE:
				return createDLRelationshipParticipationTypeFromString(eDataType, initialValue);
			case RsldlPackage.DL_CONTROL_FLOW_STATEMENT_TYPE:
				return createDLControlFlowStatementTypeFromString(eDataType, initialValue);
			case RsldlPackage.DL_PREDEFINED_STEP_TYPE:
				return createDLPredefinedStepTypeFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case RsldlPackage.DL_TYPE_ROLE:
				return convertDLTypeRoleToString(eDataType, instanceValue);
			case RsldlPackage.DL_RELATIONSHIP_PARTICIPATION_DIRECTION:
				return convertDLRelationshipParticipationDirectionToString(eDataType, instanceValue);
			case RsldlPackage.DL_RELATIONSHIP_PARTICIPATION_MULTIPLICITY:
				return convertDLRelationshipParticipationMultiplicityToString(eDataType, instanceValue);
			case RsldlPackage.DL_PROPERTY_VALUE_TYPE:
				return convertDLPropertyValueTypeToString(eDataType, instanceValue);
			case RsldlPackage.DL_TRANSITION_PATTERN_TYPE:
				return convertDLTransitionPatternTypeToString(eDataType, instanceValue);
			case RsldlPackage.DL_CONDITION_PATTERN_TYPE:
				return convertDLConditionPatternTypeToString(eDataType, instanceValue);
			case RsldlPackage.DL_FEATURE_TYPE:
				return convertDLFeatureTypeToString(eDataType, instanceValue);
			case RsldlPackage.DL_RELATIONSHIP_PARTICIPATION_TYPE:
				return convertDLRelationshipParticipationTypeToString(eDataType, instanceValue);
			case RsldlPackage.DL_CONTROL_FLOW_STATEMENT_TYPE:
				return convertDLControlFlowStatementTypeToString(eDataType, instanceValue);
			case RsldlPackage.DL_PREDEFINED_STEP_TYPE:
				return convertDLPredefinedStepTypeToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DLDomain createDLDomain() {
		DLDomainImpl dlDomain = new DLDomainImpl();
		return dlDomain;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DLPrimitive createDLPrimitive() {
		DLPrimitiveImpl dlPrimitive = new DLPrimitiveImpl();
		return dlPrimitive;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DLRelationshipParticipation createDLRelationshipParticipation() {
		DLRelationshipParticipationImpl dlRelationshipParticipation = new DLRelationshipParticipationImpl();
		return dlRelationshipParticipation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DLDiagram createDLDiagram() {
		DLDiagramImpl dlDiagram = new DLDiagramImpl();
		return dlDiagram;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DLAlghoritmicTransition createDLAlghoritmicTransition() {
		DLAlghoritmicTransitionImpl dlAlghoritmicTransition = new DLAlghoritmicTransitionImpl();
		return dlAlghoritmicTransition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DLPatternBasedTransition createDLPatternBasedTransition() {
		DLPatternBasedTransitionImpl dlPatternBasedTransition = new DLPatternBasedTransitionImpl();
		return dlPatternBasedTransition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DLPatternBasedReference createDLPatternBasedReference() {
		DLPatternBasedReferenceImpl dlPatternBasedReference = new DLPatternBasedReferenceImpl();
		return dlPatternBasedReference;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DLDataBasedReference createDLDataBasedReference() {
		DLDataBasedReferenceImpl dlDataBasedReference = new DLDataBasedReferenceImpl();
		return dlDataBasedReference;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DLEntity createDLEntity() {
		DLEntityImpl dlEntity = new DLEntityImpl();
		return dlEntity;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DLProperty createDLProperty() {
		DLPropertyImpl dlProperty = new DLPropertyImpl();
		return dlProperty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DLAttributeLink createDLAttributeLink() {
		DLAttributeLinkImpl dlAttributeLink = new DLAttributeLinkImpl();
		return dlAttributeLink;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DLInheritanceCondition createDLInheritanceCondition() {
		DLInheritanceConditionImpl dlInheritanceCondition = new DLInheritanceConditionImpl();
		return dlInheritanceCondition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DLIdentityCondition createDLIdentityCondition() {
		DLIdentityConditionImpl dlIdentityCondition = new DLIdentityConditionImpl();
		return dlIdentityCondition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DLValidityCondition createDLValidityCondition() {
		DLValidityConditionImpl dlValidityCondition = new DLValidityConditionImpl();
		return dlValidityCondition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DLTransitionPattern createDLTransitionPattern() {
		DLTransitionPatternImpl dlTransitionPattern = new DLTransitionPatternImpl();
		return dlTransitionPattern;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DLConditionPattern createDLConditionPattern() {
		DLConditionPatternImpl dlConditionPattern = new DLConditionPatternImpl();
		return dlConditionPattern;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DLRepository createDLRepository() {
		DLRepositoryImpl dlRepository = new DLRepositoryImpl();
		return dlRepository;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DLDomainElement createDLDomainElement() {
		DLDomainElementImpl dlDomainElement = new DLDomainElementImpl();
		return dlDomainElement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DLDereferenceLink createDLDereferenceLink() {
		DLDereferenceLinkImpl dlDereferenceLink = new DLDereferenceLinkImpl();
		return dlDereferenceLink;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DLCustomAlghoritmicTransitionStep createDLCustomAlghoritmicTransitionStep() {
		DLCustomAlghoritmicTransitionStepImpl dlCustomAlghoritmicTransitionStep = new DLCustomAlghoritmicTransitionStepImpl();
		return dlCustomAlghoritmicTransitionStep;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DLPredefinedAlghoritmicTransitionStep createDLPredefinedAlghoritmicTransitionStep() {
		DLPredefinedAlghoritmicTransitionStepImpl dlPredefinedAlghoritmicTransitionStep = new DLPredefinedAlghoritmicTransitionStepImpl();
		return dlPredefinedAlghoritmicTransitionStep;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DLControlFlowStatement createDLControlFlowStatement() {
		DLControlFlowStatementImpl dlControlFlowStatement = new DLControlFlowStatementImpl();
		return dlControlFlowStatement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DLPartLink createDLPartLink() {
		DLPartLinkImpl dlPartLink = new DLPartLinkImpl();
		return dlPartLink;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DLTypeRole createDLTypeRoleFromString(EDataType eDataType, String initialValue) {
		DLTypeRole result = DLTypeRole.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertDLTypeRoleToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DLRelationshipParticipationDirection createDLRelationshipParticipationDirectionFromString(EDataType eDataType, String initialValue) {
		DLRelationshipParticipationDirection result = DLRelationshipParticipationDirection.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertDLRelationshipParticipationDirectionToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DLRelationshipParticipationMultiplicity createDLRelationshipParticipationMultiplicityFromString(EDataType eDataType, String initialValue) {
		DLRelationshipParticipationMultiplicity result = DLRelationshipParticipationMultiplicity.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertDLRelationshipParticipationMultiplicityToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DLPropertyValueType createDLPropertyValueTypeFromString(EDataType eDataType, String initialValue) {
		DLPropertyValueType result = DLPropertyValueType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertDLPropertyValueTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DLTransitionPatternType createDLTransitionPatternTypeFromString(EDataType eDataType, String initialValue) {
		DLTransitionPatternType result = DLTransitionPatternType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertDLTransitionPatternTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DLConditionPatternType createDLConditionPatternTypeFromString(EDataType eDataType, String initialValue) {
		DLConditionPatternType result = DLConditionPatternType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertDLConditionPatternTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DLFeatureType createDLFeatureTypeFromString(EDataType eDataType, String initialValue) {
		DLFeatureType result = DLFeatureType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertDLFeatureTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DLRelationshipParticipationType createDLRelationshipParticipationTypeFromString(EDataType eDataType, String initialValue) {
		DLRelationshipParticipationType result = DLRelationshipParticipationType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertDLRelationshipParticipationTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DLControlFlowStatementType createDLControlFlowStatementTypeFromString(EDataType eDataType, String initialValue) {
		DLControlFlowStatementType result = DLControlFlowStatementType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertDLControlFlowStatementTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DLPredefinedStepType createDLPredefinedStepTypeFromString(EDataType eDataType, String initialValue) {
		DLPredefinedStepType result = DLPredefinedStepType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertDLPredefinedStepTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RsldlPackage getRsldlPackage() {
		return (RsldlPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static RsldlPackage getPackage() {
		return RsldlPackage.eINSTANCE;
	}

} //RsldlFactoryImpl
