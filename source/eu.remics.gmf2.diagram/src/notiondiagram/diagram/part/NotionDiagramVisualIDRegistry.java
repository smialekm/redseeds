package notiondiagram.diagram.part;

import notiondiagram.NotionDiagram;
import notiondiagram.NotiondiagramPackage;
import notiondiagram.diagram.edit.parts.AttributeRelationEditPart;
import notiondiagram.diagram.edit.parts.DirectedRelationEditPart;
import notiondiagram.diagram.edit.parts.DirectedRelationSourceMultiplicityEditPart;
import notiondiagram.diagram.edit.parts.DirectedRelationStereotypeEditPart;
import notiondiagram.diagram.edit.parts.DirectedRelationTargetMultiplicityEditPart;
import notiondiagram.diagram.edit.parts.GeneralizationEditPart;
import notiondiagram.diagram.edit.parts.IndirectRelationEditPart;
import notiondiagram.diagram.edit.parts.IndirectRelationSourceMultiplicityEditPart;
import notiondiagram.diagram.edit.parts.IndirectRelationStereotypeEditPart;
import notiondiagram.diagram.edit.parts.IndirectRelationTargetMultiplicityEditPart;
import notiondiagram.diagram.edit.parts.NotionDiagramEditPart;
import notiondiagram.diagram.edit.parts.NotionEditPart;

import notiondiagram.diagram.edit.parts.NotionNameEditPart;
import notiondiagram.diagram.edit.parts.NotionNotionCompartmentEditPart;
import notiondiagram.diagram.edit.parts.NotionTypeEditPart;
import notiondiagram.diagram.edit.parts.PhraseEditPart;
import notiondiagram.diagram.edit.parts.PhraseTextEditPart;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;

/**
 * This registry is used to determine which type of visual object should be
 * created for the corresponding Diagram, Node, ChildNode or Link represented
 * by a domain model object.
 * 
 * @generated
 */
public class NotionDiagramVisualIDRegistry {

	/**
	 * @generated
	 */
	private static final String DEBUG_KEY = "eu.remics.gmf2.diagram/debug/visualID"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static int getVisualID(View view) {
		if (view instanceof Diagram) {
			if (NotionDiagramEditPart.MODEL_ID.equals(view.getType())) {
				return NotionDiagramEditPart.VISUAL_ID;
			} else {
				return -1;
			}
		}
		return notiondiagram.diagram.part.NotionDiagramVisualIDRegistry
				.getVisualID(view.getType());
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
				NotionDiagramDiagramEditorPlugin.getInstance().logError(
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
		if (NotiondiagramPackage.eINSTANCE.getNotionDiagram().isSuperTypeOf(
				domainElement.eClass())
				&& isDiagram((NotionDiagram) domainElement)) {
			return NotionDiagramEditPart.VISUAL_ID;
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
		String containerModelID = notiondiagram.diagram.part.NotionDiagramVisualIDRegistry
				.getModelID(containerView);
		if (!NotionDiagramEditPart.MODEL_ID.equals(containerModelID)) {
			return -1;
		}
		int containerVisualID;
		if (NotionDiagramEditPart.MODEL_ID.equals(containerModelID)) {
			containerVisualID = notiondiagram.diagram.part.NotionDiagramVisualIDRegistry
					.getVisualID(containerView);
		} else {
			if (containerView instanceof Diagram) {
				containerVisualID = NotionDiagramEditPart.VISUAL_ID;
			} else {
				return -1;
			}
		}
		switch (containerVisualID) {
		case NotionDiagramEditPart.VISUAL_ID:
			if (NotiondiagramPackage.eINSTANCE.getNotion().isSuperTypeOf(
					domainElement.eClass())) {
				return NotionEditPart.VISUAL_ID;
			}
			break;
		case NotionNotionCompartmentEditPart.VISUAL_ID:
			if (NotiondiagramPackage.eINSTANCE.getPhrase().isSuperTypeOf(
					domainElement.eClass())) {
				return PhraseEditPart.VISUAL_ID;
			}
			break;
		}
		return -1;
	}

	/**
	 * @generated
	 */
	public static boolean canCreateNode(View containerView, int nodeVisualID) {
		String containerModelID = notiondiagram.diagram.part.NotionDiagramVisualIDRegistry
				.getModelID(containerView);
		if (!NotionDiagramEditPart.MODEL_ID.equals(containerModelID)) {
			return false;
		}
		int containerVisualID;
		if (NotionDiagramEditPart.MODEL_ID.equals(containerModelID)) {
			containerVisualID = notiondiagram.diagram.part.NotionDiagramVisualIDRegistry
					.getVisualID(containerView);
		} else {
			if (containerView instanceof Diagram) {
				containerVisualID = NotionDiagramEditPart.VISUAL_ID;
			} else {
				return false;
			}
		}
		switch (containerVisualID) {
		case NotionDiagramEditPart.VISUAL_ID:
			if (NotionEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case NotionEditPart.VISUAL_ID:
			if (NotionNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (NotionTypeEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (NotionNotionCompartmentEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case PhraseEditPart.VISUAL_ID:
			if (PhraseTextEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case NotionNotionCompartmentEditPart.VISUAL_ID:
			if (PhraseEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case DirectedRelationEditPart.VISUAL_ID:
			if (DirectedRelationSourceMultiplicityEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (DirectedRelationTargetMultiplicityEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (DirectedRelationStereotypeEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case IndirectRelationEditPart.VISUAL_ID:
			if (IndirectRelationSourceMultiplicityEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (IndirectRelationTargetMultiplicityEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (IndirectRelationStereotypeEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		}
		return false;
	}

	/**
	 * @generated
	 */
	public static int getLinkWithClassVisualID(EObject domainElement) {
		if (domainElement == null) {
			return -1;
		}
		if (NotiondiagramPackage.eINSTANCE.getGeneralization().isSuperTypeOf(
				domainElement.eClass())) {
			return GeneralizationEditPart.VISUAL_ID;
		}
		if (NotiondiagramPackage.eINSTANCE.getAttributeRelation()
				.isSuperTypeOf(domainElement.eClass())) {
			return AttributeRelationEditPart.VISUAL_ID;
		}
		if (NotiondiagramPackage.eINSTANCE.getDirectedRelation().isSuperTypeOf(
				domainElement.eClass())) {
			return DirectedRelationEditPart.VISUAL_ID;
		}
		if (NotiondiagramPackage.eINSTANCE.getIndirectRelation().isSuperTypeOf(
				domainElement.eClass())) {
			return IndirectRelationEditPart.VISUAL_ID;
		}
		return -1;
	}

	/**
	 * User can change implementation of this method to handle some specific
	 * situations not covered by default logic.
	 * 
	 * @generated
	 */
	private static boolean isDiagram(NotionDiagram element) {
		return true;
	}

}
