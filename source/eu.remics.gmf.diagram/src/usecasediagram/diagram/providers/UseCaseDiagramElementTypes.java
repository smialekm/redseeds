package usecasediagram.diagram.providers;

import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gmf.runtime.emf.type.core.ElementTypeRegistry;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;

import usecasediagram.UsecasediagramPackage;
import usecasediagram.diagram.edit.parts.ActorEditPart;
import usecasediagram.diagram.edit.parts.AssociationEditPart;
import usecasediagram.diagram.edit.parts.InvokeEditPart;
import usecasediagram.diagram.edit.parts.UseCaseDiagramEditPart;
import usecasediagram.diagram.edit.parts.UseCaseEditPart;
import usecasediagram.diagram.part.UseCaseDiagramDiagramEditorPlugin;

/**
 * @generated
 */
public class UseCaseDiagramElementTypes {

	/**
	 * @generated
	 */
	private UseCaseDiagramElementTypes() {
	}

	/**
	 * @generated
	 */
	private static Map<IElementType, ENamedElement> elements;

	/**
	 * @generated
	 */
	private static ImageRegistry imageRegistry;

	/**
	 * @generated
	 */
	private static Set<IElementType> KNOWN_ELEMENT_TYPES;

	/**
	 * @generated
	 */
	public static final IElementType UseCaseDiagram_1000 = getElementType("eu.remics.gmf.diagram.UseCaseDiagram_1000"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType Actor_2001 = getElementType("eu.remics.gmf.diagram.Actor_2001"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType UseCase_2002 = getElementType("eu.remics.gmf.diagram.UseCase_2002"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType Invoke_4001 = getElementType("eu.remics.gmf.diagram.Invoke_4001"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType Association_4002 = getElementType("eu.remics.gmf.diagram.Association_4002"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	private static ImageRegistry getImageRegistry() {
		if (imageRegistry == null) {
			imageRegistry = new ImageRegistry();
		}
		return imageRegistry;
	}

	/**
	 * @generated
	 */
	private static String getImageRegistryKey(ENamedElement element) {
		return element.getName();
	}

	/**
	 * @generated
	 */
	private static ImageDescriptor getProvidedImageDescriptor(
			ENamedElement element) {
		if (element instanceof EStructuralFeature) {
			EStructuralFeature feature = ((EStructuralFeature) element);
			EClass eContainingClass = feature.getEContainingClass();
			EClassifier eType = feature.getEType();
			if (eContainingClass != null && !eContainingClass.isAbstract()) {
				element = eContainingClass;
			} else if (eType instanceof EClass
					&& !((EClass) eType).isAbstract()) {
				element = eType;
			}
		}
		if (element instanceof EClass) {
			EClass eClass = (EClass) element;
			if (!eClass.isAbstract()) {
				return UseCaseDiagramDiagramEditorPlugin.getInstance()
						.getItemImageDescriptor(
								eClass.getEPackage().getEFactoryInstance()
										.create(eClass));
			}
		}
		// TODO : support structural features
		return null;
	}

	/**
	 * @generated
	 */
	public static ImageDescriptor getImageDescriptor(ENamedElement element) {
		String key = getImageRegistryKey(element);
		ImageDescriptor imageDescriptor = getImageRegistry().getDescriptor(key);
		if (imageDescriptor == null) {
			imageDescriptor = getProvidedImageDescriptor(element);
			if (imageDescriptor == null) {
				imageDescriptor = ImageDescriptor.getMissingImageDescriptor();
			}
			getImageRegistry().put(key, imageDescriptor);
		}
		return imageDescriptor;
	}

	/**
	 * @generated
	 */
	public static Image getImage(ENamedElement element) {
		String key = getImageRegistryKey(element);
		Image image = getImageRegistry().get(key);
		if (image == null) {
			ImageDescriptor imageDescriptor = getProvidedImageDescriptor(element);
			if (imageDescriptor == null) {
				imageDescriptor = ImageDescriptor.getMissingImageDescriptor();
			}
			getImageRegistry().put(key, imageDescriptor);
			image = getImageRegistry().get(key);
		}
		return image;
	}

	/**
	 * @generated
	 */
	public static ImageDescriptor getImageDescriptor(IAdaptable hint) {
		ENamedElement element = getElement(hint);
		if (element == null) {
			return null;
		}
		return getImageDescriptor(element);
	}

	/**
	 * @generated
	 */
	public static Image getImage(IAdaptable hint) {
		ENamedElement element = getElement(hint);
		if (element == null) {
			return null;
		}
		return getImage(element);
	}

	/**
	 * Returns 'type' of the ecore object associated with the hint.
	 * 
	 * @generated
	 */
	public static ENamedElement getElement(IAdaptable hint) {
		Object type = hint.getAdapter(IElementType.class);
		if (elements == null) {
			elements = new IdentityHashMap<IElementType, ENamedElement>();

			elements.put(UseCaseDiagram_1000,
					UsecasediagramPackage.eINSTANCE.getUseCaseDiagram());

			elements.put(Actor_2001, UsecasediagramPackage.eINSTANCE.getActor());

			elements.put(UseCase_2002,
					UsecasediagramPackage.eINSTANCE.getUseCase());

			elements.put(Invoke_4001,
					UsecasediagramPackage.eINSTANCE.getInvoke());

			elements.put(Association_4002,
					UsecasediagramPackage.eINSTANCE.getAssociation());
		}
		return (ENamedElement) elements.get(type);
	}

	/**
	 * @generated
	 */
	private static IElementType getElementType(String id) {
		return ElementTypeRegistry.getInstance().getType(id);
	}

	/**
	 * @generated
	 */
	public static boolean isKnownElementType(IElementType elementType) {
		if (KNOWN_ELEMENT_TYPES == null) {
			KNOWN_ELEMENT_TYPES = new HashSet<IElementType>();
			KNOWN_ELEMENT_TYPES.add(UseCaseDiagram_1000);
			KNOWN_ELEMENT_TYPES.add(Actor_2001);
			KNOWN_ELEMENT_TYPES.add(UseCase_2002);
			KNOWN_ELEMENT_TYPES.add(Invoke_4001);
			KNOWN_ELEMENT_TYPES.add(Association_4002);
		}
		return KNOWN_ELEMENT_TYPES.contains(elementType);
	}

	/**
	 * @generated
	 */
	public static IElementType getElementType(int visualID) {
		switch (visualID) {
		case UseCaseDiagramEditPart.VISUAL_ID:
			return UseCaseDiagram_1000;
		case ActorEditPart.VISUAL_ID:
			return Actor_2001;
		case UseCaseEditPart.VISUAL_ID:
			return UseCase_2002;
		case InvokeEditPart.VISUAL_ID:
			return Invoke_4001;
		case AssociationEditPart.VISUAL_ID:
			return Association_4002;
		}
		return null;
	}

}
