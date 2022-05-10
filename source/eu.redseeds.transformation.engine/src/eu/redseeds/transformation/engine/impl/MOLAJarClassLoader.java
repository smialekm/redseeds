package eu.redseeds.transformation.engine.impl;

import java.io.IOException;
import java.util.jar.JarFile;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;
import java.io.ByteArrayOutputStream;
import eu.redseeds.transformation.engine.Activator;


/**
 * A class loader for loading MOLA transformations (jar files).
 */
class MOLAJarClassLoader extends ClassLoader {
	  private JarFile jarFile;
	  private JarEntry jarEntry;

	  /**
	   * Creates a new MOLAJarClassLoader for the specified jarFile and JarEntry.
	   *
	   * @param jarFile the jarFile where the transformation is located
	   * @param jarEntry the entry for transformation
	   * @param parentClLoader 
	   */
	  public MOLAJarClassLoader(JarFile jarFile, JarEntry jarEntry, ClassLoader parentClLoader) {
			super(parentClLoader);
		  	this.jarFile = jarFile;
		  	this.jarEntry = jarEntry;
	  }
    
    /**
     * Loads and return class specified by 'name' from jar
     * 
     * @param name 
     */
    public Class<?> loadClass(String name) throws ClassNotFoundException  {
    	
    	//Activator.log("loadClass1: " + name, Status.INFO);
    	
    	// Make sure not to load duplicate classes.
        Class<?> cls = findLoadedClass(name);

        try {
	        if (cls == null) {
	        	cls = super.loadClass(name); // try to resolve "system" classes
	        	//cls = super.findLoadedClass(name);
	        	//cls = super.findClass(name);
	        }
        } catch (Exception e) { //guard for classes which should be loaded by this classloader
        	// do nothing 
        	//Activator.log("exception loadClass24: ", Status.INFO);
		}
        
        if (cls != null) return cls;
    	//Activator.log("loadClass2: ", Status.INFO);

    	ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {

        	JarInputStream jis = new JarInputStream(jarFile.getInputStream(jarEntry));        
	    	byte[] buf = new byte[1024];
	        JarEntry je;
	        String entryName = name.replace('.', '/').concat(".class");  
        
			je = jis.getNextJarEntry();
	        while (je != null) {
	        	//Activator.log("loadClass3: " + je.getName(), Status.INFO);
	        	//Activator.log("loadClass4: " + je.getSize(), Status.INFO);
		        if (je.getName().equals(entryName)) {
		            while (true) {
		            	int len = jis.read(buf, 0, 1024);
		            	if (len < 0) break;        	
		            	baos.write(buf, 0, len); 
		            } 	
		        }
		        je = jis.getNextJarEntry();
	        }    
	        //Activator.log("after while baos.size(): " + baos.size(), Status.INFO);
	        jis.close();
		} catch (IOException e) {
			Activator.logException(e);
		}
		
        byte bytes[] = baos.toByteArray();

        //Activator.log("bytes.length: " + bytes.length, Status.INFO);
    	//Activator.log("baos.length: " + baos.size(), Status.INFO);
    	//Activator.log("MOLAJarClassLoader.loadClass(<name>): " + name, Status.INFO);
	    return defineClass(name, bytes, 0, bytes.length, MOLAJarClassLoader.class.getProtectionDomain());
    }    
 }
