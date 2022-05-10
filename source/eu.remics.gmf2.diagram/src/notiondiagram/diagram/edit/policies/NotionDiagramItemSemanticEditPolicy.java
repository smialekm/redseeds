package notiondiagram.diagram.edit.policies;

import notiondiagram.diagram.edit.commands.NotionCreateCommand;
import notiondiagram.diagram.providers.NotionDiagramElementTypes;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DuplicateElementsRequest;

/**
 * @generated
 */
public class NotionDiagramItemSemanticEditPolicy extends
		NotionDiagramBaseItemSemanticEditPolicy {

	/**
	 * @generated
	 */
	public NotionDiagramItemSemanticEditPolicy() {
		super(NotionDiagramElementTypes.NotionDiagram_1000);
	}

	/**
	 * @generated NOT
	 */
	protected Command getCreateCommand(CreateElementRequest req) {
		if (NotionDiagramElementTypes.Notion_2001 == req.getElementType()) {
			return getGEFWrapper(new NotionCreateCommand(req));
		}
		if (NotionDiagramElementTypes.Notion_2002 == req.getElementType()) {
			return getGEFWrapper(new NotionCreateCommand(req));
		}
		if (NotionDiagramElementTypes.Notion_2003 == req.getElementType()) {
			return getGEFWrapper(new NotionCreateCommand(req));
		}
		if (NotionDiagramElementTypes.Notion_2004 == req.getElementType()) {
			return getGEFWrapper(new NotionCreateCommand(req));
		}
		if (NotionDiagramElementTypes.Notion_2005 == req.getElementType()) {
			return getGEFWrapper(new NotionCreateCommand(req));
		}
		if (NotionDiagramElementTypes.Notion_2006 == req.getElementType()) {
			return getGEFWrapper(new NotionCreateCommand(req));
		}
		if (NotionDiagramElementTypes.Notion_2007 == req.getElementType()) {
			return getGEFWrapper(new NotionCreateCommand(req));
		}
		if (NotionDiagramElementTypes.Notion_2008 == req.getElementType()) {
			return getGEFWrapper(new NotionCreateCommand(req));
		}
		if (NotionDiagramElementTypes.Notion_2009 == req.getElementType()) {
			return getGEFWrapper(new NotionCreateCommand(req));
		}
		if (NotionDiagramElementTypes.Notion_2010 == req.getElementType()) {
			return getGEFWrapper(new NotionCreateCommand(req));
		}
		return super.getCreateCommand(req);
	}

	/**
	 * @generated NOT
	 */
	protected Command getDuplicateCommand(DuplicateElementsRequest req) {
		return UnexecutableCommand.INSTANCE;
	}

}
