package notiondiagram.diagram.edit.policies;

import notiondiagram.diagram.providers.NotionDiagramElementTypes;

import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;

/**
 * @generated
 */
public class IndirectRelationItemSemanticEditPolicy extends
		NotionDiagramBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	public IndirectRelationItemSemanticEditPolicy() {
		super(NotionDiagramElementTypes.IndirectRelation_4004);
	}

	/**
	 * @generated
	 */
	protected Command getDestroyElementCommand(DestroyElementRequest req) {
		return getGEFWrapper(new DestroyElementCommand(req));
	}

}
