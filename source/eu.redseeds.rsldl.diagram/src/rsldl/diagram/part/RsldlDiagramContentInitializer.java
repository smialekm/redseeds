package rsldl.diagram.part;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

import org.eclipse.gmf.runtime.diagram.core.services.ViewService;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;

import rsldl.DLDiagram;
import rsldl.diagram.edit.parts.DLAlghoritmicTransitionEditPart;
import rsldl.diagram.edit.parts.DLAttributeLinkEditPart;
import rsldl.diagram.edit.parts.DLDataBasedReferenceEditPart;
import rsldl.diagram.edit.parts.DLDiagramEditPart;
import rsldl.diagram.edit.parts.DLEntityDLEntityCompartmentEditPart;
import rsldl.diagram.edit.parts.DLEntityEditPart;
import rsldl.diagram.edit.parts.DLIdentityCondition2EditPart;
import rsldl.diagram.edit.parts.DLIdentityConditionEditPart;
import rsldl.diagram.edit.parts.DLInheritanceCondition2EditPart;
import rsldl.diagram.edit.parts.DLInheritanceConditionEditPart;
import rsldl.diagram.edit.parts.DLPatternBasedReferenceEditPart;
import rsldl.diagram.edit.parts.DLPatternBasedTransitionEditPart;
import rsldl.diagram.edit.parts.DLPrimitiveEditPart;
import rsldl.diagram.edit.parts.DLPropertyDLPropertyCompartmentEditPart;
import rsldl.diagram.edit.parts.DLPropertyEditPart;
import rsldl.diagram.edit.parts.DLRelationshipParticipation2EditPart;
import rsldl.diagram.edit.parts.DLRelationshipParticipation3EditPart;
import rsldl.diagram.edit.parts.DLRelationshipParticipationEditPart;
import rsldl.diagram.edit.parts.DLValidityCondition2EditPart;
import rsldl.diagram.edit.parts.DLValidityConditionEditPart;

/**
 * @generated
 */
public class RsldlDiagramContentInitializer {

	/**
	 * @generated
	 */
	private Map myDomain2NotationMap = new HashMap();

	/**
	 * @generated
	 */
	private Collection myLinkDescriptors = new LinkedList();

	/**
	 * @generated
	 */
	public void initDiagramContent(Diagram diagram) {
		if (!DLDiagramEditPart.MODEL_ID.equals(diagram.getType())) {
			RsldlDiagramEditorPlugin.getInstance().logError(
					"Incorrect diagram passed as a parameter: "
							+ diagram.getType());
			return;
		}
		if (false == diagram.getElement() instanceof DLDiagram) {
			RsldlDiagramEditorPlugin.getInstance().logError(
					"Incorrect diagram element specified: "
							+ diagram.getElement() + " instead of DLDiagram");
			return;
		}
		createDLDiagram_1000Children(diagram);
		createLinks(diagram);
	}

	/**
	 * @generated
	 */
	private void createDLDiagram_1000Children(View view) {
		Collection childNodeDescriptors = RsldlDiagramUpdater
				.getDLDiagram_1000SemanticChildren(view);
		for (Iterator it = childNodeDescriptors.iterator(); it.hasNext();) {
			createNode(view, (RsldlNodeDescriptor) it.next());
		}
	}

	/**
	 * @generated
	 */
	private void createDLEntity_2001Children(View view) {
		myDomain2NotationMap.put(view.getElement(), view);
		myLinkDescriptors.addAll(RsldlDiagramUpdater
				.getDLEntity_2001OutgoingLinks(view));
		createDLEntityDLEntityCompartment_7001Children(getCompartment(view,
				DLEntityDLEntityCompartmentEditPart.VISUAL_ID));

	}

	/**
	 * @generated
	 */
	private void createDLProperty_2002Children(View view) {
		myDomain2NotationMap.put(view.getElement(), view);
		myLinkDescriptors.addAll(RsldlDiagramUpdater
				.getDLProperty_2002OutgoingLinks(view));
		createDLPropertyDLPropertyCompartment_7002Children(getCompartment(view,
				DLPropertyDLPropertyCompartmentEditPart.VISUAL_ID));

	}

	/**
	 * @generated
	 */
	private void createDLPrimitive_2003Children(View view) {
		myDomain2NotationMap.put(view.getElement(), view);
		myLinkDescriptors.addAll(RsldlDiagramUpdater
				.getDLPrimitive_2003OutgoingLinks(view));

	}

