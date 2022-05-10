package rsldl.diagram.edit.policies;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.diagram.ui.commands.DeferredLayoutCommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.commands.SetViewMutabilityCommand;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.CanonicalEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateConnectionViewRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.RequestConstants;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;

import rsldl.RsldlPackage;
import rsldl.diagram.edit.parts.DLAlghoritmicTransitionEditPart;
import rsldl.diagram.edit.parts.DLAttributeLinkEditPart;
import rsldl.diagram.edit.parts.DLDataBasedReferenceEditPart;
import rsldl.diagram.edit.parts.DLDiagramEditPart;
import rsldl.diagram.edit.parts.DLEntityEditPart;
import rsldl.diagram.edit.parts.DLIdentityCondition2EditPart;
import rsldl.diagram.edit.parts.DLIdentityConditionEditPart;
import rsldl.diagram.edit.parts.DLInheritanceCondition2EditPart;
import rsldl.diagram.edit.parts.DLInheritanceConditionEditPart;
import rsldl.diagram.edit.parts.DLPatternBasedReferenceEditPart;
import rsldl.diagram.edit.parts.DLPatternBasedTransitionEditPart;
import rsldl.diagram.edit.parts.DLPrimitiveEditPart;
import rsldl.diagram.edit.parts.DLPropertyEditPart;
import rsldl.diagram.edit.parts.DLRelationshipParticipation2EditPart;
import rsldl.diagram.edit.parts.DLRelationshipParticipation3EditPart;
import rsldl.diagram.edit.parts.DLRelationshipParticipationEditPart;
import rsldl.diagram.edit.parts.DLValidityCondition2EditPart;
import rsldl.diagram.edit.parts.DLValidityConditionEditPart;
import rsldl.diagram.part.RsldlDiagramUpdater;
import rsldl.diagram.part.RsldlLinkDescriptor;
import rsldl.diagram.part.RsldlNodeDescriptor;
import rsldl.diagram.part.RsldlVisualIDRegistry;

/**
 * @generated
 */
public class DLDiagramCanonicalEditPolicy extends CanonicalEditPolicy {

	/**
	 * @generated
	 */
	private Set<EStructuralFeature> myFeaturesToSynchronize;

	/**
	 * @generated
	 */
	protected void refreshOnActivate() {
		// Need to activate editpart children before invoking the canonical refresh for EditParts to add event listeners
		List<?> c = getHost().getChildren();
		for (int i = 0; i < c.size(); i++) {
			((EditPart) c.get(i)).activate();
		}
		super.refreshOnActivate();
	}

	/**
	 * @generated
	 */
	protected Set getFeaturesToSynchronize() {
		if (myFeaturesToSynchronize == null) {
			myFeaturesToSynchronize = new HashSet<EStructuralFeature>();
			myFeaturesToSynchronize.add(RsldlPackage.eINSTANCE
					.getDLDiagram_RelationshipParticipants());
			myFeaturesToSynchronize.add(RsldlPackage.eINSTANCE
					.getDLDiagram_Relationships());
		}
		return myFeaturesToSynchronize;
	}

	/**
	 * @generated
	 */
	@SuppressWarnings("rawtypes")
	protected List getSemanticChildrenList() {
		View viewObject = (View) getHost().getModel();
		LinkedList<EObject> result = new LinkedList<EObject>();
		List<RsldlNodeDescriptor> childDescriptors = RsldlDiagramUpdater
				.getDLDiagram_1000SemanticChildren(viewObject);
		for (RsldlNodeDescriptor d : childDescriptors) {
			result.add(d.getModelElement());
		}
		return result;
	}

	/**
	 * @generated
	 */
	protected boolean isOrphaned(Collection<EObject> semanticChildren,
			final View view) {
		return isMyDiagramElement(view)
				&& !semanticChildren.contains(view.getElement());
	}

	/**
	 * @generated
	 */
	private boolean isMyDiagramElement(View view) {
		int visualID = RsldlVisualIDRegistry.getVisualID(view);
		switch (visualID) {
		case DLEntityEditPart.VISUAL_ID:
		case DLPropertyEditPart.VISUAL_ID:
		case DLPrimitiveEditPart.VISUAL_ID:
		case DLDataBasedReferenceEditPart.VISUAL_ID:
		case DLPatternBasedReferenceEditPart.VISUAL_ID:
		case DLAlghoritmicTransitionEditPart.VISUAL_ID:
		case DLPatternBasedTransitionEditPart.VISUAL_ID:
			return true;
		}
		return false;
	}

