package rsldl.diagram.edit.parts;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.draw2d.BorderLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.RoundedRectangle;
import org.eclipse.draw2d.Shape;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.LayoutEditPolicy;
import org.eclipse.gef.editpolicies.NonResizableEditPolicy;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gmf.runtime.diagram.core.edithelpers.CreateElementRequestAdapter;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.CreationEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewAndElementRequest;
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

import rsldl.diagram.edit.policies.DLPropertyItemSemanticEditPolicy;
import rsldl.diagram.part.RsldlVisualIDRegistry;
import rsldl.diagram.providers.RsldlElementTypes;

/**
 * @generated
 */
public class DLPropertyEditPart extends ShapeNodeEditPart {

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
	public DLPropertyEditPart(View view) {
		super(view);
	}

	/**
	 * @generated
	 */
	protected void createDefaultEditPolicies() {
		installEditPolicy(EditPolicyRoles.CREATION_ROLE,
				new CreationEditPolicy());
		super.createDefaultEditPolicies();
		installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE,
				new DLPropertyItemSemanticEditPolicy());
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
		return primaryShape = new DLPropertyFigure();
	}

	/**
	 * @generated
	 */
	public DLPropertyFigure getPrimaryShape() {
		return (DLPropertyFigure) primaryShape;
	}

	/**
	 * @generated
	 */
	protected boolean addFixedChild(EditPart childEditPart) {
		if (childEditPart instanceof DLPropertyNameEditPart) {
			((DLPropertyNameEditPart) childEditPart).setLabel(getPrimaryShape()
					.getDLPropertyNameFigure());
			return true;
		}
		if (childEditPart instanceof DLPropertyTypeEditPart) {
			((DLPropertyTypeEditPart) childEditPart).setLabel(getPrimaryShape()
					.getDLPropertyTypeFigure());
			return true;
		}
		if (childEditPart instanceof DLPropertyDLPropertyCompartmentEditPart) {
			IFigure pane = getPrimaryShape().getDLPropertyCompartmentFigure();
			setupContentPane(pane); // FIXME each comparment should handle his content pane in his own way 
			pane.add(((DLPropertyDLPropertyCompartmentEditPart) childEditPart)
					.getFigure());
			return true;
		}
		return false;
	}

	/**
	 * @generated
	 */
	protected boolean removeFixedChild(EditPart childEditPart) {
		if (childEditPart instanceof DLPropertyNameEditPart) {
			return true;
		}
		if (childEditPart instanceof DLPropertyTypeEditPart) {
			return true;
		}
		if (childEditPart instanceof DLPropertyDLPropertyCompartmentEditPart) {
			IFigure pane = getPrimaryShape().getDLPropertyCompartmentFigure();
			setupContentPane(pane); // FIXME each comparment should handle his content pane in his own way 
			pane.remove(((DLPropertyDLPropertyCompartmentEditPart) childEditPart)
					.getFigure());
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
		if (editPart instanceof DLPropertyDLPropertyCompartmentEditPart) {
			return getPrimaryShape().getDLPropertyCompartmentFigure();
		}
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
				.getType(DLPropertyNameEditPart.VISUAL_ID));
	}

	/**
	 * @generated
	 */
	public List<IElementType> getMARelTypesOnSource() {
		ArrayList<IElementType> types = new ArrayList<IElementType>(1);
		types.add(RsldlElementTypes.DLAttributeLink_4004);
		return types;
	}

	/**
	 * @generated
	 */
	public List<IElementType> getMARelTypesOnSourceAndTarget(
			IGraphicalEditPart targetEditPart) {
		LinkedList<IElementType> types = new LinkedList<IElementType>();
		if (targetEditPart instanceof DLEntityEditPart) {
			types.add(RsldlElementTypes.DLAttributeLink_4004);
		}
		if (targetEditPart instanceof rsldl.diagram.edit.parts.DLPropertyEditPart) {
			types.add(RsldlElementTypes.DLAttributeLink_4004);
		}
		return types;
	}

	/**
	 * @generated
	 */
	public List<IElementType> getMATypesForTarget(IElementType relationshipType) {
		LinkedList<IElementType> types = new LinkedList<IElementType>();
		if (relationshipType == RsldlElementTypes.DLAttributeLink_4004) {
			types.add(RsldlElementTypes.DLEntity_2001);
			types.add(RsldlElementTypes.DLProperty_2002);
		}
		return types;
	}

	/**
	 * @generated
	 */
	public List<IElementType> getMARelTypesOnTarget() {
		ArrayList<IElementType> types = new ArrayList<IElementType>(4);
		types.add(RsldlElementTypes.DLRelationshipParticipation_4001);
		types.add(RsldlElementTypes.DLRelationshipParticipation_4002);
		types.add(RsldlElementTypes.DLRelationshipParticipation_4003);
		types.add(RsldlElementTypes.DLAttributeLink_4004);
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
		} else if (relationshipType == RsldlElementTypes.DLAttributeLink_4004) {
			types.add(RsldlElementTypes.DLProperty_2002);
		}
		return types;
	}

	/**
	 * @generated
	 */
	public EditPart getTargetEditPart(Request request) {
		if (request instanceof CreateViewAndElementRequest) {
			CreateElementRequestAdapter adapter = ((CreateViewAndElementRequest) request)
					.getViewAndElementDescriptor()
					.getCreateElementRequestAdapter();
			IElementType type = (IElementType) adapter
					.getAdapter(IElementType.class);
			if (type == RsldlElementTypes.DLIdentityCondition_3004) {
				return getChildBySemanticHint(RsldlVisualIDRegistry
						.getType(DLPropertyDLPropertyCompartmentEditPart.VISUAL_ID));
			}
			if (type == RsldlElementTypes.DLInheritanceCondition_3005) {
				return getChildBySemanticHint(RsldlVisualIDRegistry
						.getType(DLPropertyDLPropertyCompartmentEditPart.VISUAL_ID));
			}
			if (type == RsldlElementTypes.DLValidityCondition_3006) {
				return getChildBySemanticHint(RsldlVisualIDRegistry
						.getType(DLPropertyDLPropertyCompartmentEditPart.VISUAL_ID));
			}
		}
		return super.getTargetEditPart(request);
	}

	/**
	 * @generated
	 */
	public class DLPropertyFigure extends RoundedRectangle {

		/**
		 * @generated
		 */
		private WrappingLabel fDLPropertyTypeFigure;
		/**
		 * @generated
		 */
		private WrappingLabel fDLPropertyNameFigure;
		/**
		 * @generated
		 */
		private RoundedRectangle fDLPropertyCompartmentFigure;

		/**
		 * @generated
		 */
		public DLPropertyFigure() {

			BorderLayout layoutThis = new BorderLayout();
			this.setLayoutManager(layoutThis);

			this.setCornerDimensions(new Dimension(getMapMode().DPtoLP(8),
					getMapMode().DPtoLP(8)));
			this.setForegroundColor(THIS_FORE);
			this.setBackgroundColor(THIS_BACK);
			this.setBorder(new LineBorder(null, getMapMode().DPtoLP(1)));
			createContents();
		}

		/**
		 * @generated
		 */
		private void createContents() {

			RoundedRectangle dLPropertyTitleFigure0 = new RoundedRectangle();
			dLPropertyTitleFigure0.setCornerDimensions(new Dimension(
					getMapMode().DPtoLP(8), getMapMode().DPtoLP(8)));
			dLPropertyTitleFigure0.setFill(false);
			dLPropertyTitleFigure0.setOutline(false);

			this.add(dLPropertyTitleFigure0, BorderLayout.TOP);

			BorderLayout layoutDLPropertyTitleFigure0 = new BorderLayout();
			dLPropertyTitleFigure0
					.setLayoutManager(layoutDLPropertyTitleFigure0);

			fDLPropertyTypeFigure = new WrappingLabel();
			fDLPropertyTypeFigure.setText("");

			fDLPropertyTypeFigure.setFont(FDLPROPERTYTYPEFIGURE_FONT);

			dLPropertyTitleFigure0.add(fDLPropertyTypeFigure, BorderLayout.TOP);

			fDLPropertyNameFigure = new WrappingLabel();
			fDLPropertyNameFigure.setText("");

			dLPropertyTitleFigure0.add(fDLPropertyNameFigure,
					BorderLayout.CENTER);

			fDLPropertyCompartmentFigure = new RoundedRectangle();
			fDLPropertyCompartmentFigure.setCornerDimensions(new Dimension(
					getMapMode().DPtoLP(8), getMapMode().DPtoLP(8)));
			fDLPropertyCompartmentFigure.setFill(false);
			fDLPropertyCompartmentFigure.setOutline(false);
			fDLPropertyCompartmentFigure
					.setForegroundColor(FDLPROPERTYCOMPARTMENTFIGURE_FORE);

			this.add(fDLPropertyCompartmentFigure, BorderLayout.CENTER);

		}

		/**
		 * @generated
		 */
		public WrappingLabel getDLPropertyTypeFigure() {
			return fDLPropertyTypeFigure;
		}

		/**
		 * @generated
		 */
		public WrappingLabel getDLPropertyNameFigure() {
			return fDLPropertyNameFigure;
		}

		/**
		 * @generated
		 */
		public RoundedRectangle getDLPropertyCompartmentFigure() {
			return fDLPropertyCompartmentFigure;
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
	static final Font FDLPROPERTYTYPEFIGURE_FONT = new Font(
			Display.getCurrent(), Display.getDefault().getSystemFont()
					.getFontData()[0].getName(), 7, SWT.ITALIC);

	/**
	 * @generated
	 */
	static final Color FDLPROPERTYCOMPARTMENTFIGURE_FORE = new Color(null, 215,
			235, 250);

}
