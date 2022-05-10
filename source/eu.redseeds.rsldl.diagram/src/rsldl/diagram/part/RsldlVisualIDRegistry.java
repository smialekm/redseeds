package rsldl.diagram.part;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;

import rsldl.DLDiagram;
import rsldl.DLRelationshipParticipation;
import rsldl.DLRelationshipParticipationDirection;
import rsldl.RsldlPackage;
import rsldl.diagram.edit.parts.DLAlghoritmicTransitionEditPart;
import rsldl.diagram.edit.parts.DLAlghoritmicTransitionNameEditPart;
import rsldl.diagram.edit.parts.DLAlghoritmicTransitionTypeEditPart;
import rsldl.diagram.edit.parts.DLAttributeLinkEditPart;
import rsldl.diagram.edit.parts.DLAttributeLinkNameEditPart;
import rsldl.diagram.edit.parts.DLDataBasedReferenceEditPart;
import rsldl.diagram.edit.parts.DLDataBasedReferenceNameEditPart;
import rsldl.diagram.edit.parts.DLDataBasedReferenceTypeEditPart;
import rsldl.diagram.edit.parts.DLDiagramEditPart;
import rsldl.diagram.edit.parts.DLEntityDLEntityCompartmentEditPart;
import rsldl.diagram.edit.parts.DLEntityEditPart;
import rsldl.diagram.edit.parts.DLEntityNameEditPart;
import rsldl.diagram.edit.parts.DLEntityTypeEditPart;
import rsldl.diagram.edit.parts.DLIdentityCondition2EditPart;
import rsldl.diagram.edit.parts.DLIdentityConditionEditPart;
import rsldl.diagram.edit.parts.DLIdentityConditionText2EditPart;
import rsldl.diagram.edit.parts.DLIdentityConditionTextEditPart;
import rsldl.diagram.edit.parts.DLIdentityConditionType2EditPart;
import rsldl.diagram.edit.parts.DLIdentityConditionTypeEditPart;
import rsldl.diagram.edit.parts.DLInheritanceCondition2EditPart;
import rsldl.diagram.edit.parts.DLInheritanceConditionEditPart;
import rsldl.diagram.edit.parts.DLInheritanceConditionText2EditPart;
import rsldl.diagram.edit.parts.DLInheritanceConditionTextEditPart;
import rsldl.diagram.edit.parts.DLInheritanceConditionType2EditPart;
import rsldl.diagram.edit.parts.DLInheritanceConditionTypeEditPart;
import rsldl.diagram.edit.parts.DLPatternBasedReferenceEditPart;
import rsldl.diagram.edit.parts.DLPatternBasedReferenceNameEditPart;
import rsldl.diagram.edit.parts.DLPatternBasedReferenceTypeEditPart;
import rsldl.diagram.edit.parts.DLPatternBasedTransitionEditPart;
import rsldl.diagram.edit.parts.DLPatternBasedTransitionNameEditPart;
import rsldl.diagram.edit.parts.DLPatternBasedTransitionTypeEditPart;
import rsldl.diagram.edit.parts.DLPrimitiveEditPart;
import rsldl.diagram.edit.parts.DLPrimitiveNameEditPart;
import rsldl.diagram.edit.parts.DLPropertyDLPropertyCompartmentEditPart;
import rsldl.diagram.edit.parts.DLPropertyEditPart;
import rsldl.diagram.edit.parts.DLPropertyNameEditPart;
import rsldl.diagram.edit.parts.DLPropertyTypeEditPart;
import rsldl.diagram.edit.parts.DLRelationshipParticipation2EditPart;
import rsldl.diagram.edit.parts.DLRelationshipParticipation3EditPart;
import rsldl.diagram.edit.parts.DLRelationshipParticipationEditPart;
import rsldl.diagram.edit.parts.DLRelationshipParticipationMultiplicity2EditPart;
import rsldl.diagram.edit.parts.DLRelationshipParticipationMultiplicity3EditPart;
import rsldl.diagram.edit.parts.DLRelationshipParticipationMultiplicityEditPart;
import rsldl.diagram.edit.parts.DLRelationshipParticipationName2EditPart;
import rsldl.diagram.edit.parts.DLRelationshipParticipationName3EditPart;
import rsldl.diagram.edit.parts.DLRelationshipParticipationNameEditPart;
import rsldl.diagram.edit.parts.DLValidityCondition2EditPart;
import rsldl.diagram.edit.parts.DLValidityConditionEditPart;
import rsldl.diagram.edit.parts.DLValidityConditionText2EditPart;
import rsldl.diagram.edit.parts.DLValidityConditionTextEditPart;
import rsldl.diagram.edit.parts.DLValidityConditionType2EditPart;
import rsldl.diagram.edit.parts.DLValidityConditionTypeEditPart;
import rsldl.diagram.edit.parts.WrappingLabelEditPart;

