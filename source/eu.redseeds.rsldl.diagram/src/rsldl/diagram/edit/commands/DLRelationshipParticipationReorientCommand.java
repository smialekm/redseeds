package rsldl.diagram.edit.commands;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.emf.type.core.commands.EditElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientRelationshipRequest;

import rsldl.DLRelationship;
import rsldl.DLRelationshipParticipant;
import rsldl.DLRelationshipParticipation;
import rsldl.diagram.edit.policies.RsldlBaseItemSemanticEditPolicy;

/**
 * @generated
 */
public class DLRelationshipParticipationReorientCommand extends
		EditElementCommand {

	/**
	 * @generated
	 */
	private final int reorientDirection;

	/**
	 * @generated
	 */
	private final EObject oldEnd;

	/**
	 * @generated
	 */
	private final EObject newEnd;

	/**
	 * @generated
	 */
	public DLRelationshipParticipationReorientCommand(
			ReorientRelationshipRequest request) {
		super(request.getLabel(), request.getRelationship(), request);
		reorientDirection = request.getDirection();
		oldEnd = request.getOldRelationshipEnd();
		newEnd = request.getNewRelationshipEnd();
	}

	/**
	 * @generated
	 */
	public boolean canExecute() {
		if (false == getElementToEdit() instanceof DLRelationshipParticipation) {
			return false;
		}
		if (reorientDirection == ReorientRelationshipRequest.REORIENT_SOURCE) {
			return canReorientSource();
		}
		if (reorientDirection == ReorientRelationshipRequest.REORIENT_TARGET) {
			return canReorientTarget();
		}
		return false;
	}

	/**
	 * @generated
	 */
	protected boolean canReorientSource() {
		if (!(oldEnd instanceof DLRelationship && newEnd instanceof DLRelationship)) {
			return false;
		}
		DLRelationshipParticipant target = getLink().getParticipant();
		if (!(getLink().eContainer() instanceof DLRelationship)) {
			return false;
		}
		DLRelationship container = (DLRelationship) getLink().eContainer();
		return RsldlBaseItemSemanticEditPolicy.getLinkConstraints()
				.canExistDLRelationshipParticipation_4001(container, getLink(),
						getNewSource(), target);
	}

	/**
	 * @generated
	 */
	protected boolean canReorientTarget() {
		if (!(oldEnd instanceof DLRelationshipParticipant && newEnd instanceof DLRelationshipParticipant)) {
			return false;
		}
		DLRelationship source = getLink().getRelationship();
		if (!(getLink().eContainer() instanceof DLRelationship)) {
			return false;
		}
		DLRelationship container = (DLRelationship) getLink().eContainer();
		return RsldlBaseItemSemanticEditPolicy.getLinkConstraints()
				.canExistDLRelationshipParticipation_4001(container, getLink(),
						source, getNewTarget());
	}

	/**
	 * @generated
	 */
	protected CommandResult doExecuteWithResult(IProgressMonitor monitor,
			IAdaptable info) throws ExecutionException {
		if (!canExecute()) {
			throw new ExecutionException(
					"Invalid arguments in reorient link command"); //$NON-NLS-1$
		}
		if (reorientDirection == ReorientRelationshipRequest.REORIENT_SOURCE) {
			return reorientSource();
		}
		if (reorientDirection == ReorientRelationshipRequest.REORIENT_TARGET) {
			return reorientTarget();
		}
		throw new IllegalStateException();
	}

	/**
	 * @generated
	 */
	protected CommandResult reorientSource() throws ExecutionException {
		getLink().setRelationship(getNewSource());
		return CommandResult.newOKCommandResult(getLink());
	}

	/**
	 * @generated
	 */
	protected CommandResult reorientTarget() throws ExecutionException {
		getLink().setParticipant(getNewTarget());
		return CommandResult.newOKCommandResult(getLink());
	}

	/**
	 * @generated
	 */
	protected DLRelationshipParticipation getLink() {
		return (DLRelationshipParticipation) getElementToEdit();
	}

	/**
	 * @generated
	 */
	protected DLRelationship getOldSource() {
		return (DLRelationship) oldEnd;
	}

	/**
	 * @generated
	 */
	protected DLRelationship getNewSource() {
		return (DLRelationship) newEnd;
	}

	/**
	 * @generated
	 */
	protected DLRelationshipParticipant getOldTarget() {
		return (DLRelationshipParticipant) oldEnd;
	}

	/**
	 * @generated
	 */
	protected DLRelationshipParticipant getNewTarget() {
		return (DLRelationshipParticipant) newEnd;
	}
}
