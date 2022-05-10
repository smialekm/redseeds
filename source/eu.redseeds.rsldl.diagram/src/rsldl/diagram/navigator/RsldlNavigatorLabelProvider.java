package rsldl.diagram.navigator;

import org.eclipse.gmf.runtime.common.ui.services.parser.IParser;
import org.eclipse.gmf.runtime.common.ui.services.parser.ParserOptions;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.viewers.ITreePathLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.ViewerLabel;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.navigator.ICommonContentExtensionSite;
import org.eclipse.ui.navigator.ICommonLabelProvider;

import rsldl.DLDiagram;
import rsldl.diagram.edit.parts.DLAlghoritmicTransitionEditPart;
import rsldl.diagram.edit.parts.DLAlghoritmicTransitionNameEditPart;
import rsldl.diagram.edit.parts.DLAttributeLinkEditPart;
import rsldl.diagram.edit.parts.DLAttributeLinkNameEditPart;
import rsldl.diagram.edit.parts.DLDataBasedReferenceEditPart;
import rsldl.diagram.edit.parts.DLDataBasedReferenceNameEditPart;
import rsldl.diagram.edit.parts.DLDiagramEditPart;
import rsldl.diagram.edit.parts.DLEntityEditPart;
import rsldl.diagram.edit.parts.DLEntityNameEditPart;
import rsldl.diagram.edit.parts.DLIdentityCondition2EditPart;
import rsldl.diagram.edit.parts.DLIdentityConditionEditPart;
import rsldl.diagram.edit.parts.DLIdentityConditionType2EditPart;
import rsldl.diagram.edit.parts.DLIdentityConditionTypeEditPart;
import rsldl.diagram.edit.parts.DLInheritanceCondition2EditPart;
import rsldl.diagram.edit.parts.DLInheritanceConditionEditPart;
import rsldl.diagram.edit.parts.DLInheritanceConditionType2EditPart;
import rsldl.diagram.edit.parts.DLInheritanceConditionTypeEditPart;
import rsldl.diagram.edit.parts.DLPatternBasedReferenceEditPart;
import rsldl.diagram.edit.parts.DLPatternBasedReferenceNameEditPart;
import rsldl.diagram.edit.parts.DLPatternBasedTransitionEditPart;
import rsldl.diagram.edit.parts.DLPatternBasedTransitionNameEditPart;
import rsldl.diagram.edit.parts.DLPrimitiveEditPart;
import rsldl.diagram.edit.parts.DLPrimitiveNameEditPart;
import rsldl.diagram.edit.parts.DLPropertyEditPart;
import rsldl.diagram.edit.parts.DLPropertyNameEditPart;
import rsldl.diagram.edit.parts.DLRelationshipParticipation2EditPart;
import rsldl.diagram.edit.parts.DLRelationshipParticipation3EditPart;
import rsldl.diagram.edit.parts.DLRelationshipParticipationEditPart;
import rsldl.diagram.edit.parts.DLRelationshipParticipationName2EditPart;
import rsldl.diagram.edit.parts.DLRelationshipParticipationName3EditPart;
import rsldl.diagram.edit.parts.DLRelationshipParticipationNameEditPart;
import rsldl.diagram.edit.parts.DLValidityCondition2EditPart;
import rsldl.diagram.edit.parts.DLValidityConditionEditPart;
import rsldl.diagram.edit.parts.DLValidityConditionType2EditPart;
import rsldl.diagram.edit.parts.DLValidityConditionTypeEditPart;
import rsldl.diagram.part.RsldlDiagramEditorPlugin;
import rsldl.diagram.part.RsldlVisualIDRegistry;
import rsldl.diagram.providers.RsldlElementTypes;
import rsldl.diagram.providers.RsldlParserProvider;

/**
 * @generated
 */
