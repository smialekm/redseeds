package notiondiagram.diagram.edit.policies;

import java.util.Iterator;
import notiondiagram.diagram.edit.commands.AttributeRelationCreateCommand;
import notiondiagram.diagram.edit.commands.AttributeRelationReorientCommand;
import notiondiagram.diagram.edit.commands.DirectedRelationCreateCommand;
import notiondiagram.diagram.edit.commands.DirectedRelationReorientCommand;
import notiondiagram.diagram.edit.commands.GeneralizationCreateCommand;
import notiondiagram.diagram.edit.commands.GeneralizationReorientCommand;
import notiondiagram.diagram.edit.commands.IndirectRelationCreateCommand;
import notiondiagram.diagram.edit.commands.IndirectRelationReorientCommand;
import notiondiagram.diagram.edit.parts.AttributeRelationEditPart;
import notiondiagram.diagram.edit.parts.DirectedRelationEditPart;
import notiondiagram.diagram.edit.parts.GeneralizationEditPart;
import notiondiagram.diagram.edit.parts.IndirectRelationEditPart;
import notiondiagram.diagram.edit.parts.NotionNotionCompartmentEditPart;
import notiondiagram.diagram.edit.parts.PhraseEditPart;
import notiondiagram.diagram.part.NotionDiagramVisualIDRegistry;
import notiondiagram.diagram.providers.NotionDiagramElementTypes;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.common.core.command.ICompositeCommand;
import org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand;
import org.eclipse.gmf.runtime.emf.commands.core.command.CompositeTransactionalCommand;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateRelationshipRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientRelationshipRequest;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @generated
 */
