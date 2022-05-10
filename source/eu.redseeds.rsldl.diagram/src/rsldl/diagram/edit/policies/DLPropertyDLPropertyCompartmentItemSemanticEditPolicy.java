package rsldl.diagram.edit.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;

import rsldl.diagram.edit.commands.DLIdentityCondition2CreateCommand;
import rsldl.diagram.edit.commands.DLInheritanceCondition2CreateCommand;
import rsldl.diagram.edit.commands.DLValidityCondition2CreateCommand;
import rsldl.diagram.providers.RsldlElementTypes;

/**
 * @generated
 */
public class DLPropertyDLPropertyCompartmentItemSemanticEditPolicy extends
		RsldlBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	public DLPropertyDLPropertyCompartmentItemSemanticEditPolicy() {
		super(RsldlElementTypes.DLProperty_2002);
	}

	/**
	 * @generated
	 */
	protected Command getCreateCommand(CreateElementRequest req) {
		if (RsldlElementTypes.DLIdentityCondition_3004 == req.getElementType()) {
			return getGEFWrapper(new DLIdentityCondition2CreateCommand(req));
		}
		if (RsldlElementTypes.DLInheritanceCondition_3005 == req
				.getElementType()) {
			return getGEFWrapper(new DLInheritanceCondition2CreateCommand(req));
		}
		if (RsldlElementTypes.DLValidityCondition_3006 == req.getElementType()) {
			return getGEFWrapper(new DLValidityCondition2CreateCommand(req));
		}
		return super.getCreateCommand(req);
	}

}
