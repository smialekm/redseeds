package notiondiagram.diagram.providers;

import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Set;

import notiondiagram.NotiondiagramPackage;
import notiondiagram.diagram.edit.parts.AttributeRelationEditPart;
import notiondiagram.diagram.edit.parts.DirectedRelationEditPart;
import notiondiagram.diagram.edit.parts.GeneralizationEditPart;
import notiondiagram.diagram.edit.parts.IndirectRelationEditPart;
import notiondiagram.diagram.edit.parts.NotionDiagramEditPart;
import notiondiagram.diagram.edit.parts.NotionEditPart;
import notiondiagram.diagram.edit.parts.PhraseEditPart;
import notiondiagram.diagram.part.NotionDiagramDiagramEditorPlugin;

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

/**
 * @generated
 */
public class NotionDiagramElementTypes {

	/**
	 * @generated
	 */
	private NotionDiagramElementTypes() {
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
	public static final IElementType NotionDiagram_1000 = getElementType("eu.remics.gmf2.diagram.NotionDiagram_1000"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType Notion_2001 = getElementType("eu.remics.gmf2.diagram.Notion_2001"); //$NON-NLS-1$
	/**
	 * @generated NOT
	 */
	public static final IElementType Notion_2002 = getElementType("eu.remics.gmf2.diagram.Notion_2002"); //$NON-NLS-1$
	/**
	 * @generated NOT
	 */
	public static final IElementType Notion_2003 = getElementType("eu.remics.gmf2.diagram.Notion_2003"); //$NON-NLS-1$
	/**
	 * @generated NOT
	 */
	public static final IElementType Notion_2004 = getElementType("eu.remics.gmf2.diagram.Notion_2004"); //$NON-NLS-1$
	/**
	 * @generated NOT
	 */
	public static final IElementType Notion_2005 = getElementType("eu.remics.gmf2.diagram.Notion_2005"); //$NON-NLS-1$
	/**
	 * @generated NOT
	 */
	public static final IElementType Notion_2006 = getElementType("eu.remics.gmf2.diagram.Notion_2006"); //$NON-NLS-1$
	/**
	 * @generated NOT
	 */
	public static final IElementType Notion_2007 = getElementType("eu.remics.gmf2.diagram.Notion_2007"); //$NON-NLS-1$
	/**
	 * @generated NOT
	 */
	public static final IElementType Notion_2008 = getElementType("eu.remics.gmf2.diagram.Notion_2008"); //$NON-NLS-1$
	/**
	 * @generated NOT
	 */
	public static final IElementType Notion_2009 = getElementType("eu.remics.gmf2.diagram.Notion_2009"); //$NON-NLS-1$
	/**
	 * @generated NOT
	 */
	public static final IElementType Notion_2010 = getElementType("eu.remics.gmf2.diagram.Notion_2010"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType Phrase_3001 = getElementType("eu.remics.gmf2.diagram.Phrase_3001"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType Generalization_4001 = getElementType("eu.remics.gmf2.diagram.Generalization_4001"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType AttributeRelation_4002 = getElementType("eu.remics.gmf2.diagram.AttributeRelation_4002"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType DirectedRelation_4003 = getElementType("eu.remics.gmf2.diagram.DirectedRelation_4003"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	public static final IElementType IndirectRelation_4004 = getElementType("eu.remics.gmf2.diagram.IndirectRelation_4004"); //$NON-NLS-1$

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
				return NotionDiagramDiagramEditorPlugin.getInstance()
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

			elements.put(NotionDiagram_1000,
					NotiondiagramPackage.eINSTANCE.getNotionDiagram());

			elements.put(Notion_2001,
					NotiondiagramPackage.eINSTANCE.getNotion());

			elements.put(Phrase_3001,
					NotiondiagramPackage.eINSTANCE.getPhrase());

			elements.put(Generalization_4001,
					NotiondiagramPackage.eINSTANCE.getGeneralization());

			elements.put(AttributeRelation_4002,
					NotiondiagramPackage.eINSTANCE.getAttributeRelation());

			elements.put(DirectedRelation_4003,
					NotiondiagramPackage.eINSTANCE.getDirectedRelation());

			elements.put(IndirectRelation_4004,
					NotiondiagramPackage.eINSTANCE.getIndirectRelation());
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
	 * @generated NOT
	 */
	public static boolean isKnownElementType(IElementType elementType) {
		if (KNOWN_ELEMENT_TYPES == null) {
			KNOWN_ELEMENT_TYPES = new HashSet<IElementType>();
			KNOWN_ELEMENT_TYPES.add(NotionDiagram_1000);
			KNOWN_ELEMENT_TYPES.add(Notion_2001);
			KNOWN_ELEMENT_TYPES.add(Notion_2002);
			KNOWN_ELEMENT_TYPES.add(Notion_2003);
			KNOWN_ELEMENT_TYPES.add(Notion_2004);
			KNOWN_ELEMENT_TYPES.add(Notion_2005);
			KNOWN_ELEMENT_TYPES.add(Notion_2006);
			KNOWN_ELEMENT_TYPES.add(Notion_2007);
			KNOWN_ELEMENT_TYPES.add(Notion_2008);
			KNOWN_ELEMENT_TYPES.add(Notion_2009);
			KNOWN_ELEMENT_TYPES.add(Notion_2010);
			KNOWN_ELEMENT_TYPES.add(Phrase_3001);
			KNOWN_ELEMENT_TYPES.add(Generalization_4001);
			KNOWN_ELEMENT_TYPES.add(AttributeRelation_4002);
			KNOWN_ELEMENT_TYPES.add(DirectedRelation_4003);
			KNOWN_ELEMENT_TYPES.add(IndirectRelation_4004);
		}
		return KNOWN_ELEMENT_TYPES.contains(elementType);
	}

	/**
	 * @generated
	 */
	public static IElementType getElementType(int visualID) {
		switch (visualID) {
		case NotionDiagramEditPart.VISUAL_ID:
			return NotionDiagram_1000;
		case NotionEditPart.VISUAL_ID:
			return Notion_2001;
		case PhraseEditPart.VISUAL_ID:
			return Phrase_3001;
		case GeneralizationEditPart.VISUAL_ID:
			return Generalization_4001;
		case AttributeRelationEditPart.VISUAL_ID:
			return AttributeRelation_4002;
		case DirectedRelationEditPart.VISUAL_ID:
			return DirectedRelation_4003;
		case IndirectRelationEditPart.VISUAL_ID:
			return IndirectRelation_4004;
		}
		return null;
	}

}
