package notiondiagram.diagram.part;

import java.util.Iterator;
import java.util.List;

import notiondiagram.AbstractRelation;
import notiondiagram.AttributeRelation;
import notiondiagram.Generalization;
import notiondiagram.Notion;
import notiondiagram.NotionDiagram;
import notiondiagram.Phrase;
import notiondiagram.Relation;
import notiondiagram.diagram.edit.parts.AttributeRelationEditPart;
import notiondiagram.diagram.edit.parts.DirectedRelationEditPart;
import notiondiagram.diagram.edit.parts.GeneralizationEditPart;
import notiondiagram.diagram.edit.parts.IndirectRelationEditPart;
import notiondiagram.diagram.edit.parts.NotionDiagramEditPart;
import notiondiagram.diagram.edit.parts.NotionEditPart;
import notiondiagram.diagram.edit.parts.PhraseEditPart;

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
			if (editPart instanceof NotionDiagramEditPart)
				return UnexecutableCommand.INSTANCE;
			if (editPart instanceof PhraseEditPart){
				Phrase elem = (Phrase) ((PhraseEditPart) editPart).resolveSemanticElement();
				boolean find = false;
				for (Object o : operationSet) if (o instanceof NotionEditPart && ((Notion)((NotionEditPart) o).resolveSemanticElement()).getPhrases().contains(elem)){
					find = true;
					break;
				}
				if (!find)
					return UnexecutableCommand.INSTANCE;
			}
			if (editPart instanceof AttributeRelationEditPart || editPart instanceof DirectedRelationEditPart || editPart instanceof IndirectRelationEditPart || editPart instanceof GeneralizationEditPart){
				AbstractRelation elem = (AbstractRelation) ((ConnectionNodeEditPart) editPart).resolveSemanticElement();
				boolean find = false;
				for (Object o : operationSet) if (o instanceof NotionEditPart && (((NotionEditPart) o).resolveSemanticElement().equals(elem.getSource()) || ((NotionEditPart) o).resolveSemanticElement().equals(elem.getTarget()))){
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
					NotionDiagram par = (null!=obj && obj.eContainer() instanceof NotionDiagram)?(NotionDiagram) obj.eContainer():null;
					
					@Override
					public boolean canUndo() {
						if (obj instanceof Notion){
							RecoveryModelHelper rmh = RecoveryModelHelper.instance(par.eResource());
							if (null==rmh.getNotionByVertexID((((Notion) obj).getId()))
								|| obj instanceof Generalization &&  null==rmh.getSpecialisationByVertexID((((Generalization) obj).getId()))
								|| obj instanceof Relation &&  null==rmh.getDomainElementRelationshipByVertexID((((Relation) obj).getId()))
								|| obj instanceof AttributeRelation &&  (null==rmh.getNotionByVertexID((((AttributeRelation) obj).getSourceId())) || null==rmh.getNotionByVertexID((((AttributeRelation) obj).getTargetId())) || !rmh.getNotionByVertexID((((AttributeRelation) obj).getTargetId())).getNotionDTOAttributeList().contains(rmh.getNotionByVertexID((((AttributeRelation) obj).getSourceId())))))
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