/**
 * This registry is used to determine which type of visual object should be
 * created for the corresponding Diagram, Node, ChildNode or Link represented
 * by a domain model object.
 * 
 * @generated
 */
public class RsldlVisualIDRegistry {

	/**
	 * @generated
	 */
	private static final String DEBUG_KEY = "eu.redseeds.rsldl.diagram/debug/visualID"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static int getVisualID(View view) {
		if (view instanceof Diagram) {
			if (DLDiagramEditPart.MODEL_ID.equals(view.getType())) {
				return DLDiagramEditPart.VISUAL_ID;
			} else {
				return -1;
			}
		}
		return rsldl.diagram.part.RsldlVisualIDRegistry.getVisualID(view
				.getType());
	}

	/**
	 * @generated
	 */
	public static String getModelID(View view) {
		View diagram = view.getDiagram();
		while (view != diagram) {
			EAnnotation annotation = view.getEAnnotation("Shortcut"); //$NON-NLS-1$
			if (annotation != null) {
				return (String) annotation.getDetails().get("modelID"); //$NON-NLS-1$
			}
			view = (View) view.eContainer();
		}
		return diagram != null ? diagram.getType() : null;
	}

	/**
	 * @generated
	 */
	public static int getVisualID(String type) {
		try {
			return Integer.parseInt(type);
		} catch (NumberFormatException e) {
			if (Boolean.TRUE.toString().equalsIgnoreCase(
					Platform.getDebugOption(DEBUG_KEY))) {
				RsldlDiagramEditorPlugin.getInstance().logError(
						"Unable to parse view type as a visualID number: "
								+ type);
			}
		}
		return -1;
	}

	/**
	 * @generated
	 */
	public static String getType(int visualID) {
		return Integer.toString(visualID);
	}

	/**
	 * @generated
	 */
	public static int getDiagramVisualID(EObject domainElement) {
		if (domainElement == null) {
			return -1;
		}
		if (RsldlPackage.eINSTANCE.getDLDiagram().isSuperTypeOf(
				domainElement.eClass())
				&& isDiagram((DLDiagram) domainElement)) {
			return DLDiagramEditPart.VISUAL_ID;
		}
		return -1;
	}