	/**
	 * @generated
	 */
	private void createDLDataBasedReference_2004Children(View view) {
		myDomain2NotationMap.put(view.getElement(), view);
		myLinkDescriptors.addAll(RsldlDiagramUpdater
				.getDLDataBasedReference_2004OutgoingLinks(view));

	}

	/**
	 * @generated
	 */
	private void createDLPatternBasedReference_2005Children(View view) {
		myDomain2NotationMap.put(view.getElement(), view);
		myLinkDescriptors.addAll(RsldlDiagramUpdater
				.getDLPatternBasedReference_2005OutgoingLinks(view));

	}

	/**
	 * @generated
	 */
	private void createDLAlghoritmicTransition_2006Children(View view) {
		myDomain2NotationMap.put(view.getElement(), view);
		myLinkDescriptors.addAll(RsldlDiagramUpdater
				.getDLAlghoritmicTransition_2006OutgoingLinks(view));

	}

	/**
	 * @generated
	 */
	private void createDLPatternBasedTransition_2007Children(View view) {
		myDomain2NotationMap.put(view.getElement(), view);
		myLinkDescriptors.addAll(RsldlDiagramUpdater
				.getDLPatternBasedTransition_2007OutgoingLinks(view));

	}

	/**
	 * @generated
	 */
	private void createDLIdentityCondition_3001Children(View view) {
		myDomain2NotationMap.put(view.getElement(), view);
		myLinkDescriptors.addAll(RsldlDiagramUpdater
				.getDLIdentityCondition_3001OutgoingLinks(view));

	}

	/**
	 * @generated
	 */
	private void createDLInheritanceCondition_3002Children(View view) {
		myDomain2NotationMap.put(view.getElement(), view);
		myLinkDescriptors.addAll(RsldlDiagramUpdater
				.getDLInheritanceCondition_3002OutgoingLinks(view));

	}

	/**
	 * @generated
	 */
	private void createDLValidityCondition_3003Children(View view) {
		myDomain2NotationMap.put(view.getElement(), view);
		myLinkDescriptors.addAll(RsldlDiagramUpdater
				.getDLValidityCondition_3003OutgoingLinks(view));

	}

	/**
	 * @generated
	 */
	private void createDLIdentityCondition_3004Children(View view) {
		myDomain2NotationMap.put(view.getElement(), view);
		myLinkDescriptors.addAll(RsldlDiagramUpdater
				.getDLIdentityCondition_3004OutgoingLinks(view));

	}

	/**
	 * @generated
	 */
	private void createDLInheritanceCondition_3005Children(View view) {
		myDomain2NotationMap.put(view.getElement(), view);
		myLinkDescriptors.addAll(RsldlDiagramUpdater
				.getDLInheritanceCondition_3005OutgoingLinks(view));

	}

	/**
	 * @generated
	 */
	private void createDLValidityCondition_3006Children(View view) {
		myDomain2NotationMap.put(view.getElement(), view);
		myLinkDescriptors.addAll(RsldlDiagramUpdater
				.getDLValidityCondition_3006OutgoingLinks(view));

	}

	/**
	 * @generated
	 */
	private void createDLEntityDLEntityCompartment_7001Children(View view) {
		Collection childNodeDescriptors = RsldlDiagramUpdater
				.getDLEntityDLEntityCompartment_7001SemanticChildren(view);
		for (Iterator it = childNodeDescriptors.iterator(); it.hasNext();) {
			createNode(view, (RsldlNodeDescriptor) it.next());
		}
	}

	/**
	 * @generated
	 */
	private void createDLPropertyDLPropertyCompartment_7002Children(View view) {
		Collection childNodeDescriptors = RsldlDiagramUpdater
				.getDLPropertyDLPropertyCompartment_7002SemanticChildren(view);
		for (Iterator it = childNodeDescriptors.iterator(); it.hasNext();) {
			createNode(view, (RsldlNodeDescriptor) it.next());
		}
	}

