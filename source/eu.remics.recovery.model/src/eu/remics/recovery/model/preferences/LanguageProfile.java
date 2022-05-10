package eu.remics.recovery.model.preferences;

public class LanguageProfile {

	String name,keyword,nameConverter; //domain name, key to property value used in map for that domain (null if without key) and object of class which converts names for that domain
	
    public LanguageProfile() {}

	public LanguageProfile(String name, String keyword, String miNameConverter) {
		this.name = name;
		this.keyword = keyword;
		this.nameConverter = miNameConverter;
	}
    
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getKeyword() {
		return keyword;
	}
	
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	/**
	 * Returns instance of name converter
	 * 
	 * @return instance of name converter
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 */
	public INameConverter getNameConverterInstance() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		return (INameConverter) Class.forName(nameConverter).newInstance();
	}

	public String getNameConverter() {
		return nameConverter;
	}

	public void setNameConverter(String nameConverter) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		this.nameConverter = nameConverter;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LanguageProfile other = (LanguageProfile) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
}
