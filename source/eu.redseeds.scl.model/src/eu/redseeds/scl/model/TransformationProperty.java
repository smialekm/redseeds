package eu.redseeds.scl.model;

/**
 * Holds name and value of a transformation parameter which customize model
 * transformations without modifying transformation rules and transformation
 * runtime code.
 */
public class TransformationProperty {

	private String name;
	private String value;

	public TransformationProperty(){

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}