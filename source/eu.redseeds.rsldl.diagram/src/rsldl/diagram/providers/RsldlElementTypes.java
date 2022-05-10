package rsldl.diagram.providers;

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

import rsldl.RsldlPackage;
import rsldl.diagram.edit.parts.DLAlghoritmicTransitionEditPart;
import rsldl.diagram.edit.parts.DLAttributeLinkEditPart;
import rsldl.diagram.edit.parts.DLDataBasedReferenceEditPart;
import rsldl.diagram.edit.parts.DLDiagramEditPart;
import rsldl.diagram.edit.parts.DLEntityEditPart;
import rsldl.diagram.edit.parts.DLIdentityCondition2EditPart;
import rsldl.diagram.edit.parts.DLIdentityConditionEditPart;
import rsldl.diagram.edit.parts.DLInheritanceCondition2EditPart;
import rsldl.diagram.edit.parts.DLInheritanceConditionEditPart;
import rsldl.diagram.edit.parts.DLPatternBasedReferenceEditPart;
import rsldl.diagram.edit.parts.DLPatternBasedTransitionEditPart;
import rsldl.diagram.edit.parts.DLPrimitiveEditPart;
import rsldl.diagram.edit.parts.DLPropertyEditPart;
import rsldl.diagram.edit.parts.DLRelationshipParticipation2EditPart;
import rsldl.diagram.edit.parts.DLRelationshipParticipation3EditPart;
import rsldl.diagram.edit.parts.DLRelationshipParticipationEditPart;
import rsldl.diagram.edit.parts.DLValidityCondition2EditPart;
import rsldl.diagram.edit.parts.DLValidityConditionEditPart;
import rsldl.diagram.part.RsldlDiagramEditorPlugin;

/**
 * @generated
 */
public class RsldlElementTypes {

