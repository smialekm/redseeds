package eu.redseeds.sc.current.repository;

import java.util.Hashtable;
import java.util.logging.Level;

import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Status;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import de.uni_koblenz.jgralab.JGraLab;

import eu.redseeds.sc.current.repository.impl.CurrRepoContentImpl;
import eu.redseeds.sc.current.repository.interfaces.ICurrRepoContent;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends Plugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "eu.redseeds.sc.current.repository";

	// The shared instance
	private static Activator plugin;

	private ServiceRegistration currRepoContentService;
	
	/**
	 * The constructor
	 */
	public Activator() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.runtime.Plugins#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		
		//registering services
		currRepoContentService = context.registerService(
				ICurrRepoContent.class.getName(), 
				new CurrRepoContentImpl(),
				new Hashtable<String, Object>());
		
		JGraLab.setLogLevel(Level.SEVERE);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.runtime.Plugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		//unregistering services
		if(currRepoContentService != null) {
			currRepoContentService.unregister();
			currRepoContentService = null;
		}
			
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}
	
	public static void log(String msg, int status) {
		getDefault().getLog().log(
				new Status(status, PLUGIN_ID, msg));
	}

}
