package eu.remics.recovery.model.preferences;

public class WinNameConverter extends NameConverter {

	public WinNameConverter() {}

	@Override
    public String languageSpecyficConvertName(String name) {
		return name.substring(name.indexOf(": ")+2);
    }

    @Override
    public String getSimpleName(String name) {
        return name.substring(name.indexOf(": ")+2);
    }

    @Override
    public String getValue(String name) {
        return name.substring(0,name.indexOf(": "));
    }
    
}
