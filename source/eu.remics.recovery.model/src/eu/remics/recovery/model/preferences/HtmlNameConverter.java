package eu.remics.recovery.model.preferences;

public class HtmlNameConverter extends NameConverter {

	public HtmlNameConverter() {}

	@Override
    public String languageSpecyficConvertName(String name) {
        return name;
    }

    @Override
    public String getSimpleName(String name) {
        return name.substring(1,name.indexOf(": "));
    }

    @Override
    public String getValue(String name) {
        return name.substring(name.indexOf(": ")+2);
    }
    
}
