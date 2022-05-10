package notiondiagram.diagram.part;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gmf.runtime.common.ui.services.action.contributionitem.ContributionItemService;
import org.eclipse.gmf.runtime.diagram.ui.actions.ActionIds;
import org.eclipse.gmf.runtime.diagram.ui.providers.DiagramContextMenuProvider;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.ui.IWorkbenchPart;

/**
 * @generated
 */
public class DiagramEditorContextMenuProvider extends
		DiagramContextMenuProvider {

	/**
	 * @generated
	 */
	private IWorkbenchPart part;

	/**
	 * @generated
	 */
	private DeleteElementAction deleteAction;

	/**
	 * @generated NOT
	 */
	private DeleteFromDiagramElementAction deleteFromDiagramAction;

	/**
	 * @generated NOT
	 */
	private CreateConceptNotionAction createConceptNotionAction;
	/**
	 * @generated NOT
	 */
	private CreateScreenNotionAction createScreenNotionAction;
	/**
	 * @generated NOT
	 */
	private CreateMessageNotionAction createMessageNotionAction;
	/**
	 * @generated NOT
	 */
	private CreateConfirmationDialogNotionAction createConfirmationDialogNotionAction;
	/**
	 * @generated NOT
	 */
	private CreateTriggerNotionAction createTriggerNotionAction;
	/**
	 * @generated NOT
	 */
	private CreateAttributeNotionAction createAttributeNotionAction;
	/**
	 * @generated NOT
	 */
	private CreateOptionNotionAction createOptionNotionAction;
	/**
	 * @generated NOT
	 */
	private CreateListViewNotionAction createListViewNotionAction;
	/**
	 * @generated NOT
	 */
	private CreateSimpleViewNotionAction createSimpleViewNotionAction;
	/**
	 * @generated NOT
	 */
	private CreateTreeViewNotionAction createTreeViewNotionAction;

	/**
	 * @generated NOT
	 */
	public DiagramEditorContextMenuProvider(IWorkbenchPart part,
			EditPartViewer viewer) {
		super(part, viewer);
		this.part = part;
		deleteAction = new DeleteElementAction(part);
		deleteAction.init();
		deleteFromDiagramAction = new DeleteFromDiagramElementAction(part);
		deleteFromDiagramAction.init();
		getActionRegistry().registerAction(deleteFromDiagramAction);
		createConceptNotionAction = new CreateConceptNotionAction(part);
		createConceptNotionAction.init();
		getActionRegistry().registerAction(createConceptNotionAction);
		createScreenNotionAction = new CreateScreenNotionAction(part);
		createScreenNotionAction.init();
		getActionRegistry().registerAction(createScreenNotionAction);
		createMessageNotionAction = new CreateMessageNotionAction(part);
		createMessageNotionAction.init();
		getActionRegistry().registerAction(createMessageNotionAction);
		createConfirmationDialogNotionAction = new CreateConfirmationDialogNotionAction(
				part);
		createConfirmationDialogNotionAction.init();
		getActionRegistry()
				.registerAction(createConfirmationDialogNotionAction);
		createTriggerNotionAction = new CreateTriggerNotionAction(part);
		createTriggerNotionAction.init();
		getActionRegistry().registerAction(createTriggerNotionAction);
		createAttributeNotionAction = new CreateAttributeNotionAction(part);
		createAttributeNotionAction.init();
		getActionRegistry().registerAction(createAttributeNotionAction);
		createOptionNotionAction = new CreateOptionNotionAction(part);
		createOptionNotionAction.init();
		getActionRegistry().registerAction(createOptionNotionAction);
		createListViewNotionAction = new CreateListViewNotionAction(part);
		createListViewNotionAction.init();
		getActionRegistry().registerAction(createListViewNotionAction);
		createSimpleViewNotionAction = new CreateSimpleViewNotionAction(part);
		createSimpleViewNotionAction.init();
		getActionRegistry().registerAction(createSimpleViewNotionAction);
		createTreeViewNotionAction = new CreateTreeViewNotionAction(part);
		createTreeViewNotionAction.init();
		getActionRegistry().registerAction(createTreeViewNotionAction);
	}

	/**
	 * @generated NOT
	 */
	public void dispose() {
		if (deleteAction != null) {
			deleteAction.dispose();
			deleteAction = null;
		}
		if (deleteFromDiagramAction != null) {
			deleteFromDiagramAction.dispose();
			deleteFromDiagramAction = null;
		}
		super.dispose();
	}

	/**
	 * @generated NOT
	 */
	public void buildContextMenu(final IMenuManager menu) {
		getViewer().flush();
		try {
			TransactionUtil.getEditingDomain(
					(EObject) getViewer().getContents().getModel())
					.runExclusive(new Runnable() {

						public void run() {
							ContributionItemService
									.getInstance()
									.contributeToPopupMenu(
											DiagramEditorContextMenuProvider.this,
											part);
							menu.remove(ActionIds.ACTION_DELETE_FROM_DIAGRAM);
							menu.appendToGroup("editGroup",
									deleteFromDiagramAction);
							menu.remove(ActionIds.ACTION_DELETE_FROM_MODEL);
							menu.appendToGroup("editGroup", deleteAction);
							IMenuManager diagramAddMenu = (IMenuManager) menu
									.find("diagramAddMenu");
							if (null!=diagramAddMenu){
								diagramAddMenu.appendToGroup(
										"addDiagramNotionGroup",
										createConceptNotionAction);
								diagramAddMenu.appendToGroup(
										"addDiagramNotionGroup",
										createScreenNotionAction);
								diagramAddMenu.appendToGroup(
										"addDiagramNotionGroup",
										createMessageNotionAction);
								diagramAddMenu.appendToGroup(
										"addDiagramNotionGroup",
										createConfirmationDialogNotionAction);
								diagramAddMenu.appendToGroup(
										"addDiagramNotionGroup",
										createTriggerNotionAction);
								diagramAddMenu.appendToGroup(
										"addDiagramNotionGroup",
										createAttributeNotionAction);
								diagramAddMenu.appendToGroup(
										"addDiagramNotionGroup",
										createOptionNotionAction);
								diagramAddMenu.appendToGroup(
										"addDiagramNotionGroup",
										createListViewNotionAction);
								diagramAddMenu.appendToGroup(
										"addDiagramNotionGroup",
										createSimpleViewNotionAction);
								diagramAddMenu.appendToGroup(
										"addDiagramNotionGroup",
										createTreeViewNotionAction);
							}
						}
					});
		} catch (Exception e) {
			NotionDiagramDiagramEditorPlugin.getInstance().logError(
					"Error building context menu", e);
		}
	}
}
