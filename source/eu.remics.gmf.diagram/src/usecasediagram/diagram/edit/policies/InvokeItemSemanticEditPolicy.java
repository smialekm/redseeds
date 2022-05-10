package usecasediagram.diagram.edit.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;

import usecasediagram.diagram.providers.UseCaseDiagramElementTypes;

/**
 * @generated
 */
public class InvokeItemSemanticEditPolicy extends
		UseCaseDiagramBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	public InvokeItemSemanticEditPolicy() {
		super(UseCaseDiagramElementTypes.Invoke_4001);
	}

	/**
	 * @generated
	 */
	protected Command getDestroyElementCommand(DestroyElementRequest req) {
		return getGEFWrapper(new DestroyElementCommand(req));
	}

}
