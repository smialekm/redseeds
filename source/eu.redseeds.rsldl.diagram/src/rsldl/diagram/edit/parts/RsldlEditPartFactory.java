package rsldl.diagram.edit.parts;

import org.eclipse.draw2d.FigureUtilities;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.eclipse.gef.tools.CellEditorLocator;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITextAwareEditPart;
import org.eclipse.gmf.runtime.draw2d.ui.figures.WrappingLabel;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;

import rsldl.diagram.part.RsldlVisualIDRegistry;

/**
 * @generated
 */
public class RsldlEditPartFactory implements EditPartFactory {

	/**
	 * @generated
	 */
	public EditPart createEditPart(EditPart context, Object model) {
		if (model instanceof View) {
			View view = (View) model;
			switch (RsldlVisualIDRegistry.getVisualID(view)) {

			case DLDiagramEditPart.VISUAL_ID:
				return new DLDiagramEditPart(view);

			case DLEntityEditPart.VISUAL_ID:
				return new DLEntityEditPart(view);

			case DLEntityNameEditPart.VISUAL_ID:
				return new DLEntityNameEditPart(view);

			case DLEntityTypeEditPart.VISUAL_ID:
				return new DLEntityTypeEditPart(view);

			case DLPropertyEditPart.VISUAL_ID:
				return new DLPropertyEditPart(view);

			case DLPropertyNameEditPart.VISUAL_ID:
				return new DLPropertyNameEditPart(view);

			case DLPropertyTypeEditPart.VISUAL_ID:
				return new DLPropertyTypeEditPart(view);

			case DLPrimitiveEditPart.VISUAL_ID:
				return new DLPrimitiveEditPart(view);

			case DLPrimitiveNameEditPart.VISUAL_ID:
				return new DLPrimitiveNameEditPart(view);

			case WrappingLabelEditPart.VISUAL_ID:
				return new WrappingLabelEditPart(view);

			case DLDataBasedReferenceEditPart.VISUAL_ID:
				return new DLDataBasedReferenceEditPart(view);

			case DLDataBasedReferenceNameEditPart.VISUAL_ID:
				return new DLDataBasedReferenceNameEditPart(view);

			case DLDataBasedReferenceTypeEditPart.VISUAL_ID:
				return new DLDataBasedReferenceTypeEditPart(view);

			case DLPatternBasedReferenceEditPart.VISUAL_ID:
				return new DLPatternBasedReferenceEditPart(view);

			case DLPatternBasedReferenceNameEditPart.VISUAL_ID:
				return new DLPatternBasedReferenceNameEditPart(view);

			case DLPatternBasedReferenceTypeEditPart.VISUAL_ID:
				return new DLPatternBasedReferenceTypeEditPart(view);

			case DLAlghoritmicTransitionEditPart.VISUAL_ID:
				return new DLAlghoritmicTransitionEditPart(view);

			case DLAlghoritmicTransitionNameEditPart.VISUAL_ID:
				return new DLAlghoritmicTransitionNameEditPart(view);

			case DLAlghoritmicTransitionTypeEditPart.VISUAL_ID:
				return new DLAlghoritmicTransitionTypeEditPart(view);

			case DLPatternBasedTransitionEditPart.VISUAL_ID:
				return new DLPatternBasedTransitionEditPart(view);

			case DLPatternBasedTransitionNameEditPart.VISUAL_ID:
				return new DLPatternBasedTransitionNameEditPart(view);

			case DLPatternBasedTransitionTypeEditPart.VISUAL_ID:
				return new DLPatternBasedTransitionTypeEditPart(view);

			case DLIdentityConditionEditPart.VISUAL_ID:
				return new DLIdentityConditionEditPart(view);

			case DLIdentityConditionTypeEditPart.VISUAL_ID:
				return new DLIdentityConditionTypeEditPart(view);

			case DLIdentityConditionTextEditPart.VISUAL_ID:
				return new DLIdentityConditionTextEditPart(view);

			case DLInheritanceConditionEditPart.VISUAL_ID:
				return new DLInheritanceConditionEditPart(view);

			case DLInheritanceConditionTypeEditPart.VISUAL_ID:
				return new DLInheritanceConditionTypeEditPart(view);

			case DLInheritanceConditionTextEditPart.VISUAL_ID:
				return new DLInheritanceConditionTextEditPart(view);

			case DLValidityConditionEditPart.VISUAL_ID:
				return new DLValidityConditionEditPart(view);

			case DLValidityConditionTypeEditPart.VISUAL_ID:
				return new DLValidityConditionTypeEditPart(view);

			case DLValidityConditionTextEditPart.VISUAL_ID:
				return new DLValidityConditionTextEditPart(view);

			case DLIdentityCondition2EditPart.VISUAL_ID:
				return new DLIdentityCondition2EditPart(view);

			case DLIdentityConditionType2EditPart.VISUAL_ID:
				return new DLIdentityConditionType2EditPart(view);

			case DLIdentityConditionText2EditPart.VISUAL_ID:
				return new DLIdentityConditionText2EditPart(view);

			case DLInheritanceCondition2EditPart.VISUAL_ID:
				return new DLInheritanceCondition2EditPart(view);

			case DLInheritanceConditionType2EditPart.VISUAL_ID:
				return new DLInheritanceConditionType2EditPart(view);

			case DLInheritanceConditionText2EditPart.VISUAL_ID:
				return new DLInheritanceConditionText2EditPart(view);

			case DLValidityCondition2EditPart.VISUAL_ID:
				return new DLValidityCondition2EditPart(view);

			case DLValidityConditionType2EditPart.VISUAL_ID:
				return new DLValidityConditionType2EditPart(view);

			case DLValidityConditionText2EditPart.VISUAL_ID:
				return new DLValidityConditionText2EditPart(view);

			case DLEntityDLEntityCompartmentEditPart.VISUAL_ID:
				return new DLEntityDLEntityCompartmentEditPart(view);

			case DLPropertyDLPropertyCompartmentEditPart.VISUAL_ID:
				return new DLPropertyDLPropertyCompartmentEditPart(view);

			case DLRelationshipParticipationEditPart.VISUAL_ID:
				return new DLRelationshipParticipationEditPart(view);

			case DLRelationshipParticipationNameEditPart.VISUAL_ID:
				return new DLRelationshipParticipationNameEditPart(view);

			case DLRelationshipParticipationMultiplicityEditPart.VISUAL_ID:
				return new DLRelationshipParticipationMultiplicityEditPart(view);

			case DLRelationshipParticipation2EditPart.VISUAL_ID:
				return new DLRelationshipParticipation2EditPart(view);

			case DLRelationshipParticipationName2EditPart.VISUAL_ID:
				return new DLRelationshipParticipationName2EditPart(view);

			case DLRelationshipParticipationMultiplicity2EditPart.VISUAL_ID:
				return new DLRelationshipParticipationMultiplicity2EditPart(
						view);

			case DLRelationshipParticipation3EditPart.VISUAL_ID:
				return new DLRelationshipParticipation3EditPart(view);

			case DLRelationshipParticipationName3EditPart.VISUAL_ID:
				return new DLRelationshipParticipationName3EditPart(view);

			case DLRelationshipParticipationMultiplicity3EditPart.VISUAL_ID:
				return new DLRelationshipParticipationMultiplicity3EditPart(
						view);

			case DLAttributeLinkEditPart.VISUAL_ID:
				return new DLAttributeLinkEditPart(view);

			case DLAttributeLinkNameEditPart.VISUAL_ID:
				return new DLAttributeLinkNameEditPart(view);

			}
		}
		return createUnrecognizedEditPart(context, model);
	}

