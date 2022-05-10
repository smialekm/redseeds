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

import rsldl.diagram.edit.policies.DLRelationshipParticipation2ItemSemanticEditPolicy;

/**
 * @generated
 */
public class DLRelationshipParticipation2EditPart extends
		ConnectionNodeEditPart implements ITreeBranchEditPart {

	/**
	 * @generated
	 */
	public static final int VISUAL_ID = 4002;

	/**
	 * @generated
	 */
	public DLRelationshipParticipation2EditPart(View view) {
		super(view);
	}

	/**
	 * @generated
	 */
	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
		installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE,
				new DLRelationshipParticipation2ItemSemanticEditPolicy());
	}

	/**
	 * @generated
	 */
	protected boolean addFixedChild(EditPart childEditPart) {
		if (childEditPart instanceof DLRelationshipParticipationName2EditPart) {
			((DLRelationshipParticipationName2EditPart) childEditPart)
					.setLabel(getPrimaryShape()
							.getFigureDLRelationshipParticipationToSourceNameFigure());
			return true;
		}
		if (childEditPart instanceof DLRelationshipParticipationMultiplicity2EditPart) {
			((DLRelationshipParticipationMultiplicity2EditPart) childEditPart)
					.setLabel(getPrimaryShape()
							.getDLRelationshipParticipationToSourceMultiplicityFigure());
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
		if (childEditPart instanceof DLRelationshipParticipationName2EditPart) {
			return true;
		}
		if (childEditPart instanceof DLRelationshipParticipationMultiplicity2EditPart) {
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
		return new DLRelationshipParticipationToSourceFigure();
	}

	/**
	 * @generated
	 */
	public DLRelationshipParticipationToSourceFigure getPrimaryShape() {
		return (DLRelationshipParticipationToSourceFigure) getFigure();
	}

	/**
	 * @generated
	 */
	public class DLRelationshipParticipationToSourceFigure extends
			PolylineConnectionEx {

		/**
		 * @generated
		 */
		private WrappingLabel fDLRelationshipParticipationToSourceMultiplicityFigure;
		/**
		 * @generated
		 */
		private WrappingLabel fFigureDLRelationshipParticipationToSourceNameFigure;

		/**
		 * @generated
		 */
		public DLRelationshipParticipationToSourceFigure() {
			this.setForegroundColor(THIS_FORE);

			createContents();
			setSourceDecoration(createSourceDecoration());
		}

		/**
		 * @generated
		 */
		private void createContents() {

			fDLRelationshipParticipationToSourceMultiplicityFigure = new WrappingLabel();
			fDLRelationshipParticipationToSourceMultiplicityFigure.setText("");

			fDLRelationshipParticipationToSourceMultiplicityFigure
					.setFont(FDLRELATIONSHIPPARTICIPATIONTOSOURCEMULTIPLICITYFIGURE_FONT);

			this.add(fDLRelationshipParticipationToSourceMultiplicityFigure);

			fFigureDLRelationshipParticipationToSourceNameFigure = new WrappingLabel();
			fFigureDLRelationshipParticipationToSourceNameFigure.setText("");

			this.add(fFigureDLRelationshipParticipationToSourceNameFigure);

		}

		/**
		 * @generated
		 */
		private RotatableDecoration createSourceDecoration() {
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
		public WrappingLabel getDLRelationshipParticipationToSourceMultiplicityFigure() {
			return fDLRelationshipParticipationToSourceMultiplicityFigure;
		}

		/**
		 * @generated
		 */
		public WrappingLabel getFigureDLRelationshipParticipationToSourceNameFigure() {
			return fFigureDLRelationshipParticipationToSourceNameFigure;
		}

	}

	/**
	 * @generated
	 */
	static final Color THIS_FORE = new Color(null, 0, 0, 0);

	/**
	 * @generated
	 */
	static final Font FDLRELATIONSHIPPARTICIPATIONTOSOURCEMULTIPLICITYFIGURE_FONT = new Font(
			Display.getCurrent(), Display.getDefault().getSystemFont()
					.getFontData()[0].getName(), 8, SWT.ITALIC);

	/**
	 * @generated
	 */
	static final Color DF_FORE = new Color(null, 0, 0, 0);

}
