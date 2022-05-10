package notiondiagram.diagram.navigator;

import notiondiagram.AttributeRelation;
import notiondiagram.DirectedRelation;
import notiondiagram.Generalization;
import notiondiagram.IndirectRelation;
import notiondiagram.Notion;
import notiondiagram.NotionDiagram;
import notiondiagram.diagram.edit.parts.AttributeRelationEditPart;
import notiondiagram.diagram.edit.parts.DirectedRelationEditPart;
import notiondiagram.diagram.edit.parts.DirectedRelationSourceMultiplicityEditPart;
import notiondiagram.diagram.edit.parts.GeneralizationEditPart;
import notiondiagram.diagram.edit.parts.IndirectRelationEditPart;
import notiondiagram.diagram.edit.parts.IndirectRelationSourceMultiplicityEditPart;
import notiondiagram.diagram.edit.parts.NotionDiagramEditPart;
import notiondiagram.diagram.edit.parts.NotionEditPart;
import notiondiagram.diagram.edit.parts.NotionNameEditPart;
import notiondiagram.diagram.edit.parts.PhraseEditPart;
import notiondiagram.diagram.edit.parts.PhraseTextEditPart;
import notiondiagram.diagram.part.NotionDiagramDiagramEditorPlugin;
import notiondiagram.diagram.part.NotionDiagramVisualIDRegistry;
import notiondiagram.diagram.providers.NotionDiagramElementTypes;

import notiondiagram.diagram.providers.NotionDiagramParserProvider;
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

/**
 * @generated
 */
