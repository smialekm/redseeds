package eu.redseeds.rsldl.util;

public class Util {
	
	public static boolean equals(Object a, Object b){
		if (null==a){
			if (null==b)
				return true;
			else return false;
		} else return a.equals(b);
	}
	
	public static String toCamelCase(String text) {
        String r = "";
        String[] split = text.split(" ");
        for (String s : split) {
            r += s.substring(0, 1).toUpperCase() + s.substring(1);
        }
        return r;
    }

    public static String toLowerCamelCase(String text) {
        String r = toCamelCase(text);
        return r.substring(0, 1).toLowerCase() + r.substring(1);
    }
    
}
