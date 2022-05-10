package rsldl.diagram.edit.parts;

import org.eclipse.draw2d.Connection;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITreeBranchEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.draw2d.ui.figures.PolylineConnectionEx;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Display;

import rsldl.diagram.edit.policies.DLRelationshipParticipationItemSemanticEditPolicy;

/**
 * @generated
 */
public class DLRelationshipParticipationEditPart extends ConnectionNodeEditPart
		implements ITreeBranchEditPart {

	/**
	 * @generated
	 */
	public static final int VISUAL_ID = 4001;

	/**
	 * @generated
	 */
	public DLRelationshipParticipationEditPart(View view) {
		super(view);
	}

	/**
	 * @generated
	 */
	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
		installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE,
				new DLRelationshipParticipationItemSemanticEditPolicy());
	}

	/**
	 * @generated
	 */
	protected boolean addFixedChild(EditPart childEditPart) {
		if (childEditPart instanceof DLRelationshipParticipationNameEditPart) {
			((DLRelationshipParticipationNameEditPart) childEditPart)
					.setLabel(getPrimaryShape()
							.getFigureDLRelationshipParticipationNameFigure());
			return true;
		}
		if (childEditPart instanceof DLRelationshipParticipationMultiplicityEditPart) {
			((DLRelationshipParticipationMultiplicityEditPart) childEditPart)
					.setLabel(getPrimaryShape()
							.getDLRelationshipParticipationMultiplicityFigure());
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
		if (childEditPart instanceof DLRelationshipParticipationNameEditPart) {
			return true;
		}
		if (childEditPart instanceof DLRelationshipParticipationMultiplicityEditPart) {
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
		return new DLRelationshipParticipationFigure();
	}

	/**
	 * @generated
	 */
	public DLRelationshipParticipationFigure getPrimaryShape() {
		return (DLRelationshipParticipationFigure) getFigure();
	}

	/**
	 * @generated
	 */
	public class DLRelationshipParticipationFigure extends PolylineConnectionEx {

		/**
		 * @generated
		 */
		private WrappingLabel fDLRelationshipParticipationMultiplicityFigure;
		/**
		 * @generated
		 */
		private WrappingLabel fFigureDLRelationshipParticipationNameFigure;

		/**
		 * @generated
		 */
		public DLRelationshipParticipationFigure() {
			this.setForegroundColor(THIS_FORE);

			createContents();
		}

		/**
		 * @generated
		 */
		private void createContents() {

			fDLRelationshipParticipationMultiplicityFigure = new WrappingLabel();
			fDLRelationshipParticipationMultiplicityFigure.setText("");

			fDLRelationshipParticipationMultiplicityFigure
					.setFont(FDLRELATIONSHIPPARTICIPATIONMULTIPLICITYFIGURE_FONT);

			this.add(fDLRelationshipParticipationMultiplicityFigure);

			fFigureDLRelationshipParticipationNameFigure = new WrappingLabel();
			fFigureDLRelationshipParticipationNameFigure.setText("");

			this.add(fFigureDLRelationshipParticipationNameFigure);

		}

		/**
		 * @generated
		 */
		public WrappingLabel getDLRelationshipParticipationMultiplicityFigure() {
			return fDLRelationshipParticipationMultiplicityFigure;
		}

		/**
		 * @generated
		 */
		public WrappingLabel getFigureDLRelationshipParticipationNameFigure() {
			return fFigureDLRelationshipParticipationNameFigure;
		}

	}

	/**
	 * @generated
	 */
	static final Color THIS_FORE = new Color(null, 0, 0, 0);

	/**
	 * @generated
	 */
	static final Font FDLRELATIONSHIPPARTICIPATIONMULTIPLICITYFIGURE_FONT = new Font(
			Display.getCurrent(), Display.getDefault().getSystemFont()
					.getFontData()[0].getName(), 8, SWT.ITALIC);

}
