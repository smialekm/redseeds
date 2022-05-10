package notiondiagram.diagram.edit.policies;

import notiondiagram.diagram.providers.NotionDiagramElementTypes;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.IEditCommandRequest;

/**
 * @generated
 */
public class NotionNotionCompartmentItemSemanticEditPolicy extends
		NotionDiagramBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	public NotionNotionCompartmentItemSemanticEditPolicy() {
		super(NotionDiagramElementTypes.Notion_2001);
	}

	/**
	 * @generated NOT
	 */
	protected Command getCreateCommand(CreateElementRequest req) {
		if (NotionDiagramElementTypes.Phrase_3001 == req.getElementType()) {
			return UnexecutableCommand.INSTANCE;
		}
		return super.getCreateCommand(req);
	}

	protected Command getSemanticCommand(IEditCommandRequest req) {
		if (req instanceof DestroyRequest) {
			return null;
		}
		return super.getSemanticCommand(req);
	}

}
