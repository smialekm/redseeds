package eu.redseeds.transformation.engine.impl;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
//import java.net.JarURLConnection;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarFile;
import lv.lu.mii.lx.repository.ILxModelRepository;
import eu.redseeds.scl.model.TransformationRuntime;
import eu.redseeds.transformation.engine.Activator;

/**
 * MOLA transformation executer 
 */
public class MOLATransfomationInvoker {
	
	private Map<String, MOLATransformationRuntime> molaTransfClasses;
	private static MOLATransfomationInvoker instance;
	
	public static MOLATransfomationInvoker instance() { // singletone pattern implementation
		if (instance == null) {
			instance = new MOLATransfomationInvoker();
		}
		return instance;
	}
	
	private MOLATransfomationInvoker() {
		molaTransfClasses = new HashMap<String, MOLATransformationRuntime>();
	}
	
	/**
	 * Execute MOLA transformation on data defined by repo parameter
	 *  
	 */
	private void invokeTransformation(MOLATransformationRuntime molaTransfRuntime, 
			                          ILxModelRepository <?, ?, ?> repo) throws 
	          	IllegalArgumentException, 
	          	InstantiationException, 
	          	IllegalAccessException, 
	          	InvocationTargetException {
		Object tmpObj = molaTransfRuntime.ctr.newInstance(new Object[] { repo });
		molaTransfRuntime.molaProcedure.invoke(tmpObj, new Object[] {} );
		    	
	}

     /**
     * Invokes the transformation defined in TransformationRuntime.getJarURL() jar file. 
     * Transformation will be executed on data defined by "repo" parameter.
     * 
     * @param transfRuntime object where all information about transformation jar is kept
     * @param repo instance of the IRepository 
     * @exception ClassNotFoundException if the specified class could not
     *            be found
     * @exception NoSuchMethodException if the specified class does not
     *            contain a "main" method
     * @exception InvocationTargetException if the application raised an
     *            exception
     *            ...
     */		
	public void invokeMOLATransformation(TransformationRuntime transfRuntime,
			                             ILxModelRepository <?, ?, ?> repo) throws 
					ClassNotFoundException,
					NoSuchMethodException, 
					IllegalArgumentException, 
					InstantiationException, 
					IllegalAccessException, 
					InvocationTargetException,
					MalformedURLException,
					IOException {
		
		//JarURLConnection uc = (JarURLConnection)transfRuntime.getJarURL().openConnection();
		//JarFile pluginJarFile = uc.getJarFile();
		JarFile pluginJarFile = new JarFile(transfRuntime.getJarFilePath());
		String keyForMap = pluginJarFile.getName() + "\\" + transfRuntime.getJarEntry().getName();

		MOLATransformationRuntime molaTransfRuntime = molaTransfClasses.get(keyForMap);
		if (molaTransfRuntime != null) {
			invokeTransformation(molaTransfRuntime, repo); //invoke transformation
			return;
		}
		
    	// Create the class loader for the embedded jar file
    	MOLAJarClassLoader molaClLoader = new MOLAJarClassLoader(pluginJarFile, transfRuntime.getJarEntry(), Activator.class.getClassLoader());

		//create MOLATransformationRuntime and invoke transformation 
		molaTransfRuntime = new MOLATransformationRuntime();
		molaTransfRuntime.jarFile = pluginJarFile.getName();
    	
   		molaTransfRuntime.mainClassName = transfRuntime.getMainClass();
      	molaTransfRuntime.mainClass = molaClLoader.loadClass(molaTransfRuntime.mainClassName);
   		molaTransfRuntime.ctr = molaTransfRuntime.mainClass.getConstructor(new Class[] {ILxModelRepository.class});
     	molaTransfRuntime.molaProcedure = molaTransfRuntime.mainClass.getMethod(transfRuntime.getProcedure(), new Class[] {});
     	molaTransfRuntime.molaProcedure.setAccessible(true);
    	int mods = molaTransfRuntime.molaProcedure.getModifiers();
    	if (molaTransfRuntime.molaProcedure.getReturnType() != void.class || !Modifier.isPublic(mods)) {
    		throw new NoSuchMethodException(transfRuntime.getProcedure());
    	}
    	molaTransfClasses.put(keyForMap, molaTransfRuntime);
    	invokeTransformation(molaTransfRuntime, repo);
	}
}
