package rsldl.diagram.edit.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;

import rsldl.diagram.providers.RsldlElementTypes;

/**
 * @generated
 */
public class DLAttributeLinkItemSemanticEditPolicy extends
		RsldlBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	public DLAttributeLinkItemSemanticEditPolicy() {
		super(RsldlElementTypes.DLAttributeLink_4004);
	}

	/**
	 * @generated
	 */
	protected Command getDestroyElementCommand(DestroyElementRequest req) {
		return getGEFWrapper(new DestroyElementCommand(req));
	}

}
