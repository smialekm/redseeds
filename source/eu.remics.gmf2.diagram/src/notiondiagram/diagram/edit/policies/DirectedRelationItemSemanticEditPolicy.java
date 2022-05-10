package notiondiagram.diagram.edit.policies;

import notiondiagram.diagram.providers.NotionDiagramElementTypes;

import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;

/**
 * @generated
 */
public class DirectedRelationItemSemanticEditPolicy extends
		NotionDiagramBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	public DirectedRelationItemSemanticEditPolicy() {
		super(NotionDiagramElementTypes.DirectedRelation_4003);
	}

	/**
	 * @generated
	 */
	protected Command getDestroyElementCommand(DestroyElementRequest req) {
		return getGEFWrapper(new DestroyElementCommand(req));
	}

}
