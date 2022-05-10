/*
 * JGraLab - The Java graph laboratory
 * (c) 2006-2007 Institute for Software Technology
 *               University of Koblenz-Landau, Germany
 *
 *               ist@uni-koblenz.de
 *
 * Please report bugs to http://serres.uni-koblenz.de/bugzilla
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
 
package eu.redseeds.owl.converter.uko_adapted;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class HelperMethods {
	/**
	 * Returns the element which has an {@code rdf:ID} attribute with the given id as value. 
	 * 
	 * @param id The value for {@code rdf:ID} which shall be searched for.
	 * @return The element which has an {@code rdf:ID} attribute with the given id as value.
	 */
	static Element getOwlElement(Document doc, String id) {
		Node currentElem = doc.getFirstChild().getFirstChild();
		String currentID = ((Element)currentElem).getAttribute("rdf:ID");
				
		while (!currentID.equals(id)) {
			currentElem = currentElem.getNextSibling();
			currentID = ((Element)currentElem).getAttribute("rdf:ID");
		}
		
		return (Element)currentElem;
	}
	
	/**
	 * Changes the first character of {@code string} to lower case and returns the
	 * resulting String.
	 * 
	 * @param string String whose first character shall be changed to lower case.
	 * @return The given String with its first character changed to lower case.
	 */
	public static String firstToLowerCase(String string) {
		return Character.toLowerCase(string.charAt(0)) + string.substring(1);
	}
	
	/**
	 * Changes the first character of {@code string} to upper case and returns the
	 * resulting String.
	 * 
	 * @param string String whose first character shall be changed to upper case.
	 * @return The given String with its first character changed to upper case.
	 */
	static String firstToUpperCase(String string) {
		return Character.toUpperCase(string.charAt(0)) + string.substring(1);
	}
	
	static String createBase64Representation(Object o) {
		StringBuffer b64 = null;
		
		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(bos);
			oos.writeObject(o);
			oos.close();
			b64 = new StringBuffer(Base64.encodeBytes(bos
					.toByteArray()));
			int p = b64.indexOf("\n");
			while (p >= 0) {
				b64.deleteCharAt(p);
				p = b64.indexOf("\n", p);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
			
		return b64.toString();
	}
}
