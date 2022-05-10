package rsldl.diagram.edit.parts;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.draw2d.GridData;
import org.eclipse.draw2d.GridLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.ScalablePolygonShape;
import org.eclipse.draw2d.Shape;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.LayoutEditPolicy;
import org.eclipse.gef.editpolicies.NonResizableEditPolicy;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.draw2d.ui.figures.ConstrainedToolbarLayout;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Display;

import rsldl.diagram.edit.policies.DLDataBasedReferenceItemSemanticEditPolicy;
import rsldl.diagram.part.RsldlVisualIDRegistry;
import rsldl.diagram.providers.RsldlElementTypes;

/**
 * @generated
 */
public class DLDataBasedReferenceEditPart extends ShapeNodeEditPart {

	/**
	 * @generated
	 */
	public static final int VISUAL_ID = 2004;

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
	public DLDataBasedReferenceEditPart(View view) {
		super(view);
	}

	/**
	 * @generated
	 */
	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
		installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE,
				new DLDataBasedReferenceItemSemanticEditPolicy());
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
		return primaryShape = new DLRelationshipFigure();
	}

	/**
	 * @generated
	 */
	public DLRelationshipFigure getPrimaryShape() {
		return (DLRelationshipFigure) primaryShape;
	}

	/**
	 * @generated
	 */
	protected boolean addFixedChild(EditPart childEditPart) {
		if (childEditPart instanceof DLDataBasedReferenceNameEditPart) {
			((DLDataBasedReferenceNameEditPart) childEditPart)
					.setLabel(getPrimaryShape().getDLRelationshipNameFigure());
			return true;
		}
		if (childEditPart instanceof DLDataBasedReferenceTypeEditPart) {
			((DLDataBasedReferenceTypeEditPart) childEditPart)
					.setLabel(getPrimaryShape().getDLRelationshipTypeFigure());
			return true;
		}
		return false;
	}

	/**
	 * @generated
	 */
	protected boolean removeFixedChild(EditPart childEditPart) {
		if (childEditPart instanceof DLDataBasedReferenceNameEditPart) {
			return true;
		}
		if (childEditPart instanceof DLDataBasedReferenceTypeEditPart) {
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
		return getChildBySemanticHint(RsldlVisualIDRegistry
				.getType(DLDataBasedReferenceNameEditPart.VISUAL_ID));
	}

	/**
	 * @generated
	 */
	public List<IElementType> getMARelTypesOnSource() {
		ArrayList<IElementType> types = new ArrayList<IElementType>(3);
		types.add(RsldlElementTypes.DLRelationshipParticipation_4001);
		types.add(RsldlElementTypes.DLRelationshipParticipation_4002);
		types.add(RsldlElementTypes.DLRelationshipParticipation_4003);
		return types;
	}

	/**
	 * @generated
	 */
	public List<IElementType> getMARelTypesOnSourceAndTarget(
			IGraphicalEditPart targetEditPart) {
		LinkedList<IElementType> types = new LinkedList<IElementType>();
		if (targetEditPart instanceof DLEntityEditPart) {
			types.add(RsldlElementTypes.DLRelationshipParticipation_4001);
		}
		if (targetEditPart instanceof DLPropertyEditPart) {
			types.add(RsldlElementTypes.DLRelationshipParticipation_4001);
		}
		if (targetEditPart instanceof DLPrimitiveEditPart) {
			types.add(RsldlElementTypes.DLRelationshipParticipation_4001);
		}
		if (targetEditPart instanceof DLEntityEditPart) {
			types.add(RsldlElementTypes.DLRelationshipParticipation_4002);
		}
		if (targetEditPart instanceof DLPropertyEditPart) {
			types.add(RsldlElementTypes.DLRelationshipParticipation_4002);
		}
		if (targetEditPart instanceof DLPrimitiveEditPart) {
			types.add(RsldlElementTypes.DLRelationshipParticipation_4002);
		}
		if (targetEditPart instanceof DLEntityEditPart) {
			types.add(RsldlElementTypes.DLRelationshipParticipation_4003);
		}
		if (targetEditPart instanceof DLPropertyEditPart) {
			types.add(RsldlElementTypes.DLRelationshipParticipation_4003);
		}
		if (targetEditPart instanceof DLPrimitiveEditPart) {
			types.add(RsldlElementTypes.DLRelationshipParticipation_4003);
		}
		return types;
	}

	/**
	 * @generated
	 */
	public List<IElementType> getMATypesForTarget(IElementType relationshipType) {
		LinkedList<IElementType> types = new LinkedList<IElementType>();
		if (relationshipType == RsldlElementTypes.DLRelationshipParticipation_4001) {
			types.add(RsldlElementTypes.DLEntity_2001);
			types.add(RsldlElementTypes.DLProperty_2002);
			types.add(RsldlElementTypes.DLPrimitive_2003);
		} else if (relationshipType == RsldlElementTypes.DLRelationshipParticipation_4002) {
			types.add(RsldlElementTypes.DLEntity_2001);
			types.add(RsldlElementTypes.DLProperty_2002);
			types.add(RsldlElementTypes.DLPrimitive_2003);
		} else if (relationshipType == RsldlElementTypes.DLRelationshipParticipation_4003) {
			types.add(RsldlElementTypes.DLEntity_2001);
			types.add(RsldlElementTypes.DLProperty_2002);
			types.add(RsldlElementTypes.DLPrimitive_2003);
		}
		return types;
	}

	/**
	 * @generated
	 */
	public class DLRelationshipFigure extends ScalablePolygonShape {

		/**
		 * @generated
		 */
		private WrappingLabel fDLRelationshipNameFigure;
		/**
		 * @generated
		 */
		private WrappingLabel fDLRelationshipTypeFigure;

		/**
		 * @generated
		 */
		public DLRelationshipFigure() {

			GridLayout layoutThis = new GridLayout();
			layoutThis.numColumns = 1;
			layoutThis.makeColumnsEqualWidth = true;
			this.setLayoutManager(layoutThis);

			this.addPoint(new Point(getMapMode().DPtoLP(10), getMapMode()
					.DPtoLP(0)));
			this.addPoint(new Point(getMapMode().DPtoLP(30), getMapMode()
					.DPtoLP(0)));
			this.addPoint(new Point(getMapMode().DPtoLP(40), getMapMode()
					.DPtoLP(20)));
			this.addPoint(new Point(getMapMode().DPtoLP(30), getMapMode()
					.DPtoLP(40)));
			this.addPoint(new Point(getMapMode().DPtoLP(10), getMapMode()
					.DPtoLP(40)));
			this.addPoint(new Point(getMapMode().DPtoLP(0), getMapMode()
					.DPtoLP(20)));
			this.setFill(true);
			this.setForegroundColor(THIS_FORE);
			this.setBackgroundColor(THIS_BACK);
			createContents();
		}

		/**
		 * @generated
		 */
		private void createContents() {

			fDLRelationshipTypeFigure = new WrappingLabel();
			fDLRelationshipTypeFigure.setText("");

			fDLRelationshipTypeFigure.setFont(FDLRELATIONSHIPTYPEFIGURE_FONT);

			GridData constraintFDLRelationshipTypeFigure = new GridData();
			constraintFDLRelationshipTypeFigure.verticalAlignment = GridData.CENTER;
			constraintFDLRelationshipTypeFigure.horizontalAlignment = GridData.CENTER;
			constraintFDLRelationshipTypeFigure.horizontalIndent = 0;
			constraintFDLRelationshipTypeFigure.horizontalSpan = 1;
			constraintFDLRelationshipTypeFigure.verticalSpan = 1;
			constraintFDLRelationshipTypeFigure.grabExcessHorizontalSpace = true;
			constraintFDLRelationshipTypeFigure.grabExcessVerticalSpace = false;
			this.add(fDLRelationshipTypeFigure,
					constraintFDLRelationshipTypeFigure);

			fDLRelationshipNameFigure = new WrappingLabel();
			fDLRelationshipNameFigure.setText("");

			GridData constraintFDLRelationshipNameFigure = new GridData();
			constraintFDLRelationshipNameFigure.verticalAlignment = GridData.BEGINNING;
			constraintFDLRelationshipNameFigure.horizontalAlignment = GridData.CENTER;
			constraintFDLRelationshipNameFigure.horizontalIndent = 0;
			constraintFDLRelationshipNameFigure.horizontalSpan = 1;
			constraintFDLRelationshipNameFigure.verticalSpan = 1;
			constraintFDLRelationshipNameFigure.grabExcessHorizontalSpace = true;
			constraintFDLRelationshipNameFigure.grabExcessVerticalSpace = true;
			this.add(fDLRelationshipNameFigure,
					constraintFDLRelationshipNameFigure);

		}

		/**
		 * @generated
		 */
		public WrappingLabel getDLRelationshipNameFigure() {
			return fDLRelationshipNameFigure;
		}

		/**
		 * @generated
		 */
		public WrappingLabel getDLRelationshipTypeFigure() {
			return fDLRelationshipTypeFigure;
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

	/**
	 * @generated
	 */
	static final Font FDLRELATIONSHIPTYPEFIGURE_FONT = new Font(
			Display.getCurrent(), Display.getDefault().getSystemFont()
					.getFontData()[0].getName(), 7, SWT.ITALIC);

}