	/**
	 * @generated
	 */
	protected void refreshSemantic() {
		if (resolveSemanticElement() == null) {
			return;
		}
		LinkedList<IAdaptable> createdViews = new LinkedList<IAdaptable>();
		List<RsldlNodeDescriptor> childDescriptors = RsldlDiagramUpdater
				.getDLDiagram_1000SemanticChildren((View) getHost().getModel());
		LinkedList<View> orphaned = new LinkedList<View>();
		// we care to check only views we recognize as ours
		LinkedList<View> knownViewChildren = new LinkedList<View>();
		for (View v : getViewChildren()) {
			if (isMyDiagramElement(v)) {
				knownViewChildren.add(v);
			}
		}
		// alternative to #cleanCanonicalSemanticChildren(getViewChildren(), semanticChildren)
		//
		// iteration happens over list of desired semantic elements, trying to find best matching View, while original CEP
		// iterates views, potentially losing view (size/bounds) information - i.e. if there are few views to reference same EObject, only last one 
		// to answer isOrphaned == true will be used for the domain element representation, see #cleanCanonicalSemanticChildren()
		for (Iterator<RsldlNodeDescriptor> descriptorsIterator = childDescriptors
				.iterator(); descriptorsIterator.hasNext();) {
			RsldlNodeDescriptor next = descriptorsIterator.next();
			String hint = RsldlVisualIDRegistry.getType(next.getVisualID());
			LinkedList<View> perfectMatch = new LinkedList<View>(); // both semanticElement and hint match that of NodeDescriptor
			for (View childView : getViewChildren()) {
				EObject semanticElement = childView.getElement();
				if (next.getModelElement().equals(semanticElement)) {
					if (hint.equals(childView.getType())) {
						perfectMatch.add(childView);
						// actually, can stop iteration over view children here, but
						// may want to use not the first view but last one as a 'real' match (the way original CEP does
						// with its trick with viewToSemanticMap inside #cleanCanonicalSemanticChildren
					}
				}
			}
			if (perfectMatch.size() > 0) {
				descriptorsIterator.remove(); // precise match found no need to create anything for the NodeDescriptor
				// use only one view (first or last?), keep rest as orphaned for further consideration
				knownViewChildren.remove(perfectMatch.getFirst());
			}
		}
		// those left in knownViewChildren are subject to removal - they are our diagram elements we didn't find match to,
		// or those we have potential matches to, and thus need to be recreated, preserving size/location information.
		orphaned.addAll(knownViewChildren);
		//
		ArrayList<CreateViewRequest.ViewDescriptor> viewDescriptors = new ArrayList<CreateViewRequest.ViewDescriptor>(
				childDescriptors.size());
		for (RsldlNodeDescriptor next : childDescriptors) {
			String hint = RsldlVisualIDRegistry.getType(next.getVisualID());
			IAdaptable elementAdapter = new CanonicalElementAdapter(
					next.getModelElement(), hint);
			CreateViewRequest.ViewDescriptor descriptor = new CreateViewRequest.ViewDescriptor(
					elementAdapter, Node.class, hint, ViewUtil.APPEND, false,
					host().getDiagramPreferencesHint());
			viewDescriptors.add(descriptor);
		}

		boolean changed = deleteViews(orphaned.iterator());
		//
		CreateViewRequest request = getCreateViewRequest(viewDescriptors);
		Command cmd = getCreateViewCommand(request);
		if (cmd != null && cmd.canExecute()) {
			SetViewMutabilityCommand.makeMutable(
					new EObjectAdapter(host().getNotationView())).execute();
			executeCommand(cmd);
			@SuppressWarnings("unchecked")
			List<IAdaptable> nl = (List<IAdaptable>) request.getNewObject();
			createdViews.addAll(nl);
		}
		if (changed || createdViews.size() > 0) {
			postProcessRefreshSemantic(createdViews);
		}

		Collection<IAdaptable> createdConnectionViews = refreshConnections();

		if (createdViews.size() > 1) {
			// perform a layout of the container
			DeferredLayoutCommand layoutCmd = new DeferredLayoutCommand(host()
					.getEditingDomain(), createdViews, host());
			executeCommand(new ICommandProxy(layoutCmd));
		}

		createdViews.addAll(createdConnectionViews);

		makeViewsImmutable(createdViews);
	}

