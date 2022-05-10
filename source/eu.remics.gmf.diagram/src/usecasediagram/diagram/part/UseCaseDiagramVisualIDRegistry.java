package usecasediagram.diagram.part;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;

import usecasediagram.UseCaseDiagram;
import usecasediagram.UsecasediagramPackage;
import usecasediagram.diagram.edit.parts.ActorEditPart;
import usecasediagram.diagram.edit.parts.ActorNameEditPart;
import usecasediagram.diagram.edit.parts.AssociationEditPart;
import usecasediagram.diagram.edit.parts.InvokeEditPart;
import usecasediagram.diagram.edit.parts.UseCaseDiagramEditPart;
import usecasediagram.diagram.edit.parts.UseCaseEditPart;
import usecasediagram.diagram.edit.parts.UseCaseNameEditPart;

/**
 * This registry is used to determine which type of visual object should be
 * created for the corresponding Diagram, Node, ChildNode or Link represented
 * by a domain model object.
 * 
 * @generated
 */
public class UseCaseDiagramVisualIDRegistry {

	/**
	 * @generated
	 */
	private static final String DEBUG_KEY = "eu.remics.gmf.diagram/debug/visualID"; //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static int getVisualID(View view) {
		if (view instanceof Diagram) {
			if (UseCaseDiagramEditPart.MODEL_ID.equals(view.getType())) {
				return UseCaseDiagramEditPart.VISUAL_ID;
			} else {
				return -1;
			}
		}
		return usecasediagram.diagram.part.UseCaseDiagramVisualIDRegistry
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
				UseCaseDiagramDiagramEditorPlugin.getInstance().logError(
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
		if (UsecasediagramPackage.eINSTANCE.getUseCaseDiagram().isSuperTypeOf(
				domainElement.eClass())
				&& isDiagram((UseCaseDiagram) domainElement)) {
			return UseCaseDiagramEditPart.VISUAL_ID;
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
		String containerModelID = usecasediagram.diagram.part.UseCaseDiagramVisualIDRegistry
				.getModelID(containerView);
		if (!UseCaseDiagramEditPart.MODEL_ID.equals(containerModelID)) {
			return -1;
		}
		int containerVisualID;
		if (UseCaseDiagramEditPart.MODEL_ID.equals(containerModelID)) {
			containerVisualID = usecasediagram.diagram.part.UseCaseDiagramVisualIDRegistry
					.getVisualID(containerView);
		} else {
			if (containerView instanceof Diagram) {
				containerVisualID = UseCaseDiagramEditPart.VISUAL_ID;
			} else {
				return -1;
			}
		}
		switch (containerVisualID) {
		case UseCaseDiagramEditPart.VISUAL_ID:
			if (UsecasediagramPackage.eINSTANCE.getActor().isSuperTypeOf(
					domainElement.eClass())) {
				return ActorEditPart.VISUAL_ID;
			}
			if (UsecasediagramPackage.eINSTANCE.getUseCase().isSuperTypeOf(
					domainElement.eClass())) {
				return UseCaseEditPart.VISUAL_ID;
			}
			break;
		}
		return -1;
	}

	/**
	 * @generated
	 */
	public static boolean canCreateNode(View containerView, int nodeVisualID) {
		String containerModelID = usecasediagram.diagram.part.UseCaseDiagramVisualIDRegistry
				.getModelID(containerView);
		if (!UseCaseDiagramEditPart.MODEL_ID.equals(containerModelID)) {
			return false;
		}
		int containerVisualID;
		if (UseCaseDiagramEditPart.MODEL_ID.equals(containerModelID)) {
			containerVisualID = usecasediagram.diagram.part.UseCaseDiagramVisualIDRegistry
					.getVisualID(containerView);
		} else {
			if (containerView instanceof Diagram) {
				containerVisualID = UseCaseDiagramEditPart.VISUAL_ID;
			} else {
				return false;
			}
		}
		switch (containerVisualID) {
		case UseCaseDiagramEditPart.VISUAL_ID:
			if (ActorEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (UseCaseEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case ActorEditPart.VISUAL_ID:
			if (ActorNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case UseCaseEditPart.VISUAL_ID:
			if (UseCaseNameEditPart.VISUAL_ID == nodeVisualID) {
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
		if (UsecasediagramPackage.eINSTANCE.getInvoke().isSuperTypeOf(
				domainElement.eClass())) {
			return InvokeEditPart.VISUAL_ID;
		}
		if (UsecasediagramPackage.eINSTANCE.getAssociation().isSuperTypeOf(
				domainElement.eClass())) {
			return AssociationEditPart.VISUAL_ID;
		}
		return -1;
	}

	/**
	 * User can change implementation of this method to handle some specific
	 * situations not covered by default logic.
	 * 
	 * @generated
	 */
	private static boolean isDiagram(UseCaseDiagram element) {
		return true;
	}

}
