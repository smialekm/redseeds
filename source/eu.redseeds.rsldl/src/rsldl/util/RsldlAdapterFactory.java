/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package rsldl.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

import rsldl.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see rsldl.RsldlPackage
 * @generated
 */
public class RsldlAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static RsldlPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RsldlAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = RsldlPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RsldlSwitch<Adapter> modelSwitch =
		new RsldlSwitch<Adapter>() {
			@Override
			public Adapter caseDLDomain(DLDomain object) {
				return createDLDomainAdapter();
			}
			@Override
			public Adapter caseDLRelationshipParticipant(DLRelationshipParticipant object) {
				return createDLRelationshipParticipantAdapter();
			}
			@Override
			public Adapter caseDLNotion(DLNotion object) {
				return createDLNotionAdapter();
			}
			@Override
			public Adapter caseDLPrimitive(DLPrimitive object) {
				return createDLPrimitiveAdapter();
			}
			@Override
			public Adapter caseDLRelationship(DLRelationship object) {
				return createDLRelationshipAdapter();
			}
			@Override
			public Adapter caseDLRelationshipParticipation(DLRelationshipParticipation object) {
				return createDLRelationshipParticipationAdapter();
			}
			@Override
			public Adapter caseDLDiagram(DLDiagram object) {
				return createDLDiagramAdapter();
			}
			@Override
			public Adapter caseDLReference(DLReference object) {
				return createDLReferenceAdapter();
			}
			@Override
			public Adapter caseDLTransition(DLTransition object) {
				return createDLTransitionAdapter();
			}
			@Override
			public Adapter caseDLAlghoritmicTransition(DLAlghoritmicTransition object) {
				return createDLAlghoritmicTransitionAdapter();
			}
			@Override
			public Adapter caseDLPatternBasedTransition(DLPatternBasedTransition object) {
				return createDLPatternBasedTransitionAdapter();
			}
			@Override
			public Adapter caseDLPatternBasedReference(DLPatternBasedReference object) {
				return createDLPatternBasedReferenceAdapter();
			}
			@Override
			public Adapter caseDLDataBasedReference(DLDataBasedReference object) {
				return createDLDataBasedReferenceAdapter();
			}
			@Override
			public Adapter caseDLEntity(DLEntity object) {
				return createDLEntityAdapter();
			}
			@Override
			public Adapter caseDLProperty(DLProperty object) {
				return createDLPropertyAdapter();
			}
			@Override
			public Adapter caseDLFeature(DLFeature object) {
				return createDLFeatureAdapter();
			}
			@Override
			public Adapter caseDLCondition(DLCondition object) {
				return createDLConditionAdapter();
			}
			@Override
			public Adapter caseDLAttributeLink(DLAttributeLink object) {
				return createDLAttributeLinkAdapter();
			}
			@Override
			public Adapter caseDLInheritanceCondition(DLInheritanceCondition object) {
				return createDLInheritanceConditionAdapter();
			}
			@Override
			public Adapter caseDLIdentityCondition(DLIdentityCondition object) {
				return createDLIdentityConditionAdapter();
			}
			@Override
			public Adapter caseDLValidityCondition(DLValidityCondition object) {
				return createDLValidityConditionAdapter();
			}
			@Override
			public Adapter caseDLTransitionPattern(DLTransitionPattern object) {
				return createDLTransitionPatternAdapter();
			}
			@Override
			public Adapter caseDLConditionPattern(DLConditionPattern object) {
				return createDLConditionPatternAdapter();
			}
			@Override
			public Adapter caseDLPattern(DLPattern object) {
				return createDLPatternAdapter();
			}
			@Override
			public Adapter caseDLSubset(DLSubset object) {
				return createDLSubsetAdapter();
			}
			@Override
			public Adapter caseDLRepository(DLRepository object) {
				return createDLRepositoryAdapter();
			}
			@Override
			public Adapter caseDLDomainElement(DLDomainElement object) {
				return createDLDomainElementAdapter();
			}
			@Override
			public Adapter caseDLPatternCondition(DLPatternCondition object) {
				return createDLPatternConditionAdapter();
			}
			@Override
			public Adapter caseDLAlghoritmicTransitionSequenceElement(DLAlghoritmicTransitionSequenceElement object) {
				return createDLAlghoritmicTransitionSequenceElementAdapter();
			}
			@Override
			public Adapter caseDLAlghoritmicTransitionStep(DLAlghoritmicTransitionStep object) {
				return createDLAlghoritmicTransitionStepAdapter();
			}
			@Override
			public Adapter caseDLNamedLink(DLNamedLink object) {
				return createDLNamedLinkAdapter();
			}
			@Override
			public Adapter caseDLDereferenceLink(DLDereferenceLink object) {
				return createDLDereferenceLinkAdapter();
			}
			@Override
			public Adapter caseDLCustomAlghoritmicTransitionStep(DLCustomAlghoritmicTransitionStep object) {
				return createDLCustomAlghoritmicTransitionStepAdapter();
			}
			@Override
			public Adapter caseDLPredefinedAlghoritmicTransitionStep(DLPredefinedAlghoritmicTransitionStep object) {
				return createDLPredefinedAlghoritmicTransitionStepAdapter();
			}
			@Override
			public Adapter caseDLControlFlowStatement(DLControlFlowStatement object) {
				return createDLControlFlowStatementAdapter();
			}
			@Override
			public Adapter caseDLReferencingElement(DLReferencingElement object) {
				return createDLReferencingElementAdapter();
			}
			@Override
			public Adapter caseDLPartLink(DLPartLink object) {
				return createDLPartLinkAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link rsldl.DLDomain <em>DL Domain</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see rsldl.DLDomain
	 * @generated
	 */
	public Adapter createDLDomainAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link rsldl.DLRelationshipParticipant <em>DL Relationship Participant</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see rsldl.DLRelationshipParticipant
	 * @generated
	 */
	public Adapter createDLRelationshipParticipantAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link rsldl.DLNotion <em>DL Notion</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see rsldl.DLNotion
	 * @generated
	 */
	public Adapter createDLNotionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link rsldl.DLPrimitive <em>DL Primitive</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see rsldl.DLPrimitive
	 * @generated
	 */
	public Adapter createDLPrimitiveAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link rsldl.DLRelationship <em>DL Relationship</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see rsldl.DLRelationship
	 * @generated
	 */
	public Adapter createDLRelationshipAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link rsldl.DLRelationshipParticipation <em>DL Relationship Participation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see rsldl.DLRelationshipParticipation
	 * @generated
	 */
	public Adapter createDLRelationshipParticipationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link rsldl.DLDiagram <em>DL Diagram</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see rsldl.DLDiagram
	 * @generated
	 */
	public Adapter createDLDiagramAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link rsldl.DLReference <em>DL Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see rsldl.DLReference
	 * @generated
	 */
	public Adapter createDLReferenceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link rsldl.DLTransition <em>DL Transition</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see rsldl.DLTransition
	 * @generated
	 */
	public Adapter createDLTransitionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link rsldl.DLAlghoritmicTransition <em>DL Alghoritmic Transition</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see rsldl.DLAlghoritmicTransition
	 * @generated
	 */
	public Adapter createDLAlghoritmicTransitionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link rsldl.DLPatternBasedTransition <em>DL Pattern Based Transition</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see rsldl.DLPatternBasedTransition
	 * @generated
	 */
	public Adapter createDLPatternBasedTransitionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link rsldl.DLPatternBasedReference <em>DL Pattern Based Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see rsldl.DLPatternBasedReference
	 * @generated
	 */
	public Adapter createDLPatternBasedReferenceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link rsldl.DLDataBasedReference <em>DL Data Based Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see rsldl.DLDataBasedReference
	 * @generated
	 */
	public Adapter createDLDataBasedReferenceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link rsldl.DLEntity <em>DL Entity</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see rsldl.DLEntity
	 * @generated
	 */
	public Adapter createDLEntityAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link rsldl.DLProperty <em>DL Property</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see rsldl.DLProperty
	 * @generated
	 */
	public Adapter createDLPropertyAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link rsldl.DLFeature <em>DL Feature</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see rsldl.DLFeature
	 * @generated
	 */
	public Adapter createDLFeatureAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link rsldl.DLCondition <em>DL Condition</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see rsldl.DLCondition
	 * @generated
	 */
	public Adapter createDLConditionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link rsldl.DLAttributeLink <em>DL Attribute Link</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see rsldl.DLAttributeLink
	 * @generated
	 */
	public Adapter createDLAttributeLinkAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link rsldl.DLInheritanceCondition <em>DL Inheritance Condition</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see rsldl.DLInheritanceCondition
	 * @generated
	 */
	public Adapter createDLInheritanceConditionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link rsldl.DLIdentityCondition <em>DL Identity Condition</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see rsldl.DLIdentityCondition
	 * @generated
	 */
	public Adapter createDLIdentityConditionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link rsldl.DLValidityCondition <em>DL Validity Condition</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see rsldl.DLValidityCondition
	 * @generated
	 */
	public Adapter createDLValidityConditionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link rsldl.DLTransitionPattern <em>DL Transition Pattern</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see rsldl.DLTransitionPattern
	 * @generated
	 */
	public Adapter createDLTransitionPatternAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link rsldl.DLConditionPattern <em>DL Condition Pattern</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see rsldl.DLConditionPattern
	 * @generated
	 */
	public Adapter createDLConditionPatternAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link rsldl.DLPattern <em>DL Pattern</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see rsldl.DLPattern
	 * @generated
	 */
	public Adapter createDLPatternAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link rsldl.DLSubset <em>DL Subset</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see rsldl.DLSubset
	 * @generated
	 */
	public Adapter createDLSubsetAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link rsldl.DLRepository <em>DL Repository</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see rsldl.DLRepository
	 * @generated
	 */
	public Adapter createDLRepositoryAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link rsldl.DLDomainElement <em>DL Domain Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see rsldl.DLDomainElement
	 * @generated
	 */
	public Adapter createDLDomainElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link rsldl.DLPatternCondition <em>DL Pattern Condition</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see rsldl.DLPatternCondition
	 * @generated
	 */
	public Adapter createDLPatternConditionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link rsldl.DLAlghoritmicTransitionSequenceElement <em>DL Alghoritmic Transition Sequence Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see rsldl.DLAlghoritmicTransitionSequenceElement
	 * @generated
	 */
	public Adapter createDLAlghoritmicTransitionSequenceElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link rsldl.DLAlghoritmicTransitionStep <em>DL Alghoritmic Transition Step</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see rsldl.DLAlghoritmicTransitionStep
	 * @generated
	 */
	public Adapter createDLAlghoritmicTransitionStepAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link rsldl.DLNamedLink <em>DL Named Link</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see rsldl.DLNamedLink
	 * @generated
	 */
	public Adapter createDLNamedLinkAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link rsldl.DLDereferenceLink <em>DL Dereference Link</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see rsldl.DLDereferenceLink
	 * @generated
	 */
	public Adapter createDLDereferenceLinkAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link rsldl.DLCustomAlghoritmicTransitionStep <em>DL Custom Alghoritmic Transition Step</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see rsldl.DLCustomAlghoritmicTransitionStep
	 * @generated
	 */
	public Adapter createDLCustomAlghoritmicTransitionStepAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link rsldl.DLPredefinedAlghoritmicTransitionStep <em>DL Predefined Alghoritmic Transition Step</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see rsldl.DLPredefinedAlghoritmicTransitionStep
	 * @generated
	 */
	public Adapter createDLPredefinedAlghoritmicTransitionStepAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link rsldl.DLControlFlowStatement <em>DL Control Flow Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see rsldl.DLControlFlowStatement
	 * @generated
	 */
	public Adapter createDLControlFlowStatementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link rsldl.DLReferencingElement <em>DL Referencing Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see rsldl.DLReferencingElement
	 * @generated
	 */
	public Adapter createDLReferencingElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link rsldl.DLPartLink <em>DL Part Link</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see rsldl.DLPartLink
	 * @generated
	 */
	public Adapter createDLPartLinkAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //RsldlAdapterFactory