	/**
	 * @generated
	 */
	private Collection<IAdaptable> refreshConnections() {
		Map<EObject, View> domain2NotationMap = new HashMap<EObject, View>();
		Collection<RsldlLinkDescriptor> linkDescriptors = collectAllLinks(
				getDiagram(), domain2NotationMap);
		Collection existingLinks = new LinkedList(getDiagram().getEdges());
		for (Iterator linksIterator = existingLinks.iterator(); linksIterator
				.hasNext();) {
			Edge nextDiagramLink = (Edge) linksIterator.next();
			int diagramLinkVisualID = RsldlVisualIDRegistry
					.getVisualID(nextDiagramLink);
			if (diagramLinkVisualID == -1) {
				if (nextDiagramLink.getSource() != null
						&& nextDiagramLink.getTarget() != null) {
					linksIterator.remove();
				}
				continue;
			}
			EObject diagramLinkObject = nextDiagramLink.getElement();
			EObject diagramLinkSrc = nextDiagramLink.getSource().getElement();
			EObject diagramLinkDst = nextDiagramLink.getTarget().getElement();
			for (Iterator<RsldlLinkDescriptor> linkDescriptorsIterator = linkDescriptors
					.iterator(); linkDescriptorsIterator.hasNext();) {
				RsldlLinkDescriptor nextLinkDescriptor = linkDescriptorsIterator
						.next();
				if (diagramLinkObject == nextLinkDescriptor.getModelElement()
						&& diagramLinkSrc == nextLinkDescriptor.getSource()
						&& diagramLinkDst == nextLinkDescriptor
								.getDestination()
						&& diagramLinkVisualID == nextLinkDescriptor
								.getVisualID()) {
					linksIterator.remove();
					linkDescriptorsIterator.remove();
					break;
				}
			}
		}
		deleteViews(existingLinks.iterator());
		return createConnections(linkDescriptors, domain2NotationMap);
	}

