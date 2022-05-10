package rsldl.diagram.edit.commands;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.emf.type.core.commands.EditElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.ReorientRelationshipRequest;

import rsldl.DLAttributeLink;
import rsldl.DLFeatureType;
import rsldl.DLNotion;
import rsldl.DLProperty;
import rsldl.DLTypeRole;
import rsldl.diagram.edit.policies.RsldlBaseItemSemanticEditPolicy;

/**
 * @generated
 */
public class DLAttributeLinkReorientCommand extends EditElementCommand {

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
	public DLAttributeLinkReorientCommand(ReorientRelationshipRequest request) {
		super(request.getLabel(), request.getRelationship(), request);
		reorientDirection = request.getDirection();
		oldEnd = request.getOldRelationshipEnd();
		newEnd = request.getNewRelationshipEnd();
	}

	/**
	 * @generated
	 */
	public boolean canExecute() {
		if (false == getElementToEdit() instanceof DLAttributeLink) {
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
		if (!(oldEnd instanceof DLProperty && newEnd instanceof DLProperty)) {
			return false;
		}
		DLNotion target = getLink().getNotion();
		if (!(getLink().eContainer() instanceof DLNotion)) {
			return false;
		}
		DLNotion container = (DLNotion) getLink().eContainer();
		return RsldlBaseItemSemanticEditPolicy.getLinkConstraints()
				.canExistDLAttributeLink_4004(container, getLink(),
						getNewSource(), target);
	}

	/**
	 * @generated
	 */
	protected boolean canReorientTarget() {
		if (!(oldEnd instanceof DLNotion && newEnd instanceof DLNotion)) {
			return false;
		}
		DLProperty source = getLink().getAttribute();
		if (!(getLink().eContainer() instanceof DLNotion)) {
			return false;
		}
		DLNotion container = (DLNotion) getLink().eContainer();
		return RsldlBaseItemSemanticEditPolicy.getLinkConstraints()
				.canExistDLAttributeLink_4004(container, getLink(), source,
						getNewTarget());
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
		getLink().setAttribute(getNewSource());
		return CommandResult.newOKCommandResult(getLink());
	}

	/**
	 * @generated NOT
	 */
	protected CommandResult reorientTarget() throws ExecutionException {
		getLink().setNotion(getNewTarget());
		if (DLTypeRole.TEMPORARY_ROLE.equals(getNewTarget().getType()))
			getLink().setType(DLFeatureType.REQUIRED);
		else if (DLTypeRole.IDENTITY.equals(getNewTarget().getType()))
			getLink().setType(DLFeatureType.PROVIDED);
		return CommandResult.newOKCommandResult(getLink());
	}

	/**
	 * @generated
	 */
	protected DLAttributeLink getLink() {
		return (DLAttributeLink) getElementToEdit();
	}

	/**
	 * @generated
	 */
	protected DLProperty getOldSource() {
		return (DLProperty) oldEnd;
	}

	/**
	 * @generated
	 */
	protected DLProperty getNewSource() {
		return (DLProperty) newEnd;
	}

	/**
	 * @generated
	 */
	protected DLNotion getOldTarget() {
		return (DLNotion) oldEnd;
	}

	/**
	 * @generated
	 */
	protected DLNotion getNewTarget() {
		return (DLNotion) newEnd;
	}
}
