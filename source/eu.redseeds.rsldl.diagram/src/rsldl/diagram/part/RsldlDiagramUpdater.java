package rsldl.diagram.part;

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

import rsldl.DLAlghoritmicTransition;
import rsldl.DLAttributeLink;
import rsldl.DLDataBasedReference;
import rsldl.DLDiagram;
import rsldl.DLEntity;
import rsldl.DLFeature;
import rsldl.DLNotion;
import rsldl.DLPatternBasedReference;
import rsldl.DLPatternBasedTransition;
import rsldl.DLPrimitive;
import rsldl.DLProperty;
import rsldl.DLRelationship;
import rsldl.DLRelationshipParticipant;
import rsldl.DLRelationshipParticipation;
import rsldl.RsldlPackage;
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
import rsldl.diagram.providers.RsldlElementTypes;

/**
 * @generated
 */
public class RsldlDiagramUpdater {

	/**
	 * @generated
	 */
	public static List<RsldlNodeDescriptor> getSemanticChildren(View view) {
		switch (RsldlVisualIDRegistry.getVisualID(view)) {
		case DLDiagramEditPart.VISUAL_ID:
			return getDLDiagram_1000SemanticChildren(view);
		case DLEntityDLEntityCompartmentEditPart.VISUAL_ID:
			return getDLEntityDLEntityCompartment_7001SemanticChildren(view);
		case DLPropertyDLPropertyCompartmentEditPart.VISUAL_ID:
			return getDLPropertyDLPropertyCompartment_7002SemanticChildren(view);
		}
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<RsldlNodeDescriptor> getDLDiagram_1000SemanticChildren(
			View view) {
		if (!view.isSetElement()) {
			return Collections.emptyList();
		}
		DLDiagram modelElement = (DLDiagram) view.getElement();
		LinkedList<RsldlNodeDescriptor> result = new LinkedList<RsldlNodeDescriptor>();
		for (Iterator<?> it = modelElement.getRelationshipParticipants()
				.iterator(); it.hasNext();) {
			DLRelationshipParticipant childElement = (DLRelationshipParticipant) it
					.next();
			int visualID = RsldlVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == DLEntityEditPart.VISUAL_ID) {
				result.add(new RsldlNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == DLPropertyEditPart.VISUAL_ID) {
				result.add(new RsldlNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == DLPrimitiveEditPart.VISUAL_ID) {
				result.add(new RsldlNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		for (Iterator<?> it = modelElement.getRelationships().iterator(); it
				.hasNext();) {
			DLRelationship childElement = (DLRelationship) it.next();
			int visualID = RsldlVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == DLDataBasedReferenceEditPart.VISUAL_ID) {
				result.add(new RsldlNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == DLPatternBasedReferenceEditPart.VISUAL_ID) {
				result.add(new RsldlNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == DLAlghoritmicTransitionEditPart.VISUAL_ID) {
				result.add(new RsldlNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == DLPatternBasedTransitionEditPart.VISUAL_ID) {
				result.add(new RsldlNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List<RsldlNodeDescriptor> getDLEntityDLEntityCompartment_7001SemanticChildren(
			View view) {
		if (false == view.eContainer() instanceof View) {
			return Collections.emptyList();
		}
		View containerView = (View) view.eContainer();
		if (!containerView.isSetElement()) {
			return Collections.emptyList();
		}
		DLEntity modelElement = (DLEntity) containerView.getElement();
		LinkedList<RsldlNodeDescriptor> result = new LinkedList<RsldlNodeDescriptor>();
		for (Iterator<?> it = modelElement.getFeatures().iterator(); it
				.hasNext();) {
			DLFeature childElement = (DLFeature) it.next();
			int visualID = RsldlVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == DLIdentityConditionEditPart.VISUAL_ID) {
				result.add(new RsldlNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == DLInheritanceConditionEditPart.VISUAL_ID) {
				result.add(new RsldlNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == DLValidityConditionEditPart.VISUAL_ID) {
				result.add(new RsldlNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List<RsldlNodeDescriptor> getDLPropertyDLPropertyCompartment_7002SemanticChildren(
			View view) {
		if (false == view.eContainer() instanceof View) {
			return Collections.emptyList();
		}
		View containerView = (View) view.eContainer();
		if (!containerView.isSetElement()) {
			return Collections.emptyList();
		}
		DLProperty modelElement = (DLProperty) containerView.getElement();
		LinkedList<RsldlNodeDescriptor> result = new LinkedList<RsldlNodeDescriptor>();
		for (Iterator<?> it = modelElement.getFeatures().iterator(); it
				.hasNext();) {
			DLFeature childElement = (DLFeature) it.next();
			int visualID = RsldlVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == DLIdentityCondition2EditPart.VISUAL_ID) {
				result.add(new RsldlNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == DLInheritanceCondition2EditPart.VISUAL_ID) {
				result.add(new RsldlNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == DLValidityCondition2EditPart.VISUAL_ID) {
				result.add(new RsldlNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List<RsldlLinkDescriptor> getContainedLinks(View view) {
		switch (RsldlVisualIDRegistry.getVisualID(view)) {
		case DLDiagramEditPart.VISUAL_ID:
			return getDLDiagram_1000ContainedLinks(view);
		case DLEntityEditPart.VISUAL_ID:
			return getDLEntity_2001ContainedLinks(view);
		case DLPropertyEditPart.VISUAL_ID:
			return getDLProperty_2002ContainedLinks(view);
		case DLPrimitiveEditPart.VISUAL_ID:
			return getDLPrimitive_2003ContainedLinks(view);
		case DLDataBasedReferenceEditPart.VISUAL_ID:
			return getDLDataBasedReference_2004ContainedLinks(view);
		case DLPatternBasedReferenceEditPart.VISUAL_ID:
			return getDLPatternBasedReference_2005ContainedLinks(view);
		case DLAlghoritmicTransitionEditPart.VISUAL_ID:
			return getDLAlghoritmicTransition_2006ContainedLinks(view);
		case DLPatternBasedTransitionEditPart.VISUAL_ID:
			return getDLPatternBasedTransition_2007ContainedLinks(view);
		case DLIdentityConditionEditPart.VISUAL_ID:
			return getDLIdentityCondition_3001ContainedLinks(view);
		case DLInheritanceConditionEditPart.VISUAL_ID:
			return getDLInheritanceCondition_3002ContainedLinks(view);
		case DLValidityConditionEditPart.VISUAL_ID:
			return getDLValidityCondition_3003ContainedLinks(view);
		case DLIdentityCondition2EditPart.VISUAL_ID:
			return getDLIdentityCondition_3004ContainedLinks(view);
		case DLInheritanceCondition2EditPart.VISUAL_ID:
			return getDLInheritanceCondition_3005ContainedLinks(view);
		case DLValidityCondition2EditPart.VISUAL_ID:
			return getDLValidityCondition_3006ContainedLinks(view);
		case DLRelationshipParticipationEditPart.VISUAL_ID:
			return getDLRelationshipParticipation_4001ContainedLinks(view);
		case DLRelationshipParticipation2EditPart.VISUAL_ID:
			return getDLRelationshipParticipation_4002ContainedLinks(view);
		case DLRelationshipParticipation3EditPart.VISUAL_ID:
			return getDLRelationshipParticipation_4003ContainedLinks(view);
		case DLAttributeLinkEditPart.VISUAL_ID:
			return getDLAttributeLink_4004ContainedLinks(view);
		}
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<RsldlLinkDescriptor> getIncomingLinks(View view) {
		switch (RsldlVisualIDRegistry.getVisualID(view)) {
		case DLEntityEditPart.VISUAL_ID:
			return getDLEntity_2001IncomingLinks(view);
		case DLPropertyEditPart.VISUAL_ID:
			return getDLProperty_2002IncomingLinks(view);
		case DLPrimitiveEditPart.VISUAL_ID:
			return getDLPrimitive_2003IncomingLinks(view);
		case DLDataBasedReferenceEditPart.VISUAL_ID:
			return getDLDataBasedReference_2004IncomingLinks(view);
		case DLPatternBasedReferenceEditPart.VISUAL_ID:
			return getDLPatternBasedReference_2005IncomingLinks(view);
		case DLAlghoritmicTransitionEditPart.VISUAL_ID:
			return getDLAlghoritmicTransition_2006IncomingLinks(view);
		case DLPatternBasedTransitionEditPart.VISUAL_ID:
			return getDLPatternBasedTransition_2007IncomingLinks(view);
		case DLIdentityConditionEditPart.VISUAL_ID:
			return getDLIdentityCondition_3001IncomingLinks(view);
		case DLInheritanceConditionEditPart.VISUAL_ID:
			return getDLInheritanceCondition_3002IncomingLinks(view);
		case DLValidityConditionEditPart.VISUAL_ID:
			return getDLValidityCondition_3003IncomingLinks(view);
		case DLIdentityCondition2EditPart.VISUAL_ID:
			return getDLIdentityCondition_3004IncomingLinks(view);
		case DLInheritanceCondition2EditPart.VISUAL_ID:
			return getDLInheritanceCondition_3005IncomingLinks(view);
		case DLValidityCondition2EditPart.VISUAL_ID:
			return getDLValidityCondition_3006IncomingLinks(view);
		case DLRelationshipParticipationEditPart.VISUAL_ID:
			return getDLRelationshipParticipation_4001IncomingLinks(view);
		case DLRelationshipParticipation2EditPart.VISUAL_ID:
			return getDLRelationshipParticipation_4002IncomingLinks(view);
		case DLRelationshipParticipation3EditPart.VISUAL_ID:
			return getDLRelationshipParticipation_4003IncomingLinks(view);
		case DLAttributeLinkEditPart.VISUAL_ID:
			return getDLAttributeLink_4004IncomingLinks(view);
		}
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<RsldlLinkDescriptor> getOutgoingLinks(View view) {
		switch (RsldlVisualIDRegistry.getVisualID(view)) {
		case DLEntityEditPart.VISUAL_ID:
			return getDLEntity_2001OutgoingLinks(view);
		case DLPropertyEditPart.VISUAL_ID:
			return getDLProperty_2002OutgoingLinks(view);
		case DLPrimitiveEditPart.VISUAL_ID:
			return getDLPrimitive_2003OutgoingLinks(view);
		case DLDataBasedReferenceEditPart.VISUAL_ID:
			return getDLDataBasedReference_2004OutgoingLinks(view);
		case DLPatternBasedReferenceEditPart.VISUAL_ID:
			return getDLPatternBasedReference_2005OutgoingLinks(view);
		case DLAlghoritmicTransitionEditPart.VISUAL_ID:
			return getDLAlghoritmicTransition_2006OutgoingLinks(view);
		case DLPatternBasedTransitionEditPart.VISUAL_ID:
			return getDLPatternBasedTransition_2007OutgoingLinks(view);
		case DLIdentityConditionEditPart.VISUAL_ID:
			return getDLIdentityCondition_3001OutgoingLinks(view);
		case DLInheritanceConditionEditPart.VISUAL_ID:
			return getDLInheritanceCondition_3002OutgoingLinks(view);
		case DLValidityConditionEditPart.VISUAL_ID:
			return getDLValidityCondition_3003OutgoingLinks(view);
		case DLIdentityCondition2EditPart.VISUAL_ID:
			return getDLIdentityCondition_3004OutgoingLinks(view);
		case DLInheritanceCondition2EditPart.VISUAL_ID:
			return getDLInheritanceCondition_3005OutgoingLinks(view);
		case DLValidityCondition2EditPart.VISUAL_ID:
			return getDLValidityCondition_3006OutgoingLinks(view);
		case DLRelationshipParticipationEditPart.VISUAL_ID:
			return getDLRelationshipParticipation_4001OutgoingLinks(view);
		case DLRelationshipParticipation2EditPart.VISUAL_ID:
			return getDLRelationshipParticipation_4002OutgoingLinks(view);
		case DLRelationshipParticipation3EditPart.VISUAL_ID:
			return getDLRelationshipParticipation_4003OutgoingLinks(view);
		case DLAttributeLinkEditPart.VISUAL_ID:
			return getDLAttributeLink_4004OutgoingLinks(view);
		}
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<RsldlLinkDescriptor> getDLDiagram_1000ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<RsldlLinkDescriptor> getDLEntity_2001ContainedLinks(
			View view) {
		DLEntity modelElement = (DLEntity) view.getElement();
		LinkedList<RsldlLinkDescriptor> result = new LinkedList<RsldlLinkDescriptor>();
		result.addAll(getContainedTypeModelFacetLinks_DLAttributeLink_4004(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<RsldlLinkDescriptor> getDLProperty_2002ContainedLinks(
			View view) {
		DLProperty modelElement = (DLProperty) view.getElement();
		LinkedList<RsldlLinkDescriptor> result = new LinkedList<RsldlLinkDescriptor>();
		result.addAll(getContainedTypeModelFacetLinks_DLAttributeLink_4004(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<RsldlLinkDescriptor> getDLPrimitive_2003ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<RsldlLinkDescriptor> getDLDataBasedReference_2004ContainedLinks(
			View view) {
		DLDataBasedReference modelElement = (DLDataBasedReference) view
				.getElement();
		LinkedList<RsldlLinkDescriptor> result = new LinkedList<RsldlLinkDescriptor>();
		result.addAll(getContainedTypeModelFacetLinks_DLRelationshipParticipation_4001(modelElement));
		result.addAll(getContainedTypeModelFacetLinks_DLRelationshipParticipation_4002(modelElement));
		result.addAll(getContainedTypeModelFacetLinks_DLRelationshipParticipation_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<RsldlLinkDescriptor> getDLPatternBasedReference_2005ContainedLinks(
			View view) {
		DLPatternBasedReference modelElement = (DLPatternBasedReference) view
				.getElement();
		LinkedList<RsldlLinkDescriptor> result = new LinkedList<RsldlLinkDescriptor>();
		result.addAll(getContainedTypeModelFacetLinks_DLRelationshipParticipation_4001(modelElement));
		result.addAll(getContainedTypeModelFacetLinks_DLRelationshipParticipation_4002(modelElement));
		result.addAll(getContainedTypeModelFacetLinks_DLRelationshipParticipation_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<RsldlLinkDescriptor> getDLAlghoritmicTransition_2006ContainedLinks(
			View view) {
		DLAlghoritmicTransition modelElement = (DLAlghoritmicTransition) view
				.getElement();
		LinkedList<RsldlLinkDescriptor> result = new LinkedList<RsldlLinkDescriptor>();
		result.addAll(getContainedTypeModelFacetLinks_DLRelationshipParticipation_4001(modelElement));
		result.addAll(getContainedTypeModelFacetLinks_DLRelationshipParticipation_4002(modelElement));
		result.addAll(getContainedTypeModelFacetLinks_DLRelationshipParticipation_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<RsldlLinkDescriptor> getDLPatternBasedTransition_2007ContainedLinks(
			View view) {
		DLPatternBasedTransition modelElement = (DLPatternBasedTransition) view
				.getElement();
		LinkedList<RsldlLinkDescriptor> result = new LinkedList<RsldlLinkDescriptor>();
		result.addAll(getContainedTypeModelFacetLinks_DLRelationshipParticipation_4001(modelElement));
		result.addAll(getContainedTypeModelFacetLinks_DLRelationshipParticipation_4002(modelElement));
		result.addAll(getContainedTypeModelFacetLinks_DLRelationshipParticipation_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<RsldlLinkDescriptor> getDLIdentityCondition_3001ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<RsldlLinkDescriptor> getDLInheritanceCondition_3002ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<RsldlLinkDescriptor> getDLValidityCondition_3003ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<RsldlLinkDescriptor> getDLIdentityCondition_3004ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<RsldlLinkDescriptor> getDLInheritanceCondition_3005ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<RsldlLinkDescriptor> getDLValidityCondition_3006ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<RsldlLinkDescriptor> getDLRelationshipParticipation_4001ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<RsldlLinkDescriptor> getDLRelationshipParticipation_4002ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<RsldlLinkDescriptor> getDLRelationshipParticipation_4003ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<RsldlLinkDescriptor> getDLAttributeLink_4004ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<RsldlLinkDescriptor> getDLEntity_2001IncomingLinks(
			View view) {
		DLEntity modelElement = (DLEntity) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<RsldlLinkDescriptor> result = new LinkedList<RsldlLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_DLRelationshipParticipation_4001(
				modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_DLRelationshipParticipation_4002(
				modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_DLRelationshipParticipation_4003(
				modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_DLAttributeLink_4004(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<RsldlLinkDescriptor> getDLProperty_2002IncomingLinks(
			View view) {
		DLProperty modelElement = (DLProperty) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<RsldlLinkDescriptor> result = new LinkedList<RsldlLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_DLRelationshipParticipation_4001(
				modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_DLRelationshipParticipation_4002(
				modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_DLRelationshipParticipation_4003(
				modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_DLAttributeLink_4004(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<RsldlLinkDescriptor> getDLPrimitive_2003IncomingLinks(
			View view) {
		DLPrimitive modelElement = (DLPrimitive) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<RsldlLinkDescriptor> result = new LinkedList<RsldlLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_DLRelationshipParticipation_4001(
				modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_DLRelationshipParticipation_4002(
				modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_DLRelationshipParticipation_4003(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<RsldlLinkDescriptor> getDLDataBasedReference_2004IncomingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<RsldlLinkDescriptor> getDLPatternBasedReference_2005IncomingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<RsldlLinkDescriptor> getDLAlghoritmicTransition_2006IncomingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<RsldlLinkDescriptor> getDLPatternBasedTransition_2007IncomingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<RsldlLinkDescriptor> getDLIdentityCondition_3001IncomingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<RsldlLinkDescriptor> getDLInheritanceCondition_3002IncomingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<RsldlLinkDescriptor> getDLValidityCondition_3003IncomingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<RsldlLinkDescriptor> getDLIdentityCondition_3004IncomingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<RsldlLinkDescriptor> getDLInheritanceCondition_3005IncomingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<RsldlLinkDescriptor> getDLValidityCondition_3006IncomingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<RsldlLinkDescriptor> getDLRelationshipParticipation_4001IncomingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<RsldlLinkDescriptor> getDLRelationshipParticipation_4002IncomingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<RsldlLinkDescriptor> getDLRelationshipParticipation_4003IncomingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<RsldlLinkDescriptor> getDLAttributeLink_4004IncomingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<RsldlLinkDescriptor> getDLEntity_2001OutgoingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<RsldlLinkDescriptor> getDLProperty_2002OutgoingLinks(
			View view) {
		DLProperty modelElement = (DLProperty) view.getElement();
		LinkedList<RsldlLinkDescriptor> result = new LinkedList<RsldlLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_DLAttributeLink_4004(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<RsldlLinkDescriptor> getDLPrimitive_2003OutgoingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<RsldlLinkDescriptor> getDLDataBasedReference_2004OutgoingLinks(
			View view) {
		DLDataBasedReference modelElement = (DLDataBasedReference) view
				.getElement();
		LinkedList<RsldlLinkDescriptor> result = new LinkedList<RsldlLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_DLRelationshipParticipation_4001(modelElement));
		result.addAll(getOutgoingTypeModelFacetLinks_DLRelationshipParticipation_4002(modelElement));
		result.addAll(getOutgoingTypeModelFacetLinks_DLRelationshipParticipation_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<RsldlLinkDescriptor> getDLPatternBasedReference_2005OutgoingLinks(
			View view) {
		DLPatternBasedReference modelElement = (DLPatternBasedReference) view
				.getElement();
		LinkedList<RsldlLinkDescriptor> result = new LinkedList<RsldlLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_DLRelationshipParticipation_4001(modelElement));
		result.addAll(getOutgoingTypeModelFacetLinks_DLRelationshipParticipation_4002(modelElement));
		result.addAll(getOutgoingTypeModelFacetLinks_DLRelationshipParticipation_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<RsldlLinkDescriptor> getDLAlghoritmicTransition_2006OutgoingLinks(
			View view) {
		DLAlghoritmicTransition modelElement = (DLAlghoritmicTransition) view
				.getElement();
		LinkedList<RsldlLinkDescriptor> result = new LinkedList<RsldlLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_DLRelationshipParticipation_4001(modelElement));
		result.addAll(getOutgoingTypeModelFacetLinks_DLRelationshipParticipation_4002(modelElement));
		result.addAll(getOutgoingTypeModelFacetLinks_DLRelationshipParticipation_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<RsldlLinkDescriptor> getDLPatternBasedTransition_2007OutgoingLinks(
			View view) {
		DLPatternBasedTransition modelElement = (DLPatternBasedTransition) view
				.getElement();
		LinkedList<RsldlLinkDescriptor> result = new LinkedList<RsldlLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_DLRelationshipParticipation_4001(modelElement));
		result.addAll(getOutgoingTypeModelFacetLinks_DLRelationshipParticipation_4002(modelElement));
		result.addAll(getOutgoingTypeModelFacetLinks_DLRelationshipParticipation_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<RsldlLinkDescriptor> getDLIdentityCondition_3001OutgoingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<RsldlLinkDescriptor> getDLInheritanceCondition_3002OutgoingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<RsldlLinkDescriptor> getDLValidityCondition_3003OutgoingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<RsldlLinkDescriptor> getDLIdentityCondition_3004OutgoingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<RsldlLinkDescriptor> getDLInheritanceCondition_3005OutgoingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<RsldlLinkDescriptor> getDLValidityCondition_3006OutgoingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<RsldlLinkDescriptor> getDLRelationshipParticipation_4001OutgoingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<RsldlLinkDescriptor> getDLRelationshipParticipation_4002OutgoingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<RsldlLinkDescriptor> getDLRelationshipParticipation_4003OutgoingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<RsldlLinkDescriptor> getDLAttributeLink_4004OutgoingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	private static Collection<RsldlLinkDescriptor> getContainedTypeModelFacetLinks_DLRelationshipParticipation_4001(
			DLRelationship container) {
		LinkedList<RsldlLinkDescriptor> result = new LinkedList<RsldlLinkDescriptor>();
		for (Iterator<?> links = container.getParticipations().iterator(); links
				.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof DLRelationshipParticipation) {
				continue;
			}
			DLRelationshipParticipation link = (DLRelationshipParticipation) linkObject;
			if (DLRelationshipParticipationEditPart.VISUAL_ID != RsldlVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			DLRelationshipParticipant dst = link.getParticipant();
			DLRelationship src = link.getRelationship();
			result.add(new RsldlLinkDescriptor(src, dst, link,
					RsldlElementTypes.DLRelationshipParticipation_4001,
					DLRelationshipParticipationEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<RsldlLinkDescriptor> getContainedTypeModelFacetLinks_DLRelationshipParticipation_4002(
			DLRelationship container) {
		LinkedList<RsldlLinkDescriptor> result = new LinkedList<RsldlLinkDescriptor>();
		for (Iterator<?> links = container.getParticipations().iterator(); links
				.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof DLRelationshipParticipation) {
				continue;
			}
			DLRelationshipParticipation link = (DLRelationshipParticipation) linkObject;
			if (DLRelationshipParticipation2EditPart.VISUAL_ID != RsldlVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			DLRelationshipParticipant dst = link.getParticipant();
			DLRelationship src = link.getRelationship();
			result.add(new RsldlLinkDescriptor(src, dst, link,
					RsldlElementTypes.DLRelationshipParticipation_4002,
					DLRelationshipParticipation2EditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<RsldlLinkDescriptor> getContainedTypeModelFacetLinks_DLRelationshipParticipation_4003(
			DLRelationship container) {
		LinkedList<RsldlLinkDescriptor> result = new LinkedList<RsldlLinkDescriptor>();
		for (Iterator<?> links = container.getParticipations().iterator(); links
				.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof DLRelationshipParticipation) {
				continue;
			}
			DLRelationshipParticipation link = (DLRelationshipParticipation) linkObject;
			if (DLRelationshipParticipation3EditPart.VISUAL_ID != RsldlVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			DLRelationshipParticipant dst = link.getParticipant();
			DLRelationship src = link.getRelationship();
			result.add(new RsldlLinkDescriptor(src, dst, link,
					RsldlElementTypes.DLRelationshipParticipation_4003,
					DLRelationshipParticipation3EditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<RsldlLinkDescriptor> getContainedTypeModelFacetLinks_DLAttributeLink_4004(
			DLNotion container) {
		LinkedList<RsldlLinkDescriptor> result = new LinkedList<RsldlLinkDescriptor>();
		for (Iterator<?> links = container.getFeatures().iterator(); links
				.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof DLAttributeLink) {
				continue;
			}
			DLAttributeLink link = (DLAttributeLink) linkObject;
			if (DLAttributeLinkEditPart.VISUAL_ID != RsldlVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			DLNotion dst = link.getNotion();
			DLProperty src = link.getAttribute();
			result.add(new RsldlLinkDescriptor(src, dst, link,
					RsldlElementTypes.DLAttributeLink_4004,
					DLAttributeLinkEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<RsldlLinkDescriptor> getIncomingTypeModelFacetLinks_DLRelationshipParticipation_4001(
			DLRelationshipParticipant target,
			Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences) {
		LinkedList<RsldlLinkDescriptor> result = new LinkedList<RsldlLinkDescriptor>();
		Collection<EStructuralFeature.Setting> settings = crossReferences
				.get(target);
		for (EStructuralFeature.Setting setting : settings) {
			if (setting.getEStructuralFeature() != RsldlPackage.eINSTANCE
					.getDLRelationshipParticipation_Participant()
					|| false == setting.getEObject() instanceof DLRelationshipParticipation) {
				continue;
			}
			DLRelationshipParticipation link = (DLRelationshipParticipation) setting
					.getEObject();
			if (DLRelationshipParticipationEditPart.VISUAL_ID != RsldlVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			DLRelationship src = link.getRelationship();
			result.add(new RsldlLinkDescriptor(src, target, link,
					RsldlElementTypes.DLRelationshipParticipation_4001,
					DLRelationshipParticipationEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<RsldlLinkDescriptor> getIncomingTypeModelFacetLinks_DLRelationshipParticipation_4002(
			DLRelationshipParticipant target,
			Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences) {
		LinkedList<RsldlLinkDescriptor> result = new LinkedList<RsldlLinkDescriptor>();
		Collection<EStructuralFeature.Setting> settings = crossReferences
				.get(target);
		for (EStructuralFeature.Setting setting : settings) {
			if (setting.getEStructuralFeature() != RsldlPackage.eINSTANCE
					.getDLRelationshipParticipation_Participant()
					|| false == setting.getEObject() instanceof DLRelationshipParticipation) {
				continue;
			}
			DLRelationshipParticipation link = (DLRelationshipParticipation) setting
					.getEObject();
			if (DLRelationshipParticipation2EditPart.VISUAL_ID != RsldlVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			DLRelationship src = link.getRelationship();
			result.add(new RsldlLinkDescriptor(src, target, link,
					RsldlElementTypes.DLRelationshipParticipation_4002,
					DLRelationshipParticipation2EditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<RsldlLinkDescriptor> getIncomingTypeModelFacetLinks_DLRelationshipParticipation_4003(
			DLRelationshipParticipant target,
			Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences) {
		LinkedList<RsldlLinkDescriptor> result = new LinkedList<RsldlLinkDescriptor>();
		Collection<EStructuralFeature.Setting> settings = crossReferences
				.get(target);
		for (EStructuralFeature.Setting setting : settings) {
			if (setting.getEStructuralFeature() != RsldlPackage.eINSTANCE
					.getDLRelationshipParticipation_Participant()
					|| false == setting.getEObject() instanceof DLRelationshipParticipation) {
				continue;
			}
			DLRelationshipParticipation link = (DLRelationshipParticipation) setting
					.getEObject();
			if (DLRelationshipParticipation3EditPart.VISUAL_ID != RsldlVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			DLRelationship src = link.getRelationship();
			result.add(new RsldlLinkDescriptor(src, target, link,
					RsldlElementTypes.DLRelationshipParticipation_4003,
					DLRelationshipParticipation3EditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<RsldlLinkDescriptor> getIncomingTypeModelFacetLinks_DLAttributeLink_4004(
			DLNotion target,
			Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences) {
		LinkedList<RsldlLinkDescriptor> result = new LinkedList<RsldlLinkDescriptor>();
		Collection<EStructuralFeature.Setting> settings = crossReferences
				.get(target);
		for (EStructuralFeature.Setting setting : settings) {
			if (setting.getEStructuralFeature() != RsldlPackage.eINSTANCE
					.getDLFeature_Notion()
					|| false == setting.getEObject() instanceof DLAttributeLink) {
				continue;
			}
			DLAttributeLink link = (DLAttributeLink) setting.getEObject();
			if (DLAttributeLinkEditPart.VISUAL_ID != RsldlVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			DLProperty src = link.getAttribute();
			result.add(new RsldlLinkDescriptor(src, target, link,
					RsldlElementTypes.DLAttributeLink_4004,
					DLAttributeLinkEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<RsldlLinkDescriptor> getOutgoingTypeModelFacetLinks_DLRelationshipParticipation_4001(
			DLRelationship source) {
		DLRelationship container = null;
		// Find container element for the link.
		// Climb up by containment hierarchy starting from the source
		// and return the first element that is instance of the container class.
		for (EObject element = source; element != null && container == null; element = element
				.eContainer()) {
			if (element instanceof DLRelationship) {
				container = (DLRelationship) element;
			}
		}
		if (container == null) {
			return Collections.emptyList();
		}
		LinkedList<RsldlLinkDescriptor> result = new LinkedList<RsldlLinkDescriptor>();
		for (Iterator<?> links = container.getParticipations().iterator(); links
				.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof DLRelationshipParticipation) {
				continue;
			}
			DLRelationshipParticipation link = (DLRelationshipParticipation) linkObject;
			if (DLRelationshipParticipationEditPart.VISUAL_ID != RsldlVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			DLRelationshipParticipant dst = link.getParticipant();
			DLRelationship src = link.getRelationship();
			if (src != source) {
				continue;
			}
			result.add(new RsldlLinkDescriptor(src, dst, link,
					RsldlElementTypes.DLRelationshipParticipation_4001,
					DLRelationshipParticipationEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<RsldlLinkDescriptor> getOutgoingTypeModelFacetLinks_DLRelationshipParticipation_4002(
			DLRelationship source) {
		DLRelationship container = null;
		// Find container element for the link.
		// Climb up by containment hierarchy starting from the source
		// and return the first element that is instance of the container class.
		for (EObject element = source; element != null && container == null; element = element
				.eContainer()) {
			if (element instanceof DLRelationship) {
				container = (DLRelationship) element;
			}
		}
		if (container == null) {
			return Collections.emptyList();
		}
		LinkedList<RsldlLinkDescriptor> result = new LinkedList<RsldlLinkDescriptor>();
		for (Iterator<?> links = container.getParticipations().iterator(); links
				.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof DLRelationshipParticipation) {
				continue;
			}
			DLRelationshipParticipation link = (DLRelationshipParticipation) linkObject;
			if (DLRelationshipParticipation2EditPart.VISUAL_ID != RsldlVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			DLRelationshipParticipant dst = link.getParticipant();
			DLRelationship src = link.getRelationship();
			if (src != source) {
				continue;
			}
			result.add(new RsldlLinkDescriptor(src, dst, link,
					RsldlElementTypes.DLRelationshipParticipation_4002,
					DLRelationshipParticipation2EditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<RsldlLinkDescriptor> getOutgoingTypeModelFacetLinks_DLRelationshipParticipation_4003(
			DLRelationship source) {
		DLRelationship container = null;
		// Find container element for the link.
		// Climb up by containment hierarchy starting from the source
		// and return the first element that is instance of the container class.
		for (EObject element = source; element != null && container == null; element = element
				.eContainer()) {
			if (element instanceof DLRelationship) {
				container = (DLRelationship) element;
			}
		}
		if (container == null) {
			return Collections.emptyList();
		}
		LinkedList<RsldlLinkDescriptor> result = new LinkedList<RsldlLinkDescriptor>();
		for (Iterator<?> links = container.getParticipations().iterator(); links
				.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof DLRelationshipParticipation) {
				continue;
			}
			DLRelationshipParticipation link = (DLRelationshipParticipation) linkObject;
			if (DLRelationshipParticipation3EditPart.VISUAL_ID != RsldlVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			DLRelationshipParticipant dst = link.getParticipant();
			DLRelationship src = link.getRelationship();
			if (src != source) {
				continue;
			}
			result.add(new RsldlLinkDescriptor(src, dst, link,
					RsldlElementTypes.DLRelationshipParticipation_4003,
					DLRelationshipParticipation3EditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<RsldlLinkDescriptor> getOutgoingTypeModelFacetLinks_DLAttributeLink_4004(
			DLProperty source) {
		DLNotion container = null;
		// Find container element for the link.
		// Climb up by containment hierarchy starting from the source
		// and return the first element that is instance of the container class.
		for (EObject element = source; element != null && container == null; element = element
				.eContainer()) {
			if (element instanceof DLNotion) {
				container = (DLNotion) element;
			}
		}
		if (container == null) {
			return Collections.emptyList();
		}
		LinkedList<RsldlLinkDescriptor> result = new LinkedList<RsldlLinkDescriptor>();
		for (Iterator<?> links = container.getFeatures().iterator(); links
				.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof DLAttributeLink) {
				continue;
			}
			DLAttributeLink link = (DLAttributeLink) linkObject;
			if (DLAttributeLinkEditPart.VISUAL_ID != RsldlVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			DLNotion dst = link.getNotion();
			DLProperty src = link.getAttribute();
			if (src != source) {
				continue;
			}
			result.add(new RsldlLinkDescriptor(src, dst, link,
					RsldlElementTypes.DLAttributeLink_4004,
					DLAttributeLinkEditPart.VISUAL_ID));
		}
		return result;
	}

}