public class NotionItemSemanticEditPolicy extends
		NotionDiagramBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	public NotionItemSemanticEditPolicy() {
		super(NotionDiagramElementTypes.Notion_2001);
	}

	/**
	 * @generated
	 */
	protected Command getDestroyElementCommand(DestroyElementRequest req) {
		View view = (View) getHost().getModel();
		CompositeTransactionalCommand cmd = new CompositeTransactionalCommand(
				getEditingDomain(), null);
		cmd.setTransactionNestingEnabled(false);
		for (Iterator<?> it = view.getTargetEdges().iterator(); it.hasNext();) {
			Edge incomingLink = (Edge) it.next();
			if (NotionDiagramVisualIDRegistry.getVisualID(incomingLink) == GeneralizationEditPart.VISUAL_ID) {
				DestroyElementRequest r = new DestroyElementRequest(
						incomingLink.getElement(), false);
				cmd.add(new DestroyElementCommand(r));
				cmd.add(new DeleteCommand(getEditingDomain(), incomingLink));
				continue;
			}
			if (NotionDiagramVisualIDRegistry.getVisualID(incomingLink) == AttributeRelationEditPart.VISUAL_ID) {
				DestroyElementRequest r = new DestroyElementRequest(
						incomingLink.getElement(), false);
				cmd.add(new DestroyElementCommand(r));
				cmd.add(new DeleteCommand(getEditingDomain(), incomingLink));
				continue;
			}
			if (NotionDiagramVisualIDRegistry.getVisualID(incomingLink) == DirectedRelationEditPart.VISUAL_ID) {
				DestroyElementRequest r = new DestroyElementRequest(
						incomingLink.getElement(), false);
				cmd.add(new DestroyElementCommand(r));
				cmd.add(new DeleteCommand(getEditingDomain(), incomingLink));
				continue;
			}
			if (NotionDiagramVisualIDRegistry.getVisualID(incomingLink) == IndirectRelationEditPart.VISUAL_ID) {
				DestroyElementRequest r = new DestroyElementRequest(
						incomingLink.getElement(), false);
				cmd.add(new DestroyElementCommand(r));
				cmd.add(new DeleteCommand(getEditingDomain(), incomingLink));
				continue;
			}
		}
		for (Iterator<?> it = view.getSourceEdges().iterator(); it.hasNext();) {
			Edge outgoingLink = (Edge) it.next();
			if (NotionDiagramVisualIDRegistry.getVisualID(outgoingLink) == GeneralizationEditPart.VISUAL_ID) {
				DestroyElementRequest r = new DestroyElementRequest(
						outgoingLink.getElement(), false);
				cmd.add(new DestroyElementCommand(r));
				cmd.add(new DeleteCommand(getEditingDomain(), outgoingLink));
				continue;
			}
			if (NotionDiagramVisualIDRegistry.getVisualID(outgoingLink) == AttributeRelationEditPart.VISUAL_ID) {
				DestroyElementRequest r = new DestroyElementRequest(
						outgoingLink.getElement(), false);
				cmd.add(new DestroyElementCommand(r));
				cmd.add(new DeleteCommand(getEditingDomain(), outgoingLink));
				continue;
			}
			if (NotionDiagramVisualIDRegistry.getVisualID(outgoingLink) == DirectedRelationEditPart.VISUAL_ID) {
				DestroyElementRequest r = new DestroyElementRequest(
						outgoingLink.getElement(), false);
				cmd.add(new DestroyElementCommand(r));
				cmd.add(new DeleteCommand(getEditingDomain(), outgoingLink));
				continue;
			}
			if (NotionDiagramVisualIDRegistry.getVisualID(outgoingLink) == IndirectRelationEditPart.VISUAL_ID) {
				DestroyElementRequest r = new DestroyElementRequest(
						outgoingLink.getElement(), false);
				cmd.add(new DestroyElementCommand(r));
				cmd.add(new DeleteCommand(getEditingDomain(), outgoingLink));
				continue;
			}
		}
		EAnnotation annotation = view.getEAnnotation("Shortcut"); //$NON-NLS-1$
		if (annotation == null) {
			// there are indirectly referenced children, need extra commands: false
			addDestroyChildNodesCommand(cmd);
			addDestroyShortcutsCommand(cmd, view);
			// delete host element
			cmd.add(new DestroyElementCommand(req));
		} else {
			cmd.add(new DeleteCommand(getEditingDomain(), view));
		}
		return getGEFWrapper(cmd.reduce());
	}

	/**
	 * @generated
	 */
	private void addDestroyChildNodesCommand(ICompositeCommand cmd) {
		View view = (View) getHost().getModel();
		for (Iterator<?> nit = view.getChildren().iterator(); nit.hasNext();) {
			Node node = (Node) nit.next();
			switch (NotionDiagramVisualIDRegistry.getVisualID(node)) {
			case NotionNotionCompartmentEditPart.VISUAL_ID:
				for (Iterator<?> cit = node.getChildren().iterator(); cit
						.hasNext();) {
					Node cnode = (Node) cit.next();
					switch (NotionDiagramVisualIDRegistry.getVisualID(cnode)) {
					case PhraseEditPart.VISUAL_ID:
						cmd.add(new DestroyElementCommand(
								new DestroyElementRequest(getEditingDomain(),
										cnode.getElement(), false))); // directlyOwned: true
						// don't need explicit deletion of cnode as parent's view deletion would clean child views as well 
						// cmd.add(new org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand(getEditingDomain(), cnode));
						break;
					}
				}
				break;
			}
		}
	}

	/**
	 * @generated
	 */
	protected Command getCreateRelationshipCommand(CreateRelationshipRequest req) {
		Command command = req.getTarget() == null ? getStartCreateRelationshipCommand(req)
				: getCompleteCreateRelationshipCommand(req);
		return command != null ? command : super
				.getCreateRelationshipCommand(req);
	}

	/**
	 * @generated
	 */
	protected Command getStartCreateRelationshipCommand(
			CreateRelationshipRequest req) {
		if (NotionDiagramElementTypes.Generalization_4001 == req
				.getElementType()) {
			return getGEFWrapper(new GeneralizationCreateCommand(req,
					req.getSource(), req.getTarget()));
		}
		if (NotionDiagramElementTypes.AttributeRelation_4002 == req
				.getElementType()) {
			return getGEFWrapper(new AttributeRelationCreateCommand(req,
					req.getSource(), req.getTarget()));
		}
		if (NotionDiagramElementTypes.DirectedRelation_4003 == req
				.getElementType()) {
			return getGEFWrapper(new DirectedRelationCreateCommand(req,
					req.getSource(), req.getTarget()));
		}
		if (NotionDiagramElementTypes.IndirectRelation_4004 == req
				.getElementType()) {
			return getGEFWrapper(new IndirectRelationCreateCommand(req,
					req.getSource(), req.getTarget()));
		}
		return null;
	}

	/**
	 * @generated
	 */
	protected Command getCompleteCreateRelationshipCommand(
			CreateRelationshipRequest req) {
		if (NotionDiagramElementTypes.Generalization_4001 == req
				.getElementType()) {
			return getGEFWrapper(new GeneralizationCreateCommand(req,
					req.getSource(), req.getTarget()));
		}
		if (NotionDiagramElementTypes.AttributeRelation_4002 == req
				.getElementType()) {
			return getGEFWrapper(new AttributeRelationCreateCommand(req,
					req.getSource(), req.getTarget()));
		}
		if (NotionDiagramElementTypes.DirectedRelation_4003 == req
				.getElementType()) {
			return getGEFWrapper(new DirectedRelationCreateCommand(req,
					req.getSource(), req.getTarget()));
		}
		if (NotionDiagramElementTypes.IndirectRelation_4004 == req
				.getElementType()) {
			return getGEFWrapper(new IndirectRelationCreateCommand(req,
					req.getSource(), req.getTarget()));
		}
		return null;
	}

	/**
	 * Returns command to reorient EClass based link. New link target or source
	 * should be the domain model element associated with this node.
	 * 
	 * @generated
	 */
	protected Command getReorientRelationshipCommand(
			ReorientRelationshipRequest req) {
		switch (getVisualID(req)) {
		case GeneralizationEditPart.VISUAL_ID:
			return getGEFWrapper(new GeneralizationReorientCommand(req));
		case AttributeRelationEditPart.VISUAL_ID:
			return getGEFWrapper(new AttributeRelationReorientCommand(req));
		case DirectedRelationEditPart.VISUAL_ID:
			return getGEFWrapper(new DirectedRelationReorientCommand(req));
		case IndirectRelationEditPart.VISUAL_ID:
			return getGEFWrapper(new IndirectRelationReorientCommand(req));
		}
		return super.getReorientRelationshipCommand(req);
	}

}
