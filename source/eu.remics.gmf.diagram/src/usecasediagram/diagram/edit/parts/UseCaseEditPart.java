package usecasediagram.diagram.edit.parts;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.draw2d.Ellipse;
import org.eclipse.draw2d.GridData;
import org.eclipse.draw2d.GridLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Shape;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.LayoutEditPolicy;
import org.eclipse.gef.editpolicies.NonResizableEditPolicy;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gef.requests.SelectionRequest;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.draw2d.ui.figures.ConstrainedToolbarLayout;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.gmf.runtime.notation.impl.NodeImpl;
import org.eclipse.swt.graphics.Color;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import eu.redseeds.common.SCProjectHelper;
import eu.redseeds.sc.current.repository.SCProjectContainer;
import eu.redseeds.sc.editor.rsl.editors.UseCaseEditor;
import eu.redseeds.scl.model.rsl.rslrequirements.requirementsspecifications.UseCaseDTO;
import eu.remics.recovery.model.RecoveryModelHelper;

import usecasediagram.UseCase;
import usecasediagram.diagram.edit.policies.UseCaseItemSemanticEditPolicy;
import usecasediagram.diagram.part.UseCaseDiagramVisualIDRegistry;
import usecasediagram.diagram.providers.UseCaseDiagramElementTypes;

/**
 * @param <UseCaseEditorInput>
 * @generated
 */
public class UseCaseEditPart extends ShapeNodeEditPart {

	/**
	 * @generated
	 */
	public static final int VISUAL_ID = 2002;

	/**
	 * @generated
	 */
	protected IFigure contentPane;

	/**
	 * @generated
	 */
	protected IFigure primaryShape;

	/**
	 * @generated
	 */
	public UseCaseEditPart(View view) {
		super(view);
	}