public class RsldlNavigatorLabelProvider extends LabelProvider implements
		ICommonLabelProvider, ITreePathLabelProvider {

	/**
	 * @generated
	 */
	static {
		RsldlDiagramEditorPlugin
				.getInstance()
				.getImageRegistry()
				.put("Navigator?UnknownElement", ImageDescriptor.getMissingImageDescriptor()); //$NON-NLS-1$
		RsldlDiagramEditorPlugin
				.getInstance()
				.getImageRegistry()
				.put("Navigator?ImageNotFound", ImageDescriptor.getMissingImageDescriptor()); //$NON-NLS-1$
	}

	/**
	 * @generated
	 */
	public void updateLabel(ViewerLabel label, TreePath elementPath) {
		Object element = elementPath.getLastSegment();
		if (element instanceof RsldlNavigatorItem
				&& !isOwnView(((RsldlNavigatorItem) element).getView())) {
			return;
		}
		label.setText(getText(element));
		label.setImage(getImage(element));
	}

	/**
	 * @generated
	 */
	public Image getImage(Object element) {
		if (element instanceof RsldlNavigatorGroup) {
			RsldlNavigatorGroup group = (RsldlNavigatorGroup) element;
			return RsldlDiagramEditorPlugin.getInstance().getBundledImage(
					group.getIcon());
		}

		if (element instanceof RsldlNavigatorItem) {
			RsldlNavigatorItem navigatorItem = (RsldlNavigatorItem) element;
			if (!isOwnView(navigatorItem.getView())) {
				return super.getImage(element);
			}
			return getImage(navigatorItem.getView());
		}

		return super.getImage(element);
	}

	/**
	 * @generated
	 */
	public Image getImage(View view) {
		switch (RsldlVisualIDRegistry.getVisualID(view)) {
		case DLValidityCondition2EditPart.VISUAL_ID:
			return getImage(
					"Navigator?Node?http://rsldl/1.0?DLValidityCondition", RsldlElementTypes.DLValidityCondition_3006); //$NON-NLS-1$
		case DLRelationshipParticipation3EditPart.VISUAL_ID:
			return getImage(
					"Navigator?Link?http://rsldl/1.0?DLRelationshipParticipation", RsldlElementTypes.DLRelationshipParticipation_4003); //$NON-NLS-1$
		case DLAttributeLinkEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Link?http://rsldl/1.0?DLAttributeLink", RsldlElementTypes.DLAttributeLink_4004); //$NON-NLS-1$
		case DLDataBasedReferenceEditPart.VISUAL_ID:
			return getImage(
					"Navigator?TopLevelNode?http://rsldl/1.0?DLDataBasedReference", RsldlElementTypes.DLDataBasedReference_2004); //$NON-NLS-1$
		case DLValidityConditionEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Node?http://rsldl/1.0?DLValidityCondition", RsldlElementTypes.DLValidityCondition_3003); //$NON-NLS-1$
		case DLDiagramEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Diagram?http://rsldl/1.0?DLDiagram", RsldlElementTypes.DLDiagram_1000); //$NON-NLS-1$
		case DLIdentityConditionEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Node?http://rsldl/1.0?DLIdentityCondition", RsldlElementTypes.DLIdentityCondition_3001); //$NON-NLS-1$
		case DLRelationshipParticipation2EditPart.VISUAL_ID:
			return getImage(
					"Navigator?Link?http://rsldl/1.0?DLRelationshipParticipation", RsldlElementTypes.DLRelationshipParticipation_4002); //$NON-NLS-1$
		case DLInheritanceCondition2EditPart.VISUAL_ID:
			return getImage(
					"Navigator?Node?http://rsldl/1.0?DLInheritanceCondition", RsldlElementTypes.DLInheritanceCondition_3005); //$NON-NLS-1$
		case DLPrimitiveEditPart.VISUAL_ID:
			return getImage(
					"Navigator?TopLevelNode?http://rsldl/1.0?DLPrimitive", RsldlElementTypes.DLPrimitive_2003); //$NON-NLS-1$
		case DLPatternBasedTransitionEditPart.VISUAL_ID:
			return getImage(
					"Navigator?TopLevelNode?http://rsldl/1.0?DLPatternBasedTransition", RsldlElementTypes.DLPatternBasedTransition_2007); //$NON-NLS-1$
		case DLPatternBasedReferenceEditPart.VISUAL_ID:
			return getImage(
					"Navigator?TopLevelNode?http://rsldl/1.0?DLPatternBasedReference", RsldlElementTypes.DLPatternBasedReference_2005); //$NON-NLS-1$
		case DLEntityEditPart.VISUAL_ID:
			return getImage(
					"Navigator?TopLevelNode?http://rsldl/1.0?DLEntity", RsldlElementTypes.DLEntity_2001); //$NON-NLS-1$
		case DLAlghoritmicTransitionEditPart.VISUAL_ID:
			return getImage(
					"Navigator?TopLevelNode?http://rsldl/1.0?DLAlghoritmicTransition", RsldlElementTypes.DLAlghoritmicTransition_2006); //$NON-NLS-1$
		case DLPropertyEditPart.VISUAL_ID:
			return getImage(
					"Navigator?TopLevelNode?http://rsldl/1.0?DLProperty", RsldlElementTypes.DLProperty_2002); //$NON-NLS-1$
		case DLInheritanceConditionEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Node?http://rsldl/1.0?DLInheritanceCondition", RsldlElementTypes.DLInheritanceCondition_3002); //$NON-NLS-1$
		case DLRelationshipParticipationEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Link?http://rsldl/1.0?DLRelationshipParticipation", RsldlElementTypes.DLRelationshipParticipation_4001); //$NON-NLS-1$
		case DLIdentityCondition2EditPart.VISUAL_ID:
			return getImage(
					"Navigator?Node?http://rsldl/1.0?DLIdentityCondition", RsldlElementTypes.DLIdentityCondition_3004); //$NON-NLS-1$
		}
		return getImage("Navigator?UnknownElement", null); //$NON-NLS-1$
	}

	/**
	 * @generated
	 */
	private Image getImage(String key, IElementType elementType) {
		ImageRegistry imageRegistry = RsldlDiagramEditorPlugin.getInstance()
				.getImageRegistry();
		Image image = imageRegistry.get(key);
		if (image == null && elementType != null
				&& RsldlElementTypes.isKnownElementType(elementType)) {
			image = RsldlElementTypes.getImage(elementType);
			imageRegistry.put(key, image);
		}

		if (image == null) {
			image = imageRegistry.get("Navigator?ImageNotFound"); //$NON-NLS-1$
			imageRegistry.put(key, image);
		}
		return image;
	}

	/**
	 * @generated
	 */
	public String getText(Object element) {
		if (element instanceof RsldlNavigatorGroup) {
			RsldlNavigatorGroup group = (RsldlNavigatorGroup) element;
			return group.getGroupName();
		}

		if (element instanceof RsldlNavigatorItem) {
			RsldlNavigatorItem navigatorItem = (RsldlNavigatorItem) element;
			if (!isOwnView(navigatorItem.getView())) {
				return null;
			}
			return getText(navigatorItem.getView());
		}

		return super.getText(element);
	}

	/**
	 * @generated
	 */
	public String getText(View view) {
		if (view.getElement() != null && view.getElement().eIsProxy()) {
			return getUnresolvedDomainElementProxyText(view);
		}
		switch (RsldlVisualIDRegistry.getVisualID(view)) {
		case DLValidityCondition2EditPart.VISUAL_ID:
			return getDLValidityCondition_3006Text(view);
		case DLRelationshipParticipation3EditPart.VISUAL_ID:
			return getDLRelationshipParticipation_4003Text(view);
		case DLAttributeLinkEditPart.VISUAL_ID:
			return getDLAttributeLink_4004Text(view);
		case DLDataBasedReferenceEditPart.VISUAL_ID:
			return getDLDataBasedReference_2004Text(view);
		case DLValidityConditionEditPart.VISUAL_ID:
			return getDLValidityCondition_3003Text(view);
		case DLDiagramEditPart.VISUAL_ID:
			return getDLDiagram_1000Text(view);
		case DLIdentityConditionEditPart.VISUAL_ID:
			return getDLIdentityCondition_3001Text(view);
		case DLRelationshipParticipation2EditPart.VISUAL_ID:
			return getDLRelationshipParticipation_4002Text(view);
		case DLInheritanceCondition2EditPart.VISUAL_ID:
			return getDLInheritanceCondition_3005Text(view);
		case DLPrimitiveEditPart.VISUAL_ID:
			return getDLPrimitive_2003Text(view);
		case DLPatternBasedTransitionEditPart.VISUAL_ID:
			return getDLPatternBasedTransition_2007Text(view);
		case DLPatternBasedReferenceEditPart.VISUAL_ID:
			return getDLPatternBasedReference_2005Text(view);
		case DLEntityEditPart.VISUAL_ID:
			return getDLEntity_2001Text(view);
		case DLAlghoritmicTransitionEditPart.VISUAL_ID:
			return getDLAlghoritmicTransition_2006Text(view);
		case DLPropertyEditPart.VISUAL_ID:
			return getDLProperty_2002Text(view);
		case DLInheritanceConditionEditPart.VISUAL_ID:
			return getDLInheritanceCondition_3002Text(view);
		case DLRelationshipParticipationEditPart.VISUAL_ID:
			return getDLRelationshipParticipation_4001Text(view);
		case DLIdentityCondition2EditPart.VISUAL_ID:
			return getDLIdentityCondition_3004Text(view);
		}
		return getUnknownElementText(view);
	}

	/**
	 * @generated
	 */
	private String getDLDiagram_1000Text(View view) {
		DLDiagram domainModelElement = (DLDiagram) view.getElement();
		if (domainModelElement != null) {
			return domainModelElement.getName();
		} else {
			RsldlDiagramEditorPlugin.getInstance().logError(
					"No domain element for view with visualID = " + 1000); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getDLPatternBasedTransition_2007Text(View view) {
		IParser parser = RsldlParserProvider
				.getParser(
						RsldlElementTypes.DLPatternBasedTransition_2007,
						view.getElement() != null ? view.getElement() : view,
						RsldlVisualIDRegistry
								.getType(DLPatternBasedTransitionNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			RsldlDiagramEditorPlugin.getInstance().logError(
					"Parser was not found for label " + 5025); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getDLInheritanceCondition_3002Text(View view) {
		IParser parser = RsldlParserProvider.getParser(
				RsldlElementTypes.DLInheritanceCondition_3002, view
						.getElement() != null ? view.getElement() : view,
				RsldlVisualIDRegistry
						.getType(DLInheritanceConditionTypeEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			RsldlDiagramEditorPlugin.getInstance().logError(
					"Parser was not found for label " + 5003); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getDLProperty_2002Text(View view) {
		IParser parser = RsldlParserProvider
				.getParser(RsldlElementTypes.DLProperty_2002,
						view.getElement() != null ? view.getElement() : view,
						RsldlVisualIDRegistry
								.getType(DLPropertyNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			RsldlDiagramEditorPlugin.getInstance().logError(
					"Parser was not found for label " + 5015); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getDLIdentityCondition_3001Text(View view) {
		IParser parser = RsldlParserProvider.getParser(
				RsldlElementTypes.DLIdentityCondition_3001,
				view.getElement() != null ? view.getElement() : view,
				RsldlVisualIDRegistry
						.getType(DLIdentityConditionTypeEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			RsldlDiagramEditorPlugin.getInstance().logError(
					"Parser was not found for label " + 5001); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getDLValidityCondition_3006Text(View view) {
		IParser parser = RsldlParserProvider.getParser(
				RsldlElementTypes.DLValidityCondition_3006,
				view.getElement() != null ? view.getElement() : view,
				RsldlVisualIDRegistry
						.getType(DLValidityConditionType2EditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			RsldlDiagramEditorPlugin.getInstance().logError(
					"Parser was not found for label " + 5013); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getDLDataBasedReference_2004Text(View view) {
		IParser parser = RsldlParserProvider.getParser(
				RsldlElementTypes.DLDataBasedReference_2004,
				view.getElement() != null ? view.getElement() : view,
				RsldlVisualIDRegistry
						.getType(DLDataBasedReferenceNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			RsldlDiagramEditorPlugin.getInstance().logError(
					"Parser was not found for label " + 5019); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getDLPatternBasedReference_2005Text(View view) {
		IParser parser = RsldlParserProvider
				.getParser(
						RsldlElementTypes.DLPatternBasedReference_2005,
						view.getElement() != null ? view.getElement() : view,
						RsldlVisualIDRegistry
								.getType(DLPatternBasedReferenceNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			RsldlDiagramEditorPlugin.getInstance().logError(
					"Parser was not found for label " + 5021); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getDLPrimitive_2003Text(View view) {
		IParser parser = RsldlParserProvider.getParser(
				RsldlElementTypes.DLPrimitive_2003,
				view.getElement() != null ? view.getElement() : view,
				RsldlVisualIDRegistry
						.getType(DLPrimitiveNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			RsldlDiagramEditorPlugin.getInstance().logError(
					"Parser was not found for label " + 5017); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getDLIdentityCondition_3004Text(View view) {
		IParser parser = RsldlParserProvider.getParser(
				RsldlElementTypes.DLIdentityCondition_3004,
				view.getElement() != null ? view.getElement() : view,
				RsldlVisualIDRegistry
						.getType(DLIdentityConditionType2EditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			RsldlDiagramEditorPlugin.getInstance().logError(
					"Parser was not found for label " + 5009); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getDLEntity_2001Text(View view) {
		IParser parser = RsldlParserProvider.getParser(
				RsldlElementTypes.DLEntity_2001,
				view.getElement() != null ? view.getElement() : view,
				RsldlVisualIDRegistry.getType(DLEntityNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			RsldlDiagramEditorPlugin.getInstance().logError(
					"Parser was not found for label " + 5007); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getDLRelationshipParticipation_4003Text(View view) {
		IParser parser = RsldlParserProvider
				.getParser(
						RsldlElementTypes.DLRelationshipParticipation_4003,
						view.getElement() != null ? view.getElement() : view,
						RsldlVisualIDRegistry
								.getType(DLRelationshipParticipationName3EditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			RsldlDiagramEditorPlugin.getInstance().logError(
					"Parser was not found for label " + 6005); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getDLRelationshipParticipation_4001Text(View view) {
		IParser parser = RsldlParserProvider
				.getParser(
						RsldlElementTypes.DLRelationshipParticipation_4001,
						view.getElement() != null ? view.getElement() : view,
						RsldlVisualIDRegistry
								.getType(DLRelationshipParticipationNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			RsldlDiagramEditorPlugin.getInstance().logError(
					"Parser was not found for label " + 6001); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getDLValidityCondition_3003Text(View view) {
		IParser parser = RsldlParserProvider.getParser(
				RsldlElementTypes.DLValidityCondition_3003,
				view.getElement() != null ? view.getElement() : view,
				RsldlVisualIDRegistry
						.getType(DLValidityConditionTypeEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			RsldlDiagramEditorPlugin.getInstance().logError(
					"Parser was not found for label " + 5005); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getDLInheritanceCondition_3005Text(View view) {
		IParser parser = RsldlParserProvider
				.getParser(
						RsldlElementTypes.DLInheritanceCondition_3005,
						view.getElement() != null ? view.getElement() : view,
						RsldlVisualIDRegistry
								.getType(DLInheritanceConditionType2EditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			RsldlDiagramEditorPlugin.getInstance().logError(
					"Parser was not found for label " + 5011); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getDLRelationshipParticipation_4002Text(View view) {
		IParser parser = RsldlParserProvider
				.getParser(
						RsldlElementTypes.DLRelationshipParticipation_4002,
						view.getElement() != null ? view.getElement() : view,
						RsldlVisualIDRegistry
								.getType(DLRelationshipParticipationName2EditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			RsldlDiagramEditorPlugin.getInstance().logError(
					"Parser was not found for label " + 6003); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getDLAlghoritmicTransition_2006Text(View view) {
		IParser parser = RsldlParserProvider
				.getParser(
						RsldlElementTypes.DLAlghoritmicTransition_2006,
						view.getElement() != null ? view.getElement() : view,
						RsldlVisualIDRegistry
								.getType(DLAlghoritmicTransitionNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			RsldlDiagramEditorPlugin.getInstance().logError(
					"Parser was not found for label " + 5023); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getDLAttributeLink_4004Text(View view) {
		IParser parser = RsldlParserProvider.getParser(
				RsldlElementTypes.DLAttributeLink_4004,
				view.getElement() != null ? view.getElement() : view,
				RsldlVisualIDRegistry
						.getType(DLAttributeLinkNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			RsldlDiagramEditorPlugin.getInstance().logError(
					"Parser was not found for label " + 6007); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getUnknownElementText(View view) {
		return "<UnknownElement Visual_ID = " + view.getType() + ">"; //$NON-NLS-1$  //$NON-NLS-2$
	}

	/**
	 * @generated
	 */
	private String getUnresolvedDomainElementProxyText(View view) {
		return "<Unresolved domain element Visual_ID = " + view.getType() + ">"; //$NON-NLS-1$  //$NON-NLS-2$
	}

	/**
	 * @generated
	 */
	public void init(ICommonContentExtensionSite aConfig) {
	}

	/**
	 * @generated
	 */
	public void restoreState(IMemento aMemento) {
	}

	/**
	 * @generated
	 */
	public void saveState(IMemento aMemento) {
	}

	/**
	 * @generated
	 */
	public String getDescription(Object anElement) {
		return null;
	}

	/**
	 * @generated
	 */
	private boolean isOwnView(View view) {
		return DLDiagramEditPart.MODEL_ID.equals(RsldlVisualIDRegistry
				.getModelID(view));
	}

}