	/**
	 * @generated
	 */
	private void createNode(View parentView, RsldlNodeDescriptor nodeDescriptor) {
		final String nodeType = RsldlVisualIDRegistry.getType(nodeDescriptor
				.getVisualID());
		Node node = ViewService.createNode(parentView,
				nodeDescriptor.getModelElement(), nodeType,
				RsldlDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);
		switch (nodeDescriptor.getVisualID()) {
		case DLEntityEditPart.VISUAL_ID:
			createDLEntity_2001Children(node);
			return;
		case DLPropertyEditPart.VISUAL_ID:
			createDLProperty_2002Children(node);
			return;
		case DLPrimitiveEditPart.VISUAL_ID:
			createDLPrimitive_2003Children(node);
			return;
		case DLDataBasedReferenceEditPart.VISUAL_ID:
			createDLDataBasedReference_2004Children(node);
			return;
		case DLPatternBasedReferenceEditPart.VISUAL_ID:
			createDLPatternBasedReference_2005Children(node);
			return;
		case DLAlghoritmicTransitionEditPart.VISUAL_ID:
			createDLAlghoritmicTransition_2006Children(node);
			return;
		case DLPatternBasedTransitionEditPart.VISUAL_ID:
			createDLPatternBasedTransition_2007Children(node);
			return;
		case DLIdentityConditionEditPart.VISUAL_ID:
			createDLIdentityCondition_3001Children(node);
			return;
		case DLInheritanceConditionEditPart.VISUAL_ID:
			createDLInheritanceCondition_3002Children(node);
			return;
		case DLValidityConditionEditPart.VISUAL_ID:
			createDLValidityCondition_3003Children(node);
			return;
		case DLIdentityCondition2EditPart.VISUAL_ID:
			createDLIdentityCondition_3004Children(node);
			return;
		case DLInheritanceCondition2EditPart.VISUAL_ID:
			createDLInheritanceCondition_3005Children(node);
			return;
		case DLValidityCondition2EditPart.VISUAL_ID:
			createDLValidityCondition_3006Children(node);
			return;
		}
	}

	/**
	 * @generated
	 */
	private void createLinks(Diagram diagram) {
		for (boolean continueLinkCreation = true; continueLinkCreation;) {
			continueLinkCreation = false;
			Collection additionalDescriptors = new LinkedList();
			for (Iterator it = myLinkDescriptors.iterator(); it.hasNext();) {
				RsldlLinkDescriptor nextLinkDescriptor = (RsldlLinkDescriptor) it
						.next();
				if (!myDomain2NotationMap.containsKey(nextLinkDescriptor
						.getSource())
						|| !myDomain2NotationMap.containsKey(nextLinkDescriptor
								.getDestination())) {
					continue;
				}
				final String linkType = RsldlVisualIDRegistry
						.getType(nextLinkDescriptor.getVisualID());
				Edge edge = ViewService.getInstance().createEdge(
						nextLinkDescriptor.getSemanticAdapter(), diagram,
						linkType, ViewUtil.APPEND, true,
						RsldlDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);
				if (edge != null) {
					edge.setSource((View) myDomain2NotationMap
							.get(nextLinkDescriptor.getSource()));
					edge.setTarget((View) myDomain2NotationMap
							.get(nextLinkDescriptor.getDestination()));
					it.remove();
					if (nextLinkDescriptor.getModelElement() != null) {
						myDomain2NotationMap.put(
								nextLinkDescriptor.getModelElement(), edge);
					}
					continueLinkCreation = true;
					switch (nextLinkDescriptor.getVisualID()) {
					case DLRelationshipParticipationEditPart.VISUAL_ID:
						additionalDescriptors
								.addAll(RsldlDiagramUpdater
										.getDLRelationshipParticipation_4001OutgoingLinks(edge));
						break;
					case DLRelationshipParticipation2EditPart.VISUAL_ID:
						additionalDescriptors
								.addAll(RsldlDiagramUpdater
										.getDLRelationshipParticipation_4002OutgoingLinks(edge));
						break;
					case DLRelationshipParticipation3EditPart.VISUAL_ID:
						additionalDescriptors
								.addAll(RsldlDiagramUpdater
										.getDLRelationshipParticipation_4003OutgoingLinks(edge));
						break;
					case DLAttributeLinkEditPart.VISUAL_ID:
						additionalDescriptors.addAll(RsldlDiagramUpdater
								.getDLAttributeLink_4004OutgoingLinks(edge));
						break;
					}
				}
			}
			myLinkDescriptors.addAll(additionalDescriptors);
		}
	}

	/**
	 * @generated
	 */
	private Node getCompartment(View node, int visualID) {
		String type = RsldlVisualIDRegistry.getType(visualID);
		for (Iterator it = node.getChildren().iterator(); it.hasNext();) {
			View nextView = (View) it.next();
			if (nextView instanceof Node && type.equals(nextView.getType())) {
				return (Node) nextView;
			}
		}
		return null;
	}

}