	/**
	 * @generated
	 */
	private Collection<RsldlLinkDescriptor> collectAllLinks(View view,
			Map<EObject, View> domain2NotationMap) {
		if (!DLDiagramEditPart.MODEL_ID.equals(RsldlVisualIDRegistry
				.getModelID(view))) {
			return Collections.emptyList();
		}
		LinkedList<RsldlLinkDescriptor> result = new LinkedList<RsldlLinkDescriptor>();
		switch (RsldlVisualIDRegistry.getVisualID(view)) {
		case DLDiagramEditPart.VISUAL_ID: {
			if (!domain2NotationMap.containsKey(view.getElement())) {
				result.addAll(RsldlDiagramUpdater
						.getDLDiagram_1000ContainedLinks(view));
			}
			if (!domain2NotationMap.containsKey(view.getElement())
					|| view.getEAnnotation("Shortcut") == null) { //$NON-NLS-1$
				domain2NotationMap.put(view.getElement(), view);
			}
			break;
		}
		case DLEntityEditPart.VISUAL_ID: {
			if (!domain2NotationMap.containsKey(view.getElement())) {
				result.addAll(RsldlDiagramUpdater
						.getDLEntity_2001ContainedLinks(view));
			}
			if (!domain2NotationMap.containsKey(view.getElement())
					|| view.getEAnnotation("Shortcut") == null) { //$NON-NLS-1$
				domain2NotationMap.put(view.getElement(), view);
			}
			break;
		}
		case DLPropertyEditPart.VISUAL_ID: {
			if (!domain2NotationMap.containsKey(view.getElement())) {
				result.addAll(RsldlDiagramUpdater
						.getDLProperty_2002ContainedLinks(view));
			}
			if (!domain2NotationMap.containsKey(view.getElement())
					|| view.getEAnnotation("Shortcut") == null) { //$NON-NLS-1$
				domain2NotationMap.put(view.getElement(), view);
			}
			break;
		}
		case DLPrimitiveEditPart.VISUAL_ID: {
			if (!domain2NotationMap.containsKey(view.getElement())) {
				result.addAll(RsldlDiagramUpdater
						.getDLPrimitive_2003ContainedLinks(view));
			}
			if (!domain2NotationMap.containsKey(view.getElement())
					|| view.getEAnnotation("Shortcut") == null) { //$NON-NLS-1$
				domain2NotationMap.put(view.getElement(), view);
			}
			break;
		}
		case DLDataBasedReferenceEditPart.VISUAL_ID: {
			if (!domain2NotationMap.containsKey(view.getElement())) {
				result.addAll(RsldlDiagramUpdater
						.getDLDataBasedReference_2004ContainedLinks(view));
			}
			if (!domain2NotationMap.containsKey(view.getElement())
					|| view.getEAnnotation("Shortcut") == null) { //$NON-NLS-1$
				domain2NotationMap.put(view.getElement(), view);
			}
			break;
		}
		case DLPatternBasedReferenceEditPart.VISUAL_ID: {
			if (!domain2NotationMap.containsKey(view.getElement())) {
				result.addAll(RsldlDiagramUpdater
						.getDLPatternBasedReference_2005ContainedLinks(view));
			}
			if (!domain2NotationMap.containsKey(view.getElement())
					|| view.getEAnnotation("Shortcut") == null) { //$NON-NLS-1$
				domain2NotationMap.put(view.getElement(), view);
			}
			break;
		}
		case DLAlghoritmicTransitionEditPart.VISUAL_ID: {
			if (!domain2NotationMap.containsKey(view.getElement())) {
				result.addAll(RsldlDiagramUpdater
						.getDLAlghoritmicTransition_2006ContainedLinks(view));
			}
			if (!domain2NotationMap.containsKey(view.getElement())
					|| view.getEAnnotation("Shortcut") == null) { //$NON-NLS-1$
				domain2NotationMap.put(view.getElement(), view);
			}
			break;
		}
		case DLPatternBasedTransitionEditPart.VISUAL_ID: {
			if (!domain2NotationMap.containsKey(view.getElement())) {
				result.addAll(RsldlDiagramUpdater
						.getDLPatternBasedTransition_2007ContainedLinks(view));
			}
			if (!domain2NotationMap.containsKey(view.getElement())
					|| view.getEAnnotation("Shortcut") == null) { //$NON-NLS-1$
				domain2NotationMap.put(view.getElement(), view);
			}
			break;
		}
		case DLIdentityConditionEditPart.VISUAL_ID: {
			if (!domain2NotationMap.containsKey(view.getElement())) {
				result.addAll(RsldlDiagramUpdater
						.getDLIdentityCondition_3001ContainedLinks(view));
			}
			if (!domain2NotationMap.containsKey(view.getElement())
					|| view.getEAnnotation("Shortcut") == null) { //$NON-NLS-1$
				domain2NotationMap.put(view.getElement(), view);
			}
			break;
		}
		case DLInheritanceConditionEditPart.VISUAL_ID: {
			if (!domain2NotationMap.containsKey(view.getElement())) {
				result.addAll(RsldlDiagramUpdater
						.getDLInheritanceCondition_3002ContainedLinks(view));
			}
			if (!domain2NotationMap.containsKey(view.getElement())
					|| view.getEAnnotation("Shortcut") == null) { //$NON-NLS-1$
				domain2NotationMap.put(view.getElement(), view);
			}
			break;
		}
		case DLValidityConditionEditPart.VISUAL_ID: {
			if (!domain2NotationMap.containsKey(view.getElement())) {
				result.addAll(RsldlDiagramUpdater
						.getDLValidityCondition_3003ContainedLinks(view));
			}
			if (!domain2NotationMap.containsKey(view.getElement())
					|| view.getEAnnotation("Shortcut") == null) { //$NON-NLS-1$
				domain2NotationMap.put(view.getElement(), view);
			}
			break;
		}
		case DLIdentityCondition2EditPart.VISUAL_ID: {
			if (!domain2NotationMap.containsKey(view.getElement())) {
				result.addAll(RsldlDiagramUpdater
						.getDLIdentityCondition_3004ContainedLinks(view));
			}
			if (!domain2NotationMap.containsKey(view.getElement())
					|| view.getEAnnotation("Shortcut") == null) { //$NON-NLS-1$
				domain2NotationMap.put(view.getElement(), view);
			}
			break;
		}
		case DLInheritanceCondition2EditPart.VISUAL_ID: {
			if (!domain2NotationMap.containsKey(view.getElement())) {
				result.addAll(RsldlDiagramUpdater
						.getDLInheritanceCondition_3005ContainedLinks(view));
			}
			if (!domain2NotationMap.containsKey(view.getElement())
					|| view.getEAnnotation("Shortcut") == null) { //$NON-NLS-1$
				domain2NotationMap.put(view.getElement(), view);
			}
			break;
		}
		case DLValidityCondition2EditPart.VISUAL_ID: {
			if (!domain2NotationMap.containsKey(view.getElement())) {
				result.addAll(RsldlDiagramUpdater
						.getDLValidityCondition_3006ContainedLinks(view));
			}
			if (!domain2NotationMap.containsKey(view.getElement())
					|| view.getEAnnotation("Shortcut") == null) { //$NON-NLS-1$
				domain2NotationMap.put(view.getElement(), view);
			}
			break;
		}
		case DLRelationshipParticipationEditPart.VISUAL_ID: {
			if (!domain2NotationMap.containsKey(view.getElement())) {
				result.addAll(RsldlDiagramUpdater
						.getDLRelationshipParticipation_4001ContainedLinks(view));
			}
			if (!domain2NotationMap.containsKey(view.getElement())
					|| view.getEAnnotation("Shortcut") == null) { //$NON-NLS-1$
				domain2NotationMap.put(view.getElement(), view);
			}
			break;
		}
		case DLRelationshipParticipation2EditPart.VISUAL_ID: {
			if (!domain2NotationMap.containsKey(view.getElement())) {
				result.addAll(RsldlDiagramUpdater
						.getDLRelationshipParticipation_4002ContainedLinks(view));
			}
			if (!domain2NotationMap.containsKey(view.getElement())
					|| view.getEAnnotation("Shortcut") == null) { //$NON-NLS-1$
				domain2NotationMap.put(view.getElement(), view);
			}
			break;
		}
		case DLRelationshipParticipation3EditPart.VISUAL_ID: {
			if (!domain2NotationMap.containsKey(view.getElement())) {
				result.addAll(RsldlDiagramUpdater
						.getDLRelationshipParticipation_4003ContainedLinks(view));
			}
			if (!domain2NotationMap.containsKey(view.getElement())
					|| view.getEAnnotation("Shortcut") == null) { //$NON-NLS-1$
				domain2NotationMap.put(view.getElement(), view);
			}
			break;
		}
		case DLAttributeLinkEditPart.VISUAL_ID: {
			if (!domain2NotationMap.containsKey(view.getElement())) {
				result.addAll(RsldlDiagramUpdater
						.getDLAttributeLink_4004ContainedLinks(view));
			}
			if (!domain2NotationMap.containsKey(view.getElement())
					|| view.getEAnnotation("Shortcut") == null) { //$NON-NLS-1$
				domain2NotationMap.put(view.getElement(), view);
			}
			break;
		}
		}
		for (Iterator children = view.getChildren().iterator(); children
				.hasNext();) {
			result.addAll(collectAllLinks((View) children.next(),
					domain2NotationMap));
		}
		for (Iterator edges = view.getSourceEdges().iterator(); edges.hasNext();) {
			result.addAll(collectAllLinks((View) edges.next(),
					domain2NotationMap));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private Collection<IAdaptable> createConnections(
			Collection<RsldlLinkDescriptor> linkDescriptors,
			Map<EObject, View> domain2NotationMap) {
		LinkedList<IAdaptable> adapters = new LinkedList<IAdaptable>();
		for (RsldlLinkDescriptor nextLinkDescriptor : linkDescriptors) {
			EditPart sourceEditPart = getEditPart(
					nextLinkDescriptor.getSource(), domain2NotationMap);
			EditPart targetEditPart = getEditPart(
					nextLinkDescriptor.getDestination(), domain2NotationMap);
			if (sourceEditPart == null || targetEditPart == null) {
				continue;
			}
			CreateConnectionViewRequest.ConnectionViewDescriptor descriptor = new CreateConnectionViewRequest.ConnectionViewDescriptor(
					nextLinkDescriptor.getSemanticAdapter(),
					RsldlVisualIDRegistry.getType(nextLinkDescriptor
							.getVisualID()), ViewUtil.APPEND, false,
					((IGraphicalEditPart) getHost())
							.getDiagramPreferencesHint());
			CreateConnectionViewRequest ccr = new CreateConnectionViewRequest(
					descriptor);
			ccr.setType(RequestConstants.REQ_CONNECTION_START);
			ccr.setSourceEditPart(sourceEditPart);
			sourceEditPart.getCommand(ccr);
			ccr.setTargetEditPart(targetEditPart);
			ccr.setType(RequestConstants.REQ_CONNECTION_END);
			Command cmd = targetEditPart.getCommand(ccr);
			if (cmd != null && cmd.canExecute()) {
				executeCommand(cmd);
				IAdaptable viewAdapter = (IAdaptable) ccr.getNewObject();
				if (viewAdapter != null) {
					adapters.add(viewAdapter);
				}
			}
		}
		return adapters;
	}

	/**
	 * @generated
	 */
	private EditPart getEditPart(EObject domainModelElement,
			Map<EObject, View> domain2NotationMap) {
		View view = (View) domain2NotationMap.get(domainModelElement);
		if (view != null) {
			return (EditPart) getHost().getViewer().getEditPartRegistry()
					.get(view);
		}
		return null;
	}

	/**
	 * @generated
	 */
	private Diagram getDiagram() {
		return ((View) getHost().getModel()).getDiagram();
	}
}
