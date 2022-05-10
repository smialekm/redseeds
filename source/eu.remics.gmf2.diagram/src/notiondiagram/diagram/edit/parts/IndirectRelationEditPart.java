package notiondiagram.diagram.edit.parts;

import notiondiagram.diagram.edit.policies.IndirectRelationItemSemanticEditPolicy;

import org.eclipse.draw2d.Connection;
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
public class IndirectRelationEditPart extends ConnectionNodeEditPart implements
		ITreeBranchEditPart {

	/**
	 * @generated
	 */
	public static final int VISUAL_ID = 4004;

	/**
	 * @generated
	 */
	public IndirectRelationEditPart(View view) {
		super(view);
	}

	/**
	 * @generated
	 */
	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
		installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE,
				new IndirectRelationItemSemanticEditPolicy());
	}

	/**
	 * @generated
	 */
	protected boolean addFixedChild(EditPart childEditPart) {
		if (childEditPart instanceof IndirectRelationSourceMultiplicityEditPart) {
			((IndirectRelationSourceMultiplicityEditPart) childEditPart)
					.setLabel(getPrimaryShape().getFigureSourceMultiplicityI());
			return true;
		}
		if (childEditPart instanceof IndirectRelationTargetMultiplicityEditPart) {
			((IndirectRelationTargetMultiplicityEditPart) childEditPart)
					.setLabel(getPrimaryShape().getFigureTargetMultiplicityI());
			return true;
		}
		if (childEditPart instanceof IndirectRelationStereotypeEditPart) {
			((IndirectRelationStereotypeEditPart) childEditPart)
					.setLabel(getPrimaryShape().getFigureIRName());
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
		if (childEditPart instanceof IndirectRelationSourceMultiplicityEditPart) {
			return true;
		}
		if (childEditPart instanceof IndirectRelationTargetMultiplicityEditPart) {
			return true;
		}
		if (childEditPart instanceof IndirectRelationStereotypeEditPart) {
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
		return new IndirectRelationFigure();
	}

	/**
	 * @generated
	 */
	public IndirectRelationFigure getPrimaryShape() {
		return (IndirectRelationFigure) getFigure();
	}

	/**
	 * @generated
	 */
	public class IndirectRelationFigure extends PolylineConnectionEx {

		/**
		 * @generated
		 */
		private WrappingLabel fFigureSourceMultiplicityI;
		/**
		 * @generated
		 */
		private WrappingLabel fFigureTargetMultiplicityI;

		/**
		 * @generated
		 */
		private WrappingLabel fFigureIRName;

		/**
		 * @generated
		 */
		public IndirectRelationFigure() {
			this.setForegroundColor(THIS_FORE);

			createContents();
		}

		/**
		 * @generated
		 */
		private void createContents() {

			fFigureTargetMultiplicityI = new WrappingLabel();
			fFigureTargetMultiplicityI.setText("");

			this.add(fFigureTargetMultiplicityI);

			fFigureSourceMultiplicityI = new WrappingLabel();
			fFigureSourceMultiplicityI.setText("");

			this.add(fFigureSourceMultiplicityI);

			fFigureIRName = new WrappingLabel();
			fFigureIRName.setText("");

			this.add(fFigureIRName);

		}

		/**
		 * @generated
		 */
		public WrappingLabel getFigureSourceMultiplicityI() {
			return fFigureSourceMultiplicityI;
		}

		/**
		 * @generated
		 */
		public WrappingLabel getFigureTargetMultiplicityI() {
			return fFigureTargetMultiplicityI;
		}

		/**
		 * @generated
		 */
		public WrappingLabel getFigureIRName() {
			return fFigureIRName;
		}

	}

	/**
	 * @generated
	 */
	static final Color THIS_FORE = new Color(null, 0, 0, 0);

}
