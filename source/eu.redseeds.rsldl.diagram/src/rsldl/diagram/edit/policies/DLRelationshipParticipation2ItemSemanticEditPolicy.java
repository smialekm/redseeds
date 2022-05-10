package rsldl.diagram.edit.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;

import rsldl.diagram.providers.RsldlElementTypes;

/**
 * @generated
 */
public class DLRelationshipParticipation2ItemSemanticEditPolicy extends
		RsldlBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	public DLRelationshipParticipation2ItemSemanticEditPolicy() {
		super(RsldlElementTypes.DLRelationshipParticipation_4002);
	}

	/**
	 * @generated
	 */
	protected Command getDestroyElementCommand(DestroyElementRequest req) {
		return getGEFWrapper(new DestroyElementCommand(req));
	}

}
