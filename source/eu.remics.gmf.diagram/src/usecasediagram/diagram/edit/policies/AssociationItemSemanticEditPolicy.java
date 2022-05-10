package usecasediagram.diagram.edit.policies;

import java.util.List;

import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;

import eu.redseeds.scl.rsl.rslrequirements.requirementsspecifications.RSLUseCase;
import eu.redseeds.scl.uml.classes.dependencies.Dependency;
import eu.redseeds.scl.uml.classes.kernel.NamedElement;
import eu.remics.common.RecoveryManagerHelper;
import eu.remics.recovery.model.RecoveryModelHelper;

import usecasediagram.Association;
import usecasediagram.diagram.providers.UseCaseDiagramElementTypes;

/**
 * @generated
 */
public class AssociationItemSemanticEditPolicy extends
		UseCaseDiagramBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	public AssociationItemSemanticEditPolicy() {
		super(UseCaseDiagramElementTypes.Association_4002);
	}

	/**
	 * @generated
	 */
	protected Command getDestroyElementCommand(DestroyElementRequest req) {
		return getGEFWrapper(new DestroyElementCommand(req));
	}

}
