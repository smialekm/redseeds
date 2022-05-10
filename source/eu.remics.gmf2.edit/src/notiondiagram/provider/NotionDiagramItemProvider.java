/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package notiondiagram.provider;


import java.util.Collection;
import java.util.List;

import notiondiagram.NotionDiagram;
import notiondiagram.NotiondiagramFactory;
import notiondiagram.NotiondiagramPackage;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.ResourceLocator;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ViewerNotification;

/**
 * This is the item provider adapter for a {@link notiondiagram.NotionDiagram} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class NotionDiagramItemProvider
	extends ItemProviderAdapter
	implements
		IEditingDomainItemProvider,
		IStructuredItemContentProvider,
		ITreeItemContentProvider,
		IItemLabelProvider,
		IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotionDiagramItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			addNamePropertyDescriptor(object);
			addPackagePropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Name feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addNamePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_NotionDiagram_Name_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_NotionDiagram_Name_feature", "_UI_NotionDiagram_type"),
				 NotiondiagramPackage.Literals.NOTION_DIAGRAM__NAME,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Package feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addPackagePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_NotionDiagram_Package_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_NotionDiagram_Package_feature", "_UI_NotionDiagram_type"),
				 NotiondiagramPackage.Literals.NOTION_DIAGRAM__PACKAGE,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.INTEGRAL_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
	 * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
	 * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object) {
		if (childrenFeatures == null) {
			super.getChildrenFeatures(object);
			childrenFeatures.add(NotiondiagramPackage.Literals.NOTION_DIAGRAM__RELATIONS);
			childrenFeatures.add(NotiondiagramPackage.Literals.NOTION_DIAGRAM__ATTRIBUTE_RELATIONS);
			childrenFeatures.add(NotiondiagramPackage.Literals.NOTION_DIAGRAM__GENERALIZATIONS);
			childrenFeatures.add(NotiondiagramPackage.Literals.NOTION_DIAGRAM__NOTIONS);
		}
		return childrenFeatures;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EStructuralFeature getChildFeature(Object object, Object child) {
		// Check the type of the specified child object and return the proper feature to use for
		// adding (see {@link AddCommand}) it as a child.

		return super.getChildFeature(object, child);
	}

	/**
	 * This returns NotionDiagram.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/NotionDiagram"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((NotionDiagram)object).getName();
		return label == null || label.length() == 0 ?
			getString("_UI_NotionDiagram_type") :
			getString("_UI_NotionDiagram_type") + " " + label;
	}

	/**
	 * This handles model notifications by calling {@link #updateChildren} to update any cached
	 * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void notifyChanged(Notification notification) {
		updateChildren(notification);

		switch (notification.getFeatureID(NotionDiagram.class)) {
			case NotiondiagramPackage.NOTION_DIAGRAM__NAME:
			case NotiondiagramPackage.NOTION_DIAGRAM__PACKAGE:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
			case NotiondiagramPackage.NOTION_DIAGRAM__RELATIONS:
			case NotiondiagramPackage.NOTION_DIAGRAM__ATTRIBUTE_RELATIONS:
			case NotiondiagramPackage.NOTION_DIAGRAM__GENERALIZATIONS:
			case NotiondiagramPackage.NOTION_DIAGRAM__NOTIONS:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
				return;
		}
		super.notifyChanged(notification);
	}

	/**
	 * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
	 * that can be created under this object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);

		newChildDescriptors.add
			(createChildParameter
				(NotiondiagramPackage.Literals.NOTION_DIAGRAM__RELATIONS,
				 NotiondiagramFactory.eINSTANCE.createDirectedRelation()));

		newChildDescriptors.add
			(createChildParameter
				(NotiondiagramPackage.Literals.NOTION_DIAGRAM__RELATIONS,
				 NotiondiagramFactory.eINSTANCE.createIndirectRelation()));

		newChildDescriptors.add
			(createChildParameter
				(NotiondiagramPackage.Literals.NOTION_DIAGRAM__ATTRIBUTE_RELATIONS,
				 NotiondiagramFactory.eINSTANCE.createAttributeRelation()));

		newChildDescriptors.add
			(createChildParameter
				(NotiondiagramPackage.Literals.NOTION_DIAGRAM__GENERALIZATIONS,
				 NotiondiagramFactory.eINSTANCE.createGeneralization()));

		newChildDescriptors.add
			(createChildParameter
				(NotiondiagramPackage.Literals.NOTION_DIAGRAM__NOTIONS,
				 NotiondiagramFactory.eINSTANCE.createNotion()));
	}

	/**
	 * Return the resource locator for this item provider's resources.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		return NotionDiagramEditPlugin.INSTANCE;
	}

}
