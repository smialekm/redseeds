package eu.redseeds.scl.model;

import java.util.List;

/**
 * A MOLA transformation profile. Contains name, type and properties (used in
 * customisation of the profile). Points at TransformationRuntime used by the
 * transformation engine.
 */
public class TransformationProfile {

	private String name;
	protected List<TransformationProperty> transformationProperties;
	protected TransformationRuntime transformationRuntime;
	protected TransformationType type;

	public TransformationProfile(){

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<TransformationProperty> getTransformationProperties() {
		return transformationProperties;
	}

	public void setTransformationProperties(
			List<TransformationProperty> transformationProperties) {
		this.transformationProperties = transformationProperties;
	}

	public TransformationRuntime getTransformationRuntime() {
		return transformationRuntime;
	}

	public void setTransformationRuntime(TransformationRuntime transformationRuntime) {
		this.transformationRuntime = transformationRuntime;
	}

	public TransformationType getType() {
		return type;
	}

	public void setType(TransformationType type) {
		this.type = type;
	}
	
	public String toString() {
	    return  this.name + " : " + this.type.getType();
	}
}