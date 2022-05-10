package rsldl.diagram.edit.policies;

import java.util.Iterator;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand;
import org.eclipse.gmf.runtime.emf.commands.core.command.CompositeTransactionalCommand;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateRelationshipRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientRelationshipRequest;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.View;

import rsldl.diagram.edit.commands.DLRelationshipParticipation2CreateCommand;
import rsldl.diagram.edit.commands.DLRelationshipParticipation2ReorientCommand;
import rsldl.diagram.edit.commands.DLRelationshipParticipation3CreateCommand;
import rsldl.diagram.edit.commands.DLRelationshipParticipation3ReorientCommand;
import rsldl.diagram.edit.commands.DLRelationshipParticipationCreateCommand;
import rsldl.diagram.edit.commands.DLRelationshipParticipationReorientCommand;
import rsldl.diagram.edit.parts.DLRelationshipParticipation2EditPart;
import rsldl.diagram.edit.parts.DLRelationshipParticipation3EditPart;
import rsldl.diagram.edit.parts.DLRelationshipParticipationEditPart;
import rsldl.diagram.part.RsldlVisualIDRegistry;
import rsldl.diagram.providers.RsldlElementTypes;

/**
 * @generated
 */
public class DLPatternBasedReferenceItemSemanticEditPolicy extends
		RsldlBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	public DLPatternBasedReferenceItemSemanticEditPolicy() {
		super(RsldlElementTypes.DLPatternBasedReference_2005);
	}

	/**
	 * @generated
	 */
	protected Command getDestroyElementCommand(DestroyElementRequest req) {
		View view = (View) getHost().getModel();
		CompositeTransactionalCommand cmd = new CompositeTransactionalCommand(
				getEditingDomain(), null);
		cmd.setTransactionNestingEnabled(false);
		for (Iterator<?> it = view.getSourceEdges().iterator(); it.hasNext();) {
			Edge outgoingLink = (Edge) it.next();
			if (RsldlVisualIDRegistry.getVisualID(outgoingLink) == DLRelationshipParticipationEditPart.VISUAL_ID) {
				DestroyElementRequest r = new DestroyElementRequest(
						outgoingLink.getElement(), false);
				cmd.add(new DestroyElementCommand(r));
				cmd.add(new DeleteCommand(getEditingDomain(), outgoingLink));
				continue;
			}
			if (RsldlVisualIDRegistry.getVisualID(outgoingLink) == DLRelationshipParticipation2EditPart.VISUAL_ID) {
				DestroyElementRequest r = new DestroyElementRequest(
						outgoingLink.getElement(), false);
				cmd.add(new DestroyElementCommand(r));
				cmd.add(new DeleteCommand(getEditingDomain(), outgoingLink));
				continue;
			}
			if (RsldlVisualIDRegistry.getVisualID(outgoingLink) == DLRelationshipParticipation3EditPart.VISUAL_ID) {
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
		return null;
	}

	/**
	 * @generated
	 */
	protected Command getCompleteCreateRelationshipCommand(
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
		}
		return super.getReorientRelationshipCommand(req);
	}

}
