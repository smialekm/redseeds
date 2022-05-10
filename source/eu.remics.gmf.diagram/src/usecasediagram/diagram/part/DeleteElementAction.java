package usecasediagram.diagram.part;

import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gmf.runtime.diagram.ui.actions.AbstractDeleteFromAction;
import org.eclipse.gmf.runtime.diagram.ui.actions.ActionIds;
import org.eclipse.gmf.runtime.diagram.ui.commands.CommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.l10n.DiagramUIMessages;
import org.eclipse.gmf.runtime.emf.commands.core.command.CompositeTransactionalCommand;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

import eu.remics.common.RecoveryManagerHelper;
import usecasediagram.Invoke;
import usecasediagram.diagram.edit.parts.InvokeEditPart;
import usecasediagram.diagram.edit.parts.UseCaseDiagramEditPart;
import usecasediagram.diagram.edit.parts.UseCaseEditPart;

/**
 * @generated
 */
public class DeleteElementAction extends AbstractDeleteFromAction {

	/**
	 * @generated
	 */
	public DeleteElementAction(IWorkbenchPart part) {
		super(part);
	}

	/**
	 * @generated
	 */
	public DeleteElementAction(IWorkbenchPage workbenchPage) {
		super(workbenchPage);
	}

	/**
	 * @generated
	 */
	public void init() {
		super.init();
		setId(ActionIds.ACTION_DELETE_FROM_MODEL);
		setText(DiagramUIMessages.DiagramEditor_Delete_from_Model);
		setToolTipText(DiagramUIMessages.DiagramEditor_Delete_from_ModelToolTip);
		ISharedImages workbenchImages = PlatformUI.getWorkbench()
				.getSharedImages();
		setHoverImageDescriptor(workbenchImages
				.getImageDescriptor(ISharedImages.IMG_TOOL_DELETE));
		setImageDescriptor(workbenchImages
				.getImageDescriptor(ISharedImages.IMG_TOOL_DELETE));
		setDisabledImageDescriptor(workbenchImages
				.getImageDescriptor(ISharedImages.IMG_TOOL_DELETE_DISABLED));
	}

	/**
	 * @generated
	 */
	protected String getCommandLabel() {
		return DiagramUIMessages.DiagramEditor_Delete_from_Model;
	}

	protected void doRun(IProgressMonitor progressMonitor) {
		RecoveryManagerHelper.setModelDeleteActionOccuredState(true);
		execute(getCommand(), progressMonitor);
		RecoveryManagerHelper.setModelDeleteActionOccuredState(false);
	}

	/**
	 * @generated NOT
	 */
	protected Command getCommand(Request request) {
		List operationSet = getOperationSet();
		if (operationSet.isEmpty()) {
			return UnexecutableCommand.INSTANCE;
		}
		Iterator editParts = operationSet.iterator();
		CompositeTransactionalCommand command = new CompositeTransactionalCommand(
				getEditingDomain(), getCommandLabel()) {

			@Override
			public boolean canUndo() {
				return false;
			}

		};
		while (editParts.hasNext()) {
			EditPart editPart = (EditPart) editParts.next();
			if (editPart instanceof UseCaseDiagramEditPart)
				return UnexecutableCommand.INSTANCE;
			if (editPart instanceof InvokeEditPart) {
				Invoke elem = (Invoke) ((InvokeEditPart) editPart)
						.resolveSemanticElement();
				boolean find = false;
				for (Object o : operationSet)
					if (o instanceof UseCaseEditPart
							&& (((UseCaseEditPart) o).resolveSemanticElement()
									.equals(elem.getSource()) || ((UseCaseEditPart) o)
									.resolveSemanticElement().equals(
											elem.getTarget()))) {
						find = true;
						break;
					}
				if (!find)
					return UnexecutableCommand.INSTANCE;
			}
			Command curCommand = editPart.getCommand(request);
			if (curCommand != null) {
				command.compose(new CommandProxy(curCommand));
			}
		}
		if (command.isEmpty() || command.size() != operationSet.size())
			return UnexecutableCommand.INSTANCE;
		return new ICommandProxy(command);
	}
}
