package eu.redset.tsl.editor.editors;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;

import eu.redset.tsl.editor.Activator;


public class ImageCache { 

	private static Map<ImageDescriptor, Image> imageCache = new HashMap<ImageDescriptor, Image>(); 

	public static Image getImage(String name){
		// prepare the icon path in a string buffer 
		StringBuffer iconPathBuffer = new StringBuffer();
		//		iconPathBuffer.append("icons"); 
		//		iconPathBuffer.append(System.getProperty("file.separator")); 
		iconPathBuffer.append(name);

		ImageDescriptor descriptor 
			= Activator.imageDescriptorFromPlugin(Activator.PLUGIN_ID, iconPathBuffer.toString());
		Image image = (Image) imageCache.get(descriptor);
		if(image == null) {
			image = descriptor.createImage();
			imageCache.put(descriptor, image);
		}
		iconPathBuffer.delete(0, iconPathBuffer.length());
		iconPathBuffer = null;
		return image;
	} 
}
