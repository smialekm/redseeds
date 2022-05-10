package eu.remics.recovery.model.preferences;

public abstract class NameConverter implements INameConverter {
	
	@Override
    public final String convertName(String name) {
		return languageSpecyficConvertName(name).replaceAll("\\s*(\\n|\\r)+\\s*", " ");
    }
	
	protected abstract String languageSpecyficConvertName(String name);

    @Override
    public abstract String getSimpleName(String name);

    @Override
    public abstract String getValue(String name);
    
}
