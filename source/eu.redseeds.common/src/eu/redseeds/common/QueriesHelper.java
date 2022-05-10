package eu.redseeds.common;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QueriesHelper {

	public static String getDescription(String desc){
		Matcher match = Pattern.compile("<rsldl>.*</rsldl>").matcher(desc);
		if (!match.find())
			return desc;
		return desc.substring(0,match.start())+desc.substring(match.end());
	}
	
	public static String[] getParameters(String desc){
		Matcher match = Pattern.compile("<rsldl>.*</rsldl>").matcher(desc);
		if (!match.find())
			return new String[3];
		String rsldl = desc.substring(match.start()+7,match.end()-8);;
		String[] res = new String[3];
		res[0] = getType(rsldl);
		res[1] = getSubject(rsldl);
		res[2] = getDomain(rsldl);
		return res;
	}
	
	public static String getType(String desc){
		Matcher match = Pattern.compile("<type>.*</type>").matcher(desc);
		if (!match.find())
			return null;
		return desc.substring(match.start()+6,match.end()-7);
	}
	
	public static String getSubject(String desc){
		Matcher match = Pattern.compile("<subject>.*</subject>").matcher(desc);
		if (!match.find())
			return null;
		return desc.substring(match.start()+9,match.end()-10);
	}
	
	public static String getDomain(String desc){
		Matcher match = Pattern.compile("<domain>.*</domain>").matcher(desc);
		if (!match.find())
			return null;
		String d = desc.substring(match.start()+8,match.end()-9);
		return d.isEmpty()?null:d;
	}

	public static Map<Integer,String> getCodeLinks(String desc) {
		Matcher match = Pattern.compile("<rsldl>.*</rsldl>").matcher(desc);
		if (!match.find())
			return null;
		String rsldl = desc.substring(match.start()+7,match.end()-8);
		match = Pattern.compile("<codeLinks>.*</codeLinks>").matcher(rsldl);
		if (!match.find())
			return null;
		String codeLinksFragment = rsldl.substring(match.start()+11,match.end()-12);
		Map<Integer,String> cl = new HashMap<Integer, String>();
		match = Pattern.compile("<codeLink id=\"(\\d+)\">(.*)</codeLink>").matcher(codeLinksFragment);
		while(match.find())
			cl.put(Integer.parseInt(match.group(1)), match.group(2));
		return cl;
	}
	
	public static String getCodeLink(String desc) {
		Matcher match = Pattern.compile("<rsldl>.*</rsldl>").matcher(desc);
		if (!match.find())
			return null;
		String rsldl = desc.substring(match.start()+7,match.end()-8);
		match = Pattern.compile("<codeLinks>.*</codeLinks>").matcher(rsldl);
		if (!match.find())
			return null;
		String codeLinksFragment = rsldl.substring(match.start()+11,match.end()-12);
		match = Pattern.compile("<codeLink>.*</codeLink>").matcher(codeLinksFragment);
		if (!match.find())
			return null;
		return codeLinksFragment.substring(match.start()+10,match.end()-11);
	}
	
	public static String getRSLDLDescription(String type, String subject, String domain, Map<Integer,String> codeLinks){
		return getRSLDLDescription(type, subject, domain, codeLinks, null);
	}
	
	public static String getRSLDLDescription(String type, String subject, String domain, String code){
		return getRSLDLDescription(type, subject, domain, null, code);
	}
		
		
	
	public static String getRSLDLDescription(String type, String subject, String domain, Map<Integer,String> codeLinks, String code){
		String s = "<rsldl>";
		if (null!=type)
			s+="<type>"+type+"</type>";
		if (null!=subject)
			s+="<subject>"+subject+"</subject>";
		if (null!=domain && !domain.isEmpty())
			s+="<domain>"+domain+"</domain>";
		if (null!=codeLinks){
			s+="<codeLinks>";
			if(null!=codeLinks) 
				for(Integer k:codeLinks.keySet())
					s+="<codeLink id=\""+k+"\">"+codeLinks.get(k)+"</codeLink>";
			if(null!=code) 
				s+="<codeLink>"+code+"</codeLink>";
			s+="</codeLinks>";
		}
		return s+"</rsldl>";
	}
	
}
