package usecasediagram.diagram.part;

import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gmf.runtime.diagram.ui.actions.AbstractDeleteFromAction;
import org.eclipse.gmf.runtime.diagram.ui.actions.ActionIds;
import org.eclipse.gmf.runtime.diagram.ui.commands.CommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.l10n.DiagramUIMessages;
import org.eclipse.gmf.runtime.emf.commands.core.command.CompositeTransactionalCommand;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

import eu.remics.common.RecoveryManagerHelper;
import eu.remics.recovery.model.RecoveryModelHelper;

import usecasediagram.Actor;
import usecasediagram.Association;
import usecasediagram.Invoke;
import usecasediagram.UseCase;
import usecasediagram.UseCaseDiagram;
import usecasediagram.diagram.edit.parts.ActorEditPart;
import usecasediagram.diagram.edit.parts.AssociationEditPart;
import usecasediagram.diagram.edit.parts.InvokeEditPart;
import usecasediagram.diagram.edit.parts.UseCaseDiagramEditPart;
import usecasediagram.diagram.edit.parts.UseCaseEditPart;

/**
 * @generated
 */
public class DeleteFromDiagramElementAction extends AbstractDeleteFromAction {

	/**
	 * @generated
	 */
	public DeleteFromDiagramElementAction(IWorkbenchPart part) {
		super(part);
	}

	/**
	 * @generated
	 */
	public DeleteFromDiagramElementAction(IWorkbenchPage workbenchPage) {
		super(workbenchPage);
	}

	/**
	 * @generated
	 */
	public void init() {
		super.init();
		setId(ActionIds.ACTION_DELETE_FROM_DIAGRAM);
		setText(DiagramUIMessages.DiagramEditor_Delete_from_Diagram);
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
		return DiagramUIMessages.DiagramEditor_Delete_from_Diagram;
	}
	
	protected void doRun(IProgressMonitor progressMonitor) {
		RecoveryManagerHelper.setDiagramDeleteActionOccuredState(true);
		execute(getCommand(), progressMonitor);
		RecoveryManagerHelper.setDiagramDeleteActionOccuredState(false);
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
				getEditingDomain(), getCommandLabel());
		while (editParts.hasNext()) {
			final EditPart editPart = (EditPart) editParts.next();
			if (editPart instanceof UseCaseDiagramEditPart)
				return UnexecutableCommand.INSTANCE;
			if (editPart instanceof InvokeEditPart){
				Invoke elem = (Invoke) ((InvokeEditPart) editPart).resolveSemanticElement();
				boolean find = false;
				for (Object o : operationSet) if (o instanceof UseCaseEditPart && (((UseCaseEditPart) o).resolveSemanticElement().equals(elem.getSource()) || ((UseCaseEditPart) o).resolveSemanticElement().equals(elem.getTarget()))){
					find = true;
					break;
				}
				if (!find)
					return UnexecutableCommand.INSTANCE;
			}
			if(editPart instanceof AssociationEditPart){
				Association elem = (Association) ((AssociationEditPart) editPart).resolveSemanticElement();
				boolean find = false;
				for (Object o : operationSet) if (o instanceof UseCaseEditPart && ((UseCaseEditPart) o).resolveSemanticElement().equals(elem.getTarget()) || o instanceof ActorEditPart && ((ActorEditPart) o).resolveSemanticElement().equals(elem.getSource())){
					find = true;
					break;
				}
				if (!find)
					return UnexecutableCommand.INSTANCE;
			}
			Command curCommand = editPart.getCommand(request);
			if (curCommand != null) {
				CompositeTransactionalCommand temp = new CompositeTransactionalCommand(getEditingDomain(),
						null!=curCommand.getLabel()?curCommand.getLabel():""){
					
					EObject obj = (editPart instanceof ShapeNodeEditPart)?((ShapeNodeEditPart) editPart).resolveSemanticElement():((ConnectionNodeEditPart) editPart).resolveSemanticElement();
					UseCaseDiagram par = null!=obj?(UseCaseDiagram) obj.eContainer():null;
					
					@Override
					public boolean canUndo() {
						if (obj instanceof UseCase){
							RecoveryModelHelper rmh = RecoveryModelHelper.instance(par.eResource());
							if (null==rmh.getBussinessLayerFacade().getUseCaseByVertexID((((UseCase) obj).getId()))
								|| obj instanceof Association &&  null==rmh.getDependencyByVertexID((((Association) obj).getId()))
								|| obj instanceof Actor &&  null==rmh.getActorByVertexID((((Actor) obj).getId()))
								|| obj instanceof Invoke &&  null==rmh.getInvocationRelationshipByVertexID((((Invoke) obj).getId())))
								return false;
						}
						return super.canUndo();
					}
					
				};
				temp.compose(new CommandProxy(curCommand));
				command.compose(temp);
			}
		}
		if (command.isEmpty() || command.size() != operationSet.size())
			return UnexecutableCommand.INSTANCE;
		return new ICommandProxy(command);
	}
}
