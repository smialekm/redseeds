/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package rsldl;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see rsldl.RsldlPackage
 * @generated
 */
public interface RsldlFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	RsldlFactory eINSTANCE = rsldl.impl.RsldlFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>DL Domain</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>DL Domain</em>'.
	 * @generated
	 */
	DLDomain createDLDomain();

	/**
	 * Returns a new object of class '<em>DL Primitive</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>DL Primitive</em>'.
	 * @generated
	 */
	DLPrimitive createDLPrimitive();

	/**
	 * Returns a new object of class '<em>DL Relationship Participation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>DL Relationship Participation</em>'.
	 * @generated
	 */
	DLRelationshipParticipation createDLRelationshipParticipation();

	/**
	 * Returns a new object of class '<em>DL Diagram</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>DL Diagram</em>'.
	 * @generated
	 */
	DLDiagram createDLDiagram();

	/**
	 * Returns a new object of class '<em>DL Alghoritmic Transition</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>DL Alghoritmic Transition</em>'.
	 * @generated
	 */
	DLAlghoritmicTransition createDLAlghoritmicTransition();

	/**
	 * Returns a new object of class '<em>DL Pattern Based Transition</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>DL Pattern Based Transition</em>'.
	 * @generated
	 */
	DLPatternBasedTransition createDLPatternBasedTransition();

	/**
	 * Returns a new object of class '<em>DL Pattern Based Reference</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>DL Pattern Based Reference</em>'.
	 * @generated
	 */
	DLPatternBasedReference createDLPatternBasedReference();

	/**
	 * Returns a new object of class '<em>DL Data Based Reference</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>DL Data Based Reference</em>'.
	 * @generated
	 */
	DLDataBasedReference createDLDataBasedReference();

	/**
	 * Returns a new object of class '<em>DL Entity</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>DL Entity</em>'.
	 * @generated
	 */
	DLEntity createDLEntity();

	/**
	 * Returns a new object of class '<em>DL Property</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>DL Property</em>'.
	 * @generated
	 */
	DLProperty createDLProperty();

	/**
	 * Returns a new object of class '<em>DL Attribute Link</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>DL Attribute Link</em>'.
	 * @generated
	 */
	DLAttributeLink createDLAttributeLink();

	/**
	 * Returns a new object of class '<em>DL Inheritance Condition</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>DL Inheritance Condition</em>'.
	 * @generated
	 */
	DLInheritanceCondition createDLInheritanceCondition();

	/**
	 * Returns a new object of class '<em>DL Identity Condition</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>DL Identity Condition</em>'.
	 * @generated
	 */
	DLIdentityCondition createDLIdentityCondition();

	/**
	 * Returns a new object of class '<em>DL Validity Condition</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>DL Validity Condition</em>'.
	 * @generated
	 */
	DLValidityCondition createDLValidityCondition();

	/**
	 * Returns a new object of class '<em>DL Transition Pattern</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>DL Transition Pattern</em>'.
	 * @generated
	 */
	DLTransitionPattern createDLTransitionPattern();

	/**
	 * Returns a new object of class '<em>DL Condition Pattern</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>DL Condition Pattern</em>'.
	 * @generated
	 */
	DLConditionPattern createDLConditionPattern();

	/**
	 * Returns a new object of class '<em>DL Repository</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>DL Repository</em>'.
	 * @generated
	 */
	DLRepository createDLRepository();

	/**
	 * Returns a new object of class '<em>DL Domain Element</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>DL Domain Element</em>'.
	 * @generated
	 */
	DLDomainElement createDLDomainElement();

	/**
	 * Returns a new object of class '<em>DL Dereference Link</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>DL Dereference Link</em>'.
	 * @generated
	 */
	DLDereferenceLink createDLDereferenceLink();

	/**
	 * Returns a new object of class '<em>DL Custom Alghoritmic Transition Step</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>DL Custom Alghoritmic Transition Step</em>'.
	 * @generated
	 */
	DLCustomAlghoritmicTransitionStep createDLCustomAlghoritmicTransitionStep();

	/**
	 * Returns a new object of class '<em>DL Predefined Alghoritmic Transition Step</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>DL Predefined Alghoritmic Transition Step</em>'.
	 * @generated
	 */
	DLPredefinedAlghoritmicTransitionStep createDLPredefinedAlghoritmicTransitionStep();

	/**
	 * Returns a new object of class '<em>DL Control Flow Statement</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>DL Control Flow Statement</em>'.
	 * @generated
	 */
	DLControlFlowStatement createDLControlFlowStatement();

	/**
	 * Returns a new object of class '<em>DL Part Link</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>DL Part Link</em>'.
	 * @generated
	 */
	DLPartLink createDLPartLink();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	RsldlPackage getRsldlPackage();

} //RsldlFactory
