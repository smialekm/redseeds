package rsldl.diagram.edit.policies;

import java.util.Iterator;

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

import rsldl.diagram.edit.commands.DLAttributeLinkCreateCommand;
import rsldl.diagram.edit.commands.DLAttributeLinkReorientCommand;
import rsldl.diagram.edit.commands.DLRelationshipParticipation2CreateCommand;
import rsldl.diagram.edit.commands.DLRelationshipParticipation2ReorientCommand;
import rsldl.diagram.edit.commands.DLRelationshipParticipation3CreateCommand;
import rsldl.diagram.edit.commands.DLRelationshipParticipation3ReorientCommand;
import rsldl.diagram.edit.commands.DLRelationshipParticipationCreateCommand;
import rsldl.diagram.edit.commands.DLRelationshipParticipationReorientCommand;
import rsldl.diagram.edit.parts.DLAttributeLinkEditPart;
import rsldl.diagram.edit.parts.DLIdentityCondition2EditPart;
import rsldl.diagram.edit.parts.DLInheritanceCondition2EditPart;
import rsldl.diagram.edit.parts.DLPropertyDLPropertyCompartmentEditPart;
import rsldl.diagram.edit.parts.DLRelationshipParticipation2EditPart;
import rsldl.diagram.edit.parts.DLRelationshipParticipation3EditPart;
import rsldl.diagram.edit.parts.DLRelationshipParticipationEditPart;
import rsldl.diagram.edit.parts.DLValidityCondition2EditPart;
import rsldl.diagram.part.RsldlVisualIDRegistry;
import rsldl.diagram.providers.RsldlElementTypes;

/**
 * @generated
 */
public class DLPropertyItemSemanticEditPolicy extends
		RsldlBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	public DLPropertyItemSemanticEditPolicy() {
		super(RsldlElementTypes.DLProperty_2002);
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
			if (RsldlVisualIDRegistry.getVisualID(incomingLink) == DLRelationshipParticipationEditPart.VISUAL_ID) {
				DestroyElementRequest r = new DestroyElementRequest(
						incomingLink.getElement(), false);
				cmd.add(new DestroyElementCommand(r));
				cmd.add(new DeleteCommand(getEditingDomain(), incomingLink));
				continue;
			}
			if (RsldlVisualIDRegistry.getVisualID(incomingLink) == DLRelationshipParticipation2EditPart.VISUAL_ID) {
				DestroyElementRequest r = new DestroyElementRequest(
						incomingLink.getElement(), false);
				cmd.add(new DestroyElementCommand(r));
				cmd.add(new DeleteCommand(getEditingDomain(), incomingLink));
				continue;
			}
			if (RsldlVisualIDRegistry.getVisualID(incomingLink) == DLRelationshipParticipation3EditPart.VISUAL_ID) {
				DestroyElementRequest r = new DestroyElementRequest(
						incomingLink.getElement(), false);
				cmd.add(new DestroyElementCommand(r));
				cmd.add(new DeleteCommand(getEditingDomain(), incomingLink));
				continue;
			}
			if (RsldlVisualIDRegistry.getVisualID(incomingLink) == DLAttributeLinkEditPart.VISUAL_ID) {
				DestroyElementRequest r = new DestroyElementRequest(
						incomingLink.getElement(), false);
				cmd.add(new DestroyElementCommand(r));
				cmd.add(new DeleteCommand(getEditingDomain(), incomingLink));
				continue;
			}
		}
		for (Iterator<?> it = view.getSourceEdges().iterator(); it.hasNext();) {
			Edge outgoingLink = (Edge) it.next();
			if (RsldlVisualIDRegistry.getVisualID(outgoingLink) == DLAttributeLinkEditPart.VISUAL_ID) {
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
			switch (RsldlVisualIDRegistry.getVisualID(node)) {
			case DLPropertyDLPropertyCompartmentEditPart.VISUAL_ID:
				for (Iterator<?> cit = node.getChildren().iterator(); cit
						.hasNext();) {
					Node cnode = (Node) cit.next();
					switch (RsldlVisualIDRegistry.getVisualID(cnode)) {
					case DLIdentityCondition2EditPart.VISUAL_ID:
						cmd.add(new DestroyElementCommand(
								new DestroyElementRequest(getEditingDomain(),
										cnode.getElement(), false))); // directlyOwned: true
						// don't need explicit deletion of cnode as parent's view deletion would clean child views as well 
						// cmd.add(new org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand(getEditingDomain(), cnode));
						break;
					case DLInheritanceCondition2EditPart.VISUAL_ID:
						cmd.add(new DestroyElementCommand(
								new DestroyElementRequest(getEditingDomain(),
										cnode.getElement(), false))); // directlyOwned: true
						// don't need explicit deletion of cnode as parent's view deletion would clean child views as well 
						// cmd.add(new org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand(getEditingDomain(), cnode));
						break;
					case DLValidityCondition2EditPart.VISUAL_ID:
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
		if (RsldlElementTypes.DLRelationshipParticipation_4001 == req
				.getElementType()) {
			return null;
		}
		if (RsldlElementTypes.DLRelationshipParticipation_4002 == req
				.getElementType()) {
			return null;
		}
		if (RsldlElementTypes.DLRelationshipParticipation_4003 == req
				.getElementType()) {
			return null;
		}
		if (RsldlElementTypes.DLAttributeLink_4004 == req.getElementType()) {
			return getGEFWrapper(new DLAttributeLinkCreateCommand(req,
					req.getSource(), req.getTarget()));
		}
		return null;
	}

	/**
	 * @generated
	 */
	protected Command getCompleteCreateRelationshipCommand(
			CreateRelationshipRequest req) {
		if (RsldlElementTypes.DLRelationshipParticipation_4001 == req
				.getElementType()) {
			return getGEFWrapper(new DLRelationshipParticipationCreateCommand(
					req, req.getSource(), req.getTarget()));
		}
		if (RsldlElementTypes.DLRelationshipParticipation_4002 == req
				.getElementType()) {
			return getGEFWrapper(new DLRelationshipParticipation2CreateCommand(
					req, req.getSource(), req.getTarget()));
		}
		if (RsldlElementTypes.DLRelationshipParticipation_4003 == req
				.getElementType()) {
			return getGEFWrapper(new DLRelationshipParticipation3CreateCommand(
					req, req.getSource(), req.getTarget()));
		}
		if (RsldlElementTypes.DLAttributeLink_4004 == req.getElementType()) {
			return getGEFWrapper(new DLAttributeLinkCreateCommand(req,
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
		case DLRelationshipParticipationEditPart.VISUAL_ID:
			return getGEFWrapper(new DLRelationshipParticipationReorientCommand(
					req));
		case DLRelationshipParticipation2EditPart.VISUAL_ID:
			return getGEFWrapper(new DLRelationshipParticipation2ReorientCommand(
					req));
		case DLRelationshipParticipation3EditPart.VISUAL_ID:
			return getGEFWrapper(new DLRelationshipParticipation3ReorientCommand(
					req));
		case DLAttributeLinkEditPart.VISUAL_ID:
			return getGEFWrapper(new DLAttributeLinkReorientCommand(req));
		}
		return super.getReorientRelationshipCommand(req);
	}

}
