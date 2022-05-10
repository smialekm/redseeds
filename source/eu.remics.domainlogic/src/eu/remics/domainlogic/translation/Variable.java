package eu.remics.domainlogic.translation;

public class Variable {
	
	private String name;
	private String type;

	public Variable(String name, String type) {
		this.name = name;
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public String getName() {
		return name;
	}
}