	/**
	 * @generated
	 */
	public static int getNodeVisualID(View containerView, EObject domainElement) {
		if (domainElement == null) {
			return -1;
		}
		String containerModelID = rsldl.diagram.part.RsldlVisualIDRegistry
				.getModelID(containerView);
		if (!DLDiagramEditPart.MODEL_ID.equals(containerModelID)) {
			return -1;
		}
		int containerVisualID;
		if (DLDiagramEditPart.MODEL_ID.equals(containerModelID)) {
			containerVisualID = rsldl.diagram.part.RsldlVisualIDRegistry
					.getVisualID(containerView);
		} else {
			if (containerView instanceof Diagram) {
				containerVisualID = DLDiagramEditPart.VISUAL_ID;
			} else {
				return -1;
			}
		}
		switch (containerVisualID) {
		case DLDiagramEditPart.VISUAL_ID:
			if (RsldlPackage.eINSTANCE.getDLEntity().isSuperTypeOf(
					domainElement.eClass())) {
				return DLEntityEditPart.VISUAL_ID;
			}
			if (RsldlPackage.eINSTANCE.getDLProperty().isSuperTypeOf(
					domainElement.eClass())) {
				return DLPropertyEditPart.VISUAL_ID;
			}
			if (RsldlPackage.eINSTANCE.getDLPrimitive().isSuperTypeOf(
					domainElement.eClass())) {
				return DLPrimitiveEditPart.VISUAL_ID;
			}
			if (RsldlPackage.eINSTANCE.getDLDataBasedReference().isSuperTypeOf(
					domainElement.eClass())) {
				return DLDataBasedReferenceEditPart.VISUAL_ID;
			}
			if (RsldlPackage.eINSTANCE.getDLPatternBasedReference()
					.isSuperTypeOf(domainElement.eClass())) {
				return DLPatternBasedReferenceEditPart.VISUAL_ID;
			}
			if (RsldlPackage.eINSTANCE.getDLAlghoritmicTransition()
					.isSuperTypeOf(domainElement.eClass())) {
				return DLAlghoritmicTransitionEditPart.VISUAL_ID;
			}
			if (RsldlPackage.eINSTANCE.getDLPatternBasedTransition()
					.isSuperTypeOf(domainElement.eClass())) {
				return DLPatternBasedTransitionEditPart.VISUAL_ID;
			}
			break;
		case DLEntityDLEntityCompartmentEditPart.VISUAL_ID:
			if (RsldlPackage.eINSTANCE.getDLIdentityCondition().isSuperTypeOf(
					domainElement.eClass())) {
				return DLIdentityConditionEditPart.VISUAL_ID;
			}
			if (RsldlPackage.eINSTANCE.getDLInheritanceCondition()
					.isSuperTypeOf(domainElement.eClass())) {
				return DLInheritanceConditionEditPart.VISUAL_ID;
			}
			if (RsldlPackage.eINSTANCE.getDLValidityCondition().isSuperTypeOf(
					domainElement.eClass())) {
				return DLValidityConditionEditPart.VISUAL_ID;
			}
			break;
		case DLPropertyDLPropertyCompartmentEditPart.VISUAL_ID:
			if (RsldlPackage.eINSTANCE.getDLIdentityCondition().isSuperTypeOf(
					domainElement.eClass())) {
				return DLIdentityCondition2EditPart.VISUAL_ID;
			}
			if (RsldlPackage.eINSTANCE.getDLInheritanceCondition()
					.isSuperTypeOf(domainElement.eClass())) {
				return DLInheritanceCondition2EditPart.VISUAL_ID;
			}
			if (RsldlPackage.eINSTANCE.getDLValidityCondition().isSuperTypeOf(
					domainElement.eClass())) {
				return DLValidityCondition2EditPart.VISUAL_ID;
			}
			break;
		}
		return -1;
	}

