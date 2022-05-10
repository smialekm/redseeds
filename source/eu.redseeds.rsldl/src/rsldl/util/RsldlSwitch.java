/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package rsldl.util;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.Switch;
import rsldl.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see rsldl.RsldlPackage
 * @generated
 */
public class RsldlSwitch<T> extends Switch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static RsldlPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RsldlSwitch() {
		if (modelPackage == null) {
			modelPackage = RsldlPackage.eINSTANCE;
		}
	}

	/**
	 * Checks whether this is a switch for the given package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param ePackage the package in question.
	 * @return whether this is a switch for the given package.
	 * @generated
	 */
	@Override
	protected boolean isSwitchFor(EPackage ePackage) {
		return ePackage == modelPackage;
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	@Override
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case RsldlPackage.DL_DOMAIN: {
				DLDomain dlDomain = (DLDomain)theEObject;
				T result = caseDLDomain(dlDomain);
				if (result == null) result = caseDLSubset(dlDomain);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RsldlPackage.DL_RELATIONSHIP_PARTICIPANT: {
				DLRelationshipParticipant dlRelationshipParticipant = (DLRelationshipParticipant)theEObject;
				T result = caseDLRelationshipParticipant(dlRelationshipParticipant);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RsldlPackage.DL_NOTION: {
				DLNotion dlNotion = (DLNotion)theEObject;
				T result = caseDLNotion(dlNotion);
				if (result == null) result = caseDLRelationshipParticipant(dlNotion);
				if (result == null) result = caseDLDomainElement(dlNotion);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RsldlPackage.DL_PRIMITIVE: {
				DLPrimitive dlPrimitive = (DLPrimitive)theEObject;
				T result = caseDLPrimitive(dlPrimitive);
				if (result == null) result = caseDLRelationshipParticipant(dlPrimitive);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RsldlPackage.DL_RELATIONSHIP: {
				DLRelationship dlRelationship = (DLRelationship)theEObject;
				T result = caseDLRelationship(dlRelationship);
				if (result == null) result = caseDLDomainElement(dlRelationship);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RsldlPackage.DL_RELATIONSHIP_PARTICIPATION: {
				DLRelationshipParticipation dlRelationshipParticipation = (DLRelationshipParticipation)theEObject;
				T result = caseDLRelationshipParticipation(dlRelationshipParticipation);
				if (result == null) result = caseDLNamedLink(dlRelationshipParticipation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RsldlPackage.DL_DIAGRAM: {
				DLDiagram dlDiagram = (DLDiagram)theEObject;
				T result = caseDLDiagram(dlDiagram);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RsldlPackage.DL_REFERENCE: {
				DLReference dlReference = (DLReference)theEObject;
				T result = caseDLReference(dlReference);
				if (result == null) result = caseDLRelationship(dlReference);
				if (result == null) result = caseDLDomainElement(dlReference);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RsldlPackage.DL_TRANSITION: {
				DLTransition dlTransition = (DLTransition)theEObject;
				T result = caseDLTransition(dlTransition);
				if (result == null) result = caseDLRelationship(dlTransition);
				if (result == null) result = caseDLDomainElement(dlTransition);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RsldlPackage.DL_ALGHORITMIC_TRANSITION: {
				DLAlghoritmicTransition dlAlghoritmicTransition = (DLAlghoritmicTransition)theEObject;
				T result = caseDLAlghoritmicTransition(dlAlghoritmicTransition);
				if (result == null) result = caseDLTransition(dlAlghoritmicTransition);
				if (result == null) result = caseDLRelationship(dlAlghoritmicTransition);
				if (result == null) result = caseDLDomainElement(dlAlghoritmicTransition);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RsldlPackage.DL_PATTERN_BASED_TRANSITION: {
				DLPatternBasedTransition dlPatternBasedTransition = (DLPatternBasedTransition)theEObject;
				T result = caseDLPatternBasedTransition(dlPatternBasedTransition);
				if (result == null) result = caseDLTransition(dlPatternBasedTransition);
				if (result == null) result = caseDLRelationship(dlPatternBasedTransition);
				if (result == null) result = caseDLDomainElement(dlPatternBasedTransition);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RsldlPackage.DL_PATTERN_BASED_REFERENCE: {
				DLPatternBasedReference dlPatternBasedReference = (DLPatternBasedReference)theEObject;
				T result = caseDLPatternBasedReference(dlPatternBasedReference);
				if (result == null) result = caseDLReference(dlPatternBasedReference);
				if (result == null) result = caseDLRelationship(dlPatternBasedReference);
				if (result == null) result = caseDLDomainElement(dlPatternBasedReference);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RsldlPackage.DL_DATA_BASED_REFERENCE: {
				DLDataBasedReference dlDataBasedReference = (DLDataBasedReference)theEObject;
				T result = caseDLDataBasedReference(dlDataBasedReference);
				if (result == null) result = caseDLReference(dlDataBasedReference);
				if (result == null) result = caseDLRelationship(dlDataBasedReference);
				if (result == null) result = caseDLDomainElement(dlDataBasedReference);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RsldlPackage.DL_ENTITY: {
				DLEntity dlEntity = (DLEntity)theEObject;
				T result = caseDLEntity(dlEntity);
				if (result == null) result = caseDLNotion(dlEntity);
				if (result == null) result = caseDLRelationshipParticipant(dlEntity);
				if (result == null) result = caseDLDomainElement(dlEntity);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RsldlPackage.DL_PROPERTY: {
				DLProperty dlProperty = (DLProperty)theEObject;
				T result = caseDLProperty(dlProperty);
				if (result == null) result = caseDLNotion(dlProperty);
				if (result == null) result = caseDLRelationshipParticipant(dlProperty);
				if (result == null) result = caseDLDomainElement(dlProperty);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RsldlPackage.DL_FEATURE: {
				DLFeature dlFeature = (DLFeature)theEObject;
				T result = caseDLFeature(dlFeature);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RsldlPackage.DL_CONDITION: {
				DLCondition dlCondition = (DLCondition)theEObject;
				T result = caseDLCondition(dlCondition);
				if (result == null) result = caseDLFeature(dlCondition);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RsldlPackage.DL_ATTRIBUTE_LINK: {
				DLAttributeLink dlAttributeLink = (DLAttributeLink)theEObject;
				T result = caseDLAttributeLink(dlAttributeLink);
				if (result == null) result = caseDLFeature(dlAttributeLink);
				if (result == null) result = caseDLNamedLink(dlAttributeLink);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RsldlPackage.DL_INHERITANCE_CONDITION: {
				DLInheritanceCondition dlInheritanceCondition = (DLInheritanceCondition)theEObject;
				T result = caseDLInheritanceCondition(dlInheritanceCondition);
				if (result == null) result = caseDLCondition(dlInheritanceCondition);
				if (result == null) result = caseDLFeature(dlInheritanceCondition);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RsldlPackage.DL_IDENTITY_CONDITION: {
				DLIdentityCondition dlIdentityCondition = (DLIdentityCondition)theEObject;
				T result = caseDLIdentityCondition(dlIdentityCondition);
				if (result == null) result = caseDLCondition(dlIdentityCondition);
				if (result == null) result = caseDLPatternCondition(dlIdentityCondition);
				if (result == null) result = caseDLFeature(dlIdentityCondition);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RsldlPackage.DL_VALIDITY_CONDITION: {
				DLValidityCondition dlValidityCondition = (DLValidityCondition)theEObject;
				T result = caseDLValidityCondition(dlValidityCondition);
				if (result == null) result = caseDLCondition(dlValidityCondition);
				if (result == null) result = caseDLPatternCondition(dlValidityCondition);
				if (result == null) result = caseDLFeature(dlValidityCondition);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RsldlPackage.DL_TRANSITION_PATTERN: {
				DLTransitionPattern dlTransitionPattern = (DLTransitionPattern)theEObject;
				T result = caseDLTransitionPattern(dlTransitionPattern);
				if (result == null) result = caseDLPattern(dlTransitionPattern);
				if (result == null) result = caseDLReferencingElement(dlTransitionPattern);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RsldlPackage.DL_CONDITION_PATTERN: {
				DLConditionPattern dlConditionPattern = (DLConditionPattern)theEObject;
				T result = caseDLConditionPattern(dlConditionPattern);
				if (result == null) result = caseDLPattern(dlConditionPattern);
				if (result == null) result = caseDLReferencingElement(dlConditionPattern);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RsldlPackage.DL_PATTERN: {
				DLPattern dlPattern = (DLPattern)theEObject;
				T result = caseDLPattern(dlPattern);
				if (result == null) result = caseDLReferencingElement(dlPattern);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RsldlPackage.DL_SUBSET: {
				DLSubset dlSubset = (DLSubset)theEObject;
				T result = caseDLSubset(dlSubset);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RsldlPackage.DL_REPOSITORY: {
				DLRepository dlRepository = (DLRepository)theEObject;
				T result = caseDLRepository(dlRepository);
				if (result == null) result = caseDLSubset(dlRepository);
				if (result == null) result = caseDLDiagram(dlRepository);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RsldlPackage.DL_DOMAIN_ELEMENT: {
				DLDomainElement dlDomainElement = (DLDomainElement)theEObject;
				T result = caseDLDomainElement(dlDomainElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RsldlPackage.DL_PATTERN_CONDITION: {
				DLPatternCondition dlPatternCondition = (DLPatternCondition)theEObject;
				T result = caseDLPatternCondition(dlPatternCondition);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RsldlPackage.DL_ALGHORITMIC_TRANSITION_SEQUENCE_ELEMENT: {
				DLAlghoritmicTransitionSequenceElement dlAlghoritmicTransitionSequenceElement = (DLAlghoritmicTransitionSequenceElement)theEObject;
				T result = caseDLAlghoritmicTransitionSequenceElement(dlAlghoritmicTransitionSequenceElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RsldlPackage.DL_ALGHORITMIC_TRANSITION_STEP: {
				DLAlghoritmicTransitionStep dlAlghoritmicTransitionStep = (DLAlghoritmicTransitionStep)theEObject;
				T result = caseDLAlghoritmicTransitionStep(dlAlghoritmicTransitionStep);
				if (result == null) result = caseDLAlghoritmicTransitionSequenceElement(dlAlghoritmicTransitionStep);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RsldlPackage.DL_NAMED_LINK: {
				DLNamedLink dlNamedLink = (DLNamedLink)theEObject;
				T result = caseDLNamedLink(dlNamedLink);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RsldlPackage.DL_DEREFERENCE_LINK: {
				DLDereferenceLink dlDereferenceLink = (DLDereferenceLink)theEObject;
				T result = caseDLDereferenceLink(dlDereferenceLink);
				if (result == null) result = caseDLNamedLink(dlDereferenceLink);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RsldlPackage.DL_CUSTOM_ALGHORITMIC_TRANSITION_STEP: {
				DLCustomAlghoritmicTransitionStep dlCustomAlghoritmicTransitionStep = (DLCustomAlghoritmicTransitionStep)theEObject;
				T result = caseDLCustomAlghoritmicTransitionStep(dlCustomAlghoritmicTransitionStep);
				if (result == null) result = caseDLAlghoritmicTransitionStep(dlCustomAlghoritmicTransitionStep);
				if (result == null) result = caseDLAlghoritmicTransitionSequenceElement(dlCustomAlghoritmicTransitionStep);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RsldlPackage.DL_PREDEFINED_ALGHORITMIC_TRANSITION_STEP: {
				DLPredefinedAlghoritmicTransitionStep dlPredefinedAlghoritmicTransitionStep = (DLPredefinedAlghoritmicTransitionStep)theEObject;
				T result = caseDLPredefinedAlghoritmicTransitionStep(dlPredefinedAlghoritmicTransitionStep);
				if (result == null) result = caseDLAlghoritmicTransitionStep(dlPredefinedAlghoritmicTransitionStep);
				if (result == null) result = caseDLAlghoritmicTransitionSequenceElement(dlPredefinedAlghoritmicTransitionStep);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RsldlPackage.DL_CONTROL_FLOW_STATEMENT: {
				DLControlFlowStatement dlControlFlowStatement = (DLControlFlowStatement)theEObject;
				T result = caseDLControlFlowStatement(dlControlFlowStatement);
				if (result == null) result = caseDLAlghoritmicTransitionSequenceElement(dlControlFlowStatement);
				if (result == null) result = caseDLReferencingElement(dlControlFlowStatement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RsldlPackage.DL_REFERENCING_ELEMENT: {
				DLReferencingElement dlReferencingElement = (DLReferencingElement)theEObject;
				T result = caseDLReferencingElement(dlReferencingElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case RsldlPackage.DL_PART_LINK: {
				DLPartLink dlPartLink = (DLPartLink)theEObject;
				T result = caseDLPartLink(dlPartLink);
				if (result == null) result = caseDLFeature(dlPartLink);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>DL Domain</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>DL Domain</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDLDomain(DLDomain object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>DL Relationship Participant</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>DL Relationship Participant</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDLRelationshipParticipant(DLRelationshipParticipant object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>DL Notion</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>DL Notion</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDLNotion(DLNotion object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>DL Primitive</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>DL Primitive</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDLPrimitive(DLPrimitive object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>DL Relationship</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>DL Relationship</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDLRelationship(DLRelationship object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>DL Relationship Participation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>DL Relationship Participation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDLRelationshipParticipation(DLRelationshipParticipation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>DL Diagram</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>DL Diagram</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDLDiagram(DLDiagram object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>DL Reference</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>DL Reference</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDLReference(DLReference object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>DL Transition</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>DL Transition</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDLTransition(DLTransition object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>DL Alghoritmic Transition</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>DL Alghoritmic Transition</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDLAlghoritmicTransition(DLAlghoritmicTransition object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>DL Pattern Based Transition</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>DL Pattern Based Transition</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDLPatternBasedTransition(DLPatternBasedTransition object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>DL Pattern Based Reference</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>DL Pattern Based Reference</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDLPatternBasedReference(DLPatternBasedReference object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>DL Data Based Reference</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>DL Data Based Reference</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDLDataBasedReference(DLDataBasedReference object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>DL Entity</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>DL Entity</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDLEntity(DLEntity object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>DL Property</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>DL Property</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDLProperty(DLProperty object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>DL Feature</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>DL Feature</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDLFeature(DLFeature object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>DL Condition</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>DL Condition</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDLCondition(DLCondition object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>DL Attribute Link</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>DL Attribute Link</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDLAttributeLink(DLAttributeLink object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>DL Inheritance Condition</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>DL Inheritance Condition</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDLInheritanceCondition(DLInheritanceCondition object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>DL Identity Condition</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>DL Identity Condition</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDLIdentityCondition(DLIdentityCondition object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>DL Validity Condition</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>DL Validity Condition</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDLValidityCondition(DLValidityCondition object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>DL Transition Pattern</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>DL Transition Pattern</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDLTransitionPattern(DLTransitionPattern object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>DL Condition Pattern</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>DL Condition Pattern</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDLConditionPattern(DLConditionPattern object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>DL Pattern</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>DL Pattern</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDLPattern(DLPattern object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>DL Subset</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>DL Subset</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDLSubset(DLSubset object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>DL Repository</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>DL Repository</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDLRepository(DLRepository object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>DL Domain Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>DL Domain Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDLDomainElement(DLDomainElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>DL Pattern Condition</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>DL Pattern Condition</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDLPatternCondition(DLPatternCondition object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>DL Alghoritmic Transition Sequence Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>DL Alghoritmic Transition Sequence Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDLAlghoritmicTransitionSequenceElement(DLAlghoritmicTransitionSequenceElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>DL Alghoritmic Transition Step</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>DL Alghoritmic Transition Step</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDLAlghoritmicTransitionStep(DLAlghoritmicTransitionStep object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>DL Named Link</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>DL Named Link</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDLNamedLink(DLNamedLink object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>DL Dereference Link</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>DL Dereference Link</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDLDereferenceLink(DLDereferenceLink object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>DL Custom Alghoritmic Transition Step</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>DL Custom Alghoritmic Transition Step</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDLCustomAlghoritmicTransitionStep(DLCustomAlghoritmicTransitionStep object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>DL Predefined Alghoritmic Transition Step</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>DL Predefined Alghoritmic Transition Step</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDLPredefinedAlghoritmicTransitionStep(DLPredefinedAlghoritmicTransitionStep object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>DL Control Flow Statement</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>DL Control Flow Statement</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDLControlFlowStatement(DLControlFlowStatement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>DL Referencing Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>DL Referencing Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDLReferencingElement(DLReferencingElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>DL Part Link</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>DL Part Link</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDLPartLink(DLPartLink object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	@Override
	public T defaultCase(EObject object) {
		return null;
	}

} //RsldlSwitch