	/**
	 * @generated
	 */
	private RsldlElementTypes() {
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
	public static final IElementType DLDiagram_1000 = getElementType("eu.redseeds.rsldl.diagram.DLDiagram_1000"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType DLEntity_2001 = getElementType("eu.redseeds.rsldl.diagram.DLEntity_2001"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType DLProperty_2002 = getElementType("eu.redseeds.rsldl.diagram.DLProperty_2002"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType DLPrimitive_2003 = getElementType("eu.redseeds.rsldl.diagram.DLPrimitive_2003"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType DLDataBasedReference_2004 = getElementType("eu.redseeds.rsldl.diagram.DLDataBasedReference_2004"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType DLPatternBasedReference_2005 = getElementType("eu.redseeds.rsldl.diagram.DLPatternBasedReference_2005"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType DLAlghoritmicTransition_2006 = getElementType("eu.redseeds.rsldl.diagram.DLAlghoritmicTransition_2006"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType DLPatternBasedTransition_2007 = getElementType("eu.redseeds.rsldl.diagram.DLPatternBasedTransition_2007"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType DLIdentityCondition_3001 = getElementType("eu.redseeds.rsldl.diagram.DLIdentityCondition_3001"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType DLInheritanceCondition_3002 = getElementType("eu.redseeds.rsldl.diagram.DLInheritanceCondition_3002"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType DLValidityCondition_3003 = getElementType("eu.redseeds.rsldl.diagram.DLValidityCondition_3003"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType DLIdentityCondition_3004 = getElementType("eu.redseeds.rsldl.diagram.DLIdentityCondition_3004"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType DLInheritanceCondition_3005 = getElementType("eu.redseeds.rsldl.diagram.DLInheritanceCondition_3005"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType DLValidityCondition_3006 = getElementType("eu.redseeds.rsldl.diagram.DLValidityCondition_3006"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType DLRelationshipParticipation_4001 = getElementType("eu.redseeds.rsldl.diagram.DLRelationshipParticipation_4001"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType DLRelationshipParticipation_4002 = getElementType("eu.redseeds.rsldl.diagram.DLRelationshipParticipation_4002"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType DLRelationshipParticipation_4003 = getElementType("eu.redseeds.rsldl.diagram.DLRelationshipParticipation_4003"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType DLAttributeLink_4004 = getElementType("eu.redseeds.rsldl.diagram.DLAttributeLink_4004"); //$NON-NLS-1$

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
				return RsldlDiagramEditorPlugin.getInstance()
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

			elements.put(DLDiagram_1000, RsldlPackage.eINSTANCE.getDLDiagram());

			elements.put(DLEntity_2001, RsldlPackage.eINSTANCE.getDLEntity());

			elements.put(DLProperty_2002,
					RsldlPackage.eINSTANCE.getDLProperty());

			elements.put(DLPrimitive_2003,
					RsldlPackage.eINSTANCE.getDLPrimitive());

			elements.put(DLDataBasedReference_2004,
					RsldlPackage.eINSTANCE.getDLDataBasedReference());

			elements.put(DLPatternBasedReference_2005,
					RsldlPackage.eINSTANCE.getDLPatternBasedReference());

			elements.put(DLAlghoritmicTransition_2006,
					RsldlPackage.eINSTANCE.getDLAlghoritmicTransition());

			elements.put(DLPatternBasedTransition_2007,
					RsldlPackage.eINSTANCE.getDLPatternBasedTransition());

			elements.put(DLIdentityCondition_3001,
					RsldlPackage.eINSTANCE.getDLIdentityCondition());

			elements.put(DLInheritanceCondition_3002,
					RsldlPackage.eINSTANCE.getDLInheritanceCondition());

			elements.put(DLValidityCondition_3003,
					RsldlPackage.eINSTANCE.getDLValidityCondition());

			elements.put(DLIdentityCondition_3004,
					RsldlPackage.eINSTANCE.getDLIdentityCondition());

			elements.put(DLInheritanceCondition_3005,
					RsldlPackage.eINSTANCE.getDLInheritanceCondition());

			elements.put(DLValidityCondition_3006,
					RsldlPackage.eINSTANCE.getDLValidityCondition());

			elements.put(DLRelationshipParticipation_4001,
					RsldlPackage.eINSTANCE.getDLRelationshipParticipation());

			elements.put(DLRelationshipParticipation_4002,
					RsldlPackage.eINSTANCE.getDLRelationshipParticipation());

			elements.put(DLRelationshipParticipation_4003,
					RsldlPackage.eINSTANCE.getDLRelationshipParticipation());

			elements.put(DLAttributeLink_4004,
					RsldlPackage.eINSTANCE.getDLAttributeLink());
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
			KNOWN_ELEMENT_TYPES.add(DLDiagram_1000);
			KNOWN_ELEMENT_TYPES.add(DLEntity_2001);
			KNOWN_ELEMENT_TYPES.add(DLProperty_2002);
			KNOWN_ELEMENT_TYPES.add(DLPrimitive_2003);
			KNOWN_ELEMENT_TYPES.add(DLDataBasedReference_2004);
			KNOWN_ELEMENT_TYPES.add(DLPatternBasedReference_2005);
			KNOWN_ELEMENT_TYPES.add(DLAlghoritmicTransition_2006);
			KNOWN_ELEMENT_TYPES.add(DLPatternBasedTransition_2007);
			KNOWN_ELEMENT_TYPES.add(DLIdentityCondition_3001);
			KNOWN_ELEMENT_TYPES.add(DLInheritanceCondition_3002);
			KNOWN_ELEMENT_TYPES.add(DLValidityCondition_3003);
			KNOWN_ELEMENT_TYPES.add(DLIdentityCondition_3004);
			KNOWN_ELEMENT_TYPES.add(DLInheritanceCondition_3005);
			KNOWN_ELEMENT_TYPES.add(DLValidityCondition_3006);
			KNOWN_ELEMENT_TYPES.add(DLRelationshipParticipation_4001);
			KNOWN_ELEMENT_TYPES.add(DLRelationshipParticipation_4002);
			KNOWN_ELEMENT_TYPES.add(DLRelationshipParticipation_4003);
			KNOWN_ELEMENT_TYPES.add(DLAttributeLink_4004);
		}
		return KNOWN_ELEMENT_TYPES.contains(elementType);
	}

	/**
	 * @generated
	 */
	public static IElementType getElementType(int visualID) {
		switch (visualID) {
		case DLDiagramEditPart.VISUAL_ID:
			return DLDiagram_1000;
		case DLEntityEditPart.VISUAL_ID:
			return DLEntity_2001;
		case DLPropertyEditPart.VISUAL_ID:
			return DLProperty_2002;
		case DLPrimitiveEditPart.VISUAL_ID:
			return DLPrimitive_2003;
		case DLDataBasedReferenceEditPart.VISUAL_ID:
			return DLDataBasedReference_2004;
		case DLPatternBasedReferenceEditPart.VISUAL_ID:
			return DLPatternBasedReference_2005;
		case DLAlghoritmicTransitionEditPart.VISUAL_ID:
			return DLAlghoritmicTransition_2006;
		case DLPatternBasedTransitionEditPart.VISUAL_ID:
			return DLPatternBasedTransition_2007;
		case DLIdentityConditionEditPart.VISUAL_ID:
			return DLIdentityCondition_3001;
		case DLInheritanceConditionEditPart.VISUAL_ID:
			return DLInheritanceCondition_3002;
		case DLValidityConditionEditPart.VISUAL_ID:
			return DLValidityCondition_3003;
		case DLIdentityCondition2EditPart.VISUAL_ID:
			return DLIdentityCondition_3004;
		case DLInheritanceCondition2EditPart.VISUAL_ID:
			return DLInheritanceCondition_3005;
		case DLValidityCondition2EditPart.VISUAL_ID:
			return DLValidityCondition_3006;
		case DLRelationshipParticipationEditPart.VISUAL_ID:
			return DLRelationshipParticipation_4001;
		case DLRelationshipParticipation2EditPart.VISUAL_ID:
			return DLRelationshipParticipation_4002;
		case DLRelationshipParticipation3EditPart.VISUAL_ID:
			return DLRelationshipParticipation_4003;
		case DLAttributeLinkEditPart.VISUAL_ID:
			return DLAttributeLink_4004;
		}
		return null;
	}

}
