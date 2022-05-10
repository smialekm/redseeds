package usecasediagram.diagram.edit.parts;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.draw2d.Ellipse;
import org.eclipse.draw2d.FlowLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PolylineShape;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.Shape;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.LayoutEditPolicy;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gef.requests.SelectionRequest;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.FlowLayoutEditPolicy;
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
import eu.redseeds.sc.editor.rsl.editors.domain.ActorEditor;
import eu.redseeds.sc.editor.rsl.editors.domain.ActorEditorInput;
import eu.redseeds.scl.model.rsl.rsldomainelements.actors.ActorDTO;
import eu.remics.recovery.model.RecoveryModelHelper;

import usecasediagram.Actor;
import usecasediagram.diagram.edit.policies.ActorItemSemanticEditPolicy;
import usecasediagram.diagram.part.UseCaseDiagramVisualIDRegistry;
import usecasediagram.diagram.providers.UseCaseDiagramElementTypes;

/**
 * @generated
 */
public class ActorEditPart extends ShapeNodeEditPart {

	/**
	 * @generated
	 */
	public static final int VISUAL_ID = 2001;

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
	public ActorEditPart(View view) {
		super(view);
	}

	/**
	 * @generated
	 */
	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
		installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE,
				new ActorItemSemanticEditPolicy());
		installEditPolicy(EditPolicy.LAYOUT_ROLE, createLayoutEditPolicy());
		// XXX need an SCR to runtime to have another abstract superclass that would let children add reasonable editpolicies
		// removeEditPolicy(org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles.CONNECTION_HANDLES_ROLE);
	}

	/**
	 * @generated
	 */
	protected LayoutEditPolicy createLayoutEditPolicy() {

		FlowLayoutEditPolicy lep = new FlowLayoutEditPolicy() {

			protected Command createAddCommand(EditPart child, EditPart after) {
				return null;
			}

			protected Command createMoveChildCommand(EditPart child,
					EditPart after) {
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
		return primaryShape = new ActorFigure();
	}

	/**
	 * @generated
	 */
	public ActorFigure getPrimaryShape() {
		return (ActorFigure) primaryShape;
	}

	/**
	 * @generated
	 */
	protected boolean addFixedChild(EditPart childEditPart) {
		if (childEditPart instanceof ActorNameEditPart) {
			((ActorNameEditPart) childEditPart).setLabel(getPrimaryShape()
					.getFigureActorNameFigure());
			return true;
		}
		return false;
	}

	/**
	 * @generated
	 */
	protected boolean removeFixedChild(EditPart childEditPart) {
		if (childEditPart instanceof ActorNameEditPart) {
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
				.getType(ActorNameEditPart.VISUAL_ID));
	}

	/**
	 * @generated
	 */
	public List<IElementType> getMARelTypesOnSource() {
		ArrayList<IElementType> types = new ArrayList<IElementType>(1);
		types.add(UseCaseDiagramElementTypes.Association_4002);
		return types;
	}

	/**
	 * @generated
	 */
	public List<IElementType> getMARelTypesOnSourceAndTarget(
			IGraphicalEditPart targetEditPart) {
		LinkedList<IElementType> types = new LinkedList<IElementType>();
		if (targetEditPart instanceof UseCaseEditPart) {
			types.add(UseCaseDiagramElementTypes.Association_4002);
		}
		return types;
	}

	/**
	 * @generated
	 */
	public List<IElementType> getMATypesForTarget(IElementType relationshipType) {
		LinkedList<IElementType> types = new LinkedList<IElementType>();
		if (relationshipType == UseCaseDiagramElementTypes.Association_4002) {
			types.add(UseCaseDiagramElementTypes.UseCase_2002);
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
				if (elem instanceof Actor) {
					Actor actor = (Actor) elem;
					RecoveryModelHelper rmh = RecoveryModelHelper.instance(elem
							.eResource());
					ActorDTO selectedElement = rmh.getActorByVertexID(actor
							.getId());
					IWorkbenchPage activePage = PlatformUI.getWorkbench()
							.getActiveWorkbenchWindow().getActivePage();

					ActorEditorInput actorInput = new ActorEditorInput();
					actorInput.setActorDTO((ActorDTO) selectedElement);
					ActorEditor actorEditor;
					try {
						actorEditor = (ActorEditor) activePage.openEditor(
								actorInput, ActorEditor.EDITOR_ID, false, 1);
						actorEditor.setActor((ActorDTO) selectedElement);
						actorEditor.setFacade(SCProjectContainer
								.instance()
								.getSCProject(
										SCProjectHelper.getActiveProject())
								.getBusinessLayerFacade());
						actorEditor.setScProject(SCProjectContainer.instance()
								.getSCProject(
										SCProjectHelper.getActiveProject()));
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
	public class ActorFigure extends RectangleFigure {

		/**
		 * @generated
		 */
		private WrappingLabel fFigureActorNameFigure;

		/**
		 * @generated
		 */
		public ActorFigure() {

			FlowLayout layoutThis = new FlowLayout();
			layoutThis.setStretchMinorAxis(false);
			layoutThis.setMinorAlignment(FlowLayout.ALIGN_CENTER);

			layoutThis.setMajorAlignment(FlowLayout.ALIGN_CENTER);
			layoutThis.setMajorSpacing(5);
			layoutThis.setMinorSpacing(5);
			layoutThis.setHorizontal(false);

			this.setLayoutManager(layoutThis);

			this.setFill(false);
			this.setOutline(false);
			createContents();
		}

		/**
		 * @generated
		 */
		private void createContents() {

			RectangleFigure figure0 = new RectangleFigure();
			figure0.setFill(false);
			figure0.setOutline(false);

			this.add(figure0);
			figure0.setLayoutManager(new XYLayout());

			Ellipse head1 = new Ellipse();
			head1.setForegroundColor(HEAD1_FORE);
			head1.setBackgroundColor(HEAD1_BACK);
			head1.setPreferredSize(new Dimension(getMapMode().DPtoLP(40),
					getMapMode().DPtoLP(40)));

			figure0.add(head1, new Rectangle(getMapMode().DPtoLP(0),
					getMapMode().DPtoLP(0), getMapMode().DPtoLP(40),
					getMapMode().DPtoLP(40)));

			PolylineShape arms1 = new PolylineShape();
			arms1.addPoint(new Point(getMapMode().DPtoLP(0), getMapMode()
					.DPtoLP(0)));
			arms1.addPoint(new Point(getMapMode().DPtoLP(40), getMapMode()
					.DPtoLP(0)));
			arms1.setFill(false);
			arms1.setForegroundColor(ARMS1_FORE);

			figure0.add(arms1, new Rectangle(getMapMode().DPtoLP(0),
					getMapMode().DPtoLP(45), getMapMode().DPtoLP(40),
					getMapMode().DPtoLP(1)));

			PolylineShape body1 = new PolylineShape();
			body1.addPoint(new Point(getMapMode().DPtoLP(0), getMapMode()
					.DPtoLP(40)));
			body1.addPoint(new Point(getMapMode().DPtoLP(0), getMapMode()
					.DPtoLP(0)));
			body1.setFill(false);
			body1.setForegroundColor(BODY1_FORE);

			figure0.add(body1, new Rectangle(getMapMode().DPtoLP(20),
					getMapMode().DPtoLP(40), getMapMode().DPtoLP(1),
					getMapMode().DPtoLP(40)));

			PolylineShape legs1 = new PolylineShape();
			legs1.addPoint(new Point(getMapMode().DPtoLP(0), getMapMode()
					.DPtoLP(20)));
			legs1.addPoint(new Point(getMapMode().DPtoLP(21), getMapMode()
					.DPtoLP(0)));
			legs1.addPoint(new Point(getMapMode().DPtoLP(40), getMapMode()
					.DPtoLP(20)));
			legs1.setFill(false);
			legs1.setForegroundColor(LEGS1_FORE);

			figure0.add(legs1, new Rectangle(getMapMode().DPtoLP(0),
					getMapMode().DPtoLP(80), getMapMode().DPtoLP(40),
					getMapMode().DPtoLP(20)));

			fFigureActorNameFigure = new WrappingLabel();
			fFigureActorNameFigure.setText("");

			this.add(fFigureActorNameFigure);

		}

		/**
		 * @generated
		 */
		public WrappingLabel getFigureActorNameFigure() {
			return fFigureActorNameFigure;
		}

	}

	/**
	 * @generated
	 */
	static final Color HEAD1_FORE = new Color(null, 0, 0, 0);

	/**
	 * @generated
	 */
	static final Color HEAD1_BACK = new Color(null, 215, 235, 250);

	/**
	 * @generated
	 */
	static final Color ARMS1_FORE = new Color(null, 0, 0, 0);

	/**
	 * @generated
	 */
	static final Color BODY1_FORE = new Color(null, 0, 0, 0);

	/**
	 * @generated
	 */
	static final Color LEGS1_FORE = new Color(null, 0, 0, 0);

}
