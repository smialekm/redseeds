package eu.redset.navigator;

import org.eclipse.core.runtime.Status;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import eu.redseeds.sc.current.repository.interfaces.ICurrRepoContent;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "eu.redset.navigator"; //$NON-NLS-1$

	// The shared instance
	private static Activator plugin;
	
	//service reference & interfaces caching
	ICurrRepoContent currRepo = null;
	
	/**
	 * The constructor
	 */
	public Activator() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
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

	/**
	 * Returns an image descriptor for the image file at the given
	 * plug-in relative path
	 *
	 * @param path the path
	 * @return the image descriptor
	 */
	public static ImageDescriptor getImageDescriptor(String path) {
		return imageDescriptorFromPlugin(PLUGIN_ID, path);
	}
	
	public static void log(String msg, int status) {
		getDefault().getLog().log(
				new Status(status, PLUGIN_ID, msg));
	}
	
	public ICurrRepoContent getICurrRepoContentInstance() {
		
		
		if (currRepo != null) {
			return currRepo;
		}

		BundleContext bc = Activator.getDefault().getBundle().getBundleContext();
		ServiceReference currRepoRef = bc.getServiceReference(ICurrRepoContent.class.getName());

		if (currRepoRef != null) {
			ICurrRepoContent currRepo = (ICurrRepoContent) bc
					.getService(currRepoRef);
			if (currRepo != null) {
				return currRepo;
			} else {
				Activator.log("Service not obtained!", Status.WARNING);
				return null;
			}
		} else {
			Activator.log("No service for " + ICurrRepoContent.class.getName()
					+ " found!", Status.WARNING);
			return null;
		}
	}
}