	/**
	 * @generated
	 */
	private EditPart createUnrecognizedEditPart(EditPart context, Object model) {
		// Handle creation of unrecognized child node EditParts here
		return null;
	}

	/**
	 * @generated
	 */
	public static CellEditorLocator getTextCellEditorLocator(
			ITextAwareEditPart source) {
		if (source.getFigure() instanceof WrappingLabel)
			return new TextCellEditorLocator((WrappingLabel) source.getFigure());
		else {
			return new LabelCellEditorLocator((Label) source.getFigure());
		}
	}

	/**
	 * @generated
	 */
	static private class TextCellEditorLocator implements CellEditorLocator {

		/**
		 * @generated
		 */
		private WrappingLabel wrapLabel;

		/**
		 * @generated
		 */
		public TextCellEditorLocator(WrappingLabel wrapLabel) {
			this.wrapLabel = wrapLabel;
		}

		/**
		 * @generated
		 */
		public WrappingLabel getWrapLabel() {
			return wrapLabel;
		}

		/**
		 * @generated
		 */
		public void relocate(CellEditor celleditor) {
			Text text = (Text) celleditor.getControl();
			Rectangle rect = getWrapLabel().getTextBounds().getCopy();
			getWrapLabel().translateToAbsolute(rect);
			if (!text.getFont().isDisposed()) {
				if (getWrapLabel().isTextWrapOn()
						&& getWrapLabel().getText().length() > 0) {
					rect.setSize(new Dimension(text.computeSize(rect.width,
							SWT.DEFAULT)));
				} else {
					int avr = FigureUtilities.getFontMetrics(text.getFont())
							.getAverageCharWidth();
					rect.setSize(new Dimension(text.computeSize(SWT.DEFAULT,
							SWT.DEFAULT)).expand(avr * 2, 0));
				}
			}
			if (!rect.equals(new Rectangle(text.getBounds()))) {
				text.setBounds(rect.x, rect.y, rect.width, rect.height);
			}
		}
	}

	/**
	 * @generated
	 */
	private static class LabelCellEditorLocator implements CellEditorLocator {

		/**
		 * @generated
		 */
		private Label label;

		/**
		 * @generated
		 */
		public LabelCellEditorLocator(Label label) {
			this.label = label;
		}

		/**
		 * @generated
		 */
		public Label getLabel() {
			return label;
		}

		/**
		 * @generated
		 */
		public void relocate(CellEditor celleditor) {
			Text text = (Text) celleditor.getControl();
			Rectangle rect = getLabel().getTextBounds().getCopy();
			getLabel().translateToAbsolute(rect);
			if (!text.getFont().isDisposed()) {
				int avr = FigureUtilities.getFontMetrics(text.getFont())
						.getAverageCharWidth();
				rect.setSize(new Dimension(text.computeSize(SWT.DEFAULT,
						SWT.DEFAULT)).expand(avr * 2, 0));
			}
			if (!rect.equals(new Rectangle(text.getBounds()))) {
				text.setBounds(rect.x, rect.y, rect.width, rect.height);
			}
		}
	}
}
