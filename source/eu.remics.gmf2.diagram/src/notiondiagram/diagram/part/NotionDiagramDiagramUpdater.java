package notiondiagram.diagram.part;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import java.util.Map;
import notiondiagram.AttributeRelation;
import notiondiagram.DirectedRelation;
import notiondiagram.Generalization;
import notiondiagram.IndirectRelation;
import notiondiagram.Notion;
import notiondiagram.NotionDiagram;
import notiondiagram.NotiondiagramPackage;
import notiondiagram.Phrase;
import notiondiagram.diagram.edit.parts.AttributeRelationEditPart;
import notiondiagram.diagram.edit.parts.DirectedRelationEditPart;
import notiondiagram.diagram.edit.parts.GeneralizationEditPart;
import notiondiagram.diagram.edit.parts.IndirectRelationEditPart;
import notiondiagram.diagram.edit.parts.NotionDiagramEditPart;
import notiondiagram.diagram.edit.parts.NotionEditPart;

import notiondiagram.diagram.edit.parts.NotionNotionCompartmentEditPart;
import notiondiagram.diagram.edit.parts.PhraseEditPart;
import notiondiagram.diagram.providers.NotionDiagramElementTypes;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @generated
 */
public class NotionDiagramDiagramUpdater {

	/**
	 * @generated
	 */
	public static List<NotionDiagramNodeDescriptor> getSemanticChildren(
			View view) {
		switch (NotionDiagramVisualIDRegistry.getVisualID(view)) {
		case NotionDiagramEditPart.VISUAL_ID:
			return getNotionDiagram_1000SemanticChildren(view);
		case NotionNotionCompartmentEditPart.VISUAL_ID:
			return getNotionNotionCompartment_7001SemanticChildren(view);
		}
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<NotionDiagramNodeDescriptor> getNotionDiagram_1000SemanticChildren(
			View view) {
		if (!view.isSetElement()) {
			return Collections.emptyList();
		}
		NotionDiagram modelElement = (NotionDiagram) view.getElement();
		LinkedList<NotionDiagramNodeDescriptor> result = new LinkedList<NotionDiagramNodeDescriptor>();
		for (Iterator<?> it = modelElement.getNotions().iterator(); it
				.hasNext();) {
			Notion childElement = (Notion) it.next();
			int visualID = NotionDiagramVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == NotionEditPart.VISUAL_ID) {
				result.add(new NotionDiagramNodeDescriptor(childElement,
						visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List<NotionDiagramNodeDescriptor> getNotionNotionCompartment_7001SemanticChildren(
			View view) {
		if (false == view.eContainer() instanceof View) {
			return Collections.emptyList();
		}
		View containerView = (View) view.eContainer();
		if (!containerView.isSetElement()) {
			return Collections.emptyList();
		}
		Notion modelElement = (Notion) containerView.getElement();
		LinkedList<NotionDiagramNodeDescriptor> result = new LinkedList<NotionDiagramNodeDescriptor>();
		for (Iterator<?> it = modelElement.getPhrases().iterator(); it
				.hasNext();) {
			Phrase childElement = (Phrase) it.next();
			int visualID = NotionDiagramVisualIDRegistry.getNodeVisualID(view,
					childElement);
			if (visualID == PhraseEditPart.VISUAL_ID) {
				result.add(new NotionDiagramNodeDescriptor(childElement,
						visualID));
				continue;
			}
		}
		return result;
	}

	/**
	 * @generated
	 */
	public static List<NotionDiagramLinkDescriptor> getContainedLinks(View view) {
		switch (NotionDiagramVisualIDRegistry.getVisualID(view)) {
		case NotionDiagramEditPart.VISUAL_ID:
			return getNotionDiagram_1000ContainedLinks(view);
		case NotionEditPart.VISUAL_ID:
			return getNotion_2001ContainedLinks(view);
		case PhraseEditPart.VISUAL_ID:
			return getPhrase_3001ContainedLinks(view);
		case GeneralizationEditPart.VISUAL_ID:
			return getGeneralization_4001ContainedLinks(view);
		case AttributeRelationEditPart.VISUAL_ID:
			return getAttributeRelation_4002ContainedLinks(view);
		case DirectedRelationEditPart.VISUAL_ID:
			return getDirectedRelation_4003ContainedLinks(view);
		case IndirectRelationEditPart.VISUAL_ID:
			return getIndirectRelation_4004ContainedLinks(view);
		}
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<NotionDiagramLinkDescriptor> getIncomingLinks(View view) {
		switch (NotionDiagramVisualIDRegistry.getVisualID(view)) {
		case NotionEditPart.VISUAL_ID:
			return getNotion_2001IncomingLinks(view);
		case PhraseEditPart.VISUAL_ID:
			return getPhrase_3001IncomingLinks(view);
		case GeneralizationEditPart.VISUAL_ID:
			return getGeneralization_4001IncomingLinks(view);
		case AttributeRelationEditPart.VISUAL_ID:
			return getAttributeRelation_4002IncomingLinks(view);
		case DirectedRelationEditPart.VISUAL_ID:
			return getDirectedRelation_4003IncomingLinks(view);
		case IndirectRelationEditPart.VISUAL_ID:
			return getIndirectRelation_4004IncomingLinks(view);
		}
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<NotionDiagramLinkDescriptor> getOutgoingLinks(View view) {
		switch (NotionDiagramVisualIDRegistry.getVisualID(view)) {
		case NotionEditPart.VISUAL_ID:
			return getNotion_2001OutgoingLinks(view);
		case PhraseEditPart.VISUAL_ID:
			return getPhrase_3001OutgoingLinks(view);
		case GeneralizationEditPart.VISUAL_ID:
			return getGeneralization_4001OutgoingLinks(view);
		case AttributeRelationEditPart.VISUAL_ID:
			return getAttributeRelation_4002OutgoingLinks(view);
		case DirectedRelationEditPart.VISUAL_ID:
			return getDirectedRelation_4003OutgoingLinks(view);
		case IndirectRelationEditPart.VISUAL_ID:
			return getIndirectRelation_4004OutgoingLinks(view);
		}
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<NotionDiagramLinkDescriptor> getNotionDiagram_1000ContainedLinks(
			View view) {
		NotionDiagram modelElement = (NotionDiagram) view.getElement();
		LinkedList<NotionDiagramLinkDescriptor> result = new LinkedList<NotionDiagramLinkDescriptor>();
		result.addAll(getContainedTypeModelFacetLinks_Generalization_4001(modelElement));
		result.addAll(getContainedTypeModelFacetLinks_AttributeRelation_4002(modelElement));
		result.addAll(getContainedTypeModelFacetLinks_DirectedRelation_4003(modelElement));
		result.addAll(getContainedTypeModelFacetLinks_IndirectRelation_4004(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<NotionDiagramLinkDescriptor> getNotion_2001ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<NotionDiagramLinkDescriptor> getPhrase_3001ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<NotionDiagramLinkDescriptor> getGeneralization_4001ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<NotionDiagramLinkDescriptor> getAttributeRelation_4002ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<NotionDiagramLinkDescriptor> getDirectedRelation_4003ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<NotionDiagramLinkDescriptor> getIndirectRelation_4004ContainedLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<NotionDiagramLinkDescriptor> getNotion_2001IncomingLinks(
			View view) {
		Notion modelElement = (Notion) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<NotionDiagramLinkDescriptor> result = new LinkedList<NotionDiagramLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_Generalization_4001(
				modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_AttributeRelation_4002(
				modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_DirectedRelation_4003(
				modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_IndirectRelation_4004(
				modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<NotionDiagramLinkDescriptor> getPhrase_3001IncomingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<NotionDiagramLinkDescriptor> getGeneralization_4001IncomingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<NotionDiagramLinkDescriptor> getAttributeRelation_4002IncomingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<NotionDiagramLinkDescriptor> getDirectedRelation_4003IncomingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<NotionDiagramLinkDescriptor> getIndirectRelation_4004IncomingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<NotionDiagramLinkDescriptor> getNotion_2001OutgoingLinks(
			View view) {
		Notion modelElement = (Notion) view.getElement();
		LinkedList<NotionDiagramLinkDescriptor> result = new LinkedList<NotionDiagramLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_Generalization_4001(modelElement));
		result.addAll(getOutgoingTypeModelFacetLinks_AttributeRelation_4002(modelElement));
		result.addAll(getOutgoingTypeModelFacetLinks_DirectedRelation_4003(modelElement));
		result.addAll(getOutgoingTypeModelFacetLinks_IndirectRelation_4004(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<NotionDiagramLinkDescriptor> getPhrase_3001OutgoingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<NotionDiagramLinkDescriptor> getGeneralization_4001OutgoingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<NotionDiagramLinkDescriptor> getAttributeRelation_4002OutgoingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<NotionDiagramLinkDescriptor> getDirectedRelation_4003OutgoingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<NotionDiagramLinkDescriptor> getIndirectRelation_4004OutgoingLinks(
			View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	private static Collection<NotionDiagramLinkDescriptor> getContainedTypeModelFacetLinks_Generalization_4001(
			NotionDiagram container) {
		LinkedList<NotionDiagramLinkDescriptor> result = new LinkedList<NotionDiagramLinkDescriptor>();
		for (Iterator<?> links = container.getGeneralizations().iterator(); links
				.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof Generalization) {
				continue;
			}
			Generalization link = (Generalization) linkObject;
			if (GeneralizationEditPart.VISUAL_ID != NotionDiagramVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			Notion dst = link.getTarget();
			Notion src = link.getSource();
			result.add(new NotionDiagramLinkDescriptor(src, dst, link,
					NotionDiagramElementTypes.Generalization_4001,
					GeneralizationEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<NotionDiagramLinkDescriptor> getContainedTypeModelFacetLinks_AttributeRelation_4002(
			NotionDiagram container) {
		LinkedList<NotionDiagramLinkDescriptor> result = new LinkedList<NotionDiagramLinkDescriptor>();
		for (Iterator<?> links = container.getAttributeRelations().iterator(); links
				.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof AttributeRelation) {
				continue;
			}
			AttributeRelation link = (AttributeRelation) linkObject;
			if (AttributeRelationEditPart.VISUAL_ID != NotionDiagramVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			Notion dst = link.getTarget();
			Notion src = link.getSource();
			result.add(new NotionDiagramLinkDescriptor(src, dst, link,
					NotionDiagramElementTypes.AttributeRelation_4002,
					AttributeRelationEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<NotionDiagramLinkDescriptor> getContainedTypeModelFacetLinks_DirectedRelation_4003(
			NotionDiagram container) {
		LinkedList<NotionDiagramLinkDescriptor> result = new LinkedList<NotionDiagramLinkDescriptor>();
		for (Iterator<?> links = container.getRelations().iterator(); links
				.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof DirectedRelation) {
				continue;
			}
			DirectedRelation link = (DirectedRelation) linkObject;
			if (DirectedRelationEditPart.VISUAL_ID != NotionDiagramVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			Notion dst = link.getTarget();
			Notion src = link.getSource();
			result.add(new NotionDiagramLinkDescriptor(src, dst, link,
					NotionDiagramElementTypes.DirectedRelation_4003,
					DirectedRelationEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<NotionDiagramLinkDescriptor> getContainedTypeModelFacetLinks_IndirectRelation_4004(
			NotionDiagram container) {
		LinkedList<NotionDiagramLinkDescriptor> result = new LinkedList<NotionDiagramLinkDescriptor>();
		for (Iterator<?> links = container.getRelations().iterator(); links
				.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof IndirectRelation) {
				continue;
			}
			IndirectRelation link = (IndirectRelation) linkObject;
			if (IndirectRelationEditPart.VISUAL_ID != NotionDiagramVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			Notion dst = link.getTarget();
			Notion src = link.getSource();
			result.add(new NotionDiagramLinkDescriptor(src, dst, link,
					NotionDiagramElementTypes.IndirectRelation_4004,
					IndirectRelationEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<NotionDiagramLinkDescriptor> getIncomingTypeModelFacetLinks_Generalization_4001(
			Notion target,
			Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences) {
		LinkedList<NotionDiagramLinkDescriptor> result = new LinkedList<NotionDiagramLinkDescriptor>();
		Collection<EStructuralFeature.Setting> settings = crossReferences
				.get(target);
		for (EStructuralFeature.Setting setting : settings) {
			if (setting.getEStructuralFeature() != NotiondiagramPackage.eINSTANCE
					.getAbstractRelation_Target()
					|| false == setting.getEObject() instanceof Generalization) {
				continue;
			}
			Generalization link = (Generalization) setting.getEObject();
			if (GeneralizationEditPart.VISUAL_ID != NotionDiagramVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			Notion src = link.getSource();
			result.add(new NotionDiagramLinkDescriptor(src, target, link,
					NotionDiagramElementTypes.Generalization_4001,
					GeneralizationEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<NotionDiagramLinkDescriptor> getIncomingTypeModelFacetLinks_AttributeRelation_4002(
			Notion target,
			Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences) {
		LinkedList<NotionDiagramLinkDescriptor> result = new LinkedList<NotionDiagramLinkDescriptor>();
		Collection<EStructuralFeature.Setting> settings = crossReferences
				.get(target);
		for (EStructuralFeature.Setting setting : settings) {
			if (setting.getEStructuralFeature() != NotiondiagramPackage.eINSTANCE
					.getAbstractRelation_Target()
					|| false == setting.getEObject() instanceof AttributeRelation) {
				continue;
			}
			AttributeRelation link = (AttributeRelation) setting.getEObject();
			if (AttributeRelationEditPart.VISUAL_ID != NotionDiagramVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			Notion src = link.getSource();
			result.add(new NotionDiagramLinkDescriptor(src, target, link,
					NotionDiagramElementTypes.AttributeRelation_4002,
					AttributeRelationEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<NotionDiagramLinkDescriptor> getIncomingTypeModelFacetLinks_DirectedRelation_4003(
			Notion target,
			Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences) {
		LinkedList<NotionDiagramLinkDescriptor> result = new LinkedList<NotionDiagramLinkDescriptor>();
		Collection<EStructuralFeature.Setting> settings = crossReferences
				.get(target);
		for (EStructuralFeature.Setting setting : settings) {
			if (setting.getEStructuralFeature() != NotiondiagramPackage.eINSTANCE
					.getAbstractRelation_Target()
					|| false == setting.getEObject() instanceof DirectedRelation) {
				continue;
			}
			DirectedRelation link = (DirectedRelation) setting.getEObject();
			if (DirectedRelationEditPart.VISUAL_ID != NotionDiagramVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			Notion src = link.getSource();
			result.add(new NotionDiagramLinkDescriptor(src, target, link,
					NotionDiagramElementTypes.DirectedRelation_4003,
					DirectedRelationEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<NotionDiagramLinkDescriptor> getIncomingTypeModelFacetLinks_IndirectRelation_4004(
			Notion target,
			Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences) {
		LinkedList<NotionDiagramLinkDescriptor> result = new LinkedList<NotionDiagramLinkDescriptor>();
		Collection<EStructuralFeature.Setting> settings = crossReferences
				.get(target);
		for (EStructuralFeature.Setting setting : settings) {
			if (setting.getEStructuralFeature() != NotiondiagramPackage.eINSTANCE
					.getAbstractRelation_Target()
					|| false == setting.getEObject() instanceof IndirectRelation) {
				continue;
			}
			IndirectRelation link = (IndirectRelation) setting.getEObject();
			if (IndirectRelationEditPart.VISUAL_ID != NotionDiagramVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			Notion src = link.getSource();
			result.add(new NotionDiagramLinkDescriptor(src, target, link,
					NotionDiagramElementTypes.IndirectRelation_4004,
					IndirectRelationEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<NotionDiagramLinkDescriptor> getOutgoingTypeModelFacetLinks_Generalization_4001(
			Notion source) {
		NotionDiagram container = null;
		// Find container element for the link.
		// Climb up by containment hierarchy starting from the source
		// and return the first element that is instance of the container class.
		for (EObject element = source; element != null && container == null; element = element
				.eContainer()) {
			if (element instanceof NotionDiagram) {
				container = (NotionDiagram) element;
			}
		}
		if (container == null) {
			return Collections.emptyList();
		}
		LinkedList<NotionDiagramLinkDescriptor> result = new LinkedList<NotionDiagramLinkDescriptor>();
		for (Iterator<?> links = container.getGeneralizations().iterator(); links
				.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof Generalization) {
				continue;
			}
			Generalization link = (Generalization) linkObject;
			if (GeneralizationEditPart.VISUAL_ID != NotionDiagramVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			Notion dst = link.getTarget();
			Notion src = link.getSource();
			if (src != source) {
				continue;
			}
			result.add(new NotionDiagramLinkDescriptor(src, dst, link,
					NotionDiagramElementTypes.Generalization_4001,
					GeneralizationEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<NotionDiagramLinkDescriptor> getOutgoingTypeModelFacetLinks_AttributeRelation_4002(
			Notion source) {
		NotionDiagram container = null;
		// Find container element for the link.
		// Climb up by containment hierarchy starting from the source
		// and return the first element that is instance of the container class.
		for (EObject element = source; element != null && container == null; element = element
				.eContainer()) {
			if (element instanceof NotionDiagram) {
				container = (NotionDiagram) element;
			}
		}
		if (container == null) {
			return Collections.emptyList();
		}
		LinkedList<NotionDiagramLinkDescriptor> result = new LinkedList<NotionDiagramLinkDescriptor>();
		for (Iterator<?> links = container.getAttributeRelations().iterator(); links
				.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof AttributeRelation) {
				continue;
			}
			AttributeRelation link = (AttributeRelation) linkObject;
			if (AttributeRelationEditPart.VISUAL_ID != NotionDiagramVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			Notion dst = link.getTarget();
			Notion src = link.getSource();
			if (src != source) {
				continue;
			}
			result.add(new NotionDiagramLinkDescriptor(src, dst, link,
					NotionDiagramElementTypes.AttributeRelation_4002,
					AttributeRelationEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<NotionDiagramLinkDescriptor> getOutgoingTypeModelFacetLinks_DirectedRelation_4003(
			Notion source) {
		NotionDiagram container = null;
		// Find container element for the link.
		// Climb up by containment hierarchy starting from the source
		// and return the first element that is instance of the container class.
		for (EObject element = source; element != null && container == null; element = element
				.eContainer()) {
			if (element instanceof NotionDiagram) {
				container = (NotionDiagram) element;
			}
		}
		if (container == null) {
			return Collections.emptyList();
		}
		LinkedList<NotionDiagramLinkDescriptor> result = new LinkedList<NotionDiagramLinkDescriptor>();
		for (Iterator<?> links = container.getRelations().iterator(); links
				.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof DirectedRelation) {
				continue;
			}
			DirectedRelation link = (DirectedRelation) linkObject;
			if (DirectedRelationEditPart.VISUAL_ID != NotionDiagramVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			Notion dst = link.getTarget();
			Notion src = link.getSource();
			if (src != source) {
				continue;
			}
			result.add(new NotionDiagramLinkDescriptor(src, dst, link,
					NotionDiagramElementTypes.DirectedRelation_4003,
					DirectedRelationEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<NotionDiagramLinkDescriptor> getOutgoingTypeModelFacetLinks_IndirectRelation_4004(
			Notion source) {
		NotionDiagram container = null;
		// Find container element for the link.
		// Climb up by containment hierarchy starting from the source
		// and return the first element that is instance of the container class.
		for (EObject element = source; element != null && container == null; element = element
				.eContainer()) {
			if (element instanceof NotionDiagram) {
				container = (NotionDiagram) element;
			}
		}
		if (container == null) {
			return Collections.emptyList();
		}
		LinkedList<NotionDiagramLinkDescriptor> result = new LinkedList<NotionDiagramLinkDescriptor>();
		for (Iterator<?> links = container.getRelations().iterator(); links
				.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof IndirectRelation) {
				continue;
			}
			IndirectRelation link = (IndirectRelation) linkObject;
			if (IndirectRelationEditPart.VISUAL_ID != NotionDiagramVisualIDRegistry
					.getLinkWithClassVisualID(link)) {
				continue;
			}
			Notion dst = link.getTarget();
			Notion src = link.getSource();
			if (src != source) {
				continue;
			}
			result.add(new NotionDiagramLinkDescriptor(src, dst, link,
					NotionDiagramElementTypes.IndirectRelation_4004,
					IndirectRelationEditPart.VISUAL_ID));
		}
		return result;
	}

}
