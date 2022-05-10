package rsldl.diagram.edit.parts;

import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.PolylineDecoration;
import org.eclipse.draw2d.RotatableDecoration;
import org.eclipse.draw2d.geometry.PointList;
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

import rsldl.diagram.edit.policies.DLRelationshipParticipation3ItemSemanticEditPolicy;

/**
 * @generated
 */
public class DLRelationshipParticipation3EditPart extends
		ConnectionNodeEditPart implements ITreeBranchEditPart {

	/**
	 * @generated
	 */
	public static final int VISUAL_ID = 4003;

	/**
	 * @generated
	 */
	public DLRelationshipParticipation3EditPart(View view) {
		super(view);
	}

	/**
	 * @generated
	 */
	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
		installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE,
				new DLRelationshipParticipation3ItemSemanticEditPolicy());
	}

	/**
	 * @generated
	 */
	protected boolean addFixedChild(EditPart childEditPart) {
		if (childEditPart instanceof DLRelationshipParticipationName3EditPart) {
			((DLRelationshipParticipationName3EditPart) childEditPart)
					.setLabel(getPrimaryShape()
							.getFigureDLRelationshipParticipationToTargetNameFigure());
			return true;
		}
		if (childEditPart instanceof DLRelationshipParticipationMultiplicity3EditPart) {
			((DLRelationshipParticipationMultiplicity3EditPart) childEditPart)
					.setLabel(getPrimaryShape()
							.getDLRelationshipParticipationToTargetMultiplicityFigure());
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
		if (childEditPart instanceof DLRelationshipParticipationName3EditPart) {
			return true;
		}
		if (childEditPart instanceof DLRelationshipParticipationMultiplicity3EditPart) {
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
		return new DLRelationshipParticipationToTargetFigure();
	}

	/**
	 * @generated
	 */
	public DLRelationshipParticipationToTargetFigure getPrimaryShape() {
		return (DLRelationshipParticipationToTargetFigure) getFigure();
	}

	/**
	 * @generated
	 */
	public class DLRelationshipParticipationToTargetFigure extends
			PolylineConnectionEx {

		/**
		 * @generated
		 */
		private WrappingLabel fDLRelationshipParticipationToTargetMultiplicityFigure;
		/**
		 * @generated
		 */
		private WrappingLabel fFigureDLRelationshipParticipationToTargetNameFigure;

		/**
		 * @generated
		 */
		public DLRelationshipParticipationToTargetFigure() {
			this.setForegroundColor(THIS_FORE);

			createContents();
			setTargetDecoration(createTargetDecoration());
		}

		/**
		 * @generated
		 */
		private void createContents() {

			fDLRelationshipParticipationToTargetMultiplicityFigure = new WrappingLabel();
			fDLRelationshipParticipationToTargetMultiplicityFigure.setText("");

			fDLRelationshipParticipationToTargetMultiplicityFigure
					.setFont(FDLRELATIONSHIPPARTICIPATIONTOTARGETMULTIPLICITYFIGURE_FONT);

			this.add(fDLRelationshipParticipationToTargetMultiplicityFigure);

			fFigureDLRelationshipParticipationToTargetNameFigure = new WrappingLabel();
			fFigureDLRelationshipParticipationToTargetNameFigure.setText("");

			this.add(fFigureDLRelationshipParticipationToTargetNameFigure);

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
		public WrappingLabel getDLRelationshipParticipationToTargetMultiplicityFigure() {
			return fDLRelationshipParticipationToTargetMultiplicityFigure;
		}

		/**
		 * @generated
		 */
		public WrappingLabel getFigureDLRelationshipParticipationToTargetNameFigure() {
			return fFigureDLRelationshipParticipationToTargetNameFigure;
		}

	}

	/**
	 * @generated
	 */
	static final Color THIS_FORE = new Color(null, 0, 0, 0);

	/**
	 * @generated
	 */
	static final Font FDLRELATIONSHIPPARTICIPATIONTOTARGETMULTIPLICITYFIGURE_FONT = new Font(
			Display.getCurrent(), Display.getDefault().getSystemFont()
					.getFontData()[0].getName(), 8, SWT.ITALIC);

	/**
	 * @generated
	 */
	static final Color DF_FORE = new Color(null, 0, 0, 0);

}
