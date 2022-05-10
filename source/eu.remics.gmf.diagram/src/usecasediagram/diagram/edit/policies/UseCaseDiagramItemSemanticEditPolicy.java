package usecasediagram.diagram.edit.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DuplicateElementsRequest;

import usecasediagram.diagram.edit.commands.ActorCreateCommand;
import usecasediagram.diagram.edit.commands.UseCaseCreateCommand;
import usecasediagram.diagram.providers.UseCaseDiagramElementTypes;

/**
 * @generated
 */
public class UseCaseDiagramItemSemanticEditPolicy extends
		UseCaseDiagramBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	public UseCaseDiagramItemSemanticEditPolicy() {
		super(UseCaseDiagramElementTypes.UseCaseDiagram_1000);
	}

	/**
	 * @generated
	 */
	protected Command getCreateCommand(CreateElementRequest req) {
		if (UseCaseDiagramElementTypes.Actor_2001 == req.getElementType()) {
			return getGEFWrapper(new ActorCreateCommand(req));
		}
		if (UseCaseDiagramElementTypes.UseCase_2002 == req.getElementType()) {
			return getGEFWrapper(new UseCaseCreateCommand(req));
		}
		return super.getCreateCommand(req);
	}

	/**
	 * @generated NOT
	 */
	protected Command getDuplicateCommand(DuplicateElementsRequest req) {
		return UnexecutableCommand.INSTANCE;
	}

}
