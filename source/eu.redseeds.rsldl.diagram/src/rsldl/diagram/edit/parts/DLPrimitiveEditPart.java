package rsldl.diagram.edit.parts;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.draw2d.Ellipse;
import org.eclipse.draw2d.GridData;
import org.eclipse.draw2d.GridLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Shape;
import org.eclipse.draw2d.StackLayout;
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

import rsldl.diagram.edit.policies.DLPrimitiveItemSemanticEditPolicy;
import rsldl.diagram.part.RsldlVisualIDRegistry;
import rsldl.diagram.providers.RsldlElementTypes;

/**
 * @generated
 */
public class DLPrimitiveEditPart extends ShapeNodeEditPart {

	/**
	 * @generated
	 */
	public static final int VISUAL_ID = 2003;

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
	public DLPrimitiveEditPart(View view) {
		super(view);
	}

	/**
	 * @generated
	 */
	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
		installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE,
				new DLPrimitiveItemSemanticEditPolicy());
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
		return primaryShape = new DLPrimitiveFigure();
	}

	/**
	 * @generated
	 */
	public DLPrimitiveFigure getPrimaryShape() {
		return (DLPrimitiveFigure) primaryShape;
	}

	/**
	 * @generated
	 */
	protected boolean addFixedChild(EditPart childEditPart) {
		if (childEditPart instanceof DLPrimitiveNameEditPart) {
			((DLPrimitiveNameEditPart) childEditPart)
					.setLabel(getPrimaryShape()
							.getDLPrimitiveIdentifierFigure());
			return true;
		}
		if (childEditPart instanceof WrappingLabelEditPart) {
			((WrappingLabelEditPart) childEditPart).setLabel(getPrimaryShape()
					.getDLPrimitiveTypeFigure());
			return true;
		}
		return false;
	}

	/**
	 * @generated
	 */
	protected boolean removeFixedChild(EditPart childEditPart) {
		if (childEditPart instanceof DLPrimitiveNameEditPart) {
			return true;
		}
		if (childEditPart instanceof WrappingLabelEditPart) {
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
				.getType(DLPrimitiveNameEditPart.VISUAL_ID));
	}

	/**
	 * @generated
	 */
	public List<IElementType> getMARelTypesOnTarget() {
		ArrayList<IElementType> types = new ArrayList<IElementType>(3);
		types.add(RsldlElementTypes.DLRelationshipParticipation_4001);
		types.add(RsldlElementTypes.DLRelationshipParticipation_4002);
		types.add(RsldlElementTypes.DLRelationshipParticipation_4003);
		return types;
	}

	/**
	 * @generated
	 */
	public List<IElementType> getMATypesForSource(IElementType relationshipType) {
		LinkedList<IElementType> types = new LinkedList<IElementType>();
		if (relationshipType == RsldlElementTypes.DLRelationshipParticipation_4001) {
			types.add(RsldlElementTypes.DLDataBasedReference_2004);
			types.add(RsldlElementTypes.DLPatternBasedReference_2005);
			types.add(RsldlElementTypes.DLAlghoritmicTransition_2006);
			types.add(RsldlElementTypes.DLPatternBasedTransition_2007);
		} else if (relationshipType == RsldlElementTypes.DLRelationshipParticipation_4002) {
			types.add(RsldlElementTypes.DLDataBasedReference_2004);
			types.add(RsldlElementTypes.DLPatternBasedReference_2005);
			types.add(RsldlElementTypes.DLAlghoritmicTransition_2006);
			types.add(RsldlElementTypes.DLPatternBasedTransition_2007);
		} else if (relationshipType == RsldlElementTypes.DLRelationshipParticipation_4003) {
			types.add(RsldlElementTypes.DLDataBasedReference_2004);
			types.add(RsldlElementTypes.DLPatternBasedReference_2005);
			types.add(RsldlElementTypes.DLAlghoritmicTransition_2006);
			types.add(RsldlElementTypes.DLPatternBasedTransition_2007);
		}
		return types;
	}

	/**
	 * @generated
	 */
	public class DLPrimitiveFigure extends Ellipse {

		/**
		 * @generated
		 */
		private WrappingLabel fDLPrimitiveTypeFigure;
		/**
		 * @generated
		 */
		private WrappingLabel fDLPrimitiveIdentifierFigure;

		/**
		 * @generated
		 */
		public DLPrimitiveFigure() {

			GridLayout layoutThis = new GridLayout();
			layoutThis.numColumns = 1;
			layoutThis.makeColumnsEqualWidth = true;
			this.setLayoutManager(layoutThis);

			this.setForegroundColor(THIS_FORE);
			this.setBackgroundColor(THIS_BACK);
			createContents();
		}

		/**
		 * @generated
		 */
		private void createContents() {

			fDLPrimitiveTypeFigure = new WrappingLabel();
			fDLPrimitiveTypeFigure.setText("Primitive");

			fDLPrimitiveTypeFigure.setFont(FDLPRIMITIVETYPEFIGURE_FONT);

			GridData constraintFDLPrimitiveTypeFigure = new GridData();
			constraintFDLPrimitiveTypeFigure.verticalAlignment = GridData.CENTER;
			constraintFDLPrimitiveTypeFigure.horizontalAlignment = GridData.CENTER;
			constraintFDLPrimitiveTypeFigure.horizontalIndent = 0;
			constraintFDLPrimitiveTypeFigure.horizontalSpan = 1;
			constraintFDLPrimitiveTypeFigure.verticalSpan = 1;
			constraintFDLPrimitiveTypeFigure.grabExcessHorizontalSpace = true;
			constraintFDLPrimitiveTypeFigure.grabExcessVerticalSpace = false;
			this.add(fDLPrimitiveTypeFigure, constraintFDLPrimitiveTypeFigure);

			fDLPrimitiveIdentifierFigure = new WrappingLabel();
			fDLPrimitiveIdentifierFigure.setText("");

			GridData constraintFDLPrimitiveIdentifierFigure = new GridData();
			constraintFDLPrimitiveIdentifierFigure.verticalAlignment = GridData.BEGINNING;
			constraintFDLPrimitiveIdentifierFigure.horizontalAlignment = GridData.CENTER;
			constraintFDLPrimitiveIdentifierFigure.horizontalIndent = 0;
			constraintFDLPrimitiveIdentifierFigure.horizontalSpan = 1;
			constraintFDLPrimitiveIdentifierFigure.verticalSpan = 1;
			constraintFDLPrimitiveIdentifierFigure.grabExcessHorizontalSpace = true;
			constraintFDLPrimitiveIdentifierFigure.grabExcessVerticalSpace = true;
			this.add(fDLPrimitiveIdentifierFigure,
					constraintFDLPrimitiveIdentifierFigure);

		}

		/**
		 * @generated
		 */
		public WrappingLabel getDLPrimitiveTypeFigure() {
			return fDLPrimitiveTypeFigure;
		}

		/**
		 * @generated
		 */
		public WrappingLabel getDLPrimitiveIdentifierFigure() {
			return fDLPrimitiveIdentifierFigure;
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
	static final Font FDLPRIMITIVETYPEFIGURE_FONT = new Font(
			Display.getCurrent(), Display.getDefault().getSystemFont()
					.getFontData()[0].getName(), 9, SWT.ITALIC);

}
