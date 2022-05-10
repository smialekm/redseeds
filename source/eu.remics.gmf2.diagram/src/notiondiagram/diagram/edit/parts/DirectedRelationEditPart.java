package notiondiagram.diagram.edit.parts;

import notiondiagram.diagram.edit.policies.DirectedRelationItemSemanticEditPolicy;

import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.PolylineDecoration;
import org.eclipse.draw2d.RotatableDecoration;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITreeBranchEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.draw2d.ui.figures.PolylineConnectionEx;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.swt.graphics.Color;

/**
 * @generated
 */
public class DirectedRelationEditPart extends ConnectionNodeEditPart implements
		ITreeBranchEditPart {

	/**
	 * @generated
	 */
	public static final int VISUAL_ID = 4003;

	/**
	 * @generated
	 */
	public DirectedRelationEditPart(View view) {
		super(view);
	}

	/**
	 * @generated
	 */
	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
		installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE,
				new DirectedRelationItemSemanticEditPolicy());
	}

	/**
	 * @generated
	 */
	protected boolean addFixedChild(EditPart childEditPart) {
		if (childEditPart instanceof DirectedRelationSourceMultiplicityEditPart) {
			((DirectedRelationSourceMultiplicityEditPart) childEditPart)
					.setLabel(getPrimaryShape().getFigureSourceMultiplicityD());
			return true;
		}
		if (childEditPart instanceof DirectedRelationTargetMultiplicityEditPart) {
			((DirectedRelationTargetMultiplicityEditPart) childEditPart)
					.setLabel(getPrimaryShape().getFigureTargetMultiplicityD());
			return true;
		}
		if (childEditPart instanceof DirectedRelationStereotypeEditPart) {
			((DirectedRelationStereotypeEditPart) childEditPart)
					.setLabel(getPrimaryShape().getFigureDRName());
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
		super.addChildVisual(childEditPart, index);
	}

	/**
	 * @generated
	 */
	protected boolean removeFixedChild(EditPart childEditPart) {
		if (childEditPart instanceof DirectedRelationSourceMultiplicityEditPart) {
			return true;
		}
		if (childEditPart instanceof DirectedRelationTargetMultiplicityEditPart) {
			return true;
		}
		if (childEditPart instanceof DirectedRelationStereotypeEditPart) {
			return true;
		}
		return false;
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
	 * Creates figure for this edit part.
	 * 
	 * Body of this method does not depend on settings in generation model
	 * so you may safely remove <i>generated</i> tag and modify it.
	 * 
	 * @generated
	 */
	protected Connection createConnectionFigure() {
		return new DirectedRelationFigure();
	}

	/**
	 * @generated
	 */
	public DirectedRelationFigure getPrimaryShape() {
		return (DirectedRelationFigure) getFigure();
	}

	/**
	 * @generated
	 */
	public class DirectedRelationFigure extends PolylineConnectionEx {

		/**
		 * @generated
		 */
		private WrappingLabel fFigureSourceMultiplicityD;
		/**
		 * @generated
		 */
		private WrappingLabel fFigureTargetMultiplicityD;

		/**
		 * @generated
		 */
		private WrappingLabel fFigureDRName;

		/**
		 * @generated
		 */
		public DirectedRelationFigure() {
			this.setForegroundColor(THIS_FORE);

			createContents();
			setTargetDecoration(createTargetDecoration());
		}

		/**
		 * @generated
		 */
		private void createContents() {

			fFigureTargetMultiplicityD = new WrappingLabel();
			fFigureTargetMultiplicityD.setText("");

			this.add(fFigureTargetMultiplicityD);

			fFigureSourceMultiplicityD = new WrappingLabel();
			fFigureSourceMultiplicityD.setText("");

			this.add(fFigureSourceMultiplicityD);

			fFigureDRName = new WrappingLabel();
			fFigureDRName.setText("");

			this.add(fFigureDRName);

		}

		/**
		 * @generated
		 */
		private RotatableDecoration createTargetDecoration() {
			PolylineDecoration df = new PolylineDecoration();
			df.setForegroundColor(DF_FORE);
			PointList pl = new PointList();
			pl.addPoint(getMapMode().DPtoLP(-3), getMapMode().DPtoLP(2));
			pl.addPoint(getMapMode().DPtoLP(0), getMapMode().DPtoLP(0));
			pl.addPoint(getMapMode().DPtoLP(-3), getMapMode().DPtoLP(-2));
			df.setTemplate(pl);
			df.setScale(getMapMode().DPtoLP(7), getMapMode().DPtoLP(3));
			return df;
		}

		/**
		 * @generated
		 */
		public WrappingLabel getFigureSourceMultiplicityD() {
			return fFigureSourceMultiplicityD;
		}

		/**
		 * @generated
		 */
		public WrappingLabel getFigureTargetMultiplicityD() {
			return fFigureTargetMultiplicityD;
		}

		/**
		 * @generated
		 */
		public WrappingLabel getFigureDRName() {
			return fFigureDRName;
		}

	}

	/**
	 * @generated
	 */
	static final Color THIS_FORE = new Color(null, 0, 0, 0);
	/**
	 * @generated
	 */
	static final Color DF_FORE = new Color(null, 0, 0, 0);

}
