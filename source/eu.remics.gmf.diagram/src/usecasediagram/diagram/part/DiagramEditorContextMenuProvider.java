package usecasediagram.diagram.part;

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
	private CreateUseCaseAction createUseCaseAction;

	/**
	 * @generated NOT
	 */
	private CreateActorAction createActorAction;

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
		createUseCaseAction = new CreateUseCaseAction(part);
		createUseCaseAction.init();
		getActionRegistry().registerAction(createUseCaseAction);
		createActorAction = new CreateActorAction(part);
		createActorAction.init();
		getActionRegistry().registerAction(createActorAction);
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
										"addDiagramUseCaseGroup",
										createUseCaseAction);
								diagramAddMenu
										.appendToGroup("addDiagramUseCaseGroup",
												createActorAction);
							}
						}
					});
		} catch (Exception e) {
			UseCaseDiagramDiagramEditorPlugin.getInstance().logError(
					"Error building context menu", e);
		}
	}
}
