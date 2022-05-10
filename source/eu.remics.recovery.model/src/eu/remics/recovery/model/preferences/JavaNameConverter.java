package eu.remics.recovery.model.preferences;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JavaNameConverter extends NameConverter {

	public JavaNameConverter() {}

	@Override
    public String languageSpecyficConvertName(String name) {
		Matcher match = Pattern.compile("\\s*(\\n|\\r)+\\s*").matcher(name);
        return (match.find())?name.substring(0, match.start()):name;
    }

    @Override
    public String getSimpleName(String name) {
    	Matcher match = Pattern.compile("\\s*(\\n|\\r)+\\s*").matcher(name);
        return (match.find())?name.substring(0, match.start()):name;
    }

    @Override
    public String getValue(String name) {
    	Matcher match = Pattern.compile("\\s*(\\n|\\r)+\\s*").matcher(name);
        return (match.find())?name.substring(0, match.start()):name;
    }
    
}