	/**
	 * @generated
	 */
	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
		installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE,
				new UseCaseItemSemanticEditPolicy());
		installEditPolicy(EditPolicy.LAYOUT_ROLE, createLayoutEditPolicy());
		// XXX need an SCR to runtime to have another abstract superclass that would let children add reasonable editpolicies
		// removeEditPolicy(org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles.CONNECTION_HANDLES_ROLE);
	}

	/**
	 * @generated
	 */
	protected LayoutEditPolicy createLayoutEditPolicy() {
		org.eclipse.gmf.runtime.diagram.ui.editpolicies.LayoutEditPolicy lep = new org.eclipse.gmf.runtime.diagram.ui.editpolicies.LayoutEditPolicy() {

			protected EditPolicy createChildEditPolicy(EditPart child) {
				EditPolicy result = child
						.getEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE);
				if (result == null) {
					result = new NonResizableEditPolicy();
				}
				return result;
			}

			protected Command getMoveChildrenCommand(Request request) {
				return null;
			}

			protected Command getCreateCommand(CreateRequest request) {
				return null;
			}
		};
		return lep;
	}

	/**
	 * @generated
	 */
	protected IFigure createNodeShape() {
		return primaryShape = new UseCaseFigure();
	}

	/**
	 * @generated
	 */
	public UseCaseFigure getPrimaryShape() {
		return (UseCaseFigure) primaryShape;
	}

	/**
	 * @generated
	 */
	protected boolean addFixedChild(EditPart childEditPart) {
		if (childEditPart instanceof UseCaseNameEditPart) {
			((UseCaseNameEditPart) childEditPart).setLabel(getPrimaryShape()
					.getFigureUseCaseNameFigure());
			return true;
		}
		return false;
	}

	/**
	 * @generated
	 */
	protected boolean removeFixedChild(EditPart childEditPart) {
		if (childEditPart instanceof UseCaseNameEditPart) {
			return true;
		}
		return false;
	}

	/**
	 * @generated
	 */
	protected void addChildVisual(EditPart childEditPart, int index) {
		if (addFixedChild(childEditPart)) {
			return;
		}
		super.addChildVisual(childEditPart, -1);
	}

	/**
	 * @generated
	 */
	protected void removeChildVisual(EditPart childEditPart) {
		if (removeFixedChild(childEditPart)) {
			return;
		}
		super.removeChildVisual(childEditPart);
	}

	/**
	 * @generated
	 */
	protected IFigure getContentPaneFor(IGraphicalEditPart editPart) {
		return getContentPane();
	}

	/**
	 * @generated
	 */
	protected NodeFigure createNodePlate() {
		DefaultSizeNodeFigure result = new DefaultSizeNodeFigure(40, 40);
		return result;
	}

	/**
	 * Creates figure for this edit part.
	 * 
	 * Body of this method does not depend on settings in generation model
	 * so you may safely remove <i>generated</i> tag and modify it.
	 * 
	 * @generated
	 */
	protected NodeFigure createNodeFigure() {
		NodeFigure figure = createNodePlate();
		figure.setLayoutManager(new StackLayout());
		IFigure shape = createNodeShape();
		figure.add(shape);
		contentPane = setupContentPane(shape);
		return figure;
	}

	/**
	 * Default implementation treats passed figure as content pane.
	 * Respects layout one may have set for generated figure.
	 * @param nodeShape instance of generated figure class
	 * @generated
	 */
	protected IFigure setupContentPane(IFigure nodeShape) {
		if (nodeShape.getLayoutManager() == null) {
			ConstrainedToolbarLayout layout = new ConstrainedToolbarLayout();
			layout.setSpacing(5);
			nodeShape.setLayoutManager(layout);
		}
		return nodeShape; // use nodeShape itself as contentPane
	}

	/**
	 * @generated
	 */
	public IFigure getContentPane() {
		if (contentPane != null) {
			return contentPane;
		}
		return super.getContentPane();
	}

	/**
	 * @generated
	 */
	protected void setForegroundColor(Color color) {
		if (primaryShape != null) {
			primaryShape.setForegroundColor(color);
		}
	}

	/**
	 * @generated
	 */
	protected void setBackgroundColor(Color color) {
		if (primaryShape != null) {
			primaryShape.setBackgroundColor(color);
		}
	}

	/**
	 * @generated
	 */
	protected void setLineWidth(int width) {
		if (primaryShape instanceof Shape) {
			((Shape) primaryShape).setLineWidth(width);
		}
	}

	/**
	 * @generated
	 */
	protected void setLineType(int style) {
		if (primaryShape instanceof Shape) {
			((Shape) primaryShape).setLineStyle(style);
		}
	}

	/**
	 * @generated
	 */
	public EditPart getPrimaryChildEditPart() {
		return getChildBySemanticHint(UseCaseDiagramVisualIDRegistry
				.getType(UseCaseNameEditPart.VISUAL_ID));
	}

	/**
	 * @generated
	 */
	public List<IElementType> getMARelTypesOnSource() {
		ArrayList<IElementType> types = new ArrayList<IElementType>(1);
		types.add(UseCaseDiagramElementTypes.Invoke_4001);
		return types;
	}

	/**
	 * @generated
	 */
	public List<IElementType> getMARelTypesOnSourceAndTarget(
			IGraphicalEditPart targetEditPart) {
		LinkedList<IElementType> types = new LinkedList<IElementType>();
		if (targetEditPart instanceof usecasediagram.diagram.edit.parts.UseCaseEditPart) {
			types.add(UseCaseDiagramElementTypes.Invoke_4001);
		}
		return types;
	}

	/**
	 * @generated
	 */
	public List<IElementType> getMATypesForTarget(IElementType relationshipType) {
		LinkedList<IElementType> types = new LinkedList<IElementType>();
		if (relationshipType == UseCaseDiagramElementTypes.Invoke_4001) {
			types.add(UseCaseDiagramElementTypes.UseCase_2002);
		}
		return types;
	}

	/**
	 * @generated
	 */
	public List<IElementType> getMARelTypesOnTarget() {
		ArrayList<IElementType> types = new ArrayList<IElementType>(2);
		types.add(UseCaseDiagramElementTypes.Invoke_4001);
		types.add(UseCaseDiagramElementTypes.Association_4002);
		return types;
	}

	/**
	 * @generated
	 */
	public List<IElementType> getMATypesForSource(IElementType relationshipType) {
		LinkedList<IElementType> types = new LinkedList<IElementType>();
		if (relationshipType == UseCaseDiagramElementTypes.Invoke_4001) {
			types.add(UseCaseDiagramElementTypes.UseCase_2002);
		} else if (relationshipType == UseCaseDiagramElementTypes.Association_4002) {
			types.add(UseCaseDiagramElementTypes.Actor_2001);
		}
		return types;
	}

	/**
	 * @generated NOT
	 */
	@Override
	public void performRequest(Request request) {
		if (RequestConstants.REQ_OPEN.equals(request.getType())) {
			if (request instanceof SelectionRequest) {
				EObject elem = ((NodeImpl) this.getModel()).getElement();
				if (elem instanceof UseCase) {
					UseCase uc = (UseCase) elem;
					RecoveryModelHelper rmh = RecoveryModelHelper.instance(elem
							.eResource());
					UseCaseDTO selectedElement = rmh.getBussinessLayerFacade()
							.getUseCaseByVertexID(uc.getId());
					IWorkbenchPage activePage = PlatformUI.getWorkbench()
							.getActiveWorkbenchWindow().getActivePage();

					eu.redseeds.sc.editor.rsl.editors.UseCaseEditorInput useCaseInput = new eu.redseeds.sc.editor.rsl.editors.UseCaseEditorInput();
					useCaseInput.setUseCaseDTO((UseCaseDTO) selectedElement);
					UseCaseEditor useCaseEditor;
					try {
						useCaseEditor = (UseCaseEditor) activePage
								.openEditor(
										useCaseInput,
										"eu.redseeds.sc.editor.rsl.editors.UseCaseEditor",
										false, 1);
						useCaseEditor.setFacade(SCProjectContainer
								.instance()
								.getSCProject(
										SCProjectHelper.getActiveProject())
								.getBusinessLayerFacade());
						useCaseEditor.setScProject(SCProjectContainer
								.instance().getSCProject(
										SCProjectHelper.getActiveProject()));
						useCaseEditor.setUseCase((UseCaseDTO) selectedElement);
					} catch (PartInitException e) {
						e.printStackTrace();
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

	/**
	 * @generated
	 */
	public class UseCaseFigure extends Ellipse {

		/**
		 * @generated
		 */
		private WrappingLabel fFigureUseCaseNameFigure;

		/**
		 * @generated
		 */
		public UseCaseFigure() {

			GridLayout layoutThis = new GridLayout();
			layoutThis.numColumns = 1;
			layoutThis.makeColumnsEqualWidth = true;
			this.setLayoutManager(layoutThis);

			this.setForegroundColor(THIS_FORE);
			this.setBackgroundColor(THIS_BACK);
			this.setMinimumSize(new Dimension(getMapMode().DPtoLP(160),
					getMapMode().DPtoLP(80)));
			createContents();
		}

		/**
		 * @generated
		 */
		private void createContents() {

			fFigureUseCaseNameFigure = new WrappingLabel();
			fFigureUseCaseNameFigure.setText("");

			GridData constraintFFigureUseCaseNameFigure = new GridData();
			constraintFFigureUseCaseNameFigure.verticalAlignment = GridData.CENTER;
			constraintFFigureUseCaseNameFigure.horizontalAlignment = GridData.CENTER;
			constraintFFigureUseCaseNameFigure.horizontalIndent = 0;
			constraintFFigureUseCaseNameFigure.horizontalSpan = 1;
			constraintFFigureUseCaseNameFigure.verticalSpan = 1;
			constraintFFigureUseCaseNameFigure.grabExcessHorizontalSpace = true;
			constraintFFigureUseCaseNameFigure.grabExcessVerticalSpace = true;
			this.add(fFigureUseCaseNameFigure,
					constraintFFigureUseCaseNameFigure);

		}

		/**
		 * @generated
		 */
		public WrappingLabel getFigureUseCaseNameFigure() {
			return fFigureUseCaseNameFigure;
		}

	}

	/**
	 * @generated
	 */
	static final Color THIS_FORE = new Color(null, 0, 0, 0);

	/**
	 * @generated
	 */
	static final Color THIS_BACK = new Color(null, 215, 235, 250);

}
