package usecasediagram.diagram.navigator;

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

import usecasediagram.Association;
import usecasediagram.Invoke;
import usecasediagram.UseCaseDiagram;
import usecasediagram.diagram.edit.parts.ActorEditPart;
import usecasediagram.diagram.edit.parts.ActorNameEditPart;
import usecasediagram.diagram.edit.parts.AssociationEditPart;
import usecasediagram.diagram.edit.parts.InvokeEditPart;
import usecasediagram.diagram.edit.parts.UseCaseDiagramEditPart;
import usecasediagram.diagram.edit.parts.UseCaseEditPart;
import usecasediagram.diagram.edit.parts.UseCaseNameEditPart;
import usecasediagram.diagram.part.UseCaseDiagramDiagramEditorPlugin;
import usecasediagram.diagram.part.UseCaseDiagramVisualIDRegistry;
import usecasediagram.diagram.providers.UseCaseDiagramElementTypes;
import usecasediagram.diagram.providers.UseCaseDiagramParserProvider;

/**
 * @generated
 */
public class UseCaseDiagramNavigatorLabelProvider extends LabelProvider
		implements ICommonLabelProvider, ITreePathLabelProvider {

	/**
	 * @generated
	 */
	static {
		UseCaseDiagramDiagramEditorPlugin
				.getInstance()
				.getImageRegistry()
				.put("Navigator?UnknownElement", ImageDescriptor.getMissingImageDescriptor()); //$NON-NLS-1$
		UseCaseDiagramDiagramEditorPlugin
				.getInstance()
				.getImageRegistry()
				.put("Navigator?ImageNotFound", ImageDescriptor.getMissingImageDescriptor()); //$NON-NLS-1$
	}

	/**
	 * @generated
	 */
	public void updateLabel(ViewerLabel label, TreePath elementPath) {
		Object element = elementPath.getLastSegment();
		if (element instanceof UseCaseDiagramNavigatorItem
				&& !isOwnView(((UseCaseDiagramNavigatorItem) element).getView())) {
			return;
		}
		label.setText(getText(element));
		label.setImage(getImage(element));
	}

	/**
	 * @generated
	 */
	public Image getImage(Object element) {
		if (element instanceof UseCaseDiagramNavigatorGroup) {
			UseCaseDiagramNavigatorGroup group = (UseCaseDiagramNavigatorGroup) element;
			return UseCaseDiagramDiagramEditorPlugin.getInstance()
					.getBundledImage(group.getIcon());
		}

		if (element instanceof UseCaseDiagramNavigatorItem) {
			UseCaseDiagramNavigatorItem navigatorItem = (UseCaseDiagramNavigatorItem) element;
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
		switch (UseCaseDiagramVisualIDRegistry.getVisualID(view)) {
		case ActorEditPart.VISUAL_ID:
			return getImage(
					"Navigator?TopLevelNode?http://usecasediagram/1.0?Actor", UseCaseDiagramElementTypes.Actor_2001); //$NON-NLS-1$
		case InvokeEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Link?http://usecasediagram/1.0?Invoke", UseCaseDiagramElementTypes.Invoke_4001); //$NON-NLS-1$
		case UseCaseDiagramEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Diagram?http://usecasediagram/1.0?UseCaseDiagram", UseCaseDiagramElementTypes.UseCaseDiagram_1000); //$NON-NLS-1$
		case UseCaseEditPart.VISUAL_ID:
			return getImage(
					"Navigator?TopLevelNode?http://usecasediagram/1.0?UseCase", UseCaseDiagramElementTypes.UseCase_2002); //$NON-NLS-1$
		case AssociationEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Link?http://usecasediagram/1.0?Association", UseCaseDiagramElementTypes.Association_4002); //$NON-NLS-1$
		}
		return getImage("Navigator?UnknownElement", null); //$NON-NLS-1$
	}

	/**
	 * @generated
	 */
	private Image getImage(String key, IElementType elementType) {
		ImageRegistry imageRegistry = UseCaseDiagramDiagramEditorPlugin
				.getInstance().getImageRegistry();
		Image image = imageRegistry.get(key);
		if (image == null && elementType != null
				&& UseCaseDiagramElementTypes.isKnownElementType(elementType)) {
			image = UseCaseDiagramElementTypes.getImage(elementType);
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
		if (element instanceof UseCaseDiagramNavigatorGroup) {
			UseCaseDiagramNavigatorGroup group = (UseCaseDiagramNavigatorGroup) element;
			return group.getGroupName();
		}

		if (element instanceof UseCaseDiagramNavigatorItem) {
			UseCaseDiagramNavigatorItem navigatorItem = (UseCaseDiagramNavigatorItem) element;
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
		switch (UseCaseDiagramVisualIDRegistry.getVisualID(view)) {
		case ActorEditPart.VISUAL_ID:
			return getActor_2001Text(view);
		case InvokeEditPart.VISUAL_ID:
			return getInvoke_4001Text(view);
		case UseCaseDiagramEditPart.VISUAL_ID:
			return getUseCaseDiagram_1000Text(view);
		case UseCaseEditPart.VISUAL_ID:
			return getUseCase_2002Text(view);
		case AssociationEditPart.VISUAL_ID:
			return getAssociation_4002Text(view);
		}
		return getUnknownElementText(view);
	}

	/**
	 * @generated
	 */
	private String getInvoke_4001Text(View view) {
		Invoke domainModelElement = (Invoke) view.getElement();
		if (domainModelElement != null) {
			return String.valueOf(domainModelElement.getId());
		} else {
			UseCaseDiagramDiagramEditorPlugin.getInstance().logError(
					"No domain element for view with visualID = " + 4001); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getActor_2001Text(View view) {
		IParser parser = UseCaseDiagramParserProvider.getParser(
				UseCaseDiagramElementTypes.Actor_2001,
				view.getElement() != null ? view.getElement() : view,
				UseCaseDiagramVisualIDRegistry
						.getType(ActorNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			UseCaseDiagramDiagramEditorPlugin.getInstance().logError(
					"Parser was not found for label " + 5001); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getAssociation_4002Text(View view) {
		Association domainModelElement = (Association) view.getElement();
		if (domainModelElement != null) {
			return String.valueOf(domainModelElement.getId());
		} else {
			UseCaseDiagramDiagramEditorPlugin.getInstance().logError(
					"No domain element for view with visualID = " + 4002); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getUseCaseDiagram_1000Text(View view) {
		UseCaseDiagram domainModelElement = (UseCaseDiagram) view.getElement();
		if (domainModelElement != null) {
			return domainModelElement.getName();
		} else {
			UseCaseDiagramDiagramEditorPlugin.getInstance().logError(
					"No domain element for view with visualID = " + 1000); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * @generated
	 */
	private String getUseCase_2002Text(View view) {
		IParser parser = UseCaseDiagramParserProvider.getParser(
				UseCaseDiagramElementTypes.UseCase_2002,
				view.getElement() != null ? view.getElement() : view,
				UseCaseDiagramVisualIDRegistry
						.getType(UseCaseNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(
					view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			UseCaseDiagramDiagramEditorPlugin.getInstance().logError(
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
		return UseCaseDiagramEditPart.MODEL_ID
				.equals(UseCaseDiagramVisualIDRegistry.getModelID(view));
	}

}