	/**
	 * @generated
	 */
	public static boolean canCreateNode(View containerView, int nodeVisualID) {
		String containerModelID = rsldl.diagram.part.RsldlVisualIDRegistry
				.getModelID(containerView);
		if (!DLDiagramEditPart.MODEL_ID.equals(containerModelID)) {
			return false;
		}
		int containerVisualID;
		if (DLDiagramEditPart.MODEL_ID.equals(containerModelID)) {
			containerVisualID = rsldl.diagram.part.RsldlVisualIDRegistry
					.getVisualID(containerView);
		} else {
			if (containerView instanceof Diagram) {
				containerVisualID = DLDiagramEditPart.VISUAL_ID;
			} else {
				return false;
			}
		}
		switch (containerVisualID) {
		case DLDiagramEditPart.VISUAL_ID:
			if (DLEntityEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (DLPropertyEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (DLPrimitiveEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (DLDataBasedReferenceEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (DLPatternBasedReferenceEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (DLAlghoritmicTransitionEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (DLPatternBasedTransitionEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case DLEntityEditPart.VISUAL_ID:
			if (DLEntityNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (DLEntityTypeEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (DLEntityDLEntityCompartmentEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case DLPropertyEditPart.VISUAL_ID:
			if (DLPropertyNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (DLPropertyTypeEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (DLPropertyDLPropertyCompartmentEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case DLPrimitiveEditPart.VISUAL_ID:
			if (DLPrimitiveNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (WrappingLabelEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case DLDataBasedReferenceEditPart.VISUAL_ID:
			if (DLDataBasedReferenceNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (DLDataBasedReferenceTypeEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case DLPatternBasedReferenceEditPart.VISUAL_ID:
			if (DLPatternBasedReferenceNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (DLPatternBasedReferenceTypeEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case DLAlghoritmicTransitionEditPart.VISUAL_ID:
			if (DLAlghoritmicTransitionNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (DLAlghoritmicTransitionTypeEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case DLPatternBasedTransitionEditPart.VISUAL_ID:
			if (DLPatternBasedTransitionNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (DLPatternBasedTransitionTypeEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case DLIdentityConditionEditPart.VISUAL_ID:
			if (DLIdentityConditionTypeEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (DLIdentityConditionTextEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case DLInheritanceConditionEditPart.VISUAL_ID:
			if (DLInheritanceConditionTypeEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (DLInheritanceConditionTextEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case DLValidityConditionEditPart.VISUAL_ID:
			if (DLValidityConditionTypeEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (DLValidityConditionTextEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case DLIdentityCondition2EditPart.VISUAL_ID:
			if (DLIdentityConditionType2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (DLIdentityConditionText2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case DLInheritanceCondition2EditPart.VISUAL_ID:
			if (DLInheritanceConditionType2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (DLInheritanceConditionText2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case DLValidityCondition2EditPart.VISUAL_ID:
			if (DLValidityConditionType2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (DLValidityConditionText2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case DLEntityDLEntityCompartmentEditPart.VISUAL_ID:
			if (DLIdentityConditionEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (DLInheritanceConditionEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (DLValidityConditionEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case DLPropertyDLPropertyCompartmentEditPart.VISUAL_ID:
			if (DLIdentityCondition2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (DLInheritanceCondition2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (DLValidityCondition2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case DLRelationshipParticipationEditPart.VISUAL_ID:
			if (DLRelationshipParticipationNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (DLRelationshipParticipationMultiplicityEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case DLRelationshipParticipation2EditPart.VISUAL_ID:
			if (DLRelationshipParticipationName2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (DLRelationshipParticipationMultiplicity2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case DLRelationshipParticipation3EditPart.VISUAL_ID:
			if (DLRelationshipParticipationName3EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (DLRelationshipParticipationMultiplicity3EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case DLAttributeLinkEditPart.VISUAL_ID:
			if (DLAttributeLinkNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		}
		return false;
	}

	/**
	 * @generated NOT
	 */
	public static int getLinkWithClassVisualID(EObject domainElement) {
		if (domainElement == null) {
			return -1;
		}
		if (RsldlPackage.eINSTANCE.getDLRelationshipParticipation()
				.isSuperTypeOf(domainElement.eClass())
				&& DLRelationshipParticipationDirection.UNDEFINED
						.equals(((DLRelationshipParticipation) domainElement)
								.getDirection())) {
			return DLRelationshipParticipationEditPart.VISUAL_ID;
		}
		if (RsldlPackage.eINSTANCE.getDLRelationshipParticipation()
				.isSuperTypeOf(domainElement.eClass())
				&& DLRelationshipParticipationDirection.SOURCE
						.equals(((DLRelationshipParticipation) domainElement)
								.getDirection())) {
			return DLRelationshipParticipation2EditPart.VISUAL_ID;
		}
		if (RsldlPackage.eINSTANCE.getDLRelationshipParticipation()
				.isSuperTypeOf(domainElement.eClass())
				&& DLRelationshipParticipationDirection.TARGET
						.equals(((DLRelationshipParticipation) domainElement)
								.getDirection())) {
			return DLRelationshipParticipation3EditPart.VISUAL_ID;
		}
		if (RsldlPackage.eINSTANCE.getDLAttributeLink().isSuperTypeOf(
				domainElement.eClass())) {
			return DLAttributeLinkEditPart.VISUAL_ID;
		}
		return -1;
	}

	/**
	 * User can change implementation of this method to handle some specific
	 * situations not covered by default logic.
	 * 
	 * @generated
	 */
	private static boolean isDiagram(DLDiagram element) {
		return true;
	}

}
