/**
 */
package rsldl.provider;


import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;

import rsldl.DLCustomAlghoritmicTransitionStep;
import rsldl.RsldlFactory;
import rsldl.RsldlPackage;

/**
 * This is the item provider adapter for a {@link rsldl.DLCustomAlghoritmicTransitionStep} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class DLCustomAlghoritmicTransitionStepItemProvider extends DLAlghoritmicTransitionStepItemProvider {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DLCustomAlghoritmicTransitionStepItemProvider(AdapterFactory adapterFactory) {
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

		}
		return itemPropertyDescriptors;
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
			childrenFeatures.add(RsldlPackage.Literals.DL_CUSTOM_ALGHORITMIC_TRANSITION_STEP__ALTERNATIVE);
			childrenFeatures.add(RsldlPackage.Literals.DL_CUSTOM_ALGHORITMIC_TRANSITION_STEP__ACTION);
			childrenFeatures.add(RsldlPackage.Literals.DL_CUSTOM_ALGHORITMIC_TRANSITION_STEP__CONDITION);
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
	 * This returns DLCustomAlghoritmicTransitionStep.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/DLCustomAlghoritmicTransitionStep"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		return getString("_UI_DLCustomAlghoritmicTransitionStep_type");
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

		switch (notification.getFeatureID(DLCustomAlghoritmicTransitionStep.class)) {
			case RsldlPackage.DL_CUSTOM_ALGHORITMIC_TRANSITION_STEP__ALTERNATIVE:
			case RsldlPackage.DL_CUSTOM_ALGHORITMIC_TRANSITION_STEP__ACTION:
			case RsldlPackage.DL_CUSTOM_ALGHORITMIC_TRANSITION_STEP__CONDITION:
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
				(RsldlPackage.Literals.DL_CUSTOM_ALGHORITMIC_TRANSITION_STEP__ALTERNATIVE,
				 RsldlFactory.eINSTANCE.createDLCustomAlghoritmicTransitionStep()));

		newChildDescriptors.add
			(createChildParameter
				(RsldlPackage.Literals.DL_CUSTOM_ALGHORITMIC_TRANSITION_STEP__ALTERNATIVE,
				 RsldlFactory.eINSTANCE.createDLPredefinedAlghoritmicTransitionStep()));

		newChildDescriptors.add
			(createChildParameter
				(RsldlPackage.Literals.DL_CUSTOM_ALGHORITMIC_TRANSITION_STEP__ALTERNATIVE,
				 RsldlFactory.eINSTANCE.createDLControlFlowStatement()));

		newChildDescriptors.add
			(createChildParameter
				(RsldlPackage.Literals.DL_CUSTOM_ALGHORITMIC_TRANSITION_STEP__ACTION,
				 RsldlFactory.eINSTANCE.createDLTransitionPattern()));

		newChildDescriptors.add
			(createChildParameter
				(RsldlPackage.Literals.DL_CUSTOM_ALGHORITMIC_TRANSITION_STEP__CONDITION,
				 RsldlFactory.eINSTANCE.createDLConditionPattern()));
	}

	/**
	 * This returns the label text for {@link org.eclipse.emf.edit.command.CreateChildCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getCreateChildText(Object owner, Object feature, Object child, Collection<?> selection) {
		Object childFeature = feature;
		Object childObject = child;

		boolean qualify =
			childFeature == RsldlPackage.Literals.DL_ALGHORITMIC_TRANSITION_SEQUENCE_ELEMENT__SEQUENT ||
			childFeature == RsldlPackage.Literals.DL_CUSTOM_ALGHORITMIC_TRANSITION_STEP__ALTERNATIVE;

		if (qualify) {
			return getString
				("_UI_CreateChild_text2",
				 new Object[] { getTypeText(childObject), getFeatureText(childFeature), getTypeText(owner) });
		}
		return super.getCreateChildText(owner, feature, child, selection);
	}

}
