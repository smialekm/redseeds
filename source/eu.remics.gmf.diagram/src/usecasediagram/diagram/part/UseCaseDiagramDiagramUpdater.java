package usecasediagram.diagram.part;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gmf.runtime.notation.View;

import usecasediagram.Actor;
import usecasediagram.Association;
import usecasediagram.Invoke;
import usecasediagram.UseCase;
import usecasediagram.UseCaseDiagram;
import usecasediagram.UsecasediagramPackage;
import usecasediagram.diagram.edit.parts.ActorEditPart;
import usecasediagram.diagram.edit.parts.AssociationEditPart;
import usecasediagram.diagram.edit.parts.InvokeEditPart;
import usecasediagram.diagram.edit.parts.UseCaseDiagramEditPart;
import usecasediagram.diagram.edit.parts.UseCaseEditPart;
import usecasediagram.diagram.providers.UseCaseDiagramElementTypes;

/**
 * @generated
 */
public class UseCaseDiagramDiagramUpdater {

	/**
	 * @generated
	 */
	public static List<UseCaseDiagramNodeDescriptor> getSemanticChildren(
			View view) {
		switch (UseCaseDiagramVisualIDRegistry.getVisualID(view)) {
		case UseCaseDiagramEditPart.VISUAL_ID:
			return getUseCaseDiagram_1000SemanticChildren(view);
		}
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<UseCaseDiagramNodeDescriptor> getUseCaseDiagram_1000SemanticChildren(
			View view) {
		if (!view.isSetElement()) {
			return Collections.emptyList();
		}
		UseCaseDiagram modelElement = (UseCaseDiagram) view.getElement();
		LinkedList<UseCaseDiagramNodeDescriptor> result = new LinkedList<UseCaseDiagramNodeDescriptor>();
		for (Iterator<?> it = modelElement.getActors().iterator(); it.hasNext();) {
			Actor childElement = (Actor) it.next();
			int visualID = UseCaseDiagramVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == ActorEditPart.VISUAL_ID) {
				result.add(new UseCaseDiagramNodeDescriptor(childElement,
						visualID));
				continue;
			}
		}
		for (Iterator<?> it = modelElement.getUsecases().iterator(); it
				.hasNext();) {
			UseCase childElement = (UseCase) it.next();
			int visualID = UseCaseDiagramVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == UseCaseEditPart.VISUAL_ID) {
				result.add(new UseCaseDiagramNodeDescriptor(childElement,
						visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List<UseCaseDiagramLinkDescriptor> getContainedLinks(View view) {
		switch (UseCaseDiagramVisualIDRegistry.getVisualID(view)) {
		case UseCaseDiagramEditPart.VISUAL_ID:
			return getUseCaseDiagram_1000ContainedLinks(view);
		case ActorEditPart.VISUAL_ID:
			return getActor_2001ContainedLinks(view);
		case UseCaseEditPart.VISUAL_ID:
			return getUseCase_2002ContainedLinks(view);
		case InvokeEditPart.VISUAL_ID:
			return getInvoke_4001ContainedLinks(view);
		case AssociationEditPart.VISUAL_ID:
			return getAssociation_4002ContainedLinks(view);
		}
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<UseCaseDiagramLinkDescriptor> getIncomingLinks(View view) {
		switch (UseCaseDiagramVisualIDRegistry.getVisualID(view)) {
		case ActorEditPart.VISUAL_ID:
			return getActor_2001IncomingLinks(view);
		case UseCaseEditPart.VISUAL_ID:
			return getUseCase_2002IncomingLinks(view);
		case InvokeEditPart.VISUAL_ID:
			return getInvoke_4001IncomingLinks(view);
		case AssociationEditPart.VISUAL_ID:
			return getAssociation_4002IncomingLinks(view);
		}
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<UseCaseDiagramLinkDescriptor> getOutgoingLinks(View view) {
		switch (UseCaseDiagramVisualIDRegistry.getVisualID(view)) {
		case ActorEditPart.VISUAL_ID:
			return getActor_2001OutgoingLinks(view);
		case UseCaseEditPart.VISUAL_ID:
			return getUseCase_2002OutgoingLinks(view);
		case InvokeEditPart.VISUAL_ID:
			return getInvoke_4001OutgoingLinks(view);
		case AssociationEditPart.VISUAL_ID:
			return getAssociation_4002OutgoingLinks(view);
		}
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<UseCaseDiagramLinkDescriptor> getUseCaseDiagram_1000ContainedLinks(
			View view) {
		UseCaseDiagram modelElement = (UseCaseDiagram) view.getElement();
		LinkedList<UseCaseDiagramLinkDescriptor> result = new LinkedList<UseCaseDiagramLinkDescriptor>();
		result.addAll(getContainedTypeModelFacetLinks_Invoke_4001(modelElement));
		result.addAll(getContainedTypeModelFacetLinks_Association_4002(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<UseCaseDiagramLinkDescriptor> getActor_2001ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<UseCaseDiagramLinkDescriptor> getUseCase_2002ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<UseCaseDiagramLinkDescriptor> getInvoke_4001ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<UseCaseDiagramLinkDescriptor> getAssociation_4002ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<UseCaseDiagramLinkDescriptor> getActor_2001IncomingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<UseCaseDiagramLinkDescriptor> getUseCase_2002IncomingLinks(
			View view) {
		UseCase modelElement = (UseCase) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<UseCaseDiagramLinkDescriptor> result = new LinkedList<UseCaseDiagramLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_Invoke_4001(modelElement,
				crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_Association_4002(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<UseCaseDiagramLinkDescriptor> getInvoke_4001IncomingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<UseCaseDiagramLinkDescriptor> getAssociation_4002IncomingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<UseCaseDiagramLinkDescriptor> getActor_2001OutgoingLinks(
			View view) {
		Actor modelElement = (Actor) view.getElement();
		LinkedList<UseCaseDiagramLinkDescriptor> result = new LinkedList<UseCaseDiagramLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_Association_4002(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<UseCaseDiagramLinkDescriptor> getUseCase_2002OutgoingLinks(
			View view) {
		UseCase modelElement = (UseCase) view.getElement();
		LinkedList<UseCaseDiagramLinkDescriptor> result = new LinkedList<UseCaseDiagramLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_Invoke_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<UseCaseDiagramLinkDescriptor> getInvoke_4001OutgoingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<UseCaseDiagramLinkDescriptor> getAssociation_4002OutgoingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	private static Collection<UseCaseDiagramLinkDescriptor> getContainedTypeModelFacetLinks_Invoke_4001(
			UseCaseDiagram container) {
		LinkedList<UseCaseDiagramLinkDescriptor> result = new LinkedList<UseCaseDiagramLinkDescriptor>();
		for (Iterator<?> links = container.getInvokes().iterator(); links
				.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof Invoke) {
				continue;
			}
			Invoke link = (Invoke) linkObject;
			if (InvokeEditPart.VISUAL_ID != UseCaseDiagramVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			UseCase dst = link.getTarget();
			UseCase src = link.getSource();
			result.add(new UseCaseDiagramLinkDescriptor(src, dst, link,
					UseCaseDiagramElementTypes.Invoke_4001,
					InvokeEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<UseCaseDiagramLinkDescriptor> getContainedTypeModelFacetLinks_Association_4002(
			UseCaseDiagram container) {
		LinkedList<UseCaseDiagramLinkDescriptor> result = new LinkedList<UseCaseDiagramLinkDescriptor>();
		for (Iterator<?> links = container.getAssociations().iterator(); links
				.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof Association) {
				continue;
			}
			Association link = (Association) linkObject;
			if (AssociationEditPart.VISUAL_ID != UseCaseDiagramVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			UseCase dst = link.getTarget();
			Actor src = link.getSource();
			result.add(new UseCaseDiagramLinkDescriptor(src, dst, link,
					UseCaseDiagramElementTypes.Association_4002,
					AssociationEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<UseCaseDiagramLinkDescriptor> getIncomingTypeModelFacetLinks_Invoke_4001(
			UseCase target,
			Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences) {
		LinkedList<UseCaseDiagramLinkDescriptor> result = new LinkedList<UseCaseDiagramLinkDescriptor>();
		Collection<EStructuralFeature.Setting> settings = crossReferences
				.get(target);
		for (EStructuralFeature.Setting setting : settings) {
			if (setting.getEStructuralFeature() != UsecasediagramPackage.eINSTANCE
					.getInvoke_Target()
					|| false == setting.getEObject() instanceof Invoke) {
				continue;
			}
			Invoke link = (Invoke) setting.getEObject();
			if (InvokeEditPart.VISUAL_ID != UseCaseDiagramVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			UseCase src = link.getSource();
			result.add(new UseCaseDiagramLinkDescriptor(src, target, link,
					UseCaseDiagramElementTypes.Invoke_4001,
					InvokeEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<UseCaseDiagramLinkDescriptor> getIncomingTypeModelFacetLinks_Association_4002(
			UseCase target,
			Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences) {
		LinkedList<UseCaseDiagramLinkDescriptor> result = new LinkedList<UseCaseDiagramLinkDescriptor>();
		Collection<EStructuralFeature.Setting> settings = crossReferences
				.get(target);
		for (EStructuralFeature.Setting setting : settings) {
			if (setting.getEStructuralFeature() != UsecasediagramPackage.eINSTANCE
					.getAssociation_Target()
					|| false == setting.getEObject() instanceof Association) {
				continue;
			}
			Association link = (Association) setting.getEObject();
			if (AssociationEditPart.VISUAL_ID != UseCaseDiagramVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			Actor src = link.getSource();
			result.add(new UseCaseDiagramLinkDescriptor(src, target, link,
					UseCaseDiagramElementTypes.Association_4002,
					AssociationEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<UseCaseDiagramLinkDescriptor> getOutgoingTypeModelFacetLinks_Invoke_4001(
			UseCase source) {
		UseCaseDiagram container = null;
		// Find container element for the link.
		// Climb up by containment hierarchy starting from the source
		// and return the first element that is instance of the container class.
		for (EObject element = source; element != null && container == null; element = element
				.eContainer()) {
			if (element instanceof UseCaseDiagram) {
				container = (UseCaseDiagram) element;
			}
		}
		if (container == null) {
			return Collections.emptyList();
		}
		LinkedList<UseCaseDiagramLinkDescriptor> result = new LinkedList<UseCaseDiagramLinkDescriptor>();
		for (Iterator<?> links = container.getInvokes().iterator(); links
				.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof Invoke) {
				continue;
			}
			Invoke link = (Invoke) linkObject;
			if (InvokeEditPart.VISUAL_ID != UseCaseDiagramVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			UseCase dst = link.getTarget();
			UseCase src = link.getSource();
			if (src != source) {
				continue;
			}
			result.add(new UseCaseDiagramLinkDescriptor(src, dst, link,
					UseCaseDiagramElementTypes.Invoke_4001,
					InvokeEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<UseCaseDiagramLinkDescriptor> getOutgoingTypeModelFacetLinks_Association_4002(
			Actor source) {
		UseCaseDiagram container = null;
		// Find container element for the link.
		// Climb up by containment hierarchy starting from the source
		// and return the first element that is instance of the container class.
		for (EObject element = source; element != null && container == null; element = element
				.eContainer()) {
			if (element instanceof UseCaseDiagram) {
				container = (UseCaseDiagram) element;
			}
		}
		if (container == null) {
			return Collections.emptyList();
		}
		LinkedList<UseCaseDiagramLinkDescriptor> result = new LinkedList<UseCaseDiagramLinkDescriptor>();
		for (Iterator<?> links = container.getAssociations().iterator(); links
				.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof Association) {
				continue;
			}
			Association link = (Association) linkObject;
			if (AssociationEditPart.VISUAL_ID != UseCaseDiagramVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			UseCase dst = link.getTarget();
			Actor src = link.getSource();
			if (src != source) {
				continue;
			}
			result.add(new UseCaseDiagramLinkDescriptor(src, dst, link,
					UseCaseDiagramElementTypes.Association_4002,
					AssociationEditPart.VISUAL_ID));
		}
		return result;
	}

}
