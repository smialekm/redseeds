package eu.redseeds.transformation.store.interfaces;

import eu.redseeds.scl.model.TransformationProfile;

public interface ITransformationExchange {
	/**
	 *  Returns Transformation Profile.
	 */
	public TransformationProfile getTransfomation(int transformationId);

}
