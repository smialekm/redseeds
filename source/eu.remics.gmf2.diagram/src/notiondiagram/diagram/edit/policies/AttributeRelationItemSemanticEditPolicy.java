package notiondiagram.diagram.edit.policies;

import notiondiagram.diagram.providers.NotionDiagramElementTypes;

import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;

/**
 * @generated
 */
public class AttributeRelationItemSemanticEditPolicy extends
		NotionDiagramBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	public AttributeRelationItemSemanticEditPolicy() {
		super(NotionDiagramElementTypes.AttributeRelation_4002);
	}

	/**
	 * @generated
	 */
	protected Command getDestroyElementCommand(DestroyElementRequest req) {
		return getGEFWrapper(new DestroyElementCommand(req));
	}

}
