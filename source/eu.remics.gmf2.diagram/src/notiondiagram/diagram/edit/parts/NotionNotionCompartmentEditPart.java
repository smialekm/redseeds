package notiondiagram.diagram.edit.parts;

import notiondiagram.Notion;
import notiondiagram.diagram.edit.policies.NotionNotionCompartmentCanonicalEditPolicy;
import notiondiagram.diagram.edit.policies.NotionNotionCompartmentItemSemanticEditPolicy;
import notiondiagram.diagram.part.Messages;

import org.eclipse.draw2d.IFigure;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.SelectionRequest;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ListCompartmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.CreationEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.DragDropEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.ResizableCompartmentEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.figures.ResizableCompartmentFigure;
import org.eclipse.gmf.runtime.draw2d.ui.figures.ConstrainedToolbarLayout;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.gmf.runtime.notation.impl.BasicCompartmentImpl;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import eu.redseeds.common.SCProjectHelper;
import eu.redseeds.sc.current.repository.SCProjectContainer;
import eu.redseeds.sc.editor.rsl.editors.domain.NotionEditor;
import eu.redseeds.sc.editor.rsl.editors.domain.NotionEditorInput;
import eu.redseeds.scl.model.rsl.rsldomainelements.notions.NotionDTO;
import eu.remics.recovery.model.RecoveryModelHelper;

/**
 * @generated
 */
public class NotionNotionCompartmentEditPart extends ListCompartmentEditPart {

	/**
	 * @generated
	 */
	public static final int VISUAL_ID = 7001;

	/**
	 * @generated
	 */
	public NotionNotionCompartmentEditPart(View view) {
		super(view);
	}

	/**
	 * @generated
	 */
	protected boolean hasModelChildrenChanged(Notification evt) {
		return false;
	}

	/**
	 * @generated
	 */
	public String getCompartmentName() {
		return Messages.NotionNotionCompartmentEditPart_title;
	}

	/**
	 * @generated
	 */
	public IFigure createFigure() {
		ResizableCompartmentFigure result = (ResizableCompartmentFigure) super
				.createFigure();
		result.setTitleVisibility(false);
		return result;
	}

	/**
	 * @generated
	 */
	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
		installEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE,
				new ResizableCompartmentEditPolicy());
		installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE,
				new NotionNotionCompartmentItemSemanticEditPolicy());
		installEditPolicy(EditPolicyRoles.CREATION_ROLE,
				new CreationEditPolicy());
		installEditPolicy(EditPolicyRoles.DRAG_DROP_ROLE,
				new DragDropEditPolicy());
		installEditPolicy(EditPolicyRoles.CANONICAL_ROLE,
				new NotionNotionCompartmentCanonicalEditPolicy());
	}

	/**
	 * @generated
	 */
	protected void setRatio(Double ratio) {
		if (getFigure().getParent().getLayoutManager() instanceof ConstrainedToolbarLayout) {
			super.setRatio(ratio);
		}
	}

	/**
	 * @generated NOT
	 */
	@Override
	public void performRequest(Request request) {
		if (RequestConstants.REQ_OPEN.equals(request.getType())) {
			if (request instanceof SelectionRequest) {
				EObject elem = ((BasicCompartmentImpl) this.getModel())
						.getElement();
				if (elem instanceof Notion) {
					Notion notion = (Notion) elem;
					RecoveryModelHelper rmh = RecoveryModelHelper.instance(elem
							.eResource());
					NotionDTO selectedElement = rmh.getNotionByVertexID(notion
							.getId());
					IWorkbenchPage activePage = PlatformUI.getWorkbench()
							.getActiveWorkbenchWindow().getActivePage();

					NotionEditorInput notionInput = new NotionEditorInput();
					notionInput.setNotionDTO((NotionDTO) selectedElement);
					NotionEditor notionEditor;
					try {
						notionEditor = (NotionEditor) activePage.openEditor(
								notionInput, NotionEditor.EDITOR_ID, false, 1);
						notionEditor.setNotion((NotionDTO) selectedElement);
						notionEditor.setFacade(SCProjectContainer
								.instance()
								.getSCProject(
										SCProjectHelper.getActiveProject())
								.getBusinessLayerFacade());
						notionEditor.setScProject(SCProjectContainer.instance()
								.getSCProject(
										SCProjectHelper.getActiveProject()));
					} catch (PartInitException e1) {
						e1.printStackTrace();
					}
				}
			}
		}
		if (!isEditModeEnabled()) {
			if (RequestConstants.REQ_OPEN.equals(request.getType())) {
			} else {
				return;
			}
		}

		if (RequestConstants.REQ_DIRECT_EDIT == request.getType()) {
			performDirectEditRequest(request);
		} else {
			EditPart targetEditPart = getTargetEditPart(request);
			if (targetEditPart != null) {
				Command command = targetEditPart.getCommand(request);
				if (command != null) {
					getDiagramEditDomain().getDiagramCommandStack().execute(
							command);
					return;
				}
			}
		}
	}

}
