/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package eu.redset.emf.model.tsl.provider;


import eu.redset.emf.model.tsl.TestPackage;
import eu.redset.emf.model.tsl.TslFactory;
import eu.redset.emf.model.tsl.TslPackage;

import java.util.Collection;
import java.util.List;

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
 * This is the item provider adapter for a {@link eu.redset.emf.model.tsl.TestPackage} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class TestPackageItemProvider
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
	public TestPackageItemProvider(AdapterFactory adapterFactory) {
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
				 getString("_UI_TestPackage_name_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_TestPackage_name_feature", "_UI_TestPackage_type"),
				 TslPackage.Literals.TEST_PACKAGE__NAME,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
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
			childrenFeatures.add(TslPackage.Literals.TEST_PACKAGE__EREFERENCE0);
			childrenFeatures.add(TslPackage.Literals.TEST_PACKAGE__EREFERENCE1);
			childrenFeatures.add(TslPackage.Literals.TEST_PACKAGE__EREFERENCE3);
			childrenFeatures.add(TslPackage.Literals.TEST_PACKAGE__NOTIONS);
			childrenFeatures.add(TslPackage.Literals.TEST_PACKAGE__NF_TESTS);
			childrenFeatures.add(TslPackage.Literals.TEST_PACKAGE__GUI_TESTS);
			childrenFeatures.add(TslPackage.Literals.TEST_PACKAGE__BL_TESTS);
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
	 * This returns TestPackage.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/TestPackage"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((TestPackage)object).getName();
		return label == null || label.length() == 0 ?
			getString("_UI_TestPackage_type") :
			getString("_UI_TestPackage_type") + " " + label;
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

		switch (notification.getFeatureID(TestPackage.class)) {
			case TslPackage.TEST_PACKAGE__NAME:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
			case TslPackage.TEST_PACKAGE__EREFERENCE0:
			case TslPackage.TEST_PACKAGE__EREFERENCE1:
			case TslPackage.TEST_PACKAGE__EREFERENCE3:
			case TslPackage.TEST_PACKAGE__NOTIONS:
			case TslPackage.TEST_PACKAGE__NF_TESTS:
			case TslPackage.TEST_PACKAGE__GUI_TESTS:
			case TslPackage.TEST_PACKAGE__BL_TESTS:
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
				(TslPackage.Literals.TEST_PACKAGE__EREFERENCE0,
				 TslFactory.eINSTANCE.createTestPackage()));

		newChildDescriptors.add
			(createChildParameter
				(TslPackage.Literals.TEST_PACKAGE__EREFERENCE1,
				 TslFactory.eINSTANCE.createUseCaseTest()));

		newChildDescriptors.add
			(createChildParameter
				(TslPackage.Literals.TEST_PACKAGE__EREFERENCE3,
				 TslFactory.eINSTANCE.createTestScenario()));

		newChildDescriptors.add
			(createChildParameter
				(TslPackage.Literals.TEST_PACKAGE__NOTIONS,
				 TslFactory.eINSTANCE.createNotion()));

		newChildDescriptors.add
			(createChildParameter
				(TslPackage.Literals.TEST_PACKAGE__NF_TESTS,
				 TslFactory.eINSTANCE.createNFTest()));

		newChildDescriptors.add
			(createChildParameter
				(TslPackage.Literals.TEST_PACKAGE__GUI_TESTS,
				 TslFactory.eINSTANCE.createGUITest()));

		newChildDescriptors.add
			(createChildParameter
				(TslPackage.Literals.TEST_PACKAGE__BL_TESTS,
				 TslFactory.eINSTANCE.createBLTest()));
	}

	/**
	 * Return the resource locator for this item provider's resources.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		return TslEditPlugin.INSTANCE;
	}

}
