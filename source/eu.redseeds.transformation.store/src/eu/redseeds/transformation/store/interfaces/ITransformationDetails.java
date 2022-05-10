package eu.redseeds.transformation.store.interfaces;

import java.util.List;

import eu.redseeds.scl.model.TransformationProfile;

public interface ITransformationDetails {
	/**
	 *  Returns Transformation Profile filled with all details.
	 */
	public TransformationProfile getTransformationDetails(TransformationProfile transformationProfile);
	
	/**
	 *  Returns a list of Transtomation Profiles.
	 */
	public List<TransformationProfile> getTransformationsList();

}