public class NotionDiagramNavigatorLabelProvider extends LabelProvider
		implements ICommonLabelProvider, ITreePathLabelProvider {

	/**
	 * @generated
	 */
	static {
		NotionDiagramDiagramEditorPlugin
				.getInstance()
				.getImageRegistry()
				.put("Navigator?UnknownElement", ImageDescriptor.getMissingImageDescriptor()); //$NON-NLS-1$
		NotionDiagramDiagramEditorPlugin
				.getInstance()
				.getImageRegistry()
				.put("Navigator?ImageNotFound", ImageDescriptor.getMissingImageDescriptor()); //$NON-NLS-1$
	}

	/**
	 * @generated
	 */
	public void updateLabel(ViewerLabel label, TreePath elementPath) {
		Object element = elementPath.getLastSegment();
		if (element instanceof NotionDiagramNavigatorItem
				&& !isOwnView(((NotionDiagramNavigatorItem) element).getView())) {
			return;
		}
		label.setText(getText(element));
		label.setImage(getImage(element));
	}

	/**
	 * @generated
	 */
	public Image getImage(Object element) {
		if (element instanceof NotionDiagramNavigatorGroup) {
			NotionDiagramNavigatorGroup group = (NotionDiagramNavigatorGroup) element;
			return NotionDiagramDiagramEditorPlugin.getInstance()
					.getBundledImage(group.getIcon());
		}

		if (element instanceof NotionDiagramNavigatorItem) {
			NotionDiagramNavigatorItem navigatorItem = (NotionDiagramNavigatorItem) element;
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
		switch (NotionDiagramVisualIDRegistry.getVisualID(view)) {
		case AttributeRelationEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Link?http://notiondiagram/1.0?AttributeRelation", NotionDiagramElementTypes.AttributeRelation_4002); //$NON-NLS-1$
		case IndirectRelationEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Link?http://notiondiagram/1.0?IndirectRelation", NotionDiagramElementTypes.IndirectRelation_4004); //$NON-NLS-1$
		case NotionDiagramEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Diagram?http://notiondiagram/1.0?NotionDiagram", NotionDiagramElementTypes.NotionDiagram_1000); //$NON-NLS-1$
		case GeneralizationEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Link?http://notiondiagram/1.0?Generalization", NotionDiagramElementTypes.Generalization_4001); //$NON-NLS-1$
		case PhraseEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Node?http://notiondiagram/1.0?Phrase", NotionDiagramElementTypes.Phrase_3001); //$NON-NLS-1$
		case NotionEditPart.VISUAL_ID:
			return getImage(
					"Navigator?TopLevelNode?http://notiondiagram/1.0?Notion", NotionDiagramElementTypes.Notion_2001); //$NON-NLS-1$
		case DirectedRelationEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Link?http://notiondiagram/1.0?DirectedRelation", NotionDiagramElementTypes.DirectedRelation_4003); //$NON-NLS-1$
		}
		return getImage("Navigator?UnknownElement", null); //$NON-NLS-1$
	}

	/**
	 * @generated
	 */
	private Image getImage(String key, IElementType elementType) {
		ImageRegistry imageRegistry = NotionDiagramDiagramEditorPlugin
				.getInstance().getImageRegistry();
		Image image = imageRegistry.get(key);
		if (image == null && elementType != null
				&& NotionDiagramElementTypes.isKnownElementType(elementType)) {
			image = NotionDiagramElementTypes.getImage(elementType);
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
		if (element instanceof NotionDiagramNavigatorGroup) {
			NotionDiagramNavigatorGroup group = (NotionDiagramNavigatorGroup) element;
			return group.getGroupName();
		}

		if (element instanceof NotionDiagramNavigatorItem) {
			NotionDiagramNavigatorItem navigatorItem = (NotionDiagramNavigatorItem) element;
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
		switch (NotionDiagramVisualIDRegistry.getVisualID(view)) {
		case AttributeRelationEditPart.VISUAL_ID:
			return getAttributeRelation_4002Text(view);
		case IndirectRelationEditPart.VISUAL_ID:
			return getIndirectRelation_4004Text(view);
		case NotionDiagramEditPart.VISUAL_ID:
			return getNotionDiagram_1000Text(view);
		case GeneralizationEditPart.VISUAL_ID:
			return getGeneralization_4001Text(view);
		case PhraseEditPart.VISUAL_ID:
			return getPhrase_3001Text(view);
		case NotionEditPart.VISUAL_ID:
			return getNotion_2001Text(view);
		case DirectedRelationEditPart.VISUAL_ID:
			return getDirectedRelation_4003Text(view);
		}
		return getUnknownElementText(view);
	}

	/**
	 * @generated
	 */
	private String getIndirectRelation_4004Text(View view) {
		IParser parser = NotionDiagramParserProvider
				.getParser(
						NotionDiagramElementTypes.IndirectRelation_4004,
						view.getElement() != null ? view.getElement() : view,
						NotionDiagramVisualIDRegistry
								.getType(IndirectRelationSourceMultiplicityEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			NotionDiagramDiagramEditorPlugin.getInstance().logError(
					"Parser was not found for label " + 6003); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getDirectedRelation_4003Text(View view) {
		IParser parser = NotionDiagramParserProvider
				.getParser(
						NotionDiagramElementTypes.DirectedRelation_4003,
						view.getElement() != null ? view.getElement() : view,
						NotionDiagramVisualIDRegistry
								.getType(DirectedRelationSourceMultiplicityEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			NotionDiagramDiagramEditorPlugin.getInstance().logError(
					"Parser was not found for label " + 6001); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getNotionDiagram_1000Text(View view) {
		NotionDiagram domainModelElement = (NotionDiagram) view.getElement();
		if (domainModelElement != null) {
			return domainModelElement.getName();
		} else {
			NotionDiagramDiagramEditorPlugin.getInstance().logError(
					"No domain element for view with visualID = " + 1000); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getAttributeRelation_4002Text(View view) {
		AttributeRelation domainModelElement = (AttributeRelation) view
				.getElement();
		if (domainModelElement != null) {
			return String.valueOf(domainModelElement.getSourceId());
		} else {
			NotionDiagramDiagramEditorPlugin.getInstance().logError(
					"No domain element for view with visualID = " + 4002); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getGeneralization_4001Text(View view) {
		Generalization domainModelElement = (Generalization) view.getElement();
		if (domainModelElement != null) {
			return String.valueOf(domainModelElement.getId());
		} else {
			NotionDiagramDiagramEditorPlugin.getInstance().logError(
					"No domain element for view with visualID = " + 4001); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getNotion_2001Text(View view) {
		IParser parser = NotionDiagramParserProvider.getParser(
				NotionDiagramElementTypes.Notion_2001,
				view.getElement() != null ? view.getElement() : view,
				NotionDiagramVisualIDRegistry
						.getType(NotionNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			NotionDiagramDiagramEditorPlugin.getInstance().logError(
					"Parser was not found for label " + 5001); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getPhrase_3001Text(View view) {
		IParser parser = NotionDiagramParserProvider.getParser(
				NotionDiagramElementTypes.Phrase_3001,
				view.getElement() != null ? view.getElement() : view,
				NotionDiagramVisualIDRegistry
						.getType(PhraseTextEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			NotionDiagramDiagramEditorPlugin.getInstance().logError(
					"Parser was not found for label " + 5002); //$NON-NLS-1$
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
		return NotionDiagramEditPart.MODEL_ID
				.equals(NotionDiagramVisualIDRegistry.getModelID(view));
	}

}
