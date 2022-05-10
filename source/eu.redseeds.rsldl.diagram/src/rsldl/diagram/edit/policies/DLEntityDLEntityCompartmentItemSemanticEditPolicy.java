package rsldl.diagram.edit.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;

import rsldl.diagram.edit.commands.DLIdentityConditionCreateCommand;
import rsldl.diagram.edit.commands.DLInheritanceConditionCreateCommand;
import rsldl.diagram.edit.commands.DLValidityConditionCreateCommand;
import rsldl.diagram.providers.RsldlElementTypes;

/**
 * @generated
 */
public class DLEntityDLEntityCompartmentItemSemanticEditPolicy extends
		RsldlBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	public DLEntityDLEntityCompartmentItemSemanticEditPolicy() {
		super(RsldlElementTypes.DLEntity_2001);
	}

	/**
	 * @generated
	 */
	protected Command getCreateCommand(CreateElementRequest req) {
		if (RsldlElementTypes.DLIdentityCondition_3001 == req.getElementType()) {
			return getGEFWrapper(new DLIdentityConditionCreateCommand(req));
		}
		if (RsldlElementTypes.DLInheritanceCondition_3002 == req
				.getElementType()) {
			return getGEFWrapper(new DLInheritanceConditionCreateCommand(req));
		}
		if (RsldlElementTypes.DLValidityCondition_3003 == req.getElementType()) {
			return getGEFWrapper(new DLValidityConditionCreateCommand(req));
		}
		return super.getCreateCommand(req);
	}

}
